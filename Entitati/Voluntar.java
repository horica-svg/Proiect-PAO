package Entitati;

public class Voluntar extends Angajat implements Comparable<Voluntar> {
    private String program;

    public Voluntar(int id, String nume, String prenume, double salariu, int varsta, String program) {
        super(id, nume, prenume, salariu, varsta, "voluntar");
        this.program = program;
    }

    @Override
    public void efectueazaSarcina() {
        System.out.println("Voluntarul " + nume + " " + prenume + " ajuta la activitatile din programul " + program);
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return super.toString() + ", Program: " + program;
    }

    @Override
    public int compareTo(Voluntar altVoluntar) {
        return this.nume.compareTo(altVoluntar.nume);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Voluntar)) return false;
        Voluntar altVoluntar = (Voluntar) obj;
        return this.nume.equals(altVoluntar.nume) && this.prenume.equals(altVoluntar.prenume);
    }
}
