/*
	Todo lo que sea codigo comentado, es el primer intento que esta mal.
*/
sig User
{
	carrito : one ShoppingCart
}

sig ShoppingCart
{
	/* Los dos conjuntos ya son disjuntos
	ordenesAbiertas : set OpenOrder,
	ordenesCerradas : set CloseOrder
	*/
	ordenes: set Order
}

abstract sig Order
{
	elementos : set Item
}

sig OpenOrder extends Order{}
sig CloseOrder extends Order{}

sig Item{}

//--------- Facts------------------------
//El carrito pertenece a un unico usuario
fact{
	no u1,u2:User ,s:ShoppingCart | (u1->s in carrito) and (u2->s in carrito) and u1!=u2 
}

//La orden pertenece a un carrito ya sea en un grupo u otro
fact{
	no o:Order ,s1,s2:ShoppingCart | 
	(o in s1.ordenes) and (o in s2.ordenes) and s1!=s2
}

//El item debe pertenecer a una orden
fact{
	no i:Item, s:ShoppingCart | (i not in s.ordenes.elementos) and (i not in s.ordenes.elementos) 
}

//El nro de carritos es igual al de usuarios
fact{
	#ShoppingCart = #User
}

//--------Funciones--------------
//Añadir a la orden
pred anadirItem[o1,o2:OpenOrder,i:Item]
{
	(no it:Item | it not in o2.elementos) and
	(o1.elementos = o2.elementos + i) 
}

//Remover de la orden
pred removerItem[o1,o2:OpenOrder,i:Item]
{
	(no it:Item | it in o2.lementos) and
	(o1.elementos = o2.elementos - i) 
}

//funcion o predicado? ---->pred
pred same_status[o1,o2:Order]
{
	(o1 in OpenOrder and o2 in OpenOrder) or (o1 in OpenOrder and o2 in OpenOrder)
}

//Añadir orden al usuario
pred añadirOrden[u1,u2:User, o:Order]
{	
	//-----------------------------------------------------------------------
	//-----------------Esquema: precond - efecto---------------------
	//-----------------------------------------------------------------------	
	
	//Precondiciones

	( (o in OpenOrder) and (o.elementos=none) and (o not in u1.carrito.ordenes) ) and	

	//si  o' esta en el primero entonces esta en el segundo
	//o1!=o2 se puede obviar con los correspondientes facts
	
	//Pruebo que sean 2 atomos diferentes pero equivalentes entre u1 y u2
	(all o1:Order | 
	 	(o1 in u1.carrito.ordenes implies 
			(some o2: Order | 
					(o2 in u2.carrito.ordenes and o2.elementos=o1.elementos and o1!=o2 and same_status[o1,o2] )
			 )
	  	)
	) and
	//Pruebo que sean 2 atomos diferentes pero equivalentes entre u2 y u1, o que sea el "o" en u2 
	(all o3:Order |
	 	(o3 in u2.carrito.ordenes implies
			( 
				(some o4: Order |
					(o4 in u1.carrito.ordenes and o3.elementos=o4.elementos and o3!=o4 and same_status[o3,o4]) 
				)
				 or
				(o3 = o)
			)
		)
	) and
	//Verificaciones extras para el agregado
	(#u1.carrito.ordenes = #(u2.carrito.ordenes + o))

	/*(ord1 in u1.carrito.ordenes ) and ( ord2 in u2.carrito.ordenes) and (ord1.elementos = ord2.elementos) and
						(o in u2.carrito.ordenes) and (o in OpenOrder) and (#o = 0)  
	*/
	//u2 = u2.carrito.ordenesAbiertas + o
}

//Remover orden del usuario
pred removerOrden[u1,u2:User, o:Order]
{
	//u1.carrito.ordenesAbiertas = u2.carrito.ordenesAbiertas - o
	
	//Precondiciones
	(o in u2.ordenes) and

	//Efecto
	(all o1:Order |
		(o1 in u1.carrito.ordenes implies
			(some o2:Order | o2 in u2.carrito.ordenes and o2.elementos=o1.elementos and o1!=o2 and same_status[o1,o2])
		)
	)
	and
	(all o3:Order |
		(o3 in u2.carrito.ordenes implies
			(
				(some o4:Order | o4 in u1.carrito.ordenes and o3.elementos=o4.elementos and o3!=o4 and same_status[o3,o4] )
				or
				(o3=o)
			)
		)
	)
	//Verificaciones extras para el agregado
	(#u1.carrito.ordenes = #(u2.carrito.ordenes - o))
}

//Cerrar una orden del usuario
pred cerrarOrden[u1,u2: User, o:Order]
{
	//(u1.carrito.ordenesAbiertas = u2.carrito.ordenesAbiertas - o) and 
	//(u1.carrito.ordenesCerradas = u2.carrito.ordenesCerradas + o)
}

//Despachar el carrito de un usuario
//Implica liberar el carrito de todas las ordenes ya cerradas, y asumo que solo se puede cuando no hay ordenes abiertas 
pred despacharCarrito[u1,u2:User]
{
	//(#u1.carrito.ordenesAbiertas = 0) and 
	//(u1.carrito.ordenesCerradas = u2.carrito.ordenesCerradas - u2.carrito.ordenesCerradas)
}

run
{
	some u1, u2:User , o:OpenOrder | añadirOrden[u1,u2,o]
} 
