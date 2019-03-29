abstract sig Deporte{
	delegacion: some Persona
}

sig Individual extends Deporte{
	miembro: Persona
}

sig Grupal extends Deporte{
	equipo: some Persona
}

sig Mixto in Grupal{
	coordinador: Persona
}

sig Persona{
	
}

sig Deportista in Persona{}

sig Entrenador in Persona{}

/*No hay equipos grupales que sean mixtos*/
fact{
	no (Mixto & Grupal)
}

/*Los deportes grupales poseen un equipo de tres
o m´as personas, entre entrenadores y deportistas*/
fact{
	all g:Grupal,p:Persona | (#g.equipo>=3) and (p in g.equipo => (p in Deportista or p in Entrenador))	 
}

/*Todo miembro de un deporte individual debe ser deportista*/
fact{
	all i:Individual | i.miembro in Deportista
}

/*El equipo de un deporte grupal no puede tener mas de un entrenador, y el resto de los
miembros del equipo son sólo deportistas*/
fact{
	all g:Grupal | #(g.equipo & Entrenador)<=1
}

/*Una persona no puede cumplir dos roles diferentes dentro del mismo deporte, pero sí
en deportes diferentes.*/
fact{
	
}

/*La delegaci´on de un deporte est´a conformada por todas las personas vinculadas a ese
deporte, cualquiera sea su rol.*/
fact{
	(all m:Mixto | m.delegacion = m.equipo + m.coordinador) and
	(all g:Grupal | g not in Mixto => g.delegacion = g.equipo) and
	(all i:Individual | i.delegacion=i.miembro)
}



/*Determinar si un deporte posee en su equipo entre 3 y 5 miembros que son s´olo deportistas.*/
pred soloDeportistas[d:Deporte]{
	#(d.delegacion & (Deportista - Entrenador)) >= 3 and #(d.delegacion & (Deportista - Entrenador)) <= 5
}

/*Obtener el conjunto de personas vinculadas a cualquier deporte que
 ´unicamente cumplen rol de entrenador*/
fun soloEntrenadores[]: set Entrenador{
	{e:Entrenador | e in Deporte.delegacion and e not in Deportista}
}


/*
Agregar un deportista al equipo de un deporte, siempre y cuando el equipo tenga
actualmente entre 3 y 5 miembros que son s´olo deportistas, posea entrenador, y no se
trate de un deporte mixto.
*/
pred agregar[d,d1:Deporte,depo:Deportista]{
	(  (soloDeportistas[d]) and #((d & Grupal).equipo & Entrenador)>0) and (d1.equipo = d1.equipo + depo)
}

/*Remover de un equipo al entrenador, siempre y cuando el mismo participe en otros 2
deportes como entrenador o deportista.*/
pred quitarEntrenador[d,d1:Deportista,e:Entrenador]{
	(e in d.delegacion) and #(e & (Deporte-d).delegacion )>=2
}

run{some d,d1:Deporte, depo:Deportista | agregar[d,d1,depo]} for 7
//run{some d:Deporte | soloDeportistas[d] and #(soloEntrenadores[])>3} for 10
