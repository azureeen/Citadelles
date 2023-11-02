package citadelles.modele;

public class Quartier {
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	String nom;
	String type;
	int coutConstruction;
	String caracteristiques;
	Pioche pioche;
	Joueur joueur;
	
	public Quartier(String nom, String type, int coutConstruction, String caracteristiques)
	{
		this.nom = nom;
		this.coutConstruction = coutConstruction;
		this.caracteristiques = caracteristiques;
		this.type = type;
	}
}
