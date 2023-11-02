package modele;

public class Roi extends Personnage{


    /**
     * Constructeur Roi
     */
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }

    /**
     * Utiliser le pouvoir du Roi
     */
    @Override
    public void utiliserPouvoir() {
        // TODO : Remplir méthode utiliserPouvoir
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        // TODO : Remplir Méthode surchargée percevoirRessourcesSpecifiques
    }
}
