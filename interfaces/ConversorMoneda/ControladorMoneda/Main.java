package ControladorMoneda;

import javax.swing.SwingUtilities;

import VistaMoneda.Vista;

public class Main {
	public static void main(String[] args) {
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista);
		SwingUtilities.invokeLater(() -> {
			vista.setVisible(true);
		});
	}
	
}
