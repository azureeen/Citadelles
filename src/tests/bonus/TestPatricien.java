package tests.bonus;

import modele.Caracteristiques;
import modele.Joueur;
import modele.Quartier;
import modele.Patricien;
import modele.Pioche;
import modele.PlateauDeJeu;
import tests.part1.Test;

public class TestPatricien {

	public static void main(String[] args) {
		TestPatricien testPatricien= new TestPatricien();
		//testPatricien.test1();
		//testPatricien.test2();
		//testPatricien.test3();
		//testPatricien.test4();
		//testPatricien.test5();
		//testPatricien.test6();
		//testPatricien.test7();
		//testPatricien.test8();
		//testPatricien.test9();
		//testPatricien.test10();
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Patricien patricien = new Patricien();
		Test.test(patricien.getNom().equals("Patricien"),"test du nom du personnage Patricien");
		Test.test(patricien.getRang()== 4,"test du rang du personnage Patricien");
		Test.test(patricien.getCaracteristiques().equals(Caracteristiques.PATRICIEN),
				"test des caract�ristiques du personnage Patricien");
		Test.test(patricien.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!patricien.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!patricien.getVole(), "test de l'initialisation de la variable \"vole\"");
	}
	public void test2(){
		System.out.println("TEST DE L'ATTRIBUTION D'UN JOUEUR");
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.setJoueur(joueur);
		Test.test(patricien.getJoueur() == joueur,"test de l'attribution de la variable \"joueur\"");
		Test.test(patricien.getJoueur().getNom().equals("Billy"),"test du nom de joueur attribu�");
	}
	public void test3(){
		System.out.println("TEST DE L'ASSASSINAT DU PERSONNAGE");
		Patricien patricien = new Patricien();
		patricien.setAssassine();
		Test.test(patricien.getAssassine(),"test de l'assassinat");
	}
	public void test4(){
		System.out.println("TEST DU VOL DU PERSONNAGE");
		Patricien patricien = new Patricien();
		patricien.setVole();
		Test.test(patricien.getVole(),"test du vol");
	}
	public void test5(){
		System.out.println("TEST DE LA PERCEPTION DE PIECES D'OR");
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.ajouterPieces();
		Test.test(patricien.getJoueur() == null,"test alors que le joueur n'est pas attribu�");
		patricien.setJoueur(joueur);
		patricien.ajouterPieces();
		Test.test(patricien.getJoueur().nbPieces() == 2,"v�&rification du nombre de pi�ces d'or");
	}
	public void test6(){
		System.out.println("TEST DE L'AJOUT DE QUARTIER DANS LA MAIN DU JOUEUR");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.ajouterQuartier(quartier1);
		Test.test(patricien.getJoueur() == null,"test alors que le joueur n'est pas attribu�");
		patricien.setJoueur(joueur);
		patricien.ajouterQuartier(quartier1);
		patricien.ajouterQuartier(quartier2);
		patricien.ajouterQuartier(quartier3);
		Test.test(patricien.getJoueur().nbQuartiersDansMain() == 3,"test du nombre de quartiers apr�s ajout");
	}
	public void test7(){
		System.out.println("TEST DE LA CONSTRUCTION D'UN QUARTIER DANS LA CITE DU JOUEUR");
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.construire(quartier1);
		Test.test(patricien.getJoueur() == null,
				"test alors que le joueur n'est pas attribu�");
		patricien.setJoueur(joueur);
		patricien.construire(quartier1);
		patricien.construire(quartier2);
		patricien.construire(quartier3);
		Test.test(patricien.getJoueur().nbQuartiersDansCite() == 3,
				"test du nombre de quartiers apr�s construction");
		
		Test.test(patricien.getJoueur().quartierPresentDansCite("prison"),
				"test de la pr�sence de la prison dans la cit�");
	}
	public void test8(){
		System.out.println("TEST DE LA PERCEPTION DE RESSOURCES SPECIFIQUES");
		Joueur joueur = new Joueur("Billy");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Patricien patricien = new Patricien();
		plateau.ajouterJoueur(joueur);
		plateau.ajouterPersonnage(patricien);
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Pioche pioche = plateau.getPioche();
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
		q = new Quartier("�choppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
		pioche.ajouter(q);
		patricien.percevoirRessourcesSpecifiques();
		Test.test(patricien.getJoueur() == null,
			"test alors que le joueur n'est pas attribu�");
		patricien.setJoueur(joueur);
		patricien.ajouterPieces();
		Test.test(patricien.getJoueur().nbQuartiersDansMain() == 0,
			"test du nombre de quartier dans la main avant perception");
		patricien.percevoirRessourcesSpecifiques();
		Test.test(patricien.getJoueur().nbQuartiersDansMain() == 0,
			"test alors qu'il n'y a pas de quartiers nobles");
		patricien.construire(quartier1);
		patricien.construire(quartier2);
		patricien.construire(quartier3);		
		patricien.percevoirRessourcesSpecifiques();
		Test.test(patricien.getJoueur().nbQuartiersDansMain() == 1,
			"test du nombre de quartiers dans la main apr�s perception de ressources sp�cifiques avec 1 quartier noble");
	}
	public void test9(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR DU PATRICIEN");
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.utiliserPouvoir();
		Test.test(patricien.getJoueur() == null,
				"test alors que le joueur n'est pas attribu�");
		patricien.setJoueur(joueur);
		Test.test(!patricien.getJoueur().getPossedeCouronne(), "test avant utilisation");
		patricien.utiliserPouvoir();
		Test.test(patricien.getJoueur().getPossedeCouronne(), "test apr�s utilisation");
	}
	public void test10(){
		System.out.println("TEST DE LA REINITIALISATION");
		Joueur joueur = new Joueur("Billy");
		Patricien patricien = new Patricien();
		patricien.setJoueur(joueur);
		patricien.setAssassine();
		patricien.setVole();
		patricien.reinitialiser();
		Test.test(patricien.getJoueur() == null, "test du joueur non attribu�");
		Test.test(!patricien.getAssassine(), "test de l'assassinat du personnage");
		Test.test(!patricien.getVole(), "test du vol du personnage");
	}
}
