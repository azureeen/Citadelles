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
        if(getJoueur() != null && getAssassine() == false){
            System.out.println("Je prends la couronne");
            getJoueur().setPossedeCouronne(true);
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        if(getJoueur() != null && getAssassine() == false){
            int nbQuartiersNobles = 0;
            for(Quartier quartier : getJoueur().getCite()){
                if(quartier != null && quartier.getType() == "NOBLE")nbQuartiersNobles++;
            }
            getJoueur().ajouterPieces(nbQuartiersNobles);
            System.out.println(nbQuartiersNobles + " pièces ont été ajoutées au tresor de " + getJoueur().getNom());
        }
    }
}
