package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    public static synchronized Connection getConexion() {
        Connection conex = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Debug: Mostrar todas las variables de entorno relevantes
            System.out.println("=== DEBUG: Variables de entorno ===");
            System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
            System.out.println("MYSQLHOST: " + System.getenv("MYSQLHOST"));
            System.out.println("MYSQLUSER: " + System.getenv("MYSQLUSER"));
            System.out.println("MYSQLPASSWORD: " + System.getenv("MYSQLPASSWORD"));
            System.out.println("MYSQLDATABASE: " + System.getenv("MYSQLDATABASE"));
            System.out.println("MYSQLPORT: " + System.getenv("MYSQLPORT"));
            
            String dbUrl = System.getenv("DATABASE_URL");
            
            if (dbUrl != null && !dbUrl.trim().isEmpty()) {
                System.out.println("Procesando DATABASE_URL: " + dbUrl);
                
                try {
                    // Parsear la URL de Railway
                    if (dbUrl.startsWith("mysql://")) {
                        dbUrl = dbUrl.substring(8); // Quitar "mysql://"
                    }
                    
                    // Separar credenciales de host/database
                    String[] parts = dbUrl.split("@");
                    if (parts.length != 2) {
                        System.out.println("Error: Formato incorrecto - falta '@'");
                        return fallbackLocal();
                    }
                    
                    // Obtener usuario y contraseña
                    String[] credentials = parts[0].split(":");
                    if (credentials.length < 2) {
                        System.out.println("Error: Formato de credenciales incorrecto");
                        return fallbackLocal();
                    }
                    
                    String user = credentials[0];
                    // La contraseña puede contener ":" así que unimos el resto
                    String password = parts[0].substring(user.length() + 1);
                    
                    // Obtener host, puerto y base de datos
                    String hostDbPart = parts[1];
                    String[] hostDbParts = hostDbPart.split("/");
                    if (hostDbParts.length < 2) {
                        System.out.println("Error: Formato host/database incorrecto");
                        return fallbackLocal();
                    }
                    
                    String hostPort = hostDbParts[0];
                    String database = hostDbParts[1];
                    
                    // Limpiar parámetros adicionales si existen
                    if (database.contains("?")) {
                        database = database.split("\\?")[0];
                    }
                    
                    String url = "jdbc:mysql://" + hostPort + "/" + database + 
                               "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
                    
                    System.out.println("URL JDBC construida: " + url);
                    System.out.println("Usuario: " + user);
                    System.out.println("Password length: " + password.length());
                    
                    conex = DriverManager.getConnection(url, user, password);
                    System.out.println("Conexión exitosa a Railway MySQL!");
                    
                } catch (Exception e) {
                    System.out.println("Error parsing DATABASE_URL: " + e.getMessage());
                    return fallbackLocal();
                }
                
            } else {
                // Intentar con variables individuales
                String mysqlHost = System.getenv("MYSQLHOST");
                String mysqlUser = System.getenv("MYSQLUSER");
                String mysqlPassword = System.getenv("MYSQLPASSWORD");
                String mysqlDatabase = System.getenv("MYSQLDATABASE");
                String mysqlPort = System.getenv("MYSQLPORT");
                
                if (mysqlHost != null && mysqlUser != null) {
                    String url = "jdbc:mysql://" + mysqlHost + ":" + 
                               (mysqlPort != null ? mysqlPort : "3306") + "/" + mysqlDatabase +
                               "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
                    
                    System.out.println("Usando variables individuales:");
                    System.out.println("URL: " + url);
                    System.out.println("User: " + mysqlUser);
                    
                    conex = DriverManager.getConnection(url, mysqlUser, mysqlPassword);
                    System.out.println("Conexión exitosa con variables individuales!");
                } else {
                    System.out.println("No se encontraron variables de Railway, usando local");
                    return fallbackLocal();
                }
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
    
    private static Connection fallbackLocal() {
        try {
            String url = "jdbc:mysql://localhost:3306/bdnegocio?autoReconnect=true&useSSL=false";
            String usr = "root";
            String psw = "ucss";
            System.out.println("Usando conexión local: " + url);
            return DriverManager.getConnection(url, usr, psw);
        } catch (SQLException e) {
            System.out.println("Error en fallback local: " + e.getMessage());
            return null;
        }
    }
}