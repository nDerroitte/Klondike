package be.ac.ulg.montefiore.oop.Handlers;

import be.ac.ulg.montefiore.oop.Klondike.Cards;

/**
 * Classe abstraite permettant d'obliger une méthode d'ajout dans ses 3 fils
 * WasteHandler, PileHandler et Foundationhandler.
 * 
 * Nous ne pouvons faire de même avec remove car elle prend des  arugments 
 * différents en fonction de l'objet considéré.
 * 
 * @author Natan Derroitte
 */
public abstract class ObjectHandler extends Handler 
{
	   abstract Cards add(Cards a,Cards b);
}
