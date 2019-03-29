package GTTree;
import java.util.Iterator;
import TDALista.*;
public class LinkedTree<E> implements Tree<E>{

		
	//ATRIBUTOS	
		Nodo<E> root;
		int tamaño;
		
	//COMANDOS
		public void createRoot(E e)throws InvalidOperationException{
			if (!isEmpty())
				throw new InvalidOperationException("el Arbol no esta vacio, ya tiene una raiz");
			root= new Nodo<E>(e);
			tamaño=1;
		}
		
		
		public Position<E> addFirstChild(Position<E> v,E e) throws InvalidPositionException{
			Nodo<E> n=checkPosition(v);
			Nodo<E> nuevo= new Nodo<E>(n,e);
			n.getHijos().addFirst(nuevo);
			tamaño++;
			return nuevo;
		}
		
		public Position<E> addLastChild(Position<E> v, E e)throws InvalidPositionException{
			Nodo<E> n=checkPosition(v);
			Nodo<E> nuevo= new Nodo<E>(n,e);
			n.getHijos().addLast(nuevo);
			tamaño++;
			return nuevo;
		}
		
		public Position<E> addBefore(Position<E> v, Position<E> rb, E e) throws InvalidPositionException{
			Nodo<E> padre=checkPosition(v);
			Nodo<E> hermano=checkPosition(rb);			
			Nodo<E> nuevo= new Nodo<E>(padre,e);
			PositionList<Nodo<E>> listaHijos= padre.getHijos();
			boolean encontre=false;
			Position<Nodo<E>> posHijosPadre=null;
			try{
				posHijosPadre= listaHijos.first();
				while(posHijosPadre!=null && !encontre){
					if(hermano!=posHijosPadre.element())
						if (posHijosPadre!=listaHijos.last())
							posHijosPadre=listaHijos.next(posHijosPadre);
						else
							posHijosPadre=null;		
					else
							encontre=true;
				}
				if (!encontre)
					throw new InvalidPositionException("rb no es hijo de p");
				
					
			}
			catch (EmptyListException e1) {System.out.println(e1.getMessage());}
			catch(BoundaryViolationException e1){System.out.println(e1.getMessage());}
			listaHijos.addBefore(posHijosPadre, nuevo);
			tamaño++;
			return nuevo;		
			
			}
		
		public Position<E> addAfter(Position<E> v, Position<E> lb, E e) throws InvalidPositionException{
			Nodo<E> padre=checkPosition(v);
			Nodo<E> hermano=checkPosition(lb);			
			Nodo<E> nuevo= new Nodo<E>(padre,e);
			PositionList<Nodo<E>> listaHijos= padre.getHijos();
			boolean encontre=false;
			Position<Nodo<E>> posHijosPadre=null;
			try {
				posHijosPadre= listaHijos.first();
				while(posHijosPadre!=null && !encontre){
					if(hermano!=posHijosPadre.element())
						if (posHijosPadre!=listaHijos.last())
							posHijosPadre=listaHijos.next(posHijosPadre);
						else
							posHijosPadre=null;		
					else
							encontre=true;
				}
				if (!encontre)
					throw new InvalidPositionException("lb no es hijo de p");
				
					
			}
			catch (EmptyListException e1) {System.out.println(e1.getMessage());}
			catch(BoundaryViolationException e1){System.out.println(e1.getMessage());}
			listaHijos.addAfter(posHijosPadre, nuevo);
			tamaño++;
			return nuevo;		
		}
		
	//CONSULTAS
		public int size(){return tamaño;}
		public boolean isEmpty(){return tamaño==0;}
		public Position<E> root()throws EmptyTreeException{
			if (isEmpty())
				throw new EmptyTreeException("Arbol vacio, no hay raiz");
			return root;
		}
		
		public Position<E> parent(Position<E> v)throws InvalidPositionException,BoundaryViolationException{
			Nodo<E> n= checkPosition(v);
			if (isRoot(n))
				throw new BoundaryViolationException("La posicion pasada es la raiz");
			return n.getPadre();
		}
		
		public Iterable<Position<E>> children(Position<E> v)throws InvalidPositionException{
			Nodo<E> n=checkPosition(v);
			PositionList<Position<E>> lista= new DoubleLinkedList<Position<E>>();
			if (!n.getHijos().isEmpty())
				for (Nodo<E> nodo: n.getHijos())
					lista.addLast(nodo);
			return lista;
			
		}
		
		public boolean isExternal(Position<E> v)throws InvalidPositionException{
				Nodo<E> n= checkPosition(v);
				return (n.getHijos().isEmpty());
		}
		
		public boolean isInternal(Position<E> v)throws InvalidPositionException{
			Nodo<E> n= checkPosition(v);
			return !(n.getHijos().isEmpty());	
		}
		
		public boolean isRoot(Position<E> v) throws InvalidPositionException{
			Nodo<E> n=checkPosition(v);
			return n==root;	
		}
		public Iterator<E> iterator(){
			PositionList<E> lista= new DoubleLinkedList<E>();
			for (Position<E> p: positions())
				lista.addLast(p.element());
			return lista.iterator();
			
		}
		public Iterable<Position<E>> positions(){
			Nodo<E> n= root;
			PositionList<Position<E>> list= new DoubleLinkedList<Position<E>>();	
			if (!isEmpty())
				pre(list,n);
			
			return list;	
		}
		
		private void pre(PositionList<Position<E>> l, Nodo<E> r){
			//Metodo auxiliar recursivo
			l.addLast(r);
			for (Nodo<E> h: r.getHijos())
				pre(l,h);
			
		}
		
		public E replace(Position<E> v, E e)throws InvalidPositionException{
			Nodo<E> n= checkPosition(v);
			E aux= n.element();
			n.setElemento(e);
			return aux;
		}
		
		
		
		
		private Nodo<E> checkPosition(Position<E> v)throws InvalidPositionException{
			if (v==null)
				throw new InvalidPositionException("Posicion Nula");
			if (isEmpty())
				throw new InvalidPositionException("El arbol esta vacio");
			return(Nodo<E>) v;
		}


		public E raplace(Position<E> v, E e) throws InvalidPositionException {
			Nodo<E> n= checkPosition(v);
			E aux= n.element();
			n.setElemento(e);
			return aux;
		}


		public void removeExternalNode(Position<E> p)throws InvalidPositionException {
			Nodo<E> n=checkPosition(p);
			
			if (!isExternal(n))
				throw new InvalidPositionException("La posicion pasada no es una posicion interna");
			if (isRoot(n) && n.getHijos().isEmpty()){
				root.setElemento(null);
				root=null;
				tamaño=0;
			}
			else{
				Nodo<E> padre=n.getPadre();
				boolean encontre=false;
			try {
				
				if (isRoot(n))
					throw new InvalidPositionException("Es la raiz con mas de un hijo");
				else{
					//BUSCO LA POSICION DEL NODO EN LA LISTA HERMANOS
					PositionList<Nodo<E>> hermanos= padre.getHijos();
					Position<Nodo<E>> posAE=hermanos.first();
					while(!encontre && posAE!=null){
						if (n==posAE.element())
							encontre=true;
						else
							if (posAE==hermanos.last())
								posAE=null;
							else
								posAE=hermanos.next(posAE);
					}
					if (!encontre)
						throw new InvalidPositionException ("La posicion ingresada no esta en el arbo---arbol dañado");
					
					
					//ELIMINO AL NODO DE LA LISTA DE HERMANOS
					n.setPadre(null);
					hermanos.remove(posAE);
					tamaño--;
				
				
				}
			
				
			} 
			catch (EmptyListException e) {System.out.println(e.getMessage());}
			catch (BoundaryViolationException e){System.out.println(e.getMessage());}
		}
	}


		public void removeInternalNode(Position<E> p)throws InvalidPositionException {
			
			Nodo<E> n=checkPosition(p);
		
			if (!isInternal(n))
				throw new InvalidPositionException("La posicion pasada no es una posicion interna");
			
			PositionList<Nodo<E>> hijos=n.getHijos();
			Nodo<E> padre=n.getPadre();
			boolean encontre=false;
			try {
				
				if (isRoot(n))
					if (n.getHijos().size()==1){
						Nodo<E> nuevo= n.getHijos().remove(n.getHijos().first());
						root=nuevo;
					}
					else
						throw new InvalidPositionException("Es la raiz con mas de un hijo");
				else{
					//BUSCO LA POSICION DEL NODO EN HIJOS
					PositionList<Nodo<E>> hermanos= padre.getHijos();
					Position<Nodo<E>> posAE=hermanos.first();
					while(!encontre && posAE!=null){
						if (n==posAE.element())
							encontre=true;
						else
							if (posAE==hermanos.last())
								posAE=null;
							else
								posAE=hermanos.next(posAE);
					}
					if (!encontre)
						throw new InvalidPositionException ("La posicion ingresada no esta en el arbo---arbol dañado");
					
					//SUBO A LOS HIJOS DEL NODO A ELIMINAR
					while(!hijos.isEmpty()){
						hijos.first().element().setPadre(padre);
						hermanos.addBefore(posAE,hijos.first().element());
						hijos.remove(hijos.first());
					}
					
					//ELIMINO AL NODO DE LA LISTA DE HERMANOS
					n.setPadre(null);
					hermanos.remove(posAE);
				
				
				}
				tamaño--;
			} 
			catch (EmptyListException e) {System.out.println(e.getMessage());}
			catch (BoundaryViolationException e){System.out.println(e.getMessage());}
		}



		/*public void removeNode(Position<E> p) throws InvalidPositionException {
			Nodo<E> n =checkPosition(p);
			if (isExternal(n))
				removeExternalNode(n);
			else
				if (isInternal(n))
					removeInternalNode(n);
			
		}*/
		
		public void removeNode(Position<E> p)throws InvalidPositionException{
			if(p==null)
				throw new InvalidPositionException("Posicion nula");
			
			Nodo<E> nodo=null;
			try{
				nodo=(Nodo<E>) p;
			}
			catch(ClassCastException e){System.out.println("La posicion pasada no pertenece a este arbol");}
			
			if(root==nodo)
				if(nodo.getHijos().isEmpty()){
					root=null;
					tamaño=0;
				}
				else
					if(nodo.getHijos().size()>1)
						throw new InvalidPositionException("La posición pasada es la raíz con mas de un hijo");
					else{
						//Es raíz y tiene un solo hijo
						try{
							root=nodo.getHijos().first().element();
							root.setPadre(null);
							tamaño--;
						}
						catch(EmptyListException e){System.out.println(e.getMessage());}
					}
			else{
				//No es raiz
				Position<Nodo<E>> posAE=null;
				PositionList<Nodo<E>> hermanos=nodo.getPadre().getHijos();
				try{
					posAE=hermanos.first();
					}
				catch(EmptyListException e){System.out.println(e.getMessage());}
				boolean encontre=false;
				try{
					while(!encontre && posAE!=null){
						if(nodo==posAE.element())
							encontre=true;
						else
							if(hermanos.last()==posAE)
								posAE=null;
							else
								posAE=hermanos.next(posAE);
					
							if(!nodo.getHijos().isEmpty()){
								//Es interno
								
								while(!nodo.getHijos().isEmpty()){
									nodo.getHijos().first().element().setPadre(nodo.getPadre());
									hermanos.addBefore(posAE,nodo.getHijos().remove(nodo.getHijos().first()));
									
								}//Fin while
							}
						}
					}
					catch(EmptyListException e){System.out.println(e.getMessage());}
					catch(BoundaryViolationException e){System.out.println(e.getMessage());}
					
					//Lo elimino
					hermanos.remove(posAE);
					tamaño--;
					nodo.setPadre(null);
					
					
				}
			}	
			
		
		
		
		
		
}
