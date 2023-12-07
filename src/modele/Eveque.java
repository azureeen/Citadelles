package modele;

import java.util.Objects;

public class Eveque extends Personnage{

    /**
     * Constructeur Eveque
     */
    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }

    /**
     * Utiliser le pouvoir de l'Évêque
     * Reçoit une pièce d’or supplémentaire par quartier religieux dans sa cité
     */
    @Override
    public void utiliserPouvoir() {
        System.out.println("L'Évêque est protégé par les pouvoirs des personnages de rang 8");
    }

    @Override
    public void utiliserPouvoirAvatar() {

    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {

    }


    /**
     * Percevoir ressources spécifiques de l'Évêque
     */
    @Override
    public void percevoirRessourcesSpecifiques() {
        if(!getAssassine() && getJoueur() != null){

            int nbQuartiersReligieux = 0;
            for(int i = 0 ; i < getJoueur().getCite().length; i++) {

                if(getJoueur().getCite()[i] != null && Objects.equals(getJoueur().getCite()[i].getType(), "RELIGIEUX")){
                    nbQuartiersReligieux++;
                }
            }
            getJoueur().ajouterPieces(nbQuartiersReligieux);
            System.out.println(nbQuartiersReligieux + " pièces supplémentaires ont été ajoutées au tresor de " + getJoueur().getNom());
        }

    }


}
