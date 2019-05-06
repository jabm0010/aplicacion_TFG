/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DTOs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.ujaen.apptfg.Servidor.Modelo.Video;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class VideoDTO {
    
    private String identificador;
    
    private String datosVideo;
    
    public VideoDTO(){
        
    }
    
    public VideoDTO(Video v){
        this.identificador = v.getIdentificador();
        try {
            this.datosVideo = Base64.encodeBase64String(v.cargarVideo());
        } catch (Exception e) {
            this.datosVideo = null;
        }
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the datosVideo
     */
    public String getDatosVideo() {
        return datosVideo;
    }

    /**
     * @param datosVideo the datosVideo to set
     */
    public void setDatosVideo(String datosVideo) {
        this.datosVideo = datosVideo;
    }
}
