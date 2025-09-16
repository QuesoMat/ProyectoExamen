package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    public static synchronized Connection getConexion() {
        Connection conex = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Obtener DATABASE_URL de las variables de entorno
            String dbUrl = System.getenv("DATABASE_URL");
            
            if (dbUrl != null && dbUrl.startsWith("mysql://")) {
                // Parsear la URL de Railway
                dbUrl = dbUrl.replaceFirst("mysql://", "");
                
                String[] parts = dbUrl.split("@");
                if (parts.length != 2) {
                    System.out.println("Formato incorrecto de DATABASE_URL");
                    return null;
                }
                
                String[] credentials = parts[0].split(":");
                String[] hostAndDb = parts[1].split("/");
                
                if (credentials.length < 2 || hostAndDb.length < 2) {
                    System.out.println("Formato incorrecto de DATABASE_URL");
                    return null;
                }
                
                String user = credentials[0];
                String password = credentials[1];
                String hostPort = hostAndDb[0];
                String database = hostAndDb[1];
                
                // Construir URL JDBC
                String url = "jdbc:mysql://" + hostPort + "/" + database + 
                           "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
                
                conex = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión exitosa a Railway MySQL!");
                
            } else {
                // Fallback para desarrollo local
                String url = "jdbc:mysql://localhost:3306/bdnegocio?autoReconnect=true&useSSL=false";
                String usr = "root";
                String psw = "ucss";
                conex = DriverManager.getConnection(url, usr, psw);
                System.out.println("Conexión local exitosa");
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver no Instalado!!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error >> de conexion con la BD: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        return conex;
    }
}