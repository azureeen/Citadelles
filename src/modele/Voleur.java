package modele;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Exception.RangException;
import Exception.SelfChosen;

public class Voleur extends Personnage {
    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        if (this.getAssassine()) {
            System.out.println("Votre personnage a été assassiné");
        } else {
            boolean continu = true;
            Scanner sc = new Scanner(System.in);
            System.out.println("Quel personnage voulez-vous voler ?");
            for (int i = 0; i < this.getPlateau().getNombrePersonnages(); i++) {
                System.out.println((i + 1) + "." + this.getPlateau().getPersonnage(i).getNom());
            }
            do {
                System.out.println("Votre choix : ");
                try {
                    int j = sc.nextInt();
                    int k = j - 1;
                    if (this.getPlateau().getPersonnage(k).equals(this)) {
                        throw new SelfChosen();
                    } else if (this.getPlateau().getPersonnage(k).getRang() == 1) {
                        throw new RangException();
                    }
                    this.getPlateau().getPersonnage(k).setVole();
                    //Gérer les pièces ??

                    System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été vole.");
                    continu = false;
                } catch (SelfChosen e) {
                    System.out.println("Vous ne pouvez pas vous voler");
                } catch (RangException e) {
                    System.out.println("Vous ne pouvez pas choisir un personnage de rang 1");
                }
            } while (continu);
        }

    }

    @Override
    public void utiliserPouvoirAvatar() {
        if (this.getAssassine()) {
            System.out.println("Votre personnage a été assassiné");
        } else {
            boolean continu = true;
            System.out.println("Quel personnage voulez-vous voler ?");
            for (int i = 0; i < this.getPlateau().getNombrePersonnages(); i++) {
                System.out.println((i + 1) + "." + this.getPlateau().getPersonnage(i).getNom());
            }
            do {
                System.out.println("Votre choix : ");
                try {
                    int j = ThreadLocalRandom.current().nextInt(0, this.getPlateau().getNombrePersonnages()) + 1;
                    int k = j - 1;
                    System.out.println("\n" + k + "\n");
                    if (this.getPlateau().getPersonnage(k).equals(this)) {
                        throw new SelfChosen();
                    } else if (this.getPlateau().getPersonnage(k).getRang() == 1) {
                        throw new RangException();
                    }
                    this.getPlateau().getPersonnage(k).setVole();
                    //Gérer les pièces ??

                    System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été vole.");
                    continu = false;
                } catch (SelfChosen e) {
                    System.out.println("Vous ne pouvez pas vous voler");
                } catch (RangException e) {
                    System.out.println("Vous ne pouvez pas choisir un personnage de rang 1");
                }
            } while (continu);
        }

    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub

    }
}