/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Cliente;

import org.springframework.context.ApplicationContext;
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
        
        String correo = "jabm97@gmail.com";
        String nombre = "Pepe";
        String apellidos = "Martinez";
        
        serviciosMedico.registro(correo, nombre, apellidos);
        
        serviciosMedico.registro(correo, nombre, apellidos); 
        //  Detail: Key (correo_electronico)=(jabm97@gmail.com) already exists.
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
