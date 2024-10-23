package modelo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero {
	private Map<JButton, Casilla> botonCasilla;
	private Map<Coordenada, Casilla> coordenadaCasilla;
	private int contadorCasillas=0;
	private int tamaño;
	private int dimension;
	private ArrayList<JButton> listaBotones;
	public Tablero(int tamaño) {
		this.tamaño=tamaño;
		dimension = tamaño * tamaño;
		botonCasilla= new HashMap<JButton, Casilla>();
		coordenadaCasilla= new HashMap<Coordenada, Casilla>();
		listaBotones = new ArrayList<JButton>();
		for (int x = 0; x < tamaño; x++) {
			for (int y = 0; y < tamaño; y++) {
				// CONFIGURACION DE BOTON
				JButton boton = new JButton();
				boton.setSize((int) (20 - dimension / 3), (int) (20 - dimension / 3));
				boton.setBackground(Color.gray);
				Coordenada coordenada = new Coordenada(x, y);
				Casilla casilla = new Casilla(coordenada, boton);
				botonCasilla.put(boton, casilla);
				coordenadaCasilla.put(coordenada, casilla);
				listaBotones.add(boton);
			}
		}
		
	}
	public Map<JButton, Casilla> getBotonCasilla() {
		return botonCasilla;
	}
	public Map<Coordenada, Casilla> getCoordenadaCasilla() {
		return coordenadaCasilla;
	}
	public int getContadorCasillas() {
		return contadorCasillas;
	}
	public int getTamaño() {
		return tamaño;
	}
	public int getDimension() {
		return dimension;
	}
	public ArrayList<JButton> getListaBotones() {
		return listaBotones;
	}
	public void setContadorCasillas(int contadorCasillas) {
		this.contadorCasillas = contadorCasillas;
	}
	
}
