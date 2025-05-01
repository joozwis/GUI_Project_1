import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogOperacji {
    private static String nazwaPliku = "log_operacji.txt";

    public static void zapiszOperacje(String typOperacji, String szczegoly) {

        try (FileWriter fw = new FileWriter(nazwaPliku, true)) {
            LocalDateTime czasOperacji = LocalDateTime.now();
            fw.write(czasOperacji + " | " + typOperacji + " | " + szczegoly + "\n");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }

    public static List<String> odczytajOperacje() {
        List<String> historia = new ArrayList<>();

        try (FileReader fr = new FileReader(nazwaPliku)) {
            StringBuilder linia = new StringBuilder();
            int znak;

            while ((znak = fr.read()) != -1) {
                if (znak == '\n') {
                    historia.add(linia.toString());
                    linia = new StringBuilder();
                } else {
                    linia.append((char) znak);
                }
            }

            if (!linia.isEmpty()) {
                historia.add(linia.toString());
            }

        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu z pliku: " + e.getMessage());
        }

        return historia;
    }
}
