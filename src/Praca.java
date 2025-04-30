import exceptions.IncorrectTaskIdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Praca implements Runnable {
    private List<Zadanie> listaZadan;
    private Zespol zespol;
    private String opis;
    private static int counter;
    static Map<Integer, Zadanie> mapaZadan = new ConcurrentHashMap<>();

    public Praca(List<Zadanie> listaZadan, Zespol zespol, String opis) {
        this.listaZadan = listaZadan;
        this.zespol = zespol;
        this.opis = opis;

        if (!listaZadan.isEmpty())
            listaZadan.forEach(el -> {
                mapaZadan.put(el.getCurrElementId(), el);
            });
    }

    public Praca(Zespol zespol, String opis) {
        this.listaZadan = new ArrayList<>();
        this.zespol = zespol;
        this.opis = opis;

    }

    public void dodajZadanie(Zadanie zadanie) {
        listaZadan.add(zadanie);
        mapaZadan.put(zadanie.getCurrElementId(), zadanie);
    }

    static Zadanie getTaskById(int id) {
        if (!mapaZadan.containsKey(id)) {
            throw new IncorrectTaskIdException("Brak zadania z podanym ID!");
        }
        return mapaZadan.get(id);
    }


    @Override
    public void run() {


    }
}
