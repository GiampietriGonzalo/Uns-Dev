sig Candidato { }

one sig Alejo, Luca, Carlos, David in Candidato { }

one sig Maria {
			alto : set Candidato,
			moreno : set Candidato,
			buenmozo : set Candidato
		    }

fact	{
	Alejo != Luca and Luca != Carlos and Carlos != David and
	 Alejo !=David and Luca!=David and 
	 Alejo !=Carlos    
}

fact {
	#alto=3 and #moreno=2 and #buenmozo=1
}

fact{ 
	Alejo in (Maria.alto +Maria. moreno +Maria.buenmozo)
	 Luca in (Maria.alto + Maria.moreno +Maria.buenmozo)
         Carlos in (Maria.alto + Maria.moreno +Maria.buenmozo)
	 David in (Maria.alto + Maria.moreno +Maria.buenmozo)
}

fact{
	(Alejo in Maria.moreno and Luca in Maria.moreno) or not (Alejo in Maria.moreno or Luca in Maria.moreno)
}

fact{
	(Carlos in Maria.alto and Luca in Maria.alto) or not (Carlos in Maria.alto or Luca in Maria.alto)
}

fact{
	(Carlos in Maria.alto and David not in Maria.alto) or (Carlos not in Maria.alto and David in Maria.alto)
}

fact{
	((Carlos in Maria.alto)and(Carlos in Maria.moreno)and(Carlos in Maria.buenmozo) ) or
	((Luca in Maria.alto)and(Luca in Maria.moreno)and(Luca in Maria.buenmozo) ) or
	((David in Maria.alto)and(David in Maria.moreno)and(David in Maria.buenmozo) ) or
	((Alejo in Maria.alto)and(Alejo in Maria.moreno)and(Alejo in Maria.buenmozo) ) 
}

run{ } for 4
