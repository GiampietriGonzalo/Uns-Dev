package GrafoConMatrizDeAdyascencias;
import TDALista.*;

public class Vertice<V,E> implements Vertex<V>{
	
	//Atributos
	private V rotulo;
	int indice;
	private Position<Vertice<V,E>> posEnVertices;
	
	//Constructor
	public Vertice(V rotulo,int indice){
		this.rotulo=rotulo;
		posEnVertices=null;
		this.indice=indice;
	}
	
	//Comandos
	public void setRotulo(V r){rotulo=r;}
	public void setPosEnVertices(Position<Vertice<V,E>> pos){posEnVertices=pos;}
	public void setIndice(int i){indice=i;}

	
	//Consultas
	public V element(){return rotulo;}
	public int getIndice(){return indice;}
	public Position<Vertice<V,E>> getPosEnVertices(){return posEnVertices;}
	
}
