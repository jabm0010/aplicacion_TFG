/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.File;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {

    public enum Rol {
        MEDICO, PACIENTE
    }

    @Id
    private String correoElectronico;
    private String nombre;
    private String apellidos;
    private String clave;
    
    private boolean activado;
    
    private Usuario.Rol rol;



    @OneToMany(cascade = CascadeType.ALL)
    @OneToOne
    private Imagen imagenperfil;

    
    /**
     * Constructor por defecto
     */
    public Usuario() {
        this.correoElectronico = "";
        this.nombre = "";
        this.apellidos = "";
        this.clave = "";
        this.imagenperfil = null;
        this.activado = false;


    }
    
    /**
     * Constructor de usuario parametrizado sin imagen de perfil
     * @param correoElectronico
     * @param nombre
     * @param apellidos
     * @param clave 
     */
    public Usuario(String correoElectronico, String nombre, String apellidos, String clave) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        
    }

    /**
     * Constructor de usuario con una imagen de perfil asociada
     * 
     * @param correoElectronico
     * @param nombre
     * @param apellidos
     * @param clave
     * @param imagenperfil

     */
    public Usuario(String correoElectronico, String nombre, String apellidos, String clave, Imagen imagenperfil) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.imagenperfil = imagenperfil;
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
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the contraseña to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the imagenperfil
     */
    public Imagen getImagenperfil() {
        return imagenperfil;
    }

    /**
     * @param imagenperfil the imagenperfil to set
     */
    public void setImagenperfil(Imagen imagenperfil) {
        this.imagenperfil = imagenperfil;
    }

    /**
     * @return the activado
     */
    public boolean isActivado() {
        return activado;
    }

    /**
     * @param activado the activado to set
     */
    public void setActivado(boolean activado) {
        this.activado = activado;
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

}
