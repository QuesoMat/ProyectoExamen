<%@page import="Controlador.*" %>
<%@page import="Modelo.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        .form-group input[type="text"], .form-group input[type="date"], .form-group select, .form-group input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
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
    </style>
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
    <div class="container">
        <h3>CATÁLOGO DE PRODUCTOS</h3>
        <div class="btn-group">
            <a href="index.html" class="button link-button">Ir al Inicio</a>
        </div>

        <form action="Catalogo_Productos.jsp">
            <div class="form-group">
                <label for="fecha">Fecha:</label>
                <input type="date" id="fecha" name="fecha" value="<%= request.getParameter("fecha") != null ? request.getParameter("fecha") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="lstCliente">Cliente:</label>
                <select name="lstCliente" id="lstCliente">
                    <%
                        lc = AdmCli.Lista();
                        for (int n = 0; n < lc.size(); ++n) {
                            cl = lc.get(n);
                            String selected = (request.getParameter("lstCliente") != null && request.getParameter("lstCliente").equals(String.valueOf(cl.getIdCliente()))) ? "selected" : "";
                            out.println("<option value=\"" + cl.getIdCliente() + "\" " + selected + ">" + cl.getCliente() + "</option>");
                        }
                    %>
                </select>
            </div>
            
            <div class="form-group">
                <label for="lstCategorias">Categorías:</label>
                <select name="lstCategorias" id="lstCategorias">
                    <%
                        Lista = AdmCat.Lista();
                        for (int n = 0; n < Lista.size(); ++n) {
                            Cat = Lista.get(n);
                            String selected = (request.getParameter("lstCategorias") != null && request.getParameter("lstCategorias").equals(String.valueOf(Cat.getIdCategoria()))) ? "selected" : "";
                            out.println("<option value=\"" + Cat.getIdCategoria() + "\" " + selected + ">" + Cat.getCategoria() + "</option>");
                        }
                    %>
                </select>
            </div>
            
            <input type="submit" value="Ver Productos" name="btnVerPrd" />
            <br><br>

            <%
                if (request.getParameter("btnVerPrd") != null || request.getParameter("verPedido") != null || request.getParameter("btnGuardarPedido") != null) {
                    idCat = Integer.parseInt(request.getParameter("lstCategorias") != null ? request.getParameter("lstCategorias") : request.getParameter("catSeleccionada"));
                    CatProd = AdmPrd.Catalogo(idCat);
            %>

            <input type="hidden" name="catSeleccionada" value="<%=idCat%>">
            <input type="hidden" name="fecha" value="<%=request.getParameter("fecha")%>">
            <input type="hidden" name="lstCliente" value="<%=request.getParameter("lstCliente")%>">

            <div class="table-container">
                <table class="styled-table">
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
                                out.println("<td><input type='number' name='CantPed" + n + "' value='0' min='0' size='10' /></td>");
                                out.println("</tr>");
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <br>
            <input type="submit" value="Ver Pedido" name="verPedido" />
            <input type="submit" value="Guardar Pedido" name="btnGuardarPedido" />
            <br><br>

            <div class="table-container">
                <table class="styled-table">
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
                                            // Manejar el error de conversión si es necesario
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
                                                out.println("<p class='alert-message alert-error'>Error. No puedes pedir más cantidad de producto de la que existe en el inventario.</p>");
                                                break;
                                            }else{
                                                AdmPrd.acPrd(cantAc-cantAg,de.getIdProducto());
                                            }
                                        }
                                        if(bn==1){
                                            int nroPedido =ap.getCantPed2()+1;
                                            Ped = new Pedido(nroPedido, fechaPedido, idCliente);
                                            ap.guardarPedido(Ped, lstDetalle);
                                            out.println("<p class='alert-message alert-success'>Pedido guardado exitosamente.<br>Número de pedido: " + nroPedido + "</p>");
                                        }
                                    } catch (Exception e) {
                                        out.println("<p class='alert-message alert-error'>Error al guardar el pedido: " + e.getMessage() + "</p>");
                                    }
                                } else {
                                    out.println("<p class='alert-message alert-error'>Debe seleccionar una fecha y un cliente antes de guardar el pedido.</p>");
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <% } %>
        </form>
    </div>
</body>
</html>