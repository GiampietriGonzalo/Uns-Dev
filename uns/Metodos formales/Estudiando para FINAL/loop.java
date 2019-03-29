public class loop{

public int x;
public int y;

/*@ public normal_behavior
  @ requires x>=0 && y>=0;
  @ ensures \result >= 0;
  @*/

public int method(){
	
	int x1=x, q=0;
	/*@ loop_invariant
	  @ (x1>=y && q>=0	&&
	  @ (x1<y && q>=0));
	  @ assignable x1,q;
	  @ decreasing x1-y; //Determina correctitud total
	  @*/

	while(x1>=y){
		x1 = x1 - y;
		q = q + 1;
	}

	return q;
}
}
