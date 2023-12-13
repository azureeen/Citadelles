package tests.bonus;

import modele.*;
import tests.Test;

public class TestEspion {

	public static void main(String[] args) {
		TestEspion test= new TestEspion();
		test.test1();
		test.test2();	
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Espion espion = new Espion();
		Test.test(espion.getNom().equals("Espion"),"test du nom du personnage");
		Test.test(espion.getRang()== 2,"test du rang du personnage");
		Test.test(espion.getCaracteristiques().equals(Caracteristiques.ESPION),
				"test des caract�ristiques du personnage");
		Test.test(espion.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!espion.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!espion.getVole(), "test de l'initialisation de la variable \"vole\"");
	}
	public void test2() {
		System.out.println("TEST DU POUVOIR");
		PlateauDeJeu plateau = new PlateauDeJeu();
        Espion espion = new Espion();
		Assassin assassin = new Assassin();
        Joueur jEspion = new Joueur("jEspion");
        Joueur jAssassin = new Joueur("jAssassin");
        espion.setJoueur(jEspion);
        assassin.setJoueur(jAssassin);
        plateau.ajouterPersonnage(espion);
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterJoueur(jEspion);
        plateau.ajouterJoueur(jAssassin);
        jAssassin.ajouterPieces(5);
        Test.test(jEspion.nbPieces()==0 && jAssassin.nbPieces() == 5, "Test des trésors avant utilisation du pouvoir");
		jAssassin.ajouterQuartierDansMain(new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1));
        jAssassin.ajouterQuartierDansMain(new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2));
        jAssassin.ajouterQuartierDansMain(new Quartier("monastère", Quartier.TYPE_QUARTIERS[0], 3));
        jAssassin.ajouterQuartierDansMain(new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5)); //au moins - quartier de chaque type
        jAssassin.ajouterQuartierDansMain(new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5));
        jAssassin.ajouterQuartierDansMain(new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5));
        jAssassin.ajouterQuartierDansMain(new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2));
        jAssassin.ajouterQuartierDansMain(new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez de piocher des cartes au début du tour, concervez-les toutes."));
		

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



		espion.utiliserPouvoir();
        Test.test(jEspion.nbPieces()>0 && jAssassin.nbPieces() < 5, "Test des trésors après utilisation du pouvoir");
        Test.test(jEspion.nbQuartiersDansMain()>0 , "Test des trésors avant utilisation du pouvoir");
	}
}