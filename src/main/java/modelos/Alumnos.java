/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Date;

public class Alumnos {
    private int codigo;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    private String celular;
    private String sexo;
    private Date fec_nac;
    private String estado;

    public Alumnos() {
        // Constructor por defecto
    }

    public Alumnos(int codigo, String nombre, String direccion, String email, String telefono, String celular, String sexo, Date fec_nac, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.sexo = sexo;
        this.fec_nac = fec_nac;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(Date fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
