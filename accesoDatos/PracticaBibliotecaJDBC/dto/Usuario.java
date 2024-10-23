package dto;

import java.util.ArrayList;

public class Usuario {
	// CREATE TABLE IF NOT EXISTS Usuario (
	//     id INT PRIMARY KEY AUTO_INCREMENT,
	//     nombre VARCHAR(100) NOT NULL
	// );

	private String nombre;
	private ArrayList<String> datos = new ArrayList<String>();

	public Usuario(String nombre) {
		try {
			if (nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[\\s\\-]?[A-Za-zÁÉÍÓÚáéíóúÑñ]*)*$"))
				this.nombre = nombre;
			else
				throw new Exception("Error de nombre " + nombre);

			datos.add(this.nombre);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del usuario: " + e.getMessage());
		}
	}

	// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
	// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID (AUTOINCREMENT)
	public static Usuario objetoArray(ArrayList<String> datos) {
		Usuario objeto = new Usuario(datos.get(1));
		return objeto;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}
}
