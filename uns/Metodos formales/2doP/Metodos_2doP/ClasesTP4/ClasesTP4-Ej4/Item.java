public class Item {

	private int ItemID;
	private /*@ spec_public @*/ int Value;

	public Item(int ID, int Val) {
		this.ItemID = ID;
		this.Value = Val;
	}

	public /*@ pure @*/ int getValue() {
		return this.Value;
	}
}
