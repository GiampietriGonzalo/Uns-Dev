public class AyudaArreglo {

    /*@ public normal_behavior
      @ ensures arreglo[pos] == nuevoElem;
      @
      @ also
      @
      @ public normal_behavior
      @ ensures true;
      @*/
    public static void reemplazarSiMayor(int nuevoElem, int pos, int[] arreglo) {
        if (nuevoElem > arreglo[pos]) {
            arreglo[pos] = nuevoElem;
        }
    }
}
