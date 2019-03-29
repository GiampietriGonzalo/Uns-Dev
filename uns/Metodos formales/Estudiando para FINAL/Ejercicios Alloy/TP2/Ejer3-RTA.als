//Ejercicio 3 - Trabajo Práctico N2 - Métodos Formales

abstract sig Person {
				children: set Person,
				siblings: set Person
}
sig Man, Woman extends Person{}
sig Married in Person {
				spouse: one Married 
}

//Inciso c)
//Defina una restricción para el mdelo que permita identificar los padres de una persona
fun padres[p: Person]: set Person{children.p}

//Inciso d)
//Ninguna persona puede ser su propio ancestro
fact { no(^children & iden) }

//Ninguna persona puede tener más de una madre, ni más de un padre
fact { all p: Person | lone (children.p & Man) and lone (children.p &Woman) }

//Los hermanos de una persona son aquellas personas que poseen un padre en común
//es decir, considere la existencia de medio hermanos
fact { all p1,p2: Person | p1!=p2 => (p1->p2 in siblings <=>  (padres[p1]&padres[p2] )!=none)}

//Inciso e)
//Defina una restricción para chequear que la relación sibling es simétrica
assert siblingsSimetrica {some p1,p2: Person | p1!=p2 and (p1->p2 in siblings) and (p2->p2 not in siblings)}
check siblingsSimetrica

//Inciso f)
//Defina una restricción para chequear que una persona sea hermana de si misma
assert siblingsReflexiva { all p1: Person | p1->p1 not in siblings}
check siblingsReflexiva

//Modificación al modelo para evitar que una persona pueda ser hermano de si mismo
fact { no(siblings & iden)}

//Inciso g)
//Defina una restricción para determinar si dos personas son parientes de sangre 
//(Dos personas son parientes de sangre si poseen un ancestro en común)
fun ancestros[p: Person]: set Person { (^children).p + p }
pred parientesSangre[p1,p2:Person]{ (ancestros[p1] & ancestros[p2]) !=none}

//Inciso h)
//Función auxiliar hijos: Retorna el conjunto de hijos de una Persona
fun hijos[p:Person]: set Person { p.children }

//Defina una restricción para chequear si dos parientes de sangre pueden tener hijos en común
assert incesto{all p1,p2:Person | p1!=p2 =>( (hijos[p1]&hijos[p2])!=none <=> not parientesSangre[p1,p2])}
check incesto

//De acuerdo a esto pueden existir parientes de sangre con hijos en común
fact { all p1,p2: Person | p1!=p2 => ((hijos[p1]&hijos[p2]!=none) <=> not parientesSangre[p1,p2])}

run {} for 4
