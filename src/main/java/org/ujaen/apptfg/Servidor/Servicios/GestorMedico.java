/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Servicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.EjercicioTerapeuticoDAO;
import org.ujaen.apptfg.Servidor.DAOs.ImagenDAO;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Modelo.EjercicioTerapeutico;
import org.ujaen.apptfg.Servidor.Modelo.Imagen;
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
    
    @Autowired
    ImagenDAO imagenDAO;

    @Override
    public void registro(MedicoDTO medico) {

        Medico medicotmp = new Medico();
        medicotmp = medicotmp.medicoFromDTO(medico);
        Imagen imagentmp = new Imagen(medico.getImagen(), medico.getNombreImagen());
        
        /*Problema con guardar imagen antes de registrar usuario:
        Si ya existe un usuario registrado con el mismo correo, aunque se deniegue el registro del usuario
        se seguirá guardando la imagen asociada a este.     
        */
        imagenDAO.guardarImagen(imagentmp);
        
        medicotmp.setImagenperfil(imagentmp);
        
        medicoDAO.registrarUsuario(medicotmp);

    }

    @Override
    public void crearEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeuticoDTO, String medicoId) {
        Medico medico = medicoDAO.buscarMedico(medicoId);

        EjercicioTerapeutico ejercicioTerapeutico = new EjercicioTerapeutico();
        ejercicioTerapeutico = ejercicioTerapeutico.ejercicioTerapeuticoFromDTO(ejercicioTerapeuticoDTO);
        ejercicioDAO.crearEjercicioTerapeutic(ejercicioTerapeutico);
        medico.crearEjercicioTerapeutico(ejercicioTerapeutico);

        medicoDAO.actualizarMedico(medico);
    }

    @Override
    public List<EjercicioTerapeuticoDTO> obtenerEjercicios(String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);

        List<EjercicioTerapeuticoDTO> ret_ejerciciosTerapeuticos = new ArrayList<>();
        List<EjercicioTerapeutico> ejerciciosTerapeuticosSource;
        ejerciciosTerapeuticosSource = new ArrayList<>(medicotmp.getEjerciciosCreados().values());

        ejerciciosTerapeuticosSource.forEach((e) -> {
            ret_ejerciciosTerapeuticos.add(e.ejercicioTerapeuticoToDTO());
        });

        return ret_ejerciciosTerapeuticos;
    }

    @Override
    public void guardarEjercicioTerapeutico(EjercicioTerapeuticoDTO ejercicioTerapeutico, String medico) {
        Medico medicotmp = medicoDAO.buscarMedico(medico);
        EjercicioTerapeutico ejerciciotmp = new EjercicioTerapeutico();
        ejerciciotmp = ejerciciotmp.ejercicioTerapeuticoFromDTO(ejercicioTerapeutico);
        medicotmp.editarEjercicioTerapeutico(ejerciciotmp);
        
        medicoDAO.actualizarMedico(medicotmp);

    }

    

}
