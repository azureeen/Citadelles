package tests.bonus;
import modele.*;
import tests.part1.Test;

public class TestMaitreChanteuse {

	public static void main(String[] args) {
		TestMaitreChanteuse test= new TestMaitreChanteuse();
		test.test1();
		//test.test2();	
	}
	
	public void test1(){
		System.out.println("TEST DU CONSTRUCTEUR");
		MaitreChanteuse mchanteuse = new MaitreChanteuse();
		Test.test(mchanteuse.getNom().equals("Maître-Chanteuse"),"test du nom du personnage");
		Test.test(mchanteuse.getRang()== 2,"test du rang du personnage");
		Test.test(mchanteuse.getCaracteristiques().equals(Caracteristiques.MAITRE_CHANTEUSE),
				"test des caractéristiques du personnage");
		Test.test(mchanteuse.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
		Test.test(!mchanteuse.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
		Test.test(!mchanteuse.getVole(), "test de l'initialisation de la variable \"vole\"");
	}
}