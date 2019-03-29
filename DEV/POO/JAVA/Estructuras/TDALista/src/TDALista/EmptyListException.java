package TDALista;

/**
 * Clase EmptyListException extiende a Exception.
 * Excepcion lanzada cuando se realiza una operacion y la lista esta vacia.
 * @author Giampietri Gonzalo Emanuel - Stephano Favalessa.
 * */

public class EmptyListException extends Exception{
	
	/**
	 * Construye una nueva EmptyListException con el mensaje pasado como parametro.
	 * @param m, mensaje de la exception.
	 * */
	public EmptyListException(String m){super(m);}

}
