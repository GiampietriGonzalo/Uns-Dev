one sig Juan, Pedro {}
// existe un solo juan y un solo pedro

sig Culpable in univ {}
//culpable puede estar tanto juan como pedro

fact { Juan not in Culpable }
//juan no esta en culpable

fact { Juan in Culpable implies Pedro in Culpable }
//en una implicacion cualquier cosa implica falso es falso y como simpre va a dar
//falso implica algo puede que el fact de verdadero , no suma nada 

assert conclusion { Pedro not in Culpable }
//Las aserciones son restricciones son consecuencia de los hechos del modelo

check conclusion
//El comando check instruye al analizadorpara que encuentre un contraejemplo.

run{}

/*una vez removido el primer hecho puede encontrar dos contra ejemplos ya que
 juan puede ser culpable o no v v | f v | esos serian los contraejemplos*/
/* dejando la primer hecho solo tengo un contraejemplo ya que  f v| osea pedro 
tiene que ser culpable por el contrejemoplo y por el primer hecho juan no es culpable osea f ->v*/ 
