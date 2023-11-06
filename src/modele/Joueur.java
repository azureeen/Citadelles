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
     * @return null
     */
    public Integer nbQuartiersDansCite() {
        // TODO : Remplir Méthode nbQuartiersDansCite
        return null;
    }


    /**
     * Renvoie le nombre quartiers de la main
     * @return null
     */
    public Integer nbQuartiersDansMain() {
        // TODO : Remplir Méthode nbQuartiersDansMain
        return null;
    }

    /**
     * Ajouter des pièces d’or du trésor du joueur
     * @param nbPieces /
     */
    public void ajouterPieces(int nbPieces){
       // TODO : Remplir Méthode ajouterPieces
    }

    /**
     * Retirer des pièces d’or du trésor du joueur
     * @param nbPieces /
     */
    public void retirerPieces(int nbPieces){
        // TODO : Remplir Méthode retirerPieces
    }


    /**
     * Ajouter un quartier dans la cité
     * @param quartier /
     */
    public void ajouterQuartierDansCite(Quartier quartier){
        // TODO : Remplir Méthode ajouterQuartierDansCite
    }

    /**
     * Renvoie true si le quartier dont le nom est passé en paramètre est présent dans la cité
     * @param quartier /
     * @return boolean
     */
    public boolean quartierPresentDansCite(String quartier){
        // TODO : Remplir Méthode quartierPresentDansCite
        return false;
    }

    /**
     * Retire le quartier de la cité dont le nom est donné en paramètre
     * @param quartier /
     */
    public Quartier retirerQuartierDansCite(String quartier){
        // TODO : Remplir Méthode retirerQuartierDansCite
        return null;
    }

    /**
     * Ajouter un quartier à la main du joueur
     * @param quartier /
     */
    public void ajouterQuartierDansMain(Quartier quartier){
        // TODO : Remplir Méthode ajouterQuartierDansMain
    }

    /**
     * Retirer un quartier à la main du joueur de manière aléatoire
     * @return quartier
     */
    public Quartier retirerQuartierDansMain(){
        // TODO : Remplir Méthode retirerQuartierDansMain
        return null;
    }

    /**
     * Remet à 0 le trésor du joueur, videra sa main et sa cité
     */
    public void reinitialiser(){
        // TODO : Remplir Méthode reinitialiser
    }
}
