import java.util.Objects;

public class Recepcjonista extends Pracownik {
    private String login;
    private String haslo;
    private String initial;
    private static int counter;


    public Recepcjonista(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.initial = createInitial();
        dzialPracownikow.dodajPracownikaDoDzialu(this);
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

    @Override
    public int getNextId() {
        return counter++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recepcjonista that = (Recepcjonista) o;
        return Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo) && Objects.equals(initial, that.initial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, haslo, initial);
    }
}
