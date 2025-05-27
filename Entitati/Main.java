package Entitati;

import javax.swing.*;

import Menus.MeniuGrafic;
import Services.EvenimentService;
import Services.ExponatService;
import Services.HabitatService;
import Services.SponsorService;

public class Main {
    public static void main(String[] args) {
        SponsorService sponsorService = SponsorService.getInstance();
        EvenimentService evenimentService = EvenimentService.getInstance();
        ExponatService exponentService = ExponatService.getInstance();
        HabitatService habitatService = HabitatService.getInstance();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MeniuGrafic(sponsorService, evenimentService, exponentService, habitatService);
            frame.setVisible(true);
        });
    }
}
