sig Candidato { }

one sig Alejo, Luca, Carlos, David in Candidato { }

one sig Maria {
	alto : set Candidato,
	moreno : set Candidato,
	buenmozo : set Candidato
}

fact{
	Alejo not in Luca and
	Luca not in Carlos and
	Carlos not in David and
	Alejo not in David and
	Luca not in David and
	Alejo not in Carlos
}


//Solo tres de los hombres son altos, solo dos son morenos, y solo uno es buenmozo
fact{
	#Maria.alto=3 and
	#Maria.moreno=2 and
	#Maria.buenmozo=1
}

//Cada uno de los cuatro hombres tiene al menos una de las caracterısticas buscadas por Marıa.
fact{
	
	all c:Candidato | (c in Maria.alto) or (c in Maria.moreno) or (c in Maria.buenmozo)

}

//Alejo y Luca tienen la misma complexion (ambos son morenos o ambos no lo son).
fact{
	(Alejo in Maria.moreno and Luca in Maria.moreno) or
	(Alejo not in Maria.moreno and Luca not in Maria.moreno)
}

//Luca y Carlos tienen la misma altura.
fact{
	(Carlos in Maria.alto and Luca in Maria.alto) or
	(Carlos not in Maria.alto and Luca not in Maria.alto)
}

//O bien Carlos es alto o David lo es, pero ambos no lo son.
fact{
	(Carlos in Maria.alto and David not in Maria.alto) or
	(Carlos not in Maria.alto and David in Maria.alto)
}

run{} for 4
