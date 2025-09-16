package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URI;

public class Conectar {

    public static synchronized Connection getConexion() {
        Connection conex = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = System.getenv("DATABASE_URL");

            if (dbUrl != null) {
                try {
                    URI uri = new URI(dbUrl);

                    String userInfo = uri.getUserInfo(); // "user:pass"
                    String[] userParts = userInfo.split(":");
                    String username = userParts[0];
                    String password = userParts[1];

                    String jdbcUrl = "jdbc:mysql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath()
                            + "?autoReconnect=true&useSSL=false";

                    conex = DriverManager.getConnection(jdbcUrl, username, password);
                    System.out.println("Conexion exitosa a Railway!");
                } catch (Exception ex) {
                    System.out.println("Error parseando DATABASE_URL: " + ex.getMessage());
                }
            }

            if (conex == null) {
                String url = "jdbc:mysql://localhost:3306/bdnegocio?autoReconnect=true&useSSL=false";
                String usr = "root";
                String psw = "ucss";
                conex = DriverManager.getConnection(url, usr, psw);
                System.out.println("Conexion local exitosa!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver no Instalado!!");
        } catch (SQLException e) {
            System.out.println(e + " Error >> de conexion con la BD");
        }

        return conex;
    }
}
