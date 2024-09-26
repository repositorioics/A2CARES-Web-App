package ni.org.ics.webapp.domain.entomologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Everts on 02/09/2024.
 */
@Entity
@Table(name = "ento_cuestionario_cambio_climatico", catalog = "a2cares")
public class CuestionarioCAP extends BaseMetaData implements Auditable {
    private String codigoEncuesta;
    private Date fechaCuestionario;

   //1.	Código de vivienda
    private String codigoVivienda;
    private Double latitud;
    private Double longitud;

    //2. Con respecto a quien responde el cuestionario, ¿Es usted participante o usted fue quien dio la autorización para la participación del niño/niña en el estudio?
    private String quienContesta;
    private String quienContestaOtro;

    //03. ¿Qué edad tiene usted?
    private int edadContest;

    //4.	Género por observación
    private String generoObservacion;

    //5.	¿Cuál es su último grado o año aprobado?
    private String ultimoGradoAprobado;

    //6.	¿Usted a qué se dedica?
    private String aqueSeDedica;
    private String aqueSeDedicaOtros;

    //07.	 ¿Cuántas personas viven en esta casa?
    private int cuantasPersonasViven;

    //08.	¿Qué edad tienen las niñas y mujeres que viven en esta casa, me    puede decir sus edades de menor a mayor?
    private String edadesFemenino;

    //09.	¿Qué edad tienen los niños y hombres que viven en esta casa, me puede decir sus edades de menor a mayor?
    private String edadesMasculino;

   //10. ¿Alguna vez ha escuchado hablar sobre el cambio climático?
   private String escuchadoCambioClimatico;

   //11.	¿Qué tan informado se siente usted sobre el cambio climático?
   private String queTanInformadoCambioClimatico;

   //12.	¿Cómo se ha informado sobre el cambio climático?
   private String comoseHaInformado;
    private String comoseHaInformadoOtros;

    //13.	¿Por qué ocurre el cambio climático?
    private String porqueOcurreCambioclimatico;
    private String porqueOcurreCambioclimaticoOtros;

    //14.	¿Qué problemas surgen por el cambio climático?
    private String porqueProblemasOcurreCambioclimatico;
    private String porqueProblemasOcurreCambioclimaticoOtros;

    //15.	¿Quién tiene la principal responsabilidad de llevar a cabo acciones para reducir los daños causados por el cambio climático?
    private String responsableAccionesReducirDanos;
    private String responsableAccionesReducirDanosOtros;

    //16.	¿Considera que el cambio climático puede afectar la salud de las personas?
    private String consideraCambioCliAfectaSalud;

    //17.	¿Qué enfermedades considera usted que son causadas por el cambio climático?
    private String enfermedadesCausadasCambioClimatico;
    private String enfermedadesCausadasCambioClimaticoOtros;

    //18. ¿Qué tanto riesgo considera que hay en su casa de enfermarse por el virus del dengue?
    private String riesgoCasaEnfermarseDengue;

    //19.	¿Qué tanto riesgo considera que hay en su barrio de enfermar por el virus del dengue?
    private String riesgoBarrioEnfermarseDengue;

    //20.	 ¿Considera que el cambio climático puede aumentar el riesgo de enfermar por el virus del dengue en su barrio?
    private String consideraCambCliAumentaDengueBarrio;

    //21. ¿Considera usted que los zancudos pueden adaptarse a vivir en lugares donde hace mucho frío?
    private String consideraZancudosVivirLugarFrio;

    //22.	 ¿Considera usted que los zancudos pueden adaptarse a vivir en lugares donde hace mucho calor?
    private String consideraZancudosVivirLugarCalor;

    //23. ¿Hay muchos zancudos en su barrio?
    private String hayZancudosBarrio;

    //24.	¿Hay muchos zancudos donde usted trabaja?
    private String hayZancudosTrabajo;

    //25.	¿Hay muchos zancudos en la escuela del barrio?
    private String hayZancudosEscuela;

    //26. ¿Y hay muchos zancudos aquí en su casa?
    private String hayZancudosCasa;

    //27 ¿Sabe cómo identificar un criadero de zancudos en su casa?
    private String sabeIdentificarCriaderosCasa;

    //27.1 Si la respuesta es Sí -> ¿Alguien de esta casa dedica tiempo para buscar y eliminar criaderos de zancudos aquí en su casa?
    private String alguienBuscaEliminaCriaderosCasa;

    //27.2 ¿Cada cuánto tiempo buscan y eliminan criaderos de zancudos aquí en su casa?
    private String cadaCuantoBuscaEliminaCriaderosCasa;

    //28.	¿Cuándo fue la última vez que el MINSA visitó su casa para aplicar abate en sus recipientes con agua?
    private String ultimaVezAbateMinsa;

    //29.	¿Cuándo fue la última vez que el MINSA fumigó su casa?
    private String ultimaVezFumigaMinsa;

    //30.	¿Que tanto le preocupa el cambio climático?
    private String lePreocupaCambioClimatico;

    //31.	¿Es posible prevenir los daños que causan el cambio climático?
    private String prevenirDanosCambioClimatico;
 private String prevenirDanosCambioClimaticoOtros;

    //32.	¿Qué tan preparado/a se siente usted para enfrentar situaciones climáticas como: huracanes, inundaciones o calores extremos?
    private String seSientePreparadoEnfrentarSituaciones;

    //33.	¿Considera usted que el cambio climático afectará negativamente su vida?
    private String afectaraSuVidaNegativamenteCambioClimatico;

    //34.	¿Considera usted que el cambio climático afectará negativamente la vida de la próxima generación?
    private String afectaraNegativamenteGeneracionCambioClimatico;

    //35.	 ¿Qué tanto riesgo hay de que las lluvias intensas puedan causar daño a su casa?
    private String riesgoLluviasCausanDañoCasa;

    //36.	¿Considera usted que el cambio climático afectará negativamente su barrio?
    private String consideraCambioCliAfectaNegativamenteBarrio;

    //37.	¿Qué tan preparada está su comunidad para enfrentar situaciones climáticas como: huracanes, inundaciones o calores extremos?
    private String  queTanPreparadoComunidadEnfrentarSituaciones;

    //38.	¿Cuándo fue la última vez que se inundó su barrio?
    private String  ultimaVezInundoBarrio;

    //39.	¿Cuándo fue la última vez que tuvieron un incendio forestal en su barrio?
    private String  ultimaVezIncendioBarrio;

    //39.1 Si hubo un incendio -> ¿El incendio ocurrió por actividades humanas o fue de origen natural?
    private String  ultimaVezIncendioBarrioCausas;
    private String  ultimaVezIncendioBarrioCausasOtros;

    //40.	¿Cuánto tiempo tiene de vivir en este barrio?
    private String tiempoVivirBarrio;

    //41.	¿En los últimos 5 años, ha notado algún cambio en el clima de su barrio?
    private String ultimos5AñosCambioClima;

    //41.1 Si la respuesta es Sí > ¿Ha notado más calor o más frío?
    private String ultimos5AñosCambioClimaMasCalorFrios;

    //42.	¿En comparación a hace 5 años, ha visto más o menos árboles en su barrio?
    private String ultimos5AñosMasMenosArbolesBarrio;

    //43.	¿En comparación a hace 5 años, ha notado una mejora o deterioro en el abastecimiento de agua en su barrio?
    private String ultimos5AñosAbastecimientoAgua;

    //44.	¿Cuál es el lugar que más produce problemas de contaminación en su barrio?
    private String lugarProduceMasContaminacion;
    private String lugarProduceMasContaminacionOtros;

    //45.	¿Ahorra energía en su casa?
    private String ahorraEnergiaCasa;

    //45.1	Si la respuesta es Sí > ¿Qué hace para ahorrar energía en su casa?
    private String queHaceParaAhorraEnergiaCasa;
    private String queHaceParaAhorraEnergiaCasaOtros;

    //46.	¿Ahorra agua en su casa?
    private String ahorraAguaCasa;

    //46.1	Si la respuesta es Sí > ¿Qué hace para ahorrar Agua en su casa?
    private String queHaceParaAhorraAguaCasa;
    private String queHaceParaAhorraAguaCasaOtros;

    //47.	¿Recolecta agua de lluvia?
    private String recolectaAguaLluvia;

    //47.1 Si la respuesta es Sí -> ¿Para qué se usa el agua de lluvia?
    private String paraQueRecolectaAguaLluvia;
    private String paraQueRecolectaAguaLluviaOtros;

    //48.	¿Hay problemas con el abastecimiento de agua en este barrio?
    private String hayProblemasAbastecimientoAgua;

    //48.1 Si la respuesta es Sí > ¿Cada cuanto se les va el agua?
    private String cadaCuantoSeVaelAgua;
    private String cadaCuantoSeVaelAguaOtros;

    //48.2 ¿Cuántas horas al día se les va?
    private String cuantasHorasAlDiaSeVaelAgua;

    //49.	¿Qué utilizan para cocinar?
    private String queUsanParaCocinar;
    private String queUsanParaCocinarOtros;

    //49.1 Si mencionan leña: ¿De dónde consiguen la leña para cocinar?
    private String dondeConsiguenLeña;
    private String dondeConsiguenLeñaOtros;

    //49.2 ¿En qué parte de la casa cocina con leña?
    private String queParteCasaCocinanLeña;

    //50.	 ¿Qué hacen con la basura del hogar?
    private String queHacenBasuraHogar;
    private String queHacenBasuraHogarOtros;

    //51. ¿Separa los plásticos tales como galones y botellas plásticas de la basura de su hogar?
    private String separaPlasticosBasuraHogar;

    //51.1	Si la respuesta es Sí > ¿Qué hacen con los plásticos que separa?
    private String separaPlasticosBasuraHogar_queHacen;
    private String separaPlasticosBasuraHogarQueHacen;
    private String separaPlasticosBasuraHogar_queHacen_Otros;
    private String separaPlasticosBasuraHogarQueHacenOtros;
    //52.	¿En los últimos seis meses, usted o alguien de su casa ha participado en alguna actividad para el cuido del medio ambiente en su barrio?
    private String ultimos6MesesParticipadoActividadCambioClimatico;
    private String ultimos6MesesParticipadoACCL;

    //52.1 Si la respuesta es Sí -> Me podría decir, ¿En qué actividades han participado?
    private String enQueActividadHaParticipado;
    private String enQueActividadHaParticipadoOtros;

    //53.	¿La casa en la que vive es de su propiedad o es alquilada?
    private String viviendaEsPropiaAlquilada;
    private String viviendaEsPropiaAlquiladaOtros;

    //54.	Clasificación de la vivienda
    private String tipoVivienda;
    private String tipoViviendaOtros;

    //55.	¿La vivienda cuenta con servicio de energía eléctrica?
    private String tieneEnergiaElectrica;

    //55.1 ¿Tiene medidor de energía?
    private String tieneMedidorEnergiaElectrica;

    //56.	¿La vivienda cuenta con servicio de agua potable?
    private String tieneAguaPotable;

    //55.1 ¿Tiene medidor de agua?
    private String tieneMedidorAguaPotable;

    //57.	¿Tiene pozo en su propiedad?
    private String tienePozo;

    //58.	¿Cuál es la opción de saneamiento que posee en su vivienda?
    private String tipoSaneamiento;
    private String tipoSaneamientoOtros;

    //59.	¿En el barrio tienen sistema de alcantarillado sanitario?
    private String barrioTieneAlcantarillado;

    //59.1 Si la respuesta es Sí > ¿Su vivienda está conectada al sistema de alcantarillado
    private String viviendaConectadaAlcantarillado;

    //60.	¿Tiene sumidero en su propiedad?
    private String tieneSumidero;

    //61.	¿Aproximadamente, qué distancia hay entre su casa y el cauce más cercano?
    private String distanciaCauseCasa;

    //62.	¿Qué material de construcción predomina en la vivienda?
    private String materialParedes;
    private String materialPiso;
    private String materialTecho;
    private String otroMaterialParedes;
    private String otroMaterialPiso;
    private String otroMaterialTecho;

    //63. Observar si la calle de la cuadra de la vivienda es:
    private String calleCuadraVivienda;

    //64.	Barrio
    private int barrio;

    //65.	¿Estaría dispuesta a participar en una actividad educativa sobre el cambio climático y las enfermedades transmitidas por los zancudos?
    private String dispuestoParticiparActividad;


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cuestionario", nullable = true, length = 200)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public String getCodigoEncuesta() {
  return codigoEncuesta;
 }
    public void setCodigoEncuesta(String codigoEncuesta) {
  this.codigoEncuesta = codigoEncuesta;
 }

    @Column(name = "fecha_cuestionario", nullable = true)
    public Date getFechaCuestionario() {
        return fechaCuestionario;
    }
    public void setFechaCuestionario(Date fechaCuestionario) {
        this.fechaCuestionario = fechaCuestionario;
    }

    @Column(name = "P1_codigoVivienda", nullable = true, length = 20)
    public String getCodigoVivienda() {
  return codigoVivienda;
 }
    public void setCodigoVivienda(String codigoVivienda) {
  this.codigoVivienda = codigoVivienda;
 }

    @Column(name = "P1_latitud", nullable = true)
    public Double getLatitud() {
  return latitud;
 }
    public void setLatitud(Double latitud) {
  this.latitud = latitud;
 }

    @Column(name = "P1_longitud", nullable = true)
    public Double getLongitud() {
  return longitud;
 }
    public void setLongitud(Double longitud) {
  this.longitud = longitud;
 }

    @Column(name = "P2_quienContesta", nullable = true)
    public String getQuienContesta() {
  return quienContesta;
 }
    public void setQuienContesta(String quienContesta) {
  this.quienContesta = quienContesta;
 }

    @Column(name = "P2_quienContestaOtro", nullable = true, length = 20)
    public String getQuienContestaOtro() {
  return quienContestaOtro;
 }
    public void setQuienContestaOtro(String quienContestaOtro) {
  this.quienContestaOtro = quienContestaOtro;
 }

    @Column(name = "P3_edadContesta", nullable = true, length = 3)
    public int getEdadContest() {
  return edadContest;
 }
    public void setEdadContest(int edadContest) {
  this.edadContest = edadContest;
 }

    @Column(name = "P4_generoObservacion", nullable = true, length = 2)
    public String getGeneroObservacion() {
  return generoObservacion;
 }
    public void setGeneroObservacion(String generoObservacion) {
  this.generoObservacion = generoObservacion;
 }

    @Column(name = "P5_ultimoGradoAprobado", nullable = true, length = 20)
    public String getUltimoGradoAprobado() {
  return ultimoGradoAprobado;
 }
    public void setUltimoGradoAprobado(String ultimoGradoAprobado) {
  this.ultimoGradoAprobado = ultimoGradoAprobado;
 }

    @Column(name = "P6_aqueSeDedica", nullable = true, length = 5)
    public String getAqueSeDedica() {
  return aqueSeDedica;
 }
    public void setAqueSeDedica(String aqueSeDedica) {
  this.aqueSeDedica = aqueSeDedica;
 }

    @Column(name = "P6_aqueSeDedicaOtros", nullable = true, length = 30)
    public String getAqueSeDedicaOtros() {
  return aqueSeDedicaOtros;
 }
    public void setAqueSeDedicaOtros(String aqueSeDedicaOtros) {
  this.aqueSeDedicaOtros = aqueSeDedicaOtros;
 }

    @Column(name = "P7_cuantasPersonasViven", nullable = true, length = 5)
    public int getCuantasPersonasViven() {
  return cuantasPersonasViven;
 }
    public void setCuantasPersonasViven(int cuantasPersonasViven) {
  this.cuantasPersonasViven = cuantasPersonasViven;
 }

    @Column(name = "P8_edadesFemenino", nullable = true, length = 80)
    public String getEdadesFemenino() {
  return edadesFemenino;
 }
    public void setEdadesFemenino(String edadesFemenino) {
  this.edadesFemenino = edadesFemenino;
 }

    @Column(name = "P9_edadesMasculino", nullable = true, length = 80)
    public String getEdadesMasculino() {
  return edadesMasculino;
 }
    public void setEdadesMasculino(String edadesMasculino) {
  this.edadesMasculino = edadesMasculino;
    }


    @Column(name = "P10_escuchadoCambioClimatico", nullable = true, length = 5)
    public String getEscuchadoCambioClimatico() {
  return escuchadoCambioClimatico;
 }
    public void setEscuchadoCambioClimatico(String escuchadoCambioClimatico) {
  this.escuchadoCambioClimatico = escuchadoCambioClimatico;
 }

    @Column(name = "P11_queTanInformadoCambioClimatico", nullable = true, length = 5)
    public String getQueTanInformadoCambioClimatico() {
  return queTanInformadoCambioClimatico;
 }
    public void setQueTanInformadoCambioClimatico(String queTanInformadoCambioClimatico) {
  this.queTanInformadoCambioClimatico = queTanInformadoCambioClimatico;
 }

    @Column(name = "P12_comoseHaInformado", nullable = true, length = 5)
    public String getComoseHaInformado() {
  return comoseHaInformado;
 }
    public void setComoseHaInformado(String comoseHaInformado) {
  this.comoseHaInformado = comoseHaInformado;
 }

    @Column(name = "P12_comoseHaInformadoOtros", nullable = true, length = 30)
    public String getComoseHaInformadoOtros() {
  return comoseHaInformadoOtros;
 }
    public void setComoseHaInformadoOtros(String comoseHaInformadoOtros) {
  this.comoseHaInformadoOtros = comoseHaInformadoOtros;
 }

    @Column(name = "P13_porqueOcurreCambioclimatico", nullable = true, length = 5)
    public String getPorqueOcurreCambioclimatico() {
  return porqueOcurreCambioclimatico;
 }
    public void setPorqueOcurreCambioclimatico(String porqueOcurreCambioclimatico) {
  this.porqueOcurreCambioclimatico = porqueOcurreCambioclimatico;
 }

    @Column(name = "P13_porqueOcurreCambioclimaticoOtros", nullable = true, length = 30)
    public String getPorqueOcurreCambioclimaticoOtros() {
  return porqueOcurreCambioclimaticoOtros;
 }
    public void setPorqueOcurreCambioclimaticoOtros(String porqueOcurreCambioclimaticoOtros) {
  this.porqueOcurreCambioclimaticoOtros = porqueOcurreCambioclimaticoOtros;
 }

    @Column(name = "P14_porqueProblemasOcurreCambioclimatico", nullable = true, length = 5)
    public String getPorqueProblemasOcurreCambioclimatico() {
  return porqueProblemasOcurreCambioclimatico;
 }
    public void setPorqueProblemasOcurreCambioclimatico(String porqueProblemasOcurreCambioclimatico) {
  this.porqueProblemasOcurreCambioclimatico = porqueProblemasOcurreCambioclimatico;
 }

    @Column(name = "P14_porqueProblemasOcurreCambioclimaticoOtros", nullable = true, length = 30)
    public String getPorqueProblemasOcurreCambioclimaticoOtros() {
  return porqueProblemasOcurreCambioclimaticoOtros;
 }
    public void setPorqueProblemasOcurreCambioclimaticoOtros(String porqueProblemasOcurreCambioclimaticoOtros) {
  this.porqueProblemasOcurreCambioclimaticoOtros = porqueProblemasOcurreCambioclimaticoOtros;
 }


    @Column(name = "P15_responsableAccionesReducirDanos", nullable = true, length = 5)
    public String getResponsableAccionesReducirDanos() {
  return responsableAccionesReducirDanos;
 }
    public void setResponsableAccionesReducirDanos(String responsableAccionesReducirDanos) {
  this.responsableAccionesReducirDanos = responsableAccionesReducirDanos;
 }

    @Column(name = "P15_responsableAccionesReducirDanosOtros", nullable = true, length = 20)
    public String getResponsableAccionesReducirDanosOtros() {
  return responsableAccionesReducirDanosOtros;
 }
    public void setResponsableAccionesReducirDanosOtros(String responsableAccionesReducirDanosOtros) {
  this.responsableAccionesReducirDanosOtros = responsableAccionesReducirDanosOtros;
 }

    @Column(name = "P16_consideraCambioCliAfectaSalud", nullable = true, length = 20)
    public String getConsideraCambioCliAfectaSalud() {
  return consideraCambioCliAfectaSalud;
 }
    public void setConsideraCambioCliAfectaSalud(String consideraCambioCliAfectaSalud) {
  this.consideraCambioCliAfectaSalud = consideraCambioCliAfectaSalud;
 }

    @Column(name = "P17_enfermedadesCausadasCambioClimatico", nullable = true, length = 20)
    public String getEnfermedadesCausadasCambioClimatico() {
     return enfermedadesCausadasCambioClimatico;
    }
    public void setEnfermedadesCausadasCambioClimatico(String enfermedadesCausadasCambioClimatico) {
  this.enfermedadesCausadasCambioClimatico = enfermedadesCausadasCambioClimatico;
 }

    @Column(name = "P17_enfermedadesCausadasCambioClimaticoOtros", nullable = true, length = 20)
    public String getEnfermedadesCausadasCambioClimaticoOtros() {
  return enfermedadesCausadasCambioClimaticoOtros;
 }
    public void setEnfermedadesCausadasCambioClimaticoOtros(String enfermedadesCausadasCambioClimaticoOtros) {
  this.enfermedadesCausadasCambioClimaticoOtros = enfermedadesCausadasCambioClimaticoOtros;
 }

    @Column(name = "P18_riesgoCasaEnfermarseDengue", nullable = true, length = 20)
    public String getRiesgoCasaEnfermarseDengue() {
  return riesgoCasaEnfermarseDengue;
 }
    public void setRiesgoCasaEnfermarseDengue(String riesgoCasaEnfermarseDengue) {
  this.riesgoCasaEnfermarseDengue = riesgoCasaEnfermarseDengue;
 }

    @Column(name = "P19_riesgoBarrioEnfermarseDengue", nullable = true, length = 20)
    public String getRiesgoBarrioEnfermarseDengue() {
  return riesgoBarrioEnfermarseDengue;
 }
    public void setRiesgoBarrioEnfermarseDengue(String riesgoBarrioEnfermarseDengue) {
  this.riesgoBarrioEnfermarseDengue = riesgoBarrioEnfermarseDengue;
 }

    @Column(name = "P20_consideraCambCliAumentaDengueBarrio", nullable = true, length = 20)
    public String getConsideraCambCliAumentaDengueBarrio() {
  return consideraCambCliAumentaDengueBarrio;
 }
    public void setConsideraCambCliAumentaDengueBarrio(String consideraCambCliAumentaDengueBarrio) {
  this.consideraCambCliAumentaDengueBarrio = consideraCambCliAumentaDengueBarrio;
 }

    @Column(name = "P21_consideraZancudosVivirLugarFrio", nullable = true, length = 20)
    public String getConsideraZancudosVivirLugarFrio() {
  return consideraZancudosVivirLugarFrio;
 }
    public void setConsideraZancudosVivirLugarFrio(String consideraZancudosVivirLugarFrio) {
  this.consideraZancudosVivirLugarFrio = consideraZancudosVivirLugarFrio;
 }

    @Column(name = "P22_consideraZancudosVivirLugarCalor", nullable = true, length = 20)
    public String getConsideraZancudosVivirLugarCalor() {
  return consideraZancudosVivirLugarCalor;
 }
    public void setConsideraZancudosVivirLugarCalor(String consideraZancudosVivirLugarCalor) {
  this.consideraZancudosVivirLugarCalor = consideraZancudosVivirLugarCalor;
 }

    @Column(name = "P23_hayZancudosBarrio", nullable = true, length = 20)
    public String getHayZancudosBarrio() {
  return hayZancudosBarrio;
 }
    public void setHayZancudosBarrio(String hayZancudosBarrio) {
  this.hayZancudosBarrio = hayZancudosBarrio;
 }

    @Column(name = "P24_hayZancudosTrabajo", nullable = true, length = 20)
    public String getHayZancudosTrabajo() {
  return hayZancudosTrabajo;
 }
    public void setHayZancudosTrabajo(String hayZancudosTrabajo) {
  this.hayZancudosTrabajo = hayZancudosTrabajo;
 }

    @Column(name = "P25_hayZancudosEscuela", nullable = true, length = 20)
    public String getHayZancudosEscuela() {
  return hayZancudosEscuela;
 }
    public void setHayZancudosEscuela(String hayZancudosEscuela) {
  this.hayZancudosEscuela = hayZancudosEscuela;
 }

    @Column(name = "P26_hayZancudosCasa", nullable = true, length = 20)
    public String getHayZancudosCasa() {
  return hayZancudosCasa;
 }
    public void setHayZancudosCasa(String hayZancudosCasa) {
  this.hayZancudosCasa = hayZancudosCasa;
 }

    @Column(name = "P27_sabeIdentificarCriaderosCasa", nullable = true, length = 20)
    public String getSabeIdentificarCriaderosCasa() {
  return sabeIdentificarCriaderosCasa;
 }
    public void setSabeIdentificarCriaderosCasa(String sabeIdentificarCriaderosCasa) {
  this.sabeIdentificarCriaderosCasa = sabeIdentificarCriaderosCasa;
 }

    @Column(name = "P27_1_alguienBuscaEliminaCriaderosCasa", nullable = true, length = 20)
    public String getAlguienBuscaEliminaCriaderosCasa() {
  return alguienBuscaEliminaCriaderosCasa;
 }
    public void setAlguienBuscaEliminaCriaderosCasa(String alguienBuscaEliminaCriaderosCasa) {
  this.alguienBuscaEliminaCriaderosCasa = alguienBuscaEliminaCriaderosCasa;
 }

 @Column(name = "P27_2_cadaCuantoBuscaEliminaCriaderosCasa", nullable = true, length = 20)
 public String getCadaCuantoBuscaEliminaCriaderosCasa() {
  return cadaCuantoBuscaEliminaCriaderosCasa;
 }
 public void setCadaCuantoBuscaEliminaCriaderosCasa(String cadaCuantoBuscaEliminaCriaderosCasa) {
  this.cadaCuantoBuscaEliminaCriaderosCasa = cadaCuantoBuscaEliminaCriaderosCasa;
 }

 @Column(name = "P28_ultimaVezAbateMinsa", nullable = true, length = 20)
 public String getUltimaVezAbateMinsa() {
  return ultimaVezAbateMinsa;
 }
 public void setUltimaVezAbateMinsa(String ultimaVezAbateMinsa) {
  this.ultimaVezAbateMinsa = ultimaVezAbateMinsa;
 }

 @Column(name = "P29_ultimaVezFumigaMinsa", nullable = true, length = 20)
 public String getUltimaVezFumigaMinsa() {
  return ultimaVezFumigaMinsa;
 }
 public void setUltimaVezFumigaMinsa(String ultimaVezFumigaMinsa) {
  this.ultimaVezFumigaMinsa = ultimaVezFumigaMinsa;
 }

 @Column(name = "P30_lePreocupaCambioClimatico", nullable = true, length = 20)
 public String getLePreocupaCambioClimatico() {
  return lePreocupaCambioClimatico;
 }
 public void setLePreocupaCambioClimatico(String lePreocupaCambioClimatico) {
  this.lePreocupaCambioClimatico = lePreocupaCambioClimatico;
 }

 @Column(name = "P31_prevenirDanosCambioClimatico", nullable = true, length = 20)
 public String getPrevenirDanosCambioClimatico() {
  return prevenirDanosCambioClimatico;
 }
 public void setPrevenirDanosCambioClimatico(String prevenirDanosCambioClimatico) {
  this.prevenirDanosCambioClimatico = prevenirDanosCambioClimatico;
 }

 @Column(name = "P31_prevenirDanosCambioClimaticoOtros", nullable = true, length = 20)
 public String getPrevenirDanosCambioClimaticoOtros() {
  return prevenirDanosCambioClimaticoOtros;
 }
 public void setPrevenirDanosCambioClimaticoOtros(String prevenirDanosCambioClimaticoOtros) {
  this.prevenirDanosCambioClimaticoOtros = prevenirDanosCambioClimaticoOtros;
 }

 @Column(name = "P32_seSientePreparadoEnfrentarSituaciones", nullable = true, length = 20)
 public String getSeSientePreparadoEnfrentarSituaciones() {
  return seSientePreparadoEnfrentarSituaciones;
 }
 public void setSeSientePreparadoEnfrentarSituaciones(String seSientePreparadoEnfrentarSituaciones) {
  this.seSientePreparadoEnfrentarSituaciones = seSientePreparadoEnfrentarSituaciones;
 }

 @Column(name = "P33_afectaraSuVidaNegativamenteCambioClimatico", nullable = true, length = 20)
 public String getAfectaraSuVidaNegativamenteCambioClimatico() {
  return afectaraSuVidaNegativamenteCambioClimatico;
 }
 public void setAfectaraSuVidaNegativamenteCambioClimatico(String afectaraSuVidaNegativamenteCambioClimatico) {
  this.afectaraSuVidaNegativamenteCambioClimatico = afectaraSuVidaNegativamenteCambioClimatico;
 }

 @Column(name = "P34_afectaNegativamenteGeneracionCambioClimatico", nullable = true, length = 20)
 public String getAfectaraNegativamenteGeneracionCambioClimatico() {
  return afectaraNegativamenteGeneracionCambioClimatico;
 }
 public void setAfectaraNegativamenteGeneracionCambioClimatico(String afectaraNegativamenteGeneracionCambioClimatico) {
  this.afectaraNegativamenteGeneracionCambioClimatico = afectaraNegativamenteGeneracionCambioClimatico;
 }

 @Column(name = "P35_riesgoLluviasCausanDañoCasa", nullable = true, length = 20)
 public String getRiesgoLluviasCausanDañoCasa() {
  return riesgoLluviasCausanDañoCasa;
 }
 public void setRiesgoLluviasCausanDañoCasa(String riesgoLluviasCausanDañoCasa) {
  this.riesgoLluviasCausanDañoCasa = riesgoLluviasCausanDañoCasa;
 }

 @Column(name = "P36_consideraCambioCliAfectaNegativamenteBarrio", nullable = true, length = 20)
 public String getConsideraCambioCliAfectaNegativamenteBarrio() {
  return consideraCambioCliAfectaNegativamenteBarrio;
 }
 public void setConsideraCambioCliAfectaNegativamenteBarrio(String consideraCambioCliAfectaNegativamenteBarrio) {
  this.consideraCambioCliAfectaNegativamenteBarrio = consideraCambioCliAfectaNegativamenteBarrio;
 }

 @Column(name = "P37_queTanPreparadoComunidadEnfrentarSituaciones", nullable = true, length = 20)
 public String getQueTanPreparadoComunidadEnfrentarSituaciones() {
  return queTanPreparadoComunidadEnfrentarSituaciones;
 }
 public void setQueTanPreparadoComunidadEnfrentarSituaciones(String queTanPreparadoComunidadEnfrentarSituaciones) {
  this.queTanPreparadoComunidadEnfrentarSituaciones = queTanPreparadoComunidadEnfrentarSituaciones;
 }

 @Column(name = "P38_ultimaVezInundoBarrio", nullable = true, length = 20)
 public String getUltimaVezInundoBarrio() {
  return ultimaVezInundoBarrio;
 }
 public void setUltimaVezInundoBarrio(String ultimaVezInundoBarrio) {
  this.ultimaVezInundoBarrio = ultimaVezInundoBarrio;
 }

 @Column(name = "P39_ultimaVezIncendioBarrio", nullable = true, length = 20)
 public String getUltimaVezIncendioBarrio() {
  return ultimaVezIncendioBarrio;
 }
 public void setUltimaVezIncendioBarrio(String ultimaVezIncendioBarrio) {
  this.ultimaVezIncendioBarrio = ultimaVezIncendioBarrio;
 }

 @Column(name = "P39_1_ultimaVezIncendioBarrioCausas", nullable = true, length = 20)
 public String getUltimaVezIncendioBarrioCausas() {
  return ultimaVezIncendioBarrioCausas;
 }
 public void setUltimaVezIncendioBarrioCausas(String ultimaVezIncendioBarrioCausas) {
  this.ultimaVezIncendioBarrioCausas = ultimaVezIncendioBarrioCausas;
 }

 @Column(name = "P39_1_ultimaVezIncendioBarrioCausasOtros", nullable = true, length = 20)
 public String getUltimaVezIncendioBarrioCausasOtros() {
  return ultimaVezIncendioBarrioCausasOtros;
 }
 public void setUltimaVezIncendioBarrioCausasOtros(String ultimaVezIncendioBarrioCausasOtros) {
  this.ultimaVezIncendioBarrioCausasOtros = ultimaVezIncendioBarrioCausasOtros;
 }

 @Column(name = "P40_tiempoVivirBarrio", nullable = true, length = 20)
 public String getTiempoVivirBarrio() {
  return tiempoVivirBarrio;
 }
 public void setTiempoVivirBarrio(String tiempoVivirBarrio) {
  this.tiempoVivirBarrio = tiempoVivirBarrio;
 }

 @Column(name = "P41_ultimos5AñosCambioClima", nullable = true, length = 20)
 public String getUltimos5AñosCambioClima() {
  return ultimos5AñosCambioClima;
 }
 public void setUltimos5AñosCambioClima(String ultimos5AñosCambioClima) {
  this.ultimos5AñosCambioClima = ultimos5AñosCambioClima;
 }

 @Column(name = "P41_1_ultimos5AñosCambioClimaMasCalorFrios", nullable = true, length = 20)
 public String getUltimos5AñosCambioClimaMasCalorFrios() {
  return ultimos5AñosCambioClimaMasCalorFrios;
 }
 public void setUltimos5AñosCambioClimaMasCalorFrios(String ultimos5AñosCambioClimaMasCalorFrios) {
  this.ultimos5AñosCambioClimaMasCalorFrios = ultimos5AñosCambioClimaMasCalorFrios;
 }

 @Column(name = "P42_ultimos5AñosMasMenosArbolesBarrio", nullable = true, length = 20)
 public String getUltimos5AñosMasMenosArbolesBarrio() {
  return ultimos5AñosMasMenosArbolesBarrio;
 }
 public void setUltimos5AñosMasMenosArbolesBarrio(String ultimos5AñosMasMenosArbolesBarrio) {
  this.ultimos5AñosMasMenosArbolesBarrio = ultimos5AñosMasMenosArbolesBarrio;
 }

 @Column(name = "P43_ultimos5AñosAbastecimientoAgua", nullable = true, length = 20)
 public String getUltimos5AñosAbastecimientoAgua() {
  return ultimos5AñosAbastecimientoAgua;
 }
 public void setUltimos5AñosAbastecimientoAgua(String ultimos5AñosAbastecimientoAgua) {
  this.ultimos5AñosAbastecimientoAgua = ultimos5AñosAbastecimientoAgua;
 }

 @Column(name = "P44_lugarProduceMasContaminacion", nullable = true, length = 20)
 public String getLugarProduceMasContaminacion() {
  return lugarProduceMasContaminacion;
 }
 public void setLugarProduceMasContaminacion(String lugarProduceMasContaminacion) {
  this.lugarProduceMasContaminacion = lugarProduceMasContaminacion;
 }

 @Column(name = "P44_lugarProduceMasContaminacionOtros", nullable = true, length = 20)
 public String getLugarProduceMasContaminacionOtros() {
  return lugarProduceMasContaminacionOtros;
 }
 public void setLugarProduceMasContaminacionOtros(String lugarProduceMasContaminacionOtros) {
  this.lugarProduceMasContaminacionOtros = lugarProduceMasContaminacionOtros;
 }

 @Column(name = "P45_ahorraEnergiaCasa", nullable = true, length = 20)
 public String getAhorraEnergiaCasa() {
  return ahorraEnergiaCasa;
 }
 public void setAhorraEnergiaCasa(String ahorraEnergiaCasa) {
  this.ahorraEnergiaCasa = ahorraEnergiaCasa;
 }

 @Column(name = "P45_1_queHaceParaAhorraEnergiaCasa", nullable = true, length = 20)
 public String getQueHaceParaAhorraEnergiaCasa() {
  return queHaceParaAhorraEnergiaCasa;
 }
 public void setQueHaceParaAhorraEnergiaCasa(String queHaceParaAhorraEnergiaCasa) {
  this.queHaceParaAhorraEnergiaCasa = queHaceParaAhorraEnergiaCasa;
 }

 @Column(name = "P45_1_queHaceParaAhorraEnergiaCasaOtros", nullable = true, length = 20)
 public String getQueHaceParaAhorraEnergiaCasaOtros() {
  return queHaceParaAhorraEnergiaCasaOtros;
 }
 public void setQueHaceParaAhorraEnergiaCasaOtros(String queHaceParaAhorraEnergiaCasaOtros) {
  this.queHaceParaAhorraEnergiaCasaOtros = queHaceParaAhorraEnergiaCasaOtros;
 }

 @Column(name = "P46_ahorraAguaCasa", nullable = true, length = 20)
 public String getAhorraAguaCasa() {
  return ahorraAguaCasa;
 }
 public void setAhorraAguaCasa(String ahorraAguaCasa) {
  this.ahorraAguaCasa = ahorraAguaCasa;
 }

 @Column(name = "P46_1_queHaceParaAhorraAguaCasa", nullable = true, length = 20)
 public String getQueHaceParaAhorraAguaCasa() {
  return queHaceParaAhorraAguaCasa;
 }
 public void setQueHaceParaAhorraAguaCasa(String queHaceParaAhorraAguaCasa) {
  this.queHaceParaAhorraAguaCasa = queHaceParaAhorraAguaCasa;
 }

 @Column(name = "P46_1_queHaceParaAhorraAguaCasaOtros", nullable = true, length = 20)
 public String getQueHaceParaAhorraAguaCasaOtros() {
  return queHaceParaAhorraAguaCasaOtros;
 }
 public void setQueHaceParaAhorraAguaCasaOtros(String queHaceParaAhorraAguaCasaOtros) {
  this.queHaceParaAhorraAguaCasaOtros = queHaceParaAhorraAguaCasaOtros;
 }

 @Column(name = "P47_recolectaAguaLluvia", nullable = true, length = 20)
 public String getRecolectaAguaLluvia() {
  return recolectaAguaLluvia;
 }
 public void setRecolectaAguaLluvia(String recolectaAguaLluvia) {
  this.recolectaAguaLluvia = recolectaAguaLluvia;
 }

 @Column(name = "P47_1_paraQueRecolectaAguaLluvia", nullable = true, length = 20)
 public String getParaQueRecolectaAguaLluvia() {
  return paraQueRecolectaAguaLluvia;
 }
 public void setParaQueRecolectaAguaLluvia(String paraQueRecolectaAguaLluvia) {
  this.paraQueRecolectaAguaLluvia = paraQueRecolectaAguaLluvia;
 }

 @Column(name = "P47_1_paraQueRecolectaAguaLluviaOtros", nullable = true, length = 20)
 public String getParaQueRecolectaAguaLluviaOtros() {
  return paraQueRecolectaAguaLluviaOtros;
 }
 public void setParaQueRecolectaAguaLluviaOtros(String paraQueRecolectaAguaLluviaOtros) {
  this.paraQueRecolectaAguaLluviaOtros = paraQueRecolectaAguaLluviaOtros;
 }

 @Column(name = "P48_hayProblemasAbastecimientoAgua", nullable = true, length = 20)
 public String getHayProblemasAbastecimientoAgua() {
  return hayProblemasAbastecimientoAgua;
 }
 public void setHayProblemasAbastecimientoAgua(String hayProblemasAbastecimientoAgua) {
  this.hayProblemasAbastecimientoAgua = hayProblemasAbastecimientoAgua;
 }

 @Column(name = "P48_1_cadaCuantoSeVaelAgua", nullable = true, length = 20)
 public String getCadaCuantoSeVaelAgua() {
  return cadaCuantoSeVaelAgua;
 }
 public void setCadaCuantoSeVaelAgua(String cadaCuantoSeVaelAgua) {
  this.cadaCuantoSeVaelAgua = cadaCuantoSeVaelAgua;
 }

 @Column(name = "P48_1_cadaCuantoSeVaelAguaOtros", nullable = true, length = 20)
 public String getCadaCuantoSeVaelAguaOtros() {
  return cadaCuantoSeVaelAguaOtros;
 }
 public void setCadaCuantoSeVaelAguaOtros(String cadaCuantoSeVaelAguaOtros) {
  this.cadaCuantoSeVaelAguaOtros = cadaCuantoSeVaelAguaOtros;
 }

 @Column(name = "P48_2_cuantasHorasAlDiaSeVaelAgua", nullable = true, length = 20)
 public String getCuantasHorasAlDiaSeVaelAgua() {
  return cuantasHorasAlDiaSeVaelAgua;
 }
 public void setCuantasHorasAlDiaSeVaelAgua(String cuantasHorasAlDiaSeVaelAgua) {
  this.cuantasHorasAlDiaSeVaelAgua = cuantasHorasAlDiaSeVaelAgua;
 }

 @Column(name = "P49_queUsanParaCocinar", nullable = true, length = 20)
 public String getQueUsanParaCocinar() {
  return queUsanParaCocinar;
 }
 public void setQueUsanParaCocinar(String queUsanParaCocinar) {
  this.queUsanParaCocinar = queUsanParaCocinar;
 }

 @Column(name = "P49_queUsanParaCocinarOtros", nullable = true, length = 20)
 public String getQueUsanParaCocinarOtros() {
  return queUsanParaCocinarOtros;
 }
 public void setQueUsanParaCocinarOtros(String queUsanParaCocinarOtros) {
  this.queUsanParaCocinarOtros = queUsanParaCocinarOtros;
 }

 @Column(name = "P49_1_dondeConsiguenLeña", nullable = true, length = 20)
 public String getDondeConsiguenLeña() {
  return dondeConsiguenLeña;
 }
 public void setDondeConsiguenLeña(String dondeConsiguenLeña) {
  this.dondeConsiguenLeña = dondeConsiguenLeña;
 }

 @Column(name = "P49_1_dondeConsiguenLeñaOtros", nullable = true, length = 20)
 public String getDondeConsiguenLeñaOtros() {
  return dondeConsiguenLeñaOtros;
 }
 public void setDondeConsiguenLeñaOtros(String dondeConsiguenLeñaOtros) {
  this.dondeConsiguenLeñaOtros = dondeConsiguenLeñaOtros;
 }

 @Column(name = "P49_2_queParteCasaCocinanLeña", nullable = true, length = 20)
 public String getQueParteCasaCocinanLeña() {
  return queParteCasaCocinanLeña;
 }
 public void setQueParteCasaCocinanLeña(String queParteCasaCocinanLeña) {
  this.queParteCasaCocinanLeña = queParteCasaCocinanLeña;
 }

 @Column(name = "P50_queHacenBasuraHogar", nullable = true, length = 20)
 public String getQueHacenBasuraHogar() {
  return queHacenBasuraHogar;
 }
 public void setQueHacenBasuraHogar(String queHacenBasuraHogar) {
  this.queHacenBasuraHogar = queHacenBasuraHogar;
 }

 @Column(name = "P50_queHacenBasuraHogarOtros", nullable = true, length = 20)
 public String getQueHacenBasuraHogarOtros() {
  return queHacenBasuraHogarOtros;
 }
 public void setQueHacenBasuraHogarOtros(String queHacenBasuraHogarOtros) {
  this.queHacenBasuraHogarOtros = queHacenBasuraHogarOtros;
 }

 @Column(name = "P51_separaPlasticosBasuraHogar", nullable = true, length = 20)
 public String getSeparaPlasticosBasuraHogar() {
  return separaPlasticosBasuraHogar;
 }
 public void setSeparaPlasticosBasuraHogar(String separaPlasticosBasuraHogar) {
  this.separaPlasticosBasuraHogar = separaPlasticosBasuraHogar;
 }

 @Column(name = "P51_1_separaPlasticosBasuraHogar_QueHacen", nullable = true, length = 20)
 public String getSeparaPlasticosBasuraHogar_queHacen() {
  return separaPlasticosBasuraHogar_queHacen;
 }
 public void setSeparaPlasticosBasuraHogar_queHacen(String separaPlasticosBasuraHogar_queHacen) {
  this.separaPlasticosBasuraHogar_queHacen = separaPlasticosBasuraHogar_queHacen;
 }

 @Column(name = "P51_1_queHacenConPlasticosSeparados_Otros", nullable = true, length = 20)
 public String getSeparaPlasticosBasuraHogar_queHacen_Otros() {
  return separaPlasticosBasuraHogar_queHacen_Otros;
 }
 public void setSeparaPlasticosBasuraHogar_queHacen_Otros(String separaPlasticosBasuraHogar_queHacen_Otros) {
  this.separaPlasticosBasuraHogar_queHacen_Otros = separaPlasticosBasuraHogar_queHacen_Otros;
 }

 @Column(name = "P52_participadoActividadCambioClimatico", nullable = true, length = 20)
 public String getUltimos6MesesParticipadoActividadCambioClimatico() {
  return ultimos6MesesParticipadoActividadCambioClimatico;
 }
 public void setUltimos6MesesParticipadoActividadCambioClimatico(String ultimos6MesesParticipadoActividadCambioClimatico) {
  this.ultimos6MesesParticipadoActividadCambioClimatico = ultimos6MesesParticipadoActividadCambioClimatico;
 }

 @Column(name = "P52_1_enQueActividadHaParticipado", nullable = true, length = 20)
 public String getEnQueActividadHaParticipado() {
  return enQueActividadHaParticipado;
 }
 public void setEnQueActividadHaParticipado(String enQueActividadHaParticipado) {
  this.enQueActividadHaParticipado = enQueActividadHaParticipado;
 }

 @Column(name = "P52_1_enQueActividadHaParticipadoOtros", nullable = true, length = 20)
 public String getEnQueActividadHaParticipadoOtros() {
  return enQueActividadHaParticipadoOtros;
 }
 public void setEnQueActividadHaParticipadoOtros(String enQueActividadHaParticipadoOtros) {
  this.enQueActividadHaParticipadoOtros = enQueActividadHaParticipadoOtros;
 }

 @Column(name = "P53_viviendaEsPropiaAlquilada", nullable = true, length = 20)
 public String getViviendaEsPropiaAlquilada() {
  return viviendaEsPropiaAlquilada;
 }
 public void setViviendaEsPropiaAlquilada(String viviendaEsPropiaAlquilada) {
  this.viviendaEsPropiaAlquilada = viviendaEsPropiaAlquilada;
 }

 @Column(name = "P53_viviendaEsPropiaAlquiladaOtros", nullable = true, length = 20)
 public String getViviendaEsPropiaAlquiladaOtros() {
  return viviendaEsPropiaAlquiladaOtros;
 }
 public void setViviendaEsPropiaAlquiladaOtros(String viviendaEsPropiaAlquiladaOtros) {
  this.viviendaEsPropiaAlquiladaOtros = viviendaEsPropiaAlquiladaOtros;
 }

 @Column(name = "P54_tipoVivienda", nullable = true, length = 20)
 public String getTipoVivienda() {
  return tipoVivienda;
 }
 public void setTipoVivienda(String tipoVivienda) {
  this.tipoVivienda = tipoVivienda;
 }

 @Column(name = "P54_tipoViviendaOtros", nullable = true, length = 20)
 public String getTipoViviendaOtros() {
  return tipoViviendaOtros;
 }
 public void setTipoViviendaOtros(String tipoViviendaOtros) {
  this.tipoViviendaOtros = tipoViviendaOtros;
 }

 @Column(name = "P55_tieneEnergiaElectrica", nullable = true, length = 20)
 public String getTieneEnergiaElectrica() {
  return tieneEnergiaElectrica;
 }
 public void setTieneEnergiaElectrica(String tieneEnergiaElectrica) {
  this.tieneEnergiaElectrica = tieneEnergiaElectrica;
 }

 @Column(name = "P55_1_tieneMedidorEnergiaElectrica", nullable = true, length = 20)
 public String getTieneMedidorEnergiaElectrica() {
  return tieneMedidorEnergiaElectrica;
 }
 public void setTieneMedidorEnergiaElectrica(String tieneMedidorEnergiaElectrica) {
  this.tieneMedidorEnergiaElectrica = tieneMedidorEnergiaElectrica;
 }

 @Column(name = "P56_tieneAguaPotable", nullable = true, length = 20)
 public String getTieneAguaPotable() {
  return tieneAguaPotable;
 }
 public void setTieneAguaPotable(String tieneAguaPotable) {
  this.tieneAguaPotable = tieneAguaPotable;
 }

 @Column(name = "P56_1_tieneMedidorAguaPotable", nullable = true, length = 20)
 public String getTieneMedidorAguaPotable() {
  return tieneMedidorAguaPotable;
 }
 public void setTieneMedidorAguaPotable(String tieneMedidorAguaPotable) {
  this.tieneMedidorAguaPotable = tieneMedidorAguaPotable;
 }

 @Column(name = "P57_tienePozo", nullable = true, length = 20)
 public String getTienePozo() {
  return tienePozo;
 }
 public void setTienePozo(String tienePozo) {
  this.tienePozo = tienePozo;
 }

 @Column(name = "P58_tipoSaneamiento", nullable = true, length = 20)
 public String getTipoSaneamiento() {
  return tipoSaneamiento;
 }
 public void setTipoSaneamiento(String tipoSaneamiento) {
  this.tipoSaneamiento = tipoSaneamiento;
 }

 @Column(name = "P58_tipoSaneamientoOtros", nullable = true, length = 20)
 public String getTipoSaneamientoOtros() {
  return tipoSaneamientoOtros;
 }
 public void settipoSaneamientoOtros(String tipoSaneamientoOtros) {
  this.tipoSaneamientoOtros = tipoSaneamientoOtros;
 }

 @Column(name = "P59_barrioTieneAlcantarillado", nullable = true, length = 20)
 public String getBarrioTieneAlcantarillado() {
  return barrioTieneAlcantarillado;
 }
 public void setBarrioTieneAlcantarillado(String barrioTieneAlcantarillado) {
  this.barrioTieneAlcantarillado = barrioTieneAlcantarillado;
 }

 @Column(name = "P59_1_viviendaConectadaAlcantarillado", nullable = true, length = 20)
 public String getViviendaConectadaAlcantarillado() {
  return viviendaConectadaAlcantarillado;
 }
 public void setViviendaConectadaAlcantarillado(String viviendaConectadaAlcantarillado) {
  this.viviendaConectadaAlcantarillado = viviendaConectadaAlcantarillado;
 }

 @Column(name = "P60_tieneSumidero", nullable = true, length = 20)
 public String getTieneSumidero() {
  return tieneSumidero;
 }
 public void setTieneSumidero(String tieneSumidero) {
  this.tieneSumidero = tieneSumidero;
 }

 @Column(name = "P61_distanciaCauseCasa", nullable = true, length = 20)
 public String getDistanciaCauseCasa() {
  return distanciaCauseCasa;
 }
 public void setDistanciaCauseCasa(String distanciaCauseCasa) {
  this.distanciaCauseCasa = distanciaCauseCasa;
 }

 @Column(name = "P63_calleCuadraVivienda", nullable = true, length = 20)
 public String getCalleCuadraVivienda() {
  return calleCuadraVivienda;
 }
 public void setCalleCuadraVivienda(String calleCuadraVivienda) {
  this.calleCuadraVivienda = calleCuadraVivienda;
 }

 @Column(name = "P64_barrio", nullable = true, length = 20)
 public int getBarrio() {
  return barrio;
 }
 public void setBarrio(int barrio) {
  this.barrio = barrio;
 }

 @Column(name = "P65_dispuestoParticiparActividad", nullable = true, length = 20)
 public String getDispuestoParticiparActividad() {
  return dispuestoParticiparActividad;
 }
 public void setDispuestoParticiparActividad(String dispuestoParticiparActividad) {
  this.dispuestoParticiparActividad = dispuestoParticiparActividad;
 }

 @Column(name = "P62_meterial_paredes", length = 20)
    public String getMaterialParedes() {
        return materialParedes;
    }

    public void setMaterialParedes(String materialParedes) {
        this.materialParedes = materialParedes;
    }

    @Column(name = "P62_material_piso", length = 16)
    public String getMaterialPiso() {
        return materialPiso;
    }

    public void setMaterialPiso(String materialPiso) {
        this.materialPiso = materialPiso;
    }

    @Column(name = "P62_material_techo", length = 3)
    public String getMaterialTecho() {
        return materialTecho;
    }

    public void setMaterialTecho(String materialTecho) {
        this.materialTecho = materialTecho;
    }

    @Column(name = "P62_otro_meterial_paredes")
    public String getOtroMaterialParedes() {
        return otroMaterialParedes;
    }

    public void setOtroMaterialParedes(String otroMaterialParedes) {
        this.otroMaterialParedes = otroMaterialParedes;
    }

    @Column(name = "P62_otro_material_piso")
    public String getOtroMaterialPiso() {
        return otroMaterialPiso;
    }

    public void setOtroMaterialPiso(String otroMaterialPiso) {
        this.otroMaterialPiso = otroMaterialPiso;
    }

    @Column(name = "P62_otro_material_techo")
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
        if (!(o instanceof CuestionarioCAP)) return true;

        CuestionarioCAP that = (CuestionarioCAP) o;

        if (!codigoEncuesta.equals(that.codigoEncuesta)) return true;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoEncuesta.hashCode();
    }
}
