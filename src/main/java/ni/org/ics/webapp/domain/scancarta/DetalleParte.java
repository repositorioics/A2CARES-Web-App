package ni.org.ics.webapp.domain.scancarta;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.catalogs.Parte;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "scan_detalle_parte", catalog = "a2cares", uniqueConstraints = { @UniqueConstraint(columnNames = "IDDETALLE") })
public class  DetalleParte extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer iddetalle;
    ParticipanteCarta participantecarta;
    Parte parte;
    private boolean acepta;
    private boolean anulada;
    private String usuarioModifica;
    private Date fechaModifica;

    public DetalleParte(){}

    @Id
    @Column(name = "IDDETALLE", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    @ManyToOne
    @JoinColumn(name = "IDPARTICIPANTECARTA", nullable = false)
    @ForeignKey(name = "FK_IDPARTICIPANTECARTA")
    public ParticipanteCarta getParticipantecarta() {
        return participantecarta;
    }

    public void setParticipantecarta(ParticipanteCarta participantecarta) {
        this.participantecarta = participantecarta;
    }

    @ManyToOne
    @JoinColumn(name = "IDPARTE", nullable = false)
    @ForeignKey(name = "FK_IDPARTE")
    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    @Column(name = "ACEPTA")
    public Boolean getAcepta() {
        return acepta;
    }

    public void setAcepta(Boolean acepta) {
        this.acepta = acepta;
    }

    @Column(name = "ANULADA")
    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    @Column(name = "USUARIO_MODIFICA", nullable = true, length = 255)
    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    @Column(name = "FECHA_MODIFICACION", nullable = true)
    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
