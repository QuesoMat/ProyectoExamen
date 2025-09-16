package Modelo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Boleta {
   String Cliente;
   ArrayList<Detalle> Lista;
   String Bol;
   String Fecha;

   public Boleta() {
   }

   public Boleta(String Fecha, String Cliente, ArrayList<Detalle> Lista) {
      this.Fecha = Fecha;
      this.Cliente = Cliente;
      this.Lista = Lista;
   }

   public String ajusteTxt(String Txt, int L) {
      for(int n = 0; n < L; ++n) {
         Txt = Txt + " ";
      }

      return Txt.substring(0, L);
   }

   public String getBoleta() {
      double TotIC = 0.0D;
      this.Bol = "-".repeat(40) + "\n";
      this.Bol = this.Bol + " D E T A L L E   D E   B O L E T A \n";
      String var10001 = this.Bol;
      this.Bol = var10001 + "-".repeat(40) + "\n";
      this.Bol = this.Bol + "FECHA :" + this.Fecha + "\n";
      var10001 = this.Bol;
      this.Bol = var10001 + "-".repeat(40) + "\n";
      this.Bol = this.Bol + "CLIENTE :" + this.Cliente + "\n";
      var10001 = this.Bol;
      this.Bol = var10001 + "-".repeat(40) + "\n";
      this.Bol = this.Bol + "Producto\tCantidad Precio\tImpComp\n";
      var10001 = this.Bol;
      this.Bol = var10001 + "-".repeat(40) + "\n";

      for(int n = 0; n < this.Lista.size(); ++n) {
         Detalle Deta = (Detalle)this.Lista.get(n);
         double ImpComp = Deta.getCantPed() * Deta.getPrecio();
         TotIC += ImpComp;
         var10001 = this.Bol;
         this.Bol = var10001 + this.ajusteTxt(Deta.getProducto(), 15) + "\t" + Deta.getCantPed() + "\t " + Deta.getPrecio() + "\t" + ImpComp + "\n";
      }

      var10001 = this.Bol;
      this.Bol = var10001 + "-".repeat(40) + "\n";
      this.Bol = this.Bol + "Total de Importes de Compra : S/, " + TotIC;
      return this.Bol;
   }

   public void escribirBoleta() {
      try {
         FileWriter fichero = new FileWriter("Boleta.txt");

         try {
            PrintWriter pw = new PrintWriter(fichero);
            pw.println(this.getBoleta());
         } catch (Throwable var5) {
            try {
               fichero.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         fichero.close();
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }
}
