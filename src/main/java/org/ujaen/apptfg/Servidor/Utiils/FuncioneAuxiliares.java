/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Utiils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Antonio Béjar Martos
 */
public class FuncioneAuxiliares {
    
    
    /**
     * Método parametrizable para eliminar valores duplicados de una lsita
     * @param <T>
     * @param lista
     * @return 
     */
    public static <T> List<T> eliminarValoresDuplicadosLista(List<T> lista){
        ArrayList<T> listaRet = new ArrayList<T>(); 
        for (T elemento : lista) { 
            if (!listaRet.contains(elemento)) { 
                listaRet.add(elemento); 
            } 
        } 
        return listaRet; 
    }
}
