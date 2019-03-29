package TDADiccionario;
import TDALista.*;
import java.util.Iterator;

public class HashDictionary<K,V> implements Dictionary<K,V> {

		//Atributos
		private int size;
		private DoubleLinkedList<Entry<K,V>> T;
		
		//Constructor
		public HashDictionary(){
			size=0;
			T= new DoubleLinkedList<Entry<K,V>>();
		}
		
		//Consultas
		public int size(){return size;}
		public boolean isEmpty(){return size==0;}
		
		//Comandos
		public Entry<K,V> insert(K key, V value)throws InvalidKeyException{
			checkKey(key);
			Entrada<K,V> toReturn= new Entrada<K,V>(key,value);
			T.addLast(toReturn);
			size++;
			return toReturn;
		}
		
		public Entry<K,V> find(K key)throws InvalidKeyException{
			checkKey(key);
			Iterator<Entry<K,V>> it= entries().iterator();
			boolean encontre=false;
			Entrada<K,V> toReturn=null;
			Entry<K,V> entradaActual;
			while(!encontre && it.hasNext()){
				entradaActual=it.next();
				if(entradaActual.getKey().equals(key)){
					toReturn=(Entrada<K,V>)entradaActual;	
					encontre=true;
				}	
			}
			return toReturn;
		}
		
		
		
		public Iterable<Entry<K,V>> findAll(K key)throws InvalidKeyException{
			checkKey(key);
			DoubleLinkedList<Entry<K,V>> toReturn= new DoubleLinkedList<Entry<K,V>>();
			for(Entry<K,V> in: entries())
				if(in.getKey().equals(key))
					toReturn.addLast(in);
			
			return toReturn;
		}
		
		
		public Entry<K,V> remove(Entry<K,V> en) throws InvalidEntryException{
			if(en==null)
				throw new InvalidEntryException("Entrada nula");

			try{
				for(Position<Entry<K,V>> pos: T.positions()){
					if(pos.element()==en){
						T.remove(pos);
						size--;
						return en;
					}
				}	
				throw new InvalidEntryException("No se encuentra la entrada dentro del diccionario");
			}
			catch(InvalidPositionException e){System.out.println(e.getMessage());}
			
			return null;
		}
		
		public Iterable<Entry<K,V>> entries(){
			DoubleLinkedList<Entry<K,V>> toReturn= new DoubleLinkedList();
			for(Position<Entry<K,V>> pos: T.positions())
				toReturn.addLast(pos.element());
			
			return toReturn;
		}
		
		
		
		
		private void checkKey(K key)throws InvalidKeyException{
			if(key==null)
				throw new InvalidKeyException("Key invalida");
		}
		
}
