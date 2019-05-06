/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class EjercicioTerapeutico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;

    @Column(length = 500)
    private String descripcion;
    private LocalDateTime fechaCreacion;

    @OneToOne(cascade = {CascadeType.ALL})
    private Video videoEjercicio;

    public EjercicioTerapeutico() {
        this.titulo = "";
        this.descripcion = "";
        fechaCreacion = LocalDateTime.now();

    }

    public EjercicioTerapeutico(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        fechaCreacion = LocalDateTime.now();
    }

    public EjercicioTerapeutico(EjercicioTerapeuticoDTO e) {
        this.titulo = e.getTitulo();
        this.descripcion = e.getDescripcion();
        this.id = e.getIdentificador();
        this.videoEjercicio = null;
        if (e.getFechaCreacion() == null) {
            this.fechaCreacion = LocalDateTime.now();
        } else {
            this.fechaCreacion = e.getFechaCreacion();
        }
    }

    public EjercicioTerapeutico(EjercicioTerapeuticoDTO e, Video v) {
        this.titulo = e.getTitulo();
        this.descripcion = e.getDescripcion();
        this.id = e.getIdentificador();
        this.videoEjercicio = v;
        if (e.getFechaCreacion() == null) {
            this.fechaCreacion = LocalDateTime.now();
        } else {
            this.fechaCreacion = e.getFechaCreacion();
        }
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the videoEjercicio
     */
    public Video getVideoEjercicio() {
        return videoEjercicio;
    }

    /**
     * @param videoEjercicio the videoEjercicio to set
     */
    public void setVideoEjercicio(Video videoEjercicio) {
        this.videoEjercicio = videoEjercicio;
    }

}
