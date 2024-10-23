package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import dto.Equipo;
import packageDAO.DAO;
import sql.SQL;
import sql.metaDatos;

public class GestionEquipo implements DAO {
	private static String nombreId = "id"; //igual se puede obtener de los metadatos
	private metaDatos metaDatos;
	private ArrayList<Object> registroObjetos;

	// SINCRONIZAR MEMORIA CON BASE DE DATOS
	public GestionEquipo(Connection conexion) {
		metaDatos = new metaDatos(conexion, "equipo");
		registroObjetos = new ArrayList<>();
		// REGISTROS SIN TIPO
		ArrayList<String> registros = SQL.obtenerTabla(conexion, metaDatos);
		for (String registro : registros) {
			registro.replace("(", "");
			registro.replace(")", "");
			ArrayList<String> datos = new ArrayList<String>(Arrays.asList(registro.split(",")));
			// CONSTRUCTOR A PARTIR DE UNA CADENA DE DATOS Y AÑADIR A MEMORIA
			if (datos.isEmpty()) {
				System.out.println("La tabla está vacia");
			} else {
				registroObjetos.add(Equipo.equipoArray(datos));
			}
		}
	}

	// METODO PARA SINCRONIZAR
	@Override
	public void actualizarMemoria(Connection conexion) {
		this.registroObjetos = new GestionEquipo(conexion).getRegistroObjetos();
	}

	// IMPLEMENTACION CRUD
	// CREATE
	@Override
	public boolean insertar(Connection conexion, ArrayList<String> datos) {
		//instanciamos jugador a partir del array para validar
		Equipo equipo = Equipo.equipoArray(datos); 
		//obtenemos datos validados
		if (SQL.insertar(conexion, metaDatos, equipo.getDatos())) {
			registroObjetos.add(equipo);
			return true;
		}
		return false;
	}

	// READ (DEFAULT EN DTO .obtenerTabla)

	// UPLOAD
	@Override
	public boolean actualizar(Connection conexion,ArrayList<Integer> ids, ArrayList<String> datos) {
		Equipo jugador = Equipo.equipoArray(datos);
		return SQL.actualizar(conexion, metaDatos, ids, jugador.getDatos());
	}

	// DELETE
	@Override
	public boolean eliminar(Connection conexion, ArrayList<Integer> ids) {
		return SQL.eliminar(conexion, metaDatos, ids);
	}


	// ORDENAR POR SQL Y FILTRAR SON DEFAULT EN DTO(.obtenerTabla) CON LOS ARGUMENTOS CORRESPONDIENTES
	@Override
	public metaDatos getMetaDatos() {
		return metaDatos;
	}
	@Override
	public ArrayList<Object> getRegistroObjetos() {
		return registroObjetos;
	}



	
	
}