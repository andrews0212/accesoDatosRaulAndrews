package varios;
import java.sql.Connection;
import java.sql.SQLException;

import sql.SQL;

public class CrearGestionEquipos {
    public static void ejecutar(Connection conx) throws SQLException {

        // Crear la base de datos 'gestion_equipos'
        SQL.ejecutarSql(conx, "CREATE DATABASE gestion_equipos");

        // Usar la base de datos 'gestion_equipos'
        SQL.ejecutarSql(conx, "USE gestion_equipos");

        // Crear la tabla 'Equipo'
        SQL.ejecutarSql(conx, "CREATE TABLE Equipo (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "estadio VARCHAR(100) NOT NULL" +
                ")");

        // Crear la tabla 'Jugador' con clave foránea a 'Equipo'
        SQL.ejecutarSql(conx, "CREATE TABLE Jugador (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "estatura FLOAT NOT NULL, " +
                "peso FLOAT NOT NULL, " +
                "idEquipo INT, " +
                "FOREIGN KEY (idEquipo) REFERENCES Equipo(id) ON DELETE CASCADE" +
                ")");

        // Insertar registros en la tabla 'Equipo'
        SQL.ejecutarSql(conx, "INSERT INTO Equipo (nombre, estadio) VALUES " +
            "('Real Madrid', 'Santiago Bernabéu'), " +
            "('FC Barcelona', 'Camp Nou'), " +
            "('Atlético de Madrid', 'Wanda Metropolitano'), " +
            "('Valencia CF', 'Mestalla')");

        // Insertar registros en la tabla 'Jugador'
        SQL.ejecutarSql(conx, "INSERT INTO Jugador (nombre, estatura, peso, idEquipo) VALUES " +
            "('Karim Benzema', 1.85, 80.0, 1), " +   // Real Madrid
            "('Lionel Messi', 1.70, 72.0, 2), " +    // FC Barcelona
            "('João Félix', 1.80, 70.0, 3), " +      // Atlético de Madrid
            "('Carlos Soler', 1.80, 74.0, 4)");      // Valencia CF

        System.out.println("Base de datos 'gestion_equipos' creada con tablas y registros insertados.");
    }
}
