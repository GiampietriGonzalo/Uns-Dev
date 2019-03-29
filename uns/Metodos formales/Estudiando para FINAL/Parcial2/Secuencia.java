public class Secuencia {

  private /*@ spec_public @*/ int[] elementos;

  // < ESTA PROHIBIDO ELIMINAR O MODIFICAR ESTE INVARIANTE >
  /*@ public invariant elementos != null; @*/


  public Secuencia(int[] elems){
    elementos = elems;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  public boolean estaOrdenada(){
    // Determina si la secuencia (atributo elementos) está ordenada de menor a mayor
    //...
    // < ESTA PROHIBIDO IMPLEMENTAR ESTE METODO >
  }


  /*@ public normal_behavior
    @ requires elementos.length == 1;
    @ requires pos < elementos.length;
    @ ensures \result == false;
    @ assignable \nothing;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos == 0;
    @ ensures elementos[pos] == e;
    @ ensures \result == false;
    @ assignable \nothing;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos == 0;
    @ ensures elementos[pos] == e;
    @ ensures \result == true;
    @ assignable \nothing;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos == elementos.length - 1;
    @ requires e > elementos[pos-1];
    @ ensures elementos[pos] == e;
    @ ensures \result == false;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos == elementos.length - 1;
    @ requires e < elementos[pos-1];
    @ ensures elementos[pos] == e;
    @ ensures \result == true;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos != 0;
    @ requires pos != elementos.length - 1;
    @ ensures elementos[pos] == e;
    @ ensures \result == false;
    @
    @ also
    @
    @ public normal_behavior
    @ requires elementos.length > 1;
    @ requires pos != 0;
    @ requires pos != elementos.length - 1;
    @ ensures elementos[pos] == e;
    @ ensures \result == true;
    @
    @ also
    @
    @ public exceptional_behavior
    @ requires pos < 1;
    @ signals_only ExcepcionFueraLimites;
    @*/
  public boolean reemplazarElemento(int e, int pos) throws ExcepcionFueraLimites {
    if (pos < 0 | pos >= elementos.length)
      throw new ExcepcionFueraLimites();
    elementos[pos]=e;
    if (elementos.length > 1){
      if (pos == 0)
        if (e > elementos[pos+1])
          return true;
        else return false;
      if (pos == elementos.length - 1)
        if (e > elementos[pos-1])
          return true;
        else return false;
      else  if (e > elementos[pos+1] & e < elementos[pos-1])
              return true;
            else return false;
    }
    // < SE PERMITE MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


}
