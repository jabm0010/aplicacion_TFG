/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.ServiciosWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RestController;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Servicios.GestorMedico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class ServiciosMedicoREST {

    @Autowired
    GestorMedico gestorMedico;

    @RequestMapping(value = "/saludo", method = GET)
    public String saludar() {
          System.out.println("E");
        String saludo = "hola";
        
        return saludo;
    }

    @RequestMapping(value = "/medico", method = POST, consumes = "application/json")
    public boolean registrar(@RequestBody MedicoDTO medico) {
        System.out.println("Hola");
        MedicoDTO tmp = medico;

        gestorMedico.registro(tmp);
        
        System.out.println("Fine");
        return true;
    }

}
