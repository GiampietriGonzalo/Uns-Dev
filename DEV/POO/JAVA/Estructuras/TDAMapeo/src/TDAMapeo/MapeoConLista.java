package TDAMapeo;
import TDALista.*;
import java.util.Iterator;

public class MapeoConLista<K,V> implements Map<K,V>{
	//Atributos
	private DoubleLinkedList<Entrada<K,V>> S;
	
	//Constructor
	public MapeoConLista(){S = new DoubleLinkedList<Entrada<K,V>>();}
	
	//Metodos
	
	public V put(K k, V v){
		V aux=null;
		boolean encontro=false;
		Iterator<Position<Entrada<K,V>>> it=S.positions().iterator();
		Position<Entrada<K,V>> e;
		while(!encontro && it.hasNext()){
			e=it.next();
			if (e.element().getKey().equals(k)){
				//Encontro
				aux=e.element().getValue();
				e.element().setValue(v);
				encontro=true;
			}
		}
		
		if(!encontro)
			//Si no lo encuentro en la lista, lo agrego y retorno null
			S.addLast(new Entrada<K,V>(k,v));
		
		return aux;
	}
	public V get(K k){
		V aux=null;
		boolean encontro=false;
		Iterator<Position<Entrada<K,V>>> it=S.positions().iterator();
		Position<Entrada<K,V>> e;
		while(!encontro && it.hasNext()){
			e=it.next();
			if (e.element().getKey().equals(k)){
				//Encontro
				aux=e.element().getValue();
				encontro=true;
			}
		}
		
		return aux;
	}
	
	public V remove(K k){
		V aux=null;
		try{
				boolean encontro=false;
				Iterator<Position<Entrada<K,V>>> it=S.positions().iterator();
				Position<Entrada<K,V>> e;
				
				while(!encontro && it.hasNext()){
					e=it.next();
					if (e.element().getKey() == k){
						//Encontro
						aux= (S.remove(e)).getValue();
						encontro=true;
					}
				}
			}
		catch(InvalidPositionException e){System.out.println(e.getMessage());}
		
		return aux;
		
	}

	
	public int size() {return S.size();
	}

	
	public boolean isEmpty() {return S.isEmpty();}

	
	public Iterable<K> keys() {
		
		DoubleLinkedList<K> toReturn= new DoubleLinkedList<K>();
		for (Position<Entrada<K,V>> e: S.positions())
				toReturn.addLast(e.element().getKey());
		
		return toReturn;
	}

	
	public Iterable<V> values() {
		DoubleLinkedList<V> toReturn= new DoubleLinkedList<V>();
		for (Position<Entrada<K,V>> e: S.positions())
				toReturn.addLast(e.element().getValue());
		
		return toReturn;
	}

	
	public Iterable<Entry<K, V>> entries() {
		
		DoubleLinkedList<Entry<K,V>> toReturn= new DoubleLinkedList<Entry<K,V>>();
		for (Position<Entrada<K,V>> e: S.positions())
				toReturn.addLast(e.element());
		
		return toReturn;
	}
	
	

}
