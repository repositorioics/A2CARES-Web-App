package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Representa los datos de la entrega de obsequios
 * 
 * @author Miguel Salinas
 **/
@Entity
@Table(name = "obsequios", catalog = "a2cares")
public class ObsequioGeneral extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
    private String id;
    private String participante;
    private String casa;
    private String motivo;//1 MA, 2 Seguimiento
    private String entregoObsequio;
    private String personaRecibe;
    private String observacion;
    private String seguimiento;
    private String numVisitaSeguimiento;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = true)
    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    @Column(name = "CODIGO_CASA", nullable = true, length = 5)
    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    @Column(name = "CODIGO_SEGUIMIENTO", nullable = true, length = 36)
    public String getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    @Column(name = "VISITA", nullable = true, length = 2)
    public String getNumVisitaSeguimiento() {
        return numVisitaSeguimiento;
    }

    public void setNumVisitaSeguimiento(String numVisitaSeguimiento) {
        this.numVisitaSeguimiento = numVisitaSeguimiento;
    }

    @Column(name = "MOTIVO", nullable = false, length = 3)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Column(name = "ENTREGO_OBSEQUIO", nullable = true)
    public String getEntregoObsequio() {
        return entregoObsequio;
    }

    public void setEntregoObsequio(String entregoObsequio) {
        this.entregoObsequio = entregoObsequio;
    }

    @Column(name = "PERSONA_RECIBE", nullable = true, length = 250)
    public String getPersonaRecibe() {
        return personaRecibe;
    }

    public void setPersonaRecibe(String personaRecibe) {
        this.personaRecibe = personaRecibe;
    }

    @Column(name = "OBSERVACION", nullable = true, length = 250)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


    @Override
    public String toString() {
        return id;
    }
}
