/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ujaen.apptfg.Servidor.DTOs.EjercicioTerapeuticoDTO;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.DTOs.PacienteDTO;
import org.ujaen.apptfg.Servidor.DTOs.TerapiaDTO;
import org.ujaen.apptfg.Servidor.Modelo.InfoEjerciciosTerapia;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
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

          
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            for (int i = 0; i < 5; i++) {
                String correo = "usuario" + i + "@gmail.com";
                String nombre = "nombre" + i;
                String apellidos = "apellidos" + i;
                String clave = "clave" + i;

                clave = passwordEncoder.encode(clave);
                Medico.versionCuenta v = Medico.versionCuenta.BASICA;
                MedicoDTO medicoDTO = new MedicoDTO(correo, nombre, apellidos, clave, v);

                serviciosMedico.registroPruebas(medicoDTO);
            }

            for (int i = 0; i < 10; i++) {

                String nombreEjercicio = "ejercicio" + i;
                String descripcionEjercicio = "descripcion" + i;
                EjercicioTerapeuticoDTO ejercicioTerapeutico = new EjercicioTerapeuticoDTO(nombreEjercicio, descripcionEjercicio);
                serviciosMedico.crearEjercicioTerapeutico(ejercicioTerapeutico, "usuario1@gmail.com",null);
            }

            PacienteDTO p = new PacienteDTO("paciente@gmail.com", "paciente0", "paciente0apellidos");
            p.setClave(passwordEncoder.encode("clave"));
            

            serviciosMedico.añadirPaciente("usuario1@gmail.com", p);

            TerapiaDTO t = new TerapiaDTO();
            t.setComentarios("comentario 1");
            List<InfoEjerciciosTerapia> listaInfoEjerciciosTerapia = new ArrayList<>();
            InfoEjerciciosTerapia info1 = new InfoEjerciciosTerapia();
            info1.setCodigoEjercicio(Long.valueOf(1));
            info1.setDuracionEjercicio(5);
            InfoEjerciciosTerapia info2 = new InfoEjerciciosTerapia();
            info2.setCodigoEjercicio(Long.valueOf(2));
            info2.setDuracionEjercicio(8);
            listaInfoEjerciciosTerapia.add(info1);
            listaInfoEjerciciosTerapia.add(info2);
            t.setEjerciciosTerapia(listaInfoEjerciciosTerapia);
            List<LocalDate> fechas = new ArrayList<>();

            LocalDate inputDate = LocalDate.of(2019, 4, 20);
            LocalDate inputDate2 = LocalDate.of(2019, 5, 3);

            fechas.add(inputDate);
            fechas.add(inputDate2);
            t.setFechas(fechas);

            serviciosMedico.asignarTerapia("paciente@gmail.com", "usuario1@gmail.com", t);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
