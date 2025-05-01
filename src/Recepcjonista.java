import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recepcjonista extends Pracownik implements IDobryPracownik {
    private String login;
    private String haslo;
    private String initial;
    private static int counter;
    private List<Zadanie> wykonaneZadania = new ArrayList<>();
    private int lacznyCzasSek;


    public Recepcjonista(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.initial = createInitial();
        dzialPracownikow.dodajPracownikaDoDzialu(this);
    }

    public String createInitial() {
        return (super.getImie().charAt(0) + "") + (super.getNazwisko().charAt(0) + "");
    }

    public void setImie(String imie) {
        super.setImie(imie);
        this.initial = createInitial();

    }

    public void setNazwisko(String nazwisko) {
        super.setNazwisko(nazwisko);
        this.initial = createInitial();
    }

    @Override
    public int getNextId() {
        return counter++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recepcjonista that = (Recepcjonista) o;
        return Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo) && Objects.equals(initial, that.initial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, haslo, initial);
    }

    @Override
    public String toString() {
        String additionalInfo = this instanceof Manager ? "" : "\nInicjały: " + this.initial;
        return super.toString() + additionalInfo;
    }

    @Override
    public synchronized void completeTask(Zadanie zadanie) {
        wykonaneZadania.add(zadanie);
        lacznyCzasSek += zadanie.getCzasWykonania();
        dodajDoHistorii(zadanie);
        LogOperacji.zapiszOperacje("Wykonanie_Zadania", this.getClass().getSimpleName() + " " +
                this.getImie() + " " + this.getNazwisko() + " wykonał zadanie: " + zadanie.getNazwa());
    }

    @Override
    public synchronized String generateDailyReport() {
        return "Recepcjonista " + getImie() +
                " zakończył dziś " + wykonaneZadania.size() + " zadań.";
    }

    @Override
    public synchronized double getEfficiency() {
        return wykonaneZadania.isEmpty()
                ? 0.0
                : (double) wykonaneZadania.size() / lacznyCzasSek;
    }
}
