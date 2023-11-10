package modele;

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
