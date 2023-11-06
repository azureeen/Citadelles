package modele;

import java.util.ArrayList;

public class Pioche {
    private ArrayList<Quartier> liste;

    /**
     * Constructeur Pioche
     */
    public Pioche() {
        this.liste = new ArrayList<Quartier>();
    }

    /**
     * Retire la carte au sommet de la pioche et la renvoie
     * @return carte Quartier
     */
    public Quartier piocher(){
        // TODO : Remplir Méthode piocher
        return null;
    }

    /**
     * Ajoute une carte Quartier au bas de la pioche
     * @param nouveau : nouvelle carte quartier
     */
    public void ajouter(Quartier nouveau){
        // TODO : Remplir Méthode ajouter
    }

    /**
     * Renvoie le nombre d’élèments de la pioche
     * @return nombre d’élèments
     */
    public Integer nombreElements(){
        // TODO : Remplir Méthode nombreElements
        return null;
    }

    /**
     * Permet de mélanger la pioche
     */
    public void melanger(){
        // TODO : Remplir Méthode melanger
    }
}
