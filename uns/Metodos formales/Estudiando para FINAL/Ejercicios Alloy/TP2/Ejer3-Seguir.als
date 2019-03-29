
abstract sig Person {
	children: set Person,
	siblings: set Person
}

sig Man, Woman extends Person {}

sig Married in Person {
	spouse: one Married
}

/*Defina una restricción para el modelo que permita identificar los padres de una
persona*/
fun padres[p: Person]: set Person{children.p}

//Una persona no puede ser su propio hermano/a
fact{
	all p:Person | p not in p.siblings
}

//Los hijos de una persona no pueden ser sus hermanos
fact{
	no (^children &  ^siblings)
}

//El padre no debe ser hijo de sus hijos, ni de su descendencia: TRANSITIVA
fact{
	no (^children & iden)
}

//Ninguna persona puede tener más de una madre, ni más de un padre.
fact{
	all p:Person | lone (children.p & Man) and lone (children.p & Woman) 
}

/*Los hermanos de una persona son aquellas personas que poseen un padre en
común (es decir, considere la existencia de medio-hermanos).*/
fact{
	all p1,p2:Person | (p2 in p1.siblings) implies #(padres[p1] & padres[p2] )>0 
									/*	(((children.p1 & Man) = (children.p2 & Man)) and #(children.p1 & Man)=1) 
									or 
										(((children.p1 & Woman) = (children.p2 & Woman)) and #(children.p1 & Woman)=1)
									)*/
}

//El padre no debe estar casado con alguno de sus hijos, ni su descendencia: TRANSITIVA

//Relacion de simetria de siblings
fact{
	all p1,p2:Person | p1 in p2.siblings <=> p2 in p1.siblings
}

assert HermanosSim{
	no p1,p2:Person | p1!=p2 and (p1 in p2.siblings and  p2 not in p1.siblings)
}


/*Defina en Alloy una restricci´on para determinar si dos personas son parientes de
sangre. (Observaci´on: En general, dos personas son parientes de sangre si poseen un
ancestro en com´un)*/
fun ancestros[p: Person]: set Person {
	(^children).p			
}

pred parientesDeSangre[p1,p2:Person]{
	#(ancestros[p1] & ancestros[p2])>0
}

/*Defina una restricci´on
que permita identificar si una persona no tiene padres, y otra restricci´on
que permita obtener el conjunto de personas en tal condici´on.*/
pred tienePadres[p:Person]{
	#(children.p)>0
}


assert HijosyHermanos{
	no p:Person | #(p.children & p.siblings)>0
}


assert MatrimonioEHijos{
	no p1,p2:Person | p2 in Married and ((p1 in p2.children) and (p1 in p2.spouse))
}


run{#children>2 and #siblings>3} for 10
