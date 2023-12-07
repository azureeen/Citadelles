package modele;

import java.util.Random;

import Controleur.Interaction;

public class Echevin extends Personnage {
    public Echevin(){
        super("Echevin", 1, Caracteristiques.ECHEVIN);
    }
    @Override
    public void utiliserPouvoir(){
        if(this.getJoueur() != null && !this.getAssassine()){
            boolean continu=true;
            System.out.println("Quels personnages voulez-vous cibler ?");

            for (int i=0; i<this.getPlateau().getNombrePersonnages(); i++){
                Personnage personnage = this.getPlateau().getPersonnage(i);

                System.out.println((i+1)+". "+personnage.getNom());
            }
            do {
                System.out.println("A qui attribuez-vous le mandat signé ?");
                int choix1 = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages());

                System.out.println("A qui attribuez-vous un mandat sans effet ?");
                int choix2 = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages());
                int choix3 = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages());

                Personnage persoC1 = this.getPlateau().getPersonnage(choix1-1);
                Personnage persoC2 = this.getPlateau().getPersonnage(choix2-1);
                Personnage persoC3 = this.getPlateau().getPersonnage(choix3-1);
                if((choix1 != choix2 && choix2 != choix3 && choix1 != choix3) && (persoC1.getRang() != 1 && persoC2.getRang() != 1 && persoC3.getRang() !=1)){
                    persoC1.setVraiEchevin(true);
                    persoC2.setFauxEchevin(true);
                    persoC3.setFauxEchevin(true);
                    continu = false;
                }else{
                    System.out.println("Vous ne pouvez pas vous choisir ou plusieurs fois le même personnage");
                }

            } while(continu);
        }
    }

    public void utiliserPouvoirAvatar(){
        if(this.getJoueur() != null && !this.getAssassine()){
            boolean continu=true;
            System.out.println("Quels personnages voulez-vous cibler ?");

            for (int i=0; i<this.getPlateau().getNombrePersonnages(); i++){
                Personnage personnage = this.getPlateau().getPersonnage(i);

                System.out.println((i+1)+". "+personnage.getNom());
            }
            do {
                Random ran = new Random();
                int choix1 = ran.nextInt(this.getPlateau().getNombrePersonnages());

                System.out.println("A qui attribuez-vous un mandat sans effet ?");
                int choix2 = ran.nextInt(this.getPlateau().getNombrePersonnages());
                int choix3 = ran.nextInt(this.getPlateau().getNombrePersonnages());

                Personnage persoC1 = this.getPlateau().getPersonnage(choix1);
                Personnage persoC2 = this.getPlateau().getPersonnage(choix2);
                Personnage persoC3 = this.getPlateau().getPersonnage(choix3);
                if((choix1 != choix2 && choix2 != choix3 && choix1 != choix3) && (persoC1.getRang() != 1 && persoC2.getRang() != 1 && persoC3.getRang() !=1)){
                    persoC1.setVraiEchevin(true);
                    persoC2.setFauxEchevin(true);
                    persoC3.setFauxEchevin(true);
                    continu = false;
                }else{
                    System.out.println("Vous ne pouvez pas vous choisir ou plusieurs fois le même personnage");
                }

            } while(continu);
        }
    }
    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
