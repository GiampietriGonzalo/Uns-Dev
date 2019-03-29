sig Objeto{}
sig Llave extends Objeto{}
sig Valor extends Objeto{}
sig  Mapeo{
	R: Llave->lone Valor
}

fact{
	all o: Objeto | (o in Llave) or (o in Valor) 
}

fact{
	all l:Llave, m:Mapeo | (l in m.R.Valor) implies (l not in (Mapeo.R.Valor - m.R.Valor)) else (l not in m.R.Valor)
}

/*fact{
	all v:Valor,l:Llave,m:Mapeo | (l->v not in (Mapeo.R - m.R)) => (l->v in m.R) 
}*/

/*
fact{
	all v:Valor,l:Llave, m: Mapeo | (l->v in m.R) and (l->v not in (Mapeo.R - m.R))
}
*/

fact{
	#Mapeo>=1
	all m:Mapeo | #m.R>=1
}
run{} for 5
