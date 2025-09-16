package Controlador;

import Modelo.Detalle;
import Modelo.Pedido;
import java.util.ArrayList;

public interface Control_Pedidos {
   int guardarPedido(Pedido var1, ArrayList<Detalle> var2);

   int getMaxPed();
}
