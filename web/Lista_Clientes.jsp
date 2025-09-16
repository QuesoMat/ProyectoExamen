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
    </style>
</head>
<%!
    Admin_Clientes ac=new Admin_Clientes();
%>
<body>
    <div class="container">
        <h3>LISTA DE CLIENTES</h3><br>
        <div class="btn-group">
            <form action="Lista_Clientes.jsp">
                <input type="submit" value="Ver Clientes" name="btnVerCli" class="button" />
            </form>
            <a href="index.html" class="button link-button">Ir al Inicio</a>
        </div>
        
        <div class="table-container">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>CÓDIGO</th>
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
        </div>
    </div>
</body>
</html>