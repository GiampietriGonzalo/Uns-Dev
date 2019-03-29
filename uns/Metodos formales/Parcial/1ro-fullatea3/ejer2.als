sig Objeto {}

sig llave, valor extends Objeto {}

sig Mapeo {
	valores: llave -> lone valor
}

fact{
	all o: Objeto | o in (llave+valor)
}

run{}
