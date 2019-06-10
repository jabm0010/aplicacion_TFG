
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.time.LocalDate;
import java.util.List;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public interface InterfazServiciosPaciente {

    boolean registro(PacienteDTO paciente);

    boolean configurarPerfil(String paciente, PacienteDTO pacienteDTO);
    
    List<TerapiaDTO> obtenerTerapias(String paciente);

    boolean actualizarTerapia(String paciente, Long idTerapia, LocalDate fecha);

    PacienteDTO obtenerPerfilUsuario(String paciente);

}

