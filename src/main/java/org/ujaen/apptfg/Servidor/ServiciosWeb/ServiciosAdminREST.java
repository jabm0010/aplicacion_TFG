/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Servicios.GestorAdministrador;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class ServiciosAdminREST {

    @Autowired
    GestorAdministrador gestorAdministrador;

    /**
     * Servicio utilizado por un administrador del sistema para dar de alta a un
     * nuevo médico
     *
     * @param medico
     * @return ResponseEntity CONFLICT en caso de error, OK en caso de que se
     * haya realizado el registro correctamente
     */
    @RequestMapping(value = "/administrador/medicos", method = POST, consumes = "application/json")
    public ResponseEntity<Void> registroMedico(@RequestBody MedicoDTO medico) {

        if (gestorAdministrador.registro(medico)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Servicio utilizado para un administrador para buscar un médico a partir
     * de su correo
     *
     * @param medico
     * @return
     */
    @RequestMapping(value = "/administrador/medicos/{medico}", method = GET, produces = "application/json")
    public ResponseEntity<MedicoDTO> obtenerMedico(@PathVariable String medico) {
        try {

            MedicoDTO tmp = gestorAdministrador.obtenerMedico(medico);
            return new ResponseEntity<>(tmp, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/administrador/medicos", method = PUT, consumes = "application/json")
    public ResponseEntity<Void> modificarMedico(@RequestBody MedicoDTO medico) {
        try {

            gestorAdministrador.modificarMedico(medico);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}
