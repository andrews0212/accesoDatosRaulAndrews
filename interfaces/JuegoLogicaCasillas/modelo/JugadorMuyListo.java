package modelo;

import java.util.ArrayList;

import javax.swing.JButton;

public class JugadorMuyListo implements Runnable {
	private Tablero tablero;

	public JugadorMuyListo(Tablero tab) {
		tablero = tab;
	}

	@Override
	public void run() {
		int tamaño = tablero.getTamaño();
		while (tablero.getContadorCasillas() < tablero.getDimension()) {
			ArrayList<Coordenada> coordenadas = Coordenada.getTablero(tamaño);
			ArrayList<JButton> ultimosBotones = new ArrayList<JButton>();
			pintar(coordenadas, 5, ultimosBotones, 0);
		}
	}

	private boolean pintar(ArrayList<Coordenada> coordenadas, int ronda,ArrayList<JButton> ultimosBotones, int intentos) {
		for (Coordenada coordenada : coordenadas) {
			int aciertos = 0;
			intentos++;
			for (Coordenada cor : coordenada.getAlrededores()) {
				// se podria quitar pero da miedete
				if (tablero.getCoordenadaCasilla().containsKey(cor)) {
					if (!tablero.getCoordenadaCasilla().get(cor).isPintada()) {
						aciertos++;
					} else {
						aciertos--;
					}
				}

			}
			if (aciertos == ronda ) {
				if(!(ultimosBotones.contains(tablero.getCoordenadaCasilla().get(coordenada).getBoton()))) {
					tablero.getCoordenadaCasilla().get(coordenada).getBoton().doClick();
					if(ultimosBotones.size()>2) {
						ultimosBotones.remove(ultimosBotones.get(0));
						}
						ultimosBotones.add(tablero.getCoordenadaCasilla().get(coordenada).getBoton());
						return pintar(coordenadas, 5,ultimosBotones, intentos);
				}else {
					Coordenada aleatoria = Coordenada.aleatoria(tablero.getTamaño());
					while(tablero.getCoordenadaCasilla().get(aleatoria).isPintada()) {
						aleatoria = Coordenada.aleatoria(tablero.getTamaño());
					}
					tablero.getCoordenadaCasilla().get(aleatoria).getBoton().doClick();
					if(ultimosBotones.size()>2) {
						ultimosBotones.remove(ultimosBotones.get(0));
						}
						ultimosBotones.add(tablero.getCoordenadaCasilla().get(aleatoria).getBoton());
						return pintar(coordenadas, 5,ultimosBotones, intentos);
					
				}
			}
		}
		int nuevaRonda = ronda-1;
		return pintar(coordenadas, nuevaRonda,ultimosBotones, intentos);
	}

}