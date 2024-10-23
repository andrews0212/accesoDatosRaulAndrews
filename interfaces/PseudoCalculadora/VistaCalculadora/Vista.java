package VistaCalculadora;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Vista extends JFrame {
	
	//DECLARACION DE ELEMENTOS GRAFICOS
	private JPanel border = new JPanel(new BorderLayout()); 
	private JPanel norte = new JPanel();
	private JPanel sur = new JPanel();
	private JPanel este = new JPanel(new FlowLayout());
	private JPanel oeste = new JPanel(new FlowLayout());
	private JPanel centro = new JPanel();
	private JTextArea texto = new JTextArea("Introduce los operandos");
	private JTextField valor1 = new JTextField("Operando");
	private JTextField valor2 = new JTextField("Operando");
	private JRadioButton sumar = new JRadioButton("Sumar");
	private JRadioButton restar = new JRadioButton("Restar");
	private JRadioButton multiplicar = new JRadioButton("Multiplicar");
	private JRadioButton dividir = new JRadioButton("Dividir");
	private ButtonGroup grupo = new ButtonGroup();
	private JTextArea resultado = new JTextArea("resultado");
	private JButton calcular = new JButton("Calcular");
	//ESTRUCTURA DE ELEMENTOS GRAFICOS
	public Vista() {
		setTitle("Calculadora");
		setSize(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		texto.setEditable(false);
		border.add(norte,BorderLayout.NORTH);
		border.add(sur, BorderLayout.SOUTH);
		border.add(este, BorderLayout.EAST);
		border.add(oeste, BorderLayout.WEST);
		border.add(centro, BorderLayout.CENTER);
		norte.add(texto);
		centro.add(valor1);
		centro.add(valor2);
		grupo.add(sumar);
		grupo.add(restar);
		grupo.add(multiplicar);
		grupo.add(dividir);
		oeste.add(sumar);
		oeste.add(restar);
		este.add(multiplicar);
		este.add(dividir);
		sur.add(calcular);
		resultado.setLineWrap(true);
		centro.add(resultado);
		add(border);
		
		//CONFIGURACION ESTÉTICA
		este.setLayout(new BoxLayout(este, BoxLayout.Y_AXIS));
		oeste.setLayout(new BoxLayout(oeste, BoxLayout.Y_AXIS));
		
	}
	
	//Esto es gráfico? MENSAJE DE ERROR
	public static void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	
	//GETTERS AND SETTERS
	public JTextArea getTexto() {
		return texto;
	}

	public void setTexto(JTextArea texto) {
		this.texto = texto;
	}

	public JTextField getValor1() {
		return valor1;
	}

	public void setValor1(JTextField valor1) {
		this.valor1 = valor1;
	}

	public JTextField getValor2() {
		return valor2;
	}

	public void setValor2(JTextField valor2) {
		this.valor2 = valor2;
	}

	public JRadioButton getSumar() {
		return sumar;
	}

	public void setSumar(JRadioButton sumar) {
		this.sumar = sumar;
	}

	public JRadioButton getRestar() {
		return restar;
	}

	public void setRestar(JRadioButton restar) {
		this.restar = restar;
	}

	public JRadioButton getMultiplicar() {
		return multiplicar;
	}

	public void setMultiplicar(JRadioButton multiplicar) {
		this.multiplicar = multiplicar;
	}

	public JRadioButton getDividir() {
		return dividir;
	}

	public void setDividir(JRadioButton dividir) {
		this.dividir = dividir;
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
