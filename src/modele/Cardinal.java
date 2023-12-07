package modele;

import java.util.Random;
import Controleur.Interaction;
import java.util.ArrayList;

public class Cardinal extends Personnage {
    public Cardinal(){
        super("Cardinal",5,Caracteristiques.CARDINAL);
    }

    public void percevoirRessourcesSpecifiques(){

        if(this.getJoueur() != null && !this.getAssassine()){
            for(int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++){
                if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[0]) && this.getJoueur().getCite()[i] != null){
                    this.getJoueur().ajouterPieces(1);
                }
            }
            if(this.getJoueur().getPossedeEcoleMag()){//la merveille Ecole de Magie change de type a la perception des ressources
                this.getJoueur().ajouterPieces(1);
            }
        }
    }    

    public void utiliserPouvoir(){

    }

    public void utiliserPouvoirAvatar(){
    
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}