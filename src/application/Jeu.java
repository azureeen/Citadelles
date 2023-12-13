package application;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import Controleur.Interaction;


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
    private Personnage sorciere;
    private Joueur joueurRoiPatricien;
    private boolean construitEcurie;
    public static int nbPersonnages;

    public static int nbJoueurs;

    private Personnage persoFaceCachee;

    public static int orDesTaxes;

    public static ArrayList<Quartier> quartiersSousMusee = new ArrayList<Quartier>();

    public Jeu(){
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public PlateauDeJeu getPlateau(){
        return this.plateauDeJeu;
    }

    public void setPlateau(PlateauDeJeu plateau){
        this.plateauDeJeu = plateau;
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
                    continu = false;
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
        System.out.println("Deux a huit joueurs s\'affrontent pour construire le plus rapidement possible la plus prestigieuse cite.\nPour cela, chaque joueur devra construire des quartiers, ayant chacun des couts differents.\nComme dans un jeu de role, chaque joueur doit se mettre dans la peau d’un personnage, a ceci pres que les joueurs changent de personnage a chaque tour de jeu.\nCes personnages ont chacun des pouvoirs particuliers : la meilleure stategie est de choisir un personnage au bon moment du jeu.");


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

        //or des taxes

        orDesTaxes = 0;

        //initialisation du premier à finir

        premierAFinir = null;

        //implémentation nombre de Joueurs
        nbPersonnages = 8;
        nbJoueurs = 0;

        System.out.println("Vous voulez une partie de combien de joueurs ?\n");
        nbJoueurs = Interaction.lireUnEntier(2, 8);

        if(nbJoueurs==2){

            nbPersonnages=8;

        }else if(nbJoueurs ==3){

            nbPersonnages=9;

        }else if(nbJoueurs == 8){

            nbPersonnages = 9;

        }else{
            System.out.println("Vous voulez une partie de combien de personnages ?\n");
            nbPersonnages = Interaction.lireUnEntier(8, 9);
        }

        
        

        String[] configuration = {"configuration de base", "Aristocrate ambitieux", "Intrigants Subtils", "Emissaires Illustres", "Dignitaires Sournois", "Oligarques Tenaces", "Nobles Retors"};
        
        //implémentation différentes configurations
        int configurationAChoisir = 0;
        Pioche pioche;
        int couronne;
        do {
            System.out.println("Veuillez choisir votre configuration de jeu : \n");
            for(int i = 0; i<configuration.length; i++){
                System.out.println((i+1) + ". " + configuration[i]);
            }

            
            configurationAChoisir = Interaction.lireUnEntier(1, configuration.length);
            configurationAChoisir--;

            couronne = generateur.nextInt(nbJoueurs);
            pioche = Configuration.nouvellePioche();
            if(configurationAChoisir==0 && nbPersonnages==9){
                System.out.println("Vous ne pouvez pas choisir la configuration de base avec 9 personnages.");
            }
        } while (configurationAChoisir==0 && nbPersonnages==9);
        

        switch(configurationAChoisir){
            case 0:
                this.plateauDeJeu = Configuration.configurationDeBase(pioche);
                break;
            
            case 1:
                this.plateauDeJeu = Configuration.configurationAristocrateAmbitieux(pioche);
                break;

            case 2:
                this.plateauDeJeu = Configuration.configurationIntrigantsSubtils(pioche);
                break;

            case 3:
                this.plateauDeJeu = Configuration.configurationEmissairesIllustres(pioche);
                break;

            case 4:
                this.plateauDeJeu = Configuration.configurationDignitairesSournois(pioche);
                break;

            case 5:
                this.plateauDeJeu = Configuration.configurationOligarquesTenaces(pioche);
                break;

            case 6:
                this.plateauDeJeu = Configuration.configurationNoblesRetors(pioche);
                break;

            

        }


        
        


        



        for(int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++){
            this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
            for(int j = 0; j < 4; j++){
               this.plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            }
            
            
        }
        this.plateauDeJeu.getJoueur(couronne).setPossedeCouronne(true);
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
                if((personnage.getNom().equals("Roi")||personnage.getNom().equals("Patricien")) && !personnage.getEnsorcele()){
                    for(int j = 0; j < listePersonnage.size(); j++){
                        Personnage personnage2 = listePersonnage.get(j);
                        if(!(personnage2.getJoueur() == null)){
                            personnage2.getJoueur().setPossedeCouronne(false);
                        }
                    }
                    personnage.getJoueur().setPossedeCouronne(true);
                    condition = true;
                }else if((personnage.getNom().equals("Roi")||personnage.getNom().equals("Patricien")) && personnage.getEnsorcele()){
                    for(int j = 0; j < listePersonnage.size(); j++){
                        Personnage personnage2 = listePersonnage.get(j);
                        if(!(personnage2.getJoueur() == null)){
                            personnage2.getJoueur().setPossedeCouronne(false);
                        }
                    }
                    joueurRoiPatricien.setPossedeCouronne(true);
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
            if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() == 8 || this.plateauDeJeu.getJoueur(i).nbQuartiersMonumentDansCite() == 8){
                this.calculDesPoints();
                listeJoueur.clear();
                listePersonnage.clear();
                listePersonnageUtilises.clear();
                quartiersSousMusee.clear();
                return false;
            }
        }
        return true;
    }

    private void tourDeJeu(){
        boolean condition = false;
        boolean bailliPresent=false;

        for(int i = 0; i < listePersonnage.size(); i++){
            if(listePersonnage.get(i).getNom().equals("Bailli")){
                bailliPresent = true;
            }
        }


        boolean quartierNonCommercantDejaConstruit = false;
        this.choixPersonnage();
        for(int i = 0; i < nbPersonnages; i++){

            for(int j = 0; j < listePersonnageUtilises.size(); j++){

                if(listePersonnageUtilises.get(j).getRang() == i+1 && !listePersonnageUtilises.get(j).getAssassine()){
                    System.out.println("--------------------------------------------------------------------------------------");
                    System.out.println(listePersonnageUtilises.get(j).getNom() + " joue.\n");
                    construitEcurie = false;

                    
                    if(listePersonnageUtilises.get(j).getVraiEchevin()||listePersonnageUtilises.get(j).getFauxEchevin()){
                        System.out.println("Vous avez un mandat de requisition, s'il s'agit d'un vrai mandat, le quartier que vous comptez construire sera construit dans la cité de l'echevin et vous serez remboursez");
                        listePersonnageUtilises.get(j).setFauxEchevin(false);
                    }


                    if(listePersonnageUtilises.get(j).getVole()){

                        int tresor = listePersonnageUtilises.get(j).getJoueur().nbPieces();
                        listePersonnageUtilises.get(j).getJoueur().retirerPieces(tresor);

                        for(int k = 0; k < listePersonnageUtilises.size(); k++){

                            if(listePersonnageUtilises.get(k).getNom().equals("Voleur")){
                                listePersonnageUtilises.get(k).getJoueur().ajouterPieces(tresor);
                            }
                        }

                    }
                    System.out.println("Pour information vous avez "+listePersonnageUtilises.get(j).getJoueur().nbPieces()+" pièces.");
                    this.percevoirRessource(listePersonnageUtilises.get(j));
                    listePersonnageUtilises.get(j).percevoirRessourcesSpecifiques();

                    if(listePersonnageUtilises.get(j).getFausseMenace()||listePersonnageUtilises.get(j).getVraieMenace()){
                        System.out.println("Vous êtes menacé par la maitre-chanteuse, voulez vous payer la moitié de votre trésor pour enlever le jeton?");
                        boolean choixTresor;
                        if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                            choixTresor = Interaction.lireOuiOuNon();
                        }else{
                            choixTresor = generateur.nextBoolean();
                        }
                        int taxe=0;
                        if(choixTresor){
                            taxe = listePersonnageUtilises.get(j).getJoueur().nbPieces()/2;
                            System.out.println("Vous payer "+taxe);

                        }else if(listePersonnageUtilises.get(j).getVraieMenace()){
                            taxe = listePersonnageUtilises.get(j).getJoueur().nbPieces();
                            System.out.println("Vous payer "+taxe);

                        }else{
                            System.out.println("Vous ne payez rien !");
                        }
                        listePersonnageUtilises.get(j).getJoueur().retirerPieces(taxe);
                        for(int m=0;m<listePersonnageUtilises.size(); m++){
                            if(listePersonnageUtilises.get(m).getNom().equals("Maître-Chanteuse")){
                                listePersonnageUtilises.get(m).getJoueur().ajouterPieces(taxe);
                            }
                        }
                        listePersonnageUtilises.get(j).setFausseMenace(false);
                        listePersonnageUtilises.get(j).setVraieMenace(false);
                    }

                    if(listePersonnageUtilises.get(j).getEnsorcele()){
                        if(listePersonnageUtilises.get(j).getNom().equals("Roi")||listePersonnageUtilises.get(j).getNom().equals("Patricien")){
                            joueurRoiPatricien = listePersonnageUtilises.get(j).getJoueur();
                        }
                        System.out.println("Vous avez été ensorcelé, la sorciere joue à votre place.");
                        sorciere.activationPouvoirSorciere(sorciere.getJoueur());
                        
                        
                    }

                    if(!(listePersonnageUtilises.get(j).getNom().equals("Sorciere"))){

                        
                        //implémentation de la merveille Laboratoire 

                        //implémentation de la merveille Forge
                        
                        //implémentation de la merveille Poudriere

                        //implémentation de la merveille Musee
                        
                        if(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()>0){
                            for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                    //-----------------------------------------------------------------------//
                                    //réinitialise les valeurs
                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeCarriere(false);
                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeManu(false);
                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeEcoleMag(false);
                                    //Recherche si le joueur a la carriere, manufacture
                                    for(int b=0;b<this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite();b++){
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[b]!=null){
                                            switch (this.listePersonnageUtilises.get(j).getJoueur().getCite()[b].getNom()) {
                                                case "Carriere":
                                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeCarriere(true);
                                                    break;
                                                case "Ecole de Magie":
                                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeEcoleMag(true);
                                                    break;
                                                case "Manufacture":
                                                    this.listePersonnageUtilises.get(j).getJoueur().setPossedeManu(true);
                                                    break;
                                                
                                                default:
                                                    break;
                                            } 
                                        }
                                        
                                    }
        
                                    //-----------------------------------------------------------------------//
        
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.musee)){
                                        Boolean choix = false;
                                        System.out.println("Vous avez la merveille musee dans votre cite.\nVous pouvez choisir de placer des cartes de votre main sous votre carte musee.\nChaque carte deposee sous le musee vous apportera un point en fin de partie.\nChoisissez-vous de placer une carte sous votre musee ? (o/n)\n");
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                            choix = Interaction.lireOuiOuNon();
                                        }else{
                                            choix = generateur.nextBoolean();
                                        }
        
                                        if(choix && !(this.listePersonnageUtilises.get(j).getJoueur().getMain().size() == 0)){
        
                                            Quartier quartierSousMusee = new Quartier();
        
                                            int choix2 = 0;
        
                                            for(int l = 0; l < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); l++){
                                                System.out.println((l+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(l).getNom() + " ( " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(l).getCoutConstruction() + " piece(s) )");
                                            }
        
                                            System.out.println("Quel quartier choisissez-vous ?");
        
                                            if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                choix2 = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                                choix2--;
                                            }else{
                                                choix2 = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                            }
        
                                            quartierSousMusee = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
        
                                            this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix2);
        
                                            quartiersSousMusee.add(quartierSousMusee);
                                        }
        
                                    }
        
                                
        
                                    //-----------------------------------------------------------------------//
        
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.poudriere) && this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() > 1){
                                        Boolean detruirePoudriere;
                                        System.out.println("Vous avez la merveille Poudriere dans votre cite.\nVous pouvez choisir de dÃ©truire cette merveille afin de detruire un autre quartier dans votre cite.\nVoulez-vous detruire la merveille Poudriere afin de detruire un autre quartier dans votre cite ? (o/n)\n");
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                            detruirePoudriere = Interaction.lireOuiOuNon();
                                        }else{
                                            detruirePoudriere = generateur.nextBoolean();
                                        }
        
                                        this.listePersonnageUtilises.get(j).getJoueur().retirerQuartierDansCite("Poudriere");
        
                                        System.out.println("Poudriere correctement detruite");
        
                                        if(detruirePoudriere && this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() > 0){
                                            for(int z = 0; z < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); z++){
                                                System.out.println((z+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getCite()[z].getNom() + " (" + this.listePersonnageUtilises.get(j).getJoueur().getCite()[z].getType() + ") ");
                                            }
        
                                            System.out.println("Quel quartier choisissez-vous de detruire ?");
        
                                            int quartierADetruire = 0;
        
                                            if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                quartierADetruire = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite());
                                                quartierADetruire -= 1;
                                            }else{
                                                quartierADetruire = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite());
                                            }
        
                                            this.listePersonnageUtilises.get(j).getJoueur().retirerQuartierDansCite(this.listePersonnageUtilises.get(j).getJoueur().getCite()[quartierADetruire].getNom());
                                            System.out.println("Quartier correctement detruit.");
                                        }
                                    }
        
                                    
        
                                    //-----------------------------------------------------------------------//
        
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.forge) && this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 1){
                                            System.out.println("\nVous possedez la merveille forge, vous pouvez donc choisir d'utiliser 2 pieces d'or pour piocher 3 cartes\n");
                                            System.out.println("Voulez-vous piocher 3 cartes en echange de 2 pieces d'or ? (o/n)\n");
            
                                            boolean choix = false;
            
            
                                            if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                choix  = Interaction.lireOuiOuNon();
                                            }else{
                                                choix  = generateur.nextBoolean();
            
                                            }
            
                                            System.out.println(choix);
                                            if(choix){
                                                System.out.println("Vous avez bien pioche vos 3 cartes.\nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                                
                                                this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(2);
                                                System.out.println("Vous avez bien pioche vos 3 cartes.\nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                                
                                                this.listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
                                                this.listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
                                                this.listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
                                                System.out.println("Vous avez bien pioche vos 3 cartes.\nPour information, votre tresor est de " + listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                                
                                            }
            
                                        }
        
                                    }
                                    
                                    //-----------------------------------------------------------------------//
        
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.laboratoire)){
                                            System.out.println("\nVous possedez la merveille laboratoire, vous pouvez donc défausser une carte de votre main pour recevoir 2 pieces d'or");
                                            if(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansMain()==0){
                                                System.out.println("Vous n'avez pas de carte à défausser");
                                            }else{
                                                System.out.println("Voulez-vous defausser une carte de votre main et recevoir 2 pieces d'or ? (o/n)\n");
                                                boolean choix = false;
            
                                                if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                    choix  = Interaction.lireOuiOuNon();
                                                }else{
                                                    choix  = generateur.nextBoolean();
            
                                                }
            
                                                if(choix){
                                                    this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(2);
                                                    for(int z = 0; z < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); z++){
                                                        System.out.println(z+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getNom() + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(z).getType() + ").");
                                                    }
                                                    System.out.println("\nQuel quartier choisissez-vous de defausser ?");
            
                                                    int choix2 = 1;
            
                                                    if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                        choix2  = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                                        choix2--;
                                                    }else{
                                                        choix2  = generateur.nextInt((this.listePersonnageUtilises.get(j).getJoueur().getMain().size()));
            
                                                    }
            
                                                    Quartier carteDefausse  = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix2);
                                                    System.out.println("Vous avez bien defausse " + carteDefausse.getNom() + ". \nPour information, votre tresor est de " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s)");
                                                    
                                                }
                                            }
                                            
                                        }
            
                                    }
                                    
                                    //-----------------------------------------------------------------------//
                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                        if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.chantier)){
                                            Boolean choix2 = false;
                                            System.out.println("Vous pouvez détruire le chantier au lieu de payer le coût de construction.\n" + "Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" + "Voulez-vous utiliser votre chantier pour construire un quartier ? (o/n) ");
                                            
                                            if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                choix2  = Interaction.lireOuiOuNon();
                                        
                                                System.out.println("Quel quartier voulez-vous construire ? (0 pour ne rien faire)");
                                                int indxQuart=-1;
                                                Quartier quartConstr;
                                                if(listePersonnageUtilises.get(j).getJoueur().getMain().size()!=0){
                                                    if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                        indxQuart = Interaction.lireUnEntier(0, listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansMain())-1;
                                                    }else{
                                                        choix2  = generateur.nextBoolean();
                            
                                                    }
                        
                                                    if(choix2 && !(listePersonnageUtilises.get(j).getJoueur().getMain().size() == 0)){
                                                        System.out.println("Veuillez choisir un quartier à construire");
                                                        int choix3 = 0;
                        
                                                        if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                            choix3  = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size()) - 1;
                                                        }else{
                                                            choix3  = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
                                                        }
                                                        quartConstr = listePersonnageUtilises.get(j).getJoueur().getMain().get(choix3);
                                                        while(quartConstr.getNom().equals("Monument")&&listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()>5 && indxQuart!=-1){
                                                            System.out.println("Vous avez + de 5 quartier dans votre cité, vous ne pouvez pas contruire le Monument.\nChoisissez un autre quartier(entrer 0 pour ne rien faire):");
                                                            if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                                indxQuart = Interaction.lireUnEntier(0, listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansMain())-1;
                                                            }else{
                                                                indxQuart = generateur.nextInt(listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansMain()+1)-1 ; //indx entre 0 et nbQuartarsdansmain -1
                                                            }
                                                            if(indxQuart!=-1){
                                                            quartConstr = listePersonnageUtilises.get(j).getJoueur().getMain().get(indxQuart);

                                                            }
                                                        }
                        
                                                        Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix3);
                                                        this.listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansCite(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix3));
                                                        this.listePersonnageUtilises.get(j).getJoueur().retirerQuartierDansCite("Chantier");

                                                        if(((this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 0) && (!(this.listePersonnageUtilises.get(j).getNom().equals("Bailli")) && nbJoueurs>3)) || ((this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2)) && bailliPresent){
                                                            this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(1);
                                                            orDesTaxes++;
                                                    
                                                        } 
                                                        
                                                        System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");
                        
                                                    }
                        
                                                }
            
                                            }
                                        } 
                                    
                                    }
                                    
                                }


                                
                            }
                        }
                        
                        System.out.println("Voulez-vous utiliser votre pouvoir ? (o/n)");
                        boolean choix = false;
                        if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                            choix  = Interaction.lireOuiOuNon();
                        }else{
                            choix  = generateur.nextBoolean();

                        }

                        if(choix){
                            if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                listePersonnageUtilises.get(j).utiliserPouvoir();
                            }else{
                                listePersonnageUtilises.get(j).utiliserPouvoirAvatar();
        
                            }    
                        }
                        if(listePersonnageUtilises.get(j).getNom().equals("Sorcière")&&choix){

                        }

                        //Construction quartier

                        Personnage persoQuiJoue = listePersonnageUtilises.get(j);
                        int argentUtiliseConstr = 0;
                        if(persoQuiJoue.getNom().equals("Navigatrice")){
                            System.out.println("La navigatrice ne peut pas construire pendant son tour.");
                        }
                        else{
                            System.out.println("Voulez-vous construire un quartier (plusieurs si architecte, archiviste, voyante ou negociant, ou que vous construisez Ecurie pendant ce tour) ?\nPour information vous avez "+listePersonnageUtilises.get(j).getJoueur().nbPieces()+" pièces.");
                            boolean choixQuart=false;
                            boolean human = listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur");
                            
                            
                            if(human){
                                choixQuart = Interaction.lireOuiOuNon();
                            } else{
                                choixQuart = generateur.nextBoolean();
                            }

                            if(choixQuart){
                                if(human){
                                    if(persoQuiJoue.getJoueur().getMain().size()!=0){
                                        System.out.println("Voici votre main: ");
                                        for(int l=0; l<persoQuiJoue.getJoueur().nbQuartiersDansMain(); l++){
                                            Quartier quartier = persoQuiJoue.getJoueur().getMain().get(l);
                                            System.out.println((l+1));
                                            System.out.println("Nom du quartier: "+quartier.getNom());
                                            System.out.println("Type du quartier: "+quartier.getType());
                                            if(persoQuiJoue.getJoueur().getPossedeManu()&& quartier.getType().equals("MERVEILLE")){
                                                System.out.println("Coût de construction: "+(quartier.getCoutConstruction()-1));//le cout est réduit de 1 pour les merveilles si le joueur possede Manufacture
                                            }else{
                                                System.out.println("Coût de construction: "+quartier.getCoutConstruction());
                                            }
                                            
                                            if(quartier.getType().equals("MERVEILLE")){
                                                System.out.println("Caracteristique: "+quartier.getCaracteristiques());
                                            }
                                        }
                                    }else{
                                        System.out.println("Vous n'avez pas de quartier dans la main");
                                    }
                                    
                                }else{
                                    System.out.println("C'est un bot, tu ne verras pas sa main.");
                                }
                                
                                System.out.println("Quel quartier voulez-vous construire ? (0 pour ne rien faire)");
                                int indxQuart;
                                Quartier quartConstr;
                                if(persoQuiJoue.getJoueur().getMain().size()!=0){
                                    if(human){
                                        indxQuart = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                    }else{
                                        indxQuart = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain()+1)-1 ; //indx entre 0 et nbQuartarsdansmain -1
                                    }

                                    
                                    if(indxQuart==-1){
                                        System.out.println("Vous ne construisez pas");
                                    }else{
                                        Boolean construitNecro = false;
                                        quartConstr = persoQuiJoue.getJoueur().getMain().get(indxQuart);
                                        boolean quartierPresent=false;
                                        int coutQuart = quartConstr.getCoutConstruction();
                                        if(persoQuiJoue.getJoueur().getPossedeManu()&&quartConstr.getType().equals("MERVEILLE")){
                                            coutQuart-=1;
                                        }
                                        if(!persoQuiJoue.getJoueur().getPossedeCarriere()){ //Verifie si le quartier est déjà présent sauf si le joueur a la merveille Carriere
                                            for(int q=0;q<persoQuiJoue.getJoueur().nbQuartiersDansCite();q++){
                                                if(persoQuiJoue.getJoueur().getCite()[q]!=null){
                                                    if(quartConstr.getNom().equals(persoQuiJoue.getJoueur().getCite()[q].getNom())){
                                                        quartierPresent = true;
                                                    }
                                                }
                                                
                                            } 
                                        }
                                        if(quartConstr.equals(Configuration.necropole)){
                                            Boolean construireNecropole = false;
    
                                            System.out.println("Vous avez choisi la merveille necropole.\nVous pouvez choisir de detruire un quartier dans votre cite afin de construire necropole gratuitement.\nVoulez-vous detruire un quartier pour construire necropole ?");
    
                                            if(human){
                                                construireNecropole = Interaction.lireOuiOuNon();
                                            }else{
                                                construireNecropole = generateur.nextBoolean();
                                            }
    
                                            if(construireNecropole){
                                                int quartierADetruire = 0;
                                                
                                                for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                                                    System.out.println((k+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].getNom());
                                                }
    
                                                System.out.println("\nQuel quartier choisissez vous ?");
                                                if(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()>0){
                                                    if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                                                        quartierADetruire = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()) -1;
                                                    }else{
                                                        quartierADetruire = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite());
                                                    }
        
                                                    
                                                    this.listePersonnageUtilises.get(j).getJoueur().retirerQuartierDansCite(this.listePersonnageUtilises.get(j).getJoueur().getCite()[quartierADetruire].getNom());
                                                    if(this.listePersonnageUtilises.get(j).getVraiEchevin()){
                                                        for(int l=0; l<listePersonnageUtilises.size(); l++){
                                                            if(listePersonnageUtilises.get(l).getNom().equals("Echevin")){
                                                                boolean quartierPresentEchevin = false;
                                                                for(int q=0; q<listePersonnageUtilises.get(l).getJoueur().nbQuartiersDansCite(); q++){
                                                                    if(listePersonnageUtilises.get(l).getJoueur().getCite()[q].equals(quartConstr)){
                                                                        quartierPresentEchevin = true;
                                                                    }
                                                                }
                                                                if(!quartierPresentEchevin){
                                                                    System.out.println("L'echevin vole votre quartier pour le mettre dans sa cite");
                                                                    listePersonnageUtilises.get(l).getJoueur().ajouterQuartierDansCite(quartConstr);
                                                                    if(quartConstr.getNom().equals("Ecuries")){
                                                                        construitEcurie=true;
                                                                    }
                                                                    if(this.listePersonnageUtilises.get(l).getJoueur().nbPieces() > 0 && bailliPresent){
                                                                        this.listePersonnageUtilises.get(l).getJoueur().retirerPieces(1);
                                                                        orDesTaxes++;
                                                                
                                                                    }                                                           
                                                                }
                                                            }
                                                        }
                                                        this.listePersonnageUtilises.get(j).setVraiEchevin(false); 
                                                    }else{
                                                        this.listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansCite(quartConstr);
                                                        if(((this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > 0) && (!(this.listePersonnageUtilises.get(j).getNom().equals("Bailli")) && nbJoueurs>3)) || ((persoQuiJoue.getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2)) && bailliPresent){
                                                            this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(1);
                                                            orDesTaxes++;
                                                    
                                                        } 
                                                        System.out.println("Construction de " + quartConstr.getNom() + ".\n");
                                                    }
                                                    construitNecro = true;
                                                    
                                                }
                                                
    
    
    
                                            }
    
    
                                           
                                        }
                                        if(quartConstr.getNom().equals("Tripot")){

                                            int carteTripot = 0;
                                            if(persoQuiJoue.getJoueur().nbPieces()+(persoQuiJoue.getJoueur().nbQuartiersDansMain()-1)>=coutQuart &&persoQuiJoue.getJoueur().nbQuartiersDansMain()>1){
                                                System.out.println("Vous pouvez payer la construction du Tripot en partie ou entierement avec des cartes. Choisissez le nombre de cartes que vous voulez utiliser pour payer (le reste sera des pièces). [0;"+persoQuiJoue.getJoueur().nbQuartiersDansMain()+"] (entre 0 et le nombre de quartiers dans votre main)");
                                                if(human){
                                                    
                                                    carteTripot = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain()-1);
                                                    
                                                    if(carteTripot<persoQuiJoue.getJoueur().nbQuartiersDansMain()-1){

                                                    }
                                                    coutQuart-=carteTripot;
                                                    if(carteTripot!=0){
                                                        for(int l=0;l<carteTripot; l++){
                                                            System.out.println("Voici votre main:");
                                                            for(int q=0; q<persoQuiJoue.getJoueur().nbQuartiersDansMain(); q++){//suppression des cartes de la main
                                                                Quartier quartier = persoQuiJoue.getJoueur().getMain().get(q);
                                                                System.out.println((q+1));
                                                                System.out.println("Nom du quartier: "+quartier.getNom());
                                                                System.out.println("Type du quartier: "+quartier.getType());
                                                                System.out.println("Coût de construction: "+quartier.getCoutConstruction());
                                                                if(quartier.getType().equals("MERVEILLE")){
                                                                    System.out.println("Caracteristique: "+quartier.getCaracteristiques());
                                                                } 
                                                            }
                                                            System.out.println("Entrer les quartiers a utiliser pour payer: ");
                                                            boolean choisiTripot=false;
                                                            do {
                                                                int indxQuartTripot = Interaction.lireUnEntier(1, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                                                Quartier qTripot = persoQuiJoue.getJoueur().getMain().get(indxQuartTripot);
                                                                if(qTripot.equals(Configuration.tripot)){
                                                                    System.out.println("Vous ne pouvez pas payer le tripot avec le tripot.");
                                                                    choisiTripot = true;
                                                                }else{
                                                                    persoQuiJoue.getJoueur().getMain().remove(qTripot);
                                                                    this.plateauDeJeu.getPioche().ajouter(qTripot);
                                                                    choisiTripot = false;
                                                                }
                                                                
                                                            } while (choisiTripot);
                                                            
                                                            
                                                        }
                                                    }
                                                }else{
                                                    carteTripot = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain());
                                                    coutQuart-=carteTripot;
                                                    if(carteTripot!=0){
                                                        for(int l=0;l<carteTripot; l++){
                                                            
                                                            System.out.println("Entrer les quartiers a utiliser pour payer: ");
                                                            boolean choisiTripot=false;
                                                            do {
                                                                int indxQuartTripot = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain());
                                                                Quartier qTripot = persoQuiJoue.getJoueur().getMain().get(indxQuartTripot);
                                                                if(qTripot.equals(Configuration.tripot)){
                                                                    System.out.println("Vous ne pouvez pas payer le tripot avec le tripot.");
                                                                    choisiTripot = true;
                                                                }else{
                                                                    persoQuiJoue.getJoueur().getMain().remove(qTripot);
                                                                    this.plateauDeJeu.getPioche().ajouter(qTripot);
                                                                    choisiTripot = false;
                                                                }
                                                                
                                                            } while (choisiTripot);
                                                            
                                                            
                                                        }
                                                    }
                                                }
    
                                            }else{
                                                System.out.println("Pas assez de carte, vous paierez qu'avec des pièces.");
                                            }    
                                        }

                                    
                                        while(quartConstr.getNom().equals("Monument")&&persoQuiJoue.getJoueur().nbQuartiersDansCite()>5&&indxQuart!=-1){
                                            System.out.println("Vous avez + de 5 quartier dans votre cité, vous ne pouvez pas contruire le Monument.\nChoisissez un autre quartier (0 pour ne rien faire):");
                                            if(human){
                                                indxQuart = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                            }else{
                                                indxQuart = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain()+1)-1 ; //indx entre 0 et nbQuartarsdansmain -1
                                            }
                                            if(indxQuart!=-1){
                                            quartConstr = persoQuiJoue.getJoueur().getMain().get(indxQuart);

                                            }
                                        }

                                        if(persoQuiJoue.getNom().equals("Cardinal") && !construitNecro){
                                            System.out.println("Vous avez le personnage Cardinal.\n");
                                            if(coutQuart>persoQuiJoue.getJoueur().nbPieces()){
                                                int ecart = persoQuiJoue.getJoueur().getMain().get(indxQuart).getCoutConstruction() - persoQuiJoue.getJoueur().nbPieces();
                                                boolean assezDePieces = false;
                                                for(int e = 0; e < listeJoueur.size(); e++){
                                                    if(listeJoueur.get(e).nbPieces() >= ecart){
                                                        assezDePieces = true;
                                                    }
                                                }
                                                
                                                if(assezDePieces){
                                                    int joueurAVoler = 0;
                                                    for(int e = 0; e < listeJoueur.size(); e++){
                                                        System.out.println((e+1) + ". " + listeJoueur.get(e).getNom() + " (" + listeJoueur.get(joueurAVoler).nbPieces() + " piece(s))");
                                                    }
                                                    


                                                    do{
                                                        System.out.println("Veuillez choisir un joueur à voler : (0 pour ne rien faire) ");

                                                        if(human){
                                                            joueurAVoler = Interaction.lireUnEntier(0, listeJoueur.size());
                                                            joueurAVoler --;
                                                        }else{
                                                            joueurAVoler = generateur.nextInt(listeJoueur.size()+1) -1;
                                                        }

                                                        if(joueurAVoler != -1){
                                                            if(listeJoueur.get(joueurAVoler).nbPieces() < ecart){
                                                                assezDePieces = false; 
                                                            }else{
                                                                assezDePieces = true;
                                                            }
                                                        }
                                                        

                                                    }while(!assezDePieces && joueurAVoler != -1);
                                                    
                                                    if(joueurAVoler != -1 && assezDePieces){
                                                        listeJoueur.get(joueurAVoler).retirerPieces(ecart);
                                                        persoQuiJoue.getJoueur().ajouterPieces(ecart);
                                                        System.out.println("Vous avez volé " + ecart + " piece(s) à " + listeJoueur.get(joueurAVoler).getNom());
                                                    }


                                                }else{

                                                    System.out.println("Aucun joueur n'a assez de pieces.\n");
                                                }
                                            }
                                            


                                        }

                                        while ((coutQuart>persoQuiJoue.getJoueur().nbPieces()||quartierPresent)&& !construitNecro){
                                            if(coutQuart>persoQuiJoue.getJoueur().nbPieces()){
                                                System.out.println("Le coût est trop élevé, choisissez un autre quartier (0 pour ne rien faire)");
                                            }

                                            if(quartierPresent){
                                                System.out.println("Ce quartier est déjà présent dans la cité, vous ne pouvez pas construire plusieurs fois le même, choisissez un autre quartier (0 pour ne rien faire)");
                                            }

                                            if(human){
                                                indxQuart = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                            }else{
                                                indxQuart = 0+generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain()+1-0) -1;
                                            }
                                            if(indxQuart==-1){
                                                break;
                                            }else{
                                                quartConstr = persoQuiJoue.getJoueur().getMain().get(indxQuart); 
                                                
                                            }
                                            quartierPresent = false;

                                            if(!persoQuiJoue.getJoueur().getPossedeCarriere()){ //Verifie si le quartier est déjà présent sauf si le joueur a la merveille Carriere
                                                for(int q=0;q<persoQuiJoue.getJoueur().nbQuartiersDansCite();q++){
                                                    if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[q]!=null){
                                                        if(quartConstr.getNom().equals(persoQuiJoue.getJoueur().getCite()[q].getNom())){
                                                            quartierPresent = true;
                                                        }
                                                    }
                                                    
                                                } 
                                            }
                                            coutQuart = quartConstr.getCoutConstruction();
                                            if(persoQuiJoue.getJoueur().getPossedeManu()&&quartConstr.getType().equals("MERVEILLE")){
                                                coutQuart-=1;
                                            }
                                        }

                                        
                                        
                                        if(indxQuart!=-1 && !quartConstr.getNom().equals("Tripot") && !construitNecro){ //si le joueur n'a pas entré 0 et que le quartier est bon, le cout pas trop cher
                                            System.out.println("Vous avez choisi "+quartConstr.getNom());
                                            if(quartConstr.getNom().equals("Ecuries")){
                                                construitEcurie = true;
                                            }
                                            boolean quartierPresentEchevin = false;

                                            if(this.listePersonnageUtilises.get(j).getVraiEchevin()){
                                                for(int l=0; l<listePersonnageUtilises.size(); l++){
                                                    if(listePersonnageUtilises.get(l).getNom().equals("Echevin")){
                                                        for(int q=0; q<listePersonnageUtilises.get(l).getJoueur().nbQuartiersDansCite(); q++){
                                                            if(listePersonnageUtilises.get(l).getJoueur().getCite()[q].equals(quartConstr)){
                                                                quartierPresentEchevin = true;
                                                            }
                                                        }
                                                        if(!quartierPresentEchevin){
                                                            System.out.println("L'echevin vole votre quartier pour le mettre dans sa cite");
                                                            listePersonnageUtilises.get(l).getJoueur().ajouterQuartierDansCite(quartConstr);
                                                            if(quartConstr.getNom().equals("Ecuries")){
                                                                construitEcurie=true;
                                                            }
                                                            if(this.listePersonnageUtilises.get(l).getJoueur().nbPieces() > 0 && bailliPresent){
                                                                this.listePersonnageUtilises.get(l).getJoueur().retirerPieces(1);
                                                                orDesTaxes++;
                                                        
                                                            }  
                                                        }else{
                                                            persoQuiJoue.getJoueur().ajouterQuartierDansCite(quartConstr); //ajouter du quartier dans la cité
                                                            persoQuiJoue.getJoueur().getMain().remove(indxQuart); //quartier retiré de la main
                                                            persoQuiJoue.getJoueur().retirerPieces(coutQuart); //paiement de la construction du quartier
                                                            if(((persoQuiJoue.getJoueur().nbPieces() > 0) && (!(persoQuiJoue.getNom().equals("Bailli")) && nbJoueurs>3)) || ((persoQuiJoue.getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2)) && bailliPresent){
                                                                persoQuiJoue.getJoueur().retirerPieces(1);
                                                                orDesTaxes++;
                                                        
                                                            }   
                                                        }
                                                    }
                                                }
                                                this.listePersonnageUtilises.get(j).setVraiEchevin(false); 
                                            }else{
                                                persoQuiJoue.getJoueur().ajouterQuartierDansCite(quartConstr); //ajouter du quartier dans la cité
                                                persoQuiJoue.getJoueur().getMain().remove(indxQuart); //quartier retiré de la main
                                                persoQuiJoue.getJoueur().retirerPieces(coutQuart); //paiement de la construction du quartier
                                            
                                                if(((persoQuiJoue.getJoueur().nbPieces() > 0) && (!(persoQuiJoue.getNom().equals("Bailli")) && nbJoueurs>3)) || ((persoQuiJoue.getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2))){
                                                    persoQuiJoue.getJoueur().retirerPieces(1);
                                                    orDesTaxes++;
                                                        
                                                }  

                                                argentUtiliseConstr += coutQuart;
                                                if(!quartConstr.getType().equals("COMMERCANT")) {
                                            	    quartierNonCommercantDejaConstruit = true;
                                                }
                                            }
                                            
                                            
                                            
                                        } else{
                                            System.out.println("Vous avez choisi de ne rien faire OU vous avez déjà construit la nécropole ce tour-ci");
                                        }


                                        if(persoQuiJoue.getNom().equals("Architecte")||persoQuiJoue.getNom().equals("Archiviste")||persoQuiJoue.getNom().equals("Voyante")||construitEcurie){ // Si architecte, peut construire 3 quartier en 1 tour, archiviste 2

                                            for(int b=0;b<this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite();b++){
                                                if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[b]!=null){
                                                    switch (this.listePersonnageUtilises.get(j).getJoueur().getCite()[b].getNom()) {
                                                        case "Carrière":
                                                            this.listePersonnageUtilises.get(j).getJoueur().setPossedeCarriere(true);
                                                            break;
                                                        case "Ecole de Magie":
                                                            this.listePersonnageUtilises.get(j).getJoueur().setPossedeEcoleMag(true);
                                                            break;
                                                        case "Manufacture":
                                                            this.listePersonnageUtilises.get(j).getJoueur().setPossedeManu(true);
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }
                                                
                                            }
                                            

                                            for(int m=0; m<2; m++){ //2 nouveau quartier en + max
                                                System.out.println("Voulez-vous construire un nouveau quartier ?\nPour information votre trésor est de "+persoQuiJoue.getJoueur().nbPieces()+" pièces.");
                                                boolean reponseArchH=false;
                                                int reponseArchA=1;
                                                if(human){
                                                    reponseArchH = Interaction.lireOuiOuNon();
                                                }else{
                                                    reponseArchA = 0+generateur.nextInt(2-0); //0 = oui et 1 = non 
                                                }
                                                if(reponseArchH || reponseArchA==0){
                                                    if(human){
                                                        System.out.println("Voici votre main: ");
                                                        for(int l=0; l<persoQuiJoue.getJoueur().nbQuartiersDansMain(); l++){
                                                            Quartier quartier = persoQuiJoue.getJoueur().getMain().get(l);
                                                            System.out.println((l+1));
                                                            System.out.println("Nom du quartier: "+quartier.getNom());
                                                            System.out.println("Type du quartier: "+quartier.getType());
                                                            System.out.println("Coût de construction: "+quartier.getCoutConstruction());
                                                            if(quartier.getType().equals("MERVEILLE")){
                                                                System.out.println("Caracteristique: "+quartier.getCaracteristiques());
                                                            }
                                                        }
                                                    }else{
                                                        System.out.println("L'architecte / archiviste / voyante souhaite construire un nouveau quartier");
                                                    }
                                                    if(persoQuiJoue.getJoueur().nbQuartiersDansMain()==0){
                                                        System.out.println("Vous n'avez pas de quartier à construire");
                                                    }else{
                                                        System.out.println("Quel quartier voulez-vous construire ?");
                                                        int indxQuartArch;
                                                        Quartier quartConstrArch;
                                                        if(human){
                                                            indxQuartArch = Interaction.lireUnEntier(1, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                                        }else{
                                                            indxQuartArch = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain());
                                                        }
                                                        quartConstrArch = persoQuiJoue.getJoueur().getMain().get(indxQuartArch); 
                                                        int coutQuartArch = quartConstrArch.getCoutConstruction();
                                                        quartierPresent = false;
                                                        if(persoQuiJoue.getJoueur().getPossedeManu()&&quartConstrArch.getType().equals("MERVEILLE")){
                                                            coutQuart-=1;
                                                        }
                                                        if(!persoQuiJoue.getJoueur().getPossedeCarriere()){ //Verifie si le quartier est déjà présent sauf si le joueur a la merveille Carriere
                                                            for(int q=0;q<persoQuiJoue.getJoueur().nbQuartiersDansCite();q++){
                                                                if(persoQuiJoue.getJoueur().getCite()[q]!=null){
                                                                    if(quartConstrArch.getNom().equals(persoQuiJoue.getJoueur().getCite()[q].getNom())){
                                                                        quartierPresent = true;
                                                                        break;
                                                                    }
                                                                }
                                                                
                                                            } 
                                                        }
                                                        
                                                        while (coutQuartArch>persoQuiJoue.getJoueur().nbPieces()||quartierPresent){
                                                            if(coutQuartArch>persoQuiJoue.getJoueur().nbPieces()){
                                                                System.out.println("Le coût est trop élevé, choisissez un autre quartier (0 pour ne rien faire)");
                                                            }
                                                            if(quartierPresent){
                                                                System.out.println("Ce quartier est déjà présent dans la cité, vous ne pouvez pas construire plusieurs fois le même, choisissez un autre quartier (0 pour ne rien faire)");
                                                            }
                                                            if(human){
                                                                indxQuartArch = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                                            }else{
                                                                indxQuartArch = 0+generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain()+1-0) -1;
                                                            }
                                                            if(indxQuartArch==-1){
                                                                break;
                                                            }else{
                                                                quartConstrArch = persoQuiJoue.getJoueur().getMain().get(indxQuartArch); 
                                                            }
                                                        }
                                                        if(indxQuartArch!=-1){ //si le joueur n'a pas entré 0
                                                            System.out.println("Vous avez choisi "+quartConstrArch.getNom());
                                                            persoQuiJoue.getJoueur().ajouterQuartierDansCite(quartConstrArch); //ajouter du quartier dans la cité
                                                            persoQuiJoue.getJoueur().getMain().remove(indxQuartArch); //quartier retiré de la main
                                                            persoQuiJoue.getJoueur().retirerPieces(quartConstrArch.getCoutConstruction()); //paiement de la construction du quartier
                                                            
                                                            if(((persoQuiJoue.getJoueur().nbPieces() > 0) && (!(persoQuiJoue.getNom().equals("Bailli")) && nbJoueurs>3)) || ((persoQuiJoue.getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2)) && bailliPresent){
                                                                persoQuiJoue.getJoueur().retirerPieces(1);
                                                                orDesTaxes++;
                                                            }

                                                            argentUtiliseConstr += coutQuart;
                                                        } else{
                                                            System.out.println("Vous avez choisi de ne rien faire");
                                                        }
                                                        if(persoQuiJoue.getNom().equals("Archiviste")||persoQuiJoue.getNom().equals("Voyante")||construitEcurie){
                                                            m++;
                                                        }
                                                    }
                                                    
                                                }else{
                                                    break;
                                                }
                                            }
                                        }else if(persoQuiJoue.getNom().equals("Negociant")) {
                                        	System.out.println("Le negociant peut construire 1 quartier non-commercant et autant de quartiers commercants qu'il veut");
                                        	for(int b=0;b<this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite();b++){
                                                switch (this.listePersonnageUtilises.get(j).getJoueur().getCite()[b].getNom()) {
                                                    case "Carriere":
                                                        this.listePersonnageUtilises.get(j).getJoueur().setPossedeCarriere(true);
                                                        break;
                                                    case "Ecole de Magie":
                                                        this.listePersonnageUtilises.get(j).getJoueur().setPossedeEcoleMag(true);
                                                        break;
                                                    case "Manufacture":
                                                        this.listePersonnageUtilises.get(j).getJoueur().setPossedeManu(true);
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        	Boolean continuNego = true;
                                        	do {
                                        		System.out.println("Voulez-vous construire un nouveau quartier ?\nPour information votre trésor est de "+persoQuiJoue.getJoueur().nbPieces()+" pièces.");
                                                boolean reponseH=false;
                                                Boolean reponseA= false;
                                                
                                                if(human){
                                                    reponseH = Interaction.lireOuiOuNon();
                                                }else{
                                                    reponseA = generateur.nextBoolean();
                                                }
                                                if(reponseH || reponseA){
                                                    if(human){
                                                        System.out.println("Voici votre main: ");
                                                        for(int l=0; l<persoQuiJoue.getJoueur().nbQuartiersDansMain(); l++){
                                                            Quartier quartier = persoQuiJoue.getJoueur().getMain().get(l);
                                                            System.out.println((l+1));
                                                            System.out.println("Nom du quartier: "+quartier.getNom());
                                                            System.out.println("Type du quartier: "+quartier.getType());
                                                            System.out.println("Coût de construction: "+quartier.getCoutConstruction());
                                                            if(quartier.getType().equals("MERVEILLE")){
                                                                System.out.println("Caracteristique: "+quartier.getCaracteristiques());
                                                            }
                                                        }
                                                    }else{
                                                        System.out.println("Le negociant souhaite construire un nouveau quartier");
                                                    }
                                                    if(persoQuiJoue.getJoueur().nbQuartiersDansMain()>0){
                                                        System.out.println("Quel quartier voulez-vous construire ?");
                                                        int indxQuartNego;
                                                        Quartier quartConstrNego;
                                                        if(human){
                                                            indxQuartNego = Interaction.lireUnEntier(1, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                                        }else{
                                                            indxQuartNego = generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain());
                                                        }
                                                        if(!persoQuiJoue.getJoueur().getMain().get(indxQuartNego).getType().equals("COMMERCANT") && quartierNonCommercantDejaConstruit) {
                                                            System.out.println("Vous avez deja construit un quartier non-commercant dans ce tour");
                                                        }else {
                                                            quartConstrNego = persoQuiJoue.getJoueur().getMain().get(indxQuartNego); 
                                                            int coutQuartArch = quartConstrNego.getCoutConstruction();
                                                            quartierPresent = false;
                                                            if(persoQuiJoue.getJoueur().getPossedeManu() && quartConstrNego.getType().equals("MERVEILLE")){
                                                                coutQuart-=1;
                                                            }
                                                            if(!persoQuiJoue.getJoueur().getPossedeCarriere()){ //Verifie si le quartier est déjà présent sauf si le joueur a la merveille Carriere
                                                                for(int q=0;q<persoQuiJoue.getJoueur().nbQuartiersDansCite();q++){
                                                                    if(quartConstrNego.getNom().equals(persoQuiJoue.getJoueur().getCite()[q].getNom())){
                                                                        quartierPresent = true;
                                                                        break;
                                                                    }
                                                                } 
                                                            }
                                                            
                                                            while (coutQuartArch>persoQuiJoue.getJoueur().nbPieces()||quartierPresent){
                                                                if(coutQuartArch>persoQuiJoue.getJoueur().nbPieces()){
                                                                    System.out.println("Le coût est trop élevé, choisissez un autre quartier (0 pour ne rien faire)");
                                                                }
                                                                if(quartierPresent){
                                                                    System.out.println("Ce quartier est déjà présent dans la cité, vous ne pouvez pas construire plusieurs fois le même, choisissez un autre quartier (0 pour ne rien faire)");
                                                                }
                                                                if(human){
                                                                    indxQuartNego = Interaction.lireUnEntier(0, persoQuiJoue.getJoueur().nbQuartiersDansMain())-1;
                                                                }else{
                                                                    indxQuartNego = 0+generateur.nextInt(persoQuiJoue.getJoueur().nbQuartiersDansMain()+1-0) -1;
                                                                }
                                                                if(indxQuartNego==-1){
                                                                    break;
                                                                }else{
                                                                    quartConstrNego = persoQuiJoue.getJoueur().getMain().get(indxQuartNego); 
                                                                }
                                                            }
                                                            if(indxQuartNego!=-1){ //si le joueur n'a pas entré 0
                                                                System.out.println("Vous avez choisi "+quartConstrNego.getNom());
                                                                persoQuiJoue.getJoueur().ajouterQuartierDansCite(quartConstrNego); //ajouter du quartier dans la cité
                                                                persoQuiJoue.getJoueur().getMain().remove(indxQuartNego); //quartier retiré de la main
                                                                persoQuiJoue.getJoueur().retirerPieces(quartConstrNego.getCoutConstruction()); //paiement de la construction du quartier

                                                                if(((persoQuiJoue.getJoueur().nbPieces() > 0) && (!(persoQuiJoue.getNom().equals("Bailli")) && nbJoueurs>3)) || ((persoQuiJoue.getJoueur().nbPieces() > 0) && (nbJoueurs == 3 || nbJoueurs == 2)) && bailliPresent){
                                                                    persoQuiJoue.getJoueur().retirerPieces(1);
                                                                    orDesTaxes++;
                                                                        
                                                                } 
                                                                
                                                                argentUtiliseConstr += coutQuart;
                                                                if(!quartConstr.getType().equals("COMMERCANT")) {
                                                                    quartierNonCommercantDejaConstruit = true;
                                                                }
                                                                
                                                            } else{
                                                                System.out.println("Vous avez choisi de ne rien faire");
                                                            }
                                                        }
                                                    }else{
                                                        System.out.println("Pas de quartier dans la main");
                                                    }
                                                    

                                                }else{
                                                    continuNego = false; 
                                                }
                                            
                                        	}while(continuNego);
                                            
                                        	
                                        	

                                        }
                                    }
                                    
                                }else{
                                    System.out.println("Vous n'avez pas de quartier dans la main");
                                }
                                

                                
                            }
                        }


                        //implémentation de la merveille Hospice

                        //-----------------------------------------------------------------------//

                        for(int k = 0; k < listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                            if(!(listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()==0)){
                                if(listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                    if(listePersonnageUtilises.get(j).getJoueur().nbPieces() == 0 && listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.hospice)){
                                        System.out.println("Vous possedez la merveille hospice dans votre cite. Vous gagnez une piece.\n");
                                        listePersonnageUtilises.get(j).getJoueur().ajouterPieces(1);
                                    }
                                }
                                
                            }
                            
                        }

                        //-----------------------------------------------------------------------//

                        if(persoQuiJoue.getNom().equals("Alchimiste")){
                            System.out.println("Tout l'or dépensé pour la construction de vos quartiers est remboursé.\nOr avant remboursement:"+persoQuiJoue.getJoueur().nbPieces()+" pièces.");
                            persoQuiJoue.getJoueur().ajouterPieces(argentUtiliseConstr);
                            System.out.println("Or apres remboursement: "+persoQuiJoue.getJoueur().nbPieces()+" pièces.");
                        }
                        

                        

                        //implémentation de la merveille Parc

                        for(int k = 0; k < listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
                            if(!(listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()==0)&&listePersonnageUtilises.get(j).getJoueur().getCite()[k]!=null){
                                if(listePersonnageUtilises.get(j).getJoueur().getMain().size() == 0 && listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.parc)){

                                    System.out.println("Vous possedez la merveille parc dans votre cite. Comme vous n'avez aucune carte dans votre mains vous piochez 2 cartes.\n ");

                                    listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
                                    listePersonnageUtilises.get(j).getJoueur().ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());

                                }
                            }
                        }
                        

                        //-----------------------------------------------------------------------//
                    }else{
                        if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
                            listePersonnageUtilises.get(j).utiliserPouvoir();
                        }else{
                            listePersonnageUtilises.get(j).utiliserPouvoirAvatar();
                        }
                        sorciere = listePersonnageUtilises.get(j);
                        System.out.println("La sorciere continue son tour plus tard.");
                    }
                    
                }

                if((this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() == 8 || this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersMonumentDansCite() == 8) && premierAFinir == null){
                    premierAFinir = this.listePersonnageUtilises.get(j).getJoueur();
                    System.out.println("\n" + premierAFinir.getNom() + " a 8 quartiers en premier.");
                }else if((this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() == 8 || this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersMonumentDansCite() == 8) && !(premierAFinir == null)){
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

        //implémentation personnages de 2 à 8

        int nbCartesEcarteeFaceVisible = 0;
        int nbCartesEcarteeFaceCachee = 0;

        //partie à 7 joueurs
        if(nbPersonnages==8 && nbJoueurs!=2 && nbJoueurs!=3){
            switch(nbJoueurs){
                case 4:
                    nbCartesEcarteeFaceVisible = 2;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 5:
                    nbCartesEcarteeFaceVisible = 1;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 6:
                    nbCartesEcarteeFaceVisible = 0;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 7:
                    nbCartesEcarteeFaceVisible = 0;
                    nbCartesEcarteeFaceCachee = 1;
                    break;  

            }
        }else if(nbPersonnages == 9 && nbJoueurs!=2 && nbJoueurs!=3){
            switch(nbJoueurs){
                case 4:
                    nbCartesEcarteeFaceVisible = 3;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 5:
                    nbCartesEcarteeFaceVisible = 2;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 6:
                    nbCartesEcarteeFaceVisible = 1;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 7:
                    nbCartesEcarteeFaceVisible = 0;
                    nbCartesEcarteeFaceCachee = 1;
                    break;
                case 8:
                    nbCartesEcarteeFaceVisible = 0;
                    nbCartesEcarteeFaceCachee = 1;
                    break;    

            }

        }

        

        if(nbJoueurs!=2 && nbJoueurs!=3){
            for(int i = 0; i < nbCartesEcarteeFaceVisible; i ++){
                int persoARetirer = generateur.nextInt(listePersonnage.size());
                Personnage perso = listePersonnage.get(persoARetirer);
                listePersonnage.remove(persoARetirer);
                System.out.println("Le personnage " + "\"" + perso.getNom() + "\"" + " est ecarte face visible\n" );
            }

            
            for(int i = 0; i < nbCartesEcarteeFaceCachee; i ++){
                int persoARetirer = generateur.nextInt(listePersonnage.size());

                //partie à 7 joueurs avec 8 perso OU partie a 8 joueurs avec 9 personnages

                if((nbJoueurs == 7 && nbPersonnages==8) || (nbJoueurs == 8 && nbPersonnages==9)){
                    this.persoFaceCachee = listePersonnage.get(persoARetirer);
                }

                listePersonnage.remove(persoARetirer);
                System.out.println("Un personnage est ecarte face cachee\n");

            }
        
        }

        
        for(int i = 0; i < listeJoueur.size(); i++){
            if(listeJoueur.get(i).getPossedeCouronne()){
                Joueur possedeCouronne = listeJoueur.get(i);
                listeJoueur.remove(i);
                listeJoueur.add(0, possedeCouronne);
            }
        }

        for(int i = 0; i < listeJoueur.size(); i++){

            if(nbJoueurs!=2 && nbJoueurs!=3){
                if(listeJoueur.get(0).getNom().equals("Joueur") && listeJoueur.get(i).getNom().equals("Joueur")){
                    System.out.println("Vous avez la couronne !\n");

                    for(int j = 0; j < listePersonnage.size(); j++){
                        System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
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
                        System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                    }
                    
                    System.out.println("\nQuel personnage choisissez vous ?");
                    int choix = interaction.lireUnEntier(1, listePersonnage.size()) - 1;

                    System.out.println("Vous avez choisi le personnage " + this.listePersonnage.get(choix).getNom() + "\n");

                    this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(i));
                    this.listePersonnageUtilises.add(listePersonnage.get(choix));
                    listePersonnage.remove(choix);  

                }
            
                    //partie à 7 joueurs et 8 joueurs

                else if(((nbJoueurs == 7 && nbPersonnages==8)||(nbJoueurs == 8 && nbPersonnages == 9)) && i == (listeJoueur.size() - 1)){

                    ArrayList<Personnage> persoAChoisir = new ArrayList<Personnage>();
                    persoAChoisir.add(persoFaceCachee);
                    persoAChoisir.add(listePersonnage.get(0));

                    if(listeJoueur.get(i).getNom().equals("Joueur")){
                        for(int j = 0; j < persoAChoisir.size(); j++){
                            System.out.println((j+1) + "." + persoAChoisir.get(j).getNom());
                        }
                        
                        System.out.println("\nQuel personnage choisissez vous ?");
                        int choix = Interaction.lireUnEntier(1, persoAChoisir.size() - 1);
            
                        System.out.println("Vous avez choisi le personnage " + persoAChoisir.get(choix).getNom() + "\n");
            
                        persoAChoisir.get(choix).setJoueur(this.listeJoueur.get(i));
                        this.listePersonnageUtilises.add(persoAChoisir.get(choix));
                        listePersonnage.remove(persoAChoisir.get(choix));  

                    }else{

                        System.out.println("A " + listeJoueur.get(i).getNom() + " de jouer\n");

                        for(int j = 0; j < persoAChoisir.size(); j++){
                            System.out.println((j+1) + "." + persoAChoisir.get(j).getNom());
                        }
                        
                        
                        System.out.println("\nQuel personnage choisissez vous ?");
                        int choix = generateur.nextInt(persoAChoisir.size() - 1) + 1;
                        System.out.println(choix); 
                        choix -= 1; 

                        System.out.println("Vous avez choisi le personnage " + persoAChoisir.get(choix).getNom() + "\n");
            
                        persoAChoisir.get(choix).setJoueur(this.listeJoueur.get(i));
                        this.listePersonnageUtilises.add(persoAChoisir.get(choix));
                        listePersonnage.remove(persoAChoisir.get(choix));  


                    }

                }
                
                
                else{
                    System.out.println("A " + listeJoueur.get(i).getNom() + " de jouer\n");

                    for(int j = 0; j < listePersonnage.size(); j++){
                        System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                    }
                    
                    System.out.println("\nQuel personnage choisissez vous ?");
                    int choix = generateur.nextInt(listePersonnage.size());
                    System.out.println(choix);  

                    System.out.println("Vous avez choisi le personnage " + this.listePersonnage.get(choix).getNom() + "\n");

                    this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(i));
                    this.listePersonnageUtilises.add(listePersonnage.get(choix));
                    listePersonnage.remove(choix);
                }
            
            }
          
        }

        int joueur;

        if(nbJoueurs==2){
            for(int o=0; o<4; o++){
                if(o==0 || o==2){
                    joueur = 0;
                }else{
                    joueur = 1;
                }
                System.out.println("A " + listeJoueur.get(joueur).getNom() + " de jouer\n");

                for(int j = 0; j < listePersonnage.size(); j++){
                    System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                }
                System.out.println("Choisissez un personnage à écarter :\n");
                int choix=0;
                if(listeJoueur.get(joueur).getNom().equals("Joueur")){
                    choix = interaction.lireUnEntier(1, listePersonnage.size())-1;
                }else{
                    choix = generateur.nextInt(listePersonnage.size());
                }
                listePersonnage.remove(choix);

                for(int j = 0; j < listePersonnage.size(); j++){
                    System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                }

                System.out.println("Choisissez un personnage à prendre :`\n");
                if(listeJoueur.get(joueur).getNom().equals("Joueur")){
                    choix = interaction.lireUnEntier(1, listePersonnage.size()+1)-1;
                }else{
                    choix = generateur.nextInt(listePersonnage.size());
                }
                this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(joueur));
                listePersonnageUtilises.add(listePersonnage.get(choix));
                listePersonnage.remove(choix);
                
            }
        }else if(nbJoueurs==3){

            for(int o=0; o<6; o++){

                if(o==0 || o==3){
                    joueur =0;
                }else if(o==1 || o==4){
                    joueur =1;
                }else{
                    joueur =2;
                }
                for(int j = 0; j < listePersonnage.size(); j++){
                    System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                }

                int choix=0;
                System.out.println("Choisissez un personnage à prendre :`\n");
                if(listeJoueur.get(joueur).getNom().equals("Joueur")){
                    choix = interaction.lireUnEntier(1, listePersonnage.size())-1;
                }else{
                    choix = generateur.nextInt(listePersonnage.size());
                }
                this.listePersonnage.get(choix).setJoueur(this.listeJoueur.get(joueur));
                listePersonnageUtilises.add(listePersonnage.get(choix));
                listePersonnage.remove(choix);
                for(int j = 0; j < listePersonnage.size(); j++){
                    System.out.println("\n"+(j+1) + "." + listePersonnage.get(j).getNom()+" caracteristiques: "+listePersonnage.get(j).getCaracteristiques());
                }

                if(o==0 || o==2){
                    System.out.println("Choisissez un personnage à écarter :\n");
                    if(listeJoueur.get(joueur).getNom().equals("Joueur")){
                        choix = interaction.lireUnEntier(1, listePersonnage.size())-1;
                    }else{
                        choix = generateur.nextInt(listePersonnage.size());
                    }
                    listePersonnage.remove(choix);
                }
                
            }
            listePersonnage.remove(0);
        }

        //implémentation theatre 

        for(int i = 0; i < this.listeJoueur.size(); i++){
            if(this.listeJoueur.get(i).nbQuartiersDansCite()>0){
                for(int j = 0; j < this.listeJoueur.get(i).nbQuartiersDansCite(); j++){
                    if(this.listeJoueur.get(i).getCite()[j]!=null){
                        if(this.listeJoueur.get(i).getCite()[j].equals(Configuration.theatre)){
                            System.out.println("Vous avez la merveille Theatre.\nVous pouvez choisir d'echanger votre personnage avec un autre joueur.\nVoulez-vous echanger ?");
    
                            boolean echangerPerso;
    
                            if(this.listeJoueur.get(i).getNom().equals("Joueur")){
                                echangerPerso = Interaction.lireOuiOuNon();
                            }else{
                                echangerPerso = generateur.nextBoolean();
                            }
    
                            if(echangerPerso){
                                for(int k = 0; k < this.listeJoueur.size(); k++){
                                    System.out.println((k+1) + ". " + this.listeJoueur.get(k).getNom());
                                }
    
                                System.out.println("Avec quel joueur voulez-vous echanger votre personnage ?");
    
                                boolean choisiLuiMeme = false;
                                int echangerAvecJoueur = 0;
    
                                do{
    
                                    choisiLuiMeme = false;
    
                                    if(this.listeJoueur.get(i).getNom().equals("Joueur")){
                                        echangerAvecJoueur = Interaction.lireUnEntier(1, this.listeJoueur.size());
                                        echangerAvecJoueur--;
                                    }else{
                                        echangerAvecJoueur = generateur.nextInt(this.listeJoueur.size());
                                    }
    
                                    if(this.listeJoueur.get(echangerAvecJoueur).equals(this.listeJoueur.get(i))){
                                        choisiLuiMeme = true;
                                        System.out.println("Vous ne pouvez pas vous choisir vous-même");
                                    }
    
                                }while(choisiLuiMeme);
                                Personnage persoEchange1 = this.listeJoueur.get(echangerAvecJoueur).getPersonnage();
                                Personnage persoEchange2 = this.listeJoueur.get(i).getPersonnage();
    
                                persoEchange1.setJoueur(this.listeJoueur.get(i));
                                persoEchange2.setJoueur(this.listeJoueur.get(echangerAvecJoueur));
    
                                System.out.println("Les personnages ont bien ete echanges.");
    
                            }
                        }
                    }
                    
            
                }
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
                    //implémentation de la merveille Mine d'Or 

                    boolean aMineDOr = false;
                    for(int j=0; j <personnage.getJoueur().nbQuartiersDansCite(); j++){
                        if(personnage.getJoueur().getCite()[j].equals(Configuration.mineDOr)){
                            aMineDOr = true;
                        }
                    }

                    if(aMineDOr){
                        personnage.getJoueur().ajouterPieces(3);

                        System.out.println("Vous possedez la merveille Mine d'Or.\n Ajout de 3 pièces");

                    }else{
                        personnage.getJoueur().ajouterPieces(2);

                        System.out.println("Ajout de 2 pièces");
                    }
                    break;
                case 2:  
                    
                    //-----------------------------------------------------------------------//

                    //implémentation merveille Bibliotheque
                    Boolean possedeBibliotheque = false;

                    //implémentation de la merveille Observatoire
                    Boolean aObservatoire = false;
                
                    for(int i = 0; i < personnage.getJoueur().nbQuartiersDansCite(); i++){
                        if(!(personnage.getJoueur().getCite()[i] == null)){
                            if(personnage.getJoueur().getCite()[i].equals(Configuration.bibliotheque)){
                                possedeBibliotheque = true;
                            }

                            if(personnage.getJoueur().getCite()[i].equals(Configuration.observatoire)){
                                aObservatoire = true;
                            }
                        }
                    }

                    //-----------------------------------------------------------------------//


                    ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();

                    if(aObservatoire){

                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());

                    }else{

                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());

                    }
                    
                    for(int i = 0; i < piocheJoueur.size(); i++){
                        System.out.println(i + 1 + ". " + piocheJoueur.get(i).getNom() + " (" + piocheJoueur.get(i).getCoutConstruction() + " pieces)");
                    }

                    if(possedeBibliotheque){
                        System.out.println("\nVous possedez la merveille Bibliotheque, vous gardez donc les deux cartes\n");
                        for(int i = 0; i < piocheJoueur.size(); i++){
                            personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(i));      
                        }
                        break;
                    }else{
                        System.out.println("Veuillez choisir une carte à garder :\n");
                        int choix2 = Interaction.lireUnEntier(1, piocheJoueur.size()) - 1;
                        personnage.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(choix2));
                        piocheJoueur.remove(choix2);
                        this.getPlateau().getPioche().ajouter(piocheJoueur.get(0));

                        if(aObservatoire){
                            this.getPlateau().getPioche().ajouter(piocheJoueur.get(1)); 
                        }
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
                    //-----------------------------------------------------------------------//

                    //implémentation merveille Bibliotheque
                    Boolean possedeBibliotheque = false;

                    //implémentation de la merveille Observatoire
                    Boolean aObservatoire = false;
                
                    for(int i = 0; i < personnage.getJoueur().getMain().size(); i++){
                        if(!(personnage.getJoueur().getCite()[i] == null)){
                            if(personnage.getJoueur().getCite()[i].equals(Configuration.bibliotheque)){
                                possedeBibliotheque = true;
                            }

                            if(personnage.getJoueur().getCite()[i].equals(Configuration.observatoire)){
                                aObservatoire = true;
                            }
                        }
                    }
                    
                    //-----------------------------------------------------------------------//


                    ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();

                    if(aObservatoire){

                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());

                    }else{

                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());
                        piocheJoueur.add(this.plateauDeJeu.getPioche().piocher());

                    }

                    for(int i = 0; i < piocheJoueur.size(); i++){
                        System.out.println(i + 1 + ". " + piocheJoueur.get(i).getNom() + " (" + piocheJoueur.get(i).getCoutConstruction() + " pieces)");
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
                        this.getPlateau().getPioche().ajouter(piocheJoueur.get(0));
                       
                        break;
                    }
            }

        }
       


    }

    private void calculDesPoints(){
        ArrayList<Integer> pointsListe = new ArrayList<Integer>();
        System.out.println("\nListe Joueurs : " + listeJoueur.size() + "\n");

        for(int i = 0; i < listeJoueur.size(); i++){
            Joueur joueur = listeJoueur.get(i);
            int points = 0;
            ArrayList<String> typesQuartiers = new ArrayList<String>();
            
            

            for(int j = 0; j < joueur.nbQuartiersDansCite(); j++){

                if(!(joueur.getCite()[j] == null)){
                    points += joueur.getCite()[j].getCoutConstruction();
                    System.out.println("Ajout points cout quartier +"+joueur.getCite()[j].getCoutConstruction()+" pieces (points : "+points + ")");
                    typesQuartiers.add(joueur.getCite()[j].getType());
                }
             
            }

            for(int j = 0; j < joueur.nbQuartiersDansCite(); j++){
                List<String> typesQuartiersDistinct = typesQuartiers.stream().distinct().collect(Collectors.toList());
                
                if(joueur.getCite()[j]!=null){
                    if(joueur.getCite()[j].equals(Configuration.courDesMiracles)){

                    
                     
                        System.out.println("Vous possedez la merveille Cour des Miracles.\nVous possedez ces types là: ");
                        for(int z = 0; z<typesQuartiersDistinct.size(); z++){
                            System.out.println((z+1)+". "+typesQuartiersDistinct.get(z));
                        }
                        
                        System.out.println("Veuillez choisir le nouveau type de votre merveille :\n");
                        int choix = 1;
                        for(int z = 0; z < Quartier.TYPE_QUARTIERS.length; z++){
                            System.out.println((z+1) + ". " + Quartier.TYPE_QUARTIERS[z]);
                        }
                        if(joueur.getNom().equals("Joueur")){
                            choix  = Interaction.lireUnEntier(1, 5);
                        }else{
                            choix  = generateur.nextInt(5) + 1;
                            System.out.println(choix);
    
                        }
    
                        choix -= 1;
                        joueur.getCite()[j].setType(Quartier.TYPE_QUARTIERS[choix]);
                        System.out.println("Vous avez change le type de votre merveille en : " + Quartier.TYPE_QUARTIERS[choix] );
    
                        for(int p = 0; p < joueur.nbQuartiersDansCite(); p++){
    
                            if(!(joueur.getCite()[p] == null)){
                                typesQuartiers.add(joueur.getCite()[p].getType());
                            }
                         
                        }
    
                    }
                
                    //implémentation Tour d'Ivoire
    
                    if(joueur.getCite()[j].equals(Configuration.tourDIvoire)){
                        if(Collections.frequency(typesQuartiers, "MERVEILLE") == 1){
                            points += 5;
                            System.out.println("Vous avez tour d'ivoire et 1 seule merveille, +5 pts (points: "+points+")");
                        }
                    }
                
                }
                
            }

            if(typesQuartiers.contains("RELIGIEUX") && typesQuartiers.contains("MILITAIRE") && typesQuartiers.contains("NOBLE") && typesQuartiers.contains("COMMERCANT") && typesQuartiers.contains("MERVEILLE")){
                points += 3;
                System.out.println("Vous êtes le premier à finir, vous avez +3pts");
            }

            if(joueur.equals(premierAFinir)){
                points += 4;
                System.out.println("Premier a finir +4pts (points: "+points+")");
            }else if(joueur.getAFini()){
                points += 2;
                System.out.println("Cite complete +2pts (points: "+points+")");
            }

            //implémentation de la merveille dracoport

            //implémentation de la merveille Fontaine aux Souhaits

            //implémentation de la merveille Salle des Cartes

            //implémentation de la merveille Statue Equestre

            //implémentation de la merveille Basilique

            //implémentation de la merveille Capitole

            //implémentation de la merveille Catacombes

            //implémentation de la merveille Musee


            for(int j = 0; j< joueur.nbQuartiersDansCite(); j++){

                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.musee)){
                    points += quartiersSousMusee.size();
                    System.out.println(quartiersSousMusee.size()+" Quartiers sous le musée (points: "+points+")");

                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.dracoport)){
                    points += 2;
                    System.out.println(" Dracoport +2pts (points: "+points+")");
                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.fontaineAuxSouhaits)){
                    for(int k = 0; k < joueur.nbQuartiersDansCite(); k++){
                        if(!(joueur.getCite()[k] == null)){
                            if(joueur.getCite()[k].getType().equals("MERVEILLE")){
                                points += 1;
                                System.out.println(" Fontaine au souhait +1pt par merveille (points: "+points+")");

                            }
                        } 
                    }
                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.salleDesCartes)){
                    points += joueur.nbQuartiersDansMain();
                    System.out.println("Salle des cartes +1pts par cartes dans la main"+" (donc +"+joueur.nbQuartiersDansMain()+ " (points: "+points+")");

                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.statueEquestre) && joueur.getPossedeCouronne()){
                    points += 5;
                    System.out.println("Statue equestre + couronne + 5pts (points: "+points+")");

                }

            //-----------------------------------------------------------------------//

                
                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.tresorImperial)){
                    points += joueur.nbPieces();
                    System.out.println(" Tresor impérial +"+joueur.nbPieces()+" (points: "+points+")");

                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.basilique)){
                    int compteur = 0;
                    for(int k = 0; k < joueur.nbQuartiersDansCite(); k++){
                        if(joueur.getCite()[k]!=null){
                            if(joueur.getCite()[k].getCoutConstruction()%2 == 1){
                                compteur++;
                                System.out.println("Basilique, Impaire, +1 pt");
                            }
                        }
                        
                    }
                    points += compteur;
                    System.out.println("Points: "+points);
                }

            //-----------------------------------------------------------------------//


                if(!(joueur.getCite()[j] == null) && joueur.getCite()[j].equals(Configuration.capitole)){
                    ArrayList<String> typesQuartier = new ArrayList<String>();
                    ArrayList<Integer> nbOccurrence = new ArrayList<Integer>();
                    for(int k = 0; k < joueur.nbQuartiersDansCite(); k++){
                        typesQuartier.add(joueur.getCite()[k].getType());
                    }
                    
                    for(int k = 0; k < typesQuartier.size(); k++){
                        nbOccurrence.add(Collections.frequency(typesQuartier, typesQuartier.get(k)));
                    }

                    if(nbOccurrence.contains(3)||nbOccurrence.contains(4)||nbOccurrence.contains(5)||nbOccurrence.contains(6)||nbOccurrence.contains(7)||nbOccurrence.contains(8)||nbOccurrence.contains(9)){
                        points += 3;
                        System.out.println("Capitole (au moins 3 occurence) +3pts (points: "+points+")");
                    }
                }

                

            //-----------------------------------------------------------------------//


            }
            if(joueur.getMain().contains(Configuration.catacombes)){
                points += 3;
                System.out.println("Catacombes dans main +3pts (points: "+points+")");

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
