open util/ordering[Time] as ord
open util/ordering[Biblioteca] as bib

sig Time { }
sig  Biblioteca { coleccion: set (Libro -> Time)}
sig  Libro { escritoPor: set Autor}
sig  Autor {}

fact {	#(bib/first.coleccion) = 0 }


fact traces {
	 no bib/first.coleccion and 
	 all t:Time - ord/last, t1= t.next { 
    	  all b:Biblioteca - bib/last, b1=b.next {  
				some l:Libro  {( b1 = b.next and (agregarAColeccion[t,t1,b,b1,l]  or quitarDeColeccion[t,t1,b,b1,l])) or b1=b }
       	 }
	  }
}

pred agregarAColeccion [t,t1:Time,b,b1:Biblioteca,l:Libro] {
	b1.coleccion = b.coleccion.t -> t1 + l->t1
}
pred quitarDeColeccion[t,t1:Time,b,b1:Biblioteca,l:Libro] {
	b1.coleccion = (b.coleccion.t - l) -> t1
}

