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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;

/**
* @author Juan Antonio Béjar Martos
 */


@Entity
public class Medico extends Usuario{
    
    private Usuario.Rol rol;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<EjercicioTerapeutico> ejerciciosCreados;
    
    public Medico(){
        super();
        this.rol = Usuario.Rol.MEDICO;
        ejerciciosCreados = new ArrayList<>();
    }

    public Medico(String correoElectronico, String nombre, String apellidos, String clave){
        super(correoElectronico, nombre, apellidos, clave);
        this.rol = Usuario.Rol.MEDICO;
        ejerciciosCreados = new ArrayList<>();
    
    }
    
    public void crearEjercicioTerapeutico(EjercicioTerapeutico ejercicio){

        ejerciciosCreados.add(ejercicio);
        
    }
    
 
    
    public MedicoDTO medicoToDTO(){
        MedicoDTO medicoDTO = new MedicoDTO(super.getCorreoElectronico(), 
                super.getNombre(), super.getApellidos(),super.getClave());
        return medicoDTO;
    }
    
    public Medico medicoFromDTO(MedicoDTO medicoDTO){
        Medico medico = new Medico(medicoDTO.getCorreoElectronico(),medicoDTO.getNombre(),
                medicoDTO.getApellidos(),medicoDTO.getClave());
        return medico;
    }

    /**
     * @return the ejerciciosCreados
     */
    public List<EjercicioTerapeutico> getEjerciciosCreados() {
        return ejerciciosCreados;
    }

    /**
     * @param ejerciciosCreados the ejerciciosCreados to set
     */
    public void setEjerciciosCreados(List<EjercicioTerapeutico> ejerciciosCreados) {
        this.ejerciciosCreados = ejerciciosCreados;
    }
    

}
