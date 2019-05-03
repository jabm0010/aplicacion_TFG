/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Excepciones;

/**
 * Excepción runtime lanzada cuando se envía al servidor una fecha de realización de una terapia que no coincide
 * con una de las fechas definidas en la terapia o cuando se trata de una fecha que ya ha sido registrada como
 * realizada en el sistema.
 * 
 * @author Juan Antonio Béjar Martos
 */
public class FechaRealizacionTerapiaNoValida extends RuntimeException{
    
        public FechaRealizacionTerapiaNoValida() {
        super("Fecha de realización de la terapia inválida");
    }
}
