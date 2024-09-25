package ni.org.ics.webapp.domain.hemodinamica;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by Lizandro Serrano on 14/08/2024.
 */
@Entity
@Table(name = "datoshemodinamica", catalog = "a2cares")
public class DatosHemodinamica extends BaseMetaData {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String idDatoHemo;
    private Participante participante;
    private int unidadSalud;
    private int sector;
    private String direccion;
    private Date fecha;
    private String nExpediente;
    private String telefono;
    private String edad;
    private String peso;
    private Double talla;
    private Double asc;
    private Double imc;
    private Date fie;
    private Integer diasenf;
    /* Nuevos Campos Agreados*/
    //Sistolica Diastolica
    private String sdMin;
    private String sdMed;
    private String sdMax;

    //Pam
    private String pamMin;
    private String pamMed;
    private String pamMax;

    //Frecuencias Cardiacas
    private Integer fcMin;
    private Integer fcMed;
    private Integer fcProm;

    //Frecuencias Respiratorias
    private Integer frMin;
    private Integer frMax;

    private String barrioF;
    private char positivo = '0';

    private Integer numParametros;

    private String numeroEvento;
    private int numeroPagina;

    /*  Getter y Setter*/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "idDatoHemo", nullable = false)
    public String getIdDatoHemo(){
        return idDatoHemo;
    }

    public void setIdDatoHemo(String idDatoHemo) {
        this.idDatoHemo = idDatoHemo;
    }

    @ManyToOne
    @JoinColumn(name="idParticipante", updatable = false)
    @ForeignKey(name = "FK_idParticipante")
    public Participante getParticipante(){
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "unidad_salud", nullable = false)
    public int getUnidadSalud() {
        return unidadSalud;
    }

    public void setUnidadSalud(int unidadSalud) {
        this.unidadSalud = unidadSalud;
    }

    @Column(name = "sector", nullable = false)
    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    @Column(name = "direccion", nullable = false)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "nExpediente", nullable = false)
    public String getnExpediente() {
        return nExpediente;
    }

    public void setnExpediente(String nExpediente) {
        this.nExpediente = nExpediente;
    }

    @Column(name = "telefono", nullable = false)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Column(name = "edad", nullable = false)
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Column(name = "peso", nullable = false)
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }


    @Column(name = "talla", nullable = false)
    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }


    @Column(name = "areasupcorp", nullable = false)
    public Double getAsc() {
        return asc;
    }

    public void setAsc(Double asc) {
        this.asc = asc;
    }


    @Column(name = "imc", nullable = false)
    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    @Column(name = "fie", nullable = false)
    public Date getFie(){return fie;}

    public void setFie(Date fie) {
        this.fie = fie;
    }

    @Column(name = "diasenf", nullable = false)
    public Integer getDiasenf(){return diasenf;}

    public void setDiasenf(Integer diasenf) {
        this.diasenf = diasenf;
    }

    /*  nuevos campos */
    @Column(name = "sdMin", nullable = true)
    public String getSdMin(){return sdMin;}
    public void setSdMin(String sdMin){this.sdMin = sdMin;}

    @Column(name = "sdMed", nullable = true)
    public String getSdMed(){return sdMed;}
    public void setSdMed(String sdMed){
        this.sdMed = sdMed;
    }

    @Column(name = "sdMax", nullable = true)
    public String getSdMax (){
        return sdMax;
    }
    public void setSdMax(String sdMax){
        this.sdMax = sdMax;
    }

    @Column(name = "pamMed", nullable = true)
    public String getPamMed() {
        return pamMed;
    }

    public void setPamMed(String pamMed) {
        this.pamMed = pamMed;
    }

    @Column(name = "pamMin", nullable = true)
    public String getPamMin() {
        return pamMin;
    }

    public void setPamMin(String pamMin) {
        this.pamMin = pamMin;
    }

    @Column(name = "pamMax", nullable = true)
    public String getPamMax() {
        return pamMax;
    }
    public void setPamMax(String pamMax) {
        this.pamMax = pamMax;
    }

    @Column(name = "fcMin", nullable = true)
    public Integer getFcMin() {
        return fcMin;
    }

    public void setFcMin(Integer fcMin) {
        this.fcMin = fcMin;
    }
    @Column(name = "fcMed", nullable = true)
    public Integer getFcMed() {
        return fcMed;
    }

    public void setFcMed(Integer fcMed) {
        this.fcMed = fcMed;
    }

    @Column(name = "fcProm", nullable = true)
    public Integer getFcProm() {
        return fcProm;
    }

    public void setFcProm(Integer fcProm) {
        this.fcProm = fcProm;
    }

    @Column(name = "frMin", nullable = true)
    public Integer getFrMin() {
        return frMin;
    }

    public void setFrMin(Integer frMin) {
        this.frMin = frMin;
    }


    @Column(name = "frMax", nullable = true)
    public Integer getFrMax() {
        return frMax;
    }

    public void setFrMax(Integer frMax) {
        this.frMax = frMax;
    }


    public String getBarrioF() {
        return barrioF;
    }

    public void setBarrioF(String barrioF) {
        this.barrioF = barrioF;
    }

    @Column(name = "positivo", nullable = true)
    public char getPositivo() {
        return positivo;
    }

    public void setPositivo(char positivo) {
        this.positivo = positivo;
    }

    @Column(name = "numparametros", nullable = true)
    public Integer getNumParametros() {
        return numParametros;
    }

    public void setNumParametros(Integer numParametros) {
        this.numParametros = numParametros;
    }

    @Column(name = "numero_evento", nullable = true, length = 15)
    public String getNumeroEvento() {
        return numeroEvento;
    }

    public void setNumeroEvento(String numeroEvento) {
        this.numeroEvento = numeroEvento;
    }

    @Column(name = "numero_pagina", nullable = false)
    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }
}
