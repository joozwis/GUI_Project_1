public class Trener extends Pracownik implements RejestrZwyklychPracownikow {
    private String specjalizacja;

    public Trener(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String specjalizacja) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.specjalizacja = specjalizacja;
        dzialPracownikow.dodajPracownikaDoDzialu(this);
    }


}
