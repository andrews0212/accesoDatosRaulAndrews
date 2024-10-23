package dto;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//-- Crear la tabla Asignatura
//CREATE TABLE Asignatura (
//    ID INT AUTO_INCREMENT PRIMARY KEY,
//    Nombre VARCHAR(100) NOT NULL,
//    HorasSemanales INT NOT NULL

public class Asignatura {
	private String nombre;
	private int horasSemanales;
	private ArrayList<String> datos = new ArrayList<String>();
	public Asignatura(String nombre, int horasSemanales) {
		try {
			String validadorNombre = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[\\s\\-]?[A-Za-zÁÉÍÓÚáéíóúÑñ]*)*$";
			Pattern myPatternNombre = Pattern.compile(validadorNombre);
			Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
			if (myMatcherNombre.matches())
				this.nombre = nombre;
			else
				throw new Exception("Error de nombre " + nombre);
			if(horasSemanales>0) {
				this.horasSemanales=horasSemanales;
			}
			datos.add(this.nombre);
			datos.add(this.horasSemanales+"");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del equipo: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID

	public static Asignatura objetoArray(ArrayList<String> datos) {
		Asignatura objeto = new Asignatura(datos.get(1), Integer.parseInt(datos.get(2)));
		return objeto;
	}

	public String getNombre() {
		return nombre;
	}

	public int getHorasSemanales() {
		return horasSemanales;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}
	
}
