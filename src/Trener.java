import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Trener extends Pracownik implements IDobryPracownik {
    private String specjalizacja;
    private static int counter;
    private final Map<Zadanie, Integer> liczbaSesji = new HashMap<>();
    private int lacznyCzas;

    public Trener(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String specjalizacja) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.specjalizacja = specjalizacja;
        dzialPracownikow.dodajPracownikaDoDzialu(this);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Trener trener = (Trener) o;
        return Objects.equals(specjalizacja, trener.specjalizacja);
    }

    @Override
    public int getNextId() {
        return counter++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specjalizacja);
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpecjalizacja: " + this.specjalizacja;
    }


    @Override
    public synchronized void completeTask(Zadanie zadanie) {
        liczbaSesji.put(zadanie, liczbaSesji.getOrDefault(zadanie, 0) + 1);
        lacznyCzas += zadanie.getCzasWykonania();
        dodajDoHistorii(zadanie);
        LogOperacji.zapiszOperacje("Wykonanie_Zadania", this.getClass().getSimpleName() + " " +
                this.getImie() + " " + this.getNazwisko() + " wykonał zadanie: " + zadanie.getNazwa());
    }

    @Override
    public synchronized String generateDailyReport() {
        int sesje = liczbaSesji.values().stream().reduce(0, (currValue, val) -> currValue + val);
        return "Trener " + getImie() + " przeprowadził dziś " + sesje + " sesji.";
    }

    @Override
    public synchronized double getEfficiency() {
        return lacznyCzas == 0 ? 0.0 : (double) liczbaSesji.size() / lacznyCzas;
    }
}
