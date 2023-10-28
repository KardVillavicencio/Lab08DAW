<%-- 
    Document   : editarCursos
    Created on : 17 oct. 2023, 01:44:33
    Author     : 51994
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
</head>
<body>
<div class="card" style="width: 50rem; padding: 30px;">
    <h4 class="display-8"><c:out value='${titulo}'/></h4>
    <form name="mod_opcion" method="POST" action="/WebSistema/controladorPrincipal">
        <input type="hidden" name="accion" value="grabarCurso">
        <input type="hidden" name="operacion" value='<c:out value='${operacion}'/>'>
        <input type="hidden" name="xcod" value="<c:out value='${curso.codigo}'/>">
        <table class="table table-striped table-hover">
            <tbody>
                <tr>
                    <th>Código:</th>
                    <td><c:out value='${curso.codigo}'/></td>
                </tr>
                <tr>
                    <th>Nombre:</th>
                    <td><input name="xnom" value="<c:out value='${curso.nombre}'/>" size="60"></td>
                </tr>
                <!-- Agrega más campos según la estructura de tu clase Curso -->
                <tr>
                    <th>Costo:</th>
                    <td><input name="xcosto" value="<c:out value='${curso.costo}'/>" size="30"></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" name="boton" class="btn btn-primary" value="GRABAR">
        <input type="submit" name="boton" class="btn btn-dark" value="CANCELAR">
    </form>
</div>
</body>
</html>