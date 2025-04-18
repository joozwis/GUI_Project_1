import java.util.List;

public class Praca implements Runnable {
    List<Zadanie> listaZadan;
    private Zespol zespol;
    private String opis;
    private static int counter;


    public Praca(List<Zadanie> listaZadan, Zespol zespol, String opis) {
        this.listaZadan = listaZadan;
        this.zespol = zespol;
        this.opis = opis;
    }

    @Override
    public void run() {

    }
}
