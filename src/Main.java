import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Tworzenie działów pracowników
        DzialPracownikow dzialObslugaKlienta = DzialPracownikow.utworzDzial("obsługa klienta");
        DzialPracownikow dzialTrenerzy = DzialPracownikow.utworzDzial("trenerzy");
        DzialPracownikow dzialIT = DzialPracownikow.utworzDzial("it");
        DzialPracownikow dzialMarketing = DzialPracownikow.utworzDzial("marketing");
        DzialPracownikow dzialFinanse = DzialPracownikow.utworzDzial("finanse");

        // Tworzenie obiektów klasy Recepcjonista
        Recepcjonista recepcjonista1 = new Recepcjonista("Anna", "Kowalska", 15, 4, 1990, dzialObslugaKlienta, "akowalska", "haslo123");
        Recepcjonista recepcjonista2 = new Recepcjonista("Jan", "Nowak", 20, 6, 1985, dzialFinanse, "jnowak", "haslo456");
        Recepcjonista recepcjonista3 = new Recepcjonista("Maria", "Wiśniewska", 10, 12, 1992, dzialFinanse, "mwisniewska", "haslo789");
        Recepcjonista recepcjonista4 = new Recepcjonista("Piotr", "Kowalczyk", 5, 3, 1988, dzialFinanse, "pkowalczyk", "haslo012");
        Recepcjonista recepcjonista5 = new Recepcjonista("Karolina", "Lewandowska", 22, 9, 1991, dzialTrenerzy, "klewandowska", "haslo345");

        // Tworzenie obiektów klasy Trener
        Trener trener1 = new Trener("Marek", "Zieliński", 18, 7, 1982, dzialTrenerzy, "Siłownia");
        Trener trener2 = new Trener("Aleksandra", "Dąbrowska", 3, 11, 1986, dzialTrenerzy, "Joga");
        Trener trener3 = new Trener("Tomasz", "Wójcik", 12, 2, 1984, dzialTrenerzy, "Pływanie");
        Trener trener4 = new Trener("Natalia", "Kamińska", 25, 5, 1989, dzialTrenerzy, "Fitness");
        Trener trener5 = new Trener("Michał", "Kaczmarek", 7, 8, 1983, dzialTrenerzy, "Boks");
        System.out.println(trener1);
        System.out.println(trener2);


        // Tworzenie obiektów klasy Manager
        Manager manager1 = new Manager("Adam", "Szymański", 14, 1, 1980, dzialTrenerzy, "aszymanski", "manager123");
        Manager manager2 = new Manager("Katarzyna", "Jankowska", 30, 10, 1978, dzialMarketing, "kjankowska", "manager456");
        Manager manager3 = new Manager("Robert", "Mazur", 8, 6, 1975, dzialFinanse, "rmazur", "manager789");

        List<Pracownik> listaPracownikow = Arrays.asList(trener1, trener2, recepcjonista1);

        Zespol zespol1 = Zespol.utworzNowyZespol("Zespół programistów", manager1);
        Zespol zespol2 = Zespol.utworzNowyZespol("Zespół marketingu cyfrowego", manager2);
        Zespol zespol3 = Zespol.utworzNowyZespol("Zespół finansów", manager3);
//
//        zespol1.dodajPracownika(trener1);
//        zespol1.dodajPracownika(trener2);
//        zespol1.dodajPracownika(recepcjonista1);
        zespol1.dodajPracownika(listaPracownikow);

    }
}