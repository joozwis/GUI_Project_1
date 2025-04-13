import exceptions.IncorrectBirthYearException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik implements Comparable<Pracownik> {

    static List<Pracownik> listaPracownikow = new ArrayList<>();
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private DzialPracownikow dzialPracownikow;
    private boolean czyZdrowy;

    public Pracownik(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow) {
        int aktualnyRok = LocalDate.now().getYear();
        if ((aktualnyRok - rok) < 18)
            throw new IncorrectBirthYearException("Pracownik musi miec minimum 18 lat. Wprowadz poprawny rok urodzenia!");

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = LocalDate.of(rok, miesiac, dzien);
        this.dzialPracownikow = dzialPracownikow;
        this.czyZdrowy = true;
        listaPracownikow.add(this);
    }


    public String getImie() {
        return this.imie;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public LocalDate getDataUrodzenia() {
        return this.dataUrodzenia;
    }

    public int getWiek() {
        return Period.between(this.dataUrodzenia, LocalDate.now()).getYears();
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public int compareTo(Pracownik p) {
        int porownajImie = this.getImie().compareTo(p.getImie());
        if (porownajImie != 0) {
            return porownajImie;
        }
        int porownajNazwisko = this.getNazwisko().compareTo(p.getNazwisko());

        return porownajNazwisko != 0 ? porownajNazwisko : this.dataUrodzenia.getYear() - p.dataUrodzenia.getYear();


    }

}
