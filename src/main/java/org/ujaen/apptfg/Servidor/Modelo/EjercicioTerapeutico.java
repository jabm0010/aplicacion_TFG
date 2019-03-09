/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;



/**
 *
 * @author Juan Antonio Béjar Martos
 */

@Entity
public class EjercicioTerapeutico implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private long id;
    
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    
    
    
    public EjercicioTerapeutico(){
        this.titulo = "";
        this.descripcion = "";
        fechaCreacion = LocalDateTime.now();
        
        
    }
    
    public EjercicioTerapeutico(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        fechaCreacion = LocalDateTime.now();
    }
    
    
        public EjercicioTerapeutico(String titulo, String descripcion, LocalDateTime fechaCreacion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }


    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaCreacion
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    public EjercicioTerapeuticoDTO ejercicioTerapeuticoToDTO(){
        EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO =
             new EjercicioTerapeuticoDTO(titulo, descripcion, fechaCreacion);
        
        return ejercicioTerapeuticoDTO;
    }
    
    public EjercicioTerapeutico ejercicioTerapeuticoFromDTO(EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO){
        EjercicioTerapeutico ejercicioTerapeutico;
        ejercicioTerapeutico = new EjercicioTerapeutico(
                ejercicioTerapeuticoDTO.getTitulo(),ejercicioTerapeuticoDTO.getDescripcion(),
                ejercicioTerapeuticoDTO.getFechaCreacion());
        if(ejercicioTerapeuticoDTO.getFechaCreacion() == null){
            ejercicioTerapeutico.setFechaCreacion(LocalDateTime.now());
        }
        
        return ejercicioTerapeutico;
    }
    
}
