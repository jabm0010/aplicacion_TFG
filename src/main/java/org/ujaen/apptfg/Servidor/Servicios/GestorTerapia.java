/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DAOs.TerapiaDAO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.Excepciones.ExcepcionUsuarioRegistrado;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Mensaje;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;
import org.ujaen.apptfg.Servidor.Modelo.Usuario;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorTerapia implements InterfazServiciosTerapia {

    @Autowired
    TerapiaDAO terapiaDAO;
    @Autowired

    MedicoDAO medicoDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    private Usuario determinarRolUsuario(String usuario) {
        Medico m = medicoDAO.buscarMedico(usuario);
        if (m != null) {

            return m;
        }
        Paciente p = pacienteDAO.buscarPaciente(usuario);
        if (p != null) {
            return p;
        }

        return null;
    }

    @Override
    public boolean enviarMensaje(Long idTerapia, String mensaje, String usuario) {
        try {

            Usuario u = determinarRolUsuario(usuario);
            if (u == null) {
                throw new ExcepcionUsuarioRegistrado();
            }

            Terapia t = terapiaDAO.obtenerTerapia(idTerapia);

            t.getMensajesTerapia().nuevoMensaje(u, mensaje);
            terapiaDAO.actualizarTerapia(t);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean editarMensaje(Long idTerapia, String mensaje, Long idMensaje, String usuario) {
        try {

            Usuario u = determinarRolUsuario(usuario);
            if (u == null) {
                throw new ExcepcionUsuarioRegistrado();
            }

            Terapia t = terapiaDAO.obtenerTerapia(idTerapia);
            t.getMensajesTerapia().modificarMensaje(idMensaje, mensaje);
            terapiaDAO.actualizarTerapia(t);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<MensajeDTO> obtenerMensajes(Long idTerapia, String usuario) {
        try {
            Usuario u = determinarRolUsuario(usuario);
            if (u == null) {
                throw new ExcepcionUsuarioRegistrado();
            }

            Terapia t = terapiaDAO.obtenerTerapia(idTerapia);
            List<Mensaje> mensajesTerapiaSource = new ArrayList<>(t.getMensajesTerapia().getMensajes());
            List<MensajeDTO> mensajeRet = new ArrayList<>();
            mensajesTerapiaSource.forEach((mensaje) -> {
                mensajeRet.add(mensaje.MensajeToDTO());
            });
            return mensajeRet;
        } catch (Exception e) {
            return null;
        }

    }
}
