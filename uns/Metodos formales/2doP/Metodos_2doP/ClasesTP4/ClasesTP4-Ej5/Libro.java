public class Libro {

	private int ISBN;
	private /*@ spec_public @*/ Pagina[] PagsLibro;
	private /*@ spec_public @*/ int CantPaginas;
	private /*@ spec_public @*/ int[] Marcadores;
	private /*@ spec_public @*/ int PaginaActual;


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

	public void pasarPagina() throws InvalidPageException{
		// Pasa a la siguiente pagina salvo que se encuentre en la ultima pagina, en cuyo caso lanza una excepcion
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
