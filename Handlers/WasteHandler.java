package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Class permettant de gérer les différentes intéractions avec le
 *  'waste'
 *  
 * @author Natan Derroitte
 */
public class WasteHandler extends ObjectHandler 
{
	/**
	 * Méthode servant à ajouter jusqu'à 3 cartes (le maximum possible)
	 * du haut du 'stock' dans le 'waste'
	 * 
	 * @param sotck 
	 * 		  Le stock de cartes
	 * @param waste
	 * 		  Le 'waste' de cartes
	 * @return
	 * 		  Le waste remplit des nouvelles cartes ajoutées
	 */
	public Cards add (Cards stock, Cards waste)
	{
		try
		{
			int compteur = super.getCount(stock);
			Cards current_stock = stock; 
			Cards temp=null;
		
			if(compteur ==1 || compteur ==2 || compteur ==3)
				waste = addToWaste (stock, waste);
			else
			{
				for(int i=0; i<(compteur-4);i++)
					current_stock = current_stock.cardAbove;
				temp = current_stock;
				current_stock = current_stock.cardAbove;
				temp.cardAbove = null;
				waste = addToWaste(current_stock,waste);
			}
		}
		catch(Exception e)
		{
		System.err.println("Error while adding a card to Waste"); 
		}
		return waste;
	}
	/**
	 * Méthode servant à ajouter une seule carte du haut  du 'stock'
	 *  dans le 'waste'
	 * 
	 * @param sotck 
	 * 		  Le stock de cartes
	 * @param waste
	 * 		  Le 'waste' de cartes
	 * @return
	 * 		  Le waste remplit de la nouvelle carte ajoutée
	 */
	public Cards addOneToWaste (Cards stock, Cards waste)
	{
		int compteur = super.getCount(stock);
		Cards newWaste = super.endingCard(stock);
		newWaste.cardAbove = waste;
		
		if (compteur ==1)
			stock=null;
		else
			super.getCard(stock, compteur-2).cardAbove=null;
		
		return newWaste;
	}
	/**
	 * Méthode servant à retourner les cartes à afficher dans le 
	 * waste si elles sont encore cachée.
	 * 
	 * @param stockToFlip
	 * 		  Les cartes à retourner
	 */
	public void flipStock (Cards stockToFlip)
	{
		Cards current = stockToFlip;
		
		while(current.cardAbove != null)
		 {
			if( current.isHidden()==true)
				current.flip();
			current= current.cardAbove;
		 }
		current.flip();
	}
	/**
	 * Méthode utilisé par addOneToWaste (Cards stock, Cards waste) et
	 * add (Cards stock, Cards waste) permettant d'ajouter au 'waste' les 
	 * cartes (ou la dans le premier cas et si le stock ne contenan qu'une
	 * seule carte dans le second) enlevées au stock.
	 * 
	 * @param stock_to_add
	 * 		  Les cartes à ajouter.
	 * @param waste
	 * 		  Le waste où l'on ajoute les cartes
	 * @return
	 * 		  Le waste fraichement remplit.
	 */
	private Cards addToWaste (Cards stock_to_add, Cards waste)
	{
		flipStock (stock_to_add);
		if (waste ==null)
			waste = stock_to_add;
		else
		{
			Cards current = super.endingCard(stock_to_add); 
			current.cardAbove = waste;
			waste = stock_to_add;
		}
		
		return waste;
	}
	/**
	 * Méthode permettant de retirer la dernière carte du waste.
	 * 
	 * @param waste
	 * 		  Le 'waste' d'où la carte doit être retirée.
	 * @param stock
	 * 		  Le stock de carte.
	 * @return
	 * 		  Le 'waste' mise à jour.
	 */
	public Cards remove (Cards waste, Cards stock)
	{
		try
		{
			if(waste==null)
				return null;
		
			int compteur = super.getCount(waste);
		//Il n'y a qu'une carte dans le waste
			if (compteur == 1)
				return null;
		// Il n'y a que deux cartes dans le waste.
			else if (compteur ==2)
				waste.cardAbove=null;
			else 
			{
				if( stock == null && compteur%3==1)
					return waste.cardAbove;
				if( stock == null && compteur%3==2)
					waste.cardAbove=waste.cardAbove.cardAbove;
				else
					waste.cardAbove.cardAbove= waste.cardAbove.cardAbove.
					cardAbove;
			}
		}
		catch(Exception e)
		{
		System.err.println("Error while removing a card from Waste"); 
		}
		return waste;
	}
}
