import java.util.Objects;

public class Trener extends Pracownik {
    private String specjalizacja;

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
    public int hashCode() {
        return Objects.hash(super.hashCode(), specjalizacja);
    }
}
