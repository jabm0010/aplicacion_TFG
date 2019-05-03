/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Mensaje> mensajes;
    
    public Chat(){
        this.mensajes = new TreeSet<>();
    }

    public void nuevoMensaje(Usuario u, String contenido){
        Mensaje m = new Mensaje(contenido, u);
        mensajes.add(m);
       
    }
    
    public void modificarMensaje(Long id, String contenido){
        for(Mensaje m : mensajes){
            if(Objects.equals(m.getId(), id)){
                m.setContenido(contenido);
            }
        }
       
    }
    
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the mensajes
     */
    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
