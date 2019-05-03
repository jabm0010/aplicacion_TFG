/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Link;
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class TerapiaDTO {

    private String idTerapia;
    
    private LocalDateTime fechaCreacion;
    
    private List<LocalDate> fechas;
    
    private List<LocalDate> fechasRealizadas;
    
    private List<InfoEjerciciosTerapia> ejerciciosTerapia;  
    
    private String comentarios;
    
    private String medicoCorreoElectronico;
    
    private String medicoNombre;
    
    private String medicoApellidos;
    
    private Link linkChatTerapia;
    
    public TerapiaDTO(){
        this.idTerapia = null;
        this.fechaCreacion = null;
        this.fechas = new ArrayList<>();
        this.ejerciciosTerapia = new ArrayList<>();
        this.comentarios = null;
        this.medicoCorreoElectronico = null;
        this.medicoNombre = null;
        this.medicoApellidos = null;
        this.linkChatTerapia = null;
    }
    
    
    public TerapiaDTO(Terapia t){
        this.idTerapia = t.getUniqueID();
        this.fechas = t.getFechas();
        this.fechaCreacion = t.getFechaCreacion();
        this.comentarios = t.getComentarios();
        this.ejerciciosTerapia = t.getDuracionesEjercicios();
        this.medicoCorreoElectronico = t.getMedico().getCorreoElectronico();
        this.medicoNombre = t.getMedico().getNombre();
        this.medicoApellidos = t.getMedico().getApellidos();
        this.linkChatTerapia = null;
        this.fechasRealizadas = t.getFechasRealizadas();
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
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the ejerciciosTerapia
     */
    public List<InfoEjerciciosTerapia> getEjerciciosTerapia() {
        return ejerciciosTerapia;
    }

    /**
     * @param ejerciciosTerapia the ejerciciosTerapia to set
     */
    public void setEjerciciosTerapia(List<InfoEjerciciosTerapia> ejerciciosTerapia) {
        this.ejerciciosTerapia = ejerciciosTerapia;
    }

    /**
     * @return the medicoCorreoElectronico
     */
    public String getMedicoCorreoElectronico() {
        return medicoCorreoElectronico;
    }

    /**
     * @param medicoCorreoElectronico the medicoCorreoElectronico to set
     */
    public void setMedicoCorreoElectronico(String medicoCorreoElectronico) {
        this.medicoCorreoElectronico = medicoCorreoElectronico;
    }

    /**
     * @return the medicoNombre
     */
    public String getMedicoNombre() {
        return medicoNombre;
    }

    /**
     * @param medicoNombre the medicoNombre to set
     */
    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
    }

    /**
     * @return the medicoApellidos
     */
    public String getMedicoApellidos() {
        return medicoApellidos;
    }

    /**
     * @param medicoApellidos the medicoApellidos to set
     */
    public void setMedicoApellidos(String medicoApellidos) {
        this.medicoApellidos = medicoApellidos;
    }

    /**
     * @return the idTerapia
     */
    public String getIdTerapia() {
        return idTerapia;
    }

    /**
     * @param idTerapia the idTerapia to set
     */
    public void setIdTerapia(String idTerapia) {
        this.idTerapia = idTerapia;
    }

    /**
     * @return the linkChatTerapia
     */
    public Link getLinkChatTerapia() {
        return linkChatTerapia;
    }

    /**
     * @param linkChatTerapia the linkChatTerapia to set
     */
    public void setLinkChatTerapia(Link linkChatTerapia) {
        this.linkChatTerapia = linkChatTerapia;
    }

    /**
     * @return the fechasRealizadas
     */
    public List<LocalDate> getFechasRealizadas() {
        return fechasRealizadas;
    }

    /**
     * @param fechasRealizadas the fechasRealizadas to set
     */
    public void setFechasRealizadas(List<LocalDate> fechasRealizadas) {
        this.fechasRealizadas = fechasRealizadas;
    }



}
