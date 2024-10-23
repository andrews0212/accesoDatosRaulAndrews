package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		File clase = new File("bin");
		String ruta = clase.getAbsolutePath();
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", ruta, "controlador.generadorAleatorios");
		Process proceso = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
		String line;
		boolean salir = false;
			line = reader.readLine();
			//BUCLE QUE ACTUA SEGUN LA SALIDA DEL PROCESO HIJO
			while (line != null&&!salir) {
				System.out.println(line);
				if (line.contains("finalizado")) {
					salir = true;
				}else if(line.contains("Introduce")){
					String cadena = entrada.nextLine();
					writer.write(cadena+"\n");
					writer.flush();
				}
			line= reader.readLine();
			}
		System.out.println("Se ha finalizado el programa padre");
		entrada.close();
	}
}
