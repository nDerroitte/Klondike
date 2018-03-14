package be.ac.ulg.montefiore.oop.Moves;

import be.ac.ulg.montefiore.oop.Klondike.Cards;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
import be.ac.ulg.montefiore.oop.graphics.NullArrayException;
import be.ac.ulg.montefiore.oop.graphics.TooManyCardsException;
import be.ac.ulg.montefiore.oop.graphics.UnknownCardException;
import be.ac.ulg.montefiore.oop.graphics.UnknownFoundationException;
import be.ac.ulg.montefiore.oop.graphics.UnknownTableauException;
/**
 * Classe permettant les mise à jour visuelles, donnant au méthodes de 
 * KlondikeSwingView des formats qu'elle accepte
 * 
 * @author Natan Derroitte
 */
public class RefreshKlondike 
{
	/**
	 * Méthode permettant la mise à jour visuelle d'un paquet de carte 
	 * central (pile).
	 * 
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param pile
	 * 		  Paquet de carte à mettre à jour
	 * @param tableau_num
	 * 		  Indice du paquet de carte à mettre à jour
	 */
	public void pile (KlondikeSwingView ssv, Cards pile, int tableau_num)
	{
		//Transformation sous un format convebable
		int [] printablepile = createPrintablePile(pile);
		//Affichage
		try 
		{
			ssv.tableauCards(tableau_num, printablepile);
		} 
		catch (UnknownTableauException e)
		{
			System.err.println("Numéro de tableau incorrect");
		}
		catch (NullArrayException e)
		{
			System.err.println("Tableau d'affichage de pile de taille nulle");
		}
		catch (TooManyCardsException e)
		{
			System.err.println("Tableau d'affichage de pile de taille "
					+ "incorrect");
		}
		catch (UnknownCardException e)
		{
			System.err.println("Numéro de carte incorrecte");
		} 
	}
	/**
	 * Méthode permettant la mise à jour visuelle d'un paquet de carte 
	 * waste.
	 * 
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param waste
	 * 		  Paquet de carte à mettre à jour
	 * @param stock
	 * 		  Stock de carte
	 */
	public void waste (KlondikeSwingView ssv, Cards waste, Cards stock)
	{
		//Transformation sous un format convenable
		int[] printable_waste = createPrintableWaste(waste,stock);
		//Affichage
		try {
			ssv.wasteCards(printable_waste);
		} 
		catch (NullArrayException e)
		{
			System.err.println("Tableau d'affichage de waste de taille nulle");
		}
		catch (TooManyCardsException e)
		{
			System.err.println("Tableau d'affichage de waste de taille "
					+ "incorrect");
		}
		catch (UnknownCardException e)
		{
			System.err.println("Numéro de carte incorrecte");
		} 
	}
	/**
	 * Méthode permettant la mise à jour visuelle d'un paquet de carte 
	 * supérieur (foundation).
	 * 
	 * @param ssv
	 * 		  Objet KlondikeSwingView
	 * @param foundation
	 * 		  Paquet de carte à mettre à jour
	 * @param foundationNum
	 * 		  Indice du paquet de carte à mettre à jour
	 */
	public void foundation (KlondikeSwingView ssv, Cards foundation, 
			int foundationNum)
	{
		//Transformation sous un format acceptable par ssv
		int[] printable_foundation = createPrintableFoundation (foundation);
		//Affichage
		try {
			ssv.foundationCards(foundationNum, printable_foundation);
		}
		catch (UnknownFoundationException e)
		{
			System.err.println("Numéro de foundation incorrecte");
		}
		catch (NullArrayException e)
		{
			System.err.println("Tableau d'affichage de foundation de taille "
					+ "nulle");
		}
		catch (TooManyCardsException e)
		{
			System.err.println("Tableau d'affichage de foundation de taille "
					+ "incorrect");
		}
		catch (UnknownCardException e)
		{
			System.err.println("Numéro de carte incorrecte");
		}
	}
/**
 * Méthode permettant de retourner une carte si elle est cachée
 * 
 * @param current
 * 		  Carte à éventuellement retourner
 */
	private void flipIfNeeded ( Cards current)
	{
		if(current.isHidden()==true)
			current.flip();
	}
	/**
	 * Méthode permettant d'obtenir le nombre de carte dans un paquet
	 * 
	 * @param current
	 * 		  Le paquet à vérifier.
	 * @return
	 * 		  Le nombre de carte.
	 */
	private int getCompteur ( Cards current)
	{
		if (current ==null)
			return 0;
		
		int compteur =0;
		final Cards safe = current;
		
		while(current.cardAbove != null)
		 {
			 compteur++;
			 current= current.cardAbove;
		 }
		compteur++;
		//Si la dernière valeur est cachée, on l'affiche.
		flipIfNeeded(current);
		current = safe;
		
		return compteur;
	}
	/**
	 * Méthode permettant la transformation d'un paquet de carte central
	 * (pile) en un tableau d'entier acceptable pour l'affichage
	 * 
	 * @param current
	 * 		  Le paquet.
	 * @return
	 * 		  Le tableau d'entier.
	 */
	private int[] createPrintablePile (Cards current)
	{
		final int compteur = getCompteur(current);
		final Cards safe = current;
		int[] printablepile = new int[compteur];
		
		for(int i =0; i<compteur;i++)
		 {
			 if(current.isHidden()==true)
				 printablepile[i]=52;
			 else
				 printablepile[i] = current.value;
			 current = current.cardAbove;
		 }
		current = safe;
		
		return printablepile;
	}
	/**
	 * Méthode permettant la transformation d'un paquet de carte 'waste'
	 *  en un tableau d'entier acceptable pour l'affichage
	 * 
	 * @param waste
	 * 		  Le paquet waste à transformer.
	 * @param stock
	 * 		  Le stock de carte.
	 * @return
	 * 		  Le tableau d'entier.
	 */
	private int[] createPrintableWaste(Cards waste, Cards stock)
	{
		int compteur = getCompteur(waste);
		int printable_waste[];
		
		if(compteur ==0 )
		{
			printable_waste = new int[0];
		}
		else if(compteur ==1|| (stock == null && compteur%3==1))
		{
			printable_waste = new int[] {waste.value};
		}
		else if(compteur ==2|| (stock == null && compteur%3==2))
		{
			printable_waste = new int[] {waste.value,waste.cardAbove.value};
		}
		else
		{
			Cards current = waste;
			printable_waste = new int[3];
			for(int i=0; i<3;i++)
			{
				printable_waste[i] = current.value;
				current= current.cardAbove;
			}
			
		}
		
		return printable_waste;
	}
	/**
	 * Méthode permettant la transformation d'un paquet de carte supérieur
	 * (foundation) en un tableau d'entier acceptable pour l'affichage
	 * 
	 * @param foundation
	 * 		  Le paquet.
	 * @return
	 * 		  Le tableau d'entier.
	 */
	private int[] createPrintableFoundation(Cards foundation)
	{
		int compteur = getCompteur(foundation);
		int[] printable_foundation;
		
		if (compteur ==0)
			printable_foundation = new int [0];
		else if (compteur ==1)
			printable_foundation = new int[] {foundation.value}; //Erreur si value!=as
		else
		{
			Cards current = foundation;
			for(int i =0; i<compteur-2;i++)
				 current = current.cardAbove;
			printable_foundation = new int[] {current.value,
					current.cardAbove.value}; //commande en 2 lignes
		}			
		
		return printable_foundation;
	}
}
