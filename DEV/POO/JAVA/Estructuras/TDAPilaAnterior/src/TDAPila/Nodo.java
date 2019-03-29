package TDAPila;
/**Nodos que constan de un elemento generico E y una referencia a otro nodo.
 * 3 constructores: Uno que establece un Dummy node
 * 					 Otro que establece el item del nodo, y referencia nula hacia el siguiente 
 * 					 Otro que establece el item del nodo y una referencia hacia el nodo siguiente	
 * @author Giampietri Gonzalo
 * @version 1.0
 * */

public class Nodo<E> {
	
	//ATRIBUTOS
	private E elemento;
	private Nodo<E> siguiente;
	
	//CONSTRUCTORES
	
	public Nodo(){this(null,null);} //Dummy node - Nodo fictisio
	public Nodo(E item){this(item,null);}
	public Nodo(E item, Nodo<E> sig){elemento=item; siguiente=sig;}
	
	//Comandos
	/**Establace un elemento al nodo*/
	public void setElemento(E item){elemento=item;}
	//CONSULTAS
	/**Retorna el elemento del nodo*/
	public E getElemento(){return elemento;}
	/**Retorna la referencia al nodo siguiente al nodo que se hace la llamada.*/
	public Nodo<E> getSiguiente(){return siguiente;}
	
	/**Establace la referencia hacia el nodo siguiente*/
	public void setSiguiente(Nodo<E> sig){siguiente=sig;}
	
} 

	
	
	
	

