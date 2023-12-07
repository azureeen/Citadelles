package application;

import java.util.ArrayList;

import javax.swing.ViewportLayout;

import modele.Abbe;
import modele.Alchimiste;
import modele.Architecte;
import modele.Archiviste;
import modele.Artiste;
import modele.Assassin;
import modele.Bailli;
import modele.Capitaine;
import modele.Cardinal;
import modele.Condottiere;
import modele.Diplomate;
import modele.Echevin;
import modele.Empereur;
import modele.Espion;
import modele.Eveque;
import modele.Joueur;
import modele.Magicienne;
import modele.MaitreChanteuse;
import modele.Marchande;
import modele.Navigatrice;
import modele.Negociant;
import modele.Patricien;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Reine;
import modele.Roi;
import modele.Sorcier;
import modele.Sorciere;
import modele.Voleur;
import modele.Voyante;

public class Configuration {

   public static Quartier bibliotheque = new Quartier("Bibliotheque", "MERVEILLE", 6, "Si vous choisissez de piocher des cartes au debut du tour, concervez-les toutes.");
   public static Quartier carriere = new Quartier("Carriere", "MERVEILLE", 5, "Vous pouvez batir des quartiers identiques a d’autres quartiers de votre cite. Le proprietaire de la carri`ere peut batir autant de quartiers identiques qu’il le souhaite, mais ne peut pas utiliser le pouvoir de l’Echevin, du Diplomate ou du Capitaine pour acquerir des quartiers identiques.");
   public static Quartier courDesMiracles = new Quartier("Cour des Miracles", "MERVEILLE", 2, "Pour le calcul du score final, la Cour des Miracles est consideree comme un quartier de type (couleur) de votre choix. Dans la cas ou le proprietaire la considere comme un quartier noble, militaire, marchant ou religieux, la Cour des Miracles ne peut plus etre consideree comme une merveille.");
   
   public static Quartier donjon = new Quartier("Donjon", "MERVEILLE", 3, "Le Donjon ne peut etre affecte par les pouvoirs des personnages de rang 8.");
   public static Quartier dracoport = new Quartier("Dracoport", "MERVEILLE", 6, "Marquez 2 points supplementaires a la fin de la partie.");
   public static Quartier ecoleDeMagie = new Quartier("Ecole de Magie", "MERVEILLE", 6, "Pour la perception des revenus des personnages, l’Ecole de Magie est consideree comme un quartier du type (couleur) de votre choix.");
   public static Quartier fontaineAuxSouhaits = new Quartier("Fontaine aux Souhaits", "MERVEILLE", 5, " la fin de la partie, marquez 1 point supplementaire par merveille dans votre cite, y compris la Fontaine aux Souhaits");
   public static Quartier forge = new Quartier("Forge", "MERVEILLE", 5, "Une fois par tour, vous pouvez payez 2 pieces d’or pour piocher 3 cartes");
   public static Quartier laboratoire = new Quartier("Laboratoire", "MERVEILLE", 5, "Une fois par tour, vous pouvez defausser 1 carte pour recevoir 2 pieces d’or.");
   public static Quartier manufacture = new Quartier("Manufacture", "MERVEILLE", 5, "Payez 1 piece d’or de moins lorsque vous batissez une autre merveille.");
   public static Quartier salleDesCartes = new Quartier("Salle des Cartes", "MERVEILLE", 5, "A la fin de la partie, marquez 1 point supplementaire par carte dans votre main.");
   public static Quartier statueEquestre = new Quartier("Statue Equestre", "MERVEILLE", 3, "Si vous d´etenez le Couronne a la fin de la partie, marquez 5 points supplementaires.");
   public static Quartier tresorImperial = new Quartier("Tresor Imperial", "MERVEILLE", 5, "A la fin de la partie, marquez 1 point supplementaire par piece d’or dans votre tresor.");
   public static Quartier tripot = new Quartier("Tripot", "MERVEILLE", 6, "Vous pouvez payer tout ou partie du cout de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 piece d’or. Si le Tripot est confisque par l’Echevin, le joueur n’est rembourse que de l’or qu’il a depense, pas des cartes.");
   public static Quartier basilique = new Quartier("Basilique", "MERVEILLE", 4, "A la fin de la partie, marquez 1 point suppl´ementaire pour chaque quartier au cout de `construction impair dans votre cit´e.");
   public static Quartier capitole = new Quartier("Capitole", "MERVEILLE", 5, " la fin de la partie, marquez 3 points suppl´ementaires si vous avez au moins 3 quartiers du `mˆeme type (couleur) dans votre cit´e. Vous ne pouvez utiliser l’effet du capitole qu’une seule fois.");
   public static Quartier catacombes = new Quartier("Catacombes", "MERVEILLE", 0, "Les catacombes ne peuvent pas ˆetre bˆaties. A la fin de la partie, si vous avez les catacombes `dans votre main, marquez 3 points suppl´ementaires.");
   public static Quartier chantier = new Quartier("Chantier", "MERVEILLE", 3, "Pour bˆatir un quartier, vous pouvez d´etruire le chantier au lieu de payer le coˆut de construction. L’Echevin ne peut pas confisquer un quartier bˆati en d´etruisant le chantier. ");
   
   public static Quartier ecuries = new Quartier("Ecuries", "MERVEILLE", 2, "Vous pouvez bˆatir les Ecuries sans qu’elles comptent dans votre permis de construire. Si ´les Ecuries sont confisqu´ees par l’ ´ Echevin, il peut quand mˆeme bˆatir un autre quartier que ´celui-ci.");

   public static Quartier grandeMuraille = new Quartier("Grande Muraille", "MERVEILLE", 6, "Les personnages de rang 8 doivent payer 1 pi`ece d’or suppl´ementaire pour affecter un quartier de votre cit´e. La Grande Muraille n’affecte pas le coˆut de construction des quartiers de la cit´e du Diplomate quand il utilise son pouvoir.");

   public static Quartier hospice = new Quartier("Hospice", "MERVEILLE", 4, "Si vous n’avez aucune pi`ece d’or dans votre tr´esor `a la fin de votre tour, gagnez 1 pi`ece d’or. Si la Sorci`ere ne reprend pas son tour de jeu, elle ne le termine donc pas et ne peut pas utiliser l’effet de l’Hospice `a la fin de son tour. L’Alchimiste utilise l’effet de l’Hospice avant d’appliquer son propre pouvoir.");
   public static Quartier mineDOr = new Quartier("Mine D'Or", "MERVEILLE", 6, "Si vous choisissez de recevoir des pi`eces d’or en d´ebut de tour, prenez-en 1 suppl´ementaire.");
   public static Quartier monument = new Quartier("Monument", "MERVEILLE", 4, "Vous ne pouvez pas bˆatir le Monument si vous avez d´ej`a au moins 5 quartiers dans votre cit´e. Le Monument compte comme 2 quartiers pour compl´eter votre cit´e.");
   public static Quartier musee = new Quartier("Musee", "MERVEILLE", 4, "Une fois par tour, vous pouvez placer une carte de votre main, face cach´ee, sous le Mus´ee. A` la fin de la partie, marquez 1 point suppl´ementaire par carte sous le Mus´ee. Si le Mus´ee est d´eplac´e d’une cit´e `a une autre, il conserve toutes les cartes qui ´etaient dessous. Si le Mus´ee est d´etruit, les cartes sont d´efauss´ees, face cach´ee, sous la pioche.");
   
   public static Quartier necropole = new Quartier("Necropole", "MERVEILLE", 5, "Pour bˆatir la n´ecropole, vous pouvez d´etruire un quartier de votre cit´e au lieu de payer son coˆut de construction. Le Bailli ne peut pas confisquer la N´ecropole sans payer son coˆut de construction");

   public static Quartier observatoire = new Quartier("Observatoire", "MERVEILLE", 4, "Si vous choisissez de piocher des cartes au d´ebut de votre tour, vous choisissez la carte parmi 3 cartes au lieu de 2.");

   public static Quartier parc = new Quartier("Parc", "MERVEILLE", 6, "Si vous n’avez aucune carte en main `a la fin de votre tour, piochez 2 cartes. Si la Sorci`ere ne reprend pas son tour, elle ne peut pas utiliser l’effet du Parc `a la fin de son tour.");
   
   public static Quartier poudriere = new Quartier("Poudriere", "MERVEILLE", 3, "Durant votre tour, vous pouvez d´etuire simultan´ement la Poudri`ere et un autre quartier de votre choix. Vous ne pouvez pas d´etruire un quartier d’une cit´e compl`ete.");
   public static Quartier theatre = new Quartier("Theatre", "MERVEILLE", 6, "A la fin de la phase de s´election, vous pouvez ´echanger votre carte Personnage face cach´ee `avec celle d’un autre joueur. Le propri´etaire du Th´eˆatre choisit avec qui il fait l’´echange, sans avoir vu aucune des cartes des autres joueurs. Les cartes ´echang´ees en peuvent ˆetre r´ev´el´ees aux autres joueurs avant d’ˆetre appel´ees. Dans une partie `a 2 ou 3 joueurs, le propri´etaire du Th´eˆatre choisit le carte Personnage parmi les deux cartes du joueur choisi pour l’´echange, sans les regarder.");
   public static Quartier tourDIvoire = new Quartier("Tour D'Ivoire", "MERVEILLE", 5, "Si la Tour d’Ivoir est votre unique merveille `a la fin de la partie, marquez 5 points suppl´ementaires. Si la Cour des Miracles et la Tour d’Ivoire sont les deux seules merveilles d’une cit´e, et que le joueur d´ecide de consid´erer la Cour des Miracles comme n’´etant pas plus une merveille, alors il peut b´en´eficier du bonus de la Tour d’Ivoire.");

   //implémentation nombre de joueurs

   private int nbJoueur = 0;


    public Configuration(){
    }

    public void setNbJoueur(int nbJoueur){
        this.nbJoueur = nbJoueur;
    }


    
    public static Pioche nouvellePioche(){
        Pioche pioche = new Pioche();
        for(int i = 0; i< 3; i++){
            pioche.ajouter(new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1));
            pioche.ajouter(new Quartier("eglise", Quartier.TYPE_QUARTIERS[0], 2));
            pioche.ajouter(new Quartier("monastere", Quartier.TYPE_QUARTIERS[0], 3));
            pioche.ajouter(new Quartier("tour de guet", Quartier.TYPE_QUARTIERS[1], 1));
            pioche.ajouter(new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2));
            pioche.ajouter(new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3));
            pioche.ajouter(new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5));
            pioche.ajouter(new Quartier("echoppe", Quartier.TYPE_QUARTIERS[3], 2));
            pioche.ajouter(new Quartier("comptoir", Quartier.TYPE_QUARTIERS[3], 3));
            pioche.ajouter(new Quartier("port", Quartier.TYPE_QUARTIERS[3], 4));
        }
        for(int i = 0; i<2; i++){
            pioche.ajouter(new Quartier("cathedrale", Quartier.TYPE_QUARTIERS[0], 5));
            pioche.ajouter(new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5));
            pioche.ajouter(new Quartier("hotel de ville", Quartier.TYPE_QUARTIERS[3], 5));
        }
        for(int i = 0; i<4; i++){
            pioche.ajouter(new Quartier("chateau", Quartier.TYPE_QUARTIERS[2], 4));
            pioche.ajouter(new Quartier("marche", Quartier.TYPE_QUARTIERS[3], 2));

        }
        for(int i = 0; i<5; i++){
            pioche.ajouter(new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3));
            pioche.ajouter(new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1));
        }
        return pioche;
    }

    public static PlateauDeJeu configurationDeBase(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(carriere);
        p.ajouter(bibliotheque);
        p.ajouter(courDesMiracles);
        p.ajouter(donjon);
        p.ajouter(dracoport);
        p.ajouter(ecoleDeMagie);
        p.ajouter(fontaineAuxSouhaits);
        p.ajouter(forge);
        p.ajouter(laboratoire);
        p.ajouter(manufacture);
        p.ajouter(salleDesCartes);
        p.ajouter(statueEquestre);
        p.ajouter(tresorImperial);
        p.ajouter(tripot);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Assassin());
        plateau.ajouterPersonnage(new Voleur());
        plateau.ajouterPersonnage(new Magicienne());
        plateau.ajouterPersonnage(new Roi());
        plateau.ajouterPersonnage(new Eveque());
        plateau.ajouterPersonnage(new Marchande());
        plateau.ajouterPersonnage(new Architecte());
        plateau.ajouterPersonnage(new Condottiere());

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


        }
        
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationAristocrateAmbitieux(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(capitole);
        p.ajouter(carriere);
        p.ajouter(chantier);
        p.ajouter(courDesMiracles);
        p.ajouter(donjon);
        p.ajouter(ecoleDeMagie);
        p.ajouter(ecuries);
        p.ajouter(grandeMuraille);
        p.ajouter(hospice);
        p.ajouter(manufacture);
        p.ajouter(necropole);
        p.ajouter(parc);
        p.ajouter(statueEquestre);
        p.ajouter(tripot);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Echevin());
        plateau.ajouterPersonnage(new Voleur());
        plateau.ajouterPersonnage(new Sorcier());
        plateau.ajouterPersonnage(new Patricien());
        plateau.ajouterPersonnage(new Eveque());
        plateau.ajouterPersonnage(new Negociant());
        plateau.ajouterPersonnage(new Architecte());
        plateau.ajouterPersonnage(new Capitaine());
        //Implementation 8 joueurs
        if(Jeu.nbPersonnages ==9 && Jeu.nbJoueurs!=3 && Jeu.nbJoueurs!=4){
            plateau.ajouterPersonnage(new Reine());
    
        }else if(Jeu.nbPersonnages ==9 && (Jeu.nbJoueurs==3 || Jeu.nbJoueurs==4) ){
            plateau.ajouterPersonnage(new Artiste());

        }

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationIntrigantsSubtils(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(basilique);
        p.ajouter(carriere);
        p.ajouter(catacombes);
        p.ajouter(donjon);
        p.ajouter(dracoport);
        p.ajouter(forge);
        p.ajouter(hospice);
        p.ajouter(mineDOr);
        p.ajouter(monument);
        p.ajouter(musee);
        p.ajouter(necropole);
        p.ajouter(parc);
        p.ajouter(poudriere);
        p.ajouter(theatre);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Sorciere());
        plateau.ajouterPersonnage(new MaitreChanteuse());
        plateau.ajouterPersonnage(new Magicienne());
        if(Jeu.nbJoueurs!=2 && Jeu.nbJoueurs!=3){
            plateau.ajouterPersonnage(new Empereur());
        }else if(Jeu.nbJoueurs==2 || Jeu.nbJoueurs==3){
            plateau.ajouterPersonnage(new Roi());
        }
        plateau.ajouterPersonnage(new Abbe());
        plateau.ajouterPersonnage(new Alchimiste());
        plateau.ajouterPersonnage(new Architecte());
        plateau.ajouterPersonnage(new Condottiere());
        //Implementation 8 joueur
        if(Jeu.nbPersonnages ==9){
            plateau.ajouterPersonnage(new Bailli());
    
        }

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationEmissairesIllustres(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(bibliotheque);
        p.ajouter(carriere);
        p.ajouter(chantier);
        p.ajouter(courDesMiracles);
        p.ajouter(donjon);
        p.ajouter(ecoleDeMagie);
        p.ajouter(forge);
        p.ajouter(grandeMuraille);
        p.ajouter(hospice);
        p.ajouter(manufacture);
        p.ajouter(musee);
        p.ajouter(observatoire);
        p.ajouter(parc);
        p.ajouter(tourDIvoire);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Sorciere());
        plateau.ajouterPersonnage(new Espion());
        plateau.ajouterPersonnage(new Voyante());
        if(Jeu.nbJoueurs!=2 && Jeu.nbJoueurs!=3){
            plateau.ajouterPersonnage(new Empereur());
        }else if(Jeu.nbJoueurs==2 || Jeu.nbJoueurs==3){
            plateau.ajouterPersonnage(new Roi());
        }
        plateau.ajouterPersonnage(new Eveque());
        plateau.ajouterPersonnage(new Marchande());
        plateau.ajouterPersonnage(new Archiviste());
        plateau.ajouterPersonnage(new Diplomate());
        //Implementation 8 joueur
        if(Jeu.nbPersonnages ==9){
            plateau.ajouterPersonnage(new Artiste());
    
        }
        

        

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationDignitairesSournois(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(catacombes);
        p.ajouter(chantier);
        p.ajouter(courDesMiracles);
        p.ajouter(dracoport);
        p.ajouter(ecuries);
        p.ajouter(fontaineAuxSouhaits);
        p.ajouter(forge);
        p.ajouter(hospice);
        p.ajouter(laboratoire);
        p.ajouter(manufacture);
        p.ajouter(necropole);
        p.ajouter(parc);
        p.ajouter(theatre);
        p.ajouter(tripot);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Echevin());
        plateau.ajouterPersonnage(new MaitreChanteuse());
        plateau.ajouterPersonnage(new Sorcier());
        plateau.ajouterPersonnage(new Roi());
        plateau.ajouterPersonnage(new Abbe());
        plateau.ajouterPersonnage(new Alchimiste());
        plateau.ajouterPersonnage(new Navigatrice());
        plateau.ajouterPersonnage(new Capitaine());
        //Implementation 8 joueur
        if(Jeu.nbPersonnages ==9 && Jeu.nbJoueurs!=3 && Jeu.nbJoueurs!=4){
            plateau.ajouterPersonnage(new Reine());
    
        }else if(Jeu.nbPersonnages ==9 && (Jeu.nbJoueurs==3 || Jeu.nbJoueurs==4) ){
            plateau.ajouterPersonnage(new Artiste());

        }

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationOligarquesTenaces(Pioche p){ //fonctionnel ta mere

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>();
      
        p.ajouter(basilique);
        p.ajouter(bibliotheque);
        p.ajouter(capitole);
        p.ajouter(catacombes);
        p.ajouter(courDesMiracles);
        p.ajouter(ecoleDeMagie);
        p.ajouter(ecuries);
        p.ajouter(fontaineAuxSouhaits);
        p.ajouter(forge);
        p.ajouter(laboratoire);
        p.ajouter(observatoire);
        p.ajouter(salleDesCartes);
        p.ajouter(statueEquestre);
        p.ajouter(tresorImperial);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Assassin());
        plateau.ajouterPersonnage(new Espion());
        plateau.ajouterPersonnage(new Voyante());
        plateau.ajouterPersonnage(new Roi());
        plateau.ajouterPersonnage(new Cardinal());
        plateau.ajouterPersonnage(new Negociant());
        plateau.ajouterPersonnage(new Archiviste());
        plateau.ajouterPersonnage(new Diplomate());
        //Implementation 8 joueur
        if(Jeu.nbPersonnages ==9){
            plateau.ajouterPersonnage(new Artiste());
    
        }

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }

    public static PlateauDeJeu configurationNoblesRetors(Pioche p){ //fonctionnel

        //Pioche 

        ArrayList<Quartier> merveilles = new ArrayList<Quartier>(); //Cardinal, Bailli
      
        p.ajouter(basilique);
        p.ajouter(dracoport);
        p.ajouter(ecoleDeMagie);
        p.ajouter(fontaineAuxSouhaits);
        p.ajouter(laboratoire);
        p.ajouter(mineDOr);
        p.ajouter(monument);
        p.ajouter(musee);
        p.ajouter(poudriere);
        p.ajouter(salleDesCartes);
        p.ajouter(statueEquestre);
        p.ajouter(tourDIvoire);
        p.ajouter(tresorImperial);
        p.ajouter(tripot);

        PlateauDeJeu plateau = new PlateauDeJeu();
        p.melanger();
        plateau.setPioche(p);
        
        //Persos

        plateau.ajouterPersonnage(new Assassin());
        plateau.ajouterPersonnage(new Voleur());
        plateau.ajouterPersonnage(new Magicienne());
        plateau.ajouterPersonnage(new Patricien());
        plateau.ajouterPersonnage(new Cardinal());
        plateau.ajouterPersonnage(new Marchande());
        plateau.ajouterPersonnage(new Navigatrice());
        plateau.ajouterPersonnage(new Condottiere());
        //Implementation 8 joueur
        if(Jeu.nbPersonnages ==9){
            plateau.ajouterPersonnage(new Bailli());
    
        }

        //Joueurs

        switch(Jeu.nbJoueurs){

            case 2:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 3:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;


            case 4:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 5:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 6:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 7:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

            case 8:
                plateau.ajouterJoueur(new Joueur("ORDI 1"));
                plateau.ajouterJoueur(new Joueur("ORDI 2"));
                plateau.ajouterJoueur(new Joueur("ORDI 3"));
                plateau.ajouterJoueur(new Joueur("ORDI 4"));
                plateau.ajouterJoueur(new Joueur("ORDI 5"));
                plateau.ajouterJoueur(new Joueur("ORDI 6"));
                plateau.ajouterJoueur(new Joueur("ORDI 7"));
                plateau.ajouterJoueur(new Joueur("Joueur"));
                break;

        }
        //Plateau configuré

        return plateau;


    }
    
}
