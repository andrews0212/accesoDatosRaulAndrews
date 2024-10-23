package procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class padre {
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Iniciando proceso padre");
		System.out.println("Escribe un numero");
		int numero = Integer.parseInt(entrada.nextLine());
		File bin = new File("bin");
		String ruta = bin.getAbsolutePath();
		ProcessBuilder pb = new ProcessBuilder("java","-cp",ruta,"procesos.hijo");
		Process proceso = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream())); 
		String line = br.readLine();
		while(line!=null) {
			System.out.println(line);
			if(line.equals("Iniciando proceso hijo")) {
				bw.write(numero+"\n");
				bw.flush();
			}
			line = br.readLine();
		}
		br.close();
		bw.close();
		entrada.close();
		System.out.println("Padre finalizado");
	}
}
