package modele;

public class Marchande extends Personnage {

    /**
     * Constructeur Marchande
     */
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }
    
    
    /**
     * Utiliser le pouvoir de la marchande
     */
    @Override
    public void utiliserPouvoir(){
        if(!getAssassine() && getJoueur() != null){
            getJoueur().ajouterPieces(1);
        }
    }

    /**
     * Precevoir ressources spécifiques de la Marchande
     */
    @Override
    public void percevoirRessourcesSpecifiques() {
        if(!getAssassine() && getJoueur() != null){

            int nbQuartiersCommercants = 0;
            for(int i = 0 ; i < getJoueur().getCite().length; i++) {

                if(getJoueur().getCite()[i] != null && getJoueur().getCite()[i].getType() == "COMMERCANT"){
                    nbQuartiersCommercants++;
                }
            }
            getJoueur().ajouterPieces(nbQuartiersCommercants);
            System.out.println(nbQuartiersCommercants + " pièces supplémentaires ont été ajoutées au tresor de " + getJoueur().getNom());
        }
        
    }    
}
