package citadelles.modele;

import java.util.*;

public class Pioche {
	ArrayList<Quartier> liste;
	
	public Quartier piocher()
	{
		return liste.get(0);
	}
	
	public void ajouter(Quartier nouveau)
	{
		liste.add(nouveau);
	}
	
	public int nombreElements()
	{
		return liste.size();
	}
	
	public void melanger()
	{
		
	}
}
