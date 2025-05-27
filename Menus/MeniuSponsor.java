package Menus;

import javax.swing.*;

import Entitati.Sponsor;
import Services.SponsorService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuSponsor extends JFrame {
    private SponsorService sponsorService;

    public MeniuSponsor(SponsorService sponsorService) {
        this.sponsorService = sponsorService;

        setTitle("Meniu Sponsor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAdaugaSponsor = new JButton("Adauga Sponsor");
        JButton btnAfiseazaSponsori = new JButton("Afiseaza Sponsorii");
        JButton btnIesire = new JButton("Inapoi la Meniu Principal");

        btnAdaugaSponsor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaSponsor();
            }
        });

        btnAfiseazaSponsori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afiseazaSponsori();
            }
        });

        btnIesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Închide fereastra curentă
            }
        });

        panel.add(btnAdaugaSponsor);
        panel.add(btnAfiseazaSponsori);
        panel.add(btnIesire);

        add(panel);
    }

    private void adaugaSponsor() {
        JTextField idField = new JTextField();
        JTextField numeField = new JTextField();
        JTextField sumaDonataField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID Sponsor:"));
        panel.add(idField);
        panel.add(new JLabel("Numele Sponsorului:"));
        panel.add(numeField);
        panel.add(new JLabel("Suma donata de acesta:"));
        panel.add(sumaDonataField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Sponsor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
            String nume = numeField.getText();
            double sumaDonata = Double.parseDouble(sumaDonataField.getText());

            Sponsor sponsor = new Sponsor(id, nume, sumaDonata);
            sponsorService.create(sponsor);
            JOptionPane.showMessageDialog(this, "Sponsorul a fost adăugat cu succes!");
        }
    }

    private void afiseazaSponsori() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        for (Sponsor sponsor : sponsorService.getSponsori()) {
            textArea.append(sponsor.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "SPonsori", JOptionPane.INFORMATION_MESSAGE);
    }
}
