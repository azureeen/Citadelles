package modele;

import java.util.Random;
import Controleur.Interaction;

public class Sorcier extends Personnage {
    
    public Sorcier(){
        super("Sorcier", 3, Caracteristiques.SORCIER);
    }
    
    public void utiliserPouvoir(){

        for (int i=0; i<this.getPlateau().getNombreJoueurs(); i++){
            Joueur joueur = this.getPlateau().getJoueur(i);
            if(joueur.getPersonnage().getRang() != 3){
                System.out.println((i+1)+". "+ joueur.getNom());
            }else{}  
        }  

        System.out.println("Veuillez sélectionner le Joueur dont vous voulez voir la main : (0 pour ne rien faire) " );
            
        int joueurVoirMain = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs()) - 1;

        if(joueurVoirMain != -1 && this.getPlateau().getJoueur(joueurVoirMain).getMain() != null ){

            for(int k = 0; k < this.getPlateau().getJoueur(joueurVoirMain).getMain().size(); k++){
                System.out.println((k+1) + ". " + this.getPlateau().getJoueur(joueurVoirMain).getMain().get(k).getNom() + " (" + this.getPlateau().getJoueur(joueurVoirMain).getMain().get(k).getCout() + " piece(s))");
            }

            System.out.println("Quel quartier souhaitez-vous choisir ?");

            if(this.getPlateau().getJoueur(joueurVoirMain).nbQuartiersReelDansMain()>0){

                int quartierAPrendre = Interaction.lireUnEntier(1, this.getPlateau().getJoueur(joueurVoirMain).getMain().size()) - 1;

                Quartier quartierC2 = this.getPlateau().getJoueur(joueurVoirMain).getMain().get(quartierAPrendre);

                this.getPlateau().getJoueur(joueurVoirMain).getMain().remove(quartierAPrendre);

                if(this.getJoueur().nbPieces() < quartierC2.getCout()){
                    this.getJoueur().ajouterQuartierDansMain(quartierC2);
                    System.out.println("Vous n'avez pas assez d'argent pour bâtir ce quartier, il est ajouté à votre main.");
                }

                else{
                    System.out.println("Le quartier coûte : "+ quartierC2.getCout() +" piece(s) vous avez "+ this.getJoueur().nbPieces() +" piece(s) d'or, voulez-vous le bâtir ?");

                    Boolean construire = Interaction.lireOuiOuNon();

                    if(construire){
                        this.getJoueur().retirerPieces(quartierC2.getCout());
                        this.getJoueur().ajouterQuartierDansCite(quartierC2); 
                    }
                    else{
                        this.getJoueur().ajouterQuartierDansMain(quartierC2);
                        System.out.println("Le quartier va dans votre main"); 
                    }   
                }
            }else{
                System.out.println("Vous n'avez pas de quartier dans la main");
            }
            


        }
        
    }

    public void utiliserPouvoirAvatar(){

        Random generateur = new Random();

        for (int i=0; i<this.getPlateau().getNombreJoueurs(); i++){
            Joueur joueur = this.getPlateau().getJoueur(i);
            if(joueur.getPersonnage().getRang() != 3){
                System.out.println((i+1)+". "+ joueur.getNom());
            }else{}  
        }  

        System.out.println("Veuillez sélectionner le Joueur dont vous voulez voir la main : (0 pour ne rien faire) " );
            
        int joueurVoirMain = generateur.nextInt(this.getPlateau().getNombreJoueurs() + 1) - 1;

        if(joueurVoirMain != -1 && this.getPlateau().getJoueur(joueurVoirMain).getMain() != null ){

            for(int k = 0; k < this.getPlateau().getJoueur(joueurVoirMain).getMain().size(); k++){
                System.out.println((k+1) + ". " + this.getPlateau().getJoueur(joueurVoirMain).getMain().get(k).getNom() + " (" + this.getPlateau().getJoueur(joueurVoirMain).getMain().get(k).getCout() + " piece(s))");
            }

            System.out.println("Quel quartier souhaitez-vous choisir ?");
            if(this.getPlateau().getJoueur(joueurVoirMain).nbQuartiersReelDansMain()>0){
                int quartierAPrendre = generateur.nextInt(this.getPlateau().getJoueur(joueurVoirMain).getMain().size());

                Quartier quartierC2 = this.getPlateau().getJoueur(joueurVoirMain).getMain().get(quartierAPrendre);

                this.getPlateau().getJoueur(joueurVoirMain).getMain().remove(quartierAPrendre);

                if(this.getJoueur().nbPieces() < quartierC2.getCout()){
                    this.getJoueur().ajouterQuartierDansMain(quartierC2);
                    System.out.println("Vous n'avez pas assez d'argent pour bâtir ce quartier, il est ajouté à votre main.");
                }

                else{
                    System.out.println("Le quartier coûte : "+ quartierC2.getCout() +" piece(s) vous avez "+ this.getJoueur().nbPieces() +" piece(s) d'or, voulez-vous le bâtir ?");

                    Boolean construire = generateur.nextBoolean();

                    if(construire){
                        this.getJoueur().retirerPieces(quartierC2.getCout());
                        this.getJoueur().ajouterQuartierDansCite(quartierC2); 
                    }
                    else{
                        this.getJoueur().ajouterQuartierDansMain(quartierC2);
                        System.out.println("Le quartier va dans votre main"); 
                    }   
                }
            }else{
                System.out.println("Vous n'avez pas de quartier dans la main");
            }
            


        }
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
                            
