/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ujaen.apptfg.Servidor.DAOs.TokenActivacionDAO;
import org.ujaen.apptfg.Servidor.Modelo.TokenActivacion;
import org.ujaen.apptfg.Servidor.Modelo.Usuario;
import org.ujaen.apptfg.Servidor.Utiils.ServicioCorreo;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Component
public class GestionRegistro {

    @Autowired
    ServicioCorreo servicioCorreo;

    @Autowired
    TokenActivacionDAO tokenActivacionDAO;

    private String subjectCorreoRegistro;

    private String url = "http://127.0.0.1:5500/public_html/registro-usuario/registro-usuario.html";

    public GestionRegistro() {
        this.subjectCorreoRegistro = "Bienvenido a la aplicación";

    }

    public void envioCorreo(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        TokenActivacion t = new TokenActivacion();
        t.setToken(token);
        t.setUsuario(usuario);
        tokenActivacionDAO.nuevoToken(t);

        String textoCorreoRegistro = "Para comenzar a utilizar la aplicación accede al siguiente enlace: ";

        textoCorreoRegistro += (url + "?token=" + token);
        servicioCorreo.sendSimpleMessage(usuario.getCorreoElectronico(), subjectCorreoRegistro, textoCorreoRegistro);
    }

}
