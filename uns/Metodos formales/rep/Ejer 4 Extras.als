sig Persona {}

sig Numero{}

sig Alumno in Persona{
	lu: one Numero,
	hAcademica: set Materia,
	inscripto: set Materia
}

//puede haber docentes que no estan en estas categorias?
sig Docente in Persona{
	legajo: one Numero
}

sig  AyudanteA,AyudanteB, Asistente, Profesor in Docente {}


sig Materia {
	codigo: one Numero,
	titulares: set Docente,
	correlativas: set Materia,
}

// no existen personas fuera que no sean alumnos o docentes 
fact {all p:Persona | (p in Alumno) or (p in Docente)}


// no existen numeros que no esten asociados con el Lu , legajo o codigo.
fact {all n:Numero, a:Alumno, d:Docente, m:Materia | (n in d.legajo) or (n in a.lu) or (n in m.codigo)}

fact {all a:Alumno | #a.inscripto <= 2 }

/*
/* no funca bien! revisar logica papa! 
fact {all a:alumno | a.inscripto.titulares != none}  
*/

fact {all a:Alumno, m:Materia | a -> m in inscripto implies m.titulares != none}

//las materias no pueden ser correlativas con si mismo
fact {all m:Materia | no (m & m.correlativas)}

fact {not (correlativas  =~correlativas)}

//los alumonos no pueden estar incriptos a materias que tengan en su historia academica.
fact {all a:Alumno |no (a.hAcademica & a.inscripto)}

fact {all a:Alumno | no (a.inscripto & (a.hAcademica.^correlativas))}

fact { all a:Alumno, m:Materia | ((m in a.inscripto) implies (m.^correlativas in a.hAcademica ))} 

//todos los ayudantes A son alumnos (puede ser redundante)
fact {all ayu:AyudanteB | ayu in Alumno}

/*este hecho no fuenciona como deberia en todos los casos revisar!!!!!
fact {all a:alumno, d:docente, m:materia | (a.lu != m.codigo) and (a.lu != d.legajo) and (m.codigo != d.legajo)}
*/

//evitan que nadie comparta un numero  de lu con un legajo o un numero de codigo
/* 
fact {all a:alumno, m:materia, n:numero |  a -> n in lu and m -> n in codigo implies a.lu != m.codigo }

fact {all m:materia, d:docente, n:numero |  m -> n in codigo and  d -> n in legajo implies m.codigo != d.legajo }

fact {all d:docente, a:alumno, n:numero |  a -> n in lu and d -> n in legajo implies a.lu != d.legajo }
*/

//2 materias no pueden compartir el mismo codigo.
//fact {all m1,m2:Materia, n:Numero | m1 -> n in codigo and m2 -> n in codigo implies m1 = m2} 

//2 docentes no pueden compartir el legajo.
fact {all d1,d2:Docente, n:Numero | d1 -> n in legajo and d2 -> n in legajo implies d1 = d2} 

//2 alumnos no pueden compartir el LU.
fact {all a1,a2:Alumno, n:Numero | a1 -> n in lu and a2 -> n in lu implies a1 = a2} 

 

//no pueden existir alumnos que sean ayudantes B o profesores o asistentes
fact {no (Alumno & Profesor) and no (Alumno & Asistente) and no (Alumno & AyudanteA) 
	 and no (AyudanteB & Profesor) and no (AyudanteB & AyudanteA) and no (AyudanteB & Asistente) }

//todos los docentes son ayudantes o profesores o asistentes
fact {all d:Docente | (d in AyudanteA) or (d in AyudanteB) or (d in Asistente) or (d in Profesor)}

//fact {(lu != codigo) and (lu != legajo) and (legajo != codigo)} 

run{#inscripto = 1 #Alumno= 1 #hAcademica= 2 #Materia= 3 #correlativas=2 and 
	some m:Materia | (#m.^correlativas > 1 and some inscripto.m)
} for 10

