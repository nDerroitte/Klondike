package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Superclass, parent des classes d'handler de waste, pile et foundation
 * ainsi que Verif.
 * Permet d'implémenter des méthodes communes à ces 4 sous-classes
 * 
 * @author Natan Derroitte
 */
public class Handler 
{
	/**
	 * Méthode vérifiant si le paquet de carte considéré est vide
	 * 
	 * @param stock
	 * 		  Le paquet de carte à vérifier.
	 * @return
	 * 		  true si il est vide
	 * 		  false sinon.
	 */
	public boolean IsEmpty(Cards stock)
	{
		if (stock==null)
			return true;
		
		return false;
	}
	/**
	 * Méthode servant à obtenir le nombre de carte dans un paqueet
	 * @param current
	 * 		  Le paquet à vérifier
	 * @return
	 * 		  Le nombre de cartes.
	 */
	public int getCount ( Cards current)
	{
		if(current == null)
			return 0;
		
		int compteur =0;
		final Cards safe = current;
		try
		{
			while(current.cardAbove != null)
			{
				compteur++;
				current= current.cardAbove;
			}
			compteur++;
			current = safe;
		}
		catch(Exception e)
		{
		System.err.println("Error while getting the number of cards"); 
		}
		return compteur;
	}
	/**
	 * Méthode servant à retourner la dernière carte d'un paquet.
	 * 
	 * @param current
	 * 		  Le paquet en question.
	 * @return
	 * 		  La dernière carte.
	 */
	public Cards endingCard (Cards current)
	{
		if (current == null)
			return null;
		
		Cards end = current;
		try
		{
			while(end.cardAbove != null)
				end= end.cardAbove;
		}
		catch(Exception e)
		{
		System.err.println("Error while getting the last card"); 
		}
		return end;
	}
	/**
	 * Méthode servant à obtenir un carte spécifique dans un paquet.
	 * 
	 * @param current
	 * 		  Le paquet en question
	 * @param num
	 * 		  L'indice de la carte dans le paquet.
	 * @return
	 * 		  La carte en question.
	 */
	public Cards getCard (Cards current,int num)
	{
		if (current == null)
			return null;
		if (num<0)
		{
			System.err.println("Card number is above 0 in getCard method"); 
			return null;
		}
		Cards card = current;
		try
		{
			for(int i=0; i<num;i++)
				card= card.cardAbove;
		}
		catch(Exception e)
		{
		System.err.println("Error while getting a specific Card"); 
		}
		return card;
	}
}
