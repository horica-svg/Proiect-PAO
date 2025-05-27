package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entitati.CRUDService;
import Entitati.Sponsor;
import Utils.CSVFileLogger;
import Utils.DatabaseConnection;

public class SponsorService extends DatabaseConnection implements CRUDService<Sponsor> {
    private SponsorService() {
        super();
    }

    private static SponsorService instance;

    public static SponsorService getInstance() {
        if (instance == null) {
            instance = new SponsorService();
        }
        return instance;
    }

    @Override
    public void create(Sponsor sponsor) {
        String sql = "INSERT INTO Sponsor (nume, sumaDonata) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sponsor.getNume());
            stmt.setDouble(2, sponsor.getSumaDonata());
            stmt.executeUpdate();

            // Recuperăm id-ul generat de baza de date
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                sponsor.setId(generatedKeys.getInt(1)); // Setăm id-ul în obiectul Java
            }
            CSVFileLogger.getInstance().logAction("create_sponsor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sponsor read(int id) {
        String sql = "SELECT * FROM Sponsor WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Sponsor(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getDouble("sumaDonata")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Sponsor sponsor) {
        String sql = "UPDATE Sponsor SET nume = ?, sumaDonata = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sponsor.getNume());
            stmt.setDouble(2, sponsor.getSumaDonata());
            stmt.setInt(3, sponsor.getId());
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("update_sponsor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Sponsor WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            CSVFileLogger.getInstance().logAction("delete_habitat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sponsor> getSponsori() {
        List<Sponsor> sponsori = new ArrayList<>();
        String sql = "SELECT * FROM Sponsor";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sponsori.add(new Sponsor(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getDouble("sumaDonata")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sponsori;
    }
}
