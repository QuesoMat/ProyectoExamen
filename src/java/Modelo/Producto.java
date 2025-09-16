package Modelo;

public class Producto {
   private int IdProducto;
   private int IdCategoria;
   private String Producto;
   private String Presentacion;
   private double Precio;
   private double Existencia;

   public Producto() {
   }

   public Producto(int IdProducto, int IdCategoria, String Producto, String Presentacion, double Precio, double Existencia) {
      this.IdProducto = IdProducto;
      this.IdCategoria = IdCategoria;
      this.Producto = Producto;
      this.Presentacion = Presentacion;
      this.Precio = Precio;
      this.Existencia = Existencia;
   }

   public int getIdProducto() {
      return this.IdProducto;
   }

   public void setIdProducto(int IdProducto) {
      this.IdProducto = IdProducto;
   }

   public int getIdCategoria() {
      return this.IdCategoria;
   }

   public void setIdCategoria(int IdCategoria) {
      this.IdCategoria = IdCategoria;
   }

   public String getProducto() {
      return this.Producto;
   }

   public void setProducto(String Producto) {
      this.Producto = Producto;
   }

   public String getPresentacion() {
      return this.Presentacion;
   }

   public void setPresentacion(String Presentacion) {
      this.Presentacion = Presentacion;
   }

   public double getPrecio() {
      return this.Precio;
   }

   public void setPrecio(double Precio) {
      this.Precio = Precio;
   }

   public double getExistencia() {
      return this.Existencia;
   }

   public void setExistencia(double Existencia) {
      this.Existencia = Existencia;
   }
}
