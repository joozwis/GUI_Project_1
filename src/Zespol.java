import exceptions.DuplicateElementException;
import exceptions.NotUniqueNameException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zespol implements ZarzadzanieListami {
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
        manager.addZespol(this);
        return new Zespol(nazwa, manager);
    }


    public <T extends Pracownik & RejestrZwyklychPracownikow> void dodajPracownika(T pracownik) {
        if (this.listaPracownikow.contains(pracownik))
            throw new DuplicateElementException("Ten pracownik zostal juz dodany do listy!");

        this.listaPracownikow.add(pracownik);
    }

    public <T extends Pracownik & RejestrZwyklychPracownikow> void dodajPracownika(List<T> nowaListaPracownikow) {
        this.listaPracownikow = Stream.concat(this.listaPracownikow.stream(), nowaListaPracownikow.stream()).distinct().toList();
    }

    public void wyswietlListePracownikow() {
        Manager.wyswietlElementyZListy(this.listaPracownikow);
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
