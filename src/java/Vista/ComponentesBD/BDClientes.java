package Vista.ComponentesBD;

import Controlador.Admin_Clientes;
import Controlador.CtrlVistas;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class BDClientes extends JList {
   String Sql = "Select * From Clientes";
   int Col = 2;
   Font font = new Font("Verdana", 1, 15);
   Admin_Clientes AC;
   String Cliente;
   String IdCliente;

   public BDClientes() {
      this.initComponents();
      this.AC = new Admin_Clientes();
      this.setModel(CtrlVistas.getModeloLst(this.Sql, this.Col));
      this.setFont(this.font);
   }

   public String getIdCliente() {
      this.IdCliente = "0";

      try {
         this.Cliente = this.getSelectedValue().toString();
         this.IdCliente = this.AC.leerClienteNb(this.Cliente).getIdCliente();
      } catch (Exception var2) {
         JOptionPane.showMessageDialog((Component)null, "No se ha seleccionado el cliente");
      }

      return this.IdCliente;
   }

   private void initComponents() {
   }
}
