/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.util.List;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public interface InterfazServiciosMedico {
    
    void registro(MedicoDTO medico);
    
    void configurarPerfil(MedicoDTO medico);
    
    void crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico);
    
    List<EjercicioTerapeuticoDTO> obtenerEjercicios(String medico);
    
    void guardarEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico);
    
    List<PacienteDTO> obtenerPacientes(String medico);
    
    void añadirPaciente(String medico, PacienteDTO paciente);
    
    MedicoDTO obtenerPerfilUsuario(String medico);
    
    
}
