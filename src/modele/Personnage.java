package modele;

public abstract class Personnage {

    private String nom;
    private Integer rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;
    private PlateauDeJeu plateau;
    protected boolean ensorcele;
    protected boolean vraiEchevin;
    protected boolean fauxEchevin;
    protected boolean vraieMenace;
    protected boolean fausseMenace;


    /**
     * Constructeur Personnage
     * @param args : Objet d'arguments
     */
    public Personnage(Object... args) {
        setNom(args.length > 0 && args[0] != null ? (String) args[0] : "");
        setRang(args.length > 1 && args[1] != null ? (Integer) args[1] : 0);
        setCaracteristiques(args.length > 2 && args[2] != null ? (String) args[2] : "");
        this.joueur = null;
        this.assassine = false;
        this.vole = false;
        this.ensorcele = false;
        this.vraiEchevin = false;
        this.fauxEchevin = false;
        this.vraieMenace = false;
        this.fausseMenace = false;
    }

    /**
     * Renvoie le nom du personnage
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter var : nom
     * @param nom /
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie le rang du personnage
     * @return rang
     */
    public Integer getRang() {
        return rang;
    }

    /**
     * Setter var : rang
     * @param rang /
     */
    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /**
     * Renvoie les caracteristiques du personnage
     * @return caracteristiques
     */
    public String getCaracteristiques() {
        return caracteristiques;
    }

    /**
     * Setter var : caracteristiques
     * @param caracteristiques /
     */
    public void setCaracteristiques(String caracteristiques){
        this.caracteristiques = caracteristiques;
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
     * TODO : comment get
     * @return
     */
    public boolean getEnsorcele() {
        return ensorcele;
    }

    /**
     *
     * @param bool
     */
    public void setVraiEchevin(boolean bool) {
        this.vraiEchevin = bool;
    }

    /**
     *
     * @return
     */
    public boolean getVraiEchevin() {
        return this.vraiEchevin;
    }

    /**
     *
     * @param bool
     */
    public void setFauxEchevin(boolean bool) {
        this.fauxEchevin = bool;
    }

    /**
     *
     * @return
     */
    public boolean getFauxEchevin() {
        return this.fauxEchevin;
    }

    /**
     *
     * @param bool
     */
    public void setFausseMenace(boolean bool) {
        this.fausseMenace = bool;
    }

    /**
     *
     * @return
     */
    public boolean getFausseMenace() {
        return this.fausseMenace;
    }

    /**
     *
     * @param bool
     */
    public void setVraieMenace(boolean bool) {
        this.vraieMenace = bool;
    }

    /**
     *
     * @return
     */
    public boolean getVraieMenace() {
        return this.vraieMenace;
    }

    /**
     *
     * @param ensorcele
     */
    public void setEnsorcele(boolean ensorcele) {
        this.ensorcele = ensorcele;
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
        this.joueur = null; // à enlever ?
        this.vole = false;
        this.assassine = false;
        this.ensorcele = false;
        if (this.joueur != null)this.joueur.monPersonnage = null;
    }

    public abstract void utiliserPouvoirAvatar();

    public abstract void activationPouvoirSorciere(Joueur joueurSorciere);
}
