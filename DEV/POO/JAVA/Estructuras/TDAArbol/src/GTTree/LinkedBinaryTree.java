package GTTree;
import TDALista.*;

import java.util.Iterator;

public class LinkedBinaryTree<E> implements BinaryTree<E>{
		//Atributos
		private BTPosition<E> root;
		private int  size;
		
		//Constructor
		public LinkedBinaryTree(){
			size=0;
			root=null;
		}
		
		//Comandos
		private void pre(PositionList<Position<E>> l, Position<E> r)throws InvalidPositionException{
			//Metodo auxiliar recursivo
			try{ 
				l.addLast(r);
				if (hasLeft(r))
					pre(l,left(r));
			}
			catch(BoundaryViolationException e){throw new InvalidPositionException(e.getMessage());}
		}
		
		public void removeInternalNode(Position<E> v)throws InvalidPositionException{
			BTPosition<E> n=checkPosition(v);
			if (n==root)
				if (n.getLeft()!=null && n.getRight()==null){
					//Es root y tiene un solo hijo
					n.getLeft().setParent(null);
					root=n.getLeft();
					n.setLeft(null);
					n.setElement(null);
				
				}
				else
					throw new InvalidPositionException("Error: la posicion pasada es la raiz con 2 hijos");
			else{
				
				if (n.getLeft()!=null && n.getRight()!=null)
					throw new InvalidPositionException("No se puede remoce un nodo con 2 hijos");
				
				//Busco la posicion en left o right del padre
				
				if (n.getParent().getLeft() == n){
					n.getParent().setLeft(n.getLeft());
				}
					
				else{
					n.getParent().setRight(n.getLeft());
				}
				
				//Modifico el padre del hijo de la pos pasada y elimino la posicion pasada
				n.getLeft().setParent(n.getParent());
				
				n.setLeft(null);
				n.setParent(null);
				n.setElement(null);
				
				size--;
			}
		}
		
		public void removeExternalNode(Position<E> v)throws InvalidPositionException{
			BTPosition<E> n=checkPosition(v);
			if (n==root){
				root.setElement(null);
			}
			else{
				//Es un nodo hoja y no es la raiz
				//Comparo con izquierda y derecha del padre, y asigno nulo a dicha referencia			
				if (n==n.getParent().getLeft())
					n.getParent().setLeft(null);
				else
					n.getParent().setRight(null);
				
				//Elimino la posicion pasado por parametro
				n.setParent(null);
				n.setElement(null);
				size--;
			}
				
			
		}
		
		public void Attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException{
			try{
				BTPosition<E> n=checkPosition(v);
				if (!isExternal(n))
					throw new InvalidPositionException("La posicion pasada como parametros no es una hoja");
			
				BTPosition<E> r1= checkPosition(T1.root());
				BTPosition<E> r2= checkPosition(T2.root());
				n.setLeft(r1);
				n.setRight(r2);
				r1.setParent(n);
				r2.setParent(n);
			}
			catch(EmptyTreeException e){throw new InvalidPositionException("Uno de los arboles esta vacio");}
			
		}
		
		
		//COnsultas
		public boolean isEmpty(){return size==0;}
		
		public boolean isRoot(Position<E> v)throws InvalidPositionException{
			checkPosition(v);
			return v==root;
		}
		
		public Position<E> root()throws EmptyTreeException{
			if (isEmpty())
				throw new EmptyTreeException("Arbol vacio");
			return root;
		}
		
		private BTPosition<E> checkPosition(Position<E> v)throws InvalidPositionException{
			if (v==null)
				throw new InvalidPositionException("Posicion Nula");
			if (isEmpty())
				throw new InvalidPositionException("El arbol esta vacio");
			return (BTPosition<E>) v;
		}
		
		public int size() { return size; }
		
		public Iterable<Position<E>> children(Position<E> v )throws InvalidPositionException {
			BTPosition<E> n=checkPosition(v);	
			PositionList<Position<E>> hijos = new DoubleLinkedList<Position<E>>();
			try{	
				if( hasLeft(v) ) 
					hijos.addLast( left(v) );
				
				if( hasRight(v) ) 
					hijos.addLast( right(v) );
			}
			catch(BoundaryViolationException e){throw new InvalidPositionException(e.getMessage());}
				
				return hijos;
			}
		
		public boolean hasLeft(BTPosition<E> v) throws InvalidPositionException {
			BTPosition<E> n = checkPosition(v);
			return n.getLeft() != null;
		}
		
		public boolean hasRigth(BTPosition<E> v) throws InvalidPositionException{
			BTPosition<E> n= checkPosition(v);
			return n.getRight()!=null;
			
		}
		
		
		
		public Position<E> left(BTPosition<E> v) throws InvalidPositionException, BoundaryViolationException {
			BTPosition<E> n = checkPosition(v);
			if( n.getLeft() == null ) throw new BoundaryViolationException("No tiene hijo izquierdo");
			return n.getLeft();
		}
		
		public Position<E> rigth(BTPosition<E> v) throws InvalidPositionException,BoundaryViolationException{
			BTPosition<E> n= checkPosition(v);
			if (n.getRight()==null)
				throw new BoundaryViolationException("No tiene hijo derecho"); 
			return n.getRight();
		}
		
		
		public boolean isInternal(Position<E> v) throws InvalidPositionException {
			return hasLeft(v) || hasRight(v);
		}
		
		public boolean isExternal(Position<E> v) throws InvalidPositionException{
			return (!hasLeft(v) && !hasRight(v));
			
		}
		public Position<E> parent(Position<E> v)throws InvalidPositionException,BoundaryViolationException{
			BTPosition<E> n=checkPosition(v);
			if (n.getParent()==null)
				throw new BoundaryViolationException("La posicion es el root o no pertenece a este arbol"); 
			return n.getParent();
		}
		
		public Iterable<Position<E>> positions(){
			PositionList<Position<E>> pos= new DoubleLinkedList<Position<E>>();
			try{
				if (size!=0)
					pre(pos,root);
			}
			catch(InvalidPositionException e){}
			return pos;
			
		}
		
		public Iterator<E> iterator(){
			Iterable<Position<E>> pos=positions();
			PositionList<E> elementos= new DoubleLinkedList<E>();
			for (Position<E> p: pos)
				elementos.addLast(p.element());
			return elementos.iterator();
		}
			
		public E replace(Position<E> v, E o)throws InvalidPositionException{
			BTPosition<E> n=checkPosition(v);
			E aux=n.element();
			n.setElement(o);
			return aux;
		}
		
		public void createRoot(E e)throws InvalidOperationException{
			if (size>0)
				throw new InvalidOperationException("El arbol ya tiene una raiz");
			size=1;
			root=new BTNode<E>();
			root.setElement(e);
			
		}
		
		public Position<E> addLeft(Position<E>v,E e) throws InvalidPositionException{
			BTPosition<E> n=checkPosition(v);
			if (n.getLeft()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo izquierdo");
			BTPosition<E> l= new BTNode<E>();
			l.setElement(e);
			l.setParent(n);
			n.setLeft(l);
			size++;
			return n;
		}
		
		public E remove(Position<E> v)throws InvalidPositionException{
			BTPosition<E> n= checkPosition(v);
			if (isInternal(n))
				removeInternalNode(v);
			else
				removeExternalNode(v);
			
			return v.element();
		}

		
		public Position<E> addFirstChild(Position<E> p, E e)throws InvalidPositionException {
			//Agrega un nuevo hijo en la posicion izquierda y la retorna
			BTPosition<E> n=checkPosition(p);
			BTNode<E> nuevo= new BTNode<E>();
			nuevo.setElement(e);
		
			if (n.getLeft()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo izquierdo");
			n.setLeft(nuevo);
			nuevo.setParent(n);
			size++;
			
		return nuevo;
			
		}

	
		public Position<E> addLastChild(Position<E> p, E e)throws InvalidPositionException {
			//Agrega un nuevo hijo en la posicion derecha y la retorna
			BTPosition<E> n=checkPosition(p);
			BTNode<E> nuevo= new BTNode<E>();
			nuevo.setElement(e);
			
			if (n.getRight()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo derecho");
			n.setRight(nuevo);
			nuevo.setParent(n);
			size++;
				
			return nuevo;
		}

		public Position<E> addBefore(Position<E> p, Position<E> rb, E e)throws InvalidPositionException{
			BTPosition<E> n=checkPosition(p);
			BTNode<E> nuevo= new BTNode<E>();
			nuevo.setElement(e);
		
			if (n.getRight()!=rb)
				throw new InvalidPositionException("rb No coincide con el hijo derecho de su padre");
			if (n.getLeft()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo izquierdo");
			n.setLeft(nuevo);
			nuevo.setParent(n);
			size++;
			
			return nuevo;
		}

		
		public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException{
			BTPosition<E> n=checkPosition(p);
			BTNode<E> nuevo= new BTNode<E>();
			nuevo.setElement(e);
	
			if (n.getLeft()!=lb)
				throw new InvalidPositionException("lb No coincide con el hijo derecho de su padre");
			if (n.getRight()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo Derecho");
			n.setRight(nuevo);
			nuevo.setParent(n);
			size++;
		
			return nuevo;
		}

		
		public void removeNode(Position<E> p) throws InvalidPositionException {
			BTPosition<E> n=checkPosition(p);
			if (isInternal(n))
				removeInternalNode(n);
			else
				removeExternalNode(n);
		}

		@Override
		public Position<E> left(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
			BTPosition<E> n=checkPosition(v);
			if (!hasLeft(n))
				throw new BoundaryViolationException("Error: la posicion pasada no tiene hijo izquierdo");
			return n.getLeft();
		}

		@Override
		public Position<E> right(Position<E> v)throws InvalidPositionException, BoundaryViolationException {
			BTPosition<E> n=checkPosition(v);
			if (!hasRight(n))
				throw new BoundaryViolationException("Error: la posicion pasada no tiene hijo derecha");
			return n.getRight();
		}
		

		
		public boolean hasLeft(Position<E> v) throws InvalidPositionException {
			BTPosition<E> n=checkPosition(v);
			return n.getLeft()!=null;
		}

		
		public boolean hasRight(Position<E> v) throws InvalidPositionException {
			BTPosition<E> n=checkPosition(v);
			return n.getRight()!=null;
		}

		
		public Position<E> addRight(Position<E> v, E e)throws InvalidOperationException, InvalidPositionException {
			BTPosition<E> n=checkPosition(v);
			
			if (n.getRight()!=null)
				throw new InvalidPositionException("La posicion pasada ya tiene un hijo Derecho");
			BTPosition<E> l= new BTNode<E>();
			l.setElement(e);
			l.setParent(n);
			n.setRight(l);
			size++;
			return n;
		}
		
		public int altura(){
			int h=0;
			try{
				Position<E> v=this.root();
				int a1=0;
				int a2=0;
				if(this.isInternal(v)){
					
					if (this.hasLeft(v))
						 a1=altura(this,this.left(v));
					if(this.hasRight(v))
						 a2=altura(this,this.right(v));
		
					if (a1>a2)
						h=a1;
					else
						h=a2;
			
				}
				return h;
			}
			catch(EmptyTreeException e){System.out.println(e.getMessage());}
			catch(InvalidPositionException e){System.out.println(e.getMessage());}
			catch(BoundaryViolationException e){System.out.println(e.getMessage());}
			return h;
		}
		private int altura(BinaryTree<E> T, Position<E> v){
			int h=0;
			try{
				if(T.isInternal(v)){
					int a1=0;
					int a2=0;
					if (T.hasLeft(v))
						 a1=altura(this,this.left(v));
					if(T.hasRight(v))
						 a2=altura(this,this.right(v));
		
					if (a1>a2)
						h=a1;
					else
						h=a2;
			
					
				}
				
			}
			catch(InvalidPositionException e){System.out.println(e.getMessage());}
			catch(BoundaryViolationException e){System.out.println(e.getMessage());}
			return h+1;
		}


}
		
		
