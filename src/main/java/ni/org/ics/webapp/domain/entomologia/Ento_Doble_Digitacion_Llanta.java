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
@Table(name = "ento_doble_digitacion_llanta", catalog = "a2cares")
public class Ento_Doble_Digitacion_Llanta extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;

   private String codigoLlanta;
   //LL1 Ubicación del recipiente <Fuera / Dentro >
    private String ubicacionRecipiente;
   //LL1.1 ¿Tiene agua?      < Sí / No> No -> Pasar al próximo recipiente
   private String tieneAgua;
   //LL9. Hay código LP < Sí / No>    Sí -> Anotar código LP
   private String hayCodigoLP;

    private String codigoLP;
   //LL6. ¿Está bajo techo? (Protegida) < Sí / No>
   private String bajoTecho;
   //LL11. Observar y anotar presencia de abate. < Sí / No>
   private String presenciaAbate;


    @Id
    @Column(name = "codigo_Llanta", nullable = false, length = 36)
    public String getCodigoLlanta() {    return codigoLlanta; }
    public void setCodigoLlanta(String codigoLlanta) { this.codigoLlanta = codigoLlanta;
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

    @Column(name = "presenciaAbate", nullable = false, length = 4)
    public String getPresenciaAbate() { return presenciaAbate;}
    public void setPresenciaAbate(String presenciaAbate) {this.presenciaAbate = presenciaAbate;}


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoLlanta='" + codigoLlanta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_Llanta)) return false;

        Ento_Doble_Digitacion_Llanta that = (Ento_Doble_Digitacion_Llanta) o;

        if (!codigoLlanta.equals(that.codigoLlanta)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoLlanta.hashCode();
    }
}
