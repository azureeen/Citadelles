// package tokeep;

// public class Construction {
//     if(!condition){
//         if(!condition3){
//             System.out.println("Vous n'avez pas assez de pièces pour construire un quartier ou vous ne pouvez pas construire un quartier deja present dans votre cite (sauf si vous possede la merveille Carriere).");
//         }
//     }else{
//         do{
//             System.out.println("Vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s).\n" + "Veuillez choisir un quartier à construire : ");
//             int choix2 = 1;

//             if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                 choix2  = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size()) - 1;
//             }else{
//                 choix2  = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().getMain().size());

//             }

//             //implémentation de la merveille Monument

//             //implémentation de la merveille Tripot

//             //implémentation de la merveille Necropole

            
//             if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() > this.listePersonnageUtilises.get(j).getJoueur().nbPieces() && !this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot) && !this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.necropole) ){
//                 condition = true;
//                 System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possedez la merveille Carriere).");
//             }else if((this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) >= this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() || this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.necropole)){
//                 condition = false;

//                 boolean effetMerveille = false;

//                 //implémentation de la merveille Ecurie*

//                 //-----------------------------------------------------------------------//


//                 if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.ecuries)){
//                     Boolean construireAutreQuartier = false;

//                     Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                     this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                     System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");
//                     System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );

//                     System.out.println("Vous possedez avez construit la merveille Ecurie.\nChoisissez-vous de construire un autre quartier ?");

//                     if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                         construireAutreQuartier = Interaction.lireOuiOuNon();
//                     }else{
//                         construireAutreQuartier = generateur.nextBoolean();
//                     }

//                     effetMerveille = true;

//                     if(construireAutreQuartier){
//                         boolean aAssezDArgent = false;
//                         for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
//                             if(this.listePersonnageUtilises.get(j).getJoueur().nbPieces() > this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout()){
//                                 aAssezDArgent = true;
//                             }
//                         }

//                         if(aAssezDArgent){
//                             condition = true;
//                             effetMerveille = false;
//                         }else{
//                             System.out.println("Vous n'avez pas assez de pieces pour construire un autre quartier");
//                             condition = false;
//                             effetMerveille = true;
//                         }
                  

//                     }
                    


//                 }



//                 //-----------------------------------------------------------------------//
                
//                 if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.necropole)){
//                     Boolean construireNecropole = false;

//                     System.out.println("Vous avez la merveille necropole dans votre cite.\nVous pouvez choisir de detruire un quartier dans votre cite afin de construire necropole gratuitement.\nVoulez-vous detruire un quartier pour construire necropole ?");

//                     if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                         construireNecropole = Interaction.lireOuiOuNon();
//                     }else{
//                         construireNecropole = generateur.nextBoolean();
//                     }

//                     if(construireNecropole){
//                         int quartierADetruire = 0;
                        
//                         for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
//                             System.out.println((k+1) + ". " + this.listePersonnageUtilises.get(j).getJoueur().getCite()[k]);
//                         }

//                         System.out.println("\nQuel quartier choisissez vous ?");

//                         if(this.listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                             quartierADetruire = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite()) -1;
//                         }else{
//                             quartierADetruire = generateur.nextInt(this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite());
//                         }

//                         Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                         this.listePersonnageUtilises.get(j).getJoueur().retirerQuartierDansCite(this.listePersonnageUtilises.get(j).getJoueur().getCite()[quartierADetruire].getNom());
//                         this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                         System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");




//                     }else if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() > this.listePersonnageUtilises.get(j).getJoueur().nbPieces()){
                       
//                         condition = true;
//                         System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier.");
                    
//                     }else{

//                         Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                         this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                         System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");
//                         System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                    
//                     }

//                     effetMerveille = true;


                   
//                 }

//                 //-----------------------------------------------------------------------//

//                 if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.monument) && this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite() < 5){
//                     System.out.println("Vous avez la merveille monument dans votre cite, celle-ci prend 2 places dans votre cite");
//                     Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                     this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                     System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");
//                     System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
//                 }

//                 //-----------------------------------------------------------------------//

//                 for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().nbQuartiersDansCite(); k++){
//                     if(this.listePersonnageUtilises.get(j).getJoueur().getCite()[k].equals(Configuration.manufacture)){
//                         condition2 = true;
//                     }
//                 }

//                 //-----------------------------------------------------------------------//
                

//                 if(condition2){
//                     System.out.println("Vous avez la merveille manufacture dans votre cite, la construction d'une autre merveille est donc réduit d'une piece.");
//                     Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                     this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                     this.listePersonnageUtilises.get(j).getJoueur().ajouterPieces(1);
//                     System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

//                     System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
//                 }
                
//                 //-----------------------------------------------------------------------//

                
//                 else{

//                     //-----------------------------------------------------------------------//


//                     if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).equals(Configuration.tripot) && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() - 1) > this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout() ){
//                         System.out.println("Vous avez choisi de construire la merveille triport \nVous avez " + (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) + " cartes dans votre mains. \nVous pouvez choisir de payer le cout de construction du triport en partie ou entierement avec vos cartes. \nVoulez-vous payer le triport en or ou en cartes ?\n1. or\n2. cartes\n" );
//                         int choix3 = 1;

//                         if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                             choix3  = Interaction.lireUnEntier(1, 2);
//                         }else{
//                             choix3  = generateur.nextInt(2) + 1;

//                         }

//                         if(choix3 == 1){
//                             if(this.listePersonnageUtilises.get(j).getJoueur().nbPieces() < this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout()){
//                                 System.out.println("Vous n'avez pas assez de pieces");
//                             }else{
//                                 Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                                 this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                                 System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

//                                 System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );
                                        
//                             }
//                         }else if(choix3 == 2 && (this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1) > 0 ){
//                             int cout = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2).getCout();
//                             Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                             System.out.println("Une carte vaut une piece d'or, combien de cartes voulez-vous dépenser ?");
//                             int choix4 = 1;

//                             if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                                 choix4  = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size() - 1);
//                             }else{
//                                 choix4  = generateur.nextInt((this.listePersonnageUtilises.get(j).getJoueur().getMain().size()-1)) + 1;
    
//                             }

//                     //-----------------------------------------------------------------------//

//                             for(int l = 0; l < choix4; l++){
//                                 boolean continu3 = true;
//                                 do{
//                                     for(int k = 0; k < this.listePersonnageUtilises.get(j).getJoueur().getMain().size(); k++){
//                                         System.out.println(k+1 + ". " + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getNom() + " (" + this.listePersonnageUtilises.get(j).getJoueur().getMain().get(k).getCout() + ")");
//                                     }
//                                     System.out.println("Quel quartier voulez-vous retirer de votre main ?");
//                                     int choix5 = 1;

//                                     if(listePersonnageUtilises.get(j).getJoueur().getNom().equals("Joueur")){
//                                         choix5  = Interaction.lireUnEntier(1, this.listePersonnageUtilises.get(j).getJoueur().getMain().size());
//                                     }else{
//                                         choix5  = generateur.nextInt((this.listePersonnageUtilises.get(j).getJoueur().getMain().size())) + 1;
            
//                                     }

//                                     choix5 -= 1;
//                                     if(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix5).equals(Configuration.tripot)){
//                                         System.out.println("Vous ne pouvez pas choisir le triport, veuillez choisir un autre quartier.");
//                                         continu3 = true;
//                                     }else{
//                                         cout -= 1;
//                                         continu3 = false;
//                                         Quartier quartierConvertiEnPiece = this.listePersonnageUtilises.get(j).getJoueur().getMain().remove(choix5);
//                                         System.out.println(quartierConvertiEnPiece + " a ete retire de votre main.\nIl vous reste " + (choix4 - (l+1)) + " carte(s) a retirer de votre main");
//                                     }

//                                 }while(continu3);
//                             }

//                             //-----------------------------------------------------------------------//

//                             System.out.println("\nIl vous reste " + cout + " piece(s) a payer.\n");
//                             this.listePersonnageUtilises.get(j).getJoueur().retirerPieces(cout);

//                             this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
                            
//                             System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");
                            
//                             //-----------------------------------------------------------------------//
                            
//                         }

//                     //-----------------------------------------------------------------------//

//                     }else if (!effetMerveille){

//                         Quartier quartierConstruit = this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2);
//                         this.listePersonnageUtilises.get(j).construire(this.listePersonnageUtilises.get(j).getJoueur().getMain().get(choix2));
//                         System.out.println("Construction de " + quartierConstruit.getNom() + ".\n");

//                         System.out.println("Pour information, vous avez " + this.listePersonnageUtilises.get(j).getJoueur().nbPieces() + " piece(s) dans votre tresor.\n" );

//                     }

//                     //-----------------------------------------------------------------------//

                    


//                 }

                
                

//             }else{
//                 condition = true;
//                 System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier ou votre quartier est deja present dans votre cite (sauf si vous possede la merveille Carriere).");
//             }

//         }while(condition);

    
        
//     }
// }
