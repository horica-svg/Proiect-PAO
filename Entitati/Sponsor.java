package Entitati;

public class Sponsor {
    private int id;
    private String nume;
    private double sumaDonata;

    public Sponsor(int id, String nume, double sumaDonata) {
        this.id = id;
        this.nume = nume;
        this.sumaDonata = sumaDonata;
    }

    public Sponsor() {
        this.id = 0;
        this.nume = "";
        this.sumaDonata = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getSumaDonata() {
        return sumaDonata;
    }

    public void setSumaDonata(double sumaDonata) {
        this.sumaDonata = sumaDonata;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", sumaDonata=" + sumaDonata +
                '}';
    }
}
