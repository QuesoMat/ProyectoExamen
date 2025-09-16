package Modelo;

public class Categoria {
   private int IdCategoria;
   private String Categoria;
   private double Descuento;

   public Categoria() {
   }

   public Categoria(int IdCategoria, String Categoria, double Descuento) {
      this.IdCategoria = IdCategoria;
      this.Categoria = Categoria;
      this.Descuento = Descuento;
   }

   public int getIdCategoria() {
      return this.IdCategoria;
   }

   public void setIdCategoria(int IdCategoria) {
      this.IdCategoria = IdCategoria;
   }

   public String getCategoria() {
      return this.Categoria;
   }

   public void setCategoria(String Categoria) {
      this.Categoria = Categoria;
   }

   public double getDescuento() {
      return this.Descuento;
   }

   public void setDescuento(double Descuento) {
      this.Descuento = Descuento;
   }
}
