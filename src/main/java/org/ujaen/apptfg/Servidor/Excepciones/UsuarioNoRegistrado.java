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
public class UsuarioNoRegistrado extends RuntimeException{

    public UsuarioNoRegistrado() {
        super("Este usuario no está registrado en el sistema");
    }
}
