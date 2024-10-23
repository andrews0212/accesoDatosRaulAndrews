package dto;

import java.util.ArrayList;

//-- Crear tabla Autor
// CREATE TABLE IF NOT EXISTS Autor (
// id INT PRIMARY KEY AUTO_INCREMENT,
// nombre VARCHAR(100) NOT NULL
// );

public class Autor {
    private String nombre;
    private ArrayList<String> datos = new ArrayList<>();

    public Autor(String nombre) {
        try {
			if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[\\s\\-]?[A-Za-zÁÉÍÓÚáéíóúÑñ]*)*$")) {
			    throw new Exception("Error de nombre " + nombre);
			}
			this.nombre = nombre;

			// Solo agregar a datos si la validación tiene éxito
			datos.add(this.nombre);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del Autor: " + e.getMessage());
		}
    }

    // METODO PARA INSTANCIAR UN AUTOR A PARTIR DE UN ARRAY
    // EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID (AUTOINCREMENT)
    public static Autor objetoArray(ArrayList<String> datos) {
        return new Autor(datos.get(1));
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getDatos() {
        return new ArrayList<>(datos); // Devolver una copia de la lista de datos
    }
}
