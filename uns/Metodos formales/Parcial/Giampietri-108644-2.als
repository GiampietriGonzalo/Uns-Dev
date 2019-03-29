//EJERCICIO2

sig Helado{
	sabores: some Sabor,
	principal: lone Envase,
	adicionales: set Envase,	
}

abstract sig Sabor{}
sig Crema,Agua in Sabor{}

abstract sig Envase{
	tamanio: set Tamanio
}
sig Fabrica extends Envase{}
abstract sig Sucursal extends Envase{}
sig Cucurucho,Tergopol extends Sucursal{}

abstract sig Tamanio{}
sig Chico,Mediano,Grande extends Tamanio{}




/*INCISO A
No, el modelo presentado no cumple con las restricciones especificadas, porque:

	1)No debería haber objetos que sean sólo de tipo Sabor. El modelo permite que haya objetos de tipo Sabor.	

	2)El tipo de sabor es a la crema O al agua. Es decir, no puedo haber un sabor que sea a la crema y al agua.
	El modelo presentado lo permite estableciendo a Crema y a Agua como "in Sabor", en lugar "de extends sabor".
	
	3)Todo envase posee UN tamaño, el cual es chico, mediano o grande. El modelo permite que un mismo envase
	tenga de 0 a muchos tamaños al establecer la relación "tamanio" con "set".

	4)Cada tamaño debería ser un objeto único dentro de la representación. El modelo permite que haya varios
	objetos de tipo "Chico", "Mediano" y "Grande", cuando con un solo objeto basta con modelar ese tipo de tamaño.
	
	5)Solo los cucuruchos comestibles son utilizados como envases adicionales para los helados. El modelo permite 
	que haya envases de tipo Fabrica,Cucurucho y Tergopol como envases adicionales.

	6)Los helados tienen UN envase principal, es decir, deben tener un envase principal. El modelo permite que los
	helados contegan A LO SUMO un envase principal; es decir, 0 o 1 envase principal.
	
	7) El modelo permite que haya objetos de tipo Sabores y Tamanio sueltos, sin estar relacionados.
*/

/*Restricciones agregadas*/

//1. No debería haber objetos de tipo Sabor
fact{
	all s:Sabor | s in (Crema+Agua)
}


//2. El tipo de sabor es a la crema O al agua-
fact{
	all s:Sabor | ( (s in Crema) implies (s not in Agua) )  and  ( (s in Agua) implies (s not in Crema) )	
}

//3. Todo envase posee sólo un tamaño.
 fact{
	all e:Envase | #(e.tamanio)=1
}

//4. Cada tamaño debería ser un objeto único dentro de la representación.
fact{
	lone Chico and
	lone Mediano and
	lone Grande
}

//5. Sólo los cucuruchos comestibles son utilizados como envases adicionales para los helados.
fact{
	all e:Envase | (e in Helado.adicionales) implies (e in Cucurucho)
}
//6. Los helados tienen UN envase principal
fact{
	all h:Helado | #(h.principal)=1
} 

//7. El modelo permite que haya objetos de tipo Sabores y Tamanio sueltos, sin estar relacionados.
fact{
	all e:Envase | e in (Helado.principal + Helado.adicionales)
	all s:Sabor | s in Helado.sabores and
	all t:Tamanio | t in Envase.tamanio
}

/*	INCISO B		*/
//El helado envasado en fábrica no tiene sabores al agua.
fact{
	all f:Fabrica, h:Helado | (f in h.principal) implies (no (h.sabores & Agua) )
}

//Comprobacion: No encuentra instancias.
//run{one f:Fabrica,h:Helado | (f in h.principal) and (#(h.sabores & Agua)>0)}

//--------------------------------------------------------------------------------------------------------------------------------------------------------

//Los cucuruchos sólo se ofrecen en tamañio mediano o grande
fact{
 all c:Cucurucho | c.tamanio in (Grande+Mediano)
}

//Comprobacion: No encuentra instancias.
//run{some c:Cucurucho | c.tamanio in Chico }

//--------------------------------------------------------------------------------------------------------------------------------------------------------

//El helado servido en cucurucho puede tener hasta 2 sabores (mediano) o hasta 3 sabores (grande).
fact{
	all c:Cucurucho, h:Helado | ( (c.tamanio in Grande) and (c in h.principal))  implies (#(h.sabores)=3) 	
}	

fact{
	all c:Cucurucho, h:Helado | ( (c.tamanio in Mediano) and (c in h.principal))  implies (#(h.sabores)=2)
}
//Comprobaciones: No encuentra instancias.
//run{some h:Helado,c:Cucurucho,g:Grande | ((c in h.principal) and (g in c.tamanio)) and (#h.sabores!=3) }
//run{some h:Helado,c:Cucurucho,m:Mediano | ((c in h.principal) and (m in c.tamanio)) and (#h.sabores!=2) }

//--------------------------------------------------------------------------------------------------------------------------------------------------------

//El helado servido en cucurucho no puede contener sabores de distintos tipo.
fact{
	all c:Cucurucho,h:Helado | (#(Agua & h.sabores)=0) iff (  (c in h.principal)  and ((#(Crema & h.sabores)>0)) )
}

fact{
	all c:Cucurucho,h:Helado | (#(Crema & h.sabores)=0) iff (  (c in h.principal)  and ((#(Agua & h.sabores)>0)) )
}

//Comprobacion: No se encuentran instancias.
//run{ some c:Cucurucho,h:Helado | c in h.principal and #h.sabores>1 and #(h.sabores & Crema )>0 and #(h.sabores & Agua)>0}

//--------------------------------------------------------------------------------------------------------------------------------------------------------

//El helado servido en tarro de telgopor puede tener hasta 2 sabores (chico), hasta 3 sabores (mediano) o hasta 4 sabores (Grande)
fact{
	all t:Tergopol,h:Helado | ((t in h.principal) and (t.tamanio in Grande)) implies #(h.sabores)=4
}

fact{
	all t:Tergopol,h:Helado | ((t in h.principal) and (t.tamanio in Chico)) implies #(h.sabores)=2
}

fact{
	all t:Tergopol,h:Helado | ((t in h.principal) and (t.tamanio in Mediano)) implies #(h.sabores)=3
}


//Comprobacion: Las instrancias cumplen con los facts
//run{#Tergopol=3 } for 5

//--------------------------------------------------------------------------------------------------------------------------------------------------------

//La cantidad de envases adicionales de un helado no puede superar la cantidad de sabores.
fact{
	all h:Helado |  (#h.adicionales) <= #(h.sabores)
}

//Comprobacion: No se encuentra instancias.
//run{some h:Helado | (#h.adicionales) < #(h.sabores)}

/*	INCISO C		*/

/*Añadir un envase adicional. Esta acción es posible si el helado está servido en tarro de telgopor y no está al limite
de la cantidad de adicionales permitidos. 
*/
pred agregarEnvase[e:Envase,h,h1:Helado]{

	//PRECONDICIONES
	((h.principal in Tergopol) and (#(h.adicionales) < #(h.sabores)) ) and
	
	//POST-CONDICIÓN
	(h1.adicionales=h.adicionales + e)

}
//Comprobacion
//run{ some e:Envase, h,h1:Helado | agregarEnvase[e,h,h1]}

//--------------------------------------------------------------------------------------------------------------------------------------------------------

/*Trasvasar un helado envasado en sucursal. Esta accion es posible si el tamaño del envase principal del helado no
supera el tamaño del envase nuevo. Además, si se quiere cambiar un envase de tergopol por uno de cucurucho, el
helado no debe poseer adicionales ni sabores de diferente tipo. Por otra parte, si se quiere cambiar un cucurucho
por un envase de tergopol, el cucurucho pasa a ser un envase adicional.
*/

pred trasvasar[e:Envase,h,h1:Helado]{

	/*CASO 1: cambiar un envase de tergopol por un envase de cucurucho.*/(
	
		/*PRECONDICIONES DEL CASO 1*/(
			(e in Cucurucho) and
			(noSuperaTamanio[h.principal,e]) and
			(h.principal in Tergopol) and
			(saboresMismoTipo[h]) and
			( #(h.adicionales)=0) 
			

		)
	
		implies
	
		/*POSTCONDICION DEL CASO 1*/(		
			h1.principal = e
		)
	)

	or

	/*CASO 2: cambiar un envase de cucurucho por un envase de tergopol.*/(
		
		/*PRECONDICIONES DEL CASO 2*/(
			(e in Tergopol) and
			(noSuperaTamanio[h.principal,e]) and
			(h.principal in Cucurucho)
		)

		implies

		/*POSTCONDICIONES DEL CASO 2*/(
			h1.principal = e and
			h1.adicionales = h.adicionales + h.principal
		)

	)

	
}

/*
Predicado auxiliar para determina si el tamaño de un envase supera al tamaño de otro envase
*/
pred noSuperaTamanio[e,e1:Envase]{
	(e.tamanio in Chico) or
	((e.tamanio in Mediano) and (e1.tamanio not in Chico)) or
	((e.tamanio in Grande) and (e1.tamanio in Grande))
}

/*
Predicado auxiliar que determian si un helado tiene sabores del mismo tipo o no
*/
pred saboresMismoTipo[h:Helado]{
	(#(h.sabores & Crema)>0) and (#(h.sabores & Agua)=0)
	or
	(#(h.sabores & Agua)>0) and (#(h.sabores & Crema)=0)
}

//COMPROBACION
//run{some h,h1:Helado,c:Cucurucho | trasvasar[c,h,h1]}


//--------------------------------------------------------------------------------------------------------------------------------------------------------
//Obtener el conjunto de helado que se pueden trasvasar a un cucurucho mediano
fun getTrasvasarCucuruchoMediano[]:set Helado{
	{h:Helado | (h.principal in Tergopol) and ( some c:Cucurucho,h1:Helado | (c.tamanio in Mediano) and (trasvasar[c,h,h1]) ) }
}



/*	INCISO D		*/
/*Sí*/
run{ #(getTrasvasarCucuruchoMediano[])>0 }

/*	INCISO E		*/
/*El healdo debe poseer 2 sabores */
