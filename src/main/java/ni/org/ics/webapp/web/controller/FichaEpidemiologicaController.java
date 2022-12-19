package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.HojaClinicaDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
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

/**
 * Created by Everts on 31/10/2022.
 */
@Controller
@RequestMapping("/fichaEpidemiologica/*")
public class FichaEpidemiologicaController {

   /* private static final Logger logger = LoggerFactory.getLogger(FichaEpidemiologicaController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "personalService")
    private PersonalService personalService;

    @Resource(name = "fichaEpidemiologicaService")
    private FichaEpidemiologicaService fichaEpidemiologicaService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando fichas epidemiologicas en JSP");
        return "fingering/fichaEpidemiologicaSheetList";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) throws ParseException {
        logger.debug("Mostrando formulario registro fichas epidemiologicas en JSP");
        List<MessageResource> catSiNo = this.messageResourceService.getCatalogo("CAT_SINO");
        List<MessageResource> catSexo = this.messageResourceService.getCatalogo("CAT_SEXO");
        List<MessageResource> catCategoria = this.messageResourceService.getCatalogo("CAT_CATEGORIA");
        List<MessageResource> catTipoConsulta = this.messageResourceService.getCatalogo("CAT_TIPO_CONSULTA");
        List<MessageResource> catLugarConsulta = this.messageResourceService.getCatalogo("CAT_LUGAR_CONS_HC");
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
        return "fingering/fichaEpidemiologicaSheet";
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) String codigo) throws ParseException {
        logger.debug("buscar participante");
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            if (procesos != null && procesos.getRetirado().equals(1))
                return JsonUtil.createJsonResponse("Participante retirado");
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
        logger.debug("Guardando ficha epidemiologica");
        String resultado = "";
        String error = "";
        try {
            FichaEpidemiologica fichaEpidemiologica = ParseJsonRequestToHojaClinica(request);
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

    private fichaEpidemiologica ParseJsonRequestToFichaEpidemiologica(HttpServletRequest request) throws Exception{
        FichaEpidemiologica fichaEpidemiologica = new FichaEpidemiologica();
        String json = "";

          Date fechaConsulta = null;
          Time horaConsulta = null;

        //Datos Generales

          String categoria = null;
          String inicial = null;
          String convaleciente = null;
          String silais = null;
          String municipio = null;
          String unidad_de_salud = null;
          int nro_Expediente = null;

        //Datos Personales

          String ocupacion = null;
          String telefonos = null;

        //Datos Clinicos y de Laboratorio

          Date fis = null;
          Date fif = null;
          Date ftm = null;
          BigDecimal temperaturac = null;
          String presion = null;
          String medico = null;
          String codMedico = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String hojaStr = jObjectRespuestas.get("fichaEpidemiologica").toString();
        String codigoParticipante = jObjectRespuestas.get("participante").getAsString();

        Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
        fichaEpidemiologica.setCodigoParticipante(participante);

        JsonObject jsonpObject = new Gson().fromJson(hojaStr, JsonObject.class);

        if (jsonpObject.get("fechaCons")!=null && !jsonpObject.get("fechaCons").getAsString().isEmpty()) fechaConsulta = DateUtil.StringToDate(jsonpObject.get("fechaCons").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("horaCons")!=null && !jsonpObject.get("horaCons").getAsString().isEmpty()) horaConsulta = Time.valueOf(DateUtil.onTimeSet(jsonpObject.get("horaCons").getAsString()));
        if (jsonpObject.get("categoria")!=null && !jsonpObject.get("categoria").getAsString().isEmpty()) categoria = (jsonpObject.get("categoria").getAsString());
        if (jsonpObject.get("inicial")!=null && !jsonpObject.get("inicial").getAsString().isEmpty()) inicial = (jsonpObject.get("inicial").getAsString());
        if (jsonpObject.get("convaleciente")!=null && !jsonpObject.get("convaleciente").getAsString().isEmpty()) convaleciente = (jsonpObject.get("convaleciente").getAsString());
        if (jsonpObject.get("silais")!=null && !jsonpObject.get("silais").getAsString().isEmpty()) silais = (jsonpObject.get("silais").getAsString());
        if (jsonpObject.get("municipio")!=null && !jsonpObject.get("municipio").getAsString().isEmpty()) municipio = (jsonpObject.get("municipio").getAsString());
        if (jsonpObject.get("unidad_de_salud")!=null && !jsonpObject.get("unidad_de_salud").getAsString().isEmpty()) unidad_de_salud = (jsonpObject.get("unidad_de_salud").getAsString());
        if (jsonpObject.get("nro_Expediente")!=null && !jsonpObject.get("nro_Expediente").getAsString().isEmpty()) nro_Expediente = jsonpObject.get("nro_Expediente").getAsBigDecimal();
        if (jsonpObject.get("ocupacion")!=null && !jsonpObject.get("ocupacion").getAsString().isEmpty()) ocupacion = (jsonpObject.get("ocupacion").getAsString());
        if (jsonpObject.get("telefonos")!=null && !jsonpObject.get("telefonos").getAsString().isEmpty()) telefonos = (jsonpObject.get("telefonos").getAsString());
        if (jsonpObject.get("fis")!=null && !jsonpObject.get("fis").getAsString().isEmpty()) fis = DateUtil.StringToDate(jsonpObject.get("fis").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("fif")!=null && !jsonpObject.get("fif").getAsString().isEmpty()) fif = DateUtil.StringToDate(jsonpObject.get("fif").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("ftm")!=null && !jsonpObject.get("ftm").getAsString().isEmpty()) ftm = DateUtil.StringToDate(jsonpObject.get("ftm").getAsString(), "dd/MM/yyyy");
        if (jsonpObject.get("temp")!=null && !jsonpObject.get("temp").getAsString().isEmpty()) temperaturac = jsonpObject.get("temp").getAsBigDecimal();
        if (jsonpObject.get("pa")!=null && !jsonpObject.get("pa").getAsString().isEmpty()) presion = jsonpObject.get("pa").getAsString();
        if (jsonpObject.get("medico")!=null && !jsonpObject.get("medico").getAsString().isEmpty()) medico = jsonpObject.get("medico").getAsShort();
        if (jsonpObject.get("codMedico")!=null && !jsonpObject.get("codMedico").getAsString().isEmpty()) codMedico = jsonpObject.get("codMedico").getAsShort();



        fichaEpidemiologica.setFechaConsulta(fechaConsulta);
        fichaEpidemiologica.setHoraConsulta(horaConsulta);
        fichaEpidemiologica.setCategoria(categoria);
        fichaEpidemiologica.setInicial(inicial);
        fichaEpidemiologica.setConvaleciente(convaleciente);
        fichaEpidemiologica.setSilais(silais);
        fichaEpidemiologica.setMunicipio(municipio);
        fichaEpidemiologica.setUnidad_de_salud(unidad_de_salud);
        fichaEpidemiologica.setOcupacion(ocupacion);
        fichaEpidemiologica.setTelefonos(telefonos);
        fichaEpidemiologica.setFis(fis);
        fichaEpidemiologica.setFif(fif);
        fichaEpidemiologica.setFtm(ftm);
        fichaEpidemiologica.setTemperaturac(temperaturac);
        fichaEpidemiologica.setPresion(presion);
        fichaEpidemiologica.setMedico(medico);
        fichaEpidemiologica.setCodMedico(codMedico);


        return fichaEpidemiologica;
    }*/
}
