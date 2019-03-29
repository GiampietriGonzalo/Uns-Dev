one sig Almacenamiento{
	mensajes : set Mensaje,
	autorizadas : set Computadora
}
sig Mensaje{
	origen: Computadora,
	destino: Computadora
}
sig Computadora{
	vecino: some Computadora
}

fact{
	#Almacenamiento.mensajes >=0 and
	#Almacenamiento.mensajes <=50 and
	#Almacenamiento.autorizadas>=0 and
	#Almacenamiento.autorizadas<=5
}

fact{
	all m: Mensaje | ((m.destino in m.origen.vecino) or (m.origen=m.destino))
}

fact{
      all c: Computadora | c not in c.vecino
}

fact{
 	all c1,c2: Computadora | (c1 in c2.vecino and c2 in c1.vecino) or (c1 not in c2.vecino and c2 not in c1.vecino)
}

fact{
	Almacenamiento.mensajes.origen in Almacenamiento.autorizadas
}

fact{
	all m: Mensaje | m in Almacenamiento.mensajes 
}

pred enviar[a1,a:Almacenamiento,m:Mensaje]{
		a1.mensajes=a.mensajes + m
}

pred recibir[a1,a:Almacenamiento,c:Computadora]{
	some m: Mensaje | (c in m.destino) and (a1.mensajes = a.mensajes - m ) 
}

fun consultarMensajes[c:Computadora] : set Mensaje {
		{m:Mensaje | (m in destino.c)}
}

fun consultarVecinos[c:Computadora]: set Mensaje{

	{m: Mensaje | (m in destino.c) and (m.origen in c.vecino )  }
}

pred recibirVecinos[a1,a:Almacenamiento,c:Computadora]{
	a1.mensajes = a.mensajes - (consultarVecinos[c])
}

pred recibirTodos[a1,a:Almacenamiento,c:Computadora]{
	a1.mensajes = a.mensajes - (consultarMensajes[c])
}

pred agregarAutorizada[a1,a:Almacenamiento, c:Computadora]{
	a1.autorizadas = a.autorizadas + c
}

pred eliminarAutorizadaCM[a1,a:Almacenamiento,c:Computadora]{
 	all m:Mensaje | (c not in m.destino) and (a1.autorizadas=a.autorizadas - c) 
}

pred eliminarAutorizadaSM[a1,a:Almacenamiento, c:Computadora]{		
	 (a1.autorizadas=a.autorizadas - c) 
}

pred eliminarAutorizadaYMD[a1,a:Almacenamiento, c:Computadora]{
	(eliminarAutorizadaSM[a1,a,c]) and (a1.mensajes=a.mensajes - consultarMensajes[c])     
}


run{some c:Computadora, a1,a:Almacenamiento | eliminarAutorizadaYMD[a1,a,c] } for 5
//run{some c:Computadora, a1,a:Almacenamiento | eliminarAutorizadaSM[a1,a,c] } for 5
//run{some c:Computadora, a1,a:Almacenamiento | eliminarAutorizadaCM[a1,a,c]} for 5
//run{some c:Computadora, a1,a:Almacenamiento | agregarAutorizada[a1,a,c]} for 5
//run{some c:Computadora, a1,a:Almacenamiento | recibirTodos[a1,a,c]} for 5

//run{some c:Computadora, a1,a:Almacenamiento | recibirVecinos[a1,a,c] and #Computadora=4 and #Mensaje=2 and #destino=2 #origen=2} for 5


//run{some c:Computadora , a,a1: Almacenamiento | recibir[a1,a,c] }for 5
