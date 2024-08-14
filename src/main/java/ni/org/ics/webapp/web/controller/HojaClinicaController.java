package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.webapp.domain.Serologia.Bhc_Bc6000;
import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.InputStream;

import java.io.IOException;

/**
 * Created by miguel on 27/8/2021.
 */
@Controller
@RequestMapping("/hojaclinica/*")
public class HojaClinicaController {

    private static final Logger logger = LoggerFactory.getLogger(HojaClinicaController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "personalService")
    private PersonalService personalService;

    @Resource(name = "hojaClinicaService")
    private HojaClinicaService hojaClinicaService;
    int nuevos =0, viejos=0;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando hojas clinicas en JSP");
        return "fingering/clinicalSheetList";
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
        return "fingering/clinicalSheet";
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
   // @RequestMapping(value = "/editarBHC/{codigo_participante}/{bhc_id}/", method = RequestMethod.GET)
   //@RequestMapping(value = "/editarBHC/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @RequestMapping(value = "/editarBHC1/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.GET, produces = "application/json")
    //public @ResponseBody
 //   ResponseEntity<String> editarBHC1(Model model, @PathVariable("participante") String participante,
    public String editarBHC1(Model model, @PathVariable("participante") String participante,
                           @PathVariable("idbhc") int idbhc,@PathVariable("fecha") String fecha,
                           @PathVariable("numEnvio") String numEnvio) throws ParseException
    {
        try{
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
            Map<String, String> map = new HashMap<String, String>();
           // Participante participante = this.participanteService.getParticipanteByCodigo(participante);

        //   String ResultModificarBhc = this.hojaClinicaService.getGuardarEdicionBHC(participante, idbhc);
           // DiferenciasHojasDigitadasDto hojaClinicaList2 = this.hojaClinicaService.getObtenerRegHojaClinica2(codigo_participante1,numero_hoja);
            List<BhcDto> Bhclist;
         //   logger.debug("ResultModificarBhc " + ResultModificarBhc);
                logger.debug("Buscar BHC Procesadas");

                logger.debug("numEnvio" + numEnvio );
            logger.debug("fechaInicioCons" + participante );
            logger.debug("nEnvios: " + idbhc );

            List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
            model.addAttribute("numero_envio", numero_envio);
                if (fecha != null) {
                    Bhclist = this.hojaClinicaService.getObtenerBHCEnviadas(DateUtil.StringToDate(fecha, Constants.STRING_FORMAT_DD_MM_YYYY), numEnvio,participante,idbhc);
                    // logger.debug("getIdbhc: " + Bhclist.get().getParticipante() );
                    // logger.debug("getParticipante: " + Bhclist.get().getParticipante() );
                    //return JsonUtil.createJsonResponse(hojaClinicaList);
                    for(BhcDto bhclst : Bhclist) {
                        //bhclst.getIdbhc();

                     //  return JsonUtil.createJsonResponse(Bhclist);

                        model.addAttribute("fecha", (fecha.substring(8,2)+"/"+fecha.substring(5,2)+"/"+fecha.substring(1,4)));
                        model.addAttribute("numEnvio", numEnvio);
                        logger.debug("fecha" + fecha);

                    }
                } //else
                 //   return JsonUtil.createJsonResponse("No se encontró Hoja Clínica según el código ingresado");
         //   return "fingering/procesamientoBHCSocratesFlores";
             //  return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());

               return "fingering/procesamientoBHCSocratesFlores1";
             // return JsonUtil.createJsonResponse("BHC de participante " + participante + " Marcada con éxito el campo. Campo: PROCESADA_CSFV = S ");
           // return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "fingering/procesamientoBHCSocratesFlores1";
          //  return JsonUtil.createJsonResponse("Error procesando Edición de BHC. ");
        }
    }
    @RequestMapping(value = "/editarBHC2/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.GET, produces = "application/json")
    //public @ResponseBody
    //   ResponseEntity<String> editarBHC1(Model model, @PathVariable("participante") String participante,
    public String editarBHC2(Model model, @PathVariable("participante") String participante,
                             @PathVariable("idbhc") int idbhc,@PathVariable("fecha") String fecha,
                             @PathVariable("numEnvio") String numEnvio) throws ParseException
    {
        try{
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
            Map<String, String> map = new HashMap<String, String>();
            // Participante participante = this.participanteService.getParticipanteByCodigo(participante);

            //   String ResultModificarBhc = this.hojaClinicaService.getGuardarEdicionBHC(participante, idbhc);
            // DiferenciasHojasDigitadasDto hojaClinicaList2 = this.hojaClinicaService.getObtenerRegHojaClinica2(codigo_participante1,numero_hoja);
            List<BhcDto> Bhclist;
            //   logger.debug("ResultModificarBhc " + ResultModificarBhc);
            logger.debug("Buscar BHC Procesadas");

            logger.debug("numEnvio" + numEnvio );
            logger.debug("fechaInicioCons" + participante );
            logger.debug("nEnvios: " + idbhc );

            List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
            model.addAttribute("numero_envio", numero_envio);
            if (fecha != null) {
                Bhclist = this.hojaClinicaService.getObtenerBHCEnviadas2(DateUtil.StringToDate(fecha, Constants.STRING_FORMAT_DD_MM_YYYY), numEnvio,participante,idbhc);
                // logger.debug("getIdbhc: " + Bhclist.get().getParticipante() );
                // logger.debug("getParticipante: " + Bhclist.get().getParticipante() );
                //return JsonUtil.createJsonResponse(hojaClinicaList);
                for(BhcDto bhclst : Bhclist) {
                    //bhclst.getIdbhc();

                    //  return JsonUtil.createJsonResponse(Bhclist);

                    model.addAttribute("fecha", (fecha.substring(8,2)+"/"+fecha.substring(5,2)+"/"+fecha.substring(1,4)));
                    model.addAttribute("numEnvio", numEnvio);
                    logger.debug("fecha" + fecha);

                }
            } //else
            //   return JsonUtil.createJsonResponse("No se encontró Hoja Clínica según el código ingresado");
            //   return "fingering/procesamientoBHCSocratesFlores";
            //  return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());

            return "fingering/procesamientoBHCSocratesFlores1";
            // return JsonUtil.createJsonResponse("BHC de participante " + participante + " Marcada con éxito el campo. Campo: PROCESADA_CSFV = S ");
            // return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "fingering/procesamientoBHCSocratesFlores1";
            //  return JsonUtil.createJsonResponse("Error procesando Edición de BHC. ");
        }
    }



    @RequestMapping(value = "/editarBHC/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    protected void editarBHC(HttpServletRequest request, HttpServletResponse response,@PathVariable("participante") String participante,
                             @PathVariable("idbhc") int idbhc,@PathVariable("fecha") String fecha,
                             @PathVariable("numEnvio") String numEnvio) throws ServletException, IOException {
        logger.debug("Guardando BHC");
        String resultado = "";
        String error = "";
        try {
          //  HojaClinica hojaClinica = ParseJsonRequestToHojaClinica(request);
         //   hojaClinica.setRecordDate(new Date());
        //    hojaClinica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            //para nuevos registros es necesario poner un valor por defecto en el numero de hoja, aunque se setee con el trigger tg_set_numero_hoja
            //
            // hojaClinica.setNumHojaConsulta(0);

         //   hojaClinicaService.saveOrUpdate(hojaClinica);
            resultado = "participante " + participante;
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




    @RequestMapping(value = "/editarHC/{codigo_participante1}/{numero_hoja1}/", method = RequestMethod.GET)
    public String editarHC(Model model, @PathVariable("codigo_participante1") String codigo_participante1,
                              @PathVariable("numero_hoja1") String numero_hoja) throws Exception
    {
        try{
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
            Map<String, String> map = new HashMap<String, String>();
            Participante participante = this.participanteService.getParticipanteByCodigo(codigo_participante1);

             DiferenciasHojasDigitadasDto hojaClinicaList = this.hojaClinicaService.getObtenerRegHojaClinica(codigo_participante1,numero_hoja);
             DiferenciasHojasDigitadasDto hojaClinicaList2 = this.hojaClinicaService.getObtenerRegHojaClinica2(codigo_participante1,numero_hoja);
               if (hojaClinicaList != null && hojaClinicaList2 != null ){
                DiferenciasHojasDigitadasDto editarHojaC = new DiferenciasHojasDigitadasDto();
                   editarHojaC.setCodigo_participante1(hojaClinicaList.getCodigo_participante1());

                   map.put("codigo", participante.getCodigo());
                   model.addAttribute("nombre", participante.getNombreCompleto());
                   model.addAttribute("fechaNac", participante.getFechaNac());
                   model.addAttribute("edad", participante.getEdad());
                   model.addAttribute("sexo", participante.getSexo());
                   model.addAttribute("codigo_participante1",hojaClinicaList.getCodigo_participante1() );

                   if (!hojaClinicaList.getCodigo_supervisor1().equalsIgnoreCase(hojaClinicaList2.getCodigo_supervisor1())) {
                       model.addAttribute("codigo_supervisor1", "/N");
                   }else{
                       model.addAttribute("codigo_supervisor1", hojaClinicaList.getCodigo_supervisor1());
                   }
                   if (hojaClinicaList.getFecha_consulta1() != null) {
                       if (!hojaClinicaList.getFecha_consulta1().equalsIgnoreCase(hojaClinicaList2.getFecha_consulta1())) {
                           model.addAttribute("fecha_consulta1", "/N");
                       } else {
                           model.addAttribute("fecha_consulta1", hojaClinicaList.getFecha_consulta1());
                       }
                   }
                   if (!hojaClinicaList.getHora_consulta1().equalsIgnoreCase(hojaClinicaList2.getHora_consulta1())) {
                       model.addAttribute("hora_consulta1", "/N");
                   }else{
                       model.addAttribute("hora_consulta1", hojaClinicaList.getHora_consulta1());
                   }
                   if (!hojaClinicaList.getNumero_hoja1().equalsIgnoreCase(hojaClinicaList2.getNumero_hoja1())) {
                       model.addAttribute("numero_hoja1", "/N");
                   }else{
                       model.addAttribute("numero_hoja1", hojaClinicaList.getNumero_hoja1());
                   }
                   if (!hojaClinicaList.getPeso_kg1().equalsIgnoreCase(hojaClinicaList2.getPeso_kg1())) {
                       model.addAttribute("peso_kg1", "/N");
                   }else{
                       model.addAttribute("peso_kg1", hojaClinicaList.getPeso_kg1());
                   }
                   if (!hojaClinicaList.getTalla_cm1().equalsIgnoreCase(hojaClinicaList2.getTalla_cm1())) {
                       model.addAttribute("talla_cm1", "/N");
                   }else{
                       model.addAttribute("talla_cm1", hojaClinicaList.getTalla_cm1());
                   }
                   if (!hojaClinicaList.getPresion1().equalsIgnoreCase(hojaClinicaList2.getPresion1())) {
                       model.addAttribute("presion1", "/N");
                   }else{
                       model.addAttribute("presion1", hojaClinicaList.getPresion1());
                   }
                   if (!hojaClinicaList.getFcia_card1().equalsIgnoreCase(hojaClinicaList2.getFcia_card1())) {
                       model.addAttribute("fcia_card1", "/N");
                   }else{
                       model.addAttribute("fcia_card1", hojaClinicaList.getFcia_card1());
                   }
                   if (!hojaClinicaList.getTemperaturac1().equalsIgnoreCase(hojaClinicaList2.getTemperaturac1())) {
                       model.addAttribute("temperaturac1", "/N");
                   }else{
                       model.addAttribute("temperaturac1", hojaClinicaList.getTemperaturac1());
                   }
                   if (!hojaClinicaList.getSaturacion1().equalsIgnoreCase(hojaClinicaList2.getSaturacion1())) {
                       model.addAttribute("saturacion1", "/N");
                   }else{
                       model.addAttribute("saturacion1", hojaClinicaList.getSaturacion1());
                   }
                   if (!hojaClinicaList.getHora_inicio_consulta1().equalsIgnoreCase(hojaClinicaList2.getHora_inicio_consulta1())) {
                       model.addAttribute("hora_inicio_consulta1", "/N");
                   }else{
                       model.addAttribute("hora_inicio_consulta1", hojaClinicaList.getHora_inicio_consulta1());
                   }
                   if (!hojaClinicaList.getConsulta1().equalsIgnoreCase(hojaClinicaList2.getConsulta1())) {
                       model.addAttribute("consulta1", "/N");
                   }else{
                       model.addAttribute("consulta1", hojaClinicaList.getConsulta1());
                   }
                   if (!hojaClinicaList.getLugar_atencion1().equalsIgnoreCase(hojaClinicaList2.getLugar_atencion1())) {
                       model.addAttribute("lugar_atencion1", "/N");
                   }else{
                       model.addAttribute("lugar_atencion1", hojaClinicaList.getLugar_atencion1());
                   }
                   if (!hojaClinicaList.getPresion_medico1().equalsIgnoreCase(hojaClinicaList2.getPresion_medico1())) {
                       model.addAttribute("presion_medico1", "/N");
                   }else{
                       model.addAttribute("presion_medico1", hojaClinicaList.getPresion_medico1());
                   }
                   if (!hojaClinicaList.getTemperatura_medico1().equalsIgnoreCase(hojaClinicaList2.getTemperatura_medico1())) {
                       model.addAttribute("temperatura_medico1", "/N");
                   }else{
                       model.addAttribute("temperatura_medico1", hojaClinicaList.getTemperatura_medico1());
                   }
                   if (!hojaClinicaList.getFcia_resp1().equalsIgnoreCase(hojaClinicaList2.getFcia_resp1())) {
                       model.addAttribute("fcia_resp1", "/N");
                   }else{
                       model.addAttribute("fcia_resp1", hojaClinicaList.getFcia_resp1());
                   }
                   if (!hojaClinicaList.getFcia_card_medico1().equalsIgnoreCase(hojaClinicaList2.getFcia_card_medico1())) {
                       model.addAttribute("fcia_card_medico1", "/N");
                   }else{
                       model.addAttribute("fcia_card_medico1", hojaClinicaList.getFcia_card_medico1());
                   }
                   if (!hojaClinicaList.getSaturaciono2_medico1().equalsIgnoreCase(hojaClinicaList2.getSaturaciono2_medico1())) {
                       model.addAttribute("saturaciono2_medico1", "/N");
                   }else{
                       model.addAttribute("saturaciono2_medico1", hojaClinicaList.getSaturaciono2_medico1());
                   }
                   if (hojaClinicaList.getFis1() != null) {
                       if (!hojaClinicaList.getFis1().equalsIgnoreCase(hojaClinicaList2.getFis1())) {
                           model.addAttribute("fis1", "/N");
                       } else {
                           model.addAttribute("fis1", hojaClinicaList.getFis1());
                       }
                   }
                   if (hojaClinicaList.getFif1() != null) {
                       if (!hojaClinicaList.getFif1().equalsIgnoreCase(hojaClinicaList2.getFif1())) {
                           model.addAttribute("fif1", "/N");
                       } else {
                           model.addAttribute("fif1", hojaClinicaList.getFif1());
                       }
                   }
                   if (hojaClinicaList.getHora_ult_dia_fiebre1() != null) {
                       if (!hojaClinicaList.getHora_ult_dia_fiebre1().equalsIgnoreCase(hojaClinicaList2.getHora_ult_dia_fiebre1())) {
                           model.addAttribute("ult_dia_fiebre1", "/N");
                       } else {
                           model.addAttribute("ult_dia_fiebre1", hojaClinicaList.getHora_ult_dia_fiebre1());
                       }
                   }
                   if (!hojaClinicaList.getHora_ult_dia_fiebre1().equalsIgnoreCase(hojaClinicaList2.getHora_ult_dia_fiebre1())) {
                       model.addAttribute("hora_ult_dia_fiebre1", "/N");
                   }else{
                       model.addAttribute("hora_ult_dia_fiebre1", hojaClinicaList.getHora_ult_dia_fiebre1());
                   }
                   if (hojaClinicaList.getUlt_dosis_antipiretico1() != null) {
                       if (!hojaClinicaList.getUlt_dosis_antipiretico1().equalsIgnoreCase(hojaClinicaList2.getUlt_dosis_antipiretico1())) {
                           model.addAttribute("ult_dosis_antipiretico1", "/N");
                       } else {
                           model.addAttribute("ult_dosis_antipiretico1", hojaClinicaList.getUlt_dosis_antipiretico1());
                       }
                   }
                   if (!hojaClinicaList.getHora_ult_dosis_antipiretico1().equalsIgnoreCase(hojaClinicaList2.getHora_ult_dosis_antipiretico1())) {
                       model.addAttribute("hora_ult_dosis_antipiretico1", "/N");
                   }else{
                       model.addAttribute("hora_ult_dosis_antipiretico1", hojaClinicaList.getHora_ult_dosis_antipiretico1());
                   }
                   if (!hojaClinicaList.getFiebre1().equalsIgnoreCase(hojaClinicaList2.getFiebre1())) {
                       model.addAttribute("fiebre1", "/N");
                   }else{
                       model.addAttribute("fiebre1", hojaClinicaList.getFiebre1());
                   }
                   if (!hojaClinicaList.getAsomnoliento1().equalsIgnoreCase(hojaClinicaList2.getAsomnoliento1())) {
                       model.addAttribute("asomnoliento1", "/N");
                   }else{
                       model.addAttribute("asomnoliento1", hojaClinicaList.getAsomnoliento1());
                   }
                   if (!hojaClinicaList.getMalEstado1().equalsIgnoreCase(hojaClinicaList2.getMalEstado1())) {
                       model.addAttribute("malEstado1", "/N");
                   }else{
                       model.addAttribute("malEstado1", hojaClinicaList.getMalEstado1());
                   }
                   if (!hojaClinicaList.getPerdidaConsciencia1().equalsIgnoreCase(hojaClinicaList2.getPerdidaConsciencia1())) {
                       model.addAttribute("perdidaConsciencia1", "/N");
                   }else{
                       model.addAttribute("perdidaConsciencia1", hojaClinicaList.getPerdidaConsciencia1());
                   }
                   if (!hojaClinicaList.getInquieto1().equalsIgnoreCase(hojaClinicaList2.getInquieto1())) {
                       model.addAttribute("inquieto1", "/N");
                   }else{
                       model.addAttribute("inquieto1", hojaClinicaList.getInquieto1());
                   }
                   if (!hojaClinicaList.getConvulsiones1().equalsIgnoreCase(hojaClinicaList2.getConvulsiones1())) {
                       model.addAttribute("convulsiones1", "/N");
                   }else{
                       model.addAttribute("convulsiones1", hojaClinicaList.getConvulsiones1());
                   }
                   if (!hojaClinicaList.getLetargia1().equalsIgnoreCase(hojaClinicaList2.getLetargia1())) {
                       model.addAttribute("letargia1", "/N");
                   }else{
                       model.addAttribute("letargia1", hojaClinicaList.getLetargia1());
                   }
                   if (!hojaClinicaList.getDolorCabeza1().equalsIgnoreCase(hojaClinicaList2.getDolorCabeza1())) {
                       model.addAttribute("dolorCabeza1", "/N");
                   }else{
                       model.addAttribute("dolorCabeza1", hojaClinicaList.getDolorCabeza1());
                   }
                   if (!hojaClinicaList.getConjuntivitis1().equalsIgnoreCase(hojaClinicaList2.getConjuntivitis1())) {
                       model.addAttribute("conjuntivitis1", "/N");
                   }else{
                       model.addAttribute("conjuntivitis1", hojaClinicaList.getConjuntivitis1());
                   }
                   if (!hojaClinicaList.getHemorragiaSuconjuntival1().equalsIgnoreCase(hojaClinicaList2.getHemorragiaSuconjuntival1())) {
                       model.addAttribute("hemorragiaSuconjuntival1", "/N");
                   }else{
                       model.addAttribute("hemorragiaSuconjuntival1", hojaClinicaList.getHemorragiaSuconjuntival1());
                   }
                   if (!hojaClinicaList.getDolorRetroocular1().equalsIgnoreCase(hojaClinicaList2.getDolorRetroocular1())) {
                       model.addAttribute("dolorRetroocular1", "/N");
                   }else{
                       model.addAttribute("dolorRetroocular1", hojaClinicaList.getDolorRetroocular1());
                   }
                   if (!hojaClinicaList.getDolorGarganta1().equalsIgnoreCase(hojaClinicaList2.getDolorGarganta1())) {
                       model.addAttribute("dolorGarganta1", "/N");
                   }else{
                       model.addAttribute("dolorGarganta1", hojaClinicaList.getDolorGarganta1());
                   }
                   if (!hojaClinicaList.getEritema1().equalsIgnoreCase(hojaClinicaList2.getEritema1())) {
                       model.addAttribute("eritema1", "/N");
                   }else{
                       model.addAttribute("eritema1", hojaClinicaList.getEritema1());
                   }
                   if (!hojaClinicaList.getAdenopatiasCervicales1().equalsIgnoreCase(hojaClinicaList2.getAdenopatiasCervicales1())) {
                       model.addAttribute("adenopatiasCervicales1", "/N");
                   }else{
                       model.addAttribute("adenopatiasCervicales1", hojaClinicaList.getAdenopatiasCervicales1());
                   }
                   if (!hojaClinicaList.getExudado1().equalsIgnoreCase(hojaClinicaList2.getExudado1())) {
                       model.addAttribute("exudado1", "/N");
                   }else{
                       model.addAttribute("exudado1", hojaClinicaList.getExudado1());
                   }
                   if (!hojaClinicaList.getPetequiasMucosa1().equalsIgnoreCase(hojaClinicaList2.getPetequiasMucosa1())) {
                       model.addAttribute("petequiasMucosa1", "/N");
                   }else{
                       model.addAttribute("petequiasMucosa1", hojaClinicaList.getPetequiasMucosa1());
                   }
                   if (!hojaClinicaList.getTos1().equalsIgnoreCase(hojaClinicaList2.getTos1())) {
                       model.addAttribute("tos1", "/N");
                   }else{
                       model.addAttribute("tos1", hojaClinicaList.getTos1());
                   }
                   if (!hojaClinicaList.getRinorrea1().equalsIgnoreCase(hojaClinicaList2.getRinorrea1())) {
                       model.addAttribute("rinorrea1", "/N");
                   }else{
                       model.addAttribute("rinorrea1", hojaClinicaList.getRinorrea1());
                   }
                   if (!hojaClinicaList.getCongestionNasal1().equalsIgnoreCase(hojaClinicaList2.getCongestionNasal1())) {
                       model.addAttribute("congestionNasal1", "/N");
                   }else{
                       model.addAttribute("congestionNasal1", hojaClinicaList.getCongestionNasal1());
                   }
                   if (!hojaClinicaList.getOtalgia1().equalsIgnoreCase(hojaClinicaList2.getOtalgia1())) {
                       model.addAttribute("otalgia1", "/N");
                   }else{
                       model.addAttribute("otalgia1", hojaClinicaList.getOtalgia1());
                   }
                   if (!hojaClinicaList.getAleteoNasal1().equalsIgnoreCase(hojaClinicaList2.getAleteoNasal1())) {
                       model.addAttribute("aleteoNasal1", "/N");
                   }else{
                       model.addAttribute("aleteoNasal1", hojaClinicaList.getAleteoNasal1());
                   }
                   if (!hojaClinicaList.getRespiracionRapida1().equalsIgnoreCase(hojaClinicaList2.getRespiracionRapida1())) {
                       model.addAttribute("respiracionRapida1", "/N");
                   }else{
                       model.addAttribute("respiracionRapida1", hojaClinicaList.getRespiracionRapida1());
                   }
                   if (!hojaClinicaList.getEstridorReposo1().equalsIgnoreCase(hojaClinicaList2.getEstridorReposo1())) {
                       model.addAttribute("estridorReposo1", "/N");
                   }else{
                       model.addAttribute("estridorReposo1", hojaClinicaList.getEstridorReposo1());
                   }
                   if (!hojaClinicaList.getTirajeSubcostal1().equalsIgnoreCase(hojaClinicaList2.getTirajeSubcostal1())) {
                       model.addAttribute("tirajeSubcostal1", "/N");
                   }else{
                       model.addAttribute("tirajeSubcostal1", hojaClinicaList.getTirajeSubcostal1());
                   }
                   if (!hojaClinicaList.getSibilancias1().equalsIgnoreCase(hojaClinicaList2.getSibilancias1())) {
                       model.addAttribute("sibilancias1", "/N");
                   }else{
                       model.addAttribute("sibilancias1", hojaClinicaList.getSibilancias1());
                   }
                   if (!hojaClinicaList.getCrepitos1().equalsIgnoreCase(hojaClinicaList2.getCrepitos1())) {
                       model.addAttribute("crepitos1", "/N");
                   }else{
                       model.addAttribute("crepitos1", hojaClinicaList.getCrepitos1());
                   }
                   if (!hojaClinicaList.getRoncos1().equalsIgnoreCase(hojaClinicaList2.getRoncos1())) {
                       model.addAttribute("roncos1", "/N");
                   }else{
                       model.addAttribute("roncos1", hojaClinicaList.getRoncos1());
                   }
                   if (!hojaClinicaList.getDisnea1().equalsIgnoreCase(hojaClinicaList2.getDisnea1())) {
                       model.addAttribute("disnea1", "/N");
                   }else{
                       model.addAttribute("disnea1", hojaClinicaList.getDisnea1());
                   }
                   if (!hojaClinicaList.getPocoApetito1().equalsIgnoreCase(hojaClinicaList2.getPocoApetito1())) {
                       model.addAttribute("pocoApetito1", "/N");
                   }else{
                       model.addAttribute("pocoApetito1", hojaClinicaList.getPocoApetito1());
                   }
                   if (!hojaClinicaList.getNausea1().equalsIgnoreCase(hojaClinicaList2.getNausea1())) {
                       model.addAttribute("nausea1", "/N");
                   }else{
                       model.addAttribute("nausea1", hojaClinicaList.getNausea1());
                   }
                   if (!hojaClinicaList.getVomito12horas1().equalsIgnoreCase(hojaClinicaList2.getVomito12horas1())) {
                       model.addAttribute("vomito12horas1", "/N");
                   }else{
                       model.addAttribute("vomito12horas1", hojaClinicaList.getVomito12horas1());
                   }
                   if (!hojaClinicaList.getNumeroVomito12h1().equalsIgnoreCase(hojaClinicaList2.getNumeroVomito12h1())) {
                       model.addAttribute("numeroVomito12h1", "/N");
                   }else{
                       model.addAttribute("numeroVomito12h1", hojaClinicaList.getNumeroVomito12h1());
                   }
                   if (!hojaClinicaList.getDiarrea1().equalsIgnoreCase(hojaClinicaList2.getDiarrea1())) {
                       model.addAttribute("diarrea1", "/N");
                   }else{
                       model.addAttribute("diarrea1", hojaClinicaList.getDiarrea1());
                   }
                   if (!hojaClinicaList.getHepatomegalia1().equalsIgnoreCase(hojaClinicaList2.getHepatomegalia1())) {
                       model.addAttribute("hepatomegalia1", "/N");
                   }else{
                       model.addAttribute("hepatomegalia1", hojaClinicaList.getHepatomegalia1());
                   }
                   if (!hojaClinicaList.getDolorAbdominal1().equalsIgnoreCase(hojaClinicaList2.getDolorAbdominal1())) {
                       model.addAttribute("dolorAbdominal1", "/N");
                   }else{
                       model.addAttribute("dolorAbdominal1", hojaClinicaList.getDolorAbdominal1());
                   }
                   if (!hojaClinicaList.getArtralgia1().equalsIgnoreCase(hojaClinicaList2.getArtralgia1())) {
                       model.addAttribute("artralgia1", "/N");
                   }else{
                       model.addAttribute("artralgia1", hojaClinicaList.getArtralgia1());
                   }
                   if (!hojaClinicaList.getMialgia1().equalsIgnoreCase(hojaClinicaList2.getMialgia1())) {
                       model.addAttribute("mialgia1", "/N");
                   }else{
                       model.addAttribute("mialgia1", hojaClinicaList.getMialgia1());
                   }
                   if (!hojaClinicaList.getLumbalgia1().equalsIgnoreCase(hojaClinicaList2.getLumbalgia1())) {
                       model.addAttribute("lumbalgia1", "/N");
                   }else{
                       model.addAttribute("lumbalgia1", hojaClinicaList.getLumbalgia1());
                   }
                   if (!hojaClinicaList.getDolorCuello1().equalsIgnoreCase(hojaClinicaList2.getDolorCuello1())) {
                       model.addAttribute("dolorCuello1", "/N");
                   }else{
                       model.addAttribute("dolorCuello1", hojaClinicaList.getDolorCuello1());
                   }
                   if (!hojaClinicaList.getEdema1().equalsIgnoreCase(hojaClinicaList2.getEdema1())) {
                       model.addAttribute("edema1", "/N");
                   }else{
                       model.addAttribute("edema1", hojaClinicaList.getEdema1());
                   }
                   if (!hojaClinicaList.getRahsLocalizado1().equalsIgnoreCase(hojaClinicaList2.getRahsLocalizado1())) {
                       model.addAttribute("rahsLocalizado1", "/N");
                   }else{
                       model.addAttribute("rahsLocalizado1", hojaClinicaList.getRahsLocalizado1());
                   }
                   if (!hojaClinicaList.getRahsGeneralizado1().equalsIgnoreCase(hojaClinicaList2.getRahsGeneralizado1())) {
                       model.addAttribute("rahsGeneralizado1", "/N");
                   }else{
                       model.addAttribute("rahsGeneralizado1", hojaClinicaList.getRahsGeneralizado1());
                   }
                   if (!hojaClinicaList.getRashEritematoso1().equalsIgnoreCase(hojaClinicaList2.getRashEritematoso1())) {
                       model.addAttribute("rashEritematoso1", "/N");
                   }else{
                       model.addAttribute("rashEritematoso1", hojaClinicaList.getRashEritematoso1());
                   }
                   if (!hojaClinicaList.getRahsMacular1().equalsIgnoreCase(hojaClinicaList2.getRahsMacular1())) {
                       model.addAttribute("rahsMacular1", "/N");
                   }else{
                       model.addAttribute("rahsMacular1", hojaClinicaList.getRahsMacular1());
                   }
                   if (!hojaClinicaList.getRashPapular1().equalsIgnoreCase(hojaClinicaList2.getRashPapular1())) {
                       model.addAttribute("rashPapular1", "/N");
                   }else{
                       model.addAttribute("rashPapular1", hojaClinicaList.getRashPapular1());
                   }
                   if (!hojaClinicaList.getPielMoteada1().equalsIgnoreCase(hojaClinicaList2.getPielMoteada1())) {
                       model.addAttribute("pielMoteada1", "/N");
                   }else{
                       model.addAttribute("pielMoteada1", hojaClinicaList.getPielMoteada1());
                   }
                   if (!hojaClinicaList.getRuborFacia1().equalsIgnoreCase(hojaClinicaList2.getRuborFacia1())) {
                       model.addAttribute("ruborFacia1", "/N");
                   }else{
                       model.addAttribute("ruborFacia1", hojaClinicaList.getRuborFacia1());
                   }
                   if (!hojaClinicaList.getCianosisCentral1().equalsIgnoreCase(hojaClinicaList2.getCianosisCentral1())) {
                       model.addAttribute("cianosisCentral1", "/N");
                   }else{
                       model.addAttribute("cianosisCentral1", hojaClinicaList.getCianosisCentral1());
                   }
                   if (!hojaClinicaList.getIctericia1().equalsIgnoreCase(hojaClinicaList2.getIctericia1())) {
                       model.addAttribute("ictericia1", "/N");
                   }else{
                       model.addAttribute("ictericia1", hojaClinicaList.getIctericia1());
                   }
                   if (!hojaClinicaList.getImc1().equalsIgnoreCase(hojaClinicaList2.getImc1())) {
                       model.addAttribute("imc1", "/N");
                   }else{
                       model.addAttribute("imc1", hojaClinicaList.getImc1());
                   }
                   if (!hojaClinicaList.getObeso1().equalsIgnoreCase(hojaClinicaList2.getObeso1())) {
                       model.addAttribute("obeso1", "/N");
                   }else{
                       model.addAttribute("obeso1", hojaClinicaList.getObeso1());
                   }
                   if (!hojaClinicaList.getSobrepeso1().equalsIgnoreCase(hojaClinicaList2.getSobrepeso1())) {
                       model.addAttribute("sobrepeso1", "/N");
                   }else{
                        model.addAttribute("sobrepeso1", hojaClinicaList.getSobrepeso1());
                   }
                   if (!hojaClinicaList.getSospechaProblema1().equalsIgnoreCase(hojaClinicaList2.getSospechaProblema1())) {
                       model.addAttribute("sospechaProblema1", "/N");
                   }else{
                       model.addAttribute("sospechaProblema1", hojaClinicaList.getSospechaProblema1());
                   }
                   if (!hojaClinicaList.getNormal1().equalsIgnoreCase(hojaClinicaList2.getNormal1())) {
                       model.addAttribute("normal1", "/N");
                   }else{
                       model.addAttribute("normal1", hojaClinicaList.getNormal1());
                   }
                   if (!hojaClinicaList.getBajoPeso1().equalsIgnoreCase(hojaClinicaList2.getBajoPeso1())) {
                       model.addAttribute("bajoPeso1", "/N");
                   }else{
                       model.addAttribute("bajoPeso1", hojaClinicaList.getBajoPeso1());
                   }
                   if (!hojaClinicaList.getBajoPesoSevero1().equalsIgnoreCase(hojaClinicaList2.getBajoPesoSevero1())) {
                       model.addAttribute("bajoPesoSevero1", "/N");
                   }else{
                       model.addAttribute("bajoPesoSevero1", hojaClinicaList.getBajoPesoSevero1());
                   }
                   if (!hojaClinicaList.getCategoria1().equalsIgnoreCase(hojaClinicaList2.getCategoria1())) {
                       model.addAttribute("categoria1", "/N");
                   }else{
                       model.addAttribute("categoria1", hojaClinicaList.getCategoria1());
                   }
                   if (!hojaClinicaList.getCambioCategoria1().equalsIgnoreCase(hojaClinicaList2.getCambioCategoria1())) {
                       model.addAttribute("cambioCategoria1", "/N");
                   }else{
                       model.addAttribute("cambioCategoria1", hojaClinicaList.getCambioCategoria1());
                   }
                   if (!hojaClinicaList.getPruebaTorniquetePositiva1().equalsIgnoreCase(hojaClinicaList2.getPruebaTorniquetePositiva1())) {
                       model.addAttribute("pruebaTorniquetePositiva1", "/N");
                   }else{
                       model.addAttribute("pruebaTorniquetePositiva1", hojaClinicaList.getPruebaTorniquetePositiva1());
                   }
                   if (!hojaClinicaList.getPetequia10Pt1().equalsIgnoreCase(hojaClinicaList2.getPetequia10Pt1())) {
                       model.addAttribute("petequia10Pt1", "/N");
                   }else{
                       model.addAttribute("petequia10Pt1", hojaClinicaList.getPetequia10Pt1());
                   }
                   if (!hojaClinicaList.getPetequia20Pt1().equalsIgnoreCase(hojaClinicaList2.getPetequia20Pt1())) {
                       model.addAttribute("petequia20Pt1", "/N");
                   }else{
                       model.addAttribute("petequia20Pt1", hojaClinicaList.getPetequia20Pt1());
                   }
                   if (!hojaClinicaList.getPielExtremidadesFrias1().equalsIgnoreCase(hojaClinicaList2.getPielExtremidadesFrias1())) {
                       model.addAttribute("pielExtremidadesFrias1", "/N");
                   }else{
                       model.addAttribute("pielExtremidadesFrias1", hojaClinicaList.getPielExtremidadesFrias1());
                   }
                   if (!hojaClinicaList.getPalidezEnExtremidades1().equalsIgnoreCase(hojaClinicaList2.getPalidezEnExtremidades1())) {
                       model.addAttribute("palidezEnExtremidades1", "/N");
                   }else{
                       model.addAttribute("palidezEnExtremidades1", hojaClinicaList.getPalidezEnExtremidades1());
                   }
                   if (!hojaClinicaList.getEpistaxis1().equalsIgnoreCase(hojaClinicaList2.getEpistaxis1())) {
                       model.addAttribute("epistaxis1", "/N");
                   }else{
                       model.addAttribute("epistaxis1", hojaClinicaList.getEpistaxis1());
                   }
                   if (!hojaClinicaList.getGingivorragia1().equalsIgnoreCase(hojaClinicaList2.getGingivorragia1())) {
                       model.addAttribute("gingivorragia1", "/N");
                   }else{
                       model.addAttribute("gingivorragia1", hojaClinicaList.getGingivorragia1());
                   }
                   if (!hojaClinicaList.getPetequiasEspontaneas1().equalsIgnoreCase(hojaClinicaList2.getPetequiasEspontaneas1())) {
                       model.addAttribute("petequiasEspontaneas1", "/N");
                   }else{
                       model.addAttribute("petequiasEspontaneas1", hojaClinicaList.getPetequiasEspontaneas1());
                   }
                   if (!hojaClinicaList.getLlenadoCapilar2seg1().equalsIgnoreCase(hojaClinicaList2.getLlenadoCapilar2seg1())) {
                       model.addAttribute("llenadoCapilar2seg1", "/N");
                   }else{
                       model.addAttribute("llenadoCapilar2seg1", hojaClinicaList.getLlenadoCapilar2seg1());
                   }
                   if (!hojaClinicaList.getCianosis1().equalsIgnoreCase(hojaClinicaList2.getCianosis1())) {
                       model.addAttribute("cianosis1", "/N");
                   }else{
                       model.addAttribute("cianosis1", hojaClinicaList.getCianosis1());
                   }
                   if (!hojaClinicaList.getHipermenorrea1().equalsIgnoreCase(hojaClinicaList2.getHipermenorrea1())) {
                       model.addAttribute("hipermenorrea1", "/N");
                   }else{
                       model.addAttribute("hipermenorrea1", hojaClinicaList.getHipermenorrea1());
                   }
                   if (!hojaClinicaList.getHematemesis1().equalsIgnoreCase(hojaClinicaList2.getHematemesis1())) {
                       model.addAttribute("hematemesis1", "/N");
                   }else{
                       model.addAttribute("hematemesis1", hojaClinicaList.getHematemesis1());
                   }
                   if (!hojaClinicaList.getHemoconcentracion1().equalsIgnoreCase(hojaClinicaList2.getHemoconcentracion1())) {
                       model.addAttribute("hemoconcentracion1", "/N");
                   }else{
                       model.addAttribute("hemoconcentracion1", hojaClinicaList.getHemoconcentracion1());
                   }
                   if (!hojaClinicaList.getHospitalizado1().equalsIgnoreCase(hojaClinicaList2.getHospitalizado1())) {
                       model.addAttribute("hospitalizado1", "/N");
                   }else{
                       model.addAttribute("hospitalizado1", hojaClinicaList.getHospitalizado1());
                   }
                   if (!hojaClinicaList.getHospitalizadoEspecificar1().equalsIgnoreCase(hojaClinicaList2.getHospitalizadoEspecificar1())) {
                       model.addAttribute("hospitalizadoEspecificar1", "/N");
                   }else{
                       model.addAttribute("hospitalizadoEspecificar1", hojaClinicaList.getHospitalizadoEspecificar1());
                   }
                   if (!hojaClinicaList.getTransfusionSangre1().equalsIgnoreCase(hojaClinicaList2.getTransfusionSangre1())) {
                       model.addAttribute("transfusionSangre1", "/N");
                   }else{
                       model.addAttribute("transfusionSangre1", hojaClinicaList.getTransfusionSangre1());
                   }
                   if (!hojaClinicaList.getTransfusionEspecificar1().equalsIgnoreCase(hojaClinicaList2.getTransfusionEspecificar1())) {
                       model.addAttribute("transfusionEspecificar1", "/N");
                   }else{
                       model.addAttribute("transfusionEspecificar1", hojaClinicaList.getTransfusionEspecificar1());
                   }
                   if (!hojaClinicaList.getTomandoMedicamento1().equalsIgnoreCase(hojaClinicaList2.getTomandoMedicamento1())) {
                       model.addAttribute("tomandoMedicamento1", "/N");
                   }else{
                       model.addAttribute("tomandoMedicamento1", hojaClinicaList.getTomandoMedicamento1());
                   }
                   if (!hojaClinicaList.getMedicamentoEspecificar1().equalsIgnoreCase(hojaClinicaList2.getMedicamentoEspecificar1())) {
                       model.addAttribute("medicamentoEspecificar1", "/N");
                   }else{
                       model.addAttribute("medicamentoEspecificar1", hojaClinicaList.getMedicamentoEspecificar1());
                   }
                   if (!hojaClinicaList.getBhc1().equalsIgnoreCase(hojaClinicaList2.getBhc1())) {
                       model.addAttribute("bhc1", "/N");
                   }else{
                       model.addAttribute("bhc1", hojaClinicaList.getBhc1());
                   }
                   if (!hojaClinicaList.getSerologiaArbovirus1().equalsIgnoreCase(hojaClinicaList2.getSerologiaArbovirus1())) {
                       model.addAttribute("serologiaArbovirus1", "/N");
                   }else{
                       model.addAttribute("serologiaArbovirus1", hojaClinicaList.getSerologiaArbovirus1());
                   }
                   if (!hojaClinicaList.getGotaGruesa1().equalsIgnoreCase(hojaClinicaList2.getGotaGruesa1())) {
                       model.addAttribute("gotaGruesa1", "/N");
                   }else{
                       model.addAttribute("gotaGruesa1", hojaClinicaList.getGotaGruesa1());
                   }
                   if (!hojaClinicaList.getEgo1().equalsIgnoreCase(hojaClinicaList2.getEgo1())) {
                       model.addAttribute("ego1", "/N");
                   }else{
                       model.addAttribute("ego1", hojaClinicaList.getEgo1());
                   }
                   if (!hojaClinicaList.getEgh1().equalsIgnoreCase(hojaClinicaList2.getEgh1())) {
                       model.addAttribute("egh1", "/N");
                   }else{
                       model.addAttribute("egh1", hojaClinicaList.getEgh1());
                   }
                   if (!hojaClinicaList.getOtroExamenLab1().equalsIgnoreCase(hojaClinicaList2.getOtroExamenLab1())) {
                       model.addAttribute("otroExamenLab1", "/N");
                   }else{
                       model.addAttribute("otroExamenLab1", hojaClinicaList.getOtroExamenLab1());
                   }
                   if (!hojaClinicaList.getOtroExamanLabEspecificar1().equalsIgnoreCase(hojaClinicaList2.getOtroExamanLabEspecificar1())) {
                       model.addAttribute("otroExamanLabEspecificar1", "/N");
                   }else{
                       model.addAttribute("otroExamanLabEspecificar1", hojaClinicaList.getOtroExamanLabEspecificar1());
                   }
                   if (!hojaClinicaList.getAcetaminofen1().equalsIgnoreCase(hojaClinicaList2.getAcetaminofen1())) {
                       model.addAttribute("acetaminofen1", "/N");
                   }else{
                       model.addAttribute("acetaminofen1", hojaClinicaList.getAcetaminofen1());
                   }
                   if (!hojaClinicaList.getAmoxicilina1().equalsIgnoreCase(hojaClinicaList2.getAmoxicilina1())) {
                       model.addAttribute("amoxicilina1", "/N");
                   }else{
                       model.addAttribute("amoxicilina1", hojaClinicaList.getAmoxicilina1());
                   }
                   if (!hojaClinicaList.getDicloxacilina1().equalsIgnoreCase(hojaClinicaList2.getDicloxacilina1())) {
                       model.addAttribute("dicloxacilina1", "/N");
                   }else{
                       model.addAttribute("dicloxacilina1", hojaClinicaList.getDicloxacilina1());
                   }
                   if (!hojaClinicaList.getPenicilina1().equalsIgnoreCase(hojaClinicaList2.getPenicilina1())) {
                       model.addAttribute("penicilina1", "/N");
                   }else{
                       model.addAttribute("penicilina1", hojaClinicaList.getPenicilina1());
                   }
                   if (!hojaClinicaList.getFurazolidona1().equalsIgnoreCase(hojaClinicaList2.getFurazolidona1())) {
                       model.addAttribute("furazolidona1", "/N");
                   }else{
                       model.addAttribute("furazolidona1", hojaClinicaList.getFurazolidona1());
                   }
                   if (!hojaClinicaList.getMetronidazolTinidazol1().equalsIgnoreCase(hojaClinicaList2.getMetronidazolTinidazol1())) {
                       model.addAttribute("metronidazolTinidazol1", "/N");
                   }else{
                       model.addAttribute("metronidazolTinidazol1", hojaClinicaList.getMetronidazolTinidazol1());
                   }
                   if (!hojaClinicaList.getAlbendazolMebendazol1().equalsIgnoreCase(hojaClinicaList2.getAlbendazolMebendazol1())) {
                       model.addAttribute("albendazolMebendazol1", "/N");
                   }else{
                       model.addAttribute("albendazolMebendazol1", hojaClinicaList.getAlbendazolMebendazol1());
                   }
                   if (!hojaClinicaList.getSueroOral1().equalsIgnoreCase(hojaClinicaList2.getSueroOral1())) {
                       model.addAttribute("sueroOral1", "/N");
                   }else{
                       model.addAttribute("sueroOral1", hojaClinicaList.getSueroOral1());
                   }
                   if (!hojaClinicaList.getOtroTratamiento1().equalsIgnoreCase(hojaClinicaList2.getOtroTratamiento1())) {
                       model.addAttribute("otroTratamiento1", "/N");
                   }else{
                       model.addAttribute("otroTratamiento1", hojaClinicaList.getOtroTratamiento1());
                   }
                   if (!hojaClinicaList.getOtroTratamientoEspecificar1().equalsIgnoreCase(hojaClinicaList2.getOtroTratamientoEspecificar1())) {
                       model.addAttribute("otroTratamientoEspecificar1", "/N");
                   }else{
                       model.addAttribute("otroTratamientoEspecificar1", hojaClinicaList.getOtroTratamientoEspecificar1());
                   }
                   if (!hojaClinicaList.getPlanes1().equalsIgnoreCase(hojaClinicaList2.getPlanes1())) {
                       model.addAttribute("planes1", "/N");
                   }else{
                       model.addAttribute("planes1", hojaClinicaList.getPlanes1());
                   }
                   if (!hojaClinicaList.getHistoriaClinica1().equalsIgnoreCase(hojaClinicaList2.getHistoriaClinica1())) {
                       model.addAttribute("historiaClinica1", "/N");
                   }else{
                       model.addAttribute("historiaClinica1", hojaClinicaList.getHistoriaClinica1());
                   }
                   if (!hojaClinicaList.getDiagnostico1().equalsIgnoreCase(hojaClinicaList2.getDiagnostico1())) {
                       model.addAttribute("diagnostico1", "/N");
                   }else{
                       model.addAttribute("diagnostico1", hojaClinicaList.getDiagnostico1());
                   }
                   if (!hojaClinicaList.getDiagnostico11().equalsIgnoreCase(hojaClinicaList2.getDiagnostico11())) {
                       model.addAttribute("diagnostico11", "/N");
                   }else{
                       model.addAttribute("diagnostico11", hojaClinicaList.getDiagnostico11());
                   }
                   if (!hojaClinicaList.getDiagnostico21().equalsIgnoreCase(hojaClinicaList2.getDiagnostico21())) {
                       model.addAttribute("diagnostico21", "/N");
                   }else{
                       model.addAttribute("diagnostico21", hojaClinicaList.getDiagnostico21());
                   }
                   if (!hojaClinicaList.getDiagnostico31().equalsIgnoreCase(hojaClinicaList2.getDiagnostico31())) {
                       model.addAttribute("diagnostico31", "/N");
                   }else{
                       model.addAttribute("diagnostico31", hojaClinicaList.getDiagnostico31());
                   }
                   if (!hojaClinicaList.getDiagnostico41().equalsIgnoreCase(hojaClinicaList2.getDiagnostico41())) {
                       model.addAttribute("diagnostico41", "/N");
                   }else{
                       model.addAttribute("diagnostico41", hojaClinicaList.getDiagnostico41());
                   }
                   if (!hojaClinicaList.getTelefonoEmergencia1().equalsIgnoreCase(hojaClinicaList2.getTelefonoEmergencia1())) {
                       model.addAttribute("telefonoEmergencia1", "/N");
                   }else{
                       model.addAttribute("telefonoEmergencia1", hojaClinicaList.getTelefonoEmergencia1());
                   }
                   if (hojaClinicaList.getProximaCita1() != null) {
                       if (!hojaClinicaList.getProximaCita1().equalsIgnoreCase(hojaClinicaList2.getProximaCita1())) {
                           model.addAttribute("proximaCita1", "/N");
                       } else {
                           model.addAttribute("proximaCita1", hojaClinicaList.getProximaCita1());
                       }
                   }
                   if (hojaClinicaList.getProximaCita1() != null) {
                       if (!hojaClinicaList.getProximaCita1().equalsIgnoreCase(hojaClinicaList2.getProximaCita1())) {
                           model.addAttribute("proximaCita1", "/N");
                       } else {
                           model.addAttribute("proximaCita1", hojaClinicaList.getProximaCita1());
                       }
                   }
                   if (!hojaClinicaList.getMedico1().equalsIgnoreCase(hojaClinicaList2.getMedico1())) {
                       model.addAttribute("medico1", "/N");
                   }else{
                       model.addAttribute("medico1", hojaClinicaList.getMedico1());
                   }
                   if (hojaClinicaList.getFechaMedico1() != null) {
                       if (!hojaClinicaList.getFechaMedico1().equalsIgnoreCase(hojaClinicaList2.getFechaMedico1())) {
                           model.addAttribute("fechaMedico1", "/N");
                       } else {
                           model.addAttribute("fechaMedico1", hojaClinicaList.getFechaMedico1());
                       }
                   }
                   if(hojaClinicaList.getHoraMedico1() != null ) {
                       if (!hojaClinicaList.getHoraMedico1().equalsIgnoreCase(hojaClinicaList2.getHoraMedico1())) {
                           model.addAttribute("horaMedico1", "/N");
                       } else {
                           model.addAttribute("horaMedico1", hojaClinicaList.getHoraMedico1());
                       }
                   }

                   if (!hojaClinicaList.getEnfermeria1().equalsIgnoreCase(hojaClinicaList2.getEnfermeria1())) {
                       model.addAttribute("enfermeria1", "/N");
                   }else{
                       model.addAttribute("enfermeria1", hojaClinicaList.getEnfermeria1());
                   }
                   if (hojaClinicaList.getFechaEnfermeria1() != null) {
                       if (!hojaClinicaList.getFechaEnfermeria1().equalsIgnoreCase(hojaClinicaList2.getFechaEnfermeria1())) {
                           model.addAttribute("fechaEnfermeria1", "/N");
                       } else {
                           model.addAttribute("fechaEnfermeria1", hojaClinicaList.getFechaEnfermeria1());
                       }
                   }
                   if(hojaClinicaList.getHoraEnfermeria1() != null ) {
                       if (!hojaClinicaList.getHoraEnfermeria1().equalsIgnoreCase(hojaClinicaList2.getHoraEnfermeria1())) {
                           model.addAttribute("horaEnfermeria1", "/N");
                       } else {
                           model.addAttribute("horaEnfermeria1", hojaClinicaList.getHoraEnfermeria1());
                       }
                   }






                   return "fingering/clinicalSheetEditar";
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
            }
            else {
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

    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Guardando hoja clinica");
        String resultado = "";
        String error = "";
        try {
            HojaClinica hojaClinica = ParseJsonRequestToHojaClinica(request);
            hojaClinica.setRecordDate(new Date());
            hojaClinica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            //para nuevos registros es necesario poner un valor por defecto en el numero de hoja, aunque se setee con el trigger tg_set_numero_hoja
           //
           // hojaClinica.setNumHojaConsulta(0);

            hojaClinicaService.saveOrUpdate(hojaClinica);
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
    @RequestMapping(value = "HCComparacion", method = RequestMethod.GET)
    public String letters(Model model) throws ParseException {
        logger.debug("Mostrando diferencias de Hojas Clínicas en JSP");

        return "fingering/comparacionHojasClinicas";
    }
    @RequestMapping(value = "ProcesarBHC", method = RequestMethod.GET)
    public String letters1(Model model) throws ParseException {
        logger.debug("Mostrando BHC enviadas en JSP");
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        return "fingering/procesamientoBHCSocratesFlores";
    }

    @RequestMapping(value = "getBHCEnviadas", method = RequestMethod.GET, produces = "application/json")
   // @RequestMapping(value = "/getBHCEnviadas1/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getBHCEnviadas(
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="nEnvios", required=false ) String nEnvios,
            @RequestParam(value="participante", required=false ) String participante,
            @RequestParam(value="bhcId", required=false ) int bhcId) throws ParseException {
        List<BhcDto> Bhclist;
        try {
            logger.debug("buscar BHC enviadas");
            logger.debug("fechaInicioCons: " + fechaInicioCons );
            logger.debug("nEnvios: " + nEnvios );
            LocalDate localDate = LocalDate.now();

             if (fechaInicioCons != null) {
                Bhclist = this.hojaClinicaService.getObtenerBHCEnviadas( DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY), nEnvios,participante,bhcId);
                // logger.debug("getIdbhc: " + Bhclist.get().getParticipante() );
                // logger.debug("getParticipante: " + Bhclist.get().getParticipante() );
                 //return JsonUtil.createJsonResponse(hojaClinicaList);

                 for(BhcDto bhclst : Bhclist) {
                     //bhclst.getIdbhc();
                     return JsonUtil.createJsonResponse(Bhclist);
                 }
                 } else
                 return JsonUtil.createJsonResponse("No se encontró Hoja Clínica según el código ingresado");

            return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());
              //  return JsonUtil.createJsonResponse(new ArrayList<Bhclist>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "getBHCImportadaBc6000", method = RequestMethod.GET, produces = "application/json")
    // @RequestMapping(value = "/getBHCEnviadas1/{participante}/{idbhc}/{fecha}/{numEnvio}/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getBHCImportadaBc6000(
            @RequestParam(value="participante", required=false ) String participante,
            @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
            @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons,
            @RequestParam(value="bhcId", required=false ) int bhcId) throws ParseException {
        List<BhcBc6000PrintDto> Bhclist;
        try {
            logger.debug("buscar BHC Importadas BC6000");
            logger.debug("participante: " + participante );
            logger.debug("fechaInicioCons: " + fechaInicioCons );
            logger.debug("fechaFinCons: " + fechaFinCons );

            LocalDate localDate = LocalDate.now();

            if (fechaInicioCons != null) {
                Bhclist = this.hojaClinicaService.getObtenerBHCImportadasBc6000( DateUtil.StringToDate(fechaInicioCons, Constants.STRING_FORMAT_DD_MM_YYYY), DateUtil.StringToDate(fechaFinCons, Constants.STRING_FORMAT_DD_MM_YYYY),participante,bhcId);


                for(BhcBc6000PrintDto bhclst : Bhclist) {
                    //bhclst.getIdbhc();
                    if (bhclst.getImpreso().equalsIgnoreCase("Si")||bhclst.getImpreso().equalsIgnoreCase("Sí")){
                        bhclst.setImpreso("<span class='badge badge-danger'>"+bhclst.getImpreso()+"</span>");
                    }
                    if (bhclst.getImpreso().equalsIgnoreCase("No")){
                        bhclst.setImpreso("<span class='badge bg-success'>"+bhclst.getImpreso()+"</span>");
                    }


                }
                return JsonUtil.createJsonResponse(Bhclist);
            } else
                return JsonUtil.createJsonResponse("No se encontró Registro según el código ingresado");

          //  return JsonUtil.createJsonResponse(new ArrayList<BhcDto>());
            //  return JsonUtil.createJsonResponse(new ArrayList<Bhclist>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    @RequestMapping(value = "getHCDigitadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getHCDigitadas(
                               @RequestParam(value="fechaInicioCons", required=false ) String fechaInicioCons,
                               @RequestParam(value="fechaFinCons", required=false ) String fechaFinCons) throws ParseException {
        try {
            logger.debug("buscar hojas clinicas Digitadas");
            if ( ((fechaInicioCons != null && !fechaInicioCons.isEmpty()) && (fechaFinCons != null && !fechaFinCons.isEmpty()))) {
                List<DiferenciasHojasDigitadasDto> hojaClinicaList = this.hojaClinicaService.getObtenerDiferenciasHC(DateUtil.StringToDate(fechaInicioCons, "dd/MM/yyyy"), DateUtil.StringToDate(fechaFinCons, "dd/MM/yyyy"));
                if (hojaClinicaList.size() > 0) {
                    for(DiferenciasHojasDigitadasDto diferencia : hojaClinicaList) {
                        if (!diferencia.getCodigo_participante1().equalsIgnoreCase(diferencia.getCodigo_participante2())) {
                            diferencia.setCodigo_participante1("<span class='badge badge-danger'>"+diferencia.getCodigo_participante1()+"</span>");
                            diferencia.setCodigo_participante2("<span class='badge badge-danger'>"+diferencia.getCodigo_participante2()+"</span>");
                        }
                        if (!diferencia.getNumero_hoja1().equalsIgnoreCase(diferencia.getNumero_hoja2())) {
                            diferencia.setNumero_hoja1("<span class='badge badge-danger'>"+diferencia.getNumero_hoja1()+"</span>");
                            diferencia.setNumero_hoja2("<span class='badge badge-danger'>"+diferencia.getNumero_hoja2()+"</span>");
                        }
                        if (!diferencia.getCodigo_supervisor1().equalsIgnoreCase(diferencia.getCodigo_supervisor2())) {
                            diferencia.setCodigo_supervisor1("<span class='badge badge-danger'>"+diferencia.getCodigo_supervisor1()+"</span>");
                            diferencia.setCodigo_supervisor2("<span class='badge badge-danger'>"+diferencia.getCodigo_supervisor2()+"</span>");
                        }
                        if(diferencia.getFecha_consulta1() != null ) {
                            if (!diferencia.getFecha_consulta1().equalsIgnoreCase(diferencia.getFecha_consulta2())) {
                                diferencia.setFecha_consulta1("<span class='badge badge-danger'>" + diferencia.getFecha_consulta1() + "</span>");
                                diferencia.setFecha_consulta2("<span class='badge badge-danger'>" + diferencia.getFecha_consulta2() + "</span>");
                            }
                        }
                        if (!diferencia.getHora_consulta1().equalsIgnoreCase(diferencia.getHora_consulta2())) {
                            diferencia.setHora_consulta1("<span class='badge badge-danger'>"+diferencia.getHora_consulta1()+"</span>");
                            diferencia.setHora_consulta2("<span class='badge badge-danger'>"+diferencia.getHora_consulta2()+"</span>");
                        }
                        if (!diferencia.getPeso_kg1().equalsIgnoreCase(diferencia.getPeso_kg2())) {
                            diferencia.setPeso_kg1("<span class='badge badge-danger'>"+diferencia.getPeso_kg1()+"</span>");
                            diferencia.setPeso_kg2("<span class='badge badge-danger'>"+diferencia.getPeso_kg2()+"</span>");
                        }
                        if (!diferencia.getTalla_cm1().equalsIgnoreCase(diferencia.getTalla_cm2())) {
                            diferencia.setTalla_cm1("<span class='badge badge-danger'>"+diferencia.getTalla_cm1()+"</span>");
                            diferencia.setTalla_cm2("<span class='badge badge-danger'>"+diferencia.getTalla_cm2()+"</span>");
                        }
                        if (!diferencia.getPresion1().equalsIgnoreCase(diferencia.getPresion2())) {
                            diferencia.setPresion1("<span class='badge badge-danger'>"+diferencia.getPresion1()+"</span>");
                            diferencia.setPresion2("<span class='badge badge-danger'>"+diferencia.getPresion2()+"</span>");
                        }
                        if (!diferencia.getFcia_card1().equalsIgnoreCase(diferencia.getFcia_card2())) {
                            diferencia.setFcia_card1("<span class='badge badge-danger'>"+diferencia.getFcia_card1()+"</span>");
                            diferencia.setFcia_card2("<span class='badge badge-danger'>"+diferencia.getFcia_card2()+"</span>");
                        }
                        if (!diferencia.getTemperaturac1().equalsIgnoreCase(diferencia.getTemperaturac2())) {
                            diferencia.setTemperaturac1("<span class='badge badge-danger'>"+diferencia.getTemperaturac1()+"</span>");
                            diferencia.setTemperaturac2("<span class='badge badge-danger'>"+diferencia.getTemperaturac2()+"</span>");
                        }
                        if (!diferencia.getSaturacion1().equalsIgnoreCase(diferencia.getSaturacion2())) {
                            diferencia.setSaturacion1("<span class='badge badge-danger'>"+diferencia.getSaturacion1()+"</span>");
                            diferencia.setSaturacion2("<span class='badge badge-danger'>"+diferencia.getSaturacion2()+"</span>");
                        }
                        if (!diferencia.getHora_inicio_consulta1().equalsIgnoreCase(diferencia.getHora_inicio_consulta2())) {
                            diferencia.setHora_inicio_consulta1("<span class='badge badge-danger'>"+diferencia.getHora_inicio_consulta1()+"</span>");
                            diferencia.setHora_inicio_consulta2("<span class='badge badge-danger'>"+diferencia.getHora_inicio_consulta2()+"</span>");
                        }
                        if (!diferencia.getConsulta1().equalsIgnoreCase(diferencia.getConsulta2())) {
                            diferencia.setConsulta1("<span class='badge badge-danger'>"+diferencia.getConsulta1()+"</span>");
                            diferencia.setConsulta2("<span class='badge badge-danger'>"+diferencia.getConsulta2()+"</span>");
                        }
                        if (!diferencia.getLugar_atencion1().equalsIgnoreCase(diferencia.getLugar_atencion2())) {
                            diferencia.setLugar_atencion1("<span class='badge badge-danger'>"+diferencia.getLugar_atencion1()+"</span>");
                            diferencia.setLugar_atencion2("<span class='badge badge-danger'>"+diferencia.getLugar_atencion2()+"</span>");
                        }
                        if (!diferencia.getPresion_medico1().equalsIgnoreCase(diferencia.getPresion_medico2())) {
                            diferencia.setPresion_medico1("<span class='badge badge-danger'>"+diferencia.getPresion_medico1()+"</span>");
                            diferencia.setPresion_medico2("<span class='badge badge-danger'>"+diferencia.getPresion_medico2()+"</span>");
                        }
                        if (!diferencia.getTemperatura_medico1().equalsIgnoreCase(diferencia.getTemperatura_medico2())) {
                            diferencia.setTemperatura_medico1("<span class='badge badge-danger'>"+diferencia.getTemperatura_medico1()+"</span>");
                            diferencia.setTemperatura_medico2("<span class='badge badge-danger'>"+diferencia.getTemperatura_medico2()+"</span>");
                        }
                        if (!diferencia.getFcia_resp1().equalsIgnoreCase(diferencia.getFcia_resp2())) {
                            diferencia.setFcia_resp1("<span class='badge badge-danger'>"+diferencia.getFcia_resp1()+"</span>");
                            diferencia.setFcia_resp2("<span class='badge badge-danger'>"+diferencia.getFcia_resp2()+"</span>");
                        }
                        if (!diferencia.getFcia_card_medico1().equalsIgnoreCase(diferencia.getFcia_card_medico2())) {
                            diferencia.setFcia_card_medico1("<span class='badge badge-danger'>"+diferencia.getFcia_card_medico1()+"</span>");
                            diferencia.setFcia_card_medico2("<span class='badge badge-danger'>"+diferencia.getFcia_card_medico2()+"</span>");
                        }
                        if (!diferencia.getSaturaciono2_medico1().equalsIgnoreCase(diferencia.getSaturaciono2_medico2())) {
                            diferencia.setSaturaciono2_medico1("<span class='badge badge-danger'>"+diferencia.getSaturaciono2_medico1()+"</span>");
                            diferencia.setSaturaciono2_medico2("<span class='badge badge-danger'>"+diferencia.getSaturaciono2_medico2()+"</span>");
                        }
                        if(diferencia.getFis1() != null ) {
                            if (!diferencia.getFis1().equalsIgnoreCase(diferencia.getFis2())) {
                                diferencia.setFis1("<span class='badge badge-danger'>" + diferencia.getFis1() + "</span>");
                                diferencia.setFis2("<span class='badge badge-danger'>" + diferencia.getFis2() + "</span>");
                            }
                        }
                        if(diferencia.getFif1() != null ) {
                            if (!diferencia.getFif1().equalsIgnoreCase(diferencia.getFif2())) {
                                diferencia.setFif1("<span class='badge badge-danger'>" + diferencia.getFif1() + "</span>");
                                diferencia.setFif2("<span class='badge badge-danger'>" + diferencia.getFif2() + "</span>");
                            }
                        }
                        if (!diferencia.getUlt_dia_fiebre1().equalsIgnoreCase(diferencia.getUlt_dia_fiebre2())) {
                            diferencia.setUlt_dia_fiebre1("<span class='badge badge-danger'>"+diferencia.getUlt_dia_fiebre1()+"</span>");
                            diferencia.setUlt_dia_fiebre2("<span class='badge badge-danger'>"+diferencia.getUlt_dia_fiebre2()+"</span>");
                        }
                        if (!diferencia.getHora_ult_dia_fiebre1().equalsIgnoreCase(diferencia.getHora_ult_dia_fiebre2())) {
                            diferencia.setHora_ult_dia_fiebre1("<span class='badge badge-danger'>"+diferencia.getHora_ult_dia_fiebre1()+"</span>");
                            diferencia.setHora_ult_dia_fiebre2("<span class='badge badge-danger'>"+diferencia.getHora_ult_dia_fiebre2()+"</span>");
                        }
                        if (!diferencia.getUlt_dosis_antipiretico1().equalsIgnoreCase(diferencia.getUlt_dosis_antipiretico2())) {
                            diferencia.setUlt_dosis_antipiretico1("<span class='badge badge-danger'>"+diferencia.getUlt_dosis_antipiretico1()+"</span>");
                            diferencia.setUlt_dosis_antipiretico2("<span class='badge badge-danger'>"+diferencia.getUlt_dosis_antipiretico2()+"</span>");
                        }
                        if (!diferencia.getHora_ult_dosis_antipiretico1().equalsIgnoreCase(diferencia.getHora_ult_dosis_antipiretico2())) {
                            diferencia.setHora_ult_dosis_antipiretico1("<span class='badge badge-danger'>"+diferencia.getHora_ult_dosis_antipiretico1()+"</span>");
                            diferencia.setHora_ult_dosis_antipiretico2("<span class='badge badge-danger'>"+diferencia.getHora_ult_dosis_antipiretico2()+"</span>");
                        }
                        if (!diferencia.getFiebre1().equalsIgnoreCase(diferencia.getFiebre2())) {
                            diferencia.setFiebre1("<span class='badge badge-danger'>"+diferencia.getFiebre1()+"</span>");
                            diferencia.setFiebre2("<span class='badge badge-danger'>"+diferencia.getFiebre2()+"</span>");
                        }
                        if (!diferencia.getAsomnoliento1().equalsIgnoreCase(diferencia.getAsomnoliento2())) {
                            diferencia.setAsomnoliento1("<span class='badge badge-danger'>"+diferencia.getAsomnoliento1()+"</span>");
                            diferencia.setAsomnoliento2("<span class='badge badge-danger'>"+diferencia.getAsomnoliento2()+"</span>");
                        }
                        if (!diferencia.getMalEstado1().equalsIgnoreCase(diferencia.getMalEstado2())) {
                            diferencia.setMalEstado1("<span class='badge badge-danger'>"+diferencia.getMalEstado1()+"</span>");
                            diferencia.setMalEstado2("<span class='badge badge-danger'>"+diferencia.getMalEstado2()+"</span>");
                        }
                        if (!diferencia.getPerdidaConsciencia1().equalsIgnoreCase(diferencia.getPerdidaConsciencia2())) {
                            diferencia.setPerdidaConsciencia1("<span class='badge badge-danger'>"+diferencia.getPerdidaConsciencia1()+"</span>");
                            diferencia.setPerdidaConsciencia2("<span class='badge badge-danger'>"+diferencia.getPerdidaConsciencia2()+"</span>");
                        }
                        if (!diferencia.getInquieto1().equalsIgnoreCase(diferencia.getInquieto2())) {
                            diferencia.setInquieto1("<span class='badge badge-danger'>"+diferencia.getInquieto1()+"</span>");
                            diferencia.setInquieto2("<span class='badge badge-danger'>"+diferencia.getInquieto2()+"</span>");
                        }
                        if (!diferencia.getConvulsiones1().equalsIgnoreCase(diferencia.getConvulsiones2())) {
                            diferencia.setConvulsiones1("<span class='badge badge-danger'>"+diferencia.getConvulsiones1()+"</span>");
                            diferencia.setConvulsiones2("<span class='badge badge-danger'>"+diferencia.getConvulsiones2()+"</span>");
                        }
                        if (!diferencia.getLetargia1().equalsIgnoreCase(diferencia.getLetargia1())) {
                            diferencia.setLetargia1("<span class='badge badge-danger'>"+diferencia.getLetargia1()+"</span>");
                            diferencia.setLetargia2("<span class='badge badge-danger'>"+diferencia.getLetargia2()+"</span>");
                        }
                        if (!diferencia.getDolorCabeza1().equalsIgnoreCase(diferencia.getDolorCabeza2())) {
                            diferencia.setDolorCabeza1("<span class='badge badge-danger'>"+diferencia.getDolorCabeza1()+"</span>");
                            diferencia.setDolorCabeza2("<span class='badge badge-danger'>"+diferencia.getDolorCabeza2()+"</span>");
                        }
                        if (!diferencia.getConjuntivitis1().equalsIgnoreCase(diferencia.getConjuntivitis2())) {
                            diferencia.setConjuntivitis1("<span class='badge badge-danger'>"+diferencia.getConjuntivitis1()+"</span>");
                            diferencia.setConjuntivitis2("<span class='badge badge-danger'>"+diferencia.getConjuntivitis2()+"</span>");
                        }
                        if (!diferencia.getHemorragiaSuconjuntival1().equalsIgnoreCase(diferencia.getHemorragiaSuconjuntival2())) {
                            diferencia.setHemorragiaSuconjuntival1("<span class='badge badge-danger'>"+diferencia.getHemorragiaSuconjuntival1()+"</span>");
                            diferencia.setHemorragiaSuconjuntival2("<span class='badge badge-danger'>"+diferencia.getHemorragiaSuconjuntival2()+"</span>");
                        }
                        if (!diferencia.getDolorRetroocular1().equalsIgnoreCase(diferencia.getDolorRetroocular2())) {
                            diferencia.setDolorRetroocular1("<span class='badge badge-danger'>"+diferencia.getDolorRetroocular1()+"</span>");
                            diferencia.setDolorRetroocular2("<span class='badge badge-danger'>"+diferencia.getDolorRetroocular2()+"</span>");
                        }
                        if (!diferencia.getDolorGarganta1().equalsIgnoreCase(diferencia.getDolorGarganta2())) {
                            diferencia.setDolorGarganta1("<span class='badge badge-danger'>"+diferencia.getDolorGarganta1()+"</span>");
                            diferencia.setDolorGarganta2("<span class='badge badge-danger'>"+diferencia.getDolorGarganta2()+"</span>");
                        }
                        if (!diferencia.getEritema1().equalsIgnoreCase(diferencia.getEritema2())) {
                            diferencia.setEritema1("<span class='badge badge-danger'>"+diferencia.getEritema1()+"</span>");
                            diferencia.setEritema2("<span class='badge badge-danger'>"+diferencia.getEritema2()+"</span>");
                        }
                        if (!diferencia.getAdenopatiasCervicales1().equalsIgnoreCase(diferencia.getAdenopatiasCervicales2())) {
                            diferencia.setAdenopatiasCervicales1("<span class='badge badge-danger'>"+diferencia.getAdenopatiasCervicales1()+"</span>");
                            diferencia.setAdenopatiasCervicales2("<span class='badge badge-danger'>"+diferencia.getAdenopatiasCervicales2()+"</span>");
                        }
                        if (!diferencia.getExudado1().equalsIgnoreCase(diferencia.getExudado2())) {
                            diferencia.setExudado1("<span class='badge badge-danger'>"+diferencia.getExudado1()+"</span>");
                            diferencia.setExudado2("<span class='badge badge-danger'>"+diferencia.getExudado2()+"</span>");
                        }
                        if (!diferencia.getPetequiasMucosa1().equalsIgnoreCase(diferencia.getPetequiasMucosa2())) {
                            diferencia.setPetequiasMucosa1("<span class='badge badge-danger'>"+diferencia.getPetequiasMucosa1()+"</span>");
                            diferencia.setPetequiasMucosa2("<span class='badge badge-danger'>"+diferencia.getPetequiasMucosa2()+"</span>");
                        }
                        if (!diferencia.getTos1().equalsIgnoreCase(diferencia.getTos2())) {
                            diferencia.setTos1("<span class='badge badge-danger'>"+diferencia.getTos1()+"</span>");
                            diferencia.setTos2("<span class='badge badge-danger'>"+diferencia.getTos2()+"</span>");
                        }
                        if (!diferencia.getRinorrea1 ().equalsIgnoreCase(diferencia.getRinorrea2())) {
                            diferencia.setRinorrea1("<span class='badge badge-danger'>"+diferencia.getRinorrea1()+"</span>");
                            diferencia.setRinorrea2("<span class='badge badge-danger'>"+diferencia.getRinorrea2()+"</span>");
                        }
                        if (!diferencia.getCongestionNasal1 ().equalsIgnoreCase(diferencia.getCongestionNasal2())) {
                            diferencia.setCongestionNasal1("<span class='badge badge-danger'>"+diferencia.getCongestionNasal1()+"</span>");
                            diferencia.setCongestionNasal2("<span class='badge badge-danger'>"+diferencia.getCongestionNasal2()+"</span>");
                        }
                        if (!diferencia.getOtalgia1().equalsIgnoreCase(diferencia.getOtalgia2())) {
                            diferencia.setOtalgia1("<span class='badge badge-danger'>"+diferencia.getOtalgia1()+"</span>");
                            diferencia.setOtalgia2("<span class='badge badge-danger'>"+diferencia.getOtalgia2()+"</span>");
                        }
                        if (!diferencia.getAleteoNasal1 ().equalsIgnoreCase(diferencia.getAleteoNasal2())) {
                            diferencia.setAleteoNasal1("<span class='badge badge-danger'>"+diferencia.getAleteoNasal1()+"</span>");
                            diferencia.setAleteoNasal2("<span class='badge badge-danger'>"+diferencia.getAleteoNasal2()+"</span>");
                        }
                        if (!diferencia.getRespiracionRapida1 ().equalsIgnoreCase(diferencia.getRespiracionRapida2())) {
                            diferencia.setRespiracionRapida1("<span class='badge badge-danger'>"+diferencia.getRespiracionRapida1()+"</span>");
                            diferencia.setRespiracionRapida2("<span class='badge badge-danger'>"+diferencia.getRespiracionRapida2()+"</span>");
                        }
                        if (!diferencia.getEstridorReposo1  ().equalsIgnoreCase(diferencia.getEstridorReposo2())) {
                            diferencia.setEstridorReposo1("<span class='badge badge-danger'>"+diferencia.getEstridorReposo1()+"</span>");
                            diferencia.setEstridorReposo2("<span class='badge badge-danger'>"+diferencia.getEstridorReposo2()+"</span>");
                        }
                        if (!diferencia.getTirajeSubcostal1 ().equalsIgnoreCase(diferencia.getTirajeSubcostal2())) {
                            diferencia.settirajeSubcostal1("<span class='badge badge-danger'>"+diferencia.getTirajeSubcostal1()+"</span>");
                            diferencia.settirajeSubcostal2("<span class='badge badge-danger'>"+diferencia.getTirajeSubcostal2()+"</span>");
                        }
                        if (!diferencia.getSibilancias1  ().equalsIgnoreCase(diferencia.getSibilancias2())) {
                            diferencia.setSibilancias1("<span class='badge badge-danger'>"+diferencia.getSibilancias1()+"</span>");
                            diferencia.setSibilancias2("<span class='badge badge-danger'>"+diferencia.getSibilancias2()+"</span>");
                        }
                        if (!diferencia.getCrepitos1 ().equalsIgnoreCase(diferencia.getCrepitos2())) {
                            diferencia.setCrepitos1("<span class='badge badge-danger'>"+diferencia.getCrepitos1()+"</span>");
                            diferencia.setCrepitos2("<span class='badge badge-danger'>"+diferencia.getCrepitos2()+"</span>");
                        }
                        if (!diferencia.getRoncos1 ().equalsIgnoreCase(diferencia.getRoncos2())) {
                            diferencia.setRoncos1("<span class='badge badge-danger'>"+diferencia.getRoncos1()+"</span>");
                            diferencia.setRoncos2("<span class='badge badge-danger'>"+diferencia.getRoncos2()+"</span>");
                        }
                        if (!diferencia.getDisnea1  ().equalsIgnoreCase(diferencia.getDisnea2())) {
                            diferencia.setDisnea1("<span class='badge badge-danger'>"+diferencia.getDisnea1()+"</span>");
                            diferencia.setDisnea2("<span class='badge badge-danger'>"+diferencia.getDisnea2()+"</span>");
                        }
                        if (!diferencia.getPocoApetito1 ().equalsIgnoreCase(diferencia.getPocoApetito2())) {
                            diferencia.setPocoApetito1("<span class='badge badge-danger'>"+diferencia.getPocoApetito1()+"</span>");
                            diferencia.setPocoApetito2("<span class='badge badge-danger'>"+diferencia.getPocoApetito2()+"</span>");
                        }
                        if (!diferencia.getNausea1 ().equalsIgnoreCase(diferencia.getNausea2())) {
                            diferencia.setNausea1("<span class='badge badge-danger'>"+diferencia.getNausea1()+"</span>");
                            diferencia.setNausea2("<span class='badge badge-danger'>"+diferencia.getNausea2()+"</span>");
                        }
                        if (!diferencia.getVomito12horas1 ().equalsIgnoreCase(diferencia.getVomito12horas2())) {
                            diferencia.setVomito12horas1("<span class='badge badge-danger'>"+diferencia.getVomito12horas1()+"</span>");
                            diferencia.setVomito12horas2("<span class='badge badge-danger'>"+diferencia.getVomito12horas2()+"</span>");
                        }
                        if (!diferencia.getNumeroVomito12h1 ().equalsIgnoreCase(diferencia.getNumeroVomito12h2())) {
                            diferencia.setNumeroVomito12h1("<span class='badge badge-danger'>"+diferencia.getNumeroVomito12h1()+"</span>");
                            diferencia.setNumeroVomito12h2("<span class='badge badge-danger'>"+diferencia.getNumeroVomito12h2()+"</span>");
                        }
                        if (!diferencia.getDiarrea1 ().equalsIgnoreCase(diferencia.getDiarrea2())) {
                            diferencia.setDiarrea1("<span class='badge badge-danger'>"+diferencia.getDiarrea1()+"</span>");
                            diferencia.setDiarrea2("<span class='badge badge-danger'>"+diferencia.getDiarrea2()+"</span>");
                        }
                        if (!diferencia.getHepatomegalia1 ().equalsIgnoreCase(diferencia.getHepatomegalia2())) {
                            diferencia.setHepatomegalia1("<span class='badge badge-danger'>"+diferencia.getHepatomegalia1()+"</span>");
                            diferencia.setHepatomegalia2("<span class='badge badge-danger'>"+diferencia.getHepatomegalia2()+"</span>");
                        }
                        if (!diferencia.getDolorAbdominal1 ().equalsIgnoreCase(diferencia.getDolorAbdominal2())) {
                            diferencia.setDolorAbdominal1("<span class='badge badge-danger'>"+diferencia.getDolorAbdominal1()+"</span>");
                            diferencia.setDolorAbdominal2("<span class='badge badge-danger'>"+diferencia.getDolorAbdominal2()+"</span>");
                        }
                        if (!diferencia.getArtralgia1 ().equalsIgnoreCase(diferencia.getArtralgia2())) {
                            diferencia.setArtralgia1("<span class='badge badge-danger'>"+diferencia.getArtralgia1()+"</span>");
                            diferencia.setArtralgia2("<span class='badge badge-danger'>"+diferencia.getArtralgia2()+"</span>");
                        }
                        if (!diferencia.getMialgia1 ().equalsIgnoreCase(diferencia.getMialgia2())) {
                            diferencia.setMialgial("<span class='badge badge-danger'>"+diferencia.getMialgia1()+"</span>");
                            diferencia.setMialgia2("<span class='badge badge-danger'>"+diferencia.getMialgia2()+"</span>");
                        }
                        if (!diferencia.getLumbalgia1().equalsIgnoreCase(diferencia.getLumbalgia2())) {
                            diferencia.setLumbalgia1("<span class='badge badge-danger'>"+diferencia.getLumbalgia1()+"</span>");
                            diferencia.setLumbalgia2("<span class='badge badge-danger'>"+diferencia.getLumbalgia2()+"</span>");
                        }
                        if (!diferencia.getDolorCuello1 ().equalsIgnoreCase(diferencia.getDolorCuello2())) {
                            diferencia.setDolorCuello1("<span class='badge badge-danger'>"+diferencia.getDolorCuello1()+"</span>");
                            diferencia.setDolorCuello2("<span class='badge badge-danger'>"+diferencia.getDolorCuello2()+"</span>");
                        }
                        if (!diferencia.getEdema1 ().equalsIgnoreCase(diferencia.getEdema2())) {
                            diferencia.setEdema1("<span class='badge badge-danger'>"+diferencia.getEdema1()+"</span>");
                            diferencia.setEdema2("<span class='badge badge-danger'>"+diferencia.getEdema2()+"</span>");
                        }
                        if (!diferencia.getRahsLocalizado1 ().equalsIgnoreCase(diferencia.getRahsLocalizado2())) {
                            diferencia.setRahsLocalizado1("<span class='badge badge-danger'>"+diferencia.getRahsLocalizado1()+"</span>");
                            diferencia.setRahsLocalizado2("<span class='badge badge-danger'>"+diferencia.getRahsLocalizado2()+"</span>");
                        }
                        if (!diferencia.getRahsGeneralizado1 ().equalsIgnoreCase(diferencia.getRahsGeneralizado2())) {
                            diferencia.setRahsGeneralizado1("<span class='badge badge-danger'>"+diferencia.getRahsGeneralizado1()+"</span>");
                            diferencia.setRahsGeneralizado2("<span class='badge badge-danger'>"+diferencia.getRahsGeneralizado2()+"</span>");
                        }
                        if (!diferencia.getRashEritematoso1 ().equalsIgnoreCase(diferencia.getRashEritematoso2())) {
                            diferencia.setRashEritematoso1("<span class='badge badge-danger'>"+diferencia.getRashEritematoso1()+"</span>");
                            diferencia.setRashEritematoso2("<span class='badge badge-danger'>"+diferencia.getRashEritematoso2()+"</span>");
                        }
                        if (!diferencia.getRahsMacular1  ().equalsIgnoreCase(diferencia.getRahsMacular2())) {
                            diferencia.setRahsMacular1("<span class='badge badge-danger'>"+diferencia.getRahsMacular1()+"</span>");
                            diferencia.setRahsMacular2("<span class='badge badge-danger'>"+diferencia.getRahsMacular2()+"</span>");
                        }
                        if (!diferencia.getRashPapular1 ().equalsIgnoreCase(diferencia.getRashPapular2())) {
                            diferencia.setRashPapular1("<span class='badge badge-danger'>"+diferencia.getRashPapular1()+"</span>");
                            diferencia.setRashPapular2("<span class='badge badge-danger'>"+diferencia.getRashPapular2()+"</span>");
                        }
                        if (!diferencia.getPielMoteada1 ().equalsIgnoreCase(diferencia.getPielMoteada2())) {
                            diferencia.setPielMoteada1("<span class='badge badge-danger'>"+diferencia.getPielMoteada1()+"</span>");
                            diferencia.setPielMoteada2("<span class='badge badge-danger'>"+diferencia.getPielMoteada2()+"</span>");
                        }
                        if (!diferencia.getRuborFacia1 ().equalsIgnoreCase(diferencia.getRuborFacia2())) {
                            diferencia.setRuborFacia1("<span class='badge badge-danger'>"+diferencia.getRuborFacia1()+"</span>");
                            diferencia.setRuborFacia2("<span class='badge badge-danger'>"+diferencia.getRuborFacia2()+"</span>");
                        }
                        if (!diferencia.getCianosisCentral1 ().equalsIgnoreCase(diferencia.getCianosisCentral2())) {
                            diferencia.setCianosisCentral1("<span class='badge badge-danger'>"+diferencia.getCianosisCentral1()+"</span>");
                            diferencia.setCianosisCentral2("<span class='badge badge-danger'>"+diferencia.getCianosisCentral2()+"</span>");
                        }
                        if (!diferencia.getIctericia1 ().equalsIgnoreCase(diferencia.getIctericia2())) {
                            diferencia.setIctericia1("<span class='badge badge-danger'>"+diferencia.getIctericia1()+"</span>");
                            diferencia.setIctericia2("<span class='badge badge-danger'>"+diferencia.getIctericia2()+"</span>");
                        }
                        if (!diferencia.getImc1 ().equalsIgnoreCase(diferencia.getImc2())) {
                            diferencia.setImc1("<span class='badge badge-danger'>"+diferencia.getImc1()+"</span>");
                            diferencia.setImc2("<span class='badge badge-danger'>"+diferencia.getImc2()+"</span>");
                        }
                        if (!diferencia.getObeso1  ().equalsIgnoreCase(diferencia.getObeso2())) {
                            diferencia.setObeso1("<span class='badge badge-danger'>"+diferencia.getObeso1()+"</span>");
                            diferencia.setObeso2("<span class='badge badge-danger'>"+diferencia.getObeso2()+"</span>");
                        }
                        if (!diferencia.getSobrepeso1 ().equalsIgnoreCase(diferencia.getSobrepeso2())) {
                            diferencia.setSobrepeso1("<span class='badge badge-danger'>"+diferencia.getSobrepeso1()+"</span>");
                            diferencia.setSobrepeso2("<span class='badge badge-danger'>"+diferencia.getSobrepeso2()+"</span>");
                        }
                        if (!diferencia.getSospechaProblema1 ().equalsIgnoreCase(diferencia.getSospechaProblema2())) {
                            diferencia.setSospechaProblema1("<span class='badge badge-danger'>"+diferencia.getSospechaProblema1()+"</span>");
                            diferencia.setSospechaProblema2("<span class='badge badge-danger'>"+diferencia.getSospechaProblema2()+"</span>");
                        }
                        if (!diferencia.getNormal1 ().equalsIgnoreCase(diferencia.getNormal2())) {
                            diferencia.setNormal1("<span class='badge badge-danger'>"+diferencia.getNormal1()+"</span>");
                            diferencia.setNormal2("<span class='badge badge-danger'>"+diferencia.getNormal2()+"</span>");
                        }
                        if (!diferencia.getBajoPeso1 ().equalsIgnoreCase(diferencia.getBajoPeso2())) {
                            diferencia.setBajoPeso1("<span class='badge badge-danger'>"+diferencia.getBajoPeso1()+"</span>");
                            diferencia.setBajoPeso2("<span class='badge badge-danger'>"+diferencia.getBajoPeso2()+"</span>");
                        }
                        if (!diferencia.getBajoPesoSevero1 ().equalsIgnoreCase(diferencia.getBajoPesoSevero2())) {
                            diferencia.setBajoPesoSevero1("<span class='badge badge-danger'>"+diferencia.getBajoPesoSevero1()+"</span>");
                            diferencia.setBajoPesoSevero2("<span class='badge badge-danger'>"+diferencia.getBajoPesoSevero2()+"</span>");
                        }
                        if (!diferencia.getCategoria1 ().equalsIgnoreCase(diferencia.getCategoria2())) {
                            diferencia.setCategoria1("<span class='badge badge-danger'>"+diferencia.getCategoria1()+"</span>");
                            diferencia.setCategoria2("<span class='badge badge-danger'>"+diferencia.getCategoria2()+"</span>");
                        }
                        if (!diferencia.getCambioCategoria1().equalsIgnoreCase(diferencia.getCambioCategoria2())) {
                            diferencia.setCambioCategoria1("<span class='badge badge-danger'>"+diferencia.getCambioCategoria1()+"</span>");
                            diferencia.setCambioCategoria2("<span class='badge badge-danger'>"+diferencia.getCambioCategoria2()+"</span>");
                        }
                        if (!diferencia.getCambioCategoria1 ().equalsIgnoreCase(diferencia.getCambioCategoria2())) {
                            diferencia.setCambioCategoria1("<span class='badge badge-danger'>"+diferencia.getCambioCategoria1()+"</span>");
                            diferencia.setCambioCategoria2("<span class='badge badge-danger'>"+diferencia.getCambioCategoria2()+"</span>");
                        }
                        if (!diferencia.getPruebaTorniquetePositiva1 ().equalsIgnoreCase(diferencia.getPruebaTorniquetePositiva2())) {
                            diferencia.setPruebaTorniquetePositiva1("<span class='badge badge-danger'>"+diferencia.getPruebaTorniquetePositiva1()+"</span>");
                            diferencia.setPruebaTorniquetePositiva2("<span class='badge badge-danger'>"+diferencia.getPruebaTorniquetePositiva2()+"</span>");
                        }
                        if (!diferencia.getPetequia10Pt1 ().equalsIgnoreCase(diferencia.getPetequia10Pt2())) {
                            diferencia.setPetequia10Pt1("<span class='badge badge-danger'>"+diferencia.getPetequia10Pt1()+"</span>");
                            diferencia.setPetequia10Pt2("<span class='badge badge-danger'>"+diferencia.getPetequia10Pt2()+"</span>");
                        }
                        if (!diferencia.getPetequia20Pt1  ().equalsIgnoreCase(diferencia.getPetequia20Pt1())) {
                            diferencia.setPetequia20Pt1("<span class='badge badge-danger'>"+diferencia.getPetequia20Pt1()+"</span>");
                            diferencia.setPetequia20Pt2("<span class='badge badge-danger'>"+diferencia.getPetequia20Pt2()+"</span>");
                        }
                        if (!diferencia.getPielExtremidadesFrias1 ().equalsIgnoreCase(diferencia.getPielExtremidadesFrias1())) {
                            diferencia.setPielExtremidadesFrias1("<span class='badge badge-danger'>"+diferencia.getPielExtremidadesFrias1()+"</span>");
                            diferencia.setPielExtremidadesFrias2("<span class='badge badge-danger'>"+diferencia.getPielExtremidadesFrias2()+"</span>");
                        }
                        if (!diferencia.getPalidezEnExtremidades1 ().equalsIgnoreCase(diferencia.getPalidezEnExtremidades2())) {
                            diferencia.setPalidezEnExtremidades1("<span class='badge badge-danger'>"+diferencia.getPalidezEnExtremidades1()+"</span>");
                            diferencia.setPalidezEnExtremidades2("<span class='badge badge-danger'>"+diferencia.getPalidezEnExtremidades2()+"</span>");
                        }
                        if (!diferencia.getEpistaxis1 ().equalsIgnoreCase(diferencia.getEpistaxis2())) {
                            diferencia.setEpistaxis1("<span class='badge badge-danger'>"+diferencia.getEpistaxis1()+"</span>");
                            diferencia.setEpistaxis2("<span class='badge badge-danger'>"+diferencia.getEpistaxis2()+"</span>");
                        }
                        if (!diferencia.getGingivorragia1().equalsIgnoreCase(diferencia.getGingivorragia2())) {
                            diferencia.setGingivorragia1("<span class='badge badge-danger'>"+diferencia.getGingivorragia1()+"</span>");
                            diferencia.setGingivorragia2("<span class='badge badge-danger'>"+diferencia.getGingivorragia2()+"</span>");
                        }
                        if (!diferencia.getPetequiasEspontaneas1 ().equalsIgnoreCase(diferencia.getPetequiasEspontaneas2())) {
                            diferencia.setPetequiasEspontaneas1("<span class='badge badge-danger'>"+diferencia.getPetequiasEspontaneas1()+"</span>");
                            diferencia.setPetequiasEspontaneas2("<span class='badge badge-danger'>"+diferencia.getPetequiasEspontaneas2()+"</span>");
                        }
                        if (!diferencia.getLlenadoCapilar2seg1 ().equalsIgnoreCase(diferencia.getLlenadoCapilar2seg2())) {
                            diferencia.setLlenadoCapilar2seg1("<span class='badge badge-danger'>"+diferencia.getLlenadoCapilar2seg1()+"</span>");
                            diferencia.setLlenadoCapilar2seg2("<span class='badge badge-danger'>"+diferencia.getLlenadoCapilar2seg2()+"</span>");
                        }
                        if (!diferencia.getCianosis1 ().equalsIgnoreCase(diferencia.getCianosis2())) {
                            diferencia.setCianosis1("<span class='badge badge-danger'>"+diferencia.getCianosis1()+"</span>");
                            diferencia.setCianosis2("<span class='badge badge-danger'>"+diferencia.getCianosis2()+"</span>");
                        }
                        if (!diferencia.getHipermenorrea1 ().equalsIgnoreCase(diferencia.getHipermenorrea2())) {
                            diferencia.setHipermenorrea1("<span class='badge badge-danger'>"+diferencia.getHipermenorrea1()+"</span>");
                            diferencia.setHipermenorrea2("<span class='badge badge-danger'>"+diferencia.getHipermenorrea2()+"</span>");
                        }
                        if (!diferencia.getHematemesis1 ().equalsIgnoreCase(diferencia.getHematemesis2())) {
                            diferencia.setHematemesis1("<span class='badge badge-danger'>"+diferencia.getHematemesis1()+"</span>");
                            diferencia.setHematemesis2("<span class='badge badge-danger'>"+diferencia.getHematemesis2()+"</span>");
                        }
                        if (!diferencia.getHemoconcentracion1 ().equalsIgnoreCase(diferencia.getHemoconcentracion2())) {
                            diferencia.setHemoconcentracion1("<span class='badge badge-danger'>"+diferencia.getHemoconcentracion1()+"</span>");
                            diferencia.setHemoconcentracion2("<span class='badge badge-danger'>"+diferencia.getHemoconcentracion2()+"</span>");
                        }
                        if (!diferencia.getHospitalizado1 ().equalsIgnoreCase(diferencia.getHospitalizado2())) {
                            diferencia.setHospitalizado1("<span class='badge badge-danger'>"+diferencia.getHospitalizado1()+"</span>");
                            diferencia.setHospitalizado2("<span class='badge badge-danger'>"+diferencia.getHospitalizado2()+"</span>");
                        }
                        if (!diferencia.getHospitalizadoEspecificar1 ().equalsIgnoreCase(diferencia.getHospitalizadoEspecificar2())) {
                            diferencia.setHospitalizadoEspecificar1("<span class='badge badge-danger'>"+diferencia.getHospitalizadoEspecificar1()+"</span>");
                            diferencia.setHospitalizadoEspecificar2("<span class='badge badge-danger'>"+diferencia.getHospitalizadoEspecificar2()+"</span>");
                        }
                        if (!diferencia.getTransfusionSangre1 ().equalsIgnoreCase(diferencia.getTransfusionSangre2())) {
                            diferencia.setTransfusionSangre1("<span class='badge badge-danger'>"+diferencia.getTransfusionSangre1()+"</span>");
                            diferencia.setTransfusionSangre2("<span class='badge badge-danger'>"+diferencia.getTransfusionSangre2()+"</span>");
                        }
                        if (!diferencia.getTransfusionEspecificar1 ().equalsIgnoreCase(diferencia.getTransfusionEspecificar2())) {
                            diferencia.setTransfusionEspecificar1("<span class='badge badge-danger'>"+diferencia.getTransfusionEspecificar1()+"</span>");
                            diferencia.setTransfusionEspecificar2("<span class='badge badge-danger'>"+diferencia.getTransfusionEspecificar2()+"</span>");
                        }
                        if (!diferencia.getTomandoMedicamento1 ().equalsIgnoreCase(diferencia.getTomandoMedicamento2())) {
                            diferencia.setTomandoMedicamento1("<span class='badge badge-danger'>"+diferencia.getTomandoMedicamento1()+"</span>");
                            diferencia.setTomandoMedicamento2("<span class='badge badge-danger'>"+diferencia.getTomandoMedicamento2()+"</span>");
                        }
                        if (!diferencia.getMedicamentoEspecificar1 ().equalsIgnoreCase(diferencia.getMedicamentoEspecificar2())) {
                            diferencia.setMedicamentoEspecificar1("<span class='badge badge-danger'>"+diferencia.getMedicamentoEspecificar1()+"</span>");
                            diferencia.setMedicamentoEspecificar2("<span class='badge badge-danger'>"+diferencia.getMedicamentoEspecificar2()+"</span>");
                        }
                        if (!diferencia.getBhc1 ().equalsIgnoreCase(diferencia.getBhc2())) {
                            diferencia.setBhc1("<span class='badge badge-danger'>"+diferencia.getBhc1()+"</span>");
                            diferencia.setBhc2("<span class='badge badge-danger'>"+diferencia.getBhc2()+"</span>");
                        }
                        if (!diferencia.getSerologiaArbovirus1 ().equalsIgnoreCase(diferencia.getSerologiaArbovirus2())) {
                            diferencia.setSerologiaArbovirus1("<span class='badge badge-danger'>"+diferencia.getSerologiaArbovirus1()+"</span>");
                            diferencia.setSerologiaArbovirus2("<span class='badge badge-danger'>"+diferencia.getSerologiaArbovirus2()+"</span>");
                        }
                        if (!diferencia.getGotaGruesa1 ().equalsIgnoreCase(diferencia.getGotaGruesa2())) {
                            diferencia.setGotaGruesa1("<span class='badge badge-danger'>"+diferencia.getGotaGruesa1()+"</span>");
                            diferencia.setGotaGruesa2("<span class='badge badge-danger'>"+diferencia.getGotaGruesa2()+"</span>");
                        }
                        if (!diferencia.getEgo1 ().equalsIgnoreCase(diferencia.getEgo2())) {
                            diferencia.setEgo1 ("<span class='badge badge-danger'>"+diferencia.getEgo1 ()+"</span>");
                            diferencia.setEgo2 ("<span class='badge badge-danger'>"+diferencia.getEgo2()+"</span>");
                        }
                        if (!diferencia.getEgh1 ().equalsIgnoreCase(diferencia.getEgh2())) {
                            diferencia.setEgh1 ("<span class='badge badge-danger'>"+diferencia.getEgh1 ()+"</span>");
                            diferencia.setEgh2("<span class='badge badge-danger'>"+diferencia.getEgh2()+"</span>");
                        }
                        if (!diferencia.getOtroExamenLab1 ().equalsIgnoreCase(diferencia.getOtroExamenLab2())) {
                            diferencia.setOtroExamenLab1("<span class='badge badge-danger'>"+diferencia.getOtroExamenLab1()+"</span>");
                            diferencia.setOtroExamenLab2("<span class='badge badge-danger'>"+diferencia.getOtroExamenLab2()+"</span>");
                        }
                        if (!diferencia.getOtroExamanLabEspecificar1 ().equalsIgnoreCase(diferencia.getOtroExamanLabEspecificar2 ())) {
                            diferencia.setOtroExamanLabEspecificar1 ("<span class='badge badge-danger'>"+diferencia.getOtroExamanLabEspecificar1 ()+"</span>");
                            diferencia.setOtroExamanLabEspecificar2("<span class='badge badge-danger'>"+diferencia.getOtroExamanLabEspecificar2()+"</span>");
                        }
                        if (!diferencia.getAcetaminofen1 ().equalsIgnoreCase(diferencia.getAcetaminofen2())) {
                            diferencia.setAcetaminofen1("<span class='badge badge-danger'>"+diferencia.getAcetaminofen1()+"</span>");
                            diferencia.setAcetaminofen2("<span class='badge badge-danger'>"+diferencia.getAcetaminofen2()+"</span>");
                        }
                        if (!diferencia.getAmoxicilina1 ().equalsIgnoreCase(diferencia.getAmoxicilina2())) {
                            diferencia.setAmoxicilina1("<span class='badge badge-danger'>"+diferencia.getAmoxicilina1()+"</span>");
                            diferencia.setAmoxicilina2("<span class='badge badge-danger'>"+diferencia.getAmoxicilina2()+"</span>");
                        }
                        if (!diferencia.getDicloxacilina1 ().equalsIgnoreCase(diferencia.getDicloxacilina2())) {
                            diferencia.setDicloxacilina1("<span class='badge badge-danger'>"+diferencia.getDicloxacilina1()+"</span>");
                            diferencia.setDicloxacilina2("<span class='badge badge-danger'>"+diferencia.getDicloxacilina2()+"</span>");
                        }
                        if (!diferencia.getPenicilina1  ().equalsIgnoreCase(diferencia.getPenicilina2())) {
                            diferencia.setPenicilina1("<span class='badge badge-danger'>"+diferencia.getPenicilina1()+"</span>");
                            diferencia.setPenicilina2("<span class='badge badge-danger'>"+diferencia.getPenicilina2()+"</span>");
                        }
                        if (!diferencia.getFurazolidona1 ().equalsIgnoreCase(diferencia.getFurazolidona2())) {
                            diferencia.setFurazolidona1("<span class='badge badge-danger'>"+diferencia.getFurazolidona1()+"</span>");
                            diferencia.setFurazolidona2("<span class='badge badge-danger'>"+diferencia.getFurazolidona2()+"</span>");
                        }
                        if (!diferencia.getMetronidazolTinidazol1 ().equalsIgnoreCase(diferencia.getMetronidazolTinidazol2())) {
                            diferencia.setMetronidazolTinidazol1("<span class='badge badge-danger'>"+diferencia.getMetronidazolTinidazol1()+"</span>");
                            diferencia.setMetronidazolTinidazol2("<span class='badge badge-danger'>"+diferencia.getMetronidazolTinidazol2()+"</span>");
                        }
                        if (!diferencia.getAlbendazolMebendazol1 ().equalsIgnoreCase(diferencia.getAlbendazolMebendazol1())) {
                            diferencia.setAlbendazolMebendazol1("<span class='badge badge-danger'>"+diferencia.getAlbendazolMebendazol1()+"</span>");
                            diferencia.setAlbendazolMebendazol2("<span class='badge badge-danger'>"+diferencia.getAlbendazolMebendazol2()+"</span>");
                        }
                        if (!diferencia.getSueroOral1 ().equalsIgnoreCase(diferencia.getSueroOral2())) {
                            diferencia.setSueroOral1("<span class='badge badge-danger'>"+diferencia.getSueroOral1()+"</span>");
                            diferencia.setSueroOral2("<span class='badge badge-danger'>"+diferencia.getSueroOral2()+"</span>");
                        }
                        if (!diferencia.getLiquidosiv1 ().equalsIgnoreCase(diferencia.getLiquidosiv2())) {
                            diferencia.setLiquidosiv1("<span class='badge badge-danger'>"+diferencia.getLiquidosiv1()+"</span>");
                            diferencia.setLiquidosiv2("<span class='badge badge-danger'>"+diferencia.getLiquidosiv2()+"</span>");
                        }
                        if (!diferencia.getOtroTratamiento1 ().equalsIgnoreCase(diferencia.getOtroTratamiento2())) {
                            diferencia.setOtroTratamiento1("<span class='badge badge-danger'>"+diferencia.getOtroTratamiento1()+"</span>");
                            diferencia.setOtroTratamiento2("<span class='badge badge-danger'>"+diferencia.getOtroTratamiento2()+"</span>");
                        }
                        if (!diferencia.getOtroTratamientoEspecificar1 ().equalsIgnoreCase(diferencia.getOtroTratamientoEspecificar2())) {
                            diferencia.setOtroTratamientoEspecificar1("<span class='badge badge-danger'>"+diferencia.getOtroTratamientoEspecificar1()+"</span>");
                            diferencia.setOtroTratamientoEspecificar2("<span class='badge badge-danger'>"+diferencia.getOtroTratamientoEspecificar2()+"</span>");
                        }
                        if (!diferencia.getPlanes1 ().equalsIgnoreCase(diferencia.getPlanes2())) {
                            diferencia.setPlanes1 ("<span class='badge badge-danger'>"+diferencia.getPlanes1 ()+"</span>");
                            diferencia.setPlanes2("<span class='badge badge-danger'>"+diferencia.getPlanes2()+"</span>");
                        }
                        if (!diferencia.getHistoriaClinica1  ().equalsIgnoreCase(diferencia.getHistoriaClinica2())) {
                            diferencia.setHistoriaClinica1 ("<span class='badge badge-danger'>"+diferencia.getHistoriaClinica1 ()+"</span>");
                            diferencia.setHistoriaClinica2("<span class='badge badge-danger'>"+diferencia.getHistoriaClinica2()+"</span>");
                        }
                        if (!diferencia.getDiagnostico1 ().equalsIgnoreCase(diferencia.getDiagnostico2())) {
                            diferencia.setDiagnostico1 ("<span class='badge badge-danger'>"+diferencia.getDiagnostico1 ()+"</span>");
                            diferencia.setDiagnostico2("<span class='badge badge-danger'>"+diferencia.getDiagnostico2()+"</span>");
                        }

                        if (!diferencia.getDiagnostico11 ().equalsIgnoreCase(diferencia.getDiagnostico12())) {
                            diferencia.setDiagnostico11 ("<span class='badge badge-danger'>"+diferencia.getDiagnostico11 ()+"</span>");
                            diferencia.setDiagnostico12("<span class='badge badge-danger'>"+diferencia.getDiagnostico12()+"</span>");
                        }

                        if (!diferencia.getDiagnostico21 ().equalsIgnoreCase(diferencia.getDiagnostico22())) {
                            diferencia.setDiagnostico21 ("<span class='badge badge-danger'>"+diferencia.getDiagnostico21 ()+"</span>");
                            diferencia.setDiagnostico22("<span class='badge badge-danger'>"+diferencia.getDiagnostico22()+"</span>");
                        }

                        if (!diferencia.getDiagnostico31 ().equalsIgnoreCase(diferencia.getDiagnostico32())) {
                            diferencia.setDiagnostico31 ("<span class='badge badge-danger'>"+diferencia.getDiagnostico31 ()+"</span>");
                            diferencia.setDiagnostico32("<span class='badge badge-danger'>"+diferencia.getDiagnostico32()+"</span>");
                        }

                        if (!diferencia.getDiagnostico41 ().equalsIgnoreCase(diferencia.getDiagnostico42())) {
                            diferencia.setDiagnostico41 ("<span class='badge badge-danger'>"+diferencia.getDiagnostico41 ()+"</span>");
                            diferencia.setDiagnostico42("<span class='badge badge-danger'>"+diferencia.getDiagnostico42()+"</span>");
                        }

                       /* if (!diferencia.getCodigo_supervisor1().equalsIgnoreCase(diferencia.getCodigo_supervisor2())) {
                            diferencia.setCodigo_supervisor1("<span class='badge badge-danger'>"+diferencia.getCodigo_supervisor1 ()+"</span>");
                            diferencia.setCodigo_supervisor2("<span class='badge badge-danger'>"+diferencia.getCodigo_supervisor2()+"</span>");
                        }*/

                        if (!diferencia.getTelefonoEmergencia1 ().equalsIgnoreCase(diferencia.getTelefonoEmergencia2())) {
                            diferencia.setTelefonoEmergencia1 ("<span class='badge badge-danger'>"+diferencia.getTelefonoEmergencia1()+"</span>");
                            diferencia.setTelefonoEmergencia2("<span class='badge badge-danger'>"+diferencia.getTelefonoEmergencia2()+"</span>");
                        }

                        if (!diferencia.getProximaCita1 ().equalsIgnoreCase(diferencia.getProximaCita2())) {
                            diferencia.setProximaCita1 ("<span class='badge badge-danger'>"+diferencia.getProximaCita1 ()+"</span>");
                            diferencia.setProximaCita2("<span class='badge badge-danger'>"+diferencia.getProximaCita2()+"</span>");
                        }

                        if (!diferencia.getMedico1 ().equalsIgnoreCase(diferencia.getMedico2())) {
                            diferencia.setMedico1 ("<span class='badge badge-danger'>"+diferencia.getMedico1 ()+"</span>");
                            diferencia.setMedico2("<span class='badge badge-danger'>"+diferencia.getMedico2()+"</span>");
                        }
                        if(diferencia.getFechaMedico1() != null ) {
                            if (!diferencia.getFechaMedico1().equalsIgnoreCase(diferencia.getFechaMedico2())) {
                                diferencia.setFechaMedico1("<span class='badge badge-danger'>" + diferencia.getFechaMedico1() + "</span>");
                                diferencia.setFechaMedico2("<span class='badge badge-danger'>" + diferencia.getFechaMedico2() + "</span>");
                            }
                        }
                        if(diferencia.getHoraMedico1() != null ) {
                            if (!diferencia.getHoraMedico1().equalsIgnoreCase(diferencia.getHoraMedico2())) {
                                diferencia.setHoraMedico1("<span class='badge badge-danger'>" + diferencia.getHoraMedico1() + "</span>");
                                diferencia.setHoraMedico2("<span class='badge badge-danger'>" + diferencia.getHoraMedico2() + "</span>");
                            }
                        }

                        if (!diferencia.getEnfermeria1 ().equalsIgnoreCase(diferencia.getEnfermeria2())) {
                            diferencia.setEnfermeria1 ("<span class='badge badge-danger'>"+diferencia.getEnfermeria1 ()+"</span>");
                            diferencia.setEnfermeria2("<span class='badge badge-danger'>"+diferencia.getEnfermeria2()+"</span>");
                        }
                        if(diferencia.getFechaEnfermeria1() != null ) {
                            if (!diferencia.getFechaEnfermeria1().equalsIgnoreCase(diferencia.getFechaEnfermeria2())) {
                                diferencia.setFechaEnfermeria1("<span class='badge badge-danger'>" + diferencia.getFechaEnfermeria1() + "</span>");
                                diferencia.setFechaEnfermeria2("<span class='badge badge-danger'>" + diferencia.getFechaEnfermeria2() + "</span>");
                            }
                        }
                        if(diferencia.getHoraEnfermeria1() != null ) {
                            if (!diferencia.getHoraEnfermeria1().equalsIgnoreCase(diferencia.getHoraEnfermeria2())) {
                                diferencia.setHoraEnfermeria1("<span class='badge badge-danger'>" + diferencia.getHoraEnfermeria1() + "</span>");
                                diferencia.setHoraEnfermeria2("<span class='badge badge-danger'>" + diferencia.getHoraEnfermeria2() + "</span>");
                            }
                        }





                    }

                    return JsonUtil.createJsonResponse(hojaClinicaList);
                 //   return JsonUtil.createJsonResponse(hojaClinicaList);
                } else
                    return JsonUtil.createJsonResponse("No se encontró Hoja Clínica según el código ingresado");
            } else

                return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
                List<HojaClinicaDto> hojaClinicaList = this.hojaClinicaService.get(codigo, DateUtil.StringToDate(fechaInicioCons, "dd/MM/yyyy"), DateUtil.StringToDate(fechaFinCons, "dd/MM/yyyy"));
                if (hojaClinicaList.size() > 0) {
                    return JsonUtil.createJsonResponse(hojaClinicaList);
                } else
                    return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDto>());
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
                nombreMedico = hojaClinicaService.getcodigoSupervisor(codigoSupervisor);
                if (!nombreMedico.isEmpty()) {
                    return JsonUtil.createJsonResponse(nombreMedico);
                } else
                    return JsonUtil.createJsonResponse("No se encontró Médico según el código ingresado");
            } else return JsonUtil.createJsonResponse(new ArrayList<HojaClinicaDto>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private HojaClinica ParseJsonRequestToHojaClinica(HttpServletRequest request) throws Exception{
        HojaClinica hojaClinica = new HojaClinica();
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

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String hojaStr = jObjectRespuestas.get("hojaClinica").toString();
        String codigoParticipante = jObjectRespuestas.get("participante").getAsString();

        Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
        hojaClinica.setCodigoParticipante(participante);

        JsonObject jsonpObject = new Gson().fromJson(hojaStr, JsonObject.class);

        if (jsonpObject.get("codSup1")!=null && !jsonpObject.get("codSup1").getAsString().isEmpty()) codigosupe = jsonpObject.get("codSup1").getAsString();

        if (jsonpObject.get("fechaCons")!=null && !jsonpObject.get("fechaCons").getAsString().isEmpty()) fechaConsulta = DateUtil.StringToDate(jsonpObject.get("fechaCons").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaCons")!=null && !jsonpObject.get("horaCons").getAsString().isEmpty()) horaConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaCons").getAsString()));

        if (jsonpObject.get("numHoja1")!=null && !jsonpObject.get("numHoja1").getAsString().isEmpty()) numeroHoja = Integer.valueOf((jsonpObject.get("numHoja1").getAsString()));

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

      //  hojaClinica.setCodigoParticipante(codigoParticipante);
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
    /**carga el archivo excel**/

    @RequestMapping(value = "cargarFileBc6000bhc", method = RequestMethod.GET)
    public String cargarFileBc6000bhc(Model model) throws ParseException {
        logger.debug("Cargar file excel BC6000");
        return "Serologia/uploadFileBhc6000";
    }
    @RequestMapping(value = "imprimirBhcBc6000", method = RequestMethod.GET)
    public String imprimirBhcBc6000(Model model) throws ParseException {
        logger.debug("imprimir Bhc BC6000");
        return "Serologia/imprimirBhc6000";
    }

    @RequestMapping(value = "/uploadEntityFile/", method = RequestMethod.POST)
    public String submitUploadForm(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        boolean checkLabReceiptDate = false;
        String bhc_id = "";
         String id_muestr;
         String modo_recue;
         String panel_prue;
         java.util.Date fec;
         String hora;
         String pos_tubo;
         String comunicado;
         String impreso;
         double wbc;
         double neu;
         double linf;
         double mon;
         double eos;
         double bas;
         double img;
         double neu_porcentaje;
         double linf_porcentaje;
         double mon_porcentaje;
         double eos_porcentaje;
         double bas_porcentaje;
         double img_porcentaje;
         double rbc;
         double hgb;
         double hct;
         double mcv;
         double mch;
         double mchc;
         double rdw_cv;
         double rdw_sd;
         double plt;
         double mpv;
         double pdw;
         double pct;
         double p_lcc;
         double p_lcr;
         double nrbc;
         double nrbc_porcentaje;
        /*Hasta aqui son 34 campos con información exportada de Bc6000*/

         double wbc_bf;
         double tc_bf;
         double mn;
         double mn_porcentaje;
         double pmn;
         double pmn_porcentaje;
         double rbc_bf;

         String id_pacient;
         String nomb;
         String sexo;
         Date fecha_nacim;
         double edad;
         String tipo_pac;
         String grupo_ref;
         String dpto;
         String num_cama;
         Date fecha_extr;
         String hora_extr;
         Date fecha_ext;
         String hora_entrega;
         String medico;
         String operador;
         String validado_por;
         String coments;
        String Record_date;

        Map<String, String> map = new HashMap<String, String>();
        //Record_user = "";
        Record_date = "";
        Bhc_Bc6000 entidad = new Bhc_Bc6000();
        Bhc_Bc6000 entidad_error = new Bhc_Bc6000();
        List<Bhc_Bc6000> entidades = new ArrayList<Bhc_Bc6000>();
        List<Bhc_Bc6000> entidades_error = new ArrayList<Bhc_Bc6000>();
        List<String> codigos_error = new ArrayList<String>();
        List<String> codigos_error_mensaje = new ArrayList<String>();
        //WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        // UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        try {
            //Read the file
            try {
                //Read the file
                Reader in = new InputStreamReader(file.getInputStream());

                //Define the format
                CSVFormat format = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase();

                //Create the parser
                CSVParser parsed = CSVParser.parse(in, format);
                //  CSVParser parsed = CSVParser.parse(in, CSVFormat.EXCEL.withHeader("ID muestr", "Modo recue", "Fec.","Hora","Pos. tubo","Comunicado","Impreso","WBC","Neu#","Linf#","Mon#","Eos#","Bas#","IMG#","Neu%","Linf%","Mon%","Eos%","Bas%","IMG%","RBC","HGB","HCT","MCV","MCH","MCHC","RDW-CV","RDW-SD","PLT","MPV","PDW","PCT","P-LCC","P-LCR","NRBC#","NRBC%","WBC-BF","TC-BF#"
                //         ,"MN#","MN%","PMN#","PMN%","RBC-BF","ID pacient","Nomb","Sexo","Fecha nacim","Ed","Tipo pac","Grupo ref.","Dpto","Nº cama","Fech extr","Hora extr","Fecha ext","Hora entrega","Médico","Operador","Validado por","Coments"));


                //Verify that labId Exist in the file

                List<String> encabezados = parsed.getHeaderNames();

                for (String encabezado : encabezados) {
                    String[] enca = encabezado.split("\t");
                    if (enca[0].contains("ID muestr")) {
                        logger.info(encabezado + " found....");
                    } else if (enca[0].contains("Modo recue")) {
                        logger.info(encabezado + " found....");
                        //   checkName = true;
                    } else if (encabezado.equalsIgnoreCase("Fec.")) {
                        logger.info(encabezado + " found....");
                        //  checkObs = true;
                    } else if (encabezado.equalsIgnoreCase("Hora")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Pos. tubo")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Comunicado")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Impreso")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("WBC")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Neu#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Linf#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Mon#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Eos#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Bas#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("IMG#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Neu%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Linf%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Mon%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Eos%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Bas%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("IMG%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("RBC")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("HGB")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("HCT")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MCV")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MCH")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MCHC")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("RDW-CV")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("RDW-SD")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("PLT")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MPV")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("PDW")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("PCT")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("P-LCC")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("P-LCR")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("NRBC#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("NRBC%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("WBC-BF")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("TC-BF#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MN#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("MN%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("PMN#")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("PMN%")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("RBC-BF")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("ID pacient")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Nomb")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Sexo")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Fecha nacim")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Ed")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Tipo pac")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Grupo ref.")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Dpto")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Nº cama")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Fech extr")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Hora entrega")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Fecha ext")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Hora entrega")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Médico")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Operador")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Validado por")) {
                        logger.info(encabezado + " found....");
                    } else if (encabezado.equalsIgnoreCase("Coments")) {
                        logger.info(encabezado + " found....");
                    }
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date fechaConvertida = null;


                //Create the records
                Iterable<CSVRecord> records = parsed.getRecords();

                //Iterate over the records
                int it = 0;
                for (CSVRecord record : records) {
                     viejos++;
                    // id_muestr = record.get("ID muestr");
                    String[] entries = ((CSVRecord) ((ArrayList) records).get(it)).get(0).split("\t");
                    id_muestr = entries[1].replaceAll("\"", "");
                    id_muestr = id_muestr.replace("\u0000", "");

                    if (id_muestr.matches("^100\\d{4}$")) {
                            Participante par;
                            par = participanteService.getParticipanteByCodigo(id_muestr.substring(3, 7));
                            id_pacient = par.getCodigo();

                            if (!id_pacient.equalsIgnoreCase(null)) {
                                String fecfiltro = "";
                                modo_recue = entries[4].replaceAll("\"", "");
                                modo_recue = modo_recue.replace("\u0000", "");
                                fecfiltro = entries[4].replaceAll("\"", "");
                                fecfiltro = modo_recue.replace("\u0000", "");

                                fec = dateFormat.parse(modo_recue);

                                hora = entries[5].replaceAll("\"", "");
                                hora = hora.replace("\u0000", "");
                                fechaConvertida =  dateFormat.parse(modo_recue);

                                if ((this.hojaClinicaService.getbuscarParticipanteBhc6000(id_muestr.substring(0, 7).toString(), modo_recue, hora).size()==0)) {
                                    //String dat = null;
                                    //dat =  this.hojaClinicaService.getbuscarParticipanteBhc6000Medico(id_muestr.substring(0, 7).toString()).toString();

                                    entidad.setId_muestr(id_muestr);
                                    entidad.setId_pacient(id_pacient);
                                    entidad.setFec(fec);
                                    entidad.setHora(hora);

                                    modo_recue = entries[2].replaceAll("\"", "");
                                    modo_recue = modo_recue.replace("\u0000", "");
                                    entidad.setModo_recue(modo_recue);
                                    try {
                                        modo_recue = entries[3].replaceAll("\"", "");
                                        modo_recue = modo_recue.replace("\u0000", "");
                                        fechaConvertida = (java.sql.Date) dateFormat.parse(modo_recue);
                                    } catch (Exception e) {
                                            System.out.println("Error occurred" + e.getMessage());
                                    }

                                    fec = fechaConvertida;
                                    String text = "";
                                    text = entries[6].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    pos_tubo = text;
                                    entidad.setPos_tubo(pos_tubo);
                                    text = entries[7].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    comunicado = text;
                                    entidad.setComunicado(comunicado);
                                    text = entries[8].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    impreso = text;
                                    entidad.setImpreso("No");
                                    text = entries[9].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                       wbc = Double.parseDouble(text);
                                        entidad.setWbc(wbc);
                                    }
                                    text = entries[10].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        neu = Double.parseDouble(text);
                                        entidad.setNeu(neu);
                                    }
                                    text = entries[11].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        linf = Double.parseDouble(text);
                                        entidad.setLinf(linf);
                                    }
                                    text = entries[12].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mon = Double.parseDouble(text);
                                        entidad.setMon(mon);
                                    }
                                    text = entries[13].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        eos = Double.parseDouble(text);
                                        entidad.setEos(eos);
                                    }
                                    text = entries[14].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        bas = Double.parseDouble(text);
                                        entidad.setBas(bas);
                                    }
                                    text = entries[15].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        img = Double.parseDouble(text);
                                        entidad.setImg(img);
                                    }
                                    text = entries[16].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        neu_porcentaje = Double.parseDouble(text);
                                        entidad.setNeu_porcentaje(neu_porcentaje);
                                    }
                                    text = entries[17].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        linf_porcentaje = Double.parseDouble(text);
                                        entidad.setLinf_porcentaje(linf_porcentaje);
                                    }
                                    text = entries[18].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mon_porcentaje  = Double.parseDouble(text);
                                        entidad.setMon_porcentaje(mon_porcentaje);
                                    }
                                    text = entries[19].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        eos_porcentaje  = Double.parseDouble(text);
                                        entidad.setEos_porcentaje(eos_porcentaje);
                                    }
                                    text = entries[20].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        bas_porcentaje  = Double.parseDouble(text);
                                        entidad.setBas_porcentaje(bas_porcentaje);
                                    }

                                    text = entries[21].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        img_porcentaje  = Double.parseDouble(text);
                                        entidad.setImg_porcentaje(img_porcentaje);
                                    }
                                    text = entries[22].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        rbc  = Double.parseDouble(text);
                                        entidad.setRbc(rbc);
                                    }
                                    text = entries[23].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        hgb  = Double.parseDouble(text);
                                        entidad.setHgb(hgb);
                                    }
                                    text = entries[24].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        hct  = Double.parseDouble(text);
                                        entidad.setHct(hct);
                                    }
                                    text = entries[25].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mcv  = Double.parseDouble(text);
                                        entidad.setMcv(mcv);
                                    }
                                    text = entries[26].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mch  = Double.parseDouble(text);
                                        entidad.setMch(mch);
                                    }
                                    text = entries[27].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mchc  = Double.parseDouble(text);
                                        entidad.setMchc(mchc);
                                    }
                                    text = entries[28].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        rdw_cv  = Double.parseDouble(text);
                                        entidad.setRdw_cv(rdw_cv);
                                    }
                                    text = entries[29].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        rdw_sd  = Double.parseDouble(text);
                                        entidad.setRdw_sd(rdw_sd);
                                    }
                                    text = entries[30].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        plt  = Double.parseDouble(text);
                                        entidad.setPlt(plt);
                                    }
                                    text = entries[31].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mpv  = Double.parseDouble(text);
                                        entidad.setMpv(mpv);
                                    }
                                    text = entries[32].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        pdw  = Double.parseDouble(text);
                                        entidad.setPdw(pdw);
                                    }
                                    text = entries[33].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        pct  = Double.parseDouble(text);
                                        entidad.setPct(pct);
                                    }
                                    text = entries[34].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        p_lcc  = Double.parseDouble(text);
                                        entidad.setP_lcc(p_lcc);
                                    }
                                    text = entries[35].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        p_lcr  = Double.parseDouble(text);
                                        entidad.setP_lcr(p_lcr);
                                    }
                                    text = entries[36].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        nrbc  = Double.parseDouble(text);
                                        entidad.setNrbc(nrbc);
                                    }
                                    text = entries[37].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        nrbc_porcentaje  = Double.parseDouble(text);
                                        entidad.setNrbc_porcentaje(nrbc_porcentaje);
                                    }


                            /*Hasta aqui son 34 campos con información exportada de Bc6000*/
                                    text = entries[38].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        wbc_bf  = Double.parseDouble(text);
                                        entidad.setWbc(wbc_bf);
                                    }
                                    text = entries[39].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        tc_bf  = Double.parseDouble(text);
                                        entidad.setTc_bf(tc_bf);
                                    }
                                    text = entries[40].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mn  = Double.parseDouble(text);
                                        entidad.setMn(mn);
                                    }
                                    text = entries[41].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        mn_porcentaje  = Double.parseDouble(text);
                                        entidad.setMn_porcentaje(mn_porcentaje);
                                    }
                                    text = entries[42].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        pmn  = Double.parseDouble(text);
                                        entidad.setPmn(pmn);
                                    }
                                    text = entries[43].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        pmn_porcentaje  = Double.parseDouble(text);
                                        entidad.setPmn_porcentaje(pmn_porcentaje);
                                    }
                                    text = entries[44].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        rbc_bf  = Double.parseDouble(text);
                                        entidad.setRbc_bf(rbc_bf);
                                    }
                                    text = entries[45].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                        nomb  = (text);
                                        if (nomb.isEmpty()){
                                           nomb = par.getNombreCompleto();
                                        }
                                    entidad.setNomb(nomb);
                                    text = entries[46].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                        sexo  = (text);
                                    if (sexo.isEmpty()){
                                        sexo = par.getSexo();
                                    }
                                    entidad.setSexo(sexo);

                            try {
                                text = entries[47].replaceAll("\"", "");
                                text = text.replace("\u0000", "");

                                if (!text.isEmpty()) {
                                    fecha_nacim = (java.sql.Date) dateFormat.parse(text);

                                    entidad.setFecha_nacim(fecha_nacim);
                                }
                                fecha_nacim = (java.sql.Date) dateFormat.parse(text);

                                entidad.setFecha_nacim(fecha_nacim);
                               // entidad.setFecha_nacim(f);

                            } catch (Exception e) {
                                System.out.println("Error occurred" + e.getMessage());
                            }
                                    text = entries[48].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    if (!text.isEmpty()) {
                                        edad  = Double.parseDouble(text);
                                        entidad.setEdad(edad);
                                    }
                                    text = entries[49].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    tipo_pac  = (text);
                                    entidad.setTipo_pac(tipo_pac);
                                    text = entries[50].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    grupo_ref  = (text);
                                    entidad.setGrupo_ref(grupo_ref);
                                    text = entries[51].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    dpto  = (text);
                                    entidad.setDpto(dpto);
                                    text = entries[52].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    num_cama  = (text);
                                    entidad.setNum_cama(num_cama);

                            try {
                                text = entries[53].replaceAll("\"", "");
                                text = text.replace("\u0000", "");
                                if (!text.isEmpty()) {
                                    fecha_extr = (java.sql.Date) dateFormat.parse(text);
                                    entidad.setFecha_extr(fecha_extr);
                                }
                            } catch (Exception e) {
                                System.out.println("Error occurred" + e.getMessage());
                            }
                                    text = entries[54].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    hora_extr  = (text);
                                    entidad.setHora_extr(hora_extr);
                            try {
                                text = entries[55].replaceAll("\"", "");
                                text = text.replace("\u0000", "");
                                if (!text.isEmpty()) {
                                    fecha_ext = (java.sql.Date) dateFormat.parse(text);
                                    entidad.setFecha_ext(fecha_ext);
                                }
                            } catch (Exception e) {
                                System.out.println("Error occurred" + e.getMessage());
                            }
                                    text = entries[56].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    hora_entrega  = (text);
                                    entidad.setHora_entrega(hora_entrega);
                                    text = entries[57].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    medico  = this.hojaClinicaService.getbuscarParticipanteBhc6000Medico(id_muestr.substring(0, 7).toString()).toString();
                                    entidad.setMedico(medico);
                                    text = entries[58].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    operador  = (text);
                                    entidad.setOperador(operador);
                                    text = entries[59].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    validado_por  = (text);
                                    entidad.setValidado_por(validado_por);
                                    text = entries[60].replaceAll("\"", "");
                                    text = text.replace("\u0000", "");
                                    coments  = (text);
                                    entidad.setComents(coments);
                                    entidad.setEstado('1');
                                    entidad.setPasive('0');
                                    entidad.setRecordDate(new Date());
                                    entidad.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                                    entidades.add(entidad);
                                    this.hojaClinicaService.saveBhc6000(entidad);

                                    entidad = new Bhc_Bc6000();
                                    nuevos++;

                                }
                            }
                    }
                    it++;
                }
                modelMap.addAttribute("entidades", entidades);
                modelMap.addAttribute("nuevos", nuevos);
                modelMap.addAttribute("viejos", viejos);
                map.put("nuevos", String.valueOf(nuevos));
            } catch (IllegalArgumentException ile) {
                logger.error(ile.getLocalizedMessage());
                modelMap.addAttribute("importError", true);
                modelMap.addAttribute("errorMessage", ile.getLocalizedMessage());
                return "Serologia/UploadResult";
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                modelMap.addAttribute("entidades", entidades);
                modelMap.addAttribute("nuevos", nuevos);
                modelMap.addAttribute("viejos", viejos);
                modelMap.addAttribute("importError", true);
                modelMap.addAttribute("errorMessage", e.getLocalizedMessage());
                return "Serologia/UploadResult";
            }
        }catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            modelMap.addAttribute("importError", true);
            modelMap.addAttribute("errorMessage", e.getLocalizedMessage());
            return "Serologia/UploadResult";
        }
        modelMap.addAttribute("entidades", entidades);
        modelMap.addAttribute("nuevos", nuevos);
        return "Serologia/UploadResult";
}
}
