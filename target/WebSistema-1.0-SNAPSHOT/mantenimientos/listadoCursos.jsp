<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
        <div class="card" style="width: 50rem;padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Listado de Cursos</h4>
        <form method="POST" action="/WebSistema/controladorPrincipal">
            <input type="HIDDEN" name="accion" value="nuevoEliminarCurso">
            <input type="submit" name="boton" class="btn btn-primary" value="Nuevo Curso">
            <input type="submit" name="boton" class="btn btn-dark" value="Eliminar Curso">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Codigo</th>
                <th scope="col">Nombre</th>
                <th scope="col">Costo</th>
                <th scope="col">Duracion</th>
                <th scope="col">Sesiones</th>
                <th scope="col">Capacidad</th>
                <th scope="col">Inscritos</th>
                <th scope="col">Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${arrCursos}" var="curso">
            <tr>
            <td><input type="checkbox" name="xcod${status.index}"
                  value="<c:out value='${curso.codigo}'/>" ></td>
            <td><c:out value='${curso.codigo}'/></td>
            <td>
                <a href="/WebSistema/controladorPrincipal?accion=modificarCurso&xcod=<c:out value="${curso.codigo}"/>">
                    <c:out value="${curso.nombre}" /></a></td> 
            <td><c:out value="${curso.costo}" /></td>
            <td><c:out value="${curso.duracion}" /></td>
            <td><c:out value="${curso.sesiones}" /></td>
            <td><c:out value="${curso.capacidad}" /></td>
            <td><c:out value="${curso.inscritos}" /></td>
            <td><c:out value="${curso.estado}" /></td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>