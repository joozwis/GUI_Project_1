import java.time.LocalDate;

public abstract class Pracownik implements Comparable<Pracownik> {

    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String dzialPracownikow;
    private boolean czyZdrowy;


    public Pracownik(String imie, String nazwisko, int dzien, int miesiac, int rok, String dzialPracownikow) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = LocalDate.of(rok, miesiac, dzien);
        this.dzialPracownikow = dzialPracownikow;
        this.czyZdrowy = true;

    }

    public String getImie() {
        return imie;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    @Override
    public int compareTo(Pracownik o) {

        int porownajImie = this.getImie().compareTo(o.getImie());

        if (porownajImie != 0) {
            return porownajImie;
        }

        return this.dataUrodzenia.getYear() - o.dataUrodzenia.getYear();
    }

}
