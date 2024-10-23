package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//CLASE UTILITARIA SQL
//ESTA CLASE SE APROVECHA DE LA SIMETRÍA ENTRE LOS CAMPOS DE LA TABLA QUE OBTENEMOS VIA METADATOS Y EL ARRAY "DATOS" DE LA CLASE DAO
//RAUL MORA Y ANDREWS
public class SQL {
	private static ArrayList<Statement> listaStm = new ArrayList<Statement>();
	private static ArrayList<ResultSet> listaRs = new ArrayList<ResultSet>();

	// METODO PARA CERRAR TODOS LOS RECURSOS USADOS DESDE ESTA CLASE
	public static void cerrarRecursos() throws SQLException {
		for (Statement stm : listaStm) {
			stm.close();
		}
		for (ResultSet rs : listaRs) {
			rs.close();
		}
	}

	// METODO PARA LLEVAR A CABO ACCIONES ESPECIFICAS POR EL DESARROLLADOR
	// TODO METODO QUE INVOQUE ESTE METODO DEBERA OBTENER LOS DATOS FILTRADOS DE
	// OTRA CLASE Y NO DESDE TECLADO PARA EVITAR LA INYECCION
	public static boolean ejecutarSql(Connection conexion, String sentencia) throws SQLException {
		Statement stm = conexion.createStatement();
		listaStm.add(stm);
		return stm.execute(sentencia);
	}

	// METODO PARA OBTENER RS
	// TODO METODO QUE INVOQUE ESTE METODO DEBERA OBTENER LOS DATOS FILTRADOS DE
	// OTRA CLASE Y NO DESDE TECLADO PARA EVITAR LA INYECCION
	public static ResultSet obtenerSentencia(Connection conexion, String sentencia) throws SQLException {
		Statement stm = conexion.createStatement();
		listaStm.add(stm);
		return stm.executeQuery(sentencia);
	}

	public static ResultSet obtenerRS(Connection conexion, String nombreTabla) throws SQLException {
		Statement stm = conexion.createStatement();
		listaStm.add(stm);
		return stm.executeQuery("SELECT * FROM " + nombreTabla);
	}

	public static ResultSet obtenerBBDD(Connection conexion) throws SQLException {
		Statement stm = conexion.createStatement();
		listaStm.add(stm);
		return stm.executeQuery("SHOW DATABASES");
	}

	public static ResultSet obtenerTablas(Connection conexion) throws SQLException {
		Statement stm = conexion.createStatement();
		listaStm.add(stm);
		return stm.executeQuery("SHOW TABLES");
	}
	// CRUD

	// CREATE:
	// METODO PARA INSERTAR CON ID AUTOINCREMENT
	public static boolean insertarAuto(Connection conexion, metaDatos tabla,
			ArrayList<String> valor)/*
									 * EN UN FUTURO ESTO PODRÍA SER UNA CLASE DAO GENERICA CUYO UNICO ATRIBUTO SEA
									 * ESTE ARRAY DE LA QUE HEREDEN EL RESTO DE CLASES DAO
									 */ {
		String sentencia = "";
		String campos = "";
		String valores = "";
		// saltamos id
		for (int i = 1; i < tabla.getTipos().size(); i++) {
			if (i == 1) {
				campos = tabla.getCampos().get(i);
				valores = "?";
			} else {
				campos = campos + "," + tabla.getCampos().get(i);
				valores = valores + ",?";
			}
		}
		campos = "(" + campos + ")";
		valores = "(" + valores + ")";
		// SENTENCIA SQL RESULTADO DE LAS OPERACIONES ANTERIORES
		sentencia = "INSERT INTO " + tabla.getNombre() + " " + campos + " VALUES " + valores;
		// ASIGNACIÓN DE VALORES PARA EL PREPAREDSTATEMENT
		try {
			PreparedStatement ps = conexion.prepareStatement(sentencia);
			// saltamos el tipo 0 que corresponde al id
			for (int i = 1; i < tabla.getTipos().size(); i++) {
				String tipo = tabla.getTipos().get(i);
				if (tipo.contains("VARCHAR") || tipo.contains("TEXT")) {
					// podria hacerse (campo,valor) en lugar de (int,valor)
					ps.setString(i, valor.get(i - 1));
				} else if (tipo.contains("INT")) {
					ps.setInt(i, Integer.parseInt(valor.get(i - 1)));
				} else if (tipo.contains("DOUBLE")) {
					ps.setDouble(i, Double.parseDouble(valor.get(i - 1)));
				} else if (tipo.contains("BOOLEAN")) {
					ps.setBoolean(i, Boolean.parseBoolean(valor.get(i - 1)));
				} else if (tipo.contains("FLOAT")) {
					ps.setFloat(i, Float.parseFloat(valor.get(i - 1)));
				} else if (tipo.contains("DATE")) {
					ps.setDate(i, Date.valueOf(valor.get(i - 1)));
				} else {
					System.out.println(tipo + "no soportado");
				}
			}
			ps.executeUpdate();
			listaStm.add(ps);
		} catch (SQLException e) {
			System.out.println("Error de inserción");
			e.printStackTrace();
		}
		return true;
	}

	// INSERCION CON ID NO AUTOINCREMENT:
	public static boolean insertar(Connection conexion, metaDatos tabla,
			ArrayList<String> valor)/*
									 * EN UN FUTURO ESTO PODRÍA SER UNA CLASE DAO GENERICA CUYO UNICO ATRIBUTO SEA
									 * ESTE ARRAY DE LA QUE HEREDEN EL RESTO DE CLASES DAO
									 */ {
		String sentencia = "";
		String campos = "";
		String valores = "";
		// empieza en 0 por id
		for (int i = 0; i < tabla.getTipos().size(); i++) {
			if (i == 0) {
				campos = tabla.getCampos().get(i);
				valores = "?";
			} else {
				campos = campos + "," + tabla.getCampos().get(i);
				valores = valores + ",?";
			}
		}
		campos = "(" + campos + ")";
		valores = "(" + valores + ")";
		// SENTENCIA SQL RESULTADO DE LAS OPERACIONES ANTERIORES
		sentencia = "INSERT INTO " + tabla.getNombre() + " " + campos + " VALUES " + valores;
		// ASIGNACIÓN DE VALORES PARA EL PREPAREDSTATEMENT
		try {
			PreparedStatement ps = conexion.prepareStatement(sentencia);
			// empieza en 0 por id
			for (int i = 0; i < tabla.getTipos().size(); i++) {
				String tipo = tabla.getTipos().get(i);
				if (tipo.contains("VARCHAR") || tipo.contains("TEXT")) {
					// podria hacerse (campo,valor) en lugar de (ind,valor)
					ps.setString(i + 1, valor.get(i));
				} else if (tipo.contains("INT")) {
					ps.setInt(i + 1, Integer.parseInt(valor.get(i)));
				} else if (tipo.contains("DOUBLE")) {
					ps.setDouble(i + 1, Double.parseDouble(valor.get(i)));
				} else if (tipo.contains("BOOLEAN")) {
					ps.setBoolean(i + 1, Boolean.parseBoolean(valor.get(i)));
				} else if (tipo.contains("FLOAT")) {
					ps.setFloat(i + 1, Float.parseFloat(valor.get(i)));
				} else if (tipo.contains("DATE")) {
					ps.setDate(i + 1, Date.valueOf(valor.get(i)));
				} else {
					System.out.println(tipo + "no soportado");
				}
			}
			ps.executeUpdate();
			listaStm.add(ps);
		} catch (SQLException e) {
			System.out.println("Error de inserción");
			e.printStackTrace();
		}
		return true;
	}
	// READ:

	// METODO PARA OBTENER DESGLOSE DE TABLA EN FORMA DE ARRAY DE STRINGS
	// LA TABLA tabla VALIDA(SU CONSTRUCTOR OBTIENE LOS DATOS DE LA BBDD) Y
	// CONVIERTE LOS DATOS
	// RETORNA LOS DATOS "SIN TIPO" SE PUEDEN RECUPERAR LAS INSTANCIAS DAO
	// IMPLEMENTANDO UN CONSTRUCTOR DAO QUE RECIBA UN STRING DESDE ESTE
	// ARRAY<STRING> E INTERPRETE LOS DATOS
	public static ArrayList<String> obtenerTabla(Connection conexion, metaDatos tabla) {
		ArrayList<String> resultado = new ArrayList<>();
		try {
			ResultSet rsTabla = obtenerRS(conexion, tabla.getNombre());
			String registro = "";
			while (rsTabla.next()) {
				for (int i = 0; i < tabla.getTipos().size(); i++) {
					if (tabla.getTipos().get(i).contains("INT") || tabla.getTipos().get(i).contains("YEAR")) {
						registro = registro + rsTabla.getInt(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else if (tabla.getTipos().get(i).contains("DOUBLE")) {
						registro = registro + rsTabla.getDouble(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else if (tabla.getTipos().get(i).contains("CHAR") || tabla.getTipos().get(i).contains("TEXT")) {
						registro = registro + rsTabla.getString(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else if (tabla.getTipos().get(i).contains("BOOLEAN")) {
						registro = registro + rsTabla.getBoolean(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else if (tabla.getTipos().get(i).contains("FLOAT")) {
						registro = registro + rsTabla.getFloat(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else if (tabla.getTipos().get(i).contains("DATE")) {
						registro = registro + rsTabla.getDate(i + 1);
						if (i != tabla.getTipos().size() - 1) {
							registro += ",";
						}
					} else {
						System.out.println("Tipo de dato " + tabla.getTipos().get(i) + " no soportado");
					}
				}
				resultado.add(registro);
				registro = "";
			}
			listaRs.add(rsTabla);
		} catch (SQLException e) {
			System.out.println("Error al obtener los registros");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;

	}
	// UPDATE:

	// METODO PARA ACTUALIZAR UN REGISTRO CON ID AUTOINCREMENT
	public static boolean actualizarAuto(Connection conexion, metaDatos tabla, ArrayList<Integer> valoresID,
			ArrayList<String> nuevosDatos) {
		String sentencia;
		String cambios = "";
		// NO HACE FALTA LA CONVERSIÓN AL TIPO DE DATO CONCRETO PORQUE EL ARRAY PROVIENE
		// DE UN DAO SIMILAR: ESTA VALIDADO EN EL CONSTRUCTOR Y ES CASI SIMÉTRICO (EL
		// ARRAY NUEVOS DATOS NO INCLUYE ID)
		for (int i = 1; i < tabla.getTipos().size(); i++) {
			if (tabla.getTipos().get(i).contains("VARCHAR") || tabla.getTipos().get(i).contains("DATE")
					|| tabla.getTipos().get(i).contains("TEXT")) {
				nuevosDatos.set(i - 1, "'" + nuevosDatos.get(i - 1) + "'");
			}
			if (i == 1) {
				cambios = cambios + tabla.getCampos().get(i) + "=" + nuevosDatos.get(i - 1);
			} else {
				cambios = cambios + ", " + tabla.getCampos().get(i) + "=" + nuevosDatos.get(i - 1);
			}
		}
		String condicion = "";
		for (int i = 0; i < tabla.getCamposID().size(); i++) {
			if (i == tabla.getCamposID().size() - 1) {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i);
			} else {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i) + " AND";
			}
		}
		sentencia = "UPDATE " + tabla.getNombre() + " SET " + cambios + " WHERE " + condicion;

		try {
			SQL.ejecutarSql(conexion, sentencia);
		} catch (SQLException e) {
			System.out.println("Error al actualizar");
			e.printStackTrace();
		}
		return true;
	}

	public static boolean actualizar(Connection conexion, metaDatos tabla, ArrayList<Integer> valoresID,
			ArrayList<String> nuevosDatos) {
		String sentencia;
		String cambios = "";
		// NO HACE FALTA LA CONVERSIÓN AL TIPO DE DATO CONCRETO PORQUE EL ARRAY PROVIENE
		// DE UN DAO SIMILAR: ESTA VALIDADO EN EL CONSTRUCTOR Y ES CASI SIMÉTRICO (EL
		// ARRAY NUEVOS DATOS NO INCLUYE ID)
		for (int i = 0; i < tabla.getTipos().size(); i++) {
			if (tabla.getTipos().get(i).contains("VARCHAR") || tabla.getTipos().get(i).contains("DATE")
					|| tabla.getTipos().get(i).contains("TEXT")) {
				nuevosDatos.set(i, "'" + nuevosDatos.get(i) + "'");
			}
			if (i == 0) {
				cambios = cambios + tabla.getCampos().get(i) + "=" + nuevosDatos.get(i);
			} else {
				cambios = cambios + ", " + tabla.getCampos().get(i) + "=" + nuevosDatos.get(i);
			}
		}
		String condicion = "";
		for (int i = 0; i < tabla.getCamposID().size(); i++) {
			if (i == tabla.getCamposID().size() - 1) {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i);
			} else {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i) + " AND";
			}
		}
		sentencia = "UPDATE " + tabla.getNombre() + " SET " + cambios + " WHERE " + condicion;

		try {
			SQL.ejecutarSql(conexion, sentencia);
		} catch (SQLException e) {
			System.out.println("Error al actualizar");
			e.printStackTrace();
		}
		return true;
	}
	// DELETE:

	// METODO PARA ELIMINAR UN REGISTRO
	public static boolean eliminar(Connection conexion, metaDatos tabla, ArrayList<Integer> valoresID) {
		String condicion = "";
		for (int i = 0; i < tabla.getCamposID().size(); i++) {
			if (i == tabla.getCamposID().size() - 1) {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i);
			} else {
				condicion = condicion + " " + tabla.getCamposID().get(i) + " = " + valoresID.get(i) + " AND";
			}
		}
		String sentencia = ("DELETE FROM " + tabla.getNombre() + " WHERE" + condicion);
		try {

			SQL.ejecutarSql(conexion, sentencia);
		} catch (SQLException e) {
			System.out.println("Error al eliminar registro");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// ORDENAR (SOBRECARGA DE READ)
	// METODO ALTERNATIVO A ORDENAR UN ARRAYLIST POR COMPARADORES
	// ¿AÑADIR UN ARGUMENTO BOLEANO PARA DESC?
	public static ArrayList<String> obtenerTabla(Connection conexion, metaDatos tabla, String orden) {
		ArrayList<String> resultado = new ArrayList<>();
		// VALIDA: COMPRUEBA QUE EL CAMPO EXISTA
		if (tabla.getCampos().contains(orden)) {
			try {
				ResultSet rsTabla = obtenerSentencia(conexion,
						"SELECT * FROM " + tabla.getNombre() + " ORDER BY " + orden);
				String registro = "";
				while (rsTabla.next()) {
					for (int i = 0; i < tabla.getTipos().size(); i++) {
						if (tabla.getTipos().get(i).contains("INT") || tabla.getTipos().get(i).contains("YEAR")) {
							registro = registro + rsTabla.getInt(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("DOUBLE")) {
							registro = registro + rsTabla.getDouble(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("CHAR")
								|| tabla.getTipos().get(i).contains("TEXT")) {
							registro = registro + rsTabla.getString(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("BOOLEAN")) {
							registro = registro + rsTabla.getBoolean(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("FLOAT")) {
							registro = registro + rsTabla.getFloat(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("DATE")) {
							registro = registro + rsTabla.getDate(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else {
							System.out.println("Tipo de dato " + tabla.getTipos().get(i) + " no soportado");
						}
					}
					resultado.add("(" + registro + ")");
					registro = "";
				}
				listaRs.add(rsTabla);
			} catch (SQLException e) {
				System.out.println("Error al obtener los registros");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No existe ese campo");
		}
		return resultado;

	}

	// FILTRAR (SOBRECARGA DE READ)
	public static ArrayList<String> obtenerTabla(Connection conexion, metaDatos tabla, String campo, String filtro) {
		ArrayList<String> resultado = new ArrayList<>();
		// VALIDA: COMPRUEBA QUE EL CAMPO EXISTA
		if (tabla.getCampos().contains(campo)) {
			// RUDIMENTARIO PERO EFICAZ
			filtro.replace(";", "");
			filtro.replace(",", "");
			try {
				String sentencia = "SELECT * FROM " + tabla.getNombre() + " WHERE " + campo + " = " + filtro;
				ResultSet rsTabla = obtenerSentencia(conexion, sentencia);
				String registro = "";
				while (rsTabla.next()) {
					for (int i = 0; i < tabla.getTipos().size(); i++) {
						if (tabla.getTipos().get(i).contains("INT") || tabla.getTipos().get(i).contains("YEAR")) {
							registro = registro + rsTabla.getInt(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("DOUBLE")) {
							registro = registro + rsTabla.getDouble(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("CHAR")
								|| tabla.getTipos().get(i).contains("TEXT")) {
							registro = registro + rsTabla.getString(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("BOOLEAN")) {
							registro = registro + rsTabla.getBoolean(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("FLOAT")) {
							registro = registro + rsTabla.getFloat(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else if (tabla.getTipos().get(i).contains("DATE")) {
							registro = registro + rsTabla.getDate(i + 1);
							if (i != tabla.getTipos().size() - 1) {
								registro += ",";
							}
						} else {
							System.out.println("Tipo de dato " + tabla.getTipos().get(i) + " no soportado");
						}
					}
					resultado.add("(" + registro + ")");
					registro = "";
				}
				listaRs.add(rsTabla);
			} catch (SQLException e) {
				System.out.println("Error al obtener los registros");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No existe ese campo");
		}
		return resultado;

	}

}
