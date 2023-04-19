package ni.org.ics.webapp.domain.Serologia;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 06/01/2022.
 */
@Entity
@Table(name = "bhc_detalle", catalog = "a2cares")
public class Bhc_Detalles_Envio implements Serializable {

    private Integer detalle_id;
    private BhcEnvio bhcEnvio;
    private Bhc bhc;

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
    @JoinColumn(name="BHC_ENVIO_ID", updatable = false)
    @ForeignKey(name = "FK_BHC_ENVIO_ID")
    public BhcEnvio getBhcEnvio() {
        return bhcEnvio;
    }

    public void setBhcEnvio(BhcEnvio bhcEnvio) {
        this.bhcEnvio = bhcEnvio;
    }


    @ManyToOne
    @JoinColumn(name="BHC_ID", updatable = false)
    @ForeignKey(name = "FK_BHC_ID")
    public Bhc getBhc() {
        return bhc;
    }

    public void setBhc(Bhc bhc) {
        this.bhc = bhc;
    }




}
