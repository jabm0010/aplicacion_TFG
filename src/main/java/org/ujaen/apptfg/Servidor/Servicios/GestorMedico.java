/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.EjercicioTerapeuticoDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Modelo.EjercicioTerapeutico;
import org.ujaen.apptfg.Servidor.Modelo.Medico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorMedico implements InterfazServiciosMedico {

    @Autowired
    MedicoDAO medicoDAO;
    
    @Autowired
    EjercicioTerapeuticoDAO ejercicioDAO;
    
    @Override
    public void registro(MedicoDTO medico) {

        Medico medicotmp = new Medico();
        medicotmp = medicotmp.medicoFromDTO(medico);
        medicoDAO.registrarUsuario(medicotmp);
        

    }

    @Override
    public void crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO, String medicoId) {
        Medico medico = medicoDAO.buscarMedico(medicoId);
        
        EjercicioTerapeutico ejercicioTerapeutico = new EjercicioTerapeutico();
        ejercicioTerapeutico = ejercicioTerapeutico.ejercicioTerapeuticoFromDTO(ejercicioTerapeuticoDTO);     
        medico.crearEjercicioTerapeutico(ejercicioTerapeutico);

        medicoDAO.actualizarMedico(medico);
    }

}
