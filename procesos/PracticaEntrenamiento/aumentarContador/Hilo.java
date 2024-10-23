package aumentarContador;

public class Hilo implements Runnable{
private static int contador=0;
private int indice; 
	public Hilo(int indice) {
		this.indice=indice;
	}
	@Override
	public void run() {
		while(Hilo.contador<99) {
		aumentarContador(indice);
		}
	}
	public static synchronized void aumentarContador(int indice) {
		contador=contador+1;
		System.out.println("El hilo "+indice+" a aumentado el contador a "+contador);
	}
}
