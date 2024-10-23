package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//SE EXPLICA SOLA
public class metaDatos {
	private String nombre;
	private ArrayList<String> tiposID;
	private ArrayList<String> camposID;
	private ArrayList<String> tipos;
	private ArrayList<String> campos;

	public metaDatos(Connection conexion, String nombre) {
		try {
			this.nombre = nombre;
			// Obtener los metadatos de la base de datos
			DatabaseMetaData metaData = conexion.getMetaData();
			// Obtener los metadatos de las columnas de la tabla
			ResultSet metaDatos = metaData.getColumns(null, null, this.nombre, null);
			// Obtener los datos solo de las columnas que son clave primaria o compuesta (util para las tablas intermedias)
			ResultSet camposClave = metaData.getPrimaryKeys(null, null, this.nombre);
			ArrayList<String> camposTabla = new ArrayList<>();
			ArrayList<String> tiposTabla = new ArrayList<>();
			ArrayList<String> idsTabla = new ArrayList<String>();
			while (metaDatos.next()) {
				//DATOS JUGOSOS
				camposTabla.add(metaDatos.getString("COLUMN_NAME"));
				tiposTabla.add(metaDatos.getString("TYPE_NAME"));
				
			}
			while (camposClave.next()) {
				//CAMPOS CLAVE
				idsTabla.add(camposClave.getString("COLUMN_NAME"));
			}
			tipos=tiposTabla;
			campos=camposTabla;
			camposID=idsTabla;
		} catch (SQLException e) {
			System.out.println("Error al obtener los datos de la tabla");
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombre;
	}


	public ArrayList<String> getTipos() {
		return tipos;
	}

	
	public ArrayList<String> getCampos() {
		return campos;
	}

	public ArrayList<String> getCamposID() {
		return camposID;
	}

	public ArrayList<String> getTiposID() {
		return tiposID;
	}
	
}
