package Controlador;

import Modelo.Cliente;
import java.util.ArrayList;

public interface Control_Clientes {
   Cliente leerClienteId(String var1);

   Cliente leerClienteNb(String var1);

   int añadirCliente(Cliente var1);

   int añadirCliente(String var1, String var2, String var3, String var4);

   int actualizarCliente(Cliente var1);

   int eliminarCliente(int var1);

   ArrayList<Cliente> Lista();
}
