public class Trener extends Pracownik {
    private String specjalizacja;

    public Trener(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String specjalizacja) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.specjalizacja = specjalizacja;
    }


}
