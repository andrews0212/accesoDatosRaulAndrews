package modelo;

public class JugadorListo implements Runnable {
	private Tablero tablero;

	public JugadorListo(Tablero tab) {
		tablero = tab;
	}

	@Override
	public void run() {
		int intentos = 0;
		int intentosTotales=0;
		while (tablero.getContadorCasillas() < tablero.getDimension()) {
			Coordenada cordenada = Coordenada.aleatoria(tablero.getTamaño());
			int aciertos = 0;
			for (Coordenada cor : cordenada.getAlrededores()) {
				if (tablero.getCoordenadaCasilla().containsKey(cor)) {
					if (!tablero.getCoordenadaCasilla().get(cor).isPintada()) {
						aciertos++;
					} else {
						aciertos--;
					}
				}
			}
			if (aciertos > 0) {
				tablero.getCoordenadaCasilla().get(cordenada).getBoton().doClick();
			} else {
				intentos++;
				if(intentos>tablero.getDimension()*1.5 && !tablero.getCoordenadaCasilla().get(cordenada).isPintada()) {
					tablero.getCoordenadaCasilla().get(cordenada).getBoton().doClick();
					intentosTotales = intentosTotales+intentos;
					intentos=0;
					System.out.println("Intentos totales: "+intentosTotales);
				}
			}
			aciertos = 0;
		}
	}

}
