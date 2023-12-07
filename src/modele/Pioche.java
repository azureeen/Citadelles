package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
    private final ArrayList<Quartier> liste;

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
        Quartier recupVar;

        if(liste.isEmpty()){
            recupVar = null;
        }
        else {
            recupVar = liste.remove(0);
        }

        return recupVar;
    }

    /**
     * Ajoute une carte Quartier au bas de la pioche
     * @param nouveau : nouvelle carte quartier
     */
    public void ajouter(Quartier nouveau){
        liste.add(nouveau);
    }

    /**
     * Renvoie le nombre d’élèments de la pioche
     * @return nombre d’élèments
     */
    public Integer nombreElements(){
        return liste.size();
    }

    /**
     * Permet de mélanger la pioche
     */
    public void melanger(){

        Random generateur = new Random();

        int i = generateur.nextInt(nombreElements());
        int j = generateur.nextInt(nombreElements());

        Quartier q1 = liste.get(i);
        Quartier q2 = liste.get(j);

        liste.set(i,q2);
        liste.set(j,q1);
    }
}