package Menus;

import javax.swing.*;

import Entitati.Eveniment;
import Services.EvenimentService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MeniuEveniment extends JFrame {
    private EvenimentService evenimentService;

    public MeniuEveniment(EvenimentService evenimentService) {
        this.evenimentService = evenimentService;

        setTitle("Meniu Eveniment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAdaugaEveniment = new JButton("Adauga Eveniment");
        JButton btnAfiseazaEvenimente = new JButton("Afiseaza Evenimente");
        JButton btnIesire = new JButton("Inapoiaza la Meniu Principal");

        btnAdaugaEveniment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaEveniment();
            }
        });

        btnAfiseazaEvenimente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afiseazaEvenimente();
            }
        });

        btnIesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Închide fereastra curentă
            }
        });

        panel.add(btnAdaugaEveniment);
        panel.add(btnAfiseazaEvenimente);
        panel.add(btnIesire);

        add(panel);
    }

    private void adaugaEveniment() {
        JTextField idField = new JTextField();
        JTextField numeField = new JTextField();
        JTextField dataField = new JTextField();
        JTextField descriereField = new JTextField();
        JTextField pretBiletField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID Eveniment:"));
        panel.add(idField);
        panel.add(new JLabel("Nume:"));
        panel.add(numeField);
        panel.add(new JLabel("Data (yyyy-MM-dd):"));
        panel.add(dataField);
        panel.add(new JLabel("Descriere:"));
        panel.add(descriereField);
        panel.add(new JLabel("Preț Bilet:"));
        panel.add(pretBiletField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Eveniment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
            String nume = numeField.getText();
            LocalDate data = LocalDate.parse(dataField.getText());
            String descriere = descriereField.getText();
            double pretBilet = Double.parseDouble(pretBiletField.getText());

            Eveniment eveniment = new Eveniment(id, nume, data, descriere, pretBilet);
            evenimentService.create(eveniment);
            JOptionPane.showMessageDialog(this, "Evenimentul a fost adăugat cu succes!");
        }
    }

    private void afiseazaEvenimente() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        for (Eveniment eveniment : evenimentService.getEvenimente()) {
            textArea.append(eveniment.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Evenimente", JOptionPane.INFORMATION_MESSAGE);
    }
}
