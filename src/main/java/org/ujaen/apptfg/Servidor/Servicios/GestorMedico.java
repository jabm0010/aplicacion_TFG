/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ujaen.apptfg.Servidor.DAOs.EjercicioTerapeuticoDAO;
import org.ujaen.apptfg.Servidor.DAOs.HistorialMedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.ImagenDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DAOs.TerapiaDAO;
import org.ujaen.apptfg.Servidor.DAOs.VideoDAO;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.HistorialMedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Excepciones.EjerciciosNoValidos;
import org.ujaen.apptfg.Servidor.Excepciones.MaximoPacientesAlcanzado;
import org.ujaen.apptfg.Servidor.Excepciones.UsuarioNoRegistrado;
import org.ujaen.apptfg.Servidor.Seguridad.GestionRegistro;
import org.ujaen.apptfg.Servidor.Modelo.EjercicioTerapeutico;
import org.ujaen.apptfg.Servidor.Modelo.HistorialMedico;
import org.ujaen.apptfg.Servidor.Modelo.Imagen;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Mensaje;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;
import org.ujaen.apptfg.Servidor.Modelo.TokenActivacion;
import org.ujaen.apptfg.Servidor.Modelo.Video;

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

    @Autowired
    HistorialMedicoDAO historialMedicoDAO;

    @Autowired
    GestionRegistro gestionRegistro;

    @Autowired
    TerapiaDAO terapiaDAO;

    @Autowired
    VideoDAO videoDAO;

    /**
     *
     * @param medico
     * @return
     */
    @Override
    public boolean registro(MedicoDTO medico) {

        Medico medicoRegistro = new Medico();

        try {
            medicoRegistro = medicoDAO.buscarMedico(medico.getCorreoElectronico());
            if (medico.getImagen() != null) {
                Imagen imagentmp = new Imagen(medico.getImagen(), medico.getNombreImagen());
                imagenDAO.guardarImagen(imagentmp);
                medicoRegistro.setImagenperfil(imagentmp);
            }

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String clave = passwordEncoder.encode(medico.getClave());
            medicoRegistro.setClave(clave);
            medicoRegistro.setActivado(true);
            medicoDAO.actualizarMedico(medicoRegistro);
        } catch (Exception e) {
            if (medico.getImagen() != null) {
                imagenDAO.borrarImagen(medicoRegistro.getImagenperfil().getId());
            }
            return false;
        }

        return true;

    }

    /**
     * Método para modificar los parámetros de clave e imagen de perfil de un
     * médico.Si alguno
     *
     * @param medico
     * @param medicoDTO
     * @return 
     */
    @Override
    public boolean configurarPerfil(String medico, MedicoDTO medicoDTO) {

        try {
            Medico m = medicoDAO.buscarMedico(medico);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!medicoDTO.getClave().isBlank() && !passwordEncoder.matches(medicoDTO.getClave(), m.getClave())) {
                String clave = passwordEncoder.encode(medicoDTO.getClave());
                m.setClave(clave);
            }

            if (medicoDTO.getImagen() != null) {
                Imagen imagentmp = new Imagen(medicoDTO.getImagen(), medicoDTO.getNombreImagen());
                imagenDAO.guardarImagen(imagentmp);
                if (m.getImagenperfil() != null) {
                    imagenDAO.borrarImagen(m.getImagenperfil().getId());
                }
                m.setImagenperfil(imagentmp);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Método para crear un nuevo ejercicio terapéutico
     *
     * @param ejercicioTerapeuticoDTO información del ejercicio a crear
     * @param medicoId identificador del médico que crea el ejercicio
     * @param video
     * @return
     */
    @Override
    public boolean crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO, String medicoId, MultipartFile video) {

        try {
            if (ejercicioTerapeuticoDTO.getTitulo().trim().isEmpty() || ejercicioTerapeuticoDTO.getDescripcion().trim().isEmpty()) {
                throw new EjerciciosNoValidos();
            }
            Medico medico = medicoDAO.buscarMedico(medicoId);
            if (medico == null) {
                throw new UsuarioNoRegistrado();
            }

            EjercicioTerapeutico ejercicioTerapeutico;
            if (video == null) {
                ejercicioTerapeutico = new EjercicioTerapeutico(ejercicioTerapeuticoDTO);
            } else {
                String identificadorVideo = UUID.randomUUID().toString();
                Video v = new Video();
                v.setIdentificador(identificadorVideo);
                v.almacenarVideo(identificadorVideo, video);
                ejercicioTerapeutico = new EjercicioTerapeutico(ejercicioTerapeuticoDTO, v);
            }
            ejercicioDAO.crearEjercicioTerapeutic(ejercicioTerapeutico);
            medico.crearEjercicioTerapeutico(ejercicioTerapeutico);

            medicoDAO.actualizarMedico(medico);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Devuelve un listado con los ejercicios terapéuticos creados por un médico
     *
     * @param medicoId identificador del médico
     * @return
     */
    @Override
    public List<EjercicioTerapeuticoDTO> obtenerEjercicios(String medicoId) {
        try {

            Medico medicotmp = medicoDAO.buscarMedico(medicoId);
            if (medicoId == null) {
                throw new UsuarioNoRegistrado();
            }

            List<EjercicioTerapeuticoDTO> ret_ejerciciosTerapeuticos = new ArrayList<>();
            List<EjercicioTerapeutico> ejerciciosTerapeuticosSource;
            ejerciciosTerapeuticosSource = new ArrayList<>(medicotmp.getEjerciciosCreados().values());

            ejerciciosTerapeuticosSource.forEach((e) -> {
                ret_ejerciciosTerapeuticos.add(new EjercicioTerapeuticoDTO(e));
            });

            return ret_ejerciciosTerapeuticos;
        } catch (Exception e) {
            e.toString();
        }

        return null;
    }

    /**
     * Método para obtener un ejercicio buscando por su identificador
     *
     * @param medicoId
     * @param ejercicioTerapeuticoI
     * @return
     */
    @Override
    public EjercicioTerapeuticoDTO obtenerEjercicio(String medicoId, Long ejercicioTerapeuticoI) {
        try {

            Medico medicotmp = medicoDAO.buscarMedico(medicoId);
            if (medicotmp == null) {
                throw new UsuarioNoRegistrado();
            }

            return new EjercicioTerapeuticoDTO(medicotmp.obtenerEjercicio(ejercicioTerapeuticoI));
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Método para guardar los cambios realizados en un ejercicio terapétuico ya
     * existente
     *
     * @param ejercicioTerapeutico
     * @param medico
     * @param video
     * @return
     */
    @Override
    public boolean editarEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico, MultipartFile video) {
        try {
            if (ejercicioTerapeutico.getTitulo().trim().isEmpty() || ejercicioTerapeutico.getDescripcion().trim().isEmpty()) {
                throw new EjerciciosNoValidos();
            }

            Medico medicotmp = medicoDAO.buscarMedico(medico);
            if (medicotmp == null) {
                throw new UsuarioNoRegistrado();
            }

            EjercicioTerapeutico ejerciciotmp;
            Video v = new Video();

            if (video == null) {
                ejerciciotmp = new EjercicioTerapeutico(ejercicioTerapeutico);
            } else {
                String identificadorVideo = UUID.randomUUID().toString();
                //String identificadorVideo = medicoId + ejercicioTerapeuticoDTO.getIdentificador();
                v.setIdentificador(identificadorVideo);
                v.almacenarVideo(identificadorVideo, video);
                ejerciciotmp = new EjercicioTerapeutico(ejercicioTerapeutico, v);
            }

            medicotmp.editarEjercicioTerapeutico(ejerciciotmp);

            medicoDAO.actualizarMedico(medicotmp);
            return true;
        } catch (Exception e) {

        }
        return false;
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
    @Transactional
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
    public boolean añadirPaciente(String medico, PacienteDTO paciente) {

        Medico medicotmp = medicoDAO.buscarMedico(medico);
        Paciente pacientetmp = new Paciente();
        pacientetmp = pacientetmp.pacienteFromDTO(paciente);
        pacientetmp.setActivado(true); //Cambiar

        pacienteDAO.registrarUsuario(pacientetmp);

        try {
            medicotmp.añadirPaciente(pacienteDAO.buscarPaciente(pacientetmp.getCorreoElectronico()));
        } catch (MaximoPacientesAlcanzado e) {
            medicotmp.eliminarPaciente(pacientetmp);
            medicoDAO.actualizarMedico(medicotmp);
            pacienteDAO.borrarPaciente(pacientetmp.getCorreoElectronico());
            return false;
        }
        HistorialMedico h = new HistorialMedico();
        historialMedicoDAO.crearHistorialMedico(h);
        medicotmp.crearHistorialMedico(pacientetmp.getCorreoElectronico(), h);

        medicoDAO.actualizarMedico(medicotmp);

        try {
            gestionRegistro.envioCorreo(pacientetmp);

        } catch (Exception e) {
            medicotmp.eliminarPaciente(pacientetmp);
            medicoDAO.actualizarMedico(medicotmp);
            pacienteDAO.borrarPaciente(pacientetmp.getCorreoElectronico());

            return false;
        }

        return true;

    }

    /**
     * Servicio para que un médico asigne una terapia a uno de sus pacientes
     *
     * @param identificadorPaciente clave del paciente al que se va a asignar la
     * terapia
     * @param t contenido de la terapia
     * @param medico clave del médico que asigna la terapia
     */
    @Override
    public boolean asignarTerapia(String identificadorPaciente, String medico, TerapiaDTO t) {

        try {

            Medico m = medicoDAO.buscarMedico(medico);
            Terapia terapia;
            terapia = m.crearTerapia(t.getEjerciciosTerapia(), t.getFechas(), t.getComentarios());
            terapia.setMedico(m);
            terapiaDAO.crearTerapia(terapia);
            medicoDAO.actualizarMedico(m);

            Paciente p = pacienteDAO.buscarPaciente(identificadorPaciente);
            p.nuevaTerapia(terapia);
            pacienteDAO.actualizarPaciente(p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Devuelve el historial médico asociado a un paciente
     *
     * @param medico medico que gestiona el historial medico
     * @param paciente paciente al que está asignado el historial médico
     * @return
     */
    @Override
    public HistorialMedicoDTO obtenerHistorialMedico(String medico, String paciente) {
        try {
            Paciente p = pacienteDAO.buscarPaciente(paciente);
            Medico m = medicoDAO.buscarMedico(medico);

            HistorialMedico h = historialMedicoDAO.obtenerHistorialMedico(m.obtenerHistorialMedico(p.getCorreoElectronico()));
            return new HistorialMedicoDTO(h);

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public boolean nuevoComentarioHistorialMedico(String medico, String paciente, String texto) {
        try {
            Medico m = medicoDAO.buscarMedico(medico);
            Paciente p = pacienteDAO.buscarPaciente(paciente);
            HistorialMedico h = m.modificarHistorialMedico(texto, p.getCorreoElectronico());
            historialMedicoDAO.actualizarHistorialMedico(h);
            medicoDAO.actualizarMedico(m);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<TerapiaDTO> obtenerTerapias(String identificadorPaciente, String medico) {
        List<TerapiaDTO> terapias_ret = new ArrayList<>();

        Medico medicotmp = medicoDAO.buscarMedico(medico);
        List<Terapia> terapiasSource = medicotmp.obtenerTerapias(identificadorPaciente);

        terapiasSource.forEach((t) -> {
            terapias_ret.add(new TerapiaDTO(t));
        });

        return terapias_ret;
    }

    @Override
    public void registroPruebas(MedicoDTO medico) {

        Medico m = new Medico();
        m = Medico.medicoFromDTO(medico);
        m.setActivado(true);
        medicoDAO.registrarUsuario(m);

    }

}
