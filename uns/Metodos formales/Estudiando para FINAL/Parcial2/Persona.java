
public class Persona {

  private /*@ spec_public @*/ boolean femenino;
  private /*@ spec_public @*/ boolean masculino;

  private /*@ spec_public @*/ int edad;

  private /*@ spec_public @*/ boolean casada;

  private /*@ spec_public @*/ boolean contribuyente;

  private /*@ spec_public @*/ int salario;

  private /*@ spec_public @*/ static final int SALARIO_MINIMO_VITAL_MOVIL = 10000;

  private /*@ spec_public @*/ int montoTributario;

  private /*@ spec_public @*/ static final int MINIMO_NO_IMPONIBLE_BASE_SOLTERO = 15000;
  private /*@ spec_public @*/ static final int MINIMO_NO_IMPONIBLE_BASE_CASADO = 25000;


  public Persona (boolean f, boolean m, int e, boolean c, int s) {
    femenino = f;
    masculino = m;
    edad = e;
    casada = c;
    establecerSalario(s);
    montoTributario = obtenerMontoTributario();
    //< ESTA PROHIBIDO MODIFICAR EL CONSTRUCTOR >
  }


  private int obtenerMontoTributario(){
    if (contribuyente)
      if (casada)
        if (salario > MINIMO_NO_IMPONIBLE_BASE_CASADO)
          return salario - MINIMO_NO_IMPONIBLE_BASE_CASADO;
        else return 0;
      else  if (salario > MINIMO_NO_IMPONIBLE_BASE_SOLTERO)
              return salario - MINIMO_NO_IMPONIBLE_BASE_SOLTERO;
            else return 0;
    else return 0;
    //< ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }


  /*@ public normal_behavior
    @ requires contribuyente;
    @ ensures salario == s;
    @
    @ also
    @
    @ public normal_behavior
    @ requires !contribuyente;
    @ ensures salario == 0;
    @ assignable salario;
    @*/
  public void establecerSalario(int s){
    if (contribuyente)
      salario = s;
    else salario = 0;
    montoTributario = obtenerMontoTributario();
    //< ESTA PROHIBIDO MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }



  /*@ public normal_behavior
    @ requires true;
    @ ensures edad == \old(edad) + 1;
    @*/
  public void actualizarEdad() throws ExcepcionCambioEstadoTributario{
    edad++;
    //< SE PERMITE MODIFICAR LA IMPLEMENTACION DE ESTE METODO >
  }

}
