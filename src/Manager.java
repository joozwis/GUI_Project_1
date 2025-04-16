import exceptions.EmptyListException;

import java.util.List;
import java.util.stream.IntStream;

public class Manager extends Recepcjonista {
    private List<Zespol> listaZespolow;
    private List<Zadanie> listaZadan;

    public Manager(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow, login, haslo);
        dzialPracownikow.dodajPracownikaDoDzialu(this);
    }


    public void addZespol(Zespol zespol) {
        this.listaZespolow.add(zespol);
    }

    public void wyswietlZadania(List<Zadanie> listaZadan) {
        wyswietlElementyZListy(listaZadan);
    }

    public void wyswietlZespoply(List<Zespol> listaZespolow) {
        wyswietlElementyZListy(listaZespolow);
    }

    static <T extends ZarzadzanieListami> void wyswietlElementyZListy(List<T> lista) {
        if (lista.isEmpty()) throw new EmptyListException("Zaden element nie zostal jeszcze dodany do listy!");

        IntStream.range(0, lista.size()).forEach(i -> {
            T biezacyElement = lista.get(i);
            System.out.println(i + 1 + ": " + biezacyElement);
        });
    }


}
