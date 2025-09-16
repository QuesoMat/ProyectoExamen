package Controlador;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin_Productos implements Control_Productos {
   public static Connection con = null;
   public static PreparedStatement pst = null;

   public Producto leerProducto(int IdProd) {
      Producto Prod = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select * From productos Where IdProducto=?;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, IdProd);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Prod = new Producto();
            Prod.setIdProducto(Reg.getInt(1));
            Prod.setIdCategoria(Reg.getInt(2));
            Prod.setProducto(Reg.getString(3));
            Prod.setPresentacion(Reg.getString(4));
            Prod.setPrecio(Reg.getDouble(5));
            Prod.setExistencia(Reg.getDouble(6));
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

      return Prod;
   }

   public int añadirProducto(Producto Prod) {
      int R = 0;

      try {
         con = Conectar.getConexion();
         String sql = "Insert Into Productos(idproducto,idcategoria,Producto,Presentacion,Precio,Existencia) Values(?,?,?,?,?,?);";
         pst = con.prepareStatement(sql);
         pst.setInt(1, Prod.getIdProducto());
         pst.setInt(2, Prod.getIdCategoria());
         pst.setString(3, Prod.getProducto());
         pst.setString(4, Prod.getPresentacion());
         pst.setDouble(5, Prod.getPrecio());
         pst.setDouble(6, Prod.getExistencia());
         R = pst.executeUpdate();
      } catch (Exception var12) {
         System.out.println("Error en la sentencia " + var12.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var11) {
            System.out.println("Error al cerrar ");
         }

      }

      return R;
   }

   public int actualizarProducto(Producto Prod) {
      int R = 0;

      try {
         con = Conectar.getConexion();
         String sql = "UpDate Productos Set idCategoria=?,Producto=?,Presentacion=?,precio=?,Existencia=? where idproducto=?;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, Prod.getIdCategoria());
         pst.setString(2, Prod.getProducto());
         pst.setString(3, Prod.getPresentacion());
         pst.setDouble(4, Prod.getPrecio());
         pst.setDouble(5, Prod.getExistencia());
         pst.setInt(6, Prod.getIdProducto());
         R = pst.executeUpdate();
      } catch (Exception var12) {
         System.out.println("Error en la sentencia " + var12.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var11) {
            System.out.println("Error al cerrar ");
         }

      }

      return R;
   }
   
   public int acPrd(double a, int b) {
      int R = 0;
      try {
         con = Conectar.getConexion();
         String sql = "update productos set existencia=? where idproducto=?;";
         pst = con.prepareStatement(sql);
         pst.setDouble(1, a);
         pst.setInt(2, b);
         R = pst.executeUpdate();
      } catch (Exception var12) {
         System.out.println("Error en la sentencia " + var12.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var11) {
            System.out.println("Error al cerrar ");
         }

      }

      return R;
   }

   public int eliminarProducto(int IdProd) {
      Connection con = null;
      PreparedStatement pst = null;
      int R = 0;

      try {
         con = Conectar.getConexion();
         String sql = "Delete from productos where IdProducto=?;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, IdProd);
         R = pst.executeUpdate();
      } catch (Exception var14) {
         System.out.println("Error en la sentencia " + var14.getMessage());
      } finally {
         try {
            if (pst != null) {
               pst.close();
            }

            if (con != null) {
               con.close();
            }
         } catch (SQLException var13) {
            System.out.println("Error al cerrar ");
         }

      }

      return R;
   }

   public ArrayList<Producto> Catalogo(int IdCategoria) {
      ArrayList<Producto> Lista = new ArrayList();
      Producto Prod = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select * From productos Where IdCategoria=?;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, IdCategoria);
         Reg = pst.executeQuery();

         while(Reg.next()) {
            Prod = new Producto();
            Prod.setIdProducto(Reg.getInt(1));
            Prod.setIdCategoria(Reg.getInt(2));
            Prod.setProducto(Reg.getString(3));
            Prod.setPresentacion(Reg.getString(4));
            Prod.setPrecio(Reg.getDouble(5));
            Prod.setExistencia(Reg.getDouble(6));
            Lista.add(Prod);
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
}
