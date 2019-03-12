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
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.UsuarioDTO;
import org.ujaen.apptfg.Servidor.Servicios.GestorMedico;
import org.ujaen.apptfg.Servidor.Utiils.Pagina;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/medicos")
public class ServiciosMedicoREST {

    @Autowired
    GestorMedico gestorMedico;

    @RequestMapping(value = "/{medico}/ejercicios", method = POST, consumes = "application/json")
    public ResponseEntity<Void> crearEjercicioTerapeutico(
            @PathVariable String medico,
            @RequestBody EjercicioTerapeuticoDTO ejercicio) {

        if (ejercicio.getTitulo().trim().isEmpty() || ejercicio.getTitulo().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            try {
                gestorMedico.crearEjercicioTerapeutico(ejercicio, medico);
                return new ResponseEntity<>(HttpStatus.CREATED);

            } catch (RuntimeException e) {
                //System.out.println(e.toString());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

    }

    @RequestMapping(value = "/{medico}/ejercicios", method = GET, produces = "application/json")
    public ResponseEntity<Pagina<EjercicioTerapeuticoDTO>> obtenerEjerciciosTerapeuticosPagina(
            @PathVariable String medico,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {

        Pagina<EjercicioTerapeuticoDTO> pagina = new Pagina<>(gestorMedico.obtenerEjercicios(medico), page, size);
        return new ResponseEntity<>(pagina, HttpStatus.OK);

    }

    @RequestMapping(value = "/{medico}/ejercicios", method = PUT, consumes = "application/json")
    public ResponseEntity<Void> guardarEjercicioTerapeutico(
            @PathVariable String medico,
            @RequestBody EjercicioTerapeuticoDTO ejercicio) {

        if (ejercicio.getTitulo().trim().isEmpty() || ejercicio.getTitulo().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            try {
                gestorMedico.guardarEjercicioTerapeutico(ejercicio, medico);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }

    }

}
