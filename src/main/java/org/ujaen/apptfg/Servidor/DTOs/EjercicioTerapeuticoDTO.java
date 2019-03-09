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
    
     public EjercicioTerapeuticoDTO(){
        this.titulo = "";
        this.descripcion = "";
        fechaCreacion = LocalDateTime.now();
        
        
    }
    
    public EjercicioTerapeuticoDTO(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = null;
    }
     
    public EjercicioTerapeuticoDTO(String titulo, String descripcion, LocalDateTime fechaCreacion){
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
}
