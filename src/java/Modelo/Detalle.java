package Modelo;

public class Detalle {
   private int IdProducto;
   private String Producto;
   private double CantPed;
   private double Precio;
   private double Descuento;

   public Detalle() {
   }

   public Detalle(int IdProducto, String Producto, double CantPed, double Precio, double Descuento) {
      this.IdProducto = IdProducto;
      this.Producto = Producto;
      this.CantPed = CantPed;
      this.Precio = Precio;
      this.Descuento = Descuento;
   }

   public int getIdProducto() {
      return this.IdProducto;
   }

   public void setIdProducto(int IdProducto) {
      this.IdProducto = IdProducto;
   }

   public String getProducto() {
      return this.Producto;
   }

   public void setProducto(String Producto) {
      this.Producto = Producto;
   }

   public double getCantPed() {
      return this.CantPed;
   }

   public void setCantPed(double CantPed) {
      this.CantPed = CantPed;
   }

   public double getPrecio() {
      return this.Precio;
   }

   public void setPrecio(double Precio) {
      this.Precio = Precio;
   }

   public double getDescuento() {
      return this.Descuento;
   }

   public void setDescuento(double Descuento) {
      this.Descuento = Descuento;
   }
}
