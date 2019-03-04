/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.ujaen.apptfg.Cliente.ClientePruebas;

/**
 *
 * @author Juan Antonio Béjar Martos
 */

@SpringBootApplication()
public class ServidorTerapias {

    public static void main(String[] args) {
        SpringApplication servidor = new SpringApplication(ServidorTerapias.class);
        ApplicationContext context = servidor.run(args);
        System.out.println("TFG");
        
        ClientePruebas cliente = new ClientePruebas(context);
        cliente.run();
        
        
        
    }

}
