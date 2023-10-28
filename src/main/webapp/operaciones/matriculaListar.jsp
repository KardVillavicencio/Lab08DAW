<%-- 
    Document   : matriculaListar
    Created on : 21/10/2023, 09:49:08 PM
    Author     : Carlos
--%>

<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
</head>
<body>
<div class="card" style="width: 80%; margin: 0 auto;">
    <h4 class="display-8">Listado de Matrículas</h4>
    <form method="POST" action="/WebSistema/controladorPrincipal">
        <input type="hidden" name="accion" value="buscarMatriculas">
        <table class="table table-striped">
            <tr>
                <td>
                    Buscar por Nro. Documento: <input name="nro_doc" size="15">
                </td>
                <td>
                    <input type="submit" name="boton" class="btn btn-primary" value="Buscar">
                </td>
            </tr>
        </table>
    </form>
    <table class="table table-striped">
        <tr>
            <th>Código</th>
            <th>Fecha</th>
            <th>Nro. Documento</th>
            <!-- Agrega aquí las columnas que quieras mostrar -->
        </tr>
        <c:forEach items="${matriculas}" var="matricula">
            <tr>
                <td><c:out value="${matricula.codigo}" /></td>
                <td><c:out value="${matricula.fecha}" /></td>
                <td><c:out value="${matricula.nro_doc}" /></td>
                <!-- Agrega aquí las columnas que quieras mostrar -->
            </tr>
        </c:forEach>
    </table>
    
</div>
</body>
</html> 