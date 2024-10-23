package dto;

import java.util.ArrayList;

public class Libro {
//	-- Crear tabla Libro
//	CREATE TABLE IF NOT EXISTS Libro (
//	    id INT PRIMARY KEY AUTO_INCREMENT,
//	    titulo VARCHAR(100) NOT NULL,
//	    isbn VARCHAR(20) NOT NULL
//	);
	private String titulo;
	private String isbn;
	private ArrayList<String> datos = new ArrayList<String>();
	//VALIDADOR UTIL PARA EVITAR LA INYECCION
	public Libro(String titulo, String isbn) {
		try {
			if (titulo.matches("[a-zA-Z0-9\\s,.!?ñÑáéíóúÁÉÍÓÚ']+"))
				this.titulo = titulo;
			else
				throw new Exception("Error de titulo " + titulo);

			if (isbn.matches("^(978|979)?-?\\d{1,5}-?\\d{1,7}-?\\d{1,7}-?[0-9X]$"))
				this.isbn = isbn;
			else
				throw new Exception("Error de ISBN " + isbn);

			datos.add(this.titulo);
			datos.add(this.isbn);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del Libro: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID (AUTOINCREMENT)

	public static Libro objetoArray(ArrayList<String> datos) {
		Libro objeto = new Libro(datos.get(1),datos.get(2));
		return objeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}

}
