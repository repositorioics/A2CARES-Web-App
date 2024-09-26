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
@Table(name = "ento_doble_digitacion_PichelPanasBotella", catalog = "a2cares")
public class Ento_Doble_Digitacion_PichelPanasBotella extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;

    private String codigoPichelPanasBotella;
   //PP1. Ubicación del recipiente <Fuera / Dentro >
    private String ubicacionRecipiente;
   //PP2. ¿De dónde sacan el agua para llenar este… (recipiente) <llave o tubería / otro -> especificar >
    private String deDondeSacanAgua;
    //PP3. ¿Para qué usan el agua de esté… (recipiente)? <Tomar/ Lavar / bañarse /regar / lavar inodoro /otro ->
    private String paraQueUsanAguaPichelPanasBotella;
   //BT9. Hay código LP < Sí / No>    Sí -> Anotar código LP
    private String hayCodigoLP;

    private String codigoLP;

    @Id
    @Column(name = "codigo_PichelPanasBotella", nullable = false, length = 36)
    public String getCodigoPichelPanasBotella() {    return codigoPichelPanasBotella; }
    public void setCodigoPichelPanasBotella(String codigoPichelPanasBotella) { this.codigoPichelPanasBotella = codigoPichelPanasBotella;
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

    @Column(name = "para_Que_Usan_PichelPanasBotella", nullable = false, length = 2)
    public String getParaQueUsanAguaPichelPanasBotella() { return paraQueUsanAguaPichelPanasBotella;}
    public void setParaQueUsanAguaPichelPanasBotella(String paraQueUsanAguaPichelPanasBotella) {this.paraQueUsanAguaPichelPanasBotella = paraQueUsanAguaPichelPanasBotella;}

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
        return "codigoPichelPanasBotella='" + codigoPichelPanasBotella;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_PichelPanasBotella)) return false;

        Ento_Doble_Digitacion_PichelPanasBotella that = (Ento_Doble_Digitacion_PichelPanasBotella) o;

        if (!codigoPichelPanasBotella.equals(that.codigoPichelPanasBotella)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoPichelPanasBotella.hashCode();
    }
}*/
