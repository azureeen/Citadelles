package modele;

public class Alchimiste extends Personnage{

    public Alchimiste() {
        super("Alchimiste", 6, Caracteristiques.ALCHIMISTE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        System.out.println("Pas de pouvoir");
        
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        System.out.println("Pas de pouvoir");
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
    
}
