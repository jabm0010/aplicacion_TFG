
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.HistorialMedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public interface InterfazServiciosMedico {

    boolean registro(MedicoDTO medico);

    boolean configurarPerfil(String medico, MedicoDTO medicoDTO);

    boolean crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico, MultipartFile video);

    List<EjercicioTerapeuticoDTO> obtenerEjercicios(String medico);

    EjercicioTerapeuticoDTO obtenerEjercicio(String medico, Long id);

    boolean editarEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico, MultipartFile video);

    List<PacienteDTO> obtenerPacientes(String medico);

    boolean añadirPaciente(String medico, PacienteDTO paciente);

    MedicoDTO obtenerPerfilUsuario(String medico);

    boolean asignarTerapia(String identificadorPaciente, String medico, TerapiaDTO t);

    List<TerapiaDTO> obtenerTerapias(String identificadorPaciente, String medico);

    HistorialMedicoDTO obtenerHistorialMedico(String medico, String paciente);

    boolean nuevoComentarioHistorialMedico(String medico, String paciente, String texto);


    //Pruebas - eliminar en el futuro
    void registroPruebas(MedicoDTO medico);
    
}
