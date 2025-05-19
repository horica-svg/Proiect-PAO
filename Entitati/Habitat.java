package Entitati;

public class Habitat {
    private String tip;
    private double arie;
    private int id;

    public Habitat(int id, String tip, double arie) {
        this.id = id;
        this.tip = tip;
        this.arie = arie;
    }

    public Habitat() {
        this.id = 0;
        this.tip = "";
        this.arie = 0.0;
    }

    // Getters și setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getArie() {
        return arie;
    }

    public void setArie(double arie) {
        this.arie = arie;
    }

    @Override
    public String toString() {
        return "Habitat: " + tip + ", Arie: " + arie + " m²";
    }
}
