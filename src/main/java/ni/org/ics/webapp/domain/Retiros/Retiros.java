package ni.org.ics.webapp.domain.Retiros;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 29/10/2020.
 */

@Entity
@Table(name = "documentacion_retiro_data", catalog = "a2cares")
public class Retiros extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idretiro;
    private Participante participante;
    private Integer codigo_casa;
    private Date fecha_retiro;
    private String quien_comunica;
    private Integer relfam;
    private Integer persona_documenta;
    private Integer medico_supervisor;
    private String motivo;
    private String otros_motivo;
    private Character devolvio_carnet;
    private String observaciones;
    private Date fecha_fallecido;

    public Retiros() {}

    public Retiros(Integer idretiro, Participante participante, Integer codigo_casa, Date fecha_retiro, String quien_comunica, Integer medico_supervisor, Integer relfam,  Integer persona_documenta, String motivo, String otros_motivo, Character devolvio_carnet, String observaciones,  Date fecha_fallecido, String estudio_retirado, Boolean actual) {
        this.idretiro = idretiro;
        this.participante = participante;
        this.codigo_casa = codigo_casa;
        this.fecha_retiro = fecha_retiro;
        this.quien_comunica = quien_comunica;
        this.relfam = relfam;
        this.persona_documenta = persona_documenta;
        this.motivo = motivo;
        this.otros_motivo = otros_motivo;
        this.devolvio_carnet = devolvio_carnet;
        this.observaciones = observaciones;
        this.fecha_fallecido = fecha_fallecido;
        this.medico_supervisor = medico_supervisor;
    }


    @Id
    @Column(name = "ID_RETIRO", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdretiro() {
        return idretiro;
    }

    public void setIdretiro(Integer idretiro) {
        this.idretiro = idretiro;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_CODIGO_PARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "CODIGO_CASA", nullable = true)
    public Integer getCodigo_casa() {
        return codigo_casa;
    }

    public void setCodigo_casa(Integer codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    @Column(name = "FECHA_RETIRO", nullable = true)
    public Date getFecha_retiro() {
        return fecha_retiro;
    }

    public void setFecha_retiro(Date fecha_retiro) {
        this.fecha_retiro = fecha_retiro;
    }

    @Column(name = "QUIEN_COMUNICA", nullable = true)
    public String getQuien_comunica() {
        return quien_comunica;
    }

    public void setQuien_comunica(String quien_comunica) {
        this.quien_comunica = quien_comunica;
    }

    @Column(name = "PERSONA_DOCUMENTA", nullable = true)
    public Integer getPersona_documenta() {
        return persona_documenta;
    }

    public void setPersona_documenta(Integer persona_documenta) {
        this.persona_documenta = persona_documenta;
    }

    @Column(name = "MEDICO_SUPERVISOR", nullable = true)
    public Integer getMedico_supervisor() {
        return medico_supervisor;
    }

    public void setMedico_supervisor(Integer medico_supervisor) {
        this.medico_supervisor = medico_supervisor;
    }

    @Column(name = "OTRO_MOTIVO_RETIRO", nullable = true)
    public String getOtros_motivo() {
        return otros_motivo;
    }

    public void setOtros_motivo(String otros_motivo) {
        this.otros_motivo = otros_motivo;
    }

    @Column(name = "DEVOLVIO_CARNET", nullable = true)
    public Character getDevolvio_carnet() {
        return devolvio_carnet;
    }

    public void setDevolvio_carnet(Character devolvio_carnet) {
        this.devolvio_carnet = devolvio_carnet;
    }

    @Column(name = "FECHA_FALLECIDO", nullable = true)
    public Date getFecha_fallecido() {
        return fecha_fallecido;
    }

    public void setFecha_fallecido(Date fecha_fallecido) {
        this.fecha_fallecido = fecha_fallecido;
    }

    @Column(name = "RELFAM", nullable = true)
    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }

    @Column(name = "MOTIVO_RETIRO", nullable = true)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Column(name = "OBSERVACIONES", nullable = true)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }



    @Override
    public boolean isFieldAuditable(String fieldname) {
        // TODO Auto-generated method stub
        return true;
    }


}
