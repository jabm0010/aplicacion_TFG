/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String extension;
    private LocalDateTime fechaSubida;

    @Lob
    private byte[] imagen;

    /**
     * Constructor por defecto de la clase Imagen
     */
    public Imagen() {
        nombre = "";
        extension = "";
        this.fechaSubida = LocalDateTime.now();
        imagen = null;
    }

    /**
     * Constructor de la clase Imagen a partir de un objeto de tipo file y un
     * nombre.
     *
     * @param fichero contiene la imagen propiamente dicha
     * @param nombre
     */
    public Imagen(String fichero, String nombre) {

        byte[] imagenRecibida = Base64.decodeBase64(fichero);
        this.imagen = imagenRecibida;
        this.extension = FilenameUtils.getExtension(fichero);
        this.fechaSubida = LocalDateTime.now();
        this.nombre = nombre;

    }

    /**
     * Devuelve un objeto de tipo File con el contenido de la imagen almacenada
     * en la instancia.
     *
     * @return
     */
    public File obtenerImagen() {
        try {
            File tempFile = File.createTempFile(this.nombre,null, null);
            System.out.println(tempFile.toPath());
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(imagen);

            return tempFile;
        } catch (IOException e) {

        }
        return null;
    }

//    public Imagen retImagen(String path) {
//        try (FileOutputStream stream = new FileOutputStream(path)) {
//            stream.write(b);
//        }
//    }
//    public Imagen crearImagen(File fichero) {
//        try {
//            byte[] imagenLeida = Files.readAllBytes(fichero.toPath());
//            this.imagen = imagenLeida;
//            return this;
//        } catch (IOException e) {
//
//        }
//        return null;
//    }
//
//    public void nuevaImagen() {
//        try {
//            leerImagen(this.imagen, "C:\\Users\\jabm9\\Desktop\\TFG" + "\\pruebaNuva.jpg");
//            System.out.println("ee");
//        } catch (IOException e) {
//        }
//    }
//
//    private static void leerImagen(byte[] b, String path) throws FileNotFoundException, IOException {
//        try (FileOutputStream stream = new FileOutputStream(path)) {
//            stream.write(b);
//        }
//    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the fechaSubida
     */
    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    /**
     * @param fechaSubida the fechaSubida to set
     */
    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
