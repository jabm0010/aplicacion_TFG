/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import org.ujaen.apptfg.Servidor.Modelo.Medico.versionCuenta;
import org.ujaen.apptfg.Servidor.Modelo.Usuario;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class MedicoDTO extends UsuarioDTO {

    private versionCuenta versionCuenta;

    public MedicoDTO() {
        super();
        super.setRol(Usuario.Rol.MEDICO);

    }

    public MedicoDTO(String correoElectronico, String nombre, String apellidos, versionCuenta version) {
        super(correoElectronico, nombre, apellidos, Usuario.Rol.MEDICO);
        this.versionCuenta = version;
    }

    public MedicoDTO(String correoElectronico, String nombre, String apellidos, String clave, versionCuenta version) {
        super(correoElectronico, nombre, apellidos, clave, Usuario.Rol.MEDICO);
        this.versionCuenta = version;
    }

    public MedicoDTO(String correoElectronico, String nombre, String apellidos, String clave,
            String imagen, String nombreimagen, versionCuenta version) {
        super(correoElectronico, nombre, apellidos, clave, Usuario.Rol.MEDICO, imagen, nombreimagen);
        this.versionCuenta = version;
    }

    public MedicoDTO(UsuarioDTO usuario) {
        super(usuario.getCorreoElectronico(), usuario.getNombre(), usuario.getApellidos(), usuario.getClave(), Usuario.Rol.MEDICO,
                usuario.getImagen(), usuario.getNombreImagen());
    }

    public MedicoDTO(MedicoDTO medico) {
        super(medico.getCorreoElectronico(), medico.getNombre(), medico.getApellidos(),
                medico.getClave(), Usuario.Rol.MEDICO,
                medico.getImagen(), medico.getNombreImagen());
        this.versionCuenta = medico.getVersionCuenta();
    }

    /**
     * @return the versionCuenta
     */
    public versionCuenta getVersionCuenta() {
        return versionCuenta;
    }

    /**
     * @param versionCuenta the version to set
     */
    public void setVersion(versionCuenta versionCuenta) {
        this.versionCuenta = versionCuenta;
    }

}
