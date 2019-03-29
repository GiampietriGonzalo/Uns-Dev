--  Modelo

sig Persona { }

sig Deportista, Entrenador in Persona { }

abstract sig Deporte { delegacion: some Persona}

sig Individual extends Deporte { miembro: Persona } 
/*
	Definición alternativa (se aleja del diagrama):

	sig Individual extends Deporte { miembro: Deportista }
*/

sig Grupal extends Deporte { equipo: some Persona }
/*
	Definición alternativa (se aleja del diagrama):

	sig Grupal extends Deporte { equipo: some (Deportista+Entrenador) }
*/

sig Mixto in Deporte { coordinador: Persona }

//---------------------------------------

-- Los deportes individuales no pueden ser mixtos 
fact { no (Individual & Mixto) } 


-- El miembro de un deporte individual es deportista
fact { all i: Individual | i.miembro in Deportista }

-- El equipo de un deporte grupal tiene al menos tres miembros
fact { all g: Grupal | #(g.equipo) >= 3 }

-- El equipo de un deporte grupal tiene a lo sumo un entrenador y el resto son solo deportistas
fact { all g: Grupal | lone (g.equipo & Entrenador) }

fact{ all g: Grupal | (g.equipo - (g.equipo & Entrenador)) in (Deportista-Entrenador) }
/*
	 Alternativas más simples para este hecho:

	fact{ all g: Grupal | (g.equipo - Entrenador) in Deportista }

	fact {all g: Grupal | g.equipo in (Entrenador+Deportista) }
	-- El hecho anterior garantiza que hay como maximo un entrenador
	-- Por lo tanto, en el equipo habra a lo sumo un atomo de "Entrenador"
	-- El resto de los miembros del equipo seran solo deportistas
*/

assert loneEntrenadorRestoDep {
	all g: Grupal |  #g.equipo = #((g.equipo & Entrenador) + (g.equipo & (Deportista-Entrenador))) 
}

check loneEntrenadorRestoDep for 10

-- Una misma persona no puede cumplir dos roles diferentes dentro del mismo deporte
// OBS! El hecho anterior garantiza que una persona no puede cumplir dos roles diferentes dentro del equipo
// Por lo tanto, solamente queda chequear que coordinador no se intersecte con equipo
fact { all g: Grupal | no (g.coordinador & g.equipo) }

-- La delegacion de un deporte esta conformada por todas las personas vinculadas a el, cualquiera sea su rol
fact {all d: Deporte | d.delegacion = (d.equipo + d.miembro + d.coordinador) }

//------------------------------------------

// Predicados y Funciones

-- Determinar si un deporte posee en su equipo entre 3 y 5 miembros que son sólo deportistas
pred entre3y5SoloDeportistas [g: Grupal] {
	(#(g.equipo & Deportista - Entrenador) >=3) and 
	(#(g.equipo & Deportista - Entrenador) =<5)
	// OBS! En este caso la expresión que opera los conjuntos no está parentizada porque su orden de evaluación no afecta al resultado.
	// En este caso, se realizará primero la intersección y luego la diferencia (por la precedencia de los operadores).
	// Alternativamente, podría haberse definido como: (g.equipo & (Deportista-Entrenador))
}

-- Seleccionar las personas vinculadas a deportes que únicamente cumplen rol de entrenador

fun soloEntrenadores [] : set Entrenador {
 	{ e: Entrenador | 	(some g: Grupal | e in g.equipo) and 
					(no m: Mixto | e in m.coordinador) and 
	                        	(no i: Individual | e in i.miembro) 
    }
	//  Alternativa:
	// { e: Entrenador | (some g: Grupal | e in g.equipo) and (no d: Deporte | e in (d.coordinador + d.miembro)) }

}

-- Agregar un deportista al equipo de un deporte siempre y cuando el equipo tenga actualmente 
-- entre 3 y 5 miembros que son sólo deportistas, posea entrenador, y no se trate de un deporte mixto

pred agregarDeportista[d: Deportista, g, g1: Grupal] {
	--------- Precond ------------------
	entre3y5SoloDeportistas[g] and 
	some (Entrenador & g.equipo) and 
	(g !in Mixto) and
	(d !in g.equipo) and		 // Implícita pero necesaria!! Si ya está no lo agrega
	(d !in Entrenador)	and   // Esta sería redundante (hay un fact que garantiza que sólo puede haber 1 entrenador en el equipo), 
						// así que si se quisiera agregar otro entrenador no se verificaría el predicado
       --------- Poscond ------------------
	(g1 !in Mixto) and  
	(g1.delegacion = g.delegacion + d) and
	(g1.equipo = g.equipo + d)
}

-- Remover al entrenador de un equipo, siempre y cuando el mismo participe en otros dos equipos
-- como entrenador o deportista
-- OBS! Una alternativa sería pensar que el entrenador no es un argumento del predicado, sino que se "obtiene" a través del deporte. En tal caso debería chequearse que el deporte "viejo" (en este caso, g) tenga efectivamente entrenador.

pred removerEntrenador[e: Entrenador, g, g1: Grupal] {
	--------- Precond ------------------
	(e in g.equipo) 	and 		//Implícita pero necesaria!!
	(some disj d1, d2: Deporte |   (d1!= g) and 
							(d2!= g) and 
							(e in d1.(miembro+equipo)) and
							(e in d2.(miembro+equipo)) 
        ) and
	--------- Poscond ------------------
	(g1.delegacion = g.delegacion - e) and 
	(g1.equipo = g.equipo - e) and
	(g1.coordinador = g.coordinador)
}


//------------------------------------------

run hayMixto {some Mixto} //Al no especificar scope, se toma el default = 3
//No instance found! Para los mixtos es necesario que haya al menos 4 personas (3 en el equipo y el coordinador)

run entre3y5SoloDep {some g: Grupal | entre3y5SoloDeportistas[g] and some (Entrenador & Deportista & g.equipo) } for 4

run entre3y5SoloDep2 {some g: Grupal | some g.coordinador and entre3y5SoloDeportistas[g] and some (Entrenador & Deportista & g.equipo) } for 4
//No instance found! Como pide que haya coordinador (1 persona) + entre 3 y 5 sólo deportistas (al menos otras 3 personas) + 1 Entrenador (que también sea deportista --- Recordar que este solapamiento era posible para el rol de entrenador), necesito al menos 5 personas!

run entre3y5SoloDep2 {some g: Grupal | some g.coordinador and entre3y5SoloDeportistas[g] and some (Entrenador & Deportista & g.equipo) } for 5

run soloEntrenad { some e, e1: Entrenador, d: Deporte | e in soloEntrenadores[] and e1 !in soloEntrenadores[] and e1 in d.(miembro+equipo+coordinador) } for 5

run agregarDep {some d: Deportista, g,g1: Grupal | (#g.equipo = 7) and  agregarDeportista[d,g,g1] } for 10
// No instance found! Si el equipo tiene actualmente 7 miembros, quiere decir que tiene: 7 sólo deportistas, o 6 sólo deportistas + 1 entrenador. 
// En ambos casos no cumpliría la precondición para agregar que exige que tenga entre 3 y 5 sólo deportistas.
// Además, en el primer caso, no cumpliría la precondición para agregar que exige que el equipo del deporte tenga entrenador.

run agregarDep2 {some d: Deportista, g,g1: Grupal | (#g.equipo >=4) and (#g.equipo =<6) and agregarDeportista[d,g,g1] } for 10
// Si el equipo tiene entre 4 y 6 miembros, es posible generar esa instancia. 
// Si tiene 4: la instancia generada deberá tener 3 sólo deportistas + 1 entrenador (no podrá tener 4 sólo deportistas, porque en tal caso no verificaría la condición de agregar, la cual exige que tenga entrenador)
// Si tiene 5: la instancia generada puede tener 4 sólo deportistas + 1 entrenador (no podrá tener 5 sólo deportistas, porque en tal caso no verificaría la condición de agregar, la cual exige que tenga entrenador)
// Si tiene 6: la instancia generada tendrá 5 sólo deportistas + 1 entrenador (no podrá tener 6 sólo deportistas, porque en tal caso no verificaría la condición de agregar, la cual exige que tenga entrenador, ni tampoco la condición que exige que tenga entre 3 y 5 sólo deportistas.)

run remEntrenad { some e: Entrenador, g, g1: Grupal, i: Individual | removerEntrenador[e,g,g1] and e in i.miembro} for 4
