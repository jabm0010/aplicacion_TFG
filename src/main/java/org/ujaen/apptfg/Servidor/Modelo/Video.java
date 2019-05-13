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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Video {

    @Id
    private String identificador;

    @Transient
    private final String sourceFolder = "C:\\Users\\jabm9\\Desktop\\TFG\\Videos\\";

    private String extension;

    @Transient
    final int BUFFERSIZE = 4 * 1024;

    public Video(String identificador) {
        this.identificador = identificador;
    }

    public Video() {
    }

    public byte[] cargarVideo() throws FileNotFoundException, IOException {

        Path ficheroOrigen = new File(sourceFolder + getIdentificador() + getExtension()).toPath();
        System.out.println(ficheroOrigen);
        byte[] representacionByteFichero = Files.readAllBytes(ficheroOrigen);
        // System.out.println(Arrays.toString(representacionByteFichero));
        return representacionByteFichero;
    }

    public void almacenarVideo(String identificador, String video) throws FileNotFoundException, IOException {
        byte[] datosVideo = Base64.decodeBase64(video);
        File ficheroDestino = new File(sourceFolder + identificador + getExtension());

        OutputStream os = new FileOutputStream(ficheroDestino);
        os.write(datosVideo);
        os.close();

    }

    public void almacenarVideo(String identificador, MultipartFile video) throws IOException {
        this.extension = "." + FilenameUtils.getExtension(video.getOriginalFilename());

        File ficheroDestino = new File(sourceFolder + identificador + this.extension);
        ficheroDestino.createNewFile();
        FileOutputStream fos = new FileOutputStream(ficheroDestino);
        fos.write(video.getBytes());
        fos.close();
    }

    public void eliminarVideo(String identificador) {
        File ficheroBorrado = new File(sourceFolder + identificador + this.extension);
        ficheroBorrado.delete();
    }

    public void eliminarVideo() {
        File ficheroBorrado = new File(this.sourceFolder + this.identificador + this.extension);
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
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

}
