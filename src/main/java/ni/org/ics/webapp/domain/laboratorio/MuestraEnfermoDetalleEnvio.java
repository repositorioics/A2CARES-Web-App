package ni.org.ics.webapp.domain.laboratorio;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 06/01/2022.
 */
@Entity
@Table(name = "mx_enfermos_detalles_envios", catalog = "a2cares")
public class MuestraEnfermoDetalleEnvio implements Serializable {

    private Integer idDetalle;
    private MuestraEnfermoEnvio envio;
    private RecepcionEnfermo muestra;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DETALLE", nullable = false)
    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer detalle_id) {
        this.idDetalle = detalle_id;
    }


    @ManyToOne
    @JoinColumn(name="ID_ENVIO", updatable = false)
    @ForeignKey(name = "FK_MUESTRA_ENFERMO_ENVIO_ID")
    public MuestraEnfermoEnvio getEnvio() {
        return envio;
    }

    public void setEnvio(MuestraEnfermoEnvio serologiaEnvio) {
        this.envio = serologiaEnvio;
    }


    @ManyToOne
    @JoinColumn(name="ID_RECEPCION", updatable = false)
    @ForeignKey(name = "FK_RECEPCION_ENFERMO_ID")
    public RecepcionEnfermo getMuestra() {
        return muestra;
    }

    public void setMuestra(RecepcionEnfermo serologia) {
        this.muestra = serologia;
    }




}
