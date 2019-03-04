/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Cliente;

import org.springframework.context.ApplicationContext;
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
        
        
        System.out.println("ppppp");
        serviciosMedico.registro(medicoDTO); 
        //  Detail: Key (correo_electronico)=(jabm97@gmail.com) already exists.
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
