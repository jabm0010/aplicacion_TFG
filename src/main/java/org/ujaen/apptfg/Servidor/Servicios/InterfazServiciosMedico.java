/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;

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
    
    void asignarTerapia(String identificadorPaciente, String medico, TerapiaDTO t);
    
}
