package modele;

public class Magicienne extends Personnage{

    /**
     * Constructeur Magicienne
     */
    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
    }

    /**
     * La magicienne peut échanger un certain nombre des cartes de sa main, soit en échangeant toutes
     * ses cartes avec celles d’un autre joueur, soit en remplaçant un certain nombre de cartes par des cartes
     * de la pioche
     */
    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
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
