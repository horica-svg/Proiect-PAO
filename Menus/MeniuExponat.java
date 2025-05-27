package Menus;

import javax.swing.*;

import Entitati.Exponat;
import Services.ExponatService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuExponat extends JFrame {
    private ExponatService exponatService;

    public MeniuExponat(ExponatService exponatService) {
        this.exponatService = exponatService;

        setTitle("Meniu Exponate");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAdaugaExponat = new JButton("Adauga Exponat");
        JButton btnAfiseazaExponate = new JButton("Afiseaza Exponate");
        JButton btnIesire = new JButton("Inapoi la Meniu Principal");

        btnAdaugaExponat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaExponat();
            }
        });

        btnAfiseazaExponate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afiseazaExponate();
            }
        });

        btnIesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Închide fereastra curentă
            }
        });

        panel.add(btnAdaugaExponat);
        panel.add(btnAfiseazaExponate);
        panel.add(btnIesire);

        add(panel);
    }

    private void adaugaExponat() {
        JTextField idField = new JTextField();
        JTextField numeField = new JTextField();
        JTextField specieField = new JTextField();
        JTextField varstaField = new JTextField();
        JTextField habitatIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID Exponat:"));
        panel.add(idField);
        panel.add(new JLabel("Nume:"));
        panel.add(numeField);
        panel.add(new JLabel("Specie:"));
        panel.add(specieField);
        panel.add(new JLabel("Varsta:"));
        panel.add(varstaField);
        panel.add(new JLabel("Id-ul Habitatului:"));
        panel.add(habitatIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Exponat", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
            String nume = numeField.getText();
            String specie = specieField.getText();
            int varsta = varstaField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());
            int habitatId = habitatIdField.getText().isEmpty() ? 0 : Integer.parseInt(habitatIdField.getText());

            Exponat exponat = new Exponat(id, nume, specie, varsta, habitatId);
            exponatService.create(exponat);
            JOptionPane.showMessageDialog(this, "Exponatul a fost adăugat cu succes!");
        }
    }

    private void afiseazaExponate() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        for (Exponat exponat : exponatService.getExponate()) {
            textArea.append(exponat.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Exponate:", JOptionPane.INFORMATION_MESSAGE);
    }
}
