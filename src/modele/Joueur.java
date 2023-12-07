package modele;


import java.util.ArrayList;
import java.util.Arrays;

public class Joueur {

    private final String nom;
    private Integer tresor;
    private final Quartier[] cite;
    private Integer nbQuartiers;
    private Integer nbQuartiersMonument;
    private final ArrayList<Quartier> main;
    private boolean possedeCouronne;
    protected Personnage monPersonnage;
    protected Personnage[] mesPersonnages;
    private boolean aFini;
    private int points;
    private boolean possedeManu;
    private boolean possedeCarriere;
    private boolean possedeEcoleMag;

    /**
     * Constructeur Joueur
     * @param nom : nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.possedeCarriere = false;
        this.possedeEcoleMag = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<Quartier>();
        this.monPersonnage = null;
        this.mesPersonnages = new Personnage[2];
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
     *
     * @param bool /
     */
    public void setPossedeEcoleMag(boolean bool){
        this.possedeEcoleMag = bool;
    }

    /**
     *
     * @return bool
     */
    public boolean getPossedeEcoleMag(){
        return this.possedeEcoleMag;
    }

    /**
     *
     * @return /
     */
    public boolean getPossedeManu() {
        return possedeManu;
    }

    /**
     *
     * @param possedeManu -/
     */
    public void setPossedeManu(boolean possedeManu) {
        this.possedeManu = possedeManu;
    }

    /**
     *
     * @param bool /
     */
    public void setPossedeCarriere(boolean bool){
        this.possedeCarriere = bool;
    }

    /**
     *
     * @return /
     */
    public boolean getPossedeCarriere(){
        return this.possedeCarriere;
    }

    /**
     *
     * @return //
     */
    public int getPoints(){
        return this.points;
    }

    /**
     *
     * @param points /
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     *
     * @return /
     */
    public boolean getAFini(){
        return this.aFini;
    }

    /**
     *
     * @param choix /
     */
    public void setAFini(boolean choix){
        this.aFini = choix;
    }

    /**
     * Getter var : monPersonnage
     * @return /
     */
    public Personnage getPersonnage(){
        return this.monPersonnage;
    }


    /**
     * Renvoie le nombre quartiers de la cité
     * @return Le nombre de quartiers dans la cité
     */
    public Integer nbQuartiersDansCite() {
        return nbQuartiers;
    }

    /**
     *
     * @return /
     */
    public int nbQuartiersMonumentDansCite(){
        return this.nbQuartiersMonument;
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
        if (nbPieces >= 0) {
            tresor += nbPieces;
        }
    }
    /**
     * Retirer des pièces d’or du trésor du joueur
     * @param nbPieces Le nombre de pièces à retirer
     */
    public void retirerPieces(int nbPieces){
        if(nbPieces >= 0){
            if(tresor-nbPieces < 0){
                System.out.println("trop grand nombre de pièces");
            }else{
                this.tresor -= nbPieces;
            }
        }else{
            System.out.println("le nombre de pièces passé en paramètre n'est pas valable");
        }
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
        if(!(quartier==null)){
            main.add(quartier);
        }
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
     * Retirer un quartier de manière arbitraire de la main du joueur
     * @param quartier /
     * @return Quartier
     */
    public Quartier retirerQuartierDansMain(Quartier quartier){
        Quartier q;
        if(main.isEmpty()){
            q= quartier;
        }else{
            main.remove(quartier);
            q = quartier;
        }
        return q;
    }

    /**
     * Remet à 0 le trésor du joueur, videra sa main et sa cité
     */
    public void reinitialiser() {
        this.nbQuartiers = 0;
        this.nbQuartiersMonument = 0;
        this.setAFini(false);
        this.setPoints(0);
        this.tresor = 0;
        this.main.clear();
        this.possedeCouronne = false;
        Arrays.fill(cite, null);
    }

}
