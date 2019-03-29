
open util/ordering[State]

/* granjero y sus posesiones son objetos */
abstract sig Object { eats: set Object }
one sig Farmer, Fox, Chicken, Grain extends Object {}

/* define que comen las poseciones del granjero */
fact { eats = Fox->Chicken + Chicken->Grain}

/* define el estado como el conjunto de objetos a cada orilla */
sig State { right, left: set Object }

/* En el estado inicial, todo esta en la orilla derecha */
fact { first.right = Object && no first.left }

/* solo un item cambia de orilla */
pred crossRiver [from, from', to, to': set Object] {
  one x: from | {
    from' = from - x - Farmer - from'.eats
    to' = to + x + Farmer
  }
}

/* cambio de estado ocasionado por la transicion "crossRiver" */
fact {
  all s: State, s': s.next {
    Farmer in s.right =>
      crossRiver [s.right, s'.right, s.left, s'.left]
    else
      crossRiver [s.left, s'.left, s.right, s'.right]
  }
}

/* el granjero mueve todas sus posesiones a la orilla izquierda */
run { last.left=Object } for exactly 8 State

