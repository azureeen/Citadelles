package tests.bonus;
import modele.*;
import tests.Test;

public class TestArchiviste {

	public static void main(String[] args) {
		TestArchiviste test= new TestArchiviste();
		test.test1();
		test.test2();	
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Archiviste archiviste = new Archiviste();
		Test.test(archiviste.getNom().equals("Archiviste"),"test du nom du personnage");
		Test.test(archiviste.getRang()== 7,"test du rang du personnage");
		Test.test(archiviste.getCaracteristiques().equals(Caracteristiques.ARCHIVISTE),
				"test des caract�ristiques du personnage");
		Test.test(archiviste.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!archiviste.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!archiviste.getVole(), "test de l'initialisation de la variable \"vole\"");
	}
	
	public void test2(){
		System.out.println("TEST DE L'UTILISATION DU POUVOIR");
		// on cr�e un plateau et on ajoute des cartes Quartier � la pioche:		
		PlateauDeJeu plateau = new PlateauDeJeu();
		Quartier quartier1 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier2 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier3 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
        Quartier quartier4 = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
		Quartier quartier5 = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
		Quartier quartier6 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Quartier quartier7 = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
		Pioche pioche = plateau.getPioche();
		pioche.ajouter(quartier1);
		pioche.ajouter(quartier2);
		pioche.ajouter(quartier3);
        pioche.ajouter(quartier4);
        pioche.ajouter(quartier5);
        pioche.ajouter(quartier6);
        pioche.ajouter(quartier7);
		// on ajoute le personnage au plateau:
		Archiviste archiviste = new Archiviste();
		plateau.ajouterPersonnage(archiviste);
		// on ajoute le joueur au plateau:
		Joueur joueur = new Joueur("Billy");
		plateau.ajouterJoueur(joueur);
		archiviste.setJoueur(joueur);

		Test.test(archiviste.getJoueur().nbQuartiersDansMain() == 0,
				"test du nombre de cartes dans la main avant l'utilisation du pouvoir");			
		archiviste.utiliserPouvoir();
		Test.test(archiviste.getJoueur().nbQuartiersDansMain() == 1,
				"test du nombre de cartes dans la main apr�s l'utilisation du pouvoir");
        Test.test(pioche.nombreElements()==6, "test qu'il y a juste 1 carte en moins dans la pioche");
	}
}
