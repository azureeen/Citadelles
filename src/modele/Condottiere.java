package modele;

import Controleur.Interaction;

import java.util.Objects;

public class Condottiere extends Personnage{

    /**
     * Constructeur Condottiere
     */
    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }

    /**
     * Utiliser le pouvoir du Condottiere
     * Pouvoir détruire les quartiers d’une cité non complète d’un joueur adverse ou de sa propre cité.
     */
    @Override
    public void utiliserPouvoir() {
        if(!getAssassine() && getJoueur() != null){
            Personnage[] listePersonnages = this.getPlateau().getListePersonnages();
            Joueur[] listeJoueurs = this.getPlateau().getListeJoueurs();

            // Affichage Question
            String ouiOuNon;
            do {
                System.out.print("Voulez-vous utiliser votre pouvoir de destruction ? (O/N) : ");
                ouiOuNon = Interaction.lireUneChaine();
            } while (!Objects.equals(ouiOuNon, "O") && !Objects.equals(ouiOuNon, "N"));

            // Si le joueur n'utilise pas le pouvoir alors, on quitte la méthode
            if(ouiOuNon.equals("N")) return;

            // Sinon, on affiche les quartiers détenus par les différents joueurs
            int nbJoueurs=0;
            for (Joueur joueur : listeJoueurs) {
                StringBuilder quartiersDuJoueur = new StringBuilder();

                // Récupération des quartiers du joueur dans une variable destinée à l'affichage
                if (joueur != null) {
                    int nbQuartiers = 0;
                    for (Quartier quartier : joueur.getCite()) {
                        if (quartier != null) quartiersDuJoueur.append(" ").append(nbQuartiers + 1).append(" ").append(quartier.getNom()).append("(coût ").append(quartier.getCoutConstruction()).append("),");
                        nbQuartiers++;
                    }
                }

                // Affichage dans la console
                if(joueur != null) {
                    System.out.println("    " + String.valueOf(nbJoueurs+1) + " " + joueur.getNom() + " " + quartiersDuJoueur);
                    nbJoueurs++;
                }
            }

            System.out.println("Pour information, vous avez " + getJoueur().nbPieces() + " pièces d’or dans votre trésor");

            int joueurChoisiIndex;
            boolean joueurPossedeEveque;
            boolean joueurPossedeCiteComplete;

            do {
                // Initialize boolean
                joueurPossedeEveque = false;
                joueurPossedeCiteComplete = false;

                // Demander à l'utilisateur quel joueur choisit-il et verifier s'il entre une valeur valide
                System.out.println("NB Joueurs : " + nbJoueurs);
                System.out.print("Quel joueur choisissez-vous ? ");
                joueurChoisiIndex = Interaction.lireUnEntier();

                // Les quartiers de l'Évêque ne peuvent pas être altérés par les pouvoirs de personnages de rang 8
                for (Personnage personnage: listePersonnages) {
                    if (personnage!= null && Objects.equals(personnage.getNom(), "Eveque") && personnage.getJoueur() == listePersonnages[joueurChoisiIndex - 1].getJoueur() && !personnage.getAssassine()) {
                        joueurPossedeEveque = true;
                        System.out.println("Ce joueur possède un Évêque, impossible de détruire un de ses quartiers");
                    }
                }

                // Les cités complètes ne peuvent avoir un quartier détruit
                boolean auMoinsUnQuartierNull = false;
                for (int i=0; i < listeJoueurs[joueurChoisiIndex -1].getCite().length; i++) {
                    if (listeJoueurs[joueurChoisiIndex -1].getCite()[i] == null) {
                        auMoinsUnQuartierNull = true;
                        break;
                    }
                }
                if (!auMoinsUnQuartierNull){
                    joueurPossedeCiteComplete = true;
                    System.out.println("Ce joueur possède une cité complète, impossible de détruire un de ses quartiers");
                }
            } while (joueurPossedeEveque || joueurPossedeCiteComplete || joueurChoisiIndex < 1 || joueurChoisiIndex > nbJoueurs);

            // Compter le nombre de quartiers dans la cité du joueur choisi
            int nbQuartiersDansCiteDuJoueur = 0;
            for (Quartier quartier: listePersonnages[joueurChoisiIndex - 1].getJoueur().getCite()) {
                if (quartier != null) nbQuartiersDansCiteDuJoueur++;
            }


            // Demander à l'utilisateur quel quartier choisit-il et verifier s'il entre une valeur valide
            int quartierChoisiIndex;
            do {
                System.out.print("Quel quartier choisissez-vous ? ");
                quartierChoisiIndex = Interaction.lireUnEntier();
            } while (quartierChoisiIndex < 1 || quartierChoisiIndex > nbQuartiersDansCiteDuJoueur);


            // Savoir si le Condottiere a assez de pieces pour détruire le quartier choisi
            Joueur joueurChoisi = listePersonnages[joueurChoisiIndex -1].getJoueur();
            Quartier quartierChoisi = joueurChoisi.getCite()[quartierChoisiIndex -1];

            if (getJoueur().nbPieces() >= quartierChoisi.getCoutConstruction() - 1){
                Quartier quartierDetruit = joueurChoisi.retirerQuartierDansCite(quartierChoisi.getNom());
                System.out.println("On retire " + quartierDetruit.getNom() + " à " + joueurChoisi.getNom());
                System.out.println("Pour information, vous avez maintenant " + getJoueur().nbPieces() + " pièces d’or dans votre trésor");
            } else {
                System.out.println("Votre trésor n’est pas suffisant ! Le coût pour détruire ce quartier est de " + quartierChoisi.getCoutConstruction());
                System.out.println("Pour information, vous avez " + getJoueur().nbPieces() + " pièces d’or dans votre trésor");
            }

        }
    }

    /**
     * Percevoir ressources spécifiques du Condottiere
     */
    @Override
    public void percevoirRessourcesSpecifiques() {
        if(!getAssassine() && getJoueur() != null){

            int nbQuartiersMilitaires = 0;
            for(int i = 0 ; i < getJoueur().getCite().length; i++) {

                if(getJoueur().getCite()[i] != null && Objects.equals(getJoueur().getCite()[i].getType(), "MILITAIRE")){
                    nbQuartiersMilitaires++;
                }
            }
            getJoueur().ajouterPieces(nbQuartiersMilitaires);
            System.out.println(nbQuartiersMilitaires + " pièces supplémentaires ont été ajoutées au trésor de " + getJoueur().getNom());
        }

    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
    }
}
