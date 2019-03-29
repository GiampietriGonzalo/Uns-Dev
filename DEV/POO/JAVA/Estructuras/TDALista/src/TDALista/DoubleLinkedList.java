package TDALista;
import java.util.*;

/**
 * Clase DoubleLinkedList implementa a la interfaz PositionList.
 * Implementa a una lista doblemente enlazada con referencia a nodos Dummy como primer y ultimo nodo.
 * @author Giampietri Gonzalo Emanuel - Stephano Favalessa.
 * */

public class DoubleLinkedList<E> implements PositionList<E>{
	
	//Atributos
	DNode<E> header;
	DNode<E> trailer;
	int longitud;
	
	
	//Constructor
	public DoubleLinkedList(){
		header=new DNode<E>(null,null,null);
		trailer= new DNode<E>(null,null,null);
		header.setSiguiente(trailer);
		trailer.setAnterior(header);
		longitud=0;
	}
	
	
	//Metodos
	public void addFirst(E e){
		
			DNode<E>nuevo=new DNode<E>(e,header,header.getSiguiente());
			(header.getSiguiente()).setAnterior(nuevo);
			header.setSiguiente(nuevo);		
			longitud++;
	}
	
	public void addLast(E e){
		DNode<E> nuevo= new DNode<E>(e,trailer.getAnterior(),trailer);
		trailer.getAnterior().setSiguiente(nuevo);
		trailer.setAnterior(nuevo);
		longitud++;
		
	}
		
	public void addAfter(Position<E> pos, E e)throws InvalidPositionException{
		DNode<E> n=checkPosition(pos);
		DNode<E> nuevo=new DNode<E>(e,n,n.getSiguiente());
		((DNode<E>)n).getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		longitud++;
	}
	
	public void addBefore(Position<E> pos, E e)throws InvalidPositionException{
		DNode<E> n=checkPosition(pos);
		DNode<E> nuevo= new DNode<E>(e,n.getAnterior(),n);
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		longitud++;
	}
	
	//Consultas
	public boolean isEmpty(){return longitud==0;}
	
	public int size(){return longitud;}
	
	public Position<E> first()throws EmptyListException{
		if(isEmpty())
			throw new EmptyListException("Lista vacia");
		return header.getSiguiente();
		}
	
	
	public Position<E> last() throws EmptyListException{
		if(isEmpty())
			throw new EmptyListException("Lista vacia");
		return trailer.getAnterior();
		}
	
	public Position<E> prev(Position<E> pos)throws InvalidPositionException, BoundaryViolationException{
	
		DNode<E> n=checkPosition(pos);
		if (n.getAnterior()==header)
				throw new BoundaryViolationException("Es el primer nodo");
		return n.getAnterior();
	}
	
	
	
	public Position<E> next(Position<E> pos)throws InvalidPositionException, BoundaryViolationException{
		DNode<E> n=checkPosition(pos);
		if (n.getSiguiente()==trailer)
				throw new BoundaryViolationException("Es el ultimo nodo");
		return n.getSiguiente();
		
	}
	
	public E remove(Position<E> pos)throws InvalidPositionException{
		
		DNode<E> n=checkPosition(pos);
		E toReturn=n.element();
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		n.setAnterior(null);
		n.setSiguiente(null);
		longitud--;		
		return toReturn;	
	}
		
	public E set(Position<E> pos, E e)throws InvalidPositionException{
		DNode<E> n= checkPosition(pos);
		E aux=n.element();
		n.setElemento(e);
		return aux;
	}
	
	
	public Iterator<E> iterator(){return new ElementIterator<E>(this);}
	
	private DNode<E> checkPosition(Position<E> pos)throws InvalidPositionException{
		try{
			if(pos==null)
				throw new InvalidPositionException("Posicion nula");
			if(pos==header)
				throw new InvalidPositionException("Posicion Invalida, es el header");
			if (pos==trailer)
				throw new InvalidPositionException("Posicion Invalida, es el trailer");
			if (((DNode<E>)pos).getSiguiente()==null)
				throw new InvalidPositionException("Posicion Invalida, no peretenece a esta lista");
			if(((DNode<E>)pos).getAnterior()==null)
				throw new InvalidPositionException("Posicion Invalida, no pertenece a esta lista");

			return (DNode<E>)pos;
		}
		catch(ClassCastException e){throw new InvalidPositionException("error de casteo");}
		
	}
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> lis= new DoubleLinkedList<Position<E>>();
		try{
		;
		if (!isEmpty()){
			Position<E> pos=first();
			while(pos!=null){
				lis.addLast(pos);
				pos=next(pos);
				}
				
		}
		}
		catch(BoundaryViolationException r){}
		catch(EmptyListException f){}
		catch(InvalidPositionException g){}
		
		return lis;
		
	}
}
