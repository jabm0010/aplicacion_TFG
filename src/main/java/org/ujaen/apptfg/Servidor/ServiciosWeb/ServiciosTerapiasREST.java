/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
import org.ujaen.apptfg.Servidor.Modelo.Usuario;
import org.ujaen.apptfg.Servidor.Servicios.GestorMedico;
import org.ujaen.apptfg.Servidor.Servicios.GestorPaciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/terapias")
public class ServiciosTerapiasREST {

    @Autowired
    GestorPaciente gestorPaciente;

    @Autowired
    GestorMedico gestorMedico;

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    public Usuario.Rol determinarRolUsuario(String usuario) {
        Medico m = medicoDAO.buscarMedico(usuario);
        if (m != null) {
            return Usuario.Rol.MEDICO;
        }
        Paciente p = pacienteDAO.buscarPaciente(usuario);
        if (p != null) {
            return Usuario.Rol.PACIENTE;
        }

        return null;
    }

    @RequestMapping(value = "/{usuario}/{terapia}/mensajes", method = GET, produces = "application/json")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesTerapia(
            @PathVariable String usuario,
            @PathVariable Long terapia
    ) {

        Usuario.Rol rol = determinarRolUsuario(usuario);
        if (rol == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<MensajeDTO> mensajesTerapia = new ArrayList<>();
        if (rol == Usuario.Rol.MEDICO) {
            mensajesTerapia = gestorMedico.obtenerMensajes(terapia);
        } else {
            mensajesTerapia = gestorPaciente.obtenerMensajes(terapia);
        }

        return new ResponseEntity<>(mensajesTerapia, HttpStatus.OK);

    }

    @RequestMapping(value = "/{usuario}/{terapia}/mensajes", method = POST, produces = "application/json")
    public ResponseEntity<Void> enviarMensaje(
            @PathVariable String usuario,
            @PathVariable Long terapia,
            @RequestBody MensajeDTO mensaje
    ) {
        Usuario.Rol rol = determinarRolUsuario(usuario);
        if (rol == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (rol == Usuario.Rol.MEDICO) {
            if (gestorMedico.enviarMensaje(terapia, mensaje.getContenido(), usuario)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            if (gestorPaciente.enviarMensaje(terapia, mensaje.getContenido(), usuario)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

    }

    @RequestMapping(value = "/{usuario}/{terapia}/mensajes", method = PUT, produces = "application/json")
    public ResponseEntity<Void> modificarMensaje(
            @PathVariable String usuario,
            @PathVariable Long terapia,
            @RequestBody MensajeDTO mensaje
    ) {
        Usuario.Rol rol = determinarRolUsuario(usuario);
        if (rol == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (rol == Usuario.Rol.MEDICO) {
            if (gestorMedico.editarMensaje(terapia, mensaje.getContenido(), mensaje.getIdentificador())) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            if (gestorPaciente.editarMensaje(terapia, mensaje.getContenido(), mensaje.getIdentificador())) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }


    }

}
