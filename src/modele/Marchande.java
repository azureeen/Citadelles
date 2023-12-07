package modele;

public class Marchande extends Personnage {

    public Marchande(){
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){
            super.percevoirRessourcesSpecifiques();
        }else{
            for(int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++){
                if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[3])){
                    this.getJoueur().ajouterPieces(1);
                }
            }
            if(this.getJoueur().getPossedeEcoleMag()){//la merveille Ecole de Magie change de type a la perception des ressources
                this.getJoueur().ajouterPieces(1);
            }
        }
      
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        if(!this.getAssassine()){
            this.getJoueur().ajouterPieces(1);
        }else{
            System.out.println("Votre personnage a été assassiné");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        if(!this.getAssassine()){
            this.getJoueur().ajouterPieces(1);
        }else{
            System.out.println("Votre personnage a été assassiné");
        }    
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
    
=======

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
