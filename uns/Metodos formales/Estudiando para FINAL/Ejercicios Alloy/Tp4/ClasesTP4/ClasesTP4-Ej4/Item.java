public class Item {

	private int ItemID;
	private /*@ spec_public @*/ int Value;
	//El valor de un ´ıtem debe ser un n´umero positivo
	/*@ public invariant Value > 0 ;*/

	public Item(int ID, int Val) {
		this.ItemID = ID;
		this.Value = Val;
	}

	public /*@ pure @*/ int getValue() {
		return this.Value;
	}
}
