package controladorJuegoCasillas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Casilla;
import modelo.Coordenada;
import vistaJuegoLogica.Vista;

public class ControladorJuegoLogica {
	public ControladorJuegoLogica(Vista v) {
	for (JButton boton : v.getTablero().getListaBotones()) {
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Coordenada centro = v.getTablero().getBotonCasilla().get(boton).getCoordenada();
				for (Coordenada cor : centro.getAlrededores()) {
					if (v.getTablero().getCoordenadaCasilla().containsKey(cor)) {
						if (v.getTablero().getCoordenadaCasilla().get(cor).isPintada()) {
							v.getTablero().getCoordenadaCasilla().get(cor).getBoton().setBackground(Color.gray);
							v.getTablero().setContadorCasillas(v.getTablero().getContadorCasillas()-1);
						} else {
							//v.getTablero().getCoordenadaCasilla().get(cor).getBoton().setBackground(Color.green);
							v.getTablero().getCoordenadaCasilla().get(cor).getBoton().setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
							v.getTablero().setContadorCasillas(v.getTablero().getContadorCasillas()+1);    ;
						}
						v.getTablero().getCoordenadaCasilla().get(cor).invertir();
					}
				}
				if(v.getTablero().getContadorCasillas()==v.getTablero().getDimension()) {
					JOptionPane.showMessageDialog(null, "Ganaste");
					for (Casilla casilla : v.getTablero().getBotonCasilla().values()) {
						casilla.getBoton().setBackground(Color.gray);
						casilla.setPintada(false);
					}
					v.getTablero().setContadorCasillas(0);
				}
			}
		});
	}
	}
}
