/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import javax.persistence.Entity;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Administrador extends Usuario{

    public Administrador() {
        super();
        super.setRol(Usuario.Rol.ADMINISTRADOR);
    }
    
    public Administrador(String correoElectronico, String clave){
        super.setCorreoElectronico(correoElectronico);
        super.setClave(clave);
        super.setRol(Usuario.Rol.ADMINISTRADOR);
    }
}
