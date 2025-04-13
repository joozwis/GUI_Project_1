public class Recepcjonista extends Pracownik {
    private String login;
    private String haslo;
    private String initial;

    public Recepcjonista(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.initial = createInitial();
    }

    public String createInitial() {
        return super.getImie().charAt(0) + super.getNazwisko().charAt(0) + "";
    }


    public void setImie(String imie) {
        super.setImie(imie);
        this.initial = createInitial();

    }

    public void setNazwisko(String nazwisko) {
        super.setNazwisko(nazwisko);
        this.initial = createInitial();
    }
}
