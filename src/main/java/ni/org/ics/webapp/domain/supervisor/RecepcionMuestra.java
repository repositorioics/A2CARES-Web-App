package ni.org.ics.webapp.domain.supervisor;

import ni.org.ics.webapp.domain.BaseMetaData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Miguel Salinas on 30/8/2021.
 */

@Entity
@Table(name = "recepcion_muestras", catalog = "a2cares")
public class RecepcionMuestra extends BaseMetaData {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @Column(name = "ID_RECEPCION", length = 50, nullable = false)
    private String idRecepcion;

    @Column(name = "CODIGO_MX", length = 50, nullable = false)
    private String codigoMx;

    @Column(name = "FECHA_RECEPCION", nullable = false)
    private Date fechaRecepcion;

    @Column(name="VOLUMEN", nullable = true)
    private Double volumen;

    @Column(name="LUGAR", length = 10, nullable = true)
    private String lugar;

    @Column(name="OBSERVACION", nullable = true)
    private String observacion;

    @Column(name = "TIPO_TUBO", length = 5, nullable = true)
    private String tipoTubo;

    public String getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(String idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public String getCodigoMx() {
        return codigoMx;
    }

    public void setCodigoMx(String codigoMx) {
        this.codigoMx = codigoMx;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoTubo() {
        return tipoTubo;
    }

    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }
}
