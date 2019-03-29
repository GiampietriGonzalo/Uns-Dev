//-----------Signatures------------------
abstract sig Persona{}

sig Alumno in Persona{
	LU: one Numero,
	atiende: set Materia,
	aprobadas: set Materia
}

sig Materia{
	codigo: one Numero,
	correlativas: set Materia
}

sig Numero{}

sig Docente in Persona{
	legajo: one Numero,
	dicta: set Materia
}

sig Ayudante_B in Docente{}

sig Ayudante_A in Docente{}

sig Asistente in Docente{}

sig Profesor in Docente{}

//---------Hechos-----------
//Evitar codigos, legajos y LU repetidos
fact{
	(all m1,m2:Materia | m1.codigo=m2.codigo implies m1=m2)
}
fact{
	(all a1,a2:Alumno | a1.LU=a2.LU implies a1=a2)
}
fact{
	(all d1,d2:Docente | d1.legajo=d2.legajo implies d1=d2)
}
//Todo ayudante B es un alumno
fact{
	(all a: Ayudante_B | a in Alumno)
}
//Para toda materia, si es alumno no es docente y viceversa
fact{
	(all m:Materia | 
		(all a:Alumno, d:Docente | (a.atiende=m and d.dicta=m) implies a!=d)
	)
}
//Para todas las materias, todos los docentes que la dictan son de una sola categoria.
fact{
	(all m:Materia|
		(all d:Docente | 
			(m in d.dicta) implies (
			( (d in Ayudante_B) and (d not in Ayudante_A) and (d not in Asistente) and (d not in Profesor) ) or
			( (d not in Ayudante_B) and (d in Ayudante_A) and (d not in Asistente) and (d not in Profesor) ) or
			( (d not in Ayudante_B) and (d not in Ayudante_A) and (d in Asistente) and (d not in Profesor) ) or
			( (d not in Ayudante_B) and (d not in Ayudante_A) and (d not in Asistente) and (d in Profesor) ) 
			)
		)
	)
}
//Si el alumno atiendo no la tiene aprobada, si la tiene aprobada no la atiende
fact{
	(all m:Materia|
		(all a:Alumno | (m in a.aprobadas implies m not in a.atiende) or (m in a.atiende implies m not in a.aprobadas))
	)
}
//
fact{
	(all a:Alumno|
		(all m:Materia | (m in a.atiende) implies (m.correlativas in a.aprobadas) )
	)
}
//--------Run---------------
run{ 
	//Prueba de materias con mismo codigo
	//some m1,m2: Materia | m1.codigo!=m2.codigo and m1=m2
} for 5
