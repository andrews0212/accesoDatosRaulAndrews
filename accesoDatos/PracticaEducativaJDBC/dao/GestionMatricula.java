package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dto.Asignatura;
import dto.Matricula;
import packageDAO.DAO;
import sql.SQL;
import sql.metaDatos;

public class GestionMatricula implements DAO {
	private static String nombreId = "id"; // igual se puede obtener de los metadatos
	private metaDatos metaDatos;
	private ArrayList<Object> registroObjetos;

	// SINCRONIZAR MEMORIA CON BASE DE DATOS
	public GestionMatricula(Connection conexion) {
		metaDatos = new metaDatos(conexion, "matricula");
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
				registroObjetos.add(Matricula.objetoArray(datos));
			}
		}
	}

	// METODO PARA SINCRONIZAR
	@Override
	public void actualizarMemoria(Connection conexion) {
		this.registroObjetos = new GestionMatricula(conexion).getRegistroObjetos();
	}

	// IMPLEMENTACION CRUD
	// CREATE
	@Override
	public boolean insertar(Connection conexion, ArrayList<String> datos) {
		// instanciamos objeto a partir del array para validar
		Matricula objeto = Matricula.objetoArray(datos);
		// obtenemos datos validados
		if (SQL.insertar(conexion, metaDatos, objeto.getDatos())) {
			registroObjetos.add(objeto);
			return true;
		}
		return false;
	}

	// READ (DEFAULT EN DTO .obtenerTabla)

	// UPLOAD 
	@Override
	public boolean actualizar(Connection conexion, ArrayList<Integer> ids, ArrayList<String> datos) {
		Matricula objeto = Matricula.objetoArray(datos);
		return SQL.actualizar(conexion, metaDatos, ids, objeto.getDatos());
		
	}
	// DELETE
	
	@Override
	public boolean eliminar(Connection conexion, ArrayList<Integer> ids) {
		return SQL.eliminar(conexion, metaDatos, ids);
	}

	// ORDENAR POR SQL Y FILTRAR SON DEFAULT EN DTO(.obtenerTabla) CON LOS
	// ARGUMENTOS CORRESPONDIENTES
	@Override
	public metaDatos getMetaDatos() {
		return metaDatos;
	}

	@Override
	public ArrayList<Object> getRegistroObjetos() {
		// Hay que castear al objeto en cuestión
		return registroObjetos;
	}



}
