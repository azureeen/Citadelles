package citadelles.modele;

import java.util.ArrayList;

public class Joueur {
	String nom;
	int tresor;
	Quartier[] cite;
	int nbQuartiers;
	ArrayList<Quartier> main;
	boolean possedeCouronne;
	
	public Joueur(String nom)
	{
		this.nom = nom;
	}
	
	public void ajouterPiece(int nbPieces)
	{
		
	}
	
	public void retirerPiece(int nbPieces)
	{
		
	}
	
	public int nbQuartiersDansCite()
	{
		return this.nbQuartiers;
	}
	
	public boolean quartierPresentDansCite(String nom)
	{
		
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	
	
	
}
