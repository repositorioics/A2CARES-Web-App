package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.dto.FiltroMxEnfermoDto;
import ni.org.ics.webapp.dto.RecepcionEnfermoDto;
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
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;

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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando lista de mx de enfermo para envio del dia de hoy en JSP");

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);

        return "mxEnfermos/list";
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

            RecepcionEnfermo recepcionEnfermo = this.recepcionEnfermoService.getRecepcionEnfermoById(idRecepcion);
            if (recepcionEnfermo.getParticipante()!=null){
                RecepcionEnfermoDto recepcionEnfermoDto = new RecepcionEnfermoDto();
                recepcionEnfermoDto.setIdRecepcion(recepcionEnfermo.getIdRecepcion());
                recepcionEnfermoDto.setParticipante(recepcionEnfermo.getParticipante().getCodigo());
                recepcionEnfermoDto.setNombreCompleto(recepcionEnfermo.getParticipante().getNombreCompleto());
                recepcionEnfermoDto.setFechaRecepcion(DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY));
                recepcionEnfermoDto.setFis(DateUtil.DateToString(recepcionEnfermo.getFis(), Constants.STRING_FORMAT_DD_MM_YYYY));
                recepcionEnfermoDto.setFif(DateUtil.DateToString(recepcionEnfermo.getFif(), Constants.STRING_FORMAT_DD_MM_YYYY));
                recepcionEnfermoDto.setCategoria(recepcionEnfermo.getCategoria());
                recepcionEnfermoDto.setTipoMuestra(recepcionEnfermo.getTipoMuestra());
                recepcionEnfermoDto.setConsulta(recepcionEnfermo.getConsulta());
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
        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            if (procesos != null && procesos.getRetirado().equals(1))
                return JsonUtil.createJsonResponse("Participante retirado");
            else {
                map.put("codigo", participante.getCodigo());
                map.put("nombreCompleto", participante.getNombreCompleto());
                map.put("casa", participante.getCasa().getCodigo().toString());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), Constants.STRING_FORMAT_DD_MM_YYYY));
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
            ,@RequestParam( value = "observacion",    required = false, defaultValue=""  ) String observacion
            ,@RequestParam( value = "tiporequest",    required = false, defaultValue=""  ) String tiporequest
    ) throws Exception {
        try{
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
            RecepcionEnfermo recepcionEnfermo = new RecepcionEnfermo();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            String ComputerIp = InetAddress.getLocalHost().getHostAddress();
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
                    String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                    recepcionEnfermo.setObservacion(obs);
                    recepcionEnfermo.setVolumen(Double.parseDouble(volumen));

                    //setear codigo a imprimir
                    String anio = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), "YY");
                    String fToma = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY);
                    String codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), anio, recepcionEnfermo.getCategoria(), recepcionEnfermo.getTipoMuestra());

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
                recepcionEnfermo.setTipoMuestra(faseMuestra);
                //setear codigo a imprimir
                String anio = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), "YY");
                String fToma = DateUtil.DateToString(recepcionEnfermo.getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY);
                String codigoMx = String.format(Constants.CODIGO_MX_FORMAT, recepcionEnfermo.getParticipante().getCodigo(), recepcionEnfermo.getTipoTubo(), anio, recepcionEnfermo.getCategoria(), recepcionEnfermo.getTipoMuestra());

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
