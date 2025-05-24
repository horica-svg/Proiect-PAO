package Entitati;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatService extends DatabaseConnection implements CRUDService<Angajat> {
    private static AngajatService instance;

    // Constructor privat pentru Singleton
    private AngajatService() {
        super(); // Apelăm constructorul clasei părinte (DatabaseService)
    }

    // Metodă pentru obținerea unei instanțe singleton
    public static AngajatService getInstance() {
        if (instance == null) {
            instance = new AngajatService();
        }
        return instance;
    }

    /**
     * Creează un nou angajat în baza de date.
     *
     * @param angajat Obiectul Angajat care trebuie creat.
     */
    @Override
    public void create(Angajat angajat) {
        String sql = "INSERT INTO Angajat (nume, prenume, salariu, varsta, tip) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, angajat.getNume());
            stmt.setString(2, angajat.getPrenume());
            stmt.setDouble(3, angajat.getSalariu());
            stmt.setInt(4, angajat.getVarsta());
            stmt.setString(5, angajat.getTip());
            stmt.executeUpdate();

            // Recuperăm id-ul generat de baza de date
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                angajat.setId(generatedKeys.getInt(1)); // Setăm id-ul în obiectul Java

                if (angajat instanceof Ingrijitor) {
                String sqlIngrijitor = "INSERT INTO Ingrijitor (id, sectie) VALUES (?, ?)";
                try (PreparedStatement stmt2 = conn.prepareStatement(sqlIngrijitor)) {
                    stmt2.setInt(1, angajat.getId());
                    stmt2.setString(2, ((Ingrijitor) angajat).getSectie());
                    stmt2.executeUpdate();
                }
            } else if (angajat instanceof Manager) {
                String sqlManager = "INSERT INTO Manager (id, departament) VALUES (?, ?)";
                try (PreparedStatement stmt2 = conn.prepareStatement(sqlManager)) {
                    stmt2.setInt(1, angajat.getId());
                    stmt2.setString(2, ((Manager) angajat).getDepartament());
                    stmt2.executeUpdate();
                }
            } else if (angajat instanceof Veterinar) {
                String sqlVeterinar = "INSERT INTO Veterinar (id, specializare) VALUES (?, ?)";
                try (PreparedStatement stmt2 = conn.prepareStatement(sqlVeterinar)) {
                    stmt2.setInt(1, angajat.getId());
                    stmt2.setString(2, ((Veterinar) angajat).getSpecializare());
                    stmt2.executeUpdate();
                }
            }
            // Logăm acțiunea
            CSVFileLogger.getInstance().logAction("create_angajat");
        }
    } catch (SQLException e) {
        System.err.println("Eroare la crearea angajatului: " + e.getMessage());
        e.printStackTrace();
    }
}

    /**
     * Citește un angajat din baza de date pe baza ID-ului.
     *
     * @param id ID-ul angajatului.
     * @return Obiectul Angajat corespunzător ID-ului sau null dacă nu există.
     */
    @Override
    public Angajat read(int id) {
        String sql = "SELECT * FROM Angajat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tip = rs.getString("tip");
                switch (tip) {
                    case "ingrijitor":
                        return new Ingrijitor(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("sectie")
                        );
                    case "manager":
                        return new Manager(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("departament")
                        );
                    case "veterinar":
                        return new Veterinar(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("specializare")
                        );
                    default:
                        throw new IllegalArgumentException("Tipul angajatului este invalid: " + tip);
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la citirea angajatului: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        String sql = "SELECT a.*, i.sectie, m.departament, v.specializare " +
                    "FROM Angajat a " +
                    "LEFT JOIN Ingrijitor i ON a.id = i.id " +
                    "LEFT JOIN Manager m ON a.id = m.id " +
                    "LEFT JOIN Veterinar v ON a.id = v.id";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tip = rs.getString("tip");
                switch (tip) {
                    case "ingrijitor":
                        angajati.add(new Ingrijitor(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("sectie")
                        ));
                        break;
                    case "manager":
                        angajati.add(new Manager(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("departament")
                        ));
                        break;
                    case "veterinar":
                        angajati.add(new Veterinar(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getDouble("salariu"),
                            rs.getInt("varsta"),
                            rs.getString("specializare")
                        ));
                        break;
                    default:
                        throw new IllegalArgumentException("Tipul angajatului este invalid: " + tip);
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la obținerea angajaților: " + e.getMessage());
            e.printStackTrace();
        }
        return angajati;
    }

    /**
     * Actualizează un angajat existent în baza de date.
     *
     * @param angajat Obiectul Angajat care trebuie actualizat.
     */
    @Override
    public void update(Angajat angajat) {
        String sql = "UPDATE Angajat SET nume = ?, prenume = ?, salariu = ?, varsta = ?, tip = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, angajat.getNume());
            stmt.setString(2, angajat.getPrenume());
            stmt.setDouble(3, angajat.getSalariu());
            stmt.setInt(4, angajat.getVarsta());
            stmt.setString(5, angajat.getTip());
            stmt.setInt(6, angajat.getId());
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("update_angajat");
        } catch (SQLException e) {
            System.err.println("Eroare la actualizarea angajatului: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Șterge un angajat din baza de date pe baza ID-ului.
     *
     * @param id ID-ul angajatului care trebuie șters.
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Angajat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("delete_angajat");
        } catch (SQLException e) {
            System.err.println("Eroare la ștergerea angajatului: " + e.getMessage());
            e.printStackTrace();
        }
    }
}