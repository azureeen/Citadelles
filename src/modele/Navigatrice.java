package modele;

import java.util.Random;

import Controleur.Interaction;

public class Navigatrice extends Personnage {

    public Navigatrice() {
        super("Navigatrice", 7, Caracteristiques.NAVIGATRICE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        System.out.println("Voici votre trésor: "+this.getJoueur().nbPieces());
        System.out.println("Voulez-vous recevoir 4 pièces ou 4 cartes (1 pour les pieces, 2 pour les cartes?");
        int choix = Interaction.lireUnEntier(1, 2);
        if(choix == 1){
            this.getJoueur().ajouterPieces(4);
            System.out.println("Vous recevez 4 pièces, voici votre trésor: "+this.getJoueur().nbPieces());
        }else{
            System.out.println("Voici les cartes piochées: \n");
            for(int i=0; i<4; i++){
                Quartier piocheJoueur = this.getPlateau().getPioche().piocher();
                System.out.println((i+1)+". Nom: "+piocheJoueur.getNom()+"\ntype: "+piocheJoueur.getType()+"\ncout: "+piocheJoueur.getCout()+"\n");
                this.getJoueur().ajouterQuartierDansMain(piocheJoueur);
            }
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        System.out.println("Voulez-vous recevoir 4 pièces ou 4 cartes (0 pour les pieces, 1 pour les cartes)?");
        Random random = new Random();
        int choix = random.nextInt(2);
        if(choix == 0){
            this.getJoueur().ajouterPieces(4);
            System.out.println("Vous recevez 4 pièces");
        }else{
            System.out.println("4 cartes ont été piochées. \n");
            for(int i=0; i<4; i++){
                Quartier piocheJoueur = this.getPlateau().getPioche().piocher();
                this.getJoueur().ajouterQuartierDansMain(piocheJoueur);
            }
        }
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
    
}
