public class Pagina {

	/*@ public invariant NumeroPag >0; @*/
	private int /*@ spec_public @*/ NumeroPag;
	
	public Pagina (int NumPag) {
		this.NumeroPag = NumPag;
	}
	
	public int numeroPagina() {
		return this.NumeroPag;
	}
	
	public void renumerarPagina(int n) {
		this.NumeroPag = n;
	}
	
	
}
