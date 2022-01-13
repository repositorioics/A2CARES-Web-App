package ni.org.ics.webapp.web.controller.Retiro;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.*;
import ni.org.ics.webapp.domain.Retiros.Retiros;
import ni.org.ics.webapp.domain.catalogs.Razones_Retiro;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.ParticipanteSeroDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.Retiro.RetiroService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.lang.String;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 29/10/2020.
 */

@Controller
@RequestMapping("/retiro")
public class RetiroController {

    private static final Logger logger = LoggerFactory.getLogger(RetiroController.class);

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "RetiroService")
    private RetiroService retiroservice;


    /* Buscar Listado por Codigo Participante /retiro/ListRetiro */
    @RequestMapping(value = "/ListRetiro", method = RequestMethod.GET)
    public ModelAndView ListRetiro()throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Retiro/ListaRetiro");
        return modelAndView;
    }

    //Obtener la lista de retiro por codigo participante
    @RequestMapping(value = "/ListaHojaRetiro", method = RequestMethod.GET, produces ="application/json")
    public @ResponseBody
    List<Retiros> ListaHojaRetiro(@RequestParam(value = "parametro", required=true ) String parametro)
            throws Exception{
        List<Retiros> lista = null;
        try {
            lista = this.retiroservice.getListadoRetiroByID(parametro);
            return lista;
        } catch (Exception e) {
            return  lista = null;
        }
    }

    //retiro/searchParticipant
    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="parametro", required=true ) String parametro) throws ParseException {
        try {
            ParticipanteSeroDto participante = retiroservice.getDatosParticipanteByCodigo(parametro);
            Participante participante2 = this.retiroservice.getParticipante(parametro);
            String NombreTutor ="";
            if (participante2 != null){
                NombreTutor = participante2.getTutor();
            }
            if (participante != null) {
                // nombre del tutor
                participante.setNombretutor(NombreTutor.toUpperCase());

                //nombre del participante
                String nombrecompleto = participante2.getNombre1().toUpperCase();

                if (participante2.getNombre2() != null)
                    nombrecompleto = nombrecompleto +" "+ participante2.getNombre2().toUpperCase();
                    nombrecompleto = nombrecompleto +" "+ participante2.getApellido1().toUpperCase();
                if (participante2.getApellido2() != null)
                    nombrecompleto = nombrecompleto +" "+ participante2.getApellido2().toUpperCase();
                participante.setNombreCompleto(nombrecompleto);

                // nombre de la Madre
                String madre = participante2.getNombre1Madre().toUpperCase();
                if (participante2.getNombre2Madre() != null)
                    madre = madre + " " + participante2.getNombre2Madre().toUpperCase();
                madre = madre + " " + participante2.getApellido1Madre().toUpperCase();
                if (participante2.getApellido2Madre() != null)
                    madre = madre + " " + participante2.getApellido2Madre().toUpperCase();
                participante.setNombremadre(madre);

                // nombre del padre el participante
                String padre = participante2.getNombre1Padre().toUpperCase();
                if (participante2.getNombre2Padre() != null)
                    padre = padre + " " + participante2.getNombre2Padre().toUpperCase();
                padre = padre + " " + participante2.getApellido1Padre().toUpperCase();
                if (participante2.getApellido2Padre() != null)
                    padre = padre + " " + participante2.getApellido2Padre().toUpperCase();
                participante.setNombrepadre(padre);

                participante.setEdadParticipante(participante2.getEdad());

                if (participante.getEstado().equals(1))
                    return JsonUtil.createJsonResponse("Participante retirado");
            } else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            return JsonUtil.createJsonResponse(participante);
        }catch (Exception e){
            return JsonUtil.createJsonResponse(e.getMessage());
        }
    }

    //region Guardar Retiros
    @RequestMapping(value = "saveRetiroForm", method = RequestMethod.GET)
    public String saveRetiroForm(Model model) throws Exception {
        try{
            List<MessageResource> parentesco = messageResourceService.getCatalogo("CAT_RF_TUTOR");
            model.addAttribute("parentesco", parentesco);
            List<MessageResource> causaRetiro = messageResourceService.getCatalogo("CAT_CAUSAS_RETIROS");
            model.addAttribute("causaRetiro",causaRetiro);
            List<Personal> supervisor = this.retiroservice.getSupervisor();
            model.addAttribute("supervisor", supervisor);
            List<Personal> supervisorYdigitador = this.retiroservice.getSupervisorAndDigitador();
            model.addAttribute("supervisorYdigitador", supervisorYdigitador);
            model.addAttribute("estudios", "");
            return "/Retiro/RetiroForm";
        }
        catch (Exception e){
            return "404";
        }
    }

    //Obtener motivos
    @RequestMapping(value = "/getMotivo", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String getMotivo(@RequestParam(value = "razonretiro") Integer razonretiro, Model model)  throws Exception {
        List<Razones_Retiro> razonList = null;
        try{
           razonList = this.retiroservice.getRazonesRetiros(razonretiro);
             model.addAttribute("razonList", razonList);
            String jsonResponse;
            jsonResponse = new Gson().toJson(model);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //Guardar Retiro
    @RequestMapping( value="GuardarRetiro", method=RequestMethod.POST)
    public ResponseEntity<String> saveCaseCovid( @RequestParam(value="codigoParticipante", required=false, defaultValue="" ) String codigoParticipante
            ,@RequestParam(value="codigo_casa", required=false, defaultValue="" ) String codigo_casa
            ,@RequestParam(value="fehaRetiro", required=false, defaultValue="" ) String fehaRetiro
            ,@RequestParam(value="medicosupervisor", required=false, defaultValue="" ) String medicosupervisor
            ,@RequestParam(value="recibidaPor", required=false, defaultValue="" ) String recibidaPor
            ,@RequestParam(value="observacion", required=false, defaultValue="" ) String observacion
            ,@RequestParam(value="otromotivo", required=false, defaultValue="" ) String otromotivo
            ,@RequestParam(value="parentesco", required=false, defaultValue="" ) String parentesco
            ,@RequestParam(value="quiencomunica", required=false, defaultValue="" ) String quiencomunica
            ,@RequestParam(value="fechaFallecido", required=false, defaultValue="" ) String fechaFallecido
            ,@RequestParam(value="devolvioCarnet", required=false, defaultValue="" ) String devolvioCarnet
            ,@RequestParam(value="razonretiro", required=false, defaultValue="" ) String razonretiro

    )throws Exception{
        try{
            Integer codigo = Integer.parseInt(codigoParticipante);
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
            Retiros obj = new Retiros();
            Participante p = new Participante();
            p.setCodigo(codigoParticipante);
            obj.setParticipante(p);
            Integer kpediatrica = Integer.parseInt(codigo_casa);
            obj.setCodigo_casa(kpediatrica);
            obj.setFecha_retiro(DateUtil.StringToDate(fehaRetiro, "dd/MM/yyyy"));
            if(!fechaFallecido.equals("")){
                obj.setFecha_fallecido(DateUtil.StringToDate(fechaFallecido,"dd/MM/yyyy"));
            }
            Integer supervisor = Integer.parseInt(medicosupervisor);
            obj.setMedico_supervisor(supervisor);
            String comunicador = quiencomunica.equals("") ? "-" : quiencomunica;
            obj.setQuien_comunica(comunicador.toUpperCase());
            Integer relfamiliar = 0;
            if (parentesco.equals("")){
                obj.setRelfam(relfamiliar);
            }else{
                Integer parent = Integer.parseInt(parentesco);
                obj.setRelfam(parent);
            }
            Integer persona = null;
            if (recibidaPor.equals("")){

            }else {
                persona = Integer.parseInt(recibidaPor);
            }
            obj.setPersona_documenta(persona);
            obj.setMotivo(razonretiro);
            Character c = devolvioCarnet.equals("on") ? '1' : '0';
            obj.setDevolvio_carnet(c);
            obj.setObservaciones(observacion.toUpperCase());
            obj.setOtros_motivo(otromotivo.toUpperCase());
            obj.setEstado('1');
            obj.setPasive('0');
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            obj.setDeviceid(computerName);
            obj.setRecordIp(computerIp);
            obj.setRecordDate(new Date());
            obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            procesos.setRetirado(1); // Actualizo en tabla participantes_procesos el campo Retirado
            this.participanteProcesosService.saveOrUpdateParticipanteProc(procesos);
            this.retiroservice.SaveRetiros(obj);
            return JsonUtil.createJsonResponse(obj);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    List<MessageResource> messageRelFam = new ArrayList<MessageResource>();
    @RequestMapping(value = "/DetallesRetiro", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String DetallesRetiro(@RequestParam(value = "idretiro") Integer idretiro)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Object> model = new HashMap<String, Object>();
        Retiros obj = null;
        Personal test = new Personal();
        try {
            obj = this.retiroservice.getRetiroByID(idretiro);
            Integer idsupervisor = obj.getMedico_supervisor();
            test = this.retiroservice.getSupervisorById(idsupervisor);
            map.put("supervisor",test.getNombre());
            Razones_Retiro motivoDetalle = this.retiroservice.getRazonRetiro(obj.getMotivo());
            map.put("motivoDetalle", motivoDetalle.getDescripcion());
            Personal objPersonalDocumenta = this.retiroservice.getSupervisorById(obj.getPersona_documenta());
            map.put("persona_documenta",objPersonalDocumenta.getNombre());
            if (obj.getCodigo_casa() == null){
                Integer numerocasa = 0;
                map.put("casapediatrica", numerocasa.toString());
            }else{
                String codigo_casa = ""+obj.getCodigo_casa();
                map.put("codigo_casa", codigo_casa );
            }
            map.put("otrosmotivo", (obj.getOtros_motivo() != null ? obj.getOtros_motivo() : "-") );
            map.put("observacion", obj.getObservaciones() != null ? obj.getObservaciones() : "-");
            map.put("relfamId", obj.getRelfam().toString());
            map.put("quiencomunica",obj.getQuien_comunica());
            String resultadoCarnet =  (obj.getDevolvio_carnet() == '0') ? "No":"Si";
            map.put("carnet", resultadoCarnet);
            messageRelFam = messageResourceService.getMensajeByCatalogAndCatKeys(""+obj.getRelfam(),"CAT_RF_TUTOR");
            map.put("relFam",getDescripcionCatalogo(""+obj.getRelfam(),"CAT_RF_TUTOR"));
            map.put("motivo", obj.getMotivo());


            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);


        } catch (Exception e) {
            String mensaje = e.getMessage();
            map.put("mensaje", mensaje);
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }
    }

    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messageRelFam){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

    private ResponseEntity<String> JsonUtilcreateJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }

    private ResponseEntity<String> createJsonResponse( Object o ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>(json, headers, HttpStatus.CREATED);
    }

}
