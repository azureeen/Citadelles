package modele;

import java.util.Objects;

public class Roi extends Personnage{

    /**
     * Constructeur Roi
     */
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }

    /**
     * Utiliser le pouvoir du Roi
     */
    @Override
    public void utiliserPouvoir() {
        if(!this.getAssassine()){
            if(this.getJoueur() != null){
                System.out.println("Je prend la couronne");
                this.getJoueur().setPossedeCouronne(true);
            }else{
                System.out.println("le joueur n'est pas défini");
            }
        }else{
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
        }
    }

    /**
     * Precevoir ressources spécifiques du Roi
     */
    @Override
    public void percevoirRessourcesSpecifiques(){
        if(getJoueur() != null && !getAssassine()){
            int nbQuartiersNobles = 0;
            for(Quartier quartier : getJoueur().getCite()){
                if(quartier != null && Objects.equals(quartier.getType(), "NOBLE"))nbQuartiersNobles++;
            }
            getJoueur().ajouterPieces(nbQuartiersNobles);
            System.out.println(nbQuartiersNobles + " pièces ont été ajoutées au tresor de " + getJoueur().getNom());
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        if(!this.getAssassine()){
            if(this.getJoueur() != null){
                System.out.println("Je prend la couronne");
                this.getJoueur().setPossedeCouronne(true);
            }else{
                System.out.println("le joueur n'est pas défini");
            }
        }else{
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
        }

    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub

    }
}
