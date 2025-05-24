package Entitati;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SponsorService sponsorService = new SponsorService();

        // Creare sponsor
        Sponsor sponsor1 = new Sponsor(1, "Sponsor A", 1000.0);
        sponsorService.create(sponsor1);

        // Citire sponsor
        Sponsor retrievedSponsor = sponsorService.read(sponsor1.getId());
        if (retrievedSponsor == null) {
            System.out.println("Sponsor not found.");
            return;
        }
        System.out.println("Retrieved Sponsor: " + retrievedSponsor);

        // Actualizare sponsor
        retrievedSponsor.setSumaDonata(1500.0);
        sponsorService.update(retrievedSponsor);
        System.out.println("Updated Sponsor: " + sponsorService.read(retrievedSponsor.getId()));

    }
}
