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
      @ requires TarjetaBloqueada == false;
      @ ensures \result == true;
      @ assignable \nothing;
      @
      @ also
      @
      @ public normal_behavior
      @ requires TarjetaBloqueada;
      @ ensures \result == false;
      @ assignable \nothing;
      @
      @ also
      @
      @ public normal_behavior
      @ requires Pin != NumeroPin;
      @ requires ! TarjetaBloqueada;
      @ requires CantAccesosIncorrectos < 2;
      @ ensures \result == false;
      @ ensures CantAccesosIncorrectos == \old(CantAccesosIncorrectos) + 1;
      @ assignable CantAccesosIncorrectos;
      @
      @ also
      @
      @ public normal_behavior
      @ requires Pin != NumeroPin;
      @ requires !TarjetaBloqueada;
      @ requires CantAccesosIncorrectos == 2;
      @ ensures \result == false;
      @ ensures CantAccesosIncorrectos == \old(CantAccesosIncorrectos) + 1;
      @ ensures TarjetaBloqueada == true;
      @ assignable CantAccesosIncorrectos, TarjetaBloqueada;
      @*/
    public boolean AccesoValido (int Pin) {
        if (!TarjetaBloqueada) {
            if (Pin == NumeroPin)
                return true;
            else {
                CantAccesosIncorrectos++;
                if (CantAccesosIncorrectos == 3)
                    TarjetaBloqueada = true;
                return false;
            }
        }
        else return false;
    }

    /*@ public normal_behavior
      @ requires AccesoValido(Pin) == true;
      @ requires Monto <= Saldo;
      @ ensures Saldo == \old(Saldo) - Monto;
      @ assignable Saldo;
      @
      @ also
      @
      @ public normal_behavior
      @ requires AccesoValido(Pin) == false;
      @ ensures Saldo == \old(Saldo);
      @ assignable TarjetaBloqueada, CantAccesosIncorrectos;
      @
      @ also
      @
      @ public normal_behavior
      @ requires Monto > Saldo;
      @ requires AccesoValido(Pin) == true;
      @ assignable \nothing;
      @ */
    public void ExtraerDinero (int Pin, int Monto){
        if (AccesoValido(Pin)  & Monto <= Saldo)
          Saldo = Saldo - Monto;
    }

}
