abstract sig Deporte{
	delegacion:set Persona
}

sig Individual extends Deporte{}
sig Grupal extends Deporte{}
sig Mixto in Grupal{}

sig Persona{}
sig Entrenador,Deportista in Persona{}
sig Coordinador in Persona{}

fact{
	#Individual.delegacion=1 
	//#Grupal.delegacion=3
}

fact{ all p:Persona , g:Grupal | (p in g.delegacion) and ( (p in Deportista) or  (p in Entrenador))}



//INCISO A

//1
fact{ all p:Persona, i:Individual | (p in i.delegacion) and (p in Deportista) }

//2

fact{
 all p1,p2:Persona, g:Grupal | (p1 in g.delegacion) and (p2 in g.delegacion)  and (  ((p1 in Entrenador) and (p2 not in Entrenador) )  or ((p1 not in Entrenador) and (p2 in Entrenador) ) )  
}

//3
/*
//MIXTOS
fact{ 
 all p:Persona, m:Mixto | (p in m.delegacion) and ( 
					   	( (p in Deportista) and (p not in Entrenador) and (p not in m.coordinador) ) or
					   	( (p in Entrenador) and (p not in Deportista) and (p not in m.coordinador) )	 or
						( (p in m.coordinador) and (p not in Deportista) and (p not in Entrenador) )
					    )
}

//GRUPAL
fact{ 
 all p:Persona, g:Grupal | (p in g.delegacion) and ( 
					   	( (p in Deportista) and (p not in Entrenador) ) or
					   	( (p in Entrenador) and (p not in Deportista) )	
					    )
}

*/

//4
//fact{all m:Mixto | m.coordinador in m.delegacion} 

run{}

