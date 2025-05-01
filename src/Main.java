import exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // tworzenie dzialow
        System.out.println("TWORZENIE DZIAŁÓW:");
        DzialPracownikow dzialIT = DzialPracownikow.utworzDzial("IT");
        DzialPracownikow dzialHR = DzialPracownikow.utworzDzial("HR");
        DzialPracownikow dzialMarketing = DzialPracownikow.utworzDzial("Marketing");

        // nieunikalna nazwa dzialu
        try {
            DzialPracownikow dzialItDuplikat = DzialPracownikow.utworzDzial("IT");
        } catch (NotUniqueNameException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // tworzenie pracownikow
        System.out.println("\nTWORZENIE PRACOWNIKOW:");

        // trenerzy
        Trener trener1 = new Trener("Jan", "Kowalski", 15, 5, 1980, dzialIT, "Java");
        Trener trener2 = new Trener("Anna", "Nowak", 10, 3, 1985, dzialIT, "Python");

        // za mlody pracownik
        try {
            Trener trenerNiepoprawny = new Trener("Młody", "Pracownik", 1, 1, 2010, dzialIT, "JavaScript");
        } catch (IncorrectBirthYearException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // recepcjonisci
        Recepcjonista recepcjonista1 = new Recepcjonista("Tomasz", "Wiśniewski", 22, 7, 1990, dzialHR, "twisniewski", "haslo123");
        Recepcjonista recepcjonista2 = new Recepcjonista("Karolina", "Kowalczyk", 5, 12, 1988, dzialMarketing, "kkowalczyk", "haslo456");

        // managerowie
        Manager manager1 = new Manager("Piotr", "Dąbrowski", 18, 9, 1975, dzialIT, "pdabrowski", "manager123");
        Manager manager2 = new Manager("Katarzyna", "Lewandowska", 30, 1, 1980, dzialHR, "klewandowska", "manager456");

        // info o pracownikach
        System.out.println("\nINFORMACJE O PRACOWNIKACH:");
        System.out.println(trener1);
        System.out.println("\n" + recepcjonista1);
        System.out.println("\n" + manager1);

        // zmiana imienia/nazwiska + inicjaly
        System.out.println("\nZMIANA IMIENIA/NAZWISKA I AKTUALIZACJA INITIAL:");
        System.out.println("Inicjaly przed zmiana: " + recepcjonista1.toString());
        recepcjonista1.setImie("Robert");
        recepcjonista1.setNazwisko("Kaminski");
        System.out.println("Inicjaly po zmianie: " + recepcjonista1.toString());

        // wyswietlanie pracownikow w dzialach
        System.out.println("\nPRACOWNICY W DZIALACH:");
        try {
            System.out.println(dzialIT.wypiszPracownikow());
            System.out.println(dzialHR.wypiszPracownikow());
        } catch (EmptyListException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // tworzenie zespolow
        System.out.println("\nTWORZENIE ZESPOLOW:");
        Zespol zespolDevelopment = Zespol.utworzNowyZespol("Development", manager1);
        Zespol zespolTesting = Zespol.utworzNowyZespol("Testing", manager1);

        // nieunikalna nazwa zespolu
        try {
            Zespol zespolDevelopmentDuplikat = Zespol.utworzNowyZespol("Development", manager2);
        } catch (NotUniqueNameException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // dodawanie pracownikow do zespolu
        System.out.println("\nDODAWANIE PRACOWNIKOW DO ZESPOLOW:");
        zespolDevelopment.dodajPracownika(trener1);
        zespolDevelopment.dodajPracownika(trener2);
        zespolDevelopment.dodajPracownika(recepcjonista1);

        // dodawanie tego samego pracownika
        try {
            zespolDevelopment.dodajPracownika(trener1);
        } catch (DuplicateElementException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // dodawanie managera jako pracownika
        try {
            zespolDevelopment.dodajPracownika(manager2);
        } catch (InvalidEmployeeTypeException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // dodawanie listy pracownikow
        List<Pracownik> pracownicyTestingu = new ArrayList<>();
        pracownicyTestingu.add(recepcjonista2);
        zespolTesting.dodajPracownika(pracownicyTestingu);

        System.out.println("Zespol Development:");
        zespolDevelopment.wyswietlListePracownikow();

        System.out.println("\nZespol Testing:");
        zespolTesting.wyswietlListePracownikow();

        // sortowanie za pomoca comparable
        System.out.println("\nSORTOWANIE PRACOWNIKOW:");
        List<Pracownik> wszyscyPracownicy = new ArrayList<>();
        wszyscyPracownicy.add(trener1);
        wszyscyPracownicy.add(trener2);
        wszyscyPracownicy.add(recepcjonista1);
        wszyscyPracownicy.add(recepcjonista2);
        wszyscyPracownicy.add(manager1);
        wszyscyPracownicy.add(manager2);

        Collections.sort(wszyscyPracownicy);
        System.out.println("Pracownicy posortowani:");
        for (Pracownik p : wszyscyPracownicy) {
            System.out.println("- " + p.getImie() + " " + p.getNazwisko() + ", urodzony: " + p.getDataUrodzenia());
        }

        // tworzenie zadan
        System.out.println("\nTWORZENIE ZADAN:");
        Zadanie zadanie1 = new Zadanie("Implementacja logowania", "Utworzenie systemu logowania", true, zespolDevelopment);
        Zadanie zadanie2 = new Zadanie("Testowanie API", "Przeprowadzenie testow API", true, zespolDevelopment);
        Zadanie zadanie3 = new Zadanie("Dokumentacja", "Przygotowanie dokumentacji", false, zespolDevelopment);

        // tworzenie zadan z konstruktorem z 2 argumentami
        Zadanie zadanie4 = new Zadanie("Naprawa bledow", zespolDevelopment);
        System.out.println("Zadanie utworzone z drugim konstruktorem: " + zadanie4.getNazwa() + ", zatwierdzone: " + zadanie4.isZatwierdzenie());

        // dopisanie listy zadan do obiektu praca
        System.out.println("\nPRZYGOTOWANIE PRACY:");
        List<Zadanie> listaZadan = new ArrayList<>();
        listaZadan.add(zadanie1);
        listaZadan.add(zadanie2);
        listaZadan.add(zadanie3);

        Praca praca = new Praca(listaZadan, zespolDevelopment, "Sprint 1");

        // drugi obiekt praca bez dodawania listy
        Praca praca2 = new Praca(zespolTesting, "Sprint 2");
        praca2.dodajZadanie(zadanie4);

        // dodanie tego samego zadania drugi raz
        try {
            praca2.dodajZadanie(zadanie4);
        } catch (NotUniqueZadanieException e) {
            System.out.println("Wyjatek " + e.getMessage());
        }

        // delegowanie zadanie przelozonemu
        System.out.println("\nDELEGOWANIE ZADAN:");
        manager1.delegateTask(zadanie1, trener1);
        manager1.delegateTask(zadanie2, recepcjonista1);

        // dodatkowe metody z IDobryPracownik
        System.out.println("\nRAPORTY PRACOWNIKOW:");
        IDobryPracownik[] pracownicy = {trener1, recepcjonista1, manager1};
        for (IDobryPracownik pracownik : pracownicy) {
            System.out.println(pracownik.generateDailyReport());
            System.out.println("Efektywnosc: " + pracownik.getEfficiency());
        }

        // historia zadan pracownika
        System.out.println("\nHISTORIA ZADAN PRACOWNIKA:");
        System.out.println("Historia zadan " + trener1.getImie() + " " + trener1.getNazwisko() + ":");
        List<Zadanie> historiaZadan = trener1.getHistoriaZadan();
        for (Zadanie zadanie : historiaZadan) {
            System.out.println("- " + zadanie.getNazwa());
        }

        // pobierania zadania po ID
        System.out.println("\nPOBIERANIE ZADANIA PO ID:");
        try {
            int idZadania = zadanie1.getCurrElementId();
            Zadanie pobrane = Praca.getTaskById(idZadania);
            System.out.println("Pobrano zadanie: " + pobrane.getNazwa());

            // nieprawidlowe id dla zadania
            Zadanie niepoprawne = Praca.getTaskById(999);
        } catch (IncorrectTaskIdException e) {
            System.out.println("Wyjatek: " + e.getMessage());
        }

        // uruchomienie zadan
        System.out.println("\nURUCHOMIENIE ZADAN:");
        System.out.println("Zadanie " + zadanie1.getNazwa() + " ma stan początkowy: " + zadanie1.getStan());
        System.out.println("Uruchamianie wszystkich zadan poprzez obiekt Praca...");
        Thread pracaThread = new Thread(praca);
        pracaThread.start();

        try {
            pracaThread.join();
            System.out.println("Wszystkie zadania zostaly zakonczone!");
            System.out.println("Zadanie " + zadanie1.getNazwa() + " ma teraz stan: " + zadanie1.getStan());
        } catch (InterruptedException e) {
            System.out.println("Przerwano wykonywanie zadan: " + e.getMessage());
        }

        System.out.println("\nSTAN ZADAN PO WYKONANIU:");
        System.out.println("Zadanie 1 (" + zadanie1.getNazwa() + "): " + zadanie1.getStan());
        System.out.println("Zadanie 2 (" + zadanie2.getNazwa() + "): " + zadanie2.getStan());
        System.out.println("Zadanie 3 (" + zadanie3.getNazwa() + "): " + zadanie3.getStan() +
                " (niezatwierdzone, zostalo pominiete)");

        // odczyt logow
        System.out.println("\nODCZYT LOGOW OPERACJI:");
        List<String> logi = LogOperacji.odczytajOperacje();
        if (logi.isEmpty()) {
            System.out.println("Brak zapisanych logow (plik log_operacji.txt nie istnieje lub jest pusty)");
        } else {
            for (String s : logi) {
                System.out.println(s);
            }
        }

        // iterowanie po zespole
        System.out.println("\nITEROWANIE PO ZESPOLE");
        System.out.println("Pracownicy zespolu Development:");
        for (Pracownik pracownik : zespolDevelopment) {
            System.out.println("- " + pracownik.getImie() + " " + pracownik.getNazwisko());
        }

        // statyczna lista - wykorzystanie
        System.out.println("\nSTATYCZNA LISTA WSZYSTKICH PRACOWNIKÓW:");
        System.out.println("Laczna liczba utworzonych pracownikow: " + Pracownik.listaPracownikow.size());


    }
}