package Controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next(); // Pass the non-integer input to avoid looping
            }
        } while (continu);
        return i;
    }

    // Returns an integer read from the keyboard within the range [borneMin, borneMax[
    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                if (i >= borneMin && i < borneMax) {
                    continu = false;
                } else {
                    System.out.print("Veuillez rentrer un chiffre dans la plage [" + borneMin + ", " + borneMax + "[ : ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next();
            }
        } while (continu);
        return i;
    }

    // Reads "oui", "non", "o", or "n" and returns a boolean
    public static boolean lireOuiOuNon() {
        String input;
        boolean retour = true;
        boolean continu = true;
        do {
            input = sc.next().toLowerCase();
            if (input.equals("oui") || input.equals("o")) {
                retour = true;
                continu = false;
            } else if (input.equals("non") || input.equals("n")) {
                retour = false;
                continu = false;
            } else {
                System.out.print("Veuillez répondre par 'oui' ou 'non' : ");
            }
        } while (continu);
        return retour;
    }

    // Returns a string read from the keyboard
    public static String lireUneChaine() {
        String retour = sc.next();
        return retour;
    }
}

