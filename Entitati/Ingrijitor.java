package Entitati;

public class Ingrijitor extends Angajat implements Comparable<Ingrijitor> {
    private String sectie;

    public Ingrijitor(String nume, String prenume, double salariu, int varsta, String sectie) {
        super(nume, prenume, salariu, varsta);
        this.sectie = sectie;
    }

    @Override
    public void efectueazaSarcina() {
        System.out.println("Ingrijitorul " + nume + " " + prenume + " ingrijeste animalele din sectia " + sectie);
    }

    public String getSectie() {
        return sectie;
    }

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sectie: " + sectie;
    }

    @Override
    public int compareTo(Ingrijitor altIngrijitor) {
        return this.nume.compareTo(altIngrijitor.nume);
    }
}
