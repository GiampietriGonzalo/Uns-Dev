open util/ordering[State] as ord

sig  Biblioteca { states: set State}
sig  Libro { escritoPor: set Autor}
sig  Autor {}

sig  State { coleccion: set Libro}

fact traces { 
	init [ord/first]
	all s:State - ord/last |
	 let s1 = s.next | { some l: Libro | 
		agregarAColeccion[s,s1,l] or quitarDeColeccion[s,s1,l] }
}

pred init[s:State] {
	no s.coleccion
}
pred agregarAColeccion [s,s1: State, l:Libro] {
	s1.coleccion = s.coleccion + l
}
pred quitarDeColeccion [s,s1: State, l:Libro] {
	s1.coleccion = s.coleccion - l
}

