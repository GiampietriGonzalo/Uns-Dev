package TDACola;

public class ArrayQueue<E> implements Queue<E>{
	//Atributos
	private E [] D;
	private int f,r;;
	private int n=10000;
	
	//Constructor
	
	public ArrayQueue(){
		D= (E[])new Object[n];
		f=0;
		r=0;
		
	}
	
	//Comandos
	public void enqueue(E item){
		if ( ! (size()==n-1)){
			D[r]=item;
			r= (r+1) % n;
		}
	}
	//Consultas
	public boolean isEmpty(){return f==r;}
	public int size(){return (n-f+r)%n;}
	public E dequeue() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		E aux=D[f];
		D[f]=null;
		f=(f+1)%n;

		return aux;
		
	}
	public E front() throws EmptyQueueException{
		if(isEmpty())
			throw new EmptyQueueException("Cila vacía");
		return D[f];
		
	}
	
}
