public class A {

    private /*@ spec_public @*/ int value;

    /*@ public normal_behavior
      @ requires a!=null;
	  @ requires a.length >= 2;
      @ requires a[0]!=null && a[1]!=null;
      @ ensures a[0].value >= a[1].value;
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires a!=null;
	  @ requires a.length<2;
      @ ensures a == \old(a);
	  @
	  @ also
	  @
	  @ public normal_behavior
	  @ requires a==null;
	  @ ensures a==null;
	  @
      @*/
    private void m(/*@nullable @*/A[] a) {

        if (a != null) {
          
	        if (a.length>1 && a[0].value < a[1].value) {
	            int tmp = a[0].value;
	            a[0].value = a[1].value;
	            a[1].value = tmp;
	        }
		}
        /*
           Si el arreglo a posee al menos dos elementos, entonces
           intercambia su primer y segundo elemento (de ser necesario)
           para que el primero sea mayor o igual que el segundo.
        */
        // SE PERMITE MODIFICAR EL CODIGO DE ESTE METODO
    }

    /*@ public normal_behavior
      @ requires a1!=null && a2!=null;
	  @ requires a1>=a2;
      @ ensures this.value == a1;
      @ ensures \result >= 0;
      @
      @ also
      @
      @ public normal_behavior
      @ requires a1!=null && a2!=null;
	  @ requires a1<a2;
      @ ensures \result < 0;
      @ ensures this.value == a1;
      @
      @ also
      @
      @ public normal_behavior
      @ requires a1==null || a2==null;
      @ ensures \result == null;
      @ ensures this.value == null;
      @
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
