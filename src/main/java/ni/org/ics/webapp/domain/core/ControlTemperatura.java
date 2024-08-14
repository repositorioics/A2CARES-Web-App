package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;

import java.util.Date;

/**
 * Simple objeto de dominio que representa un barrio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "control_temperatura_termo", catalog = "a2cares")
public class ControlTemperatura extends BaseMetaData implements Auditable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Date fechatemp;
    private String horatomatemp;
    private Double temperaturaTermo;
    private String usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "FECHA_TEMPERATURA", nullable = true)
    public Date getFechatemp() { return fechatemp; }

    public void setFechatemp(Date fechatemp) {
        this.fechatemp = fechatemp;
    }
    @Column(name = "HORA_TEMPERATURA", nullable = true)
    public String getHoratomatemp() {
        return horatomatemp;
    }

    public void setHoratomatemp(String horatomatemp) {
        this.horatomatemp = horatomatemp;
    }
    @Column(name = "TEMPERATURA_TERMO", nullable = true)
    public Double getTemperaturaTermo() {
        return temperaturaTermo;
    }

    public void setTemperaturaTermo(Double temperaturaTermo) {
        this.temperaturaTermo = temperaturaTermo;
    }
    @Column(name = "USUARIO_TOMA_TEMPERATURA", nullable = true)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

    @Override
    public String toString() {
        return "'" + id + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ControlTemperatura)) return false;

        ControlTemperatura admision_pacientes = (ControlTemperatura) o;

        return (!id.equals(admision_pacientes.id));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
