package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Classe permettant d'implémenter l'ensemble méthodes servant à vérifier les 
 * conditions de déplacements des cartes
 * 
 * @author Natan Derroitte
 */
public class Verif extends Handler
{
	/**
	 * Méthode vérifiant si l'on peut déplacer une carte vers un des 7 paquets
	 *  de cartes centraux
	 * 
	 * @param Src
	 * 		  La carte qu'on veut déplacer
	 * @param Dst
	 * 		  La dernière carte dans le paquet où on veut ajouter la carte
	 * @return
	 * 		  true si on peut faire le déplacement
	 * 		  false sinon.
	 */
	public boolean pile (Cards Src, Cards Dst)
	{
		if(super.IsEmpty(Dst)==true)
		{
			if(Src.getNumber()==12)
				return true;
			return false;
		}
		if((Src.getNumber()==Dst.getNumber()-1) && (Src.getColor()!=
				Dst.getColor()))
			return true;
		return false;
	}
	/**
	 * Méthode vérifiant si l'on peut déplacer une carte vers un des 4 paquets de 
	 * cartes supérieurs (foundations)
	 * 
	 * @param Src
	 * 		  La carte qu'on veut déplacer
	 * @param Dst
	 * 		  La dernière carte dans le paquet (foundation) où on veut ajouter 
	 * 		  la carte
	 * @return
	 * 		  true si on peut faire le déplacement
	 * 		  false sinon.
	 */
	public boolean foundation ( Cards Src , Cards endOfFoundation)
	{
		if (super.IsEmpty(endOfFoundation)==true)
		{
			if(Src.getNumber()==0)
				return true;
			return false;
		}
		if ( (Src.getNumber()==endOfFoundation.getNumber()+1) && 
				( Src.getType()==endOfFoundation.getType()))//if en 2 lignes
			return true;
		return false;
	}
	/**
	 * Méthode vérifiant si le joueur à gagner la partie au terme de son dernier 
	 * mouvement
	 * 
	 * @param foundationtab
	 * 		  Tableau comprenant les 4 paquets supérieurs (foundations)
	 * @return
	 * 		  true si le joueur à gagner
	 * 		  false sinon.
	 */
	public boolean win (Cards[]foundationtab)
	{
		boolean result =false;
		
		for (int i =0; i<4;i++)
		{
			if(super.IsEmpty(foundationtab[i])==true)
				return false;
			if (super.endingCard(foundationtab[i]).getNumber()==12)
				result =true;
			else 
			{
				result = false;
				break;
			}
		}
		
		return result;
	}
}
