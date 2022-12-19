package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos de una muestra tomada por enfermeria
 * 
 * @author Everts Morales
 **/

public class OrdenLaboratorioDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */


    private String participante;
    private String fechaOrden;
    private String tipoMuestra;
    private String observacion;
    private String fis;
    private String fif;
    private String categoria; //A,B,C
    private String consulta; //Inicial, Seguimiento, Convaleciente
    private String tipoOrden; //Aguda, Convalecient
    //private Date recordDate;
    private String recordUser;



    public String getParticipante() {
        return participante;
    }
    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }
    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }


    public String getTipoOrden() {
        return tipoOrden;
    }
    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
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

    public String getRecordUser() {
        return recordUser;
    }
    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }


}
