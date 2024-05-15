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
    private String direccion_cambio_domicilio;
    private String telefono_cambio_domicilio;
    private String telefono_1_Actualizado ;
    private String telefono_2_Actualizado ;

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

    @Column(name="DIRECCION_CAMBIO_DOMICILIO", nullable = true, length = 100)
    public String getDireccion_cambio_domicilio() {
        return direccion_cambio_domicilio;
    }
    public void setDireccion_cambio_domicilio(String direccion_cambio_domicilio) {
        this.direccion_cambio_domicilio = direccion_cambio_domicilio;
    }

    @Column(name="TELEFONO_CAMBIO_DOMICILIO", nullable = true, length = 100)
    public String getTelefono_cambio_domicilio() {
        return telefono_cambio_domicilio;
    }
    public void setTelefono_cambio_domicilio(String telefono_cambio_domicilio) {
        this.telefono_cambio_domicilio = telefono_cambio_domicilio;
    }
    @Column(name="TELEFONO_1_ACTUALIZADO", nullable = true, length = 100)
    public String getTelefono_1_Actualizado() {
        return telefono_1_Actualizado;
    }

    public void setTelefono_1_Actualizado(String telefono_1_Actualizado) {
        this.telefono_1_Actualizado = telefono_1_Actualizado;
    }
    @Column(name="TELEFONO_2_ACTUALIZADO", nullable = true, length = 100)
    public String getTelefono_2_Actualizado() {
        return telefono_2_Actualizado;
    }

    public void setTelefono_2_Actualizado(String telefono_2_Actualizado) {
        this.telefono_2_Actualizado = telefono_2_Actualizado;
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
