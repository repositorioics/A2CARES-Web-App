package ni.org.ics.webapp.domain.Serologia;


import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 17/10/2020.
 */
@Entity
@Table(name = "serologia_envios", catalog = "a2cares")
public class SerologiaEnvio extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idserologiaenvio;
    private Serologia serologia;
    private Integer idenvio;
    private Date fecha;
    private String hora;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEROLOGIA_ENVIO_ID", nullable = false)
    public Integer getIdserologiaenvio() {
        return idserologiaenvio;
    }

    public void setIdserologiaenvio(Integer idserologiaenvio) {
        this.idserologiaenvio = idserologiaenvio;
    }

    @ManyToOne
    @JoinColumn(name="SEROLOGIA_ID", updatable = false)
    @ForeignKey(name = "FK_SEROLOGIA_ID")
    public Serologia getSerologia() {
        return serologia;
    }

    public void setSerologia(Serologia serologia) {
        this.serologia = serologia;
    }

    @Column(name = "ENVIO_ID", nullable = false)
    public Integer getIdenvio() {
        return idenvio;
    }

    public void setIdenvio(Integer idenvio) {
        this.idenvio = idenvio;
    }

    @Column(name = "FECHA_ENVIO", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Column(name = "HORA_ENVIO", nullable = false)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }


}
