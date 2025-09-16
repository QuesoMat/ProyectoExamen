package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    Connection conex;

    public Conectar() {
        try {
            String dbUrl = System.getenv("DATABASE_URL");

            if (dbUrl != null) {
                // Ejemplo que devuelve Railway:
                // mysql://user:password@host:3306/database

                // Quitamos el "mysql://"
                dbUrl = dbUrl.replaceFirst("mysql://", "");

                // Separamos user:password de host:puerto/db
                String userInfo = dbUrl.split("@")[0];   // user:password
                String hostInfo = dbUrl.split("@")[1];   // host:3306/db

                String user = userInfo.split(":")[0];
                String pass = userInfo.split(":")[1];

                String hostPort = hostInfo.split("/")[0]; // host:3306
                String database = hostInfo.split("/")[1]; // db

                String jdbcUrl = "jdbc:mysql://" + hostPort + "/" + database
                        + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

                // Cargar el driver explícitamente (opcional en Java 6+, pero seguro en Tomcat)
                Class.forName("com.mysql.cj.jdbc.Driver");

                conex = DriverManager.getConnection(jdbcUrl, user, pass);
                System.out.println("Conexion exitosa a Railway!");
            } else {
                System.err.println("DATABASE_URL no esta configurada en Railway");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontro el driver JDBC de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexion con la BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conex;
    }
}
