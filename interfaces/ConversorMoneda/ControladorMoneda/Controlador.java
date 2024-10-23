package ControladorMoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModeloMoneda.Modelo;
import VistaMoneda.Vista;



public class Controlador {
	private Vista vista;

	public Controlador(Vista vista2) {
		this.vista = vista2;
		ActionListener listenerBoton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String conversion = null;
				if (vista2.getEuroDolar().isSelected())
					conversion = "euroDolar";
				if (vista2.getDolarEuro().isSelected())
					conversion = "dolarEuro";
				vista2.getResultado()
						.setText(Modelo.conversor(Double.parseDouble(vista2.getValorInicial().getText()), conversion));

			}
		};
		vista2.getCalcular().addActionListener(listenerBoton);

	};
}
