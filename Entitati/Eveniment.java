package Entitati;

import java.time.LocalDate;

public class Eveniment  implements Comparable<Eveniment> {
    private int id;
    private String numeEveniment;
    private LocalDate dataEveniment;
    private String descriereEveniment;
    private double pretBilet;

    public Eveniment(int id, String numeEveniment, LocalDate dataEveniment, String descriereEveniment, double pretBilet) {
        this.id = id;
        this.numeEveniment = numeEveniment;
        this.dataEveniment = dataEveniment;
        this.descriereEveniment = descriereEveniment;
        this.pretBilet = pretBilet;
    }

    public Eveniment() {
        this.id = 0;
        this.numeEveniment = "";
        this.dataEveniment = LocalDate.now();
        this.descriereEveniment = "";
        this.pretBilet = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeEveniment() {
        return numeEveniment;
    }

    public void setNumeEveniment(String numeEveniment) {
        this.numeEveniment = numeEveniment;
    }

    public LocalDate getDataEveniment() {
        return dataEveniment;
    }

    public void setDataEveniment(LocalDate dataEveniment) {
        this.dataEveniment = dataEveniment;
    }

    public String getDescriereEveniment() {
        return descriereEveniment;
    }

    public void setDescriereEveniment(String descriereEveniment) {
        this.descriereEveniment = descriereEveniment;
    }

    public double getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(double pretBilet) {
        this.pretBilet = pretBilet;
    }

    @Override
    public String toString() {
        return "Eveniment: " + numeEveniment + ", Data: " + dataEveniment + ", Descriere: " + descriereEveniment + ", Pret bilet: " + pretBilet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Eveniment eveniment = (Eveniment) obj;
        return Double.compare(eveniment.pretBilet, pretBilet) == 0 &&
                numeEveniment.equals(eveniment.numeEveniment) &&
                dataEveniment.equals(eveniment.dataEveniment) &&
                descriereEveniment.equals(eveniment.descriereEveniment);
    }

    public int compareTo(Eveniment altEveniment) {
        return this.dataEveniment.compareTo(altEveniment.dataEveniment);
    }

    

}
