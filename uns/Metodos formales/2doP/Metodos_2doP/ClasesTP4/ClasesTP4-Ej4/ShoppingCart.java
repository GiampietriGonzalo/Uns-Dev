public class ShoppingCart {

	private /*@ spec_public @*/ Order CartOrder;
	
	public ShoppingCart(){
	}
	
	
	public boolean isEmpty() {
		return CartOrder == null;
	}
	
	public boolean isDispatchable() {
		return (!isEmpty() & !CartOrder.isOpen());
	}
	
	
	public void dispatchCart(){
		this.CartOrder = null;
	}
	
	
	public void addOrder(Order Ord) {
		if (isEmpty() & Ord.isOpen()) 
			this.CartOrder = Ord; 
	}

	
	public /*@ pure @*/ int cartValue() {
		if (isEmpty())
			return 0;
		else return CartOrder.orderValue();
	}
	
	
}
