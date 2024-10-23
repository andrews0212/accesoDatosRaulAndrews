package modelo;

public class Jugador implements Runnable{
	private Tablero tablero;
	public Jugador(Tablero tab) {
		tablero=tab;
	}

	@Override
	public void run() {
		while(tablero.getContadorCasillas()<tablero.getDimension()) {
		Coordenada cor = Coordenada.aleatoria(tablero.getTamaño());
		tablero.getCoordenadaCasilla().get(cor).getBoton().doClick();
		}
	}

}
