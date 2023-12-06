package modele;

public class Architecte extends Personnage {
    public Architecte(){
        super("Architecte", 7, Caracteristiques.ARCHITECTE);
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
        System.out.println("Vous avez pioche deux cartes");
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
        System.out.println("Vous avez pioche deux cartes");

    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
    
}
