/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Cursos;
import modelos.Conexion;
/**
 *
 * @author carlos
 */
public class CursoDAOImpl implements CursosDAO{
    @Override
    public boolean agregarCurso(Cursos curso) {
    Conexion co = new Conexion();
    boolean agregar = false;
    Statement stm = null;
    Connection con = null;
    String xcod = co.generarCodigo("cursos", "codigo");
    String sql = "INSERT INTO cursos VALUES (" + xcod +
                 ", '" + curso.getNombre() +
                 "', " + curso.getCosto() +
                 ", '" + curso.getFechaInicio() +
                 "', '" + curso.getFechaFin() +
                 "', " + curso.getDuracion() +
                 ", " + curso.getSesiones() +
                 ", " + curso.getCapacidad() +
                 ", '" + curso.getEstado() + "')";
    try {
        con = co.Conectar();
        stm = con.createStatement();
        stm.execute(sql);
        agregar = true;
    } catch (SQLException e) {
        System.out.println("Error: Clase CursoDaoImpl, método agregarCurso");
        e.printStackTrace();
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return agregar;
    }



    @Override
    public List<Cursos> listarCursos() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        List<Cursos> listaCursos = new ArrayList<Cursos>();
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCosto(BigDecimal.valueOf(rs.getDouble("costo")));
                curso.setFechaInicio(rs.getDate("fec_ini"));
                curso.setFechaFin(rs.getDate("fec_fin"));
                curso.setDuracion(rs.getInt("duracion"));
                curso.setSesiones(rs.getInt("sesiones"));
                curso.setCapacidad(rs.getInt("capacidad"));
                curso.setEstado(rs.getString("estado"));
                listaCursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDaoImpl, método listarCursos");
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (co != null) {
                    co.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaCursos;
    }

    @Override
    public Cursos buscarCurso(int codigo) {
        Connection co = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos WHERE codigo=?";
        Cursos curso = null;
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            pst = co.prepareStatement(sql);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                curso = new Cursos();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCosto(BigDecimal.valueOf(rs.getDouble("costo")));
                curso.setFechaInicio(rs.getDate("fec_ini"));
                curso.setFechaFin(rs.getDate("fec_fin"));
                curso.setDuracion(rs.getInt("duracion"));
                curso.setSesiones(rs.getInt("sesiones"));
                curso.setCapacidad(rs.getInt("capacidad"));
                curso.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDaoImpl, método buscarCurso");
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (co != null) {
                    co.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return curso;
    }

    @Override
   public boolean editarCurso(Cursos curso) {
    Conexion co = new Conexion();
    boolean actualizar = false;
    Statement stm = null;
    Connection con = null;
    String sql = "UPDATE cursos SET nombre='" + curso.getNombre() +
                 "', costo=" + curso.getCosto() +
                 ", fec_ini='" + curso.getFechaInicio() +
                 "', fec_fin='" + curso.getFechaFin() +
                 "', duracion=" + curso.getDuracion() +
                 ", sesiones=" + curso.getSesiones() +
                 ", capacidad=" + curso.getCapacidad() +
                 ", estado='" + curso.getEstado() +
                 "' WHERE codigo=" + curso.getCodigo();
    try {
        con = co.Conectar();
        stm = con.createStatement();
        stm.execute(sql);
        actualizar = true;
    } catch (SQLException e) {
        System.out.println("Error: Clase CursoDaoImpl, método editarCurso");
        e.printStackTrace();
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return actualizar;
    }



    @Override
    public boolean eliminarCurso(int codigo) {
        Conexion co = new Conexion();
        boolean eliminar = false;
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "DELETE FROM cursos WHERE codigo=?";
        try {
            con = co.Conectar();
            pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.executeUpdate();
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDaoImpl, método eliminarCurso");
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eliminar;
    }
}
