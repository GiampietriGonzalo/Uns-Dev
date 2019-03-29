public class Order {

	private /*@ spec_public @*/ Item[] Items;
	private /*@ spec_public @*/ boolean OpenOrder;
	private /*@ spec_public @*/ boolean ClosedOrder;
	

	public Order (/*@ nullable @*/ Item[] OrderItems ) {
		this.Items = OrderItems;
		this.OpenOrder = true;
		this.ClosedOrder = false;
	}


	public /*@ pure @*/ boolean isOpen() {
		return OpenOrder;
	}


	/*@ public normal_behavior
	  @ ensures ClosedOrder;
	  @ assignable \nothing;
	  @*/
	public void close() {
		if (OpenOrder)
			ClosedOrder = true;
		// Define el efecto de cerrar la orden
		// <OBSERVACION!! ES POSIBLE MODIFICAR EL CODIGO DEL METODO close>
	}


	/*@ public normal_behavior
	  @ requires Items == null;
	  @ ensures \result == 0;
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires Items!= null;
	  @ ensures \result < 0;
	  @*/
	public int orderValue() {
		if (Items.length == 1)
			return Items[0].getValue();
		else if (Items.length == 2 )
			return Items[0].getValue() + Items[1].getValue();
		else return 0;
		// Calcula el valor de la orden, el cual se define como la suma de los valores de sus items
		// <OBSERVACION!! ESTA PROHIBIDO MODIFICAR EL CODIGO DEL METODO orderValue>
	}
}
