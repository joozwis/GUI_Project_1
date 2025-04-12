import java.util.*;

public class DzialPracownikow {
    static Set<String> unikalnyDzial = new HashSet<>();
    private String nazwa;
    private List<Pracownik> listaPracownikow;

    private DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
    }

    private boolean czyDzialJestUnikalny(String nazwaDzialu) {
        return unikalnyDzial.contains(nazwaDzialu.toLowerCase());
    }

    public void utworzDzial(String nazwaDzialu) {
        if (!czyDzialJestUnikalny(nazwaDzialu.toLowerCase()))
            throw new NotUniqueNameException("Dzial o takiej nazwie znajduje sie juz w naszej bazie!");

        System.out.println("Dzial o nazwie: " + nazwaDzialu + "  zostal utworzony!");
        unikalnyDzial.add(nazwaDzialu.toLowerCase());
        new DzialPracownikow(nazwaDzialu.toLowerCase());
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
