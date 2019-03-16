/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.ImagenDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.Modelo.Imagen;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorPaciente implements InterfazServiciosPaciente{
    @Autowired
    PacienteDAO pacienteDAO;
    
    @Autowired
    ImagenDAO imagenDAO;



    @Override
    public void registro(PacienteDTO paciente) {

        Paciente pacientetmp = new Paciente();
        pacientetmp = pacientetmp.pacienteFromDTO(paciente);
        
         Imagen imagentmp = new Imagen(paciente.getImagen(), paciente.getNombreImagen());
         
        imagenDAO.guardarImagen(imagentmp);
        
        pacientetmp.setImagenperfil(imagentmp);
        pacienteDAO.registrarUsuario(pacientetmp);
        

    }
}
