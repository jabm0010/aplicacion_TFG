/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Excepciones;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class UsuarioYaActivado extends RuntimeException {

    public UsuarioYaActivado() {
        super("Esta cuenta de usuario ya ha sido activada");
    }
}
