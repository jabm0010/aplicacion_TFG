/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.ujaen.apptfg.Servidor.Modelo.HistorialMedico;

/**
 * Clase DTO de HistorialMedico
 * @author Juan Antonio Béjar Martos
 */
public class HistorialMedicoDTO {

    private Map<LocalDateTime, String> comentariosHistorial;

    public HistorialMedicoDTO() {
        comentariosHistorial = new HashMap<>();
    }

    public HistorialMedicoDTO(HistorialMedico h) {
        this.comentariosHistorial = h.getComentariosHistorial();
    }

    /**
     * @return the comentariosHistorial
     */
    public Map<LocalDateTime, String> getComentariosHistorial() {
        return comentariosHistorial;
    }

    /**
     * @param comentariosHistorial the comentariosHistorial to set
     */
    public void setComentariosHistorial(Map<LocalDateTime, String> comentariosHistorial) {
        this.comentariosHistorial = comentariosHistorial;
    }

}
