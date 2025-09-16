package Controlador;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin_Ctegorias implements Control_Categorias {
   public static Connection con = null;
   public static PreparedStatement pst = null;

   @Override
   public Categoria leerCategoriaId(String IdCat) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public Categoria leerCategoriaNb(String Categoria) {
      Categoria Cat = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select * from Categorias Where Categoria=?;";
         pst = con.prepareStatement(sql);
         pst.setString(1, Categoria);
         Reg = pst.executeQuery();
         if (Reg.first()) {
            Cat = new Categoria();
            Cat.setIdCategoria(Reg.getInt(1));
            Cat.setCategoria(Reg.getString(2));
            Cat.setDescuento(Reg.getDouble(3));
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

      return Cat;
   }
   
   public Categoria leerCategoria2(int idc) {
      Categoria Cat = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from categorias where idcategoria=?;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, idc);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Cat = new Categoria();
            Cat.setIdCategoria(Reg.getInt(1));
            Cat.setCategoria(Reg.getString(2));
            Cat.setDescuento(Reg.getDouble(3));
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

      return Cat;
   }
   

   @Override
   public int añadirCategoria(Categoria Cat) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public int actualizarCategoria(Categoria Cat) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public int eliminarCategoria(int IdCat) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public ArrayList<Categoria> Lista() {
      ArrayList<Categoria> lstCat = new ArrayList();
      Categoria Cat = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select * from categorias";
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();

         while(Reg.next()) {
            Cat = new Categoria();
            Cat.setIdCategoria(Reg.getInt(1));
            Cat.setCategoria(Reg.getString(2));
            Cat.setDescuento(Reg.getDouble(3));
            lstCat.add(Cat);
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

      return lstCat;
   }
}
