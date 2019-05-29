/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ujaen.apptfg.Servidor.Excepciones.ExcepcionUsuarioRegistrado;
import org.ujaen.apptfg.Servidor.Modelo.Paciente;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PacienteDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void registrarUsuario(Paciente paciente) {
        try {
            em.persist(paciente);
        } catch (RuntimeException ex) {
            throw new ExcepcionUsuarioRegistrado();
        }
    }

    @Transactional
    public Paciente buscarPaciente(String id) {
        return em.find(Paciente.class, id);

    }

    @Transactional
    public boolean existePaciente(String id) {

        return em.find(Paciente.class, id) != null;
    }

    @Transactional
    public void actualizarPaciente(Paciente p) {

        em.merge(p);
    }

    @Transactional
    public void borrarPaciente(String id) {
        em.remove(em.find(Paciente.class, id));
    }

}
