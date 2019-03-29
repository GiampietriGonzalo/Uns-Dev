public class Libro {

  private /*@ spec_public @*/ boolean tieneCategoria;

  private /*@ spec_public @*/ Libro[] otrosLibrosEstanteria;

  // < ESTA PROHIBIDO ELIMINAR O MODIFICAR ESTE INVARIANTE >
  // Todo elemento de la estanteria es una instancia de la clase Libro (es no nulo)
  /*@ public invariant (\forall int i; i>=0 && i< otrosLibrosEstanteria.length; otrosLibrosEstanteria[i]!= null); @*/


  public Libro(boolean tc, Libro[] ole){
    tieneCategoria = tc;
    otrosLibrosEstanteria = ole;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  public boolean estaCategorizado(){
    return tieneCategoria;
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

  /*@ public normal_behavior
    @ requires otrosLibrosEstanteria.length == 1;
    @ ensures \result == 1;
    @ assignable \nothing;
    @
    @ also
    @
    @ public normal_behavior
    @ requires otrosLibrosEstanteria.length == 2;
    @ requires otrosLibrosEstanteria[1].tieneCategoria;
    @ ensures \result == 1;
    @
    @ also
    @
    @ public normal_behavior
    @ requires otrosLibrosEstanteria.length == 2;
    @ ensures \result == 2;
    @
    @ also
    @
    @ public exceptional_behavior
    @ requires otrosLibrosEstanteria.length == 0;
    @ signals_only ExcepcionEstanteriaVacia;
    @ signals (ExcepcionEstanteriaVacia) otrosLibrosEstanteria.length == 0;
    @*/
  public int contarOtrosCategorizadosEstanteria() throws ExcepcionEstanteriaVacia{
    if (otrosLibrosEstanteria.length == 1)
        if (otrosLibrosEstanteria[0].tieneCategoria)
          return 1;
    if (otrosLibrosEstanteria.length == 2)
        if (otrosLibrosEstanteria[0].tieneCategoria & otrosLibrosEstanteria[1].tieneCategoria)
          return 2;
        else  if (otrosLibrosEstanteria[0].tieneCategoria | otrosLibrosEstanteria[1].tieneCategoria)
                return 1;
              else  throw new ExcepcionEstanteriaVacia();
    // < SE PERMITE MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


}
