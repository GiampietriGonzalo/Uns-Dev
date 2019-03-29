public class User {

	private ShoppingCart Cart;
	private PaymentMethod Method;
	
	public User (ShoppingCart C, PaymentMethod M) {
		this.Cart = C;
		this.Method = M;
	}
	
	
	public void finishPurchase() {
		try {
			if (Cart.cartValue() <= Method.methodLimit())
				Cart.dispatchCart();
		}
		catch (InvalidPaymentMethodExc E) {}
	}		
}
