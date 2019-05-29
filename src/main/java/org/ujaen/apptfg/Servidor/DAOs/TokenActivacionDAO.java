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
import org.ujaen.apptfg.Servidor.Modelo.TokenActivacion;


/**
 *
 * @author Juan Antonio Béjar Martos
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TokenActivacionDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void nuevoToken(TokenActivacion t) {
        em.persist(t);
    }
    
    @Transactional
    public TokenActivacion obtenerToken(String id){
        return em.find(TokenActivacion.class, id);
    }
    
    @Transactional
    public void borrarToken(TokenActivacion t){
        em.remove(t);
    }
}
