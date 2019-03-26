/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
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
    ServicioCorreo servicioCorreo;
    
    private String subjectCorreoRegistro;
    private String textoCorreoRegistro="";

    public GestorAdministrador() {
        this.subjectCorreoRegistro = "Bienvenido a la aplicación";
        this.textoCorreoRegistro = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod "
                + "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"
                + " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor "
                + "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint"
                + " occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"";
    }

    @Override
    public void registro(MedicoDTO medico) {
        Medico medicotmp = new Medico();
        medicotmp = Medico.medicoFromDTO(medico);
        
        medicoDAO.registrarUsuario(medicotmp);
        
        servicioCorreo.sendSimpleMessage(medico.getCorreoElectronico(), subjectCorreoRegistro,textoCorreoRegistro );
        
        
    }

    @Override
    public MedicoDTO obtenerMedico(String medico) {
        return medicoDAO.buscarMedico(medico).medicoToDTO();
       
    }

    @Override
    public void modificarMedico(MedicoDTO medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico.getCorreoElectronico());
        medicotmp = Medico.medicoFromDTO(medico);
        medicoDAO.actualizarMedico(medicotmp);
    }
    
    

}
