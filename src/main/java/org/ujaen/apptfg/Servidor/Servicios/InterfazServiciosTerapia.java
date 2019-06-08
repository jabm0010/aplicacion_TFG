/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.util.List;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public interface InterfazServiciosTerapia {
    
    boolean enviarMensaje(Long idTerapia, String mensaje, String usuario);

    boolean editarMensaje(Long idTerapia, String mensaje, Long idMensaje, String usuario);

    List<MensajeDTO> obtenerMensajes(Long idTerapia, String usuario);
}
