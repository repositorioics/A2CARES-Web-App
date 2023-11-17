package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos demograficos de la casa
 * donde habitan 1 o mas participantes de los estudios
 * 
 * @author Everts Morales
 **/

@Entity
@Table(name = "control_asistencia", catalog = "a2cares")
public class ControlAsistencia extends BaseMetaData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */


    private Integer id;
    private Date fechaasistencia;
    private String horaentrada;
    private String horasalida;
    private Double latitud;
    private Double longitud;


    @Id
    @Column(name = "ID", nullable = false)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name = "FECHAASISTENCIA", nullable = false)
	public Date getFechaasistencia() {
		return fechaasistencia;
	}
	public void setFechaasistencia(Date fechaasistencia) {
		this.fechaasistencia = fechaasistencia;
	}

    @Column(name = "HORAENTRADA", nullable = true, length = 100)
    public String getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    @Column(name = "HORASALIDA", nullable = true, length = 100)
    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    @Column(name = "LATITUD", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }



}
