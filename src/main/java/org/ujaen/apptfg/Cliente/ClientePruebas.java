/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Cliente;

import org.springframework.context.ApplicationContext;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Servicios.InterfazServiciosMedico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class ClientePruebas {
    ApplicationContext context;
    
     public ClientePruebas(ApplicationContext context) {
        this.context = context;
    }
     
    public void run(){
        
        try{
        System.out.println("Ejecutando cliente pruebas");
        
        InterfazServiciosMedico serviciosMedico = (InterfazServiciosMedico) context.getBean("gestorMedico");
        
        String correo = "jabm979@gmail.com";
        String nombre = "Pepe";
        String apellidos = "Martinez";
        String clave = "pwd";
        
        MedicoDTO medicoDTO = new MedicoDTO(correo, nombre, apellidos,clave);
        
        serviciosMedico.registro(medicoDTO);
        
        EjercicioTerapeuticoDTO ejercicioTerapeutico = new EjercicioTerapeuticoDTO("Terapia espalda","abcdefg");
        EjercicioTerapeuticoDTO ejercicioTerapeutico2 = new EjercicioTerapeuticoDTO("Terapia pierna","abcdefg");
         
        serviciosMedico.crearEjercicioTerapeutico(ejercicioTerapeutico, correo);
        serviciosMedico.crearEjercicioTerapeutico(ejercicioTerapeutico2, correo);
        System.out.println("Ejercicio creado");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
