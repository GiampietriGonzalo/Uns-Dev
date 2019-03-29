package TDAMapeo;
import java.util.Iterator;
import TDALista.*;

public class OpenHMap<K,V> implements Map<K,V> {
	
	//Atributos
	private DoubleLinkedList<Entry<K,V>>[] A;
	private int n;
	private int N=13;
	
	//Constructor
	public OpenHMap(){
		n=0;
		A=(DoubleLinkedList<Entry<K,V>>[])new DoubleLinkedList[N];
		for (int i=0; i<N; i++)
			A[i]= new DoubleLinkedList<Entry<K,V>>();
		
	}
	
	public int size() {
		return n;
	}

	
	public boolean isEmpty(){return n==0;}

	
	public V get(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("La clave pasada es nula");
		int d=hashValue(key);
		
		V toReturn=null;
		DoubleLinkedList<Entry<K,V>> list= A[d];
		Iterator<Entry<K,V>> it=list.iterator();
		Entry<K,V> in;
		
		while(it.hasNext()){
			in=it.next();
			if (in.getKey()==key)
				toReturn=in.getValue();
		}
		
		return toReturn;
	}


	public V put(K key, V value) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("La clave ingresada es nula");
		int d=hashValue(key);
		
		if(A[d].size() > 3){
			rehash();
			d=hashValue(key);
		}
		
		boolean encontro=false;
		V toReturn=null;

		Iterator<Entry<K,V>> it=A[d].iterator();
		Entry<K,V> in;
		while(!encontro && it.hasNext()){
			in=it.next();
			if (in.getKey().equals(key)){
				//La entrada con clave key se encuentra en el mapeo
				toReturn=in.getValue();
				((Entrada<K,V>)in).setValue(value);
				encontro=true;
			}
		}
		
		if (!encontro){
			A[d].addLast(new Entrada<K,V>(key,value));
			n++;
		}
		return toReturn;
	}

	
	public V remove(K key) throws InvalidKeyException {
	
		if (key==null)
			throw new InvalidKeyException("la clave pasada es nula");
		V toReturn=null;
	try{	
		
		int d=hashValue(key);
		DoubleLinkedList<Entry<K,V>> list=A[d];
		Iterator<Position<Entry<K,V>>> it=list.positions().iterator();
		Position<Entry<K,V>> in;
		boolean borre=false;
		
		while(!borre && it.hasNext()){
			in=it.next();
			if (in.element().getKey()==key){
				toReturn=in.element().getValue();
				A[d].remove(in);
				n--;
				borre=true;
			}
		}
		return toReturn;
	
	}
	catch(InvalidPositionException e){System.out.println("Pos invalida");}
	return null;
	}
	

	public Iterable<K> keys() {
		PositionList<K> toReturn= new DoubleLinkedList<K>();
		for (int j=0; j<N; j++)
			for(Entry<K,V> in: A[j])
				toReturn.addLast(in.getKey());
					
		
		return toReturn;
	}

	
	public Iterable<V> values() {
		// Por cada mapeo del arreglo A, agrego (a la lista iterable) los valores de cada una de sus entradas
		PositionList<V> toReturn = new DoubleLinkedList<V>();
		for (DoubleLinkedList<Entry<K,V>> list: A){
			for (Entry<K,V> in: list)
				toReturn.addLast(in.getValue());
		}
		return toReturn;
	}

	
	public Iterable<Entry<K, V>> entries() {
		//Por cada mapeo del arreglo, agrego a la lista iterable cada una de sus entradas
		PositionList<Entry<K,V>> toReturn = new DoubleLinkedList<Entry<K,V>>();
		for (PositionList<Entry<K,V>> pos: A){
			for (Entry<K,V> in: pos)
				toReturn.addLast(in);
		}	
		
		return toReturn;
	}
	
	public int hashValue(K key){
		return Math.abs(key.hashCode()) % N;
	}
	
	private void rehash()throws InvalidKeyException{
		 
		 int aux=2*N;
		 DoubleLinkedList<Entry<K,V>>[] M=(DoubleLinkedList<Entry<K,V>>[])new DoubleLinkedList[aux];
		 
		 //Asigno una Lista de entradas a cada index del arreglo auxiliar
		 	for (int k=0; k<aux;k++)
		 		M[k]=new DoubleLinkedList<Entry<K,V>>();
		 
		 	//Por cada lista, verifico si tengo entradas y los coloco en el arreglo auxiliar 
		 	for (int j=0; j<N; j++)
		 		if(A[j].size()!=0){
		 			for(Entry<K,V> entrada: A[j]){
		 				remove(entrada.getKey());
		 				M[Math.abs((entrada.getKey().hashCode()%aux))].addLast(entrada);
		 				n++;
		 			}
		 		}
		 	A=M;		 
		 	N=aux;
		 
				  
		
	}
	
	public PositionList<Entry<K,V>> getLista(int i){return A[i];}
	public int cantListas(){return N;}
}

	