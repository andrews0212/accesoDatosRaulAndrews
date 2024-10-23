package ModeloMoneda;

public class Modelo {
	public static String conversor(double valorInicial, String conversion) {

		double euroDolar = 1.11;
		double dolarEuro = 0.9;
		double valorFinal = 0;
		try {
			if (conversion.equals("euroDolar"))
				valorFinal = valorInicial * euroDolar;
			else if (conversion.equals("dolarEuro"))
				valorFinal = valorInicial * dolarEuro;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valorFinal + "";
	}
}
