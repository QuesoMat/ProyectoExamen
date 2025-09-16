package Modelo;

public class Cliente {
   private String IdCliente;
   private String Cliente;
   private String DNI;
   private String RUC;

   public Cliente() {
   }

   public Cliente(String IdCliente, String Cliente, String DNI, String RUC) {
      this.IdCliente = IdCliente;
      this.Cliente = Cliente;
      this.DNI = DNI;
      this.RUC = RUC;
   }

   public String getIdCliente() {
      return this.IdCliente;
   }

   public void setIdCliente(String IdCliente) {
      this.IdCliente = IdCliente;
   }

   public String getCliente() {
      return this.Cliente;
   }

   public void setCliente(String Cliente) {
      this.Cliente = Cliente;
   }

   public String getDNI() {
      return this.DNI;
   }

   public void setDNI(String DNI) {
      this.DNI = DNI;
   }

   public String getRUC() {
      return this.RUC;
   }

   public void setRUC(String RUC) {
      this.RUC = RUC;
   }
}
