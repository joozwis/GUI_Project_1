import exceptions.DuplicateElementException;
import exceptions.EmptyListException;
import exceptions.NotUniqueNameException;

import java.util.*;
import java.util.stream.IntStream;

public class DzialPracownikow {
    static Set<String> czyNazwaDzialuJestUnikalna = new HashSet<>();
    private String nazwa;
    private List<Pracownik> listaPracownikow;
    private static int counter;
    private int id;

    private DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
        this.id = counter++;
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

    public String getNazwa() {
        return this.nazwa;
    }

    public String wypiszPracownikow() {
        if (listaPracownikow.isEmpty())
            throw new EmptyListException("Lista pracownikow jest pusta! Wpierw dodaj pierwszego pracownika!");
        
        StringBuilder stringBuilder = new StringBuilder("\n\t\t===== LISTA PRACOWNIKÓW DZIAŁU " + this.nazwa.toUpperCase() + " =====\n");
        IntStream.range(0, this.listaPracownikow.size()).forEach(i -> {
            Pracownik aktualnyPracownik = this.listaPracownikow.get(i);

            stringBuilder.append(i + 1).append(". Pracownik: ").append(aktualnyPracownik.getImie()).append(", wiek: ").append(aktualnyPracownik.getWiek()).append("\n");
        });
        return stringBuilder.toString();
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

    @Override
    public String toString() {

        return "Nazwa dzialu: " + this.nazwa + wypiszPracownikow();
    }
}
