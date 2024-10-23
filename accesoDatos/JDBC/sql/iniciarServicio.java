package sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class iniciarServicio {
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Indica el servicio a iniciar");
		String servicio = entrada.nextLine();
		String sistema = System.getProperty("os.name").toLowerCase();
		ProcessBuilder pb;
		Process proceso;
		if (sistema.contains("win")) {
			pb = new ProcessBuilder("cmd", "/C", "net start " + servicio);
			pb.redirectErrorStream(true);
			proceso = pb.start();
		} else {
			pb = new ProcessBuilder("sudo", "-S", "systemctl", "start", servicio);
			pb.redirectErrorStream(true);
			proceso = pb.start();

		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
		String line;
		line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			if (line.equals("Error de sistema 5.")) {
				System.out.println(line + "\nServicio"+servicio+"no iniciado quizá deba ejecutar como administrador");
			}
			if (line.contains("password") || line.contains("contraseña")) {	
				String contrasena = entrada.nextLine();
				writer.write(contrasena);
				writer.newLine();
				writer.flush();		
			}
			line = reader.readLine();
		}
		writer.close();
		entrada.close();
	}
}
