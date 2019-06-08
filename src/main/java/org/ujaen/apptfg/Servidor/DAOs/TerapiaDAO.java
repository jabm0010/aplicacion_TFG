/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.DAOs;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ujaen.apptfg.Servidor.Modelo.Terapia;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TerapiaDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void crearTerapia(Terapia t) {
        em.persist(t);
    }

    @Transactional
    public Terapia obtenerTerapia(Long id) {
        System.out.println("Estoy dentro");
        return em.find(Terapia.class, id);
    }

    @Transactional
    public void actualizarTerapia(Long id) {
        em.merge(em.find(Terapia.class, id));
    }

    @Transactional
    public void actualizarTerapia(Terapia t) {
        em.merge(t);
    }

}
