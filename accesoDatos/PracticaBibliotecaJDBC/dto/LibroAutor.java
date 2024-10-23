package dto;

import java.util.ArrayList;

public class LibroAutor {
    /*
    CREATE TABLE IF NOT EXISTS Libro_Autor (
        idLibro INT NOT NULL,
        idAutor INT NOT NULL,
        PRIMARY KEY (idLibro, idAutor),
        FOREIGN KEY (idLibro) REFERENCES Libro(id) ON DELETE CASCADE,
        FOREIGN KEY (idAutor) REFERENCES Autor(id) ON DELETE CASCADE
    );
    */

    private Integer idLibro;
    private Integer idAutor;
    private ArrayList<String> datos = new ArrayList<>();

    public LibroAutor(Integer idLibro, Integer idAutor)  {
        try {
			if (idLibro == null || idLibro <= 0) {
			    throw new Exception("Error de idLibro: debe ser mayor que 0.");
			}
			if (idAutor == null || idAutor <= 0) {
			    throw new Exception("Error de idAutor: debe ser mayor que 0.");
			}	
        this.idLibro = idLibro;
        this.idAutor = idAutor;

        // Solo agregar a datos si la validación tiene éxito
        datos.add(String.valueOf(this.idLibro));
        datos.add(String.valueOf(this.idAutor));
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // METODO PARA INSTANCIAR UN LIBRO-AUTOR A PARTIR DE UN ARRAY
    // EMPEZAMOS EN 0 YA QUE NO ES AUTOINCREMENT
    public static LibroAutor objetoArray(ArrayList<String> datos){
        return new LibroAutor(
            Integer.parseInt(datos.get(0)),
            Integer.parseInt(datos.get(1))
        );
    }

    public ArrayList<String> getDatos() {
        return new ArrayList<>(datos); // Devolver una copia de la lista de datos
    }
}