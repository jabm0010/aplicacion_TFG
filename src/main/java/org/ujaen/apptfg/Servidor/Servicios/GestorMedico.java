/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.EjercicioTerapeuticoDAO;
import org.ujaen.apptfg.Servidor.DAOs.ImagenDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Modelo.EjercicioTerapeutico;
import org.ujaen.apptfg.Servidor.Modelo.Imagen;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorMedico implements InterfazServiciosMedico {

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    EjercicioTerapeuticoDAO ejercicioDAO;

    @Autowired
    ImagenDAO imagenDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    @Override
    public void registro(MedicoDTO medico) {

        Medico medicotmp = new Medico();
        medicotmp = Medico.medicoFromDTO(medico);

        if (medico.getImagen() != null) {
            Imagen imagentmp = new Imagen(medico.getImagen(), medico.getNombreImagen());

            /*Problema con guardar imagen antes de registrar usuario:
           Si ya existe un usuario registrado con el mismo correo, aunque se deniegue el registro del usuario
           se seguirá guardando la imagen asociada a este.     
          Posible solucion:   registrar -> asignar imagen -> actualizar usuario 
             */
            imagenDAO.guardarImagen(imagentmp);

            medicotmp.setImagenperfil(imagentmp);
        }

        medicoDAO.registrarUsuario(medicotmp);

    }

    /**
     * Método para crear un nuevo ejercicio terapéutico
     *
     * @param ejercicioTerapeuticoDTO información del ejercicio a crear
     * @param medicoId identificador del médico que crea el ejercicio
     */
    @Override
    public void crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO, String medicoId) {
        Medico medico = medicoDAO.buscarMedico(medicoId);

        EjercicioTerapeutico ejercicioTerapeutico = new EjercicioTerapeutico();
        ejercicioTerapeutico = ejercicioTerapeutico.ejercicioTerapeuticoFromDTO(ejercicioTerapeuticoDTO);
        ejercicioDAO.crearEjercicioTerapeutic(ejercicioTerapeutico);
        medico.crearEjercicioTerapeutico(ejercicioTerapeutico);

        medicoDAO.actualizarMedico(medico);
    }

    /**
     * Devuelve un listado con los ejercicios terapéuticos creados por un médico
     *
     * @param medico identificador del médico
     * @return
     */
    @Override
    public List<EjercicioTerapeuticoDTO> obtenerEjercicios(String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);

        List<EjercicioTerapeuticoDTO> ret_ejerciciosTerapeuticos = new ArrayList<>();
        List<EjercicioTerapeutico> ejerciciosTerapeuticosSource;
        ejerciciosTerapeuticosSource = new ArrayList<>(medicotmp.getEjerciciosCreados().values());

        ejerciciosTerapeuticosSource.forEach((e) -> {
            ret_ejerciciosTerapeuticos.add(e.ejercicioTerapeuticoToDTO());
        });

        return ret_ejerciciosTerapeuticos;
    }

    /**
     * Método para guardar los cambios realizados en un ejercicio terapétuico ya
     * existente
     *
     * @param ejercicioTerapeutico ejercicio terapéutico actualizado
     * @param medico identificador del mñedico
     */
    @Override
    public void guardarEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);
        EjercicioTerapeutico ejerciciotmp = new EjercicioTerapeutico();
        ejerciciotmp = ejerciciotmp.ejercicioTerapeuticoFromDTO(ejercicioTerapeutico);
        medicotmp.editarEjercicioTerapeutico(ejerciciotmp);

        medicoDAO.actualizarMedico(medicotmp);

    }

    /**
     * Obtiene el perfil de usuario asociado a la clave primaria de un médico
     *
     * @param medico identificador del médico
     * @return
     */
    @Override
    public MedicoDTO obtenerPerfilUsuario(String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);
        MedicoDTO retMedicoDTO = medicotmp.medicoToDTO();
        if (medicotmp.getImagenperfil() != null) {
            retMedicoDTO.setImagen(medicotmp.getImagenperfil().obtenerImagenBase64());
            retMedicoDTO.setNombreImagen(medicotmp.getImagenperfil().getNombre());
        }

        return retMedicoDTO;

    }

    /**
     * Obtiene la lista de pacientes de un médico
     *
     * @param medico identificador del médico que quiere acceder a su lista
     * @return
     */
    @Override
    public List<PacienteDTO> obtenerPacientes(String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);

        List<PacienteDTO> ret_pacientes = new ArrayList<>();
        List<Paciente> pacientesSource;
        pacientesSource = new ArrayList<>(medicotmp.getPacientes().values());

        pacientesSource.forEach((p) -> {
            ret_pacientes.add(p.pacienteToDTO());
        });

        return ret_pacientes;

    }

    /**
     *
     * @param medico identificador del médico que quiere añadir un usuario
     * @param paciente información del paciente que se quiere añadir a la lista
     */
    @Override
    public void añadirPaciente(String medico, PacienteDTO paciente) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);

        Paciente pacientetmp = new Paciente();
        pacientetmp = pacientetmp.pacienteFromDTO(paciente);
        if (!pacienteDAO.existePaciente(paciente.getCorreoElectronico())) {
            pacienteDAO.registrarUsuario(pacientetmp);
        }
        medicotmp.añadirPaciente(pacienteDAO.buscarPaciente(paciente.getCorreoElectronico()));
        medicoDAO.actualizarMedico(medicotmp);

    }

    @Override
    public void configurarPerfil(MedicoDTO medico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Servicio para que un médico asigne una terapia a uno de sus pacientes
     * @param identificadorPaciente clave del paciente al que se va a asignar la terapia
     * @param t contenido de la terapia
     * @param medico clave del médico que asigna la terapia
     */
    @Override
    public void asignarTerapia(String identificadorPaciente, String medico, TerapiaDTO t) {

        Medico m = medicoDAO.buscarMedico(medico);
        Terapia terapia = new Terapia();
        terapia =m.crearTerapia(t.getEjerciciosTerapia(), t.getFechas(),t.getComentarios());
        medicoDAO.actualizarMedico(m);
        //m.asignarTerapia(identificadorPaciente, t.getEjerciciosTerapia(), t.getFechas(), t.getComentarios());
        Paciente p = pacienteDAO.buscarPaciente(identificadorPaciente);
        p.nuevaTerapia(terapia);
        pacienteDAO.actualizarPaciente(p);
        
 
    }

}
