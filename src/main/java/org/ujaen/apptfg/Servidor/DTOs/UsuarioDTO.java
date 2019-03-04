/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import org.ujaen.apptfg.Servidor.Modelo.Usuario;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class UsuarioDTO {

    private String correoElectronico;
    private String nombre;
    private String apellidos;
    private String clave;
    private Usuario.Rol rol;

    public UsuarioDTO() {
        this.correoElectronico = "";
        this.nombre = "";
        this.apellidos = "";
        this.clave = "";
        this.rol = null;

    }

    public UsuarioDTO(String correoElectronico, String nombre, String apellidos, String clave, Usuario.Rol rol) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.rol = rol;

    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the rol
     */
    public Usuario.Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Usuario.Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

}
