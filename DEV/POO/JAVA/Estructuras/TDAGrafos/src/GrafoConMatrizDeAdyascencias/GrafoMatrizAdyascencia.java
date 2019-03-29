package GrafoConMatrizDeAdyascencias;
import TDALista.*;

public class GrafoMatrizAdyascencia<V,E> implements Graph<V,E>{
		
	//Atributos
	int cantVertices;
	Edge<E>[][] M;
	PositionList<Arco<V,E>> arcos;
	PositionList<Vertice<V,E>> vertices;
	

	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> tR= new DoubleLinkedList<Vertex<V>>();
		for(Vertice<V,E> v: vertices)
			tR.addLast(v);
		return tR;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> tR= new DoubleLinkedList<Edge<E>>();
		for(Arco<V,E> v: arcos)
			tR.addLast(v);
		return tR;
	}

	
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> v1= checkVertex(v);
		PositionList<Edge<E>> tR= new DoubleLinkedList<Edge<E>>();
		for(int i=0; i< M[0].length;i++)
			if(M[v1.getIndice()][i]!=null)
				tR.addLast(M[v1.getIndice()][i]=null);	
		return tR;
	}

	
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> v1= checkVertex(v);
		Arco<V,E> a1= checkEdge(e);
		Vertice<V,E> tR=null;
		
		if(v1==a1.getSucesor())
			tR=a1.getPredecesor();
		else
			if (v1==a1.getPredecesor())
				tR=a1.getSucesor();
			else
				throw new InvalidVertexException("El vertice no tiene un opuesto con respecto a este arco");
		return tR;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> a=checkEdge(e);
		Vertex<V>[] tR= (Vertice<V,E>[])new Vertice[2];
		tR[0]=a.getPredecesor();
		tR[1]=a.getSucesor();
		return tR;
	}

	
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w)throws InvalidVertexException {
		Vertice<V,E> v1=checkVertex(v);
		Vertice<V,E> v2=checkVertex(w);
		boolean son=false;
		int j=0;
		Arco<V,E> actual;
		while(!son && j<M[0].length){
			actual=(Arco<V,E>)M[v1.getIndice()][j];
			if(actual.getSucesor()==v2 || actual.getPredecesor()==v2)
				son=true;
			j++;
		}
		
		return son;
	}

	
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> v1= checkVertex(v);
		V tR= v.element();
		v1.setRotulo(x);
		return tR;
	}


	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v1= new Vertice<V,E>(x,M.length);
		Edge<E>[][]aux=(Edge<E>[][])new Arco[v1.getIndice()][v1.getIndice()];
		for(int i=0; i<M.length;i++)
			for(int j=0; i<M[0].length;i++)
				aux[i][j]=M[i][j];
		M=aux;
		vertices.addLast(v1);
		try{
			v1.setPosEnVertices(vertices.last());
		}
		catch(EmptyListException e){System.out.println(e.getMessage());}
		cantVertices++;
		return v1;
	}

	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e)throws InvalidVertexException {
		Vertice<V,E> v1= checkVertex(v);
		Vertice<V,E> v2= checkVertex(w);
		Arco<V,E> ar= new Arco<V,E>(e,v1,v2);
		M[v1.getIndice()][v2.getIndice()]=ar;
		M[v2.getIndice()][v1.getIndice()]=ar;
		arcos.addLast(ar);
		
		try{
			ar.setPosicionEnAdyascentes(arcos.last());
		}
		catch(EmptyListException e1){System.out.println(e1.getMessage());}
		
		return ar;
	}

	
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> v1= checkVertex(v);
		Edge<E>[][] aux= (Edge<E>[][])new Arco[v1.getIndice()-1][v1.getIndice()-1];
		for(int i=0; i<M.length-1;i++)
			for(int j=0; i<M[0].length-1;i++)
				aux[i][j]=M[i][j];
		M=aux;
		try {
			vertices.remove(v1.getPosEnVertices());
		} catch (InvalidPositionException e) {System.out.println(e.getMessage());
		}
		cantVertices--;
		return v.element();
	}

	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> ar= checkEdge(e);
		M[ar.getSucesor().getIndice()][ar.getPredecesor().getIndice()]=null;
		M[ar.getPredecesor().getIndice()][ar.getSucesor().getIndice()]=null;
		try{	
			arcos.remove(ar.getPosEnAdyascentes());
		}
		catch(InvalidPositionException e1){System.out.println(e1.getMessage());}
		return null;
	}
	
	private Vertice<V,E> checkVertex(Vertex<V> ver) throws InvalidVertexException{
		if(ver==null)
			throw new InvalidVertexException("Vertice nulo");
		return (Vertice<V,E>)ver;
		
	}
	
	private Arco<V,E> checkEdge(Edge<E> ver) throws InvalidEdgeException{
		if(ver==null)
			throw new InvalidEdgeException("Arco nulo");
		return (Arco<V,E>)ver;
		
	}
	
}
