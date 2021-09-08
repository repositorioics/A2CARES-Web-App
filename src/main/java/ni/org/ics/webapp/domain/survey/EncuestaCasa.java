package ni.org.ics.webapp.domain.survey;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.core.Casa;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 14/6/2021
 * V1.0
 */
@Entity
@Table(name = "encuestas_casas", catalog = "a2cares")
public class EncuestaCasa extends BaseMetaData implements Auditable {

    private String codigo;
    private Casa casa;
    private Integer participante; //participantes encuestado
    private int cuantasPersonas; //1
    private int cuantasMujeres; //2
    private String edadMujer1;
    private String edadMujer2;
    private String edadMujer3;
    private String edadMujer4;
    private String edadMujer5;
    private String edadMujer6;
    private String edadMujer7;
    private String edadMujer8;
    private String edadMujer9;
    private String edadMujer10;
    private int cuantosHombres; //3
    private String edadHombre1;
    private String edadHombre2;
    private String edadHombre3;
    private String edadHombre4;
    private String edadHombre5;
    private String edadHombre6;
    private String edadHombre7;
    private String edadHombre8;
    private String edadHombre9;
    private String edadHombre10;
    private int cantidadCuartos;
    private int cantidadCuartosDormir;
    private String problemaAgua; //6
    private int hrsSinServicioAgua;
    private int frecuenciaSeVaAgua;
    private String tienePozo; //7
    private String tieneMedidorAgua; //8
    private String tieneTanque; //10
    private String ubicacionLlaveAgua;
    private String llaveCompartida;
    private String almacenaAgua;
    private String almacenaEnBarriles;
    private String almacenaEnTanques;
    private String almacenaEnPilas;
    private String almacenaOtrosRecipientes;
    private String otrosRecipientes;
    private Integer numBarriles;
    private Integer numTanques;
    private Integer numPilas;
    private Integer numOtrosRecipientes;
    private String barrilesTapados;
    private String tanquesTapados;
    private String pilasTapadas;
    private String otrosRecipientesTapados;
    private String cambiadoCasa; //13
    private String remodeladoCasa; //14
    private String ubicacionLavandero;
    private String materialParedes;
    private String materialPiso;
    private String materialTecho;
    private String otroMaterialParedes;
    private String otroMaterialPiso;
    private String otroMaterialTecho;
    private String casaPropia;
    private String tieneAbanico;
    private String tieneTelevisor;
    private String tieneRefrigerador;
    private String tienAireAcondicionado;
    private Integer numAbanicos;
    private Integer numTelevisores;
    private Integer numRefrigeradores;
    private Integer numAireAcondicionado;
    private String aireAcondicionadoFuncionando;
    private String lavadoraFuncionando; //20.5.1
    private String tieneMuro; //21
    private String tieneInternet; //22
    private String tieneInodoro; //23
    private String tieneServicioEnergia; //24
    private String tieneMedidorEnergia; //24.a
    private String casaDosPisos; //25
    private String tieneOtrosServicios; //26
    private String tieneVehiculo; //27
    private String tieneMoto;
    private String tieneCarro;
    private String tieneMicrobus;
    private String tieneCamioneta;
    private String tieneCamion;
    private String tieneOtroMedioTransAuto;
    private String otroMedioTransAuto;
    private Integer anioFabMoto; //28.2
    private Integer anioFabCarro; //28.2
    private Integer anioFabMicrobus; //28.2
    private Integer anioFabCamioneta; //28.2
    private Integer anioFabCamion; //28.2
    private Integer anioFabOtroMedioTrans; //28.2
    private String marcaMoto; //28.3
    private String marcaCarro; //28.3
    private String marcaMicrobus; //28.3
    private String marcaCamioneta; //28.3
    private String marcaCamion; //28.3
    private String marcaOtroMedioTrans; //28.3
    private String tipoCocina; //29
    private Integer cuantosQuemadores; //29.a
    private String tieneHorno; //30
    private String cocinaConLenia;
    private String ubicacionCocinaLenia;
    private String periodicidadCocinaLenia;
    private Integer numDiarioCocinaLenia;   //# de veces que cocina
    private Integer numSemanalCocinaLenia;  //# de veces que cocina semanalmente
    private Integer numQuincenalCocinaLenia;    //# de veces que cocina quincenalmente
    private Integer numMensualCocinaLenia;  //# de veces que cocina al mes
    private String tieneAnimales;
    private String tieneGallinas; //33
    private String tienePatos; //33
    private String tienePerros; //33
    private String tieneGatos; //33
    private String tieneCerdos; //33
    private String tieneVacas; //33
    private String tieneCabras; //33
    private String tieneCaballos; //33
    private String tienePelibueys; //33
    private String tieneAves; //33
    private String tieneOtrosAnimales; //33
    private Integer cantidadGallinas; //33.1
    private Integer cantidadPatos;
    private Integer cantidadPerros;
    private Integer cantidadGatos;
    private Integer cantidadCerdos;
    private Integer cantidadVacas;
    private Integer cantidadCabras;
    private Integer cantidadCaballos;
    private Integer cantidadPelibueys;
    private Integer cantidadAves;
    private Integer cantidadOtrosAnimales; //33.1
    private String gallinasDentroCasa;//34
    private String patosDentroCasa;
    private String perrosDentroCasa;
    private String gatosDentroCasa;
    private String cerdosDentroCasa;
    private String vacasDentroCasa;
    private String cabrasDentroCasa;
    private String caballosDentroCasa;
    private String pelibueysDentroCasa;
    private String avesDentroCasa;
    private String otrosAnimalesDentroCasa; //34
    private String personaFumaDentroCasa; //35
    private String tieneRecoleccionBasura; //36
    private Integer cuantasVecesRecBasura; //37
    private String dondePasaRecBasura; //38

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_ENCUESTACASA")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @Column(name = "CODIGO_PARTICIPANTE")
    public Integer getParticipante() {
        return participante;
    }

    public void setParticipante(Integer participante) {
        this.participante = participante;
    }

    @Column(name = "CANTIDAD_PERSONAS")
    public int getCuantasPersonas() {
        return cuantasPersonas;
    }

    public void setCuantasPersonas(int cuantasPersonas) {
        this.cuantasPersonas = cuantasPersonas;
    }

    @Column(name = "CANTIDAD_FEMENINAS")
    public int getCuantasMujeres() {
        return cuantasMujeres;
    }

    public void setCuantasMujeres(int cuantasMujeres) {
        this.cuantasMujeres = cuantasMujeres;
    }

    @Column(name = "EDAD_F1", length = 10)
    public String getEdadMujer1() {
        return edadMujer1;
    }

    public void setEdadMujer1(String edadMujer1) {
        this.edadMujer1 = edadMujer1;
    }

    @Column(name = "EDAD_F2", length = 10)
    public String getEdadMujer2() {
        return edadMujer2;
    }

    public void setEdadMujer2(String edadMujer2) {
        this.edadMujer2 = edadMujer2;
    }

    @Column(name = "EDAD_F3", length = 10)
    public String getEdadMujer3() {
        return edadMujer3;
    }

    public void setEdadMujer3(String edadMujer3) {
        this.edadMujer3 = edadMujer3;
    }

    @Column(name = "EDAD_F4", length = 10)
    public String getEdadMujer4() {
        return edadMujer4;
    }

    public void setEdadMujer4(String edadMujer4) {
        this.edadMujer4 = edadMujer4;
    }

    @Column(name = "EDAD_F5", length = 10)
    public String getEdadMujer5() {
        return edadMujer5;
    }

    public void setEdadMujer5(String edadMujer5) {
        this.edadMujer5 = edadMujer5;
    }

    @Column(name = "EDAD_F6", length = 10)
    public String getEdadMujer6() {
        return edadMujer6;
    }

    public void setEdadMujer6(String edadMujer6) {
        this.edadMujer6 = edadMujer6;
    }

    @Column(name = "EDAD_F7", length = 10)
    public String getEdadMujer7() {
        return edadMujer7;
    }

    public void setEdadMujer7(String edadMujer7) {
        this.edadMujer7 = edadMujer7;
    }

    @Column(name = "EDAD_F8", length = 10)
    public String getEdadMujer8() {
        return edadMujer8;
    }

    public void setEdadMujer8(String edadMujer8) {
        this.edadMujer8 = edadMujer8;
    }

    @Column(name = "EDAD_F9", length = 10)
    public String getEdadMujer9() {
        return edadMujer9;
    }

    public void setEdadMujer9(String edadMujer9) {
        this.edadMujer9 = edadMujer9;
    }

    @Column(name = "EDAD_F10", length = 10)
    public String getEdadMujer10() {
        return edadMujer10;
    }

    public void setEdadMujer10(String edadMujer10) {
        this.edadMujer10 = edadMujer10;
    }

    @Column(name = "CANTIDAD_MASCULINAS")
    public int getCuantosHombres() {
        return cuantosHombres;
    }

    public void setCuantosHombres(int cuantosHombres) {
        this.cuantosHombres = cuantosHombres;
    }

    @Column(name = "EDAD_M1", length = 10)
    public String getEdadHombre1() {
        return edadHombre1;
    }

    public void setEdadHombre1(String edadHombre1) {
        this.edadHombre1 = edadHombre1;
    }

    @Column(name = "EDAD_M2", length = 10)
    public String getEdadHombre2() {
        return edadHombre2;
    }

    public void setEdadHombre2(String edadHombre2) {
        this.edadHombre2 = edadHombre2;
    }

    @Column(name = "EDAD_M3", length = 10)
    public String getEdadHombre3() {
        return edadHombre3;
    }

    public void setEdadHombre3(String edadHombre3) {
        this.edadHombre3 = edadHombre3;
    }

    @Column(name = "EDAD_M4", length = 10)
    public String getEdadHombre4() {
        return edadHombre4;
    }

    public void setEdadHombre4(String edadHombre4) {
        this.edadHombre4 = edadHombre4;
    }

    @Column(name = "EDAD_M5", length = 10)
    public String getEdadHombre5() {
        return edadHombre5;
    }

    public void setEdadHombre5(String edadHombre5) {
        this.edadHombre5 = edadHombre5;
    }

    @Column(name = "EDAD_M6", length = 10)
    public String getEdadHombre6() {
        return edadHombre6;
    }

    public void setEdadHombre6(String edadHombre6) {
        this.edadHombre6 = edadHombre6;
    }

    @Column(name = "EDAD_M7", length = 10)
    public String getEdadHombre7() {
        return edadHombre7;
    }

    public void setEdadHombre7(String edadHombre7) {
        this.edadHombre7 = edadHombre7;
    }

    @Column(name = "EDAD_M8", length = 10)
    public String getEdadHombre8() {
        return edadHombre8;
    }

    public void setEdadHombre8(String edadHombre8) {
        this.edadHombre8 = edadHombre8;
    }

    @Column(name = "EDAD_M9", length = 10)
    public String getEdadHombre9() {
        return edadHombre9;
    }

    public void setEdadHombre9(String edadHombre9) {
        this.edadHombre9 = edadHombre9;
    }

    @Column(name = "EDAD_M10", length = 10)
    public String getEdadHombre10() {
        return edadHombre10;
    }

    public void setEdadHombre10(String edadHombre10) {
        this.edadHombre10 = edadHombre10;
    }

    @Column(name = "CANTIDAD_CUARTOS")
    public int getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(int cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    @Column(name = "CANT_CUARTOS_DORMIR")
    public int getCantidadCuartosDormir() {
        return cantidadCuartosDormir;
    }

    public void setCantidadCuartosDormir(int cantidadCuartosDormir) {
        this.cantidadCuartosDormir = cantidadCuartosDormir;
    }

    @Column(name = "PROBLEMAS_AGUA", length = 1)
    public String getProblemaAgua() {
        return problemaAgua;
    }

    public void setProblemaAgua(String problemaAgua) {
        this.problemaAgua = problemaAgua;
    }

    @Column(name = "HRS_SIN_SRV_AGUA")
    public int getHrsSinServicioAgua() {
        return hrsSinServicioAgua;
    }

    public void setHrsSinServicioAgua(int hrsSinServicioAgua) {
        this.hrsSinServicioAgua = hrsSinServicioAgua;
    }

    @Column(name = "FREC_DIAS_VA_AGUA")
    public int getFrecuenciaSeVaAgua() {
        return frecuenciaSeVaAgua;
    }

    public void setFrecuenciaSeVaAgua(int frecuenciaSeVaAgua) {
        this.frecuenciaSeVaAgua = frecuenciaSeVaAgua;
    }

    @Column(name = "TIENE_POZO", length = 1)
    public String getTienePozo() {
        return tienePozo;
    }

    public void setTienePozo(String tienePozo) {
        this.tienePozo = tienePozo;
    }

    @Column(name = "TIENE_MEDIDOR_AGUA", length = 1)
    public String getTieneMedidorAgua() {
        return tieneMedidorAgua;
    }

    public void setTieneMedidorAgua(String tieneMedidorAgua) {
        this.tieneMedidorAgua = tieneMedidorAgua;
    }

    @Column(name = "TIENE_TANQUE", length = 1)
    public String getTieneTanque() {
        return tieneTanque;
    }

    public void setTieneTanque(String tieneTanque) {
        this.tieneTanque = tieneTanque;
    }

    @Column(name = "UBICACION_LLAVE_AGUA", length = 1)
    public String getUbicacionLlaveAgua() {
        return ubicacionLlaveAgua;
    }

    public void setUbicacionLlaveAgua(String ubicacionLlaveAgua) {
        this.ubicacionLlaveAgua = ubicacionLlaveAgua;
    }

    @Column(name = "LLAVEAGUA_COMPARTIDA", length = 1)
    public String getLlaveCompartida() {
        return llaveCompartida;
    }

    public void setLlaveCompartida(String llaveCompartida) {
        this.llaveCompartida = llaveCompartida;
    }

    @Column(name = "ALMACENA_AGUA", length = 1)
    public String getAlmacenaAgua() {
        return almacenaAgua;
    }

    public void setAlmacenaAgua(String almacenaAgua) {
        this.almacenaAgua = almacenaAgua;
    }

    @Column(name = "ALMACENA_EN_BARRILES", length = 1)
    public String getAlmacenaEnBarriles() {
        return almacenaEnBarriles;
    }

    public void setAlmacenaEnBarriles(String almacenaEnBarriles) {
        this.almacenaEnBarriles = almacenaEnBarriles;
    }

    @Column(name = "ALMACENA_EN_TANQUES", length = 1)
    public String getAlmacenaEnTanques() {
        return almacenaEnTanques;
    }

    public void setAlmacenaEnTanques(String almacenaEnTanques) {
        this.almacenaEnTanques = almacenaEnTanques;
    }

    @Column(name = "ALMACENA_EN_PILAS", length = 1)
    public String getAlmacenaEnPilas() {
        return almacenaEnPilas;
    }

    public void setAlmacenaEnPilas(String almacenaEnPilas) {
        this.almacenaEnPilas = almacenaEnPilas;
    }

    @Column(name = "ALMACENA_EN_OTROSRECIP", length = 1)
    public String getAlmacenaOtrosRecipientes() {
        return almacenaOtrosRecipientes;
    }

    public void setAlmacenaOtrosRecipientes(String almacenaOtrosRecipientes) {
        this.almacenaOtrosRecipientes = almacenaOtrosRecipientes;
    }

    @Column(name = "DESC_OTROS_RECIPIENTES")
    public String getOtrosRecipientes() {
        return otrosRecipientes;
    }

    public void setOtrosRecipientes(String otrosRecipientes) {
        this.otrosRecipientes = otrosRecipientes;
    }

    @Column(name = "NUMERO_BARRILES")
    public Integer getNumBarriles() {
        return numBarriles;
    }

    public void setNumBarriles(Integer numBarriles) {
        this.numBarriles = numBarriles;
    }

    @Column(name = "NUMERO_TANQUES")
    public Integer getNumTanques() {
        return numTanques;
    }

    public void setNumTanques(Integer numTanques) {
        this.numTanques = numTanques;
    }

    @Column(name = "NUMERO_PILAS")
    public Integer getNumPilas() {
        return numPilas;
    }

    public void setNumPilas(Integer numPilas) {
        this.numPilas = numPilas;
    }

    @Column(name = "NUMERO_OTROS_RECIPIENTES")
    public Integer getNumOtrosRecipientes() {
        return numOtrosRecipientes;
    }

    public void setNumOtrosRecipientes(Integer numOtrosRecipientes) {
        this.numOtrosRecipientes = numOtrosRecipientes;
    }

    @Column(name = "BARRILES_TAPADOS", length = 1)
    public String getBarrilesTapados() {
        return barrilesTapados;
    }

    public void setBarrilesTapados(String barrilesTapados) {
        this.barrilesTapados = barrilesTapados;
    }

    @Column(name = "TANQUES_TAPADOS", length = 1)
    public String getTanquesTapados() {
        return tanquesTapados;
    }

    public void setTanquesTapados(String tanquesTapados) {
        this.tanquesTapados = tanquesTapados;
    }

    @Column(name = "PILAS_TAPADAS", length = 1)
    public String getPilasTapadas() {
        return pilasTapadas;
    }

    public void setPilasTapadas(String pilasTapadas) {
        this.pilasTapadas = pilasTapadas;
    }

    @Column(name = "OTROS_RECIP_TAPADOS", length = 1)
    public String getOtrosRecipientesTapados() {
        return otrosRecipientesTapados;
    }

    public void setOtrosRecipientesTapados(String otrosRecipientesTapados) {
        this.otrosRecipientesTapados = otrosRecipientesTapados;
    }

    @Column(name = "CAMBIADO_CASA", length = 1)
    public String getCambiadoCasa() {
        return cambiadoCasa;
    }

    public void setCambiadoCasa(String cambiadoCasa) {
        this.cambiadoCasa = cambiadoCasa;
    }

    @Column(name = "REMODELADO_CASA", length = 1)
    public String getRemodeladoCasa() {
        return remodeladoCasa;
    }

    public void setRemodeladoCasa(String remodeladoCasa) {
        this.remodeladoCasa = remodeladoCasa;
    }

    @Column(name = "UBICACION_LAVANDERO", length = 1)
    public String getUbicacionLavandero() {
        return ubicacionLavandero;
    }

    public void setUbicacionLavandero(String ubicacionLavandero) {
        this.ubicacionLavandero = ubicacionLavandero;
    }

    @Column(name = "MATERIAL_PAREDES", length = 20)
    public String getMaterialParedes() {
        return materialParedes;
    }

    public void setMaterialParedes(String materialParedes) {
        this.materialParedes = materialParedes;
    }

    @Column(name = "MATERIAL_PISO", length = 16)
    public String getMaterialPiso() {
        return materialPiso;
    }

    public void setMaterialPiso(String materialPiso) {
        this.materialPiso = materialPiso;
    }

    @Column(name = "MATERIAL_TECHO", length = 3)
    public String getMaterialTecho() {
        return materialTecho;
    }

    public void setMaterialTecho(String materialTecho) {
        this.materialTecho = materialTecho;
    }

    @Column(name = "OTRO_MATERIAL_PAREDES")
    public String getOtroMaterialParedes() {
        return otroMaterialParedes;
    }

    public void setOtroMaterialParedes(String otroMaterialParedes) {
        this.otroMaterialParedes = otroMaterialParedes;
    }

    @Column(name = "OTRO_MATERIAL_PISO")
    public String getOtroMaterialPiso() {
        return otroMaterialPiso;
    }

    public void setOtroMaterialPiso(String otroMaterialPiso) {
        this.otroMaterialPiso = otroMaterialPiso;
    }

    @Column(name = "OTRO_MATERIAL_TECHO")
    public String getOtroMaterialTecho() {
        return otroMaterialTecho;
    }

    public void setOtroMaterialTecho(String otroMaterialTecho) {
        this.otroMaterialTecho = otroMaterialTecho;
    }

    @Column(name = "CASA_PROPIA", length = 1)
    public String getCasaPropia() {
        return casaPropia;
    }

    public void setCasaPropia(String casaPropia) {
        this.casaPropia = casaPropia;
    }

    @Column(name = "TIENE_ABANICO", length = 1)
    public String getTieneAbanico() {
        return tieneAbanico;
    }

    public void setTieneAbanico(String tieneAbanico) {
        this.tieneAbanico = tieneAbanico;
    }

    @Column(name = "TIENE_TELEVISOR", length = 1)
    public String getTieneTelevisor() {
        return tieneTelevisor;
    }

    public void setTieneTelevisor(String tieneTelevisor) {
        this.tieneTelevisor = tieneTelevisor;
    }

    @Column(name = "TIENE_REFRIGERADOR", length = 1)
    public String getTieneRefrigerador() {
        return tieneRefrigerador;
    }

    public void setTieneRefrigerador(String tieneRefrigerador) {
        this.tieneRefrigerador = tieneRefrigerador;
    }

    @Column(name = "TIENE_AIRE_ACONDICIONADO", length = 1)
    public String getTienAireAcondicionado() {
        return tienAireAcondicionado;
    }

    public void setTienAireAcondicionado(String tienAireAcondicionado) {
        this.tienAireAcondicionado = tienAireAcondicionado;
    }

    @Column(name = "CANTIDAD_ABANICOS")
    public Integer getNumAbanicos() {
        return numAbanicos;
    }

    public void setNumAbanicos(Integer numAbanicos) {
        this.numAbanicos = numAbanicos;
    }

    @Column(name = "CANTIDAD_TELEVISORES")
    public Integer getNumTelevisores() {
        return numTelevisores;
    }

    public void setNumTelevisores(Integer numTelevisores) {
        this.numTelevisores = numTelevisores;
    }

    @Column(name = "CANTIDAD_REFRIGERADORES")
    public Integer getNumRefrigeradores() {
        return numRefrigeradores;
    }

    public void setNumRefrigeradores(Integer numRefrigeradores) {
        this.numRefrigeradores = numRefrigeradores;
    }

    @Column(name = "CANTIDAD_AIRES")
    public Integer getNumAireAcondicionado() {
        return numAireAcondicionado;
    }

    public void setNumAireAcondicionado(Integer numAireAcondicionado) {
        this.numAireAcondicionado = numAireAcondicionado;
    }

    @Column(name = "FUNCIONAMIENTO_AIRE", length = 1)
    public String getAireAcondicionadoFuncionando() {
        return aireAcondicionadoFuncionando;
    }

    public void setAireAcondicionadoFuncionando(String aireAcondicionadoFuncionando) {
        this.aireAcondicionadoFuncionando = aireAcondicionadoFuncionando;
    }

    @Column(name = "LAVADORA_FUNCIONANDO", length = 1)
    public String getLavadoraFuncionando() {
        return lavadoraFuncionando;
    }

    public void setLavadoraFuncionando(String lavadoraFuncionando) {
        this.lavadoraFuncionando = lavadoraFuncionando;
    }

    @Column(name = "TIENE_MURO", length = 1)
    public String getTieneMuro() {
        return tieneMuro;
    }

    public void setTieneMuro(String tieneMuro) {
        this.tieneMuro = tieneMuro;
    }

    @Column(name = "TIENE_INTERNET", length = 1)
    public String getTieneInternet() {
        return tieneInternet;
    }

    public void setTieneInternet(String tieneInternet) {
        this.tieneInternet = tieneInternet;
    }

    @Column(name = "TIENE_INODORO", length = 1)
    public String getTieneInodoro() {
        return tieneInodoro;
    }

    public void setTieneInodoro(String tieneInodoro) {
        this.tieneInodoro = tieneInodoro;
    }

    @Column(name = "TIENE_SRV_ENERGIA", length = 1)
    public String getTieneServicioEnergia() {
        return tieneServicioEnergia;
    }

    public void setTieneServicioEnergia(String tieneServicioEnergia) {
        this.tieneServicioEnergia = tieneServicioEnergia;
    }

    @Column(name = "TIENE_MEDIDOR_ENERGIA", length = 1)
    public String getTieneMedidorEnergia() {
        return tieneMedidorEnergia;
    }

    public void setTieneMedidorEnergia(String tieneMedidorEnergia) {
        this.tieneMedidorEnergia = tieneMedidorEnergia;
    }

    @Column(name = "CASA_DOS_PISOS", length = 1)
    public String getCasaDosPisos() {
        return casaDosPisos;
    }

    public void setCasaDosPisos(String casaDosPisos) {
        this.casaDosPisos = casaDosPisos;
    }

    @Column(name = "OTROS_SERVICIOS", length = 16)
    public String getTieneOtrosServicios() {
        return tieneOtrosServicios;
    }

    public void setTieneOtrosServicios(String tieneOtrosServicios) {
        this.tieneOtrosServicios = tieneOtrosServicios;
    }

    @Column(name = "TIENE_VEHICULO", length = 1)
    public String getTieneVehiculo() {
        return tieneVehiculo;
    }

    public void setTieneVehiculo(String tieneVehiculo) {
        this.tieneVehiculo = tieneVehiculo;
    }

    @Column(name = "TIENE_MOTO", length = 1)
    public String getTieneMoto() {
        return tieneMoto;
    }

    public void setTieneMoto(String tieneMoto) {
        this.tieneMoto = tieneMoto;
    }

    @Column(name = "TIENE_CARRO", length = 1)
    public String getTieneCarro() {
        return tieneCarro;
    }

    public void setTieneCarro(String tieneCarro) {
        this.tieneCarro = tieneCarro;
    }

    @Column(name = "TIENE_MICROBUS", length = 1)
    public String getTieneMicrobus() {
        return tieneMicrobus;
    }

    public void setTieneMicrobus(String tieneMicrobus) {
        this.tieneMicrobus = tieneMicrobus;
    }

    @Column(name = "TIENE_CAMIONETA", length = 1)
    public String getTieneCamioneta() {
        return tieneCamioneta;
    }

    public void setTieneCamioneta(String tieneCamioneta) {
        this.tieneCamioneta = tieneCamioneta;
    }

    @Column(name = "TIENE_CAMION", length = 1)
    public String getTieneCamion() {
        return tieneCamion;
    }

    public void setTieneCamion(String tieneCamion) {
        this.tieneCamion = tieneCamion;
    }

    @Column(name = "TIENE_OTRO_MEDIO_TRANS", length = 1)
    public String getTieneOtroMedioTransAuto() {
        return tieneOtroMedioTransAuto;
    }

    public void setTieneOtroMedioTransAuto(String tieneOtroMedioTransAuto) {
        this.tieneOtroMedioTransAuto = tieneOtroMedioTransAuto;
    }

    @Column(name = "DESC_OTRO_MEDIO_TRANS")
    public String getOtroMedioTransAuto() {
        return otroMedioTransAuto;
    }

    public void setOtroMedioTransAuto(String otroMedioTransAuto) {
        this.otroMedioTransAuto = otroMedioTransAuto;
    }

    @Column(name = "ANIO_FAB_MOTO")
    public Integer getAnioFabMoto() {
        return anioFabMoto;
    }

    public void setAnioFabMoto(Integer anioFabMoto) {
        this.anioFabMoto = anioFabMoto;
    }

    @Column(name = "ANIO_FAB_CARRO")
    public Integer getAnioFabCarro() {
        return anioFabCarro;
    }

    public void setAnioFabCarro(Integer anioFabCarro) {
        this.anioFabCarro = anioFabCarro;
    }

    @Column(name = "ANIO_FAB_MICROBUS")
    public Integer getAnioFabMicrobus() {
        return anioFabMicrobus;
    }

    public void setAnioFabMicrobus(Integer anioFabMicrobus) {
        this.anioFabMicrobus = anioFabMicrobus;
    }

    @Column(name = "ANIO_FAB_CAMIONETA")
    public Integer getAnioFabCamioneta() {
        return anioFabCamioneta;
    }

    public void setAnioFabCamioneta(Integer anioFabCamioneta) {
        this.anioFabCamioneta = anioFabCamioneta;
    }

    @Column(name = "ANIO_FAB_CAMION")
    public Integer getAnioFabCamion() {
        return anioFabCamion;
    }

    public void setAnioFabCamion(Integer anioFabCamion) {
        this.anioFabCamion = anioFabCamion;
    }

    @Column(name = "ANIO_FAB_OTRO_MEDIO")
    public Integer getAnioFabOtroMedioTrans() {
        return anioFabOtroMedioTrans;
    }

    public void setAnioFabOtroMedioTrans(Integer anioFabOtroMedioTrans) {
        this.anioFabOtroMedioTrans = anioFabOtroMedioTrans;
    }

    @Column(name = "MARCA_MOTO")
    public String getMarcaMoto() {
        return marcaMoto;
    }

    public void setMarcaMoto(String marcaMoto) {
        this.marcaMoto = marcaMoto;
    }

    @Column(name = "MARCA_CARRO")
    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    @Column(name = "MARCA_MICROBUS")
    public String getMarcaMicrobus() {
        return marcaMicrobus;
    }

    public void setMarcaMicrobus(String marcaMicrobus) {
        this.marcaMicrobus = marcaMicrobus;
    }

    @Column(name = "MARCA_CAMIONETA")
    public String getMarcaCamioneta() {
        return marcaCamioneta;
    }

    public void setMarcaCamioneta(String marcaCamioneta) {
        this.marcaCamioneta = marcaCamioneta;
    }

    @Column(name = "MARCA_CAMION")
    public String getMarcaCamion() {
        return marcaCamion;
    }

    public void setMarcaCamion(String marcaCamion) {
        this.marcaCamion = marcaCamion;
    }

    @Column(name = "MARCA_OTRO_MEDIO")
    public String getMarcaOtroMedioTrans() {
        return marcaOtroMedioTrans;
    }

    public void setMarcaOtroMedioTrans(String marcaOtroMedioTrans) {
        this.marcaOtroMedioTrans = marcaOtroMedioTrans;
    }

    @Column(name = "TIPO_COCINA", length = 1)
    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    @Column(name = "CANT_QUEMADORES")
    public Integer getCuantosQuemadores() {
        return cuantosQuemadores;
    }

    public void setCuantosQuemadores(Integer cuantosQuemadores) {
        this.cuantosQuemadores = cuantosQuemadores;
    }

    @Column(name = "TIENE_HORNO", length = 1)
    public String getTieneHorno() {
        return tieneHorno;
    }

    public void setTieneHorno(String tieneHorno) {
        this.tieneHorno = tieneHorno;
    }

    @Column(name = "COCINA_CON_LENIA", length = 1)
    public String getCocinaConLenia() {
        return cocinaConLenia;
    }

    public void setCocinaConLenia(String cocinaConLenia) {
        this.cocinaConLenia = cocinaConLenia;
    }

    @Column(name = "UBICACION_COCINA_LENIA", length = 50)
    public String getUbicacionCocinaLenia() {
        return ubicacionCocinaLenia;
    }

    public void setUbicacionCocinaLenia(String ubicacionCocinaLenia) {
        this.ubicacionCocinaLenia = ubicacionCocinaLenia;
    }

    @Column(name = "PERIODICIDAD_COCINA_LENIA", length = 50)
    public String getPeriodicidadCocinaLenia() {
        return periodicidadCocinaLenia;
    }

    public void setPeriodicidadCocinaLenia(String periodicidadCocinaLenia) {
        this.periodicidadCocinaLenia = periodicidadCocinaLenia;
    }

    @Column(name = "NUM_DIARIO_COCINA_LENIA", length = 2)
    public Integer getNumDiarioCocinaLenia() {
        return numDiarioCocinaLenia;
    }

    public void setNumDiarioCocinaLenia(Integer numDiarioCocinaLenia) {
        this.numDiarioCocinaLenia = numDiarioCocinaLenia;
    }

    @Column(name = "NUM_SEMANAL_COCINA_LENIA", length = 2)
    public Integer getNumSemanalCocinaLenia() {
        return numSemanalCocinaLenia;
    }

    public void setNumSemanalCocinaLenia(Integer numSemanalCocinaLenia) {
        this.numSemanalCocinaLenia = numSemanalCocinaLenia;
    }

    @Column(name = "NUM_QUINCENAL_COCINA_LENIA", length = 2)
    public Integer getNumQuincenalCocinaLenia() {
        return numQuincenalCocinaLenia;
    }

    public void setNumQuincenalCocinaLenia(Integer numQuincenalCocinaLenia) {
        this.numQuincenalCocinaLenia = numQuincenalCocinaLenia;
    }

    @Column(name = "NUM_MENSUAL_COCINA_LENIA", length = 2)
    public Integer getNumMensualCocinaLenia() {
        return numMensualCocinaLenia;
    }

    public void setNumMensualCocinaLenia(Integer numMensualCocinaLenia) {
        this.numMensualCocinaLenia = numMensualCocinaLenia;
    }

    @Column(name = "TIENE_ANIMALES", length = 1)
    public String getTieneAnimales() {
        return tieneAnimales;
    }

    public void setTieneAnimales(String tieneAnimales) {
        this.tieneAnimales = tieneAnimales;
    }

    @Column(name = "TIENE_GALLINAS", length = 1)
    public String getTieneGallinas() {
        return tieneGallinas;
    }

    public void setTieneGallinas(String tieneGallinas) {
        this.tieneGallinas = tieneGallinas;
    }

    @Column(name = "TIENE_PATOS", length = 1)
    public String getTienePatos() {
        return tienePatos;
    }

    public void setTienePatos(String tienePatos) {
        this.tienePatos = tienePatos;
    }

    @Column(name = "TIENE_PERROS", length = 1)
    public String getTienePerros() {
        return tienePerros;
    }

    public void setTienePerros(String tienePerros) {
        this.tienePerros = tienePerros;
    }

    @Column(name = "TIENE_GATOS", length = 1)
    public String getTieneGatos() {
        return tieneGatos;
    }

    public void setTieneGatos(String tieneGatos) {
        this.tieneGatos = tieneGatos;
    }

    @Column(name = "TIENE_CERDOS", length = 1)
    public String getTieneCerdos() {
        return tieneCerdos;
    }

    public void setTieneCerdos(String tieneCerdos) {
        this.tieneCerdos = tieneCerdos;
    }

    @Column(name = "TIENE_VACAS", length = 1)
    public String getTieneVacas() {
        return tieneVacas;
    }

    public void setTieneVacas(String tieneVacas) {
        this.tieneVacas = tieneVacas;
    }

    @Column(name = "TIENE_CABRAS", length = 1)
    public String getTieneCabras() {
        return tieneCabras;
    }

    public void setTieneCabras(String tieneCabras) {
        this.tieneCabras = tieneCabras;
    }

    @Column(name = "TIENE_CABALLOS", length = 1)
    public String getTieneCaballos() {
        return tieneCaballos;
    }

    public void setTieneCaballos(String tieneCaballos) {
        this.tieneCaballos = tieneCaballos;
    }

    @Column(name = "TIENE_PELIBUEYS", length = 1)
    public String getTienePelibueys() {
        return tienePelibueys;
    }

    public void setTienePelibueys(String tienePelibueys) {
        this.tienePelibueys = tienePelibueys;
    }

    @Column(name = "TIENE_AVES", length = 1)
    public String getTieneAves() {
        return tieneAves;
    }

    public void setTieneAves(String tieneAves) {
        this.tieneAves = tieneAves;
    }

    @Column(name = "TIENE_OTROS_ANIMALES", length = 1)
    public String getTieneOtrosAnimales() {
        return tieneOtrosAnimales;
    }

    public void setTieneOtrosAnimales(String tieneOtrosAnimales) {
        this.tieneOtrosAnimales = tieneOtrosAnimales;
    }

    @Column(name = "CANT_GALLINAS")
    public Integer getCantidadGallinas() {
        return cantidadGallinas;
    }

    public void setCantidadGallinas(Integer cantidadGallinas) {
        this.cantidadGallinas = cantidadGallinas;
    }

    @Column(name = "CANT_PATOS")
    public Integer getCantidadPatos() {
        return cantidadPatos;
    }

    public void setCantidadPatos(Integer cantidadPatos) {
        this.cantidadPatos = cantidadPatos;
    }

    @Column(name = "CANT_PERROS")
    public Integer getCantidadPerros() {
        return cantidadPerros;
    }

    public void setCantidadPerros(Integer cantidadPerros) {
        this.cantidadPerros = cantidadPerros;
    }

    @Column(name = "CANT_GATOS")
    public Integer getCantidadGatos() {
        return cantidadGatos;
    }

    public void setCantidadGatos(Integer cantidadGatos) {
        this.cantidadGatos = cantidadGatos;
    }

    @Column(name = "CANT_CERDOS")
    public Integer getCantidadCerdos() {
        return cantidadCerdos;
    }

    public void setCantidadCerdos(Integer cantidadCerdos) {
        this.cantidadCerdos = cantidadCerdos;
    }

    @Column(name = "CANT_VACAS")
    public Integer getCantidadVacas() {
        return cantidadVacas;
    }

    public void setCantidadVacas(Integer cantidadVacas) {
        this.cantidadVacas = cantidadVacas;
    }

    @Column(name = "CANT_CABRAS")
    public Integer getCantidadCabras() {
        return cantidadCabras;
    }

    public void setCantidadCabras(Integer cantidadCabras) {
        this.cantidadCabras = cantidadCabras;
    }

    @Column(name = "CANT_CABALLOS")
    public Integer getCantidadCaballos() {
        return cantidadCaballos;
    }

    public void setCantidadCaballos(Integer cantidadCaballos) {
        this.cantidadCaballos = cantidadCaballos;
    }

    @Column(name = "CANT_PELIBUEYS")
    public Integer getCantidadPelibueys() {
        return cantidadPelibueys;
    }

    public void setCantidadPelibueys(Integer cantidadPelibueys) {
        this.cantidadPelibueys = cantidadPelibueys;
    }

    @Column(name = "CANT_AVES")
    public Integer getCantidadAves() {
        return cantidadAves;
    }

    public void setCantidadAves(Integer cantidadAves) {
        this.cantidadAves = cantidadAves;
    }

    @Column(name = "CANT_OTROS_ANIMALES")
    public Integer getCantidadOtrosAnimales() {
        return cantidadOtrosAnimales;
    }

    public void setCantidadOtrosAnimales(Integer cantidadOtrosAnimales) {
        this.cantidadOtrosAnimales = cantidadOtrosAnimales;
    }

    @Column(name = "GALLINAS_DENTRO_CASA", length = 1)
    public String getGallinasDentroCasa() {
        return gallinasDentroCasa;
    }

    public void setGallinasDentroCasa(String gallinasDentroCasa) {
        this.gallinasDentroCasa = gallinasDentroCasa;
    }

    @Column(name = "PATOS_DENTRO_CASA", length = 1)
    public String getPatosDentroCasa() {
        return patosDentroCasa;
    }

    public void setPatosDentroCasa(String patosDentroCasa) {
        this.patosDentroCasa = patosDentroCasa;
    }

    @Column(name = "PERROS_DENTRO_CASA", length = 1)
    public String getPerrosDentroCasa() {
        return perrosDentroCasa;
    }

    public void setPerrosDentroCasa(String perrosDentroCasa) {
        this.perrosDentroCasa = perrosDentroCasa;
    }

    @Column(name = "GATOS_DENTRO_CASA", length = 1)
    public String getGatosDentroCasa() {
        return gatosDentroCasa;
    }

    public void setGatosDentroCasa(String gatosDentroCasa) {
        this.gatosDentroCasa = gatosDentroCasa;
    }

    @Column(name = "CERDOS_DENTRO_CASA", length = 1)
    public String getCerdosDentroCasa() {
        return cerdosDentroCasa;
    }

    public void setCerdosDentroCasa(String cerdosDentroCasa) {
        this.cerdosDentroCasa = cerdosDentroCasa;
    }

    @Column(name = "VACAS_DENTRO_CASA", length = 1)
    public String getVacasDentroCasa() {
        return vacasDentroCasa;
    }

    public void setVacasDentroCasa(String vacasDentroCasa) {
        this.vacasDentroCasa = vacasDentroCasa;
    }

    @Column(name = "CABRAS_DENTRO_CASA", length = 1)
    public String getCabrasDentroCasa() {
        return cabrasDentroCasa;
    }

    public void setCabrasDentroCasa(String cabrasDentroCasa) {
        this.cabrasDentroCasa = cabrasDentroCasa;
    }

    @Column(name = "CABALLOS_DENTRO_CASA", length = 1)
    public String getCaballosDentroCasa() {
        return caballosDentroCasa;
    }

    public void setCaballosDentroCasa(String caballosDentroCasa) {
        this.caballosDentroCasa = caballosDentroCasa;
    }

    @Column(name = "PELIBUEYS_DENTRO_CASA", length = 1)
    public String getPelibueysDentroCasa() {
        return pelibueysDentroCasa;
    }

    public void setPelibueysDentroCasa(String pelibueysDentroCasa) {
        this.pelibueysDentroCasa = pelibueysDentroCasa;
    }

    @Column(name = "AVES_DENTRO_CASA", length = 1)
    public String getAvesDentroCasa() {
        return avesDentroCasa;
    }

    public void setAvesDentroCasa(String avesDentroCasa) {
        this.avesDentroCasa = avesDentroCasa;
    }

    @Column(name = "OTROS_ANI_DENTRO_CASA", length = 1)
    public String getOtrosAnimalesDentroCasa() {
        return otrosAnimalesDentroCasa;
    }

    public void setOtrosAnimalesDentroCasa(String otrosAnimalesDentroCasa) {
        this.otrosAnimalesDentroCasa = otrosAnimalesDentroCasa;
    }

    @Column(name = "PERS_FUMA_DENTRO_CASA", length = 1)
    public String getPersonaFumaDentroCasa() {
        return personaFumaDentroCasa;
    }

    public void setPersonaFumaDentroCasa(String personaFumaDentroCasa) {
        this.personaFumaDentroCasa = personaFumaDentroCasa;
    }

    @Column(name = "TIENE_RECOLECCION_BAS", length = 1)
    public String getTieneRecoleccionBasura() {
        return tieneRecoleccionBasura;
    }

    public void setTieneRecoleccionBasura(String tieneRecoleccionBasura) {
        this.tieneRecoleccionBasura = tieneRecoleccionBasura;
    }

    @Column(name = "CANT_RECOLECCION_BAS")
    public Integer getCuantasVecesRecBasura() {
        return cuantasVecesRecBasura;
    }

    public void setCuantasVecesRecBasura(Integer cuantasVecesRecBasura) {
        this.cuantasVecesRecBasura = cuantasVecesRecBasura;
    }

    @Column(name = "DONDE_RECOLECCION_BAS")
    public String getDondePasaRecBasura() {
        return dondePasaRecBasura;
    }

    public void setDondePasaRecBasura(String dondePasaRecBasura) {
        this.dondePasaRecBasura = dondePasaRecBasura;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaCasa{" + casa.getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaCasa)) return false;

        EncuestaCasa that = (EncuestaCasa) o;

        return  (!casa.equals(that.casa));
    }

    @Override
    public int hashCode() {
        return casa.hashCode();
    }
}
