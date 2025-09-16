package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    public static synchronized Connection getConexion() {
        Connection conex = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentar primero leer desde la variable de entorno
            String dbUrl = System.getenv("DATABASE_URL");

            if (dbUrl != null) {
                // Railway devuelve algo como mysql://user:pass@host:3306/db
                if (dbUrl.startsWith("mysql://")) {
                    dbUrl = dbUrl.replaceFirst("mysql://", "jdbc:mysql://");
                }
                conex = DriverManager.getConnection(dbUrl);
                System.out.println("Conexion exitosa a Railway!");
            } else {
                // Si no existe la variable, usar configuración local
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
