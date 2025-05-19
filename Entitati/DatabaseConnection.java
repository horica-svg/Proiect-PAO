package Entitati;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    // Parametrii de conectare la baza de date
    private static final String URL = "jdbc:mysql://localhost:3306/zoo";
    private static final String USER = "root";
    private static final String PASSWORD = "1923Rapid1923";

    /**
     * Constructor protejat.
     * Este folosit pentru a permite subclaselor să apeleze constructorul clasei părinte.
     */
    protected DatabaseConnection() {
        // Acest constructor poate fi lăsat gol sau poate conține inițializări comune.
        // De exemplu, puteți adăuga loguri sau verificați dacă driverul JDBC este încărcat.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Încărcăm driverul MySQL
        } catch (ClassNotFoundException e) {
            System.err.println("Eroare: Driverul JDBC nu a fost găsit!");
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru obtinerea unei conexiuni la baza de date.
     *
     * @return O conexiune valida catre baza de date.
     * @throws SQLException Daca apare o eroare la conectare.
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Metoda utilitara pentru inchiderea conexiunii.
     * Aceasta metoda poate fi folosita pentru a elibera resursele atunci cand conexiunea nu mai este necesara.
     *
     * @param connection Conexiunea care trebuie inchisa.
     */
    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Eroare la închiderea conexiunii: " + e.getMessage());
            }
        }
    }
}