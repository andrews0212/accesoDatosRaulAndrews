package aumentarContador;

public class Main2 {
	public static void main(String[] args) {
		new Thread(new Hilo(1)).start();
		new Thread(new Hilo(2)).start();	
	}
}
