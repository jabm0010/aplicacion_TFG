/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Modelo.Medico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorMedico implements InterfazServiciosMedico {

    @Autowired
    MedicoDAO medicoDAO;

    @Override
    public boolean registro(MedicoDTO medico) {

        Medico medicotmp = new Medico();
        medicotmp = medicotmp.MedicoFromDTO(medico);
        medicoDAO.registrarUsuario(medicotmp);
        return true;

    }

}
