package Entitati;

public class Veterinar extends Angajat {
    private String specializare;

    public Veterinar(int id, String nume, String prenume, double salariu, int varsta, String specializare) {
        super(id, nume, prenume, salariu, varsta, "veterinar");
        this.specializare = specializare;
    }

    @Override
    public void efectueazaSarcina() {
        System.out.println("Veterinarul " + nume + " " + prenume + " efectueaza consultatii medicale.");
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specializare: " + specializare;
    }
}
