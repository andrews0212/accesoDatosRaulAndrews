package varios;

import java.sql.Connection;
import java.sql.SQLException;

import sql.SQL;

public class CrearGestionEducativa {
    public static void ejecutar(Connection conx) throws SQLException {
        
        // Crear la base de datos 'gestion_educativa'
        SQL.ejecutarSql(conx, "CREATE DATABASE gestion_educativa");

        // Usar la base de datos 'gestion_educativa'
        SQL.ejecutarSql(conx, "USE gestion_educativa");

        // Crear la tabla 'Alumno'
        SQL.ejecutarSql(conx, "CREATE TABLE alumno (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Nombre VARCHAR(100) NOT NULL, " +
                "FechaNacimiento DATE NOT NULL, " +
                "Telefono VARCHAR(15), " +
                "Direccion VARCHAR(255)" +
                ")");

        // Crear la tabla 'Asignatura'
        SQL.ejecutarSql(conx, "CREATE TABLE asignatura (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Nombre VARCHAR(100) NOT NULL, " +
                "HorasSemanales INT NOT NULL" +
                ")");

        // Crear la tabla intermedia 'Matricula'
        SQL.ejecutarSql(conx, "CREATE TABLE matricula (" +
                "idAlumno INT, " +
                "idAsignatura INT, " +
                "FechaMatricula DATE NOT NULL, " +
                "PRIMARY KEY (idAlumno, idAsignatura), " +
                "FOREIGN KEY (idAlumno) REFERENCES alumno(ID) ON DELETE CASCADE, " +
                "FOREIGN KEY (idAsignatura) REFERENCES asignatura(ID) ON DELETE CASCADE" +
                ")");

     // Insertar más registros en la tabla 'Alumno'
        SQL.ejecutarSql(conx, "INSERT INTO alumno (Nombre, FechaNacimiento, Telefono, Direccion) VALUES " +
            "('Juan Pérez', '1995-06-15', '600123456', 'Calle Falsa 123'), " +
            "('Ana García', '1993-12-22', '611234567', 'Avenida Siempre Viva 742'), " +
            "('Luis Martín', '1990-03-08', '622345678', 'Calle del Sol 8'), " +
            "('María López', '1998-10-10', '633456789', 'Calle Luna 22'), " +
            "('Carlos Sánchez', '1992-05-05', '644567890', 'Calle Estrella 33'), " +
            "('Marta Ramírez', '1997-08-25', '655678901', 'Calle Mar 44'), " +
            "('Javier Díaz', '1994-09-17', '666789012', 'Avenida Río 55'), " +
            "('Sofía Fernández', '1991-11-11', '677890123', 'Calle Nube 66'), " +
            "('Raúl Gómez', '1999-07-30', '688901234', 'Calle Cielo 77'), " +
            "('Laura Morales', '1996-04-12', '699012345', 'Calle Tierra 88');");

        // Insertar más registros en la tabla 'Asignatura'
        SQL.ejecutarSql(conx, "INSERT INTO asignatura (Nombre, HorasSemanales) VALUES " +
            "('Matemáticas', 5), " +
            "('Lengua', 4), " +
            "('Ciencias', 3), " +
            "('Historia', 4), " +
            "('Geografía', 3), " +
            "('Educación Física', 2), " +
            "('Inglés', 5), " +
            "('Francés', 3), " +
            "('Filosofía', 2), " +
            "('Música', 2);");

        // Insertar más registros en la tabla intermedia 'Matricula'
        SQL.ejecutarSql(conx, "INSERT INTO matricula (idAlumno, idAsignatura, FechaMatricula) VALUES " +
            "(1, 1, '2023-09-01'), (1, 2, '2023-09-01'), " +
            "(2, 3, '2023-09-01'), (2, 4, '2023-09-01'), " +
            "(3, 5, '2023-09-01'), (3, 6, '2023-09-01'), " +
            "(4, 7, '2023-09-01'), (4, 8, '2023-09-01'), " +
            "(5, 9, '2023-09-01'), (5, 10, '2023-09-01');");

        System.out.println("Base de datos 'gestion_educativa' creada con tablas y registros insertados.");
    }
}
