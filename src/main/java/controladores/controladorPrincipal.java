package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlumnoDAOImpl;
import dao.CursoDAOImpl;
import dao.CursosDAO;
import dao.IAlumnosDAO;
import dao.IMatriculaDAO;
import dao.MatriculaDAOImpl;
import java.math.BigDecimal;
import modelos.Alumnos;
import modelos.Conexion;
import modelos.Cursos;
import modelos.Matriculas;
import modelos.cBaseDatos;

@WebServlet(name = "controladorPrincipal", urlPatterns = {"/controladorPrincipal"})
public class controladorPrincipal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            cBaseDatos objDatos = new cBaseDatos();
            String accion = request.getParameter("accion");
            if (accion == null)
                accion = "bienvenida";
            if (accion.equals("bienvenida"))
                request.getRequestDispatcher("/contenido.html").forward(request, response);
            else if (accion.equals("listadoAreas")) {
                Vector arrAreas = (Vector) objDatos.getAreas();
                request.setAttribute("arrAreas", arrAreas);
                request.getRequestDispatcher("/mantenimientos/listadoAreas.jsp").forward(
                        request, response);
            } else if (accion.equals("NuevoEliminarArea")) {
                if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                    Vector registro = new Vector();
                    registro.add("");
                    registro.add("");
                    registro.add("");
                    registro.add("");

                    request.setAttribute("registro", registro);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nueva Area");
                    request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(
                            request, response);
                } else {
                    String[] datos = request.getParameterValues("xcod");
                    objDatos.eliminarAreas(datos);
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                            request, response);
                }
            } else if (accion.compareTo("GRABAR_AREA") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                    String operacion = request.getParameter("operacion");
                    if (operacion.equals("INSERT")) {
                        String[] datos = new String[3];
                        datos[0] = request.getParameter("xnom");
                        datos[1] = request.getParameter("xabr");
                        datos[2] = request.getParameter("xest");
                        objDatos.grabarNuevaArea(datos);
                    } else {
                        String[] datos = new String[4];
                        datos[0] = request.getParameter("xcod");
                        datos[1] = request.getParameter("xnom");
                        datos[2] = request.getParameter("xabr");
                        datos[3] = request.getParameter("xest");
                        objDatos.modificarArea(datos);
                    }
                }
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                        request, response);
            } else if (accion.compareTo("modificarArea") == 0) {
                String xcod = request.getParameter("xcod");
                Vector registro = objDatos.buscarArea(xcod);

                request.setAttribute("registro", registro);
                request.setAttribute("operacion", "UPDATE");
                request.setAttribute("titulo", "Modificar Area");
                request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(
                        request, response);
            } else if (accion.equals("listadoAlumnos")) {
                List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                IAlumnosDAO dao = new AlumnoDAOImpl();
                arrAlumnos = dao.obtener();
                request.setAttribute("arrAlumnos", arrAlumnos);
                request.getRequestDispatcher("/mantenimientos/listadoAlumnos.jsp").forward(
                        request, response);

            } else if (accion.equals("NuevoEliminarAlumno")) {
                if (request.getParameter("boton").equals("Nuevo Registro")) {
                    Alumnos alumno = new Alumnos();
                    request.setAttribute("alumno", alumno);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nuevo Alumno");
                    request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(request, response);
                } else {
                    /* Código para eliminar aquí */
                    String[] codigos = request.getParameterValues("xcod");
                    IAlumnosDAO dao = new AlumnoDAOImpl();

                    for (String codigo : codigos) {
                        dao.eliminar(Integer.parseInt(codigo));
                    }

                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(request, response);
                }
            } else if (accion.compareTo("GRABAR_ALUMNO") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                    String operacion = request.getParameter("operacion");
                    String strDate = request.getParameter("xfec");
                    Date xfec = Date.valueOf(strDate);
                    Alumnos alumno = new Alumnos();
                    alumno.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                    alumno.setNombre(request.getParameter("xnom"));
                    alumno.setDireccion(request.getParameter("xdir"));
                    alumno.setEmail(request.getParameter("xema"));
                    alumno.setTelefono(request.getParameter("xtel"));
                    alumno.setCelular(request.getParameter("xcel"));
                    alumno.setSexo(request.getParameter("xsex"));
                    alumno.setFec_nac(xfec);
                    alumno.setEstado(request.getParameter("xest"));
                    if (operacion.equals("INSERT")) {
                        IAlumnosDAO dao = new AlumnoDAOImpl();
                        dao.registrar(alumno);
                    } else {
                        IAlumnosDAO dao = new AlumnoDAOImpl();
                        dao.actualizar(alumno);
                    }
                }
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(
                        request, response);
            } else if (accion.compareTo("modificarAlumno") == 0) {
                String xcod = request.getParameter("xcod").trim();
                IAlumnosDAO dao = new AlumnoDAOImpl();
                Alumnos alumno = dao.buscar(Integer.parseInt(xcod));
                request.setAttribute("alumno", alumno);
                request.setAttribute("operacion", "UPDATE");
                request.setAttribute("titulo", "Modificar Alumno");
                request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(
                        request, response);
            } else if (accion.equals("listadoCursos")) {
                // Obtener la lista de cursos desde la base de datos
                CursosDAO cursoDAO = new CursoDAOImpl();
                List<Cursos> arrCursos = cursoDAO.listarCursos();

                // Agregar la lista de cursos al request
                request.setAttribute("arrCursos", arrCursos);

                // Redirigir a la página de listado de cursos
                request.getRequestDispatcher("/mantenimientos/listadoCursos.jsp").forward(
                        request, response);
            } else if (accion.equals("nuevoEliminarCurso")) {
                if (request.getParameter("boton").equals("Nuevo Curso")) {
                    // Crear un nuevo objeto Curso para el formulario
                    Cursos curso = new Cursos();
                    request.setAttribute("curso", curso);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nuevo Curso");
                    request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(request, response);
                } else {
                    // Código para eliminar cursos aquí
                    String[] codigos = request.getParameterValues("xcod");
                    CursosDAO cursoDAO = new CursoDAOImpl();

                    for (String codigo : codigos) {
                        cursoDAO.eliminarCurso(Integer.parseInt(codigo));
                    }

                    // Redirigir a la página de listado de cursos
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(request, response);
                }
            } else if (accion.equals("grabarCurso")) {
                if (request.getParameter("boton").equals("GRABAR")) {
                    String operacion = request.getParameter("operacion");
                    Cursos curso = new Cursos();
                    curso.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                    curso.setNombre(request.getParameter("xnom"));
                    
                    // Añadir más atributos según la estructura de tu clase Curso

                    if (operacion.equals("INSERT")) {
                        // Insertar nuevo curso
                        CursosDAO cursoDAO = new CursoDAOImpl();
                        cursoDAO.agregarCurso(curso);
                    } else {
                        // Actualizar curso existente
                        CursosDAO cursoDAO = new CursoDAOImpl();
                        cursoDAO.editarCurso(curso);
                    }
                }
                // Redirigir a la página de listado de cursos
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(
                        request, response);
                } else if (accion.equals("matriculaMostrarAlumnos")) {
                    if (request.getParameter("modo").equals("Lista")) {
                        List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                        Alumnos alumno = new Alumnos();
                        alumno.setNombre(" ");
                        IMatriculaDAO dao = new MatriculaDAOImpl();
                        arrAlumnos = dao.buscarAlumnos(alumno);
                        request.setAttribute("arrAlumnos", arrAlumnos);
                        request.getRequestDispatcher("/operaciones/matriculaMostrarAlumnos.jsp").forward(request, response);
                    } else if (request.getParameter("boton").equals("Buscar")) {
                        List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                        Alumnos alumno = new Alumnos();
                        alumno.setNombre(request.getParameter("xalu"));
                        IMatriculaDAO dao = new MatriculaDAOImpl();
                        arrAlumnos = dao.buscarAlumnos(alumno);
                        request.setAttribute("arrAlumnos", arrAlumnos);
                        request.setAttribute("nombre", alumno.getNombre());
                        request.getRequestDispatcher("/operaciones/matriculaMostrarAlumnos.jsp").forward(request, response);
                
                } else {
                   int xcodAlumno = Integer.parseInt(request.getParameter("xcodAlumno"));
                   Alumnos alumno = new Alumnos();
                   IAlumnosDAO dao = new AlumnoDAOImpl();
                   alumno = dao.buscar(xcodAlumno);
                   List<Cursos> arrCursos = new ArrayList<Cursos>();
                   IMatriculaDAO daoMatri = new MatriculaDAOImpl();
                   arrCursos = daoMatri.buscarCursos();
                   request.setAttribute("arrCursos", arrCursos);
                   request.setAttribute("alumno", alumno);
                   request.getRequestDispatcher("/operaciones/matriculaMostrarCursos.jsp").forward(
                           request, response);
                }
            } else if (accion.compareTo("matriculaMostrarSubtotal") == 0) {
                String xcodCursos[] = request.getParameterValues("xcodCurso");
                List<Cursos> arrCursos = new ArrayList<Cursos>();
                CursosDAO dao = new CursoDAOImpl();
                double total = 0;
                for (int xc = 0; xc < xcodCursos.length; xc++) {
                    Cursos curso = new Cursos();
                    curso = dao.buscarCurso(Integer.parseInt(xcodCursos[xc]));
                    BigDecimal costo = curso.getCosto();
                    total += costo.doubleValue();
                    arrCursos.add(curso);
                }
                int xcodAlumno = Integer.parseInt(request.getParameter("xcodAlumno"));
                Alumnos alumno = new Alumnos();
                IAlumnosDAO dao2 = new AlumnoDAOImpl();
                alumno = dao2.buscar(xcodAlumno);
                Conexion co = new Conexion();
                String xcodMatricula = "MAT" + co.generarCodigo("matriculas", "codigo");
                request.setAttribute("arrCursos", arrCursos);
                request.setAttribute("alumno", alumno);
                request.setAttribute("total", total);
                request.setAttribute("xmatri", xcodMatricula);
                request.getRequestDispatcher("/operaciones/matriculaMostrarSubtotal.jsp").forward(
                        request, response);

            } else if (accion.compareTo("matriculaGrabar") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                    String xcodAlumno = request.getParameter("xcodAlumno");
                    String xcodCursos[] = request.getParameterValues("xcodCurso");
                    String xmontos[] = request.getParameterValues("xmonto");
                    
                    String[] datosMatricula = new String[3];
                    datosMatricula[0] = request.getParameter("xndoc");
                    datosMatricula[1] = xcodAlumno;
                    datosMatricula[2] = request.getParameter("xtotal");
                    
                    IMatriculaDAO dao = new MatriculaDAOImpl();
                    boolean rpta = dao.grabarMatricula(datosMatricula, xcodCursos, xmontos);
                    if (rpta) {
                    out.println("<br><h2>Registro grabado correctamente!</h2>");
                    } else {
                    out.println("<br><h2>Error al grabar Matricula!</h2>");
                    }
                } else if (accion.compareTo("buscarMatriculas") == 0) {
    String nroDoc = request.getParameter("nro_doc");
    IMatriculaDAO dao = new MatriculaDAOImpl();
    List<Matriculas> matriculas = new ArrayList<>(); // Inicializa la lista

    if (nroDoc != null && !nroDoc.isEmpty()) {
        matriculas = dao.buscarMatriculasPorNroDoc(nroDoc);

        if (!matriculas.isEmpty()) {
            out.println("<h2>Matrículas encontradas:</h2>");
            // Aquí se definen las matriculas como atributo
            request.setAttribute("matriculas", matriculas);
            request.getRequestDispatcher("/operaciones/matriculaListar.jsp").forward(request, response);
        } else {
            out.println("<h2>Matrícula no encontrada.</h2>");
        }
    } else {
        matriculas = dao.listarMatriculas();

        if (!matriculas.isEmpty()) {
            out.println("<h2>Lista de Matrículas:</h2>");
        } else {
            out.println("<h2>No hay matrículas registradas.</h2>");
        }
        
        // Aquí se definen las matriculas como atributo
        request.setAttribute("matriculas", matriculas);
        request.getRequestDispatcher("/operaciones/matriculaListar.jsp").forward(request, response);
    }
    }
} else if (accion.equals("listarMatriculas")) {
    // Implementa el código para listar todas las matrículas
                    System.out.println("entro");
    IMatriculaDAO dao = new MatriculaDAOImpl();
    List<Matriculas> matriculas = dao.listarMatriculas();
    request.setAttribute("matriculas", matriculas);
    request.getRequestDispatcher("/operaciones/matriculaListar.jsp").forward(request, response);
} else {
    out.println("Accion: (" + accion + ") no reconocida...");
}
                 
        } catch (Exception ex) {
    System.out.println(ex.toString());
} finally {
    out.close();
}
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}


    
