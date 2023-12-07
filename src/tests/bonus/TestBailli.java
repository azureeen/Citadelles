package tests.bonus;
import application.Jeu;
import modele.*;
import tests.part1.Test;

public class TestBailli {

	public static void main(String[] args) {
		TestBailli test= new TestBailli();
		test.test1();
		//test.test2();
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		Bailli bailli = new Bailli();
		Test.test(bailli.getNom().equals("Bailli"),"test du nom du personnage");
		Test.test(bailli.getRang()== 9,"test du rang du personnage");
		Test.test(bailli.getCaracteristiques().equals(Caracteristiques.BAILLI),
				"test des caractéristiques du personnage");
		Test.test(bailli.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!bailli.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!bailli.getVole(), "test de l'initialisation de la variable \"vole\"");
	}

}