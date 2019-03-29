public class Helado {

  private /*@ spec_public @*/ boolean telgopor;

  private /*@ spec_public @*/ boolean chico;
  private /*@ spec_public @*/ boolean grande;
 
 //posee un tamaño, el cual es chico o grande, siendo estos mutuamente excluyentes (atributos chico y grande).
 /*@ public invariant chico!=grande; @*/	

  private /*@ spec_public @*/ static final int maxSaboresTelgoporChico = 3;
  private /*@ spec_public @*/ static final int maxSaboresTelgoporGrande = 4;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoChico = 2;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoGrande = 3;

  private /*@ spec_public @*/ int maxSabores;

  private /*@ spec_public @*/ int cantSabores;

  /*posee una cantidad maxima de sabores (atributos maxSabores), la cual se encuentra determinada por el tipo del envase principal
	y el tamaño del helado:telgopor y chico = 3, cucurucho y grande = 3 y cucurucho y chico = 2.*/
	
  /*@ public invariant (telgopor && chico) ==> (maxSabores == maxSaboresTelgoporChico); @*/
  /*@ public invariant (telgopor && grande) ==> (maxSabores == maxSaboresTelgoporGrande); @*/
  /*@ public invariant (!telgopor && chico) ==> (maxSabores == maxSaboresCucuruchoChico); @*/
  /*@ public invariant (!telgopor && grande) ==> (maxSabores == maxSaboresCucuruchoGrande); @*/	
	
  /*@ public invariant cantSabores <= maxSabores; @*/

  public Helado (boolean t, boolean c, boolean g){
    telgopor = t;
    asignarTamanio(c, g);
    establecerLimiteSabores();
    cantSabores = 0;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  /*@ public normal_behavior
	@ requires c;
	@ requires !g;
	@ requires chico;
	@ ensures chico;
	@
	@ also
	@
	@ public normal_behavior
	@ requires !c;
	@ requires g;
	@ requires grande;
	@ ensures grande;
	@
	@ also
	@
	@ public normal_behavior
    @ requires !c;
	@ requires g;
	@ requires !telgopor;
    @ requires chico;		
    @ ensures grande;
	@ ensures maxSabores == maxSaboresCucuruchoGrande;
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public normal_behavior
    @ requires !c;
	@ requires g;
	@ requires telgopor;
    @ requires chico;		
    @ ensures grande;
	@ ensures maxSabores == maxSaboresTelgoporGrande;
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public normal_behavior
	@ requires c;
	@ requires !g;
	@ requires !telgopor;
	@ requires grande;
    @ requires cantSabores <= maxSaboresCucuruchoChico;
	@ ensures chico;
	@ ensures maxSabores == maxSaboresCucuruchoChico;
	@ ensures cantSabores <= maxSaboresCucuruchoChico;
	@
	@ also
	@
	@ public normal_behavior
	@ requires c;
	@ requires !g;
	@ requires telgopor;
	@ requires grande;
    @ requires cantSabores <= maxSaboresTelgoporChico;
	@ ensures chico;	
	@ ensures maxSabores == maxSaboresTelgoporChico;	
	@ ensures cantSabores <= maxSaboresTelgoporChico;
	@
    @*/
  public void asignarTamanio(boolean c, boolean g){
    chico = c;
    grande = g;
    establecerLimiteSabores();
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

  /*@ public normal_behavior
  @ requires telgopor;
  @ requires chico;
  @ requires !grande;
  @ ensures maxSabores == maxSaboresTelgoporChico;
  @ ensures cantSabores <= maxSabores;
  @   
  @ also
  @
  @ public normal_behavior
  @ requires telgopor;
  @ requires !chico;
  @ requires grande;
  @ ensures maxSabores == maxSaboresTelgoporGrande;
  @ ensures cantSabores <= maxSabores;
  @
  @ also
  @
  @ public normal_behavior
  @ requires !telgopor;
  @ requires chico;
  @ requires !grande;
  @ ensures maxSabores == maxSaboresCucuruchoChico;
  @ ensures cantSabores <= maxSabores;
  @
  @ also
  @
  @ public normal_behavior
  @ requires !telgopor;
  @ requires !chico;
  @ requires grande;
  @ ensures maxSabores == maxSaboresCucuruchoGrande;
  @ ensures cantSabores <= maxSabores;
  @
  */
  public void establecerLimiteSabores() throws ExcepcionEnvasePrincipalInvalido{
    if (telgopor)
      if (chico)
        maxSabores = maxSaboresTelgoporChico;
      else maxSabores = maxSaboresTelgoporGrande;
    else  if (telgopor == false)
            if (chico)
              maxSabores = maxSaboresCucuruchoChico;
            else maxSabores = maxSaboresCucuruchoGrande;
          else throw new ExcepcionEnvasePrincipalInvalido();
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

  /*NUNCA TIRA LA EXCEPCION*/
  /*@ public normal_behavior
	@ requires !telgopor; 
	@ requires grande;
	@ requires !chico;
	@ requires maxSabores == maxSaboresCucuruchoGrande;
	@ requires cantSabores < maxSabores;
	@ ensures cantSabores == \old(cantSabores)+1;
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public normal_behavior
	@ requires !telgopor; 
	@ requires !grande;
	@ requires chico;
	@ requires maxSabores == maxSaboresCucuruchoChico;
	@ requires cantSabores < maxSabores;
	@ ensures cantSabores == \old(cantSabores)+1;
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public normal_behavior
	@ requires telgopor; 
	@ requires !grande == false;
	@ requires chico;
	@ requires maxSabores == maxSaboresTelgoporChico;
	@ requires cantSabores < maxSabores;
	@ ensures cantSabores == \old(cantSabores)+1;
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public normal_behavior
	@ requires telgopor; 
	@ requires grande;
	@ requires !chico;
	@ requires maxSabores == maxSaboresCucuruchoGrande;
	@ requires cantSabores < maxSabores;
	@ ensures cantSabores == \old(cantSabores)+1;  
	@ ensures cantSabores <= maxSabores;
	@
	@ also
	@
	@ public exceptional_behavior
	@ requires telgopor;
	@ requires grande;
	@ requires !chico;
	@ requires cantSabores == maxSaboresTelgoporGrande;
	@ signals_only ExcepcionCantSaboresSuperada;
	@
	@ also
	@
	@ public exceptional_behavior
	@ requires telgopor == true;
	@ requires grande == false;
	@ requires chico == true;
	@ requires cantSabores == maxSaboresTelgoporChico;
	@ signals_only ExcepcionCantSaboresSuperada;
	@
	@ also
	@
	@ public exceptional_behavior
	@ requires telgopor == false;
	@ requires grande == true;
	@ requires chico == false;
	@ requires cantSabores == maxSaboresCucuruchoGrande;
	@ signals_only ExcepcionCantSaboresSuperada;
	@
	@ also
	@
	@ public exceptional_behavior
	@ requires telgopor == false;
	@ requires grande == false;
	@ requires chico == true;
	@ requires cantSabores == maxSaboresCucuruchoChico;
	@ signals_only ExcepcionCantSaboresSuperada; 
	@*/
  public void agregarSabor() throws ExcepcionCantSaboresSuperada {
    cantSabores++;
    // < SE PERMITE MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

}
