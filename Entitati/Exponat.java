package Entitati;

public class Exponat implements Comparable<Exponat> {
    private int id;
    private String nume;
    private String specie;
    private int varsta;
    private int habitatId;

    public Exponat(int id, String nume, String specie, int varsta, int habitatId) {
        this.id = id;
        this.nume = nume;
        this.specie = specie;
        this.varsta = varsta;
        this.habitatId = habitatId;
    }

    public Exponat() {
        this.id = 0;
        this.nume = "";
        this.specie = "";
        this.varsta = 0;
        this.habitatId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHabitatId() {
        return habitatId;
    }

    public void setHabitatId(int habitatId) {
        this.habitatId = habitatId;
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
