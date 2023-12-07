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
    
}
