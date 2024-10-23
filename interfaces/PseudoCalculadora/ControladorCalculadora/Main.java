package ControladorCalculadora;

import javax.swing.SwingUtilities;

import VistaCalculadora.Vista;
//Trabajo realizado por Raúl Mora
public class Main {
	public static void main(String[] args) {
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista);
		SwingUtilities.invokeLater(() -> {
			vista.setVisible(true);
		});
	}
	
}
