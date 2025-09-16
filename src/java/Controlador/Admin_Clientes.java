package Controlador;

import Modelo.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin_Clientes implements Control_Clientes {
   public static Connection con = null;
   public static PreparedStatement pst = null;

   @Override
   public Cliente leerClienteId(String IdClie) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public Cliente leerClienteNb(String Cliente) {
      Cliente Clie = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select * from Clientes Where Cliente=?;";
         pst = con.prepareStatement(sql);
         pst.setString(1, Cliente);
         Reg = pst.executeQuery();
         if (Reg.first()) {
            Clie = new Cliente();
            Clie.setIdCliente(Reg.getString(1));
            Clie.setCliente(Reg.getString(2));
            Clie.setDNI(Reg.getString(3));
            Clie.setRUC(Reg.getString(4));
         } else {
            Clie = new Cliente();
            Clie.setIdCliente("0");
            Clie.setCliente("0");
            Clie.setDNI("0");
            Clie.setRUC("0");
         }
      } catch (Exception var15) {
         System.out.println("Error en la sentencia " + var15.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var14) {
            System.out.println("Error al cerrar ");
         }

      }

      return Clie;
   }

   public Cliente leerCliente2(String idc) {
      Cliente Clie = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from clientes where idcliente=?;";
         pst = con.prepareStatement(sql);
         pst.setString(1, idc);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Clie = new Cliente();
            Clie.setIdCliente(Reg.getString(1));
            Clie.setCliente(Reg.getString(2));
            Clie.setDNI(Reg.getString(3));
            Clie.setRUC(Reg.getString(4));
         } else {
            Clie = new Cliente();
            Clie.setIdCliente("0");
            Clie.setCliente("0");
            Clie.setDNI("0");
            Clie.setRUC("0");
         }
      } catch (Exception var15) {
         System.out.println("Error en la sentencia " + var15.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var14) {
            System.out.println("Error al cerrar ");
         }

      }

      return Clie;
   }

   @Override
   public int añadirCliente(Cliente Clie) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public int actualizarCliente(Cliente Clie) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public int eliminarCliente(int IdClie) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public ArrayList<Cliente> Lista() {
      ArrayList<Cliente> Lista = new ArrayList();
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from clientes";
         Cliente c;
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         while(Reg.next()) {
            c = new Cliente();
            c.setIdCliente(String.valueOf(Reg.getString(1)));
            c.setCliente(Reg.getString(2));
            c.setDNI(Reg.getString(3));
            c.setRUC(Reg.getString(4));
            Lista.add(c);
         }
      } catch (Exception var16) {
         System.out.println("Error en la sentencia " + var16.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var15) {
            System.out.println("Error al cerrar ");
         }

      }

      return Lista;
   }

   @Override
   public int añadirCliente(String IdClie, String Cliente, String DNI, String RUC) {
      int R = 0;
      Connection con = null;

      try {
         con = Conectar.getConexion();
         CallableStatement cStmt = con.prepareCall("{call añadir_Cliente(?, ?, ?, ?)}");
         cStmt.setString(1, IdClie);
         cStmt.setString(2, Cliente);
         cStmt.setString(3, DNI);
         cStmt.setString(4, RUC);
         cStmt.execute();
      } catch (Exception var10) {
         try {
            con.close();
         } catch (SQLException var9) {
            var9.printStackTrace();
         }
      }

      return R;
   }
}
