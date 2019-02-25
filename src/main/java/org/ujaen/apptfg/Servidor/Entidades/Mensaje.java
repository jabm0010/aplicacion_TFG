/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class Mensaje {
    
    private LocalDateTime fechaActualización;
    private String contenido;
    private boolean editado;
    
    public Mensaje(){
        fechaActualización = LocalDateTime.now();
        contenido = "";
        editado = false;
    }

   /*
    * @brief Constructor de mensajes parametrizado con el cuerpo del mensaje
    * @param [in] contenido     Cuerpo del mensasje
    *
    */
    public Mensaje(String contenido){
        this.contenido = contenido;
        this.fechaActualización = LocalDateTime.now();
        editado = false;
    }

    /**
     * @return the fechaActualización
     */
    public LocalDateTime getFechaActualización() {
        return fechaActualización;
    }

    /**
     * @param fechaActualización the fechaActualización to set
     */
    public void setFechaActualización(LocalDateTime fechaActualización) {
        this.fechaActualización = fechaActualización;
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
    
}
