/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelos.Alumnos;
import modelos.Cursos;
import modelos.Matriculas;

/**
 *
 * @author Carlos
 */
public interface IMatriculaDAO {
    public List<Alumnos> buscarAlumnos(Alumnos alumno);
    public List<Cursos> buscarCursos();
    public boolean grabarMatricula(String[] datosMatricula, 
            String[] codigoCursos, String[] montos);
    public List<Matriculas> listarMatriculas();
    public List<Matriculas> buscarMatriculasPorNroDoc(String nroDoc);
}

