package procesos;

import java.io.IOException;
import java.util.Scanner;

public class nieto {
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Iniciando proceso nieto");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("El proceso nieto recibió: "+ numero);
		numero= numero+10;
		System.out.println("El resultado final es: "+numero);
		System.out.println("Nieto finalizado");
		entrada.close();
	}
}
