/*package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.text.html.HTMLDocument.BlockElement;

import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import Controleur.Interaction;
import modele.*;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;
    private ArrayList<Personnage> listePersonnage = new ArrayList<Personnage>();
    private ArrayList<Personnage> listePersonnageUtilises = new ArrayList<Personnage>();
    private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
    private Interaction interaction = new Interaction();
    private Joueur premierAFinir;
    private Joueur joueurGagnant;

    public Jeu(){
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer(){
        Interaction sc = new Interaction();
        boolean continu = true;
        System.out.println("Bienvenue dans le jeu Citadelles");

        while(continu){
            System.out.println("Menu :\n \n1.Jouer\n2.Regles\n3.Quitter\n");
            System.out.println("Veuillez choisir une option : ");
            int choix = sc.lireUnEntier(1,3);
            switch(choix){
                case 1:
                    this.jouerPartie();
                    break;
                case 2:
                    this.afficherLesRegles();
                    break;
                case 3:
                    continu = false;      
            }
        }
    }

    private void afficherLesRegles(){
        System.out.println("Simple message.");


    } 

    private void jouerPartie(){
        boolean continu = true;
        this.initialisation();
        while(continu){
            this.tourDeJeu();
            this.gestionCouronne();
            this.reinitialisationPersonnage();
            continu = this.partieFinie();          
        }


    }

    private void initialisation(){
        int couronne = generateur.nextInt(4);
        Pioche pioche = Configuration.nouvellePioche();
        this.plateauDeJeu = Configuration.configurationDeBase(pioche);
        for(int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++){
            this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
            for(int j = 0; j < 4; j++){
                this.plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            }
            this.plateauDeJeu.getJoueur(couronne).setPossedeCouronne(true);
        }

        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++){
            listePersonnage.add(this.plateauDeJeu.getPersonnage(i));
        }

        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++){
            listeJoueur.add(this.plateauDeJeu.getJoueur(i));
        }
    }

    private void gestionCouronne(){
        boolean condition = false;
        for(int i = 0; i < listePersonnage.size(); i++){
            Personnage personnage = listePersonnage.get(i);

            System.out.println(personnage.getNom());

            if(!(personnage.getJoueur() == null)){
                if(personnage.getNom().equals("Roi")){
                    for(int j = 0; j < listePersonnage.size(); j++){
                        Personnage personnage2 = listePersonnage.get(j);
                        if(!(personnage2.getJoueur() == null)){
                            personnage2.getJoueur().setPossedeCouronne(false);
                        }
                    }
                    personnage.getJoueur().setPossedeCouronne(true);
                    condition = true;
                }

            }
            
        }
    }

    private void reinitialisationPersonnage(){
        for(int i = 0; i < listePersonnage.size(); i++){
            listePersonnage.get(i).reinitialiser();
        }

    }

    private boolean partieFinie(){
        for(int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++){
            if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8){
                this.calculDesPoints();
                return false;
            }
        }
        return true;
    }

    private void tourDeJeu(){
        Interaction interaction = new Interaction();
        boolean condition = false;
        this.choixPersonnage();
        for(int i = 0; i < 8; i++){

            for(int j = 0; j < listePersonnageUtilises.size(); j++){

                if(listePersonnageUtilises.get(j).getRang() == i+1 && !listePersonnageUtilises.get(j).getAssassine()){

                    System.out.println(listePersonnageUtilises.get(j).getNom() + " joue.\n");


                    if(listePersonnageUtilises.get(j).getVole()){

                        int tresor = listePersonnageUtilises.get(j).getJoueur().nbPieces();
                        listePersonnageUtilises.get(j).getJoueur().retirerPieces(tresor);

                        for(int k = 0; k < listePersonnageUtilises.size(); k++){

                            if(listePersonnageUtilises.get(k).getNom().equals("Voleur")){
                                listePersonnageUtilises.get(k).getJoueur().ajouterPieces(tresor);
                            }
                        }

                    }
                    
                    this.percevoirRessource(listePersonnageUtilises.get(j));
                    listePersonnageUtilises.get(j).percevoirRessourcesSpecifiques();

                    if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){

                        //implémentation de la merveille Laboratoire 

                        //implémentation de la merveille Forge
                        
                        //implémentation de la merveille Cour des Miracles

                        //implémentation de la merveille Ecole de Magie

                        for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.courDesMiracles)){
                                for(int z = 0; z < Quartier.TYPE_QUARTIERS.length; z++){
                                    System.out.println(z+1 + ". " + Quartier.TYPE_QUARTIERS[z]);
                                }
                                System.out.println("Veuillez choisir le nouveau type de votre merveille :\n");
                                int choix  = Interaction.lireUnEntier(1, 6);
                                choix -= 1;
                                this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].setType(Quartier.TYPE_QUARTIERS[choix]);
                                System.out.println("Vous avez change le type de votre merveille en : " + Quartier.TYPE_QUARTIERS[choix] );


                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.ecoleDeMagie)){
                                for(int z = 0; z < Quartier.TYPE_QUARTIERS.length; z++){
                                    System.out.println(z+1 + ". " + Quartier.TYPE_QUARTIERS[z]);
                                }
                                System.out.println("Veuillez choisir le nouveau type de votre merveille :\n");
                                int choix  = Interaction.lireUnEntier(1, 6);
                                choix -= 1;
                                this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].setType(Quartier.TYPE_QUARTIERS[choix]);
                                System.out.println("Vous avez change le type de votre merveille en : " + Quartier.TYPE_QUARTIERS[choix] );

                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.forge) && this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 1){
                                System.out.println("\nVous possedez la merveille forge, vous pouvez donc choisir d'utiliser 2 pieces d'or pour piocher 3 cartes\n");
                                System.out.println("Voulez-vous piocher 3 cartes en echange de 2 pieces d'or ? (o/n)\n");
                                boolean choix = Interaction.lireOuiOuNon();
                                System.out.println(choix);
                                if(choix){
                                    this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(2);
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    System.out.println("Vous avez bien pioche vos 3 cartes.\nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                    
                                }

                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.laboratoire)){
                                System.out.println("\nVous possedez la merveille laboratoire, vous pouvez donc défausser une carte de votre main pour recevoir 2 pieces d'or");
                                System.out.println("Voulez-vous defausser une carte de votre main et recevoir 2 pieces d'or ? (o/n)\n");
                                boolean choix = Interaction.lireOuiOuNon();
                                if(choix){
                                    this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(2);
                                    for(int z = 0; z < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); z++){
                                        System.out.println(z+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getNom() + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getType() + ").");
                                    }
                                    System.out.println("\nQuel quartier choisissez-vous de defausser ?");
                                    int choix2 = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                    choix2 -= 1;
                                    Quartier carteDefausse  = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix2);
                                    System.out.println("Vous avez bien defausse" + carteDefausse.getNom() + ". \nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                    
                                }
                            }
                        }

                        System.out.println("Voulez-vous utiliser votre pouvoir ? (o/n)");
                        boolean choix = Interaction.lireOuiOuNon();

                        if(choix){
                            listePersonnageUtilises.get(j).utiliserPouvoir();
                        }
    
                        System.out.println("Voulez-vous construire ? (o/n)");
                        choix = Interaction.lireOuiOuNon();
                        if(choix){
                            //implémentation de la merveille Carriere
                            
                            //implémentation de la merveille Manufacture
                            boolean condition2 = false;
                            
                            ArrayList<Quartier> quartierDansCite = new ArrayList<Quartier>();
                            
                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                quartierDansCite.add(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]);
                            }

                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
                                if(quartierDansCite.contains(Configuration.carriere)){
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= this.listePersonnageUtilises.get(j).getJoueur().nbPieces()){
                                        condition = true;
                                    }
                                }else{
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= this.listePersonnageUtilises.get(j).getJoueur().nbPieces() && !quartierDansCite.contains(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k))){
                                        condition = true;
                                    }
                                }

                                if(quartierDansCite.contains(Configuration.manufacture)){
                                    System.out.println("Vous avez la merveille manufacture dans votre cite.\nVous avez une reduction d'une piece sur la construction de toutes cartes merveilles.\n");
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= (this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + 1) && !quartierDansCite.contains(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k)) && this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getType().equals("MERVEILLE")){
                                        condition = true;
                                    }
                                }
                               
                                System.out.println((k+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getNom() + " (cout : " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() + ")" );
                            }

                            quartierDansCite.clear();
    
                            if(!condition){
                                System.out.println("Vous n'avez pas assez de pièces pour construire un quartier ou vous ne pouvez pas construire un quartier deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                            }else{
                                do{
                                    System.out.println("Vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s).\n" + "Veuillez choisir un quartier à construire : ");
                                    int choix2 = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size()) - 1;

                                    //implémentation de la merveille tripot
                                    
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() > this.listePersonnageUtilises.get(j).getJoueur().nbPieces() && !this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot)){
                                        condition = true;
                                        System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                                    }else if((this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) >= this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout()){
                                        condition = false;

                                        for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.manufacture)){
                                                condition2 = true;
                                            }
                                        }

                                        if(condition2){
                                            System.out.println("Vous avez la merveille manufacture dans votre cite, la construction d'une autre merveille est donc réduit d'une piece.");
                                            Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                            this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                            this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(1);
                                            System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                            System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                                        }else{

                                            if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot) && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) > this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() ){
                                                System.out.println("Vous avez choisi de construire la merveille triport \nVous avez " + (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) + " cartes dans votre mains. \nVous pouvez choisir de payer le cout de construction du triport en partie ou entierement avec vos cartes. \nVoulez-vous payer le triport en or ou en cartes ?\n1. or\n2. cartes\n" );
                                                int choix3 = Interaction.lireUnEntier(1, 2);

                                                if(choix3 == 1){
                                                    if(this.listePersonnageUtilises.get(j).getJoueur().nbPieces() < this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout()){
                                                        System.out.println("Vous n'avez pas assez de pieces");
                                                    }else{
                                                        Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                                        this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                                        System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                                        System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                                                                
                                                    }
                                                }else if(choix3 == 2 && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) > 0 ){
                                                    int cout = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout();
                                                    System.out.println("Une carte vaut une piece d'or, combien de cartes voulez-vous dépenser ?");
                                                    int choix4 = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1);
                                                    for(int l = 0; l < choix4; l++){
                                                        boolean continu3 = true;
                                                        do{
                                                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
                                                                System.out.println(k+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k) + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() + ")");
                                                            }
                                                            System.out.println("Quel quartier voulez-vous retirer de votre main ?");
                                                            int choix5 = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                                            choix5 -= 1;
                                                            if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix5).equals(Configuration.tripot)){
                                                                System.out.println("Vous ne pouvez pas choisir le triport, veuillez choisir un autre quartier.");
                                                                continu3 = true;
                                                            }else{
                                                                cout -= 1;
                                                                continu3 = false;
                                                                Quartier quartierConvertiEnPiece = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix5);
                                                                System.out.println(quartierConvertiEnPiece + " a ete retire de votre main.\nIl vous reste " + (choix4 - (l+1)) + " carte(s) a retirer de votre main");
                                                            }

                                                        }while(continu3);
                                                    }
                                                    System.out.println("\nIl vous reste" + cout + " piece(s) a payer.\n");
                                                    this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(cout);

                                                    Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                                    this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                                   
                                                    System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");      
                                                }
                                            }else{

                                                Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                                this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                                System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                                System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );

                                            }
                                            


                                        }

                                        
                                        

                                    }else{
                                        condition = true;
                                        System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                                    }
    
                                }while(condition);
                                
                            }
                        }
                        
                    }else if(!listePersonnageUtilises.get(j).getJoueur().getNom().equals(null) && !listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){

                        //implémentation de la merveille Laboratoire 

                        //implémentation de la merveille Forge

                        //implémentation de la merveille Cour des Miracles

                        //implémentation de la merveille Ecole de Magie

                        for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.courDesMiracles)){
                                for(int z = 0; z < Quartier.TYPE_QUARTIERS.length; z++){
                                    System.out.println(z+1 + ". " + Quartier.TYPE_QUARTIERS[z]);
                                }
                                System.out.println("Veuillez choisir le nouveau type de votre merveille :\n");
                                int choix  = generateur.nextInt(5);
                                System.out.println(choix);
                                this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].setType(Quartier.TYPE_QUARTIERS[choix]);
                                System.out.println("\nVous avez change le type de votre merveille en : " + Quartier.TYPE_QUARTIERS[choix] + "\n" );


                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.ecoleDeMagie)){
                                for(int z = 0; z < Quartier.TYPE_QUARTIERS.length; z++){
                                    System.out.println(z+1 + ". " + Quartier.TYPE_QUARTIERS[z]);
                                }
                                System.out.println("Veuillez choisir le nouveau type de votre merveille :\n");
                                int choix  = generateur.nextInt(5);
                                choix -= 1;
                                this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].setType(Quartier.TYPE_QUARTIERS[choix]);
                                System.out.println("Vous avez change le type de votre merveille en : " + Quartier.TYPE_QUARTIERS[choix] );

                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.forge) && this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 1){
                                System.out.println("\nVous possedez la merveille forge, vous pouvez donc choisir d'utiliser 2 pieces d'or pour piocher 3 cartes\n");
                                System.out.println("Voulez-vous piocher 3 cartes en echange de 2 pieces d'or ? (o/n)\n");
                                boolean choix = generateur.nextBoolean();
                                if(choix){
                                    this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(2);
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    this.listePersonnageUtilises.get(j).getJoueur().getMain().add(this.plateauDeJeu.getPioche().piocher());
                                    System.out.println("Vous avez bien pioche vos 3 cartes.\nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                    
                                }

                            }

                            if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.laboratoire)){
                                System.out.println("\nVous possedez la merveille laboratoire, vous pouvez donc défausser une carte de votre main pour recevoir 2 pieces d'or");
                                System.out.println("Voulez-vous defausser une carte de votre main et recevoir 2 pieces d'or ? (o/n)\n");
                                boolean choix = generateur.nextBoolean();
                                if(choix){
                                    this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(2);
                                    for(int z = 0; z < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); z++){
                                        System.out.println(z+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getNom() + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getType() + ").");
                                    }
                                    System.out.println("\nQuel quartier choisissez-vous de defausser ?");
                                    int choix2 = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                    Quartier carteDefausse  = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix2);
                                    System.out.println("Vous avez bien defausse" + carteDefausse.getNom() + ". \nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                    
                                }
                            }
                        }

                        System.out.println("Voulez-vous utiliser votre pouvoir ? (o/n)");
                        boolean choix = generateur.nextBoolean();
                        System.out.println(choix + "\n");
                        if(choix){
                            listePersonnageUtilises.get(j).utiliserPouvoirAvatar();
                        }
    
                        System.out.println("Voulez-vous construire ? (o/n)");
                        choix = generateur.nextBoolean();
                        System.out.println(choix + "\n");
                        if(choix){

                            //implémentation de la merveille Manufacture
                            boolean condition2 = false;

                            //implémentation de la merveille Carriere
                            
                            ArrayList<Quartier> quartierDansCite = new ArrayList<Quartier>();

                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                 quartierDansCite.add(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]);
                            }

                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
                                if(quartierDansCite.contains(Configuration.carriere)){
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= this.listePersonnageUtilises.get(j).getJoueur().nbPieces()){
                                        condition = true;
                                    }
                                }else{
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= this.listePersonnageUtilises.get(j).getJoueur().nbPieces() && !quartierDansCite.contains(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k))){
                                        condition = true;
                                    }
                                }

                                if(quartierDansCite.contains(Configuration.manufacture)){
                                    System.out.println("Vous avez la merveille manufacture dans votre cite.\nVous avez une reduction d'une piece sur la construction de toutes cartes merveilles.\n");
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() <= (this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + 1) && !quartierDansCite.contains(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k)) && this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getType().equals("MERVEILLE")){
                                        condition = true;
                                    }
                                }
                               
                                System.out.println((k+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getNom() + " (cout : " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() + ")" );
                            }

                            quartierDansCite.clear();
    
                            if(!condition){
                                System.out.println("Vous n'avez pas assez de pièces pour construire un quartier ou vous ne pouvez pas construire un quartier deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                            }else{
                                do{
                                    System.out.println("Vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s).\n" + "Veuillez choisir un quartier à construire : ");
                                    int choix2 = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                    System.out.println(choix2 + "\n");
                                   //implémentation de la merveille tripot
                                    
                                   if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() > this.listePersonnageUtilises.get(j).getJoueur().nbPieces() && !this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot)){
                                       condition = true;
                                       System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                                   }else if((this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) >= this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout()){
                                       condition = false;

                                       for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                           if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.manufacture)){
                                               condition2 = true;
                                           }
                                       }

                                       if(condition2){
                                           System.out.println("Vous avez la merveille manufacture dans votre cite, la construction d'une autre merveille est donc réduit d'une piece.");
                                           Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                           this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                           this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(1);
                                           System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                           System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                                       }else{

                                           if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot) && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) > this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() ){
                                               System.out.println("Vous avez choisi de construire la merveille triport \nVous avez " + (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) + " cartes dans votre mains. \nVous pouvez choisir de payer le cout de construction du triport en partie ou entierement avec vos cartes. \nVoulez-vous payer le triport en or ou en cartes ?\n1. or\n2. cartes\n" );
                                               int choix3 = generateur.nextInt(1) + 1;

                                               if(choix3 == 1){
                                                   if(this.listePersonnageUtilises.get(j).getJoueur().nbPieces() < this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout()){
                                                       System.out.println("Vous n'avez pas assez de pieces");
                                                   }else{
                                                       Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                                       this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                                       System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                                       System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                                                               
                                                   }
                                               }else if(choix3 == 2 && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) > 0 ){
                                                   int cout = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout();
                                                   System.out.println("Une carte vaut une piece d'or, combien de cartes voulez-vous dépenser ?");
                                                   int choix4 = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1);
                                                   if(choix4 == 0){
                                                       choix4++;
                                                   }
                                                   for(int l = 0; l < choix4; l++){
                                                       boolean continu3 = true;
                                                       do{
                                                           for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
                                                               System.out.println(k+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k) + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() + ")");
                                                           }
                                                           System.out.println("Quel quartier voulez-vous retirer de votre main ?");
                                                           int choix5 = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size()- 1);
                                                           if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix5).equals(Configuration.tripot)){
                                                               System.out.println("Vous ne pouvez pas choisir le triport, veuillez choisir un autre quartier.");
                                                               continu3 = true;
                                                           }else{
                                                               cout -= 1;
                                                               continu3 = false;
                                                               Quartier quartierConvertiEnPiece = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix5);
                                                               System.out.println(quartierConvertiEnPiece + " a ete retire de votre main.\nIl vous reste " + (choix4 - (l+1)) + " carte(s) a retirer de votre main");
                                                           }

                                                       }while(continu3);
                                                   }
                                                   System.out.println("\nIl vous reste" + cout + " piece(s) a payer.\n");
                                                   this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(cout);

                                                   Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                                   this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                                  
                                                   System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");      
                                               }
                                           }else{

                                               Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
                                               this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                                               System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

                                               System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );

                                           }
                                           


                                       }

                                       
                                       

                                   }else{
                                       condition = true;
                                       System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possede la merveille Carriere).");
                                   }
   
    
                                }while(condition);
                                
                            }
                        }

                    }         

                }

                if(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() == 8 && premierAFinir == null){
                    premierAFinir = this.listePersonnageUtilises.get(j).getJoueur();
                    System.out.println("\n" + premierAFinir.getNom() + "a 8 quartiers en premier.");
                }else if(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() == 8 && !(premierAFinir == null)){
                    this.listePersonnageUtilises.get(j).getJoueur().setAFini(true);
                }
            }
        }

        listeJoueur.clear();
        listePersonnage.clear();
        listePersonnageUtilises.clear();

        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++){
            listePersonnage.add(this.plateauDeJeu.getPersonnage(i));
        }

        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++){
            listeJoueur.add(this.plateauDeJeu.getJoueur(i));
        }

    }

    private void choixPersonnage(){
        System.out.println("Choix des personnages :\n");

        int nbCartesEcarteeFaceVisible = 2;
        int nbCartesEcarteeFaceCachee = 1;

        for(int i = 0; i < nbCartesEcarteeFaceVisible; i ++){
            int persoARetirer = generateur.nextInt(listePersonnage.size());
            Personnage perso = listePersonnage.get(persoARetirer);
            listePersonnage.remove(persoARetirer);
            System.out.println("Le personnage " + "\"" + perso.getNom() + "\"" + " est ecarte face visible\n" );
        }

        
        for(int i = 0; i < nbCartesEcarteeFaceCachee; i ++){
            int persoARetirer = generateur.nextInt(listePersonnage.size());
            listePersonnage.remove(persoARetirer);
            System.out.println("Un personnage est ecarte face cachee\n");
        }
        
        for(int i = 0; i < listeJoueur.size(); i++){
            if(listeJoueur.get(i).getPossedeCouronne()){
                Joueur possedeCouronne = listeJoueur.get(i);
                listeJoueur.remove(i);
                listeJoueur.add(0, possedeCouronne);
            }
        }

        for(int i = 0; i < listeJoueur.size(); i++){

          if(listeJoueur.get(0).getNom().equals("Joueur") && listeJoueur.get(i).getNom().equals("Joueur")){
            System.out.println("Vous avez la couronne !\n");

            for(int j = 0; j < listePersonnage.size(); j++){
                System.out.println((j+1) + "." + listePersonnage.get(j).getNom());
            }
            
            System.out.println("\nQuel personnage choisissez vous ?");
            int choix = interaction.lireUnEntier(1, listePersonnage.size()) - 1;
            
            System.out.println("Vous avez choisi le personnage " + this.listePersonnage.get(choix).getNom() + "\n");

            this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(i));
            this.listePersonnageUtilises.add(listePersonnage.get(choix));
            listePersonnage.remove(choix); 

          }else if(listeJoueur.get(i).getNom().equals("Joueur") && !listeJoueur.get(0).getNom().equals("Joueur")) {
            System.out.println("A vous de jouer\n");

            for(int j = 0; j < listePersonnage.size(); j++){
                System.out.println((j+1) + "." + listePersonnage.get(j).getNom());
            }
            
            System.out.println("\nQuel personnage choisissez vous ?");
            int choix = interaction.lireUnEntier(1, listePersonnage.size()) - 1;

            System.out.println("Vous avez choisi le personnage " + this.listePersonnage.get(choix).getNom() + "\n");

            this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(i));
            this.listePersonnageUtilises.add(listePersonnage.get(choix));
            listePersonnage.remove(choix);  

          }else{
            System.out.println("A " + listeJoueur.get(i).getNom() + " de jouer\n");

            for(int j = 0; j < listePersonnage.size(); j++){
                System.out.println((j+1) + "." + listePersonnage.get(j).getNom());
            }
            
            System.out.println("\nQuel personnage choisissez vous ?");
            int choix = generateur.nextInt(listePersonnage.size() - 1) + 1;
            System.out.println(choix); 
            choix -= 1; 

            System.out.println("Vous avez choisi le personnage " + this.listePersonnage.get(choix).getNom() + "\n");

            this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(i));
            this.listePersonnageUtilises.add(listePersonnage.get(choix));
            listePersonnage.remove(choix);
          }
          
          
          
          
        }



    }

    private void percevoirRessource(Personnage personnage){

        if(personnage.getJoueur().getNom().equals("Joueur")){
            Interaction interaction = new Interaction();
            System.out.println("Choisissez vos ressources :\n1. 2 pieces d'or \n2. Piocher une carte \n");
            int choix = Interaction.lireUnEntier(1, 2);
            System.out.println("\n");
            switch(choix){
                case 1:
                    personnage.getJoueur().ajouterPieces(2);

                    System.out.println("Ajout de 2 pièces");

                    break;
                case 2:
                    ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();
                    piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                    piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());

                    //implémentation merveille Bibliotheque
                    Boolean possedeBibliotheque = false;
                    for(int i = 0; i < personnage.getJoueur().getMain().size(); i++){
                        if(!(personnage.getJoueur().getCite()[i] == null)){
                            if(personnage.getJoueur().getCite()[i].equals(Configuration.bibliotheque)){
                                possedeBibliotheque = true;
                            }
                        }
                    }

                    for(int i = 0; i < piocheJoueur.size(); i++){
                        System.out.println(i + 1 + ". " + piocheJoueur.get(i).getNom() + " (" + piocheJoueur.get(i).getCout() + " pieces)");
                    }

                    if(possedeBibliotheque){
                        System.out.println("\nVous possedez la merveille Bibliotheque, vous gardez donc les deux cartes\n");
                        for(int i = 0; i < piocheJoueur.size(); i++){
                            personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(i));      
                        }
                        break;
                    }else{
                        System.out.println("Veuillez choisir une carte à garder :\n");
                        int choix2 = Interaction.lireUnEntier(1, 2) - 1;
                        personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(choix2)); 
                        break;
                    }

                    
            }

        }else if(!personnage.getJoueur().equals(null) && !personnage.getJoueur().getNom().equals("Joueur") ){
            System.out.println("Choisissez vos ressources :\n1. 2 pieces d'or \n2. Piocher une carte\n");
            int choix = generateur.nextInt(1) + 1;

            System.out.println(choix + "\n");

            switch(choix){
                case 1:
                    personnage.getJoueur().ajouterPieces(2);

                    System.out.println("Ajout de 2 pièces");

                    break;
                case 2:
                    ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();
                    piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                    piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                    
                    //implémentation de la merveille Bibliotheque
                    Boolean possedeBibliotheque = false;
                    for(int i = 0; i < personnage.getJoueur().getMain().size(); i++){
                        if(personnage.getJoueur().getCite()[i].equals(Configuration.bibliotheque)){
                            possedeBibliotheque = true;
                        }
                    }

                    for(int i = 0; i < piocheJoueur.size(); i++){
                        System.out.println(i + 1 + ". " + piocheJoueur.get(i).getNom() + " (" + piocheJoueur.get(i).getCout() + " pieces)");
                    }

                    if(possedeBibliotheque){
                        System.out.println("\nVous possedez la merveille Bibliotheque, vous gardez donc les deux cartes\n");
                        for(int i = 0; i < piocheJoueur.size(); i++){
                            personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(i));      
                        }
                    }else{
                        System.out.println("Veuillez choisir une carte à garder :\n");
                        int choix2 = generateur.nextInt(1);
                        personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(choix2)); 
                        break;
                    }
            }

        }
       


    }

    private void calculDesPoints(){
        ArrayList<Integer> pointsListe = new ArrayList<Integer>();

        for(int i = 0; i < listeJoueur.size(); i++){
            Joueur joueur = listeJoueur.get(i);
            int points = 0;
            ArrayList<String> typesQuartiers = new ArrayList<String>();

            for(int j = 0; j < joueur.nbQuartiersDansCite(); j++){
                points += joueur.getCite()[i].getCout();
                typesQuartiers.add(joueur.getCite()[i].getType());
            }

            if(typesQuartiers.contains("RELIGIEUX") && typesQuartiers.contains("MILITAIRE") && typesQuartiers.contains("NOBLE") && typesQuartiers.contains("COMMERCANT") && typesQuartiers.contains("MERVEILLE")){
                points += 3;
            }

            if(joueur.equals(premierAFinir)){
                points += 4;
                break;

            }else if(joueur.getAFini()){
                points += 2;
            }

            //implémentation de la merveille dracoport


            //implémentation de la merveille Fontaine aux Souhaits


            //implémentation de la merveille Salle des Cartes

            //implémentation de la merveille Statue Equestre


            for(int j = 0; j< joueur.nbQuartiersDansCite(); j++){
                if(joueur.getCite()[j].equals(Configuration.dracoport)){
                    points += 2;
                }

                if(joueur.getCite()[j].equals(Configuration.fontaineAuxSouhaits)){
                    for(int k = 0; k < joueur.nbQuartiersDansCite(); k++){
                        if(joueur.getCite()[k].getType().equals("MERVEILLE")){
                            points += 1;
                        }
                    }
                }

                if(joueur.getCite()[j].equals(Configuration.salleDesCartes)){
                    points += joueur.nbQuartiersDansMain();
                }

                if(joueur.getCite()[j].equals(Configuration.statueEquestre) && joueur.getPossedeCouronne()){
                    points += 5;
                }
                
                if(joueur.getCite()[j].equals(Configuration.tresorImperial)){
                    points += joueur.nbPieces();
                }

            }

            

            pointsListe.add(points);
            joueur.setPoints(points);
            System.out.println(joueur.getNom() + ": " + points + " points");

        }

        if(Collections.frequency(pointsListe, Collections.max(pointsListe)) == 1){
            joueurGagnant = this.listeJoueur.get(pointsListe.indexOf(Collections.max(pointsListe)));
        }else{
            int maxPoints = pointsListe.indexOf(Collections.max(pointsListe));
            int maxRang = 0;
            for(int i = 0; i < listeJoueur.size(); i++){
                if(listeJoueur.get(i).getPoints() == maxPoints && listeJoueur.get(i).getPersonnage().getRang() > maxRang){
                    joueurGagnant = listeJoueur.get(i);
                }
            }
        }

        System.out.println(joueurGagnant.getNom() + " a gagne");

        

    }


    


    
}
*/