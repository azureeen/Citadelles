package modele;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Controleur.Interaction;
import Exception.SelfChosen;
import Exception.WrongInput;

public class Assassin extends Personnage {

    public Assassin(){
        super("Assassin", 1, Caracteristiques.ASSASSIN); 
    }

    @Override
    public void utiliserPouvoir() {
        boolean continu = true;
        System.out.println("Quel personnage voulez-vous assassiner ?");
        for(int i = 0; i<this.getPlateau().getNombrePersonnages(); i++){
            System.out.println((i+1) + "." + this.getPlateau().getPersonnage(i).getNom());
        }
        do{
            System.out.println("Votre choix : ");
            try{
                int j = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages());
                int k = j-1;
                if(this.getPlateau().getPersonnage(k).equals(this)){
                    throw new SelfChosen();
                }
                this.getPlateau().getPersonnage(k).setAssassine();
                System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été assassine.");
                continu = false;
            }catch(SelfChosen e){
                System.out.println("Vous ne pouvez pas vous assassiner");
            }
        }while(continu);   
    }

    @Override
    public void utiliserPouvoirAvatar() {
        boolean continu = true;
        System.out.println("Quel personnage voulez-vous assassiner ?");
        for(int i = 0; i<this.getPlateau().getNombrePersonnages(); i++){
            System.out.println((i+1) + "." + this.getPlateau().getPersonnage(i).getNom());
        }
        do{
            System.out.println("Choix de " + this.getJoueur().getNom() + " :");
            try{
                int j = ThreadLocalRandom.current().nextInt(-1, this.getPlateau().getNombreJoueurs()) + 1;
                int k = j-1;
                if(!(this.getPlateau().getPersonnage(k) == null) && this.getPlateau().getPersonnage(k).equals(this)){
                    throw new SelfChosen();
                }else if(this.getPlateau().getPersonnage(k) == null){
                    throw new WrongInput();
                }
                this.getPlateau().getPersonnage(k).setAssassine();
                System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été assassine.");
                continu = false;
            } catch(SelfChosen e){}
            catch(WrongInput e){
            }
        }while(continu);
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
=======
import controleur.Interaction;


public class Assassin extends Personnage{

    /**
     * Constructeur Assassin
     */
    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
    }

    
    /**
     * Utiliser le pouvoir de l'Assassin
     */
    @Override
    public void utiliserPouvoir() {
        if(!getAssassine()){
            Personnage[] listePersonnages = this.getPlateau().getListPersonnages();

            System.out.println("Quel personnage voulez-vous assassiner ?");
            
            int nombreDePersonnageIndex=0;
            for (Personnage personnage : listePersonnages) {
                
                if(listePersonnages[nombreDePersonnageIndex] != null) {
                    System.out.println("    " + String.valueOf(nombreDePersonnageIndex+1) + " " + personnage.getNom());
                    nombreDePersonnageIndex++;
                }
            }

            int personnageAssassine=0;
            boolean seSuicide = false;

            do {
                System.out.print("Votre choix : ");
                personnageAssassine = Interaction.lireUnEntier();

                if(listePersonnages[personnageAssassine-1] == this) {
                    System.out.println("Vous ne pouvez pas vous assassiner");
                    seSuicide = true;
                } else seSuicide = false;
            } while((personnageAssassine < 1 || personnageAssassine > nombreDePersonnageIndex+1) || seSuicide == true);
            listePersonnages[personnageAssassine-1].setAssassine();
        }
    }
}
