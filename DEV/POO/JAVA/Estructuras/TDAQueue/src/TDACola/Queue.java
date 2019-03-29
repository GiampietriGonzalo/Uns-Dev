package TDACola;
/**Cola de elementos abstractos*/
public interface Queue<E> {
		
	//Comandos

	/**Encola un elemento E en la parte trasera de la cola. Si la cola esta vacia, lanza un error 
	 * @throws EmptyQueueException: Cola Vacia
	 * */
	public void enqueue(E item);
	
	//Consultas
	/**Retorna true si la Cola esta vacia, false en caso contrario*/
	public boolean isEmpty();
	/**Retorna  la cantidad de items en la cola*/
	public int size();
	/** Desencola y retorna el elemento que esta al frente de la cola. Si la cola esta vacia, lanza un error
	 *  @throws EmptyQueueException: Cola vacia
	 * */
	public E dequeue()throws EmptyQueueException;
	/**Retorna el elemento que esta al frente de la cola. Sila cola esta vacoa, lanza un error
	 * @throws EmptyQueueException: Cola vacia */
	public E front() throws EmptyQueueException;	
}
