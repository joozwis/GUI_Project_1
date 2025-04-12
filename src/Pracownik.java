import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public abstract class Pracownik implements Comparable<Pracownik> {

    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String dzialPracownikow;
    private boolean czyZdrowy;


    /// /////// DODAC SPRAWDZANIE, CZY PRACOWNIK MA WIECEJ NIZ 18 LAT!!!!!!
    /// /////// DODAC SPRAWDZANIE, CZY PRACOWNIK MA WIECEJ NIZ 18 LAT!!!!!!
    /// /////// DODAC SPRAWDZANIE, CZY PRACOWNIK MA WIECEJ NIZ 18 LAT!!!!!!

    public Pracownik(String imie, String nazwisko, int dzien, int miesiac, int rok, String dzialPracownikow) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = LocalDate.of(rok, miesiac, dzien);
        this.dzialPracownikow = dzialPracownikow;
        this.czyZdrowy = true;

    }

    
    public String getImie() {
        return this.imie;
    }

    public LocalDate getDataUrodzenia() {
        return this.dataUrodzenia;
    }

    public int getWiek() {
        return Period.between(LocalDate.now(), this.dataUrodzenia).getYears();
    }


    @Override
    public int compareTo(Pracownik p1) {
        return Comparator.comparing((Pracownik p2) -> p2.getImie())
                .thenComparing(p2 -> p2.getDataUrodzenia())
                .compare(this, p1);
    }
}
