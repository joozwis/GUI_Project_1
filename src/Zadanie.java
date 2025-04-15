import java.time.LocalDate;

public class Zadanie extends Thread {
    String nazwa;
    String opis;
    Stan stan;
    LocalDate dataRozpoczecia;
    LocalDate dataZakonczenia;
    int czasWykonania = (int) ((Math.random() * 6)) + 3;

}
