package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zoo"; // URL-ul bazei de date
        String user = "root"; // Numele de utilizator
        String password = "1923Rapid1923"; // Parola pe care ai setat-o

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}