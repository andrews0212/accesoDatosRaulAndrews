package dto;

import java.sql.Date;
import java.util.ArrayList;

public class Prestamo {
    /*
     * CREATE TABLE IF NOT EXISTS Prestamo ( 
     * id INT PRIMARY KEY AUTO_INCREMENT,
     * fechaInicio DATE NOT NULL, 
     * fechaFin DATE NOT NULL, 
     * usuarioId INT NOT NULL,
     * libroId INT NOT NULL, 
     * FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE,
     * FOREIGN KEY (libroId) REFERENCES Libro(id) ON DELETE CASCADE 
     * );
     */

    private Date fechaInicio;
    private Date fechaFin;
    private Integer usuarioId;
    private Integer libroId;
    private ArrayList<String> datos = new ArrayList<>();

    public Prestamo(Date fechaInicio, Date fechaFin, Integer usuarioId, Integer libroId) {
        try {
			if (fechaInicio == null || fechaFin == null) {
			    throw new Exception("Error: fechaInicio y fechaFin no pueden ser nulas.");
			}
			if (fechaInicio.after(fechaFin)) {
			    throw new Exception("Error: fechaInicio debe ser anterior a fechaFin.");
			}
			if (usuarioId == null || usuarioId <= 0) {
			    throw new Exception("Error de usuarioId: debe ser mayor que 0.");
			}
			if (libroId == null || libroId <= 0) {
			    throw new Exception("Error de libroId: debe ser mayor que 0.");
			}
		
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.libroId = libroId;

        // Solo agregar a datos si la validación tiene éxito
        datos.add(this.fechaInicio+"");
        datos.add(this.fechaFin+"");
        datos.add(this.usuarioId+"");
        datos.add(this.libroId+"");
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del Libro: " + e.getMessage());
		}

    }

    // METODO PARA INSTANCIAR UN PRESTAMO A PARTIR DE UN ARRAY
    // EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID (AUTOINCREMENT)
    public static Prestamo objetoArray(ArrayList<String> datos) {
        return new Prestamo(
            Date.valueOf(datos.get(1)), 
            Date.valueOf(datos.get(2)),
            Integer.parseInt(datos.get(3)), 
            Integer.parseInt(datos.get(4))
        );
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public ArrayList<String> getDatos() {
        return datos;// Devolver una copia de la lista de datos
    }
}
