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
 *//*
@Entity
@Table(name = "ento_doble_digitacion_OtrosDepositosAgua", catalog = "a2cares")
public class Ento_Doble_Digitacion_OtrosDepositosAgua extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;

    private String codigoOtrosDepositosAgua;
    //X1 Nombre del recipiente <Anotar nombre del recipiente>
    private String nombreRecipiente;
   //X1.1 Ubicación del recipiente <Fuera / Dentro >
    private String ubicacionRecipiente;
   //X3 ¿El recipiente tiene uso?   < Sí / No>
    private String tieneUso;
   //X9. Hay código LP < Sí / No>    Sí -> Anotar código LP
    private String hayCodigoLP;

    private String codigoLP;

    @Id
    @Column(name = "codigo_OtrosDepositosAgua", nullable = false, length = 36)
    public String getCodigoOtrosDepositosAgua() {    return codigoOtrosDepositosAgua; }
    public void setCodigoOtrosDepositosAgua(String codigoOtrosDepositosAgua) { this.codigoOtrosDepositosAgua = codigoOtrosDepositosAgua;
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

    @Column(name = "nombre_Recipiente", nullable = false, length = 50)
    public String getNombreRecipiente() { return nombreRecipiente;}
    public void setNombreRecipiente(String nombreRecipiente) {this.nombreRecipiente = nombreRecipiente;}

    @Column(name = "ubicacion_recipiente", nullable = false, length = 2)
    public String getUbicacionRecipiente() { return ubicacionRecipiente;}
    public void setUbicacionRecipiente(String ubicacionRecipiente) {this.ubicacionRecipiente = ubicacionRecipiente;}



    @Column(name = "tiene_Uso", nullable = false, length = 2)
    public String getTieneUso() { return tieneUso;}
    public void setTieneUso(String tieneUso) {this.tieneUso = tieneUso;}

    @Column(name = "hayCodigoLP", nullable = false, length = 2)
    public String getHayCodigoLP() { return hayCodigoLP;}
    public void setHayCodigoLP(String hayCodigoLP) {this.hayCodigoLP = hayCodigoLP;}

    @Column(name = "codigoLP", nullable = false, length = 4)
    public String getCodigoLP() { return codigoLP;}
    public void setCodigoLP(String codigoLP) {this.codigoLP = codigoLP;}


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoOtrosDepositosAgua='" + codigoOtrosDepositosAgua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_OtrosDepositosAgua)) return false;

        Ento_Doble_Digitacion_OtrosDepositosAgua that = (Ento_Doble_Digitacion_OtrosDepositosAgua) o;

        if (!codigoOtrosDepositosAgua.equals(that.codigoOtrosDepositosAgua)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoOtrosDepositosAgua.hashCode();
    }
}*/
