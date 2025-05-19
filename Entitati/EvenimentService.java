package Entitati;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenimentService extends DatabaseConnection implements CRUDService<Eveniment> {
    private static EvenimentService instance;

    // Constructor privat pentru Singleton
    private EvenimentService() {
        super(); // Apelăm constructorul clasei părinte (DatabaseService)
    }

    // Metodă pentru obținerea unei instanțe singleton
    public static EvenimentService getInstance() {
        if (instance == null) {
            instance = new EvenimentService();
        }
        return instance;
    }

    /**
     * Creează un nou eveniment în baza de date.
     *
     * @param eveniment Obiectul Eveniment care trebuie creat.
     */
    @Override
    public void create(Eveniment eveniment) {
        String sql = "INSERT INTO Eveniment (numeEveniment, data, descriere, pretBilet) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, eveniment.getNumeEveniment());
            stmt.setDate(2, Date.valueOf(eveniment.getDataEveniment()));
            stmt.setString(3, eveniment.getDescriereEveniment());
            stmt.setDouble(4, eveniment.getPretBilet());
            stmt.executeUpdate();

            // Recuperăm id-ul generat de baza de date
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                eveniment.setId(generatedKeys.getInt(1)); // Setăm id-ul în obiectul Java
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Eveniment read(int id) {
        String sql = "SELECT * FROM Eveniment WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Eveniment(
                        rs.getInt("id"),
                        rs.getString("numeEveniment"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("descriere"),
                        rs.getDouble("pretBilet")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Eveniment eveniment) {
        String sql = "UPDATE Eveniment SET numeEveniment = ?, data = ?, descriere = ?, pretBilet = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eveniment.getNumeEveniment());
            stmt.setDate(2, Date.valueOf(eveniment.getDataEveniment()));
            stmt.setString(3, eveniment.getDescriereEveniment());
            stmt.setDouble(4, eveniment.getPretBilet());
            stmt.setInt(5, eveniment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Eveniment WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Eveniment> getEvenimente() {
        List<Eveniment> evenimente = new ArrayList<>();
        String sql = "SELECT * FROM Eveniment";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nume Eveniment: " + rs.getString("numeEveniment"));
                System.out.println("Data Eveniment: " + rs.getDate("data"));
                System.out.println("Descriere Eveniment: " + rs.getString("descriere"));
                System.out.println("Pret Bilet: " + rs.getDouble("pretBilet"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenimente;
    }
}