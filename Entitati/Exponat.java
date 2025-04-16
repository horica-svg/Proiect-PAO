package Entitati;

public class Exponat implements Comparable<Exponat> {
    private String nume;
    private String specie;
    private int varsta;
    private Habitat habitat;

    public Exponat(String nume, String specie, int varsta, Habitat habitat) {
        this.nume = nume;
        this.specie = specie;
        this.varsta = varsta;
        this.habitat = habitat;
    }

    // Getters și setters
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "Exponat: " + nume + ", Specie: " + specie + ", Varsta: " + varsta;
    }

    @Override
    public int compareTo(Exponat altExponat) {
        return this.nume.compareTo(altExponat.nume);
    }
}
