package modele;

public abstract class Personnage {

    private String nom;
    private Integer rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;
    private PlateauDeJeu plateau;

    /**
     * Constructeur Personnage
     * @param nom : nom du personnage
     * @param rang : rang du personnage
     * @param caracteristiques : caracteristiques du personnage
     */
    public Personnage(String nom, Integer rang, String caracteristiques) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.joueur = null;
        this.assassine = false;
        this.vole = false;
    }

    /**
     * Renvoie le nom du personnage
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le rang du personnage
     * @return rang
     */
    public Integer getRang() {
        return rang;
    }

    /**
     * Renvoie les caracteristiques du personnage
     * @return caracteristiques
     */
    public String getCaracteristiques() {
        return caracteristiques;
    }

    /**
     * Renvoie le joueur auquel le personnage est attribué
     * @return joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Associe le joueur j au personnage
     * @param joueur 
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        this.joueur.monPersonnage = this;
    }

    /**
     * Renvoie si le personnage est assassiné ou non
     * @return assassine
     */
    public boolean getAssassine() {
        return assassine;
    }

    /**
     * Met l’attribut vole à true
     */
    public void setAssassine() {
        this.assassine = true;
    }

    /**
     * Renvoie si le personnage est volé ou non
     * @return vole
     */
    public boolean getVole() {
        return vole;
    }

    /**
     * Met l’attribut assassine à true
     * @param args : Si non vide, cet objet contient le personnage qui vole un autre
     */
    public void setVole(Object... args) {
        if(args.length > 0 && args[0] != null ) {
            ((Personnage) args[0]).getJoueur().ajouterPieces(this.joueur.nbPieces());
            this.joueur.retirerPieces(this.joueur.nbPieces());
        }
        this.vole = true;
    }

    /**
     * Renvoie le plateau de jeu du personnage
     * @return plateau
     */
    public PlateauDeJeu getPlateau() {
        return plateau;
    }

    /**
     * Modifie le plateau de jeu du personnage
     * @param plateau : plateau
     */
    public void setPlateau(PlateauDeJeu plateau) {
        this.plateau = plateau;
    }

    /**
     * Ajouter deux pièces au trésor du joueur auquel le personnage est associé
     */
    public void ajouterPieces(){
        if(getJoueur() != null && !getAssassine())this.joueur.ajouterPieces(2);
    }

    /**
     * Ajouter deux pièces au trésor du joueur auquel le personnage est associé
     * @param nouveau : nouveau quartier
     */
    public void ajouterQuartier(Quartier nouveau){
        if(getJoueur() != null && !getAssassine())this.joueur.ajouterQuartierDansMain(nouveau);
    }
    /**
     * Ajoute un nouveau quartier dans la cité du joueur
     * @param nouveau : nouveau quartier
     */
    public void construire(Quartier nouveau){
        if(getJoueur() != null && !getAssassine())this.joueur.ajouterQuartierDansCite(nouveau);
    }

    /**
     * Affiche par défaut le message aucune ressource spécifique
     */
    public void percevoirRessourcesSpecifiques(){
        if(getJoueur() != null && !getAssassine())System.out.println("aucune ressource sp´ecifique");
    }

    /**
     * Utiliser le pouvoir du personnage
     */
    public abstract void utiliserPouvoir();

    /**
     * Remettre à leur valeur initiale (comme dans le constructeur) les attributs joueur, vole et assassine
     */
    public void reinitialiser(){
        this.assassine = false;
        this.vole = false;
        if (this.joueur != null)this.joueur.monPersonnage = null;
    }
}
