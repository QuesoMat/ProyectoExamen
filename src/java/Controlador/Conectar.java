package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    // Bloque estático para debug al cargar la clase
    static {
        System.out.println("=== CLASE Conectar CARGADA ===");
        System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
        System.out.println("MYSQLHOST: " + System.getenv("MYSQLHOST"));
        System.out.println("MYSQLUSER: " + System.getenv("MYSQLUSER"));
        System.out.println("MYSQLPASSWORD: " + (System.getenv("MYSQLPASSWORD") != null ? "***" : "null"));
        System.out.println("MYSQLDATABASE: " + System.getenv("MYSQLDATABASE"));
        System.out.println("MYSQLPORT: " + System.getenv("MYSQLPORT"));
    }

    public static synchronized Connection getConexion() {
        System.out.println("=== getConexion() LLAMADO ===");
        Connection conex = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC cargado exitosamente");
            
            // Primero intentar con variables individuales (más confiable)
            String mysqlHost = System.getenv("MYSQLHOST");
            String mysqlUser = System.getenv("MYSQLUSER");
            String mysqlPassword = System.getenv("MYSQLPASSWORD");
            String mysqlDatabase = System.getenv("MYSQLDATABASE");
            String mysqlPort = System.getenv("MYSQLPORT");

            if (mysqlHost != null && mysqlUser != null) {
                System.out.println("Usando variables individuales de Railway");
                
                String url = "jdbc:mysql://" + mysqlHost + ":" + 
                           (mysqlPort != null ? mysqlPort : "3306") + "/" + mysqlDatabase +
                           "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
                
                System.out.println("URL: " + url);
                System.out.println("User: " + mysqlUser);
                
                conex = DriverManager.getConnection(url, mysqlUser, mysqlPassword);
                System.out.println("Conexión exitosa a Railway MySQL!");
                
            } else {
                // Fallback a DATABASE_URL
                String dbUrl = System.getenv("DATABASE_URL");
                if (dbUrl != null && dbUrl.startsWith("mysql://")) {
                    System.out.println("Usando DATABASE_URL: " + dbUrl);
                    
                    // Parseo simplificado y robusto
                    try {
                        // mysql://user:password@host:port/database
                        String cleanUrl = dbUrl.substring(8); // quitar mysql://
                        int atIndex = cleanUrl.indexOf('@');
                        
                        if (atIndex != -1) {
                            String userPass = cleanUrl.substring(0, atIndex);
                            String hostDb = cleanUrl.substring(atIndex + 1);
                            
                            String[] userPassParts = userPass.split(":", 2);
                            String user = userPassParts[0];
                            String password = userPassParts.length > 1 ? userPassParts[1] : "";
                            
                            String[] hostDbParts = hostDb.split("/", 2);
                            String hostPort = hostDbParts[0];
                            String database = hostDbParts.length > 1 ? hostDbParts[1] : "";
                            
                            // Limpiar parámetros si existen
                            if (database.contains("?")) {
                                database = database.split("\\?")[0];
                            }
                            
                            String url = "jdbc:mysql://" + hostPort + "/" + database + 
                                       "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
                            
                            System.out.println("URL parseada: " + url);
                            System.out.println("User: " + user);
                            
                            conex = DriverManager.getConnection(url, user, password);
                            System.out.println("Conexión exitosa via DATABASE_URL!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing DATABASE_URL: " + e.getMessage());
                    }
                }
                
                // Si todo falla, usar local
                if (conex == null) {
                    System.out.println("Usando conexión local de fallback");
                    return getLocalConnection();
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
    
    private static Connection getLocalConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/bdnegocio?autoReconnect=true&useSSL=false";
            String usr = "root";
            String psw = "ucss";
            System.out.println("Conectando localmente: " + url);
            return DriverManager.getConnection(url, usr, psw);
        } catch (SQLException e) {
            System.out.println("Error en conexión local: " + e.getMessage());
            return null;
        }
    }
}