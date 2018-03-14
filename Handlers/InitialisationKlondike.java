package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Classe permettant l'initialisation d'une nouvelle partie.
 * @author Natan Derroitte
 */
public class InitialisationKlondike 
{
	/**
	 * Méthode permettant de créer aléatoirement un tableau de paquets de 
	 * cartes contenant les 7 tas ainsi que le stock de carte.
	 * 
	 * @param piletab
	 * 		  Le tableau vide à remplir.
	 * @return
	 * 		  Le tableau remplit.
	 */
	public Cards[] initialisation (Cards[] piletab)
	{
		int tab[] = creationSortArray();
		Cards creation_tab[][] =  fillCreationArray(tab);
		
		piletab = fillTabInit(piletab, creation_tab);
		
		return piletab;
	}
	/**
	 * Méthode utilisée par initialisation(Cards[]piletab) permettant de créer
	 * un tableau de 52 entiers de 0 à 51.
	 * Ce tableau sera utilisé pour générer des paquets de cartes aléatoires.
	 * 
	 * @return
	 * 		  Le tableau trié de 0 à 51.
	 */
	private int[] creationSortArray()
	{
		int tab[] = new int[52];
		
		for (int i =0; i<52;i++)
			tab[i] = i;
		
		return tab;
	}
	/**
	 * Méthode utilisé par initialisation(Cards[] piletab) permettant de 
	 * créer les 7 paquets de cartes et le stock aléatoirement. Ceux-ci
	 * sont temporairement stockés dans un tableau bidimensionnel.
	 * 
	 * @param tab
	 * 		  Le tableau trié par ordre croissant de 0 à 51
	 * @return
	 * 		  Le tableau bidimensionnel de résultat
	 */
	private Cards[][] fillCreationArray ( int[] tab)
	{
		int random ;	
		int valmax= 51;
		Cards[][] creation_tab = new Cards[8][];
		
		for (int i =0; i<8;i++)
		{
			if(i!=7)
			{
				creation_tab[i]= new Cards[i+1];
				for(int j =0;j<i+1;j++)
				{
					random = (int)(Math.random()*(valmax));
					if (j==0)
						creation_tab[i][j]= new Cards (tab[random],true);
					else
						creation_tab[i][j]= new Cards (tab[random],true,
								creation_tab[i][j-1]);
					tab[random]=tab[valmax--];
				}
			}
			else 
			{
				creation_tab[i]= new Cards[24];
				for(int j =0;j<24;j++)
				{
					random = (int)(Math.random()*(valmax));
					if (j==0)
						creation_tab[i][j]= new Cards (tab[random],true);
					else
						creation_tab[i][j]= new Cards (tab[random],true,
								creation_tab[i][j-1]);//commande en 2 l.
					tab[random]=tab[valmax--];
				}
			}
		}
		
		return creation_tab;
	}
	/**
	 * Méthode utilisée par initialisation(Cards[] piletab) permettant de 
	 * remplir le tableau à retourner des différents paquets de cartes
	 * crées aléatoirement 
	 * 
	 * @param tabinit
	 * 		  Le tableau à remplir.
	 * @param creation_tab
	 * 		  Le tableau servant à remplir le tableau à retourner
	 * @return
	 * 	 	  Le tableau a retourner remplit.
	 */
	private Cards [] fillTabInit(Cards[] tabinit, Cards [][]creation_tab)
	{
		int j=0;
		
		for (int i =0; i<8;i++)
		{
			if(j==7)
			{
			tabinit[i] = creation_tab[i][23];
			continue;
			}
			tabinit[i] = creation_tab[i][j++];
		}
		
		return tabinit;
	}
}
