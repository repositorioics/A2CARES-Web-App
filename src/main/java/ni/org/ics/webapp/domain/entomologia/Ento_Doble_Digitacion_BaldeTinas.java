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
@Table(name = "ento_doble_digitacion_BaldeTinas", catalog = "a2cares")
public class Ento_Doble_Digitacion_BaldeTinas extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;

    private String codigoBaldeTinas;
   //BT1. Ubicación del recipiente <Fuera / Dentro >
    private String ubicacionRecipiente;
   //BT2. ¿De dónde sacan el agua para llenar este…(recipiente)? <llave o tubería / otro -> especificar >
    private String deDondeSacanAgua;
    //BT3. ¿Para qué usan el agua de esté… (recipiente)? <Tomar/ Lavar / bañarse /regar / lavar inodoro /otro ->
    private String paraQueUsanAguaBaldeTinas;
   //BT9. Hay código LP < Sí / No>    Sí -> Anotar código LP
    private String hayCodigoLP;

    private String codigoLP;
   //BT6. Tapa: < Bien tapado / Medio / Destapado>
    private String tapa;
   //BT11. Observar y anotar Aplicación de BTI <Sí/No>
    private String aplicacionBTI;


    @Id
    @Column(name = "codigo_BaldeTinas", nullable = false, length = 36)
    public String getCodigoBaldeTinas() {    return codigoBaldeTinas; }
    public void setCodigoBaldeTinas(String codigoBaldeTinas) { this.codigoBaldeTinas = codigoBaldeTinas;
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

    @Column(name = "de_Donde_Sacan_Agua", nullable = false, length = 2)
    public String getDeDondeSacanAgua() { return deDondeSacanAgua;}
    public void setDeDondeSacanAgua(String deDondeSacanAgua) {this.deDondeSacanAgua = deDondeSacanAgua;}

    @Column(name = "para_Que_Usan_AguaBaldeTinas", nullable = false, length = 2)
    public String getParaQueUsanAguaBaldeTinas() { return paraQueUsanAguaBaldeTinas;}
    public void setParaQueUsanAguaBaldeTinas(String paraQueUsanAguaBaldeTinas) {this.paraQueUsanAguaBaldeTinas = paraQueUsanAguaBaldeTinas;}

    @Column(name = "tapa", nullable = false, length = 2)
    public String getTapa() { return tapa;}
    public void setTapa(String tapa) {this.tapa = tapa;}

    @Column(name = "hayCodigoLP", nullable = false, length = 2)
    public String getHayCodigoLP() { return hayCodigoLP;}
    public void setHayCodigoLP(String hayCodigoLP) {this.hayCodigoLP = hayCodigoLP;}

    @Column(name = "codigoLP", nullable = false, length = 4)
    public String getCodigoLP() { return codigoLP;}
    public void setCodigoLP(String codigoLP) {this.codigoLP = codigoLP;}

    @Column(name = "aplicacionBTI", nullable = false, length = 4)
    public String getAplicacionBTI() { return aplicacionBTI;}
    public void setAplicacionBTI(String aplicacionBTI) {this.aplicacionBTI = aplicacionBTI;}


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoBaldeTinas='" + codigoBaldeTinas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_BaldeTinas)) return false;

        Ento_Doble_Digitacion_BaldeTinas that = (Ento_Doble_Digitacion_BaldeTinas) o;

        if (!codigoBaldeTinas.equals(that.codigoBaldeTinas)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoBaldeTinas.hashCode();
    }
}*/
