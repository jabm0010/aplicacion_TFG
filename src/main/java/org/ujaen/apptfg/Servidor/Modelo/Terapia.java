/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Terapia implements Serializable {

    @Id
    private String uniqueID;
    
   
    private LocalDateTime fechaCreacion;
    
    @ElementCollection 
    private List<LocalDate> fechas;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<EjercicioTerapeutico> listaEjercicios;

    @OneToMany (cascade = CascadeType.ALL)
    private List<InfoEjerciciosTerapia> duracionesEjercicios;

    private String comentarios;

    public Terapia() {
        this.uniqueID = UUID.randomUUID().toString();
        this.fechaCreacion = LocalDateTime.now();
        this.fechas = new ArrayList<>();
        this.duracionesEjercicios = new ArrayList<>();
        this.listaEjercicios = new ArrayList<>();

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
     * @return the duracionesEjercicios
     */
    public List<InfoEjerciciosTerapia> getDuracionesEjercicios() {
        return duracionesEjercicios;
    }

    /**
     * @param duracionesEjercicios the duracionesEjercicios to set
     */
    public void setDuracionesEjercicios(List<InfoEjerciciosTerapia> duracionesEjercicios) {
        this.duracionesEjercicios = duracionesEjercicios;
    }

    /**
     * @return the listaEjercicios
     */
    public List<EjercicioTerapeutico> getListaEjercicios() {
        return listaEjercicios;
    }

    /**
     * @param listaEjercicios the listaEjercicios to set
     */
    public void setListaEjercicios(List<EjercicioTerapeutico> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    /**
     * @return the uniqueID
     */
    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * @param uniqueID the uniqueID to set
     */
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    
}
