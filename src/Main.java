import exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== DEMONSTRACJA SYSTEMU ZARZĄDZANIA PRACOWNIKAMI =====\n");


        // tworzenie dzialow
        System.out.println("TWORZENIE DZIAŁÓW:");
        DzialPracownikow dzialIT = DzialPracownikow.utworzDzial("IT");
        DzialPracownikow dzialHR = DzialPracownikow.utworzDzial("HR");
        DzialPracownikow dzialMarketing = DzialPracownikow.utworzDzial("Marketing");

        // nieunikalna nazwa dzialu
        try {
            DzialPracownikow dzialItDuplikat = DzialPracownikow.utworzDzial("IT");
        } catch (NotUniqueNameException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // tworzenie pracownikow
        System.out.println("\nTWORZENIE PRACOWNIKÓW:");

        // trenerzy
        Trener trener1 = new Trener("Jan", "Kowalski", 15, 5, 1980, dzialIT, "Java");
        Trener trener2 = new Trener("Anna", "Nowak", 10, 3, 1985, dzialIT, "Python");

        // za mlody pracownik
        try {
            Trener trenerNiepoprawny = new Trener("Młody", "Pracownik", 1, 1, 2010, dzialIT, "JavaScript");
        } catch (IncorrectBirthYearException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
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
        System.out.println("Inicjały przed zmianą: " + recepcjonista1.toString());
        recepcjonista1.setImie("Robert");
        recepcjonista1.setNazwisko("Kaminski");
        System.out.println("Inicjały po zmianie: " + recepcjonista1.toString());

        // wyswietlanie rpacownikow w dzialach
        System.out.println("\nPRACOWNICY W DZIAŁACH:");
        try {
            System.out.println(dzialIT.wypiszPracownikow());
            System.out.println(dzialHR.wypiszPracownikow());
        } catch (EmptyListException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // 6. Tworzenie zespołów
        System.out.println("\nTWORZENIE ZESPOŁÓW:");
        Zespol zespolDevelopment = Zespol.utworzNowyZespol("Development", manager1);
        Zespol zespolTesting = Zespol.utworzNowyZespol("Testing", manager1);

        // Demonstracja wyjątku dla nieunikalnej nazwy zespołu
        try {
            Zespol zespolDevelopmentDuplikat = Zespol.utworzNowyZespol("Development", manager2);
        } catch (NotUniqueNameException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // 7. Dodawanie pracowników do zespołów
        System.out.println("\nDODAWANIE PRACOWNIKÓW DO ZESPOŁÓW:");
        zespolDevelopment.dodajPracownika(trener1);
        zespolDevelopment.dodajPracownika(trener2);
        zespolDevelopment.dodajPracownika(recepcjonista1);

        // Demonstracja wyjątku dla duplikacji pracownika
        try {
            zespolDevelopment.dodajPracownika(trener1);
        } catch (DuplicateElementException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // Demonstracja wyjątku dla dodawania managera jako zwykłego pracownika
        try {
            zespolDevelopment.dodajPracownika(manager2);
        } catch (InvalidEmployeeTypeException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // Dodawanie listy pracowników
        List<Pracownik> pracownicyTestingu = new ArrayList<>();
        pracownicyTestingu.add(recepcjonista2);
        zespolTesting.dodajPracownika(pracownicyTestingu);

        System.out.println("Zespół Development:");
        zespolDevelopment.wyswietlListePracownikow();

        System.out.println("\nZespół Testing:");
        zespolTesting.wyswietlListePracownikow();

        // 8. Demonstracja metody compareTo (sortowanie pracowników)
        System.out.println("\nSORTOWANIE PRACOWNIKÓW (IMPLEMENTACJA COMPARABLE):");
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

        // 9. Tworzenie zadań
        System.out.println("\nTWORZENIE ZADAŃ:");
        Zadanie zadanie1 = new Zadanie("Implementacja logowania", "Utworzenie systemu logowania", true, zespolDevelopment);
        Zadanie zadanie2 = new Zadanie("Testowanie API", "Przeprowadzenie testów API", true, zespolDevelopment);
        Zadanie zadanie3 = new Zadanie("Dokumentacja", "Przygotowanie dokumentacji", false, zespolDevelopment);

        // Zadanie z drugim konstruktorem
        Zadanie zadanie4 = new Zadanie("Naprawa błędów", zespolDevelopment);
        System.out.println("Zadanie utworzone z drugim konstruktorem: " + zadanie4.getNazwa() + ", zatwierdzone: " + zadanie4.isZatwierdzenie());

        // 10. Przypisanie zadań do kolekcji Praca
        System.out.println("\nPRZYGOTOWANIE PRACY:");
        List<Zadanie> listaZadan = new ArrayList<>();
        listaZadan.add(zadanie1);
        listaZadan.add(zadanie2);
        listaZadan.add(zadanie3);

        Praca praca = new Praca(listaZadan, zespolDevelopment, "Sprint 1");

        // Demonstracja drugiego konstruktora Praca
        Praca praca2 = new Praca(zespolTesting, "Sprint 2");
        praca2.dodajZadanie(zadanie4);

        // Demonstracja wyjątku dla duplikacji zadania
        try {
            praca2.dodajZadanie(zadanie4);
        } catch (NotUniqueZadanieException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // 11. Delegowanie zadań pracownikom
        System.out.println("\nDELEGOWANIE ZADAŃ:");
        manager1.delegateTask(zadanie1, trener1);
        manager1.delegateTask(zadanie2, recepcjonista1);

        // 12. Demonstracja interfejsu IDobryPracownik
        System.out.println("\nRAPORTY PRACOWNIKÓW (IDobryPracownik):");
        IDobryPracownik[] pracownicy = {trener1, recepcjonista1, manager1};
        for (IDobryPracownik pracownik : pracownicy) {
            System.out.println(pracownik.generateDailyReport());
            System.out.println("Efektywność: " + pracownik.getEfficiency());
        }

        // 13. Sprawdzanie historii zadań pracownika
        System.out.println("\nHISTORIA ZADAŃ PRACOWNIKA:");
        System.out.println("Historia zadań " + trener1.getImie() + " " + trener1.getNazwisko() + ":");
        List<Zadanie> historiaZadan = trener1.getHistoriaZadan();
        for (Zadanie zadanie : historiaZadan) {
            System.out.println("- " + zadanie.getNazwa());
        }

        // 14. Pobieranie zadania po ID
        System.out.println("\nPOBIERANIE ZADANIA PO ID:");
        try {
            int idZadania = zadanie1.getCurrElementId();
            Zadanie pobrane = Praca.getTaskById(idZadania);
            System.out.println("Pobrano zadanie: " + pobrane.getNazwa());

            // Demonstracja wyjątku dla nieprawidłowego ID zadania
            Zadanie niepoprawne = Praca.getTaskById(999);
        } catch (IncorrectTaskIdException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        // 15. Demonstracja sprawdzania stanu zdrowia pracowników przed uruchomieniem zadania
        System.out.println("\nSPRAWDZANIE STANU ZDROWIA PRZED URUCHOMIENIEM ZADANIA:");
        // Poniższy blok jest komentarzem, aby nie powodować rzeczywistego błędu, ale pokazuje zasadę działania
        System.out.println("Implementacja w metodzie run() klasy Zadanie sprawdza, czy wszyscy pracownicy są zdrowi:");
        System.out.println("if (this.zespol.getListaPracownikow().stream().anyMatch(pracownik -> !pracownik.isCzyZdrowy())) {");
        System.out.println("    throw new Error(\"Chociaz jeden z pracownikow nie jest zdrowy!\");");
        System.out.println("}");

        // 16. Demonstracja uruchomienia zadań (komentujemy faktyczne uruchomienie, bo trwałoby kilka sekund)
        System.out.println("\nDEMONSTRACJA URUCHOMIENIA ZADAŃ:");
        System.out.println("Zadanie " + zadanie1.getNazwa() + " ma stan: " + zadanie1.getStan());

        // Symulacja przebiegu zadania bez faktycznego uruchamiania
        System.out.println("Po uruchomieniu run():");
        System.out.println("1. Stan zadania zmienia się na: " + Stan.ROZPOCZETE);
        System.out.println("2. Wyświetlane są informacje co sekundę przez " + zadanie1.getCzasWykonania() + " sekund");
        System.out.println("3. Po zakończeniu stan zmienia się na: " + Stan.ZAKONCZONE);
        System.out.println("4. Ustawiana jest dataZakonczenia na bieżący czas");

        // Symulacja uruchomienia wszystkich zadań przez Praca.run()
        System.out.println("\nUruchomienie wszystkich zadań przez Praca.run():");
        System.out.println("1. Zatwierdzone zadania są uruchamiane przez start()");
        System.out.println("2. Niezatwierdzone zadania (jak " + zadanie3.getNazwa() + ") są pomijane");
        System.out.println("3. Metoda join() czeka na zakończenie wszystkich uruchomionych zadań");

        // 17. Odczyt logów operacji
        System.out.println("\nODCZYT LOGÓW OPERACJI:");
        List<String> logi = LogOperacji.odczytajOperacje();
        if (logi.isEmpty()) {
            System.out.println("Brak zapisanych logów (plik log_operacji.txt nie istnieje lub jest pusty)");
        } else {
            for (int i = 0; i < Math.min(5, logi.size()); i++) {
                System.out.println(logi.get(i));
            }
            if (logi.size() > 5) {
                System.out.println("... oraz " + (logi.size() - 5) + " więcej wpisów");
            }
        }

        // 18. Demonstracja iterowania po zespole (implementacja Iterable)
        System.out.println("\nITEROWANIE PO ZESPOLE (INTERFEJS ITERABLE):");
        System.out.println("Pracownicy zespołu Development:");
        for (Pracownik pracownik : zespolDevelopment) {
            System.out.println("- " + pracownik.getImie() + " " + pracownik.getNazwisko());
        }

        // 19. Wykorzystanie statycznej listy wszystkich pracowników
        System.out.println("\nSTATYCZNA LISTA WSZYSTKICH PRACOWNIKÓW:");
        System.out.println("Łączna liczba utworzonych pracowników: " + Pracownik.listaPracownikow.size());


        System.out.println("\n===== KONIEC DEMONSTRACJI =====");
    }
}