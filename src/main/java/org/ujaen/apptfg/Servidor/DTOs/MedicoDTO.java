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
public class MedicoDTO extends UsuarioDTO {


    public MedicoDTO() {
        super();
        super.setRol(Usuario.Rol.MEDICO);
        

    }

    public MedicoDTO(String correoElectronico, String nombre, String apellidos, String clave) {
        super(correoElectronico, nombre, apellidos, clave, Usuario.Rol.MEDICO);
    }
    
    public MedicoDTO(UsuarioDTO usuario){
        super(usuario.getCorreoElectronico(), usuario.getNombre(), usuario.getApellidos(), usuario.getClave(),Usuario.Rol.MEDICO);
    }


}
