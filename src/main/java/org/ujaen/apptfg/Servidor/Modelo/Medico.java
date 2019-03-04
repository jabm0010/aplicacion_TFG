/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;

/**
* @author Juan Antonio Béjar Martos
 */


@Entity
public class Medico extends Usuario{
    
    private Usuario.Rol rol;
    
    public Medico(){
        
    }

    public Medico(String correoElectronico, String nombre, String apellidos, String clave){
        super(correoElectronico, nombre, apellidos, clave);
        this.rol = Usuario.Rol.MEDICO;
    }
    
    
    
    public MedicoDTO MedicoToDTO(){
        MedicoDTO medicoDTO = new MedicoDTO(super.getCorreoElectronico(), 
                super.getNombre(), super.getApellidos(),super.getClave());
        return medicoDTO;
    }
    
    public Medico MedicoFromDTO(MedicoDTO medicoDTO){
        Medico medico = new Medico(medicoDTO.getCorreoElectronico(),medicoDTO.getNombre(),
                medicoDTO.getApellidos(),medicoDTO.getClave());
        return medico;
    }
    

}
