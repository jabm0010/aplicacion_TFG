/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime fechaActualización;
    private String contenido;
    private boolean editado;

    @OneToOne
    Usuario usuario;

    public Mensaje() {
        fechaActualización = LocalDateTime.now();
        contenido = "";
        editado = false;
        usuario = null;
    }

    public Mensaje(String contenido, Usuario usuario) {
        this.contenido = contenido;
        this.fechaActualización = LocalDateTime.now();
        editado = false;
        this.usuario = usuario;
    }

    public void modificarMensaje(String contenido) {
        this.contenido = contenido;
        this.fechaActualización = LocalDateTime.now();
        this.editado = true;
    }

    public MensajeDTO MensajeToDTO() {

       MensajeDTO m = new MensajeDTO(this.contenido, this.fechaActualización, this.editado,
                this.usuario.getNombre() + " " + this.usuario.getApellidos(),
                this.usuario.getCorreoElectronico(),this.id);
       
       if(this.usuario.getImagenperfil() != null){
           m.setImagenAutor(this.usuario.getImagenperfil().obtenerImagenBase64());
       }
      return m;
    }

    
    
    
    
    /**
     * @return the fechaActualización
     */
    public LocalDateTime getFechaActualización() {
        return fechaActualización;
    }

    /**
     * @param fechaActualización the fechaActualización to set
     */
    public void setFechaActualización(LocalDateTime fechaActualización) {
        this.fechaActualización = fechaActualización;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
