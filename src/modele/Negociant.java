package modele;

public class Negociant extends Personnage{

	public Negociant() {
		super("Negociant", 6, Caracteristiques.NEGOCIANT);
		// TODO Auto-generated constructor stub
	}

    public void percevoirRessourcesSpecifiques() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){
            super.percevoirRessourcesSpecifiques();
        }else{
            for(int i = 0; i < this.getJoueur().nbQuartiersReelDansCite(); i++){
                if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[3])){
                    this.getJoueur().ajouterPieces(1);
                }
            }
        }
      
    }
	
	
	public void utiliserPouvoir() {
		// TODO Auto-generated method stub
		
	}


	public void utiliserPouvoirAvatar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activationPouvoirSorciere(Joueur joueurSorciere) {
		// TODO Auto-generated method stub
		
	}

	
	
}
