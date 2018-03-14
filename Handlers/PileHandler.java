package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Class permettant de gérer les différentes intéractions avec les
 *  7 paquets centraux de cartes
 *  
 * @author Natan Derroitte
 */
public class PileHandler extends ObjectHandler
{
	/**
	 * Méthode permettant d'ajoutée une carte au paquet 'pile' 
	 * considéré
	 * 
	 * @param cardToAdd
	 * 		  La carte à ajouter.
	 * @param pile
	 * 		  Le paquet de carte 'pile' dans lequel l'ajouter
	 * @return
	 * 		  Le nouveau paquet 'pile' mis à jour.
	 */
	public Cards add (Cards cardToAdd, Cards pile)
	{
		try
		{
			if(pile==null)
			{
				pile = cardToAdd;
				return pile;
			}
		
			Cards end_pile = super.endingCard(pile);
			end_pile.cardAbove = cardToAdd;
		}
		catch(Exception e)
		{
		System.err.println("Error while adding a card to a pile"); 
		}
		return pile;
	}
	/**
	 * Méthode permettant de retirer un paquet de carte d'une 'pile'.
	 * 
	 * @param foundation
	 * 		  La 'pile' d'où la carte doit être retirée
	 * @param cardPileNumber
	 * 		  L'indice de la premère carte à retirer.
	 * @return
	 * 		  La 'pile' mise à jour.
	 */
	public Cards remove(Cards pile, int cardPileNumber)
	{
		try
		{
			if(pile==null)
				return null;
			if(cardPileNumber ==0)
				return null;

			Cards newend = getCard(pile,cardPileNumber-1);
			newend.cardAbove=null;
		}
		catch(Exception e)
		{
		System.err.println("Error while removing a card from a pile"); 
		}
		return pile;
	}
}
