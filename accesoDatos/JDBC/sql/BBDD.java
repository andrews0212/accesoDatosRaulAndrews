package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BBDD {
	private String nombreBbdd;
	private ArrayList<String> tablas;
	 public BBDD(Connection conx, String nombre) throws SQLException {
		 SQL.ejecutarSql(conx, "USE "+nombre);
		 nombreBbdd=nombre;
		 tablas = new ArrayList<String>();
		 ResultSet rsTablas = SQL.obtenerTablas(conx);
		 while (rsTablas.next()) {
			tablas.add(rsTablas.getString(1));
		}
		 rsTablas.close();
	 }
	public String getNombreBbdd() {
		return nombreBbdd;
	}
	public ArrayList<String> getTablas() {
		return tablas;
	}
	@Override
	public String toString() {
		return ("\nESTAS USANDO LA BBDD: "+nombreBbdd+"\n1.Gestionar tablas\n2.Eliminar tabla\n3.Eliminar "+nombreBbdd+"\n4.Salir");
	}
}
