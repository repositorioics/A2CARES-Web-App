package ni.org.ics.webapp.domain.entomologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by miguel on 15/8/2022.
 */
@Entity
@Table(name = "ento_cuestionario_hogar", catalog = "a2cares")
public class CuestionarioHogar  extends BaseMetaData implements Auditable {

    private String codigoEncuesta;


    private Date fechaCuestionario;
    private Integer barrio;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private String tipoIngresoCodigo;
    private String codigoVivienda;
    private String tipoVivienda;

    private String hayAmbientePERI;
    private String horaCapturaPERI;
    private Double humedadRelativaPERI;
    private Double temperaturaPERI;
    private String tipoIngresoCodigoPERI;
    private String codigoPERI;

    private String hayAmbienteINTRA;
    private String horaCapturaINTRA;
    private Double humedadRelativaINTRA;
    private Double temperaturaINTRA;
    private String tipoIngresoCodigoINTRA;
    private String codigoINTRA;

    //01.	¿Quién está contestando éste cuestionario?
    private String quienContesta;
    private String quienContestaOtro;

    //02.	Me podría decir ¿Qué edad tiene usted?
    private String edadContest;

    //03.	¿Cuál es su último año aprobado?
    private String escolaridadContesta;

    //04.	¿Cuánto tiempo tienen de vivir en este barrio?
    private String tiempoVivirBarrio;

    //05.	 ¿Cuántas personas viven en esta casa?
    private int cuantasPersonasViven;

    //06.	¿Qué edad tienen las niñas y mujeres que viven en esta casa, me    puede decir sus edades de menor a mayor?
    private String edadesFemenino;

    //07.	¿Qué edad tienen los niños y hombres que viven en esta casa, me puede decir sus edades de menor a mayor?
    private String edadesMasculino;

    //08.	De todas las personas que me mencionó, ¿Alguna de ellas usó mosquitero para dormir el día de ayer?
    private String usaronMosquitero;
    private String quienesUsaronMosquitero;

    //09.	De todas las personas que me mencionó, ¿Alguna de ellas usó repelente en su piel, para protegerse de los zancudos el día de ayer?
    private String usaronRepelente;
    private String quienesUsaronRepelente;

    //10.	¿Conoce Usted las larvas o clavitos de los zancudos?
    private String conoceLarvas;

    //11.	¿Alguien les ha visitado para enseñarles como buscar y eliminar las larvas o clavitos de los zancudos?
    private String alguienVisEliminarLarvas;

    //12.	¿Quién?
    private String quienVisEliminarLarvas;
    private String quienVisEliminarLarvasOtro;

    //13. ¿Alguien de esta casa dedica tiempo para buscar y eliminar criaderos de zancudos aquí en su casa?
    private String alguienDedicaElimLarvasCasa;

    //14.	¿Quién?
    private String quienDedicaElimLarvasCasa;

    //15. ¿Cada cuánto tiempo buscan y eliminan criaderos de zancudos aquí en su casa?
    private String tiempoElimanCridaros;

    //16. ¿Hay bastante zancudos aquí en su casa?
    private String hayBastanteZancudos;

    //17.	¿Qué hace falta en esta casa para evitar los criaderos de zancudos?
    private String queFaltaCasaEvitarZancudos;
    private String queFaltaCasaEvitarZancudosOtros;

    //18.	El mes pasado, gastaron dinero en compra de productos para evitar los Zancudos?
    private String gastaronDineroProductos;

    //19.	¿Qué productos compraron?
    private String queProductosCompraron;
    private String queProductosCompraronOtros;

    //20.	¿Cuánto gastaron?
    private String cuantoGastaron;

    //21	¿Cuándo fue la última vez que el MINSA visitó su casa para aplicar BTI en sus recipientes con agua?
    private String ultimaVisitaMinsaBTI;

    //22	¿Cuándo fue la última vez que el MINSA fumigó su casa?
    private String ultimaVezMinsaFumigo;

    //23 ¿Qué tanto riesgo hay en su casa de enfermar por el virus del dengue?
    private String riesgoCasaDengue;

    //24.	¿Hay problemas con el abastecimiento de agua en este barrio?
    private String problemasAbastecimiento;

    //25.	¿Cada cuánto se les va el agua?
    private String cadaCuantoVaAgua;
    private String cadaCuantoVaAguaOtro;

    //26.	¿Cuantas horas al día se les va?
    private int horasSinAguaDia;

    //27.	¿Qué hacen con la basura del hogar?
    private String queHacenBasuraHogar;
    private String queHacenBasuraHogarOtro;

    //28.	¿Qué tanto riesgo hay en este barrio de enfermar por el virus del dengue?
    private String riesgoBarrioDengue;

    //29.	¿En los últimos tres meses, en este barrio han realizado acciones para eliminar criaderos de zancudos del barrio?
    private String accionesCriaderosBarrio;

    //30.	¿Qué acciones realizaron?
    private String queAcciones;
    private String queAccionesOtro;

    //31.	Alguien de la casa participó en esa actividad?
    private String alguienParticipo;

    //32 Quién?
    private String quienParticipo;

    //33.	¿Cuál es el mayor criadero de Zancudos de este barrio?
    private String mayorCriaderoBarrio;

    private String materialParedes;
    private String materialPiso;
    private String materialTecho;
    private String otroMaterialParedes;
    private String otroMaterialPiso;
    private String otroMaterialTecho;

    @Id
    @Column(name = "codigo_cuestionario", nullable = false, length = 36)
    public String getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(String codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    @Column(name = "fecha_cuestionario", nullable = false)
    public Date getFechaCuestionario() {
        return fechaCuestionario;
    }

    public void setFechaCuestionario(Date fechaCuestionario) {
        this.fechaCuestionario = fechaCuestionario;
    }

    @Column(name = "codigo_barrio", nullable = false)
    public Integer getBarrio() {
        return barrio;
    }

    public void setBarrio(Integer barrio) {
        this.barrio = barrio;
    }

    @Column(name = "direccion", nullable = false, length = 500)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    @Column(name = "latitud", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "longitud", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Column(name = "tipo_ingreso_codigo", nullable = false, length = 2)
    public String getTipoIngresoCodigo() {
        return tipoIngresoCodigo;
    }

    public void setTipoIngresoCodigo(String tipoIngresoCodigo) {
        this.tipoIngresoCodigo = tipoIngresoCodigo;
    }

    @Column(name = "codigo_vivienda", nullable = false, length = 4)
    public String getCodigoVivienda() {
        return codigoVivienda;
    }

    public void setCodigoVivienda(String codigoVivienda) {
        this.codigoVivienda = codigoVivienda;
    }

    @Column(name = "tipo_vivienda", nullable = false, length = 2)
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }


    @Column(name = "hay_ambiente_peri", nullable = false, length = 2)
    public String getHayAmbientePERI() {
        return hayAmbientePERI;
    }

    public void setHayAmbientePERI(String hayAmbientePERI) {
        this.hayAmbientePERI = hayAmbientePERI;
    }

    @Column(name = "hora_captura_peri", nullable = true, length = 16)
    public String getHoraCapturaPERI() {
        return horaCapturaPERI;
    }

    public void setHoraCapturaPERI(String horaCaptura) {
        this.horaCapturaPERI = horaCaptura;
    }

    @Column(name = "porcentaje_humedad_peri", nullable = true)
    public Double getHumedadRelativaPERI() {
        return humedadRelativaPERI;
    }

    public void setHumedadRelativaPERI(Double porcentajeHumedadRelativa) {
        this.humedadRelativaPERI = porcentajeHumedadRelativa;
    }

    @Column(name = "temperatura_peri", nullable = true)
    public Double getTemperaturaPERI() {
        return temperaturaPERI;
    }

    public void setTemperaturaPERI(Double temperatura) {
        this.temperaturaPERI = temperatura;
    }

    @Column(name = "tipo_ingreso_cod_peri", nullable = true, length = 2)
    public String getTipoIngresoCodigoPERI() {
        return tipoIngresoCodigoPERI;
    }

    public void setTipoIngresoCodigoPERI(String tipoIngresoCodigoPERI) {
        this.tipoIngresoCodigoPERI = tipoIngresoCodigoPERI;
    }

    @Column(name = "codigo_peri", nullable = true, length = 16)
    public String getCodigoPERI() {
        return codigoPERI;
    }

    public void setCodigoPERI(String codigoPERI) {
        this.codigoPERI = codigoPERI;
    }

    @Column(name = "hay_ambiente_intra", nullable = false, length = 2)
    public String getHayAmbienteINTRA() {
        return hayAmbienteINTRA;
    }

    public void setHayAmbienteINTRA(String hayAmbienteINTRA) {
        this.hayAmbienteINTRA = hayAmbienteINTRA;
    }

    @Column(name = "hora_captura_intra", nullable = true, length = 16)
    public String getHoraCapturaINTRA() {
        return horaCapturaINTRA;
    }

    public void setHoraCapturaINTRA(String horaCapturaINTRA) {
        this.horaCapturaINTRA = horaCapturaINTRA;
    }

    @Column(name = "porcentaje_humedad_intra", nullable = true)
    public Double getHumedadRelativaINTRA() {
        return humedadRelativaINTRA;
    }

    public void setHumedadRelativaINTRA(Double humedadRelativaINTRA) {
        this.humedadRelativaINTRA = humedadRelativaINTRA;
    }

    @Column(name = "temperatura_intra", nullable = true)
    public Double getTemperaturaINTRA() {
        return temperaturaINTRA;
    }

    public void setTemperaturaINTRA(Double temperaturaINTRA) {
        this.temperaturaINTRA = temperaturaINTRA;
    }

    @Column(name = "tipo_ingreso_cod_intra", nullable = true, length = 2)
    public String getTipoIngresoCodigoINTRA() {
        return tipoIngresoCodigoINTRA;
    }

    public void setTipoIngresoCodigoINTRA(String tipoIngresoCodigoINTRA) {
        this.tipoIngresoCodigoINTRA = tipoIngresoCodigoINTRA;
    }

    @Column(name = "codigo_intra", nullable = true, length = 16)
    public String getCodigoINTRA() {
        return codigoINTRA;
    }

    public void setCodigoINTRA(String codigoINTRA) {
        this.codigoINTRA = codigoINTRA;
    }

    @Column(name = "quien_contesta", nullable = false, length = 4)
    public String getQuienContesta() {
        return quienContesta;
    }

    public void setQuienContesta(String quienContesta) {
        this.quienContesta = quienContesta;
    }

    @Column(name = "quien_contesta_otro", length = 255)
    public String getQuienContestaOtro() {
        return quienContestaOtro;
    }

    public void setQuienContestaOtro(String quienContestaOtro) {
        this.quienContestaOtro = quienContestaOtro;
    }

    @Column(name = "quien_contesta_edad", nullable = false, length = 8)
    public String getEdadContest() {
        return edadContest;
    }

    public void setEdadContest(String edadContest) {
        this.edadContest = edadContest;
    }

    @Column(name = "quien_contesta_escolaridad", nullable = false, length = 4)
    public String getEscolaridadContesta() {
        return escolaridadContesta;
    }

    public void setEscolaridadContesta(String escolaridadContesta) {
        this.escolaridadContesta = escolaridadContesta;
    }

    @Column(name = "tiempo_vivir_barrio", nullable = false, length = 4)
    public String getTiempoVivirBarrio() {
        return tiempoVivirBarrio;
    }

    public void setTiempoVivirBarrio(String tiempoVivirBarrio) {
        this.tiempoVivirBarrio = tiempoVivirBarrio;
    }

    @Column(name = "cuantas_personas_viven", nullable = false)
    public int getCuantasPersonasViven() {
        return cuantasPersonasViven;
    }

    public void setCuantasPersonasViven(int cuantasPersonasViven) {
        this.cuantasPersonasViven = cuantasPersonasViven;
    }

    @Column(name = "edades_femenino", nullable = false, length = 64)
    public String getEdadesFemenino() {
        return edadesFemenino;
    }

    public void setEdadesFemenino(String edadesFemenino) {
        this.edadesFemenino = edadesFemenino;
    }

    @Column(name = "edades_masculino", nullable = false, length = 64)
    public String getEdadesMasculino() {
        return edadesMasculino;
    }

    public void setEdadesMasculino(String edadesMasculio) {
        this.edadesMasculino = edadesMasculio;
    }

    @Column(name = "usaron_mosquitero", nullable = false, length = 4)
    public String getUsaronMosquitero() {
        return usaronMosquitero;
    }

    public void setUsaronMosquitero(String usaronMosqutero) {
        this.usaronMosquitero = usaronMosqutero;
    }

    @Column(name = "quienes_usaron_mosquitero", nullable = true, length = 128)
    public String getQuienesUsaronMosquitero() {
        return quienesUsaronMosquitero;
    }

    public void setQuienesUsaronMosquitero(String quienesUsaronMosquitero) {
        this.quienesUsaronMosquitero = quienesUsaronMosquitero;
    }

    @Column(name = "usaron_repelente", nullable = false, length = 4)
    public String getUsaronRepelente() {
        return usaronRepelente;
    }

    public void setUsaronRepelente(String usaronRepelente) {
        this.usaronRepelente = usaronRepelente;
    }

    @Column(name = "quienes_usaron_repelente", nullable = true, length = 128)
    public String getQuienesUsaronRepelente() {
        return quienesUsaronRepelente;
    }

    public void setQuienesUsaronRepelente(String quienesUsaronRepelente) {
        this.quienesUsaronRepelente = quienesUsaronRepelente;
    }

    @Column(name = "conoce_larvas", nullable = false, length = 4)
    public String getConoceLarvas() {
        return conoceLarvas;
    }

    public void setConoceLarvas(String conoceLarvas) {
        this.conoceLarvas = conoceLarvas;
    }

    @Column(name = "alguien_vis_elim_larvas", nullable = false, length = 4)
    public String getAlguienVisEliminarLarvas() {
        return alguienVisEliminarLarvas;
    }

    public void setAlguienVisEliminarLarvas(String alguienVisEliminarLarvas) {
        this.alguienVisEliminarLarvas = alguienVisEliminarLarvas;
    }

    @Column(name = "quien_vis_elim_larvas", nullable = true, length = 4)
    public String getQuienVisEliminarLarvas() {
        return quienVisEliminarLarvas;
    }

    public void setQuienVisEliminarLarvas(String quienVisEliminarLarvas) {
        this.quienVisEliminarLarvas = quienVisEliminarLarvas;
    }

    @Column(name = "quien_vis_elim_larvas_otro", nullable = true, length = 255)
    public String getQuienVisEliminarLarvasOtro() {
        return quienVisEliminarLarvasOtro;
    }

    public void setQuienVisEliminarLarvasOtro(String quienVisEliminarLarvasOtro) {
        this.quienVisEliminarLarvasOtro = quienVisEliminarLarvasOtro;
    }

    @Column(name = "alguien_dedica_elim_larvas", nullable = false, length = 4)
    public String getAlguienDedicaElimLarvasCasa() {
        return alguienDedicaElimLarvasCasa;
    }

    public void setAlguienDedicaElimLarvasCasa(String alguienDedicaElimLarvasCasa) {
        this.alguienDedicaElimLarvasCasa = alguienDedicaElimLarvasCasa;
    }

    @Column(name = "quien_dedica_elim_larvas", nullable = true, length = 128)
    public String getQuienDedicaElimLarvasCasa() {
        return quienDedicaElimLarvasCasa;
    }

    public void setQuienDedicaElimLarvasCasa(String quienDedicaElimLarvasCasa) {
        this.quienDedicaElimLarvasCasa = quienDedicaElimLarvasCasa;
    }

    @Column(name = "tiempo_eliminan_criaderos", nullable = true, length = 4)
    public String getTiempoElimanCridaros() {
        return tiempoElimanCridaros;
    }

    public void setTiempoElimanCridaros(String tiempoElimanCridaros) {
        this.tiempoElimanCridaros = tiempoElimanCridaros;
    }

    @Column(name = "hay_bastante_zancudos", nullable = false, length = 4)
    public String getHayBastanteZancudos() {
        return hayBastanteZancudos;
    }

    public void setHayBastanteZancudos(String hayBastanteZancudos) {
        this.hayBastanteZancudos = hayBastanteZancudos;
    }

    @Column(name = "falta_evitar_zancudos", nullable = false, length = 64)
    public String getQueFaltaCasaEvitarZancudos() {
        return queFaltaCasaEvitarZancudos;
    }

    public void setQueFaltaCasaEvitarZancudos(String queFaltaCasaEvitarZancudos) {
        this.queFaltaCasaEvitarZancudos = queFaltaCasaEvitarZancudos;
    }

    @Column(name = "falta_evitar_zancudos_otros", nullable = true, length = 255)
    public String getQueFaltaCasaEvitarZancudosOtros() {
        return queFaltaCasaEvitarZancudosOtros;
    }

    public void setQueFaltaCasaEvitarZancudosOtros(String queFaltaCasaEvitarZancudosOtros) {
        this.queFaltaCasaEvitarZancudosOtros = queFaltaCasaEvitarZancudosOtros;
    }

    @Column(name = "gastaron_dinero_productos", nullable = false, length = 4)
    public String getGastaronDineroProductos() {
        return gastaronDineroProductos;
    }

    public void setGastaronDineroProductos(String gastaronDineroProductos) {
        this.gastaronDineroProductos = gastaronDineroProductos;
    }

    @Column(name = "que_productos_compraron", nullable = true, length = 64)
    public String getQueProductosCompraron() {
        return queProductosCompraron;
    }

    public void setQueProductosCompraron(String queProductosCompraron) {
        this.queProductosCompraron = queProductosCompraron;
    }

    @Column(name = "que_productos_compraron_otros", nullable = true, length = 255)
    public String getQueProductosCompraronOtros() {
        return queProductosCompraronOtros;
    }

    public void setQueProductosCompraronOtros(String queProductosCompraronOtros) {
        this.queProductosCompraronOtros = queProductosCompraronOtros;
    }

    @Column(name = "cuanto_gastaron_productos", nullable = true, length = 4)
    public String getCuantoGastaron() {
        return cuantoGastaron;
    }

    public void setCuantoGastaron(String cuantoGastaron) {
        this.cuantoGastaron = cuantoGastaron;
    }

    @Column(name = "ultima_vez_minsa_bti", nullable = false, length = 4)
    public String getUltimaVisitaMinsaBTI() {
        return ultimaVisitaMinsaBTI;
    }

    public void setUltimaVisitaMinsaBTI(String ultimaVisitaMinsaBTI) {
        this.ultimaVisitaMinsaBTI = ultimaVisitaMinsaBTI;
    }

    @Column(name = "ultima_vez_minsa_fumigo", nullable = false, length = 4)
    public String getUltimaVezMinsaFumigo() {
        return ultimaVezMinsaFumigo;
    }

    public void setUltimaVezMinsaFumigo(String ultimaVezMinsaFumigo) {
        this.ultimaVezMinsaFumigo = ultimaVezMinsaFumigo;
    }

    @Column(name = "riesgo_enfermar_dengue_casa", nullable = false, length = 4)
    public String getRiesgoCasaDengue() {
        return riesgoCasaDengue;
    }

    public void setRiesgoCasaDengue(String riesgoCasaDengue) {
        this.riesgoCasaDengue = riesgoCasaDengue;
    }

    @Column(name = "hay_problema_agua", nullable = false, length = 4)
    public String getProblemasAbastecimiento() {
        return problemasAbastecimiento;
    }

    public void setProblemasAbastecimiento(String problemasAbastecimiento) {
        this.problemasAbastecimiento = problemasAbastecimiento;
    }

    @Column(name = "cada_cuanto_va_agua", nullable = true, length = 4)
    public String getCadaCuantoVaAgua() {
        return cadaCuantoVaAgua;
    }

    public void setCadaCuantoVaAgua(String cadaCuantoVaAgua) {
        this.cadaCuantoVaAgua = cadaCuantoVaAgua;
    }

    @Column(name = "cada_cuanto_va_agua_otro", nullable = true, length = 255)
    public String getCadaCuantoVaAguaOtro() {
        return cadaCuantoVaAguaOtro;
    }

    public void setCadaCuantoVaAguaOtro(String cadaCuantoVaAguaOtro) {
        this.cadaCuantoVaAguaOtro = cadaCuantoVaAguaOtro;
    }

    @Column(name = "horas_sin_agua", nullable = true)
    public int getHorasSinAguaDia() {
        return horasSinAguaDia;
    }

    public void setHorasSinAguaDia(int horasSinAguaDia) {
        this.horasSinAguaDia = horasSinAguaDia;
    }

    @Column(name = "que_hacen_basura", nullable = false, length = 4)
    public String getQueHacenBasuraHogar() {
        return queHacenBasuraHogar;
    }

    public void setQueHacenBasuraHogar(String queHacenBasuraHogar) {
        this.queHacenBasuraHogar = queHacenBasuraHogar;
    }

    @Column(name = "que_hacen_basura_otro", nullable = true, length = 255)
    public String getQueHacenBasuraHogarOtro() {
        return queHacenBasuraHogarOtro;
    }

    public void setQueHacenBasuraHogarOtro(String queHacenBasuraHogarOtro) {
        this.queHacenBasuraHogarOtro = queHacenBasuraHogarOtro;
    }

    @Column(name = "riesgo_enfermar_dengue_barrio", nullable = false, length = 4)
    public String getRiesgoBarrioDengue() {
        return riesgoBarrioDengue;
    }

    public void setRiesgoBarrioDengue(String riesgoBarrioDengue) {
        this.riesgoBarrioDengue = riesgoBarrioDengue;
    }

    @Column(name = "acciones_barrio_elim_zancudos", nullable = false, length = 4)
    public String getAccionesCriaderosBarrio() {
        return accionesCriaderosBarrio;
    }

    public void setAccionesCriaderosBarrio(String accionesCriaderosBarrio) {
        this.accionesCriaderosBarrio = accionesCriaderosBarrio;
    }

    @Column(name = "que_acciones", nullable = true, length = 16)
    public String getQueAcciones() {
        return queAcciones;
    }

    public void setQueAcciones(String queAcciones) {
        this.queAcciones = queAcciones;
    }

    @Column(name = "que_acciones_otro", nullable = true, length = 255)
    public String getQueAccionesOtro() {
        return queAccionesOtro;
    }

    public void setQueAccionesOtro(String queAccionesOtro) {
        this.queAccionesOtro = queAccionesOtro;
    }

    @Column(name = "alguien_participo_acciones", nullable = true, length = 4)
    public String getAlguienParticipo() {
        return alguienParticipo;
    }

    public void setAlguienParticipo(String alguienParticipo) {
        this.alguienParticipo = alguienParticipo;
    }

    @Column(name = "quien_participo_acciones", nullable = true, length = 128)
    public String getQuienParticipo() {
        return quienParticipo;
    }

    public void setQuienParticipo(String quienParticipo) {
        this.quienParticipo = quienParticipo;
    }

    @Column(name = "mayor_criadero_barrio", nullable = false, length = 255)
    public String getMayorCriaderoBarrio() {
        return mayorCriaderoBarrio;
    }

    public void setMayorCriaderoBarrio(String mayorCreaderoBarrio) {
        this.mayorCriaderoBarrio = mayorCreaderoBarrio;
    }


    @Column(name = "meterial_paredes", length = 20)
    public String getMaterialParedes() {
        return materialParedes;
    }

    public void setMaterialParedes(String materialParedes) {
        this.materialParedes = materialParedes;
    }

    @Column(name = "material_piso", length = 16)
    public String getMaterialPiso() {
        return materialPiso;
    }

    public void setMaterialPiso(String materialPiso) {
        this.materialPiso = materialPiso;
    }

    @Column(name = "material_techo", length = 3)
    public String getMaterialTecho() {
        return materialTecho;
    }

    public void setMaterialTecho(String materialTecho) {
        this.materialTecho = materialTecho;
    }

    @Column(name = "otro_meterial_paredes")
    public String getOtroMaterialParedes() {
        return otroMaterialParedes;
    }

    public void setOtroMaterialParedes(String otroMaterialParedes) {
        this.otroMaterialParedes = otroMaterialParedes;
    }

    @Column(name = "otro_material_piso")
    public String getOtroMaterialPiso() {
        return otroMaterialPiso;
    }

    public void setOtroMaterialPiso(String otroMaterialPiso) {
        this.otroMaterialPiso = otroMaterialPiso;
    }

    @Column(name = "otro_material_techo")
    public String getOtroMaterialTecho() {
        return otroMaterialTecho;
    }

    public void setOtroMaterialTecho(String otroMaterialTecho) {
        this.otroMaterialTecho = otroMaterialTecho;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoEncuesta='" + codigoEncuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuestionarioHogar)) return false;

        CuestionarioHogar that = (CuestionarioHogar) o;

        if (!codigoEncuesta.equals(that.codigoEncuesta)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoEncuesta.hashCode();
    }
}
