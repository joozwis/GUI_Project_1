import exceptions.NotUniqueNameException;

import java.util.*;

public class Zespol {
    static Set<String> czyNazwaZespoluJestUnikalna = new HashSet<>();
    private String nazwa;
    private Manager manager;
    List<Pracownik> listaPracownikow;


    private Zespol(String nazwa, Manager manager) {
        this.nazwa = nazwa;
        this.manager = manager;
        this.listaPracownikow = new ArrayList<>();
    }

    public Zespol utworzNowyZespol(String nazwa, Manager manager) {
        if (!DzialPracownikow.czyNazwaJestUnikalna(nazwa.toLowerCase(), czyNazwaZespoluJestUnikalna))
            throw new NotUniqueNameException("Zespol o tej nazwie juz istnieje!");

        czyNazwaZespoluJestUnikalna.add(nazwa.toLowerCase());
        return new Zespol(nazwa, manager);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Zespol zespol = (Zespol) o;
        return Objects.equals(nazwa, zespol.nazwa) && Objects.equals(manager, zespol.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, manager);
    }
}
