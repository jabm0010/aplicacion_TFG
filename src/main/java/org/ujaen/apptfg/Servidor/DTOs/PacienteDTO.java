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

    /**
     * Constructor DTO pensado para la creación de un nuevo paciente por parte de un médico.
     * El médico únicamente introduce los parámetros de correo electrónico, nombre y apellidos,
     * el resto serán posteriormente configurados por el propio paciente.
     * @param correoElectronico
     * @param nombre
     * @param apellidos 
     */
    public PacienteDTO(String correoElectronico, String nombre, String apellidos){
        super.setCorreoElectronico(correoElectronico);
        super.setNombre(nombre);
        super.setApellidos(apellidos);
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
