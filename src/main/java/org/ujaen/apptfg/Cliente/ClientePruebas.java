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

    public void run() {

        try {
            System.out.println("Ejecutando cliente pruebas");

            InterfazServiciosMedico serviciosMedico = (InterfazServiciosMedico) context.getBean("gestorMedico");

            for (int i = 0; i < 5; i++) {
                String correo = "usuario" + i + "@gmail.com";
                String nombre = "nombre" + i;
                String apellidos = "apellidos" + i;
                String clave = "clave" + i;
                MedicoDTO medicoDTO = new MedicoDTO(correo, nombre, apellidos, clave);
                serviciosMedico.registro(medicoDTO);
            }

            for (int i = 0; i < 10; i++) {

                String nombreEjercicio = "ejercicio" +i;
                String descripcionEjercicio = "descripcion" +i;
                EjercicioTerapeuticoDTO ejercicioTerapeutico = new EjercicioTerapeuticoDTO(nombreEjercicio, descripcionEjercicio);
                serviciosMedico.crearEjercicioTerapeutico(ejercicioTerapeutico, "usuario1@gmail.com");
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
