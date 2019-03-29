package TDACola;

public interface Queue<E> {

	/**
	 * Encola el elemento pasado por parametro.
	 * @param elemento a encolar.
	 * */
	void enqueue(E elemento);
	
	/**
	 *Desencola el primer elemento y lo retorna.
	 *@return elemento desencolado. 
	 * */
	E dequeue() throws EmptyQueueException;
	
	/**
	 * Retorna y no desencola el primer elemento de la cola.
	 * @return el primer elemento de la cola.
	 * */
	E front() throws EmptyQueueException;
	
	/**
	 * Determina si la cola esta vacia.
	 * @return true si la cola esta vacia, false en caso contrario.
	 * */
	boolean isEmpty();
	
	/**
	 * Determina la cantidad de elementos que hay en la cola.
	 * @return cantidad de elementos de la cola
	 * */
	int size();
}
