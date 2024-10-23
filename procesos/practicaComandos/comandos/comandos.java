package comandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


//CLASE UTILITARIA QUE GESTIONA LA COMUNICACIÓN CON EL SISTEMA

public class comandos {
	private comandos() {
		
	}
	//REALIZA UN PING TANTO EN LINUX COMO WINDOWS
	public static void ping(String url) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("ping", url);
		Process process = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null && i < 10) {
			System.out.println(line); // Imprimir la salida del ping
			i++;
		}
	}
	//CONSULTAR SYSTEMA OPERATIVO
	//COMANDOS LINUX
	//LISTAR DIRECTORIOS 
	
	public static void listarDirectorioLinux(String ruta) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("ls");
        pb.directory(new File(ruta));
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	public static void listarDirectorioLinux() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("ls");  
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	
	//CONSULTAR Y CONCLUIR PROCESOS
	
	public static void consultarProcesosLinux() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("ps", "aux");
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	
	public static void concluirProcesosLinux(String pid) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("kill", pid);
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	
	//ABRIR APLICACIONES
	
	
	public static void abrirAplicacionLinux(String ruta,String aplicacion) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(aplicacion);
		pb.directory(new File(ruta));
        pb.start();
	}
	
	//COMANDOS WINDOWS
	//LISTAR DIRECTORIOS
	
	public static void listarDirectorioWindows() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","dir");
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	public static void listarDirectorioWindows(String ruta) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","dir");
        pb.directory(new File(ruta));
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	
	//CONSULTAR PROCESOS
	
	public static void consultarProcesosWindows() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","tasklist");
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
			//if(line.contains("MySQL80"))
				System.out.println(line);
	}
	public static void concluirProcesosWindows(String pid) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","taskkill /PID "+pid);
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	public static void iniciarServicioWindows(String servicio) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","net start "+servicio);
		pb.redirectErrorStream(true);
		Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
        line=br.readLine();
        if(line.equals("Error de sistema 5.")) {
        	System.out.println(line+"\nServicio "+servicio+" no iniciado quizá deba ejecutar como administrador");
        }else {
        	System.out.println(line);
        }
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
	public static void consultarServiciosWindows() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C","net start");
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
			System.out.println(line);
	}
	public static void abrirAplicacionWindows(String ruta, String aplicacion) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/C",aplicacion);
        pb.directory(new File(ruta));
        Process proceso = pb.start();
        BufferedReader br= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String line;
		while((line=br.readLine())!=null)
        System.out.println(line);
	}
}
	