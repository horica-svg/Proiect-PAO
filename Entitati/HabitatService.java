package Entitati;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HabitatService extends DatabaseConnection implements CRUDService<Habitat> {
    private static HabitatService instance;

    // Constructor privat pentru Singleton
    private HabitatService() {
        super(); // Apelăm constructorul clasei părinte (DatabaseService)
    }

    // Metodă pentru obținerea unei instanțe singleton
    public static HabitatService getInstance() {
        if (instance == null) {
            instance = new HabitatService();
        }
        return instance;
    }

    /**
     * Creează un nou eveniment în baza de date.
     *
     * @param habitat Obiectul Eveniment care trebuie creat.
     */
    @Override
    public void create(Habitat habitat) {
        String sql = "INSERT INTO Habitat (arie, tip) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, habitat.getArie());
            stmt.setString(2, habitat.getTip());
            stmt.executeUpdate();

            // Recuperăm id-ul generat de baza de date
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                habitat.setId(generatedKeys.getInt(1)); // Setăm id-ul în obiectul Java
            }
            CSVFileLogger.getInstance().logAction("create_habitat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Habitat read(int id) {
        String sql = "SELECT * FROM Habitat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Habitat(
                        rs.getInt("id"),
                        rs.getString("tip"),
                        rs.getDouble("arie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Habitat habitat) {
        String sql = "UPDATE Habitat SET arie = ?, tip = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, habitat.getArie());
            stmt.setString(2, habitat.getTip());
            stmt.setInt(3, habitat.getId());
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("update_habitat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Habitat WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("delete_habitat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Habitat> getHabitate() {
        List<Habitat> habitate = new ArrayList<>();
        String sql = "SELECT * FROM Habitat";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Suprafata: " + rs.getDouble("arie"));
                System.out.println("Tipul habitatului: " + rs.getString("tip"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitate;
    }
}