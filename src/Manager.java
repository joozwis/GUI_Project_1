import java.util.List;

public class Manager extends Recepcjonista {
    private List<Zespol> listaZespolow;
    private List<Zadanie> listaZadan;

    public Manager(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow, login, haslo);
    }


}
