import exceptions.EmptyListException;
import exceptions.IncorrectTaskIdException;
import exceptions.NotUniqueZadanieException;

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
    private static final Object lockObj = new Object();

    public Praca(List<Zadanie> listaZadan, Zespol zespol, String opis) {
        this.listaZadan = listaZadan;
        this.zespol = zespol;
        this.opis = opis;

        if (!listaZadan.isEmpty())
            synchronized (lockObj) {
                listaZadan.forEach(el -> {
                    mapaZadan.computeIfAbsent(el.getCurrElementId(), (item) -> el);
                });
            }
    }

    public Praca(Zespol zespol, String opis) {
        this.listaZadan = new ArrayList<>();
        this.zespol = zespol;
        this.opis = opis;

    }

    public void dodajZadanie(Zadanie zadanie) {
        synchronized (lockObj) {
            if (!listaZadan.contains(zadanie)) {
                listaZadan.add(zadanie);
                mapaZadan.computeIfAbsent(zadanie.getCurrElementId(), (item) -> zadanie);
                LogOperacji.zapiszOperacje("Dodanie_Zadania_Do_Pracy",
                        "Dodano zadanie ID: " + zadanie.getCurrElementId() + " do pracy dla zespołu: " + this.zespol.getNazwa());
            } else {
                throw new NotUniqueZadanieException("Podane zadanie wystepuje juz w liscie zadan!");
            }
        }


    }

    static Zadanie getTaskById(int id) {
        synchronized (lockObj) {
            Zadanie z = mapaZadan.get(id);
            if (z == null) throw new IncorrectTaskIdException("Brak zadania z podanym ID!");
            return z;
        }
    }


    @Override
    public void run() {

        if (listaZadan.isEmpty()) {
            LogOperacji.zapiszOperacje("Praca", "Brak zadań");
            System.out.println("Lista z zadaniami jest pusta! Dodaj zadanie!");
            return;
        }

        LogOperacji.zapiszOperacje("Rozpoczecie_Pracy",
                "Rozpoczęto pracę dla zespołu: " + zespol.getNazwa()
                        + ", liczba zadań: " + listaZadan.size());


        listaZadan.forEach(z -> {
            if (z.isZatwierdzenie()) {
                z.start();
            } else {
                System.out.println("Zadanie ID " + z.getCurrElementId()
                        + " niezatwierdzone – pominiete");
                LogOperacji.zapiszOperacje("Pominiecie_Zadania",
                        "Zadanie ID: " + z.getCurrElementId()
                                + " pominiete (niezatwierdzone)");
            }
        });

        for (Zadanie z : listaZadan) {
            if (z.isZatwierdzenie()) {
                try {
                    z.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        LogOperacji.zapiszOperacje("Zakonczenie_Pracy",
                "Wszystkie zatwierdzone zadania zakończone dla zespołu: "
                        + zespol.getNazwa());
    }

}
