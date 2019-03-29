sig Objeto {}

sig Llave extends Objeto {}

sig Valor extends Objeto {}

sig Mapeo {
	valores: Llave -> lone Valor
}

/*assert MapeoUnico {
	all m:Mapeo, l:llave, v, v':valor |
		l -> v in m.valores and l -> v' in m.valores implies v = v'
}*/

assert esFuncion {all m:Mapeo | all l:Llave | l in m.valores.Valor implies #l.(m.valores) = 1}
//check esFuncion for 5


assert noExisteObj {
	all o:Objeto | o in (Llave + Valor)
}
check noExisteObj for 5

/*
b)
	No ,no puede existir un objeto que no sea 'llave' o 'valor' .Ya que no tendria sentido generar 
	objetos que no sean parte de algunos de estos conjutos. por la tanto anbos conjuntos son disjuntos

	assert noExisteObj {
		all o:Objeto | o in (Llave + Valor)
	}
	check noExisteObj for 5
*/

/*
d)
	Puede existir una llave o un valor que no pertenescan a un determinado mapeo, ya que la relacion 
	ternaria del diagrama no impide que no pueda existir una llave y un valor en un mapeo ya que la 
	relacion es ... "llave (set implicito osea 0*..) -> lone (0..1) valor" de esta forma es posible 
	generar esta instancia.Sin embargo todas las llaves tienen que estar asociadas a un mapeo. 


	run {#Mapeo = 2 #Llave = 3 #valores = 2 #Valor=2} no puede generar intancias por que no puede
	haber llaves sin estar relacionadas a un mapeo. 
*/

/*
e)
	Es posible que una misma llave este asociada a distintos valores sin embargo esto ocurre en distintos
	mapeos.

	run {#Mapeo = 2 #Llave = 1 #valores = 2 #Valor=2}  	
*/

//fact { llave != valor }

//fact {#Objeto = 3}

//fact {all o:Objeto | o in (llave + valor) }
/*
fact {
	(all o:Objeto | (o in Llave and o not in Valor ) or (o not in Llave and o in Valor))
}*/

fact {
	(all o:Objeto | o in Mapeo.valores[Llave] or o in Mapeo.(valores.Valor) )
}

/*
pred poner (m, m':Mapeo, l:llave, v:valor) { 
	m'.valores = m.valores + l -> v 
}*/
/*
fact {
	all l:llave | some v:valor, m:Mapeo | l -> v in m.valores
	all v:valor | some l:llave, m:Mapeo | l -> v in m.valores
}*/

//#Mapeo = 1 #llave=2  #valor=1 #valores=2




/*
assert objEnMapeo {
	all o:Objeto, m:Mapeo | (o -> Valor in m.valores) or
			 (Llave -> o in m.valores)  
}
check objEnMapeo for 5*/


//#Mapeo= 0 #llave=1  #valor=1 #valores=0

//(Objeto not in llave) or (Objeto not in valor)
run {#Mapeo = 2 #Llave = 3 #valores = 2 #Valor=2} 


