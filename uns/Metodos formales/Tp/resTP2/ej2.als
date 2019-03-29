sig Candidato { }
one sig Alejo, Luca, Carlos, David extends Candidato { }

one sig Maria {
	alto : set Candidato,	
	moreno : set Candidato,
	buenmozo : set Candidato
}

fact{
  one c: Candidato | c in Maria.moreno and c in Maria.alto and c in Maria.buenmozo
}

fact {
  #Maria.alto=3
  #Maria.moreno=2
  #Maria.buenmozo=1	
}

fact {
  all c: Candidato | c in Maria.alto or c in Maria.moreno or c in Maria.buenmozo
}

fact {
  (Alejo in Maria.moreno and Luca in Maria.moreno) or (Alejo not in Maria.moreno and Luca not in Maria.moreno)
}

fact{
  (Luca in Maria.alto and Carlos in Maria.alto) or (Luca not in Maria.alto and Carlos not in Maria.alto)
}

fact{
  (Carlos in Maria.alto and David not in Maria.alto) or (Carlos not in Maria.alto and David in Maria.alto)
}


run{}
