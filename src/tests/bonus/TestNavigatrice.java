package tests.bonus;
import modele.*;
import tests.Test;

public class TestNavigatrice {

	public static void main(String[] args) {
		TestNavigatrice test= new TestNavigatrice();
		test.test1();
		test.test2();	
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Navigatrice navigatrice = new Navigatrice();
		Test.test(navigatrice.getNom().equals("Navigatrice"),"test du nom du personnage");
		Test.test(navigatrice.getRang()== 7,"test du rang du personnage");
		Test.test(navigatrice.getCaracteristiques().equals(Caracteristiques.NAVIGATRICE),
				"test des caract�ristiques du personnage");
		Test.test(navigatrice.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!navigatrice.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!navigatrice.getVole(), "test de l'initialisation de la variable \"vole\"");
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
		Navigatrice navigatrice = new Navigatrice();
		plateau.ajouterPersonnage(navigatrice);
		// on ajoute le joueur au plateau:
		Joueur joueur = new Joueur("Billy");
		plateau.ajouterJoueur(joueur);
		navigatrice.setJoueur(joueur);

		Test.test(navigatrice.getJoueur().nbQuartiersDansMain() == 0 && navigatrice.getJoueur().nbPieces()==0,
				"test du nombre de cartes  dans la main et de pieces avant l'utilisation du pouvoir");			
		navigatrice.utiliserPouvoirAvatar();
		Test.test(navigatrice.getJoueur().nbQuartiersDansMain() == 4 ||navigatrice.getJoueur().nbPieces()==4,
				"test du nombre de cartes dans la main et de pieces apr�s l'utilisation du pouvoir");
        Test.test(pioche.nombreElements()==3 || pioche.nombreElements()==7, "test qu'il y a 4 ou 7 carte en moins dans la pioche ");
			
	}
}
