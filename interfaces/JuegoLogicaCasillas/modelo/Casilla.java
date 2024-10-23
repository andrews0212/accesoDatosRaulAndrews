package modelo;

import javax.swing.JButton;

public class Casilla {
	private JButton boton;
	private Coordenada coordenada;
	private boolean pintada;
	public Casilla(Coordenada coordenada, JButton boton) {
		this.coordenada=coordenada;
		pintada=false;
		this.boton = boton;
	}
	public void invertir() {
		if(pintada)
			pintada=false;
		else
			pintada=true;
	}
	public JButton getBoton() {
		return boton;
	}
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public boolean isPintada() {
		return pintada;
	}
	public void setPintada(boolean v) {
		pintada=v;
	}
	
}
