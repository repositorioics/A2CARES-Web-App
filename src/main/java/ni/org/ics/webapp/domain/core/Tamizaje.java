package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "tamizajes", catalog = "a2cares")
public class Tamizaje extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigo;
    private String aceptaTamizajePersona;
    private String razonNoAceptaTamizajePersona;
    private String otraRazonNoAceptaTamizajePersona;
    private Estudio estudio;
    private String sexo;
    private Date fechaNacimiento;
    private String tipoVivienda;
    private String tiempoResidencia;
    private String planesMudarse;
    private String asentimientoVerbal;
    private String aceptaParticipar;
    private String razonNoAceptaParticipar;
    private String otraRazonNoAceptaParticipar;
    private String esElegible;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_ESTUDIO", nullable = false)
    @ForeignKey(name = "FK_ESTUDIO_TAMIZAJE")
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Column(name = "SEXO", length = 50)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "FECHA_NACIMIENTO", nullable = true)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name="ACEPTA_TAMIZAJE", nullable = false, length = 1)
    public String getAceptaTamizajePersona() {
        return aceptaTamizajePersona;
    }

    public void setAceptaTamizajePersona(String aceptaTamizajePersona) {
        this.aceptaTamizajePersona = aceptaTamizajePersona;
    }

    @Column(name="RAZON_NO_ACEPTA_TAMIZAJE", nullable = true, length = 50)
    public String getRazonNoAceptaTamizajePersona() {
        return razonNoAceptaTamizajePersona;
    }

    public void setRazonNoAceptaTamizajePersona(String razonNoAceptaTamizajePersona) {
        this.razonNoAceptaTamizajePersona = razonNoAceptaTamizajePersona;
    }

    @Column(name="OTRA_RAZON_NO_ACEPTA_TAMIZAJE", nullable = true)
    public String getOtraRazonNoAceptaTamizajePersona() {
        return otraRazonNoAceptaTamizajePersona;
    }

    public void setOtraRazonNoAceptaTamizajePersona(String otraRazonNoAceptaTamizajePersona) {
        this.otraRazonNoAceptaTamizajePersona = otraRazonNoAceptaTamizajePersona;
    }

    @Column(name="TIEMPO_RESIDENCIA", nullable = true, length = 1)
    public String getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(String tiempoResidencia) {
        this.tiempoResidencia = tiempoResidencia;
    }

    @Column(name="TIPO_VIVIENDA", nullable = true, length = 1)
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    @Column(name="PLANES_MUDARSE", nullable = true, length = 1)
    public String getPlanesMudarse() {
        return planesMudarse;
    }

    public void setPlanesMudarse(String planesMudarse) {
        this.planesMudarse = planesMudarse;
    }

    @Column(name="ASENTIMIENTO_VERBAL", nullable = true, length = 1)
    public String getAsentimientoVerbal() {
        return asentimientoVerbal;
    }

    public void setAsentimientoVerbal(String asentimientoVerbal) {
        this.asentimientoVerbal = asentimientoVerbal;
    }

    @Column(name="ACEPTA_PARTICIPAR", nullable = true, length = 1)
    public String getAceptaParticipar() {
        return aceptaParticipar;
    }

    public void setAceptaParticipar(String aceptaParticipar) {
        this.aceptaParticipar = aceptaParticipar;
    }

    @Column(name="RAZON_NO_ACEPTA_PARTICIPAR", nullable = true, length = 10)
    public String getRazonNoAceptaParticipar() {
        return razonNoAceptaParticipar;
    }

    public void setRazonNoAceptaParticipar(String razonNoAceptaParticipar) {
        this.razonNoAceptaParticipar = razonNoAceptaParticipar;
    }

    @Column(name="O_RAZON_NO_ACEPTA_PARTICIPAR", nullable = true)
    public String getOtraRazonNoAceptaParticipar() {
        return otraRazonNoAceptaParticipar;
    }

    public void setOtraRazonNoAceptaParticipar(String otraRazonNoAceptaParticipar) {
        this.otraRazonNoAceptaParticipar = otraRazonNoAceptaParticipar;
    }

    @Column(name="ELEGIBLE", nullable = true, length = 1)
    public String getEsElegible() {
        return esElegible;
    }

    public void setEsElegible(String esElegible) {
        this.esElegible = esElegible;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tamizaje)) return false;

        Tamizaje tamizaje = (Tamizaje) o;

        return (!codigo.equals(tamizaje.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
