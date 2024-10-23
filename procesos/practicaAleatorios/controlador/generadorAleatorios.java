package controlador;

import java.util.Scanner;

public class generadorAleatorios {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			System.out.println("Introduce una cadena, fin para terminar");
			String cadena = entrada.nextLine();
			if (cadena.toLowerCase().equals("fin")) {
				salir = true;
			} else {
				for (int i = 0; i < cadena.length(); i++) {
					if (i == cadena.length() - 1) {
						System.out.println((int) (Math.random() * 10));
					} else {
						System.out.print((int) (Math.random() * 10));
					}
				}
			}
		}
		System.out.println("Se ha finalizado el programa hijo");
		entrada.close();
	}
}
