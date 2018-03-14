package be.ac.ulg.montefiore.oop.Klondike;
import be.ac.ulg.montefiore.oop.Moves.KlondikeMovesHandler;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
/**
 * Class principal permettant de lancer une nouvelle partie
 * @author Natan Derroitte.
 */
public class Main 
{
	public static void main(String[] args) 
	{
		try 
		{
			KlondikeMovesHandler handler = new KlondikeMovesHandler();
			KlondikeSwingView ssv = new KlondikeSwingView(handler);
			handler.setView(ssv);
			handler.newGame();
		}
		catch(Exception e)
		{
		System.err.println("Error while creating a new game"); 
		}
	}

}
