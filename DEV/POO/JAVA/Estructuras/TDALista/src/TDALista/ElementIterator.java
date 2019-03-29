package TDALista;
import java.util.*;

/**
 * Clase ElementIterator implementa a la interfaz Iterator.
 * Iterador de elementos de la  lista doblemente enlazada.
 * @author Giampietri Gonzalo Emanuel - Stephano Favalessa.
 * */

import java.util.*;


public class ElementIterator<E> implements Iterator<E>{
	
	//Atributos
	protected PositionList<E> list; //Lista a iterar
	protected Position<E> cursor; //Posicion del elemento corriente
	
	
	//Constructor
	
	/**
	 * Crea un nuevo iterador y establece su lista a iterar.
	 * @param l, lista a iterar.
	 * */
	
	public ElementIterator(PositionList<E> l){
	
			list=l;
			if (list.isEmpty())	
				cursor=null;
			else
				try {
					cursor=l.first();
				} catch (EmptyListException e) {
					System.out.println(e.getMessage());
				}
		}
	
	/**
	 * Determina si hay elementos a iterar.
	 * @return true si hay al menos un elemento a iterar, false en caso contrario.
	 * */
	public boolean hasNext(){return cursor!=null;}
	
	/**
	 * Retorna el siguiente elemento de la iteracion
	 * @return E elemento de la lista.
	 * @throws NoSuchElementException si no hay elementos a iterar.
	 * */
	public E next()throws NoSuchElementException{
		E toReturn=null;
	try{
		if (cursor==null)
			throw new NoSuchElementException("No tiene siguiente");
		toReturn= cursor.element();
		
		cursor=(cursor==list.last())? null : list.next(cursor);
		
		
	 }//Fin Try
	 catch(EmptyListException f){System.out.println(f.getMessage());}
	 catch(BoundaryViolationException t){System.out.println(t.getMessage());}
	 catch(InvalidPositionException r){System.out.println(r.getMessage());}
	 return toReturn;
	}
	
	public void remove(){
		//No lo definimos.
	}
	
}//Fin clase
