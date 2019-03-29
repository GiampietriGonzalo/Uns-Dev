sig Category{
	
}

sig Book{
	cat: lone Category,
	similar: set Book
}

//Un libro no puede estar dentro de su conjunto de libros similares
fact{
	all b:Book | b not in b.similar
}

//Simetria de la relacion similar
fact{
	all b1,b2:Book | b1 in b2.similar <=> b2 in b1.similar
}

//Los libros similares comparten categoria
fact{
	all b1,b2:Book | (b1 in b2.similar) => (b1.cat=b2.cat)
}


run{#Book=7 and #Category=3} for 10
	
