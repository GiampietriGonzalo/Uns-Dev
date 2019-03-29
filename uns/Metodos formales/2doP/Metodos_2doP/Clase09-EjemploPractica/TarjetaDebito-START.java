public class TarjetaDebito {

    private /*@ spec_public @*/ int NumeroPin;

    private /*@ spec_public @*/ int CantAccesosIncorrectos;
    /*@ public invariant (CantAccesosIncorrectos <= 3); @*/

    private /*@ spec_public @*/ boolean TarjetaBloqueada;
    /*@ public invariant TarjetaBloqueada <==> (CantAccesosIncorrectos == 3); @*/

    private /*@ spec_public @*/ int Saldo;
    /*@ public invariant Saldo >= 0; @*/

    public TarjetaDebito (int Pin) {
        NumeroPin = Pin;
        CantAccesosIncorrectos = 0;
        TarjetaBloqueada = false;
        Saldo = 0;
    }

    /*@ public normal_behavior
      @ requires Pin == NumeroPin;
      @ ensures \result == true;
      @
      @ also
      @
      @ public normal_behavior
      @ ensures \result == false;
      @ ensures CantAccesosIncorrectos == \old(CantAccesosIncorrectos) + 1;
      @*/
    public boolean AccesoValido (int Pin) {
        if (!TarjetaBloqueada) {
            if (Pin == NumeroPin)
                return true;
            else {
                CantAccesosIncorrectos++;
                return false;
            }
        }
        else return false;
    }

    /*@ public normal_behavior
      @ requires true;
      @ ensures Saldo == \old(Saldo) - Monto;
      @
      @ also
      @
      @ public normal_behavior
      @ requires true;
      @ ensures Saldo == \old(Saldo);
      @ assignable \nothing;
      @ */
    public void ExtraerDinero (int Pin, int Monto){
        if (AccesoValido(Pin))
          Saldo = Saldo - Monto;
    }

}
