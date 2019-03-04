/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.UsuarioDTO;
import org.ujaen.apptfg.Servidor.Modelo.Usuario;
import org.ujaen.apptfg.Servidor.Servicios.GestorMedico;
import org.ujaen.apptfg.Servidor.Servicios.GestorPaciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class ServiciosPublicosREST {

    @Autowired
    GestorPaciente gestorPaciente;

    @Autowired
    GestorMedico gestorMedico;

    @RequestMapping(value = "/usuarios", method = POST, consumes = "application/json")
    public ResponseEntity<Void> registroUsuario(@RequestBody UsuarioDTO usuario) {

        if (usuario.getRol() == Usuario.Rol.MEDICO) {

            try {
                MedicoDTO medicoDTOtmp = new MedicoDTO(usuario);

                gestorMedico.registro(medicoDTOtmp);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }
        if (usuario.getRol() == Usuario.Rol.PACIENTE) {
            try {

                PacienteDTO pacienteDTOtmp = new PacienteDTO(usuario);
                gestorPaciente.registro(pacienteDTOtmp);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
