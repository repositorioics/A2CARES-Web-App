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
    private String idRecepcion;
    private String codigoMx;
    private Date fechaRecepcion;
    private Double volumen;
    private String lugar;
    private String observacion;
    private String tipoTubo;

    @Id
    @Column(name = "ID_RECEPCION", length = 50, nullable = false)
    public String getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(String idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    @Column(name = "CODIGO_MX", length = 50, nullable = false)
    public String getCodigoMx() {
        return codigoMx;
    }

    public void setCodigoMx(String codigoMx) {
        this.codigoMx = codigoMx;
    }

    @Column(name = "FECHA_RECEPCION", nullable = false)
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    @Column(name="VOLUMEN", nullable = true)
    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    @Column(name="LUGAR", length = 10, nullable = true)
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Column(name="OBSERVACION", nullable = true)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "TIPO_TUBO", length = 5, nullable = true)
    public String getTipoTubo() {
        return tipoTubo;
    }

    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }
}
