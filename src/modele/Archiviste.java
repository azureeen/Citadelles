package modele;

import java.util.ArrayList;
import java.util.Random;

import Controleur.Interaction;

public class Archiviste extends Personnage {

    public Archiviste(){
        super("Archiviste", 7, Caracteristiques.ARCHIVISTE);
    }


    @Override
    public void utiliserPouvoir() {
        // TODO Auto-generated method stub
        ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();
        System.out.println("Vous pouvez piocher 7 cartes et en garder 1. \nVeuillez choisir une carte à garder.");
        for(int i=0; i<7; i++){
            if(this.getPlateau().getPioche().nombreElements()>0){
                piocheJoueur.add(this.getPlateau().getPioche().piocher());System.out.println((i+1)+". Nom: "+piocheJoueur.get(i).getNom()+"\ntype: "+piocheJoueur.get(i).getType()+"\ncout: "+piocheJoueur.get(i).getCout());
            }else{
                System.out.println("La pioche est vide");
                break;
            }
        }
        if(piocheJoueur.size()!=0){
            int choix = Interaction.lireUnEntier(1, piocheJoueur.size())-1;
            this.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(choix));
            piocheJoueur.remove(choix);
            for(int i=0;i<6;i++){
                this.getPlateau().getPioche().ajouter(piocheJoueur.get(i));
            }  
        }
        
        piocheJoueur.clear();
        this.getPlateau().getPioche().melanger();
        
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // TODO Auto-generated method stub
        ArrayList<Quartier> piocheJoueur = new ArrayList<Quartier>();
        System.out.println("Vous pouvez piocher 7 cartes et en garder 1. \nVeuillez choisir une carte à garder.");
        for(int i=0; i<7; i++){
            if(this.getPlateau().getPioche().nombreElements()>0){
                piocheJoueur.add(this.getPlateau().getPioche().piocher());System.out.println((i+1)+". Nom: "+piocheJoueur.get(i).getNom()+"\ntype: "+piocheJoueur.get(i).getType()+"\ncout: "+piocheJoueur.get(i).getCout());
            }else{
                System.out.println("La pioche est vide");
                break;
            }
            
        }
        Random random = new Random();
        if(piocheJoueur.size()!=0){
            int choix = random.nextInt(piocheJoueur.size());
            this.getJoueur().ajouterQuartierDansMain(piocheJoueur.get(choix));
            piocheJoueur.remove(choix);
            for(int i=0;i<piocheJoueur.size();i++){
                this.getPlateau().getPioche().ajouter(piocheJoueur.get(i));
            }
        }
        
        piocheJoueur.clear();
        this.getPlateau().getPioche().melanger();
    }


    @Override
    public void activationPouvoirSorciere(Joueur joueurSorciere) {
        // TODO Auto-generated method stub
        
    }
    
}
