/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;

/**
 *
 
import org.ujaen.apptfg.Servidor.Entidades.Usuarios.Usuario;
* @author Juan Antonio Béjar Martos
 */
@Entity
public class Paciente extends Usuario{
    private Usuario.Rol rol;
    
    public Paciente(){
        
    }
    
    public Paciente(String correoElectronico, String nombre, String apellidos, String clave){
        super(correoElectronico, nombre, apellidos, clave);
        this.rol = Usuario.Rol.PACIENTE;
    }
    

    public PacienteDTO pacienteToDTO(){
        PacienteDTO pacienteDTO = new PacienteDTO(super.getCorreoElectronico(), 
                super.getNombre(), super.getApellidos(),super.getClave());
        return pacienteDTO;
    }
    
    public Paciente pacienteFromDTO(PacienteDTO pacienteDTO){
        Paciente paciente = new Paciente(pacienteDTO.getCorreoElectronico(),pacienteDTO.getNombre(),
                pacienteDTO.getApellidos(),pacienteDTO.getClave());
        return paciente;
    }
}
