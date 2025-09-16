package Controlador;

import Modelo.Detalle;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class CtrlVistas {
   public static DefaultTableModel getModeloDetalle(ArrayList<Detalle> Lista) {
      DefaultTableModel Modelo = new DefaultTableModel();
      String[] etiquetas = new String[]{"PRODUCTO", "IMP.COMP", "IMP.DSCTO", "IMP.PAGO"};
      Modelo.setColumnIdentifiers(etiquetas);
      Object[] datosFila = new Object[4];

      for(int n = 0; n < Lista.size(); ++n) {
         Detalle Reg = (Detalle)Lista.get(n);
         datosFila[0] = Reg.getProducto();
         double IpComp = Reg.getPrecio() * Reg.getCantPed();
         double IpDscto = IpComp * Reg.getDescuento();
         double IpPago = IpComp - IpDscto;
         datosFila[1] = IpComp;
         datosFila[2] = IpDscto;
         datosFila[3] = IpPago;
         Modelo.addRow(datosFila);
      }

      return Modelo;
   }

   public static DefaultTableModel getModeloTbl(String Sql) {
      DefaultTableModel Modelo = new DefaultTableModel();
      Statement Acceso = null;

      try {
         Acceso = Conectar.getConexion().createStatement();
         ResultSet Consulta = null;
         Consulta = Acceso.executeQuery(Sql);
         ResultSetMetaData rsmetadatos = Consulta.getMetaData();
         int Cols = rsmetadatos.getColumnCount();
         String[] etiquetas = new String[Cols];

         for(int i = 1; i <= Cols; ++i) {
            etiquetas[i - 1] = rsmetadatos.getColumnLabel(i);
         }

         Modelo.setColumnIdentifiers(etiquetas);
         Object[] datosFila = new Object[Cols];

         while(Consulta.next()) {
            for(int i = 0; i < Cols; ++i) {
               datosFila[i] = Consulta.getString(i + 1);
            }

            Modelo.addRow(datosFila);
         }
      } catch (SQLException var9) {
         //System.out.println(String.valueOf(var9).makeConcatWithConstants<invokedynamic>(String.valueOf(var9)));
      }

      return Modelo;
   }

   public static DefaultComboBoxModel getModeloCbo(String Sql, int col) {
      DefaultComboBoxModel Modelo = new DefaultComboBoxModel();
      Statement Acceso = null;

      try {
         Acceso = Conectar.getConexion().createStatement();
         ResultSet Consulta = null;
         Consulta = Acceso.executeQuery(Sql);

         while(Consulta.next()) {
            Modelo.addElement(Consulta.getString(col));
         }
      } catch (SQLException var5) {
         //System.out.println(String.valueOf(var5).makeConcatWithConstants<invokedynamic>(String.valueOf(var5)));
      }

      return Modelo;
   }

   public static DefaultListModel getModeloLst(String Sql, int col) {
      DefaultListModel Modelo = new DefaultListModel();
      Statement Acceso = null;

      try {
         Acceso = Conectar.getConexion().createStatement();
         ResultSet Consulta = null;
         Consulta = Acceso.executeQuery(Sql);

         while(Consulta.next()) {
            Modelo.addElement(Consulta.getString(col));
         }
      } catch (SQLException var5) {
         //System.out.println(String.valueOf(var5).makeConcatWithConstants<invokedynamic>(String.valueOf(var5)));
      }

      return Modelo;
   }
}
