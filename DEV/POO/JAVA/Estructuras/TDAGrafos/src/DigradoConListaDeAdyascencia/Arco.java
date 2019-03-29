package DigradoConListaDeAdyascencia;
import TDALista.*;

public class Arco<V,E> implements Edge<E>{
	
	//Atributos 
	private E rotulo;
	private Position<Arco<V,E>> posEnAdyascentes;
	private Vertice<V,E> sucesor,predecesor;
	
	//Constructor
	public Arco(E rotulo, Vertice<V,E> p,Vertice<V,E> s){
			this.rotulo=rotulo;
			sucesor=s;
			predecesor=p;
			posEnAdyascentes=null;
	}
	
	//Comandos
	public void setRotulo(E r){rotulo=r;}
	public void setSucesor(Vertice<V,E> s){sucesor=s;}
	public void setPredecesor(Vertice<V,E> p){predecesor=p;}
	public void setPosicionEnAdyascentes(Position<Arco<V,E>> pos){posEnAdyascentes=pos;}
	
	
	//Consultas
	public E element(){return rotulo;}
	public E getRotulo(){return rotulo;}
	public Vertice<V,E> getSucesor(){return sucesor;}
	public Vertice<V,E> getPredecesor(){return predecesor;}
	public Position<Arco<V,E>> getPosEnAdyascentes(){return posEnAdyascentes;}
	
	
}
