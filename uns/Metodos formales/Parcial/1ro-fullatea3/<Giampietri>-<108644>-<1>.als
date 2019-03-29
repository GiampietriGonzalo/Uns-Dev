abstract sig Ciudad{
	guerrero: Guerrero,
	ejercito: Ejercito
}
one sig Hatti,Akkad,Elam extends Ciudad{}

abstract sig Arma{}
one sig Hacha,Espada,Lanza extends Arma{}

abstract sig Ejercito{
	lider:Guerrero
}

one sig EjercitoMenor, EjercitoMedio,EjercitoMayor extends Ejercito{}

abstract sig Guerrero{
	arma: Arma,
	ejercito: Ejercito,
	ciudad: Ciudad
}
one sig Sargon,UrEl, Hattusil extends Guerrero{}


//Restricciones sobre las relaciones:

//Relacion reciproca entre una ciudad y su ejercito
fact{ all e:Ejercito, c: Ciudad | ((e in c.ejercito) and (e.lider in c.guerrero)) or ((e not in c.ejercito) and (e.lider not in c.guerrero)) }
//Relación recíproca entre una ciudad y su guerrero
fact{ all g:Guerrero, c:Ciudad | ((g in c.guerrero) and (c in g.ciudad)) or ((g not in c.guerrero) and (c not in g.ciudad)) }
//Relación recíproca entre un ejercito y su lider
fact{ all e:Ejercito, g:Guerrero | ((g in e.lider) and (e in g.ejercito)) or ((g not in e.lider) and (e not in g.ejercito))}


//Restricciones del enunciado:

//1
fact{ Hatti not in UrEl.ciudad }

//2
fact{Lanza in EjercitoMenor.lider.arma }

//3
fact{ 
	Elam not in Sargon.ciudad and
	EjercitoMayor not in Elam.ejercito and
	EjercitoMenor not in Sargon.ejercito
}

//4
fact{ Espada in Akkad.guerrero.arma } 

//5
fact{ (Hacha in Hattusil.arma) and (Hacha not in EjercitoMayor.lider.arma)}

run{}
