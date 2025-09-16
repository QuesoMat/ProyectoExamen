package sysnegocio;

import Controlador.Admin_Clientes;
import Controlador.Conectar;

public class SysNegocio {
   public static void main(String[] args) {
      Conectar.getConexion();
      Admin_Clientes AC = new Admin_Clientes();
      AC.añadirCliente("*", "Polleria Criolla El Papita", "90989876", "34565676789");
   }
}
