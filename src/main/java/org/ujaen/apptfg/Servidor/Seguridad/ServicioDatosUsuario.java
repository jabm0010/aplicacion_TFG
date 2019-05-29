/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.MedicoDAO;
import org.ujaen.apptfg.Servidor.DAOs.PacienteDAO;
import org.ujaen.apptfg.Servidor.Modelo.Medico;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class ServicioDatosUsuario implements UserDetailsService {

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Medico m;
        Paciente p;
        m = medicoDAO.buscarMedico(userName);
        p = pacienteDAO.buscarPaciente(userName);
        UserDetails userDetails;
        if (m == null && p == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        } else {
            if (m != null) {
                userDetails = User.withUsername(userName).password("{bcrypt}" + m.getClave()).roles("MEDICO").build();
            } else {
                userDetails = User.withUsername(userName).password("{bcrypt}" + p.getClave()).roles("PACIENTE").build();
            }
        }

        return userDetails;
    }
}
