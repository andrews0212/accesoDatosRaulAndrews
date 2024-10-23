package dto;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equipo {

	private String nombre;
	private String estadio;
	private ArrayList<String> datos = new ArrayList<String>();

	public Equipo(String nombre, String estadio) {

		try {
			String validadorNombre = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[\\s\\-]?[A-Za-zÁÉÍÓÚáéíóúÑñ]*)*$";
			Pattern myPatternNombre = Pattern.compile(validadorNombre);
			Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
			if (myMatcherNombre.matches())
				this.nombre = nombre;
			else
				throw new Exception("Error de nombre " + nombre);
			
			Matcher myMatcherEstadio = myPatternNombre.matcher(estadio);
			if (myMatcherEstadio.matches())
				this.estadio = estadio;
			else
				throw new Exception("Error de estadio " + estadio);
	
			datos.add(this.nombre);
			datos.add(this.estadio);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del equipo: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID

	public static Equipo equipoArray(ArrayList<String> datos) {
		Equipo equipo = new Equipo(datos.get(1), datos.get(2));
		return equipo;

	}

	public String getNombre() {
		return nombre;
	}

	public String getEstadio() {
		return estadio;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}
	


}
