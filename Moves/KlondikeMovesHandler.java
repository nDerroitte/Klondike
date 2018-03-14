package be.ac.ulg.montefiore.oop.Moves;
import be.ac.ulg.montefiore.oop.Handlers.InitialisationKlondike;
import be.ac.ulg.montefiore.oop.Handlers.Verif;
import be.ac.ulg.montefiore.oop.Klondike.BoardState;
import be.ac.ulg.montefiore.oop.Klondike.Cards;
import be.ac.ulg.montefiore.oop.graphics.KlondikeEventsHandler;
import be.ac.ulg.montefiore.oop.graphics.NegativeNumberException;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
/**
 * Classe permettant l'affichage des différents évênements de jeu.
 * 
 * Beaucoup de méthodes ont déjà été documentés dans KlondikeEventsHandler,
 * je ne documente ici que les méthdes supplémentaires.
 * 
 * @author Natan Derroitte
 */
public class KlondikeMovesHandler implements KlondikeEventsHandler 
{
	//Déclaration des variables d'instances
	private InitialisationKlondike init 		 = new InitialisationKlondike();
	private KlondikeSwingView 	   ssv			      = null;
	private RefreshKlondike		   refresh 	  	 	  = new RefreshKlondike();
	private Verif 				   verif 			  = new Verif();
	private UpdateBoard 		   updateB			  = new UpdateBoard();
	private UpdateVariable		   updateV			  = new UpdateVariable();
	private BoardState 			   BS				  = new BoardState(null,null,null,null);
	
	private Cards stock 		   = null;
	private Cards waste 		   = null;
	private Cards pileTab 		[] = new Cards [8];
	private Cards foundationTab [] = new Cards [4];
	
	private Cards currentStock;
	private Cards currentWaste;
	private Cards currentPiletab 		[] = new Cards [7];
	private Cards currentFoundationtab 	[] = new Cards [4];
	
	private Cards firstCardToAdd 		= null;
	private Cards dstCard		 		= null;
	private Cards firstWasteCardToAdd	= null;
	private Cards endOfFoundation		= null;
	
	private int		 nbMove   			  = 0;
	private int 	 nbInPile			  = 0;
	private int 	 nbWasteMax			  = 24;
	
	private Cards [] resultTab			= new  Cards[3];
	
	/**
	 * Méthode permettant d'avoir accès à l'affichage des mouvements via 
	 * un objet de la classe KlondikeSwingView.
	 * 
	 * @param view
	 *		  Objet KlondikeSwingView.
	 */
	public void setView(KlondikeSwingView view)
	{
	 ssv = view;
	}
	
	public void newGame()
	{
		  //Réinitialisation des variables d'instances
		  nbMove		 =0;
		  nbWasteMax	 =24;
		  updateB.lastCardInStockAdded =false;
		  
		  pileTab = init.initialisation(pileTab);
		  stock =  pileTab[7];
		  waste = null;
		  
		  //Mise a jour visuelle de l'ensemble du plateau de jeu
		  ssv.stockEmpty(false);
		  refresh.pile(ssv,pileTab[0],0);
		  refresh.pile(ssv,pileTab[1],1);
		  refresh.pile(ssv,pileTab[2],2);
		  refresh.pile(ssv,pileTab[3],3);
		  refresh.pile(ssv,pileTab[4],4);
		  refresh.pile(ssv,pileTab[5],5);
		  refresh.pile(ssv,pileTab[6],6);
		  foundationTab[0] = null;
		  foundationTab[1] = null;
		  foundationTab[2] = null;
		  foundationTab[3] = null;
		  refresh.foundation(ssv,foundationTab[0],0);
		  refresh.foundation(ssv,foundationTab[1],1);
		  refresh.foundation(ssv,foundationTab[2],2);
		  refresh.foundation(ssv,foundationTab[3],3);
		  refresh.waste(ssv, waste,stock);
		  try 
		  {
			ssv.updateMoves(nbMove);
		  } catch (NegativeNumberException e) 
		  {
			System.err.println("Nombres de moves initial négatif.");
		  }
		  ssv.refreshWindow();
		  
		  //Sauvegarde du plateau de jeu courant
		  currentStock			 = BS.safeStock(stock);
		  currentWaste			 = BS.safeWaste(waste);
		  currentPiletab		 = BS.safePileTab(pileTab);
		  currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
		  BS = new BoardState (currentStock, currentWaste, currentPiletab,
				  currentFoundationtab);
	  }
	public void clickStock() 
	{
		  //Modifications des variables principales d'instances
		  resultTab = updateB.clickOnStock(stock, waste, ssv, nbWasteMax, resultTab);
		  stock = resultTab[0];
		  waste = resultTab[1];
		  
		  //Mise à jour visuelle
		  refresh.waste(ssv, waste, stock);
		  try 
			{
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
		  {
			System.err.println("Nombres de moves négatif.");
		  }
		  ssv.refreshWindow();

		  //Sauvegarde du plateau de jeu courrant
		  currentStock			 = BS.safeStock(stock);
		  currentWaste			 = BS.safeWaste(waste);
		  currentPiletab		 = BS.safePileTab(pileTab);
		  currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
		  BS = new BoardState (BS,currentStock, currentWaste, currentPiletab,
				  currentFoundationtab,false);
		}
	public void moveWasteToTableau(int tableau)
	{
		//Modifications des variables de vérifications
		firstWasteCardToAdd = updateV.firstWasteCard(waste,stock);
		dstCard 		    = updateV.endingCard(pileTab[tableau]);

		//Vérification
		if (verif.pile(firstWasteCardToAdd, dstCard)==true)
		{
			//Modification  des variables principales d'instances
			resultTab = updateB.moveWasteToTableau(waste, stock, 
					pileTab[tableau], firstWasteCardToAdd, ssv, resultTab);
			stock			  = resultTab[0];
			waste			  = resultTab[1];
			pileTab[tableau]  = resultTab[2];
			
			nbWasteMax--;
			//On reset la key
			updateB.lastCardInStockAdded =false;
			
			//Mise à jour visuelle
			refresh.pile(ssv,pileTab[tableau],tableau);
			refresh.waste(ssv,waste,stock);
			try {
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
			ssv.refreshWindow();
			
			//Sauvegarde du plateau de jeu courrant
			currentStock			 = BS.safeStock(stock);
			currentWaste			 = BS.safeWaste(waste);
			currentPiletab			 = BS.safePileTab(pileTab);
			currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
			BS = new BoardState (BS,currentStock, currentWaste, currentPiletab,
					  currentFoundationtab,true);
		}

	}
	public void moveWasteToFoundation(int foundation)
	{	
		//Modification des variables de vérifications
		firstWasteCardToAdd = updateV.firstWasteCard(waste,stock);
		endOfFoundation 	= updateV.endingCard(foundationTab[foundation]);
		
		//Vérification
		if (verif.foundation(firstWasteCardToAdd, endOfFoundation)==true)
		{
			//Mise à jour des variables principales d'instances
			resultTab=updateB.moveWasteToFoundation(stock, waste, 
					foundationTab[foundation], firstWasteCardToAdd, ssv, resultTab);
			stock						= resultTab[0];
			waste						= resultTab[1];
			foundationTab[foundation]	= resultTab[2];
			nbWasteMax--;
			
			//Mise à jour visuelle
			refresh.waste(ssv, waste, stock);
			refresh.foundation(ssv, foundationTab[foundation], foundation);
			if(verif.win(foundationTab)==true)
				ssv.win();
			try {
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
			ssv.refreshWindow();
			
			//Sauvegarde du plateau de jeu courant
			currentStock			 = BS.safeStock(stock);
			currentWaste			 = BS.safeWaste(waste);
			currentPiletab			 = BS.safePileTab(pileTab);
			currentFoundationtab	 = BS.safeFoundationTab(foundationTab);  
			BS= new BoardState (BS,currentStock, currentWaste, currentPiletab,
					  currentFoundationtab,true);
			//Reset la key
			updateB.lastCardInStockAdded =false;
		}
	}
	public void moveTableauToFoundation(int tableau, int foundation)
	{
		//Modifications des variables de vérifications
		firstCardToAdd 	= updateV.endingCard(pileTab[tableau]);
		endOfFoundation = updateV.endingCard(foundationTab[foundation]);
		
		//Vérification
		if (verif.foundation(firstCardToAdd, endOfFoundation)==true)
		{		
			//Modifications des variables principales d'instances
			resultTab = updateB.moveTableauToFoundation(foundationTab[foundation],
					pileTab[tableau], firstCardToAdd, resultTab);
			foundationTab[foundation]	= resultTab[0];
			pileTab[tableau] 			= resultTab[1];

			//Mise à jour visuelle
			refresh.pile(ssv,pileTab[tableau],tableau);
			refresh.foundation(ssv, foundationTab[foundation], foundation);
			try {
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
			//Vérification d'une victoire
			if(verif.win(foundationTab)==true)
				ssv.win();
			ssv.refreshWindow();
			
			//On reset la key
			updateB.lastCardInStockAdded =false;
			
			//Sauvegarde du plateau de jeu actuel
			currentStock			 = BS.safeStock(stock);
			currentWaste			 = BS.safeWaste(waste);
			currentPiletab			 = BS.safePileTab(pileTab);
			currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
			BS = new BoardState (BS,currentStock, currentWaste, currentPiletab,
					  currentFoundationtab,false);
		}
	}
	public void moveTableauToTableau(int tableauSrc,int numCards,int tableauDst)
	{
		//Modifications des variables de vérifications
		nbInPile	   = updateV.getCount(pileTab[tableauSrc])-numCards;
		firstCardToAdd = updateV.getCard(pileTab[tableauSrc],nbInPile);
		dstCard 	   = updateV.endingCard(pileTab[tableauDst]);
		
		//Vérification
		if (verif.pile(firstCardToAdd, dstCard)==true)
		{
			//Mise à jour des variables princiaples d'instances
			resultTab = updateB.moveTableauToTableau(pileTab[tableauSrc], 
					pileTab[tableauDst], firstCardToAdd,nbInPile, resultTab);
			pileTab[tableauSrc] = resultTab[0];
			pileTab[tableauDst] = resultTab[1];
			
			//Mise à jour visuelle
			refresh.pile(ssv,pileTab[tableauSrc],tableauSrc);
			refresh.pile(ssv,pileTab[tableauDst],tableauDst);
			try {
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
			ssv.refreshWindow();
			
			//Sauvegarde du plateau de jeu
			currentStock			 = BS.safeStock(stock);
			currentWaste			 = BS.safeWaste(waste);
			currentPiletab			 = BS.safePileTab(pileTab);
			currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
			BS = new BoardState (BS,currentStock, currentWaste, currentPiletab,
					currentFoundationtab,false);
		}
	}
	public void moveFoundationToTableau(int foundation,int tableau)
	{
		//Mise à jour des variables de vérifications
		endOfFoundation = updateV.endingCard(foundationTab[foundation]);
		dstCard 		= updateV.endingCard(pileTab[tableau]);
		
		//Vérification
		if (verif.pile(endOfFoundation, dstCard)==true) 
		{
			//Mise à jour des variables princiaples d'instances
			updateB.moveFoundationToTableau(pileTab[tableau], 
					foundationTab[foundation],endOfFoundation, resultTab);
			pileTab[tableau] 		  = resultTab[0];
			foundationTab[foundation] = resultTab[1];
			
			//Mise à jour visuelle
			refresh.pile(ssv,pileTab[tableau],tableau);
			refresh.foundation(ssv,foundationTab[foundation],foundation);
			try {
				ssv.updateMoves(++nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
			ssv.refreshWindow();
			
			//Sauvegarde du plateau de jeu.
			currentStock			 = BS.safeStock(stock);
			currentWaste			 = BS.safeWaste(waste);
			currentPiletab		 = BS.safePileTab(pileTab);
			currentFoundationtab	 = BS.safeFoundationTab(foundationTab);
			BS = new BoardState (BS,currentStock, currentWaste, currentPiletab,
					currentFoundationtab,false);
		}
	}
	public void undo()
	{
		//Diminution du nombre de coups
			try {
				ssv.updateMoves(--nbMove);
			} catch (NegativeNumberException e) 
			  {
				System.err.println("Nombres de moves négatif.");
			  }
		//Si le dernier évênement a diminué la taille du waste, on l'augemente
		if(BS.moveFromWaste==true)
			nbWasteMax++;
		
		//Retour à la version passée du plateau de jeu
		BS = BS.oldBS;
		if(BS.stock!=null)
			stock = (Cards)BS.stock.clone();
		else
			stock = null;
		if(BS.waste!=null)
			waste = (Cards)BS.waste.clone();
		else
			waste = null;
		for (int i=0;i<7;i++)
		{
		  if (BS.pileTab[i]!=null)
			  pileTab[i] = (Cards)BS.pileTab[i].clone();
		  else
			  pileTab[i]=null;
		}
		for (int i=0;i<4;i++)
	    {
		  if(BS.foundationTab[i]!=null)
			  foundationTab[i] = (Cards)BS.foundationTab[i].clone();
		  else
			  foundationTab[i]=null;
		}
		
		//Mise à jour visuelle
		refresh.pile(ssv,pileTab[0],0);
		refresh.pile(ssv,pileTab[1],1);
		refresh.pile(ssv,pileTab[2],2);
		refresh.pile(ssv,pileTab[3],3);
		refresh.pile(ssv,pileTab[4],4);
		refresh.pile(ssv,pileTab[5],5);
		refresh.pile(ssv,pileTab[6],6);
		refresh.foundation(ssv,foundationTab[0],0);
		refresh.foundation(ssv,foundationTab[1],1);
		refresh.foundation(ssv,foundationTab[2],2);
		refresh.foundation(ssv,foundationTab[3],3);
		refresh.waste(ssv, waste,stock);
		if(stock ==null)
			ssv.stockEmpty(true);
		else
			ssv.stockEmpty(false);
		ssv.refreshWindow();

	}
	public String getName(){
		return "Natan Derroitte";
	}
}