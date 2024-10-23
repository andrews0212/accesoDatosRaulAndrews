package vistaJuegoLogica;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.Coordenada;
import modelo.Tablero;

public class Vista extends JFrame {
	private Tablero tablero;
	public Vista(int tamaño) {
		tablero = new Tablero(tamaño);
		setTitle("Juego Lógica");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel tableroVisible = new JPanel();
		GridLayout layout = new GridLayout(tamaño, tamaño);
		tableroVisible.setLayout(layout);
		for (int y = 0; y < tamaño; y++) {
			for (int x = 0; x < tamaño; x++) {
				tableroVisible.add(tablero.getCoordenadaCasilla().get(new Coordenada(x, y)).getBoton());
			}
		}
		add(tableroVisible);
	}
	public Tablero getTablero() {
		return tablero;
	}
}
