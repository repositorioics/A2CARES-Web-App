package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;

import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos de una muestra tomada por enfermeria
 * 
 * @author Everts Morales
 **/

public class MuestrasEnfermosDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private String idMuestra;
    private String participante;
    private String fechaMuestra;
    private String horaMuestra;
    private String tipoTubo; //Serogologia, BHC, etc
    private Double volumen;
    private String observacion;
    private String fis;
    private String fif;
    private String categoria; //A,B,C
    private String consulta; //Inicial, Seguimiento, Convaleciente
    private String tipoMuestra; //Aguda, Convalecient
    //private Date recordDate;
    private String recordUser1;
    private String usuarioRegistro;

    public String getidMuestra() {
        return idMuestra;
    }
    public void setidMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getParticipante() {
        return participante;
    }
    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFechaMuestra() {
        return fechaMuestra;
    }
    public void setFechaMuestra(String fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public String getHoraMuestra() {
        return horaMuestra;
    }
    public void setHoraMuestra(String horaMuestra) {
        this.horaMuestra = horaMuestra;
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


  /*  public Date getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }*/

    public String getRecordUser1() {
        return recordUser1;
    }
    public void setRecordUser1(String recordUser1) {
        this.recordUser1 = recordUser1;
    }
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }
    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

}
