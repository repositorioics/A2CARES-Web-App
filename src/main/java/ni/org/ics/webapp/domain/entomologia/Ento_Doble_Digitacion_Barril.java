package ni.org.ics.webapp.domain.entomologia;

import ni.org.ics.webapp.domain.BaseMetaData;
//import ni.org.ics.webapp.domain.entomologia.Tabla_Doble_Digitacion;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

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
@Table(name = "ento_doble_digitacion_barril", catalog = "a2cares")
public class Ento_Doble_Digitacion_Barril extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;
    //BARRIL (B)
    private String codigoBarril;
   //B1. Ubicación del recipiente <Fuera / Dentro>
    private String ubicacionRecipiente;
   //B1.1 ¿Tiene agua? <Sí / No> No-> Pasar al próximo recipiente
   private String tieneAgua;
   // B2. ¿De dónde sacan el agua para llenar este barril? <llave o tubería / otro -> especificar >
   private String deDondeSacanAgua;
   // B3. ¿Para qué usan el agua de esté barril?  <Tomar/ Lavar / bañarse /regar / lavar inodoro /otro -> especificar>
   private String paraQueUsanAguaBarril;
   //B4. ¿Cuándo fue la última vez que cepillaron las paredes de éste barril? <Entre 1 y 7 días / 8 días o más >
   private String ultimaVezCepillaronBarril;
   //B4.1. ¿Y qué productos utilizaron cuando cepillaron éste barril? <Cepillo/paste/escoba/cloro/detergente/otro ->especificar>
   private String queProdutosCepillaronBarril;
   //B5. Material del barril: < Plástico / Metal /Concreto >
   private String materialBarril;
   //B5.1 Color de la pared interna del barril <Azul/negro/blanco/rojo/verde/gris_concreto/café_sarroso/otro->
   private String colorParedInternaBarril;
   //B6. Tapa: < Bien tapado / Medio / Destapado>
   private String tapa;
   //B7. Presencia de lama en las paredes: < Sí / No>
   private String presenciaDeLama;
   //B9. Hay código LP < Sí / No>    Sí -> Anotar código LP
   private String hayCodigoLP;
   //Sí ->Anotar código LP
    private String codigoLP;
   //B11. Observar y anotar Aplicación de BTI <Sí/No>
   private String aplicacionBTI;


    @Id
    @Column(name = "codigo_barril", nullable = false, length = 36)
    public String getCodigoBarril() {    return codigoBarril; }
    public void setCodigoBarril(String codigoBarril) { this.codigoBarril = codigoBarril;
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

    @Column(name = "de_Donde_Sacan_Agua", nullable = false, length = 2)
    public String getDeDondeSacanAgua() { return deDondeSacanAgua;}
    public void setDeDondeSacanAgua(String deDondeSacanAgua) {this.deDondeSacanAgua = deDondeSacanAgua;}

    @Column(name = "para_Que_Usan_AguaBarril", nullable = false, length = 2)
    public String getParaQueUsanAguaBarril() { return paraQueUsanAguaBarril;}
    public void setParaQueUsanAguaBarril(String paraQueUsanAguaBarril) {this.paraQueUsanAguaBarril = paraQueUsanAguaBarril;}

    @Column(name = "ultima_Vez_CepillaronBarril", nullable = false, length = 2)
    public String getUltimaVezCepillaronBarril() { return ultimaVezCepillaronBarril;}
    public void setUltimaVezCepillaronBarril(String ultimaVezCepillaronBarril) {this.ultimaVezCepillaronBarril = ultimaVezCepillaronBarril;}


    @Column(name = "queProdutos_CepillaronBarril", nullable = false, length = 2)
    public String getQueProdutosCepillaronBarril() { return queProdutosCepillaronBarril;}
    public void setQueProdutosCepillaronBarril(String queProdutosCepillaronBarril) {this.queProdutosCepillaronBarril = queProdutosCepillaronBarril;}


    @Column(name = "material_Barril", nullable = false, length = 2)
    public String getMaterialBarril() { return materialBarril;}
    public void setMaterialBarril(String materialBarril) {this.materialBarril = materialBarril;}

    @Column(name = "color_ParedInterna_Barril", nullable = false, length = 2)
    public String getColorParedInternaBarril() { return colorParedInternaBarril;}
    public void setColorParedInternaBarril(String colorParedInternaBarril) {this.colorParedInternaBarril = colorParedInternaBarril;}

    @Column(name = "tapa", nullable = false, length = 2)
    public String getTapa() { return tapa;}
    public void setTapa(String tapa) {this.tapa = tapa;}

    @Column(name = "presencia_DeLama", nullable = false, length = 2)
    public String getPresenciaDeLama() { return presenciaDeLama;}
    public void setPresenciaDeLama(String presenciaDeLama) {this.presenciaDeLama = presenciaDeLama;}

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
        return "codigoBarril='" + codigoBarril;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_Barril)) return false;

        Ento_Doble_Digitacion_Barril that = (Ento_Doble_Digitacion_Barril) o;

        if (!codigoBarril.equals(that.codigoBarril)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoBarril.hashCode();
    }
}*/
