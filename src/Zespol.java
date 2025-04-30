import exceptions.DuplicateElementException;
import exceptions.EmptyListException;
import exceptions.InvalidEmployeeTypeException;
import exceptions.NotUniqueNameException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zespol implements ZarzadzanieListami {
    static Set<String> czyNazwaZespoluJestUnikalna = new HashSet<>();
    private String nazwa;
    private Manager manager;
    private List<Pracownik> listaPracownikow;
    private static int counter;
    private int id;


    private Zespol(String nazwa, Manager manager) {
        this.nazwa = nazwa;
        this.manager = manager;
        this.listaPracownikow = new ArrayList<>();
        this.id = counter++;
    }

    static Zespol utworzNowyZespol(String nazwa, Manager manager) {
        if (DzialPracownikow.czyNazwaJestUnikalna(nazwa.toLowerCase(), czyNazwaZespoluJestUnikalna)) {
            System.out.println(czyNazwaZespoluJestUnikalna);
            throw new NotUniqueNameException("Zespol o tej nazwie juz istnieje!");
        }

        czyNazwaZespoluJestUnikalna.add(nazwa.toLowerCase());
        Zespol nowyZespol = new Zespol(nazwa, manager);
        manager.addZespol(nowyZespol);
        return nowyZespol;
    }


    public <T extends Pracownik> void dodajPracownika(T pracownik) {
        if (pracownik instanceof Manager)
            throw new InvalidEmployeeTypeException("Manager nie może być dodany jako zwykły pracownik");

        if (this.listaPracownikow.contains(pracownik))
            throw new DuplicateElementException("Ten pracownik zostal juz dodany do listy!");

        this.listaPracownikow.add(pracownik);
    }

    public <T extends Pracownik> void dodajPracownika(List<T> nowaListaPracownikow) {

        if (nowaListaPracownikow.stream().anyMatch(pracownik -> pracownik instanceof Manager)) {
            throw new InvalidEmployeeTypeException("Jedna z osób w liście jest Managerem. Manager nie może być dodany jako zwykły pracownik");
        }

        this.listaPracownikow = Stream.concat(this.listaPracownikow.stream(), nowaListaPracownikow.stream()).distinct().toList();
    }

    public void wyswietlListePracownikow() {
        Manager.wyswietlElementyZListy(this.listaPracownikow);
    }

    public List<Pracownik> getListaPracownikow() {
        if (this.listaPracownikow.isEmpty()) throw new EmptyListException("Probujesz zwrocic pusta liste!");

        return this.listaPracownikow;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.listaPracownikow.isEmpty()) {
            stringBuilder.append("Lista pracowników jest pusta");
        } else {
            String listaPracownikowString = listaPracownikow.stream().map(el -> el.getImie() + " " + el.getNazwisko()).collect(Collectors.joining(", "));
            stringBuilder.append("Lista pracowników: ").append(listaPracownikowString);
        }

        return "Nazwa zespołu: " + this.nazwa
                + "\nManager zespołu: " + this.manager.getImie() + " " + this.manager.getNazwisko() +
                "\n" + stringBuilder;

    }
}
