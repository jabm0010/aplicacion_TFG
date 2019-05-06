/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ujaen.apptfg.Servidor.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.springframework.transaction.annotation.Transactional;
import org.ujaen.apptfg.Servidor.DTOs.MedicoDTO;
import org.ujaen.apptfg.Servidor.Excepciones.EjerciciosNoValidos;
import org.ujaen.apptfg.Servidor.Excepciones.MaximoPacientesAlcanzado;
import org.ujaen.apptfg.Servidor.Excepciones.PacienteYaAñadido;

/**
 * @author Juan Antonio Béjar Martos
 */
@Entity
public class Medico extends Usuario {

    public enum versionCuenta {
        BASICA,
        PREMIUM
    }

    public static int NUMERO_MAXIMO_PACIENTES = 100;

    private versionCuenta versionCuenta;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Long, EjercicioTerapeutico> ejerciciosCreados;

    @ManyToMany
    private Map<String, Paciente> pacientes;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Paciente, HistorialMedico> historialesMedicos;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Terapia> listaTerapias;

    /**
     * Constructor por defecto de la clase médico
     */
    public Medico() {
        super();
        super.setRol(Rol.MEDICO);
        ejerciciosCreados = new HashMap<>();
        pacientes = new HashMap<>();
    }

    /**
     * Constructor parametrizado de la clase médico
     *
     * @param correoElectronico correo electrónico del médico
     * @param nombre nombre del médico
     * @param apellidos apellidos del médico
     * @param clave contraseña a utilizar
     * @param versionCuenta versión de la cuenta del médico
     */
    public Medico(String correoElectronico, String nombre, String apellidos, String clave, versionCuenta versionCuenta) {
        super(correoElectronico, nombre, apellidos, clave);
        super.setRol(Rol.MEDICO);
        ejerciciosCreados = new HashMap<>();
        pacientes = new HashMap<>();
        this.versionCuenta = versionCuenta;
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
        tmp.setVideoEjercicio(ejercicio.getVideoEjercicio());

        ejerciciosCreados.put(ejercicio.getId(), tmp);

    }

    /**
     * Método para añadir un nuevo paciente a la lista de pacientes del médico.
     *
     * @param paciente
     */
    public void añadirPaciente(Paciente paciente) {
        if (this.pacientes.size() == NUMERO_MAXIMO_PACIENTES) {
            throw new MaximoPacientesAlcanzado();
        }

        if (!pacientes.containsKey(paciente.getCorreoElectronico())) {
            pacientes.put(paciente.getCorreoElectronico(), paciente);
        } else {
            throw new PacienteYaAñadido();
        }

    }

    /**
     * Método para crear una nueva terapia
     *
     * @param ejercicios lista con objetos de tipo InfoEjerciciosTerapia que
     * contiene el código del ejercicio y su duración
     * @param fechasTerapia listado de fechas en los que se ha de realizar la
     * terapia
     * @param comentarios comentarios de terapia
     */
    public Terapia crearTerapia(List<InfoEjerciciosTerapia> ejercicios,
            List<LocalDate> fechasTerapia, String comentarios) {
        Terapia t = new Terapia();
        t.setFechas(fechasTerapia);
        t.setComentarios(comentarios);

        try {
            List<EjercicioTerapeutico> listadoEjercicios = obtenerEjercicios(ejercicios);
            ejercicios.forEach((i) -> {
                i.setTituloEjercicio(this.ejerciciosCreados.get(i.getCodigoEjercicio()).getTitulo());
            });
            t.setListaEjercicios(listadoEjercicios);
            t.setDuracionesEjercicios(ejercicios);

        } catch (RuntimeException e) {
            e.toString();
        }
        getListaTerapias().add(t);
        return t;
    }

    /**
     * Método para crear una nueva terapia y asignarla a un paciente
     *
     * @param identificadorPaciente identificador del paciente al que se
     * asignará la terapia
     *
     */
    public void asignarTerapia(String identificadorPaciente, Terapia t) {

        Paciente p = new Paciente();
        try {
            p = pacientes.get(identificadorPaciente);
        } catch (RuntimeException e) {
            e.toString();
        }

        p.getTerapiasPaciente().add(t);

    }

    /**
     * Metodo interno que devuelve los ejercicios asociados a los códigos
     * pasados. En caso de que alguno de los códigos no exista se lanza una
     * excepción EjerciciosNoValidos
     *
     * @param ejercicios listado de códigos de ejercicios que se quiere
     * comprobar
     * @return
     */
    private List<EjercicioTerapeutico> obtenerEjercicios(List<InfoEjerciciosTerapia> ejercicios) {
        List<EjercicioTerapeutico> listaEjercicios = new ArrayList<>();

        for (InfoEjerciciosTerapia e : ejercicios) {
            if (ejerciciosCreados.containsKey(e.getCodigoEjercicio())) {
                listaEjercicios.add(ejerciciosCreados.get(e.getCodigoEjercicio()));
            } else {
                throw new EjerciciosNoValidos();
            }
        }

        return listaEjercicios;
    }

    //!!!revisar eficiencia de este método -> ¿añadir un mapa key = paciente value = terapias?
    /**
     * Método para obtener las terapias asignadas por un médico a un paciente
     *
     * @param p
     * @return
     */
    public List<Terapia> obtenerTerapias(String p) {
        Paciente paciente = pacientes.get(p);
        List<Terapia> listaTerapias_ret = new ArrayList<>();
        listaTerapias_ret = paciente.getTerapiasPaciente();
        for (Terapia t : listaTerapias_ret) {
            if (!listaTerapias.contains(t)) {
                listaTerapias_ret.remove(t);
            }
        }

        return listaTerapias_ret;
    }

    /**
     * Método para inicializar el historial médico de un paciente
     *
     * @param p
     */
    public void crearHistorialMedico(Paciente p) {
        HistorialMedico h = new HistorialMedico();
        getHistorialesMedicos().put(p, h);
    }

    /**
     * Método para obtener el historial médico asociado a un paciente
     *
     * @param p paciente del que se quiere obtener su historial médico
     * @return
     */
    public HistorialMedico obtenerHistorialMedico(Paciente p) {
        return getHistorialesMedicos().get(p);
    }

    public void borrarHistorialMedico(Paciente p) {
        getHistorialesMedicos().remove(p);
    }

    public void modificarHistorialMedico(String texto, Paciente p) {
        HistorialMedico h = getHistorialesMedicos().get(p);
        h.nuevoComentario(texto);

    }

    public MedicoDTO medicoToDTO() {
        MedicoDTO medicoDTO = new MedicoDTO(super.getCorreoElectronico(),
                super.getNombre(), super.getApellidos(), super.getClave(), this.versionCuenta);
        return medicoDTO;
    }

    public static Medico medicoFromDTO(MedicoDTO medicoDTO) {
        Medico medico = new Medico(medicoDTO.getCorreoElectronico(), medicoDTO.getNombre(),
                medicoDTO.getApellidos(), medicoDTO.getClave(), medicoDTO.getVersionCuenta());
        return medico;
    }

    /**
     * @return the ejerciciosCreados
     */
    public Map<Long, EjercicioTerapeutico> getEjerciciosCreados() {
        return ejerciciosCreados;
    }

    public EjercicioTerapeutico obtenerEjercicio(Long id) {
        return ejerciciosCreados.get(id);
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
    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * @return the versionCuenta
     */
    public versionCuenta getVersionCuenta() {
        return versionCuenta;
    }

    /**
     * @param versionCuenta the versionCuenta to set
     */
    public void setVersionCuenta(versionCuenta versionCuenta) {
        this.versionCuenta = versionCuenta;
    }

    /**
     * @return the historialesMedicos
     */
    public Map<Paciente, HistorialMedico> getHistorialesMedicos() {
        return historialesMedicos;
    }

    /**
     * @param historialesMedicos the historialesMedicos to set
     */
    public void setHistorialesMedicos(Map<Paciente, HistorialMedico> historialesMedicos) {
        this.historialesMedicos = historialesMedicos;
    }

    /**
     * @return the listaTerapias
     */
    public List<Terapia> getListaTerapias() {
        return listaTerapias;
    }

    /**
     * @param listaTerapias the listaTerapias to set
     */
    public void setListaTerapias(List<Terapia> listaTerapias) {
        this.listaTerapias = listaTerapias;
    }

}
