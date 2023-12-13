package modele;

public class PlateauDeJeu {
    private Integer nombrePersonnages;
    private Integer nombreJoueurs;
    private Pioche pioche;
    private final Personnage[] listePersonnages;
    private final Joueur[] listeJoueurs;

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

    /**
     * TODO
     * @param p /
     */
    public void setPioche(Pioche p){
        this.pioche = p;
    }

    /***
     * Renvoie le i ème personnage du tableau listePersonnages
     * @param i : index dans le tableau
     * @return personnage
     */
    public Personnage getPersonnage(int i) {
        Personnage p;
        p=(i>=0 && i<=listePersonnages.length-1) ? listePersonnages[i] : null;
        return p;
    }

    /***
     * Renvoie la liste des personnages
     * @return listePersonnages
     */
    public Personnage[] getListPersonnages() {
        return this.listePersonnages;
    }

    /***
     * Renvoie le i ème joueur du tableau listeJoueurs
     * @param i : index dans le tableau
     * @return personnage
     */
    public Joueur getJoueur(int i) {

        Joueur j;

        j=(i>=0 && i<=listePersonnages.length-1) ? listeJoueurs[i] : null;

        return j;
    }

    /***
     * Renvoie la liste des personnages
     * @return listePersonnages
     */
    public Joueur[] getListeJoueurs() {
        return this.listeJoueurs;
    }

    /**
     * Ajouter un nouveau personnage dans le plateau de jeu
     * @param nouveau : nouveau personnage
     */
    public void ajouterPersonnage(Personnage nouveau){

        if(nouveau != null){
            boolean isFull = true;
            for(int i=0; i<=listePersonnages.length-1; i++){
                if (listePersonnages[i] == null) {
                    isFull = false;
                    break;
                }
            }

            if(!isFull){
                boolean isAlreadyAdded = false;
                for(int i=0; i<=listePersonnages.length-1; i++){
                    if(listePersonnages[i] == null && !isAlreadyAdded){
                        listePersonnages[i] = nouveau;
                        this.nombrePersonnages++;
                        isAlreadyAdded = true;
                    }
                }
            }
            nouveau.setPlateau(this);
        }
    }

    /**
     * Ajouter un nouveau joueur dans le plateau de jeu
     * @param nouveau : nouveau joueur
     */
    public void ajouterJoueur(Joueur nouveau){

        if(nouveau != null) {
            boolean isFull = true;
            for (int i = 0; i <= listeJoueurs.length - 1; i++) {
                if (listeJoueurs[i] == null) {
                    isFull = false;
                    break;
                }
            }

            if (!isFull) {
                boolean isAlreadyAdded = false;
                for (int i = 0; i <= listeJoueurs.length - 1; i++) {
                    if (listeJoueurs[i] == null && !isAlreadyAdded) {
                        listeJoueurs[i] = nouveau;
                        this.nombreJoueurs++;
                        isAlreadyAdded = true;
                    }
                }
            }
        }
    }

    public Personnage[] getListePersonnages() {
        return this.listePersonnages;
    }
}
