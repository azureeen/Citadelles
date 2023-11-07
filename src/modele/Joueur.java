package modele;


import java.util.ArrayList;

public class Joueur {

    private String nom;
    private Integer tresor;
    private Quartier[] cite;
    private Integer nbQuartiers;
    private ArrayList<Quartier> main;
    private boolean possedeCouronne;

    /**
     * Constructeur Joueur
     * @param nom : nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<Quartier>();
    }

    /**
     * Renvoie le nom du joueur
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le nombre de pieces d'or du joueur
     * @return tresor
     */
    public Integer nbPieces() {
        return tresor;
    }

    /**
     * Renvoie le tableau complet de quartier de la cité
     * @return cite
     */
    public Quartier[] getCite() {
        return cite;
    }


    /**
     * Renvoie l’ensemble de la main ArrayList<Quartier>
     * @return main
     */
    public ArrayList<Quartier> getMain() {
        return main;
    }

    /**
     * Renvoie la valeur de l’attribut possedeCouronne
     * @return possedeCouronne
     */
    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    /**
     * Modifie la valeur de l’attribut possedeCouronne
     * @param possedeCouronne /
     */
    public void setPossedeCouronne(boolean possedeCouronne) {
        this.possedeCouronne = possedeCouronne;
    }

    /**
     * Renvoie le nombre quartiers de la cité
     * @return Le nombre de quartiers dans la cité
     */
    public Integer nbQuartiersDansCite() {
        return nbQuartiers;
    }

    /**
     * Renvoie le nombre quartiers de la main
     * @return Le nombre de quartiers dans la main
     */
    public Integer nbQuartiersDansMain() {
        return main.size();
    }

    /**
     * Ajouter des pièces d’or au trésor du joueur
     * @param nbPieces Le nombre de pièces à ajouter
     */
    public void ajouterPieces(int nbPieces) {
        tresor += nbPieces;
    }

    /**
     * Retirer des pièces d’or du trésor du joueur
     * @param nbPieces Le nombre de pièces à retirer
     */
    public void retirerPieces(int nbPieces) {
        tresor -= nbPieces;
    }

    /**
     * Ajouter un quartier dans la cité
     * @param quartier Le quartier à ajouter
     */
    public void ajouterQuartierDansCite(Quartier quartier) {
        if (nbQuartiers < cite.length) {
            cite[nbQuartiers] = quartier;
            nbQuartiers++;
        }
    }

    /**
     * Renvoie true si le quartier dont le nom est passé en paramètre est présent dans la cité
     * @param quartier Le nom du quartier à rechercher
     * @return true si le quartier est présent, sinon false
     */
    public boolean quartierPresentDansCite(String quartier) {
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i].getNom().equals(quartier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retire le quartier de la cité dont le nom est donné en paramètre
     * @param quartier Le nom du quartier à retirer
     * @return Le quartier retiré (ou null s'il n'est pas présent)
     */
    public Quartier retirerQuartierDansCite(String quartier) {
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i].getNom().equals(quartier)) {
                Quartier removedQuartier = cite[i];
                cite[i] = null;
                nbQuartiers--;
                // Shift the remaining quartiers to fill the gap
                for (int j = i; j < nbQuartiers; j++) {
                    cite[j] = cite[j + 1];
                    cite[j + 1] = null;
                }
                return removedQuartier;
            }
        }
        return null; // Quartier not found
    }

    /**
     * Ajouter un quartier à la main du joueur
     * @param quartier Le quartier à ajouter à la main
     */
    public void ajouterQuartierDansMain(Quartier quartier) {
        main.add(quartier);
    }

    /**
     * Retirer un quartier de manière aléatoire de la main du joueur
     * @return Le quartier retiré de la main (or null if the hand is empty)
     */
    public Quartier retirerQuartierDansMain() {
        if (!main.isEmpty()) {
            int randomIndex = (int) (Math.random() * main.size());
            return main.remove(randomIndex);
        }
        return null; // Hand is empty
    }

    /**
     * Remet à 0 le trésor du joueur, videra sa main et sa cité
     */
    public void reinitialiser() {
        tresor = 0;
        nbQuartiers = 0;
        possedeCouronne = false;
        main.clear();
        for (int i = 0; i < cite.length; i++) {
            cite[i] = null;
        }
    }

}
