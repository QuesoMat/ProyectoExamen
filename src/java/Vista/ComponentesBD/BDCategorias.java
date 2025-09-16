package Vista.ComponentesBD;

import Controlador.Admin_Ctegorias;
import Controlador.CtrlVistas;
import Modelo.Categoria;
import java.awt.Font;
import javax.swing.JComboBox;

public class BDCategorias extends JComboBox {
   String Sql = "Select * from Categorias";
   int Col = 2;
   Font font = new Font("Verdana", 1, 18);
   Admin_Ctegorias ACat;

   public BDCategorias() {
      this.initComponents();
      this.ACat = new Admin_Ctegorias();
      this.setModel(CtrlVistas.getModeloCbo(this.Sql, this.Col));
      this.setFont(this.font);
   }

   public double getDescuento() {
      String Categoria = this.getSelectedItem().toString();
      Categoria Cat = this.ACat.leerCategoriaNb(Categoria);
      double Descuento = Cat.getDescuento();
      return Descuento;
   }

   private void initComponents() {
   }
}
