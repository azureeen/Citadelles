package modele;

public class Architecte extends Personnage {

    /**
     * Constructeur Architecte
     */
    public Architecte() {
        super("Architecte", 7, Caracteristiques.ARCHITECTE);
    }

    /**
     * Utiliser le pouvoir de la marchande
     * Permet de piocher deux cartes quartier supplémentaires
     */
    @Override
    public void utiliserPouvoir() {
        Quartier pioche1 = getPlateau().getPioche().piocher();
        Quartier pioche2 = getPlateau().getPioche().piocher();

        getJoueur().ajouterQuartierDansMain(pioche1);
        getJoueur().ajouterQuartierDansMain(pioche2);
    }

    @Override
    public void utiliserPouvoirAvatar() {

    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {

    }


}
