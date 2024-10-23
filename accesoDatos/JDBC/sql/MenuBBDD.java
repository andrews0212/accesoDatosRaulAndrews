package sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import dao.GestionAlumno;
import dao.GestionAsignatura;
import dao.GestionAutor;
import dao.GestionDepartamento;
import dao.GestionEmpleado;
import dao.GestionEquipo;
import dao.GestionJugador;
import dao.GestionLibro;
import dao.GestionLibroAutor;
import dao.GestionMatricula;
import dao.GestionPrestamo;
import dao.GestionUsuario;
import packageDAO.DAO;
import varios.CrearGestionBiblioteca;
import varios.CrearGestionEducativa;
import varios.CrearGestionEmpleados;
import varios.CrearGestionEquipos;

public class MenuBBDD {

	// METODO PARA INICIAR SERVICIO
	public static boolean iniciarServicio(Scanner entrada) throws IOException {
		System.out.println("¿Quieres iniciar algún servicio? Y/X");
		if (entrada.nextLine().equalsIgnoreCase("Y")) {
			File clase = new File("bin");
			String ruta = clase.getAbsolutePath();
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", ruta, "sql.iniciarServicio");
			Process proceso = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				if (line.contains("contraseña")) {
					String contraseña = entrada.nextLine();
					writer.write(contraseña);
					writer.newLine();
					writer.flush();
				}
				if (line.contains("Indica")) {
					String servicio = entrada.nextLine();
					writer.write(servicio);
					writer.newLine();
					writer.flush();
				}
				line = reader.readLine();
			}
		}
		return true;
	}

	// EJECUTAR
	public static boolean ejecutar(Scanner entrada) throws SQLException, IOException {

		// INSTANCIAMOS LA CONEXION UNA VEZ SE HA INICIADO EL SERVICIO
		Connection conx = null;
		try {
			conx = conexionSingleton.getMysql();
		} catch (SQLException e) {
			System.out.println("Error de conexión al servicio");
		}
		// CONSULTA BASES DE DATOS
		ResultSet databases = SQL.obtenerBBDD(conx);
		boolean empleados = false;
		boolean equipos = false;
		boolean educativa = false;
		boolean biblioteca = false;
		Integer indiceEmpleados = -1;
		Integer indiceEquipos = -2;
		Integer indiceEducativa = -3;
		Integer indiceBiblioteca = -4;
		String nombreEmpleados = null;
		String nombreEquipos = null;
		String nombreEducativa = null;
		String nombreBiblioteca = null;
		int i = 1;
		while (databases.next()) {
			String dbName;
			dbName = databases.getString(1); // Obtener el nombre de la base de datos

			// Comprobar si la base de datos contiene "empleados"
			if (dbName.toLowerCase().contains("empleados")) {
				empleados = true;
				indiceEmpleados = i;
				nombreEmpleados = dbName;
			}

			// Comprobar si la base de datos contiene "equipos"
			if (dbName.toLowerCase().contains("equipos")) {
				equipos = true;
				indiceEquipos = i;
				nombreEquipos = dbName;
			}

			// Comprobar si la base de datos contiene "educativa"
			if (dbName.toLowerCase().contains("educativa")) {
				educativa = true;
				indiceEducativa = i;
				nombreEducativa = dbName;
			}
			// Comprobar si la base de datos contiene "Biblioteca"
			if (dbName.toLowerCase().contains("biblioteca")) {
				biblioteca = true;
				indiceBiblioteca = i;
				nombreBiblioteca = dbName;
			}
			i++;
		}
		databases.close();
		System.out.println("Se han detectado las siguientes bases de datos:");
		if (empleados) {
			System.out.println(indiceEmpleados + ".Gestion empleados");
		}
		if (equipos) {
			System.out.println(indiceEquipos + ".Gestion equipos");
		}
		if (educativa) {
			System.out.println(indiceEducativa + ".Gestion educativa");
		}
		if (biblioteca) {
			System.out.println(indiceBiblioteca + ".Gestion biblioteca");
		}
		boolean cambios = false;
		// CREA LA BASE DE DATOS SI NO EXISTE
		if (!empleados) {
			System.out.println("No se ha detectado la bbdd empleados ¿Quieres crearla? Y/X");
			if (entrada.nextLine().equalsIgnoreCase("Y")) {
				// FALTA ADAPTAR

				CrearGestionEmpleados.ejecutar(conx);
				cambios = true;
			}
		}
		if (!equipos) {
			System.out.println("No se ha detectado la bbdd jugadores ¿Quieres crearla? Y/X");
			if (entrada.nextLine().equalsIgnoreCase("Y")) {
				// FALTA IMPLEMENTACION SIMILAR
				cambios = true;
				CrearGestionEquipos.ejecutar(conx);
			}
		}
		if (!educativa) {
			System.out.println("No se ha detectado la bbdd educativa ¿Quieres crearla? Y/X");
			if (entrada.nextLine().equalsIgnoreCase("Y")) {
				CrearGestionEducativa.ejecutar(conx);
				nombreEducativa = "gestion_educativa";
				educativa = true;
				cambios = true;
			}
		}
		if (!biblioteca) {
			System.out.println("No se ha detectado la bbdd biblioteca ¿Quieres crearla? Y/X");
			if (entrada.nextLine().equalsIgnoreCase("Y")) {
				CrearGestionBiblioteca.ejecutar(conx);
				nombreBiblioteca = "Biblioteca";
				biblioteca = true;
				cambios = true;
			}
		}
		if (cambios) {
			if (empleados) {
				System.out.println(indiceEmpleados + ".Gestion empleados");
			}
			if (equipos) {
				System.out.println(indiceEquipos + ".Gestion equipos");
			}
			if (educativa) {
				System.out.println(indiceEducativa + ".Gestion educativa");
			}
			if (biblioteca) {
				System.out.println(indiceBiblioteca + ".Gestion biblioteca");
			}
		}
		System.out.println("Elige una base de datos (0 para salir):");
		String menu = entrada.nextLine();
		String nombreBBDD = null;
		BBDD bbdd = null;
		// ELIGE BBDD
		if (menu.equals("0")) {
			return false;
		}
		if (menu.equals(indiceEmpleados + "")) {
			nombreBBDD = nombreEmpleados;
			// INSTANCIAMOS BBDD ("USE BBDD")
			bbdd = new BBDD(conx, nombreBBDD);
		}
		if (menu.equals(indiceEquipos + "")) {
			nombreBBDD = nombreEquipos;
			bbdd = new BBDD(conx, nombreBBDD);
		}
		if (menu.equals(indiceEducativa + "")) {
			nombreBBDD = nombreEducativa;
			bbdd = new BBDD(conx, nombreBBDD);
		}
		if (menu.equals(indiceBiblioteca + "")) {
			nombreBBDD = nombreBiblioteca;
			bbdd = new BBDD(conx, nombreBBDD);
		}
		// DESPLIEGA MENU DE BBDD
		// "\nESTAS USANDO LA BBDD: "+nombreBbdd+"\n1.Gestionar tablas\n2.Eliminar
		// tabla\n3.Eliminar "+nombreBbdd+"\n4.Salir");
		System.out.println(bbdd);
		String key = entrada.nextLine();
		switch (key) {
		// GESTIONAR TABLA
		case "1": {
			// EL DTO GENERICO
			packageDAO.DAO daoGenerico = null;
			System.out.println("Elige una tabla");
			System.out.println(bbdd.getTablas());
			String tablaElegida = entrada.nextLine();
			// TODAS LAS POSIBLES TABLAS DE TODAS LAS BBDD. EN CASO DE ELEGIR UNA TABLA DE
			// OTRA BBDD: NO SE HA EJECUTADO UN USE BBDD(INSTANCIANDO BBDD) ASI QUE TAN SOLO DA ERROR
			
			if (tablaElegida.contains("jugador")) {
				// INSTANCIA CONCRETA DE DAO
				daoGenerico = new GestionJugador(conx);
				// MENU PARA GESTIONAR EL DAO
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("equipo")) {
				daoGenerico = new GestionEquipo(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("departamento")) {
				daoGenerico = new GestionDepartamento(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("empleado")) {
				daoGenerico = new GestionEmpleado(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("alumno")) {
				daoGenerico = new GestionAlumno(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("asignatura")) {
				daoGenerico = new GestionAsignatura(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.contains("matricula")) {
				daoGenerico = new GestionMatricula(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.equalsIgnoreCase("Usuario")) {
				System.out.println("holiiis");
				daoGenerico = new GestionUsuario(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.equalsIgnoreCase("Autor")) {
				daoGenerico = new GestionAutor(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.equalsIgnoreCase("Libro")) {
				daoGenerico = new GestionLibro(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.equalsIgnoreCase("Prestamo")) {
				daoGenerico = new GestionPrestamo(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			if (tablaElegida.equalsIgnoreCase("Libro_Autor")) {
				daoGenerico = new GestionLibroAutor(conx);
				menuDao(entrada, conx, daoGenerico);
			}
			break;
		}
		// ELIMINAR TABLA
		case "2": {
			System.out.println("Nombra la tabla a eliminar");
			Statement stmt = conx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet tables = stmt.executeQuery("show tables");
			while (tables.next()) {
				System.out.println(tables.getString(1));
			}
			String tablaEliminar = entrada.nextLine();
			tables.beforeFirst();
			while (tables.next()) {
				if (tablaEliminar.equalsIgnoreCase(tables.getString(1))) {
					SQL.ejecutarSql(conx, "DROP TABLE " + tables.getString(1));
				}
			}
			stmt.close();
			tables.close();
			break;

		}
		case "3": {
			SQL.ejecutarSql(conx, "DROP DATABASE " + bbdd.getNombreBbdd());
			System.out.println("Base de datos eliminada");
			break;
		}
		case "4": {
			return false;
		}
		default:
			break;
		}

		System.out.println("¿Guardar cambios? Y/X");
		String repuesta = entrada.nextLine();
		if (repuesta.equals("Y")) {
			conx.commit();
		}
		return true;

	}

	private static boolean menuDao(Scanner entrada, Connection conx, DAO daoGenerico) throws SQLException {
		boolean cambios = false;
		String nombre = daoGenerico.getMetaDatos().getNombre();
		System.out.println("\nESTAS USANDO LA TABLA: " + daoGenerico.getMetaDatos().getNombre());
		System.out.println("1.Leer tabla\n2.Añadir " + nombre + "\n3.Eliminar " + nombre + "\n4.Modificar " + nombre
				+ "\n5.Filtrar " + nombre + "\n6.Ordenar tabla" + "\n7.Salir");
		String key = entrada.nextLine();
		switch (key) {
		// MOSTRAR
		case "1": {
			System.out.println(daoGenerico.mostrarTabla(daoGenerico.obtenerTabla(conx, daoGenerico.getMetaDatos())));
			break;
		}
		// AÑADIR
		case "2": {
			ArrayList<String> objetoSVC = new ArrayList<String>();
			// si el primer campo no es "id" --> es una tabla compuesta
			if (!daoGenerico.getMetaDatos().getCampos().get(0).equalsIgnoreCase("id")) {
				for (int i = 0; i < daoGenerico.getMetaDatos().getCampos().size(); i++) {
					System.out.println(
							"Introduce un nuevo valor para el campo: " + daoGenerico.getMetaDatos().getCampos().get(i));
					objetoSVC.add(entrada.nextLine());
				}
				daoGenerico.insertar(conx, objetoSVC);
				cambios = true;
			} else {
				// añadimos un elemento id generico por temas de simetría, los metodos estan
				// adaptados a omitir el primer valor de estos arrays porque contienen el id, de
				// no existir este elemento omititriamos un dato importante en lugar del id
				objetoSVC.add("id");
				// empezamos en 1 para saltar el id
				for (int i = 1; i < daoGenerico.getMetaDatos().getCampos().size(); i++) {
					System.out.println(
							"Introduce un nuevo valor para el campo: " + daoGenerico.getMetaDatos().getCampos().get(i));
					objetoSVC.add(entrada.nextLine());
				}
				daoGenerico.insertar(conx, objetoSVC);
				cambios = true;
			}
			break;
		}
		// ELIMINAR
		case "3": {
			ArrayList<Integer> valoresID = new ArrayList<Integer>();
			for (String campoClave : daoGenerico.getMetaDatos().getCamposID()) {
				System.out.println("Indica el valor para " + campoClave + ":");
				valoresID.add(Integer.parseInt(entrada.nextLine()));
			}
			daoGenerico.eliminar(conx, valoresID);
			cambios = true;
			break;
		}
		// ACTUALIZAR
		case "4": {
			ArrayList<Integer> valoresID = new ArrayList<Integer>();
			for (String campoClave : daoGenerico.getMetaDatos().getCamposID()) {
				System.out.println("Indica el valor para " + campoClave + ":");
				valoresID.add(Integer.parseInt(entrada.nextLine()));
			}
			ArrayList<String> objetoSVC = new ArrayList<String>();
			// si el primer campo no es "id" --> es una tabla compuesta
			if (!daoGenerico.getMetaDatos().getCampos().get(0).equalsIgnoreCase("id")) {
				for (int i = 0; i < daoGenerico.getMetaDatos().getCampos().size(); i++) {
					System.out.println(
							"Introduce un nuevo valor para el campo: " + daoGenerico.getMetaDatos().getCampos().get(i));
					objetoSVC.add(entrada.nextLine());
				}
				daoGenerico.actualizar(conx, valoresID, objetoSVC);
				cambios = true;
			} else {
				// mantener simetría
				objetoSVC.add("id");
				// empezamos en 1 para saltar el id
				for (int i = 1; i < daoGenerico.getMetaDatos().getCampos().size(); i++) {
					System.out.println(
							"Introduce un nuevo valor para el campo: " + daoGenerico.getMetaDatos().getCampos().get(i));
					objetoSVC.add(entrada.nextLine());
				}
				daoGenerico.actualizar(conx, valoresID, objetoSVC);
				cambios = true;
			}
			break;
		}
		case "5": {
			System.out.println("Introduce campo para filtrar");
			String campo = entrada.nextLine();
			System.out.println("Introduce valor");
			String valor = entrada.nextLine();
			System.out.println(
					daoGenerico.mostrarTabla(daoGenerico.obtenerTabla(conx, daoGenerico.getMetaDatos(), campo, valor)));
			break;
		}
		case "6": {
			System.out.println("Introduce campo para ordenar");
			String campo = entrada.nextLine();
			System.out.println(
					daoGenerico.mostrarTabla(daoGenerico.obtenerTabla(conx, daoGenerico.getMetaDatos(), campo)));
			break;
		}
		case "7": {
			return false;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
		if (cambios) {
			System.out.println("¿Guardar cambios? Y/X");
			String repuesta = entrada.nextLine();
			if (repuesta.equalsIgnoreCase("Y")) {
				conx.commit();
				daoGenerico.actualizarMemoria(conx);
			}
		}
		sql.MenuBBDD.menuDao(entrada, conx, daoGenerico);
		return true;
	}
}
