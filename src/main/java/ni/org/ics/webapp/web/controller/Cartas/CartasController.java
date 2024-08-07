package ni.org.ics.webapp.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.webapp.domain.catalogs.Parte;
import ni.org.ics.webapp.domain.catalogs.Version;
import ni.org.ics.webapp.domain.core.Estudio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.scancarta.*;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.scancarta.ScanCartaService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.security.cert.Extension;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 09/01/2020.
 */

@Controller
@RequestMapping("/cartas")
public class CartasController {
    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    /* Instancia de mi Servicio  MessageResourceService*/
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    private String userRegistro = "";
    private Date fechaRegistro;

    @RequestMapping(value = "/Crear", method = RequestMethod.GET)
    public String Crear(ModelMap model) throws Exception {
        try {
            List<Estudio> carta = scanCartaService.getAllEstudios();
            model.addAttribute("carta", carta);
            model.addAttribute("estudio",carta.get(0));
            List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
            model.addAttribute("relFam", relFam);
            List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
            model.addAttribute("proyecto", proyecto);
            List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
            model.addAttribute("SiNoNA", SiNoNA);
            List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
            model.addAttribute("tpoasent", tpoasent);
            List<Personal> personal = scanCartaService.getPersonal();
            model.addAttribute("personal", personal);
            List<Parte> partlist = scanCartaService.getListParte();
            model.addAttribute("partlist", partlist);
            return "/Cartas/CrearCarta";
        } catch (Exception e) {
            return "404";
        }
    }


    @RequestMapping(value = "EditCarta/{idCartaParticipante}", method = RequestMethod.GET)
    public String EditCartaParticipante(@PathVariable(value = "idCartaParticipante") Integer idCartaParticipante, Model model) {
        try {
        List<Estudio> carta = scanCartaService.getEstudios();
        model.addAttribute("carta", carta);
        model.addAttribute("estudio", carta.get(0).getNombre() );
        List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
        model.addAttribute("relFam", relFam);
        List<Personal> personal = scanCartaService.getPersonal();
        model.addAttribute("personal", personal);
        List<MessageResource> scanca = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("scanca", scanca);
        List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
        model.addAttribute("proyecto", proyecto);
        List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
        model.addAttribute("tpoasent", tpoasent);
        List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("SiNoNA", SiNoNA);
        ParticipanteCarta obj = this.scanCartaService.getCartasParticipante(idCartaParticipante);
        List<Version> version = scanCartaService.getVersioCarta(obj.getVersion().getEstudio().getCodigo());
        model.addAttribute("version", version);
        Participante participante = scanCartaService.getParticipante(obj.getParticipante().getCodigo());

        userRegistro = obj.getRecordUser().toString();
            fechaRegistro = obj.getRecordDate();

        model.addAttribute("participante", participante);
        if (participante != null) {
            ParticipanteProcesos procesos = participanteProcesosService.getParticipante(participante.getCodigo());
            model.addAttribute("procesos", procesos);
        }
        List<DetalleParte> dp = scanCartaService.getDetalleParteList(idCartaParticipante);

                    model.addAttribute("dp", dp);
        model.addAttribute("obj", obj);
        return "/Cartas/EditCarta";
        } catch (Exception e) {
           return "404";
        }
    }

    @RequestMapping(value = "/VersionCarta", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String GetVersionCarta(@RequestParam(value = "idcarta") Integer idcarta, Model model) throws Exception {
        List<Version> objV = scanCartaService.getVersioCarta(idcarta);
        model.addAttribute("objV", objV);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }

    @RequestMapping(value = "/ParteVersion", method = RequestMethod.GET, produces = "application/json")
    public   @ResponseBody
    String GetParteCarta(@RequestParam(value = "idversion") Integer idversion, Model model) throws Exception {
        String result = "No Found";
        List<Parte> parte = scanCartaService.getParte(idversion);
        model.addAttribute("parte", parte);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }


    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) String parametro) throws Exception {
        try {
            if ( !parametro.matches("^\\d{4}$")) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Codigo participante no es válido");
                return createJsonResponse(map);
            }
            Map<String, String> map = new HashMap<String, String>();
            BuscaParticipanteForCarta objEncontrado = new BuscaParticipanteForCarta();
            Participante participante = scanCartaService.getParticipante(parametro);
            if (participante != null) {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                objEncontrado.setEstado(procesos.getRetirado().toString());
                String edad = participante.getEdad();
                String[] strs = edad.split("/");
                String edadyear = strs[0];
                objEncontrado.setEdadAnios(edadyear);
                String edadmeses = strs[1];
                objEncontrado.setEdadMes(edadmeses);
                String edaddias = strs[2];
                objEncontrado.setEdadDia(edaddias);
                objEncontrado.setCodigoParticipante(participante.getCodigo());
                objEncontrado.setNombreCompleto(participante.getNombreCompleto());
                objEncontrado.setFechaNac(participante.getFechaNac());
                //Nombre Madre
                String madre = participante.getNombre1Madre().toUpperCase();
                if (participante.getNombre2Madre() != null)
                    madre = madre + " " + participante.getNombre2Madre().toUpperCase();
                madre = madre + " " + participante.getApellido1Madre().toUpperCase();
                if (participante.getApellido2Madre() != null)
                    madre = madre + " " + participante.getApellido2Madre().toUpperCase();
                objEncontrado.setNombreMadre(madre);
                // Nombre Padre
                String padre = participante.getNombre1Padre().toUpperCase();
                if (participante.getNombre2Padre() != null)
                    padre = padre + " " + participante.getNombre2Padre().toUpperCase();
                padre = padre + " " + participante.getApellido1Padre().toUpperCase();
                if (participante.getApellido2Padre() != null)
                    padre = padre + " " + participante.getApellido2Padre().toUpperCase();
                objEncontrado.setNombrePadre(padre);
                //Nombre Tutor
                String tutor = participante.getNombre1Tutor().toUpperCase();
                if (participante.getNombre2Tutor() != null)
                    tutor = tutor + " " + participante.getNombre2Tutor().toUpperCase();
                tutor = tutor + " " + participante.getApellido1Tutor().toUpperCase();
                if (participante.getApellido2Tutor() != null)
                    tutor = tutor + " " + participante.getApellido2Tutor().toUpperCase();
                objEncontrado.setNombreTutor(tutor);
                objEncontrado.setRealFam(participante.getRelacionFamiliarTutor());
            }else{
                map.put("msj", "No se encontraron resultados.");
                return createJsonResponse(map);
            }
            return JsonUtil.createJsonResponse(objEncontrado);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/VerParteCarta", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<DetalleParte> VerPartes(@RequestParam(value = "idparticipantecarta") Integer idparticipantecarta) throws ParseException {
        List<DetalleParte> p = null;
        try {
            p = scanCartaService.getDetalleParteList(idparticipantecarta);
            return p;
        } catch (Exception ex) {
            return p;
        }
    }

    // cartas/VerExtension
    @RequestMapping(value = "/VerExtension", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Extensiones> VerExtension(@RequestParam(value = "idversion") Integer idversion) throws Exception {
        List<Extensiones> lista = null;
        try {
            lista = this.scanCartaService.getExtension(idversion);
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    //Metodo para guardar la Version/Participante
    @RequestMapping(value = "/saveScanCarta", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveScanCarta(@RequestBody ParticipanteCartaDto obj) {
        try {
            Version v = new Version();
            if ( !obj.getIdparticipante().matches("^\\d{4}$")) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Codigo participante no es válido");
                return createJsonResponse(map);
            }
            if (!scanCartaService.SiExisteParticipanteCarta(obj.getVersion(), obj.getIdparticipante(), obj.getFechacarta())) {
                ParticipanteCarta pc = new ParticipanteCarta();
                String computerName = InetAddress.getLocalHost().getHostName();
                String computerIp = InetAddress.getLocalHost().getHostAddress();
                if (obj != null) {
                    Participante p = this.scanCartaService.getParticipante(obj.getIdparticipante());
                    pc.setParticipante(p);
                    v.setIdversion(obj.getVersion());
                    pc.setVersion(v);
                    pc.setRelfam(obj.getRelfam());
                    pc.setAsentimiento(obj.getAsentimiento());
                    int tipoAsentimiento = (obj.getTipoasentimiento() == null) ? 0 : obj.getTipoasentimiento();
                    pc.setTipoasentimiento(tipoAsentimiento);
                    pc.setQuienfirma(obj.getNombfirma().toUpperCase());
                    String name2Tutor = (obj.getNombre2Firma() != null) ? obj.getNombre2Firma().toUpperCase() : "";
                    pc.setNombre2Firma(name2Tutor);
                    pc.setApellido1Firma(obj.getApellido1Firma().toUpperCase());
                    String ape2Tutor = (obj.getApellido2Firma() != null) ? obj.getApellido2Firma().toUpperCase() : "";
                    pc.setApellido2Firma(ape2Tutor);
                    pc.setPersonal(""+obj.getPerson());
                    pc.setProyecto(obj.getProyecto());
                    boolean cf = (obj.getContactoFuturo().equals("1")) ? true : false;
                    pc.setContactoFuturo(cf);
                    boolean testigo = obj.getTestigopresente().equals("1") ? true : false;
                    pc.setTestigopresent(testigo);
                    String name1Testigo = (obj.getNombre1testigo() != null) ? obj.getNombre1testigo().toUpperCase() : "";
                    pc.setNombre1testigo(name1Testigo);
                    String name2Testigo = (obj.getNombre2testigo() != null) ? obj.getNombre2testigo().toUpperCase() : "";
                    pc.setNombre2testigo(name2Testigo);
                    String ape1Testigo = (obj.getApellido1testigo() != null) ? obj.getApellido1testigo().toUpperCase() : "";
                    pc.setApellido1testigo(ape1Testigo);
                    String ape2Testigo = (obj.getApellido2testigo() != null) ? obj.getApellido2testigo().toUpperCase() : "";
                    pc.setApellido2testigo(ape2Testigo);
                    String obs = (obj.getObservacion() != null) ? obj.getObservacion().toUpperCase() : "";
                    pc.setObservacion(obs);
                    pc.setEdadyears(obj.getEdadyears());
                    pc.setEdadmeses(obj.getEdadmeses());
                    pc.setEdaddias(obj.getEdaddias());
                    pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                    pc.setRecordDate(new Date());
                    pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    pc.setDeviceid(computerName);
                    pc.setEstado('1');
                    pc.setPasive('0');
                    pc.setRecordIp(computerIp);
                    scanCartaService.saveOrUpdateScanCarta(pc);
                }
                if (obj.getParte() != null) {
                    Parte pr = new Parte();
                    for (ParteDto parte : obj.getParte()) {
                        DetalleParte dp = new DetalleParte();
                        dp.setParticipantecarta(pc);
                        dp.setAcepta(parte.isAcepta());
                        pr.setIdparte(parte.getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        dp.setRecordIp(computerIp);
                        scanCartaService.saveParteCarta(dp);
                    }
                }
                return createJsonResponse(pc);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Buscar Listado por Codigo Participante /cartas/ListadoCartaParticipant */
    @RequestMapping(value = "/ListadoCartaParticipant", method = RequestMethod.GET)
    public ModelAndView ListadoCartaParticipant() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Cartas/ListadoCartaParticipante");
        return modelAndView;
    }

    // Búsqueda del Listado de Cartas del Participante
    @RequestMapping(value = "/GetCartasParticipante", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<VersionExtensionCartaDto> fetchCartaParticipanteToJson(@RequestParam(value = "parametro", required = true) String parametro)
            throws ParseException {
        List<VersionExtensionCartaDto> ext = new ArrayList<VersionExtensionCartaDto>();
        try {
            List<ParticipanteCarta> cartaparticipante = scanCartaService.getScanCartasByParticipante(parametro);
            for (ParticipanteCarta pcarta : cartaparticipante) {
                VersionExtensionCartaDto obj = new VersionExtensionCartaDto();
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(pcarta.getParticipante().getCodigo());
                Integer partExt = this.scanCartaService.cantExtensionByCarta(pcarta.getIdparticipantecarta());
                obj.setCantidadExtension(partExt);
                obj.setIdParticipanteCarta(pcarta.getIdparticipantecarta());
                obj.setCodigoParticipante(pcarta.getParticipante().getCodigo());
                obj.setNombreCompleto(pcarta.getParticipante().getNombreCompleto());
                obj.setIdEstudio(pcarta.getVersion().getEstudio().getCodigo());
                obj.setNmobreEstudio(pcarta.getVersion().getEstudio().getNombre());
                obj.setIdVersion(pcarta.getVersion().getIdversion());
                obj.setNombreVersion(pcarta.getVersion().getVersion());
                obj.setFechaCarta(DateUtil.DateToString(pcarta.getFechacarta(), "dd/MM/yyyy"));
                obj.setEstado("" + procesos.getRetirado());
                obj.setAnulada(pcarta.isAnulada());
                obj.setPqAnulada(pcarta.getPq_anulada());
                obj.setTieneExtesion(this.scanCartaService.tieneExtensionByVersion(pcarta.getVersion().getIdversion()));
                ext.add(obj);
            }
            return ext;
        } catch (Exception e) {
            return ext = null;
        }
    }


    /* Anular toda la carta principal, partes y extension */
    @RequestMapping(value = "UpdateRetiro", method = RequestMethod.GET)
    public ResponseEntity<String> UpdateRetiro(@RequestParam(value = "IdParticipanteCartaModalAnular", required = true) Integer IdParticipanteCartaModalAnular,
                                               @RequestParam(value = "observacion", required = true) String observacion)
            throws Exception {
        try {
            if (this.scanCartaService.updateAnular(IdParticipanteCartaModalAnular, observacion.toUpperCase())) {
                if (this.scanCartaService.UpdateParteAnulada(IdParticipanteCartaModalAnular)) {
                    this.scanCartaService.UpdateExtensionAnulada(IdParticipanteCartaModalAnular);
                }
            }
            return createJsonResponse("Anulado éxitoso!");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //cartas/Delete
    @RequestMapping(value = "Delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> Delete(@RequestParam(value = "idParticpanteCarta", required = true) Integer idParticipanteCarta) throws Exception {
        try {
           /* if (this.scanCartaService.deleteDetalleParte(idParticipanteCarta)){
                if (this.scanCartaService.deleteExtension(idParticipanteCarta)) {
                    this.scanCartaService.deleteParticipanteCarta(idParticipanteCarta);
                }
            }*/
            return JsonUtil.createJsonResponse("Registros Eliminados!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // cartas/updateDetalleParte
    @RequestMapping(value = "updateDetalleParte", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateDetalleParte(@RequestBody ArrayList<ParteDto> postData) throws Exception {
        try {
            for (ParteDto dto : postData) {
                this.scanCartaService.ActualizarAcepta(dto.getIddetalle(), dto.isAcepta(),SecurityContextHolder.getContext().getAuthentication().getName(),new Date());
            }
            return JsonUtil.createJsonResponse("Registros Actualizados");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.OK);
        }
    }


    //metodo para actulizar partes
    @RequestMapping(value = "UpdateAll", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<String> UpdateAll(@RequestBody ParticipanteCartaDto obj) throws Exception {
        try {
            ParticipanteCarta pc = new ParticipanteCarta();
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            if (obj != null) {
                pc.setIdparticipantecarta(obj.getCodigo());
                Participante p = new Participante();
                p.setCodigo(obj.getIdparticipante());
                pc.setParticipante(p);
                Version v = new Version();
                v.setIdversion(obj.getVersion());
                pc.setVersion(v);
                pc.setAsentimiento(obj.getAsentimiento());
                int tipo_asentimiento = (obj.getTipoasentimiento() == null) ? 0 : obj.getTipoasentimiento();
                pc.setTipoasentimiento(tipo_asentimiento);
                pc.setRelfam(obj.getRelfam());
                pc.setQuienfirma(obj.getNombfirma().toUpperCase());
                String nombre2tutor = (obj.getNombre2Firma() != null) ? obj.getNombre2Firma().toUpperCase() : "";
                pc.setNombre2Firma(nombre2tutor);
                pc.setApellido1Firma(obj.getApellido1Firma().toUpperCase());
                String apellido2tutor = (obj.getApellido2Firma() != null) ? obj.getApellido2Firma().toUpperCase() : "";
                pc.setApellido2Firma(apellido2tutor);
                pc.setPersonal(""+obj.getPerson());
                pc.setProyecto(obj.getProyecto());
                boolean cf = (obj.getContactoFuturo().equals("1")) ? true : false;
                pc.setContactoFuturo(cf);
                boolean testigo = obj.getTestigopresente().equals("1") ? true : false;
                pc.setTestigopresent(testigo);
                pc.setNombre1testigo(obj.getNombre1testigo().toUpperCase());
                String name2Testigo = (obj.getNombre2testigo() != null) ? obj.getNombre2testigo().toUpperCase() : "";
                pc.setNombre2testigo(name2Testigo);
                pc.setApellido1testigo(obj.getApellido1testigo().toUpperCase());
                String ape2Testigo = (obj.getApellido2testigo() != null) ? obj.getApellido2testigo().toUpperCase() : "";
                pc.setApellido2testigo(ape2Testigo);
                String obs = (obj.getObservacion() != null) ? obj.getObservacion().toUpperCase() : "";
                pc.setObservacion(obs);
                pc.setEdadyears(obj.getEdadyears());
                pc.setEdadmeses(obj.getEdadmeses());
                pc.setEdaddias(obj.getEdaddias());
                pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                pc.setRecordDate( fechaRegistro);
                pc.setFechaModifica(new Date());
                pc.setRecordUser(userRegistro);
                pc.setUsuarioModifica(SecurityContextHolder.getContext().getAuthentication().getName());
                pc.setDeviceid(computerName);
                pc.setEstado('1');
                pc.setPasive('0');
                pc.setRecordIp(computerIp);
                scanCartaService.saveOrUpdateScanCarta(pc);
            }
            if (obj.getParte() != null) {
                for (ParteDto dto : obj.getParte()) {
                    this.scanCartaService.ActualizarAcepta(dto.getIddetalle(), dto.isAcepta(),SecurityContextHolder.getContext().getAuthentication().getName(),new Date());
                }
            }
            return createJsonResponse(pc);
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ** una vez guardada la carta
    @RequestMapping(value = "/cartaSaveEdit/{idparticipantecarta}", method = RequestMethod.GET)
    public String cartaSaveEdit(@PathVariable("idparticipantecarta") String idparticipantecarta, Model model) throws ParseException {
        try {
            logger.debug("Mostrando participantes de caso monitoreo intensivo en JSP: " + idparticipantecarta);
            Integer parametro = Integer.parseInt(idparticipantecarta);
            ParticipanteCarta cartaparticipante = scanCartaService.getScanCartasByIdParticipanteCarta(parametro);
            if (cartaparticipante != null) {
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(cartaparticipante.getParticipante().getCodigo());
                model.addAttribute("pc", cartaparticipante);
                boolean tieneExt = this.scanCartaService.tieneExtensionByVersion(cartaparticipante.getVersion().getIdversion());
                model.addAttribute("tieneExt", tieneExt);
                model.addAttribute("estado", procesos.getRetirado());
                Integer partExt = this.scanCartaService.cantExtensionByCarta(cartaparticipante.getIdparticipantecarta());
                model.addAttribute("partExt", partExt);
                return "Cartas/CartaUnique";
            } else {
                return "404";
            }
        } catch (Exception e) {
            return "404";
        }
    }


    // Ir al Listado de todas las Extensiones
    @RequestMapping(value = "/ListExtension", method = RequestMethod.GET)
    public String ListExtension(ModelMap model) throws Exception {
        try {
           List<ParticipanteExtension>listExtensiones = this.scanCartaService.getAllPartipantExtension();
            model.addAttribute("listExtensiones",listExtensiones);
            return "/Cartas/ListadoExtensionParticipante";
        } catch (Exception e) {
            return "404";
        }
    }

    // Deshabilitar Extension Oficial
    @RequestMapping(value = "/disableExtension/{idParticipantExtension}")
    public ResponseEntity<String> disableExtension(@RequestParam(value = "idParticipantExtension", required = true) Integer idParticipantExtension)throws Exception{
        try{
            ParticipanteExtension extensionToEdit = this.scanCartaService.getParticipanteExtensionById(idParticipantExtension);
            if (extensionToEdit!=null){
                extensionToEdit.setAnulada(true);
                this.scanCartaService.saveParticpanteExtension(extensionToEdit);
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro Deshabilitado");
                return createJsonResponse(map);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "No se encontraron registros, con ID: ".concat(""+idParticipantExtension));
                return createJsonResponse(map);
            }
        }catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Form/Listado para asignar una extension
    @RequestMapping(value = "/extension/{idParticipanteCarta}", method = RequestMethod.GET)
    public String extension(@PathVariable(value = "idParticipanteCarta") Integer idParticipanteCarta, Model model) throws Exception {
        try {
            ParticipanteCarta objParticipanteCarta = this.scanCartaService.getCartasParticipante(idParticipanteCarta);
            model.addAttribute("objParticipanteCarta",objParticipanteCarta);
            String nombreCompleto = objParticipanteCarta.getParticipante().getNombre1() + " " + objParticipanteCarta.getParticipante().getNombre2() + " " + objParticipanteCarta.getParticipante().getApellido1() + " " + objParticipanteCarta.getParticipante().getApellido2();
            model.addAttribute("nombreCompleto", nombreCompleto);
            List<DetalleParte> partestmp = scanCartaService.getDetalleParteByAccept(objParticipanteCarta.getIdparticipantecarta());
            String partesAccept = "";
            model.addAttribute("listPartestmp",partestmp);
            for (int i = 0; i < partestmp.size(); i++) {
                partesAccept += partestmp.get(i).getParte().getParte()+", ";
                model.addAttribute("partesAccept",partesAccept);
            }
            List<Extension> exts = scanCartaService.getExtensionVersion(objParticipanteCarta.getVersion().getIdversion());
            model.addAttribute("exts", exts);
            model.addAttribute("estudio", objParticipanteCarta.getVersion().getEstudio().getNombre());

            model.addAttribute("caso", new ParticipanteExtension());
            model.addAttribute("agregando", true);
            model.addAttribute("editando", false);
            return "/Cartas/Extensiones";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
    }

    // Editar Extension by Id
    @RequestMapping(value = "editExtension/{idParticipantExtension}", method = RequestMethod.GET)
    public String editextension(Model model, @PathVariable(value = "idParticipantExtension") Integer idParticipantExtension) throws Exception {
        try {
            ParticipanteExtension caso = this.scanCartaService.getParticipanteExtensionById(idParticipantExtension);
            model.addAttribute("caso",caso);
            ParticipanteCarta objParticipanteCarta = this.scanCartaService.getCartasParticipante(caso.getParticipantecarta().getIdparticipantecarta());
            model.addAttribute("objParticipanteCarta",objParticipanteCarta);
            String nombreCompleto = objParticipanteCarta.getParticipante().getNombre1() + " " + objParticipanteCarta.getParticipante().getNombre2() + " " + objParticipanteCarta.getParticipante().getApellido1() + " " + objParticipanteCarta.getParticipante().getApellido2();
            model.addAttribute("nombreCompleto", nombreCompleto);
            List<DetalleParte> partestmp = scanCartaService.getDetalleParteByAccept(objParticipanteCarta.getIdparticipantecarta());
            String partesAccept = "";
            model.addAttribute("listPartestmp",partestmp);
            for (int i = 0; i < partestmp.size(); i++) {
                partesAccept += partestmp.get(i).getParte().getParte()+", ";
                model.addAttribute("partesAccept",partesAccept);
            }
            model.addAttribute("estudio", objParticipanteCarta.getVersion().getEstudio().getNombre());
            List<Extension> exts = scanCartaService.getExtensionVersion(caso.getParticipantecarta().getVersion().getIdversion());
            model.addAttribute("exts", exts);
            model.addAttribute("agregando", false);
            model.addAttribute("editando", true);
            return "/Cartas/Extensiones";
        } catch (Exception e) {
            return "404";
        }
    }


    //cartas/saveExtensCarta
    @RequestMapping(value = "/saveExtensCarta", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveExtensCarta(@RequestParam(value = "fechaExtension", defaultValue = "") String fechaExtension
            , @RequestParam(value = "nombre1tutor", required = true) String nombre1tutor
            , @RequestParam(value = "nombre2tutor", required = false, defaultValue = "") String nombre2tutor
            , @RequestParam(value = "apellido1tutor", required = true, defaultValue = "") String apellido1tutor
            , @RequestParam(value = "apellido2tutor", required = false) String apellido2tutor
            , @RequestParam(value = "chktestigo", required = false, defaultValue = "") String chktestigo
            , @RequestParam(value = "nombre1Testigo", required = true, defaultValue = "") String nombre1Testigo
            , @RequestParam(value = "nombre2Testigo", required = false, defaultValue = "") String nombre2Testigo
            , @RequestParam(value = "apellido1Testigo", required = true, defaultValue = "") String apellido1Testigo
            , @RequestParam(value = "apellido2Testigo", required = true, defaultValue = "") String apellido2Testigo
            , @RequestParam(value = "observacion", required = false, defaultValue = "") String observacion
            , @RequestParam(value = "idExtension", required = true) Integer idExtension
            , @RequestParam(value = "accion", required = true) String accion
            , @RequestParam(value = "idParticipante", required = true) Integer idParticipante
            , @RequestParam(value = "idParticipanteCarta", required = true) Integer idParticipanteCarta
            , @RequestParam(value = "idVersion", required = true) Integer idVersion
            , @RequestParam(value = "idParticipantExtension", required = true) Integer idParticipantExtension
    ) throws Exception {
        try {
            String ComputerName = InetAddress.getLocalHost().getHostName();
            String ComputerIp = InetAddress.getLocalHost().getHostAddress();
            if (accion.equals("true")) { //aqui actualizar
                ParticipanteExtension editObj = new ParticipanteExtension();
                editObj.setIdParticipantExtension(idParticipantExtension);
                editObj.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                editObj.setNombre1Tutor(nombre1tutor.toUpperCase());
                String name2tutor = "";
                if (nombre2tutor.equals("")) {
                    editObj.setNombre2Tutor(name2tutor);
                } else {
                    editObj.setNombre2Tutor(nombre2tutor);
                }
                String ape2Tutor = "";
                editObj.setApellido1Tutor(apellido1tutor.toUpperCase());
                if (apellido2tutor.equals("")) {
                    editObj.setApellido2Tutor(ape2Tutor);
                } else {
                    editObj.setApellido2Tutor(apellido2tutor.toUpperCase());
                }
                boolean band = (chktestigo.equals("on")) ? true : false;
                editObj.setTestigoPresente(band);
                String name1Testigo = (nombre1Testigo != null) ? nombre1Testigo.toUpperCase() : "";
                editObj.setNombre1Testigo(name1Testigo);
                String name2testigo = (nombre2Testigo != null) ? nombre2Testigo.toUpperCase() : "";
                editObj.setNombre2Testigo(name2testigo);
                String ape1Testigo = (apellido1Testigo != null) ? apellido1Testigo.toUpperCase() : "";
                editObj.setApellido1Testigo(ape1Testigo);
                String ape2Testigo = (apellido2Testigo != null) ? apellido2Testigo.toUpperCase() : "";
                editObj.setApellido2Testigo(ape2Testigo);
                String obs = (observacion != null) ? observacion.toUpperCase() : "";
                editObj.setObservacion(obs);
                ParticipanteCarta pc = new ParticipanteCarta();
                pc.setIdparticipantecarta(idParticipanteCarta);
                editObj.setParticipantecarta(pc);
                Extensiones extensiones = new Extensiones();
                extensiones.setId(idExtension);
                editObj.setExtensiones(extensiones);
                editObj.setDeviceid(ComputerName);
                editObj.setRecordIp(ComputerIp);
                editObj.setEstado('1');
                editObj.setPasive('0');
                editObj.setRecordDate(new Date());
                editObj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                this.scanCartaService.saveParticpanteExtension(editObj);
                return JsonUtil.createJsonResponse(editObj);
            } else {
                ParticipanteExtension ext = new ParticipanteExtension();
                if (!this.scanCartaService.VerificaExtension(fechaExtension, fechaExtension, idExtension)) {
                    ext.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                    ext.setNombre1Tutor(nombre1tutor.toUpperCase());
                    String name2tutor = "";
                    if (nombre2tutor.equals("")) {
                        ext.setNombre2Tutor(name2tutor);
                    } else {
                        ext.setNombre2Tutor(nombre2tutor);
                    }
                    String ape2Tutor = "";
                    ext.setApellido1Tutor(apellido1tutor.toUpperCase());
                    if (apellido2tutor.equals("")) {
                        ext.setApellido2Tutor(ape2Tutor);
                    } else {
                        ext.setApellido2Tutor(apellido2tutor.toUpperCase());
                    }
                    boolean band = (chktestigo.equals("on")) ? true : false;
                    ext.setTestigoPresente(band);
                    String name1Testigo = (nombre1Testigo != null) ? nombre1Testigo.toUpperCase() : "";
                    ext.setNombre1Testigo(name1Testigo);
                    String name2testigo = (nombre2Testigo != null) ? nombre2Testigo.toUpperCase() : "";
                    ext.setNombre2Testigo(name2testigo);
                    String ape1Testigo = (apellido1Testigo != null) ? apellido1Testigo.toUpperCase() : "";
                    ext.setApellido1Testigo(ape1Testigo);
                    String ape2Testigo = (apellido2Testigo != null) ? apellido2Testigo.toUpperCase() : "";
                    ext.setApellido2Testigo(ape2Testigo);
                    String obs = (observacion != null) ? observacion.toUpperCase() : "";
                    ext.setObservacion(obs);
                    ParticipanteCarta pc = new ParticipanteCarta();
                    pc.setIdparticipantecarta(idParticipanteCarta);
                    ext.setParticipantecarta(pc);
                    Extensiones extensiones = new Extensiones();
                    extensiones.setId(idExtension);
                    ext.setExtensiones(extensiones);
                    ext.setRecordIp(ComputerIp);
                    ext.setDeviceid(ComputerName);
                    ext.setEstado('1');
                    ext.setPasive('0');
                    ext.setRecordDate(new Date());
                    ext.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    this.scanCartaService.saveParticpanteExtension(ext);
                    return createJsonResponse(ext);
                } else {
                    String cadenaCodificada = ("Participante tiene registrada Extension!");
                    return createJsonResponse(cadenaCodificada);
                }
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //region ******************** CARTAS TEMPORALES  **********************************

    //Metodo para crear una Nueva Carta Temporal /CatalogoCarta/CartaParticipantTmp
    @RequestMapping(value = "/CartaParticipantTmp", method = RequestMethod.GET)
    public ModelAndView CartaParticipantTmp(Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Estudio> cartas = scanCartaService.getAllEstudios();
        model.addAttribute("cartas", cartas);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
        model.addAttribute("relFam", relFam);
        List<Personal> personal = scanCartaService.getPersonal();
        model.addAttribute("personal", personal);
        List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
        model.addAttribute("proyecto", proyecto);
        List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("SiNoNA", SiNoNA);
        List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
        model.addAttribute("tpoasent", tpoasent);
        model.addAttribute("caso", new ParticipanteCartaTmp());
        List<Parte> parte = scanCartaService.getListParte();
        model.addAttribute("parte", parte);
        List<Version> version = this.scanCartaService.getVersionActiva();
        model.addAttribute("version", version);
        List<ParticipanteCartaTmp> cartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();
        // determino si una carta temporal tiene extension para habilitar el boton
        boolean tieneExtension = false;
        List<ParticipanteCartaDto> listaDto = new ArrayList<ParticipanteCartaDto>();
        for (int i = 0; i < cartaTmp.size(); i++) {
            ParticipanteCartaDto objtbl = new ParticipanteCartaDto();
            objtbl.setIdparticipante(cartaTmp.get(i).getIdparticipante());
            objtbl.setFechacarta(DateUtil.DateToString(cartaTmp.get(i).getFechacarta(), "dd/MM/yyyy"));
            objtbl.setVersion(cartaTmp.get(i).getVersion().getIdversion());
            objtbl.setEstudio(cartaTmp.get(i).getVersion().getEstudio().getCodigo());
            objtbl.setNombfirma(cartaTmp.get(i).getName1tutor());
            objtbl.setApellido1Firma(cartaTmp.get(i).getSurname1tutor());
            objtbl.setCodigo(cartaTmp.get(i).getId());
            objtbl.setNombreUsuario(cartaTmp.get(i).getRecordUser());
            tieneExtension = (this.scanCartaService.tieneExtensionByVersion(cartaTmp.get(i).getVersion().getIdversion()));
            objtbl.setTineneExtension(tieneExtension);
            List<ParteDto> listPartesDto = new ArrayList<ParteDto>();
            List<DetalleParteTmp> detalletmp = this.scanCartaService.getDetalleParteTmpById(cartaTmp.get(i).getId());
            for (DetalleParteTmp tmp1 : detalletmp) {
                ParteDto objpartes = new ParteDto();
                objpartes.setNombreparte(tmp1.getParte().getParte());
                objpartes.setAcepta(tmp1.isAcepta());
                listPartesDto.add(objpartes);
            }
            objtbl.setParte(listPartesDto);
            listaDto.add(objtbl);
        }
        model.addAttribute("listaDto", listaDto);
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        modelAndView.setViewName("/CatalogoScanCarta/ParticipanteCartaTmp");
        return modelAndView;
    }

    // ** EDITAR CARTA TEMPORAL
    @RequestMapping(value = "/editTmp/{codigo}", method = RequestMethod.GET)
    public String editTmp(Model model, @PathVariable("codigo") Integer codigo) throws Exception
    {
        try{
            ParticipanteCartaTmp caso = this.scanCartaService.getAllParticipanteCartaTmpById(codigo);
            model.addAttribute("caso", caso);
            List<Estudio> cartas = scanCartaService.getAllEstudios();
            model.addAttribute("cartas", cartas);
            List<Version> version = this.scanCartaService.getVersioCarta(caso.getVersion().getEstudio().getCodigo());
            model.addAttribute("version", version);
            List<DetalleParteTmp> dp = scanCartaService.getList_Detalle_Parte_Tmp(caso.getId());
            Parte oP = null;
            for (DetalleParteTmp prici : dp){
               oP = this.scanCartaService.getPartePrincipal(prici.getParticipantecartatmp().getVersion().getIdversion());
            }
            model.addAttribute("partePrincipal",oP.getParte());
            model.addAttribute("dp", dp);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
            model.addAttribute("relFam", relFam);
            List<Personal> personal = scanCartaService.getPersonal();
            model.addAttribute("personal", personal);
            List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
            model.addAttribute("proyecto", proyecto);
            List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
            model.addAttribute("SiNoNA", SiNoNA);
            List<ParticipanteCartaTmp> cartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();
            List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
            model.addAttribute("tpoasent", tpoasent);
            model.addAttribute("obj", new ParticipanteCartaDto());
            List<ParticipanteCartaTmp> listCartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();
            boolean tieneExtension = false;
            List<ParticipanteCartaDto> listaDto = new ArrayList<ParticipanteCartaDto>();
            for (int i = 0; i < listCartaTmp.size(); i++) {
                ParticipanteCartaDto objtbl = new ParticipanteCartaDto();
                objtbl.setIdparticipante(cartaTmp.get(i).getIdparticipante());
                objtbl.setFechacarta(DateUtil.DateToString(cartaTmp.get(i).getFechacarta(), "dd/MM/yyyy"));
                objtbl.setVersion(cartaTmp.get(i).getVersion().getIdversion());
                objtbl.setEstudio(cartaTmp.get(i).getVersion().getEstudio().getCodigo());
                objtbl.setNombfirma(cartaTmp.get(i).getName1tutor());
                objtbl.setApellido1Firma(cartaTmp.get(i).getSurname1tutor());
                objtbl.setCodigo(cartaTmp.get(i).getId());
                objtbl.setNombreUsuario(cartaTmp.get(i).getRecordUser());
                tieneExtension = (this.scanCartaService.tieneExtensionByVersion(objtbl.getVersion()));
                List<ParteDto> listPartesDto = new ArrayList<ParteDto>();
                List<DetalleParteTmp> detalletmp = this.scanCartaService.getDetalleParteTmpById(cartaTmp.get(i).getId());
                for (DetalleParteTmp tmp1 : detalletmp) {
                    ParteDto objpartes = new ParteDto();
                    objpartes.setNombreparte(tmp1.getParte().getParte());
                    objpartes.setAcepta(tmp1.isAcepta());
                    listPartesDto.add(objpartes);
                }
                objtbl.setParte(listPartesDto);
                objtbl.setTineneExtension(tieneExtension);
                listaDto.add(objtbl);
            }
            model.addAttribute("listaDto", listaDto);
            model.addAttribute("agregando",false);
            model.addAttribute("accion",true);
            return "/CatalogoScanCarta/ParticipanteCartaTmp";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    // Guardar carta temporal
    @RequestMapping(value = "/saveCartaTmp", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveCartaTmp(@RequestBody ParticipanteCartaDto obj) throws Exception {
        try {
            if (obj.getIdparticipante() == null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Ingresa codigo del participante.");
                return createJsonResponse(map);
            }
            if (obj.getVersion() == null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Seleccione la Version.");
                return createJsonResponse(map);
            }
            if (obj.getTestigopresente().equals("1") && obj.getNombre1testigo().equals("") && obj.getApellido1testigo().equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Ingresa el nombre y  apellido del testigo.");
                return createJsonResponse(map);
            }
            if (obj.getParte().size() == 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Seleccione las partes.");
                return createJsonResponse(map);
            }

            if ( !obj.getIdparticipante().matches("^\\d{4}$")) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Codigo participante no es válido");
                return createJsonResponse(map);
            }

            Participante participante = scanCartaService.getParticipante(obj.getIdparticipante());
            if (participante != null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msjCodigo", "Codigo: ".concat(obj.getIdparticipante().toString()) + " Participante ya existe!.");
                return createJsonResponse(map);
            }

            if (obj.getAsentimiento().equals("1") && obj.getTipoasentimiento() == 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Revisa el Asentiemiento y Tipo de Asentimiento: ");
                return createJsonResponse(map);
            }
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp = InetAddress.getLocalHost().getHostAddress();
            String n1, n2, a1, a2;
            boolean asent, contact, test;
            if (obj.getAccion().equals("true")) {
                System.out.println("Editando");
                ParticipanteCartaTmp temporalForEdit = new ParticipanteCartaTmp();
                temporalForEdit.setId(obj.getCodigo());
                Version versForEdit = new Version();
                versForEdit.setIdversion(obj.getVersion());
                temporalForEdit.setVersion(versForEdit);
                temporalForEdit.setIdparticipante(obj.getIdparticipante());
                temporalForEdit.setRecurso(""+obj.getRecurso());
                temporalForEdit.setTipoasentimiento(obj.getTipoasentimiento());
                temporalForEdit.setAsentimiento(obj.getAsentimiento());
                temporalForEdit.setRelfam(obj.getRelfam());
                temporalForEdit.setName1tutor(obj.getNombfirma().toUpperCase());
                String nombre2t = (obj.getNombre2Firma() == "") ? "" : obj.getNombre2Firma().toUpperCase();
                temporalForEdit.setName2tutor(nombre2t);
                temporalForEdit.setSurname1tutor(obj.getApellido1Firma().toUpperCase());
                String ap2 = (obj.getApellido2Firma() == "") ? "" : obj.getApellido2Firma().toUpperCase();
                temporalForEdit.setSurname2tutor(ap2);
                contact = (obj.getContactoFuturo().equals("0")) ? false : true;
                temporalForEdit.setContactoFuturo(contact);
                temporalForEdit.setProyecto(obj.getProyecto());
                temporalForEdit.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                String obs = (obj.getObservacion()!="")?obj.getObservacion().toUpperCase():"";
                temporalForEdit.setObservacion(obs);
                test = (obj.getTestigopresente().equals("1")) ? true : false;
                temporalForEdit.setTestigopresent(test);
                n1 = (obj.getNombre1testigo() == "") ? "" : obj.getNombre1testigo().toUpperCase();
                temporalForEdit.setNombre1testigo(n1);
                n2 = (obj.getNombre2testigo() == "") ? "" : obj.getNombre2testigo().toUpperCase();
                temporalForEdit.setNombre2testigo(n2);
                a1 = (obj.getApellido1testigo() == "") ? "" : obj.getApellido1testigo().toUpperCase();
                temporalForEdit.setApellido1testigo(a1);
                a2 = (obj.getApellido2testigo() == "") ? "" : obj.getApellido2testigo().toUpperCase();
                temporalForEdit.setApellido2testigo(a2);
                temporalForEdit.setEstado('1');
                temporalForEdit.setPasive('0');
                temporalForEdit.setDeviceid(computerName);
                temporalForEdit.setRecordDate(new Date());
                temporalForEdit.setRecordIp(computerIp);
                temporalForEdit.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                this.scanCartaService.guardarCartaTMP(temporalForEdit);
                if (obj.getParte() != null){
                   int regEliminados = this.scanCartaService.deleteDetalleParteTmp(temporalForEdit.getId());
                    Parte pr = new Parte();
                       for (ParteDto parte : obj.getParte()) {
                           DetalleParteTmp dp = new DetalleParteTmp();
                           dp.setParticipantecartatmp(temporalForEdit);//
                           dp.setAcepta(parte.isAcepta());
                           pr.setIdparte(parte.getIdparte());
                           dp.setParte(pr);
                           dp.setDeviceid(computerName);
                           dp.setRecordDate(new Date());
                           dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                           dp.setEstado('1');
                           dp.setPasive('0');
                           dp.setRecordIp(computerIp);
                           scanCartaService.saveParteCartaTMP(dp);
                       }
                }
                return JsonUtil.createJsonResponse(temporalForEdit);
            } else { // GUARDAR NUEVO
            if (!scanCartaService.SiExisteParticipanteCartaTMP(obj.getVersion(), obj.getIdparticipante(), obj.getFechacarta())) {
                ParticipanteCartaTmp temporal = new ParticipanteCartaTmp();
                temporal.setIdparticipante(obj.getIdparticipante());
                Version vers = new Version();
                vers.setIdversion(obj.getVersion());
                temporal.setVersion(vers);
                temporal.setRecurso(""+obj.getRecurso());
                temporal.setAsentimiento(obj.getAsentimiento());
                temporal.setTipoasentimiento(obj.getTipoasentimiento());
                temporal.setRelfam(obj.getRelfam());
                temporal.setName1tutor(obj.getNombfirma().toUpperCase());
                String nombre2t = (obj.getNombre2Firma() == "") ? "" : obj.getNombre2Firma().toUpperCase();
                temporal.setName2tutor(nombre2t);
                temporal.setSurname1tutor(obj.getApellido1Firma().toUpperCase());
                String ap2 = (obj.getApellido2Firma() == "") ? "" : obj.getApellido2Firma().toUpperCase();
                temporal.setSurname2tutor(ap2);
                contact = (obj.getContactoFuturo().equals("0")) ? false : true;
                temporal.setContactoFuturo(contact);
                temporal.setProyecto(obj.getProyecto());
                temporal.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                String obs = (obj.getObservacion()!="")?obj.getObservacion().toUpperCase():"";
                temporal.setObservacion(obs);
                test = (obj.getTestigopresente().equals("1")) ? true : false;
                temporal.setTestigopresent(test);
                n1 = (obj.getNombre1testigo() == "") ? "" : obj.getNombre1testigo().toUpperCase();
                temporal.setNombre1testigo(n1);
                n2 = (obj.getNombre2testigo() == "") ? "" : obj.getNombre2testigo().toUpperCase();
                temporal.setNombre2testigo(n2);
                a1 = (obj.getApellido1testigo() == "") ? "" : obj.getApellido1testigo().toUpperCase();
                temporal.setApellido1testigo(a1);
                a2 = (obj.getApellido2testigo() == "") ? "" : obj.getApellido2testigo().toUpperCase();
                temporal.setApellido2testigo(a2);
                temporal.setRecordDate(new Date());
                temporal.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                temporal.setDeviceid(computerName);
                temporal.setEstado('1');
                temporal.setPasive('0');
                temporal.setRecordIp(computerIp);
                boolean TieneExtension = this.scanCartaService.tieneExtensionByVersion(obj.getVersion());
                temporal.setTieneExtension(TieneExtension);
                this.scanCartaService.guardarCartaTMP(temporal);

                if (obj.getParte() != null) {
                    Parte pr = new Parte();
                    int count = 0;
                    for (ParteDto parte : obj.getParte()) {
                        ++count;
                        DetalleParteTmp dp = new DetalleParteTmp();
                        dp.setParticipantecartatmp(temporal);//
                        dp.setAcepta(parte.isAcepta());
                        pr.setIdparte(parte.getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        dp.setRecordIp(computerIp);
                        scanCartaService.saveParteCartaTMP(dp);
                    }
                }
                return JsonUtil.createJsonResponse(temporal);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        }
        } catch (Exception ex) {
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //cartas/codigoParticipante
    @RequestMapping(value = "/codigoParticipante", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> buscarCodigoParticipante(@RequestParam(value = "idparticipante", required = true) String codigo) throws ParseException {
        Participante participante = null;
        try {
            participante = scanCartaService.getCodigoParticipante(codigo);
            if (participante != null)
                return JsonUtil.createJsonResponse("Codigo Participante: ".concat(codigo.toString()).concat(" Existe!"));
            else
                return JsonUtil.createJsonResponse("");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //cartas/listaParteTmpById
    @RequestMapping(value = "/listaParteTmpById", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> listaParteTmpById(@RequestParam(value = "codigo", required = true) Integer codigo) throws ParseException {
        List<DetalleParteTmp> detalle = null;
        try {
            detalle = scanCartaService.getDetalleParteTmpById(codigo);
            if (detalle != null)
                return JsonUtil.createJsonResponse(detalle);
            else
                return JsonUtil.createJsonResponse("No se encontraron datos!");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //endregion

    // Formulario/Listado de extensionTmp
    @RequestMapping(value = "/extensionTmp/{codigo}", method = RequestMethod.GET)
    public String extensionTmp(@PathVariable(value = "codigo") Integer codigo, Model model) throws Exception {
        try {
            ParticipanteCartaTmp participantecartatmp = this.scanCartaService.getAllParticipanteCartaTmpById(codigo);
            model.addAttribute("participantecartatmp",participantecartatmp);
            //obtengo las parte
            List<DetalleParteTmp> partestmp = scanCartaService.getDetalleParteTmpByAccept(participantecartatmp.getId());
            model.addAttribute("listPartestmp",partestmp);
            String partesAccept = "";
            for (int i = 0; i < partestmp.size(); i++) {
                partesAccept += partestmp.get(i).getParte().getParte()+", ";
                model.addAttribute("partesAccept",partesAccept);
            }
            model.addAttribute("version",participantecartatmp.getVersion().getVersion());
            model.addAttribute("idversion",participantecartatmp.getVersion().getIdversion());
            model.addAttribute("estudio", participantecartatmp.getVersion().getEstudio().getNombre());
            model.addAttribute("codigo_participante",participantecartatmp.getIdparticipante());
            model.addAttribute("nombre1Tutor", participantecartatmp.getName1tutor());
            String name2 = (participantecartatmp.getName2tutor()=="") ? "" : participantecartatmp.getName2tutor();
            model.addAttribute("nombre2Tutor", name2 );
            model.addAttribute("Surname1tutor",  participantecartatmp.getSurname1tutor());
            String ape2 = (participantecartatmp.getSurname2tutor()== "") ? "" : participantecartatmp.getSurname2tutor();
            model.addAttribute("Surname2tutor", ape2 );
            List<Extensiones> exts =  this.scanCartaService.getExtensionsByVersion(participantecartatmp.getVersion().getIdversion());
            model.addAttribute("exts",exts);
            model.addAttribute("caso", new ExtensionesTmp());
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            return "CatalogoScanCarta/ExtensionTmp";
        } catch (Exception e) {
            return "404";
        }
    }
    // todo Editar Extension Temporal
    @RequestMapping(value = "/editExtensionTmp/{idParticipantExtensiontmp}", method = RequestMethod.GET)
    public String editExtensionTmp(@PathVariable(value = "idParticipantExtensiontmp") int idParticipantExtensiontmp, Model model)throws Exception{
        try{
            ExtensionesTmp extToEdit = this.scanCartaService.getExtensionTmpById(idParticipantExtensiontmp);
            model.addAttribute("caso",extToEdit);
            ParticipanteCartaTmp participantecartatmp = this.scanCartaService.getAllParticipanteCartaTmpById(extToEdit.getParticipantecartatmp().getId());
            model.addAttribute("participantecartatmp",participantecartatmp);
            List<DetalleParteTmp> partestmp = scanCartaService.getDetalleParteTmpByAccept(participantecartatmp.getId());
            String partesAccept = "";
            model.addAttribute("listPartestmp",partestmp);
            for (int i = 0; i < partestmp.size(); i++) {
                partesAccept += partestmp.get(i).getParte().getParte()+", ";
                model.addAttribute("partesAccept",partesAccept);
            }
            model.addAttribute("version",participantecartatmp.getVersion().getVersion());
            model.addAttribute("idversion",participantecartatmp.getVersion().getIdversion());
            model.addAttribute("estudio", participantecartatmp.getVersion().getEstudio().getNombre());
            model.addAttribute("codigo_participante",participantecartatmp.getIdparticipante());
            List<Extensiones> exts =  this.scanCartaService.getExtensionsByVersion(participantecartatmp.getVersion().getIdversion());
            model.addAttribute("exts",exts);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            return "CatalogoScanCarta/ExtensionTmp";
        }catch (Exception e){
            return "404";
        }
    }
    // Deshabilitar TODO Registro Extension Temporal individual
    @RequestMapping(value = "/disableExtensionTmp/{idParticipantExtensiontmp}")
    public ResponseEntity<String> disableExtensionTmp(@RequestParam(value = "idParticipantExtensiontmp", required = true) Integer idParticipantExtensiontmp)throws Exception{
        try{
            ExtensionesTmp extensionToEdit = this.scanCartaService.getExtensionTmpById(idParticipantExtensiontmp);
            if (extensionToEdit!=null){
                extensionToEdit.setPasive('1');
                this.scanCartaService.guardarExtensionTmp(extensionToEdit);
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro Deshabilitado");
                return createJsonResponse(map);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "No se encontraron registros, con ID: ".concat(""+idParticipantExtensiontmp));
                return createJsonResponse(map);
            }
        }catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // pagina del Listado de todas las ExtensionesTmp
    @RequestMapping(value = "/listExtensionTmp", method = RequestMethod.GET)
    public String listExtensionTmp( Model model) throws Exception {
        try {
            List<ExtensionesTmp> listaExtension = this.scanCartaService.getListExtensionTmp();
            model.addAttribute("listaExtension",listaExtension);
            return "CatalogoScanCarta/ListExtensionTmp";
        } catch (Exception e) {
            return "404";
        }
    }


    //GUARDAR EXTENSION TEMPORAL //cartas/saveExtensionTmp
    @RequestMapping(value = "/saveExtensionTmp", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveExtensionTmp(
           @RequestParam(value = "idParticipantExtensiontmp",    defaultValue = "", required = false) String idParticipantExtensiontmp
          ,@RequestParam(value = "participantecartatmp",         defaultValue = "", required = false) String participantecartatmp
          ,@RequestParam(value = "fechaExtension",               defaultValue = "", required = false) String fechaExtension
          ,@RequestParam(value = "idExtension",                  defaultValue = "", required = false) String idExtension
          ,@RequestParam(value = "nombre1Tutor",                 defaultValue = "", required = false) String nombre1Tutor
          ,@RequestParam(value = "nombre2Tutor",                 defaultValue = "", required = false) String nombre2Tutor
          ,@RequestParam(value = "apellido1Tutor",               defaultValue = "", required = false) String apellido1Tutor
          ,@RequestParam(value = "apellido2Tutor",               defaultValue = "", required = false) String apellido2Tutor
          ,@RequestParam(value = "chktestigo",                   defaultValue = "", required = false) String chktestigo
          ,@RequestParam(value = "nombre1Testigo",               defaultValue = "", required = false) String nombre1Testigo
          ,@RequestParam(value = "nombre2Testigo",               defaultValue = "", required = false) String nombre2Testigo
          ,@RequestParam(value = "apellido1Testigo",             defaultValue = "", required = false) String apellido1Testigo
          ,@RequestParam(value = "apellido2Testigo",             defaultValue = "", required = false) String apellido2Testigo
          ,@RequestParam(value = "observacion",                  defaultValue = "", required = false) String observacion
          ,@RequestParam(value = "idversion",                    defaultValue = "", required = false) String idversion
          ,@RequestParam(value = "editando",                    defaultValue = "", required = false) String editando
    ) throws Exception {
        int cod = Integer.parseInt(participantecartatmp);
        int codExtension = Integer.parseInt(idExtension);

        if (editando.equals("true")) {
            int codigo_participante_extension = Integer.parseInt(idParticipantExtensiontmp);
            ExtensionesTmp extensionToEdit = this.scanCartaService.getExtensionTmpById(codigo_participante_extension);

            extensionToEdit.setFechaExtension(DateUtil.StringToDate(fechaExtension,"dd/MM/yyyy"));
            Extensiones ex = new Extensiones();
            ex.setId(codExtension);
            extensionToEdit.setExtensiones(ex);
            //Tutor
            extensionToEdit.setNombre1Tutor(nombre1Tutor.toUpperCase());
            String name2 = (nombre2Tutor != "") ? nombre2Tutor.toUpperCase() : "";
            extensionToEdit.setNombre2Tutor(name2);
            extensionToEdit.setApellido1Tutor(apellido1Tutor.toUpperCase());
            String surname2Tutor = (apellido2Tutor != "") ? apellido2Tutor.toUpperCase() : "";
            extensionToEdit.setApellido2Tutor(surname2Tutor);
            //Testigo
            boolean isTestigoPresent = (chktestigo.equals("on")) ? true : false;
            extensionToEdit.setTestigoPresente(isTestigoPresent);
            String name1Test = (nombre1Testigo != "") ? nombre1Testigo.toUpperCase() : "";
            extensionToEdit.setNombre1Testigo(name1Test);
            String name2Test = (nombre2Testigo != "") ? nombre2Testigo.toUpperCase() : "";
            extensionToEdit.setNombre2Testigo(name2Test);
            String surname1Test = (apellido1Testigo != "") ? apellido1Testigo.toUpperCase() : "";
            extensionToEdit.setApellido1Testigo(surname1Test);
            String surname2Test = (apellido2Testigo != "") ? apellido2Testigo.toUpperCase() : "";
            extensionToEdit.setApellido2Testigo(surname2Test);

            String obs = (observacion != "") ? observacion.toUpperCase() : "";
            extensionToEdit.setObservacion(obs);

            ParticipanteCartaTmp pct = this.scanCartaService.getAllParticipanteCartaTmpById(cod);
            extensionToEdit.setParticipantecartatmp(pct);

            this.scanCartaService.guardarExtensionTmp(extensionToEdit);
            return JsonUtil.createJsonResponse(extensionToEdit);

        }else {
            if (!scanCartaService.verificaSiyaTieneExtension(cod, codExtension, fechaExtension)) {
                try {
                    ExtensionesTmp tmp = new ExtensionesTmp();
                    String computerName = InetAddress.getLocalHost().getHostName();
                    String computerIp = InetAddress.getLocalHost().getHostAddress();
                    tmp.setDeviceid(computerName);
                    tmp.setRecordDate(new Date());
                    tmp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    tmp.setEstado('1');
                    tmp.setPasive('0');
                    tmp.setRecordIp(computerIp);
                    tmp.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                    //Tutor
                    tmp.setNombre1Tutor(nombre1Tutor.toUpperCase());
                    String name2 = (nombre2Tutor != "") ? nombre2Tutor.toUpperCase() : "";
                    tmp.setNombre2Tutor(name2);
                    tmp.setApellido1Tutor(apellido1Tutor.toUpperCase());
                    String surname2Tutor = (apellido2Tutor != "") ? apellido2Tutor.toUpperCase() : "";
                    tmp.setApellido2Tutor(surname2Tutor);
                    //Testigo
                    boolean isTestigoPresent = (chktestigo.equals("on")) ? true : false;
                    tmp.setTestigoPresente(isTestigoPresent);
                    String name1Test = (nombre1Testigo != "") ? nombre1Testigo.toUpperCase() : "";
                    tmp.setNombre1Testigo(name1Test);
                    String name2Test = (nombre2Testigo != "") ? nombre2Testigo.toUpperCase() : "";
                    tmp.setNombre2Testigo(name2Test);
                    String surname1Test = (apellido1Testigo != "") ? apellido1Testigo.toUpperCase() : "";
                    tmp.setApellido1Testigo(surname1Test);
                    String surname2Test = (apellido2Testigo != "") ? apellido2Testigo.toUpperCase() : "";
                    tmp.setApellido2Testigo(surname2Test);

                    String obs = (observacion != "") ? observacion.toUpperCase() : "";
                    tmp.setObservacion(obs);

                    Extensiones ex = new Extensiones();
                    ex.setId(codExtension);
                    tmp.setExtensiones(ex);

                    ParticipanteCartaTmp pct = new ParticipanteCartaTmp();
                    pct.setId(cod);
                    tmp.setParticipantecartatmp(pct);

                    this.scanCartaService.guardarExtensionTmp(tmp);
                    return JsonUtil.createJsonResponse(tmp);
                } catch (Exception e) {
                    Gson gson = new Gson();
                    String json = gson.toJson(e.toString());
                    return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        }
    }


    //region TODO DESACTIVAR Registros Temporales con pasive=1
    @RequestMapping("/desactAllTmp/{idparticipantecartatmp}")
    public String desactAllTmp(@PathVariable("idparticipantecartatmp") Integer idparticipantecartatmp,
                               RedirectAttributes redirectAttributes)throws Exception{
        String redirecTo="404";
        try{
            ParticipanteCartaTmp participanteCartaTmp = this.scanCartaService.getAllParticipanteCartaTmpById(idparticipantecartatmp);
            if (participanteCartaTmp != null) {
                redirecTo = "redirect:/cartas/CartaParticipantTmp";
                int count_extensiones_tmp = this.scanCartaService.disableExtnsionTmpByIdCartaTmp(idparticipantecartatmp);
                int count_detail_parte_tmp = this.scanCartaService.disableDetailPartesTmpByIdCartaTmp(idparticipantecartatmp);
                int count_participanteCarta_tmp = this.scanCartaService.disableParticipanteCartaTmpById(idparticipantecartatmp);
                redirectAttributes.addFlashAttribute("RegistrosBloqueado", true);
            }
            redirectAttributes.addFlashAttribute("RegistrosBloqueado", false);
            return redirecTo;
        }catch (Exception e){
            return "403";
        }
    }

    @RequestMapping(value = "/deleteAllTmp", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> disable(@RequestParam(value = "idparticipantecartatmp", required = true) Integer idparticipantecartatmp
    ) throws Exception {
        try {
            ParticipanteCartaTmp p = this.scanCartaService.getAllParticipanteCartaTmpById(idparticipantecartatmp);
            if (p != null) {
                if(this.scanCartaService.Borrar_Detalle_Partes_tmp(p.getId())){
                    this.scanCartaService.Borrar_Participante_Carta_Extension(p.getId());
                }
                return JsonUtil.createJsonResponse(p);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "No se encontraron registros, con ID: ".concat(""+p.getId()));
                return createJsonResponse(map);
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion

    //region Buscar Por Nombres y Apellidos
    @RequestMapping(value = "/getNombre1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre1(@RequestParam(value = "nombre1", required = true) String nombre1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getNombre1(nombre1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getNombre2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre2(@RequestParam(value = "nombre2", required = true) String nombre2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getNombre2(nombre2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getApellido1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido1(@RequestParam(value = "apellido1", required = true) String apellido1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getApellido1(apellido1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getApellido2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido2(@RequestParam(value = "apellido2", required = true) String apellido2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getApellido2(apellido2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }

//endregion


    //region todo Pasar datos de una tabla a otra Finaliza Procesos
    @RequestMapping(value = "/saveTmpsToOficial", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> saveTmpsToOficial(@RequestBody List<Integer> dataArrayToSend)throws Exception{
        try{
            String computerName = InetAddress.getLocalHost().getHostName();
            String computerIp  = InetAddress.getLocalHost().getHostAddress();
            int contador = 0;
            for (Integer num : dataArrayToSend){
                ParticipanteCartaTmp cartaTemporal = this.scanCartaService.getAllParticipanteCartaTmpById(num);
                Participante participanteNuevo = this.scanCartaService.getParticipante(cartaTemporal.getIdparticipante());
                if (participanteNuevo ==null){
                    continue;
                }
                String string  = participanteNuevo.getEdad();
                String[] parts = string.split("/");
                String anios =  parts[0];
                String meses =  parts[1];
                String dias  =  parts[2];
                ParticipanteCarta pc = new ParticipanteCarta();
                pc.setEdadyears(anios);
                pc.setEdadmeses(meses);
                pc.setEdaddias(dias);
                pc.setFechacarta(cartaTemporal.getFechacarta());
                pc.setFechaModifica(new Date());
                pc.setUsuarioModifica(SecurityContextHolder.getContext().getAuthentication().getName());
                pc.setRecordDate(fechaRegistro);
                pc.setRecordUser(userRegistro);
                pc.setDeviceid(computerName);
                pc.setRecordIp(computerIp);
                pc.setEstado('1');
                pc.setPasive('0');
                pc.setQuienfirma(cartaTemporal.getName1tutor());
                pc.setNombre2Firma(cartaTemporal.getName2tutor());
                pc.setApellido1Firma(cartaTemporal.getSurname1tutor());
                pc.setApellido2Firma(cartaTemporal.getSurname2tutor());
                pc.setNombre1testigo(cartaTemporal.getNombre1testigo().toUpperCase());
                String nom1Testigo = (cartaTemporal.getNombre1testigo() !="") ? cartaTemporal.getNombre2testigo().toUpperCase() : "";
                pc.setNombre2testigo(nom1Testigo);
                pc.setApellido1testigo(cartaTemporal.getApellido1testigo());
                String ape2Testigo = (cartaTemporal.getApellido2testigo() !="") ? cartaTemporal.getApellido2testigo().toUpperCase() : "";
                pc.setApellido2testigo(ape2Testigo);
                String o = ( cartaTemporal.getObservacion() !="" ) ? cartaTemporal.getObservacion().toUpperCase() : "";
                pc.setObservacion(o);
                pc.setPq_anulada("");
                pc.setProyecto(cartaTemporal.getProyecto());
                pc.setPersonal(cartaTemporal.getRecurso());
                Integer relfam = cartaTemporal.getRelfam();
                pc.setRelfam(relfam);
                boolean testgPresent = (cartaTemporal.isTestigopresent()==false) ? false : true;
                pc.setTestigopresent(testgPresent);
                pc.setTipoasentimiento(cartaTemporal.getTipoasentimiento());
                pc.setParticipante(participanteNuevo);
                pc.setPersonal(cartaTemporal.getRecurso());
                Version version = new Version();
                version.setIdversion(cartaTemporal.getVersion().getIdversion());
                pc.setVersion(version);
                pc.setContactoFuturo(cartaTemporal.isContactoFuturo());
                pc.setAsentimiento(cartaTemporal.getAsentimiento());
                contador++;
                scanCartaService.saveOrUpdateScanCarta(pc);
                List<DetalleParteTmp> detalleParteTmps = this.scanCartaService.getDetalleParteTmpById(cartaTemporal.getId());
                if (detalleParteTmps != null) {
                    for (DetalleParteTmp dparte : detalleParteTmps) {
                        Parte pr = new Parte();
                        DetalleParte dp = new DetalleParte();
                        dp.setParticipantecarta(pc);
                        dp.setAcepta(dparte.isAcepta());
                        pr.setIdparte(dparte.getParte().getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        dp.setRecordIp(computerIp);
                        scanCartaService.saveParteCarta(dp);
                    }
                }
                boolean existExtension = scanCartaService.isExistExtensionesById(cartaTemporal.getId());
                if (existExtension) {
                    //  guardar las extensiones
                    List<ExtensionesTmp> listextensionTmps = this.scanCartaService.getListExtensionTmpByParticipanteCartaId(cartaTemporal.getId());
                    for (ExtensionesTmp ObjExtenstemp:listextensionTmps){
                        Extensiones catalog_extension = new Extensiones();
                        ParticipanteExtension part_Extension = new ParticipanteExtension();
                        part_Extension.setDeviceid(computerName);
                        part_Extension.setRecordIp(computerIp);
                        part_Extension.setRecordDate(new Date());
                        part_Extension.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        part_Extension.setEstado('1');
                        part_Extension.setPasive('0');
                        part_Extension.setFechaExtension(ObjExtenstemp.getFechaExtension());
                        part_Extension.setNombre1Tutor(ObjExtenstemp.getNombre1Tutor());
                        part_Extension.setNombre2Tutor(ObjExtenstemp.getNombre2Tutor());
                        part_Extension.setApellido1Tutor(ObjExtenstemp.getApellido1Tutor());
                        part_Extension.setApellido2Tutor(ObjExtenstemp.getApellido2Tutor());
                        part_Extension.setTestigoPresente(ObjExtenstemp.isTestigoPresente());
                        part_Extension.setNombre1Testigo(ObjExtenstemp.getNombre1Testigo());
                        part_Extension.setNombre2Testigo(ObjExtenstemp.getNombre2Testigo());
                        part_Extension.setApellido1Testigo(ObjExtenstemp.getApellido1Testigo());
                        part_Extension.setApellido2Testigo(ObjExtenstemp.getApellido2Testigo());
                        part_Extension.setObservacion(ObjExtenstemp.getObservacion());
                        //Obj Participante_Carta
                        part_Extension.setParticipantecarta(pc);
                        // Obj Catalogo Extension
                        part_Extension.setExtensiones(ObjExtenstemp.getExtensiones());
                        // Guarda Participante_Extension
                        this.scanCartaService.saveParticpanteExtension(part_Extension);
                    }
                    System.out.println("Extension exist para eliminar con id: ".concat(""+cartaTemporal.getId()));
                    this.scanCartaService.Borrar_Participante_Carta_Extension(cartaTemporal.getId());
                }
                boolean SiEliminoPartes = this.scanCartaService.Borrar_Detalle_Partes_tmp(cartaTemporal.getId());
                if(SiEliminoPartes){
                    System.out.println("Participante_Carta_Tmp exist para eliminar con id: ".concat(""+cartaTemporal.getId()));
                    this.scanCartaService.Borrar_Participante_Carta_Tmp(cartaTemporal.getId());
                }
            }
            return JsonUtil.createJsonResponse("Registro transferidos: "+ contador);
        }catch (DataAccessException ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

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
