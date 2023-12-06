package modele;

import java.util.Random;
import Controleur.Interaction;

public class Diplomate extends Personnage {
    public Diplomate(){
        super("Diplomate", 8, Caracteristiques.DIPLOMATE);

    }
    public void percevoirRessourcesSpecifiques(){

    
        if(this.getJoueur() != null && !this.getAssassine()){
            for(int i = 0; i < this.getJoueur().nbQuartiersReelDansCite(); i++){
                if(this.getJoueur().getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[1])){
                    this.getJoueur().ajouterPieces(1);
                }
            }
            if(this.getJoueur().getPossedeEcoleMag()){//la merveille Ecole de Magie change de type a la perception des ressources
                this.getJoueur().ajouterPieces(1);
            }
        }
    }    

    public void utiliserPouvoir() {
        
            boolean continu1=true;
            do{
                
                System.out.println("Voici votre cite: " );
                Joueur joueurDiplo = this.getJoueur();
                for(int i=0; i<joueurDiplo.nbQuartiersReelDansCite(); i++){
                    System.out.println((i+1)+". Nom: "+joueurDiplo.getCite()[i].getNom()+", coût: "+joueurDiplo.getCite()[i].getCout()+", type: "+joueurDiplo.getCite()[i].getType()+".");
                }
                System.out.println("Voulez-vous échanger un de vos quartier bâti avec celui d'un autre joueur ? (oui / o / non / n)" );

                
                boolean oui = Interaction.lireOuiOuNon();
                boolean continu2 = true;
                if(oui && !(this.getJoueur().nbQuartiersReelDansCite()==0)){

                    System.out.println("Choisissez le quartier que vous voulez échanger: ");
                    int indxQuartierDiplo = Interaction.lireUnEntier(1, joueurDiplo.nbQuartiersReelDansCite())-1;
                    Quartier quartierDiplo = joueurDiplo.getCite()[indxQuartierDiplo];
                    System.out.println("Vous avez choisi "+quartierDiplo.getNom());

                    

                    System.out.println("Avec quel joueur souhaitez-vous échanger ce quartier ?");
                    //implémentation Grande Muraille
                    Boolean aGrandeMuraille = false;
                    for (int i=0; i<this.getPlateau().getNombreJoueurs(); i++){
                        Personnage perso = this.getPlateau().getPersonnage(i);
                        Joueur joueur = perso.getJoueur();
                        System.out.println((i+1)+". "+joueur.getNom()); //montrer les personnages avec leurs quartiers
                        Quartier[] cite = joueur.getCite();
                        for (Quartier quartier : cite){
                            System.out.println("   "+"Nom: "+quartier.getNom()+", cout: "+quartier.getCout()+", type: "+quartier.getType());
                        }
                    }
                    do {
                        System.out.print("Votre choix d'adversaire à voler : (0 pour ne rien faire) ");
                        int choix = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs()) - 1;

                        

                        if(choix != -1){
                            if(this.getPlateau().getJoueur(choix).quartierPresentDansCite("Grande Muraille")){
                                aGrandeMuraille = true;
                            }
                            Joueur joueurChoisi = this.getPlateau().getJoueur(choix);
                            Quartier[] citJoueur = joueurChoisi.getCite();
                            if(joueurChoisi.getPersonnage().getRang() == 8){      //si se choisit soit même
                                System.out.println("Vous ne pouvez pas vous choisir.");

                            }else if(joueurChoisi.nbQuartiersReelDansCite() == 0){       // si choisit qqn qui n'a pas de quartiers bâtis
                                System.out.println("La personne choisie n'a pas de quartiers dans sa cité.");

                            }else if(joueurChoisi.nbQuartiersReelDansCite() == 8){
                                System.out.println("La personne choisie a déjà sa cité complete.");
                                
                            }else if(joueurChoisi.getPersonnage().getNom().equals("Eveque")){
                                System.out.println("Vous ne pouvez pas utiliser votre pourvoir sur l'eveque");
                            }
                            else{      // si choisit qqn d'autre
                                System.out.println("Parmis ces quartiers de la cité de"+joueurChoisi.getPersonnage().getNom()+" lequel choisissez-vous ?");
                                
                                int j =0;
                                for (Quartier quartier : citJoueur){  
                                    if(quartier != null){
                                        System.out.println(j+". "+"Nom: "+quartier.getNom()+", cout: "+quartier.getCout()+", type: "+quartier.getType()+".");
                                        j++;
                                    }else{
                                        break;
                                    } 
                                }

                                int indxQuartierAdv = Interaction.lireUnEntier(1, joueurChoisi.nbQuartiersReelDansCite())-1;
                                Quartier quartierAdv = joueurChoisi.getCite()[indxQuartierAdv];
                                boolean presentChezAdv = false;
                                for(int i=0; i<joueurChoisi.nbQuartiersReelDansCite();i++){
                                    if(quartierDiplo.getNom().equals(joueurChoisi.getCite()[i].getNom())){
                                        presentChezAdv = true;
                                    }
                                }

                                boolean presentChezDiplo = false;
                                
                                for(int i=0; i<joueurDiplo.nbQuartiersReelDansCite();i++){
                                    if(quartierAdv.getNom().equals(joueurDiplo.getCite()[i].getNom())){
                                        presentChezDiplo = true;
                                    }
                                }

                                if(!quartierDiplo.getNom().equals(quartierAdv.getNom()) && presentChezAdv){

                                    System.out.println("Quartier déjà présent dans la cité de votre adversaire");

                                }else if(!quartierDiplo.getNom().equals(quartierAdv.getNom()) && presentChezDiplo){

                                    System.out.println("Quartier déjà présent dans votre cité");

                                }else{
                                    
                                    if (this.getJoueur().nbPieces() > (quartierAdv.getCout() - quartierDiplo.getCout() + 1) && aGrandeMuraille){
                                        System.out.println("Vous avez choisi d'échanger avec la Merveille Grande Muraille. Vous payez donc 1 piece de plus");
                                        joueurDiplo.retirerPieces(quartierAdv.getCout() - quartierDiplo.getCout() + 1);

                                        joueurDiplo.retirerQuartierDansCite(quartierDiplo.getNom());
                                        joueurChoisi.retirerQuartierDansCite(quartierAdv.getNom());
                                        joueurDiplo.ajouterQuartierDansCite(quartierAdv);
                                        joueurChoisi.ajouterQuartierDansCite(quartierDiplo);
                                    }

                                    else if(this.getJoueur().nbPieces() > (quartierAdv.getCout() - quartierDiplo.getCout())){
                                        continu2 = false;
                                        continu1=false;
                                        System.out.println("Vous avez échangé "+quartierDiplo.getNom()+" avec "+quartierAdv.getNom());

                                        joueurDiplo.retirerPieces(quartierAdv.getCout() - quartierDiplo.getCout());

                                        joueurDiplo.retirerQuartierDansCite(quartierDiplo.getNom());
                                        joueurChoisi.retirerQuartierDansCite(quartierAdv.getNom());
                                        joueurDiplo.ajouterQuartierDansCite(quartierAdv);
                                        joueurChoisi.ajouterQuartierDansCite(quartierDiplo);



                                    }
                                    
                                    else{
                                        System.out.println("Vous ne pouvez pas échanger ces quartiers. Vous n'avez pas assez de pieces");
                                        continu2 = true;
                                    }

                                    


                                
                                }

                            

                            

                            

                            
                        
                            
                            }
                            
                        }else{
                            continu1 = false;
                            continu2 = false;
                            
                        }
                        
                        
                    
                    

                    }while (continu2);
                }else if(this.getJoueur().nbQuartiersReelDansCite()==0){
                    System.out.println("Vous n'avez pas de quartier à échanger.");
                    continu1 = false;

                }else{
                    System.out.println("Vous decidez de ne pas utiliser votre pouvoir.");
                    continu1 = false;
                }
            }while(continu1);
            //else if(this.getJoueur() == null){
                //System.out.println("Aucun joueur n'est attribué au personnage");
            //} else{
             //   System.out.println("Le personnage est assassiné");
        }
    
    public void utiliserPouvoirAvatar(){
        boolean continu1=true;
            do{
                
                Joueur joueurDiplo = this.getJoueur();
                
                System.out.println("Voulez-vous échanger un de vos quartier bâti avec celui d'un autre joueur ? (oui / o / non / n)" );

                //implémentation Grande Muraille
                Boolean aGrandeMuraille = false;

                Random random = new Random();
                boolean oui = random.nextBoolean();
                boolean continu2 = true;
                if(oui && !(this.getJoueur().nbQuartiersReelDansCite()==0)){

                    System.out.println("Choisissez le quartier que vous voulez échanger: ");
                    int indxQuartierDiplo = random.nextInt(joueurDiplo.nbQuartiersReelDansCite());
                    Quartier quartierDiplo = joueurDiplo.getCite()[indxQuartierDiplo];
                    System.out.println("Vous avez choisi "+quartierDiplo.getNom());

                    

                    System.out.println("Avec quel joueur souhaitez-vous échanger ce quartier ?");
                    
                    do {
                        System.out.print("Votre choix d'adversaire à voler : (0 pour ne rien faire) ");
                        int choix = random.nextInt(this.getPlateau().getNombreJoueurs()+1)-1;

                        

                        if(choix != -1){
                            if(this.getPlateau().getJoueur(choix).quartierPresentDansCite("Grande Muraille")){
                                aGrandeMuraille = true;
                            }
                            Joueur joueurChoisi = this.getPlateau().getJoueur(choix);
                            Quartier[] citJoueur = joueurChoisi.getCite();
                            if(joueurChoisi.getPersonnage().getRang() == 8){      //si se choisit soit même
                                System.out.println("Vous ne pouvez pas vous choisir.");

                            }else if(joueurChoisi.nbQuartiersReelDansCite() == 0){       // si choisit qqn qui n'a pas de quartiers bâtis
                                System.out.println("La personne choisie n'a pas de quartiers dans sa cité.");

                            }else if(joueurChoisi.nbQuartiersReelDansCite() == 8){
                                System.out.println("La personne choisie a déjà sa cité complete.");
                                
                            }else if(joueurChoisi.equals("Eveque")){
                                System.out.println("Vous ne pouvez pas utiliser votre pourvoir sur l'eveque");
                            }
                            else{      // si choisit qqn d'autre
                                System.out.println("Parmis ces quartiers de la cité de"+joueurChoisi.getNom()+" lequel choisissez-vous ?");
                                
                                int j =0;
                                for (Quartier quartier : citJoueur){
                                    if(quartier != null){
                                        System.out.println(j+". "+"Nom: "+quartier.getNom()+", cout: "+quartier.getCout()+", type: "+quartier.getType()+".");
                                        j++;
                                    }else{
                                        break;
                                    }  
                                    
                                }

                                int indxQuartierAdv = random.nextInt(joueurChoisi.nbQuartiersReelDansCite());
                                Quartier quartierAdv = joueurChoisi.getCite()[indxQuartierAdv];
                                boolean presentChezAdv = false;
                                for(int i=0; i<joueurChoisi.nbQuartiersReelDansCite();i++){
                                    if(quartierDiplo.getNom().equals(joueurChoisi.getCite()[i].getNom())){
                                        presentChezAdv = true;
                                    }
                                }

                                boolean presentChezDiplo = false;

                                for(int i=0; i<joueurDiplo.nbQuartiersReelDansCite();i++){
                                    if(quartierAdv.getNom().equals(joueurDiplo.getCite()[i].getNom())){
                                        presentChezDiplo = true;
                                    }
                                }

                                if(!quartierDiplo.getNom().equals(quartierAdv.getNom()) && presentChezAdv){

                                    System.out.println("Quartier déjà présent dans la cité de votre adversaire");

                                }else if(!quartierDiplo.getNom().equals(quartierAdv.getNom()) && presentChezDiplo){

                                    System.out.println("Quartier déjà présent dans votre cité");

                                }else{

                                   if (this.getJoueur().nbPieces() > (quartierAdv.getCout() - quartierDiplo.getCout() + 1) && aGrandeMuraille){
                                        System.out.println("Vous avez choisi d'échanger avec la Merveille Grande Muraille. Vous payez donc 1 piece de plus");
                                        joueurDiplo.retirerPieces(quartierAdv.getCout() - quartierDiplo.getCout() + 1);

                                        joueurDiplo.retirerQuartierDansCite(quartierDiplo.getNom());
                                        joueurChoisi.retirerQuartierDansCite(quartierAdv.getNom());
                                        joueurDiplo.ajouterQuartierDansCite(quartierAdv);
                                        joueurChoisi.ajouterQuartierDansCite(quartierDiplo);
                                    }

                                    else if(this.getJoueur().nbPieces() > (quartierAdv.getCout() - quartierDiplo.getCout())){
                                        continu2 = false;
                                        continu1=false;
                                        System.out.println("Vous avez échangé "+quartierDiplo.getNom()+" avec "+quartierAdv.getNom());

                                        joueurDiplo.retirerPieces(quartierAdv.getCout() - quartierDiplo.getCout());

                                        joueurDiplo.retirerQuartierDansCite(quartierDiplo.getNom());
                                        joueurChoisi.retirerQuartierDansCite(quartierAdv.getNom());
                                        joueurDiplo.ajouterQuartierDansCite(quartierAdv);
                                        joueurChoisi.ajouterQuartierDansCite(quartierDiplo);



                                    }
                                    
                                    else{
                                        System.out.println("Vous ne pouvez pas échanger ces quartiers. Vous n'avez pas assez de pieces");
                                        continu2 = true;
                                    }


                                    
                                }


                            
                        
                            
                            }

                        }else{
                            continu1 = false;
                            continu2 = false;
                        }
                        
                        
                    
                    

                    }while (continu2);
                }else if(this.getJoueur().nbQuartiersReelDansCite()==0){
                    System.out.println("Vous n'avez pas de quartier à échanger.");
                    continu1 = false;

                }else{
                    System.out.println("Vous decidez de ne pas utiliser votre pouvoir.");
                    continu1 = false;
                }
            }while(continu1);
            //else if(this.getJoueur() == null){
                //System.out.println("Aucun joueur n'est attribué au personnage");
            //} else{
             //   System.out.println("Le personnage est assassiné");
        }

    
    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }

}

