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
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class TerapiaDTO {

    private LocalDateTime fechaCreacion;

    private List<LocalDate> fechas;

    private List<InfoEjerciciosTerapia> ejerciciosTerapia;

    private String comentarios;


    public TerapiaDTO(
            List<LocalDate> fechas,
            LocalDateTime fechaCreacion,
            String comentarios,
            List<String> ejercicios,
            List<String> duraciones,
            List<InfoEjerciciosTerapia> ejerciciosTerapia
    ) {
        System.out.println("Eeee");
        this.fechas = fechas;
        this.fechaCreacion = fechaCreacion;
        this.comentarios = comentarios;
        this.ejerciciosTerapia = ejerciciosTerapia;

        System.out.println(ejerciciosTerapia);
        

    }

    public TerapiaDTO() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechas = new ArrayList<>();
        this.ejerciciosTerapia = new ArrayList<>();
        this.comentarios = null;

    }

    public TerapiaDTO(LocalDateTime fechaCreacion, List<LocalDate> fechas, String comentarios) throws IOException {
        this.fechas = fechas;
        this.comentarios = comentarios;
        if (fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        } else {
            this.fechaCreacion = fechaCreacion;
        }

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



}
