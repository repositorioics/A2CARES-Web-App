package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Miguel Salinas
 **/

public class RecepcionEnfermoMovilDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idRecepcion;
    private Date fechaRecepcion;
    private String participante;
	private String tipoTubo; //Serogologia, BHC, etc
	private Double volumen;
	private String observacion;
	private Date FIS;
	private Date FIF;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente
	private String tipoMuestra; //Aguda, Convaleciente

    private String positivo;


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

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
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

    public Date getFIS() {
        return FIS;
    }

    public void setFIS(Date FIS) {
        this.FIS = FIS;
    }

    public Date getFIF() {
        return FIF;
    }

    public void setFIF(Date FIF) {
        this.FIF = FIF;
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



    public String getPositivo() {
        return positivo;
    }

    public void setPositivo(String positivo) {
        this.positivo = positivo;
    }

}
