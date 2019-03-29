public class Order {

	private /*@ spec_public @*/ Item[] Items;
	private /*@ spec_public @*/ boolean OpenOrder;
	private /*@ spec_public @*/ boolean ClosedOrder;

	/*Una orden se encuentra abierta o cerrada (siendo estos dos estados mutuamente excluyentes) */
	/*@ public invariant OpenOrder != ClosedOrder ;*/

	/*Una orden no puede albergar m´as de dos ´ıtems*/
	/*@ public invariant (Items.length > 0 & Items.length <= 2) ;*/

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
	  @ requires !ClosedOrder;
	  @ ensures !OpenOrder;
	  @ ensures ClosedOrder;
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires !OpenOrder;
	  @ requires ClosedOrder;
	  @ ensures OpenOrder == \old(OpenOrder);
	  @ ensures ClosedOrder == \old(ClosedOrder);
	  @ assignable \nothing;
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires OpenOrder == null || ClosedOrder == null;
	  @ ensures OpenOrder == \old(OpenOrder);
	  @ ensures ClosedOrder == \old(ClosedOrder);
	  @
	  @
	  @*/
	public void close() {
		if (OpenOrder){
			ClosedOrder = true;
			OpenOrder = false;
		}
		// Define el efecto de cerrar la orden
		// <OBSERVACION!! ES POSIBLE MODIFICAR EL CODIGO DEL METODO close>
	}


	/*@ public normal_behavior
	  @ requires Items.length == 0;
	  @ ensures \result == 0;
	  @
	  @ also
	  @
	  @ public normal_behavior
		@ requires Items != null;
		@ requires Items.length == 2 & Items[0] != null & Items[1] != null;
	  @	ensures \result == (Items[0].Value + Items[1].Value);
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires Items.length == 1;
	  @	ensures \result == Items[0].Value;
	  @*/
	public int orderValue() {

		if (Items.length == 1)
			return Items[0].getValue();

		else
			if (Items.length == 2 )
				return Items[0].getValue() + Items[1].getValue();
		else
			return 0;
		// Calcula el valor de la orden, el cual se define como la suma de los valores de sus items
		// <OBSERVACION!! ESTA PROHIBIDO MODIFICAR EL CODIGO DEL METODO orderValue>
	}
}
