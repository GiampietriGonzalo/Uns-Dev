package TDAPila;
/** 
 * LinkedStack: Pila enlazada de nodo con referencia al tope de la pila. Cada nodo tiene un elemento y una referencia hacía otro nodo
 * @author Giampietri Gonzalo
 * @version 1.0
 * */

public class LinkedStack<E> implements Stack<E> {
	
	//ATRIBUTOS
	protected Nodo<E> head;
	protected int tamaño;
	
	//CONSTRUCTOR
	/**El constructor establece el nodo "head" en nulo y el tamaño de la pila en 0 */
	public LinkedStack(){head=null; tamaño=0;}
	
	//COMANDOS
	/**Coloca un nuevo nodo en la pila enlazada, haciendolo el head y aumentando el tamñano de la pila en 1*/
	public void push(E item){
		Nodo<E> aux= new Nodo<E>(item,head);
		head=aux;
		tamaño++;
	}
	
	//CONSULTAS
	/** Retorna el tamaño de la pila enlazada*/
	public int size(){return tamaño;}
	/**Retorna true si la pila enlazada esta vacía, false en caso contrario */
	public boolean isEmpty(){return tamaño==0;}
	
	/**Elimina el elemento tope de la pila (nodo head) y lo retorna 
	 * 		Si la pila esta vacía lanza una excepcion. 
	 * */
	public E pop() throws EmptyStackException{
		if (isEmpty())
				throw new EmptyStackException("Pila vacia");
		E aux= head.getElemento();
		head=head.getSiguiente();
		tamaño--;
		return aux;
	}
	
	/** retorna el elemnto de tope de la pila (nodo head).
	 * 		Si la pila esta vacía lanza una excepcion*/ 
	public E top() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException("Pila vacia");
			return head.getElemento();
	}

}
