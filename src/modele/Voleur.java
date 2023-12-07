package modele;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Exception.RangException;
import Exception.SelfChosen;

public class Voleur extends Personnage {
    public Voleur(){
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        if(this.getAssassine()){
            System.out.println("Votre personnage a été assassiné");
        }else{
            boolean continu = true;
            Scanner sc = new Scanner(System.in);
            System.out.println("Quel personnage voulez-vous voler ?");
            for(int i = 0; i<this.getPlateau().getNombrePersonnages(); i++){
                System.out.println((i+1) + "." + this.getPlateau().getPersonnage(i).getNom());
            }
            do{
                System.out.println("Votre choix : ");
                try{
                    int j = sc.nextInt();
                    int k = j-1;
                    if(this.getPlateau().getPersonnage(k).equals(this)){
                        throw new SelfChosen();
                    }else if(this.getPlateau().getPersonnage(k).getRang() == 1){
                        throw new RangException();
                    }
                    this.getPlateau().getPersonnage(k).setVole();
                    //Gérer les pièces ??
                
                    System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été vole.");
                    continu = false;
                }catch(SelfChosen e){
                    System.out.println("Vous ne pouvez pas vous voler");
                }catch(RangException e){
                    System.out.println("Vous ne pouvez pas choisir un personnage de rang 1");
                }
            }while(continu);      
        }
        
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if(this.getAssassine()){
            System.out.println("Votre personnage a été assassiné");
        }else{
            boolean continu = true;
            System.out.println("Quel personnage voulez-vous voler ?");
            for(int i = 0; i<this.getPlateau().getNombrePersonnages(); i++){
                System.out.println((i+1) + "." + this.getPlateau().getPersonnage(i).getNom());
            }
            do{
                System.out.println("Votre choix : ");
                try{
                    int j = ThreadLocalRandom.current().nextInt(0, this.getPlateau().getNombrePersonnages()) +1;
                    int k = j-1;
                    System.out.println("\n" + k + "\n");
                    if(this.getPlateau().getPersonnage(k).equals(this)){
                        throw new SelfChosen();
                    }else if(this.getPlateau().getPersonnage(k).getRang() == 1){
                        throw new RangException();
                    }
                    this.getPlateau().getPersonnage(k).setVole();
                    //Gérer les pièces ??
                
                    System.out.println(this.getPlateau().getPersonnage(k).getNom() + " a été vole.");
                    continu = false;
                }catch(SelfChosen e){
                    System.out.println("Vous ne pouvez pas vous voler");
                }catch(RangException e){
                    System.out.println("Vous ne pouvez pas choisir un personnage de rang 1");
                }
            }while(continu);      
        }
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }

=======
import controleur.Interaction;

public class Voleur extends Personnage {
    
    /**
     *  Construteur Voleur
     */
    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }

    /**
     * Utiliser le pouvoir du voleur
     */
    @Override
    public void utiliserPouvoir() {
        if(!getAssassine() && getJoueur() != null){
            Personnage[] listePersonnages = this.getPlateau().getListPersonnages();
            
            System.out.println("Quel personnage voulez-vous voler ?");

            int nombreDePersonnageIndex=0;
            for (Personnage personnage : listePersonnages) {
                
                if(listePersonnages[nombreDePersonnageIndex] != null) {
                    System.out.println("    " + String.valueOf(nombreDePersonnageIndex+1) + " " + personnage.getNom());
                    nombreDePersonnageIndex++;
                }
            }
            
            int personnageVoleeIndex=0;
            boolean seSuicide = false;

            do {
                System.out.print("Votre choix : ");
                personnageVoleeIndex = Interaction.lireUnEntier();

                if(listePersonnages[personnageVoleeIndex-1] == this) {
                    System.out.println("Vous ne pouvez pas vous voler");
                    seSuicide = true;
                } else seSuicide = false;

                if (listePersonnages[personnageVoleeIndex-1].getRang() == 1 ) {
                    System.out.println("Vous ne pouvez pas voler un personnage de rang 1");
                }
            } while((personnageVoleeIndex < 1 || personnageVoleeIndex > nombreDePersonnageIndex+1) || seSuicide == true || listePersonnages[personnageVoleeIndex-1].getRang() == 1 );
            listePersonnages[personnageVoleeIndex-1].setVole(this);
        }
    }    
}
