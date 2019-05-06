/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.time.LocalDateTime;

/**
 * Clase DTO de Mensaje
 * @author Juan Antonio Béjar Martos
 */
public class MensajeDTO {
    
    private String contenido;
    private LocalDateTime fechaActualizacion;
    private boolean editado;
    private String nombreAutor;
    private String correoAutor;
    private String imagenAutor;
    private Long identificador;
    
    public MensajeDTO(){
        
    }
    
    public MensajeDTO(String contenido, LocalDateTime fechaActualizacion, boolean editado, 
            String nombreAutor, String correoAutor, Long identificador){
        this.contenido = contenido;
        this.fechaActualizacion = fechaActualizacion;
        this.editado = editado;
        this.nombreAutor = nombreAutor;
        this.correoAutor = correoAutor;
        this.identificador = identificador;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the fechaActualizacion
     */
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    /**
     * @return the nombreAutor
     */
    public String getNombreAutor() {
        return nombreAutor;
    }

    /**
     * @param nombreAutor the nombreAutor to set
     */
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    /**
     * @return the correoAutor
     */
    public String getCorreoAutor() {
        return correoAutor;
    }

    /**
     * @param correoAutor the correoAutor to set
     */
    public void setCorreoAutor(String correoAutor) {
        this.correoAutor = correoAutor;
    }

    /**
     * @return the identificador
     */
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the imagenAutor
     */
    public String getImagenAutor() {
        return imagenAutor;
    }

    /**
     * @param imagenAutor the imagenAutor to set
     */
    public void setImagenAutor(String imagenAutor) {
        this.imagenAutor = imagenAutor;
    }
}
