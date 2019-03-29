package PriorityQueue;

public class Entrada<K,V> implements Entry<K,V>{
	
	//Atributos
	private V value;
	private K key;
	
	//Constructor
	public Entrada(K key,V value){
			this.key=key;
			this.value=value;
	}
	
	//Comandos
	public void setKey(K key){this.key=key;}
	public void setValue(V value){this.value=value;}
	
	//Consultas
	public K getKey(){return key;}
	public V getValue(){return value;}

}
