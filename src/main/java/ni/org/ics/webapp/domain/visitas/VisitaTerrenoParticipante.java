package ni.org.ics.webapp.domain.visitas;

import java.util.Date;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by FIRSTICT on 08/09/2018.
 * V1.0
 */
@Entity
@Table(name = "visitas_terreno_participante", catalog = "a2cares")
public class VisitaTerrenoParticipante extends BaseMetaData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoVisita;
	private Participante participante;
	private Date fechaVisita;
	private String visitaExitosa;
	private String razonVisitaNoExitosa;
    private String otraRazonVisitaNoExitosa;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigoVisita() {
        return codigoVisita;
    }

    public void setCodigoVisita(String codigoVisita) {
        this.codigoVisita = codigoVisita;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_VT")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "FECHA_VISITA", nullable = false)
    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    @Column(name = "VISITA_EXITOSA", nullable = false, length = 2)
    public String getVisitaExitosa() {
        return visitaExitosa;
    }

    public void setVisitaExitosa(String visitaExitosa) {
        this.visitaExitosa = visitaExitosa;
    }

    @Column(name = "RAZON_VISITA_NO_EXITOSA", nullable = true, length = 3)
    public String getRazonVisitaNoExitosa() {
        return razonVisitaNoExitosa;
    }

    public void setRazonVisitaNoExitosa(String razonVisitaNoExitosa) {
        this.razonVisitaNoExitosa = razonVisitaNoExitosa;
    }

    @Column(name = "OTRA_RAZON_VISITA_NO_EXITOSA", nullable = true)
    public String getOtraRazonVisitaNoExitosa() {
        return otraRazonVisitaNoExitosa;
    }

    public void setOtraRazonVisitaNoExitosa(String otraRazonVisitaNoExitosa) {
        this.otraRazonVisitaNoExitosa = otraRazonVisitaNoExitosa;
    }

    @Override
    public String toString() {
        return "'" + codigoVisita + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaTerrenoParticipante)) return false;

        VisitaTerrenoParticipante visita = (VisitaTerrenoParticipante) o;

        return (!codigoVisita.equals(visita.codigoVisita));
    }

    @Override
    public int hashCode() {
        return codigoVisita.hashCode();
    }
}
