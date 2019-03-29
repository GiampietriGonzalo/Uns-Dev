sig Objeto{}

sig Llave extends Objeto{
	valor:lone Valor
}

sig Valor extends Objeto{
	llave:Llave
	
}

sig Mapeo{
	llaves: some Llave
}

//INCISO A
fact{
	all l:Llave, m:Mapeo | (l in m.llaves)
}


fact{all o:Objeto | (o in Llave) or (o in Valor)}

/*INCISO B
Sí, puede haber un objeto que no sea llave o valor ya que Objeto es no abstracto. 
*/

/*Inciso C*/
//La aserción no se verifica
assert{ all v:Valor, l:Llave | (v in l.valor) and (l in v.llave)}

/*
fact{
	all v:Valor , l:Llave| (v in l.valor) and (l in v.llave)
}
*/

run{} for 5
