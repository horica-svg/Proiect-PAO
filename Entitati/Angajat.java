package Entitati;

public abstract class Angajat {
    protected String nume;
    protected String prenume;
    protected double salariu;
    protected int varsta;

    public Angajat(String nume, String prenume, double salariu, int varsta) {
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.varsta = varsta;
    }

    public abstract void efectueazaSarcina();

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Angajat: " + nume + " " + prenume + ", Salariu: " + salariu + ", Varsta: " + varsta;
    }
}
