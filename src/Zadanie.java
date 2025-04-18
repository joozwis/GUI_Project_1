
import exceptions.EmptyListException;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class Zadanie extends Thread implements ZarzadzanieListami {
    private String nazwa;
    private String opis;
    private Stan stan;
    private LocalDateTime dataRozpoczecia;
    private LocalDateTime dataZakonczenia;
    private int czasWykonania;
    private Zespol zespol;


    public Zadanie(String nazwa, String opis, boolean zatwierdzenie, Zespol zespol) {
        int randomNumber = (int) ((Math.random() * 6)) + 3;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataRozpoczecia = LocalDateTime.now();
        this.czasWykonania = randomNumber;
        if (zespol.getListaPracownikow().isEmpty())
            throw new EmptyListException("Zespol, ktory nie ma pracownikow, nie moze zaczac zadania!");
        else this.zespol = zespol;

        if (zatwierdzenie) {
            this.stan = Stan.ROZPOCZETE;
        } else {
            this.stan = Stan.UTWORZONE;
        }
    }

    public Zadanie(String nazwa, Zespol zespol) {
        int randomNumber = (int) ((Math.random() * 6)) + 3;
        this.nazwa = nazwa;
        this.stan = Stan.UTWORZONE;
        this.opis = "Dodano zadanie";
        this.dataRozpoczecia = LocalDateTime.now();
        this.czasWykonania = randomNumber;
        if (zespol.getListaPracownikow().isEmpty())
            throw new EmptyListException("Zespol, ktory nie ma pracownikow, nie moze zaczac zadania!");
        else this.zespol = zespol;


    }

    public String getStan() {
        return "Aktualny status zadania: " + this.stan;
    }

    @Override
    public void run() {

        if (this.zespol.getListaPracownikow().stream().anyMatch(pracownik -> !pracownik.isCzyZdrowy())) {
            throw new Error("Chociaz jeden z pracownikow nie jest zdrowy! Wszyscy pracownicy musza byc zdrowi, aby rozpoczac zadanie!");

        }

        if (this.stan == Stan.UTWORZONE) this.stan = Stan.ROZPOCZETE;

        System.out.println("Zadanie zostalo rozpoczęte. Bedzie trwalo: " + this.czasWykonania + " sekund.");
        IntStream.range(0, this.czasWykonania).forEach(i -> {
            System.out.println("Pozostało jeszcze: " + (this.czasWykonania - i) + " sekund.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Zadanie zostało zakończone!");
        this.stan = Stan.ZAKONCZONE;
        this.dataZakonczenia = LocalDateTime.now();

    }
}
