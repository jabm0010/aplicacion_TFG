/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class TokenActivacion {

    private static final int EXPIRATION = 60 * 24;

    @Id
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    private Date fechaExpiración;

    private Date calcularFechaExpiración(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fechaExpiración
     */
    public Date getFechaExpiración() {
        return fechaExpiración;
    }

    /**
     * @param fechaExpiración the fechaExpiración to set
     */
    public void setFechaExpiración(Date fechaExpiración) {
        this.fechaExpiración = fechaExpiración;
    }
}
