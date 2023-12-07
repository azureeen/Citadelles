package modele;

public class Reine extends Personnage{

	public Reine() {
		super("Reine", 9, Caracteristiques.REINE);
	}

	public void utiliserPouvoir() {
		Joueur[] listeJ = new Joueur[this.getPlateau().getNombreJoueurs()];
		int monId = 0;
		for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
			listeJ[i] = this.getPlateau().getJoueur(i);
			if(this.getPlateau().getJoueur(i).getNom().equals(this.getJoueur().getNom())){
				monId = i;
			}
		}
		if(monId == 0) {
			if(listeJ[this.getPlateau().getNombreJoueurs()-1].getPersonnage().getRang() == 4 && !listeJ[this.getPlateau().getNombreJoueurs() - 1].getPersonnage().getAssassine()
					|| listeJ[monId+1].getPersonnage().getRang() == 4&& !listeJ[monId + 1].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
			}
		}else if(monId == this.getPlateau().getNombreJoueurs()-1){
			if(listeJ[monId-1].getPersonnage().getRang() == 4 && !listeJ[monId - 1].getPersonnage().getAssassine()
					|| listeJ[0].getPersonnage().getRang() == 4 && !listeJ[0].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
			}
		}else {
			if(listeJ[monId-1].getPersonnage().getRang() == 4 && !listeJ[monId - 1].getPersonnage().getAssassine() ||
					listeJ[monId+1].getPersonnage().getRang() == 4 && !listeJ[monId + 1].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
			}
		}
		
	}

	@Override
	public void utiliserPouvoirAvatar() {
		// TODO Auto-generated method stub
		Joueur[] listeJ = new Joueur[this.getPlateau().getNombreJoueurs()];
		int monId = 0;
		for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++) {
			listeJ[i] = this.getPlateau().getJoueur(i);
			if(this.getPlateau().getJoueur(i).getNom().equals(this.getJoueur().getNom())){
				monId = i;
			}
		}
		if(monId == 0) {
			if(listeJ[this.getPlateau().getNombreJoueurs()-1].getPersonnage().getRang() == 4 && !listeJ[this.getPlateau().getNombreJoueurs() - 1].getPersonnage().getAssassine()
					|| listeJ[monId+1].getPersonnage().getRang() == 4&& !listeJ[monId + 1].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
				
			}
		}else if(monId == this.getPlateau().getNombreJoueurs()-1){
			if(listeJ[monId-1].getPersonnage().getRang() == 4 && !listeJ[monId - 1].getPersonnage().getAssassine()
					|| listeJ[0].getPersonnage().getRang() == 4 && !listeJ[0].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
			}
		}else {
			if(listeJ[monId-1].getPersonnage().getRang() == 4 && !listeJ[monId - 1].getPersonnage().getAssassine() ||
					listeJ[monId+1].getPersonnage().getRang() == 4 && !listeJ[monId + 1].getPersonnage().getAssassine()) {
					this.getJoueur().ajouterPieces(3);
					System.out.println("3 pi�ces ont �t� ajout�es � votre tr�sor car vous �tes assis � cot� du personnage de rang 4");
			}
		}
	}

	@Override
	public void activationPouvoirSorciere(Joueur joueurSorciere) {
		// TODO Auto-generated method stub
		
	}
	
	

}
