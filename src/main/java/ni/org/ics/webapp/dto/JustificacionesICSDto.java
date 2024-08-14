package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Everts Morales
 **/

public class JustificacionesICSDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private String idUsr;
    private String idTipoJustificacion;
    private String descripcionJust;
    private String fechaInicio;
    private String fechaFin;
    private String usuarioRegistro;
    private String fechaRegistro;


    public String getIdUsr() {
        return idUsr;
    }
    public void setIdUsr(String idUsr) {
        this.idUsr = idUsr;}

    public String getIdTipoJustificacion() {
        return idTipoJustificacion;
    }

    public void setIdTipoJustificacion(String idTipoJustificacion) {
        this.idTipoJustificacion = idTipoJustificacion;
    }

    public String getDescripcionJust() {
        return descripcionJust;
    }

    public void setDescripcionJust(String descripcionJust) {
        this.descripcionJust = descripcionJust;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
