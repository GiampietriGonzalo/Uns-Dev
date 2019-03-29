package GTTree;
import TDALista.*;

public class Nodo<E> implements Position<E>{
	E elemento;
	Nodo<E> padre;
	PositionList<Nodo<E>> hijos;
	public Nodo(E e){
		elemento=e;
		hijos= new DoubleLinkedList<Nodo<E>>();
		padre=null;
	}
	public Nodo(Nodo<E> n, E e){padre=n; elemento=e; hijos= new DoubleLinkedList<Nodo<E>>();}
	
	public E element(){return elemento;}
	public void setElemento(E e){elemento=e;}
	public void setPadre(Nodo<E> p){padre=p;}
	public PositionList<Nodo<E>> getHijos(){return hijos;}
	public Nodo<E> getPadre(){return padre;}
	
	
	
}
