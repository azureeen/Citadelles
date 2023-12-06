package modele;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Controleur.Interaction;
import Exception.CantChoose;
import Exception.NePeutPasDetruire;
import Exception.TresorInsuffisant;
import Exception.WrongInput;
import application.Configuration;
import application.Jeu;

public class Condottiere extends Personnage {
    public Condottiere(){
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }
    
    @Override
    public void percevoirRessourcesSpecifiques() {
        // TODO Auto-generated method stub    
        if(this.getAssassine()){
            super.percevoirRessourcesSpecifiques();
        }else{
            for(int i = 0; i < this.getJoueur().nbQuartiersReelDansCite(); i++){
                if(this.getJoueur().getCite()[i]!=null){
                    if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[1])){
                        this.getJoueur().ajouterPieces(1);
                    }
                }
                
            }
        }
      
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){

            System.out.println("Votre personnage a ete assassine");

        }else{

            Interaction int3 = new Interaction(); 
            System.out.println("Voulez-vous utiliser votre pouvoir de destruction ? (o/n) ");
            boolean reponse  = int3.lireOuiOuNon();

            if(reponse){
                System.out.println("Voici la liste des joueurs et le contenu de leur cite : ");
                for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){

                    System.out.println((i+1) + " " + this.getPlateau().getJoueur(i).getNom() + ": ");

                    for(int j =0; j< this.getPlateau().getJoueur(i).nbQuartiersReelDansCite(); j++){
                        if(this.getPlateau().getJoueur(i).getCite()[j]!=null){
                            System.out.println("    " + (j+1) + "."+ this.getPlateau().getJoueur(i).getCite()[j].getNom() + " (" + (this.getPlateau().getJoueur(i).getCite()[j].getCout() -1) + "pieces" + ")," );
                                                
                        }
                        
                    }

                }

                System.out.println("Pour information, vous avez " + this.getJoueur().nbPieces() + " pièce(s) d'or   dans votre trésor");
                boolean continu = true;

                do{

                    try{

                        System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire) ");
                        int i1 = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs());
                        

                        if(!(i1 == 0) && !this.getPlateau().getJoueur(i1-1).getPersonnage().getNom().equals("Eveque")){
                            boolean condition = false;

                            //implémentation de la merveille donjon

                            //implémentation de la merveille Grande Muraille
                            boolean aGrandeMuraille = false;

                            //-----------------------------------------------------------------------//

                            if(this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite()==8){
                                throw new NePeutPasDetruire();
                            }
                            //-----------------------------------------------------------------------//

                            for(int i = 0; i < this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite();i++){
                                if(this.getPlateau().getJoueur(i1-1).getCite()[i].getCout() - 1 <= this.getJoueur().nbPieces() && !this.getPlateau().getJoueur(i1-1).getCite()[i].equals(Configuration.donjon) && !this.getPlateau().getJoueur(i1-1).getCite()[i].equals(Configuration.donjon)){
                                    condition = true;
                                }else if(this.getPlateau().getJoueur(i1-1).getCite()[i].equals(Configuration.grandeMuraille)){
                                    for(int j = 0; j < this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite(); j++){
                                        if(this.getPlateau().getJoueur(i1-1).getCite()[i].getCout()+1 < this.getJoueur().nbPieces()){
                                            condition = true;
                                            aGrandeMuraille = true;
                                        }
                                    }
                                }
                            }

                        //-----------------------------------------------------------------------//

    
                            if(!condition){
                                throw new CantChoose();
                            }
    
                            Interaction int2 = new Interaction();
                            boolean continu2 = true;
                            do{
                                try{
                                    System.out.println("Quel quartier choisissez-vous ?");
                                    int i2 = int2.lireUnEntier(1, this.getPlateau().getJoueur((i1 - 1)).nbQuartiersReelDansCite());

                                    if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout() - 1  > this.getJoueur().nbPieces()){
                                        throw new TresorInsuffisant();
                                    }
                                    
                                    //-----------------------------------------------------------------------//


                                    else if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.donjon)){
                                        throw new NePeutPasDetruire();
                                    }

                                    //-----------------------------------------------------------------------//


                                    else if (aGrandeMuraille){
                                        System.out.println("Vous avez choisi un joueur possedant la merveille grande muraille, les quartiers que vous essayerez de détruire auront un cout d'une piece en plus.\n");
                                        
                                        if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout() > this.getJoueur().nbPieces()){
                                            throw new TresorInsuffisant();
                                        }

                                        //-----------------------------------------------------------------------//


                                        if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.musee)){
                                            int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                            this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());
    
                                            for(int j = 0; j < Jeu.quartiersSousMusee.size(); j++){
                                                this.getPlateau().getPioche().ajouter(Jeu.quartiersSousMusee.get(j));
                                            }
    
                                            Jeu.quartiersSousMusee.clear();
    
                                            System.out.println("Quartier correctement détruit");
                                            this.getJoueur().retirerPieces(copie-1);
                                            System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                        } 

                                        //-----------------------------------------------------------------------//


                                        else{
                                            int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                            this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());
                                            System.out.println("Quartier correctement détruit");
                                            this.getJoueur().retirerPieces(copie);
                                            System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                        }

                                        
                                    }

                                    //-----------------------------------------------------------------------//


                                    else if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.musee)){
                                        int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                        this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());

                                        for(int j = 0; j < Jeu.quartiersSousMusee.size(); j++){
                                            this.getPlateau().getPioche().ajouter(Jeu.quartiersSousMusee.get(j));
                                        }

                                        Jeu.quartiersSousMusee.clear();

                                        System.out.println("Quartier correctement détruit");
                                        this.getJoueur().retirerPieces(copie-1);
                                        System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                    }

                                    //-----------------------------------------------------------------------//


                                    else{
                                        int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                        this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());
                                        System.out.println("Quartier correctement détruit");
                                        this.getJoueur().retirerPieces(copie-1);
                                        System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                    }

                                    continu2 = false;
                                }catch(TresorInsuffisant e){
                                    System.out.println("Votre trésor n'est pas suffisant");
                                    
                                }catch(NePeutPasDetruire e){
                                    System.out.println("Vous ne pouvez pas détruire ce quartier");
                                }
                               
                            }while(continu2);
                            if(this.getPlateau().getJoueur(i1-1).getPersonnage().getNom().equals("Eveque")){
                                throw new CantChoose();
                            }
                        }else{
                            //ne pas utiliser le pouvoir
                            System.out.println("ne rien faire");
                        }

                        
                        
                        continu = false;
                    }catch(CantChoose e){
                        System.out.println("Tous les quartiers de ce joueur sont au-dessus de vos moyens, vous ne pouvez pas le choisir (ou vous avez choisi l'Eveque).");
                    }catch(NePeutPasDetruire e){
                        System.out.println("Le joueur a déjà 8 quartier, vous ne pouvez pas détruire de quartier.");
                    }
                }while(continu);
              

        }
       
           




        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){
            System.out.println("Le personnage de " + this.getJoueur().getNom() + " est assassine.");
        }else{
            System.out.println("Voulez-vous utiliser votre pouvoir de destruction ? (o/n) ");
            boolean verite[] = {true, false};
            int random = ThreadLocalRandom.current().nextInt(-1, 1)+1;
            boolean reponse  = verite[random];
            if(reponse){
                System.out.println("Voici la liste des joueurs et le contenu de leur cite : ");
                for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){
                    System.out.println((i+1) + " " + this.getPlateau().getJoueur(i).getNom() + ": ");
                    for(int j =0; j< this.getPlateau().getJoueur(i).nbQuartiersReelDansCite(); j++){
                        if(this.getPlateau().getJoueur(i).getCite()[j]!=null){
                            System.out.println("    " + (j+1) + "."+ this.getPlateau().getJoueur(i).getCite()[j].getNom() + " (" + (this.getPlateau().getJoueur(i).getCite()[j].getCout() -1) + "pieces" + ")," );
                                                
                        }
                        
                    }
                }
                System.out.println("Pour information, vous avez " + this.getJoueur().nbPieces() + " pièce(s) d'or   dans votre trésor");
                boolean continu = true;
                do{
                    try{
                        System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire) ");
                        Random random3 = new Random();
                        int i1 = random3.nextInt(this.getPlateau().getNombreJoueurs());
                        
                        if(!(i1 == 0)&& !this.getPlateau().getJoueur(i1-1).getPersonnage().getNom().equals("Eveque")){
                            boolean condition = false;

                            //implémentation de la merveille donjon

                            Quartier donjon = new Quartier("Donjon", "MERVEILLE", 3, "Le Donjon ne peut etre affecte par les pouvoirs des personnages de rang 8.");

                             //implémentation de la merveille Grande Muraille
                             boolean aGrandeMuraille = false;

                            //-----------------------------------------------------------------------//

                            if(this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite()==8){
                                throw new NePeutPasDetruire();
                            }
                            //-----------------------------------------------------------------------//

 
                             for(int i = 0; i < this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite();i++){
                                 if(this.getPlateau().getJoueur(i1-1).getCite()[i].getCout() - 1 <= this.getJoueur().nbPieces() && !this.getPlateau().getJoueur(i1-1).getCite()[i].equals(Configuration.donjon) && !this.getPlateau().getJoueur(i1-1).getCite()[i].equals(donjon)){
                                     condition = true;
                                 }else if(this.getPlateau().getJoueur(i1-1).getCite()[i].equals(Configuration.grandeMuraille)){
                                     for(int j = 0; j < this.getPlateau().getJoueur(i1-1).nbQuartiersReelDansCite(); j++){
                                         if(this.getPlateau().getJoueur(i1-1).getCite()[i].getCout()+1 < this.getJoueur().nbPieces()){
                                             condition = true;
                                             aGrandeMuraille = true;
                                         }
                                     }
                                 }
                             }

                            //-----------------------------------------------------------------------//

    
                            if(!condition){
                                throw new CantChoose();
                            }
                            boolean continu2 = true;
                            do{
                                try{
                                    System.out.println("Quel quartier choisissez-vous ?");
                                    int i2 = ThreadLocalRandom.current().nextInt(0, this.getPlateau().getJoueur((i1 - 1)).nbQuartiersReelDansCite()) +1;
                                    if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout() - 1  > this.getJoueur().nbPieces()){
                                        throw new TresorInsuffisant();
                                    }
                                    
                                        
                                    //-----------------------------------------------------------------------//

                                    else if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.donjon)){
                                        throw new NePeutPasDetruire();
                                    }

                                    //-----------------------------------------------------------------------//


                                    else if (aGrandeMuraille){
                                        System.out.println("Vous avez choisi un joueur possedant la merveille grande muraille, les quartiers que vous essayerez de détruire auront un cout d'une piece en plus.\n");
                                        if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout() > this.getJoueur().nbPieces()){
                                            throw new TresorInsuffisant();
                                        }

                                        //-----------------------------------------------------------------------//

                                        else if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.musee)){
                                            int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                            this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());

                                            for(int j = 0; j < Jeu.quartiersSousMusee.size(); j++){
                                                this.getPlateau().getPioche().ajouter(Jeu.quartiersSousMusee.get(j));
                                            }

                                            Jeu.quartiersSousMusee.clear();

                                            System.out.println("Quartier correctement détruit");
                                            this.getJoueur().retirerPieces(copie-1);
                                            System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                        }

                                        //-----------------------------------------------------------------------//


                                        else{
                                            int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                            this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());
                                            System.out.println("Quartier correctement détruit");
                                            this.getJoueur().retirerPieces(copie);
                                            System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");

                                        }
                                        
                                    }

                                    //-----------------------------------------------------------------------//


                                    
                                    else if(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].equals(Configuration.musee)){
                                        int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                        this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());

                                        for(int j = 0; j < Jeu.quartiersSousMusee.size(); j++){
                                            this.getPlateau().getPioche().ajouter(Jeu.quartiersSousMusee.get(j));
                                        }

                                        Jeu.quartiersSousMusee.clear();

                                        System.out.println("Quartier correctement détruit");
                                        this.getJoueur().retirerPieces(copie-1);
                                        System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                    }

                                    //-----------------------------------------------------------------------//

                                    
                                    else{
                                        int copie = this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getCout();
                                        this.getPlateau().getPioche().ajouter(this.getPlateau().getJoueur(i1-1).getCite()[i2-1]);
                                        this.getPlateau().getJoueur(i1-1).retirerQuartierDansCite(this.getPlateau().getJoueur(i1-1).getCite()[i2-1].getNom());
                                        System.out.println("Quartier correctement détruit");
                                        this.getJoueur().retirerPieces(copie-1);
                                        System.out.println("Pour information, votre trésor est constitue de " + this.getJoueur().nbPieces() + " pièce(s) d'or");
                                    }

                                    //-----------------------------------------------------------------------//

                                    continu2 = false;
                                }catch(TresorInsuffisant e){
                                    System.out.println("Votre trésor n'est pas suffisant");
                                }
                               
                            }while(continu2);
                            if(this.getPlateau().getJoueur(i1-1).getPersonnage().getNom().equals("Eveque")){
                                throw new CantChoose();
                            }
                        }else{
                            //ne pas utiliser le pouvoir
                            System.out.println("ne rien faire");
                        }
                        
                        
                        continu = false;
                    }catch(CantChoose e){
                        System.out.println("Tous les quartiers de ce joueur sont au-dessus de vos moyens, vous ne pouvez pas le choisir (ou vous avez choisi l'Eveque).");
                    }
                    
                    catch(NePeutPasDetruire e){
                        System.out.println("Vous ne pouvez pas détruire ce quartier");
                    }

                }while(continu);
              

        }
       
           




        }
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
