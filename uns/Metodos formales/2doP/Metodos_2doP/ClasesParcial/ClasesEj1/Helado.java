public class Helado {

  private /*@ spec_public @*/ boolean telgopor;

  private /*@ spec_public @*/ boolean chico;
  private /*@ spec_public @*/ boolean grande;
 
 //posee un tamaño, el cual es chico o grande, siendo estos mutuamente excluyentes (atributos chico y grande).
  /*@ public invariant chico!=grande @*/	

  private /*@ spec_public @*/ static final int maxSaboresTelgoporChico = 3;
  private /*@ spec_public @*/ static final int maxSaboresTelgoporGrande = 4;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoChico = 2;
  private /*@ spec_public @*/ static final int maxSaboresCucuruchoGrande = 3;

  /*posee una cantidad maxima de sabores (atributos maxSabores), la cual se encuentra determinada por el tipo del envase principal 
	y el tamaño del helado:telgopor y chico = 3, cucurucho y grande = 3 y cucurucho y chico = 2.*/
	
  /*@ public invariant (telgopor & chico) ==> maxSabores<=maxSaboresTelgoporChico @*/
  /*@ public invariant (telgopor & grande) ==> maxSabores<=maxSaboresTelgoporGrande @*/
  /*@ public invariant (!telgopor & chico) ==> maxSabores<=maxSaboresCucuruchoChico @*/
  /*@ public invariant (!telgopor & grande) ==> maxSabores<=maxSaboresCucuruchoGrande @*/	

  private /*@ spec_public @*/ int maxSabores;

  private /*@ spec_public @*/ int cantSabores;


  public Helado (boolean t, boolean c, boolean g){
    telgopor = t;
    asignarTamanio(c, g);
    establecerLimiteSabores();
    cantSabores = 0;
    // < ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  /*@ public normal_behavior
    @ requires c == true;
    @ ensures chico == true;
    @ ensures grande == false;
    @
    @ also
    @
    @ public normal_behavior
    @ requires g == true;
    @ ensures chico == false;
    @ assignable \nothing;
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
