package Entitati;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ZooService zooService = new ZooService();

        Habitat padure = new Habitat("Padure de conifere", 500);
        Exponat urs = new Exponat("Urs brun", "ursulache", 7, padure);
        zooService.adaugaExponat(urs);

        Veterinar veterinar = new Veterinar("Pompiliu", "Diocletian", 250000, 45, "veterinar generalist");
        Manager manager = new Manager("Sucu", "Dan", 5600000, 57, "Mobexpert");
        Ingrijitor ingrijitor = new Ingrijitor("Ionel", "Popescu", 20000, 30, "ursi");

        zooService.adaugaAngajat(veterinar);
        zooService.adaugaAngajat(manager);
        zooService.adaugaAngajat(ingrijitor);

        System.out.println("Activitatile angajatilor:");
        zooService.AfiseazaActivitatiAngajati();

        System.out.println("\nSalariul total al angajatilor: " + zooService.calculeazaSalariuTotal());

        Vizitator vizitator1 = new Vizitator("Ion", "Popescu", "ionpopy@gmail.com");
        Vizitator vizitator2 = new Vizitator("Costache", "Costi", "costicosti@hotmal.ro");
        Vizitator vizitator3 = new Vizitator("Habosh", "Babosh", "Shlipton@gmail.com");

        zooService.adaugaVizitator(vizitator1);
        zooService.adaugaVizitator(vizitator2);
        zooService.adaugaVizitator(vizitator3);

        System.out.println("\nVizitatorii:");
        zooService.AfiseazaVizitatori();

        zooService.stergeVizitator("Habosh");

        System.out.println("\nVizitatorii dupa stergere:\n");
        zooService.AfiseazaVizitatori();

        Eveniment eveniment1 = new Eveniment("Ziua Ursului",LocalDate.of(2024,2,5), "O zi dedicata ursilor", 100);
        Eveniment eveniment2 = new Eveniment("Ziua Elefantului", LocalDate.of(2023, 10, 2), "O zi dedicata elefantilor", 150);

        zooService.adaugaEveniment(eveniment1);
        zooService.adaugaEveniment(eveniment2);

        System.out.println("\nEvenimentele:\n");
        zooService.AfiseazaEvenimente();
    }
}
