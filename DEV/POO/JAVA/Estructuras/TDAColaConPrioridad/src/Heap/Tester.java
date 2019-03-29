package Heap;
import java.util.Comparator;

public class Tester {
	public static void main(String args[]){
		Comparator<String> comp= new Comparador<String>();
		System.out.println("Comparacion: "+comp.compare("Hola","Holo"));
	}

}
