package ni.org.ics.webapp.domain.puntos;

import ni.org.ics.webapp.domain.BaseMetaData;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 13/12/2021.
 */
@Entity
@Table(name = "puntos_gps_candidatos", catalog = "a2cares")
public class PuntoCandidato  extends BaseMetaData {

    private static final long serialVersionUID = 1L;

    private Integer codigo;
    private String Barrio;
    private Double latitud;
    private Double longitud;
    private String descartado;
    private String razonDescarte;
    private String otraRazonDescarte;
    private Date fechaDescarte;
    private String usuarioDescarte;

    @Id
    @Column(name = "CODIGO", nullable = false)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE_BARRIO", nullable = true, length = 150)
    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
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

    @Column(name = "DESCARTADO", nullable = true, length = 1)
    public String getDescartado() {
        return descartado;
    }

    public void setDescartado(String descartado) {
        this.descartado = descartado;
    }

    @Column(name="RAZON_DESCARTE", nullable = true, length = 3)
    public String getRazonDescarte() {
        return razonDescarte;
    }

    public void setRazonDescarte(String razonDescarte) {
        this.razonDescarte = razonDescarte;
    }

    @Column(name="OTRA_RAZON_DESCARTE", nullable = true)
    public String getOtraRazonDescarte() {
        return otraRazonDescarte;
    }

    public void setOtraRazonDescarte(String otraRazonDescarte) {
        this.otraRazonDescarte = otraRazonDescarte;
    }

    @Column(name="FECHA_DESCARTE", nullable = true)
    @Temporal(TemporalType.DATE)
    public Date getFechaDescarte() {
        return fechaDescarte;
    }

    public void setFechaDescarte(Date fechaDescarte) {
        this.fechaDescarte = fechaDescarte;
    }

    @Column(name="USUARIO_DESCARTE", nullable = true, length = 50)
    public String getUsuarioDescarte() {
        return usuarioDescarte;
    }

    public void setUsuarioDescarte(String usuarioDescarte) {
        this.usuarioDescarte = usuarioDescarte;
    }
}
