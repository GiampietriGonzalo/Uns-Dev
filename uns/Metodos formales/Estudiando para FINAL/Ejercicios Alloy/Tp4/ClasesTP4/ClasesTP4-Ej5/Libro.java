public class Libro {

    /*Todo libro tiene al menos una p´agina*/
	/*@ public invariant PagsLibro.length>0 ;*/

			

	private int ISBN;
	/*el cual es un n´umero natural que provee un identificadorun´ıvoco para los libros.*/
	/*@ public invariant ISBN>=0; */	
	/*@ public invariant !(\exists Libro l1,l2; l1!=l2; l1.ISBN == l2.ISBN);*/  
    

	private /*@ spec_public @*/ Pagina[] PagsLibro;
	/*@ public invariant !(\exists int x,y; x>0 && x<PagsLibro.length && y>0 && y<PagsLibro.length && x!=y;PagsLibro[X]!=PagsLibro[y] && PagsLibro[x].numeroPagina()==PagsLibro[y].numeroPagina()) ;*/

	private /*@ spec_public @*/ int CantPaginas;
	/*@ public invariant CantPaginas==PagsLibro.length ;*/

	private /*@ spec_public @*/ int[] Marcadores;
	/*Todo libro tiene exactamente tres slots para almacenar marcadores*/
	/*@ public invariant Marcadores.length==3 ;*/

	/*Distintos marcadores (no libres) de un libro no pueden referenciar a la misma pagina del libro.*/
    /*@ public invariant !(\exists int x,y; x>0 && x<Marcadores.length && y>0 && y<Marcadores.length && x!=y; Marcadores[X]!=0 && Marcadores[x]==Marcadores[y]) ;*/

	private /*@ spec_public @*/ int PaginaActual;
	/*@ public invariant (\exists int x; x>0 && x<PagsLibro.length; PaginaActual==PagsLibro[x]) ;*/

	public Libro(int CantPag, int NumISBN) {
		this.ISBN = NumISBN;
		this.CantPaginas = CantPag;
		this.PagsLibro = new Pagina[CantPag];
		this.Marcadores = new int[3];
		this.Marcadores[0] = 0;
		this.Marcadores[1] = 0;
		this.Marcadores[2] = 0;
		this.PaginaActual = 1;
		/*
		 * 	...  Seguidamente inicializa el arreglo PagsLibro con paginas no nulas
		 *       (se crea una cantidad de paginas igual a CantPag, tales que sus numeros
		 *       son correlativos de 1 a CantPag).
		 */
	}

	/*@ public normal_behavior
	  @ requires PaginaActual < (PagsLibro.length - 1);
	  @ ensures PaginaActual == \old(PaginaActual) + 1;
	  @
	  @ also
	  @
	  @ public exceptional_behavior
	  @ requires PaginaActual == (PagsLibro.length - 1)
	  @ signals_only InvalidPageException;
	  @ signals (InvalidPageException) PaginaActual == \old(PaginaActual);
	  @
	  @
	  @
	  @
	  @
	  @
	  @
	*/

	public void pasarPagina() throws InvalidPageException{
		// Pasa a la siguiente pagina salvo que se encuentre en la ultima pagina, en cuyo caso lanza una excepcion
		if(PaginaActual == PagsLibro.length-1 )
			throw new InvalidPageException();
		
		PaginaActual++;

	}


	/*
	  Marca la pagina actual, si es posible.
	 	Si hay un marcador libre, entonces se utiliza el slot correspondiente al menor número de marcador libre;
	 	en caso contrario, se reemplaza el marcador que referencia al numero de pagina mas chico.
	 */
	public void marcarPaginaActual() {
		if (Marcadores[1] == 0)
			Marcadores[1] = PaginaActual;
		if (Marcadores[2] == 0)
			Marcadores[2] = PaginaActual;
		if (Marcadores[0] == 0)
			Marcadores[0] = PaginaActual;
		if ((Marcadores[0] < Marcadores[1]) & (Marcadores[0] < Marcadores[2]))
			Marcadores[0] = PaginaActual;
		if ((Marcadores[1] < Marcadores[0]) & (Marcadores[1] < Marcadores[2]))
			Marcadores[1] = PaginaActual;
		if ((Marcadores[2] < Marcadores[0]) & (Marcadores[2] < Marcadores[1]))
			Marcadores[1] = PaginaActual;
		// <OBSERVACION!! ES POSIBLE MODIFICAR EL CODIGO DEL METODO marcarPaginaActual>
	}


	/*@ public normal_behavior
	  @ ensures PaginaActual == Marcadores[NumMarcador];
	  @ assignable \nothing;
	  @
	  @ also
	  @
	  @ public exceptional_behavior
	  @ signals_only InvalidMarkerException;
	  @*/
	public void saltarAMarcador (int NumMarcador) throws InvalidMarkerException {
		// Si es un numero de marcador valido, entonces salta a la pagina marcada
		if (NumMarcador >=0)
			if (NumMarcador < Marcadores.length)
				PaginaActual = Marcadores[NumMarcador];
			else throw new InvalidMarkerException();
		else throw new InvalidMarkerException();
		// <OBSERVACION!! ESTA PROHIBIDO MODIFICAR EL CODIGO DEL METODO saltarAMarcador>
	}

}
