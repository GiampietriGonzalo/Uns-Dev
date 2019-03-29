sig Numero{}

abstract sig Persona{}

sig Alumno in Persona{
	lu: Numero,
	aprobadas:  set Materia,
	inscripto: set Materia,																										
}		

sig Docente in Persona{			
	legajo:Numero       
}

sig AyudanteA in Docente{

}

sig AyudanteB in Docente{

}

sig Asistente in Docente{

}

sig Profesor in Docente{

}

sig Materia{
	cod: Numero,
	catedra: set Docente,
	correlativas: set Materia
}

/*SOLO LOS AYUDANTES B SON ALUMNOS Y DOCENTES*/
fact{
	all p:Persona |  (p in (AyudanteA+Profesor+Asistente)) implies (p not in Alumno)
}

fact{
	all b:AyudanteB | b in Alumno
}

fact{
	all p:Persona | (p in AyudanteB) implies  (p not in (AyudanteA+Profesor+Asistente) )
}

fact{
	no d:Docente | d  not in (AyudanteB+AyudanteA+Profesor+Asistente)
}

//INCISO A

/* Un alumno puede estar inscripto como maximo en 2 materias*/	
fact{
	all a:Alumno | #(a.inscripto)<=2
}

//NO SE ENCUENTRAN CONTRAEJMEPLO/S
//run{(#Alumno=1) and (all a:Alumno | #(a.inscripto)>2)} 

/*Los alumnos no deberian estar inscriptos en materias que no tienen docentes asociados.*/
fact{
	all m:Materia | (#m.catedra=0) implies (m not in Alumno.inscripto)
}

//INCISOB

/*Legajo unívoco para cada Docente*/
fact{
	all n:Numero,d:Docente | (n in (d.legajo )) implies (n not in  (Docente - d).legajo)
}

/*El LU es únivoco para cada alumno
fact{
	all n:Numero,a:Alumno | (n in (a.lu)) implies (n not in (Alumno-a).lu)
}
*/

/*Los LU y los Legajos no pueden coincidir
fact{
	no (Alumno.lu & Docente.legajo & Materia.cod)
}/*

/*El código de una materia es únivoco para cada una de ellas*/
fact{
	all n:Numero,m:Materia | (n in m.cod) implies (n not in (Materia-m).cod)
}

/*Una materia no puede ser correlativa de sí misma*/
fact{
	all m:Materia | m not in m.correlativas
}

/*Una materia no puede ser correlativa de otra materia de la cual depende*/
fact{
	all m:Materia | m not in ( (m.correlativas).correlativas )
}

//INCISO C
//1
/*Obtener el listado de docentes que dictan una materia.*/
fun Dictadores[]: set Docente{
	{d:Docente |  one (materiasDocente[d])}
}

//Auxiliar de 1
fun materiasDocente[d:Docente]: set Materia{
	{m:Materia | d in m.catedra}
}

//2
/* Obtener el listado de alumnos que cumplen con los requisitos de correlatividad para
cursar una determinada materia */

fun alumnosCumplen[]: set Alumno{
	{ a:Alumno | (all m:Materia | (m in a.inscripto)  implies (m.correlativas in a.aprobadas) )}
}

//3
/*Determinar si es posible añadir un docente al plantel de una determinada materia*/
pred añadirDocente[d:Docente,m:Materia]{
	(d not in m.catedra)
}

//4
/*Determinar si un alumno puede inscribirse e una determinada materia*/
pred puedeInscribirse[a:Alumno,m:Materia]{
	( m not in (a.inscripto+a.aprobadas)  ) and ( m.correlativas in a.aprobadas)
}

/*Agregar una materia aprobada al historial académico de un alumno*/
pred agregarAprobada[m:Materia,a,a1:Alumno]{
	( (m not in  a.aprobadas) and (m in a.inscripto) ) and ( (a1.aprobadas = a.aprobadas + m) and (a1.inscripto = a.inscripto-m))
}

run{#Alumno=3 and #Materia=7 and #Docente=2 and #AyudanteA=1 and #AyudanteB=1} for 10
