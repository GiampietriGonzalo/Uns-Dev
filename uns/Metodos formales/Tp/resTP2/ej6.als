sig User{
	cart: set ShoppingCart
}

sig ShoppingCart{
	orders: set Order
}

abstract sig Order{
	items: set Item,
	scart: ShoppingCart
}

sig OpenOrder,CloseOrder extends Order{}

sig Item{}

//Cardinalidades
fact{
	#ShoppingCart = #User
	#User.cart>1
}

//Relación cart/orders debe ser recíproca
fact{
	all s:ShoppingCart, o:Order | ((o in s.orders) and (s in o.scart)) or ((o not in s.orders) and (s not in o.scart))
}

//Relación 1 a 1 entre User y ShoppingCart
fact{
	some u,u1: User | (u!=u1) and (u.cart != u1.cart) 
}

pred agregarItem[o,o1:Order,i:Item]{
	all s:ShoppingCart | (o in OpenOrder) and (o in s.orders) and  (o1.items = o.items+i) and (actualizarShoppingCart[s,s,o1]) 
}

pred actualizarShoppingCart[s1,s:ShoppingCart,o:Order]{
	all u:User | (s in u.cart) and (s1.orders=s.orders + o) and actualizarUser[u,u,s1]
}

pred actualizarUser[u,u1:User,s:ShoppingCart]{
	(u1.cart = u.cart + s) and (eliminarCart[u,u1,s])
}

pred eliminarCart[u,u1:User,s:ShoppingCart]{
	(u1.cart = s)
}

/* agregarItem  a)
run{ some o,o1:Order , i:Item | agregarItem[o,o1,i]}
*/
/*actualizarUser*/
run{ some u,u1:User , s:ShoppingCart | actualizarUser[u,u1,s] #User=2}  





