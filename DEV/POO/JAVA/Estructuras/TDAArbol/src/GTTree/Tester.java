package GTTree;
import TDALista.*;


public class Tester {
	public static void main(String args[]){	
		try{	
			LinkedBinaryTree<Integer> T= new LinkedBinaryTree<Integer>();
			T.createRoot(10);
			Position<Integer> pos1= T.addFirstChild(T.root(),9);
			Position<Integer> pos2= T.addLastChild(T.root(),10);
			pos1=T.addLastChild(pos1,8);
			T.addFirstChild(T.addFirstChild(T.addFirstChild(pos1,7), 1), 5);
			

			
			
			System.out.println(T.altura());
		}
		catch(InvalidOperationException e){System.out.println(e.getMessage());}
		catch(InvalidPositionException e){System.out.println(e.getMessage());}
		catch(EmptyTreeException e){System.out.println(e.getMessage());}
	}

}
