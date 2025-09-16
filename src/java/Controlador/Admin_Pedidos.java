package Controlador;

import Modelo.Detalle;
import Modelo.Detalle_Pedido;
import Modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin_Pedidos implements Control_Pedidos {
   public static Connection con = null;
   public static PreparedStatement pst = null;

   @Override
public int guardarPedido(Pedido Ped, ArrayList<Detalle> Detalles) {
    int R = 0;
    int Max = this.getCantPed2()+1;

    try {
        con = Conectar.getConexion();

        // Insertar pedido
        String sqlPedido = "INSERT INTO Pedidos (NroPedido, Fecha, IdCliente) VALUES (?, ?, ?)";
        pst = con.prepareStatement(sqlPedido);
        pst.setInt(1, Max);
        pst.setString(2, Ped.getFecha());
        pst.setString(3, Ped.getIdCliente());
        R = pst.executeUpdate(); // este debe ejecutarse con éxito primero
        pst.close();

        // Verificar si el pedido fue insertado correctamente antes de insertar detalles
        if (R > 0) {
            String sqlDetalle = "INSERT INTO Detalle_Pedido (NroPedido, IdProducto, Cantidad, Precio, Descuento) VALUES (?, ?, ?, ?, ?)";

            for (Detalle d : Detalles) {
                pst = con.prepareStatement(sqlDetalle);
                pst.setInt(1, Max);
                pst.setInt(2, d.getIdProducto());
                pst.setDouble(3, d.getCantPed());
                pst.setDouble(4, d.getPrecio());
                pst.setDouble(5, d.getDescuento());
                R = pst.executeUpdate();
                pst.close();
            }
        } else {
            System.out.println("No se pudo insertar el pedido.");
        }

    } catch (Exception ex) {
        System.out.println("Error en la sentencia: " + ex.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex.getMessage());
        }
    }

    return R;
}
 
   public Pedido leerPedido(int Pedido) {
      Pedido p = null;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from pedidos where nropedido=?";
         pst = con.prepareStatement(sql);
         pst.setInt(1, Pedido);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            p = new Pedido();
            p.setNroPedido(Reg.getInt(1));
            p.setFecha(Reg.getString(2));
            p.setIdCliente(Reg.getString(3));
            
         } else {
            p =null;
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

      return p;
   }
   @Override
   public int getMaxPed() {
      int Max = 0;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "Select Max(NroPedido) From pedidos;";
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         if (Reg.first()) {
            Max = Reg.getInt(1);
         }
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

      return Max;
   }
   
      
   public ArrayList<Pedido> Lista() {
      ArrayList<Pedido> Lista = new ArrayList();
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from pedidos";
         Pedido c;
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         while(Reg.next()) {
            c = new Pedido();
            c.setNroPedido(Reg.getInt(1));
            c.setFecha(Reg.getString(2));
            c.setIdCliente(Reg.getString(3));
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
   
   public ArrayList<Detalle_Pedido> Lista2(String np) {
      ArrayList<Detalle_Pedido> Lista = new ArrayList();
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select*from detalle_pedido where nropedido='"+np+"'";
         Detalle_Pedido c;
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         while(Reg.next()) {
            c = new Detalle_Pedido();
            c.setNroPedido(Reg.getInt(1));
            c.setIdProducto(Reg.getInt(2));
            c.setCantidad(Reg.getInt(3));
            c.setPrecio(Reg.getDouble(4));
            c.setDescuento(Reg.getDouble(5));
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
   
   public Detalle_Pedido getDP(int idp,int nrp) {
      Detalle_Pedido c = new Detalle_Pedido();
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;
      try {
         con = Conectar.getConexion();
         String sql = "select*from detalle_pedido where nropedido='"+idp+"' and idproducto='"+nrp+"'";

         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         while(Reg.next()) {
            c = new Detalle_Pedido();
            c.setNroPedido(Reg.getInt(1));
            c.setIdProducto(Reg.getInt(2));
            c.setCantidad(Reg.getInt(3));
            c.setPrecio(Reg.getDouble(4));
            c.setDescuento(Reg.getDouble(5));
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
      return c;
   }
   
   
   public int eliminarDP(int idp,int nrp) {
      Connection con = null;
      PreparedStatement pst = null;
      int R = 0;

      try {
         con = Conectar.getConexion();
         String sql = "delete from detalle_pedido where idproducto=? and nropedido=? ;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, idp);
         pst.setInt(2, nrp);
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
   
  public int eliminar(int nrp) {
      Connection con = null;
      PreparedStatement pst = null;
      int R = 0;

      try {
         con = Conectar.getConexion();
         String sql = "delete from pedidos where nropedido=? ;";
         pst = con.prepareStatement(sql);
         pst.setInt(1, nrp);
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
   
   public int getCantPed(String n) {
      int Max = 0;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select sum(cantidad) from detalle_pedido where idproducto='"+n+"'";
         System.out.println(sql);
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Max = Reg.getInt(1);
         }
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

      return Max;
   }
   
   public int getCantPed2() {
      int Max = 0;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select max(nropedido)as nro from pedidos";
         System.out.println(sql);
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Max = Reg.getInt("nro");
         }
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

      return Max;
   }
   
      public int getCantPedDP(int n) {
      int Max = 0;
      Connection con = null;
      PreparedStatement pst = null;
      ResultSet Reg = null;

      try {
         con = Conectar.getConexion();
         String sql = "select count(*) from detalle_pedido where nropedido='"+n+"'";
         System.out.println(sql);
         pst = con.prepareStatement(sql);
         Reg = pst.executeQuery();
         if (Reg.next()) {
            Max = Reg.getInt(1);
         }
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

      return Max;
   }
}
