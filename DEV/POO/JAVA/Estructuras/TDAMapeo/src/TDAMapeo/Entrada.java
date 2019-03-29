package TDAMapeo;

public class Entrada<K,V> implements Entry<K,V>{
	//Atributos
	private K key;
	private V value;
	
	//Constructores
	public Entrada(K k, V v){
		key=k;
		value=v;
	}
	
	public Entrada(){key=null; value=null;}
	
	//Comandos
	public void setKey(K k){key=k;}
	public void setValue(V v){value=v;}
	
	//Consultas
	public K getKey(){return key;}
	public V getValue(){return value;}
	public String toString(){return "Key: "+key+"Value: "+value;}
	public boolean equals(Object o){
		Entry<K,V> ent;
		try{
			ent=(Entry<K,V>)o;
				
		}
		catch(ClassCastException e){return false;}
		return ent.getKey()==key && ent.getValue()==value;
	
	}
	
	
}
