/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import javax.persistence.Entity;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;

/**
* @author Juan Antonio Béjar Martos
 */


@Entity
public class Medico extends Usuario{
    
    private Usuario.Rol rol;

    public Medico(String correoElectronico, String nombre, String apellidos){
        super(correoElectronico, nombre, apellidos);
        this.rol = Usuario.Rol.MEDICO;
    }
    
    public MedicoDTO MedicoToDTO(){
        MedicoDTO medicoDTO = new MedicoDTO(super.getCorreoElectronico(), super.getNombre(), super.getApellidos());
        return medicoDTO;
    }
    

}
