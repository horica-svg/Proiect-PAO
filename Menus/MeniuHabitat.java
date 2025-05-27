package Menus;

import javax.swing.*;

import Entitati.Habitat;
import Services.HabitatService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuHabitat extends JFrame {
    private HabitatService habitatService;

    public MeniuHabitat(HabitatService habitatService) {
        this.habitatService = habitatService;

        setTitle("Meniu Habitat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAdaugaHabitat = new JButton("Adauga Habitat");
        JButton btnAfiseazaHabitate = new JButton("Afiseaza Habitat");
        JButton btnIesire = new JButton("Inapoi la Meniu Principal");

        btnAdaugaHabitat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaHabitat();
            }
        });

        btnAfiseazaHabitate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afiseazaHabitate();
            }
        });

        btnIesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Închide fereastra curentă
            }
        });

        panel.add(btnAdaugaHabitat);
        panel.add(btnAfiseazaHabitate);
        panel.add(btnIesire);

        add(panel);
    }

    private void adaugaHabitat() {
        JTextField idField = new JTextField();
        JTextField tipField = new JTextField();
        JTextField arieField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID Habitat:"));
        panel.add(idField);
        panel.add(new JLabel("Tip:"));
        panel.add(tipField);
        panel.add(new JLabel("Aria:"));
        panel.add(arieField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Habitat", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
            String tip = tipField.getText();
            double arie = Double.parseDouble(arieField.getText());

            Habitat habitat = new Habitat(id, tip, arie);
            habitatService.create(habitat);
            JOptionPane.showMessageDialog(this, "Habitatul a fost adăugat cu succes!");
        }
    }

    private void afiseazaHabitate() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        for (Habitat habitat : habitatService.getHabitate()) {
            textArea.append(habitat.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Evenimente", JOptionPane.INFORMATION_MESSAGE);
    }
}
