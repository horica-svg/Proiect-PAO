package Entitati;

public class Vizitator implements Comparable<Vizitator>{
    private String nume;
    private String prenume;
    private String email;
    private int id;

    public Vizitator(int id, String nume, String prenume, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.id = id;
    }

    public Vizitator() {
        this.nume = "";
        this.prenume = "";
        this.email = "";
        this.id = 0;
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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Vizitator altVizitator){
        return this.nume.compareTo(altVizitator.nume);
    }

    @Override
    public String toString(){
        return "Vizitator: " + nume + " " + prenume + ", Email: " + email;
    }
}
