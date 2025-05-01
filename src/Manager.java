import exceptions.EmptyListException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Manager extends Recepcjonista {
    private List<Zespol> listaZespolow;
    private List<Zadanie> listaZadan;
    private static int counter;
    private List<Zadanie> delegowaneZadania = new ArrayList<>();
    private List<Zadanie> samodzielnieWykonane = new ArrayList<>();

    public Manager(String imie, String nazwisko, int dzien, int miesiac, int rok, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dzien, miesiac, rok, dzialPracownikow, login, haslo);
        this.listaZespolow = new ArrayList<>();
        this.listaZadan = new ArrayList<>();
    }


    public synchronized void delegateTask(Zadanie zadanie, IDobryPracownik pracownik) {
        pracownik.completeTask(zadanie);
        delegowaneZadania.add(zadanie);
        dodajDoHistorii(zadanie);
        LogOperacji.zapiszOperacje("Delegowanie_Zadania", "Manager " + this.getImie() + " " +
                this.getNazwisko() + " delegował zadanie: " + zadanie.getNazwa());
    }

    public void addZespol(Zespol zespol) {
        this.listaZespolow.add(zespol);
    }

    public void wyswietlZadania(List<Zadanie> listaZadan) {
        listaZadan.forEach(el -> System.out.println(el));
    }

    public void wyswietlZespoply(List<Zespol> listaZespolow) {
        listaZespolow.forEach(el -> System.out.println(el));
    }

//    static <T extends ZarzadzanieListami> void wyswietlElementyZListy(List<T> lista) {
//        if (lista.isEmpty()) throw new EmptyListException("Zaden element nie zostal jeszcze dodany do listy!");
//
//        IntStream.range(0, lista.size()).forEach(i -> {
//            T biezacyElement = lista.get(i);
//            System.out.println(i + 1 + ": " + biezacyElement);
//        });
//    }

    @Override
    public int getNextId() {
        return counter++;
    }

    @Override
    public synchronized String generateDailyReport() {
        return "Manager " + getImie() + " dziś:\n" +
                " – wykonał osobiście: " + samodzielnieWykonane.size() + "\n" +
                " – delegował:        " + delegowaneZadania.size();
    }

    @Override
    public synchronized double getEfficiency() {
        int all = samodzielnieWykonane.size() + delegowaneZadania.size();
        return all == 0 ? 0.0 : (double) samodzielnieWykonane.size() / all;
    }
}
