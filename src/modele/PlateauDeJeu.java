package modele;

public class PlateauDeJeu {
    private Integer nombrePersonnages;
    private Integer nombreJoueurs;
    private Pioche pioche;
    private Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;

    public PlateauDeJeu() {
        this.nombreJoueurs = 0;
        this.nombrePersonnages = 0;
        this.pioche = new Pioche();
        this.listeJoueurs = new Joueur[9];
        this.listePersonnages = new Personnage[9];
    }

    /**
     * Renvoie la valeur nombrePersonnage
     * @return nombrePersonnages
     */
    public Integer getNombrePersonnages() {
        return nombrePersonnages;
    }

    /**
     * Renvoie la valeur nombreJoueurs
     * @return nombreJoueurs
     */
    public Integer getNombreJoueurs() {
        return nombreJoueurs;
    }

    /**
     * Renvoie la variable pioche
     * @return pioche
     */
    public Pioche getPioche() {
        return pioche;
    }

    /***
     * Renvoie le i ème personnage du tableau listePersonnages
     * @param i : index dans le tableau
     * @return personnage
     */
    public Personnage getPersonnage(int i) {
        // TODO : Remplir méthode getPersonnage
        return null;
    }

    /***
     * Renvoie le i ème joueur du tableau listeJoueurs
     * @param i : index dans le tableau
     * @return personnage
     */
    public Joueur getJoueur(int i) {
        // TODO : Remplir méthode getJoueur
        return null;
    }

    /**
     * Ajouter un nouveau personnage dans le plateau de jeu
     * @param nouveau : nouveau personnage
     */
    public void ajouterPersonnage(Personnage nouveau){
        // TODO : Remplir méthode ajouterPersonnage
    }

    /**
     * Ajouter un nouveau joueur dans le plateau de jeu
     * @param nouveau : nouveau joueur
     */
    public void ajouterJoueur(Joueur nouveau){
        // TODO : Remplir méthode ajouterJoueur
    }
}
