package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    
   public static synchronized Connection getConexion() {
      Connection conex = null;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/bdnegocio?autoReconnect=true&useSSL=false";
         String usr = "root";
         String psw = "ucss";
         conex = DriverManager.getConnection(url, usr, psw);
      } catch (ClassNotFoundException var4) {
         System.out.println("Error >> Driver no Instalado!!");
      } catch (SQLException var5) {
         System.out.println(String.valueOf(var5) + "Error >> de conexion con la BD");
      }

      return conex;
   }
}
