package dto;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jugador {
	private String nombre;
	private Float estatura;
	private Float peso;
	private Integer idEquipo;
	private ArrayList<String> datos = new ArrayList<String>();

	public Jugador(String nombre, Float estatura, Float peso, Integer idEquipo) {

		try {
			String validadorNombre = "^[A-Z][a-záéíóúñ]{1,15} [A-Z][a-záéíóúñ]{1,15}$";
			Pattern myPatternNombre = Pattern.compile(validadorNombre);
			Matcher myMatcherNombre = myPatternNombre.matcher(nombre);
			if (myMatcherNombre.matches())
				this.nombre = nombre;
			else
				throw new Exception("Error de nombre " + nombre);
			if (estatura > 0)
				this.estatura = estatura;
			else
				throw new Exception("Error de estatura " + estatura);
			if (peso > 0)
				this.peso = peso;
			else
				throw new Exception("Error de peso " + peso);
			if (idEquipo > 0)
				this.idEquipo = idEquipo;
			else
				throw new Exception("Error de Equipo");
			datos.add(this.nombre);
			datos.add(this.estatura + "");
			datos.add(this.peso + "");
			datos.add(this.idEquipo + "");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la validación de datos del jugador: " + e.getMessage());
		}

	}// METODO PARA INSTANCIAR UN JUGADOR A PARTIR DE UN ARRAY
		// EMPEZAMOS EN 1 YA QUE EL DAO OBVIA EL ID
	public static Jugador jugadorArray(ArrayList<String> datos) {
		Jugador jugador = new Jugador(datos.get(1), Float.parseFloat(datos.get(2)), Float.parseFloat(datos.get(3)),
				Integer.parseInt(datos.get(4)));
		return jugador;

	}
	// QUEDA INUTIL PORQUE LA ACTUALIZACION DE MEMORIA SE HARA VIA BBDD
	public boolean actualizar(Jugador jugador) {
		// habría que hacerlo con SETTERS PRIVADOS
		this.nombre = jugador.getNombre();
		this.estatura = jugador.getEstatura();
		this.peso = jugador.getPeso();
		this.idEquipo = jugador.getIdEquipo();
		this.datos = jugador.getDatos();
		return true;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public Float getEstatura() {
		return estatura;
	}

	public Float getPeso() {
		return peso;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public ArrayList<String> getDatos() {
		return datos;
	}

	@Override
	public String toString() {
		return "JugadorFutbol{" + "nombre='" + nombre + '\'' + ", estatura='" + estatura + '\'' + ", peso=" + peso
				+ ", idEquipo='" + idEquipo + '\'' + '}';
	}

}
