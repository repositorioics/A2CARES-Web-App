package ni.org.ics.webapp.domain.Serologia;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 06/01/2022.
 */
@Entity
@Table(name = "serologia_detalle", catalog = "a2cares")
public class Serologia_Detalles_Envio implements Serializable {

    private Integer detalle_id;
    private SerologiaEnvio serologiaEnvio;
    private Serologia serologia;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DETALLE_ID", nullable = false)
    public Integer getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(Integer detalle_id) {
        this.detalle_id = detalle_id;
    }


    @ManyToOne
    @JoinColumn(name="SEROLOGIA_ENVIO_ID", updatable = false)
    @ForeignKey(name = "FK_SEROLOGIA_ENVIO_ID")
    public SerologiaEnvio getSerologiaEnvio() {
        return serologiaEnvio;
    }

    public void setSerologiaEnvio(SerologiaEnvio serologiaEnvio) {
        this.serologiaEnvio = serologiaEnvio;
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




}
