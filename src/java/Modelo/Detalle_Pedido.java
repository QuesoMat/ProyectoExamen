package Modelo;

public class Detalle_Pedido {
   private int NroPedido;
   private int IdProducto;
   private double Cantidad;
   private double Precio;
   private double Descuento;

   public Detalle_Pedido() {
   }

   public Detalle_Pedido(int NroPedido, int IdProducto, double Cantidad, double Precio, double Descuento) {
      this.NroPedido = NroPedido;
      this.IdProducto = IdProducto;
      this.Cantidad = Cantidad;
      this.Precio = Precio;
      this.Descuento = Descuento;
   }

   public int getNroPedido() {
      return this.NroPedido;
   }

   public void setNroPedido(int NroPedido) {
      this.NroPedido = NroPedido;
   }

   public int getIdProducto() {
      return this.IdProducto;
   }

   public void setIdProducto(int IdProducto) {
      this.IdProducto = IdProducto;
   }

   public double getCantidad() {
      return this.Cantidad;
   }

   public void setCantidad(double Cantidad) {
      this.Cantidad = Cantidad;
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
