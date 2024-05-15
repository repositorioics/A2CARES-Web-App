package ni.org.ics.webapp.web.controller.Serologia;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.webapp.domain.audit.CorrectionsTrail;
import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.clinical.HojaClinicaDobleDigitacion;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.DiferenciasHojasDigitadasDto;
import ni.org.ics.webapp.dto.HojaClinicaDobleDDto;
import ni.org.ics.webapp.dto.HojaClinicaDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.service.HojaClinicaDDService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by miguel on 27/8/2021.
 */
@Controller
@RequestMapping("/hojaclinicaDD/*")
public class HojaClinicaDobleDController {

    private static final Logger logger = LoggerFactory.getLogger(HojaClinicaDobleDController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "personalService")
    private PersonalService personalService;

    @Resource(name = "hojaClinicaDDService")
    private HojaClinicaDDService hojaClinicaDDService;

    @Resource(name = "hojaClinicaService")
    private HojaClinicaService hojaClinicaService;

    private String codpart;
    private Date fechreg1;
    private String usuarreg1;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando hojas clinicas en JSP");
        return "fingering/clinicalSheetListDD";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) throws ParseException {
        logger.debug("Mostrando formulario registro hoja clinica en JSP");
        List<MessageResource> catSiNo = this.messageResourceService.getCatalogo("CAT_SINO");
        List<MessageResource> catSexo = this.messageResourceService.getCatalogo("CAT_SEXO");
        List<MessageResource> catCategoria = this.messageResourceService.getCatalogo("CAT_CATEGORIA");
        List<MessageResource> catTipoConsulta = this.messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
        List<MessageResource> catLugarConsulta = this.messageResourceService.getCatalogo("CAT_LUGAR_CONS_HC");
        List<MessageResource> CAT_DIAGNOSTICOS = this.messageResourceService.getCatalogo("CAT_DIAGNOSTICOS");
        List<Personal> medicos = this.personalService.getByCargo("CAT_CARGO_1");
        List<Personal> enfermeria = this.personalService.getByCargo("CAT_CARGO_2");

        //List<UserSistema> usuarios = usuarioService.getUsers();
        //model.addAttribute("usuarios", usuarios);
        model.addAttribute("catSiNo", catSiNo);
        model.addAttribute("catSexo", catSexo);
        model.addAttribute("catCategoria", catCategoria);
        model.addAttribute("catTipoConsulta", catTipoConsulta);
        model.addAttribute("catLugarConsulta", catLugarConsulta);
        model.addAttribute("medicos", medicos);
        model.addAttribute("enfermeria", enfermeria);
        model.addAttribute("CAT_DIAGNOSTICOS", CAT_DIAGNOSTICOS);
        return "fingering/clinicalSheetDD";
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) String codigo) throws ParseException {
        logger.debug("buscar participante");
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            if (procesos != null && procesos.getRetirado().equals(1)) {
                //  return JsonUtil.createJsonResponse("Participante retirado, confirmar que desea realizar digitación");
                map.put("codigo", participante.getCodigo());
                map.put("nombre", participante.getNombreCompleto());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));
                map.put("edad", participante.getEdad());
                map.put("sexo", participante.getSexo());
                map.put("retirado", "1");
            }  else {
                map.put("codigo", participante.getCodigo());
                map.put("nombre", participante.getNombreCompleto());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));
                map.put("edad", participante.getEdad());
                map.put("sexo", participante.getSexo());
            }
        }
        else
            return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        return JsonUtil.createJsonResponse(map);
    }

    @RequestMapping(value = "getNumHojaDigitadaH1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getNumHojaDigitadaH1(@RequestParam(value="numHoja", required=false ) Integer numHoja) throws ParseException {
        try {
            logger.debug("buscar numHoja");
            logger.debug("numeroHoja" + numHoja);
            if (numHoja != null) {
                String numeroH;
                numeroH = hojaClinicaService.getNumHojaDigitadaH1(numHoja);
                logger.debug("regresoSP: " + numeroH.toString());
                if (numeroH.equals("[1]")) {
                    return JsonUtil.createJsonResponse("Número de Hoja ingresada existe en DIGITACION 1");
                } else
                    return JsonUtil.createJsonResponse("Número de Hoja ingresada NO existe en DIGITACION 1");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        // return null;
    }

    @RequestMapping(value = "getNumHojaDigitadaH2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getNumHojaDigitadaH2(@RequestParam(value="numHoja", required=false ) Integer numHoja) throws ParseException {
        try {
            logger.debug("buscar numHoja");
            if (numHoja != null) {
                String numeroH;
                numeroH = hojaClinicaService.getNumHojaDigitadaH2(numHoja);
                if (numeroH.equals("[1]")) {
                    return JsonUtil.createJsonResponse("Número de Hoja ingresada existe en DIGITACION 2");
                } else
                    return JsonUtil.createJsonResponse("Número de Hoja ingresada NO existe en DIGITACION 2");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        // return null;
    }


    @RequestMapping(value = "saveEdit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    protected void saveEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Guardando Corrección de hoja clinica");
        String resultado = "";
        String usrreg;
        Date usrfec;
        String error = "";
        try {

            String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);



            HojaClinica hojaClinica = ParseJsonRequestToHojaClinica1(request);

            usrfec = hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HC1(codpart,hojaClinica.getNumHojaConsulta());
            usrreg = hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HC2(codpart,hojaClinica.getNumHojaConsulta());

            //Date datefr = simpleDateFormat.parse(usrreg);
            logger.debug("RECUPERANDO USAURIO Y FECHA REGISTRO: " + usrfec);
            hojaClinica.setRecordDate((usrfec) ) ;
            hojaClinica.setRecordUser(usrreg) ;
            hojaClinica.setFechaModifica(new Date()) ;
            hojaClinica.setUsuarioModifica(SecurityContextHolder.getContext().getAuthentication().getName());



            hojaClinicaService.saveOrUpdate(hojaClinica);
            hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HC2(codpart,hojaClinica.getNumHojaConsulta());

            /*GUARDANDO HOJA EN DOBLE DIGITIACION*/

            HojaClinicaDobleDigitacion hojaClinica2 = new HojaClinicaDobleDigitacion();

            hojaClinica2 = this.hojaClinicaService.getObtenerRegHojaClinicaCorrecH2(codpart,hojaClinica.getNumHojaConsulta());

            usrfec = hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HCDD1(codpart,hojaClinica.getNumHojaConsulta());
           String usrreg1 = hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HCDD2(codpart,hojaClinica.getNumHojaConsulta());

            //Date datefr = simpleDateFormat.parse(usrreg);
            logger.debug("RECUPERANDO USAURIO Y FECHA REGISTRO: " + usrreg1);
            hojaClinica2.setRecordDate((usrfec) ) ;
            hojaClinica2.setRecordUser(usrreg1) ;
            hojaClinica2.setFechaModifica(new Date()) ;
            hojaClinica2.setUsuarioModifica(SecurityContextHolder.getContext().getAuthentication().getName());
/**/
            hojaClinica2.setCodigoParticipante(hojaClinica.getCodigoParticipante());


            //   hojaClinica.setCodigoParticipante(participante);
            hojaClinica2.setFechaConsulta(hojaClinica.getFechaConsulta());
            hojaClinica2.setHoraConsulta(hojaClinica.getHoraConsulta());
            logger.debug("CODIGO SUPERVISOR: " + hojaClinica.getCodigo_supervisor());
            hojaClinica2.setCodigo_supervisor(hojaClinica.getCodigo_supervisor());
            hojaClinica2.setNumHojaConsulta(hojaClinica.getNumHojaConsulta());

            hojaClinica2.setPesoKg(hojaClinica.getPesoKg());
            hojaClinica2.setTallaCm(hojaClinica.getTallaCm());
            hojaClinica2.setPresion(hojaClinica.getPresion());
            hojaClinica2.setFciaCard(hojaClinica.getFciaCard());
            hojaClinica2.setTemperaturac(hojaClinica.getTemperaturac());
            hojaClinica2.setSaturaciono2(hojaClinica.getSaturaciono2());
            hojaClinica2.setHoraInicioConsulta(hojaClinica.getHoraInicioConsulta());
            hojaClinica2.setConsulta(hojaClinica.getConsulta());
            hojaClinica2.setLugarAtencion(hojaClinica.getLugarAtencion());
            hojaClinica2.setPresionMed(hojaClinica.getPresionMed());
            hojaClinica2.setTemMedc(hojaClinica.getTemMedc());
            hojaClinica2.setFciaRespMed(hojaClinica.getFciaRespMed());
            hojaClinica2.setFciaCardMed(hojaClinica.getFciaCardMed());
            hojaClinica2.setSaturaciono2Med(hojaClinica.getSaturaciono2Med());
            hojaClinica2.setFis(hojaClinica.getFis());
            hojaClinica2.setFif(hojaClinica.getFif());
            hojaClinica2.setUltDiaFiebre(hojaClinica.getUltDiaFiebre());
            hojaClinica2.setHoraultDiaFiebre(hojaClinica.getHoraultDiaFiebre());
            hojaClinica2.setUltDosisAntipiretico(hojaClinica.getUltDosisAntipiretico());
            hojaClinica2.setHoraUltDosisAntipiretico(hojaClinica.getHoraUltDosisAntipiretico());
            hojaClinica2.setFiebre(hojaClinica.getFiebre());
            hojaClinica2.setAsomnoliento(hojaClinica.getAsomnoliento());
            hojaClinica2.setMalEstado(hojaClinica.getMalEstado());
            hojaClinica2.setPerdidaConsciencia(hojaClinica.getPerdidaConsciencia());
            hojaClinica2.setInquieto(hojaClinica.getInquieto());
            hojaClinica2.setConvulsiones(hojaClinica.getConvulsiones());
            hojaClinica2.setLetargia(hojaClinica.getLetargia());
            hojaClinica2.setDolorCabeza(hojaClinica.getDolorCabeza());
            hojaClinica2.setConjuntivitis(hojaClinica.getConjuntivitis());
            hojaClinica2.setHemorragiaSuconjuntival(hojaClinica.getHemorragiaSuconjuntival());
            hojaClinica2.setDolorRetroocular(hojaClinica.getDolorRetroocular());
            hojaClinica2.setDolorGarganta(hojaClinica.getDolorGarganta());
            hojaClinica2.setEritema(hojaClinica.getEritema());
            hojaClinica2.setAdenopatiasCervicales(hojaClinica.getAdenopatiasCervicales());
            hojaClinica2.setExudado(hojaClinica.getExudado());
            hojaClinica2.setPetequiasMucosa(hojaClinica.getPetequiasMucosa());
            hojaClinica2.setTos(hojaClinica.getTos());
            hojaClinica2.setRinorrea(hojaClinica.getRinorrea());
            hojaClinica2.setCongestionNasal(hojaClinica.getCongestionNasal());
            hojaClinica2.setOtalgia(hojaClinica.getOtalgia());
            hojaClinica2.setAleteoNasal(hojaClinica.getAleteoNasal());
            hojaClinica2.setRespiracionRapida(hojaClinica.getRespiracionRapida());
            hojaClinica2.setEstridorReposo(hojaClinica.getEstridorReposo());
            hojaClinica2.setTirajeSubcostal(hojaClinica.getTirajeSubcostal());
            hojaClinica2.setSibilancias(hojaClinica.getSibilancias());
            hojaClinica2.setCrepitos(hojaClinica.getCrepitos());
            hojaClinica2.setRoncos(hojaClinica.getRoncos());
            hojaClinica2.setDisnea(hojaClinica.getDisnea());
            hojaClinica2.setPocoApetito(hojaClinica.getPocoApetito());
            hojaClinica2.setNausea(hojaClinica.getNausea());
            hojaClinica2.setVomito12horas(hojaClinica.getVomito12horas());
            hojaClinica2.setNumeroVomito12h(hojaClinica.getNumeroVomito12h());
            hojaClinica2.setDiarrea(hojaClinica.getDiarrea());
            hojaClinica2.setHepatomegalia(hojaClinica.getHepatomegalia());
            hojaClinica2.setDolorAbdominal(hojaClinica.getDolorAbdominal());
            hojaClinica2.setArtralgia(hojaClinica.getArtralgia());
            hojaClinica2.setMialgia(hojaClinica.getMialgia());
            hojaClinica2.setLumbalgia(hojaClinica.getLumbalgia());
            hojaClinica2.setDolorCuello(hojaClinica.getDolorCuello());
            hojaClinica2.setEdema(hojaClinica.getEdema());
            hojaClinica2.setRahsLocalizado(hojaClinica.getRahsLocalizado());
            hojaClinica2.setRahsGeneralizado(hojaClinica.getRahsGeneralizado());
            hojaClinica2.setRashEritematoso(hojaClinica.getRashEritematoso());
            hojaClinica2.setRahsMacular(hojaClinica.getRahsMacular());
            hojaClinica2.setRashPapular(hojaClinica.getRashPapular());
            hojaClinica2.setPielMoteada(hojaClinica.getPielMoteada());
            hojaClinica2.setRuborFacial(hojaClinica.getRuborFacial());
            hojaClinica2.setCianosisCentral(hojaClinica.getCianosisCentral());
            hojaClinica2.setIctericia(hojaClinica.getIctericia());
            hojaClinica2.setImc(hojaClinica.getImc());
            hojaClinica2.setObeso(hojaClinica.getObeso());
            hojaClinica2.setSobrepeso(hojaClinica.getSobrepeso());
            hojaClinica2.setSospechaProblema(hojaClinica.getSospechaProblema());
            hojaClinica2.setNormal(hojaClinica.getNormal());
            hojaClinica2.setBajoPeso(hojaClinica.getBajoPeso());
            hojaClinica2.setBajoPesoSevero(hojaClinica.getBajoPesoSevero());
            hojaClinica2.setCategoria(hojaClinica.getCategoria());
            hojaClinica2.setCambioCategoria(hojaClinica.getCambioCategoria());
            hojaClinica2.setPruebaTorniquetePositiva(hojaClinica.getPruebaTorniquetePositiva());
            hojaClinica2.setPetequia10Pt(hojaClinica.getPetequia10Pt());
            hojaClinica2.setPetequia20Pt(hojaClinica.getPetequia20Pt());
            hojaClinica2.setPielExtremidadesFrias(hojaClinica.getPielExtremidadesFrias());
            hojaClinica2.setPalidezEnExtremidades(hojaClinica.getPalidezEnExtremidades());
            hojaClinica2.setEpistaxis(hojaClinica.getEpistaxis());
            hojaClinica2.setGingivorragia(hojaClinica.getGingivorragia());
            hojaClinica2.setPetequiasEspontaneas(hojaClinica.getPetequiasEspontaneas());
            hojaClinica2.setLlenadoCapilar2seg(hojaClinica.getLlenadoCapilar2seg());
            hojaClinica2.setCianosis(hojaClinica.getCianosis());
            hojaClinica2.setHipermenorrea(hojaClinica.getHipermenorrea());
            hojaClinica2.setHematemesis(hojaClinica.getHematemesis());
            hojaClinica2.setHemoconcentracion(hojaClinica.getHemoconcentracion());
            hojaClinica2.setHospitalizado(hojaClinica.getHospitalizado());
            hojaClinica2.setHospitalizadoEspecificar(hojaClinica.getHospitalizadoEspecificar());
            hojaClinica2.setTransfusionSangre(hojaClinica.getTransfusionSangre());
            hojaClinica2.setTransfusionEspecificar(hojaClinica.getTransfusionEspecificar());
            hojaClinica2.setTomandoMedicamento(hojaClinica.getTomandoMedicamento());
            hojaClinica2.setMedicamentoEspecificar(hojaClinica.getMedicamentoEspecificar());
            hojaClinica2.setBhc(hojaClinica.getBhc());
            hojaClinica2.setSerologiaArbovirus(hojaClinica.getSerologiaArbovirus());
            hojaClinica2.setGotaGruesa(hojaClinica.getGotaGruesa());
            hojaClinica2.setEgo(hojaClinica.getEgo());
            hojaClinica2.setEgh(hojaClinica.getEgh());
            hojaClinica2.setOtroExamenLab(hojaClinica.getOtroExamenLab());
            hojaClinica2.setOtroExamanLabEspecificar(hojaClinica.getOtroExamanLabEspecificar());
            hojaClinica2.setAcetaminofen(hojaClinica.getAcetaminofen());
            hojaClinica2.setAmoxicilina(hojaClinica.getAmoxicilina());
            hojaClinica2.setDicloxacilina(hojaClinica.getDicloxacilina());
            hojaClinica2.setPenicilina(hojaClinica.getPenicilina());
            hojaClinica2.setFurazolidona(hojaClinica.getFurazolidona());
            hojaClinica2.setMetronidazolTinidazol(hojaClinica.getMetronidazolTinidazol());
            hojaClinica2.setAlbendazolMebendazol(hojaClinica.getAlbendazolMebendazol());
            hojaClinica2.setSueroOral(hojaClinica.getSueroOral());
            hojaClinica2.setLiquidos4(hojaClinica.getLiquidos4());
            hojaClinica2.setReferencia_por_dengue(hojaClinica.getReferencia_por_dengue());
            hojaClinica2.setOtroTratamiento(hojaClinica.getOtroTratamiento());
            hojaClinica2.setOtroTratamientoEspecificar(hojaClinica.getOtroTratamientoEspecificar());
            hojaClinica2.setPlanes(hojaClinica.getPlanes());
            hojaClinica2.setHistoriaClinica(hojaClinica.getHistoriaClinica());
            hojaClinica2.setDiagnostico(hojaClinica.getDiagnostico());

            hojaClinica2.setDiagnostico1(hojaClinica.getDiagnostico1());
            hojaClinica2.setDiagnostico2(hojaClinica.getDiagnostico2());
            hojaClinica2.setDiagnostico3(hojaClinica.getDiagnostico3());
            hojaClinica2.setDiagnostico4(hojaClinica.getDiagnostico4());

            hojaClinica2.setTelefonoEmergencia(hojaClinica.getTelefonoEmergencia());
            hojaClinica2.setProximaCita(hojaClinica.getProximaCita());
            hojaClinica2.setMedico(hojaClinica.getMedico());
            hojaClinica2.setFechaMedico(hojaClinica.getFechaMedico());
            hojaClinica2.setHoraMedico(hojaClinica.getHoraMedico());
            hojaClinica2.setEnfermeria(hojaClinica.getEnfermeria());
            hojaClinica2.setFechaEnfermeria(hojaClinica.getFechaEnfermeria());
            hojaClinica2.setHoraEnfermeria(hojaClinica.getHoraEnfermeria());

    /**/        hojaClinicaService.saveOrUpdate2(hojaClinica2);
            //hojaClinicaService.getfn_guardar_usuario_fecha_Modifica_HC2(codpart,hojaClinica.getNumHojaConsulta());


            resultado = "Hoja Clinica " + hojaClinica.getNumHojaConsulta() + " del participante "+hojaClinica.getCodigoParticipante().getCodigo()+" creada exitosamente";
        }catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            ex.printStackTrace();
            error = "error al guardar hoja clinica";// messageResourceService.getMensaje("msg.receipt.error").getSpanish();
            error=error+". \n "+ex.getMessage();
        } finally {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mensaje",resultado);
            map.put("error",error);
            //JsonUtil.createJsonResponse(map);
            String jsonResponse = new Gson().toJson(map);
            response.getOutputStream().write(jsonResponse.getBytes());
            response.getOutputStream().close();
        }

    }


    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Guardando hoja clinica");
        String resultado = "";
        String error = "";
        try {
          /*  HojaClinicaDobleDigitacion  hojaClinica = ParseJsonRequestToHojaClinica(request);
            hojaClinica.setRecordDate(new Date());
            hojaClinica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            hojaClinicaDDService.saveOrUpdate2(hojaClinica);*/

            HojaClinicaDobleDigitacion hojaClinica = ParseJsonRequestToHojaClinica(request);
            hojaClinica.setRecordDate(new Date());
            hojaClinica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            //para nuevos registros es necesario poner un valor por defecto en el numero de hoja, aunque se setee con el trigger tg_set_numero_hoja
            //
            // hojaClinica.setNumHojaConsulta(0);
            /**/



            /**/

            hojaClinicaDDService.saveOrUpdate(hojaClinica);

         /*   HojaClinica hojaClinica1 = ParseJsonRequestToHojaClinica1(request);
            hojaClinica1.setRecordDate(new Date());
            hojaClinica1.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            hojaClinicaDDService.saveOrUpdate1(hojaClinica1);*/
            //para nuevos registros es necesario poner un valor por defecto en el numero de hoja, aunque se setee con el trigger tg_set_numero_hoja
           //
           // hojaClinica.setNumHojaConsulta(0);



            resultado = "Hoja Clinica del participante "+hojaClinica.getCodigoParticipante().getCodigo()+" creada exitosamente";
        }catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            ex.printStackTrace();
            error = "error al guardar hoja clinica";// messageResourceService.getMensaje("msg.receipt.error").getSpanish();
            error=error+". \n "+ex.getMessage();
        } finally {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mensaje",resultado);
            map.put("error",error);
            //JsonUtil.createJsonResponse(map);
            String jsonResponse = new Gson().toJson(map);
            response.getOutputStream().write(jsonResponse.getBytes());
            response.getOutputStream().close();
        }

    }


    @RequestMapping(value = "get", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> get(@RequestParam(value="codigo", required=false ) String codigo,
                               @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
                               @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
       try {
            logger.debug("buscar hojas clinicas");
            if (codigo != null || ((fechaInicioCons != null && !fechaInicioCons.isEmpty()) && (fechaFinCons != null && !fechaFinCons.isEmpty()))) {
                List<HojaClinicaDobleDDto> hojaClinicaList = this.hojaClinicaDDService.get(codigo, DateUtil.StringToDate(fechaInicioCons, "dd/MM/yyyy"), DateUtil.StringToDate(fechaFinCons, "dd/MM/yyyy"));
                if (hojaClinicaList.size() > 0) {
                    return JsonUtil.createJsonResponse(hojaClinicaList);
                } else
                    return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDobleDDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getcodSupervisor", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getcodSupervisor(@RequestParam(value="codigoSupervisor", required=false ) String codigoSupervisor) throws ParseException {
        try {
            logger.debug("buscar codigo Supervisor");
            if (codigoSupervisor != null) {
                List<String> nombreMedico;
                nombreMedico = hojaClinicaDDService.getcodigoSupervisor(codigoSupervisor);
                if (!nombreMedico.isEmpty()) {
                    return JsonUtil.createJsonResponse(nombreMedico);
                } else
                    return JsonUtil.createJsonResponse("No se encontró Médico según el código ingresado");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDobleDDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
       // return null;
    }



    private HojaClinicaDobleDigitacion ParseJsonRequestToHojaClinica(HttpServletRequest request) throws Exception{
        HojaClinicaDobleDigitacion hojaClinica = new HojaClinicaDobleDigitacion();
        String json = "";

        Date fechaConsulta = null;
        Time horaConsulta = null;
        Integer numeroHoja = null;
        //Datos de enfermería
        BigDecimal pesoKg = null;
        BigDecimal tallaCm = null;
        String presion = null;
        Short fciaCard = null;
        BigDecimal temperaturac = null;
        Short saturaciono2 = null;

        //Datos para llenar el médico.
        Time horaInicioConsulta = null;
        String consulta = null;
        String lugarAtencion = null;
        String presionMed = null;
        BigDecimal temMedc = null;
        Short fciaRespMed = null;
        Short fciaCardMed = null;
        Short saturaciono2Med = null;
        Date fis = null;
        Date fif = null;
        Date ultDiaFiebre = null;
        Time horaultDiaFiebre = null;
        Date ultDosisAntipiretico = null;
        Time horaUltDosisAntipiretico = null;
        //sintomas
        //General
        String fiebre = null;
        String asomnoliento = null;
        String malEstado = null;
        String perdidaConsciencia = null;
        String inquieto = null;
        String convulsiones = null;
        String letargia = null;
        //cabeza
        String dolorCabeza = null;
        String conjuntivitis = null;
        String hemorragiaSuconjuntival = null;
        String dolorRetroocular = null;
        //Garganta
        String dolorGarganta = null;
        String eritema = null;
        String adenopatiasCervicales = null;
        String exudado = null;
        String petequiasMucosa = null;
        //Respiratorio
        String tos = null;
        String rinorrea = null;
        String congestionNasal = null;
        String otalgia = null;
        String aleteoNasal = null;
        String respiracionRapida = null;
        String estridorReposo = null;
        String tirajeSubcostal = null;
        String sibilancias = null;
        String crepitos = null;
        String roncos = null;
        String disnea = null;
        //Gastrointestinal
        String pocoApetito = null;
        String nausea = null;
        String vomito12horas = null;
        Short numeroVomito12h = null;
        String diarrea = null;
        String hepatomegalia = null;
        String dolorAbdominal = null;
        //Osteomuscular
        String artralgia = null;
        String mialgia = null;
        String lumbalgia = null;
        String dolorCuello = null;
        String edema = null;
        //Cutáneo
        String rahsLocalizado = null;
        String rahsGeneralizado = null;
        String rashEritematoso = null;
        String rahsMacular = null;
        String rashPapular = null;
        String pielMoteada = null;
        String ruborFacial = null;
        String cianosisCentral = null;
        String ictericia = null;
        //Estado nutricional
        BigDecimal imc = null;
        String obeso = null;
        String sobrepeso = null;
        String sospechaProblema = null;
        String normal = null;
        String bajoPeso = null;
        String bajoPesoSevero = null;
        //categoria
        String categoria = null;
        String cambioCategoria = null;
        //Manifestaciones hemorrágicas
        String pruebaTorniquetePositiva = null;
        String petequia10Pt = null;
        String petequia20Pt = null;
        String pielExtremidadesFrias = null;
        String palidezEnExtremidades = null;
        String epistaxis = null;
        String gingivorragia = null;
        String petequiasEspontaneas = null;
        String llenadoCapilar2seg = null;
        String cianosis = null;
        String hipermenorrea = null;
        String hematemesis = null;
        String hemoconcentracion = null;
        //Preguntas para todos los pacientes
        String hospitalizado = null;
        String hospitalizadoEspecificar = null;
        String transfusionSangre = null;
        String transfusionEspecificar = null;
        String tomandoMedicamento = null;
        String medicamentoEspecificar = null;
        //Exámenes del laboratorio
        String bhc = null;
        String serologiaArbovirus = null;
        String gotaGruesa = null;
        String ego = null;
        String egh = null;
        String otroExamenLab = null;
        String otroExamanLabEspecificar = null;
        //Tratamiento
        String acetaminofen = null;
        String amoxicilina = null;
        String dicloxacilina = null;
        String penicilina = null;
        String furazolidona = null;
        String metronidazolTinidazol = null;
        String albendazolMebendazol = null;
        String sueroOral = null;
        String liquidos4 = null;
        String referenciaPordengue = null;
        String otroTratamiento = null;
        String otroTratamientoEspecificar = null;
        //planes, historia y diagnostico
        String planes = null;
        String historiaClinica = null;
        String diagnostico = null;

        String diagnostico1 = null;
        String diagnostico2 = null;
        String diagnostico3 = null;
        String diagnostico4 = null;
        String codigosupe = null;

        //Cierre
        String telefonoEmergencia = null;
        Date proximaCita = null;
        Short medico = null;
        Date fechaMedico = null;
        Time horaMedico = null;
        Short enfermeria = null;
        Date fechaEnfermeria = null;
        Time horaEnfermeria = null;

     //   DiferenciasHojasDigitadasDto hojaClinicaList = this.hojaClinicaService.getObtenerRegHojaClinica(codigo_participante1,numero_hoja);



        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String hojaStr = jObjectRespuestas.get("hojaClinicaDD").toString();
        String codigoParticipante = jObjectRespuestas.get("participante").getAsString();

        Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
        hojaClinica.setCodigoParticipante(participante);

        List<CorrectionsTrail> listaCorrecciones = new ArrayList<CorrectionsTrail>();
       // String valoranterior = resultSet.getString(propiedad);

        JsonObject jsonpObject = new Gson().fromJson(hojaStr, JsonObject.class);

        if (jsonpObject.get("codSup1")!=null && !jsonpObject.get("codSup1").getAsString().isEmpty()) codigosupe = jsonpObject.get("codSup1").getAsString();
        logger.debug("codigo supervisor:" + codigosupe);
        //se guardan en bitacora correciones los datos
        //listaCorrecciones.add(fillCorrection("A2CARES-CLINICO",id, "codigoSupervisor", "", codigosupe, "UPDATE TABLA HOJA CLINICA "+ "hoja_clinica"));
        //this.correctionsTrailService.saveCorrectionsTrailList(listaCorrecciones);

        if (jsonpObject.get("fechaCons")!=null && !jsonpObject.get("fechaCons").getAsString().isEmpty()) fechaConsulta = DateUtil.StringToDate(jsonpObject.get("fechaCons").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaCons")!=null && !jsonpObject.get("horaCons").getAsString().isEmpty()) horaConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaCons").getAsString()));

        if (jsonpObject.get("numHoja1")!=null && !jsonpObject.get("numHoja1").getAsString().isEmpty()) numeroHoja = Integer.valueOf((jsonpObject.get("numHoja1").getAsString()));
        logger.debug("numero de Hoja:" + numeroHoja);

        if (jsonpObject.get("peso")!=null && !jsonpObject.get("peso").getAsString().isEmpty()) pesoKg = jsonpObject.get("peso").getAsBigDecimal();
        if (jsonpObject.get("talla")!=null && !jsonpObject.get("talla").getAsString().isEmpty()) tallaCm = jsonpObject.get("talla").getAsBigDecimal();
        if (jsonpObject.get("pa")!=null && !jsonpObject.get("pa").getAsString().isEmpty()) presion = jsonpObject.get("pa").getAsString();
        if (jsonpObject.get("fc")!=null && !jsonpObject.get("fc").getAsString().isEmpty()) fciaCard = jsonpObject.get("fc").getAsShort();
        if (jsonpObject.get("temp")!=null && !jsonpObject.get("temp").getAsString().isEmpty()) temperaturac = jsonpObject.get("temp").getAsBigDecimal();
        if (jsonpObject.get("so")!=null && !jsonpObject.get("so").getAsString().isEmpty()) saturaciono2 = jsonpObject.get("so").getAsShort();
        if (jsonpObject.get("horaIniCons")!=null && !jsonpObject.get("horaIniCons").getAsString().isEmpty()) horaInicioConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaIniCons").getAsString()));
        if (jsonpObject.get("tipoConsulta")!=null && !jsonpObject.get("tipoConsulta").getAsString().isEmpty()) consulta = jsonpObject.get("tipoConsulta").getAsString();
        if (jsonpObject.get("lugarConsulta")!=null && !jsonpObject.get("lugarConsulta").getAsString().isEmpty()) lugarAtencion = jsonpObject.get("lugarConsulta").getAsString();
        if (jsonpObject.get("paMedico")!=null && !jsonpObject.get("paMedico").getAsString().isEmpty()) presionMed = jsonpObject.get("paMedico").getAsString();
        if (jsonpObject.get("tempMedico")!=null && !jsonpObject.get("tempMedico").getAsString().isEmpty()) temMedc = jsonpObject.get("tempMedico").getAsBigDecimal();
        if (jsonpObject.get("frMedico")!=null && !jsonpObject.get("frMedico").getAsString().isEmpty()) fciaRespMed = jsonpObject.get("frMedico").getAsShort();
        if (jsonpObject.get("fcMedico")!=null && !jsonpObject.get("fcMedico").getAsString().isEmpty()) fciaCardMed = jsonpObject.get("fcMedico").getAsShort();
        if (jsonpObject.get("soMedico")!=null && !jsonpObject.get("soMedico").getAsString().isEmpty()) saturaciono2Med = jsonpObject.get("soMedico").getAsShort();
        if (jsonpObject.get("fis")!=null && !jsonpObject.get("fis").getAsString().isEmpty()) fis = DateUtil.StringToDate(jsonpObject.get("fis").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("fif")!=null && !jsonpObject.get("fif").getAsString().isEmpty()) fif = DateUtil.StringToDate(jsonpObject.get("fif").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("ultimoDiaFiebre")!=null && !jsonpObject.get("ultimoDiaFiebre").getAsString().isEmpty()) ultDiaFiebre = DateUtil.StringToDate(jsonpObject.get("ultimoDiaFiebre").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimoDiaF")!=null && !jsonpObject.get("horaUltimoDiaF").getAsString().isEmpty()) horaultDiaFiebre = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimoDiaF").getAsString()));
        if (jsonpObject.get("ultimaDosisAntip")!=null && !jsonpObject.get("ultimaDosisAntip").getAsString().isEmpty()) ultDosisAntipiretico = DateUtil.StringToDate(jsonpObject.get("ultimaDosisAntip").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimaDosisAntip")!=null && !jsonpObject.get("horaUltimaDosisAntip").getAsString().isEmpty()) horaUltDosisAntipiretico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimaDosisAntip").getAsString()));

        if (jsonpObject.get("rbgeneral_1")!=null && !jsonpObject.get("rbgeneral_1").getAsString().isEmpty()) fiebre = jsonpObject.get("rbgeneral_1").getAsString();
        if (jsonpObject.get("rbgeneral_2")!=null && !jsonpObject.get("rbgeneral_2").getAsString().isEmpty()) asomnoliento = jsonpObject.get("rbgeneral_2").getAsString();
        if (jsonpObject.get("rbgeneral_3")!=null && !jsonpObject.get("rbgeneral_3").getAsString().isEmpty()) malEstado = jsonpObject.get("rbgeneral_3").getAsString();
        if (jsonpObject.get("rbgeneral_4")!=null && !jsonpObject.get("rbgeneral_4").getAsString().isEmpty()) perdidaConsciencia = jsonpObject.get("rbgeneral_4").getAsString();
        if (jsonpObject.get("rbgeneral_5")!=null && !jsonpObject.get("rbgeneral_5").getAsString().isEmpty()) inquieto = jsonpObject.get("rbgeneral_5").getAsString();
        if (jsonpObject.get("rbgeneral_6")!=null && !jsonpObject.get("rbgeneral_6").getAsString().isEmpty()) convulsiones = jsonpObject.get("rbgeneral_6").getAsString();
        if (jsonpObject.get("rbgeneral_7")!=null && !jsonpObject.get("rbgeneral_7").getAsString().isEmpty()) letargia = jsonpObject.get("rbgeneral_7").getAsString();

        if (jsonpObject.get("rbcabeza_1")!=null && !jsonpObject.get("rbcabeza_1").getAsString().isEmpty()) dolorCabeza = jsonpObject.get("rbcabeza_1").getAsString();
        if (jsonpObject.get("rbcabeza_2")!=null && !jsonpObject.get("rbcabeza_2").getAsString().isEmpty()) conjuntivitis = jsonpObject.get("rbcabeza_2").getAsString();
        if (jsonpObject.get("rbcabeza_3")!=null && !jsonpObject.get("rbcabeza_3").getAsString().isEmpty()) hemorragiaSuconjuntival = jsonpObject.get("rbcabeza_3").getAsString();
        if (jsonpObject.get("rbcabeza_4")!=null && !jsonpObject.get("rbcabeza_4").getAsString().isEmpty()) dolorRetroocular = jsonpObject.get("rbcabeza_4").getAsString();

        if (jsonpObject.get("rbgarganta_1")!=null && !jsonpObject.get("rbgarganta_1").getAsString().isEmpty()) dolorGarganta = jsonpObject.get("rbgarganta_1").getAsString();
        if (jsonpObject.get("rbgarganta_2")!=null && !jsonpObject.get("rbgarganta_2").getAsString().isEmpty()) eritema = jsonpObject.get("rbgarganta_2").getAsString();
        if (jsonpObject.get("rbgarganta_3")!=null && !jsonpObject.get("rbgarganta_3").getAsString().isEmpty()) adenopatiasCervicales = jsonpObject.get("rbgarganta_3").getAsString();
        if (jsonpObject.get("rbgarganta_4")!=null && !jsonpObject.get("rbgarganta_4").getAsString().isEmpty()) exudado = jsonpObject.get("rbgarganta_4").getAsString();
        if (jsonpObject.get("rbgarganta_5")!=null && !jsonpObject.get("rbgarganta_5").getAsString().isEmpty()) petequiasMucosa = jsonpObject.get("rbgarganta_5").getAsString();

        if (jsonpObject.get("rbrespiratorio_1")!=null && !jsonpObject.get("rbrespiratorio_1").getAsString().isEmpty()) tos = jsonpObject.get("rbrespiratorio_1").getAsString();
        if (jsonpObject.get("rbrespiratorio_2")!=null && !jsonpObject.get("rbrespiratorio_2").getAsString().isEmpty()) rinorrea = jsonpObject.get("rbrespiratorio_2").getAsString();
        if (jsonpObject.get("rbrespiratorio_3")!=null && !jsonpObject.get("rbrespiratorio_3").getAsString().isEmpty()) congestionNasal = jsonpObject.get("rbrespiratorio_3").getAsString();
        if (jsonpObject.get("rbrespiratorio_4")!=null && !jsonpObject.get("rbrespiratorio_4").getAsString().isEmpty()) otalgia = jsonpObject.get("rbrespiratorio_4").getAsString();
        if (jsonpObject.get("rbrespiratorio_5")!=null && !jsonpObject.get("rbrespiratorio_5").getAsString().isEmpty()) aleteoNasal = jsonpObject.get("rbrespiratorio_5").getAsString();
        if (jsonpObject.get("rbrespiratorio_6")!=null && !jsonpObject.get("rbrespiratorio_6").getAsString().isEmpty()) respiracionRapida = jsonpObject.get("rbrespiratorio_6").getAsString();
        if (jsonpObject.get("rbrespiratorio_7")!=null && !jsonpObject.get("rbrespiratorio_7").getAsString().isEmpty()) estridorReposo = jsonpObject.get("rbrespiratorio_7").getAsString();
        if (jsonpObject.get("rbrespiratorio_8")!=null && !jsonpObject.get("rbrespiratorio_8").getAsString().isEmpty()) tirajeSubcostal = jsonpObject.get("rbrespiratorio_8").getAsString();
        if (jsonpObject.get("rbrespiratorio_9")!=null && !jsonpObject.get("rbrespiratorio_9").getAsString().isEmpty()) sibilancias = jsonpObject.get("rbrespiratorio_9").getAsString();
        if (jsonpObject.get("rbrespiratorio_10")!=null && !jsonpObject.get("rbrespiratorio_10").getAsString().isEmpty()) crepitos = jsonpObject.get("rbrespiratorio_10").getAsString();
        if (jsonpObject.get("rbrespiratorio_11")!=null && !jsonpObject.get("rbrespiratorio_11").getAsString().isEmpty()) roncos = jsonpObject.get("rbrespiratorio_11").getAsString();
        if (jsonpObject.get("rbrespiratorio_12")!=null && !jsonpObject.get("rbrespiratorio_12").getAsString().isEmpty()) disnea = jsonpObject.get("rbrespiratorio_12").getAsString();

        if (jsonpObject.get("rbgastroin_1")!=null && !jsonpObject.get("rbgastroin_1").getAsString().isEmpty()) pocoApetito = jsonpObject.get("rbgastroin_1").getAsString();
        if (jsonpObject.get("rbgastroin_2")!=null && !jsonpObject.get("rbgastroin_2").getAsString().isEmpty()) nausea = jsonpObject.get("rbgastroin_2").getAsString();
        if (jsonpObject.get("rbgastroin_3")!=null && !jsonpObject.get("rbgastroin_3").getAsString().isEmpty()) vomito12horas = jsonpObject.get("rbgastroin_3").getAsString();
        if (jsonpObject.get("numVomito")!=null && !jsonpObject.get("numVomito").getAsString().isEmpty()) numeroVomito12h = jsonpObject.get("numVomito").getAsShort();
        if (jsonpObject.get("rbgastroin_4")!=null && !jsonpObject.get("rbgastroin_4").getAsString().isEmpty()) diarrea = jsonpObject.get("rbgastroin_4").getAsString();
        if (jsonpObject.get("rbgastroin_5")!=null && !jsonpObject.get("rbgastroin_5").getAsString().isEmpty()) dolorAbdominal = jsonpObject.get("rbgastroin_5").getAsString();
        if (jsonpObject.get("rbgastroin_6")!=null && !jsonpObject.get("rbgastroin_6").getAsString().isEmpty()) hepatomegalia = jsonpObject.get("rbgastroin_6").getAsString();

        if (jsonpObject.get("rbosteomusc_1")!=null && !jsonpObject.get("rbosteomusc_1").getAsString().isEmpty()) artralgia = jsonpObject.get("rbosteomusc_1").getAsString();
        if (jsonpObject.get("rbosteomusc_2")!=null && !jsonpObject.get("rbosteomusc_2").getAsString().isEmpty()) mialgia = jsonpObject.get("rbosteomusc_2").getAsString();
        if (jsonpObject.get("rbosteomusc_3")!=null && !jsonpObject.get("rbosteomusc_3").getAsString().isEmpty()) lumbalgia = jsonpObject.get("rbosteomusc_3").getAsString();
        if (jsonpObject.get("rbosteomusc_4")!=null && !jsonpObject.get("rbosteomusc_4").getAsString().isEmpty()) dolorCuello = jsonpObject.get("rbosteomusc_4").getAsString();
        if (jsonpObject.get("rbosteomusc_5")!=null && !jsonpObject.get("rbosteomusc_5").getAsString().isEmpty()) edema = jsonpObject.get("rbosteomusc_5").getAsString();

        if (jsonpObject.get("rbcutaneo_1")!=null && !jsonpObject.get("rbcutaneo_1").getAsString().isEmpty()) rahsLocalizado = jsonpObject.get("rbcutaneo_1").getAsString();
        if (jsonpObject.get("rbcutaneo_2")!=null && !jsonpObject.get("rbcutaneo_2").getAsString().isEmpty()) rahsGeneralizado = jsonpObject.get("rbcutaneo_2").getAsString();
        if (jsonpObject.get("rbcutaneo_3")!=null && !jsonpObject.get("rbcutaneo_3").getAsString().isEmpty()) rashEritematoso = jsonpObject.get("rbcutaneo_3").getAsString();
        if (jsonpObject.get("rbcutaneo_4")!=null && !jsonpObject.get("rbcutaneo_4").getAsString().isEmpty()) rahsMacular = jsonpObject.get("rbcutaneo_4").getAsString();
        if (jsonpObject.get("rbcutaneo_5")!=null && !jsonpObject.get("rbcutaneo_5").getAsString().isEmpty()) rashPapular = jsonpObject.get("rbcutaneo_5").getAsString();
        if (jsonpObject.get("rbcutaneo_6")!=null && !jsonpObject.get("rbcutaneo_6").getAsString().isEmpty()) pielMoteada = jsonpObject.get("rbcutaneo_6").getAsString();
        if (jsonpObject.get("rbcutaneo_7")!=null && !jsonpObject.get("rbcutaneo_7").getAsString().isEmpty()) ruborFacial = jsonpObject.get("rbcutaneo_7").getAsString();
        if (jsonpObject.get("rbcutaneo_8")!=null && !jsonpObject.get("rbcutaneo_8").getAsString().isEmpty()) cianosisCentral = jsonpObject.get("rbcutaneo_8").getAsString();
        if (jsonpObject.get("rbcutaneo_9")!=null && !jsonpObject.get("rbcutaneo_9").getAsString().isEmpty()) ictericia = jsonpObject.get("rbcutaneo_9").getAsString();

        if (jsonpObject.get("imc")!=null && !jsonpObject.get("imc").getAsString().isEmpty()) imc = jsonpObject.get("imc").getAsBigDecimal();
        if (jsonpObject.get("rbestadonut_1")!=null && !jsonpObject.get("rbestadonut_1").getAsString().isEmpty()) obeso = jsonpObject.get("rbestadonut_1").getAsString();
        if (jsonpObject.get("rbestadonut_2")!=null && !jsonpObject.get("rbestadonut_2").getAsString().isEmpty()) sobrepeso = jsonpObject.get("rbestadonut_2").getAsString();
        if (jsonpObject.get("rbestadonut_3")!=null && !jsonpObject.get("rbestadonut_3").getAsString().isEmpty()) sospechaProblema = jsonpObject.get("rbestadonut_3").getAsString();
        if (jsonpObject.get("rbestadonut_4")!=null && !jsonpObject.get("rbestadonut_4").getAsString().isEmpty()) normal = jsonpObject.get("rbestadonut_4").getAsString();
        if (jsonpObject.get("rbestadonut_5")!=null && !jsonpObject.get("rbestadonut_5").getAsString().isEmpty()) bajoPeso = jsonpObject.get("rbestadonut_5").getAsString();
        if (jsonpObject.get("rbestadonut_6")!=null && !jsonpObject.get("rbestadonut_6").getAsString().isEmpty()) bajoPesoSevero = jsonpObject.get("rbestadonut_6").getAsString();

        if (jsonpObject.get("categoria")!=null && !jsonpObject.get("categoria").getAsString().isEmpty()) categoria = jsonpObject.get("categoria").getAsString();
        if (jsonpObject.get("cambioCategoria")!=null && !jsonpObject.get("cambioCategoria").getAsString().isEmpty()) cambioCategoria = jsonpObject.get("cambioCategoria").getAsString();
        if (jsonpObject.get("rbmanhemo_1")!=null && !jsonpObject.get("rbmanhemo_1").getAsString().isEmpty()) pruebaTorniquetePositiva = jsonpObject.get("rbmanhemo_1").getAsString();
        if (jsonpObject.get("rbmanhemo_2")!=null && !jsonpObject.get("rbmanhemo_2").getAsString().isEmpty()) petequia10Pt = jsonpObject.get("rbmanhemo_2").getAsString();
        if (jsonpObject.get("rbmanhemo_3")!=null && !jsonpObject.get("rbmanhemo_3").getAsString().isEmpty()) petequia20Pt = jsonpObject.get("rbmanhemo_3").getAsString();
        if (jsonpObject.get("rbmanhemo_4")!=null && !jsonpObject.get("rbmanhemo_4").getAsString().isEmpty()) pielExtremidadesFrias = jsonpObject.get("rbmanhemo_4").getAsString();
        if (jsonpObject.get("rbmanhemo_5")!=null && !jsonpObject.get("rbmanhemo_5").getAsString().isEmpty()) palidezEnExtremidades = jsonpObject.get("rbmanhemo_5").getAsString();
        if (jsonpObject.get("rbmanhemo_6")!=null && !jsonpObject.get("rbmanhemo_6").getAsString().isEmpty()) epistaxis = jsonpObject.get("rbmanhemo_6").getAsString();
        if (jsonpObject.get("rbmanhemo_7")!=null && !jsonpObject.get("rbmanhemo_7").getAsString().isEmpty()) gingivorragia = jsonpObject.get("rbmanhemo_7").getAsString();
        if (jsonpObject.get("rbmanhemo_8")!=null && !jsonpObject.get("rbmanhemo_8").getAsString().isEmpty()) petequiasEspontaneas = jsonpObject.get("rbmanhemo_8").getAsString();
        if (jsonpObject.get("rbmanhemo_9")!=null && !jsonpObject.get("rbmanhemo_9").getAsString().isEmpty()) llenadoCapilar2seg = jsonpObject.get("rbmanhemo_9").getAsString();
        if (jsonpObject.get("rbmanhemo_10")!=null && !jsonpObject.get("rbmanhemo_10").getAsString().isEmpty()) cianosis = jsonpObject.get("rbmanhemo_10").getAsString();
        if (jsonpObject.get("rbmanhemo_11")!=null && !jsonpObject.get("rbmanhemo_11").getAsString().isEmpty()) hipermenorrea = jsonpObject.get("rbmanhemo_11").getAsString();
        if (jsonpObject.get("rbmanhemo_12")!=null && !jsonpObject.get("rbmanhemo_12").getAsString().isEmpty()) hematemesis = jsonpObject.get("rbmanhemo_12").getAsString();
        if (jsonpObject.get("rbmanhemo_13")!=null && !jsonpObject.get("rbmanhemo_13").getAsString().isEmpty()) hemoconcentracion = jsonpObject.get("rbmanhemo_13").getAsString();

        if (jsonpObject.get("hospitalizado")!=null && !jsonpObject.get("hospitalizado").getAsString().isEmpty()) hospitalizado = jsonpObject.get("hospitalizado").getAsString();
        if (jsonpObject.get("unidadSaludHosp")!=null && !jsonpObject.get("unidadSaludHosp").getAsString().isEmpty()) hospitalizadoEspecificar = jsonpObject.get("unidadSaludHosp").getAsString();
        if (jsonpObject.get("transfusion")!=null && !jsonpObject.get("transfusion").getAsString().isEmpty()) transfusionSangre = jsonpObject.get("transfusion").getAsString();
        if (jsonpObject.get("transfusionEsp")!=null && !jsonpObject.get("transfusionEsp").getAsString().isEmpty()) transfusionEspecificar = jsonpObject.get("transfusionEsp").getAsString();
        if (jsonpObject.get("tomaMedicamento")!=null && !jsonpObject.get("tomaMedicamento").getAsString().isEmpty()) tomandoMedicamento = jsonpObject.get("tomaMedicamento").getAsString();
        if (jsonpObject.get("cualMedicamento")!=null && !jsonpObject.get("cualMedicamento").getAsString().isEmpty()) medicamentoEspecificar = jsonpObject.get("cualMedicamento").getAsString();

        if (jsonpObject.get("rbexamen_1")!=null && !jsonpObject.get("rbexamen_1").getAsString().isEmpty()) bhc = jsonpObject.get("rbexamen_1").getAsString();
        if (jsonpObject.get("rbexamen_2")!=null && !jsonpObject.get("rbexamen_2").getAsString().isEmpty()) serologiaArbovirus = jsonpObject.get("rbexamen_2").getAsString();
        if (jsonpObject.get("rbexamen_3")!=null && !jsonpObject.get("rbexamen_3").getAsString().isEmpty()) gotaGruesa = jsonpObject.get("rbexamen_3").getAsString();
        if (jsonpObject.get("rbexamen_4")!=null && !jsonpObject.get("rbexamen_4").getAsString().isEmpty()) ego = jsonpObject.get("rbexamen_4").getAsString();
        if (jsonpObject.get("rbexamen_5")!=null && !jsonpObject.get("rbexamen_5").getAsString().isEmpty()) egh = jsonpObject.get("rbexamen_5").getAsString();
        if (jsonpObject.get("rbexamen_6")!=null && !jsonpObject.get("rbexamen_6").getAsString().isEmpty()) otroExamenLab = jsonpObject.get("rbexamen_6").getAsString();
        if (jsonpObject.get("descOtroExamen")!=null && !jsonpObject.get("descOtroExamen").getAsString().isEmpty()) otroExamanLabEspecificar = jsonpObject.get("descOtroExamen").getAsString();

        if (jsonpObject.get("rbtratamiento_1")!=null && !jsonpObject.get("rbtratamiento_1").getAsString().isEmpty()) acetaminofen = jsonpObject.get("rbtratamiento_1").getAsString();
        if (jsonpObject.get("rbtratamiento_2")!=null && !jsonpObject.get("rbtratamiento_2").getAsString().isEmpty()) amoxicilina = jsonpObject.get("rbtratamiento_2").getAsString();
        if (jsonpObject.get("rbtratamiento_3")!=null && !jsonpObject.get("rbtratamiento_3").getAsString().isEmpty()) dicloxacilina = jsonpObject.get("rbtratamiento_3").getAsString();
        if (jsonpObject.get("rbtratamiento_4")!=null && !jsonpObject.get("rbtratamiento_4").getAsString().isEmpty()) penicilina = jsonpObject.get("rbtratamiento_4").getAsString();
        if (jsonpObject.get("rbtratamiento_5")!=null && !jsonpObject.get("rbtratamiento_5").getAsString().isEmpty()) furazolidona = jsonpObject.get("rbtratamiento_5").getAsString();
        if (jsonpObject.get("rbtratamiento_6")!=null && !jsonpObject.get("rbtratamiento_6").getAsString().isEmpty()) metronidazolTinidazol = jsonpObject.get("rbtratamiento_6").getAsString();
        if (jsonpObject.get("rbtratamiento_7")!=null && !jsonpObject.get("rbtratamiento_7").getAsString().isEmpty()) albendazolMebendazol = jsonpObject.get("rbtratamiento_7").getAsString();
        if (jsonpObject.get("rbtratamiento_8")!=null && !jsonpObject.get("rbtratamiento_8").getAsString().isEmpty()) sueroOral = jsonpObject.get("rbtratamiento_8").getAsString();
        if (jsonpObject.get("rbtratamiento_9")!=null && !jsonpObject.get("rbtratamiento_9").getAsString().isEmpty()) otroTratamiento = jsonpObject.get("rbtratamiento_9").getAsString();
        if (jsonpObject.get("rbtratamiento_10")!=null && !jsonpObject.get("rbtratamiento_10").getAsString().isEmpty()) liquidos4 = jsonpObject.get("rbtratamiento_10").getAsString();
        if (jsonpObject.get("rbtratamiento_11")!=null && !jsonpObject.get("rbtratamiento_11").getAsString().isEmpty()) referenciaPordengue = jsonpObject.get("rbtratamiento_11").getAsString();
        if (jsonpObject.get("descOtroTratamiento")!=null && !jsonpObject.get("descOtroTratamiento").getAsString().isEmpty()) otroTratamientoEspecificar = jsonpObject.get("descOtroTratamiento").getAsString();
        if (jsonpObject.get("planes")!=null && !jsonpObject.get("planes").getAsString().isEmpty()) planes = jsonpObject.get("planes").getAsString();
        if (jsonpObject.get("historia")!=null && !jsonpObject.get("historia").getAsString().isEmpty()) historiaClinica = jsonpObject.get("historia").getAsString();
        if (jsonpObject.get("dx")!=null && !jsonpObject.get("dx").getAsString().isEmpty()) diagnostico = jsonpObject.get("dx").getAsString();

        if (jsonpObject.get("diagnostico1")!=null && !jsonpObject.get("diagnostico1").getAsString().isEmpty()) diagnostico1 = jsonpObject.get("diagnostico1").getAsString();
        if (jsonpObject.get("diagnostico2")!=null && !jsonpObject.get("diagnostico2").getAsString().isEmpty()) diagnostico2 = jsonpObject.get("diagnostico2").getAsString();
        if (jsonpObject.get("diagnostico3")!=null && !jsonpObject.get("diagnostico3").getAsString().isEmpty()) diagnostico3 = jsonpObject.get("diagnostico3").getAsString();
        if (jsonpObject.get("diagnostico4")!=null && !jsonpObject.get("diagnostico4").getAsString().isEmpty()) diagnostico4 = jsonpObject.get("diagnostico4").getAsString();

        if (jsonpObject.get("telefono")!=null && !jsonpObject.get("telefono").getAsString().isEmpty()) telefonoEmergencia = jsonpObject.get("telefono").getAsString();
        if (jsonpObject.get("cita")!=null && !jsonpObject.get("cita").getAsString().isEmpty()) proximaCita = DateUtil.StringToDate(jsonpObject.get("cita").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("medico")!=null && !jsonpObject.get("medico").getAsString().isEmpty()) medico = jsonpObject.get("medico").getAsShort();
        if (jsonpObject.get("fechaMedico")!=null && !jsonpObject.get("fechaMedico").getAsString().isEmpty()) fechaMedico = DateUtil.StringToDate(jsonpObject.get("fechaMedico").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaMedico")!=null && !jsonpObject.get("horaMedico").getAsString().isEmpty()) horaMedico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaMedico").getAsString()));
        if (jsonpObject.get("enfermeria")!=null && !jsonpObject.get("enfermeria").getAsString().isEmpty()) enfermeria = jsonpObject.get("enfermeria").getAsShort();
        if (jsonpObject.get("fechaEnfermeria")!=null && !jsonpObject.get("fechaEnfermeria").getAsString().isEmpty()) fechaEnfermeria = DateUtil.StringToDate(jsonpObject.get("fechaEnfermeria").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaEnfermeria")!=null && !jsonpObject.get("horaEnfermeria").getAsString().isEmpty()) horaEnfermeria = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaEnfermeria").getAsString()));

       /* Integer  numSecHoja = this.hojaClinicaService.getObtenerDatosHojaClinica(codigoParticipante,numeroHoja) ;
        hojaClinica.setSecHojaConsulta(numSecHoja);*/


        hojaClinica.setFechaConsulta(fechaConsulta);
        hojaClinica.setHoraConsulta(horaConsulta);
        hojaClinica.setCodigo_supervisor(codigosupe); ;
        hojaClinica.setNumHojaConsulta(numeroHoja);

        hojaClinica.setPesoKg(pesoKg);
        hojaClinica.setTallaCm(tallaCm);
        hojaClinica.setPresion(presion);
        hojaClinica.setFciaCard(fciaCard);
        hojaClinica.setTemperaturac(temperaturac);
        hojaClinica.setSaturaciono2(saturaciono2);
        hojaClinica.setHoraInicioConsulta(horaInicioConsulta);
        hojaClinica.setConsulta(consulta);
        hojaClinica.setLugarAtencion(lugarAtencion);
        hojaClinica.setPresionMed(presionMed);
        hojaClinica.setTemMedc(temMedc);
        hojaClinica.setFciaRespMed(fciaRespMed);
        hojaClinica.setFciaCardMed(fciaCardMed);
        hojaClinica.setSaturaciono2Med(saturaciono2Med);
        hojaClinica.setFis(fis);
        hojaClinica.setFif(fif);
        hojaClinica.setUltDiaFiebre(ultDiaFiebre);
        hojaClinica.setHoraultDiaFiebre(horaultDiaFiebre);
        hojaClinica.setUltDosisAntipiretico(ultDosisAntipiretico);
        hojaClinica.setHoraUltDosisAntipiretico(horaUltDosisAntipiretico);
        hojaClinica.setFiebre(fiebre);
        hojaClinica.setAsomnoliento(asomnoliento);
        hojaClinica.setMalEstado(malEstado);
        hojaClinica.setPerdidaConsciencia(perdidaConsciencia);
        hojaClinica.setInquieto(inquieto);
        hojaClinica.setConvulsiones(convulsiones);
        hojaClinica.setLetargia(letargia);
        hojaClinica.setDolorCabeza(dolorCabeza);
        hojaClinica.setConjuntivitis(conjuntivitis);
        hojaClinica.setHemorragiaSuconjuntival(hemorragiaSuconjuntival);
        hojaClinica.setDolorRetroocular(dolorRetroocular);
        hojaClinica.setDolorGarganta(dolorGarganta);
        hojaClinica.setEritema(eritema);
        hojaClinica.setAdenopatiasCervicales(adenopatiasCervicales);
        hojaClinica.setExudado(exudado);
        hojaClinica.setPetequiasMucosa(petequiasMucosa);
        hojaClinica.setTos(tos);
        hojaClinica.setRinorrea(rinorrea);
        hojaClinica.setCongestionNasal(congestionNasal);
        hojaClinica.setOtalgia(otalgia);
        hojaClinica.setAleteoNasal(aleteoNasal);
        hojaClinica.setRespiracionRapida(respiracionRapida);
        hojaClinica.setEstridorReposo(estridorReposo);
        hojaClinica.setTirajeSubcostal(tirajeSubcostal);
        hojaClinica.setSibilancias(sibilancias);
        hojaClinica.setCrepitos(crepitos);
        hojaClinica.setRoncos(roncos);
        hojaClinica.setDisnea(disnea);
        hojaClinica.setPocoApetito(pocoApetito);
        hojaClinica.setNausea(nausea);
        hojaClinica.setVomito12horas(vomito12horas);
        hojaClinica.setNumeroVomito12h(numeroVomito12h);
        hojaClinica.setDiarrea(diarrea);
        hojaClinica.setHepatomegalia(hepatomegalia);
        hojaClinica.setDolorAbdominal(dolorAbdominal);
        hojaClinica.setArtralgia(artralgia);
        hojaClinica.setMialgia(mialgia);
        hojaClinica.setLumbalgia(lumbalgia);
        hojaClinica.setDolorCuello(dolorCuello);
        hojaClinica.setEdema(edema);
        hojaClinica.setRahsLocalizado(rahsLocalizado);
        hojaClinica.setRahsGeneralizado(rahsGeneralizado);
        hojaClinica.setRashEritematoso(rashEritematoso);
        hojaClinica.setRahsMacular(rahsMacular);
        hojaClinica.setRashPapular(rashPapular);
        hojaClinica.setPielMoteada(pielMoteada);
        hojaClinica.setRuborFacial(ruborFacial);
        hojaClinica.setCianosisCentral(cianosisCentral);
        hojaClinica.setIctericia(ictericia);
        hojaClinica.setImc(imc);
        hojaClinica.setObeso(obeso);
        hojaClinica.setSobrepeso(sobrepeso);
        hojaClinica.setSospechaProblema(sospechaProblema);
        hojaClinica.setNormal(normal);
        hojaClinica.setBajoPeso(bajoPeso);
        hojaClinica.setBajoPesoSevero(bajoPesoSevero);
        hojaClinica.setCategoria(categoria);
        hojaClinica.setCambioCategoria(cambioCategoria);
        hojaClinica.setPruebaTorniquetePositiva(pruebaTorniquetePositiva);
        hojaClinica.setPetequia10Pt(petequia10Pt);
        hojaClinica.setPetequia20Pt(petequia20Pt);
        hojaClinica.setPielExtremidadesFrias(pielExtremidadesFrias);
        hojaClinica.setPalidezEnExtremidades(palidezEnExtremidades);
        hojaClinica.setEpistaxis(epistaxis);
        hojaClinica.setGingivorragia(gingivorragia);
        hojaClinica.setPetequiasEspontaneas(petequiasEspontaneas);
        hojaClinica.setLlenadoCapilar2seg(llenadoCapilar2seg);
        hojaClinica.setCianosis(cianosis);
        hojaClinica.setHipermenorrea(hipermenorrea);
        hojaClinica.setHematemesis(hematemesis);
        hojaClinica.setHemoconcentracion(hemoconcentracion);
        hojaClinica.setHospitalizado(hospitalizado);
        hojaClinica.setHospitalizadoEspecificar(hospitalizadoEspecificar);
        hojaClinica.setTransfusionSangre(transfusionSangre);
        hojaClinica.setTransfusionEspecificar(transfusionEspecificar);
        hojaClinica.setTomandoMedicamento(tomandoMedicamento);
        hojaClinica.setMedicamentoEspecificar(medicamentoEspecificar);
        hojaClinica.setBhc(bhc);
        hojaClinica.setSerologiaArbovirus(serologiaArbovirus);
        hojaClinica.setGotaGruesa(gotaGruesa);
        hojaClinica.setEgo(ego);
        hojaClinica.setEgh(egh);
        hojaClinica.setOtroExamenLab(otroExamenLab);
        hojaClinica.setOtroExamanLabEspecificar(otroExamanLabEspecificar);
        hojaClinica.setAcetaminofen(acetaminofen);
        hojaClinica.setAmoxicilina(amoxicilina);
        hojaClinica.setDicloxacilina(dicloxacilina);
        hojaClinica.setPenicilina(penicilina);
        hojaClinica.setFurazolidona(furazolidona);
        hojaClinica.setMetronidazolTinidazol(metronidazolTinidazol);
        hojaClinica.setAlbendazolMebendazol(albendazolMebendazol);
        hojaClinica.setSueroOral(sueroOral);
        hojaClinica.setLiquidos4(liquidos4);
        hojaClinica.setReferencia_por_dengue(referenciaPordengue);
        hojaClinica.setOtroTratamiento(otroTratamiento);
        hojaClinica.setOtroTratamientoEspecificar(otroTratamientoEspecificar);
        hojaClinica.setPlanes(planes);
        hojaClinica.setHistoriaClinica(historiaClinica);
        hojaClinica.setDiagnostico(diagnostico);

        hojaClinica.setDiagnostico1(diagnostico1);
        hojaClinica.setDiagnostico2(diagnostico2);
        hojaClinica.setDiagnostico3(diagnostico3);
        hojaClinica.setDiagnostico4(diagnostico4);

        hojaClinica.setTelefonoEmergencia(telefonoEmergencia);
        hojaClinica.setProximaCita(proximaCita);
        hojaClinica.setMedico(medico);
        hojaClinica.setFechaMedico(fechaMedico);
        hojaClinica.setHoraMedico(horaMedico);
        hojaClinica.setEnfermeria(enfermeria);
        hojaClinica.setFechaEnfermeria(fechaEnfermeria);
        hojaClinica.setHoraEnfermeria(horaEnfermeria);

        return hojaClinica;
    }
    private HojaClinica ParseJsonRequestToHojaClinica1(HttpServletRequest request) throws Exception{
        HojaClinica hojaClinica = new HojaClinica();
        HojaClinicaDobleDigitacion hojaClinica2 = new HojaClinicaDobleDigitacion();
        String json = "";

        Date fechaConsulta = null;
        Time horaConsulta = null;
        Integer numeroHoja = null;
        //Datos de enfermería
        BigDecimal pesoKg = null;
        BigDecimal tallaCm = null;
        String presion = null;
        Short fciaCard = null;
        BigDecimal temperaturac = null;
        Short saturaciono2 = null;

        //Datos para llenar el médico.
        Time horaInicioConsulta = null;
        String consulta = null;
        String lugarAtencion = null;
        String presionMed = null;
        BigDecimal temMedc = null;
        Short fciaRespMed = null;
        Short fciaCardMed = null;
        Short saturaciono2Med = null;
        Date fis = null;
        Date fif = null;
        Date ultDiaFiebre = null;
        Time horaultDiaFiebre = null;
        Date ultDosisAntipiretico = null;
        Time horaUltDosisAntipiretico = null;
        //sintomas
        //General
        String fiebre = null;
        String asomnoliento = null;
        String malEstado = null;
        String perdidaConsciencia = null;
        String inquieto = null;
        String convulsiones = null;
        String letargia = null;
        //cabeza
        String dolorCabeza = null;
        String conjuntivitis = null;
        String hemorragiaSuconjuntival = null;
        String dolorRetroocular = null;
        //Garganta
        String dolorGarganta = null;
        String eritema = null;
        String adenopatiasCervicales = null;
        String exudado = null;
        String petequiasMucosa = null;
        //Respiratorio
        String tos = null;
        String rinorrea = null;
        String congestionNasal = null;
        String otalgia = null;
        String aleteoNasal = null;
        String respiracionRapida = null;
        String estridorReposo = null;
        String tirajeSubcostal = null;
        String sibilancias = null;
        String crepitos = null;
        String roncos = null;
        String disnea = null;
        //Gastrointestinal
        String pocoApetito = null;
        String nausea = null;
        String vomito12horas = null;
        Short numeroVomito12h = null;
        String diarrea = null;
        String hepatomegalia = null;
        String dolorAbdominal = null;
        //Osteomuscular
        String artralgia = null;
        String mialgia = null;
        String lumbalgia = null;
        String dolorCuello = null;
        String edema = null;
        //Cutáneo
        String rahsLocalizado = null;
        String rahsGeneralizado = null;
        String rashEritematoso = null;
        String rahsMacular = null;
        String rashPapular = null;
        String pielMoteada = null;
        String ruborFacial = null;
        String cianosisCentral = null;
        String ictericia = null;
        //Estado nutricional
        BigDecimal imc = null;
        String obeso = null;
        String sobrepeso = null;
        String sospechaProblema = null;
        String normal = null;
        String bajoPeso = null;
        String bajoPesoSevero = null;
        //categoria
        String categoria = null;
        String cambioCategoria = null;
        //Manifestaciones hemorrágicas
        String pruebaTorniquetePositiva = null;
        String petequia10Pt = null;
        String petequia20Pt = null;
        String pielExtremidadesFrias = null;
        String palidezEnExtremidades = null;
        String epistaxis = null;
        String gingivorragia = null;
        String petequiasEspontaneas = null;
        String llenadoCapilar2seg = null;
        String cianosis = null;
        String hipermenorrea = null;
        String hematemesis = null;
        String hemoconcentracion = null;
        //Preguntas para todos los pacientes
        String hospitalizado = null;
        String hospitalizadoEspecificar = null;
        String transfusionSangre = null;
        String transfusionEspecificar = null;
        String tomandoMedicamento = null;
        String medicamentoEspecificar = null;
        //Exámenes del laboratorio
        String bhc = null;
        String serologiaArbovirus = null;
        String gotaGruesa = null;
        String ego = null;
        String egh = null;
        String otroExamenLab = null;
        String otroExamanLabEspecificar = null;
        //Tratamiento
        String acetaminofen = null;
        String amoxicilina = null;
        String dicloxacilina = null;
        String penicilina = null;
        String furazolidona = null;
        String metronidazolTinidazol = null;
        String albendazolMebendazol = null;
        String sueroOral = null;
        String liquidos4 = null;
        String referenciaPordengue= null;
        String otroTratamiento = null;
        String otroTratamientoEspecificar = null;
        //planes, historia y diagnostico
        String planes = null;
        String historiaClinica = null;
        String diagnostico = null;

        String diagnostico1 = null;
        String diagnostico2 = null;
        String diagnostico3 = null;
        String diagnostico4 = null;
        String codigosupe = null;

        //Cierre
        String telefonoEmergencia = null;
        Date proximaCita = null;
        Short medico = null;
        Date fechaMedico = null;
        Time horaMedico = null;
        Short enfermeria = null;
        Date fechaEnfermeria = null;
        Time horaEnfermeria = null;



        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String hojaStr = jObjectRespuestas.get("hojaClinicaDD").toString();
        String codigoParticipante = jObjectRespuestas.get("participante").getAsString();

        Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
        hojaClinica.setCodigoParticipante(participante);

        codpart = codigoParticipante;


        JsonObject jsonpObject = new Gson().fromJson(hojaStr, JsonObject.class);

        if (jsonpObject.get("codigoSuper")!=null && !jsonpObject.get("codigoSuper").getAsString().isEmpty()) codigosupe = jsonpObject.get("codigoSuper").getAsString();

        if (jsonpObject.get("fechaCons")!=null && !jsonpObject.get("fechaCons").getAsString().isEmpty()) fechaConsulta = DateUtil.StringToDate(jsonpObject.get("fechaCons").getAsString(), "dd/MM/yyyy");
        logger.debug("fecha de consulta :" + fechaConsulta);

        if (jsonpObject.get("horaCons")!=null && !jsonpObject.get("horaCons").getAsString().isEmpty()) horaConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaCons").getAsString()));

        if (jsonpObject.get("numHoja")!=null && !jsonpObject.get("numHoja").getAsString().isEmpty()) numeroHoja = Integer.valueOf((jsonpObject.get("numHoja").getAsString()));



        if (jsonpObject.get("peso")!=null && !jsonpObject.get("peso").getAsString().isEmpty()) pesoKg = jsonpObject.get("peso").getAsBigDecimal();
        if (jsonpObject.get("talla")!=null && !jsonpObject.get("talla").getAsString().isEmpty()) tallaCm = jsonpObject.get("talla").getAsBigDecimal();
        if (jsonpObject.get("pa")!=null && !jsonpObject.get("pa").getAsString().isEmpty()) presion = jsonpObject.get("pa").getAsString();
        if (jsonpObject.get("fc")!=null && !jsonpObject.get("fc").getAsString().isEmpty()) fciaCard = jsonpObject.get("fc").getAsShort();
        if (jsonpObject.get("temp")!=null && !jsonpObject.get("temp").getAsString().isEmpty()) temperaturac = jsonpObject.get("temp").getAsBigDecimal();
        if (jsonpObject.get("so")!=null && !jsonpObject.get("so").getAsString().isEmpty()) saturaciono2 = jsonpObject.get("so").getAsShort();
        if (jsonpObject.get("horaIniCons")!=null && !jsonpObject.get("horaIniCons").getAsString().isEmpty()) horaInicioConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaIniCons").getAsString()));
        if (jsonpObject.get("tipoConsulta")!=null && !jsonpObject.get("tipoConsulta").getAsString().isEmpty()) consulta = jsonpObject.get("tipoConsulta").getAsString();
        if (jsonpObject.get("lugarConsulta")!=null && !jsonpObject.get("lugarConsulta").getAsString().isEmpty()) lugarAtencion = jsonpObject.get("lugarConsulta").getAsString();
        if (jsonpObject.get("paMedico")!=null && !jsonpObject.get("paMedico").getAsString().isEmpty()) presionMed = jsonpObject.get("paMedico").getAsString();
        if (jsonpObject.get("tempMedico")!=null && !jsonpObject.get("tempMedico").getAsString().isEmpty()) temMedc = jsonpObject.get("tempMedico").getAsBigDecimal();
        if (jsonpObject.get("frMedico")!=null && !jsonpObject.get("frMedico").getAsString().isEmpty()) fciaRespMed = jsonpObject.get("frMedico").getAsShort();
        if (jsonpObject.get("fcMedico")!=null && !jsonpObject.get("fcMedico").getAsString().isEmpty()) fciaCardMed = jsonpObject.get("fcMedico").getAsShort();
        if (jsonpObject.get("soMedico")!=null && !jsonpObject.get("soMedico").getAsString().isEmpty()) saturaciono2Med = jsonpObject.get("soMedico").getAsShort();
        if (jsonpObject.get("fis")!=null && !jsonpObject.get("fis").getAsString().isEmpty()) fis = DateUtil.StringToDate(jsonpObject.get("fis").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("fif")!=null && !jsonpObject.get("fif").getAsString().isEmpty()) fif = DateUtil.StringToDate(jsonpObject.get("fif").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("ultimoDiaFiebre")!=null && !jsonpObject.get("ultimoDiaFiebre").getAsString().isEmpty()) ultDiaFiebre = DateUtil.StringToDate(jsonpObject.get("ultimoDiaFiebre").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimoDiaF")!=null && !jsonpObject.get("horaUltimoDiaF").getAsString().isEmpty()) horaultDiaFiebre = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimoDiaF").getAsString()));
        if (jsonpObject.get("ultimaDosisAntip")!=null && !jsonpObject.get("ultimaDosisAntip").getAsString().isEmpty()) ultDosisAntipiretico = DateUtil.StringToDate(jsonpObject.get("ultimaDosisAntip").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimaDosisAntip")!=null && !jsonpObject.get("horaUltimaDosisAntip").getAsString().isEmpty()) horaUltDosisAntipiretico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimaDosisAntip").getAsString()));

        if (jsonpObject.get("rbgeneral_1")!=null && !jsonpObject.get("rbgeneral_1").getAsString().isEmpty()) fiebre = jsonpObject.get("rbgeneral_1").getAsString();
        if (jsonpObject.get("rbgeneral_2")!=null && !jsonpObject.get("rbgeneral_2").getAsString().isEmpty()) asomnoliento = jsonpObject.get("rbgeneral_2").getAsString();
        if (jsonpObject.get("rbgeneral_3")!=null && !jsonpObject.get("rbgeneral_3").getAsString().isEmpty()) malEstado = jsonpObject.get("rbgeneral_3").getAsString();
        if (jsonpObject.get("rbgeneral_4")!=null && !jsonpObject.get("rbgeneral_4").getAsString().isEmpty()) perdidaConsciencia = jsonpObject.get("rbgeneral_4").getAsString();
        if (jsonpObject.get("rbgeneral_5")!=null && !jsonpObject.get("rbgeneral_5").getAsString().isEmpty()) inquieto = jsonpObject.get("rbgeneral_5").getAsString();
        if (jsonpObject.get("rbgeneral_6")!=null && !jsonpObject.get("rbgeneral_6").getAsString().isEmpty()) convulsiones = jsonpObject.get("rbgeneral_6").getAsString();
        if (jsonpObject.get("rbgeneral_7")!=null && !jsonpObject.get("rbgeneral_7").getAsString().isEmpty()) letargia = jsonpObject.get("rbgeneral_7").getAsString();

        if (jsonpObject.get("rbcabeza_1")!=null && !jsonpObject.get("rbcabeza_1").getAsString().isEmpty()) dolorCabeza = jsonpObject.get("rbcabeza_1").getAsString();
        if (jsonpObject.get("rbcabeza_2")!=null && !jsonpObject.get("rbcabeza_2").getAsString().isEmpty()) conjuntivitis = jsonpObject.get("rbcabeza_2").getAsString();
        if (jsonpObject.get("rbcabeza_3")!=null && !jsonpObject.get("rbcabeza_3").getAsString().isEmpty()) hemorragiaSuconjuntival = jsonpObject.get("rbcabeza_3").getAsString();
        if (jsonpObject.get("rbcabeza_4")!=null && !jsonpObject.get("rbcabeza_4").getAsString().isEmpty()) dolorRetroocular = jsonpObject.get("rbcabeza_4").getAsString();

        if (jsonpObject.get("rbgarganta_1")!=null && !jsonpObject.get("rbgarganta_1").getAsString().isEmpty()) dolorGarganta = jsonpObject.get("rbgarganta_1").getAsString();
        if (jsonpObject.get("rbgarganta_2")!=null && !jsonpObject.get("rbgarganta_2").getAsString().isEmpty()) eritema = jsonpObject.get("rbgarganta_2").getAsString();
        if (jsonpObject.get("rbgarganta_3")!=null && !jsonpObject.get("rbgarganta_3").getAsString().isEmpty()) adenopatiasCervicales = jsonpObject.get("rbgarganta_3").getAsString();
        if (jsonpObject.get("rbgarganta_4")!=null && !jsonpObject.get("rbgarganta_4").getAsString().isEmpty()) exudado = jsonpObject.get("rbgarganta_4").getAsString();
        if (jsonpObject.get("rbgarganta_5")!=null && !jsonpObject.get("rbgarganta_5").getAsString().isEmpty()) petequiasMucosa = jsonpObject.get("rbgarganta_5").getAsString();

        if (jsonpObject.get("rbrespiratorio_1")!=null && !jsonpObject.get("rbrespiratorio_1").getAsString().isEmpty()) tos = jsonpObject.get("rbrespiratorio_1").getAsString();
        if (jsonpObject.get("rbrespiratorio_2")!=null && !jsonpObject.get("rbrespiratorio_2").getAsString().isEmpty()) rinorrea = jsonpObject.get("rbrespiratorio_2").getAsString();
        if (jsonpObject.get("rbrespiratorio_3")!=null && !jsonpObject.get("rbrespiratorio_3").getAsString().isEmpty()) congestionNasal = jsonpObject.get("rbrespiratorio_3").getAsString();
        if (jsonpObject.get("rbrespiratorio_4")!=null && !jsonpObject.get("rbrespiratorio_4").getAsString().isEmpty()) otalgia = jsonpObject.get("rbrespiratorio_4").getAsString();
        if (jsonpObject.get("rbrespiratorio_5")!=null && !jsonpObject.get("rbrespiratorio_5").getAsString().isEmpty()) aleteoNasal = jsonpObject.get("rbrespiratorio_5").getAsString();
        if (jsonpObject.get("rbrespiratorio_6")!=null && !jsonpObject.get("rbrespiratorio_6").getAsString().isEmpty()) respiracionRapida = jsonpObject.get("rbrespiratorio_6").getAsString();
        if (jsonpObject.get("rbrespiratorio_7")!=null && !jsonpObject.get("rbrespiratorio_7").getAsString().isEmpty()) estridorReposo = jsonpObject.get("rbrespiratorio_7").getAsString();
        if (jsonpObject.get("rbrespiratorio_8")!=null && !jsonpObject.get("rbrespiratorio_8").getAsString().isEmpty()) tirajeSubcostal = jsonpObject.get("rbrespiratorio_8").getAsString();
        if (jsonpObject.get("rbrespiratorio_9")!=null && !jsonpObject.get("rbrespiratorio_9").getAsString().isEmpty()) sibilancias = jsonpObject.get("rbrespiratorio_9").getAsString();
        if (jsonpObject.get("rbrespiratorio_10")!=null && !jsonpObject.get("rbrespiratorio_10").getAsString().isEmpty()) crepitos = jsonpObject.get("rbrespiratorio_10").getAsString();
        if (jsonpObject.get("rbrespiratorio_11")!=null && !jsonpObject.get("rbrespiratorio_11").getAsString().isEmpty()) roncos = jsonpObject.get("rbrespiratorio_11").getAsString();
        if (jsonpObject.get("rbrespiratorio_12")!=null && !jsonpObject.get("rbrespiratorio_12").getAsString().isEmpty()) disnea = jsonpObject.get("rbrespiratorio_12").getAsString();

        if (jsonpObject.get("rbgastroin_1")!=null && !jsonpObject.get("rbgastroin_1").getAsString().isEmpty()) pocoApetito = jsonpObject.get("rbgastroin_1").getAsString();
        if (jsonpObject.get("rbgastroin_2")!=null && !jsonpObject.get("rbgastroin_2").getAsString().isEmpty()) nausea = jsonpObject.get("rbgastroin_2").getAsString();
        if (jsonpObject.get("rbgastroin_3")!=null && !jsonpObject.get("rbgastroin_3").getAsString().isEmpty()) vomito12horas = jsonpObject.get("rbgastroin_3").getAsString();
        if (jsonpObject.get("numVomito")!=null && !jsonpObject.get("numVomito").getAsString().isEmpty()) numeroVomito12h = jsonpObject.get("numVomito").getAsShort();
        if (jsonpObject.get("rbgastroin_4")!=null && !jsonpObject.get("rbgastroin_4").getAsString().isEmpty()) diarrea = jsonpObject.get("rbgastroin_4").getAsString();
        if (jsonpObject.get("rbgastroin_5")!=null && !jsonpObject.get("rbgastroin_5").getAsString().isEmpty()) dolorAbdominal = jsonpObject.get("rbgastroin_5").getAsString();
        if (jsonpObject.get("rbgastroin_6")!=null && !jsonpObject.get("rbgastroin_6").getAsString().isEmpty()) hepatomegalia = jsonpObject.get("rbgastroin_6").getAsString();

        if (jsonpObject.get("rbosteomusc_1")!=null && !jsonpObject.get("rbosteomusc_1").getAsString().isEmpty()) artralgia = jsonpObject.get("rbosteomusc_1").getAsString();
        if (jsonpObject.get("rbosteomusc_2")!=null && !jsonpObject.get("rbosteomusc_2").getAsString().isEmpty()) mialgia = jsonpObject.get("rbosteomusc_2").getAsString();
        if (jsonpObject.get("rbosteomusc_3")!=null && !jsonpObject.get("rbosteomusc_3").getAsString().isEmpty()) lumbalgia = jsonpObject.get("rbosteomusc_3").getAsString();
        if (jsonpObject.get("rbosteomusc_4")!=null && !jsonpObject.get("rbosteomusc_4").getAsString().isEmpty()) dolorCuello = jsonpObject.get("rbosteomusc_4").getAsString();
        if (jsonpObject.get("rbosteomusc_5")!=null && !jsonpObject.get("rbosteomusc_5").getAsString().isEmpty()) edema = jsonpObject.get("rbosteomusc_5").getAsString();

        if (jsonpObject.get("rbcutaneo_1")!=null && !jsonpObject.get("rbcutaneo_1").getAsString().isEmpty()) rahsLocalizado = jsonpObject.get("rbcutaneo_1").getAsString();
        if (jsonpObject.get("rbcutaneo_2")!=null && !jsonpObject.get("rbcutaneo_2").getAsString().isEmpty()) rahsGeneralizado = jsonpObject.get("rbcutaneo_2").getAsString();
        if (jsonpObject.get("rbcutaneo_3")!=null && !jsonpObject.get("rbcutaneo_3").getAsString().isEmpty()) rashEritematoso = jsonpObject.get("rbcutaneo_3").getAsString();
        if (jsonpObject.get("rbcutaneo_4")!=null && !jsonpObject.get("rbcutaneo_4").getAsString().isEmpty()) rahsMacular = jsonpObject.get("rbcutaneo_4").getAsString();
        if (jsonpObject.get("rbcutaneo_5")!=null && !jsonpObject.get("rbcutaneo_5").getAsString().isEmpty()) rashPapular = jsonpObject.get("rbcutaneo_5").getAsString();
        if (jsonpObject.get("rbcutaneo_6")!=null && !jsonpObject.get("rbcutaneo_6").getAsString().isEmpty()) pielMoteada = jsonpObject.get("rbcutaneo_6").getAsString();
        if (jsonpObject.get("rbcutaneo_7")!=null && !jsonpObject.get("rbcutaneo_7").getAsString().isEmpty()) ruborFacial = jsonpObject.get("rbcutaneo_7").getAsString();
        if (jsonpObject.get("rbcutaneo_8")!=null && !jsonpObject.get("rbcutaneo_8").getAsString().isEmpty()) cianosisCentral = jsonpObject.get("rbcutaneo_8").getAsString();
        if (jsonpObject.get("rbcutaneo_9")!=null && !jsonpObject.get("rbcutaneo_9").getAsString().isEmpty()) ictericia = jsonpObject.get("rbcutaneo_9").getAsString();

        if (jsonpObject.get("imc")!=null && !jsonpObject.get("imc").getAsString().isEmpty()) imc = jsonpObject.get("imc").getAsBigDecimal();
        if (jsonpObject.get("rbestadonut_1")!=null && !jsonpObject.get("rbestadonut_1").getAsString().isEmpty()) obeso = jsonpObject.get("rbestadonut_1").getAsString();
        if (jsonpObject.get("rbestadonut_2")!=null && !jsonpObject.get("rbestadonut_2").getAsString().isEmpty()) sobrepeso = jsonpObject.get("rbestadonut_2").getAsString();
        if (jsonpObject.get("rbestadonut_3")!=null && !jsonpObject.get("rbestadonut_3").getAsString().isEmpty()) sospechaProblema = jsonpObject.get("rbestadonut_3").getAsString();
        if (jsonpObject.get("rbestadonut_4")!=null && !jsonpObject.get("rbestadonut_4").getAsString().isEmpty()) normal = jsonpObject.get("rbestadonut_4").getAsString();
        if (jsonpObject.get("rbestadonut_5")!=null && !jsonpObject.get("rbestadonut_5").getAsString().isEmpty()) bajoPeso = jsonpObject.get("rbestadonut_5").getAsString();
        if (jsonpObject.get("rbestadonut_6")!=null && !jsonpObject.get("rbestadonut_6").getAsString().isEmpty()) bajoPesoSevero = jsonpObject.get("rbestadonut_6").getAsString();

        if (jsonpObject.get("categoria")!=null && !jsonpObject.get("categoria").getAsString().isEmpty()) categoria = jsonpObject.get("categoria").getAsString();
        if (jsonpObject.get("cambioCategoria")!=null && !jsonpObject.get("cambioCategoria").getAsString().isEmpty()) cambioCategoria = jsonpObject.get("cambioCategoria").getAsString();
        if (jsonpObject.get("rbmanhemo_1")!=null && !jsonpObject.get("rbmanhemo_1").getAsString().isEmpty()) pruebaTorniquetePositiva = jsonpObject.get("rbmanhemo_1").getAsString();
        if (jsonpObject.get("rbmanhemo_2")!=null && !jsonpObject.get("rbmanhemo_2").getAsString().isEmpty()) petequia10Pt = jsonpObject.get("rbmanhemo_2").getAsString();
        if (jsonpObject.get("rbmanhemo_3")!=null && !jsonpObject.get("rbmanhemo_3").getAsString().isEmpty()) petequia20Pt = jsonpObject.get("rbmanhemo_3").getAsString();
        if (jsonpObject.get("rbmanhemo_4")!=null && !jsonpObject.get("rbmanhemo_4").getAsString().isEmpty()) pielExtremidadesFrias = jsonpObject.get("rbmanhemo_4").getAsString();
        if (jsonpObject.get("rbmanhemo_5")!=null && !jsonpObject.get("rbmanhemo_5").getAsString().isEmpty()) palidezEnExtremidades = jsonpObject.get("rbmanhemo_5").getAsString();
        if (jsonpObject.get("rbmanhemo_6")!=null && !jsonpObject.get("rbmanhemo_6").getAsString().isEmpty()) epistaxis = jsonpObject.get("rbmanhemo_6").getAsString();
        if (jsonpObject.get("rbmanhemo_7")!=null && !jsonpObject.get("rbmanhemo_7").getAsString().isEmpty()) gingivorragia = jsonpObject.get("rbmanhemo_7").getAsString();
        if (jsonpObject.get("rbmanhemo_8")!=null && !jsonpObject.get("rbmanhemo_8").getAsString().isEmpty()) petequiasEspontaneas = jsonpObject.get("rbmanhemo_8").getAsString();
        if (jsonpObject.get("rbmanhemo_9")!=null && !jsonpObject.get("rbmanhemo_9").getAsString().isEmpty()) llenadoCapilar2seg = jsonpObject.get("rbmanhemo_9").getAsString();
        if (jsonpObject.get("rbmanhemo_10")!=null && !jsonpObject.get("rbmanhemo_10").getAsString().isEmpty()) cianosis = jsonpObject.get("rbmanhemo_10").getAsString();
        if (jsonpObject.get("rbmanhemo_11")!=null && !jsonpObject.get("rbmanhemo_11").getAsString().isEmpty()) hipermenorrea = jsonpObject.get("rbmanhemo_11").getAsString();
        if (jsonpObject.get("rbmanhemo_12")!=null && !jsonpObject.get("rbmanhemo_12").getAsString().isEmpty()) hematemesis = jsonpObject.get("rbmanhemo_12").getAsString();
        if (jsonpObject.get("rbmanhemo_13")!=null && !jsonpObject.get("rbmanhemo_13").getAsString().isEmpty()) hemoconcentracion = jsonpObject.get("rbmanhemo_13").getAsString();

        if (jsonpObject.get("hospitalizado")!=null && !jsonpObject.get("hospitalizado").getAsString().isEmpty()) hospitalizado = jsonpObject.get("hospitalizado").getAsString();
        if (jsonpObject.get("unidadSaludHosp")!=null && !jsonpObject.get("unidadSaludHosp").getAsString().isEmpty()) hospitalizadoEspecificar = jsonpObject.get("unidadSaludHosp").getAsString();
        if (jsonpObject.get("transfusion")!=null && !jsonpObject.get("transfusion").getAsString().isEmpty()) transfusionSangre = jsonpObject.get("transfusion").getAsString();
        if (jsonpObject.get("transfusionEsp")!=null && !jsonpObject.get("transfusionEsp").getAsString().isEmpty()) transfusionEspecificar = jsonpObject.get("transfusionEsp").getAsString();
        if (jsonpObject.get("tomaMedicamento")!=null && !jsonpObject.get("tomaMedicamento").getAsString().isEmpty()) tomandoMedicamento = jsonpObject.get("tomaMedicamento").getAsString();
        if (jsonpObject.get("cualMedicamento")!=null && !jsonpObject.get("cualMedicamento").getAsString().isEmpty()) medicamentoEspecificar = jsonpObject.get("cualMedicamento").getAsString();

        if (jsonpObject.get("rbexamen_1")!=null && !jsonpObject.get("rbexamen_1").getAsString().isEmpty()) bhc = jsonpObject.get("rbexamen_1").getAsString();
        if (jsonpObject.get("rbexamen_2")!=null && !jsonpObject.get("rbexamen_2").getAsString().isEmpty()) serologiaArbovirus = jsonpObject.get("rbexamen_2").getAsString();
        if (jsonpObject.get("rbexamen_3")!=null && !jsonpObject.get("rbexamen_3").getAsString().isEmpty()) gotaGruesa = jsonpObject.get("rbexamen_3").getAsString();
        if (jsonpObject.get("rbexamen_4")!=null && !jsonpObject.get("rbexamen_4").getAsString().isEmpty()) ego = jsonpObject.get("rbexamen_4").getAsString();
        if (jsonpObject.get("rbexamen_5")!=null && !jsonpObject.get("rbexamen_5").getAsString().isEmpty()) egh = jsonpObject.get("rbexamen_5").getAsString();
        if (jsonpObject.get("rbexamen_6")!=null && !jsonpObject.get("rbexamen_6").getAsString().isEmpty()) otroExamenLab = jsonpObject.get("rbexamen_6").getAsString();
        if (jsonpObject.get("descOtroExamen")!=null && !jsonpObject.get("descOtroExamen").getAsString().isEmpty()) otroExamanLabEspecificar = jsonpObject.get("descOtroExamen").getAsString();

        if (jsonpObject.get("rbtratamiento_1")!=null && !jsonpObject.get("rbtratamiento_1").getAsString().isEmpty()) acetaminofen = jsonpObject.get("rbtratamiento_1").getAsString();
        if (jsonpObject.get("rbtratamiento_2")!=null && !jsonpObject.get("rbtratamiento_2").getAsString().isEmpty()) amoxicilina = jsonpObject.get("rbtratamiento_2").getAsString();
        if (jsonpObject.get("rbtratamiento_3")!=null && !jsonpObject.get("rbtratamiento_3").getAsString().isEmpty()) dicloxacilina = jsonpObject.get("rbtratamiento_3").getAsString();
        if (jsonpObject.get("rbtratamiento_4")!=null && !jsonpObject.get("rbtratamiento_4").getAsString().isEmpty()) penicilina = jsonpObject.get("rbtratamiento_4").getAsString();
        if (jsonpObject.get("rbtratamiento_5")!=null && !jsonpObject.get("rbtratamiento_5").getAsString().isEmpty()) furazolidona = jsonpObject.get("rbtratamiento_5").getAsString();
        if (jsonpObject.get("rbtratamiento_6")!=null && !jsonpObject.get("rbtratamiento_6").getAsString().isEmpty()) metronidazolTinidazol = jsonpObject.get("rbtratamiento_6").getAsString();
        if (jsonpObject.get("rbtratamiento_7")!=null && !jsonpObject.get("rbtratamiento_7").getAsString().isEmpty()) albendazolMebendazol = jsonpObject.get("rbtratamiento_7").getAsString();
        if (jsonpObject.get("rbtratamiento_8")!=null && !jsonpObject.get("rbtratamiento_8").getAsString().isEmpty()) sueroOral = jsonpObject.get("rbtratamiento_8").getAsString();
        if (jsonpObject.get("rbtratamiento_9")!=null && !jsonpObject.get("rbtratamiento_9").getAsString().isEmpty()) otroTratamiento = jsonpObject.get("rbtratamiento_9").getAsString();
        if (jsonpObject.get("rbtratamiento_10")!=null && !jsonpObject.get("rbtratamiento_10").getAsString().isEmpty()) liquidos4 = jsonpObject.get("rbtratamiento_10").getAsString();
        if (jsonpObject.get("rbtratamiento_11")!=null && !jsonpObject.get("rbtratamiento_11").getAsString().isEmpty()) referenciaPordengue = jsonpObject.get("rbtratamiento_11").getAsString();
        if (jsonpObject.get("descOtroTratamiento")!=null && !jsonpObject.get("descOtroTratamiento").getAsString().isEmpty()) otroTratamientoEspecificar = jsonpObject.get("descOtroTratamiento").getAsString();
        if (jsonpObject.get("planes")!=null && !jsonpObject.get("planes").getAsString().isEmpty()) planes = jsonpObject.get("planes").getAsString();
        if (jsonpObject.get("historia")!=null && !jsonpObject.get("historia").getAsString().isEmpty()) historiaClinica = jsonpObject.get("historia").getAsString();
        if (jsonpObject.get("dx")!=null && !jsonpObject.get("dx").getAsString().isEmpty()) diagnostico = jsonpObject.get("dx").getAsString();

        if (jsonpObject.get("diagnostico1")!=null && !jsonpObject.get("diagnostico1").getAsString().isEmpty()) diagnostico1 = jsonpObject.get("diagnostico1").getAsString();
        if (jsonpObject.get("diagnostico2")!=null && !jsonpObject.get("diagnostico2").getAsString().isEmpty()) diagnostico2 = jsonpObject.get("diagnostico2").getAsString();
        if (jsonpObject.get("diagnostico3")!=null && !jsonpObject.get("diagnostico3").getAsString().isEmpty()) diagnostico3 = jsonpObject.get("diagnostico3").getAsString();
        if (jsonpObject.get("diagnostico4")!=null && !jsonpObject.get("diagnostico4").getAsString().isEmpty()) diagnostico4 = jsonpObject.get("diagnostico4").getAsString();

        if (jsonpObject.get("telefono")!=null && !jsonpObject.get("telefono").getAsString().isEmpty()) telefonoEmergencia = jsonpObject.get("telefono").getAsString();
        if (jsonpObject.get("cita")!=null && !jsonpObject.get("cita").getAsString().isEmpty()) proximaCita = DateUtil.StringToDate(jsonpObject.get("cita").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("medico")!=null && !jsonpObject.get("medico").getAsString().isEmpty()) medico = jsonpObject.get("medico").getAsShort();
        if (jsonpObject.get("fechaMedico")!=null && !jsonpObject.get("fechaMedico").getAsString().isEmpty()) fechaMedico = DateUtil.StringToDate(jsonpObject.get("fechaMedico").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaMedico")!=null && !jsonpObject.get("horaMedico").getAsString().isEmpty()) horaMedico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaMedico").getAsString()));
        if (jsonpObject.get("enfermeria")!=null && !jsonpObject.get("enfermeria").getAsString().isEmpty()) enfermeria = jsonpObject.get("enfermeria").getAsShort();
        if (jsonpObject.get("fechaEnfermeria")!=null && !jsonpObject.get("fechaEnfermeria").getAsString().isEmpty()) fechaEnfermeria = DateUtil.StringToDate(jsonpObject.get("fechaEnfermeria").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaEnfermeria")!=null && !jsonpObject.get("horaEnfermeria").getAsString().isEmpty()) horaEnfermeria = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaEnfermeria").getAsString()));

        hojaClinica = this.hojaClinicaService.getObtenerRegHojaClinicaCorrecH1(codigoParticipante,numeroHoja);
//        fechreg1 = hojaClinica.getRecordDate();
  //      usuarreg1 = hojaClinica.getRecordUser();
        String codigoParticipante2 = jObjectRespuestas.get("participante").getAsString();

        Participante participante2 = participanteService.getParticipanteByCodigo(codigoParticipante2);
        logger.debug("codigoParticipante2 :" + participante2);
//          hojaClinica.setCodigoParticipante(participante2);
        hojaClinica.setCodigoParticipante(participante2);


     //   hojaClinica.setCodigoParticipante(participante);
        hojaClinica.setFechaConsulta(fechaConsulta);
        hojaClinica.setHoraConsulta(horaConsulta);
        hojaClinica.setCodigo_supervisor(codigosupe); ;
        hojaClinica.setNumHojaConsulta(numeroHoja);

        hojaClinica.setPesoKg(pesoKg);
        hojaClinica.setTallaCm(tallaCm);
        hojaClinica.setPresion(presion);
        hojaClinica.setFciaCard(fciaCard);
        hojaClinica.setTemperaturac(temperaturac);
        hojaClinica.setSaturaciono2(saturaciono2);
        hojaClinica.setHoraInicioConsulta(horaInicioConsulta);
        hojaClinica.setConsulta(consulta);
        hojaClinica.setLugarAtencion(lugarAtencion);
        hojaClinica.setPresionMed(presionMed);
        hojaClinica.setTemMedc(temMedc);
        hojaClinica.setFciaRespMed(fciaRespMed);
        hojaClinica.setFciaCardMed(fciaCardMed);
        hojaClinica.setSaturaciono2Med(saturaciono2Med);
        hojaClinica.setFis(fis);
        hojaClinica.setFif(fif);
        hojaClinica.setUltDiaFiebre(ultDiaFiebre);
        hojaClinica.setHoraultDiaFiebre(horaultDiaFiebre);
        hojaClinica.setUltDosisAntipiretico(ultDosisAntipiretico);
        hojaClinica.setHoraUltDosisAntipiretico(horaUltDosisAntipiretico);
        hojaClinica.setFiebre(fiebre);
        hojaClinica.setAsomnoliento(asomnoliento);
        hojaClinica.setMalEstado(malEstado);
        hojaClinica.setPerdidaConsciencia(perdidaConsciencia);
        hojaClinica.setInquieto(inquieto);
        hojaClinica.setConvulsiones(convulsiones);
        hojaClinica.setLetargia(letargia);
        hojaClinica.setDolorCabeza(dolorCabeza);
        hojaClinica.setConjuntivitis(conjuntivitis);
        hojaClinica.setHemorragiaSuconjuntival(hemorragiaSuconjuntival);
        hojaClinica.setDolorRetroocular(dolorRetroocular);
        hojaClinica.setDolorGarganta(dolorGarganta);
        hojaClinica.setEritema(eritema);
        hojaClinica.setAdenopatiasCervicales(adenopatiasCervicales);
        hojaClinica.setExudado(exudado);
        hojaClinica.setPetequiasMucosa(petequiasMucosa);
        hojaClinica.setTos(tos);
        hojaClinica.setRinorrea(rinorrea);
        hojaClinica.setCongestionNasal(congestionNasal);
        hojaClinica.setOtalgia(otalgia);
        hojaClinica.setAleteoNasal(aleteoNasal);
        hojaClinica.setRespiracionRapida(respiracionRapida);
        hojaClinica.setEstridorReposo(estridorReposo);
        hojaClinica.setTirajeSubcostal(tirajeSubcostal);
        hojaClinica.setSibilancias(sibilancias);
        hojaClinica.setCrepitos(crepitos);
        hojaClinica.setRoncos(roncos);
        hojaClinica.setDisnea(disnea);
        hojaClinica.setPocoApetito(pocoApetito);
        hojaClinica.setNausea(nausea);
        hojaClinica.setVomito12horas(vomito12horas);
        hojaClinica.setNumeroVomito12h(numeroVomito12h);
        hojaClinica.setDiarrea(diarrea);
        hojaClinica.setHepatomegalia(hepatomegalia);
        hojaClinica.setDolorAbdominal(dolorAbdominal);
        hojaClinica.setArtralgia(artralgia);
        hojaClinica.setMialgia(mialgia);
        hojaClinica.setLumbalgia(lumbalgia);
        hojaClinica.setDolorCuello(dolorCuello);
        hojaClinica.setEdema(edema);
        hojaClinica.setRahsLocalizado(rahsLocalizado);
        hojaClinica.setRahsGeneralizado(rahsGeneralizado);
        hojaClinica.setRashEritematoso(rashEritematoso);
        hojaClinica.setRahsMacular(rahsMacular);
        hojaClinica.setRashPapular(rashPapular);
        hojaClinica.setPielMoteada(pielMoteada);
        hojaClinica.setRuborFacial(ruborFacial);
        hojaClinica.setCianosisCentral(cianosisCentral);
        hojaClinica.setIctericia(ictericia);
        hojaClinica.setImc(imc);
        hojaClinica.setObeso(obeso);
        hojaClinica.setSobrepeso(sobrepeso);
        hojaClinica.setSospechaProblema(sospechaProblema);
        hojaClinica.setNormal(normal);
        hojaClinica.setBajoPeso(bajoPeso);
        hojaClinica.setBajoPesoSevero(bajoPesoSevero);
        hojaClinica.setCategoria(categoria);
        hojaClinica.setCambioCategoria(cambioCategoria);
        hojaClinica.setPruebaTorniquetePositiva(pruebaTorniquetePositiva);
        hojaClinica.setPetequia10Pt(petequia10Pt);
        hojaClinica.setPetequia20Pt(petequia20Pt);
        hojaClinica.setPielExtremidadesFrias(pielExtremidadesFrias);
        hojaClinica.setPalidezEnExtremidades(palidezEnExtremidades);
        hojaClinica.setEpistaxis(epistaxis);
        hojaClinica.setGingivorragia(gingivorragia);
        hojaClinica.setPetequiasEspontaneas(petequiasEspontaneas);
        hojaClinica.setLlenadoCapilar2seg(llenadoCapilar2seg);
        hojaClinica.setCianosis(cianosis);
        hojaClinica.setHipermenorrea(hipermenorrea);
        hojaClinica.setHematemesis(hematemesis);
        hojaClinica.setHemoconcentracion(hemoconcentracion);
        hojaClinica.setHospitalizado(hospitalizado);
        hojaClinica.setHospitalizadoEspecificar(hospitalizadoEspecificar);
        hojaClinica.setTransfusionSangre(transfusionSangre);
        hojaClinica.setTransfusionEspecificar(transfusionEspecificar);
        hojaClinica.setTomandoMedicamento(tomandoMedicamento);
        hojaClinica.setMedicamentoEspecificar(medicamentoEspecificar);
        hojaClinica.setBhc(bhc);
        hojaClinica.setSerologiaArbovirus(serologiaArbovirus);
        hojaClinica.setGotaGruesa(gotaGruesa);
        hojaClinica.setEgo(ego);
        hojaClinica.setEgh(egh);
        hojaClinica.setOtroExamenLab(otroExamenLab);
        hojaClinica.setOtroExamanLabEspecificar(otroExamanLabEspecificar);
        hojaClinica.setAcetaminofen(acetaminofen);
        hojaClinica.setAmoxicilina(amoxicilina);
        hojaClinica.setDicloxacilina(dicloxacilina);
        hojaClinica.setPenicilina(penicilina);
        hojaClinica.setFurazolidona(furazolidona);
        hojaClinica.setMetronidazolTinidazol(metronidazolTinidazol);
        hojaClinica.setAlbendazolMebendazol(albendazolMebendazol);
        hojaClinica.setSueroOral(sueroOral);
        hojaClinica.setLiquidos4(liquidos4);
        hojaClinica.setReferencia_por_dengue(referenciaPordengue);
        hojaClinica.setOtroTratamiento(otroTratamiento);
        hojaClinica.setOtroTratamientoEspecificar(otroTratamientoEspecificar);
        hojaClinica.setPlanes(planes);
        hojaClinica.setHistoriaClinica(historiaClinica);
        hojaClinica.setDiagnostico(diagnostico);

        hojaClinica.setDiagnostico1(diagnostico1);
        hojaClinica.setDiagnostico2(diagnostico2);
        hojaClinica.setDiagnostico3(diagnostico3);
        hojaClinica.setDiagnostico4(diagnostico4);

        hojaClinica.setTelefonoEmergencia(telefonoEmergencia);
        hojaClinica.setProximaCita(proximaCita);
        hojaClinica.setMedico(medico);
        hojaClinica.setFechaMedico(fechaMedico);
        hojaClinica.setHoraMedico(horaMedico);
        hojaClinica.setEnfermeria(enfermeria);
        hojaClinica.setFechaEnfermeria(fechaEnfermeria);
        hojaClinica.setHoraEnfermeria(horaEnfermeria);

        return hojaClinica;
    }
    private HojaClinicaDobleDigitacion   ParseJsonRequestToHojaClinica2(HttpServletRequest request) throws Exception{
        HojaClinicaDobleDigitacion hojaClinica = new HojaClinicaDobleDigitacion();
        String json = "";

        Date fechaConsulta = null;
        Time horaConsulta = null;
        Integer numeroHoja = null;
        //Datos de enfermería
        BigDecimal pesoKg = null;
        BigDecimal tallaCm = null;
        String presion = null;
        Short fciaCard = null;
        BigDecimal temperaturac = null;
        Short saturaciono2 = null;

        //Datos para llenar el médico.
        Time horaInicioConsulta = null;
        String consulta = null;
        String lugarAtencion = null;
        String presionMed = null;
        BigDecimal temMedc = null;
        Short fciaRespMed = null;
        Short fciaCardMed = null;
        Short saturaciono2Med = null;
        Date fis = null;
        Date fif = null;
        Date ultDiaFiebre = null;
        Time horaultDiaFiebre = null;
        Date ultDosisAntipiretico = null;
        Time horaUltDosisAntipiretico = null;
        //sintomas
        //General
        String fiebre = null;
        String asomnoliento = null;
        String malEstado = null;
        String perdidaConsciencia = null;
        String inquieto = null;
        String convulsiones = null;
        String letargia = null;
        //cabeza
        String dolorCabeza = null;
        String conjuntivitis = null;
        String hemorragiaSuconjuntival = null;
        String dolorRetroocular = null;
        //Garganta
        String dolorGarganta = null;
        String eritema = null;
        String adenopatiasCervicales = null;
        String exudado = null;
        String petequiasMucosa = null;
        //Respiratorio
        String tos = null;
        String rinorrea = null;
        String congestionNasal = null;
        String otalgia = null;
        String aleteoNasal = null;
        String respiracionRapida = null;
        String estridorReposo = null;
        String tirajeSubcostal = null;
        String sibilancias = null;
        String crepitos = null;
        String roncos = null;
        String disnea = null;
        //Gastrointestinal
        String pocoApetito = null;
        String nausea = null;
        String vomito12horas = null;
        Short numeroVomito12h = null;
        String diarrea = null;
        String hepatomegalia = null;
        String dolorAbdominal = null;
        //Osteomuscular
        String artralgia = null;
        String mialgia = null;
        String lumbalgia = null;
        String dolorCuello = null;
        String edema = null;
        //Cutáneo
        String rahsLocalizado = null;
        String rahsGeneralizado = null;
        String rashEritematoso = null;
        String rahsMacular = null;
        String rashPapular = null;
        String pielMoteada = null;
        String ruborFacial = null;
        String cianosisCentral = null;
        String ictericia = null;
        //Estado nutricional
        BigDecimal imc = null;
        String obeso = null;
        String sobrepeso = null;
        String sospechaProblema = null;
        String normal = null;
        String bajoPeso = null;
        String bajoPesoSevero = null;
        //categoria
        String categoria = null;
        String cambioCategoria = null;
        //Manifestaciones hemorrágicas
        String pruebaTorniquetePositiva = null;
        String petequia10Pt = null;
        String petequia20Pt = null;
        String pielExtremidadesFrias = null;
        String palidezEnExtremidades = null;
        String epistaxis = null;
        String gingivorragia = null;
        String petequiasEspontaneas = null;
        String llenadoCapilar2seg = null;
        String cianosis = null;
        String hipermenorrea = null;
        String hematemesis = null;
        String hemoconcentracion = null;
        //Preguntas para todos los pacientes
        String hospitalizado = null;
        String hospitalizadoEspecificar = null;
        String transfusionSangre = null;
        String transfusionEspecificar = null;
        String tomandoMedicamento = null;
        String medicamentoEspecificar = null;
        //Exámenes del laboratorio
        String bhc = null;
        String serologiaArbovirus = null;
        String gotaGruesa = null;
        String ego = null;
        String egh = null;
        String otroExamenLab = null;
        String otroExamanLabEspecificar = null;
        //Tratamiento
        String acetaminofen = null;
        String amoxicilina = null;
        String dicloxacilina = null;
        String penicilina = null;
        String furazolidona = null;
        String metronidazolTinidazol = null;
        String albendazolMebendazol = null;
        String sueroOral = null;
        String liquidos4 = null;
        String referenciaPordengue = null;
        String otroTratamiento = null;
        String otroTratamientoEspecificar = null;
        //planes, historia y diagnostico
        String planes = null;
        String historiaClinica = null;
        String diagnostico = null;

        String diagnostico1 = null;
        String diagnostico2 = null;
        String diagnostico3 = null;
        String diagnostico4 = null;
        String codigosupe = null;

        //Cierre
        String telefonoEmergencia = null;
        Date proximaCita = null;
        Short medico = null;
        Date fechaMedico = null;
        Time horaMedico = null;
        Short enfermeria = null;
        Date fechaEnfermeria = null;
        Time horaEnfermeria = null;



       /* BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String hojaStr = jObjectRespuestas.get("hojaClinicaDD").toString();
        String codigoParticipante = jObjectRespuestas.get("participante").getAsString();

        Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
        hojaClinica.setCodigoParticipante(participante);

        codpart = codigoParticipante;


        JsonObject jsonpObject = new Gson().fromJson(hojaStr, JsonObject.class);

        if (jsonpObject.get("codigoSuper")!=null && !jsonpObject.get("codigoSuper").getAsString().isEmpty()) codigosupe = jsonpObject.get("codigoSuper").getAsString();

        if (jsonpObject.get("fechaCons")!=null && !jsonpObject.get("fechaCons").getAsString().isEmpty()) fechaConsulta = DateUtil.StringToDate(jsonpObject.get("fechaCons").getAsString(), "dd/MM/yyyy");
        logger.debug("fecha de consulta :" + fechaConsulta);

        if (jsonpObject.get("horaCons")!=null && !jsonpObject.get("horaCons").getAsString().isEmpty()) horaConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaCons").getAsString()));

        if (jsonpObject.get("numHoja")!=null && !jsonpObject.get("numHoja").getAsString().isEmpty()) numeroHoja = Integer.valueOf((jsonpObject.get("numHoja").getAsString()));



        if (jsonpObject.get("peso")!=null && !jsonpObject.get("peso").getAsString().isEmpty()) pesoKg = jsonpObject.get("peso").getAsBigDecimal();
        if (jsonpObject.get("talla")!=null && !jsonpObject.get("talla").getAsString().isEmpty()) tallaCm = jsonpObject.get("talla").getAsBigDecimal();
        if (jsonpObject.get("pa")!=null && !jsonpObject.get("pa").getAsString().isEmpty()) presion = jsonpObject.get("pa").getAsString();
        if (jsonpObject.get("fc")!=null && !jsonpObject.get("fc").getAsString().isEmpty()) fciaCard = jsonpObject.get("fc").getAsShort();
        if (jsonpObject.get("temp")!=null && !jsonpObject.get("temp").getAsString().isEmpty()) temperaturac = jsonpObject.get("temp").getAsBigDecimal();
        if (jsonpObject.get("so")!=null && !jsonpObject.get("so").getAsString().isEmpty()) saturaciono2 = jsonpObject.get("so").getAsShort();
        if (jsonpObject.get("horaIniCons")!=null && !jsonpObject.get("horaIniCons").getAsString().isEmpty()) horaInicioConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaIniCons").getAsString()));
        if (jsonpObject.get("tipoConsulta")!=null && !jsonpObject.get("tipoConsulta").getAsString().isEmpty()) consulta = jsonpObject.get("tipoConsulta").getAsString();
        if (jsonpObject.get("lugarConsulta")!=null && !jsonpObject.get("lugarConsulta").getAsString().isEmpty()) lugarAtencion = jsonpObject.get("lugarConsulta").getAsString();
        if (jsonpObject.get("paMedico")!=null && !jsonpObject.get("paMedico").getAsString().isEmpty()) presionMed = jsonpObject.get("paMedico").getAsString();
        if (jsonpObject.get("tempMedico")!=null && !jsonpObject.get("tempMedico").getAsString().isEmpty()) temMedc = jsonpObject.get("tempMedico").getAsBigDecimal();
        if (jsonpObject.get("frMedico")!=null && !jsonpObject.get("frMedico").getAsString().isEmpty()) fciaRespMed = jsonpObject.get("frMedico").getAsShort();
        if (jsonpObject.get("fcMedico")!=null && !jsonpObject.get("fcMedico").getAsString().isEmpty()) fciaCardMed = jsonpObject.get("fcMedico").getAsShort();
        if (jsonpObject.get("soMedico")!=null && !jsonpObject.get("soMedico").getAsString().isEmpty()) saturaciono2Med = jsonpObject.get("soMedico").getAsShort();
        if (jsonpObject.get("fis")!=null && !jsonpObject.get("fis").getAsString().isEmpty()) fis = DateUtil.StringToDate(jsonpObject.get("fis").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("fif")!=null && !jsonpObject.get("fif").getAsString().isEmpty()) fif = DateUtil.StringToDate(jsonpObject.get("fif").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("ultimoDiaFiebre")!=null && !jsonpObject.get("ultimoDiaFiebre").getAsString().isEmpty()) ultDiaFiebre = DateUtil.StringToDate(jsonpObject.get("ultimoDiaFiebre").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimoDiaF")!=null && !jsonpObject.get("horaUltimoDiaF").getAsString().isEmpty()) horaultDiaFiebre = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimoDiaF").getAsString()));
        if (jsonpObject.get("ultimaDosisAntip")!=null && !jsonpObject.get("ultimaDosisAntip").getAsString().isEmpty()) ultDosisAntipiretico = DateUtil.StringToDate(jsonpObject.get("ultimaDosisAntip").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaUltimaDosisAntip")!=null && !jsonpObject.get("horaUltimaDosisAntip").getAsString().isEmpty()) horaUltDosisAntipiretico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaUltimaDosisAntip").getAsString()));

        if (jsonpObject.get("rbgeneral_1")!=null && !jsonpObject.get("rbgeneral_1").getAsString().isEmpty()) fiebre = jsonpObject.get("rbgeneral_1").getAsString();
        if (jsonpObject.get("rbgeneral_2")!=null && !jsonpObject.get("rbgeneral_2").getAsString().isEmpty()) asomnoliento = jsonpObject.get("rbgeneral_2").getAsString();
        if (jsonpObject.get("rbgeneral_3")!=null && !jsonpObject.get("rbgeneral_3").getAsString().isEmpty()) malEstado = jsonpObject.get("rbgeneral_3").getAsString();
        if (jsonpObject.get("rbgeneral_4")!=null && !jsonpObject.get("rbgeneral_4").getAsString().isEmpty()) perdidaConsciencia = jsonpObject.get("rbgeneral_4").getAsString();
        if (jsonpObject.get("rbgeneral_5")!=null && !jsonpObject.get("rbgeneral_5").getAsString().isEmpty()) inquieto = jsonpObject.get("rbgeneral_5").getAsString();
        if (jsonpObject.get("rbgeneral_6")!=null && !jsonpObject.get("rbgeneral_6").getAsString().isEmpty()) convulsiones = jsonpObject.get("rbgeneral_6").getAsString();
        if (jsonpObject.get("rbgeneral_7")!=null && !jsonpObject.get("rbgeneral_7").getAsString().isEmpty()) letargia = jsonpObject.get("rbgeneral_7").getAsString();

        if (jsonpObject.get("rbcabeza_1")!=null && !jsonpObject.get("rbcabeza_1").getAsString().isEmpty()) dolorCabeza = jsonpObject.get("rbcabeza_1").getAsString();
        if (jsonpObject.get("rbcabeza_2")!=null && !jsonpObject.get("rbcabeza_2").getAsString().isEmpty()) conjuntivitis = jsonpObject.get("rbcabeza_2").getAsString();
        if (jsonpObject.get("rbcabeza_3")!=null && !jsonpObject.get("rbcabeza_3").getAsString().isEmpty()) hemorragiaSuconjuntival = jsonpObject.get("rbcabeza_3").getAsString();
        if (jsonpObject.get("rbcabeza_4")!=null && !jsonpObject.get("rbcabeza_4").getAsString().isEmpty()) dolorRetroocular = jsonpObject.get("rbcabeza_4").getAsString();

        if (jsonpObject.get("rbgarganta_1")!=null && !jsonpObject.get("rbgarganta_1").getAsString().isEmpty()) dolorGarganta = jsonpObject.get("rbgarganta_1").getAsString();
        if (jsonpObject.get("rbgarganta_2")!=null && !jsonpObject.get("rbgarganta_2").getAsString().isEmpty()) eritema = jsonpObject.get("rbgarganta_2").getAsString();
        if (jsonpObject.get("rbgarganta_3")!=null && !jsonpObject.get("rbgarganta_3").getAsString().isEmpty()) adenopatiasCervicales = jsonpObject.get("rbgarganta_3").getAsString();
        if (jsonpObject.get("rbgarganta_4")!=null && !jsonpObject.get("rbgarganta_4").getAsString().isEmpty()) exudado = jsonpObject.get("rbgarganta_4").getAsString();
        if (jsonpObject.get("rbgarganta_5")!=null && !jsonpObject.get("rbgarganta_5").getAsString().isEmpty()) petequiasMucosa = jsonpObject.get("rbgarganta_5").getAsString();

        if (jsonpObject.get("rbrespiratorio_1")!=null && !jsonpObject.get("rbrespiratorio_1").getAsString().isEmpty()) tos = jsonpObject.get("rbrespiratorio_1").getAsString();
        if (jsonpObject.get("rbrespiratorio_2")!=null && !jsonpObject.get("rbrespiratorio_2").getAsString().isEmpty()) rinorrea = jsonpObject.get("rbrespiratorio_2").getAsString();
        if (jsonpObject.get("rbrespiratorio_3")!=null && !jsonpObject.get("rbrespiratorio_3").getAsString().isEmpty()) congestionNasal = jsonpObject.get("rbrespiratorio_3").getAsString();
        if (jsonpObject.get("rbrespiratorio_4")!=null && !jsonpObject.get("rbrespiratorio_4").getAsString().isEmpty()) otalgia = jsonpObject.get("rbrespiratorio_4").getAsString();
        if (jsonpObject.get("rbrespiratorio_5")!=null && !jsonpObject.get("rbrespiratorio_5").getAsString().isEmpty()) aleteoNasal = jsonpObject.get("rbrespiratorio_5").getAsString();
        if (jsonpObject.get("rbrespiratorio_6")!=null && !jsonpObject.get("rbrespiratorio_6").getAsString().isEmpty()) respiracionRapida = jsonpObject.get("rbrespiratorio_6").getAsString();
        if (jsonpObject.get("rbrespiratorio_7")!=null && !jsonpObject.get("rbrespiratorio_7").getAsString().isEmpty()) estridorReposo = jsonpObject.get("rbrespiratorio_7").getAsString();
        if (jsonpObject.get("rbrespiratorio_8")!=null && !jsonpObject.get("rbrespiratorio_8").getAsString().isEmpty()) tirajeSubcostal = jsonpObject.get("rbrespiratorio_8").getAsString();
        if (jsonpObject.get("rbrespiratorio_9")!=null && !jsonpObject.get("rbrespiratorio_9").getAsString().isEmpty()) sibilancias = jsonpObject.get("rbrespiratorio_9").getAsString();
        if (jsonpObject.get("rbrespiratorio_10")!=null && !jsonpObject.get("rbrespiratorio_10").getAsString().isEmpty()) crepitos = jsonpObject.get("rbrespiratorio_10").getAsString();
        if (jsonpObject.get("rbrespiratorio_11")!=null && !jsonpObject.get("rbrespiratorio_11").getAsString().isEmpty()) roncos = jsonpObject.get("rbrespiratorio_11").getAsString();
        if (jsonpObject.get("rbrespiratorio_12")!=null && !jsonpObject.get("rbrespiratorio_12").getAsString().isEmpty()) disnea = jsonpObject.get("rbrespiratorio_12").getAsString();

        if (jsonpObject.get("rbgastroin_1")!=null && !jsonpObject.get("rbgastroin_1").getAsString().isEmpty()) pocoApetito = jsonpObject.get("rbgastroin_1").getAsString();
        if (jsonpObject.get("rbgastroin_2")!=null && !jsonpObject.get("rbgastroin_2").getAsString().isEmpty()) nausea = jsonpObject.get("rbgastroin_2").getAsString();
        if (jsonpObject.get("rbgastroin_3")!=null && !jsonpObject.get("rbgastroin_3").getAsString().isEmpty()) vomito12horas = jsonpObject.get("rbgastroin_3").getAsString();
        if (jsonpObject.get("numVomito")!=null && !jsonpObject.get("numVomito").getAsString().isEmpty()) numeroVomito12h = jsonpObject.get("numVomito").getAsShort();
        if (jsonpObject.get("rbgastroin_4")!=null && !jsonpObject.get("rbgastroin_4").getAsString().isEmpty()) diarrea = jsonpObject.get("rbgastroin_4").getAsString();
        if (jsonpObject.get("rbgastroin_5")!=null && !jsonpObject.get("rbgastroin_5").getAsString().isEmpty()) dolorAbdominal = jsonpObject.get("rbgastroin_5").getAsString();
        if (jsonpObject.get("rbgastroin_6")!=null && !jsonpObject.get("rbgastroin_6").getAsString().isEmpty()) hepatomegalia = jsonpObject.get("rbgastroin_6").getAsString();

        if (jsonpObject.get("rbosteomusc_1")!=null && !jsonpObject.get("rbosteomusc_1").getAsString().isEmpty()) artralgia = jsonpObject.get("rbosteomusc_1").getAsString();
        if (jsonpObject.get("rbosteomusc_2")!=null && !jsonpObject.get("rbosteomusc_2").getAsString().isEmpty()) mialgia = jsonpObject.get("rbosteomusc_2").getAsString();
        if (jsonpObject.get("rbosteomusc_3")!=null && !jsonpObject.get("rbosteomusc_3").getAsString().isEmpty()) lumbalgia = jsonpObject.get("rbosteomusc_3").getAsString();
        if (jsonpObject.get("rbosteomusc_4")!=null && !jsonpObject.get("rbosteomusc_4").getAsString().isEmpty()) dolorCuello = jsonpObject.get("rbosteomusc_4").getAsString();
        if (jsonpObject.get("rbosteomusc_5")!=null && !jsonpObject.get("rbosteomusc_5").getAsString().isEmpty()) edema = jsonpObject.get("rbosteomusc_5").getAsString();

        if (jsonpObject.get("rbcutaneo_1")!=null && !jsonpObject.get("rbcutaneo_1").getAsString().isEmpty()) rahsLocalizado = jsonpObject.get("rbcutaneo_1").getAsString();
        if (jsonpObject.get("rbcutaneo_2")!=null && !jsonpObject.get("rbcutaneo_2").getAsString().isEmpty()) rahsGeneralizado = jsonpObject.get("rbcutaneo_2").getAsString();
        if (jsonpObject.get("rbcutaneo_3")!=null && !jsonpObject.get("rbcutaneo_3").getAsString().isEmpty()) rashEritematoso = jsonpObject.get("rbcutaneo_3").getAsString();
        if (jsonpObject.get("rbcutaneo_4")!=null && !jsonpObject.get("rbcutaneo_4").getAsString().isEmpty()) rahsMacular = jsonpObject.get("rbcutaneo_4").getAsString();
        if (jsonpObject.get("rbcutaneo_5")!=null && !jsonpObject.get("rbcutaneo_5").getAsString().isEmpty()) rashPapular = jsonpObject.get("rbcutaneo_5").getAsString();
        if (jsonpObject.get("rbcutaneo_6")!=null && !jsonpObject.get("rbcutaneo_6").getAsString().isEmpty()) pielMoteada = jsonpObject.get("rbcutaneo_6").getAsString();
        if (jsonpObject.get("rbcutaneo_7")!=null && !jsonpObject.get("rbcutaneo_7").getAsString().isEmpty()) ruborFacial = jsonpObject.get("rbcutaneo_7").getAsString();
        if (jsonpObject.get("rbcutaneo_8")!=null && !jsonpObject.get("rbcutaneo_8").getAsString().isEmpty()) cianosisCentral = jsonpObject.get("rbcutaneo_8").getAsString();
        if (jsonpObject.get("rbcutaneo_9")!=null && !jsonpObject.get("rbcutaneo_9").getAsString().isEmpty()) ictericia = jsonpObject.get("rbcutaneo_9").getAsString();

        if (jsonpObject.get("imc")!=null && !jsonpObject.get("imc").getAsString().isEmpty()) imc = jsonpObject.get("imc").getAsBigDecimal();
        if (jsonpObject.get("rbestadonut_1")!=null && !jsonpObject.get("rbestadonut_1").getAsString().isEmpty()) obeso = jsonpObject.get("rbestadonut_1").getAsString();
        if (jsonpObject.get("rbestadonut_2")!=null && !jsonpObject.get("rbestadonut_2").getAsString().isEmpty()) sobrepeso = jsonpObject.get("rbestadonut_2").getAsString();
        if (jsonpObject.get("rbestadonut_3")!=null && !jsonpObject.get("rbestadonut_3").getAsString().isEmpty()) sospechaProblema = jsonpObject.get("rbestadonut_3").getAsString();
        if (jsonpObject.get("rbestadonut_4")!=null && !jsonpObject.get("rbestadonut_4").getAsString().isEmpty()) normal = jsonpObject.get("rbestadonut_4").getAsString();
        if (jsonpObject.get("rbestadonut_5")!=null && !jsonpObject.get("rbestadonut_5").getAsString().isEmpty()) bajoPeso = jsonpObject.get("rbestadonut_5").getAsString();
        if (jsonpObject.get("rbestadonut_6")!=null && !jsonpObject.get("rbestadonut_6").getAsString().isEmpty()) bajoPesoSevero = jsonpObject.get("rbestadonut_6").getAsString();

        if (jsonpObject.get("categoria")!=null && !jsonpObject.get("categoria").getAsString().isEmpty()) categoria = jsonpObject.get("categoria").getAsString();
        if (jsonpObject.get("cambioCategoria")!=null && !jsonpObject.get("cambioCategoria").getAsString().isEmpty()) cambioCategoria = jsonpObject.get("cambioCategoria").getAsString();
        if (jsonpObject.get("rbmanhemo_1")!=null && !jsonpObject.get("rbmanhemo_1").getAsString().isEmpty()) pruebaTorniquetePositiva = jsonpObject.get("rbmanhemo_1").getAsString();
        if (jsonpObject.get("rbmanhemo_2")!=null && !jsonpObject.get("rbmanhemo_2").getAsString().isEmpty()) petequia10Pt = jsonpObject.get("rbmanhemo_2").getAsString();
        if (jsonpObject.get("rbmanhemo_3")!=null && !jsonpObject.get("rbmanhemo_3").getAsString().isEmpty()) petequia20Pt = jsonpObject.get("rbmanhemo_3").getAsString();
        if (jsonpObject.get("rbmanhemo_4")!=null && !jsonpObject.get("rbmanhemo_4").getAsString().isEmpty()) pielExtremidadesFrias = jsonpObject.get("rbmanhemo_4").getAsString();
        if (jsonpObject.get("rbmanhemo_5")!=null && !jsonpObject.get("rbmanhemo_5").getAsString().isEmpty()) palidezEnExtremidades = jsonpObject.get("rbmanhemo_5").getAsString();
        if (jsonpObject.get("rbmanhemo_6")!=null && !jsonpObject.get("rbmanhemo_6").getAsString().isEmpty()) epistaxis = jsonpObject.get("rbmanhemo_6").getAsString();
        if (jsonpObject.get("rbmanhemo_7")!=null && !jsonpObject.get("rbmanhemo_7").getAsString().isEmpty()) gingivorragia = jsonpObject.get("rbmanhemo_7").getAsString();
        if (jsonpObject.get("rbmanhemo_8")!=null && !jsonpObject.get("rbmanhemo_8").getAsString().isEmpty()) petequiasEspontaneas = jsonpObject.get("rbmanhemo_8").getAsString();
        if (jsonpObject.get("rbmanhemo_9")!=null && !jsonpObject.get("rbmanhemo_9").getAsString().isEmpty()) llenadoCapilar2seg = jsonpObject.get("rbmanhemo_9").getAsString();
        if (jsonpObject.get("rbmanhemo_10")!=null && !jsonpObject.get("rbmanhemo_10").getAsString().isEmpty()) cianosis = jsonpObject.get("rbmanhemo_10").getAsString();
        if (jsonpObject.get("rbmanhemo_11")!=null && !jsonpObject.get("rbmanhemo_11").getAsString().isEmpty()) hipermenorrea = jsonpObject.get("rbmanhemo_11").getAsString();
        if (jsonpObject.get("rbmanhemo_12")!=null && !jsonpObject.get("rbmanhemo_12").getAsString().isEmpty()) hematemesis = jsonpObject.get("rbmanhemo_12").getAsString();
        if (jsonpObject.get("rbmanhemo_13")!=null && !jsonpObject.get("rbmanhemo_13").getAsString().isEmpty()) hemoconcentracion = jsonpObject.get("rbmanhemo_13").getAsString();

        if (jsonpObject.get("hospitalizado")!=null && !jsonpObject.get("hospitalizado").getAsString().isEmpty()) hospitalizado = jsonpObject.get("hospitalizado").getAsString();
        if (jsonpObject.get("unidadSaludHosp")!=null && !jsonpObject.get("unidadSaludHosp").getAsString().isEmpty()) hospitalizadoEspecificar = jsonpObject.get("unidadSaludHosp").getAsString();
        if (jsonpObject.get("transfusion")!=null && !jsonpObject.get("transfusion").getAsString().isEmpty()) transfusionSangre = jsonpObject.get("transfusion").getAsString();
        if (jsonpObject.get("transfusionEsp")!=null && !jsonpObject.get("transfusionEsp").getAsString().isEmpty()) transfusionEspecificar = jsonpObject.get("transfusionEsp").getAsString();
        if (jsonpObject.get("tomaMedicamento")!=null && !jsonpObject.get("tomaMedicamento").getAsString().isEmpty()) tomandoMedicamento = jsonpObject.get("tomaMedicamento").getAsString();
        if (jsonpObject.get("cualMedicamento")!=null && !jsonpObject.get("cualMedicamento").getAsString().isEmpty()) medicamentoEspecificar = jsonpObject.get("cualMedicamento").getAsString();

        if (jsonpObject.get("rbexamen_1")!=null && !jsonpObject.get("rbexamen_1").getAsString().isEmpty()) bhc = jsonpObject.get("rbexamen_1").getAsString();
        if (jsonpObject.get("rbexamen_2")!=null && !jsonpObject.get("rbexamen_2").getAsString().isEmpty()) serologiaArbovirus = jsonpObject.get("rbexamen_2").getAsString();
        if (jsonpObject.get("rbexamen_3")!=null && !jsonpObject.get("rbexamen_3").getAsString().isEmpty()) gotaGruesa = jsonpObject.get("rbexamen_3").getAsString();
        if (jsonpObject.get("rbexamen_4")!=null && !jsonpObject.get("rbexamen_4").getAsString().isEmpty()) ego = jsonpObject.get("rbexamen_4").getAsString();
        if (jsonpObject.get("rbexamen_5")!=null && !jsonpObject.get("rbexamen_5").getAsString().isEmpty()) egh = jsonpObject.get("rbexamen_5").getAsString();
        if (jsonpObject.get("rbexamen_6")!=null && !jsonpObject.get("rbexamen_6").getAsString().isEmpty()) otroExamenLab = jsonpObject.get("rbexamen_6").getAsString();
        if (jsonpObject.get("descOtroExamen")!=null && !jsonpObject.get("descOtroExamen").getAsString().isEmpty()) otroExamanLabEspecificar = jsonpObject.get("descOtroExamen").getAsString();

        if (jsonpObject.get("rbtratamiento_1")!=null && !jsonpObject.get("rbtratamiento_1").getAsString().isEmpty()) acetaminofen = jsonpObject.get("rbtratamiento_1").getAsString();
        if (jsonpObject.get("rbtratamiento_2")!=null && !jsonpObject.get("rbtratamiento_2").getAsString().isEmpty()) amoxicilina = jsonpObject.get("rbtratamiento_2").getAsString();
        if (jsonpObject.get("rbtratamiento_3")!=null && !jsonpObject.get("rbtratamiento_3").getAsString().isEmpty()) dicloxacilina = jsonpObject.get("rbtratamiento_3").getAsString();
        if (jsonpObject.get("rbtratamiento_4")!=null && !jsonpObject.get("rbtratamiento_4").getAsString().isEmpty()) penicilina = jsonpObject.get("rbtratamiento_4").getAsString();
        if (jsonpObject.get("rbtratamiento_5")!=null && !jsonpObject.get("rbtratamiento_5").getAsString().isEmpty()) furazolidona = jsonpObject.get("rbtratamiento_5").getAsString();
        if (jsonpObject.get("rbtratamiento_6")!=null && !jsonpObject.get("rbtratamiento_6").getAsString().isEmpty()) metronidazolTinidazol = jsonpObject.get("rbtratamiento_6").getAsString();
        if (jsonpObject.get("rbtratamiento_7")!=null && !jsonpObject.get("rbtratamiento_7").getAsString().isEmpty()) albendazolMebendazol = jsonpObject.get("rbtratamiento_7").getAsString();
        if (jsonpObject.get("rbtratamiento_8")!=null && !jsonpObject.get("rbtratamiento_8").getAsString().isEmpty()) sueroOral = jsonpObject.get("rbtratamiento_8").getAsString();
        if (jsonpObject.get("rbtratamiento_9")!=null && !jsonpObject.get("rbtratamiento_9").getAsString().isEmpty()) otroTratamiento = jsonpObject.get("rbtratamiento_9").getAsString();
        if (jsonpObject.get("rbtratamiento_10")!=null && !jsonpObject.get("rbtratamiento_10").getAsString().isEmpty()) liquidos4 = jsonpObject.get("rbtratamiento_10").getAsString();
        if (jsonpObject.get("descOtroTratamiento")!=null && !jsonpObject.get("descOtroTratamiento").getAsString().isEmpty()) otroTratamientoEspecificar = jsonpObject.get("descOtroTratamiento").getAsString();
        if (jsonpObject.get("planes")!=null && !jsonpObject.get("planes").getAsString().isEmpty()) planes = jsonpObject.get("planes").getAsString();
        if (jsonpObject.get("historia")!=null && !jsonpObject.get("historia").getAsString().isEmpty()) historiaClinica = jsonpObject.get("historia").getAsString();
        if (jsonpObject.get("dx")!=null && !jsonpObject.get("dx").getAsString().isEmpty()) diagnostico = jsonpObject.get("dx").getAsString();

        if (jsonpObject.get("diagnostico1")!=null && !jsonpObject.get("diagnostico1").getAsString().isEmpty()) diagnostico1 = jsonpObject.get("diagnostico1").getAsString();
        if (jsonpObject.get("diagnostico2")!=null && !jsonpObject.get("diagnostico2").getAsString().isEmpty()) diagnostico2 = jsonpObject.get("diagnostico2").getAsString();
        if (jsonpObject.get("diagnostico3")!=null && !jsonpObject.get("diagnostico3").getAsString().isEmpty()) diagnostico3 = jsonpObject.get("diagnostico3").getAsString();
        if (jsonpObject.get("diagnostico4")!=null && !jsonpObject.get("diagnostico4").getAsString().isEmpty()) diagnostico4 = jsonpObject.get("diagnostico4").getAsString();

        if (jsonpObject.get("telefono")!=null && !jsonpObject.get("telefono").getAsString().isEmpty()) telefonoEmergencia = jsonpObject.get("telefono").getAsString();
        if (jsonpObject.get("cita")!=null && !jsonpObject.get("cita").getAsString().isEmpty()) proximaCita = DateUtil.StringToDate(jsonpObject.get("cita").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("medico")!=null && !jsonpObject.get("medico").getAsString().isEmpty()) medico = jsonpObject.get("medico").getAsShort();
        if (jsonpObject.get("fechaMedico")!=null && !jsonpObject.get("fechaMedico").getAsString().isEmpty()) fechaMedico = DateUtil.StringToDate(jsonpObject.get("fechaMedico").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaMedico")!=null && !jsonpObject.get("horaMedico").getAsString().isEmpty()) horaMedico = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaMedico").getAsString()));
        if (jsonpObject.get("enfermeria")!=null && !jsonpObject.get("enfermeria").getAsString().isEmpty()) enfermeria = jsonpObject.get("enfermeria").getAsShort();
        if (jsonpObject.get("fechaEnfermeria")!=null && !jsonpObject.get("fechaEnfermeria").getAsString().isEmpty()) fechaEnfermeria = DateUtil.StringToDate(jsonpObject.get("fechaEnfermeria").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaEnfermeria")!=null && !jsonpObject.get("horaEnfermeria").getAsString().isEmpty()) horaEnfermeria = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaEnfermeria").getAsString()));
*/
    /*    hojaClinica = this.hojaClinicaService.getObtenerRegHojaClinicaCorrecH2(codigoParticipante,numeroHoja);
//        fechreg1 = hojaClinica.getRecordDate();
        //      usuarreg1 = hojaClinica.getRecordUser();
        String codigoParticipante2 = jObjectRespuestas.get("participante").getAsString();

        Participante participante2 = participanteService.getParticipanteByCodigo(codigoParticipante2);
        logger.debug("codigoParticipante2 :" + participante2);
//          hojaClinica.setCodigoParticipante(participante2);
        hojaClinica.setCodigoParticipante(participante2);


        //   hojaClinica.setCodigoParticipante(participante);
        hojaClinica.setFechaConsulta(fechaConsulta);
        hojaClinica.setHoraConsulta(horaConsulta);
        hojaClinica.setCodigo_supervisor(codigosupe); ;
        hojaClinica.setNumHojaConsulta(numeroHoja);

        hojaClinica.setPesoKg(pesoKg);
        hojaClinica.setTallaCm(tallaCm);
        hojaClinica.setPresion(presion);
        hojaClinica.setFciaCard(fciaCard);
        hojaClinica.setTemperaturac(temperaturac);
        hojaClinica.setSaturaciono2(saturaciono2);
        hojaClinica.setHoraInicioConsulta(horaInicioConsulta);
        hojaClinica.setConsulta(consulta);
        hojaClinica.setLugarAtencion(lugarAtencion);
        hojaClinica.setPresionMed(presionMed);
        hojaClinica.setTemMedc(temMedc);
        hojaClinica.setFciaRespMed(fciaRespMed);
        hojaClinica.setFciaCardMed(fciaCardMed);
        hojaClinica.setSaturaciono2Med(saturaciono2Med);
        hojaClinica.setFis(fis);
        hojaClinica.setFif(fif);
        hojaClinica.setUltDiaFiebre(ultDiaFiebre);
        hojaClinica.setHoraultDiaFiebre(horaultDiaFiebre);
        hojaClinica.setUltDosisAntipiretico(ultDosisAntipiretico);
        hojaClinica.setHoraUltDosisAntipiretico(horaUltDosisAntipiretico);
        hojaClinica.setFiebre(fiebre);
        hojaClinica.setAsomnoliento(asomnoliento);
        hojaClinica.setMalEstado(malEstado);
        hojaClinica.setPerdidaConsciencia(perdidaConsciencia);
        hojaClinica.setInquieto(inquieto);
        hojaClinica.setConvulsiones(convulsiones);
        hojaClinica.setLetargia(letargia);
        hojaClinica.setDolorCabeza(dolorCabeza);
        hojaClinica.setConjuntivitis(conjuntivitis);
        hojaClinica.setHemorragiaSuconjuntival(hemorragiaSuconjuntival);
        hojaClinica.setDolorRetroocular(dolorRetroocular);
        hojaClinica.setDolorGarganta(dolorGarganta);
        hojaClinica.setEritema(eritema);
        hojaClinica.setAdenopatiasCervicales(adenopatiasCervicales);
        hojaClinica.setExudado(exudado);
        hojaClinica.setPetequiasMucosa(petequiasMucosa);
        hojaClinica.setTos(tos);
        hojaClinica.setRinorrea(rinorrea);
        hojaClinica.setCongestionNasal(congestionNasal);
        hojaClinica.setOtalgia(otalgia);
        hojaClinica.setAleteoNasal(aleteoNasal);
        hojaClinica.setRespiracionRapida(respiracionRapida);
        hojaClinica.setEstridorReposo(estridorReposo);
        hojaClinica.setTirajeSubcostal(tirajeSubcostal);
        hojaClinica.setSibilancias(sibilancias);
        hojaClinica.setCrepitos(crepitos);
        hojaClinica.setRoncos(roncos);
        hojaClinica.setDisnea(disnea);
        hojaClinica.setPocoApetito(pocoApetito);
        hojaClinica.setNausea(nausea);
        hojaClinica.setVomito12horas(vomito12horas);
        hojaClinica.setNumeroVomito12h(numeroVomito12h);
        hojaClinica.setDiarrea(diarrea);
        hojaClinica.setHepatomegalia(hepatomegalia);
        hojaClinica.setDolorAbdominal(dolorAbdominal);
        hojaClinica.setArtralgia(artralgia);
        hojaClinica.setMialgia(mialgia);
        hojaClinica.setLumbalgia(lumbalgia);
        hojaClinica.setDolorCuello(dolorCuello);
        hojaClinica.setEdema(edema);
        hojaClinica.setRahsLocalizado(rahsLocalizado);
        hojaClinica.setRahsGeneralizado(rahsGeneralizado);
        hojaClinica.setRashEritematoso(rashEritematoso);
        hojaClinica.setRahsMacular(rahsMacular);
        hojaClinica.setRashPapular(rashPapular);
        hojaClinica.setPielMoteada(pielMoteada);
        hojaClinica.setRuborFacial(ruborFacial);
        hojaClinica.setCianosisCentral(cianosisCentral);
        hojaClinica.setIctericia(ictericia);
        hojaClinica.setImc(imc);
        hojaClinica.setObeso(obeso);
        hojaClinica.setSobrepeso(sobrepeso);
        hojaClinica.setSospechaProblema(sospechaProblema);
        hojaClinica.setNormal(normal);
        hojaClinica.setBajoPeso(bajoPeso);
        hojaClinica.setBajoPesoSevero(bajoPesoSevero);
        hojaClinica.setCategoria(categoria);
        hojaClinica.setCambioCategoria(cambioCategoria);
        hojaClinica.setPruebaTorniquetePositiva(pruebaTorniquetePositiva);
        hojaClinica.setPetequia10Pt(petequia10Pt);
        hojaClinica.setPetequia20Pt(petequia20Pt);
        hojaClinica.setPielExtremidadesFrias(pielExtremidadesFrias);
        hojaClinica.setPalidezEnExtremidades(palidezEnExtremidades);
        hojaClinica.setEpistaxis(epistaxis);
        hojaClinica.setGingivorragia(gingivorragia);
        hojaClinica.setPetequiasEspontaneas(petequiasEspontaneas);
        hojaClinica.setLlenadoCapilar2seg(llenadoCapilar2seg);
        hojaClinica.setCianosis(cianosis);
        hojaClinica.setHipermenorrea(hipermenorrea);
        hojaClinica.setHematemesis(hematemesis);
        hojaClinica.setHemoconcentracion(hemoconcentracion);
        hojaClinica.setHospitalizado(hospitalizado);
        hojaClinica.setHospitalizadoEspecificar(hospitalizadoEspecificar);
        hojaClinica.setTransfusionSangre(transfusionSangre);
        hojaClinica.setTransfusionEspecificar(transfusionEspecificar);
        hojaClinica.setTomandoMedicamento(tomandoMedicamento);
        hojaClinica.setMedicamentoEspecificar(medicamentoEspecificar);
        hojaClinica.setBhc(bhc);
        hojaClinica.setSerologiaArbovirus(serologiaArbovirus);
        hojaClinica.setGotaGruesa(gotaGruesa);
        hojaClinica.setEgo(ego);
        hojaClinica.setEgh(egh);
        hojaClinica.setOtroExamenLab(otroExamenLab);
        hojaClinica.setOtroExamanLabEspecificar(otroExamanLabEspecificar);
        hojaClinica.setAcetaminofen(acetaminofen);
        hojaClinica.setAmoxicilina(amoxicilina);
        hojaClinica.setDicloxacilina(dicloxacilina);
        hojaClinica.setPenicilina(penicilina);
        hojaClinica.setFurazolidona(furazolidona);
        hojaClinica.setMetronidazolTinidazol(metronidazolTinidazol);
        hojaClinica.setAlbendazolMebendazol(albendazolMebendazol);
        hojaClinica.setSueroOral(sueroOral);
        hojaClinica.setLiquidos4(liquidos4);
        hojaClinica.setOtroTratamiento(otroTratamiento);
        hojaClinica.setOtroTratamientoEspecificar(otroTratamientoEspecificar);
        hojaClinica.setPlanes(planes);
        hojaClinica.setHistoriaClinica(historiaClinica);
        hojaClinica.setDiagnostico(diagnostico);

        hojaClinica.setDiagnostico1(diagnostico1);
        hojaClinica.setDiagnostico2(diagnostico2);
        hojaClinica.setDiagnostico3(diagnostico3);
        hojaClinica.setDiagnostico4(diagnostico4);

        hojaClinica.setTelefonoEmergencia(telefonoEmergencia);
        hojaClinica.setProximaCita(proximaCita);
        hojaClinica.setMedico(medico);
        hojaClinica.setFechaMedico(fechaMedico);
        hojaClinica.setHoraMedico(horaMedico);
        hojaClinica.setEnfermeria(enfermeria);
        hojaClinica.setFechaEnfermeria(fechaEnfermeria);
        hojaClinica.setHoraEnfermeria(horaEnfermeria);*/

        return hojaClinica;
    }
}
