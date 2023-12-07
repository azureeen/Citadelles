package modele;

import java.util.Random;

import Controleur.Interaction;

public class Sorciere extends Personnage{

    public Personnage persoEnsorcele=null;

    public Sorciere() {
        super("Sorciere", 1, Caracteristiques.SORCIERE);
        //TODO Auto-generated constructor stub
    }

    

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        int choix=-1;
        do {
            for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++){
                System.out.println((i+1)+". "+this.getPlateau().getPersonnage(i).getNom());
            }
            System.out.println("Choisissez un personnage à ensorceler (vous ne pouvez pas vous choisir)");
            choix = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages())-1;
            persoEnsorcele = this.getPlateau().getPersonnage(choix);
        } while (persoEnsorcele.equals(this));
        System.out.println("Vous avez choisi: "+persoEnsorcele.getNom()+", votre tour reprendra que ce personnage sera appelé");
        
        persoEnsorcele.setEnsorcele(true);


        
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        int choix=-1;
        
        Random random = new Random();
        do {
            for(int i=0; i<this.getPlateau().getNombrePersonnages(); i++){
                System.out.println((i+1)+". "+this.getPlateau().getPersonnage(i).getNom());
            }
            System.out.println("Choisissez un personnage à ensorceler (vous ne pouvez pas vous choisir)");
            choix = random.nextInt(this.getPlateau().getNombrePersonnages());
            persoEnsorcele = this.getPlateau().getPersonnage(choix);
        } while (persoEnsorcele.equals(this));
        System.out.println("Vous avez choisi: "+persoEnsorcele.getNom()+", votre tour reprendra que ce personnage sera appelé");
        persoEnsorcele.setEnsorcele(true);
    }
    public void activationPouvoirSorciere(Joueur joueurSorciere){
        persoEnsorcele.setJoueur(joueurSorciere);
    }
}
