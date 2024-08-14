package ni.org.ics.webapp.domain.personal;

import ni.org.ics.webapp.domain.BaseMetaData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos demograficos de la casa
 * donde habitan 1 o mas participantes de los estudios
 * 
 * @author Everts Morales
 **/

@Entity
@Table(name = "asistencia_ics", catalog = "a2cares")
public class ControlAsistenciaICS extends BaseMetaData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */


    private Integer id;
    private String fechaHora;
    private String tipoAsistencia;
    private String sensorId;
    private String nombreCompleto;
    private String cargo;
    private String area;


    @Id
    @Column(name = "IDEMP", nullable = false)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name = "FECHA_HORA", nullable = false)
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

    @Column(name = "TIPO_ASISTENCIA", nullable = true, length = 100)
    public String getTipoAsistencia() {
        return tipoAsistencia;
    }
    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    @Column(name = "SENSORID", nullable = true, length = 100)
    public String getSensorId() {
        return sensorId;
    }
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Column(name = "NOMBRE_COMPLETO", nullable = true)
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Column(name = "CARGO", nullable = true)
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Column(name = "SITIO_MARCACION", nullable = true)
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }



}
