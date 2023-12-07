package tests.bonus;
import modele.*;
import tests.part1.Test;

import java.util.ArrayList;

public class TestDiplomate {

    public static void main(String[] args) {
        TestDiplomate test= new TestDiplomate();
        test.test1();
        test.test2();
        test.test3();   
    }
    
    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
        Diplomate diplomate = new Diplomate();
        Test.test(diplomate.getNom().equals("Diplomate"),"test du nom du personnage");
        Test.test(diplomate.getRang()== 8,"test du rang du personnage");
        Test.test(diplomate.getCaracteristiques().equals(Caracteristiques.DIPLOMATE),
                "test des caractéristiques du personnage");
        Test.test(diplomate.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(!diplomate.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
        Test.test(!diplomate.getVole(), "test de l'initialisation de la variable \"vole\"");
    }

    public void test2(){
        System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
        Joueur joueur = new Joueur("Billy");
        Diplomate diplomate = new Diplomate();
        Quartier quartier1 = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3);
        Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
        Quartier quartier3 = new Quartier("église",Quartier.TYPE_QUARTIERS[0],2);
        diplomate.setJoueur(joueur);
        diplomate.ajouterPieces();
        Test.test(diplomate.getJoueur().nbPieces() == 2,
            "test du nombre de pièces d'or avant perception");
        diplomate.construire(quartier1);
        diplomate.construire(quartier2);
        diplomate.construire(quartier3);        
        diplomate.percevoirRessourcesSpecifiques();
        Test.test(diplomate.getJoueur().nbPieces() == 4,
            "test du nombre de pièces d'or après perception de ressources spécifiques avec 2 quartiers militaires.");
    }

    public void test3() {
        System.out.println("TEST DU POUVOIR DE LA DIPLOMATE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        
        // cr�ation de quatre personnages
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Diplomate diplomate = new Diplomate();
        plateau.ajouterPersonnage(diplomate);
            
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
        diplomate.setJoueur(joueur3);
        
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
        //construction de quartiers dans cite
        joueur1.ajouterQuartierDansCite(pioche.piocher());// à modifier
    
        joueur2.ajouterQuartierDansCite(pioche.piocher());
        
        joueur3.ajouterQuartierDansCite(pioche.piocher());
                
        //ajout de pièces aux joueurs pour pouvoir comparer leur richesse ensuite
        joueur1.ajouterPieces(4);
        joueur2.ajouterPieces(2);
        joueur3.ajouterPieces(1);

        //on affiche la cité des joueurs

        System.out.print("Cite de la Diplomate (" + diplomate.getJoueur().getNom() + "): ");
        Quartier[] citeDiplo = diplomate.getJoueur().getCite();
        for (Quartier quartier1 : citeDiplo) {
            if (quartier1 != null)System.out.print(quartier1.getNom() + ", ");
            System.out.println();
        }
            System.out.print("Cite du roi (" + roi.getJoueur().getNom() + "): ");
            Quartier[] citeRoi = roi.getJoueur().getCite();

        for (Quartier quartier : citeRoi) {
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
            System.out.println();
        }
            System.out.print("Cite de la Diplomate (" + assassin.getJoueur().getNom() + "): ");
            Quartier[] citeAssassin = assassin.getJoueur().getCite();

        for (Quartier quartier : citeAssassin) {
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
            System.out.println();
        }
        //voir le nombre de pièces initial des joueurs
        for(int i = 0; i< joueur1.nbPieces(); i++) {
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
        
        // utiliser le pouvoir de la diplomate :        
        diplomate.utiliserPouvoir();
        
        //on affiche à nouveau la cité des joueurs :
        System.out.print("Cite de la Diplomate (" + diplomate.getJoueur().getNom() + "): ");

        for (Quartier quartier : citeDiplo) {
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
            System.out.println();
        }
            System.out.print("Cite du roi (" + roi.getJoueur().getNom() + "): ");

        for (Quartier quartier : citeRoi) {
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
            System.out.println();
        }
            System.out.print("Cite de la Assassin (" + assassin.getJoueur().getNom() + "): ");

        for (Quartier quartier : citeAssassin) {
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
            System.out.println();
        }
        //vérifier que les quartiers ont bien été échangés et qu'il n'y en a pas 2 identiques dans une même cité
        //voir le nombre de pièces final des joueurs

        for(int i = 0; i< joueur1.nbPieces(); i++) {
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
    }

    
}
