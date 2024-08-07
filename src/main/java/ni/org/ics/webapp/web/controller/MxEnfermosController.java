package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
//import java.text.SimpleDateFormat;

/**
 * Created by miguel on 1/2/2022.
 */
@Controller
@RequestMapping("/mx/enfermo/*")
public class MxEnfermosController {

    private static final Logger logger = LoggerFactory.getLogger(ComparacionController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "recepcionEnfermoService")
    private RecepcionEnfermoService recepcionEnfermoService;

    @Resource(name = "informeFinDiaMedicosService")
    private InformeFinDiaMedicosService informeFinDiaMedicosService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando lista de mx de enfermo para envio del dia de hoy en JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/list";
    }
    @RequestMapping(value = "admisionPacientesMx2", method = RequestMethod.GET)
    public String informeFinDiaMedicosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de Informe Fin de Dia Medicos JSP");
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/admisionPacientesMx2";
    }
    @RequestMapping(value = "procesosPendientesMuestreoA2cares", method = RequestMethod.GET)
    public String procesosPendientesMuestreoA2cares(Model model) throws ParseException {
        logger.debug("Mostrando lista de procesosPendientesMuestreoA2cares JSP");
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/procesosPendientesMuestreo";
    }

    @RequestMapping(value = "controldeIngresosMx", method = RequestMethod.GET)
    public String controlIngresosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de mx de enfermo JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/controldeIngresosMx";
    }

    @RequestMapping(value = "cuadrarRecepcionMuestreo", method = RequestMethod.GET)
    public String cuadrarRecepcionMuestreo(Model model) throws ParseException {
        logger.debug("Mostrando lista de muestras ingresadas Muestreo JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "Serologia/cuadrarRecepcionMuestreo";
    }

    @RequestMapping(value = "diferenciasRecepcionMuestreo", method = RequestMethod.GET)
    public String diferenciasRecepcionMuestreo(Model model) throws ParseException {
        logger.debug("Mostrando lista de muestras diferenciasRecepcionMuestreo JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "Serologia/diferenciasRecepcionMuestreo";
    }

    @RequestMapping(value = "convalecientesMx", method = RequestMethod.GET)
    public String convalecientesMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de convalecientes mx de enfermo JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/convalecientesMxEnfermos";
    }

    @RequestMapping(value = "admisionPacientesMx", method = RequestMethod.GET)
    public String admisionPacientes(Model model) throws ParseException {
        logger.debug("Mostrando lista de admision mx de enfermo JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/admisionPacientesMx";
    }

    @RequestMapping(value = "getTablaSupervisor", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<MuestrasEnfermosDto> getTablaSupervisor(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<MuestrasEnfermosDto> enfermeria = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de muestreo - Supervisor");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            enfermeria = this.recepcionEnfermoService.ObtenerMuestrasSupervisores(filtro);
            //  return JsonUtil.createJsonResponse(enfermeria);
            return enfermeria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getTablaEncuestador", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<MuestrasEnfermosDto> getTablaEncuestador(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<MuestrasEnfermosDto> enfermeria = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de muestreo - getTablaEncuestador");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            enfermeria = this.recepcionEnfermoService.ObtenerMuestrasEncuestadores(filtro);
            //  return JsonUtil.createJsonResponse(enfermeria);
            return enfermeria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    @RequestMapping(value = "getTablaRecepcion", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<MuestrasEnfermosDto> getTablaRecepcion(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<MuestrasEnfermosDto> enfermeria = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de muestreo - getTablaRecepcion");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            enfermeria = this.recepcionEnfermoService.ObtenerMuestrasRecepcionMuestreo(filtro);
            //  return JsonUtil.createJsonResponse(enfermeria);
            return enfermeria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }


    @RequestMapping(value = "getTablasMxEnfermos", method = RequestMethod.GET, produces = "application/json")
   // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
   public @ResponseBody List<MuestrasEnfermosDto> getTablasMxEnfermos(
    @RequestParam(value="desde", required=false ) String desde
    ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<MuestrasEnfermosDto> enfermeria = null;
        List<OrdenLaboratorioDto> medicos = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de enfermos - enfermeria");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
         //   filtro.setCodigoMx(muestra);
          //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
         //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
          //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
              //   if (filtro.getFechaInicio() != null) {
                     enfermeria = this.recepcionEnfermoService.getMxMuestraEnfermo(filtro);
              //       medicos = this.recepcionEnfermoService.getMxOrdenLaboratorio(filtro);
              //   }
           /*      int i = 0;
            if (enfermeria.size() > 0 && medicos.size() > 0) {
                    for (MuestrasEnfermosDto enf : enfermeria) {
                        if (!enf.getFis().equalsIgnoreCase(medicos.get(i).getFis())) {
                            enf.setFis("<span class='badge badge-danger'>" + enf.getFis() + "</span>");
                        }
                        if (!enf.getFif().equalsIgnoreCase(medicos.get(i).getFif())) {
                            enf.setFif("<span class='badge badge-danger'>" + enf.getFif() + "</span>");
                        }
                    }
                    i++;
            }*/
            //return JsonUtil.createJsonResponse(enfermeriaDif);
            return enfermeria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getMxOrdenLaboratorio", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<OrdenLaboratorioDto> getMxOrdenLaboratorio(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<OrdenLaboratorioDto> medicos = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de enfermos - medicos");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            medicos = this.recepcionEnfermoService.getMxOrdenLaboratorio(filtro) ;
            //  return JsonUtil.createJsonResponse(enfermeria);
            return medicos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }



    @RequestMapping(value = "getMxRecepcionEnfermo", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<RecepcionEnfermoDto> getMxRecepcionEnfermo(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<RecepcionEnfermoDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de enfermos - recepcion");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            recepcion = this.recepcionEnfermoService.getMxRecepcionEnfermo(filtro) ;
            //  return JsonUtil.createJsonResponse(enfermeria);
            return recepcion;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getConvalecientesMx", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<ConvalecientesEnfermoDto> getConvalecientesMx(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<ConvalecientesEnfermoDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de muestras de enfermos - recepcion");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            recepcion = this.recepcionEnfermoService.getMxConvalecientesEnfermo() ;
            //  return JsonUtil.createJsonResponse(enfermeria);
            return recepcion;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    @RequestMapping(value = "getAdmisionPacientes", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody
    ResponseEntity<String> getAdmisionPacientes(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {

      //  List<AdmisionPacientesDto> adminP = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de admision de pacientes");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            List<AdmisionPacientesDto> adminP = this.recepcionEnfermoService.getAdmisionPacientesList(filtro.getFechaInicio(),filtro.getFechaFin()) ;
            if (adminP.size() > 0) {
                for (AdmisionPacientesDto aPacientes : adminP) {
                    if (aPacientes.getPerteneceEstudio().equalsIgnoreCase("SI")) {
                        aPacientes.setPerteneceEstudio("<span class='badge badge-success'>" + aPacientes.getPerteneceEstudio() + "</span>");
                    }
                    if (aPacientes.getPerteneceEstudio().equalsIgnoreCase("NO")) {
                        aPacientes.setPerteneceEstudio( aPacientes.getPerteneceEstudio());
                    }
                    if (aPacientes.getFebril().equalsIgnoreCase("SI")) {
                        aPacientes.setFebril("<span class='badge badge-danger'>" + aPacientes.getFebril() + "</span>");
                    }
                    if (aPacientes.getFebril().equalsIgnoreCase("SI")) {
                        aPacientes.setFebril( aPacientes.getFebril() );
                    }
                    aPacientes.setNumeroHoja(aPacientes.getNumeroHoja());
                    aPacientes.setEdad(aPacientes.getEdad());
                    aPacientes.setSexo(aPacientes.getSexo());
                    aPacientes.setFecha_registro(aPacientes.getFecha_registro());
                    aPacientes.setRecordUser(aPacientes.getRecordUser());
                }
            }
            //  return JsonUtil.createJsonResponse(enfermeria);
            return JsonUtil.createJsonResponse(adminP);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getDiferenciasMuestreo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasMuestreo(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasMuestreo");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifSerologiavsSupervisorMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                                recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getDiferenciasSupervsSerologia", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasSupervsSerologia(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasSupervsSerologia");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifSupervisorvsSerologiaMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                    recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getDiferenciasEncuestadoresvsSerologia", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasEncuestadoresvsSerologia(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasEncuestadoresvsSerologia");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifEncuestadoresvsSerologiaMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                    recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    @RequestMapping(value = "getDiferenciasSerologiavsEncuestadores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasSerologiavsEncuestadores(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasSerologiavsEncuestadores");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifSerologiavsEncuestadoresMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                    recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    @RequestMapping(value = "getDiferenciasSupervisorvsEncuestadores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasSupervisorvsEncuestadores(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasSupervisorvsEncuestadores");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifSupervisorvsEncuestadoresMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                    recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getDiferenciasEncuestadoresvsSupervisor", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getDiferenciasEncuestadoresvsSupervisor(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de getDiferenciasEncuestadoresvsSupervisor");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            List<MuestrasEnfermosDto> recepcion = this.recepcionEnfermoService.ObtenerDifEncuestadoresvsSupervisorMuestreo(filtro);

            if (recepcion.size() > 0) {
                for (MuestrasEnfermosDto recep : recepcion) {
                    recep.setParticipante("<span class='badge badge-danger'>" + recep.getParticipante() + "</span>");
                }
            }
            return JsonUtil.createJsonResponse(recepcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getProcesosPendientesMuestreo", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody
    ResponseEntity<String> getProcesosPendientesMuestreo(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {

        //  List<AdmisionPacientesDto> adminP = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de procesos pendientes muestreo");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
              Date fdesde = DateUtil.StringToDate(fechaInicioCons + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
              Date fhasta = DateUtil.StringToDate(fechaFinCons + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            List<ProcesosPendientesMuestreoDto> adminP = this.recepcionEnfermoService.getProcesosPendientesMuestreoList(fdesde, fhasta) ;
            if (adminP.size() > 0) {
                for (ProcesosPendientesMuestreoDto aPacientes : adminP) {
                    if (aPacientes.getMx().equalsIgnoreCase("Si")) {
                        aPacientes.setMx("<span class='badge badge-danger'>" + aPacientes.getMx() + "</span>");
                    }else
                    {
                        aPacientes.setMx(aPacientes.getMx());
                    }

                    if (aPacientes.getEnc_casa().equalsIgnoreCase("Si")) {
                        aPacientes.setEnc_casa("<span class='badge badge-danger'>" + aPacientes.getEnc_casa() + "</span>");
                    }
                    else
                    {
                        aPacientes.setEnc_casa(aPacientes.getEnc_casa());
                    }
                    if (aPacientes.getEnc_part().equalsIgnoreCase("Si")) {
                        aPacientes.setEnc_part("<span class='badge badge-danger'>" + aPacientes.getEnc_part() + "</span>");
                    }else
                    {
                        aPacientes.setEnc_part(aPacientes.getEnc_part());
                    }
                    if (aPacientes.getPyt().equalsIgnoreCase("Si")) {
                        aPacientes.setPyt("<span class='badge badge-danger'>" + aPacientes.getPyt() + "</span>");
                    }else
                    {
                        aPacientes.setPyt(aPacientes.getPyt());
                    }
                    if (aPacientes.getEnc_satis().equalsIgnoreCase("Si")) {
                        aPacientes.setEnc_satis("<span class='badge badge-danger'>" + aPacientes.getEnc_satis() + "</span>");
                    }else
                    {
                        aPacientes.setEnc_satis(aPacientes.getEnc_satis());
                    }
                    if (aPacientes.getObsequio().equalsIgnoreCase("Si")) {
                        aPacientes.setObsequio("<span class='badge badge-danger'>" + aPacientes.getObsequio() + "</span>");
                    }else
                    {
                        aPacientes.setObsequio(aPacientes.getObsequio());
                    }
                    if (aPacientes.getConv().equalsIgnoreCase("Si")) {
                        aPacientes.setConv("<span class='badge badge-danger'>" + aPacientes.getConv() + "</span>");
                    }else
                    {
                        aPacientes.setConv(aPacientes.getConv());
                    }

                    aPacientes.setCodigo_casa(aPacientes.getCodigo_casa());
                    aPacientes.setCodigo(aPacientes.getCodigo());
                    aPacientes.setSector(aPacientes.getSector());
                    aPacientes.setBarrio(aPacientes.getBarrio());
                }
            }
            //  return JsonUtil.createJsonResponse(enfermeria);
            return JsonUtil.createJsonResponse(adminP);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getInformeFinDiaMedicos", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody
    ResponseEntity<String> getInformeFinDiaMedicos(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {

        //  List<AdmisionPacientesDto> adminP = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de Informe Fin de Dia Médicos");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            //   filtro.setCodigoMx(muestra);
            //  filtro.setParticipante(participante);
            filtro.setFechaInicio(DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            filtro.setFechaFin(DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            //   Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            //  Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");

            List<InformeFinDiaMedicosDto> adminP = this.informeFinDiaMedicosService.getInformeFinDiaMedicosListar(filtro);
       /*     if (adminP.size() > 0) {
                for (AdmisionPacientesDto aPacientes : adminP) {
                    if (aPacientes.getPerteneceEstudio().equalsIgnoreCase("SI")) {
                        aPacientes.setPerteneceEstudio("<span class='badge badge-success'>" + aPacientes.getPerteneceEstudio() + "</span>");
                    }
                    if (aPacientes.getPerteneceEstudio().equalsIgnoreCase("NO")) {
                        aPacientes.setPerteneceEstudio( aPacientes.getPerteneceEstudio());
                    }
                    if (aPacientes.getFebril().equalsIgnoreCase("SI")) {
                        aPacientes.setFebril("<span class='badge badge-danger'>" + aPacientes.getFebril() + "</span>");
                    }
                    if (aPacientes.getFebril().equalsIgnoreCase("SI")) {
                        aPacientes.setFebril( aPacientes.getFebril() );
                    }
                    aPacientes.setNumeroHoja(aPacientes.getNumeroHoja());
                    aPacientes.setEdad(aPacientes.getEdad());
                    aPacientes.setSexo(aPacientes.getSexo());
                    aPacientes.setFecha_registro(aPacientes.getFecha_registro());
                    aPacientes.setRecordUser(aPacientes.getRecordUser());
                }
            }*/
            //  return JsonUtil.createJsonResponse(enfermeria);
            return JsonUtil.createJsonResponse(adminP);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model) throws ParseException {
        logger.debug("Buscar muestras de enfermo en JSP");
        return "mxEnfermos/search";
    }

    @RequestMapping(value = "/list-muestras-no-enviadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionEnfermoDto> ListaMuestraToJson() throws ParseException {
        List<RecepcionEnfermoDto> seroDtom = null;
        try{
            seroDtom =  this.recepcionEnfermoService.getSerologiaEnfermosNoEnviadasDto();
            return  seroDtom;
        }catch (Exception e){
            logger.error(e.getMessage());
            return  seroDtom;
        }
    }

    //region Formulario Serologia
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(ModelMap model) throws Exception{
        try {

            List<MessageResource> catCategoria = this.messageResourceService.getCatalogo("CAT_CATEGORIA");
            List<MessageResource> catTipoConsulta = this.messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
            List<MessageResource> catFaseMuestra = this.messageResourceService.getCatalogo("CAT_FASE_MX");

            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("recepcionEnfermo", new RecepcionEnfermoDto());
            model.addAttribute("catCategoria", catCategoria);
            model.addAttribute("catTipoConsulta", catTipoConsulta);
            model.addAttribute("catFaseMuestra", catFaseMuestra);
            model.addAttribute("listado", true);

            return "/mxEnfermos/enterForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }

    @RequestMapping(value = "/update/{idRecepcion}/{listado}", method = RequestMethod.GET)
    public String editMuestra(Model model, @PathVariable("idRecepcion") String idRecepcion,
                              @PathVariable("listado") String listado) throws Exception
    {
        try{
            List<MessageResource> catCategoria = this.messageResourceService.getCatalogo("CAT_CATEGORIA");
            List<MessageResource> catTipoConsulta = this.messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
            List<MessageResource> catFaseMuestra = this.messageResourceService.getCatalogo("CAT_FASE_MX");

            String evento = "";

            try {
              //  evento = this.recepcionEnfermoService.ObtenerEvento1(idRecepcion).toString();


            }catch (Exception e){
                logger.error(e.getMessage());
            }

            RecepcionEnfermo recepcionEnfermo = this.recepcionEnfermoService.getRecepcionEnfermoById(idRecepcion);
            if (recepcionEnfermo.getParticipante()!=null){
                RecepcionEnfermoDto recepcionEnfermoDto = new RecepcionEnfermoDto();
                recepcionEnfermoDto.setIdRecepcion(recepcionEnfermo.getIdRecepcion());
                recepcionEnfermoDto.setParticipante(recepcionEnfermo.getParticipante().getCodigo());
                recepcionEnfermoDto.setNombreCompleto(recepcionEnfermo.getParticipante().getNombreCompleto());
                recepcionEnfermoDto.setFechaRecepcion(DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY));
                recepcionEnfermoDto.setFis(DateUtil.DateToString(recepcionEnfermo.getFis(), Constants.STRING_FORMAT_DD_MM_YYYY));

                recepcionEnfermoDto.setFif(DateUtil.DateToString(recepcionEnfermo.getFif(), Constants.STRING_FORMAT_DD_MM_YYYY));
                logger.debug("fif :"+ recepcionEnfermoDto.getFif());
                logger.debug("evento :"+ recepcionEnfermoDto.getEvento());
                logger.debug("codigo_mx :"+ recepcionEnfermoDto.getCodigoBarra());
                recepcionEnfermoDto.setCategoria(recepcionEnfermo.getCategoria());
                recepcionEnfermoDto.setTipoMuestra(recepcionEnfermo.getTipoMuestra());
                recepcionEnfermoDto.setConsulta(recepcionEnfermo.getConsulta());
              //  recepcionEnfermoDto.setEvento(evento.substring(1,2));
                recepcionEnfermoDto.setEvento(recepcionEnfermoDto.getEvento());
                recepcionEnfermoDto.setObservacion(recepcionEnfermo.getObservacion());
                recepcionEnfermoDto.setVolumen(recepcionEnfermo.getVolumen());
                recepcionEnfermoDto.setCodigoCasa(recepcionEnfermo.getParticipante().getCasa().getCodigo());
                if (!recepcionEnfermo.getParticipante().getEdad().equalsIgnoreCase(Constants.NO_DATA)) {
                    String[] edad = recepcionEnfermo.getParticipante().getEdad().split("/");
                    recepcionEnfermoDto.setEdadParteAnios(edad[0]);
                    recepcionEnfermoDto.setEdadParteMeses(edad[1]);
                    recepcionEnfermoDto.setEdadParteDias(edad[2]);
                } else {
                    recepcionEnfermoDto.setEdadParteAnios(Constants.NO_DATA);
                    recepcionEnfermoDto.setEdadParteMeses(Constants.NO_DATA);
                    recepcionEnfermoDto.setEdadParteDias(Constants.NO_DATA);
                }
                
                model.addAttribute("recepcionEnfermo", recepcionEnfermoDto);
                model.addAttribute("fif", recepcionEnfermoDto.getFif());
                model.addAttribute("evento", recepcionEnfermo.getEvento());
                model.addAttribute("catCategoria", catCategoria);
                model.addAttribute("catTipoConsulta", catTipoConsulta);
                model.addAttribute("catFaseMuestra", catFaseMuestra);
                model.addAttribute("agregando",false);
                model.addAttribute("editando",true);
                model.addAttribute("listado", listado.equalsIgnoreCase("1"));
                return "/mxEnfermos/enterForm";
            }else{
                return "404";
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "404";
        }
    }


    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="parametro", required=true ) String codigo) throws ParseException {
        logger.debug("buscar participante para recepcion mx enfermo");
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
        List<ConvalecientesEnfermoDto> ultimregRec = null;

        String evento = "";
        String positivo = "";
        String ultima_consulta="";
        String fis = "";
        String fif = "";
        String categoria="";
        String consulta = "";
        String tipoMuestra="";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


        Date  fechaF2 = formato.parse("2024-04-15");


        try {
            ultimregRec = this.recepcionEnfermoService.getMxRecepcionRegUltimo(codigo);
            if (ultimregRec.size() > 0 ) {

                Date fechaI1 = formato.parse(ultimregRec.get(0).getFechaRecepcion().toString());
                map.put("fis", ultimregRec.get(0).getFis());
                map.put("fif", ultimregRec.get(0).getFif());
                map.put("categoria", ultimregRec.get(0).getCategoria());
                map.put("consulta", ultimregRec.get(0).getConsulta());
                map.put("tipoMuestra", ultimregRec.get(0).getTipoMuestra());

                if (ultimregRec.get(0).getConsulta().equalsIgnoreCase("3")) {
                    evento = this.recepcionEnfermoService.ObtenerEvento(codigo).toString();
                    evento = evento.substring(1, 2);
                    ultima_consulta = this.recepcionEnfermoService.Ultima_consulta_evento1(codigo).toString();
                }
                if (ultimregRec.get(0).getConsulta().equalsIgnoreCase("3") && fechaI1.after(fechaF2)) {
                    evento = this.recepcionEnfermoService.ObtenerEvento1(codigo).toString();
                    evento = evento.substring(1, 2);
                    ultima_consulta = this.recepcionEnfermoService.Ultima_consulta_evento(codigo).toString();
                }
                if (ultimregRec.get(0).getConsulta().equalsIgnoreCase("1")) {
                    if(ultimregRec.get(0).getCodigoBarra().substring(8,11).equals("R23")){
                        evento = this.recepcionEnfermoService.ObtenerEvento(codigo).toString();
                    }else{
                        evento = this.recepcionEnfermoService.ObtenerEvento1(codigo).toString();
                    }
                    evento = evento.substring(1, 2);
                    ultima_consulta = this.recepcionEnfermoService.Ultima_consulta_evento(codigo).toString();
                }


                positivo = ultima_consulta;
                if(ultimregRec.get(0).getCodigoBarra().substring(8,11).equals("R22") && ultimregRec.get(0).getConsulta().equalsIgnoreCase("3")){
                    map.put("consulta", "");
                    map.put("evento", "A");
                }
                if(ultimregRec.get(0).getCodigoBarra().substring(8,11).equals("R23") && ultimregRec.get(0).getConsulta().equalsIgnoreCase("3")){
                    map.put("consulta", "");
                    map.put("evento", "A");
                }else{
                    map.put("evento", evento);
                }



                map.put("ultima_consulta", ultima_consulta);

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);

            if (procesos != null && procesos.getRetirado().equals(1))
                return JsonUtil.createJsonResponse("Participante retirado");
            else {
                map.put("codigo", participante.getCodigo());
                map.put("nombreCompleto", participante.getNombreCompleto());
                map.put("casa", participante.getCasa().getCodigo().toString());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), Constants.STRING_FORMAT_DD_MM_YYYY));

                map.put("evento",evento);


                if (!participante.getEdad().equalsIgnoreCase(Constants.NO_DATA)) {
                    String[] edad = participante.getEdad().split("/");
                    map.put("edadA", edad[0]);
                    map.put("edadM", edad[1]);
                    map.put("edadD", edad[2]);
                } else {
                    map.put("edadA", Constants.NO_DATA);
                    map.put("edadM", Constants.NO_DATA);
                    map.put("edadD", Constants.NO_DATA);
                }
            }
        }
        else
            return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        return JsonUtil.createJsonResponse(map);
    }

    @RequestMapping(value = "guardarRecepcionEnfermo", method = RequestMethod.POST)
    public ResponseEntity<String> guardarRecepcionEnfermo (@RequestParam(value = "idRecepcion", required=false, defaultValue="") String idRecepcion
            ,@RequestParam( value = "codigoParticipante", required = false, defaultValue=""  ) String codigoParticipante
            ,@RequestParam( value = "fecha",          required = false, defaultValue=""  ) String fecha
            ,@RequestParam( value = "fis",          required = false, defaultValue=""  ) String fis
            ,@RequestParam( value = "fif",          required = false, defaultValue=""  ) String fif
            ,@RequestParam( value = "tipoConsulta",          required = false, defaultValue=""  ) String tipoConsulta
            ,@RequestParam( value = "categoria",          required = false, defaultValue=""  ) String categoria
            ,@RequestParam( value = "faseMuestra",          required = false, defaultValue=""  ) String faseMuestra
            ,@RequestParam( value = "volumen",        required = false, defaultValue=""  ) String volumen
            ,@RequestParam( value = "evento",        required = false, defaultValue=""  ) String evento
            ,@RequestParam( value = "observacion",    required = false, defaultValue=""  ) String observacion
            ,@RequestParam( value = "tiporequest",    required = false, defaultValue=""  ) String tiporequest
    ) throws Exception {
        try{
            List<ConvalecientesEnfermoDto> ultimregRec = null;
            if (volumen.equals("0") || volumen.equals("")){
                //Map<String, String> map = new HashMap<String, String>();
                //map.put("msj", "Volumen no puede ser igual a 0." );
                return JsonUtil.createJsonResponse("Volumen no puede ser igual a 0.");
            }
            if (fecha.equals("")){
                //Map<String, String> map = new HashMap<String, String>();
                //map.put("msj", "Fecha no puede ser vacía.");
                return JsonUtil.createJsonResponse("Fecha no puede ser vacía.");
            }
            if (!codigoParticipante.matches("^\\d{4}$")){
                //Map<String, String> map = new HashMap<String, String>();
                //map.put("msj", "Código participante no es válido." );
                return JsonUtil.createJsonResponse("Código participante no es válido.");
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


            Date  fechaF2 = formato.parse("2024-04-15");
            RecepcionEnfermo recepcionEnfermo = new RecepcionEnfermo();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            String ComputerIp = InetAddress.getLocalHost().getHostAddress();

            ultimregRec = this.recepcionEnfermoService.getMxRecepcionRegUltimo(codigoParticipante);

           // Date  fechaI1 = formato.parse(ultimregRec.get(0).getFechaRecepcion().toString());
            Date fechaI1;
            LocalDate localDate = LocalDate.now();
            if (ultimregRec.size() > 0) {
                 fechaI1 = formato.parse(ultimregRec.get(0).getFechaRecepcion().toString());
            }else{
                fechaI1 = formato.parse(localDate.toString());
            }

            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            if (tiporequest.equals("false")){//Guardar Nuevo Registro
                if (!recepcionEnfermoService.existeSerologia(DateUtil.StringToDate(fecha, Constants.STRING_FORMAT_DD_MM_YYYY), codigoParticipante)){
                    recepcionEnfermo.setIdRecepcion(UUID.randomUUID().toString());
                    recepcionEnfermo.setDeviceid(nameComputer);
                    recepcionEnfermo.setRecordIp(ComputerIp);
                    recepcionEnfermo.setEstado('1');
                    recepcionEnfermo.setPasive('0');
                    recepcionEnfermo.setRecordDate(new Date());
                    recepcionEnfermo.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    recepcionEnfermo.setParticipante(participante);
                    recepcionEnfermo.setEnviado("0");
                    recepcionEnfermo.setFechaRecepcion(DateUtil.StringToDate(fecha, Constants.STRING_FORMAT_DD_MM_YYYY));
                    if (fis != null && !fis.isEmpty()) {
                        recepcionEnfermo.setFis(DateUtil.StringToDate(fis, Constants.STRING_FORMAT_DD_MM_YYYY));
                    }
                    if (fif != null && !fif.isEmpty()) {
                        recepcionEnfermo.setFif(DateUtil.StringToDate(fif, Constants.STRING_FORMAT_DD_MM_YYYY));
                    }
                    recepcionEnfermo.setTipoTubo(Constants.TIPO_TUBO_SEROLOGIA);
                    recepcionEnfermo.setConsulta(tipoConsulta);
                    recepcionEnfermo.setCategoria(categoria);
                    recepcionEnfermo.setTipoMuestra(faseMuestra);
                    recepcionEnfermo.setEvento(evento);
                    String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                    recepcionEnfermo.setObservacion(obs);
                    recepcionEnfermo.setVolumen(Double.parseDouble(volumen));

                    //setear codigo a imprimir
                    String anio = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), "YY");
                    String fToma = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY);
                    String codigoMx = "";

                    if (faseMuestra.equals("1") ) {

                          ParticipanteProcesos pp = new ParticipanteProcesos();
                           pp = participanteProcesosService.getParticipante(codigoParticipante);
                           pp.setPendienteMxTx("1");
                           participanteProcesosService.saveOrUpdateParticipanteProc(pp);
                           codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "24", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());

                        //  codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                   // if (faseMuestra.equals("2")) {

                  //     if (  fechaI1.before(fechaF2) ) {


                   //    }
                  //    if ( fechaI1.after(fechaF2)) {

                           // codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "22", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                       // }




                    }
                    if ( faseMuestra.equals("2") ) {

                            ParticipanteProcesos pp1 = new ParticipanteProcesos();
                            pp1 = participanteProcesosService.getParticipante(codigoParticipante);
                            pp1.setPendienteMxTx("0");
                            participanteProcesosService.saveOrUpdateParticipanteProc(pp1);
                      //  if (faseMuestra.equals("2") && fechaI1.after(fechaF2)) {
                        if (faseMuestra.equals("2") ) {
                            codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "24", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                        }else
                        {
                            codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                        }
                    }
                    if (ultimregRec.size() > 0) {
                        if (ultimregRec.get(0).getCodigoBarra().substring(8, 11).equals("R24")) {
                            codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "24", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                        }
                    }
                    recepcionEnfermo.setCodigo(codigoMx);
                    recepcionEnfermo.setCodigoBarra(String.format(Constants.CODIGO_BARRA_FORMAT, fis, fToma, codigoMx));

                    recepcionEnfermoService.saveOrUpdateRecepcionEnfermo(recepcionEnfermo);
                    return JsonUtil.createJsonResponse(recepcionEnfermo);
               }
                else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Muestra ya existe." );
                    return JsonUtil.createJsonResponse(map);
                }
            }else{//Editar Registro
                recepcionEnfermo = this.recepcionEnfermoService.getRecepcionEnfermoById(idRecepcion);
                recepcionEnfermo.setDeviceid(nameComputer);
                recepcionEnfermo.setRecordIp(ComputerIp);
                recepcionEnfermo.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                recepcionEnfermo.setFechaRecepcion(DateUtil.StringToDate(fecha, Constants.STRING_FORMAT_DD_MM_YYYY));
                String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                recepcionEnfermo.setObservacion(obs);
                recepcionEnfermo.setVolumen(Double.parseDouble(volumen));
                if (fis != null && !fis.isEmpty()) {
                    recepcionEnfermo.setFis(DateUtil.StringToDate(fis, Constants.STRING_FORMAT_DD_MM_YYYY));
                }
                if (fif != null && !fif.isEmpty()) {
                    recepcionEnfermo.setFif(DateUtil.StringToDate(fif, Constants.STRING_FORMAT_DD_MM_YYYY));
                }
                recepcionEnfermo.setTipoTubo(Constants.TIPO_TUBO_SEROLOGIA);
                recepcionEnfermo.setConsulta(tipoConsulta);
                recepcionEnfermo.setCategoria(categoria);
                recepcionEnfermo.setEvento(evento);
                recepcionEnfermo.setTipoMuestra(faseMuestra);
                //setear codigo a imprimir
                String anio = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), "YY");

                String fToma = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY);
                String codigoMx=""; //= String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());

              //  recepcionEnfermo.setCodigo(codigoMx);
              //  recepcionEnfermo.setCodigoBarra(String.format(Constants.CODIGO_BARRA_FORMAT, fis, fToma, codigoMx));
                if (faseMuestra.equals("1")) {

                    ParticipanteProcesos pp = new ParticipanteProcesos();
                    pp = participanteProcesosService.getParticipante(codigoParticipante);
                    pp.setPendienteMxTx("1");
                    participanteProcesosService.saveOrUpdateParticipanteProc(pp);

                    //  codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                    // if (faseMuestra.equals("2")) {

                    //     if (  fechaI1.before(fechaF2) ) {


                    //    }
                    //    if ( fechaI1.after(fechaF2)) {

                    // codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "22", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                    // }




                }
                if ( faseMuestra.equals("2") ) {
                    ParticipanteProcesos pp1 = new ParticipanteProcesos();
                    pp1 = participanteProcesosService.getParticipante(codigoParticipante);
                    pp1.setPendienteMxTx("0");
                    participanteProcesosService.saveOrUpdateParticipanteProc(pp1);
                }

                if(ultimregRec.get(0).getCodigoBarra().substring(8,11).equals("R23")){
                    codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                }
                if(ultimregRec.get(0).getCodigoBarra().substring(8,11).equals("R24")){
                    codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "24", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                }

              //  codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), "23", recepcionEnfermo.getEvento(), recepcionEnfermo.getTipoMuestra());
                recepcionEnfermo.setCodigo(codigoMx);
                recepcionEnfermo.setCodigoBarra(String.format(Constants.CODIGO_BARRA_FORMAT, fis, fToma, codigoMx));



                recepcionEnfermoService.saveOrUpdateRecepcionEnfermo(recepcionEnfermo);
                return JsonUtil.createJsonResponse(recepcionEnfermo);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping( value="/delete", method=RequestMethod.POST)
    public ResponseEntity<String> pasivarRecepcionEnfermo( @RequestParam(value="idAccion", required=true ) String idAccion
            , @RequestParam( value="message_razon", required=true, defaultValue="" ) String message_razon ) {
        try{
            RecepcionEnfermo serologia = this.recepcionEnfermoService.getRecepcionEnfermoById(idAccion);
            if (serologia!=null) {
                serologia.setPasive('1');
                serologia.setObservacion(message_razon.toUpperCase());
                this.recepcionEnfermoService.saveOrUpdateRecepcionEnfermo(serologia);
            }
            return JsonUtil.createJsonResponse(serologia);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/sendAllSerologies", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> sendAllSerologias(@RequestParam(value="numenvio", required=false ) Integer numenvio
            ,@RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="fechaEnvio", required=false ) String fechaEnvio
            ,@RequestParam(value="horaEnvio", required=false ) String horaEnvio
            ,@RequestParam(value="sitio", required=true ) Integer sitio
            ,@RequestParam(value="temperatura", required=true ) String temperatura
    )throws Exception {
        try {
            String codigosImprimir = "";
            Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            MuestraEnfermoEnvio envio = new MuestraEnfermoEnvio();
            envio.setDeviceid(computerName);
            envio.setRecordIp(computerIp);
            envio.setEstado('1');
            envio.setPasive('0');
            envio.setRecordDate(new Date());
            envio.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            envio.setFecha(DateUtil.StringToDate(fechaEnvio, "dd/MM/yyyy"));
            envio.setHora(horaEnvio);
            envio.setNumeroEnvio(numenvio);
            envio.setSitio(sitio);
            double temp = Double.parseDouble(temperatura);
            envio.setTemperatura(temp);
            List<RecepcionEnfermo> listaNoEnviadas = this.recepcionEnfermoService.getSerologiaEnfermosNoEnviadas(fdesde, fhasta);// Serologia en pasivo='0'  y enviada='0'
            if (listaNoEnviadas.size() > 0) {
                codigosImprimir = this.recepcionEnfermoService.saveMuestraEnfermoDetalleEnvio(envio, listaNoEnviadas);
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("total", "Registros enviados: " + listaNoEnviadas.size());
            map.put("etiquetas", codigosImprimir);
            return JsonUtil.createJsonResponse(map);

        } catch (Exception ex) {
            return JsonUtil.createJsonResponse(ex.getMessage());
        }
    }


    @RequestMapping(value = "/generarReporte", method = RequestMethod.GET, produces = "application/json")
    public String listEnviosMuestras(Model model)throws Exception{
        List<MessageResource> numeroEnvio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numeroEnvio", numeroEnvio);
        return "/mxEnfermos/envioForm";
    }

    @RequestMapping(value = "search-mx", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionEnfermoDto> buscarMuestra(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="participante", required=false ) String participante
            ,@RequestParam(value="muestra", required=false ) String muestra
    ) throws Exception {
        logger.debug("buscar participante para recepcion mx enfermo");

        FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
        filtro.setCodigoMx(muestra);
        filtro.setParticipante(participante);
        filtro.setFechaInicio(DateUtil.StringToDate(desde+" 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
        filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
        UnicodeEscaper escaper = UnicodeEscaper.above(127);

        List<RecepcionEnfermoDto> recepciones = this.recepcionEnfermoService.getSerologiaEnfermosDto(filtro);
        for (RecepcionEnfermoDto recepcionEnfermo : recepciones) {
            recepcionEnfermo.setCodigoBarra(escaper.translate(String.format(Constants.CODIGO_BARRA_FORMAT_PRINT, recepcionEnfermo.getCodigoBarra().replace("A2", "*A2"))));
        }
        return recepciones;
    }

}
