package ni.org.ics.webapp.web.controller;

import ni.org.ics.webapp.domain.Retiros.Retiros;
import ni.org.ics.webapp.domain.Serologia.*;
import ni.org.ics.webapp.domain.catalogs.Razones_Retiro;
import ni.org.ics.webapp.domain.core.ControlAsistencia;
import ni.org.ics.webapp.domain.core.Estudio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.entomologia.CuestionarioHogar;
import ni.org.ics.webapp.domain.entomologia.CuestionarioHogarPoblacion;
import ni.org.ics.webapp.domain.entomologia.CuestionarioPuntoClave;
import ni.org.ics.webapp.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.webapp.domain.hemodinamica.HemoDetalle;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.scancarta.DetalleParte;
import ni.org.ics.webapp.domain.scancarta.ParticipanteCarta;
import ni.org.ics.webapp.domain.scancarta.ParticipanteExtension;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.service.Retiro.RetiroService;
import ni.org.ics.webapp.service.Serologia.SerologiaService;
import ni.org.ics.webapp.service.entomologia.CuestionarioHogarService;
import ni.org.ics.webapp.service.entomologia.CuestionarioPuntoClaveService;
import ni.org.ics.webapp.service.hemodinamica.HemodinamicaService;
import ni.org.ics.webapp.service.reportes.ReportesPdfService;
import ni.org.ics.webapp.service.scancarta.ScanCartaService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.Constants;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 9/8/2017.
 * V1.0
 */
@Controller
@RequestMapping("/reportes/*")
public class ReportesController {

    private static final Logger logger = LoggerFactory.getLogger(ReportesController.class);

    @Resource(name = "estudioService")
    private EstudioService estudioService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "reportesPdfService")
    private ReportesPdfService reportesPdfService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaService serologiaservice;

    @Resource(name = "recepcionEnfermoService")
    private RecepcionEnfermoService recepcionEnfermoService;

    /* Instancia de mi Servicio ScanCarta */
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    /* Instancia de servicio Hoja clinica */
    @Resource(name = "hojaClinicaService")
    private HojaClinicaService hojaClinicaService;

    /* Instancia de mi Servicio Retiro */
    @Resource(name = "RetiroService")
    private RetiroService retiroService;

    @Resource(name = "cuestionarioHogarService")
    private CuestionarioHogarService cuestionarioHogarService;

    @Resource(name = "cuestionarioPuntoClaveService")
    private CuestionarioPuntoClaveService cuestionarioPuntoClaveService;

    @Resource(name = "controlAsistenciaService")
    private ControlAsistenciaService controlAsistenciaService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "hemodinamicaService")
    private HemodinamicaService hemodinamicaService;

    //region todo Genera el Reporte de ScanCarta /reportes/ReporteCarta
    @RequestMapping(value = "/ReporteCarta", method = RequestMethod.GET)
    public ModelAndView ReporteCarta(@RequestParam(value = "idparticipantecarta", required = true)Integer idparticipantecarta)
        throws  Exception{
        ModelAndView ReporteCarta = new ModelAndView("pdfView");
        ParticipanteCarta obj = scanCartaService.getCartasParticipante(idparticipantecarta);
        if (obj == null){
            ReporteCarta = new ModelAndView("404");
        }else {
            ReporteCarta.addObject("obj", obj);
            if (obj != null) {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(obj.getParticipante().getCodigo());
                ReporteCarta.addObject("procesos", procesos);
            }
            List<DetalleParte> dp = scanCartaService.getDetalleParteList(idparticipantecarta);
            ReporteCarta.addObject("dp", dp);

            List<ParticipanteExtension> getListParticipanteExtension = this.scanCartaService.getAllPartExt(idparticipantecarta);
            ReporteCarta.addObject("getListParticipanteExtension", getListParticipanteExtension);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
            relFam.addAll(messageResourceService.getCatalogo("CAT_SCAN_PROYECTO"));
            relFam.addAll(messageResourceService.getCatalogo("SCANCARTA"));
            relFam.addAll(messageResourceService.getCatalogo("CAT_TIPO_ASENT"));
            ReporteCarta.addObject("relFam", relFam);
            ReporteCarta.addObject("TipoReporte", Constants.TPR_REPORTECARTA);
            return ReporteCarta;
        }
        return ReporteCarta;
    }
    //endregion
    //region  /reportes/ReporteBhcBc6000
    //@RequestMapping(value = "/ReporteBhcBc6000/", method = RequestMethod.GET)
    @RequestMapping(value = "/ReporteBhcBc6000/{participante_cod}/{bhcId}/", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView ReporteBhcBc6000(@PathVariable(value = "participante_cod")String participante_cod,
            @PathVariable(value = "bhcId")Integer bhcId)
            throws  Exception{
        ModelAndView ReporteBhcBc6000 = new ModelAndView("pdfView");
        Bhc_Bc6000 obj = hojaClinicaService.getImprimirBHCImportadasBc6000(participante_cod.substring(3,7),bhcId);
        hojaClinicaService.getcambiarImpresoBhc(bhcId);
        Participante part = participanteService.getParticipanteByCodigo(participante_cod.substring(3,7));
        ReporteBhcBc6000.addObject("part", part);
            ReporteBhcBc6000.addObject("obj", obj);

            ReporteBhcBc6000.addObject("TipoReporte", Constants.TPR_IMPRIMIR_BHC_BC6000);
            return ReporteBhcBc6000;
    }


    //region todo Este controlador Genera el Reporte Serologia PDF y EXCEL
    @RequestMapping(value = "/downloadFileEnviosSerologia", method = RequestMethod.GET)
    public ModelAndView downloadFileEnviosSerologia(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                                    @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                    @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception{
        ModelAndView ReporteEnvio = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<SerologiaEnvio> SerologiasEnviadas =  this.serologiaservice.getSerologiaEnvioByDates(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvio.addObject("nEnvios",nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvio.addObject("sitios", sitios);
        ReporteEnvio.addObject("fechaInicio",fechaInicio);
        ReporteEnvio.addObject("fechaFin",fechaFin);
        ReporteEnvio.addObject("SerologiasEnviadas",SerologiasEnviadas);
        List<Serologia_Detalles_Envio> allSerologia = this.serologiaservice.getAllSerologia(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvio.addObject("allSerologia",allSerologia);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTE);
        return ReporteEnvio;
    }



    @RequestMapping(value = "downloadFileSerologiaExcel", method = RequestMethod.GET)
    public ModelAndView SerologiaExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                       @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                       @RequestParam(value="fechaFin", required=false ) String fechaFin)
    throws Exception {
            ModelAndView ReporteEnvio = new ModelAndView("excelView");
            Date dFechaInicio = null;
            if (fechaInicio != null && !fechaInicio.isEmpty())
                dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            Date dFechaFin = null;
            if (fechaFin != null && !fechaFin.isEmpty())
                dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            List<SerologiaEnvio> SerologiasEnviadas = this.serologiaservice.getSerologiaEnvioByDates(nEnvios, dFechaInicio, dFechaFin);
            ReporteEnvio.addObject("nEnvios", nEnvios);
            List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
            ReporteEnvio.addObject("sitios", sitios);
            ReporteEnvio.addObject("fechaInicio", fechaInicio);
            ReporteEnvio.addObject("fechaFin", fechaFin);
            ReporteEnvio.addObject("SerologiasEnviadas", SerologiasEnviadas);
            List<Serologia_Detalles_Envio> allSerologia = this.serologiaservice.getAllSerologia(nEnvios, dFechaInicio, dFechaFin);
            ReporteEnvio.addObject("allSerologia", allSerologia);
            ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTE);
            return ReporteEnvio;
    }

    //endregion
    //region todo Este controlador Genera el Reporte HORAS LABORADAS ICS EXCEL
    @RequestMapping(value = "/downloadFileReporteHorasICSExcel", method = RequestMethod.GET)
    public ModelAndView downloadFileReporteHorasICSExcel(@RequestParam(value="userId", required=false ) String id,
                                                         @RequestParam(value="sitio", required=false ) String idSitio,
                                                         @RequestParam(value="depto", required=false ) String idDepto,
                                              @RequestParam(value="fechaInicioCons", required=false ) String fechaInicio,
                                              @RequestParam(value="fechaFinCons", required=false ) String fechaFin)
            throws Exception{
        ModelAndView ReporteHorasLaboradas = new ModelAndView("excelView");
        Date dFechaInicio = null;
        List<SitiosICSDto> sitios = null;
        List<DepartamentosICSDto> depto = null;
        List<ReporteHorasICSDto> horasEmpleados = null;
        FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
        logger.debug("IDSITIO "+ idSitio);



        if (fechaInicio!=null && !fechaInicio.isEmpty())
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicio + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));

        if (fechaFin!=null && !fechaFin.isEmpty())
            filtro.setFechaFin(DateUtil.StringToDate(fechaFin +" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));

        if(!id.isEmpty() && id != null && id != ""){
            String[] rst = id.split("-");
            horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, rst[1], rst[0]);
        }else {
            if (!idDepto.isEmpty() && idDepto != null && idDepto != "") {
                String[] rst = idDepto.split("-");
                horasEmpleados = this.controlAsistenciaService.getReporteHorasSitiosDeptoICS(filtro, rst[1], rst[0]);
                depto = this.controlAsistenciaService.getDepartamentosICS(idSitio);
            } else {
                if (!idSitio.isEmpty() && idSitio != null && idSitio != "") {
                    horasEmpleados = this.controlAsistenciaService.getReporteHorasSitiosICS(filtro, idSitio);
                    depto = this.controlAsistenciaService.getDepartamentosICS(idSitio);
                }
            }
        }
       // logger.debug("DEPTO "+ horasEmpleados.get(0).getDepto().toString());

        sitios = this.controlAsistenciaService.getSitiosICS();

      //  horasEmpleados = this.controlAsistenciaService.getReporteHorasSitiosICS(filtro, idSitio);
       // logger.debug("DEPTO "+ horasEmpleados.get(0).getDepto().toString());

        ReporteHorasLaboradas.addObject("sitios", sitios.get(0).getDescripcion());
        ReporteHorasLaboradas.addObject("depto",depto);
        ReporteHorasLaboradas.addObject("fechaInicio",fechaInicio);
        ReporteHorasLaboradas.addObject("fechaFin",fechaFin);
        ReporteHorasLaboradas.addObject("horasEmpleados",horasEmpleados);

        ReporteHorasLaboradas.addObject("TipoReporte", Constants.TPR_REPORTE_HORAS_TRABAJADAS);
        return ReporteHorasLaboradas;
    }

    //region todo Este controlador Genera el Reporte Serologia PDF y EXCEL
    @RequestMapping(value = "/downloadFileEnviosBhc", method = RequestMethod.GET)
    public ModelAndView downloadFileEnviosBhc(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                                    @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                    @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception{
        ModelAndView ReporteEnvioBhc = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<BhcEnvio> BhcEnviadas =  this.serologiaservice.getBhcEnvioByDates(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvioBhc.addObject("nEnvios",nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvioBhc.addObject("sitios", sitios);
        ReporteEnvioBhc.addObject("fechaInicio",fechaInicio);
        ReporteEnvioBhc.addObject("fechaFin",fechaFin);
        ReporteEnvioBhc.addObject("BhcEnviadas",BhcEnviadas);
        List<Bhc_Detalles_Envio> allBhc = this.serologiaservice.getAllBhc(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvioBhc.addObject("allBhc",allBhc);
        ReporteEnvioBhc.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC);
        return ReporteEnvioBhc;
    }

    //region todo Este controlador Genera el Reporte Serologia PDF y EXCEL
    @RequestMapping(value = "/downloadFileEnviosBhc1", method = RequestMethod.GET)
    public ModelAndView downloadFileEnviosBhc1(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                              @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                              @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception{
        ModelAndView ReporteEnvioBhc = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<BhcEnvio> BhcEnviadas =  this.serologiaservice.getBhcEnvioByDates(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvioBhc.addObject("nEnvios",nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvioBhc.addObject("sitios", sitios);
        ReporteEnvioBhc.addObject("fechaInicio",fechaInicio);
        ReporteEnvioBhc.addObject("fechaFin",fechaFin);
        ReporteEnvioBhc.addObject("BhcEnviadas",BhcEnviadas);
        List<Bhc_Detalles_Envio> allBhc = this.serologiaservice.getAllBhc(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvioBhc.addObject("allBhc",allBhc);
        ReporteEnvioBhc.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC1);
        return ReporteEnvioBhc;
    }


    @RequestMapping(value = "downloadFileBhcExcel", method = RequestMethod.GET)
    public ModelAndView BhcExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                       @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                       @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView ReporteEnvio = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<BhcEnvio> BhcEnviadas = this.serologiaservice.getBhcEnvioByDates(nEnvios, dFechaInicio, dFechaFin);
        ReporteEnvio.addObject("nEnvios", nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvio.addObject("sitios", sitios);
        ReporteEnvio.addObject("fechaInicio", fechaInicio);
        ReporteEnvio.addObject("fechaFin", fechaFin);
        ReporteEnvio.addObject("BhcEnviadas", BhcEnviadas);
        List<Bhc_Detalles_Envio> allBhc = this.serologiaservice.getAllBhc(nEnvios, dFechaInicio, dFechaFin) ;
        ReporteEnvio.addObject("allBhc", allBhc);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC);
        return ReporteEnvio;
    }
    @RequestMapping(value = "downloadFileBhcExcel1", method = RequestMethod.GET)
    public ModelAndView BhcExcel1(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                 @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                 @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView ReporteEnvio = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<BhcEnvio> BhcEnviadas = this.serologiaservice.getBhcEnvioByDates(nEnvios, dFechaInicio, dFechaFin);
        ReporteEnvio.addObject("nEnvios", nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvio.addObject("sitios", sitios);
        ReporteEnvio.addObject("fechaInicio", fechaInicio);
        ReporteEnvio.addObject("fechaFin", fechaFin);
        ReporteEnvio.addObject("BhcEnviadas", BhcEnviadas);
        List<Bhc_Detalles_Envio> allBhc = this.serologiaservice.getAllBhc(nEnvios, dFechaInicio, dFechaFin) ;
        ReporteEnvio.addObject("allBhc", allBhc);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC1);
        return ReporteEnvio;
    }
    //endregion


    //region todo Reporte Retiro A2CARE /reportes/reporteRetiro
    @RequestMapping(value = "/reporteRetiro", method = RequestMethod.GET)
    public ModelAndView retiro(@RequestParam(value="parametro", required=false ) Integer parametro)
            throws Exception{
        ModelAndView ReporteRetiro = new ModelAndView("pdfView");
        List<MessageResource> causas_retiros = messageResourceService.getCatalogo("CAT_CAUSAS_RETIROS");
        ReporteRetiro.addObject("causas_retiros", causas_retiros);
        List<MessageResource> coordinador_estudio = messageResourceService.getCatalogo("CAT_COORDINADOR_ESTUDIO");
        ReporteRetiro.addObject("coordinador_estudio", coordinador_estudio);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
        ReporteRetiro.addObject("relFam", relFam);
        Retiros retiros = this.retiroService.getRetiroByID(parametro);
        ReporteRetiro.addObject("retiros", retiros);
        Personal personal = this.retiroService.getSupervisorById(retiros.getPersona_documenta());
        ReporteRetiro.addObject("personal", personal);
        Personal supervisor = this.retiroService.getSupervisorById(retiros.getMedico_supervisor());
        ReporteRetiro.addObject("supervisor", supervisor);
        List<Razones_Retiro> listaDerazones = this.retiroService.getlistaDeRazonRetiro();
        ReporteRetiro.addObject("listaDerazones", listaDerazones);
        List<Razones_Retiro> listaDeRazonesGrupo_1 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(1);
        ReporteRetiro.addObject("listaDeRazonesGrupo_1", listaDeRazonesGrupo_1);
        List<Razones_Retiro> listaDeRazonesGrupo_2 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(2);
        ReporteRetiro.addObject("listaDeRazonesGrupo_2", listaDeRazonesGrupo_2);
        List<Razones_Retiro> listaDeRazonesGrupo_3 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(3);
        ReporteRetiro.addObject("listaDeRazonesGrupo_3", listaDeRazonesGrupo_3);
        List<Razones_Retiro> listaDeRazonesGrupo_4 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(4);
        ReporteRetiro.addObject("listaDeRazonesGrupo_4", listaDeRazonesGrupo_4);
        ReporteRetiro.addObject("TipoReporte", Constants.TPR_REPORTERETIRO);
        return ReporteRetiro;
    }
    //endregion


    //region generar reporte datos generales


    @RequestMapping(value = "/pdf/fileData", method = RequestMethod.GET)
    public String fileDataReportForm(Model model) throws ParseException {
        logger.debug("Mostrando formulario para generar datos generales para agregar al expediente");
        List<Estudio> estudios = estudioService.getEstudios();
        model.addAttribute("estudios", estudios);
        return "/reportes/fileData";
    }

    //region DESCARGA DE REPORTE DE ENVIO DE MX ENFERMOS
    @RequestMapping(value = "downloadEnvioMxEnfermoExcel", method = RequestMethod.GET)
    public ModelAndView downloadEnvioMxEnfermoExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                       @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                       @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView excelView = new ModelAndView("excelView");
        setModelAndViewPropertiesForEnvioMxEnfermo(excelView, fechaInicio, fechaFin, nEnvios);
        return excelView;
    }

    @RequestMapping(value = "/downloadEnvioMxEnfermoPdf", method = RequestMethod.GET)
    public ModelAndView downloadEnvioMxEnfermoPdf(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                                  @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                  @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception{
        ModelAndView pdfView = new ModelAndView("pdfView");
        setModelAndViewPropertiesForEnvioMxEnfermo(pdfView, fechaInicio, fechaFin, nEnvios);        return pdfView;
    }

    private void setModelAndViewPropertiesForEnvioMxEnfermo(ModelAndView ReporteEnvio, String fechaInicio, String fechaFin, Integer nEnvios) throws Exception{
        Date dFechaInicio = null;
        if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<MuestraEnfermoEnvio> SerologiasEnviadas = this.recepcionEnfermoService.getMuestraEnfermoEnvioByDatesAndNumber(nEnvios, dFechaInicio, dFechaFin);
        List<MuestraEnfermoDetalleEnvio> allSerologia = this.recepcionEnfermoService.getAllMuestrasDetalleByDatesAndNumber(nEnvios, dFechaInicio, dFechaFin);
        List<MessageResource> tiposConsultas = messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");

        ReporteEnvio.addObject("nEnvios", nEnvios);
        ReporteEnvio.addObject("sitios", sitios);
        ReporteEnvio.addObject("fechaInicio", fechaInicio);
        ReporteEnvio.addObject("fechaFin", fechaFin);
        ReporteEnvio.addObject("SerologiasEnviadas", SerologiasEnviadas);
        ReporteEnvio.addObject("allSerologia", allSerologia);
        ReporteEnvio.addObject("tiposConsultas", tiposConsultas);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIO_ENFERMO);
    }

    //endregion
    @RequestMapping(value = "/downloadConvalecientesMxEnfermoPdf", method = RequestMethod.GET)
    public ModelAndView downloadConvalecientesMxEnfermoPdf()
            throws Exception{
        ModelAndView pdfView = new ModelAndView("pdfView");
        setModelAndViewPropertiesForConvalecientesMxEnfermo(pdfView);        return pdfView;
    }

    private void setModelAndViewPropertiesForConvalecientesMxEnfermo(ModelAndView ReporteEnvio ) throws Exception{
        Date dFechaInicio = null;
        /*if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");*/
        List<ConvalecientesEnfermoDto> ConvalecientesPrint = this.recepcionEnfermoService.getMxConvalecientesEnfermo();

        List<MessageResource> tiposConsultas = messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");


        ReporteEnvio.addObject("sitios", sitios);

        ReporteEnvio.addObject("ConvalecientesPrint", ConvalecientesPrint);

        ReporteEnvio.addObject("tiposConsultas", tiposConsultas);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_CONVALECIENTES_ENFERMO);
    }


/*
    @RequestMapping(value = "/downloadFileDataReport", method = RequestMethod.GET)
    public ModelAndView downloadFilaDataReport(@RequestParam(value="estudio", required=false) int estudio,
                                               @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                               @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                               @RequestParam(value="codigoParticipante", required=false) String codigoParticipante
    ) throws Exception{
        ModelAndView excelView = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        List<DatosGeneralesParticipante> datosParticipante = reportesPdfService.getDatosGeneralesParticipante(estudio, codigoParticipante, dFechaInicio, dFechaFin);
        List<MessageResource> messageReports = messageResourceService.loadAllMessagesNoCatalogs();
        excelView.addObject("labels", messageReports);
        excelView.addObject("datos", datosParticipante);
        excelView.addObject("TipoReporte", Constants.TPR_DATOSGENERALES);
        return excelView;
    }*/

    //endregion

    @RequestMapping(value = "downloadEntoInfo", method = RequestMethod.GET)
    public ModelAndView downloadEntoInfo(@RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                         @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView reporteDatosEntomologia = new ModelAndView("excelView");
        Date dFechaInicio = null;
        Date dFechaFin = null;
        List<CuestionarioHogar> cuestionarios = new ArrayList<CuestionarioHogar>();
        List<CuestionarioHogarPoblacion> poblacion = new ArrayList<CuestionarioHogarPoblacion>();
        List<CuestionarioPuntoClave> puntosClaves = new ArrayList<CuestionarioPuntoClave>();

        if ((fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty())) {
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            cuestionarios = this.cuestionarioHogarService.getCuestionariosHogarByRangoFechas(dFechaInicio, dFechaFin);
            poblacion = this.cuestionarioHogarService.getCuestionariosHogarPobByRangoFechas(dFechaInicio, dFechaFin);
            puntosClaves = this.cuestionarioPuntoClaveService.getCuestionariosPuntoClaveByRangoFechas(dFechaInicio, dFechaFin);
        } else {
            cuestionarios = this.cuestionarioHogarService.getCuestionariosHogar();
            poblacion = this.cuestionarioHogarService.getCuestionariosHogarPoblacion();
            puntosClaves = this.cuestionarioPuntoClaveService.getCuestionariosPuntoClave();
        }

        reporteDatosEntomologia.addObject("fechaInicio", fechaInicio);
        reporteDatosEntomologia.addObject("fechaFin", fechaFin);
        reporteDatosEntomologia.addObject("cuestionarios", cuestionarios);
        reporteDatosEntomologia.addObject("poblacion", poblacion);
        reporteDatosEntomologia.addObject("puntosClaves", puntosClaves);
        reporteDatosEntomologia.addObject("TipoReporte", Constants.TPR_ENTO);

        return reporteDatosEntomologia;
    }

    @RequestMapping(value = "downloadControlAsistenciaPdf", method = RequestMethod.GET)
    public ModelAndView downloadControlAsistencia(@RequestParam(value="desde", required=false ) String desde,
                                         @RequestParam(value="hasta", required=false ) String hasta)
            throws Exception {
        ModelAndView reporteControlAsistencia = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        Date dFechaFin = null;
        List<ControlAsistenciaDto> controlAsist = new ArrayList<ControlAsistenciaDto>();


            dFechaInicio = DateUtil.StringToDate(desde, "dd/MM/yyyy");
            dFechaFin = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();

            filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            controlAsist = this.controlAsistenciaService.getControlAsistenciaPersonal(filtro);


        reporteControlAsistencia.addObject("fechaInicio", desde);
        reporteControlAsistencia.addObject("fechaFin", hasta);
        reporteControlAsistencia.addObject("controlAsist", controlAsist);
        reporteControlAsistencia.addObject("TipoReporte", Constants.TPR_CONTROL_ASISTENCIA);

        return reporteControlAsistencia;
    }

    @RequestMapping(value = "/ReporteHemodinamica", method = RequestMethod.GET)
    public ModelAndView ReporteHemodinamica(@RequestParam(value = "idDatoHemo", required = true) String idDatoHemo)
            throws Exception{
        ModelAndView pdfHemodinamic = new ModelAndView("pdfView");
        DatosHemodinamica obj = hemodinamicaService.getHemoDinamicaById(idDatoHemo);
        List<HemoDetalle> detalle = hemodinamicaService.getListHemoDetalle(idDatoHemo);
        List<MessageResource> messageReports = messageResourceService.loadAllMessagesNoCatalogs();
        List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
        extremidades.addAll(messageResourceService.getCatalogo("NIVELCONCIENCIA"));
        extremidades.addAll(messageResourceService.getCatalogo("LLENADOCAPILAR"));
        extremidades.addAll(messageResourceService.getCatalogo("PULSOCALIDAD"));
        extremidades.addAll(messageResourceService.getCatalogo("DIURESIS"));
        extremidades.addAll(messageResourceService.getCatalogo("CAT_PUESTO_SALUD"));
        pdfHemodinamic.addObject("extremidades", extremidades);
        pdfHemodinamic.addObject("labels", messageReports);
        pdfHemodinamic.addObject("obj", obj);
        pdfHemodinamic.addObject("detalle", detalle);
        pdfHemodinamic.addObject("TipoReporte", Constants.TPR_HEMOREPORTE);
        return pdfHemodinamic;
    }

}
