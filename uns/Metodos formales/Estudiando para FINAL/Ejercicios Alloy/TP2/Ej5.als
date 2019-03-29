one sig Storage{
	mensajes: set Message,
	autorizadas: set Computer
}

sig Computer{
	vecinas: some Computer,
	almacenamiento: Storage
}

sig Message{
	origen: Computer,
	destino: Computer,
}


//Una computador no puede ser su propia vecina
fact{
	all c:Computer | c not in c.vecinas
}

//Relacion simetrica de vecinas
fact{
	all c1,c2:Computer | c1 in c2.vecinas <=> c2 in c1.vecinas
}

//Storage solo puede almacenar hasta 50 mensajes
fact{
	#Storage.mensajes >=0 and
	#Storage.mensajes <= 50 and
	#Storage.autorizadas>=0 and
	#Storage.autorizadas<=5
}

//Una computadora puede enviar (añadir) al almacenamiento un mensaje 
//para sí misma o para una computadora vecina.
fact{
	all m:Message | (m.destino = m.origen) or (m.origen in m.destino.vecinas)
}

/*
Una computadora puede recibir (retirar) del almacenamiento un mensaje que la
tenga como destinataria.
*/
pred retirar[c:Computer,s,s1:Storage]{
	one m:Message | (c in m.destino) and (s1.mensajes=s.mensajes - m)
}

/*
Una computadora puede consultar al almacenamiento (sin retirar) todos los mensajes
que la tengan como destinataria.
*/
fun consultarMensajes[c:Computer,s:Storage]: set Message{
	{m:Message | m in s.mensajes and m.destino=c}
}

/*
Una computadora puede consultar al almacenamiento (sin retirar) todos los mensajes que la tengan como destinataria y que hayan sido enviados por una de sus
computadoras vecinas.
*/
fun consultarMensajesVecinas[c:Computer,s:Storage]: set Message{
	{m:Message |  m in (consultarMensajes[c,s]) and (m.origen in  c.vecinas) }
}

/*
Una computadora puede recibir (retirar) del alacenamiento todos los mensajes que
la tengan como destinataria y que hayan sido enviados por una de sus computadoras
vecinas.
*/
pred retirarVecinas[c:Computer,s,s1:Storage]{
	s1.mensajes = s.mensajes - consultarMensajesVecinas[c,s]
}

/*
Una computadora puede recibir (retirar) del alacenamiento todos los mensajes que
la tengan como destinataria.
*/
pred retirarTodas[c:Computer,s,s1:Storage]{
	all m:Message | c in m.destino and (s1.mensajes = s.mensajes - m)
}

/*
Agregar una computadora al conjunto de computadoras autorizadas de un almacenamiento.
*/

pred agregarAutorizada[c:Computer,s,s1:Storage]{
	s.autorizadas = s1.autorizadas + c
}


/*
Remover una computadora del conjunto de computadoras autorizadas de un almacenamiento. Dicha computadora puede ser removida ´unicamente si el almacenamiento
no posee mensajes que la tengan como destinataria.
*/
pred quitarAutorizada[c:Computer,s,s1:Storage]{
	#(consultarMensajes[c,s])=0	=> (s.autorizadas = s1.autorizadas - c)
}
/*
Remover una computadora del conjunto de computadoras autorizadas de un almacenamiento. Al remover dicha computadora, el almacenamiento mantiene todos los
mensajes que la tengan como destinataria.
*/
pred quitarAutorizadaManteniendo[c:Computer,s,s1:Storage]{
	(s.autorizadas = s1.autorizadas - c)
}

/*Remover una computadora del conjunto de computadoras autorizadas de un almacenamiento. Al remover dicha computadora, el almacenamiento elimina todos los
mensajes que la tengan como destinataria.*/
pred quitarAutorizadaYRemoverMensajes[c:Computer,s,s1:Storage]{
	 retirarTodas[c,s,s1] and  (s.autorizadas = s1.autorizadas - c)
}

//run{ some c:Computer,s,s1:Storage | puedeRetirar[c,s,s1]} for 15
//run{ some c:Computer, s,s1:Storage | retirarVecinas[c,s,s1]} for 5
//run{ some c:Computer, s,s1:Storage | retirarTodas[c,s,s1]} for 5
//run{ some c:Computer, s,s1:Storage | agregarAutorizada[c,s,s1]} for 5
//run{ some c:Computer, s,s1:Storage | quitarAutorizada[c,s,s1]} for 5
//run{ some c:Computer, s,s1:Storage | quitarAutorizadaYRemoverMensajes[c,s,s1]} for 5
//run{} for 10

