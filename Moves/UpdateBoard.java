package be.ac.ulg.montefiore.oop.Moves;

import be.ac.ulg.montefiore.oop.Handlers.FoundationHandler;
import be.ac.ulg.montefiore.oop.Handlers.PileHandler;
import be.ac.ulg.montefiore.oop.Handlers.WasteHandler;
import be.ac.ulg.montefiore.oop.Klondike.Cards;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
/**
 * Classe implémentant les méthodes servant à mettre à jour les 
 * variables d'instances de notre plateau de jeu lors d'un mouvement
 * 
 * @author Natan Derroitte
 */
public class UpdateBoard 
{
	//Variables d'instances
	private WasteHandler		   waste_handler 	  = new WasteHandler();
	private PileHandler 		   pile_handler  	  = new PileHandler();
	private FoundationHandler	   foundation_handler = new FoundationHandler();
	
	public boolean lastCardInStockAdded = false;
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir cliqué sur le stock
	 * 
	 * @param stock
	 * 		  Le stock de carte
	 * @param waste
	 * 		  Le paquet'waste'.
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param nbWasteMax
	 * 		  Taille maximale du waste
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] clickOnStock(Cards stock, Cards waste ,
			KlondikeSwingView ssv, int nbWasteMax, Cards[]  tabToReturn)
	{
		if (waste_handler.IsEmpty(stock)==true)
		  {
			  if(waste == null)//Tout est vide
				  ssv.stockEmpty(true);
			  else 
			  {
				  stock = waste;
				  waste_handler.flipStock(stock);
				  waste = null;
				  ssv.stockEmpty(false);
			  }
		  }	
		  else
		  {
			 waste = waste_handler.add (stock,waste);
			 if (waste_handler.getCount(waste)==nbWasteMax)
			 {
				 stock = null;
				 ssv.stockEmpty(true);
			 }
		  }
		
		tabToReturn[0]= stock;
		tabToReturn[1]= waste;
		
		return tabToReturn;
	}
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir déplacé une carte du 'waste' vers un paquet central (pile)
	 * 
	 * @param stock
	 * 		  Le stock de carte
	 * @param waste
	 * 		  Le paquet'waste'.
	 * @param pile
	 * 		  Le paquet central (pile)
	 * @param firstWasteCardToAdd
	 * 		  La carte du waste à bouger. Cette variable aurait pu être recalculée à partir
	 * 		  du waste mais est prise en argument pour éviter les calculs successifs inutiles
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] moveWasteToTableau(Cards waste, Cards stock, 
			Cards pile, Cards firstWasteCardToAdd, KlondikeSwingView ssv,
			Cards[]  tabToReturn)
	{
		pile = pile_handler.add(firstWasteCardToAdd,pile);
		waste = waste_handler.remove(waste,stock);
		if(stock!=null)
		{
			waste = waste_handler.addOneToWaste (stock, waste);
			if(stock.value == waste.value)//On a ajouté la dernière carte
			{
				stock = null;
				ssv.stockEmpty(true);
				lastCardInStockAdded =true;
			}
		}
		firstWasteCardToAdd.cardAbove=null;
		//Si c'était la dernière carte, on réinitialise le stock : 
		if (stock ==null && waste_handler.getCount(waste)%3==0
				&& lastCardInStockAdded ==false)
		{
			if(waste !=null)//tout n'est  pas vide
			{
				stock = waste;
				waste_handler.flipStock(stock);
				waste = null;
				ssv.stockEmpty(false);
			}
		}
	
	tabToReturn[0] = stock;
	tabToReturn[1] = waste;
	tabToReturn[2] = pile;
	
	return tabToReturn;
	}
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir déplacé une carte du 'waste' vers un paquet supérieur (foundation)
	 * 
	 * @param stock
	 * 		  Le stock de carte
	 * @param waste
	 * 		  Le paquet'waste'.
	 * @param foundation
	 * 		  Le paquet supérieur (foundation)
	 * @param firstWasteCardToAdd
	 * 		  La carte du waste à bouger. Cette variable aurait pu être recalculée à partir
	 * 		  du waste mais est prise en argument pour éviter les calculs successifs inutiles
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] moveWasteToFoundation(Cards stock, Cards waste, Cards
			foundation,Cards firstWasteCardToAdd, KlondikeSwingView ssv,
			Cards[] tabToReturn)
	{
		foundation= foundation_handler.add(firstWasteCardToAdd, 
				foundation);//commande sur deux lignes
		waste = waste_handler.remove(waste,stock);
		if(stock!=null)
		{
			waste = waste_handler.addOneToWaste (stock, waste);
			if(stock.value == waste.value)//On a ajouté la dernière 
			{
				stock = null;
				ssv.stockEmpty(true);
				lastCardInStockAdded =true;
			}
		}
		firstWasteCardToAdd.cardAbove=null;
		//Si c'était la dernière carte, on réinitialise le stock : 
		if (stock ==null && waste_handler.getCount(waste)%3==0 
				&& lastCardInStockAdded ==false)//commande sur deux lignes
		{
			if(waste !=null)//tout n'est  pas vide
			{
				stock = waste;
				waste_handler.flipStock(stock);
				waste = null;
				ssv.stockEmpty(false);
			}
		}
		tabToReturn[0]=stock;
		tabToReturn[1]=waste;
		tabToReturn[2]=foundation;
		
		return tabToReturn;
	}
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir déplacé une carte d'un paquet central (pile) vers un paquet 
	 * supérieur (foundation)
	 * 
	 * @param foundation
	 * 		  Le paquet supérieur (foundation)
	 * @param pile
	 * 		  Le paquet central (pile)
	 * @param firstCardToAdd
	 * 		  La carte du paquet central à bouger. Cette variable aurait pu être recalculée à
	 * 		  partir de pile central mais est prise en argument pour éviter les calculs 
	 * 		  successifs inutiles
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] moveTableauToFoundation(Cards foundation, Cards pile,
			Cards firstCardToAdd, Cards[] tabToReturn)
	{
		foundation= foundation_handler.add(firstCardToAdd,
				foundation);
		pile = pile_handler.remove(pile,pile_handler.getCount(pile)-1);
		
		tabToReturn[0] = foundation;
		tabToReturn[1] = pile;
		
		return tabToReturn;
	}
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir déplacé une carte(ou plusieurs) d'un paquet central (pile) vers un
	 *  autre. 
	 * 
	 * @param srcPile
	 * 		  Le paquet central d'ou vient la carte (ou les cartes)
	 * @param dstPile
	 * 		  Le paquet central vers où la(les) carte(s) va
	 * @param firstCardToAdd
	 * 		  La première carte du paquet central à bouger. Cette variable aurait pu être 
	 * 		  recalculée à partir de pile central mais est prise en argument pour éviter 
	 * 		  les calculs successifs inutiles
	 * @param nbInPile
	 * 		  L'indice de firstCardToAdd dans le srcPile
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] moveTableauToTableau (Cards srcPile, Cards dstPile, 
			Cards firstCardToAdd, int nbInPile, Cards[] tabToReturn)
	{
		dstPile = pile_handler.add(firstCardToAdd, dstPile);
		srcPile = pile_handler.remove(srcPile, nbInPile);
		
		tabToReturn[0] = srcPile;
		tabToReturn[1] = dstPile;
		
		return tabToReturn;
	}
	/**
	 * Méthode servant à mettre à jour les variables principales d'instance du plateau de
	 * jeu arpès avoir déplacé une carte d'un paquet supérieur (pile) vers un paquet 
	 * central (pile)
	 * 
	 * @param pile
	 * 		  Le paquet central (pile)
	 * @param foundation
	 * 		  Le paquet supérieur (foundation)
	 * @param endOfFoundation
	 * 		  La carte du paquet supérieur à bouger. Cette variable aurait pu être 
	 * 		  recalculée à partir de pile central mais est prise en argument pour éviter 
	 * 	  	  les calculs successifs inutiles
	 * @param tabToReturn
	 * 		  Tableau de résulats à remplir
	 * @return
	 * 		  Tableau contenant les résultats
	 */
	public Cards[] moveFoundationToTableau(Cards pile, Cards foundation,
			Cards endOfFoundation,Cards[] tabToReturn)
	{
		pile	   = pile_handler.add(endOfFoundation, pile);
		foundation = foundation_handler.remove(foundation);
		
		
		tabToReturn[0] = pile;
		tabToReturn[1] = foundation;
		
		return tabToReturn;
	}
}
