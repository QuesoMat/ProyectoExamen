<%@page import="Controlador.*" %>
<%@page import="Modelo.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <style>
    .button {
      background-color: #04AA6D;
      border: none;
      color: white;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
    }
    .btn {
      margin-bottom: 20px;
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
      background: linear-gradient(to bottom, rgba(50, 0, 60, 0.1), rgba(50, 0, 60, 0.5));
      border: 2px solid purple;
    }
  </style>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NEGOCIO WEB</title>
  </head>

  <%! 
    Admin_Ctegorias AdmCat = new Admin_Ctegorias();
    Admin_Productos AdmPrd = new Admin_Productos();
        Admin_Clientes AdmCli = new Admin_Clientes();
    ArrayList<Categoria> Lista = new ArrayList();
    ArrayList<Producto> CatProd = new ArrayList();
    ArrayList<Detalle> lstDetalle = new ArrayList();
        ArrayList<Cliente> lc = new ArrayList();
    Pedido Ped = new Pedido();
    Detalle RegDeta;
    Categoria Cat;
    Producto Prd;
    Cliente cl;
    int idCat;
    double Cantidad;
  %>

  <body>
    <h3>CATÁLOGO DE PRODUCTOS</h3>
    <br><a href="index.html"><button type="button" class="btn">Ir al Inicio</button></a><br>

    <form action="Catalogo_Productos.jsp">
        
        
        Fecha:
        <input type="date" id="fecha" name="fecha">
        <br><br>
        Cliente:
      <select name="lstCliente">
        <%
          lc = AdmCli.Lista();
          for (int n = 0; n < lc.size(); ++n) {
              cl = lc.get(n);
              out.println("<option value=" + cl.getIdCliente() + ">" + cl.getCliente() + "</option>");
          }
        %>
      </select><br><br>
        
      Categorías:
      <select name="lstCategorias">
        <%
          Lista = AdmCat.Lista();
          for (int n = 0; n < Lista.size(); ++n) {
              Cat = Lista.get(n);
              out.println("<option value=" + Cat.getIdCategoria() + ">" + Cat.getCategoria() + "</option>");
          }
        %>
      </select>
      <input type="submit" value="Ver Productos" name="btnVerPrd" />
      <br><br>

      <%
        if (request.getParameter("btnVerPrd") != null || request.getParameter("verPedido") != null || request.getParameter("btnGuardarPedido") != null) {
            idCat = Integer.parseInt(request.getParameter("lstCategorias") != null ? request.getParameter("lstCategorias") : request.getParameter("catSeleccionada"));
            CatProd = AdmPrd.Catalogo(idCat);
      %>

      <input type="hidden" name="catSeleccionada" value="<%=idCat%>">

      <table border="1">
        <thead>
          <tr>
            <th>CÓDIGO</th>
            <th>PRODUCTO</th>
            <th>PRESENTACIÓN</th>
            <th>PRECIO</th>
            <th>STOCK</th>
            <th>CANT. PED.</th>
          </tr>
        </thead>
        <tbody>
          <%
            for (int n = 0; n < CatProd.size(); ++n) {
                Prd = CatProd.get(n);
                out.println("<tr>");
                out.println("<td>" + Prd.getIdProducto() + "</td>");
                out.println("<td>" + Prd.getProducto() + "</td>");
                out.println("<td>" + Prd.getPresentacion() + "</td>");
                out.println("<td>" + Prd.getPrecio() + "</td>");
                out.println("<td>" + Prd.getExistencia() + "</td>");
                out.println("<td><input type='text' name='CantPed" + n + "' value='0' size='10' /></td>");
                out.println("</tr>");
            }
          %>
        </tbody>
      </table>

      <br>
      <input type="submit" value="Ver Pedido" name="verPedido" />
      <input type="submit" value="Guardar Pedido" name="btnGuardarPedido" />
      <br><br>

      <table border="1">
        <thead>
          <tr>
            <th>ITEM</th>
            <th>PRODUCTO</th>
            <th>PRESENTACIÓN</th>
            <th>PRECIO</th>
            <th>CANTIDAD</th>
            <th>IMPORTE</th>
          </tr>
        </thead>
        <tbody>
          <%
            if (request.getParameter("verPedido") != null || request.getParameter("btnGuardarPedido") != null) {
                lstDetalle.clear(); // evitar duplicados
                for (int n = 0; n < CatProd.size(); ++n) {
                    Prd = CatProd.get(n);
                    String param = request.getParameter("CantPed" + n);
                    if (param != null) {
                        try {
                            Cantidad = Double.parseDouble(param);
                            if (Cantidad > 0) {
                                RegDeta = new Detalle(Prd.getIdProducto(), Prd.getProducto(), Cantidad, Prd.getPrecio(), 0);
                                lstDetalle.add(RegDeta);
                            }
                        } catch (Exception e) {
                            
                        }
                    }
                }

                for (int n = 0; n < lstDetalle.size(); ++n) {
                    RegDeta = lstDetalle.get(n);
                    Prd = AdmPrd.leerProducto(RegDeta.getIdProducto());
                    double impComp = Prd.getPrecio() * RegDeta.getCantPed();
                    out.println("<tr>");
                    out.println("<td>" + (n + 1) + "</td>");
                    out.println("<td>" + Prd.getProducto() + "</td>");
                    out.println("<td>" + Prd.getPresentacion() + "</td>");
                    out.println("<td>" + Prd.getPrecio() + "</td>");
                    out.println("<td>" + RegDeta.getCantPed() + "</td>");
                    out.println("<td>" + impComp + "</td>");
                    out.println("</tr>");
                }
            }

            if (request.getParameter("btnGuardarPedido") != null && !lstDetalle.isEmpty()) {
                String fechaPedido = request.getParameter("fecha");
                String idCliente = request.getParameter("lstCliente");
                if (fechaPedido != null && !fechaPedido.isEmpty() && idCliente != null && !idCliente.isEmpty()) {
                    try {
                     int bn=1;
                        Admin_Pedidos ap = new Admin_Pedidos();
                        for (int n = 0; n < lstDetalle.size(); ++n) {
                                Detalle de=lstDetalle.get(n);
                                double cantAg=de.getCantPed();
                                Producto prd=AdmPrd.leerProducto(de.getIdProducto());
                                double cantAc=prd.getExistencia();
                                if(cantAg>cantAc){
                                    bn=0;
                                    out.println("<script>alert('Error. No puedes pedir más cantidad de producto\n de la que existe en el inventario.');</script>");
                                    break;
                                }else{                                
                                    AdmPrd.acPrd(cantAc-cantAg,de.getIdProducto());
                                }
                        }
                        if(bn==1){
                            int nroPedido =ap.getCantPed2()+1; 
                            Ped = new Pedido(nroPedido, fechaPedido, idCliente); 
                            ap.guardarPedido(Ped, lstDetalle);
                            out.println("<script>alert('Pedido guardado exitosamente.\\nNúmero de pedido: " + nroPedido + "');</script>");
                        }
                    } catch (Exception e) {
                        out.println("<script>alert('Error al guardar el pedido: " + e.getMessage() + "');</script>");
                    }
                } else {
                    out.println("<script>alert('Debe seleccionar una fecha y un cliente antes de guardar el pedido.');</script>");
                }
            }
          %>
        </tbody>
      </table>
      <% } %>
    </form>
  </body>
</html>
