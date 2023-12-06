package modele;
import java.util.Random;
import Controleur.Interaction;

public class Artiste extends Personnage {
    public Artiste(){
        super("Artiste",9,Caracteristiques.ARTISTE);
    }

    Random random = new Random();

    public void utiliserPouvoir(){
        System.out.println("\nVoici votre cité :");
        for(int l=0; l<this.getJoueur().nbQuartiersDansCite(); l++){
            Quartier quartier =this.getJoueur().getCite()[l];
            System.out.println((l+1));
            System.out.println("Nom du quartier: "+quartier.getNom());
            System.out.println("Type du quartier: "+quartier.getType());
            System.out.println("Coût de construction: "+quartier.getCoutConstruction());
        }
        for(int i=0; i<2; i++){
            if(this.getJoueur().nbPieces()<1){
                System.out.println("Vous n'avez pas assez de pièces");
                break;
            }else{
                System.out.println("\nChoisissez le quartier n°"+(i+1)+" à embellir, 0 pour ne rien choisir");
                int choix = Interaction.lireUnEntier(0, this.getJoueur().nbQuartiersDansCite())-1;
                if(choix!=-1 && this.getJoueur().getCite()[choix]!=null){
                    this.getJoueur().getCite()[choix].embellir();
                    this.getJoueur().retirerPieces(1);
                }else{
                    System.out.println("Vous avez choisis de ne rien faire.");
                    break;
                }
            }
            
        }
        
    }

    public void utiliserPouvoirAvatar(){
        for(int i=0; i<2; i++){
            if(this.getJoueur().nbPieces()<1){
                System.out.println("Vous n'avez pas assez de pièces");
                break;
            }else{
                System.out.println("\nChoisissez le quartier n°"+(i+1)+" à embellir, 0 pour ne rien choisir");
                int choix = random.nextInt(this.getJoueur().nbQuartiersDansCite()+1)-1;
                if(choix!=-1 && this.getJoueur().getCite()[choix]!=null){
                    this.getJoueur().getCite()[choix].embellir();
                    this.getJoueur().retirerPieces(1);
                }else{
                    System.out.println("Vous avez choisis de ne rien faire.");
                    break;
                }
            }
            
        }
    }

    /*@Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
*/
   

}
