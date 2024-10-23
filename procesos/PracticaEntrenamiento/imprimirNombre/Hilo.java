package imprimirNombre;

public class Hilo implements Runnable{
	private String nombre;
	public Hilo(String nombre) {
		this.nombre=nombre;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(nombre);
		}
	}
}