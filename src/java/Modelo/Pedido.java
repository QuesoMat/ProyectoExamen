package Modelo;

public class Pedido {
   private int NroPedido;
   private String Fecha;
   private String IdCliente;

   public Pedido() {
   }

   public Pedido(int NroPedido, String Fecha, String IdCliente) {
      this.NroPedido = NroPedido;
      this.Fecha = Fecha;
      this.IdCliente = IdCliente;
   }

   public int getNroPedido() {
      return this.NroPedido;
   }

   public void setNroPedido(int NroPedido) {
      this.NroPedido = NroPedido;
   }

   public String getFecha() {
      return this.Fecha;
   }

   public void setFecha(String Fecha) {
      this.Fecha = Fecha;
   }

   public String getIdCliente() {
      return this.IdCliente;
   }

   public void setIdCliente(String IdCliente) {
      this.IdCliente = IdCliente;
   }
}
