package comandos;


import java.io.IOException;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws IOException, InterruptedException {
		boolean salir = false;
		String os = System.getProperty("os.name").toLowerCase();
		boolean windows = false;
		if (os.contains("win"))
			windows = true;
		if (windows) {
			while (!salir) {
				System.out.println(
						"\n1.Hacer ping\n2.Consultar directorio\n3.Consultar procesos\n4.Concluir proceso\n5.Abrir aplicación\n6.Salir");
				Scanner entrada = new Scanner(System.in);
				String opcion = entrada.nextLine();

				switch (opcion) {
				case "1": {
					System.out.println("Escribe URL");
					comandos.ping(entrada.nextLine());
					break;
				}
				case "2": {
					System.out.println("Escribe ruta");
					try {
						comandos.listarDirectorioWindows(entrada.nextLine());
					} catch (Exception e) {
						// TODO: handle exception
					}			
					break;
				}
				case "3": {
					comandos.consultarProcesosWindows();
					break;
				}
				case "4": {
					System.out.println("Escribe PID");
					comandos.concluirProcesosWindows(entrada.nextLine());
					break;
				}
				case "5": {
					System.out.println("Escribe la ruta de la aplicación");
					String ruta = entrada.nextLine();
					System.out.println("Escribe el nombre de la aplicación");
					comandos.abrirAplicacionWindows(ruta, entrada.nextLine());
					break;
				}
				case "6": {
					salir = true;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + entrada);
				}
			}
		}else {
			while (!salir) {
				System.out.println(
						"\n1.Hacer ping\n2.Consultar directorio\n3.Consultar procesos\n4.Concluir proceso\n5.Abrir aplicación\n6.Salir");
				Scanner entrada = new Scanner(System.in);
				String opcion = entrada.nextLine();

				switch (opcion) {
				case "1": {
					System.out.println("Escribe URL");
					comandos.ping(entrada.nextLine());
					break;
				}
				case "2": {
					System.out.println("Escribe ruta");
					try {
						comandos.listarDirectorioLinux(entrada.nextLine());
					} catch (Exception e) {
						// TODO: handle exception
					}			
					break;
				}
				case "3": {
					comandos.consultarProcesosLinux();
					break;
				}
				case "4": {
					System.out.println("Escribe PID");
					comandos.concluirProcesosLinux(entrada.nextLine());
					break;
				}
				case "5": {
					System.out.println("Escribe la ruta de la aplicación");
					String ruta = entrada.nextLine();
					System.out.println("Escribe el nombre de la aplicación");
					comandos.abrirAplicacionLinux(ruta, entrada.nextLine());
					break;
				}
				case "6": {
					salir = true;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + entrada);
				}
			}
		}
	}

}
