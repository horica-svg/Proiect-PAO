package Entitati;

public class Habitat {
    private String tip;
    private double arie;

    public Habitat(String tip, double arie) {
        this.tip = tip;
        this.arie = arie;
    }

    // Getters și setters

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
