<%@page import="Controlador.*" %>
<%@page import="Modelo.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NEGOCIO WEB</title>
    <style>
        input[type="submit"] {
            margin-bottom: 20px;
            margin-right: 10px;
        }
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
        .table {
            table-layout: fixed;
            width: 95%;
            border-collapse: collapse;
            border: 3px solid purple;
        }
        thead th, tfoot th, tfoot td {
            background: linear-gradient(to bottom, rgba(50, 0, 60, 0.1), rgba(50, 0, 60, 0.5));
            border: 2px solid purple;
        }
    </style>
</head>
<body>

<form action="Consultar_Pedido.jsp" method="get">
    <h3>CONSULTAR PEDIDO</h3><br>
    <a href="index.html"><button type="button">Ir al Inicio</button></a><br><br><br>
    <label>Número de Pedido: </label>
    <input type="text" name="num" id="nro" placeholder="Ingresar número">
    <br><br>
    <input type="submit" value="Consultar" name="btnCons" />
</form>
<br>

<%
try {
    Admin_Pedidos ap = new Admin_Pedidos();
    Admin_Productos apr = new Admin_Productos();
    if(request.getParameter("btnActualizar") != null){
        String idProducto = request.getParameter("pro");
        String idPedido = request.getParameter("num");
        
        if (idProducto != null && idPedido != null) {
          Detalle_Pedido dp=ap.getDP(Integer.parseInt(idPedido),Integer.parseInt(idProducto));
          double cantAg=dp.getCantidad();
          Producto prd=apr.leerProducto(Integer.parseInt(idProducto));
          double cantAc=prd.getExistencia();
          apr.acPrd(cantAc+cantAg,Integer.parseInt(idProducto));
            ap.eliminarDP(Integer.parseInt(idProducto),Integer.parseInt(idPedido));
            if(ap.getCantPedDP(Integer.parseInt(idPedido))<=0){
                ap.eliminar(Integer.parseInt(idPedido));
            }
        }
    }
    if (request.getParameter("btnCons") != null ) { 
        int nroPedido = Integer.parseInt(request.getParameter("num"));
        
        Admin_Clientes ac = new Admin_Clientes();
        Admin_Ctegorias act = new Admin_Ctegorias();

        Pedido p = ap.leerPedido(nroPedido);
        Cliente c = ac.leerCliente2(p.getIdCliente());
%>

<label>Código Pedido: <%= p.getNroPedido() %></label><br>
<label>Nombre Cliente: <%= c.getCliente() %></label><br>
<label>Fecha: <%= p.getFecha() %></label><br><br>

<table border="1">
    <thead>
        <tr>
            <th>PRODUCTO</th>
            <th>CANTIDAD</th>
            <th>PRECIO IMPORTE</th>
            <th>IMP. DSCTO</th>
            <th>IMP. PAGO</th>
            <th>OPCIONES</th>
        </tr>
    </thead>
    <tbody>
<%
        ArrayList<Detalle_Pedido> ldp = ap.Lista2(String.valueOf(nroPedido));
        for (Detalle_Pedido x : ldp) {
            Producto pt = apr.leerProducto(x.getIdProducto());
            Categoria ct = act.leerCategoria2(pt.getIdCategoria());
            double imp = x.getCantidad() * pt.getPrecio();
            double impg = imp - ct.getDescuento();
%>
        <tr>
            <td><%= pt.getProducto() %></td>
            <td><%= x.getCantidad() %></td>
            <td><%= imp %></td>
            <td><%= ct.getDescuento() %></td>
            <td><%= impg %></td>
            <td>
                <a href="Consultar_Pedido.jsp?accion=eliminar&idpr=<%= pt.getIdProducto() %>&idp=<%= p.getNroPedido() %>&num=<%= p.getNroPedido() %>&btnCons=1"
                   title="Eliminar"
                   style="padding:5px; background:red; color:white; text-decoration:none;">X</a>
            </td>
        </tr>
<%
        }
%>
    </tbody>
</table>
<br>


<form method="get" action="Consultar_Pedido.jsp">
    <input type="hidden" name="btnActualizar" value="1" />
    <input type="hidden" name="num" value="<%=request.getParameter("idp") %>" />
    <input type="hidden" name="pro" value="<%= request.getParameter("idpr")  %>" />
    <input type="submit" value="Actualizar Pedido" />
</form>

<%
    } 
} catch (Exception e) {
    out.println("<p style='color:red;'>Error: No se encontro ese número de pedido.</p>");
}
%>

</body>
</html>
