import java.time.LocalDate;

public class Zadanie extends Thread implements ZarzadzanieListami {
    private String nazwa;
    private String opis;
    private Stan stan;
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    int czasWykonania = (int) ((Math.random() * 6)) + 3;

}
