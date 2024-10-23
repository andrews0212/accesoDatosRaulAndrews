package packageDAO;

import java.sql.Connection;
import java.util.ArrayList;

import sql.SQL;
import sql.metaDatos;

//IMPLEMENTACIÓN DE CRUD EN DAO
public interface DAO {
	// CREATE
	public boolean insertar(Connection conexion, ArrayList<String> datos);

	// READ
	public default ArrayList<String> obtenerTabla(Connection conexion, metaDatos metaDatos) {
		return SQL.obtenerTabla(conexion, metaDatos);
	}

	// UPLOAD
	public boolean actualizar(Connection conexion,ArrayList<Integer> ids, ArrayList<String> datos);

	// DELETE
	public boolean eliminar(Connection conexion, ArrayList<Integer> ids);

	// ORDER
	public default ArrayList<String> obtenerTabla(Connection conexion, metaDatos tabla, String orden) {
		return SQL.obtenerTabla(conexion, tabla, orden);
	}

	// FILTRAR
	public default ArrayList<String> obtenerTabla(Connection conexion, metaDatos tabla, String campo, String valor) {
		return SQL.obtenerTabla(conexion, tabla, campo, valor);
	}
	
	public void actualizarMemoria(Connection conexion);
	
	

	public default String mostrarTabla(ArrayList<String> registros) {
		String resultado = "";
		resultado+=this.getMetaDatos().getNombre().toUpperCase()+"\n";
		resultado+=this.getMetaDatos().getTipos().toString()+"\n";
		resultado+=this.getMetaDatos().getCampos().toString()+"\n";
		for (String registro : registros) {
			resultado+=this.getMetaDatos().getNombre()+": "+registro+"\n";
		}
		return resultado;
		
	}
	public metaDatos getMetaDatos();

	public ArrayList<Object> getRegistroObjetos();

}
