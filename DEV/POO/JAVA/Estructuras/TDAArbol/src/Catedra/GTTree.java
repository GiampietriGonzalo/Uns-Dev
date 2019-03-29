package Catedra;
import java.util.Iterator;
/**
 * Interface GTTree
 * Es la interfaz presentada por Goodrich y Tamassia en su cuarta edici�n. Incluye las operaciones necesarias para manejar un �rbol general 
 * sin proveer operaciones para modificarlo.
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */

public interface GTTree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos en el �rbol.
	 * @return Cantidad de nodos en el �rbol.
	 */
	public int size();
	
	/**
	 * Consulta si el �rbol est� vac�o.
	 * @return Verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * @return Iterador de los elementos almacenados en el �rbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Reemplaza el elemento almacenado en la posici�n dada por el elemento pasado por par�metro. Devuelve el elemento reemplazado.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por par�metro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol est� vac�o.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n corresponde a un nodo interno.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n dada corresponde a un nodo externo.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	

}