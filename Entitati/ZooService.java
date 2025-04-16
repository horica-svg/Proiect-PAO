package Entitati;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

public class ZooService {
    private TreeSet<Vizitator> vizitatori = new TreeSet<>();
    private List<Angajat> angajati = new ArrayList<>();
    private TreeSet<Exponat> exponate = new TreeSet<>();
    private TreeSet<Eveniment> evenimente = new TreeSet<>();

    public void adaugaEveniment(Eveniment eveniment){
        evenimente.add(eveniment);
    }

    public TreeSet<Eveniment> getEvenimente(){
        return evenimente;
    }

    public void AfiseazaEvenimente(){
        for(Eveniment eveniment : evenimente){
            System.out.println(eveniment);
        }
    }

    public void adaugaVizitator(Vizitator vizitator){
        vizitatori.add(vizitator);
    }

    public TreeSet<Vizitator> getVizitatori(){
        return vizitatori;
    }

    public void AfiseazaVizitatori(){
        for(Vizitator vizitator : vizitatori){
            System.out.println(vizitator);
        }
    }

    public void adaugaAngajat(Angajat angajat){
        angajati.add(angajat);
    }

    public List<Angajat> getAngajati(){
        return angajati;
    }

    public void AfiseazaActivitatiAngajati(){
        for(Angajat angajat : angajati){
            angajat.efectueazaSarcina();
        }
    }

    public double calculeazaSalariuTotal(){
        double total = 0;
        for(Angajat angajat : angajati){
            total += angajat.getSalariu();
        }
        return total;
    }

    public void adaugaExponat(Exponat exponat){
        exponate.add(exponat);
    }
    public TreeSet<Exponat> getExponate(){
        return exponate;
    }

    public boolean stergeVizitator(String nume){
        for (Vizitator vizitator : vizitatori) {
            if (vizitator.getNume().equals(nume)) {
                vizitatori.remove(vizitator);
                System.out.println("Vizitatorul " + nume + " a fost sters.");
                return true;
            }
        }
        System.out.println("Vizitatorul " + nume + " nu a fost gasit.");
        return false;
    }

    public boolean stergeEveniment(String nume){
        for (Eveniment eveniment : evenimente) {
            if (eveniment.getNumeEveniment().equals(nume)) {
                evenimente.remove(eveniment);
                System.out.println("Evenimentul " + nume + " a fost sters.");
                return true;
            }
        }
        System.out.println("Evenimentul " + nume + " nu a fost gasit.");
        return false;
    }

    public boolean stergeExponat(String nume){
        for (Exponat exponat : exponate) {
            if (exponat.getNume().equals(nume)) {
                exponate.remove(exponat);
                System.out.println("Exponatul " + nume + " a fost sters.");
                return true;
            }
        }
        System.out.println("Exponatul " + nume + " nu a fost gasit.");
        return false;
    }
}
