package TDALista;

/**
 * Clase Dnode implementa a la Interfaz Position
 * Nodo con rotulo de tipo E y referencia doble de tipo Dnode, hacia su nodo anterior y a hacia su nodo siguiente.
 * @author Giampietri Gonzalo Emanuel - Stephano Favalessa.
 * */

public class DNode<E> implements Position<E>{
	//Atributos
	E elemento;
	DNode<E> siguiente;
	DNode<E> anterior;
	
	//Constructor
	public DNode(){this(null,null,null);}	//Dummy Node
	
	public DNode(E elem, DNode<E> ant, DNode<E> sig){
		elemento=elem;
		anterior=ant;
		siguiente=sig;
	};
	
	//Comandos
	public void setSiguiente(DNode<E> sig){siguiente=sig;}
	public void setAnterior(DNode<E> ant){anterior=ant;}
	public void setElemento(E elem){elemento=elem;}
	
	//Consultas
	public E element(){return elemento;}
	public DNode<E> getSiguiente(){return siguiente;}
	public DNode<E> getAnterior() {return anterior;}

	

}
