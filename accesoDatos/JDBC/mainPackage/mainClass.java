package mainPackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import sql.MenuBBDD;
import sql.SQL;
import sql.conexionSingleton;

public class mainClass {
	public static void main(String[] args) throws SQLException, IOException {
		Scanner entrada = new Scanner(System.in);
		// INICIAR SERVICIO
		boolean iniciado = false;
		while (!iniciado) {
			iniciado = MenuBBDD.iniciarServicio(entrada);
		}
		// MENU
		while (MenuBBDD.ejecutar(entrada));
		// SALIR
		SQL.cerrarRecursos();
		System.out.println("Recursos cerrados");
		conexionSingleton.getMysql().close();
		System.out.println("Hasta pronto!");
	}
}
