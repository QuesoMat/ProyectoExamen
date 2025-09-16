<%-- 
    Document   : Catalogo_Productos
    Created on : 30 may. 2025, 11:15:11
    Author     : DOCENTE
--%>
<%@page import="Controlador.*" %>
<%@page import="Modelo.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <Style>
    table {
       table-layout: fixed;
       width: 95%;
       border-collapse: collapse;
       border: 3px solid purple;       
    }
thead th,
tfoot th,
tfoot td {
  background: linear-gradient(
    to bottom,
    rgba(50, 0, 60, 0.1),
    rgba(50, 0, 60, 0.5)
  );
  border: 2px solid purple;
}
</Style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NEGOCIO WEB</title>
    </head>
    <%!
        Admin_Ctegorias AdmCat=new Admin_Ctegorias();
        Admin_Productos AdmPrd=new Admin_Productos();
        ArrayList<Categoria> Lista=new ArrayList();
        ArrayList<Producto> CatProd=new ArrayList();
        Categoria Cat;
        Producto  Prd;
        String Categoria;
        int idCat;
        double Cantidad;
    %>
    <body>
        <h3>CATALOGO DE PRODUCTOS</h3>
        <form action="Catalogo_Productos.jsp">
        Categorias:
            <select name="lstCategorias">    
        <%
            Lista=AdmCat.Lista();
            for(int n=0;n<Lista.size();++n){
                Cat=Lista.get(n);
                out.println("<option Value="+Cat.getIdCategoria()+">"
                           +Cat.getCategoria()+"</option>");
            }
        %>
        </select>
       
        <input type="submit" value="Ver Productos" name="btnVerPrd" />
         <br>
        <table border="1">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>PRODUCTO</th>
                    <th>PRESENTACION</th>
                    <th>PRECIO</th>
                    <th>STOCK</th>
                    <th>CANTIDAD. PED.</th>
                </tr>
            </thead>
            <tbody>
        <%
            if(request.getParameter("btnVerPrd")!=null){
                idCat=Integer.parseInt(request.getParameter("lstCategorias"));
                CatProd=AdmPrd.Catalogo(idCat);
                for(int n=0;n<CatProd.size();++n){
                 Prd=CatProd.get(n);
                 out.println("<tr>");
                    out.println("<td>"+Prd.getIdProducto()+"</td>");
                    out.println("<td>"+Prd.getProducto()+"</td>");
                    out.println("<td>"+Prd.getPresentacion()+"</td>");
                    out.println("<td>"+Prd.getPrecio()+"</td>");
                    out.println("<td>"+Prd.getExistencia()+"</td>");
                    out.println("<td><input type=text name=CantPed"+n+" value=0 size=10 /></td>");
                 out.println("</tr>");               
                }
                
            }
        %>
         </tbody>
        </table> 
         <br>
         <input type="submit" value="Ver Pedido" name="verPedido" />  
         <br>
         <%
             if(request.getParameter("verPedido")!=null){
                for(int n=0;n<CatProd.size();++n){
                    Cantidad=Double.parseDouble(request.getParameter("CantPed"+n));
                    if(Cantidad>0){
                        out.println(CatProd.get(n).getProducto()+" - "+Cantidad+"<br>");
                    }
                }
             }
         %>
         
        </form>      
    </body>
</html>
