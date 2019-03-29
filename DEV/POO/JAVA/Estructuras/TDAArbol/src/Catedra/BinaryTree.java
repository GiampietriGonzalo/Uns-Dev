package Catedra;
/**
 * Interface BinaryTree
 * Exstiende la interfaz GTTree, presentada por Goodrich y Tamassia en su
 * cuarta edici�n (la interfaz para �rbol que no incluye operaciones de modificaci�n).
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */

public interface BinaryTree<E> extends GTTree<E>
{
	/**
	 * Devuelve la posici�n del hijo izquierdo de v.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posici�n del hijo derecho de v.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del hijo derecho de v.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v)throws InvalidPositionException, BoundaryViolationException;

	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.	
	 */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException;
	
	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.	
	 */
	public boolean hasRight(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param E R�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
	 */
	public Position<E> createRoot(E r) throws InvalidOperationException;
	
	
	/**
	 * Agrega un nodo con r�tulo r como hijo izquierdo de un nodo dado.
	 * @param r R�tulo del nuevo nodo.
	 * @param v Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException;


	/**
	 * Agrega un nodo con r�tulo r como hijo derecho de un nodo dado.
	 * @param r R�tulo del nuevo nodo.
	 * @param v Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException,
										InvalidPositionException;


	/**
	 * Elimina el nodo referenciado por una posici�n dada. Si el nodo tiene un �nico hijo, el nodo eliminado ser� reemplazado por su �nico hijo.
	 * @param v Posici�n del nodo a eliminar.
	 * @return el r�tulo del nodo eliminado.
     * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 * @throws InvalidOperationException si el nodo a eliminar tiene mas de un hijo.
     */
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException;

	/**
	 * Inserta a los �rboles T1 y T2 como sub�rboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posici�n de una hoja del �rbol.
	 * @param T1 �rbol binario a insertar como hijo izquierdo de v.
	 * @param T2 �rbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o, o v no corresponde a una hoja.
	 */
	//Pone a T1 y a T2 como sub�rboles de la hoja v, izquierdo y derecho respectivamente, si v no era hoja da InvalidPositionException.
	  public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException;
	}

	
