package tests.bonus;
import modele.*;
import tests.part1.Test;

import java.util.ArrayList;


public class TestCardinal {

    public static void main(String[] args) {
        TestCardinal test= new TestCardinal();
        test.test1();
        test.test2();
        test.test3();   
    }
    
    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
        Cardinal cardinal = new Cardinal();
        Test.test(cardinal.getNom().equals("Cardinal"),"test du nom du personnage");
        Test.test(cardinal.getRang()== 5,"test du rang du personnage");
        Test.test(cardinal.getCaracteristiques().equals(Caracteristiques.CARDINAL),
                "test des caractéristiques du personnage");
        Test.test(cardinal.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(!cardinal.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
        Test.test(!cardinal.getVole(), "test de l'initialisation de la variable \"vole\"");
    }
    public void test2(){
        System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
        Joueur joueur = new Joueur("Billy");
        Cardinal cardinal = new Cardinal();
        Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
        Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
        Quartier quartier3 = new Quartier("église",Quartier.TYPE_QUARTIERS[0],2);
        cardinal.setJoueur(joueur);
        cardinal.ajouterPieces();
        Test.test(cardinal.getJoueur().nbPieces() == 2,
            "test du nombre de pièces d'or avant perception");
        cardinal.construire(quartier1);
        cardinal.construire(quartier2);
        cardinal.construire(quartier3);     
        cardinal.percevoirRessourcesSpecifiques();
        Test.test(cardinal.getJoueur().nbPieces() == 4,
            "test du nombre de pièces d'or après perception de ressources spécifiques avec 2 quartiers religieux");
    }

    public void test3() {
        System.out.println("TEST DU POUVOIR DU CARDINAL");
        PlateauDeJeu plateau = new PlateauDeJeu();
        
        // cr�ation de quatre personnages
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Cardinal cardinal = new Cardinal();
        plateau.ajouterPersonnage(cardinal);
            
        // cr�ation de trois joueurs
        Joueur joueur1 = new Joueur("Milou");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Belle");
        plateau.ajouterJoueur(joueur3);
            
        // on associe les personnages aux joueurs
        roi.setJoueur(joueur1);
        assassin.setJoueur(joueur2);
        cardinal.setJoueur(joueur3);
        
        // cr�ation d'une pioche:
        Pioche pioche = plateau.getPioche();
        Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); pioche.ajouter(q);
        q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
        q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
        q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
        q = new Quartier("�choppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
        q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
        pioche.ajouter(q);
        q = new Quartier("cath�drale",Quartier.TYPE_QUARTIERS[0],5); pioche.ajouter(q);
        q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); pioche.ajouter(q);
        q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); pioche.ajouter(q);
        q = new Quartier("h�tel de ville",Quartier.TYPE_QUARTIERS[3],15); pioche.ajouter(q);
        q = new Quartier("biblioth�que",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
        pioche.ajouter(q);
        pioche.melanger();

        
        // on distribue les cartes aux joueurs:
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        
        //ajout de pièces aux joueurs pour pouvoir comparer leur richesse ensuite
        joueur1.ajouterPieces(4);
        joueur2.ajouterPieces(2);
        joueur3.ajouterPieces(5);

    
        //voir le nombre de pièces initial des joueurs
        
        System.out.print(joueur1.nbPieces() + ", ");
        System.out.println();
            
        System.out.print(joueur2.nbPieces() + ", ");
        System.out.println();
        
        System.out.print(joueur3.nbPieces() + ", ");
        System.out.println();
        
            // on affiche la main de chaque joueur:
        System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainRoi = roi.getJoueur().getMain();
        for (Quartier quartier : mainRoi) System.out.print(quartier.getNom() + ", ");
        System.out.println();
        System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
        for (Quartier quartier : mainAssassin) System.out.print(quartier.getNom() + ", ");
        System.out.println();
        System.out.print("Main de la cardinal (" + cardinal.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainCardinal = cardinal.getJoueur().getMain();
        for (Quartier quartier : mainCardinal) System.out.print(quartier.getNom() + ", ");
        System.out.println();

        // utiliser le pouvoir de la cardinal :     
        cardinal.utiliserPouvoir();
        
        
        //voir le nombre de pièces final des joueurs
        for(int i = 0; i< joueur1.nbPieces(); i++){
            System.out.print(joueur1.nbPieces() + ", ");
            System.out.println();
        }
        for(int i = 0; i< joueur2.nbPieces(); i++) {
            System.out.print(joueur2.nbPieces() + ", ");
            System.out.println();
        }
         for(int i = 0; i< joueur3.nbPieces(); i++) {
             System.out.print(joueur3.nbPieces() + ", ");
             System.out.println();
         }

            // on affiche la main de chaque joueur:
        System.out.print("Main du Roi (" + roi.getJoueur().getNom() + "): ");

        for (Quartier quartier : mainRoi) System.out.print(quartier.getNom() + ", ");
        System.out.println();
        System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");

        for (Quartier quartier : mainAssassin) System.out.print(quartier.getNom() + ", ");
        System.out.println();
        System.out.print("Main de la cardinal (" + cardinal.getJoueur().getNom() + "): ");

        for (Quartier quartier : mainCardinal) System.out.print(quartier.getNom() + ", ");
        System.out.println();
    }
}
