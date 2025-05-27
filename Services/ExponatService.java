package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entitati.CRUDService;
import Entitati.Exponat;
import Utils.CSVFileLogger;
import Utils.DatabaseConnection;

public class ExponatService extends DatabaseConnection implements CRUDService<Exponat> {
    private static ExponatService instance;

    // Constructor privat pentru Singleton
    private ExponatService() {
        super(); // Apelăm constructorul clasei părinte (DatabaseConnection)
    }

    // Metodă pentru obținerea unei instanțe singleton
    public static ExponatService getInstance() {
        if (instance == null) {
            instance = new ExponatService();
        }
        return instance;
    }

    /**
     * Creează un nou exponat în baza de date.
     *
     * @param exponat Obiectul Exponat care trebuie creat.
     */
    @Override
    public void create(Exponat exponat) {
        String sql = "INSERT INTO Exponat (nume, specie, varsta, idHabitat) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exponat.getNume());
            stmt.setString(2, exponat.getSpecie());
            stmt.setInt(3, exponat.getVarsta());
            stmt.setInt(4, exponat.getHabitatId()); // Cheia străină către tabela Habitat
            // stmt.setInt(5, exponat.getId());
            stmt.executeUpdate();

            // Recuperăm id-ul generat de baza de date
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                exponat.setId(generatedKeys.getInt(1)); // Setăm id-ul în obiectul Java
            }
            CSVFileLogger.getInstance().logAction("create_exponat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Citește un exponat din baza de date pe baza ID-ului.
     *
     * @param id ID-ul exponatului.
     * @return Obiectul Exponat corespunzător ID-ului sau null dacă nu există.
     */
    @Override
    public Exponat read(int id) {
        String sql = "SELECT * FROM Exponat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Exponat(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("specie"),
                    rs.getInt("varsta"),
                    rs.getInt("idHabitat") // Cheia străină către tabela Habitat
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Actualizează un exponat existent în baza de date.
     *
     * @param exponat Obiectul Exponat care trebuie actualizat.
     */
    @Override
    public void update(Exponat exponat) {
        String sql = "UPDATE Exponat SET nume = ?, specie = ?, varsta = ?, idHabitat = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, exponat.getNume());
            stmt.setString(2, exponat.getSpecie());
            stmt.setInt(3, exponat.getVarsta());
            stmt.setInt(4, exponat.getHabitatId()); // Cheia străină către tabela Habitat
            stmt.setInt(5, exponat.getId());
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("update_exponat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Șterge un exponat din baza de date pe baza ID-ului.
     *
     * @param id ID-ul exponatului care trebuie șters.
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Exponat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("delete_exponat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returnează toate exponatele asociate unui anumit habitat.
     *
     * @param habitatId ID-ul habitatului.
     * @return Lista de exponate asociate habitatului.
     */
    public List<Exponat> getExponateByHabitat(int habitatId) {
        List<Exponat> exponate = new ArrayList<>();
        String sql = "SELECT * FROM Exponat WHERE idHabitat = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, habitatId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                exponate.add(new Exponat(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("specie"),
                    rs.getInt("varsta"),
                    rs.getInt("idHabitat") // Cheia străină către tabela Habitat
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exponate;
    }

    public List<Exponat> getExponate() {
        List<Exponat> exponate = new ArrayList<>();
        String sql = "SELECT * FROM Exponat";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                exponate.add(new Exponat(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("specie"),
                    rs.getInt("varsta"),
                    rs.getInt("idHabitat") // Cheia străină către tabela Habitat
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exponate;
    }
}