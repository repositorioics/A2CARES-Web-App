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
@Table(name = "ento_doble_digitacion_pilaLavadero", catalog = "a2cares")
public class Ento_Doble_Digitacion_PilaLavadero extends BaseMetaData implements Auditable {

    private Tabla_Doble_Digitacion tabla_Doble_Digitacion;
   //PILA DE LAVADERO (P)
    private String codigoPilaLavadero;
   //PG1. Ubicación del recipiente <Fuera / Dentro>
    private String ubicacionRecipiente;
   //B1.1 ¿Tiene agua? <Sí / No> No-> Pasar al próximo recipiente
   private String tieneAgua;
   // B2. ¿De dónde sacan el agua para llenar este PilaLavadero? <llave o tubería / otro -> especificar >
   private String deDondeSacanAgua;
   // B3. ¿Para qué usan el agua de esté PilaLavadero?  <Tomar/ Lavar / bañarse /regar / lavar inodoro /otro -> especificar>
   private String paraQueUsanAguaPilaLavadero;
   //B4. ¿Cuándo fue la última vez que cepillaron las paredes de éste PilaGrande? <Entre 1 y 7 días / 8 días o más >
   private String ultimaVezCepillaronPilaLavadero;
   //B4.1. ¿Y qué productos utilizaron cuando cepillaron éste PilaLavadero? <Cepillo/paste/escoba/cloro/detergente/otro ->especificar>
   private String queProdutosCepillaronLavadero;
   //B5. Material del PilaLavadero: < Plástico / Metal /Concreto >
   private String materialLavadero;
   //B5.1 Color de la pared interna del PilaLavadero <Azul/negro/blanco/rojo/verde/gris_concreto/café_sarroso/otro->
   private String colorParedInternaLavadero;
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
    @Column(name = "codigo_PilaLavadero", nullable = false, length = 36)
    public String getCodigoPilaLavadero() {    return codigoPilaLavadero; }
    public void setCodigoPilaLavadero(String codigoPilaLavadero) { this.codigoPilaLavadero = codigoPilaLavadero;
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

    @Column(name = "para_Que_Usan_AguaPilaLavadero", nullable = false, length = 2)
    public String getParaQueUsanAguaPilaLavadero() { return paraQueUsanAguaPilaLavadero;}
    public void setParaQueUsanAguaPilaLavadero(String paraQueUsanAguaPilaLavadero) {this.paraQueUsanAguaPilaLavadero = paraQueUsanAguaPilaLavadero;}

    @Column(name = "ultima_Vez_CepillaronPilaLavadero", nullable = false, length = 2)
    public String getUltimaVezCepillaronPilaLavadero() { return ultimaVezCepillaronPilaLavadero;}
    public void setUltimaVezCepillaronPilaLavadero(String ultimaVezCepillaronPilaLavadero) {this.ultimaVezCepillaronPilaLavadero = ultimaVezCepillaronPilaLavadero;}


    @Column(name = "queProdutos_CepillaronLavadero", nullable = false, length = 2)
    public String getQueProdutosCepillaronLavadero() { return queProdutosCepillaronLavadero;}
    public void setQueProdutosCepillaronLavadero(String queProdutosCepillaronLavadero) {this.queProdutosCepillaronLavadero = queProdutosCepillaronLavadero;}


    @Column(name = "material_PilaLavadero", nullable = false, length = 2)
    public String getMaterialLavadero() { return materialLavadero;}
    public void setMaterialLavadero(String materialLavadero) {this.materialLavadero = materialLavadero;}

    @Column(name = "color_ParedInterna_PilaLavadero", nullable = false, length = 2)
    public String getColorParedInternaLavadero() { return colorParedInternaLavadero;}
    public void setColorParedInternaLavadero(String colorParedInternaLavadero) {this.colorParedInternaLavadero = colorParedInternaLavadero;}

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
        return "codigoPilaLavadero='" + codigoPilaLavadero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ento_Doble_Digitacion_PilaLavadero)) return false;

        Ento_Doble_Digitacion_PilaLavadero that = (Ento_Doble_Digitacion_PilaLavadero) o;

        if (!codigoPilaLavadero.equals(that.codigoPilaLavadero)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoPilaLavadero.hashCode();
    }
}
