/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Excepciones.PacienteYaAñadido;

/**
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Medico extends Usuario {

    private Usuario.Rol rol;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Long, EjercicioTerapeutico> ejerciciosCreados;

    @ManyToMany
    private List<Paciente> pacientes;

    /**
     * Constructor por defecto de la clase médico
     */
    public Medico() {
        super();
        this.rol = Usuario.Rol.MEDICO;
        ejerciciosCreados = new HashMap<>();
        pacientes = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de la clase médico
     *
     * @param correoElectronico correo electrónico del médico
     * @param nombre nombre del médico
     * @param apellidos apellidos del médico
     * @param clave contraseña a utilizar
     */
    public Medico(String correoElectronico, String nombre, String apellidos, String clave) {
        super(correoElectronico, nombre, apellidos, clave);
        this.rol = Usuario.Rol.MEDICO;
        ejerciciosCreados = new HashMap<>();
        pacientes = new ArrayList<>();

    }

    /**
     * Método para añadir un ejercicio terapéutico al listado de ejercicios del
     * médico
     *
     * @param ejercicio
     */
    public void crearEjercicioTerapeutico(EjercicioTerapeutico ejercicio) {
        ejerciciosCreados.put(ejercicio.getId(), ejercicio);
    }

    /**
     * Método para modificar un ejercicio terapéutico
     *
     * @param ejercicio método para modificar un ejercicio ya existente
     */
    public void editarEjercicioTerapeutico(EjercicioTerapeutico ejercicio) {
        EjercicioTerapeutico tmp = ejerciciosCreados.get(ejercicio.getId());
        tmp.setTitulo(ejercicio.getTitulo());
        tmp.setDescripcion(ejercicio.getDescripcion());

        ejerciciosCreados.put(ejercicio.getId(), tmp);

    }
    /**
     * Método para añadir un nuevo paciente a la lista de pacientes del médico.
     * 
     * @param paciente 
     */
    public void añadirPaciente(Paciente paciente) {
        if (!pacientes.contains(paciente)) {
            pacientes.add(paciente);
        } else {
            throw new PacienteYaAñadido();
        }

    }

    public MedicoDTO medicoToDTO() {
        MedicoDTO medicoDTO = new MedicoDTO(super.getCorreoElectronico(),
                super.getNombre(), super.getApellidos(), super.getClave());
        return medicoDTO;
    }

    public Medico medicoFromDTO(MedicoDTO medicoDTO) {
        Medico medico = new Medico(medicoDTO.getCorreoElectronico(), medicoDTO.getNombre(),
                medicoDTO.getApellidos(), medicoDTO.getClave());
        return medico;
    }

    /**
     * @return the ejerciciosCreados
     */
    public Map<Long, EjercicioTerapeutico> getEjerciciosCreados() {
        return ejerciciosCreados;
    }

    /**
     * @param ejerciciosCreados the ejerciciosCreados to set
     */
    public void setEjerciciosCreados(Map<Long, EjercicioTerapeutico> ejerciciosCreados) {
        this.ejerciciosCreados = ejerciciosCreados;
    }

    /**
     * @return the pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

}
