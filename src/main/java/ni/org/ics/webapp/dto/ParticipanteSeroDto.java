package ni.org.ics.webapp.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 19/10/2020.
 */
public class ParticipanteSeroDto {

    private String codigo;
    private Integer idSerologia;
    private String idparticipante;
    private double volumen;
    private Integer edadMeses;
    private String nombreCompleto;
    private Integer codigo_casa;
    private String edad_year;
    private String edad_meses;
    private String edad_dias;
    private Integer estado;
    private Date fechaNacimiento;
    private String edadParticipante;
    private ArrayList<String> SusEstudios;
    private String nombrepadre;
    private String nombremadre;
    private String nombretutor;
    private String observacion;
    private Date fecha;

    public ParticipanteSeroDto() {
    }

    public ParticipanteSeroDto(String codigo, Integer idSerologia, String idparticipante, double volumen, Integer edadMeses, String nombreCompleto, Integer codigo_casa, String edad_year, String edad_meses, String edad_dias, Integer estado, Date fechaNacimiento, String edadParticipante, ArrayList<String> susEstudios, String nombrepadre, String nombremadre, String nombretutor, String observacion, Date fecha) {
        this.codigo = codigo;
        this.idSerologia = idSerologia;
        this.idparticipante = idparticipante;
        this.volumen = volumen;
        this.edadMeses = edadMeses;
        this.nombreCompleto = nombreCompleto;
        this.codigo_casa = codigo_casa;
        this.edad_year = edad_year;
        this.edad_meses = edad_meses;
        this.edad_dias = edad_dias;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.edadParticipante = edadParticipante;
        SusEstudios = susEstudios;
        this.nombrepadre = nombrepadre;
        this.nombremadre = nombremadre;
        this.nombretutor = nombretutor;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCodigo_casa() {
        return codigo_casa;
    }

    public void setCodigo_casa(Integer codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdadParticipante() {
        return edadParticipante;
    }

    public void setEdadParticipante(String edadParticipante) {
        this.edadParticipante = edadParticipante;
    }

    public ArrayList<String> getSusEstudios() {
        return SusEstudios;
    }

    public void setSusEstudios(ArrayList<String> susEstudios) {
        SusEstudios = susEstudios;
    }

    public String getNombrepadre() {
        return nombrepadre;
    }

    public void setNombrepadre(String nombrepadre) {
        this.nombrepadre = nombrepadre;
    }

    public String getNombremadre() {
        return nombremadre;
    }

    public void setNombremadre(String nombremadre) {
        this.nombremadre = nombremadre;
    }

    public String getNombretutor() {
        return nombretutor;
    }

    public void setNombretutor(String nombretutor) {
        this.nombretutor = nombretutor;
    }

    public String getEdad_year() {
        return edad_year;
    }

    public void setEdad_year(String edad_year) {
        this.edad_year = edad_year;
    }

    public String getEdad_meses() {
        return edad_meses;
    }

    public void setEdad_meses(String edad_meses) {
        this.edad_meses = edad_meses;
    }

    public String getEdad_dias() {
        return edad_dias;
    }

    public void setEdad_dias(String edad_dias) {
        this.edad_dias = edad_dias;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdSerologia() {
        return idSerologia;
    }

    public void setIdSerologia(Integer idSerologia) {
        this.idSerologia = idSerologia;
    }

    public String getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(String idparticipante) {
        this.idparticipante = idparticipante;
    }

    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
}
