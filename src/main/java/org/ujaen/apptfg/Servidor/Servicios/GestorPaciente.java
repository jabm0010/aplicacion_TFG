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
public class GestorPaciente implements InterfazServiciosPaciente {

    @Autowired
    PacienteDAO pacienteDAO;

    @Autowired
    ImagenDAO imagenDAO;

    @Override
    public boolean registro(PacienteDTO paciente) {
        Paciente pacienteRegistro = new Paciente();

        try {
            pacienteRegistro = pacienteDAO.buscarPaciente(paciente.getCorreoElectronico());
            Imagen imagentmp = new Imagen(paciente.getImagen(), paciente.getNombreImagen());
            imagenDAO.guardarImagen(imagentmp);
            pacienteRegistro.setImagenperfil(imagentmp);
            pacienteRegistro.setClave(paciente.getClave());
            pacienteRegistro.setActivado(true);
            pacienteDAO.actualizarPaciente(pacienteRegistro);
        } catch (Exception e) {
            imagenDAO.borrarImagen(pacienteRegistro.getImagenperfil().getId());
            return false;
        }

        return true;
    }
}
