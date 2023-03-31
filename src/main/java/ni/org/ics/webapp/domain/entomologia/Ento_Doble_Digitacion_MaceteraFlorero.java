package ni.org.ics.webapp.domain.entomologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Everts Morales on 20/03/2023.
 */
@Entity
@Table(name = "ento_doble_digitacion_maceteraFlorero", catalog = "a2cares")
public class Ento_Doble_Digitacion_MaceteraFlorero extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;

   private String codigoMaceterFloreros;
   //PG1. Ubicación del recipiente <Fuera / Dentro>
    private String ubicacionRecipiente;
   //MF1.1 ¿Tiene agua? <Sí / No> No-> MF12
   private String tieneAgua;
   //MF9. Hay código LP < Sí / No>    Sí -> Anotar código LP
   private String hayCodigoLP;
   //MF9.2 Hay código LP < Sí / No>    Sí -> Anotar código LP
    private String codigoLP;
   //MF12 ¿La macetera tiene Plato? < Sí / No>
   private String tienePlato;


    @Id
    @Column(name = "codigo_MaceterFloreros", nullable = false, length = 36)
    public String getCodigoMaceterFloreros() {    return codigoMaceterFloreros; }
    public void setCodigoMaceterFloreros(String codigoMaceterFloreros) { this.codigoMaceterFloreros = codigoMaceterFloreros;
    }

    @ManyToOne
    @JoinColumn(name = "codigo_encuesta", nullable = false)
    @ForeignKey(name = "FK_DDBARRIL_CODIGO_ENCUESTA")
    public Tabla_Doble_Digitacion getTabla_Doble_Digitacion() {
        return tabla_Doble_Digitacion;
    }
    public void setTabla_Doble_Digitacion(Tabla_Doble_Digitacion tabla_Doble_Digitacion) {
        this.tabla_Doble_Digitacion = tabla_Doble_Digitacion;
    }

    @Column(name = "ubicacion_recipiente", nullable = false, length = 2)
    public String getUbicacionRecipiente() { return ubicacionRecipiente;}
    public void setUbicacionRecipiente(String ubicacionRecipiente) {this.ubicacionRecipiente = ubicacionRecipiente;}

    @Column(name = "tiene_agua", nullable = false, length = 2)
    public String getTieneAgua() { return tieneAgua;}
    public void setTieneAgua(String tieneAgua) {this.tieneAgua = tieneAgua;}

    @Column(name = "hayCodigoLP", nullable = false, length = 2)
    public String getHayCodigoLP() { return hayCodigoLP;}
    public void setHayCodigoLP(String hayCodigoLP) {this.hayCodigoLP = hayCodigoLP;}

    @Column(name = "codigoLP", nullable = false, length = 4)
    public String getCodigoLP() { return codigoLP;}
    public void setCodigoLP(String codigoLP) {this.codigoLP = codigoLP;}

    @Column(name = "tienePlato", nullable = false, length = 4)
    public String getTienePlato() { return tienePlato;}
    public void setTienePlato(String tienePlato) {this.tienePlato = tienePlato;}


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoMaceterFloreros='" + codigoMaceterFloreros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_MaceteraFlorero)) return false;

        Ento_Doble_Digitacion_MaceteraFlorero that = (Ento_Doble_Digitacion_MaceteraFlorero) o;

        if (!codigoMaceterFloreros.equals(that.codigoMaceterFloreros)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoMaceterFloreros.hashCode();
    }
}
