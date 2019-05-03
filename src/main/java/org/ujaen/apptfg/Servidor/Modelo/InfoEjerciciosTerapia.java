/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.hateoas.Link;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class InfoEjerciciosTerapia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private Long codigoEjercicio;
    private Integer duracionEjercicio;
    private String tituloEjercicio;
    private Link enlaceEjercicio;

    
    public InfoEjerciciosTerapia() {

    }

    /**
     * @return the codigoEjercicio
     */
    public Long getCodigoEjercicio() {
        return codigoEjercicio;
    }

    /**
     * @param codigoEjercicio the codigoEjercicio to set
     */
    public void setCodigoEjercicio(Long codigoEjercicio) {
        this.codigoEjercicio = codigoEjercicio;
    }

    /**
     * @return the duracionEjercicio
     */
    public Integer getDuracionEjercicio() {
        return duracionEjercicio;
    }

    /**
     * @param duracionEjercicio the duracionEjercicio to set
     */
    public void setDuracionEjercicio(Integer duracionEjercicio) {
        this.duracionEjercicio = duracionEjercicio;
    }

    /**
     * @return the enlaceEjercicio
     */
    public Link getEnlaceEjercicio() {
        return enlaceEjercicio;
    }

    /**
     * @param enlaceEjercicio the enlaceEjercicio to set
     */
    public void setEnlaceEjercicio(Link enlaceEjercicio) {
        this.enlaceEjercicio = enlaceEjercicio;
    }

    /**
     * @return the tituloEjercicio
     */
    public String getTituloEjercicio() {
        return tituloEjercicio;
    }

    /**
     * @param tituloEjercicio the tituloEjercicio to set
     */
    public void setTituloEjercicio(String tituloEjercicio) {
        this.tituloEjercicio = tituloEjercicio;
    }
}
