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
      
      input[type="submit" ]{
          margin-bottom: 20px;
          margin-right: 10px;
      }
    .button {
  background-color: #04AA6D; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
    .table {
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
        Admin_Clientes ac=new Admin_Clientes();
    %>
    <body>
        <h3>LISTA DE CLIENTES</h3><br>
        <form action="Lista_Clientes.jsp">
        <input type="submit" value="Ver Clientes" name="btnVerCli" />
        <a href="index.html"> <button type="button">Ir al Inicio</button></a>
         <br>
        <table border="1">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE CLIENTE</th>
                    <th>DNI</th>
                    <th>RUC</th>
                </tr>
            </thead>
            <tbody>
        <%
           try{
            if(request.getParameter("btnVerCli")!=null){
                ArrayList<Cliente>lc=ac.Lista();
                for(int n=0;n<lc.size();++n){
                    Cliente c=lc.get(n);
                    out.println("<tr>");
                    out.println("<td>"+c.getIdCliente()+"</td>");
                    out.println("<td>"+c.getCliente()+"</td>");
                    out.println("<td>"+c.getDNI()+"</td>");
                    out.println("<td>"+c.getRUC()+"</td>");   
                    out.println("</tr>");
                }
            }
            }catch(Exception e){
            System.out.println(""+e);
            }
        %>
         </tbody>
        </table> 
         <br>
         
        </form>      
    </body>
</html>
