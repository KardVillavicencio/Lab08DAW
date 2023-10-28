/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelos.Cursos;

/**
 *
 * @author carlos
 */
public interface CursosDAO {
    boolean agregarCurso(Cursos curso);
    List<Cursos> listarCursos();
    Cursos buscarCurso(int codigo);
    boolean editarCurso(Cursos curso);
    boolean eliminarCurso(int codigo);
}
