package ni.org.ics.webapp.domain.Serologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 14/10/2020.
 */

@Entity
@Table(name = "bhc_recepcion", catalog = "a2cares")
public class Bhc extends BaseMetaData implements Auditable, Serializable  {

   private static final long serialVersionUID = 1L;

    private Integer idbhc;
    private String participante;
    private Date fecha;
    private double volumen;
    private String observacion;
    private char enviado = '0';
    private Integer codigo_casa;
    private Integer edadMeses;
    private String descripcion;



    //getter and setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BHC_ID", nullable = false)
    public Integer getIdbhc() {
        return idbhc;
    }

    public void setIdbhc(Integer idbhc) {
        this.idbhc = idbhc;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    @Column(name = "FECHA_TOMA", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "VOLUMEN", nullable = false)
    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Column(name = "OBSERVACION", nullable = true)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "ENVIADO", nullable = true)
    public char getEnviado() {
        return enviado;
    }

    public void setEnviado(char enviado) {
        this.enviado = enviado;
    }

    @Column(name = "CODIGO_CASA", nullable = true)
    public Integer getCodigo_casa() {
        return codigo_casa;
    }

    public void setCodigo_casa(Integer codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    @Column(name = "EDAD_MESES", nullable = true)
    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
    }

    @Column(name = "DESCRIPCION_INGRESO", nullable = true, length = 80)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //fin setter

    @Transient
    public boolean isEsEnviado() {
        return this.enviado =='1';
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bhc)) return false;

        Bhc bhc = (Bhc) o;

        if (!idbhc.equals(bhc.idbhc)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idbhc.hashCode();
    }
    @Override
    public String toString() {
        return "idbhc=" + idbhc;
    }

}
