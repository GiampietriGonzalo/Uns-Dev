one sig Juan, Pedro {}
sig Culpable in univ {}
fact { Juan not in Culpable }
fact { Juan in Culpable implies Pedro in Culpable }
assert conclusion { Pedro not in Culpable }
//check conclusion

//Conclusion inválida

run{}
