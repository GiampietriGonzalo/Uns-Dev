package TDAPila;

public interface Stack<T> {
	
	/** 
	 * Inserta un elemento de tipo T al top de la pila.
	 * @param elemento a insertar.
	 * */
	void push(T elemento);
	
	/**
	 * Retorna el elemento que esta en el tope de la pila.
	 * @return elemento del topede la pila.
	 * @throw EmptyEstackException cuando la pila esta vacia.
	 * */
	T top()throws EmptyStackException;
	
	/**
	 * Quita y retorna el elemento al tope de la pila.
	 * @return elemento al tope de la pila.
	 * @throw EmptyStackException cuando la pila esta vacia.
	 * */
	T pop()throws EmptyStackException;
	
	/**
	 * Determina si la pila esta vacia.
	 * @return True si la pila esta vacia, False en caso contrario.
	 * */
	boolean isEmpty();
	
	/**
	 * Retorna la cantidad de elementos apilados
	 * @return cantidad de elementos en la pila.
	 * */
	int size();
		
}
