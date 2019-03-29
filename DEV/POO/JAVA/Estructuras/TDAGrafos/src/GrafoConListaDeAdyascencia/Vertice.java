package GrafoConListaDeAdyascencia;
import TDALista.*;

public class Vertice<V,E> implements Vertex<V>{
	
	//Atributos
	private V rotulo;
	private PositionList<Arco<V,E>> adyascentes;
	private Position<Vertice<V,E>> posEnVertices;
	
	//Constructor
	public Vertice(V rotulo){
		this.rotulo=rotulo;
		adyascentes= new DoubleLinkedList<Arco<V,E>>();
		posEnVertices=null;
	}
	
	//Comandos
	public void setRotulo(V r){rotulo=r;}
	public void setPosEnVertices(Position<Vertice<V,E>> pos){posEnVertices=pos;}


	
	//Consultas
	public V element(){return rotulo;}
	public PositionList<Arco<V,E>> getAdyascentes(){return adyascentes;}
	public Position<Vertice<V,E>> getPosEnVertices(){return posEnVertices;}
	
}
