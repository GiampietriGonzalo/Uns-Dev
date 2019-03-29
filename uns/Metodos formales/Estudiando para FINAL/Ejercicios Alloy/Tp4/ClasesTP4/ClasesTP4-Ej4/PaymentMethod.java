public class PaymentMethod {

	private /*@ spec_public @*/ int MethodID;
	/*
	   Payment method association:
	   0 = Cash
	   1 = Debit Card
	   2 = Credit Card
	 */

	private /*@ spec_public @*/ static final int MAX_CASH = 500;
	private static final int MAX_DEBIT = 1000;
	private /*@ spec_public @*/ static final int MAX_CREDIT = 5000;

	public PaymentMethod(int Method) {
		this.MethodID = Method;
	}

	/*@ public normal_behavior
	  @ requires MethodID>=0 && MethodID<3;
	  @ ensures (\result >= MAX_CASH && \result <= MAX_CREDIT);
	  @
	  @ also
	  @
	  @ public exceptional_behavior
	  @ requires MethodID<0 || MethodID>2;
	  @ signals_only InvalidPaymentMethodExc;
	  @*/
	public int methodLimit() throws InvalidPaymentMethodExc {
		switch (MethodID) {
			case 0: return MAX_CASH;
			case 1: return MAX_DEBIT;
			case 2: return MAX_CREDIT;
			default: throw new InvalidPaymentMethodExc();
		}
	}
	// Retorna el valor limite asociado con el medio de pago, siempre y cuando este sea valido
	// <OBSERVACION!! ESTA PROHIBIDO MODIFICAR EL CODIGO DEL METODO methodLimit>
}
