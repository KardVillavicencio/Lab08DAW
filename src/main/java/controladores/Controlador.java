/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Realizar la validación de usuario y contraseña (esto dependerá de tu lógica de autenticación)
        boolean validacionExitosa = validarUsuarioYContraseña(username, password);

        if (validacionExitosa) {
            // Si la autenticación es exitosa, establecer una sesión
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redireccionar a la página de inicio
            response.sendRedirect("inicio.jsp");
        } else {
            // Si la autenticación falla, redireccionar de nuevo a la página de inicio de sesión con un mensaje de error
            request.setAttribute("mensajeError", "Nombre de usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    // Método ficticio para validar el usuario y la contraseña (debes implementar tu propia lógica)
    private boolean validarUsuarioYContraseña(String username, String password) {
        // Aquí debes implementar la lógica real de validación, por ejemplo, consultando una base de datos.
        // En este ejemplo, siempre se considerará como exitosa.
        return true;
    }
}
