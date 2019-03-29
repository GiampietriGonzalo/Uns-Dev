public class Categoria {

  private /*@ spec_public @*/ Libro[] librosCat;

  // < ESTA PROHIBIDO ELIMINAR O MODIFICAR ESTE INVARIANTE >
  // Todo elemento del conjunto de libros es una instancia de la clase Libro (es no nulo)
  /*@ public invariant (\forall int i; i>=0 && i< librosCat.length; librosCat[i]!= null); @*/


  public Categoria(Libro[] librosC){
    librosCat = librosC;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  public boolean estaEnCategoria(Libro b){
    return (librosCat[0] == b | librosCat[1] == b | librosCat[2] == b | librosCat[3] == b | librosCat[4] == b);
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


}
