import exceptions.DuplicateElementException;
import exceptions.EmptyListException;
import exceptions.NotUniqueNameException;

import java.util.*;
import java.util.stream.IntStream;

public class DzialPracownikow {
    static Set<String> czyNazwaDzialuJestUnikalna = new HashSet<>();
    private String nazwa;
    private List<Pracownik> listaPracownikow;

    private DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
    }

    public void dodajPracownikaDoDzialu(Pracownik pracownik) {
        if (this.listaPracownikow.contains(pracownik))
            throw new DuplicateElementException("Ten pracownik jest juz dodany do listy.");

        this.listaPracownikow.add(pracownik);
    }

    static protected boolean czyNazwaJestUnikalna(String nazwa) {
        return czyNazwaDzialuJestUnikalna.contains(nazwa);
    }

    static protected boolean czyNazwaJestUnikalna(String nazwa, Set<String> unikalnySet) {
        return unikalnySet.contains(nazwa);
    }


    static DzialPracownikow utworzDzial(String nazwaDzialu) {
        if (czyNazwaJestUnikalna(nazwaDzialu.toLowerCase()))
            throw new NotUniqueNameException("Dzial o takiej nazwie znajduje sie juz w naszej bazie!");


        System.out.println("Dzial o nazwie: " + nazwaDzialu.toUpperCase() + " zostal utworzony!");
        czyNazwaDzialuJestUnikalna.add(nazwaDzialu.toLowerCase());
        return new DzialPracownikow(nazwaDzialu.toLowerCase());
    }

    public static DzialPracownikow utworzDzial(String nazwaDzialu, Set<String> uniklanySet) {
        if (!czyNazwaJestUnikalna(nazwaDzialu.toLowerCase()))
            throw new NotUniqueNameException("Dzial o takiej nazwie znajduje sie juz w naszej bazie!");


        System.out.println("Dzial o nazwie: " + nazwaDzialu + "  zostal utworzony!");
        czyNazwaDzialuJestUnikalna.add(nazwaDzialu.toLowerCase());
        return new DzialPracownikow(nazwaDzialu.toLowerCase());
    }


    public void wypiszPracownikow() {
        if (listaPracownikow.isEmpty())
            throw new EmptyListException("Lista pracownikow jest pusta! Wpierw dodaj pierwszego pracownika!");

        System.out.println("\t\t===== LISTA PRACOWNIKÓW DZIAŁU " + this.nazwa.toUpperCase() + " =====");

        IntStream.range(0, this.listaPracownikow.size()).forEach(i -> {
            Pracownik aktualnyPracownik = this.listaPracownikow.get(i);
            System.out.println(i + 1 + ". Pracownik: " + aktualnyPracownik.getImie() + ", wiek: " + aktualnyPracownik.getWiek());
        });
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DzialPracownikow that = (DzialPracownikow) o;
        return Objects.equals(nazwa, that.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nazwa);
    }
}
