import exceptions.EmptyListException;
import exceptions.NotUniqueNameException;

import java.util.*;

public class DzialPracownikow {
    static Set<String> czyNazwaDzialuJestUnikalna = new HashSet<>();
    private String nazwa;
    private List<Pracownik> listaPracownikow;

    private DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
    }

    /// ///  ZAIMPLEMENTOWAC DODANIE PRACOWNIKA DO DZIALU?????
    /// ///  ZAIMPLEMENTOWAC DODANIE PRACOWNIKA DO DZIALU?????
    /// ///  ZAIMPLEMENTOWAC DODANIE PRACOWNIKA DO DZIALU?????
    /// ///  ZAIMPLEMENTOWAC DODANIE PRACOWNIKA DO DZIALU?????

    static protected boolean czyNazwaJestUnikalna(String nazwa, Set<String> unikalnySet) {
        return unikalnySet.contains(nazwa);
    }

    public DzialPracownikow utworzDzial(String nazwaDzialu, Set<String> unikalnySet) {
        if (!czyNazwaJestUnikalna(nazwaDzialu.toLowerCase(), unikalnySet))
            throw new NotUniqueNameException("Dzial o takiej nazwie znajduje sie juz w naszej bazie!");


        System.out.println("Dzial o nazwie: " + nazwaDzialu + "  zostal utworzony!");
        czyNazwaDzialuJestUnikalna.add(nazwaDzialu.toLowerCase());
        return new DzialPracownikow(nazwaDzialu.toLowerCase());
    }


    public void wypiszPracownikow() {
        if (listaPracownikow.isEmpty())
            throw new EmptyListException("Lista pracownikow jest pusta! Wpierw dodaj pierwszego pracownika!");

        System.out.println("\t\t===== LISTA PRACOWNIKÓW DZIAŁU" + this.nazwa.toUpperCase() + " =====");
        this.listaPracownikow.forEach(pracownik -> {
            int index = 1;
            System.out.println(index++ + ". Pracownik: " + pracownik.getImie() + ", wiek: " + pracownik.getWiek());
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
