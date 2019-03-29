public class AyudaArreglo {

    /*@ public normal_behavior
	  @ requires arreglo != null;
      @ requires arreglo.length > 0;
	  @ requires pos>=0 & pos<arreglo.length;
	  @ requires nuevoElem > arreglo[pos];
	  @ ensures arreglo[pos] == nuevoElem;
      @ 
      @ also
      @
      @ public normal_behavior
	  @ requires arreglo != null;
      @ requires arreglo.length > 0;
	  @ requires pos>=0 & pos < arreglo.length;
	  @ requires nuevoElem <= arreglo[pos];
      @ ensures arreglo[pos] == \old(arreglo[pos]);
	  @
	  @ also
	  @
      @ public normal_behavior
	  @ requires arreglo == null;
	  @ ensures arreglo == null;
	  @
	  */	
    public static void reemplazarSiMayor(int nuevoElem, int pos, int[] arreglo) {
        if (nuevoElem > arreglo[pos]) {
            arreglo[pos] = nuevoElem;
        }
    }
}
