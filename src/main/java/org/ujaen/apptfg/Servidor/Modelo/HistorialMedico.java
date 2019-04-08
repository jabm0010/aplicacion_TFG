/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.ujaen.apptfg.Servidor.DTOs.HistorialMedicoDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection 
    Map<LocalDateTime, String> comentariosHistorial;
    
    public void nuevoComentario(String texto){
        comentariosHistorial.put(LocalDateTime.now(), texto);
    }
    
    public HistorialMedico(){
        comentariosHistorial = new HashMap<>();
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistorialMedicoDTO historialMedicoToDTO( 
        HistorialMedico this){
        HistorialMedicoDTO h = new HistorialMedicoDTO();
        h.setComentariosHistorial(this.comentariosHistorial);
        return h;
    }
}
