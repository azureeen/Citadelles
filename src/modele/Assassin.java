package modele;
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
