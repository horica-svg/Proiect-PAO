package Entitati;

public abstract class Angajat {
    protected String nume;
    protected String prenume;
    protected double salariu;
    protected int varsta;
    protected int id;
    private String tip;

    public Angajat(int id, String nume, String prenume, double salariu, int varsta, String tip) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.varsta = varsta;
        this.tip = tip;
    }

    public abstract void efectueazaSarcina();

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", salariu=" + salariu +
                ", varsta=" + varsta +
                ", tip='" + tip + '\'' +
                '}';
    }
}
