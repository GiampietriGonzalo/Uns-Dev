package GTTree;

public class BTNode<E> implements BTPosition<E>{
	
	//Atributos
	E elemento;
	BTPosition<E> parent,left,right;
	
	//Constructor
	public BTNode(){
		elemento=null;
		parent=null;
		left=null;
		right=null;
	}
	//Comandos
	public void setParent(BTPosition<E> p){parent=p;}
	public void setLeft(BTPosition<E> i){left=i;}
	public void setRight(BTPosition<E> d){right=d;}
	public void setElement(E e){elemento=e;}
	
	//Consultas
	public BTPosition<E> getParent(){return parent;}
	public BTPosition<E> getLeft(){return left;}
	public BTPosition<E> getRight(){return right;}
	public E element(){return elemento;}
	
}
