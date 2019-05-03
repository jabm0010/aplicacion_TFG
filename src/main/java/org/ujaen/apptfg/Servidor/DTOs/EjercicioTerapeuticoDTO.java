/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.time.LocalDateTime;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.ujaen.apptfg.Servidor.Modelo.Medico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class EjercicioTerapeuticoDTO  extends ResourceSupport {
       
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private long identificador;
   
    
     public EjercicioTerapeuticoDTO(){
        this.titulo = "";
        this.descripcion = "";
        this.fechaCreacion = LocalDateTime.now();
        this.identificador = 0;
        
        
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
        this.identificador = id;
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
     * @return the identificador
     */

    public long getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }


}
