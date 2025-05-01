
import exceptions.EmptyListException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.IntStream;

public class Zadanie extends Thread implements ZarzadzanieListami {
    private String nazwa;
    private String opis;
    private Stan stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private int czasWykonania;
    private Zespol zespol;
    private static int counter;
    private int id;
    boolean zatwierdzenie;


    public Zadanie(String nazwa, String opis, boolean zatwierdzenie, Zespol zespol) {
        int randomNumber = (int) ((Math.random() * 6)) + 3;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = randomNumber;
        this.id = counter++;
        if (zespol.getListaPracownikow().isEmpty())
            throw new EmptyListException("Zespol, ktory nie ma pracownikow, nie moze zaczac zadania!");
        else this.zespol = zespol;
        this.zatwierdzenie = zatwierdzenie;
        this.stan = Stan.UTWORZONE;
        LogOperacji.zapiszOperacje("Utworzenie_Zadania", "Utworzono zadanie: " + nazwa + ", opis: " + opis);

    }

    public Zadanie(String nazwa, Zespol zespol) {
        this(nazwa, "Dodano zadanie", false, zespol);

    }

    public Stan getStan() {
        return this.stan;
    }

    public boolean isZatwierdzenie() {
        return this.zatwierdzenie;
    }

    public int getCurrElementId() {
        return this.id;
    }

    public int getCzasWykonania() {
        return this.czasWykonania;
    }

    public String getNazwa() {
        return this.nazwa;
    }

    @Override
    public void run() {
        if (this.zespol.getListaPracownikow().stream().anyMatch(pracownik -> !pracownik.isCzyZdrowy())) {
            throw new Error("Chociaz jeden z pracownikow nie jest zdrowy! Wszyscy pracownicy musza byc zdrowi, aby rozpoczac zadanie!");
        }
        this.stan = Stan.ROZPOCZETE;
        LogOperacji.zapiszOperacje("Rozpoczecie_Zadania", "Rozpoczęto zadanie: " + this.nazwa +
                ", czas trwania: " + this.czasWykonania + " sekund");
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
        LogOperacji.zapiszOperacje("Zakonczenie_Zadania", "Zakończono zadanie: " + this.nazwa +
                ", czas trwania faktyczny: " +
                java.time.Duration.between(this.dataUtworzenia, this.dataZakonczenia).getSeconds() + " sekund");

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Zadanie zadanie = (Zadanie) o;
        return czasWykonania == zadanie.czasWykonania && id == zadanie.id && zatwierdzenie == zadanie.zatwierdzenie && Objects.equals(nazwa, zadanie.nazwa) && Objects.equals(opis, zadanie.opis) && stan == zadanie.stan && Objects.equals(dataUtworzenia, zadanie.dataUtworzenia) && Objects.equals(dataZakonczenia, zadanie.dataZakonczenia) && Objects.equals(zespol, zadanie.zespol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, opis, stan, dataUtworzenia, dataZakonczenia, czasWykonania, zespol, id, zatwierdzenie);
    }
}
