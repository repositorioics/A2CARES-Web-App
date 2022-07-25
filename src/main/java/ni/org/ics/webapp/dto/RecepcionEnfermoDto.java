package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Miguel Salinas
 **/

public class RecepcionEnfermoDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idRecepcion;
    private String fechaRecepcion;
    private String participante;
	private String tipoTubo; //Serogologia, BHC, etc
	private Double volumen;
	private String observacion;
	private String fis;
	private String fif;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente
	private String tipoMuestra; //Aguda, Convaleciente
    private String edadParteAnios;
    private String edadParteMeses;
    private String edadParteDias;
    private int codigoCasa;
    private String nombreCompleto;
    private String enviado;
    private String evento;
    private String codigoBarra;

    public String getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(String idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getTipoTubo() {
        return tipoTubo;
    }

    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFis() {
        return fis;
    }

    public void setFis(String fis) {
        this.fis = fis;
    }

    public String getFif() {
        return fif;
    }

    public void setFif(String fif) {
        this.fif = fif;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getEdadParteAnios() {
        return edadParteAnios;
    }

    public void setEdadParteAnios(String edadParteAnios) {
        this.edadParteAnios = edadParteAnios;
    }

    public String getEdadParteMeses() {
        return edadParteMeses;
    }

    public void setEdadParteMeses(String edadParteMeses) {
        this.edadParteMeses = edadParteMeses;
    }

    public String getEdadParteDias() {
        return edadParteDias;
    }

    public void setEdadParteDias(String edadParteDias) {
        this.edadParteDias = edadParteDias;
    }

    public int getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
}
