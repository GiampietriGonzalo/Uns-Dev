public class loop1{

	public int[] a;

	/*@ public normal_behavior
	  @ requires a!=null && a.length > 0;
	  @ ensures (\forall int x;0<=x && x<a.length; a[x]==1);
	  @ diverges true;
	@*/
	
	public void m(){

		int i=0;

		/*@ loop_invariant
		  @ (0<=i && i<a.length &&
		  @ (\forall int x; 0<=x && x<i; a[x]==1));
		  @ assignable i,a[*];
		  @*/

		while(i<a.length) {
			a[i]=1;
			i++;
		}		
	}
}
