package DigradoConListaDeAdyascencia;
import java.util.Iterator;

import TDALista.*;

public class DGrafoListaAdyascentes<V,E> implements GraphD<V,E> {
	
	//Atributos
	private PositionList<Vertice<V,E>> vertices;
	private PositionList<Arco<V,E>> adyascentes;
	
	public DGrafoListaAdyascentes(){
		vertices=new DoubleLinkedList<Vertice<V,E>>();
		adyascentes=new DoubleLinkedList<Arco<V,E>>();
	}
	
	public Vertex<V> insertVertex(V x){
		Vertice<V,E> v= new Vertice<V,E>(x);
		vertices.addLast(v);
		try{	
			v.setPosEnVertices(vertices.last());
		}
		catch(EmptyListException e){System.out.println(e.getMessage());}
		return v;
	}
	
	public Edge<E> insertEdge(Vertex<V> v1,Vertex<V> v2, E peso)throws InvalidVertexException{
		Vertice<V,E> V1= checkVertex(v1);
		Vertice<V,E> V2= checkVertex(v2);
		
		//Creo el nuevo arco
		Arco<V,E> arco= new Arco<V,E>(peso,V2,V1);
		adyascentes.addLast(arco);
		try{
			//Seteo el atributo posEnAdyascentes del arco
			arco.setPosicionEnAdyascentes(adyascentes.last());
			
			//Agrego el arco a la lista de adyascentes del vertice sucesor y predecesor 
			V1.getAdyascentes().addLast(arco);
			V2.getAdyascentes().addLast(arco);
		}
		catch(EmptyListException e){System.out.println(e.getMessage());}
		return arco;
	}
	
	
	public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V,E> ar= checkEdge(e);
		try{
			//Remuevo el arco de la lista de adyascentes
			adyascentes.remove(ar.getPosEnAdyascentes());
			Vertice<V,E> v1= ar.getSucesor();
			Vertice<V,E> v2=ar.getPredecesor();
			v1.getAdyascentes().remove(ar.getPosEnAdyascentes());
			v2.getAdyascentes().remove(ar.getPosEnAdyascentes());
			
		}
		catch(InvalidPositionException e1){System.out.println(e1.getMessage());}
	
		return ar.element();
	}
	
	
	public V removeVertex(Vertex<V> e) throws InvalidVertexException{
		Vertice<V,E> v= checkVertex(e);
		try{
			for(Arco<V,E> ar: v.getAdyascentes()){
				removeEdge(ar);
			}
			vertices.remove(v.getPosEnVertices());
		}
		catch(InvalidPositionException e1){System.out.println(e1.getMessage());}
		catch(InvalidEdgeException e1){System.out.println(e1.getMessage());}
		
		return v.element();
	}
	
	
	
	
	private Vertice<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{
		if(v==null)
			throw new InvalidVertexException("Vertice invalido");
		return (Vertice<V,E>)v;
	}
	
	private Arco<V,E> checkEdge(Edge<E> v)throws InvalidEdgeException{
		if(v==null)
			throw new InvalidEdgeException("Arco invalido");
		return (Arco<V,E>)v;
	}
	
	public Iterable<Vertex<V>> vertices(){
		DoubleLinkedList<Vertex<V>> tR= new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> v: vertices){
			tR.addLast(v);
		}
		return tR;
	}
	
	public Iterable<Edge<E>> edges(){
		DoubleLinkedList<Edge<E>> tR= new DoubleLinkedList<Edge<E>>();
		for(Edge<E> e: adyascentes){
			tR.addLast(e);
		}
		return tR;
	}
	
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException{
		Vertice<V,E> v1= checkVertex(v);
		Arco<V,E> a1= checkEdge(e);
		Vertex<V> tR=null;
		
		if(v1==a1.getSucesor())
			tR=a1.getPredecesor();
		else
			if(v1==a1.getPredecesor())
				tR=a1.getSucesor();
			else
				throw new InvalidVertexException("El vertice no tienen un opuesto con respecto a este arco");
				
		return tR;	
	}
	
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException{
		Arco<V,E> a= checkEdge(e);
		Vertex<V>[] tR= (Vertice<V,E>[])new Vertice[2];
		tR[0]=a.getPredecesor();
		tR[1]=a.getSucesor();
		return tR;
	}
	
	public boolean areAdjacent(Vertex<V> v,Vertex<V> w) throws InvalidVertexException{
		Vertice<V,E> v1= checkVertex(v);
		Vertice<V,E> v2= checkVertex(w);
		boolean son=false;
		Iterator<Arco<V,E>> it= v1.getAdyascentes().iterator(); 
		Arco<V,E> actual;
		
		while(!son && it.hasNext()){
			actual=it.next();
			if(actual.getSucesor()==v2 || actual.getPredecesor()==v2)
				son=true;
		}
		
		return son;
	}

	public V replace(Vertex<V> v, V x) throws InvalidVertexException{
		Vertice<V,E> v1= checkVertex(v);
		V tR= v1.element();
		v1.setRotulo(x);
		return tR;
	}
	
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		Vertice<V,E> v1= checkVertex(v);
		DoubleLinkedList<Edge<E>> tR= new DoubleLinkedList<Edge<E>>();
		for(Arco<V,E> ar: v1.getAdyascentes())
			if(ar.getSucesor()==v1)
				tR.addLast(ar);
		
		
		return tR;
	}

	
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v)throws InvalidVertexException {
		Vertice<V,E> v1=checkVertex(v);
		PositionList<Edge<E>> tR= new DoubleLinkedList<Edge<E>>();
		for(Arco<V,E> ar: v1.getAdyascentes()){
			if(ar.getPredecesor()==v1)
				tR.addLast(ar);
		}
		
		return tR;
	}
	
	
	
}