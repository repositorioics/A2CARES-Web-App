package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Everts Morales
 **/

public class ControlAsistenciaICSDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private String id;
    private String fechaHora;
    private String tipoAsistencia;
    private String nombreCompleto;
    private String cargo;
    private String area;
    private String sitioMarcaje;
    private String usuarioRegistro;
    private String fechaRegistro;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;}

    public String getFechaHora() {  return fechaHora;   }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }


    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }
    public void setArea (String area) {
        this.area = area;
    }

    public String getSitioMarcaje() {
        return sitioMarcaje;
    }
    public void setSitioMarcaje(String sitioMarcaje) {
        this.sitioMarcaje = sitioMarcaje;
    }
}
