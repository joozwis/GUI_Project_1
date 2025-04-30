import exceptions.EmptyListException;
import exceptions.IncorrectTaskIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Praca implements Runnable {
    private List<Zadanie> listaZadan;
    private Zespol zespol;
    private String opis;
    private static int counter;
    static Map<Integer, Zadanie> mapaZadan = new HashMap<>();

    public Praca(List<Zadanie> listaZadan, Zespol zespol, String opis) {
        this.listaZadan = listaZadan;
        this.zespol = zespol;
        this.opis = opis;

        if (!listaZadan.isEmpty())
            listaZadan.forEach(el -> {
                mapaZadan.computeIfAbsent(el.getCurrElementId(), (item) -> el);
            });
    }

    public Praca(Zespol zespol, String opis) {
        this.listaZadan = new ArrayList<>();
        this.zespol = zespol;
        this.opis = opis;

    }

    public synchronized void dodajZadanie(Zadanie zadanie) {
        if (!listaZadan.contains(zadanie)) {
            listaZadan.add(zadanie);
            mapaZadan.computeIfAbsent(zadanie.getCurrElementId(), (item) -> zadanie);
        }

    }

    static Zadanie getTaskById(int id) {
        if (!mapaZadan.containsKey(id)) {
            throw new IncorrectTaskIdException("Brak zadania z podanym ID!");
        }
        return mapaZadan.get(id);
    }


    @Override
    public synchronized void run() {

        if (!listaZadan.isEmpty()) throw new EmptyListException("Lista z zadaniami jest pusta! Dodaj zadanie!");

        listaZadan.forEach(zadanie -> {
            if (!zadanie.isZatwierdzenie()) {
                System.out.println("Zadanie nie jest zatwierdzone, zostanie pominiete!");
            } else {
                zadanie.start();
            }
        });
    }
}
