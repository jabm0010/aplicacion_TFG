/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
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
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Servicios.GestorMedico;
import org.ujaen.apptfg.Servidor.Utiils.Pagina;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.content.commons.repository.Store;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import org.springframework.web.multipart.MultipartFile;
import org.ujaen.apptfg.Servidor.DTOs.HistorialMedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MensajeDTO;
import org.ujaen.apptfg.Servidor.DTOs.VideoDTO;
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/medicos")
@ConfigurationProperties()
public class ServiciosMedicoREST {

    @Autowired
    GestorMedico gestorMedico;

    /**
     * Implementación ervicio REST para crear un ejercicio terapéutico
     *
     * @param medico id identificador del médico
     * @param ejercicio información del ejercicio a crear
     * @param file archivo de video asociado al ejercicio
     * @return ResponseEntity con código correspondiente
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/{medico}/ejercicios", method = POST)
    public ResponseEntity<Void> crearEjercicioTerapeutico(
            @PathVariable String medico,
            @RequestParam("model") String ejercicio,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        
        EjercicioTerapeuticoDTO ejercicioDTO = mapper.readValue(ejercicio, EjercicioTerapeuticoDTO.class);

        if (!gestorMedico.crearEjercicioTerapeutico(ejercicioDTO, medico, file)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * Implementación servicio REST para actualizar un ejercicio terapéutico
     *
     * @param medico id identificador del médico
     * @param ejercicio información del ejercicio a modificar
     * @param file
     * @return ResponseEntity con código correspondiente
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/{medico}/ejercicios", method = PUT)
    public ResponseEntity<Void> editarEjercicioTerapeutico(
            @PathVariable String medico,
            @RequestParam("model") String ejercicio,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        EjercicioTerapeuticoDTO ejercicioDTO = mapper.readValue(ejercicio, EjercicioTerapeuticoDTO.class);

        if (!gestorMedico.editarEjercicioTerapeutico(ejercicioDTO, medico, file)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * Implementación servicio REST para obtener ejercicios terapéuticos
     *
     * @param medico id identificador del médico
     * @param page pagina a la que se quiere acceder
     * @param size tamaño de la página, por defecto será del tamaño igual al
     * número total de ejercicios
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{medico}/ejercicios", method = GET, produces = "application/json")
    public ResponseEntity<Pagina<EjercicioTerapeuticoDTO>> obtenerEjerciciosTerapeuticos(
            @PathVariable String medico,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "0") int size
    ) {

        List<EjercicioTerapeuticoDTO> ejerciciosTerapeuticos = gestorMedico.obtenerEjercicios(medico);
        if (ejerciciosTerapeuticos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (size == 0) {
            size = ejerciciosTerapeuticos.size();
        }
        Pagina<EjercicioTerapeuticoDTO> pagina = new Pagina<>(ejerciciosTerapeuticos, page, size);
        return new ResponseEntity<>(pagina, HttpStatus.OK);
    }

    /**
     * Implementación REST para obtener un ejercicio terapéutico filtrado por su
     * ID
     *
     * @param medico identificador del médico
     * @param id identificador del ejercicio
     * @return
     */
    @RequestMapping(value = "/{medico}/ejercicios/{id}", method = GET, produces = "application/json")
    public ResponseEntity<EjercicioTerapeuticoDTO> obtenerEjercicioId(
            @PathVariable String medico,
            @PathVariable Long id
    ) {

        EjercicioTerapeuticoDTO ejercicioTerapeuticoRet = gestorMedico.obtenerEjercicio(medico, id);
        if (ejercicioTerapeuticoRet == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ejercicioTerapeuticoRet, HttpStatus.OK);

    }

    /**
     * Implementación servicio REST para obtener el perfil de un médico
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

    /**
     * Implementación servicio REST para modificar el perfil de un médico
     *
     * @param medico id identificador del médico
     * @param nuevoPerfil información del perfil a modificar
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/{medico}", method = PUT, produces = "application/json")
    public ResponseEntity<Void> modificarPerfil(
            @PathVariable String medico,
            @RequestBody MedicoDTO nuevoPerfil
    ) {

        if (gestorMedico.configurarPerfil(nuevoPerfil)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Implementación servicio REST para obtener los pacientes asociados a un
     * médico
     *
     * @param medico identificador del médico que quiere acceder a la lista de
     * pacientes
     * @return
     */
    @RequestMapping(value = "/{medico}/pacientes", method = GET, produces = "application/json")
    public ResponseEntity<List<PacienteDTO>> obtenerListaPacientes(
            @PathVariable String medico) {
        List<PacienteDTO> pacientes = gestorMedico.obtenerPacientes(medico);
        if (pacientes == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pacientes, HttpStatus.OK);

    }

    /**
     * Implementación servicio REST para añadir un nuevo paciente a la lista de
     * un médico
     *
     * @param medico identificador del médico que quiere acceder a la lista de
     * pacientes
     * @param paciente información del paciente a agregar
     * @return
     */
    @RequestMapping(value = "/{medico}/pacientes", method = POST, consumes = "application/json")
    public ResponseEntity<Void> añadirPaciente(
            @PathVariable String medico,
            @RequestBody PacienteDTO paciente) {

        if (gestorMedico.añadirPaciente(medico, paciente)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    /**
     * Implementación servicio REST para asignar terapias a un paciente
     *
     * @param medico identificador del médico que asigna la terapia
     * @param paciente paciente al que se asigna la terapia
     * @param t contenido de la terapia asignada
     * @return
     */
    @RequestMapping(value = "/{medico}/terapias/{paciente}", method = POST, consumes = "application/json")
    public ResponseEntity<Void> asignarTerapia(@PathVariable String medico,
            @PathVariable String paciente,
            @RequestBody TerapiaDTO t) {

        try {

            gestorMedico.asignarTerapia(paciente, medico, t);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{medico}/terapias/{paciente}", method = GET, produces = "application/json")
    public ResponseEntity<List<TerapiaDTO>> obtenerTerapias(@PathVariable String medico,
            @PathVariable String paciente) {

        List<TerapiaDTO> listaTerapias_ret = new ArrayList<>();
        try {
            listaTerapias_ret = gestorMedico.obtenerTerapias(paciente, medico);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        for (TerapiaDTO t : listaTerapias_ret) {
            for (InfoEjerciciosTerapia i : t.getEjerciciosTerapia()) {
                Link l = ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(ServiciosMedicoREST.class).
                                obtenerEjercicioId(medico, i.getCodigoEjercicio())).withSelfRel();

                i.setEnlaceEjercicio(l);
            }

            Link l = ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(ServiciosMedicoREST.class).
                            obtenerMensajesTerapia(medico, t.getIdTerapia())).withSelfRel();
            t.setLinkChatTerapia(l);
        }

        return new ResponseEntity<>(listaTerapias_ret, HttpStatus.OK);
    }

    /**
     *
     * @param medico
     * @param paciente
     * @return
     */
    @RequestMapping(value = "/{medico}/historial/{paciente}", method = GET, produces = "application/json")
    public ResponseEntity<HistorialMedicoDTO> obtenerHistorialMedico(
            @PathVariable String medico,
            @PathVariable String paciente) {

        HistorialMedicoDTO hDTO = gestorMedico.obtenerHistorialMedico(medico, paciente);
        if (hDTO != null) {
            return new ResponseEntity<>(hDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     *
     * @param medico
     * @param paciente
     * @param texto
     * @return
     */
    @RequestMapping(value = "/{medico}/historial/{paciente}", method = POST, consumes = "application/json")
    public ResponseEntity<Void> nuevoComentarioHistorialMedico(
            @PathVariable String medico,
            @PathVariable String paciente,
            @RequestBody String texto) {

        if (gestorMedico.nuevoComentarioHistorialMedico(medico, paciente, texto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{medico}/terapias/{terapia}/mensajes", method = GET, produces = "application/json")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesTerapia(
            @PathVariable String medico,
            @PathVariable String terapia
    ) {
        List<MensajeDTO> mensajesTerapia = new ArrayList<>();
        mensajesTerapia = gestorMedico.obtenerMensajes(terapia);
        if (!mensajesTerapia.isEmpty()) {
            return new ResponseEntity<>(mensajesTerapia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{medico}/terapias/{terapia}/mensajes", method = POST, produces = "application/json")
    public ResponseEntity<Void> enviarMensaje(
            @PathVariable String medico,
            @PathVariable String terapia,
            @RequestBody MensajeDTO mensaje
    ) {
        if (gestorMedico.enviarMensaje(terapia, mensaje.getContenido(), medico)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/{medico}/terapias/{terapia}/mensajes", method = PUT, produces = "application/json")
    public ResponseEntity<Void> modificarMensaje(
            @PathVariable String medico,
            @PathVariable String terapia,
            @RequestBody MensajeDTO mensaje
    ) {

        if (gestorMedico.editarMensaje(terapia, mensaje.getContenido(), mensaje.getIdentificador())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/{medico}/ejercicios/{idejercicio}/videos", method = POST)
    public ResponseEntity<Void> asignarVideoEjercicio(
            @PathVariable String medico,
            @PathVariable Long idejercicio,
            @RequestBody VideoDTO video) {

        if (!gestorMedico.almacenarVideo(medico, idejercicio, video.getIdentificador(), video.getDatosVideo())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{medico}/ejercicios/{idejercicio}/videos/{idvideo}", method = DELETE, produces = "application/json")
    public ResponseEntity<Void> eliminarVideoEjercicio(
            @PathVariable String medico,
            @PathVariable Long idejercicio,
            @PathVariable String idvideo) {

        if (!gestorMedico.eliminarVideo(medico, idejercicio, idvideo)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @StoreRestResource(path = "videos")
    public interface VideoStore extends Store<String> {
    }

}
