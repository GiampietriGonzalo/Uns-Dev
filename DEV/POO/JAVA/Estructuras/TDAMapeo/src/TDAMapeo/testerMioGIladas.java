package TDAMapeo;
import TDALista.*;
import java.util.Iterator;


public class testerMioGIladas {
	public static void main(String args[]){
		try{
			int cant=0;
			OpenHMap<String,Double> mapeo= new OpenHMap<String,Double>();
			for(int i=0; i<50; i++)
				mapeo.put(""+i,i+2.1);
			for(int j=0; j<mapeo.cantListas(); j++){	
					System.out.println(mapeo.getLista(j).size());
					cant++;
			}	
			System.out.println("TODAL DE ENTRADAS: "+cant);
		
		}
		catch(InvalidKeyException e){System.out.println(e.getMessage());}
	}
	
}
