	sig Persona{}

sig Entrenador in Persona{}

sig Deportista in Persona{}

abstract sig Deporte{
	delegacion: set Persona
}

sig Individual extends Deporte{
	miembro: Persona
}

sig Grupal extends Deporte{
	equipo: set Persona
}

sig Mixto in Deporte{
	coordinador: Persona
}

fact{
	all d:Deporte | #(d.delegacion)>0
}

fact{
	all d:Deporte | #(d.delegacion)>=1
}

fact{
	all g:Grupal | #(g.equipo)>=3
}

fact{
	all m:Mixto | (m not in Individual) 
}

fact{
	all d:Deporte | (d not in Mixto) implies (#d.coordinador=0)
}

//INCISO A
//1
fact{
	all i:Individual | i.miembro in Deportista
}

//2

fact{
 	all g:Grupal | lone(g.equipo & Entrenador)
}

fact{
	all g:Grupal | (g.equipo - (g.equipo & Entrenador)) in (Deportista - Entrenador) 
}

//4
fact{
	all d:Deporte | d.delegacion= d.equipo + d.miembro + d.coordinador
}



fact { all g: Grupal | no (g.coordinador & g.equipo) }

//INCISO B
//1
pred entre3y5SoloDeportistas[g: Grupal]{
	
	# ((Deportista & g.equipo) - Entrenador)>=3 and #((Deportista & g.equipo) - Entrenador)<=5
}

//2
/*Obtener el conjunto de personas vinculadas a cualquier deporte que 
unicamente cumplen rol de entrenador*/
fun cualquierDeporteUnicamenteEntrenador[]:set Entrenador{
	{e: Entrenador |  (some g:Grupal | e in g.equipo) and
				    (all m:Mixto | e not in m.coordinador) and
				    (all i:Individual | e not in i.miembro)
	}			
}

//4
/*Agregar un deportista al equipo de un deporte, siempre y cuando el equipo tenga
actualmente entre 3 y 5 miembros que son solo deportistas, posea entrenador, y no se
trate de un deporte mixto*/

pred agregarDeportista[g,g1:Grupal,d:Deportista]{
		( (d not in g.equipo) and (g not in Mixto) and (entre3y5SoloDeportistas[g]) and (#(g.equipo & Entrenador)=1) ) and ((g1.equipo=g.equipo + d) and (g1.delegacion=g.delegacion + d))
}

//5
/*Remover de un equipo al entrenador, siempre y cuando el mismo participe en otros 2
deportes como entrenador o deportista.*/
pred quitarEntrenador(e:Entrenador,g,g1:Grupal){
	
	( 	(e in g.equipo) and 
		(some d1,d2: Deporte | (d1!=d2) and 
							( 
								(e in (d1.equipo + d1.miembro)) and 
								( e in (d2.equipo + d2.miembro))  
							)
		)	
		
	) //FIN PRECONDICION
	and 
	(	(g1.equipo= g.equipo - e) and
		(g1.delegacion=g.delegacion -e ) and
		(g1.coordinador=g.coordinador)
	)//FIN POST CONDICION
}


run remEntrenad { some e: Entrenador, g, g1: Grupal, i: Individual | quitarEntrenador[e,g,g1] and e in i.miembro} for 4

//run{#Mixto=1 and #Grupal=3 and #Individual=1 and #Entrenador=2 and #Deportista=7 } for 7
run{ (all g:Grupal | (g not in Mixto) implies #g.equipo=6) and #Grupal=2  and #Individual=0 and #Mixto=0 and (all g:Grupal| no (g.equipo & Entrenador & Deportista) )} for 10
