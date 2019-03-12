/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.time.LocalDateTime;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class EjercicioTerapeuticoDTO {
       
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private long id;
    
     public EjercicioTerapeuticoDTO(){
        this.titulo = "";
        this.descripcion = "";
        this.fechaCreacion = LocalDateTime.now();
        this.id = 0;
        
        
    }
    
    public EjercicioTerapeuticoDTO(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = null;
    }
     
    public EjercicioTerapeuticoDTO(String titulo, String descripcion, LocalDateTime fechaCreacion, long id){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.id = id;
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

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
