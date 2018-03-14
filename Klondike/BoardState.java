package be.ac.ulg.montefiore.oop.Klondike;
/**
 * Cette classe permet la création d'un historique de jeu, appelé BoardState.
 * Elle permet également de sauvegarder les instances du jeu en les clonant
 * dans des objets qu'elle crée.
 * 
 * @author Natan Derroitte
 */
public class BoardState 
{
	//Variable d'instances
	public Cards stock;
	public Cards waste;
	public Cards [] pileTab;
	public Cards [] foundationTab;
	public BoardState oldBS;
	public boolean moveFromWaste;
	
	//Constructeurs
	public BoardState (Cards givenStock, Cards givenWaste, Cards [] givenPiletab
		,Cards [] givenFoundationtab)
	{
		stock = givenStock;
		waste = givenWaste;
		pileTab= givenPiletab;
		foundationTab = givenFoundationtab;
		oldBS = null;
		moveFromWaste = false;
	}
	public BoardState (BoardState OldBS,Cards givenStock, Cards givenWaste, 
			Cards[] givenPiletab,Cards [] givenFoundationtab, boolean
			Key)	
	{
		stock = givenStock;
		waste = givenWaste;
		pileTab= givenPiletab;
		foundationTab = givenFoundationtab;
		oldBS = OldBS;
		moveFromWaste = Key;
	}	
	
	/**
	 * Methode servant à copier le 'stock'.
	 * @param givenStock 
	 * 		  Le 'stock' a copier.
	 * @return 
	 * 		  La copie du 'stock'.
	 */
	public Cards safeStock(Cards givenStock)
	{
		  Cards currentStock;
		  
		  if(givenStock!=null)
			  currentStock = (Cards)givenStock.clone();
		  else
			  currentStock = null;
		  
		  return currentStock;
	}
	/**
	 * Methode servant à copier le 'waste'.
	 * @param givenWaste
	 * 		  Le 'waste' a copier.
	 * @return 
	 * 		  La copie du 'waste'.
	 */
	public Cards safeWaste(Cards givenWaste)
	{
		  Cards currentWaste;
		  if(givenWaste!=null)
			  currentWaste = (Cards)givenWaste.clone();
		  else
			  currentWaste = null;
		  
		  return currentWaste;
	}
	/**
	 * Methode servant à copier l'ensemble des 7 tas de packets de cartes
	 * 
	 * @param givenPileTab
	 * 		  Un tableau comprenant les 7 tas à copier
	 * @return
	 * 		  Un tableau contenant les 7 tas copiés.
	 */
	public Cards[] safePileTab(Cards[] givenPileTab)
	{
		  Cards [] currentPileTab = new Cards [7];
		  for (int i=0;i<7;i++)
		  {
			  if (givenPileTab[i]!=null)
				  currentPileTab[i] = (Cards)givenPileTab[i].clone();
			  else
				  currentPileTab[i]=null;
		  }
		  
		  return currentPileTab;
	}
	/**
	 * Methode servant à copier l'ensemble des 4 'foundations'.
	 * 
	 * @param givenFoundationTab
	 * 		  Un tableau comprenant les 4 'foundations' à copier
	 * @return
	 * 		  Un tableau contenant les 4 'foundations' copiées.
	 */
	public Cards[] safeFoundationTab(Cards[] givenFoundationTab)
	{
		  Cards [] currentFoundationtab  = new Cards [4];
		  for (int i=0;i<4;i++)
		  {
			  if(givenFoundationTab[i]!=null)
				  currentFoundationtab[i] = (Cards)givenFoundationTab[i].
				  clone();
			  else
				  currentFoundationtab[i]=null;
		  }	
		  
		  return currentFoundationtab;
	}
}
