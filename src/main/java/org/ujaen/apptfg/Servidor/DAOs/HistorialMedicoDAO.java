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
import org.ujaen.apptfg.Servidor.Modelo.HistorialMedico;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HistorialMedicoDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void crearHistorialMedico(HistorialMedico historialMedico) {
        em.persist(historialMedico);
    }
    
    @Transactional
    public void borrarHistorialMedico(long id){
        em.remove(em.find(HistorialMedico.class, id));
    }
}
