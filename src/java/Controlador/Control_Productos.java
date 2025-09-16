package Controlador;

import Modelo.Producto;
import java.util.ArrayList;

public interface Control_Productos {
   Producto leerProducto(int var1);

   int añadirProducto(Producto var1);

   int actualizarProducto(Producto var1);

   int eliminarProducto(int var1);

   ArrayList<Producto> Catalogo(int var1);
}
