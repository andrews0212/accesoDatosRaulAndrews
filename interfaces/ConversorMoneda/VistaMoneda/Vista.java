package VistaMoneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Vista extends JFrame {
	
	//DECLARACION DE ELEMENTOS GRAFICOS
	private JPanel panel = new JPanel();
	private JTextArea texto = new JTextArea("Introduce un valor a convertir");
	private JTextField valorInicial = new JTextField("valor", 5);
	private JRadioButton euroDolar = new JRadioButton("Euro -> Dolar");
	private JRadioButton dolarEuro = new JRadioButton("Dolar -> Euro");
	private ButtonGroup grupo = new ButtonGroup();
	private JTextArea resultado = new JTextArea("resultado");
	private JButton calcular = new JButton("Calcular");
	//CONFIGURACION DE ELEMENTOS GRAFICOS
	public Vista() {
		setTitle("Mi Ventana Swing");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		texto.setEditable(false);
		panel.add(texto);
		panel.add(valorInicial);
		grupo.add(euroDolar);
		grupo.add(dolarEuro);
		panel.add(euroDolar);
		panel.add(dolarEuro);
		panel.add(calcular);
		resultado.setLineWrap(true);
		panel.add(resultado);
		add(panel);

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextArea getTexto() {
		return texto;
	}

	public void setTexto(JTextArea texto) {
		this.texto = texto;
	}

	public JTextField getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(JTextField valorInicial) {
		this.valorInicial = valorInicial;
	}

	public JRadioButton getEuroDolar() {
		return euroDolar;
	}

	public void setEuroDolar(JRadioButton euroDolar) {
		this.euroDolar = euroDolar;
	}

	public JRadioButton getDolarEuro() {
		return dolarEuro;
	}

	public void setDolarEuro(JRadioButton dolarEuro) {
		this.dolarEuro = dolarEuro;
	}

	public ButtonGroup getGrupo() {
		return grupo;
	}

	public void setGrupo(ButtonGroup grupo) {
		this.grupo = grupo;
	}

	public JTextArea getResultado() {
		return resultado;
	}

	public void setResultado(JTextArea resultado) {
		this.resultado = resultado;
	}

	public JButton getCalcular() {
		return calcular;
	}

	public void setCalcular(JButton calcular) {
		this.calcular = calcular;
	}

}
