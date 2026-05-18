import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String HOST = "localhost:5432";
    private static final String DB_NAME = "red-code";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "qwerty";

    private static final String URL = "jdbc:postgresql://" + HOST + "/" + DB_NAME;


    public static Connection conectar() {
        Connection conexion = null;
        try {
            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("¡Conexión establecida con éxito!");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación de prueba...");

        try (Connection con = conectar()) {

            if (con != null) {
                System.out.println("=> La base de datos está lista para recibir comandos SQL.");
            }

        } catch (SQLException e) {
            System.err.println("Fallo inesperado al cerrar la base de datos: " + e.getMessage());
        }
    }
}