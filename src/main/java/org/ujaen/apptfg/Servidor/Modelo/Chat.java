/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class Chat {
    List<Mensaje> mensajes;
    
    public Chat(){
        this.mensajes = new ArrayList<>();
    }
}
