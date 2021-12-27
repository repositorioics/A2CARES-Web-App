package ni.org.ics.webapp.domain.scancarta;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.catalogs.Parte;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS on 03/08/2021.
 */
@Entity
@Table(name = "scan_detalle_parte_tmp", catalog = "a2cares")
public class DetalleParteTmp extends BaseMetaData implements Auditable {


    private Integer iddetalle;
    ParticipanteCartaTmp participantecartatmp;
    Parte parte;
    private boolean acepta;

    public DetalleParteTmp(){}

    @Id
    @Column(name = "IDDETALLE_TMP", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PARTICIPANTECARTA_TMP", nullable = false)
    @ForeignKey(name = "FK_ID_PARTICIPANTECARTA_TMP")
    public ParticipanteCartaTmp getParticipantecartatmp() {
        return participantecartatmp;
    }

    public void setParticipantecartatmp(ParticipanteCartaTmp participantecartatmp) {
        this.participantecartatmp = participantecartatmp;
    }

    @ManyToOne
    @JoinColumn(name = "IDPARTE", nullable = false)
    @ForeignKey(name = "FK_DET_PARTE_TMP_IDPARTE")
    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    @Column(name = "ACEPTA")
    public boolean isAcepta() {
        return acepta;
    }

    public void setAcepta(boolean acepta) {
        this.acepta = acepta;
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
