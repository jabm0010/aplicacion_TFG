/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;

/**
 *
 
import org.ujaen.apptfg.Servidor.Entidades.Usuarios.Usuario;
* @author Juan Antonio Béjar Martos
 */
@Entity
public class Paciente extends Usuario{

    
    @ManyToMany(mappedBy = "pacientes")
    private List<Medico> medicos;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Terapia> terapiasPaciente;
    
    public Paciente(){
        
    }
    
    public void nuevaTerapia(Terapia t){
        terapiasPaciente.add(t);
    }
    
    public Paciente(String correoElectronico, String nombre, String apellidos, String clave){
        super(correoElectronico, nombre, apellidos, clave);
        super.setRol(Rol.PACIENTE);
        this.medicos = new ArrayList<>();
        this.terapiasPaciente = new ArrayList<>();
    }
    

    public PacienteDTO pacienteToDTO(){
        PacienteDTO pacienteDTO = new PacienteDTO(super.getCorreoElectronico(), 
                super.getNombre(), super.getApellidos(),super.getClave());
        
        if(this.getImagenperfil()!=null){
          pacienteDTO.setImagen(this.getImagenperfil().obtenerImagenBase64());
        }
        return pacienteDTO;
    }
    
    public Paciente pacienteFromDTO(PacienteDTO pacienteDTO){
        Paciente paciente = new Paciente(pacienteDTO.getCorreoElectronico(),pacienteDTO.getNombre(),
                pacienteDTO.getApellidos(),pacienteDTO.getClave());
        return paciente;
    }

    /**
     * @return the terapiasPaciente
     */
    public List<Terapia> getTerapiasPaciente() {
        return terapiasPaciente;
    }

    /**
     * @param terapiasPaciente the terapiasPaciente to set
     */
    public void setTerapiasPaciente(List<Terapia> terapiasPaciente) {
        this.terapiasPaciente = terapiasPaciente;
    }
}
