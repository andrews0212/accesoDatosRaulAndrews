package controladorJuegoCasillas;

import javax.swing.SwingUtilities;

import modelo.Jugador;
import modelo.JugadorListo;
import modelo.JugadorMuyListo;
import vistaJuegoLogica.Vista;

public class Main {
	public static void main(String[] args) {
		//Vista Usuario
//		Vista vistaUsuario = new Vista(20);
//		new ControladorJuegoLogica(vistaUsuario);
//		SwingUtilities.invokeLater(() -> {
//			vistaUsuario.setVisible(true);
//		});
		
		//Vista Jugador Aleatorio:
		
//		Vista vistaAleatorio = new Vista(3);
//		new ControladorJuegoLogica(vistaAleatorio);
//		SwingUtilities.invokeLater(() -> {
//			vistaAleatorio.setVisible(true);
//		});
//		Jugador jugadorAleatorio = new Jugador(vistaAleatorio.getTablero());
//		jugadorAleatorio.run();
		
//		Vista Jugador Listo:
		
//		Vista vistaListo = new Vista(3);
//		new ControladorJuegoLogica(vistaListo);
//		SwingUtilities.invokeLater(() -> {
//			vistaListo.setVisible(true);
//		});
//		JugadorListo jugadorListo = new JugadorListo(vistaListo.getTablero());
//		jugadorListo.run();
		
		//Vista aun mas listo
		
		Vista vistaMuyListo = new Vista(20);
		new ControladorJuegoLogica(vistaMuyListo);
		SwingUtilities.invokeLater(() -> {
			vistaMuyListo.setVisible(true);
		});
		JugadorMuyListo jugadorMuyListo = new JugadorMuyListo(vistaMuyListo.getTablero());
		jugadorMuyListo.run();
	}
}
