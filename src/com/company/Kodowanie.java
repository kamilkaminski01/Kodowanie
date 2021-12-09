package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Kodowanie {

    public static String gadery(String zdanie){
        char[] szyfr = {'g','a','d','e','r','y','p','o','l','u','k','i'};
        char[] odpowiedz = new char[zdanie.length()];

        Boolean on = true;
            for (int i = 0; i < odpowiedz.length; i++){
                for (int j = 0; j < szyfr.length; j++){
                    if (zdanie.charAt(i) == szyfr[j]){
                        if (j%2 == 0){
                            odpowiedz[i] = szyfr[j+1];
                            on = false;
                            break;
                        }
                        else {
                            odpowiedz[i] = szyfr[j-1];
                            on = false;
                            break;
                        }
                    }
                    else {
                        odpowiedz[i] = zdanie.charAt(i);
                    }
                }
            }
            String str = String.valueOf(odpowiedz);
            return str;
    }

    public static final String alfabet = "abcdefghijklmnopqrstuvwxyz";

    public static String cezar(String zdanie, int liczba) {
        zdanie = zdanie.toLowerCase();
        String zaszyfrowane = "";

        for (int i = 0; i < zdanie.length(); i++) {
            char c = zdanie.charAt(i);
            int pozycja = alfabet.indexOf(c);

            if (pozycja == -1){
                zaszyfrowane += c;
                continue;
            }

            int temp = (liczba + pozycja) % 26;
            char temp2 = alfabet.charAt(temp);
            zaszyfrowane += temp2;
        }
        return zaszyfrowane;
    }

    public static boolean czyPangram(String zdanie){
        zdanie = zdanie.toLowerCase();

        boolean pangram = true;

        for (char ch = 'a'; ch <= 'z'; ch++){
            if (!zdanie.contains(String.valueOf(ch))){
                pangram = false;
                break;
            }
        }

        if (pangram){
            return true;
        }
        else {
            return false;
        }
    }

    public static String perfectpangram = "Jock nymphs waqf drug vex blitz";

    public static String pangram(String zdanie){
        zdanie.toLowerCase();

        String szyfr = perfectpangram.replaceAll("\\s+","").toLowerCase();

        if (czyPangram(szyfr)) {
            char[] odpowiedz = new char[zdanie.length()];
            boolean temp = true;

            for (int i = 0; i < zdanie.length(); i++) {
                for (int j = 0; j < szyfr.length(); j++) {
                    if (zdanie.charAt(i) == szyfr.charAt(j)) {
                        if (j % 2 == 0) {
                            odpowiedz[i] = szyfr.charAt(j + 1);
                            temp = false;
                            break;
                        } else {
                            odpowiedz[i] = szyfr.charAt(j + 1);
                            temp = false;
                            break;
                        }
                    } else {
                        odpowiedz[i] = zdanie.charAt(i);
                    }
                }
            }
            String str = String.valueOf(odpowiedz);
            return str;
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SZYFROWANIE:");
        System.out.println("-g metoda gaderypoluki");
        System.out.println("-c metoda cezara");
        System.out.println("-p metoda perfect pangram");
        System.out.print("Wybierz metode szyfrowania: ");
        String wybor = scanner.nextLine();

        if (wybor.equals("-g")){

            // Utowrzenie pliku jeśli nie istnieje, zakodowanie pliku jeśli istnieje
            File f = new File("plik.txt");
            if (!f.exists()){
                f.createNewFile();
                PrintStream output = new PrintStream("plik.txt");
                output.println("The quick brown fox jumps over the lazy dog");
                System.out.println("\nUtworzylem plik");
            }
            else {
                System.out.println("\nPlik juz istnieje");

                try (Scanner input = new Scanner(new File("plik.txt"));
                         PrintStream output = new PrintStream(new File("plik2.txt"))) {
                        while (input.hasNextLine()) {
                            output.println(gadery(input.nextLine()));
                        }
                }
                System.out.println("Plik zostal zaszyfrowany do nowego pliku");
            }

        }
        else if (wybor.equals("-c")){

            System.out.println("Przesuniecie: 4");
            int liczba = 4;

            // Utowrzenie pliku jeśli nie istnieje, zakodowanie pliku jeśli istnieje
            File f = new File("plik.txt");
            if (!f.exists()){
                f.createNewFile();
                PrintStream output = new PrintStream("plik.txt");
                output.println("The quick brown fox jumps over the lazy dog");
                System.out.println("\nUtworzylem plik");
            }
            else {
                System.out.println("\nPlik juz istnieje");

                try (Scanner input = new Scanner(new File("plik.txt"));
                         PrintStream output = new PrintStream(new File("plik2.txt"))){
                        while (input.hasNextLine()){
                            output.println(cezar(input.nextLine(), liczba));
                        }
                }
                System.out.println("Plik zostal zaszyfrowany do nowego pliku");
            }

        }
        else if (wybor.equals("-p")){

            // Utowrzenie pliku jeśli nie istnieje, zakodowanie pliku jeśli istnieje
            File f = new File("plik.txt");
            if (!f.exists()){
                f.createNewFile();
                PrintStream output = new PrintStream("plik.txt");
                output.println("The quick brown fox jumps over the lazy dog");
                System.out.println("\nUtworzylem plik");
            }
            else {
                System.out.println("\nPlik juz istnieje");

                try (Scanner input = new Scanner(new File("plik.txt"));
                         PrintStream output = new PrintStream(new File("plik2.txt"))){
                        while (input.hasNextLine()){
                            output.println(pangram(input.nextLine()));
                        }
                }
                System.out.println("Plik zostal zaszyfrowany do nowego pliku");
            }
        }
        else {
            System.out.println("Zła wartość");
        }

    }

}
