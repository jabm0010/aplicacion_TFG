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
public class PacienteDTO extends UsuarioDTO {

    public PacienteDTO() {
        super();
        super.setRol(Usuario.Rol.PACIENTE);
    }

    public PacienteDTO(String correoElectronico, String nombre, String apellidos, String clave) {
        super(correoElectronico, nombre, apellidos, clave, Usuario.Rol.PACIENTE);

    }
    public PacienteDTO(String correoElectronico, String nombre, String apellidos, String clave, String imagen, String nombreimagen) {
        super(correoElectronico, nombre, apellidos, clave, Usuario.Rol.PACIENTE, imagen, nombreimagen);

    }

    public PacienteDTO(UsuarioDTO usuario) {
        super(usuario.getCorreoElectronico(), usuario.getNombre(), usuario.getApellidos(), usuario.getClave(), Usuario.Rol.PACIENTE,
        usuario.getImagen(), usuario.getNombreImagen());
    }

}
