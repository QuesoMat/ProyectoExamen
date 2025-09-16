package Controlador;

import Modelo.Categoria;
import java.util.ArrayList;

public interface Control_Categorias {
   Categoria leerCategoriaId(String var1);

   Categoria leerCategoriaNb(String var1);

   int añadirCategoria(Categoria var1);

   int actualizarCategoria(Categoria var1);

   int eliminarCategoria(int var1);

   ArrayList<Categoria> Lista();
}
