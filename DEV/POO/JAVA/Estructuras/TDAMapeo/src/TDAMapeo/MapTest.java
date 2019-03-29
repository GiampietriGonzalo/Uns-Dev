
/**
 * Class: MapTest
 * @author Mar�a Luj�n Ganuza (mlg@cs.uns.edu.ar)
 *         Estructuras de Datos _ Primer Cuatrimestre 2013
 *         Departamento de Cs. e Ing. de la Computaci�n.
 *  @version: 2.0
 */

package TDAMapeo;

import static org.junit.Assert.*;

import java.util.Random;


import java.util.*;

import org.junit.Before;
import org.junit.Test;


public class MapTest {

	private Map<Integer, Integer> s; // interface

	private Integer n1, n2, n3;

	private Integer i1, i2, i3;

	private Map<Integer, Integer> getMapeo() {

		return new OpenHMap<Integer, Integer>();

	}

	@Before
	public void setUp() {

		s = getMapeo();

		i1 = 66554;

		i2 = 32523;

		i3 = 34230;

		n1 = 6;

		n2 = 8;

		n3 = 3;
				
	}

	/* TESTEANDO EL METODO SIZE() */

	@Test
	public void size() {

		// Testeando si put() actualiza el size correctamente.

		assertTrue("Tama�o de Mapeo justo despu�s de ser creada != 0",
				s.size() == 0);
		try {
			s.put(i1, n1);

			assertTrue("Tama�o de Mapeo luego de insertar una vez != 1",
					s.size() == 1);

			s.put(i2, n2);

			assertTrue("Tama�o de Mapeo luego de insertar dos veces != 2",
					s.size() == 2);

			s.put(i3, n3);

			assertTrue("Tama�o de Mapeo luego de insertar tres veces != 3",
					s.size() == 3);

			// Testeando que el m�todo remove() actualice el size, y que get(K key) no lo haga.

			s.get(i1);

			assertTrue("Tama�o de Mapeo cambi� al invocar get()",
					s.size() == 3);

			s.remove(i1);

			assertTrue(
					"Tama�o de Mapeo luego de eliminar una entrada es != 2",
					s.size() == 2);

			s.get(i2);

			assertTrue("Tama�o de Mapeo cambi� al invocar get()",
					s.size() == 2);

			s.remove(i2);

			assertTrue("Tama�o de Mapeo luego de eliminar es != 1",
					s.size() == 1);

			s.get(i3);

			assertTrue("Tama�o de Mapeo cambi� al invocar get()",
					s.size() == 1);

			s.remove(i3);

			assertTrue("Tama�o de Mapeo luego de eliminar es != 0",
					s.size() == 0);

		} catch (InvalidKeyException e) {
			fail("Los m�todos remove() o get() no deber�an lanzar excepci�n para una clave v�lida.");
		}
	}

	/* TESTEANDO EL METODO isEmpty() */

	@Test
	public void isEmpty() {
		
	  try {

			assertTrue("EL Mapeo no est� vac�o justo despu�s de ser creado",
					s.isEmpty());

			s.put(i1, n1);

			assertTrue("El Mapeo est� vac�a despu�s de insertar 1 elemento",
					!s.isEmpty());

			s.put(i2, n2);

			assertTrue("El Mapeo est� vac�a despu�s de insertar 2 elementos",
					!s.isEmpty());

			s.put(i3, n3);

			assertTrue("El Mapeo est� vac�a despu�s de insertar 3 elementos",
					!s.isEmpty());

		} catch (InvalidKeyException e) {

			fail("El m�todo put() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		
		try {

			s.get(i1);

			assertTrue(
					"El Mapeo est� vac�o despu�s de invocar get() teniendo 3 elementos",
					!s.isEmpty());

			s.remove(i1);

			assertTrue(
					"El Mapeo est� vac�o despu�s de eliminar 1 elemento, quedando 2.",
					!s.isEmpty());

			s.get(i2);

			assertTrue(
					"El Mapeo est� vac�o despu�s de invocar get(i2) teniendo 2 elementos",
					!s.isEmpty());

			s.remove(i2);

			assertTrue(
					"El Mapeo est� vac�o despu�s de eliminar 1 elemento, quedando 1.",
					!s.isEmpty());

			s.get(i3);

			assertTrue(
					"El Mapeo est� vac�o despu�s de invocar get(i3) teniendo 1 elemento",
					!s.isEmpty());

			s.remove(i3);

			assertTrue(
					"EL Mapeo no est� vac�o despu�s de eliminar todos los elementos que ten�a.",
					s.isEmpty());

		} catch (InvalidKeyException e) {

			fail("El m�todo get(K key) o remove(K key) no deber�a lanzar InvalidKeyException con una clave v�lida.");

		}

	}

	/* TESTEANDO EL METODO get(K key) */

	@Test
	public void get() {

		Integer valor, clave, valor_aux, clave_aux;
		Vector<Vector<Integer>> V= new Vector<Vector<Integer>>(2);
		Set<Integer> claves= new HashSet<Integer>();
		Random r= new Random();
		
		//Clave inv�lida
		try {
			 s.get(null);
			 fail("El m�todo get deber�a haber lanzado la excepci�n InvalidKeyException para una clave nula");
		} catch (InvalidKeyException e) {	
		}
		
		// Mapeo vac�o.
		try {

			valor = s.get(i1);
			assertTrue(
					"El Mapeo deber�a devolver nulo cuando la clave no est� en el mapeo.",
					valor == null);

			// Insertando 3 elementos.

			s.put(i1, n1);

			s.put(i2, n2);

			s.put(i3, n3);

		
		} catch (InvalidKeyException e) {

			fail("El m�todo put(K k, V v) no deber�a lanzar InvalidkeyException con una clave v�lida.");

		}

		
		try {

			valor = s.get(i1);

			assertTrue("get(K k) no funciona correctamente", (valor == n1));

			s.remove(i1);

			valor = s.get(i2);

			assertTrue("get(K k) no funciona correctamente", (valor == n2));

			s.remove(i2);

			valor = s.get(i3);

			assertTrue("get(K k) no funciona correctamente", (valor == n3));

			s.remove(i3);

			

		} catch (InvalidKeyException e) {

			fail("El m�todo get(K key) o remove(K key) no deber�a lanzar la excepci�n InvalidKeyException con claves v�lidas.");

		}

		// Insertando 10000 elementos
        V.add(new Vector<Integer>(10000));
        V.add(new Vector<Integer>(10000));
		try {
			for (int i=0; i<10000;i++)
				{clave=r.nextInt(10*(i+1));
				 while(claves.contains(clave))
				  	{
				 	 clave=r.nextInt(10*(i+1));	 
				 	}
				 claves.add(clave);
				 valor=r.nextInt(10000);
				 V.get(0).add(clave);
				 V.get(1).add(valor);
				 s.put(clave, valor);
				}
            
		   } catch (InvalidKeyException e) {

			fail("El m�todo put(K key) no deber�a lanzar InvalidkeyException con una clave v�lida.");

		   }

		//Testeando Get con 10000 elementos	

		try {
			for (int i=0; i<10000;i++)
				{clave_aux= V.get(0).get(i);
				 valor_aux= V.get(1).get(i);
				 valor=s.get(clave_aux);
				 assertTrue("get(K k) no funciona correctamente", valor == valor_aux);
				}
		}catch (InvalidKeyException e) {
				fail("El m�todo get(K k) no deber�a lanzar InvalidKeyException con una clave v�lida.");

		}

	}


	@Test
	public void put_remove() {

		Integer valor, clave, clave_aux, valor_aux;
		Random r= new Random();
		Vector<Vector<Integer>> V= new Vector<Vector<Integer>>(2);
		Set<Integer> claves= new HashSet<Integer>();
		
		//Clave Inv�lida
		try {
			 s.remove(null);
			 fail("El m�todo remove deber�a haber lanzado la excepci�n InvalidKeyException para una clave nula");
		} catch (InvalidKeyException e) {	
		}
		
		try {
             s.put(null,n1);
			 fail("El m�todo put deber�a haber lanzado la excepci�n InvalidKeyException para una clave inv�lida");
		} catch (InvalidKeyException e) {	
		}
	
		
		// Mapeo vacio

		try {
			valor = s.remove(i1);

			assertTrue("El valor deber�a ser nulo", valor == null);
		} catch (InvalidKeyException e) {
			fail("El m�todo remove(K k) no deber�a lanzar InvalidKeyException con una clave v�lida.");
		}
		
		// Insertando put 10000 elementos.
        V.add(new Vector<Integer>(10000));
        V.add(new Vector<Integer>(10000));
		try {
			for (int i=0; i<10000;i++)
				{clave=r.nextInt(10*(i+1));
				 while(claves.contains(clave))
					clave=r.nextInt(10*(i+1));	 
				 claves.add(clave);
				 valor=r.nextInt(10000);
				 V.get(0).add(clave);
				 V.get(1).add(valor);
				 s.put(clave, valor);
				 assertTrue("Put no actualiz� correctamente el size", s.size() == i+1);
				}
            
		} catch (InvalidKeyException e) {
			fail("El m�todo put(K key) no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}

		//Testeando remove con 10000 elementos	

		try {
			for (int i=9999; i>=0;i--)
				{clave_aux= V.get(0).get(i);
				 valor_aux= V.get(1).get(i);
				 valor=s.remove(clave_aux);
				 assertTrue("remove(K k) no funciona correctamente", valor == valor_aux);
				 assertTrue("remove(K k) no actualiza correctamente el size", s.size() == i);
				}
		} catch (InvalidKeyException e) {
				fail("El m�todo remove(K k) no deber�a lanzar InvalidKeyException con una clave v�lida.");
		}
		
		//Mapeo vac�o.

		try {
			valor = s.remove(i1);
			assertTrue("El valor deber�a ser nulo", valor == null);
		} catch (InvalidKeyException e) {
			fail("El m�todo remove(K k) no deber�a lanzar InvalidKeyException con una clave v�lida.");
		}
		
		assertTrue("El mapeo deber�a haber quedado vac�o", s.isEmpty());
		
	}

	/* TESTEANDO LOS METODOS entries(), keys() y values() */
	@Test
	public void Keys_values_entry()
	{ Iterable<Entry<Integer,Integer>> It_entries;
	  Iterable<Integer> It_values;
	  Iterable<Integer> It_keys;
	  Integer clave,valor;
	  Set<Integer> claves= new HashSet<Integer>();
	  Set<Integer> valores= new HashSet<Integer>();
	  Random r=new Random();
	  LinkedHashMap<Integer, Integer> entradas= new LinkedHashMap<Integer,Integer>();

	  //Mapeo vac�o
		It_entries=s.entries();
		It_values=s.values();
		It_keys=s.keys();
			
		assertTrue("El m�todo entries no funciona correctamente para un mapeo vac�o", !It_entries.iterator().hasNext());
	    assertTrue("El m�todo values no funciona correctamente para un mapeo vac�o", !It_values.iterator().hasNext());
        assertTrue("El m�todo keys no funciona correctamente para un mapeo vac�o", !It_keys.iterator().hasNext());
		
        //Insertando 10000 entradas en el mapeo		
    	try {
			for (int i=0; i<10000;i++)
				{ clave=r.nextInt(10*(i+1));
				  while(claves.contains(clave))
				  	 clave=r.nextInt(10*(i+1));	 
			 	  claves.add(clave);
				  valor=r.nextInt(10000);
				  while(valores.contains(valor))
				 	 valor=r.nextInt(10000);	 
				  valores.add(valor);
				  entradas.put(clave, valor);
				  s.put(clave, valor);
				  assertTrue("Put no actualiz� correctamente el size", s.size() == i+1);
				}
    	} catch (InvalidKeyException e) {
    		fail("El m�todo put(K key) no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		
		//Testeando Keys()
		 It_keys=s.keys();
		
		 for(Integer key :It_keys)
			 {	assertTrue("El m�todo keys() no funciona correctamente", claves.contains(key));
		      	claves.remove(key);
		     }
		 assertTrue("keys() no recorre todas las claves del mapeo", claves.isEmpty());	
		
		 //Testeando values()
		 It_values=s.values();
		
		 for(Integer value :It_values)
			 {	assertTrue("El m�todo values() no funciona correctamente", valores.contains(value));
		      	valores.remove(value);
				
		     }
		 assertTrue("values() no recorre todas las claves del mapeo", valores.isEmpty());	
	   
		//Testeando entries()
		 It_entries=s.entries();
		
		 for(Entry<Integer,Integer> en :It_entries)
			 {	valor=entradas.get(en.getKey());
			  	assertTrue("El m�todo entries() no funciona correctamente", valor!=null);
			  	assertTrue("El m�todo entries() no funciona correctamente", valor==en.getValue());
			  	entradas.remove(en.getKey());
			 }
		 assertTrue("entradas() no recorre todas las claves del mapeo", entradas.isEmpty());	
	
	}	
	
	
}
