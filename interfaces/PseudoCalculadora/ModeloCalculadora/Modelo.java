package ModeloCalculadora;
import VistaCalculadora.Vista;
//La clase será utilitaria ya que solo cuenta con un método y no es necesario instanciarla
public class Modelo {
	//Creamos un constructor privado para evitar instancias no deseadas
	private Modelo() {
		
	}
	//Metodo para el cálculo de operaciones 
	public static String calcular(double valor1, double valor2, String operacion) {
		
		switch (operacion) {
		case "sumar": {
			return (valor1+valor2)+"";
		}
		case "restar": {
			return (valor1-valor2)+"";
		}
		case "multiplicar": {
			return (valor1*valor2)+"";
		}
		case "dividir": {
			if(valor2==0)
				Vista.mostrarMensaje("No se puede dividir por 0");
			else
				return (valor1/valor2)+"";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
		return "";
	}
}
