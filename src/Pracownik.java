import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Pracownik implements Comparable<Pracownik> {

    static List<Pracownik> listaPracownikow = new ArrayList<>();
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String dzialPracownikow;
    private boolean czyZdrowy;

    public Pracownik(String imie, String nazwisko, int dzien, int miesiac, int rok, String dzialPracownikow) {
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

    public LocalDate getDataUrodzenia() {
        return this.dataUrodzenia;
    }

    public int getWiek() {
        return Period.between(this.dataUrodzenia, LocalDate.now()).getYears();
    }


    @Override
    public int compareTo(Pracownik p) {
        int porownajImie = this.getImie().compareTo(p.getImie());
        if (porownajImie != 0) {
            return porownajImie;
        }
        //MOZE MIEC TO SAMO IMIE + YEAR TRZEBA WZIaC POD UWAGE!!!!
        return this.dataUrodzenia.getYear() - p.dataUrodzenia.getYear();
    }

}
