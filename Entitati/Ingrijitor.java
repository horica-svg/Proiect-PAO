package Entitati;

public class Ingrijitor extends Angajat implements Comparable<Ingrijitor> {
    private String sectie;

    public Ingrijitor(int id, String nume, String prenume, double salariu, int varsta, String sectie) {
        super(id, nume, prenume, salariu, varsta, "ingrijitor");
        this.sectie = sectie;
    }

    public Ingrijitor(){
        super(0, "", "", 0, 0, "ingrijitor");
        this.sectie = "";
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
