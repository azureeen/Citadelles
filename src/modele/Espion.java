package modele;
import java.util.Random;
import Controleur.Interaction;

public class Espion extends Personnage {
    public Espion(){
        super("Espion", 2, Caracteristiques.ESPION);
    }
    Random random = new Random();


    public void utiliserPouvoir(){
        for(int i=0; i<this.getPlateau().getNombreJoueurs(); i++){
            System.out.println((i+1)+this.getPlateau().getJoueur(i).getNom());
            
        }
        int choixJ;
        do {
            System.out.println("Choisissez un joueur (vous ne pouvez pas vous choisir) (0 pour ne choisir personne): ");
            choixJ = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs())-1;
            if(choixJ==-1){
                break;
            }else if(this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain()==0){
                System.out.println("Le joueur n'a pas de carte en main");
            }
            
        } while (this.getPlateau().getJoueur(choixJ)==this.getJoueur()||this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain()==0);
        if(choixJ!=-1){
            System.out.println("Choisissez le type de Quartier(1 pour RELIGIEUX, 2 pour MILITAIRES, 3 pour NOBLES, 4 pour COMMERCANT, 5 pour MERVEILLE): ");
            int choixQ = Interaction.lireUnEntier(1, 5)-1;
            int compteur = 0;
            for(int i=0; i<this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain(); i++){
                Quartier q = this.getPlateau().getJoueur(choixJ).getMain().get(i);
                if(q.getType().equals(Quartier.TYPE_QUARTIERS[choixQ])){
                    compteur++;
                }
            }
            if(compteur>0){
                if(this.getPlateau().getJoueur(choixJ).nbPieces()<compteur){
                    System.out.println("Vous allez gagner "+this.getPlateau().getJoueur(choixJ).nbPieces()+"pièces d'or et de cartes quartiers.");
                    this.getJoueur().ajouterPieces(this.getPlateau().getJoueur(choixJ).nbPieces());
                    this.getPlateau().getJoueur(choixJ).retirerPieces(this.getPlateau().getJoueur(choixJ).nbPieces());
                }else{
                    System.out.println("Vous allez gagner "+compteur+"pièces d'or et de cartes quartiers.");
                    this.getJoueur().ajouterPieces(compteur);
                    this.getPlateau().getJoueur(choixJ).retirerPieces(compteur);
                }
                System.out.println("Quartiers piochés :");
                for(int k=0; k<compteur; k++){
                    Quartier q = this.getPlateau().getPioche().piocher();
                    this.getJoueur().ajouterQuartierDansMain(q);
                    System.out.println("Le quartier num "+(k+1)+": ");
                    System.out.println("Nom du quartier: "+q.getNom());
                    System.out.println("Type du quartier: "+q.getType());
                    if(q.getType().equals("MERVEILLE")){
                        System.out.println("Caracteristiques du quartier: "+q.getCaracteristiques());
                    }
                    System.out.println("Coût de construction: "+q.getCout()+"\n");
                }
            }
        }
        
    }

    public void utiliserPouvoirAvatar(){
        int choixJ;
        do {
            System.out.println("Choisissez un joueur (vous ne pouvez pas vous choisir) : ");
            choixJ = random.nextInt(this.getPlateau().getNombreJoueurs()+1)-1;
            if(choixJ==-1){
                break;
            }else if(this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain()==0){
                System.out.println("Le joueur n'a pas de carte en main");
            }
        } while (this.getPlateau().getJoueur(choixJ)==this.getJoueur()||this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain()==0);
        if(choixJ!=-1){
            System.out.println("Choisissez le type de Quartier(1 pour RELIGIEUX, 2 pour MILITAIRES, 3 pour NOBLES, 4 pour COMMERCANT, 5 pour MERVEILLE): ");
            int choixQ = random.nextInt(5);
            int compteur = 0;
            for(int i=0; i<this.getPlateau().getJoueur(choixJ).nbQuartiersReelDansMain(); i++){
                Quartier q = this.getPlateau().getJoueur(choixJ).getMain().get(i);
                if(q.getType().equals(Quartier.TYPE_QUARTIERS[choixQ])){
                    compteur++;
                }
            }
            if(compteur>0){
                if(this.getPlateau().getJoueur(choixJ).nbPieces()<compteur){
                    System.out.println("Vous allez gagner "+this.getPlateau().getJoueur(choixJ).nbPieces()+"pièces d'or et de cartes quartiers.");
                    this.getJoueur().ajouterPieces(this.getPlateau().getJoueur(choixJ).nbPieces());
                    this.getPlateau().getJoueur(choixJ).retirerPieces(this.getPlateau().getJoueur(choixJ).nbPieces());
                }else{
                    System.out.println("Vous allez gagner "+compteur+"pièces d'or et de cartes quartiers.");
                    this.getJoueur().ajouterPieces(compteur);
                    this.getPlateau().getJoueur(choixJ).retirerPieces(compteur);
                }
            }
        }
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
