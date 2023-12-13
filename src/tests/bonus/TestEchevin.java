package tests.bonus;
import modele.*;
import tests.Test;

public class TestEchevin {

    public static void main(String[] args) {
        TestEchevin test= new TestEchevin();
        test.test1();
        //test.test2();
    }

    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
        Echevin echevin = new Echevin();
        Test.test(echevin.getNom().equals("Echevin"),"test du nom du personnage");
        Test.test(echevin.getRang()== 1,"test du rang du personnage");
        Test.test(echevin.getCaracteristiques().equals(Caracteristiques.ECHEVIN),
                "test des caractéristiques du personnage");
        Test.test(echevin.getJoueur()==null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(!echevin.getAssassine(), "test de l'initialisation de la variable \"assassine\"");
        Test.test(!echevin.getVole(), "test de l'initialisation de la variable \"vole\"");
    }
}