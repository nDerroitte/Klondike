package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Class permettant de gérer les différentes intéractions avec les
 *  'foundations'
 *  
 * @author Natan Derroitte
 */
public class FoundationHandler extends ObjectHandler
{
	/**
	 * Méthode permettant d'ajoutée une carte à la 'foundation' 
	 * considérée
	 * 
	 * @param cardToAdd
	 * 		  La carte à ajouter.
	 * @param foundation
	 * 		  Le paquet de carte 'foundation' dans lequel l'ajouter
	 * @return
	 * 		  Le nouveau paquet 'founation' mis à jour.
	 */
	public Cards add (Cards cardToAdd, Cards foundation)
	{
		try
		{
			if(foundation==null)
			{
				foundation = cardToAdd;
				return foundation;
			}
		
			Cards end_foundation = super.endingCard(foundation);
			end_foundation.cardAbove = cardToAdd;
		}
		catch(Exception e)
		{
		System.err.println("Error while adding a card to a foundation"); 
		}
		return foundation;
	}
	/**
	 * Méthode permettant de retirer la dernière carte d'une 'foundation'.
	 * 
	 * @param foundation
	 * 		  La 'foundation' d'où la carte doit être retirée
	 * @return
	 * 		  La 'foundation' mise à jour.
	 */
	public Cards remove (Cards foundation)
	{
		try
		{
			if (foundation==null)
				return null;
			if(foundation.cardAbove==null)
				return null;
		
			Cards current = foundation;
		
			while(current.cardAbove.cardAbove!=null)
				current = current.cardAbove;
			current.cardAbove=null;
		}
		catch(Exception e)
		{
		System.err.println("Error while removing a card from a foundation"); 
		}
		return foundation;
	}
}
