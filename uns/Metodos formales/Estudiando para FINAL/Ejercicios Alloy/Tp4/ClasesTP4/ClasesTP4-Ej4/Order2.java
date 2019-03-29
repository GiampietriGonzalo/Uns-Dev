public class Order2 {

	private /*@ spec_public @*/ Item[] Items;

	/*@public invariant  (Items.length > 0 & Items.length <= 2); @*/

	private /*@ spec_public @*/ boolean OpenOrder;
	private /*@ spec_public @*/ boolean ClosedOrder;

	/*public invariant (ClosedOrder <==> (OpenOrder == false));*/

	/*@ public invariant ClosedOrder != OpenOrder; @*/

	/*nullable quiere decir que el arreglo es puede ser nulo... y su contenido en caso de ser de algun tipo?*/

	public Order (/*@ nullable @*/ Item[] OrderItems ) {
		this.Items = OrderItems;
		this.OpenOrder = true;
		this.ClosedOrder = false;
	}


	public /*@ pure @*/ boolean isOpen() {
		return OpenOrder;
	}


	/*@ public normal_behavior
	  @ requires OpenOrder;
	  @ ensures ClosedOrder;
	  @ ensures !OpenOrder;
	  @ assignable ClosedOrder, OpenOrder;
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires !OpenOrder;
	  @ assignable \nothing;
	  @
	  @*/
	public void close() {
		if (OpenOrder) {
			OpenOrder = false;
			ClosedOrder = true;
		}
		// Define el efecto de cerrar la orden
		// <OBSERVACION!! ES POSIBLE MODIFICAR EL CODIGO DEL METODO close>
	}


	/*@ public normal_behavior
	  @ requires Items == null | (Items.length < 0 | Items.length > 2);
	  @ ensures \result == 0;
   	  @
   	  @ also
   	  @
	  @ public normal_behavior
	  @ requires Items != null;
	  @ requires Items.length == 1 & Items[0] != null;
	  @ ensures \result == (Items[0].Value);
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires Items != null;
	  @ requires Items.length == 2 & Items[0] != null & Items[1] != null;
	  @ ensures \result == (Items[0].Value + Items[1].Value );
	  @
	  @
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
