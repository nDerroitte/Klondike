package be.ac.ulg.montefiore.oop.Klondike;
/**
 * Classe permettant la création de paquet de cartes ainsi que des cartes
 * individuelles.
 * Elle implémente également quelques méthodes permettant d'obtenir des 
 * informations sur ces cartes.
 * 
 * @author Natan Derroitte
 */
public class Cards implements Cloneable
{
	//Variables d'instances
	final public int value;
	private boolean hidden;
	public Cards cardAbove;
	//Constructeurs
	public Cards(int givenValue, boolean givenHideness)
	{
		value = givenValue;
		hidden= givenHideness;
		cardAbove = null;
	}
	public Cards(int givenValue, boolean givenHideness,Cards givenCard)
	{
		value = givenValue;
		hidden= givenHideness;
		cardAbove = givenCard;
	}
	
	/**
	 * Méthode permettant de déterminer le numéro de la carte (de 1 à roi)
	 * 
	 * @return
	 * 		  Le numéro de carte.
	 */
	public int getNumber()
	{
		return value%13;
	}
	/**
	 * Méthode permettant de déterminer la couleur de la carte.
	 * 
	 * @return
	 * 		  La couleur de carte :
	 * 			0 si c'est noir
	 * 			1 si c'est rouge
	 */
	public int getColor()
	{
		int x = value/13;
		if (x==0 || x==3)
			return 0;
		
		return 1;
	}
	/**
	 * Méthode servant à déterminer le type de la carte.
	 * @return
	 * 		  Le type de carte :
	 * 			0 si c'est un pique.
	 * 			1 si c'est un coeur.
	 * 			2 si c'est carreau.
	 * 			3 si c'est un trèfle.
	 */
	public int getType()
	{
		return  value/13;
	}
	/**
	 * Méthode servant à dertiminer si la carte est face cachée ou non
	 * @return
	 * 		  true si elle est cachée
	 * 		  false sinon.
	 */
	public boolean isHidden()
	{
		if( hidden ==false)
			return false;
		
		return true;
	}
	/**
	 * Méthode servant à retourner une carte.
	 */
	public void flip ()
	{
		if(isHidden()==true)
			hidden = false;
		else
			hidden = true;
	}
	/**
	 * Méthode servant à copier la carte.
	 */
	 public Object clone()
	 {
		 Cards copy;
	 try
	 {
		 copy = (Cards) super.clone();
		 if (copy.cardAbove!=null)
			 copy.cardAbove = (Cards) this.cardAbove.clone();
		 }
	     catch (CloneNotSupportedException e)
	 {
	    	 throw new InternalError("unable to clone");
	 }
	     return (Object) copy;
	 } 
}
