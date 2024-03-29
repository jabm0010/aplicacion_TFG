/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Seguridad;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Configuration
public class GestionSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    ServicioDatosUsuario servicioDatosUsuario;

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(servicioDatosUsuario);

        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();

        //httpSecurity.authorizeRequests().antMatchers("/pacientes/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/usuarios").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/usuarios/**").permitAll();

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/administrador/**").hasAnyRole("ADMINISTRADOR");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/administrador/**").hasAnyRole("ADMINISTRADOR");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/administrador/**").hasAnyRole("ADMINISTRADOR");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/medicos/{medico}/ejercicios/*").hasAnyRole("MEDICO", "PACIENTE");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/medicos/{medico}/**").access("hasRole('MEDICO') and #medico == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/medicos/{medico}/**").access("hasRole('MEDICO') and #medico == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/medicos/{medico}/**").access("hasRole('MEDICO') and #medico == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/medicos/{medico}").access("hasRole('MEDICO') and #medico == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/medicos/{medico}").access("hasRole('MEDICO') and #medico == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/medicos/{medico}").access("hasRole('MEDICO') and #medico == principal.username");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/pacientes/{paciente}/**").access("hasRole('PACIENTE') and #paciente == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/pacientes/{paciente}/**").access("hasRole('PACIENTE') and #paciente == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/pacientes/{paciente}/**").access("hasRole('PACIENTE') and #paciente == principal.username");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/pacientes/{paciente}/**").access("hasRole('PACIENTE') and #paciente == principal.username");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/terapias/**").access("hasRole('PACIENTE') or hasRole('MEDICO')");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/terapias/**").access("hasRole('PACIENTE') or hasRole('MEDICO')");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/terapias/**").access("hasRole('PACIENTE') or hasRole('MEDICO')");
    }
}
