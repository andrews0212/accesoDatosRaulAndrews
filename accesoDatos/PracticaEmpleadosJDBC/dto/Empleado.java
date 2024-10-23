package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//CREATE TABLE empleado (
//	    ID INT AUTO_INCREMENT PRIMARY KEY,
//	    DNI VARCHAR(9) NOT NULL, 
//	    Nombre VARCHAR(50) NOT NULL,
//	    Apellido VARCHAR(50) NOT NULL,
//	    Telefono VARCHAR(15) NOT NULL,
//	    Salario DOUBLE NOT NULL,
//	    departamento_id INT, -- Clave foránea que hace referencia a departamento
//	    CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(ID)
//	    ON DELETE CASCADE -- Si el departamento se elimina, los empleados asociados también se eliminan
//	);
public class Empleado {
	private String dni;
	private String nombre;
	private String apellido;
	private String telefono;
	private double salario;
	private int departamento;
	private ArrayList<String> datos = new ArrayList<String>();

	public Empleado(String dni, String nombre, String apellido, String telefono, double salario, int departamento) {

		try {
			String validadorDni = "^[0-9]{8}[A-Z]$";
			Pattern myPatternDni = Pattern.compile(validadorDni);
			Matcher myMatcherDni = myPatternDni.matcher(dni);
			if (myMatcherDni.matches())
				this.dni = dni;
			else
				throw new Exception("Error de DNI");

			String validadorNombre = "^[A-Z][a-záéíóú]{1,15}( [A-Z]?[a-záéíóú]{1,15})?$";
			Pattern myPatternNombre = Pattern.compile(validadorNombre);
			Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
			if (myMatcherNombre.matches())
				this.nombre = nombre;
			else
				throw new Exception("Error de Nombre");
			if (myMatcherNombre.matches())
				this.apellido = apellido;
			else
				throw new Exception("Error de Apellido");

			this.salario = salario;

			String validadorTelefono = "^[0-9]{9}$";
			Pattern myPatternTelefono = Pattern.compile(validadorTelefono);
			Matcher myMatcherTelefono = myPatternTelefono.matcher(telefono);
			if (myMatcherTelefono.matches())
				this.telefono = telefono;
			else
				throw new Exception("Error de Telefono "+telefono);
			try {
				this.departamento = departamento;
			} catch (Exception e) {
				System.out.println("Error de departamento");
			}
			datos.add(this.dni);
			datos.add(this.nombre);
			datos.add(this.apellido);
			datos.add(this.telefono);
			datos.add(this.salario + "");
			datos.add(this.departamento + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en la validación de datos del empleado: " + e.getMessage());
		}
	}
	public static Empleado objetoArray(ArrayList<String> datos) {
		Empleado empleado = new Empleado(datos.get(1), datos.get(2), datos.get(3),datos.get(4) ,Double.parseDouble(datos.get(5)) ,Integer.parseInt(datos.get(6)));
		return empleado;
	}
	public String getDni() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public double getSalario() {
		return salario;
	}
	public String getTelefono() {
		return telefono;
	}
	public int getDepartamento() {
		return departamento;
	}
	public ArrayList<String> getDatos() {
		return datos;
	}
	
}
