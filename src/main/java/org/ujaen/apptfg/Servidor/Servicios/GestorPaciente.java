/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.ImagenDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DAOs.TerapiaDAO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Modelo.Imagen;
import org.ujaen.apptfg.Servidor.Modelo.Mensaje;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorPaciente implements InterfazServiciosPaciente {

    @Autowired
    PacienteDAO pacienteDAO;

    @Autowired
    ImagenDAO imagenDAO;

    @Autowired
    TerapiaDAO terapiaDAO;
    
    @Autowired
    MedicoDAO medicoDAO;

    @Override
    public boolean registro(PacienteDTO paciente) {
        Paciente pacienteRegistro = new Paciente();

        try {
            pacienteRegistro = pacienteDAO.buscarPaciente(paciente.getCorreoElectronico());
            Imagen imagentmp = new Imagen(paciente.getImagen(), paciente.getNombreImagen());
            imagenDAO.guardarImagen(imagentmp);
            pacienteRegistro.setImagenperfil(imagentmp);

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String clave = passwordEncoder.encode(paciente.getClave());
            pacienteRegistro.setClave(clave);
            pacienteRegistro.setActivado(true);
            pacienteDAO.actualizarPaciente(pacienteRegistro);
        } catch (Exception e) {
            imagenDAO.borrarImagen(pacienteRegistro.getImagenperfil().getId());
            return false;
        }

        return true;
    }

    @Override
    public boolean configurarPerfil(String paciente, PacienteDTO pacienteDTO) {

        try {
            Paciente p = pacienteDAO.buscarPaciente(paciente);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!pacienteDTO.getClave().isBlank() && !passwordEncoder.matches(pacienteDTO.getClave(), p.getClave())) {
                String clave = passwordEncoder.encode(pacienteDTO.getClave());
                p.setClave(clave);
            }

            if (pacienteDTO.getImagen() != null) {
                Imagen imagentmp = new Imagen(pacienteDTO.getImagen(), pacienteDTO.getNombreImagen());
                imagenDAO.guardarImagen(imagentmp);

                if (p.getImagenperfil() != null) {
                    long idImagen = p.getImagenperfil().getId();
                    p.setImagenperfil(null);
                    imagenDAO.borrarImagen(idImagen);

                }
                p.setImagenperfil(imagentmp);
            }
            pacienteDAO.actualizarPaciente(p);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<TerapiaDTO> obtenerTerapias(String paciente) {
        Paciente p = pacienteDAO.buscarPaciente(paciente);
        List<TerapiaDTO> terapias_ret = new ArrayList<>();
        p.getTerapiasPaciente().forEach((t) -> {
            terapias_ret.add(new TerapiaDTO(t));
        });

        return terapias_ret;

    }

    @Override
    public boolean actualizarTerapia(String paciente, Long idTerapia, LocalDate fecha) {

        try {
            Terapia t = terapiaDAO.obtenerTerapia(idTerapia);
            Paciente p = pacienteDAO.buscarPaciente(paciente);
            if (!p.getTerapiasPaciente().contains(t)) {
                return false;
            }
            t.actualizarFechas(fecha);
            terapiaDAO.actualizarTerapia(t);
            //Necesario actualizar el médico también
            medicoDAO.actualizarMedico(t.getMedico());
            

        } catch (Exception e) {
            e.toString();
            return false;
        }
        return true;
    }



    @Override
    public PacienteDTO obtenerPerfilUsuario(String paciente) {

        Paciente pacientetmp = pacienteDAO.buscarPaciente(paciente);
        PacienteDTO retPacienteDTO = pacientetmp.pacienteToDTO();
        if (pacientetmp.getImagenperfil() != null) {
            retPacienteDTO.setImagen(pacientetmp.getImagenperfil().obtenerImagenBase64());
            retPacienteDTO.setNombreImagen(pacientetmp.getImagenperfil().getNombre());
        }
        return retPacienteDTO;
    }



}
