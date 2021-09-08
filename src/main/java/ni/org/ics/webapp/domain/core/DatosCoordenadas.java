package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;


/**
 *  Objeto que representa CambioDomicilio
 * @author Miguel Salinas 10/5/2021
 **/

@Entity
@Table(name = "datos_coordenadas", catalog = "a2cares")
public class DatosCoordenadas extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String codigo;
    private Integer codigoCasa;
    private String estudios;
    private Participante participante;
    private String motivo;
	private Barrio barrio;
    private String otroBarrio;
	private String direccion;
    private Integer manzana;
    private String conpunto;
    private String puntoGps;
	private Double latitud;
	private Double longitud;
    private String razonNoGeoref;
    private String otraRazonNoGeoref;
    private boolean actual;
    private String observacion;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "CODIGO_CASA", nullable = true, length = 4)
    public Integer getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(Integer codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    @Column(name = "ESTUDIOS_ACTUALES", nullable = true, length = 255)
    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_COORD")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "MOTIVO", nullable = true, length = 2)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_BARRIO", nullable = true)
    @ForeignKey(name = "FK_BARRIO_COORD")
    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    @Column(name = "OTRO_BARRIO", nullable = true, length = 250)
    public String getOtroBarrio() {
        return otroBarrio;
    }

    public void setOtroBarrio(String otroBarrio) {
        this.otroBarrio = otroBarrio;
    }

    @Column(name = "DIRECCION", nullable = true, length = 250)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "MANZANA", nullable = true, length = 4)
    public Integer getManzana() {
        return manzana;
    }

    public void setManzana(Integer manzana) {
        this.manzana = manzana;
    }

    @Column(name = "CON_PUNTO_GPS", nullable = true, length = 2)
    public String getConpunto() {
        return conpunto;
    }

    public void setConpunto(String conpunto) {
        this.conpunto = conpunto;
    }

    @Column(name = "PUNTO_GPS", nullable = true, length = 100)
    public String getPuntoGps() {
        return puntoGps;
    }

    public void setPuntoGps(String puntoGps) {
        this.puntoGps = puntoGps;
    }

    @Column(name = "LATITUD", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Column(name = "RAZON_SIN_PUNTO", nullable = true, length = 2)
    public String getRazonNoGeoref() {
        return razonNoGeoref;
    }

    public void setRazonNoGeoref(String razonNoGeoref) {
        this.razonNoGeoref = razonNoGeoref;
    }

    @Column(name = "OTRA_RAZON_SIN_PUNTO", nullable = true, length = 250)
    public String getOtraRazonNoGeoref() {
        return otraRazonNoGeoref;
    }

    public void setOtraRazonNoGeoref(String otraRazonNoGeoref) {
        this.otraRazonNoGeoref = otraRazonNoGeoref;
    }

    @Column(name = "OBSERVACION", nullable = true, length = 250)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "ACTUAL", nullable = true)
    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    @Override
	public String toString(){
		return this.codigo + " " + this.latitud+" "+this.longitud;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatosCoordenadas)) return false;

        DatosCoordenadas casa = (DatosCoordenadas) o;

        return (!codigo.equals(casa.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
