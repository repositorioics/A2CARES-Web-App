package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.ParticipanteService;
import ni.org.ics.webapp.service.RecepcionEnfermoService;
import ni.org.ics.webapp.service.PrintStickersService;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by miguel on 1/2/2022.
 */
@Controller
@RequestMapping("/ps/stickers/*")
public class PrintStickersController {

    private static final Logger logger = LoggerFactory.getLogger(ComparacionController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "printstickersservice")
    private PrintStickersService printstickersservice;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando menu printStickjer en JSP");

        return "/admin/printStickers/list";
    }

    @RequestMapping(value = "nuevosStickers", method = RequestMethod.GET)
    public String nuevosStickers(Model model) throws ParseException {
        logger.debug("Mostrando pagina nuevosStickers en JSP");

        return "/admin/printStickers/cod_nuevo_enrolamiento";
    }
    @RequestMapping(value = "CasaMuestreo", method = RequestMethod.GET)
    public String CasaMuestreo(Model model) throws ParseException {
        logger.debug("Mostrando pagina CasaMuestreo en JSP");

        return "/admin/printStickers/cod_muestreo_anual";
    }

    @RequestMapping(value = "ReimpresionCodigos", method = RequestMethod.GET)
    public String ReimpresionCodigos(Model model) throws ParseException {
        logger.debug("Mostrando pagina CasaMuestreo en JSP");

        return "/admin/printStickers/cod_nuevo_enrolamiento_individual";
    }

    @RequestMapping(value = "getControlSecCodigos", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody ResponseEntity<MuestrasEnfermosDto> getTablasMxEnfermos(
    public @ResponseBody List<ControlSecCodigosDto> getControlSecCodigos(
            @RequestParam(value="titulo_imp", required=false ) String titulo_imp)
            throws ParseException {

        List<ControlSecCodigosDto> printStick = null;
        LocalDate localDate = LocalDate.now();
        try {

            printStick = this.printstickersservice.getControlSecCodigos();
            //  return JsonUtil.createJsonResponse(enfermeria);
            return printStick;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getCasaMuestreo", method = RequestMethod.GET, produces = "application/json")
   // public @ResponseBody
    public @ResponseBody List<CasasExistMuestreoDto> getCasaMuestreo( @RequestParam(value="codigo_casa", required=false ) String codigo_casa)
            throws ParseException {

        List<CasasExistMuestreoDto> printStick = null;
        LocalDate localDate = LocalDate.now();
        try {
           if (codigo_casa != "") {
               printStick = this.printstickersservice.getExtraeCasaMuestreo(codigo_casa);
               //  return JsonUtil.createJsonResponse(enfermeria);

                  return printStick;

           } else {
               return null;
           }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getCasaMuestreo1", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody
    public @ResponseBody List<String> getCasaMuestreo1( @RequestParam(value="codigo_casa", required=false ) String codigo_casa)
            throws ParseException {

        List<String> printStick = null;
        LocalDate localDate = LocalDate.now();
        try {
            if (codigo_casa != "") {
                printStick = this.printstickersservice.getExtraeCasaMuestreo1(codigo_casa);
                //  return JsonUtil.createJsonResponse(enfermeria);

                return printStick;

            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "setIncrementarPart", method = RequestMethod.GET, produces = "application/json")
    // public @ResponseBody
    public @ResponseBody List<ControlSecCodigosDto> setIncrementarPart( )
            throws ParseException {

        List<ControlSecCodigosDto> printStick = null;
        LocalDate localDate = LocalDate.now();
        try {

                printStick = printstickersservice.setIncCodPart();
                //  return JsonUtil.createJsonResponse(enfermeria);

               return printStick;


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }


    @RequestMapping(value = "setIncrementarCasa", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ControlSecCodigosDto> setIncrementarCasa( )
            throws ParseException {

        List<ControlSecCodigosDto> printStick = null;
        LocalDate localDate = LocalDate.now();
        try {

            printStick = printstickersservice.setIncCodCasa();
                //  return JsonUtil.createJsonResponse(enfermeria);

            return printStick;


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }

}
