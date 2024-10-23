package ControladorCalculadora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModeloCalculadora.Modelo;
import VistaCalculadora.Vista;

public class Controlador {
	private Vista vista;

	public Controlador(Vista vista) {
		this.vista = vista;
		ActionListener listenerBoton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getSumar().isSelected()) {//SUMA
					vista.getResultado().setText(Modelo.calcular(Double.parseDouble(vista.getValor1().getText()),
							Double.parseDouble(vista.getValor2().getText()), "sumar"));
				}else if (vista.getRestar().isSelected()) {//RESTA
					vista.getResultado().setText(Modelo.calcular(Double.parseDouble(vista.getValor1().getText()),
							Double.parseDouble(vista.getValor2().getText()), "restar"));
				}else if (vista.getMultiplicar().isSelected()) {//MULTIPLICACION
					vista.getResultado().setText(Modelo.calcular(Double.parseDouble(vista.getValor1().getText()),
							Double.parseDouble(vista.getValor2().getText()), "multiplicar"));
				}else if (vista.getDividir().isSelected()) {//DIVISION
					vista.getResultado().setText(Modelo.calcular(Double.parseDouble(vista.getValor1().getText()),
							Double.parseDouble(vista.getValor2().getText()), "dividir"));
				}else {
					Vista.mostrarMensaje("Ninguna operación seleccionada");
				}
			}
		};
		//AÑADIMOS EL LISTENER AL BOTON
		vista.getCalcular().addActionListener(listenerBoton);

	}
}
