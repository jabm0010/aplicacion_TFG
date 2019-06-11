/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.util.List;
import java.util.UUID;
import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.AdministradorDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.TokenActivacionDAO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Modelo.Administrador;
import org.ujaen.apptfg.Servidor.Seguridad.GestionRegistro;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.TokenActivacion;
import org.ujaen.apptfg.Servidor.Utiils.ServicioCorreo;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorAdministrador implements InterfazServiciosAdministrador {

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    GestionRegistro gestionRegistro;
    
    @Autowired
    AdministradorDAO administradorDAO;

    /**
     * Primera parte del registro de usuarios de tipo médico. Se almacena un
     * usuario con su correo electrónico, nombre y apellidos, y se envía un
     * correo electrónico con un enlace para confirmar el registro.
     *
     * @param medico
     * @return
     */
    @Override
    public boolean registro(MedicoDTO medico) {

        Medico medicotmp = new Medico();
        medicotmp = Medico.medicoFromDTO(medico);
        medicotmp.setActivado(false);
        medicoDAO.registrarUsuario(medicotmp);

        try {
            gestionRegistro.envioCorreo(medicotmp);

        } catch (Exception e) {
            e.printStackTrace();
            //En caso de que exista un fallo en la generación del correo de activación de la cuenta se borra al médico
            medicoDAO.borrarMedico(medico.getCorreoElectronico());
            return false;
        }

        return true;

    }

    @Override
    public MedicoDTO obtenerMedico(String medico) {
        return medicoDAO.buscarMedico(medico).medicoToDTO();

    }

    @Override
    public void modificarMedico(MedicoDTO medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico.getCorreoElectronico());
        medicotmp.setNombre(medico.getNombre());
        medicotmp.setApellidos(medico.getApellidos());
        medicotmp.setVersionCuenta(medico.getVersionCuenta());
        
        medicoDAO.actualizarMedico(medicotmp);
    }

    @Override
    public void crearAdministrador(String correoElectronico, String clave) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String claveEncriptada = passwordEncoder.encode(clave);
        Administrador admin = new Administrador(correoElectronico,claveEncriptada);
        
        administradorDAO.registrarUsuario(admin);
        
    }

}
