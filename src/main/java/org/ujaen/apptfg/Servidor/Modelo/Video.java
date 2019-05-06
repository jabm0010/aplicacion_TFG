/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Juan Antonio Béjar Martos
 */

@ConfigurationProperties(prefix = "video")
@Entity
public class Video {

    @Id
    private String identificador;

    @Transient
    private String sourceFolder;
    
    @Transient
    private final String extension = ".mp4";

    @Transient
    final int BUFFERSIZE = 4 * 1024;

    public Video(String identificador) {
        this.identificador = identificador;
    }

    public Video() {
    }

    public byte[] cargarVideo() throws FileNotFoundException, IOException {

        Path ficheroOrigen = new File(sourceFolder + getIdentificador() + extension).toPath();
        System.out.println(ficheroOrigen);
        byte[] representacionByteFichero = Files.readAllBytes(ficheroOrigen);
       // System.out.println(Arrays.toString(representacionByteFichero));
        return representacionByteFichero;
    }

    public void almacenarVideo(String identificador, String video) throws FileNotFoundException, IOException {
        byte[] datosVideo = Base64.decodeBase64(video);
        File ficheroDestino = new File(sourceFolder + identificador + extension);
        OutputStream os = new FileOutputStream(ficheroDestino);
        os.write(datosVideo);
        os.close();

    }

    public void eliminarVideo(String identificador) {
        File ficheroBorrado = new File(sourceFolder + identificador + extension);
        ficheroBorrado.delete();
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
     * @param sourceFolder the sourceFolder to set
     */
    public void setSourceFolder(String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

}
