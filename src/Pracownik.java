import exceptions.IncorrectBirthYearException;
import exceptions.ValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        if (validateNameAndSurname(imie, "imie"))
            this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        if (validateNameAndSurname(nazwisko, "nazwisko"))
            this.nazwisko = nazwisko;
    }

    private boolean validateNameAndSurname(String value, String fieldName) {
        String regex = "^[a-zA-Z][a-zA-Z ]*$";
        if (!(Pattern.matches(regex, value))) {
            throw new ValidationException("Niepoprawne " + fieldName + ", wprawodz prawidlowe dane!");
        }

        return true;

    }

    @Override
    public int compareTo(Pracownik p) {
        int porownajImiona = this.getImie().compareTo(p.getImie());
        if (porownajImiona != 0) {
            return porownajImiona;
        }
        int porownajNazwiska = this.getNazwisko().compareTo(p.getNazwisko());

        return porownajNazwiska != 0 ? porownajNazwiska : this.dataUrodzenia.getYear() - p.dataUrodzenia.getYear();


    }


}
