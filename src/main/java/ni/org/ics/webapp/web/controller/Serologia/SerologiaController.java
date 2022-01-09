package ni.org.ics.webapp.web.controller.Serologia;

import com.google.gson.Gson;

import ni.org.ics.webapp.domain.Serologia.Serologia;
import ni.org.ics.webapp.domain.Serologia.SerologiaEnvio;
import ni.org.ics.webapp.domain.Serologia.Serologia_Detalles_Envio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.ParticipanteSeroDto;
import ni.org.ics.webapp.dto.SerologiaDto;
import ni.org.ics.webapp.dto.SerologiaEnviarDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.ParticipanteService;
import ni.org.ics.webapp.service.Serologia.SerologiaService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 15/10/2020.
 */

@Controller
@RequestMapping("/Serologia")
public class SerologiaController {
    private static final Logger logger = LoggerFactory.getLogger(SerologiaController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaService serologiaService;

    //region Formulario Serologia
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(ModelMap model) throws Exception{
        try {
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new ParticipanteSeroDto());
            return "/Serologia/SerologiaForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }
    //endregion

    //region Ir Formulario para Editar
    @RequestMapping(value = "/editMuestra/{idSerologia}", method = RequestMethod.GET)
    public String editMuestra(Model model, @PathVariable("idSerologia") String idSerologia) throws Exception
    {
        try{
            Serologia caso = this.serologiaService.getSerologiaById(idSerologia);
            ParticipanteSeroDto participanteSeroDto = new ParticipanteSeroDto();
            if (caso.getParticipante()!=null){
                Participante participante = this.participanteService.getParticipanteByCodigo(caso.getParticipante());
                if (participante != null){
                    participanteSeroDto.setIdSerologia(caso.getIdSerologia());
                    participanteSeroDto.setEdadMeses(caso.getEdadMeses());
                    participanteSeroDto.setFechaNacimiento(participante.getFechaNac());
                    participanteSeroDto.setIdparticipante(caso.getParticipante());
                    participanteSeroDto.setNombreCompleto(participante.getNombreCompleto());
                    participanteSeroDto.setCodigo_casa(caso.getCodigo_casa());
                    String string;
                    string = participante.getEdad();
                    String[] parts = string.split("/");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    participanteSeroDto.setEdad_year(part1);
                    participanteSeroDto.setEdad_meses(part2);
                    participanteSeroDto.setEdad_dias(part3);
                    participanteSeroDto.setFecha(caso.getFecha());
                    participanteSeroDto.setVolumen(caso.getVolumen());
                    participanteSeroDto.setObservacion(caso.getObservacion());
                }else{
                    participanteSeroDto.setIdSerologia(caso.getIdSerologia());
                    participanteSeroDto.setEdadMeses(caso.getEdadMeses());
                    participanteSeroDto.setFechaNacimiento(new Date());
                    participanteSeroDto.setIdparticipante(caso.getParticipante());
                    participanteSeroDto.setNombreCompleto("-");
                    participanteSeroDto.setCodigo_casa(0);
                    participanteSeroDto.setEdad_year("0");
                    participanteSeroDto.setEdad_meses("0");
                    participanteSeroDto.setEdad_dias("0");
                    participanteSeroDto.setFecha(caso.getFecha());
                    participanteSeroDto.setVolumen(caso.getVolumen());
                    participanteSeroDto.setObservacion(caso.getObservacion());
                }
                model.addAttribute("caso", participanteSeroDto);
                model.addAttribute("agregando",false);
                model.addAttribute("editando",true);
                return "/Serologia/SerologiaForm";
            }else{
                return "404";
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "404";
        }
    }
    //endregion

    //region Enviar Muestra dando Click al boton enviar /Serologia/enviarMuestra
    @RequestMapping(value = "/enviarMuestra", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> enviarMuestra(@RequestBody SerologiaEnviarDto muestraForEnvio)throws Exception{
        try{
            SerologiaEnvio objToSave = new SerologiaEnvio();
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            objToSave.setDeviceid(computerName);
            objToSave.setRecordIp(computerIp);
            objToSave.setEstado('1');
            objToSave.setPasive('0');
            objToSave.setRecordDate(new Date());
            objToSave.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            objToSave.setFecha(DateUtil.StringToDate(muestraForEnvio.getFechaenvio(), "dd/MM/yyyy"));
            objToSave.setHora(muestraForEnvio.getHora());
            objToSave.setIdenvio(muestraForEnvio.getNenvios());
            String idMxSerologica = muestraForEnvio.getIdserologia().toString();
            Serologia objSerologia = this.serologiaService.getSerologiaById(idMxSerologica);
            objSerologia.setIdSerologia(objSerologia.getIdSerologia());
            this.serologiaService.ModificarEnvio(muestraForEnvio.getIdserologia());//Modifica campo Cerrado
            return JsonUtil.createJsonResponse(objToSave);
        }catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    @RequestMapping(value = "/sendAllSerologias", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> sendAllSerologias(@RequestParam(value="numenvio", required=false ) Integer numenvio
            ,@RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="fechaEnvio", required=false ) String fechaEnvio
            ,@RequestParam(value="horaEnvio", required=false ) String horaEnvio
            ,@RequestParam(value="sitio", required=true ) Integer sitio
            ,@RequestParam(value="temperatura", required=true ) String temperatura
    )throws Exception{
        try{
            Date fdesde = DateUtil.StringToDate(desde +  " 00:00:00","dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            SerologiaEnvio envio = new SerologiaEnvio();
            envio.setDeviceid(computerName);
            envio.setRecordIp(computerIp);
            envio.setEstado('1');
            envio.setPasive('0');
            envio.setRecordDate(new Date());
            envio.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            envio.setFecha(DateUtil.StringToDate(fechaEnvio, "dd/MM/yyyy"));
            envio.setHora(horaEnvio);
            envio.setIdenvio(numenvio);
            envio.setSitio(sitio);
            double temp = Double.parseDouble(temperatura);
            envio.setTemperatura(temp);
            this.serologiaService.save_Envio_Serologia(envio);// aqui guardo los datos del envio
            List<Serologia> ListaSerologiaYaEnviadas = this.serologiaService.ObtenerSerologiasEnviadas(fdesde,fhasta);// Serologia en pasivo='0'  y cerrado='0'

            if (envio.getIdserologiaenvio()!=null){
            if (ListaSerologiaYaEnviadas.size()>0){
                for (Serologia obj: ListaSerologiaYaEnviadas){
                    Serologia_Detalles_Envio detalles_envio= new Serologia_Detalles_Envio();
                    this.serologiaService.ModificarEnvio(obj.getIdSerologia());
                    Serologia objSerologia = this.serologiaService.getSerologiaById(obj.getIdSerologia().toString());
                    detalles_envio.setSerologia(objSerologia);
                    detalles_envio.setSerologiaEnvio(envio);
                    this.serologiaService.save_Detalles_Serologia_Envio(detalles_envio);// guardo el detalle del envio
                }

            }else {
                return JsonUtil.createJsonResponse("Registros enviados: "+ListaSerologiaYaEnviadas.size());
            }//fin if getIdserologiaenvio

            }
            return JsonUtil.createJsonResponse("Registros Enviados: "+ListaSerologiaYaEnviadas.size());
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }




    //region Buscar Participante
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) String parametro)throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            ParticipanteSeroDto participanteDto = new ParticipanteSeroDto();
            Participante p = this.participanteService.getParticipanteByCodigo(parametro);
            if (p==null){
                participanteDto.setCodigo(parametro);
                participanteDto.setNombreCompleto("-");
                participanteDto.setCodigo_casa(0);
                participanteDto.setEdad_year("0");
                participanteDto.setEdad_meses("0");
                participanteDto.setEdad_dias("0");
                participanteDto.setObservacion("");
                participanteDto.setFechaNacimiento(new Date());
            }else{
                ParticipanteProcesos procesos = this.serologiaService.getParticipanteprocesos(p.getCodigo());
                if (procesos==null)
                    return JsonUtil.createJsonResponse("No se encontraron Processos del participante!");

                if (procesos.getEstado()==1)
                    return JsonUtil.createJsonResponse("Participante retirado");

                String nombres = p.getNombre1().toUpperCase();
                nombres += (p.getNombre2() != null) ? " "+p.getNombre2().toUpperCase() : "";
                String apellidos = p.getApellido1().toUpperCase();
                apellidos += (p.getApellido2() != null) ? " "+p.getApellido2().toUpperCase() : "";
                participanteDto.setNombreCompleto(nombres +" "+ apellidos);
                String string;
                string = p.getEdad();
                String[] parts = string.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];
                participanteDto.setEdad_year(part1);
                participanteDto.setEdad_meses(part2);
                participanteDto.setEdad_dias(part3);
                participanteDto.setCodigo_casa(p.getCasa().getCodigo());
                participanteDto.setFechaNacimiento(p.getFechaNac());
                participanteDto.setEstado(procesos.getRetirado());
                participanteDto.setCodigo(p.getCodigo());
            }
            return JsonUtil.createJsonResponse(participanteDto);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//endregion

    //region Serologia/GuardarSerologia
    @RequestMapping(value = "GuardarSerologia", method = RequestMethod.POST)
    public ResponseEntity<String>GuardarSerologia (@RequestParam(value = "idSerologia", required=false, defaultValue="") String idSerologia
           ,@RequestParam( value = "idParticipante", required = false, defaultValue=""  ) String idParticipante
           ,@RequestParam( value = "fecha",          required = false, defaultValue=""  ) String fecha
           ,@RequestParam( value = "volumen",        required = false, defaultValue=""  ) String volumen
           ,@RequestParam( value = "observacion",    required = false, defaultValue=""  ) String observacion
           ,@RequestParam( value = "casaCHF",        required = false, defaultValue=""  ) String casaCHF
           ,@RequestParam( value = "tiporequest",    required = false, defaultValue=""  ) String tiporequest
           ,@RequestParam( value = "edadMeses",      required = false, defaultValue=""  ) Integer edadMeses
    ) throws Exception {
        try{
            if (volumen.equals("0") || volumen.equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Volumen no puede ser igual a 0." );
                return JsonUtil.createJsonResponse(map);
            }
            if (fecha.equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Fecha no puede ser vacía.");
                return JsonUtil.createJsonResponse(map);
            }
            if (!idParticipante.matches("^\\d{4}$")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Código participante no es válido." );
                return JsonUtil.createJsonResponse(map);
            }
            Serologia sero = new Serologia();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            String ComputerIp = InetAddress.getLocalHost().getHostAddress();
            if (tiporequest.equals("false")){//Guardar Nuevo Registro
                if (!serologiaService.ExisteSerologia(DateUtil.StringToDate(fecha,"dd/MM/yyyy"),idParticipante)){
                    sero.setDeviceid(nameComputer);
                    sero.setRecordIp(ComputerIp);
                    sero.setEstado('1');
                    sero.setPasive('0');
                    sero.setRecordDate(new Date());
                    sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    sero.setParticipante(idParticipante);
                    Integer codeCasa= (casaCHF.equals(""))?0:Integer.parseInt(casaCHF);
                    sero.setCodigo_casa(codeCasa);
                    if (codeCasa==0 && edadMeses==0){
                        sero.setDescripcion("Nuevo Ingreso");
                    }else {
                        sero.setDescripcion("");
                    }
                    sero.setEnviado('0');
                    sero.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                    sero.setEdadMeses(edadMeses);
                    String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                    sero.setObservacion(obs);
                    sero.setVolumen(Double.parseDouble(volumen));
                    serologiaService.saveSerologia(sero);
                    return JsonUtil.createJsonResponse(sero);
                }
                else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Muestra ya existe." );
                    return JsonUtil.createJsonResponse(map);
                }
            }else{//Editar Registro
                Integer id = Integer.parseInt(idSerologia);
                Serologia objToEdit = this.serologiaService.getSerologiaById(idSerologia);
                objToEdit.setDeviceid(nameComputer);
                objToEdit.setRecordIp(ComputerIp);
                objToEdit.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                objToEdit.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                objToEdit.setEdadMeses(edadMeses);
                String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                objToEdit.setObservacion(obs);
                objToEdit.setVolumen(Double.parseDouble(volumen));
                serologiaService.saveSerologia(objToEdit);
                return JsonUtil.createJsonResponse(objToEdit);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//endregion

    //Listado de Serologia
    @RequestMapping(value = "/listSerologia", method = RequestMethod.GET)
    public String listSerologia(Model model)throws Exception{
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        model.addAttribute("sitios", sitios);
        return "/Serologia/List";
    }

    //Serologia/listMuestrasNoEnviadas
    @RequestMapping(value = "/listMuestrasNoEnviadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Serologia> ListaMuestraToJson() throws ParseException {
        List<Serologia> seroDtom = null;
        try{
            seroDtom =  this.serologiaService.SerologiaNoEnviada();
            return  seroDtom;
        }catch (Exception e){
            logger.error(e.getMessage());
            return  seroDtom;
        }
    }


    @RequestMapping(value = "/listEnviosMuestras", method = RequestMethod.GET, produces = "application/json")
    public String listEnviosMuestras(Model model)throws Exception{
        List<Serologia> serologias = this.serologiaService.SerologiaNoEnviada();
        model.addAttribute("serologias",serologias);


        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_ENVIO_SEROLOGIA");
        model.addAttribute("numero_envio", numero_envio);
        return "/Serologia/EnvioForm";
    }


//endregion


    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="idAccion", required=true ) String idAccion
            , @RequestParam( value="message_razon", required=true, defaultValue="" ) String message_razon ) {
        try{
            Serologia serologia = this.serologiaService.getSerologiaById(idAccion);
            if (serologia!=null) {
                serologia.setPasive('1');
                serologia.setObservacion(message_razon.toUpperCase());
                this.serologiaService.saveSerologia(serologia);
            }
            return JsonUtil.createJsonResponse(serologia);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

}
