package citadelles.modele;

import citadelles.modele.Personnage;
import java.util.*;

public class PlateauDeJeu {
	Personnage[] listePersonnages;
	Joueur[] listeJoueurs;
	Pioche pioche;
	int nombrePersonnages;
	int nombreJoueurs;
	
	public PlateauDeJeu()
	{
	}
	
	public void ajouterPersonnage(Personnage nouveau)
	{
		ArrayList<Personnage> arrayList = new ArrayList<Personnage>(Arrays.asList(listePersonnages));  
		arrayList.add(nouveau);  
		listePersonnages= arrayList.toArray(listePersonnages);
	}
	
	public Personnage getPersonnage(int i) {
		return listePersonnages[i];
	}
	
	public void ajouterJoueur(Joueur nouveau)
	{
		ArrayList<Joueur> arrayList = new ArrayList<Joueur>(Arrays.asList(listeJoueurs));  
		arrayList.add(nouveau);  
		listeJoueurs = arrayList.toArray(listeJoueurs);
	}
	
	public Joueur getJoueur(int i)
	{
		return listeJoueurs[i];
	}
	
	public int getNombrePersonnages()
	{
		return listePersonnages.length;
	}
	
	public int getNombreJoueurs()
	{
		return listeJoueurs.length;
	}
	
	public Pioche getPioche()
	{
		return pioche;
	}
}