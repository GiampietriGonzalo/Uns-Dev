public class A {

    private /*@ spec_public @*/ int value;

    /*@ public normal_behavior
      @ requires true;
      @ ensures (a != null && a.length >= 2) ==> a[0].value >= a[1].value;
      @*/
    private void m(/*@nullable @*/A[] a) {
        if (a == null) {
            return;
        }
        if (a[0].value < a[1].value) {
            int tmp = a[0].value;
            a[0].value = a[1].value;
            a[1].value = tmp;
        }
        /*
           Si el arreglo a posee al menos dos elementos, entonces
           intercambia su primer y segundo elemento (de ser necesario)
           para que el primero sea mayor o igual que el segundo.
        */
        // SE PERMITE MODIFICAR EL CODIGO DE ESTE METODO
    }

    /*@ public normal_behavior
      @ requires true;
      @ ensures \result >= 0;
      @ assignable \nothing;
      @
      @ also
      @
      @ public normal_behavior
      @ requires true;
      @ ensures \result < 0;
      @ assignable \nothing;
      @*/
    public int n(int a1, int a2) {
        this.value = a1;
        return a1 - a2;
        /*
          Asigna a1 al atributo value y retorna el resultado de restar a1 y a2.
        */
        // ESTA PROHIBIDO MODIFICAR EL CODIGO DE ESTE METODO
    }
}
