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
        :root {
            --primary-color: #4a90e2;
            --secondary-color: #f5a623;
            --background-color: #f4f7f6;
            --text-color: #333;
            --card-bg: #fff;
            --border-color: #e0e0e0;
            --box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .container {
            max-width: 900px;
            width: 100%;
            background-color: var(--card-bg);
            padding: 30px;
            border-radius: 12px;
            box-shadow: var(--box-shadow);
            margin-top: 20px;
        }
        h3 {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 25px;
            border-bottom: 2px solid var(--border-color);
            padding-bottom: 15px;
        }
        .btn-group {
            display: flex;
            justify-content: center;
            gap: 15px;
            flex-wrap: wrap;
            margin-bottom: 25px;
        }
        .button, input[type="submit"], button {
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
            text-decoration: none;
            text-align: center;
            color: #fff;
            background-color: var(--primary-color);
            font-weight: bold;
        }
        .button:hover, input[type="submit"]:hover, button:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        a.link-button {
            display: inline-block;
            background-color: var(--secondary-color);
        }
        .link-button:hover {
            background-color: #e59400;
        }
        .table-container {
            overflow-x: auto;
            margin-top: 25px;
        }
        .styled-table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
        }
        .styled-table th, .styled-table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }
        .styled-table thead th {
            background-color: var(--primary-color);
            color: white;
        }
        .styled-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .styled-table tbody tr:hover {
            background-color: #e8f4f8;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        .form-group input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            font-size: 16px;
        }
        .alert-message {
            padding: 15px;
            border-radius: 8px;
            margin-top: 20px;
            font-weight: bold;
            text-align: center;
        }
        .alert-error {
            background-color: #ffebee;
            color: #c62828;
            border: 1px solid #c62828;
        }
        .alert-success {
            background-color: #e8f5e9;
            color: #2e7d32;
            border: 1px solid #2e7d32;
        }
        .small-link {
            padding: 8px 15px;
            background-color: #e74c3c;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: background-color 0.2s;
        }
        .small-link:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="Consultar_Pedido.jsp" method="get">
        <h3>CONSULTAR PEDIDO</h3><br>
        <div class="btn-group">
            <a href="index.html" class="button link-button">Ir al Inicio</a>
        </div>
        <div class="form-group">
            <label for="nro">Número de Pedido:</label>
            <input type="text" name="num" id="nro" placeholder="Ingresar número">
        </div>
        <input type="submit" value="Consultar" name="btnCons" />
    </form>
    
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

    <div class="info-pedido">
        <label><b>Código Pedido:</b> <%= p.getNroPedido() %></label><br>
        <label><b>Nombre Cliente:</b> <%= c.getCliente() %></label><br>
        <label><b>Fecha:</b> <%= p.getFecha() %></label><br><br>
    </div>
    <div class="table-container">
        <table class="styled-table">
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
                        <a href="Consultar_Pedido.jsp?btnActualizar=1&pro=<%= pt.getIdProducto() %>&num=<%= p.getNroPedido() %>"
                           class="small-link" title="Eliminar">X</a>
                    </td>
                </tr>
    <%
            }
    %>
            </tbody>
        </table>
    </div>

    <form method="get" action="Consultar_Pedido.jsp">
        <input type="hidden" name="btnActualizar" value="1" />
        <input type="hidden" name="num" value="<%=request.getParameter("num") %>" />
        <input type="hidden" name="pro" value="<%= request.getParameter("idpr")  %>" />
        <br><br>
        <input type="submit" value="Actualizar Pedido" />
    </form>

    <%
        }  
    } catch (Exception e) {
        out.println("<p class='alert-message alert-error'>Error: No se encontró ese número de pedido.</p>");
    }
    %>
</div>
</body>
</html>