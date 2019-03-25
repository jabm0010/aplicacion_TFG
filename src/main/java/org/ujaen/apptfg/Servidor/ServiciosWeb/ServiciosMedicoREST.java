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
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
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

    /**
     * Servicio REST para crear un ejercicio terapéutico
     *
     * @param medico id identificador del médico
     * @param ejercicio información del ejercicio a crear
     * @return ResponseEntity con código correspondiente
     */
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

    /**
     * Servicio REST para obtener los ejercicios terapéuticos en formato
     * paginado
     *
     * @param medico id identificador del médico
     * @param page página que se quiere recuperar
     * @param size número de elementos que componen la página
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{medico}/ejerciciospaginados", method = GET, produces = "application/json")
    public ResponseEntity<Pagina<EjercicioTerapeuticoDTO>> obtenerEjerciciosTerapeuticosPagina(
            @PathVariable String medico,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {

        List<EjercicioTerapeuticoDTO> ejerciciosTerapeuticos = gestorMedico.obtenerEjercicios(medico);
        if (ejerciciosTerapeuticos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } else {
            Pagina<EjercicioTerapeuticoDTO> pagina = new Pagina<>(ejerciciosTerapeuticos, page, size);
            return new ResponseEntity<>(pagina, HttpStatus.OK);
        }

    }

    /**
     * Servicio REST para obtener ejercicios terapéuticos
     *
     * @param medico id identificador del médico
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{medico}/ejercicios", method = GET, produces = "application/json")
    public ResponseEntity<List<EjercicioTerapeuticoDTO>> obtenerEjerciciosTerapeuticos(
            @PathVariable String medico
    ) {

        List<EjercicioTerapeuticoDTO> ejerciciosTerapeuticos = gestorMedico.obtenerEjercicios(medico);
        if (ejerciciosTerapeuticos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(gestorMedico.obtenerEjercicios(medico), HttpStatus.OK);

    }

    /**
     * Servicio REST para modificar un ejercicio terapéutico
     *
     * @param medico id identificador del médico
     * @param ejercicio información del ejercicio a modificar
     * @return ResponseEntity con código correspondiente
     */
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

    /**
     * Servicio REST para obtener la información de un médico
     *
     * @param medico id identificador del médico
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{medico}", method = GET, produces = "application/json")
    public ResponseEntity<MedicoDTO> obtenerPerfilMedico(
            @PathVariable String medico
    ) {
        MedicoDTO retMedicoDTO;
        try {
            retMedicoDTO = gestorMedico.obtenerPerfilUsuario(medico);
            return new ResponseEntity<>(retMedicoDTO, HttpStatus.OK);
        } catch (Exception e) {

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{medico}/pacientes", method = GET, produces = "application/json")
    public ResponseEntity<List<PacienteDTO>> obtenerListaPacientes(
            @PathVariable String medico) {
        List<PacienteDTO> pacientes = gestorMedico.obtenerPacientes(medico);
        if (pacientes == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pacientes, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/{medico}/pacientes", method = POST, consumes = "application/json")
    public ResponseEntity<Void> añadirPaciente(
            @PathVariable String medico,
            @RequestBody PacienteDTO paciente) {
        try{
            gestorMedico.añadirPaciente(medico, paciente);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
