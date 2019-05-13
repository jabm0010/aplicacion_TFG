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
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.ujaen.apptfg.Servidor.Excepciones.FechaRealizacionTerapiaNoValida;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<EjercicioTerapeutico> listaEjercicios;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InfoEjerciciosTerapia> duracionesEjercicios;

    @Column(length = 2000)
    private String comentarios;

    //Nuevo
    @ElementCollection
    private List<LocalDate> fechasRealizadas;

    @ManyToOne
    private Medico medico;

    @Transient
    private int progreso;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Chat mensajesTerapia;

    public Terapia() {
        this.uniqueID = UUID.randomUUID().toString();
        this.fechaCreacion = LocalDateTime.now();
        this.fechas = new ArrayList<>();
        this.duracionesEjercicios = new ArrayList<>();
        this.listaEjercicios = new ArrayList<>();
        this.fechasRealizadas = new ArrayList<>();
        this.medico = null;
        this.mensajesTerapia = new Chat();
        actualizarProgreso();

    }

    public void actualizarFechas(LocalDate fechaRealizada) {
        if (fechas.contains(fechaRealizada) && !fechasRealizadas.contains(fechaRealizada)) {
            fechasRealizadas.add(fechaRealizada);
            actualizarProgreso();
            
            
        }else{
            throw new FechaRealizacionTerapiaNoValida();
        }
        
        
    }

    private int actualizarProgreso(){
        return 1;
        //return fechasRealizadas.size() * 100 / fechas.size();
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

    /**
     * @return the progreso
     */
    public int getProgreso() {
        return progreso;
    }

    /**
     * @param progreso the progreso to set
     */
    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the mensajesTerapia
     */
    public Chat getMensajesTerapia() {
        return mensajesTerapia;
    }

    /**
     * @param mensajesTerapia the mensajesTerapia to set
     */
    public void setMensajesTerapia(Chat mensajesTerapia) {
        this.mensajesTerapia = mensajesTerapia;
    }

}
