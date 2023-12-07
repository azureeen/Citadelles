package modele;

import java.util.ArrayList;

import Exception.CantChoose;
import Exception.SelfChosen;
import Controleur.Interaction;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Capitaine extends Personnage{
	/**
	 * Constructeur Capitaine
	 */
	public Capitaine() {
		super("Capitaine", 8, Caracteristiques.CAPITAINE);
	}

	/**
	 *
	 */
	public void percevoirRessourcesSpecifiques() {
		if(this.getAssassine()){
			super.percevoirRessourcesSpecifiques();
		}else{
			for(int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++){
				if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[1])){
					this.getJoueur().ajouterPieces(1);
				}
			}
		}

	}


	@Override
	public void utiliserPouvoir() {
		System.out.println("Vous pouvez choisir un quartier dans la cite d'un joueur avec un cout inferieur a 3");
		System.out.println("Vous pouvez l'ajouter a votre cite en payant le cout de construction a  son proprietaire");
		//implémentation de la merveille donjon
		//implémentation de la merveille Grande Muraille
        boolean aGrandeMuraille = false;
        int prixEnPlus = 0;
		boolean repeter = false;
		do{
			try {

				System.out.println("Veuillez choisir le joueur dont vous voulez prendre une carte (0 pour ne rien faire)");


				for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){
					System.out.println((i+1) + "." + this.getPlateau().getJoueur(i).getNom() + " : " + this.getPlateau().getJoueur(i).nbQuartiersDansCite() + " carte(s).");
				}
				
				int choix  = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs()) - 1;
				
				if(choix != -1){
					if(this.getPlateau().getPersonnage(choix).getNom().equals(this.getNom())){
					throw new SelfChosen();
					}else if(this.getPlateau().getPersonnage(choix).getNom().equals("Eveque")) {
						throw new CantChoose();
					}
					
					Quartier[] citeJoueur = this.getPlateau().getJoueur(choix).getCite();
					
					for(int i=0; i<this.getPlateau().getJoueur(choix).nbQuartiersDansCite(); i++) {
						if(citeJoueur[i].getNom().equals("Grande Muraille")) {
							aGrandeMuraille = true;
						}
					}
					
					if(aGrandeMuraille) {
						System.out.println("Vous avez choisi un joueur possedant la merveille grande muraille, les quartiers que vous essayerez de détruire auront un cout d'une piece en plus.\n");
						prixEnPlus = 1;
					}
					
					

					ArrayList<Quartier> copie = new ArrayList<Quartier>();
					for(int i=0; i<this.getPlateau().getJoueur(choix).nbQuartiersDansCite(); i++) {
						if(citeJoueur[i].getCoutConstruction()<=3 && !this.getJoueur().quartierPresentDansCite(citeJoueur[i].getNom())) {
							copie.add(citeJoueur[i]);
						}
					}
					if(copie.isEmpty()) {
						System.out.println("Ce joueur n'a pas de quartier rentrant dans les crit�res de votre pouvoir");
						repeter = true;
					}else {

						boolean continu = false;
						do{
							for(int i =0; i<copie.size(); i++) {
								System.out.println((i+1) + " - " + copie.get(i).getNom() + "[ " + copie.get(i).getType() + ", " + copie.get(i).getCoutConstruction() + " ]");
							}
			
							System.out.println("Veuillez choisir le quartier a prendre (0 pour ne rien faire)");
			
							int choix1 = Interaction.lireUnEntier(1, copie.size()) -1;
			
							if(choix1 != -1){
								if(this.getPlateau().getJoueur(choix).getCite()[choix1].getNom().equals("Donjon")){
									System.out.println("Vous ne pouvez pas choisir le Donjon");
									continu = true;
			
								}else{
									System.out.println("Voulez vous construire " + this.getPlateau().getJoueur(choix).getCite()[choix1].getNom() + " ?");
									boolean choixConstruct = Interaction.lireOuiOuNon();
									if(choixConstruct) {
										if(this.getJoueur().nbPieces()>= this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction()) {
											this.getJoueur().ajouterQuartierDansCite(this.getPlateau().getJoueur(choix).getCite()[choix1]);
											this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction() + prixEnPlus);
											this.getPlateau().getJoueur(choix).ajouterPieces(this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction() + prixEnPlus);
											this.getPlateau().getJoueur(choix).retirerQuartierDansCite(this.getPlateau().getJoueur(choix).getCite()[choix1].getNom());
											
											continu = false;
										}else {
											System.out.println("Votre tresor n'est pas suffisant pour construire ce quartier");
											continu = true;
										}
									}
								}
								
							}else{
								continu = false;
								repeter = false;
							}
						}while(continu);

						repeter = false;
					}
				
					
					
				}else{
					repeter = false;
						
				}

			}catch(SelfChosen e){
				System.out.println("Vous ne pouvez pas vous choisir. ");
				repeter = true;
			}catch(CantChoose e){
				System.out.println("Vous ne pouvez pas choisir l'Eveque");
				repeter = true;
			}
		}while(repeter);


	}

	@Override
	public void utiliserPouvoirAvatar() {

		//implémentation de la merveille donjon
		//implémentation de la merveille Grande Muraille
        boolean aGrandeMuraille = false;
        int prixEnPlus = 0;

		
		Random generateur = new Random();
		System.out.println("Vous pouvez choisir un quartier dans la cit� d'un joueur avec un cout inf�rieur � 3");
		System.out.println("Vous pouvez l'ajouter � votre cit� en payant le co�t de construction �  son propri�taire");
		System.out.println("Veuillez choisir le joueur dont vous voulez prendre une carte");

		boolean repeter = false;
		do{
			try {

				for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){
					System.out.println((i+1) + "." + this.getPlateau().getJoueur(i).getNom() + " : " + this.getPlateau().getJoueur(i).nbQuartiersDansCite() + " carte(s).");
				}
				int choix = ThreadLocalRandom.current().nextInt(0,this.getPlateau().getNombreJoueurs()+1) - 1;

				if(choix != -1){
					if(this.getPlateau().getPersonnage(choix).getNom().equals(this.getNom())){
					throw new SelfChosen();
					}else if(this.getPlateau().getPersonnage(choix).getNom().equals("Eveque")) {
						throw new CantChoose();
					}
					Quartier[] citeJoueur = this.getPlateau().getJoueur(choix).getCite();

					for(int i=0; i<this.getPlateau().getJoueur(choix).nbQuartiersDansCite(); i++) {
						if(citeJoueur[i].getNom().equals("Grande Muraille")) {
							aGrandeMuraille = true;
						}
					}
					
					if(aGrandeMuraille) {
						System.out.println("Vous avez choisi un joueur possedant la merveille grande muraille, les quartiers que vous essayerez de deplacer auront un cout d'une piece en plus.\n");
						prixEnPlus = 1;
					}
					

					ArrayList<Quartier> copie = new ArrayList<Quartier>();
					for(int i=0; i<this.getPlateau().getJoueur(choix).nbQuartiersDansCite(); i++) {
						if(citeJoueur[i].getCoutConstruction()<=3 && !this.getJoueur().quartierPresentDansCite(citeJoueur[i].getNom())) {
							copie.add(citeJoueur[i]);
						}
					}
					if(copie.isEmpty()) {
						System.out.println("Ce joueur n'a pas de quartier rentrant dans les crit�res de votre pouvoir");
						repeter = true;
					}else {
						boolean continu = false;
						do{
							for(int i =0; i<copie.size(); i++) {
								System.out.println((i+1) + " - " + copie.get(i).getNom() + "[ " + copie.get(i).getType() + ", " + copie.get(i).getCoutConstruction() + " ]");
							}
			
							System.out.println("Veuillez choisir le quartier a prendre (0 pour ne rien faire)");
			
							int choix1 = generateur.nextInt(copie.size()+1)-1;
			
							if(choix1 != -1){
								if(this.getPlateau().getJoueur(choix).getCite()[choix1].getNom().equals("Donjon")){
									System.out.println("Vous ne pouvez pas choisir le Donjon");
									continu = true;
			
								}else{
									System.out.println("Voulez vous construire " + this.getPlateau().getJoueur(choix).getCite()[choix1].getNom() + " ?");
									boolean choixConstruct = generateur.nextBoolean();
									if(choixConstruct) {
										if(this.getJoueur().nbPieces()>= this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction()) {
											this.getJoueur().ajouterQuartierDansCite(this.getPlateau().getJoueur(choix).getCite()[choix1]);
											this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction() + prixEnPlus);
											this.getPlateau().getJoueur(choix).ajouterPieces(this.getPlateau().getJoueur(choix).getCite()[choix1].getCoutConstruction() + prixEnPlus);
											this.getPlateau().getJoueur(choix).retirerQuartierDansCite(this.getPlateau().getJoueur(choix).getCite()[choix1].getNom());
											
											continu = false;
										}else {
											System.out.println("Votre t�sor n'est pas suffisant pour construire ce quartier");
											continu = true;
										}
									}
								}
								
							}else{
								continu = false;
								repeter = false;
			
							}
						}while(continu);

						repeter = false;
					}
				
				
				}else{
					repeter = false;
				}

			}catch(SelfChosen e){
				System.out.println("Vous ne pouvez pas vous choisir. ");
				repeter = true;
			}catch(CantChoose e){
				System.out.println("Vous ne pouvez pas choisir l'Eveque.");
				repeter = true;
			}


		}while(repeter);
		
	}

	@Override
	public void activationPouvoirSorciere(Joueur joueurSorciere) {
		// TODO Auto-generated method stub
		
	}



}
