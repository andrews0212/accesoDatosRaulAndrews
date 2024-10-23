package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Matricula {
	private int idAlumno;
	private int idAsignatura;
	private Date fecha;
	private ArrayList<String> datos = new ArrayList<String>();
	public Matricula(int idAlumno, int idAsignatura, Date fecha) {
		try {
			if(idAlumno>0) {
				this.idAlumno=idAlumno;
			}
			if(idAsignatura>0) {
				this.idAsignatura=idAsignatura;
			}
			this.fecha=fecha;
			datos.add(this.idAlumno+"");
			datos.add(this.idAsignatura+"");
			datos.add(this.fecha+"");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos de la matricula: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID

	public static Matricula objetoArray(ArrayList<String> datos) {
		Matricula objeto = new Matricula(Integer.parseInt(datos.get(0)), Integer.parseInt(datos.get(1)),Date.valueOf(datos.get(2)));
		return objeto;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public Date getFecha() {
		return fecha;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}


	
}