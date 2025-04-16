package Entitati;

public class Manager extends Angajat {
    private String departament;

    public Manager(String nume, String prenume, double salariu, int varsta, String departament) {
        super(nume, prenume, salariu, varsta);
        this.departament = departament;
    }

    @Override
    public void efectueazaSarcina() {
        System.out.println("Managerul " + nume + " " + prenume + " coordoneaza departamentul " + departament);
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return super.toString() + ", Departament: " + departament;
    }
}
