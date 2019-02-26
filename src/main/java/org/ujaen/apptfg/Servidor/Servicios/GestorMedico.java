/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.Modelo.Medico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestorMedico implements InterfazServiciosMedico{
    
    @Autowired
    MedicoDAO medicoDAO;
    
    @Override
    public boolean registro(String correoElectronico, String nombre, String apellidos) {
        Medico medico = new Medico(correoElectronico, nombre, apellidos);
        medicoDAO.registrarUsuario(medico);
        return true;

    }


    
    
}
