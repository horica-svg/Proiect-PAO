package Entitati;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // AngajatService angajatService = AngajatService.getInstance();

        // // Adăugăm angajati
        // Manager manager = new Manager(2, "Maria", "Ionescu", 5000, 40, "vânzări");
        // Ingrijitor ingrijitor = new Ingrijitor(1, "Ion", "Popescu", 3000, 30, "ursi");
        // Veterinar veterinar = new Veterinar(3, "Andrei", "Popa", 4000, 35, "chirurgie");
        // angajatService.create(ingrijitor);
        // angajatService.create(manager);
        // angajatService.create(veterinar);

        // // Citim toti angajatii
        // // Obtinem toti angajatii din baza de date
        // List<Angajat> angajati = angajatService.getAllAngajati();

        // // Afisam toti angajatii
        // System.out.println("Lista angajatilor:");
        // for (Angajat angajat : angajati) {
        //     System.out.println(angajat);
        // }

        // EvenimentService evenimentService = EvenimentService.getInstance();
        // Eveniment eveniment = new Eveniment(1, "Concert", LocalDate.of(2023, 10, 15), "Concert de muzica clasica", 100.0);
        // evenimentService.create(eveniment);

        // List<Eveniment> evenimente = evenimentService.getEvenimente();

        // System.out.println("Lista evenimentelor:");
        // for (Eveniment e : evenimente) {
        //     System.out.println(e);
        // }

        // HabitatService habitatService = HabitatService.getInstance();
        // Habitat habitat = new Habitat(1, "terestru", 1000.0);
        // Habitat habitat2 = new Habitat(2, "acvatic", 2000.0);
        // habitatService.create(habitat);
        // habitatService.create(habitat2);
        // List<Habitat> habitate = habitatService.getHabitate();
        // System.out.println("Lista habitatelor:");
        // for (Habitat h : habitate) {
        //     System.out.println(h);
        // }

        ExponatService exponatService = ExponatService.getInstance();
        Exponat exponat = new Exponat(1, "Leu", "panthera", 5, 1);
        Exponat exponat2 = new Exponat(2, "Delfin", "delphinus", 3, 2);

        exponatService.create(exponat);
        exponatService.create(exponat2);

        List<Exponat> exponate = exponatService.getExponate();
        System.out.println("Lista exponatelor:");
        for (Exponat e : exponate) {
            System.out.println(e);
        }

        
    }
}
