package ni.org.ics.webapp.domain.personal;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos demograficos de la casa
 * donde habitan 1 o mas participantes de los estudios
 * 
 * @author Everts Morales
 **/

@Entity
@Table(name = "justificaciones_ics", catalog = "a2cares")
public class JustificacionesICS extends BaseMetaData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */


    private String idUser;
    private String fechaInicio;
    private String fechaFin;
    private String idTipoJustificacion;
    private String descripcionJust;



    @Id
    @Column(name = "IDUSER", nullable = false)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

	@Column(name = "FECHA_INICIO", nullable = false)
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

    @Column(name = "FECHA_FIN", nullable = false)
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Column(name = "ID_TIPO_JUSTIFICACION", nullable = true, length = 100)
    public String getIdTipoJustificacion() {
        return idTipoJustificacion;
    }
    public void setIdTipoJustificacion(String idTipoJustificacion) {
        this.idTipoJustificacion = idTipoJustificacion;
    }

    @Column(name = "DESCRIPCION_JUST", nullable = true, length = 100)
    public String getDescripcionJust() {
        return descripcionJust;
    }
    public void setDescripcionJust(String descripcionJust) {
        this.descripcionJust = descripcionJust;
    }

}
