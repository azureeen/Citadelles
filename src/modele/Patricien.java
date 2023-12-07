package modele;

public class Patricien extends Personnage {

    public Patricien(){
        super("Patricien", 4, Caracteristiques.PATRICIEN);
    }

    public void utiliserPouvoir(){
        if(!this.getAssassine()){
            if(this.getJoueur() != null){
                System.out.println("Je prend la couronne");
                this.getJoueur().setPossedeCouronne(true);
            }else{
                System.out.println("le joueur n'est pas défini");
            }
        }else{
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        if(this.getAssassine()){
            super.percevoirRessourcesSpecifiques();
        }else{
            int a = 0;
            if(this.getJoueur() != null){
                for(int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++){
                    if(this.getJoueur().getCite()[0] != null){
                        if(this.getJoueur().getCite()[i].getType() == "NOBLE"){
                            this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
                                a += 1;
                        }
                    }
                }
                //ajouteur dafficher des cartes piochée
                if(this.getJoueur().getPossedeEcoleMag()){//la merveille Ecole de Magie change de type a la perception des ressources
                    this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
                    a+=1;
                }
            }
            System.out.println(a + " quartiers(s) ajoutée(s) dans la main");
        }
     
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        if(!this.getAssassine()){
            if(this.getJoueur() != null){
                System.out.println("Je prend la couronne");
                this.getJoueur().setPossedeCouronne(true);
            }else{
                System.out.println("le joueur n'est pas défini");
            }
        }else{
            System.out.println("Vous ne pouvez pas utiliser votre pouvoir");
        }
        
    }

    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
}
