/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public interface InterfazServiciosMedico {
    
    void registro(MedicoDTO medico);
    
    void crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico);
    
    
    
}
