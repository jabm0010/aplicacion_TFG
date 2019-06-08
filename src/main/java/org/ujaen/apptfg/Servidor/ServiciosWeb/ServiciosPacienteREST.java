/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import java.time.LocalDate;
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
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;
import org.ujaen.apptfg.Servidor.Servicios.GestorPaciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class ServiciosPacienteREST {

    @Autowired
    GestorPaciente gestorPaciente;

    @RequestMapping(value = "/{paciente}/terapias", method = GET, produces = "application/json")
    public ResponseEntity<List<TerapiaDTO>> obtenerTerapias(
            @PathVariable String paciente) {

        List<TerapiaDTO> listaTerapias_ret = new ArrayList<>();
        try {

            listaTerapias_ret = gestorPaciente.obtenerTerapias(paciente);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND    );
        }

        for (TerapiaDTO t : listaTerapias_ret) {
            for (InfoEjerciciosTerapia i : t.getEjerciciosTerapia()) {
                Link l = ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(ServiciosMedicoREST.class).
                                obtenerEjercicioId(t.getMedicoCorreoElectronico(), i.getCodigoEjercicio())).withSelfRel();

                i.setEnlaceEjercicio(l);

            }
            Link l = ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(ServiciosTerapiasREST.class).
                            obtenerMensajesTerapia(paciente, t.getIdTerapia())).withSelfRel();
            t.setLinkChatTerapia(l);

        }
        return new ResponseEntity<>(listaTerapias_ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/{paciente}/terapias/{terapia}", method = POST, produces = "application/json")
    public ResponseEntity<Void> realizarSesionTerapia(
            @PathVariable String paciente,
            @PathVariable Long terapia,
            @RequestBody LocalDate fechaRealizada) {

        if (gestorPaciente.actualizarTerapia(paciente, terapia, fechaRealizada)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }



    /**
     * Implementación servicio REST para obtener el perfil de un paciente
     *
     * @param paciente id identificador del paciente
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{paciente}", method = GET, produces = "application/json")
    public ResponseEntity<PacienteDTO> obtenerPerfilPaciente(
            @PathVariable String paciente
    ) {
        PacienteDTO retPacienteDTO;
        try {
            retPacienteDTO = gestorPaciente.obtenerPerfilUsuario(paciente);
            return new ResponseEntity<>(retPacienteDTO, HttpStatus.OK);
        } catch (Exception e) {

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Implementación servicio REST para obtener el perfil de un paciente
     *
     * @param paciente id identificador del paciente
     * @param nuevoPerfil
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{paciente}", method = PUT, produces = "application/json")
    public ResponseEntity<Void> modificarPerfil(
            @PathVariable String paciente,
            @RequestBody PacienteDTO nuevoPerfil
    ) {
        if (gestorPaciente.configurarPerfil(nuevoPerfil)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
