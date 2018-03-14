package be.ac.ulg.montefiore.oop.Moves;

import be.ac.ulg.montefiore.oop.Handlers.Handler;
import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Classe servant à mettre a jour les variables de modifications
 * du plateau de jeu
 * 
 * @author Natan Derroitte
 */
public class UpdateVariable extends Handler
{
	/**
	 * Méthode permettant d'obtenir la dernière carte du waste visuelle, 
	 * soit la première à bouger en cas de mouvement à partir du waste.
	 * 
	 * @param waste
	 * 		  Le paquet 'waste'.
	 * @param stock
	 * 		  Le stock de carte
	 * @return
	 * 		  Première carte à bouger en cas de mouvement à partir du waste.
	 */
	public Cards firstWasteCard (Cards waste,Cards stock)
	{
		if(waste==null)
			return null;
		
		int compteur = super.getCount(waste);
		Cards firstWasteCard;
		
		if (compteur == 1 || ( stock == null && compteur%3==1))
			firstWasteCard = waste;
		else if (compteur ==2||( stock == null && compteur%3==2))
			firstWasteCard = waste.cardAbove;
		else 
			firstWasteCard = waste.cardAbove.cardAbove;
		
		return firstWasteCard;
	}
}
