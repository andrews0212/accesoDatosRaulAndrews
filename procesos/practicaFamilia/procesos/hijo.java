package procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class hijo {
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Iniciando proceso hijo");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("El proceso hijo recibió: "+ numero);
		numero= numero+10;
		File bin = new File("bin");
		String ruta = bin.getAbsolutePath();
		ProcessBuilder pb = new ProcessBuilder("java","-cp",ruta,"procesos.nieto");
		Process proceso = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream())); 
		String line = br.readLine();
		while(line!=null) {
			System.out.println(line);
			if(line.equals("Iniciando proceso nieto")) {
				bw.write(numero+"\n");
				bw.flush();
			}
			line = br.readLine();
		}
		br.close();
		bw.close();
		entrada.close();
		System.out.println("Hijo finalizado");
	}

}
