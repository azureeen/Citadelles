package modele;

public class Eveque extends Personnage {
    public Eveque(){
        super("Eveque", 5, Caracteristiques.EVEQUE); // protégé par des pouvoirs des personnages rang 8 ? 
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){
            System.out.println("Votre personnage a été assassiné");
        }else{
            for(int i = 0; i < this.getJoueur().getCite().length; i++){
                if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[0])){
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
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
