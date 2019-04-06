/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DAOs.TokenActivacionDAO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.UsuarioDTO;
import org.ujaen.apptfg.Servidor.Modelo.TokenActivacion;
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

    @Autowired
    TokenActivacionDAO tokenDAO;

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    /**
     * Servicio REST para identificar a un usuario en la fase de registro con su token
     * @param token
     * @return 
     */
    @RequestMapping(value = "/usuarios/{token}", method = GET, produces = "application/json")
    public ResponseEntity<UsuarioDTO> identificarToken(@PathVariable String token) {
        TokenActivacion tokenActivacion = tokenDAO.obtenerToken(token);
        if (tokenActivacion != null) {
            Usuario u = tokenActivacion.getUsuario();
            //En caso de que la cuenta de usuario ya haya sido activada
            if(u.isActivado()){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
                      
            UsuarioDTO uDTO = new UsuarioDTO(u);
            return new ResponseEntity<>(uDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    

    /**
     * Servicio REST para registrar a un usuario
     *
     * @param usuario información del usuario a registrar. Debe contener al
     * menos los campos de correo electronico, rol y contraseña
     * @return ResponseEntity con código correspondiente
     */
    @RequestMapping(value = "/usuarios", method = POST, consumes = "application/json")
    public ResponseEntity<Void> registroUsuario(@RequestBody UsuarioDTO usuario) {

        if (usuario.getRol() == Usuario.Rol.MEDICO) {
            MedicoDTO medicoDTOtmp = new MedicoDTO(usuario);
            if (!gestorMedico.registro(medicoDTOtmp)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }
        if (usuario.getRol() == Usuario.Rol.PACIENTE) {
            PacienteDTO pacienteDTOtmp = new PacienteDTO(usuario);
            if (!gestorPaciente.registro(pacienteDTOtmp)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
