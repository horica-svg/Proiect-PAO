package Menus;

import javax.swing.*;

import Services.EvenimentService;
import Services.ExponatService;
import Services.HabitatService;
import Services.SponsorService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuGrafic extends JFrame {
    private SponsorService sponsorService;
    private EvenimentService evenimentService;
    private ExponatService exponatService;
    private HabitatService habitatService;

    public MeniuGrafic(SponsorService sponsorService, EvenimentService evenimentService, ExponatService exponatService, HabitatService habitatService) {
        this.sponsorService = sponsorService;
        this.evenimentService = evenimentService;
        this.exponatService = exponatService;
        this.habitatService = habitatService;

        setTitle("Meniu Zoo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3 ,1 ,10 ,10));

        JButton btnGestionareSponsor = new JButton("Gestionare Sponsor");
        JButton btnGestionareEveniment = new JButton("Gestionare Eveniment");
        JButton btnGestionareExponat = new JButton("Gestionare Exponat");
        JButton btnGestionareHabitat = new JButton("Gestionare Habitat");
        JButton btnIesire = new JButton("Iesire");

        btnGestionareEveniment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MeniuEveniment(evenimentService).setVisible(true);
            }
        });

        btnGestionareSponsor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MeniuSponsor(sponsorService).setVisible(true);
            }
        });

        btnGestionareExponat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MeniuExponat(exponatService).setVisible(true);
            }
        });

        btnGestionareHabitat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MeniuHabitat(habitatService).setVisible(true);
            }
        });

        btnIesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(btnGestionareSponsor);
        panel.add(btnGestionareEveniment);
        panel.add(btnGestionareExponat);
        panel.add(btnGestionareHabitat);
        panel.add(btnIesire);

        add(panel);
    }
}
