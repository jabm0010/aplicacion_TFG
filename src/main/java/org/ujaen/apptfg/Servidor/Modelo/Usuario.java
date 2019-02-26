/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Juan Antonio Béjar Martos
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Usuario {

    public enum Rol {
        MEDICO,PACIENTE   
    }
    
    @Id
    private String correoElectronico;
    private String nombre;
    private String apellidos;
    
    
    public Usuario(){
        this.correoElectronico = "";
        this.nombre = "";
        this.apellidos = "";
        
    }
    
    public Usuario(String correoElectronico, String nombre, String apellidos){
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidos = apellidos;
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


    
  

}
