package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVFileLogger {
    private static final String FILE_PATH = "logs.csv"; // Calea catre fisierul CSV
    private static CSVFileLogger instance;

    // Constructor privat pentru Singleton
    private CSVFileLogger() {
        // Verificam daca fisierul exista si, daca nu, il cream cu antetul
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.append("nume_actiune,timestamp\n");
        } catch (IOException e) {
            System.err.println("Eroare la inițializarea fișierului CSV: " + e.getMessage());
        }
    }

    // Metoda pentru obtinerea unei instante singleton
    public static CSVFileLogger getInstance() {
        if (instance == null) {
            instance = new CSVFileLogger();
        }
        return instance;
    }

    /**
     * Scrie o actiune în fisierul CSV.
     *
     * @param numeActiune Numele actiunii efectuate (de exemplu, "create_angajat").
     */
    public void logAction(String numeActiune) {
        // Obtinem timestamp-ul curent
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestamp = now.format(formatter);

        // Scriem actiunea în fisier
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.append(numeActiune).append(",").append(timestamp).append("\n");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul CSV: " + e.getMessage());
        }
    }
}