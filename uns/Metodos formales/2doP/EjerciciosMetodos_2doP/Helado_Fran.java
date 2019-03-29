public class Helado {

  private /*@ spec_public @*/ boolean telgopor;
  /*@ public invariant telgopor && chico ==> (maxSabores == maxSaboresTelgoporChico); @*/
  /*@ public invariant telgopor && grande ==> (maxSabores == maxSaboresTelgoporGrande); @*/
  /*@ public invariant !telgopor && chico ==> (maxSabores == maxSaboresCucuruchoChico); @*/
  /*@ public invariant !telgopor && grande ==> (maxSabores == maxSaboresCucuruchoGrande); @*/

  private /*@ spec_public @*/ boolean chico;
  /*@ public invariant chico <==> ( grande == false); @*/

  private /*@ spec_public @*/ boolean grande;

  private /*@ spec_public @*/ static final int maxSaboresTelgoporChico = 3;
  private /*@ spec_public @*/ static final int maxSaboresTelgoporGrande = 4;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoChico = 2;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoGrande = 3;

  private /*@ spec_public @*/ int maxSabores;
  /*@ public invariant cantSabores <= maxSabores; @*/

  private /*@ spec_public @*/ int cantSabores;


  public Helado (boolean t, boolean c, boolean g){
    telgopor = t;
    asignarTamanio(c, g);
    establecerLimiteSabores();
    cantSabores = 0;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  /*@ public normal_behavior
    @ requires telgopor;
    @ requires cantSabores <= 3;
    @ requires c == true;
    @ requires g == false; 
    @ ensures chico == true;
    @ ensures grande == false;
    @
    @ also
    @
    @ public normal_behavior
    @ requires telgopor;
    @ requires cantSabores <= 4;
    @ requires c == false;
    @ requires g == true;
    @ ensures chico == false;
    @ ensures grande == true;
    @
    @ also
    @
    @ public normal_behavior
    @ requires !telgopor;
    @ requires cantSabores <= 2;
    @ requires c == true;
    @ requires g == false;
    @ ensures chico == true;
    @ ensures grande == false;
    @
    @ also
    @
    @ public normal_behavior
    @ requires !telgopor;
    @ requires cantSabores <= 3;
    @ requires c == false;
    @ requires g == true;
    @ ensures chico == false;
    @ ensures grande == true;
    @
    @*/
  public void asignarTamanio(boolean c, boolean g){
    chico = c;
    grande = g;
    establecerLimiteSabores();
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


  public void establecerLimiteSabores() throws ExcepcionEnvasePrincipalInvalido{
    if (telgopor)
      if (chico)
        maxSabores = maxSaboresTelgoporChico;
      else maxSabores = maxSaboresTelgoporGrande;
    else  if (!telgopor)
            if (chico)
              maxSabores = maxSaboresCucuruchoChico;
            else maxSabores = maxSaboresCucuruchoGrande;
          else throw new ExcepcionEnvasePrincipalInvalido();
    // < ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


  public void agregarSabor() throws ExcepcionCantSaboresSuperada {
    cantSabores++;
    // < SE PERMITE MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

}
