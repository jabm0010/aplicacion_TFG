/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ujaen.apptfg.Servidor.Excepciones.ExcepcionUsuarioRegistrado;
import org.ujaen.apptfg.Servidor.Modelo.Medico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MedicoDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void registrarUsuario(Medico medico) {
        try {
            em.persist(medico);
        } catch (RuntimeException ex) {
            throw new ExcepcionUsuarioRegistrado();
        }
    }

    @Transactional
    public Medico buscarMedico(String correoElectronico) {
        Medico medico = em.find(Medico.class, correoElectronico);
        return medico;
    }

    @Transactional
    public void actualizarMedico(Medico medico) {
        em.merge(medico);
    }

    @Transactional
    public void borrarMedico(String correoElectronico) {
        Medico medico = em.find(Medico.class, correoElectronico);
        em.remove(medico);
    }

}
