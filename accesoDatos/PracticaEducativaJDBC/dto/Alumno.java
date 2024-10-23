package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//CREATE TABLE Alumno (
//	    ID INT AUTO_INCREMENT PRIMARY KEY,
//	    Nombre VARCHAR(100) NOT NULL,
//	    FechaNacimiento DATE NOT NULL,
//	    Telefono VARCHAR(15),
//	    Direccion VARCHAR(255)
//	);

public class Alumno {
	private String nombre;
	private Date nacimiento;
	private String telefono;
	private String direccion;
	private ArrayList<String> datos = new ArrayList<String>();

	public Alumno(String nombre, Date nacimiento, String telefono, String direccion) {
		try {
			String validadorNombre = "^[A-Z][a-záéíóúñ]{1,15} [A-Z][a-záéíóúñ]{1,15}$";
			Pattern myPatternNombre = Pattern.compile(validadorNombre);
			Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
			if (myMatcherNombre.matches())
				this.nombre = nombre;
			else
				throw new Exception("Error de nombre " + nombre);
			this.nacimiento = nacimiento;
			String validadorTelefono = "^[0-9]{9}$";
			Pattern myPatternTelefono = Pattern.compile(validadorTelefono);
			Matcher myMatcherTelefono = myPatternTelefono.matcher(telefono);
			if (myMatcherTelefono.matches())
				this.telefono = telefono;
			else
				throw new Exception("Error de Telefono");
			if(!direccion.contains(";")) {
				this.direccion=direccion;
			}
			datos.add(this.nombre);
			datos.add(this.nacimiento + "");
			datos.add(this.telefono);
			datos.add(this.direccion + "");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del alumno: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN REGISTRO A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID

	public static Alumno objetoArray(ArrayList<String> datos) {
		Alumno alumno = new Alumno(datos.get(1), Date.valueOf(datos.get(2)), datos.get(3), datos.get(4));
		return alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}

}
