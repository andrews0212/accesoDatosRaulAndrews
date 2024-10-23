package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import dto.LibroAutor;
import packageDAO.DAO;
import sql.SQL;
import sql.metaDatos;

public class GestionLibroAutor implements DAO{
	private static String nombreId = "id"; // igual se puede obtener de los metadatos
	private metaDatos metaDatos;
	private ArrayList<Object> registroObjetos;

	// SINCRONIZAR MEMORIA CON BASE DE DATOS
	public GestionLibroAutor(Connection conexion) {
		metaDatos = new metaDatos(conexion, "Libro_Autor");
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
				registroObjetos.add(LibroAutor.objetoArray(datos));
			}
		}
	}

	// METODO PARA SINCRONIZAR
	@Override
	public void actualizarMemoria(Connection conexion) {
		this.registroObjetos = new GestionAsignatura(conexion).getRegistroObjetos();
	}

	// IMPLEMENTACION CRUD
	// CREATE
	@Override
	public boolean insertar(Connection conexion, ArrayList<String> datos) {
		// instanciamos objeto a partir del array para validar
		LibroAutor objeto = LibroAutor.objetoArray(datos);
		// obtenemos datos validados
		if (SQL.insertarAuto(conexion, metaDatos, objeto.getDatos())) {
			registroObjetos.add(objeto);
			return true;
		}
		return false;
	}

	// READ (DEFAULT EN DTO .obtenerTabla)

	// UPLOAD
	@Override
	public boolean actualizar(Connection conexion, ArrayList<Integer> ids, ArrayList<String> datos) {
		LibroAutor objeto = LibroAutor.objetoArray(datos);
		return SQL.actualizarAuto(conexion, metaDatos, ids, objeto.getDatos());
	}

	// DELETE
	@Override
	public boolean eliminar(Connection conexion, ArrayList<Integer> ids) {
		return SQL.eliminar(conexion, metaDatos, ids);
	}

	// ORDENAR
	// QUEDO OBSOLETO
	public boolean ordenarComparadores(Comparator<Object> comparador) {
		Collections.sort(registroObjetos, comparador);
		return true;
	}

	// ORDENAR POR SQL Y FILTRAR SON DEFAULT EN DTO(.obtenerTabla) CON LOS
	// ARGUMENTOS CORRESPONDIENTES
	@Override
	public metaDatos getMetaDatos() {
		return metaDatos;
	}

	@Override
	public ArrayList<Object> getRegistroObjetos() {
		return registroObjetos;
	}


}
