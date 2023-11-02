package citadelles.modele;

public class Personnage {
	String nom;
	int rang;
	String caracteristiques;
	Joueur joueur;
	boolean assassine;
	boolean vole;
	PlateauDeJeu plateau;
	
	public Personnage(String nom, int rang, String caracteristiques)
	{
		this.caracteristiques = caracteristiques;
		this.rang = rang;
		this.nom = nom;
	}
	
	public void ajouterPieces()
	{
		
	}
	
}
