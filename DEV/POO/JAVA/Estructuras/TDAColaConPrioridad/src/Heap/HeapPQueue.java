package Heap;
import PriorityQueue.*;
import java.util.Comparator;

public class HeapPQueue<K,V> implements PriorityQueue<K,V>{
	
	//Atributos
	private Entrada<K,V>[] elems;
	private int size;
	private Comparator<K> comp;
	
	//Constructor
	public HeapPQueue(Comparator<K> c){
		comp=c;
		elems=(Entrada<K,V>[])new Entrada[100000];
		size=0;
	}
	
	//Metodos
	
	public int size(){return size;}
	public boolean isEmpty(){return size==0;}
	
	public Entry<K,V> insert(K key, V value)throws InvalidKeyException{
		K clave= checkKey(key);
		
		//Creo entrada y la coloco en la última posición
		Entrada<K,V> toReturn= new Entrada<K,V>(key,value);
		elems[++size]=toReturn;
		
		
		//Ordena usando burbujeo
		boolean seguir= true;
		int i= size; //Indice de la nueva entrada.
		
		while(i>1 && seguir){
			if(comp.compare(elems[i].getKey(), elems[i/2].getKey()) < 0){
				Entrada<K,V> aux = elems[i/2];
				elems[i/2]=elems[i];
				elems[i]=aux;
				i/=2;
			}
			else 
				seguir=false;
		}//Fin while
		return toReturn;
	}//O(log2 (n))
	
	
	
	
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		//Guardo entrada a retornar
		Entry<K,V> toReturn=min();
		
		if(size==1){
			size=0;
			elems[1]=null;
			return toReturn;
		}
		else{
			//Paso el ultimo elemento a la raiz y elimino el final
			elems[1]=elems[size];
			elems[size]=null;
			size--;
			
			//Burbujeo la raiz hacia abajo
			int i=1;
			boolean seguir=true;
			while(seguir){
				//Calculo la posicion de los hijos
				int izq= i*2;
				int der=i*2+1;
				boolean tieneIzq= izq<=size();
				boolean tieneDer= der<=size();
				if(!tieneIzq) 
					seguir=false;
				else{
					int m; //Minimo de hijos
					if(tieneDer){	
						//Calculo el menor de los hijos
						if(comp.compare(elems[izq].getKey(), elems[der].getKey()) < 0)
							m=izq;
						else
							m=der;
					}		
					else	m=izq;	
				
				
					//Me fijo si hay que intercambiar el actual con el menor de sus hijos
					if(comp.compare(elems[i].getKey(), elems[m].getKey())>0){
						//elems[m] es menor a elems[i]
						Entrada<K,V> aux=elems[i];
						elems[i]=elems[m];
						elems[m]=aux;
						i=m;
					}
					else //Esta ordenado
						seguir=false;
			
				}
			}
		}
		return toReturn;
	}
	
	
	public Entry<K,V> min() throws EmptyPriorityQueueException{
		if(isEmpty())
			throw new EmptyPriorityQueueException("Cola con prioridad vacia");
		return elems[1];
	}
	
	
	private K checkKey(K key)throws InvalidKeyException{
		if(key==null)
			throw new InvalidKeyException("Key invalida");
		return key;
		
	}
}
