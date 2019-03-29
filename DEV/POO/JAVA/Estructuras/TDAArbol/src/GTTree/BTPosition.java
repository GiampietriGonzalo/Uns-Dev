package GTTree;
import TDALista.*;

public interface BTPosition<E> extends Position<E>{
	
	public void setParent(BTPosition<E> p);
	public void setLeft(BTPosition<E> p);
	public void setRight(BTPosition<E> p);
	public void setElement(E e);
	
	public BTPosition<E> getParent();
	public BTPosition<E> getLeft();
	public BTPosition<E> getRight();
}
