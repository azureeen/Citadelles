package modele;

import java.util.Random;
import Controleur.Interaction;
import application.Jeu;

import java.util.ArrayList;

public class Bailli extends Personnage {
    public Bailli(){
        super("Bailli",9,Caracteristiques.BAILLI);
    }

    /**
     * TODO
     */
    public void utiliserPouvoir(){
        if(Jeu.orDesTaxes != 0){
            boolean recupererLOr;
            System.out.println("Voulez-vous récupérer l'or des taxes ?");
            recupererLOr = Interaction.lireOuiOuNon();

            if(recupererLOr){
                this.getJoueur().ajouterPieces(Jeu.orDesTaxes);
                Jeu.orDesTaxes = 0;
            }
        }
    }

    /**
     * TODO
     */
    public void percevoirRessourcesSpecifiques(){

    }

    /**
     * TODO
     */
    public void utiliserPouvoirAvatar(){
        Random generateur = new Random();
        if(Jeu.orDesTaxes != 0){
            boolean recupererLOr = false;
            System.out.println("Voulez-vous récupérer l'or des taxes ?");
            recupererLOr = generateur.nextBoolean();

            if(recupererLOr){
                this.getJoueur().ajouterPieces(Jeu.orDesTaxes);
                Jeu.orDesTaxes = 0;
            }
        }
        
    }

    /**
     * TODO
     * @param joueurSorciere
     */
    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }

}