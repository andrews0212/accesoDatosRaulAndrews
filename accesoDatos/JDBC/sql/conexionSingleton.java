package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionSingleton {
    
    // Instancia única de la clase
    private static conexionSingleton instance;
    
    // Conexión a la base de datos
    private Connection connection;
    
    // Datos de conexión a MySQL
    private String url = "jdbc:mysql://localhost:3306";
    private String username = "root";
    private String password = "contraseña";
    
    // Constructor privado para evitar la creación de múltiples instancias
    private conexionSingleton() throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            this.connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver de MySQL", e);
        }
    }
    
    // Método para obtener la conexion de la única instancia de la clase
    public static Connection getMysql() throws SQLException {
        if (instance == null) {
            instance = new conexionSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new conexionSingleton();
        }
        return instance.getConnection();
    }
    
    // Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }
}
