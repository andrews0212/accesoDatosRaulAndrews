package varios;

import java.sql.Connection;
import java.sql.SQLException;

import sql.SQL;

public class CrearGestionEmpleados {
    public static void ejecutar(Connection conx) throws SQLException {
        
        // Crear la base de datos 'EmpleadosDB'
        SQL.ejecutarSql(conx, "CREATE DATABASE EmpleadosDB");

        // Usar la base de datos 'EmpleadosDB'
        SQL.ejecutarSql(conx, "USE EmpleadosDB");

        // Crear la tabla 'departamento'
        SQL.ejecutarSql(conx, "CREATE TABLE departamento (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Nombre VARCHAR(50) NOT NULL" +
                ")");

        // Crear la tabla 'empleado' con clave foránea a 'departamento'
        SQL.ejecutarSql(conx, "CREATE TABLE empleado (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "DNI VARCHAR(9) NOT NULL, " +
                "Nombre VARCHAR(50) NOT NULL, " +
                "Apellido VARCHAR(50) NOT NULL, " +
                "Telefono VARCHAR(15) NOT NULL, " +
                "Salario DOUBLE NOT NULL, " +
                "departamento_id INT, " +
                "CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(ID) " +
                "ON DELETE CASCADE" +
                ")");

        // Insertar registros en la tabla 'departamento'
        SQL.ejecutarSql(conx, "INSERT INTO departamento (Nombre) VALUES " +
            "('Recursos Humanos'), " +
            "('Desarrollo'), " +
            "('Marketing'), " +
            "('Finanzas')");

        // Insertar registros en la tabla 'empleado'
        SQL.ejecutarSql(conx, "INSERT INTO empleado (DNI, Nombre, Apellido, Telefono, Salario, departamento_id) VALUES " +
            "('12345678A', 'Juan', 'Pérez', '600123456', 2500.50, 1), " +   // Recursos Humanos
            "('87654321B', 'Ana', 'López', '610654321', 3000.00, 2), " +   // Desarrollo
            "('11223344C', 'Luis', 'García', '620987654', 3500.75, 2), " + // Desarrollo
            "('99887766D', 'Marta', 'Sánchez', '630456789', 2800.60, 3), " + // Marketing
            "('44556677E', 'Pedro', 'Martínez', '640123987', 2000.40, 1), " + // Recursos Humanos
            "('55667788F', 'Laura', 'Gómez', '650987123', 4000.00, 4)");   // Finanzas

        System.out.println("Base de datos 'EmpleadosDB' creada con tablas y registros insertados.");
    }
}
