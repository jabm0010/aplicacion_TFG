/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DAOs.AdministradorDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DAOs.TokenActivacionDAO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.UsuarioDTO;
import org.ujaen.apptfg.Servidor.Modelo.Administrador;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;
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
    
    @Autowired
    AdministradorDAO administradorDAO;

    /**
     * Servicio REST para identificar a un usuario en la fase de registro con su
     * token
     * 
     *
     * @param token
     * @return GONE si el token de activación ha expirado. CONFLICT si no se ha encontrado el token de activación en el
     * repositorio. UsuarioDTO con código de respuesta OK si el token ha sido correctamente identificado.
     */
    @RequestMapping(value = "/usuarios/{token}", method = GET, produces = "application/json")
    public ResponseEntity<UsuarioDTO> identificarTokenRegistro(@PathVariable String token) {
        TokenActivacion tokenActivacion = tokenDAO.obtenerToken(token);
        Calendar cal = Calendar.getInstance();

        if (tokenActivacion != null) {
            if (tokenActivacion.getFechaExpiración().getTime() - cal.getTime().getTime() <= 0) {
                tokenDAO.borrarToken(tokenActivacion);
                return new ResponseEntity<>(HttpStatus.GONE);
            }

            Usuario u = tokenActivacion.getUsuario();
            //En caso de que la cuenta de usuario ya haya sido activada
            if (u.isActivado()) {
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

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/usuarios/login", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UsuarioDTO> identificarUsuarioLogin(@RequestBody UsuarioDTO usuario) throws UnsupportedEncodingException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        byte[] claveByte = Base64.decodeBase64(usuario.getClave());
        String clave = new String(claveByte);
        clave = clave.trim();
        Medico m = medicoDAO.buscarMedico(usuario.getCorreoElectronico());
        Paciente p = pacienteDAO.buscarPaciente(usuario.getCorreoElectronico());
        Administrador a = administradorDAO.buscarAdministrador(usuario.getCorreoElectronico());
        if (m != null) {
            if (passwordEncoder.matches(clave, m.getClave()) && m.isActivado()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setRol(Usuario.Rol.MEDICO);
                return new ResponseEntity<>(u, HttpStatus.OK);
            }
        } else if (p != null) {
            if (passwordEncoder.matches(clave, p.getClave()) && p.isActivado()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setRol(Usuario.Rol.PACIENTE);
                return new ResponseEntity<>(u, HttpStatus.OK);
            }
        } else if (a != null){
            if (passwordEncoder.matches(clave, a.getClave())) {
                UsuarioDTO u = new UsuarioDTO();
                u.setRol(Usuario.Rol.ADMINISTRADOR);
                return new ResponseEntity<>(u, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
