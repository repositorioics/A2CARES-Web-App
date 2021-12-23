package ni.org.ics.webapp.web.controller;

import ni.org.ics.webapp.domain.Serologia.SerologiaEnvio;
import ni.org.ics.webapp.domain.core.Estudio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.scancarta.DetalleParte;
import ni.org.ics.webapp.domain.scancarta.ParticipanteCarta;
import ni.org.ics.webapp.domain.scancarta.ParticipanteExtension;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.EstudioService;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.Serologia.SerologiaService;
import ni.org.ics.webapp.service.reportes.ReportesPdfService;
import ni.org.ics.webapp.service.scancarta.ScanCartaService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.pdf.Constants;
import ni.org.ics.webapp.web.utils.pdf.DatosGeneralesParticipante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Miguel Salinas on 9/8/2017.
 * V1.0
 */
@Controller
@RequestMapping("/reportes/*")
public class ReportesController {

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "reportesPdfService")
    private ReportesPdfService reportesPdfService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaService serologiaservice;


    /* Instancia de mi Servicio ScanCarta */
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

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

    //region todo Este controlador Genera el Reorte Serologia
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
        ReporteEnvio.addObject("fechaInicio",fechaInicio);
        ReporteEnvio.addObject("fechaFin",fechaFin);
        ReporteEnvio.addObject("SerologiasEnviadas",SerologiasEnviadas);
        List<Participante> allParticipantes = this.serologiaservice.getAllParticipantes();
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTE);
        return ReporteEnvio;
    }
//endregion

}
