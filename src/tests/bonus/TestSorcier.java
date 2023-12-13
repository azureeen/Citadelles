package tests.bonus;

import java.util.ArrayList;
import modele.*;
import tests.Test;

public class TestSorcier {

    public static void main(String[] args) {
        TestSorcier test= new TestSorcier();
        //test.test1();
        test.test2();   
    }
    
    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
        Sorcier sorcier = new Sorcier();
        Test.test(sorcier.getNom().equals("Sorcier"),"test du nom du personnage");
        Test.test(sorcier.getRang()== 3,"test du rang du personnage");
        Test.test(sorcier.getCaracteristiques().equals(Caracteristiques.SORCIER),
                "test des caractéristiques du personnage");
        Test.test(sorcier.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(sorcier.getAssassine()==false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(sorcier.getVole()==false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DU POUVOIR DU SORCIER");
        PlateauDeJeu plateau = new PlateauDeJeu();
        
        // création de quatre personnages
        Sorcier sorcier = new Sorcier();
        plateau.ajouterPersonnage(sorcier);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Magicienne magicienne = new Magicienne();
        plateau.ajouterPersonnage(magicienne);
            
        // création de 2 joueurs
        Joueur joueur1 = new Joueur("Milou");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur2);
        
            
        // on associe les personnages aux joueurs
        sorcier.setJoueur(joueur1);
        assassin.setJoueur(joueur2);
    
        
        // création d'une pioche:
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
        //on donne des pièces au sorcier
        sorcier.ajouterPieces();
        
    
        // on affiche la main de chaque joueur:
        System.out.print("Main du Sorcier (" + sorcier.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainSorcier = sorcier.getJoueur().getMain();
        for (Quartier quartier : mainSorcier) if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
        
        System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
        ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMain();
        for (Quartier quartier : mainAssassin) if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
        //on affiche la cité du Sorcier
        System.out.print("Cite du Sorcier (" + sorcier.getJoueur().getNom() + "): ");
        Quartier[] citeSorcier = sorcier.getJoueur().getCite();
        for (Quartier quartier : citeSorcier)
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
                
        
        // utiliser le pouvoir du sorcier :     
        sorcier.utiliserPouvoir();
        
        // on réaffiche la main de chaque joueur:
        System.out.print("Main du Sorcier (" + sorcier.getJoueur().getNom() + "): ");
        for (Quartier quartier : mainSorcier) if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
        
        System.out.print("Main de l'assassin (" + assassin.getJoueur().getNom() + "): ");
        for (Quartier quartier : mainAssassin)
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
        //on affiche la cité du Sorcier
        System.out.print("Cite du Sorcier (" + sorcier.getJoueur().getNom() + "): ");
        for (Quartier quartier : citeSorcier)
            if (quartier != null)System.out.print(quartier.getNom() + ", ");
        System.out.println();
        

    }
}
