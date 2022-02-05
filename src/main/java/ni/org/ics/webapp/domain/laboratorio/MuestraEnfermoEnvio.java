package ni.org.ics.webapp.domain.laboratorio;


import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 17/10/2020.
 */
@Entity
@Table(name = "mx_enfermos_envios", catalog = "a2cares")
public class MuestraEnfermoEnvio extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idEnvio;
    //private Serologia serologia;
    private Integer numeroEnvio;
    private Date fecha;
    private String hora;
    private Integer sitio;
    private Double temperatura;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ENVIO", nullable = false)
    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idserologiaenvio) {
        this.idEnvio = idserologiaenvio;
    }
    @Column(name = "NUMERO_ENVIO", nullable = false)
    public Integer getNumeroEnvio() {
        return numeroEnvio;
    }

    public void setNumeroEnvio(Integer idenvio) {
        this.numeroEnvio = idenvio;
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

    @Column(name = "ENVIADO_DESDE", nullable = false)
    public Integer getSitio() {
        return sitio;
    }

    public void setSitio(Integer sitio) {
        this.sitio = sitio;
    }

    @Column(name = "TEMPERATURA", nullable = false)
    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MuestraEnfermoEnvio)) return false;

        MuestraEnfermoEnvio that = (MuestraEnfermoEnvio) o;

        if (!idEnvio.equals(that.idEnvio)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idEnvio.hashCode();
    }

    @Override
    public String toString() {
        return "MuestrasEnfermoEnvio{" + idEnvio + '}';
    }
}
