/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class Terapia {
    
    private LocalDateTime fechaCreacion;
    
    private List<LocalDate> fechas;
    
    private Map<EjercicioTerapeutico, Integer> ejerciciosTerapia;
    
    private Chat chatTerapia;
    
    
    public Terapia(){
        this.fechaCreacion = LocalDateTime.now();
        this.fechas = new ArrayList<>();
        this.ejerciciosTerapia = new HashMap<>();
        this.chatTerapia = new Chat();
    }
    
    
    
    
    
    
    

    /**
     * @return the fechaCreacion
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @return the fechas
     */
    public List<LocalDate> getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }

    /**
     * @return the ejerciciosTerapia
     */
    public Map<EjercicioTerapeutico, Integer> getEjerciciosTerapia() {
        return ejerciciosTerapia;
    }

    /**
     * @param ejerciciosTerapia the ejerciciosTerapia to set
     */
    public void setEjerciciosTerapia(Map<EjercicioTerapeutico, Integer> ejerciciosTerapia) {
        this.ejerciciosTerapia = ejerciciosTerapia;
    }

    /**
     * @return the chatTerapia
     */
    public Chat getChatTerapia() {
        return chatTerapia;
    }

    /**
     * @param chatTerapia the chatTerapia to set
     */
    public void setChatTerapia(Chat chatTerapia) {
        this.chatTerapia = chatTerapia;
    }
    
}
