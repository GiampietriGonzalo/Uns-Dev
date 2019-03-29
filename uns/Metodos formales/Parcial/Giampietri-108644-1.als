//EJERCICIO 1
abstract sig Persona{
	apellido: Apellido,
	ec: estadoCivil,
	ciudad:Ciudad
}
one sig Silvio,Diego,Vicente,Carlos extends Persona{

}

abstract sig Apellido{}
one sig Conte,Duarte,Sosa,Valdez extends Apellido{}

abstract sig estadoCivil{}
one sig Soltero,Casado,Viudo,Divorciado extends estadoCivil{}

abstract sig Ciudad{}
one sig Darregueira,Viedma,Cabildo,Saldungaray extends Ciudad{}

//a) RESTRICCIONES

/*Todos tienen apellidos diferentes*/
fact{
	no (Silvio.apellido & Carlos.apellido & Diego.apellido & Vicente.apellido)
}

/*Todos tienen un estado civil diferente*/
fact{
	all p:Persona | p.ec not in ( (Persona - p).ec )
}

/*Carlos es viudo*/
fact{
	Viudo in Carlos.ec
}

/*Todos provienen de ciudades diferentes*/
fact{
	no (Diego.ciudad & Silvio.ciudad & Vicente.ciudad & Carlos.ciudad)
}

/*La inicial del apellido de cada uno coincide con la primer letra del nombre de la ciudad natal de cada uno*/
fact{
	one p: Persona | (Sosa in p.apellido) and (Saldungaray in p.ciudad) and	
	one p: Persona | (Valdez in p.apellido) and (Viedma in p.ciudad) and	
	one p: Persona | (Duarte in p.apellido) and (Darregueira in p.ciudad) and
	one p: Persona | (Conte in p.apellido) and (Cabildo in p.ciudad) 
}

/*Ninguno de ellos posee la misma inicial en su nombre, su apellido o su estado civil*/
fact{
	//ESTADOS CIVILES-NOMBRES
	Soltero not in Silvio.ec and
	Casado not in Carlos.ec and //Trivial, ya que en el primer fact establecimos que Carlos es Viudo
	Viudo not in Vicente.ec and
	Divorciado not in Diego.ec and
	
	//APELLIDOS-NOMBRES
	Duarte not in Diego.apellido and
	Conte not in Carlos.apellido and
	Sosa not in Silvio.apellido and
	Valdez not in Vicente.apellido	

	//ESTADOS CIVILES -APELLIDOS
	one p:Persona | (Duarte in p.apellido) and (Divorciado not in p.ec) and
	one p:Persona | (Sosa in p.apellido) and (Soltero not in p.ec) and
	one p:Persona | (Conte in p.apellido) and (Casado not in p.ec) and	
	one p:Persona | (Valdez in p.apellido) and (Viudo not in p.ec) 
}

/*Vicente se divorció de la misma mujer de la que luego enviudó el que nacion en Saldungaray*/
fact{
	Divorciado in Vicente.ec and
	Saldungaray not in Vicente.ciudad and
	Saldungaray in Carlos.ciudad
}


/*b) RESPUESTA

Carlos Sosa es viudo y nació en  Saldungaray.
Diego Valdez es soltero y nació en  Viedma.
Silvio Duarte es casado y nació en Darregueira.
Vicente Conte es divorciado y nació en Cabildo.
*/

run{}
