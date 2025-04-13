public class Trener extends Pracownik {
    String specjalizacja;

    public Trener(String imie, String nazwisko, int dzien, int miesiac, int rok, String dzialPracownikow, String specjalizacja) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.specjalizacja = specjalizacja;
    }

    
}
