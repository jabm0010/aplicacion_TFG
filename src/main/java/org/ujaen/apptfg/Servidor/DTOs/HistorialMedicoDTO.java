/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class HistorialMedicoDTO {

    private Map<LocalDateTime, String> comentariosHistorial;

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
