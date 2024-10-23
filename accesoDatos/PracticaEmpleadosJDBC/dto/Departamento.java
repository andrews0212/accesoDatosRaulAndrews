package dto;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Departamento {
	private String nombre;
	private ArrayList<String> datos = new ArrayList<String>();
	public Departamento(String nombre) {
		try {
			String validadorNombre = "^[A-Z][a-záéíóú]{1,15}( [A-Z]?[a-záéíóú]{1,15})?$";
        	Pattern myPatternNombre = Pattern.compile(validadorNombre);
            Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
            if (myMatcherNombre.matches())
            	this.nombre = nombre;
            else
            	throw new Exception("Error de Nombre"+nombre);
            datos.add(this.nombre);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del departamento: " + e.getMessage());
		}
	}
	public static Departamento objetoArray(ArrayList<String> datos) {
		Departamento objeto = new Departamento(datos.get(1));
		return objeto;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<String> getDatos() {
		return datos;
	}
	
}
