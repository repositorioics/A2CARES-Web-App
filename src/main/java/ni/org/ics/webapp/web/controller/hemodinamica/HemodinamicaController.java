package ni.org.ics.webapp.web.controller.hemodinamica;

import com.google.gson.Gson;
import ni.org.ics.webapp.domain.catalogs.RangosFrecuenciasCardiacas;
import ni.org.ics.webapp.domain.catalogs.RangosPresion;
import ni.org.ics.webapp.domain.core.Barrio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.webapp.domain.hemodinamica.HemoDetalle;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.utils.DateUtil;
import ni.org.ics.webapp.dto.HemoDetalleDto;
import ni.org.ics.webapp.dto.ParticipanteHemoDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.BarrioService;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.ParticipanteService;
import ni.org.ics.webapp.service.hemodinamica.HemodinamicaService;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Created by Lizandro Serrano on 15/08/2024.
 */
@Controller
@RequestMapping("/hemo")
public class HemodinamicaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HemodinamicaController.class);
    @Resource(name = "hemodinamicaService")
    private HemodinamicaService hemodinamicaService;
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "participanteService")
    private ParticipanteService participanteService;
    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    @Resource(name = "barrioService")
    private BarrioService barrioService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model){
        model.addAttribute("obj", "pasando datos!");
        return "/hemodinamica/List";
    }

    @RequestMapping(value = "/listaHoja", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<DatosHemodinamica> listaHoja(@RequestParam(value = "parametro", required = true) String parametro)throws Exception {
        List<DatosHemodinamica> lista = null;
        try {
            lista = hemodinamicaService.getListadoHemoByID(parametro);
            return lista;
        } catch (Exception e) {
            return lista = null;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model){
        model.addAttribute("editando",false);
        model.addAttribute("tituloForm","Creando");
        model.addAttribute("obj",new DatosHemodinamica());
        List<Barrio> barrios = this.barrioService.getBarrios();
        model.addAttribute("barrios", barrios);
        List<MessageResource> centros = this.messageResourceService.getCatalogo("CAT_PUESTO_SALUD");
        model.addAttribute("centros",centros);
        return "/hemodinamica/datosForm";
    }

    @RequestMapping(value = "/edithemo/{idDatoHemo}", method = RequestMethod.GET)
    public String edithemo(@PathVariable(value = "idDatoHemo") String idDatoHemo, Model model) {
        model.addAttribute("tituloForm","Editando");
        model.addAttribute("editando",true);
        DatosHemodinamica obj = hemodinamicaService.getHemoDinamicaById(idDatoHemo);
        model.addAttribute("obj", obj);
        List<Barrio> barrios = this.barrioService.getBarrios();
        model.addAttribute("barrios", barrios);
        List<MessageResource> centros = this.messageResourceService.getCatalogo("CAT_CENTRO_SALUD");
        model.addAttribute("centros",centros);
        return "/hemodinamica/datosForm";
    }

    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) String parametro)
            throws Exception {
        try {
            ParticipanteHemoDto participantDto = new ParticipanteHemoDto();
            Participante participante = participanteService.getParticipanteByCodigo(parametro);
            if (participante != null) {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                participantDto.setEstado(procesos.getEstado());
                participantDto.setCodigoParticipante(participante.getCodigo());
                participantDto.setNombreCompleto(participante.getNombreCompleto());
                participantDto.setFechaNacimiento(DateUtil.DateToString(participante.getFechaNac(),"dd/MM/yyyy"));
                participantDto.setEdadParticipante(participante.getEdad());
                participantDto.setDireccion(participante.getCasa().getDireccion());
                participantDto.setCodigoBarrio(participante.getCasa().getBarrio().getCodigo());
                participantDto.setSexo(participante.getSexo());
                return JsonUtil.createJsonResponse(participantDto);
            } else {
                return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado: " +parametro);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveHemodinamica(
            @RequestParam(value = "codigoParticipante") String codigoParticipante
            , @RequestParam(value = "idDatoHemo", required = true) String idDatoHemo
            , @RequestParam(value = "expediente", required = true) String expediente
            , @RequestParam(value = "barrio", required = true) int barrio
            , @RequestParam(value = "fueraSector", required = false, defaultValue = "") String fueraSector
            , @RequestParam(value = "direccion", required = true) String direccion
            , @RequestParam(value = "telefono", required = true) String telefono
            , @RequestParam(value = "fconsulta", required = true) String fconsulta
            , @RequestParam(value = "fie", required = true) String fie
            , @RequestParam(value = "talla", required = true) String talla
            , @RequestParam(value = "peso", required = true) String peso
            , @RequestParam(value = "numPagina", required = true) int numPagina
            , @RequestParam(value = "numParametro", required = true) int numParametro
            , @RequestParam(value = "numEvento", required = true) String numEvento
            , @RequestParam(value = "chkpositivo", required = true) String chkpositivo
            , @RequestParam(value = "unidadSalud", required = true) int unidadSalud
    ) throws Exception {
        DatosHemodinamica datosHemodinamica = new DatosHemodinamica();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigoParticipante);
        if (participante != null) {
            String result = obtenerEdad(participante.getFechaNac(), DateUtil.StringToDate(fconsulta,"dd/MM/yyyy"));
            String[]part = result.split(" ");
            String part1 = part[0];
            String part2 = part[1];
            Integer num = Integer.valueOf(part1);
            if (idDatoHemo.equals("")){
                if (num <= 18){
                    RangosPresion presion = hemodinamicaService.ObtenerRangosPresion(participante.getSexo(), num, part2);
                    datosHemodinamica.setSdMin(presion.getPsdmin());
                    datosHemodinamica.setSdMed(presion.getPsdmed());
                    datosHemodinamica.setSdMax(presion.getPsdmax());
                    datosHemodinamica.setPamMin(presion.getPammin());
                    datosHemodinamica.setPamMed(presion.getPammed());
                    datosHemodinamica.setPamMax(presion.getPammax());
                    RangosFrecuenciasCardiacas freC = hemodinamicaService.ObtenerFCardiaca(part2, num);
                    datosHemodinamica.setFcMin(Integer.valueOf(freC.getFcMinima()));
                    datosHemodinamica.setFcMed(Integer.valueOf(freC.getFcMedia()));
                    datosHemodinamica.setFcProm(Integer.valueOf(freC.getFcPromedio()));
                    datosHemodinamica.setFrMin(Integer.valueOf(freC.getFrMinima()));
                    datosHemodinamica.setFrMax(Integer.valueOf(freC.getFrMaxima()));
                }
                datosHemodinamica.setParticipante(participante);
                datosHemodinamica.setSector(barrio);
                datosHemodinamica.setUnidadSalud(unidadSalud);
                datosHemodinamica.setBarrioF(fueraSector);
                datosHemodinamica.setDireccion(direccion);
                datosHemodinamica.setTelefono(telefono);
                datosHemodinamica.setnExpediente(expediente);
                Date fechaConsulta = DateUtil.StringToDate(fconsulta, "dd/MM/yyyy");
                datosHemodinamica.setFecha(fechaConsulta);
                Date fechaEnf = DateUtil.StringToDate(fie, "dd/MM/yyyy");
                datosHemodinamica.setFie(fechaEnf);
                datosHemodinamica.setPeso(peso);
                Double size = Double.parseDouble(talla);
                datosHemodinamica.setTalla(size);
                datosHemodinamica.setNumeroPagina(numPagina);
                datosHemodinamica.setNumParametros(numParametro);
                datosHemodinamica.setNumeroEvento(numEvento);
                char positivo = (chkpositivo.equals("1") ? '1' : '0');
                datosHemodinamica.setPositivo(positivo);
                double valorASC = getAreaSupCorp(peso, size);
                datosHemodinamica.setAsc(Double.valueOf(round(valorASC,2)));
                double valorImc = getIMC(peso, size);
                datosHemodinamica.setImc(Double.valueOf(round(valorImc,2)));
                int diasEnf = (int) diasEntreDosFechas(fechaConsulta,fechaEnf);
                datosHemodinamica.setDiasenf(diasEnf);
                String fn = DateUtil.DateToString(participante.getFechaNac(),"dd/MM/yyyy");
                datosHemodinamica.setEdad(calculateAge(fn,fconsulta));
                datosHemodinamica.setPasive('0');
                datosHemodinamica.setEstado('1');
                datosHemodinamica.setRecordDate(new Date());
                datosHemodinamica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                datosHemodinamica.setDeviceid("NicaUmich05");
            }else{
                if (num <= 18){
                    RangosPresion presion = hemodinamicaService.ObtenerRangosPresion(participante.getSexo(), num, part2);
                    datosHemodinamica.setSdMin(presion.getPsdmin());
                    datosHemodinamica.setSdMed(presion.getPsdmed());
                    datosHemodinamica.setSdMax(presion.getPsdmax());
                    datosHemodinamica.setPamMin(presion.getPammin());
                    datosHemodinamica.setPamMed(presion.getPammed());
                    datosHemodinamica.setPamMax(presion.getPammax());
                    RangosFrecuenciasCardiacas freC = hemodinamicaService.ObtenerFCardiaca(part2, num);
                    datosHemodinamica.setFcMin(Integer.valueOf(freC.getFcMinima()));
                    datosHemodinamica.setFcMed(Integer.valueOf(freC.getFcMedia()));
                    datosHemodinamica.setFcProm(Integer.valueOf(freC.getFcPromedio()));
                    datosHemodinamica.setFrMin(Integer.valueOf(freC.getFrMinima()));
                    datosHemodinamica.setFrMax(Integer.valueOf(freC.getFrMaxima()));
                }
                datosHemodinamica.setIdDatoHemo(idDatoHemo);
                datosHemodinamica.setParticipante(participante);
                datosHemodinamica.setSector(barrio);
                datosHemodinamica.setUnidadSalud(unidadSalud);
                datosHemodinamica.setBarrioF(fueraSector);
                datosHemodinamica.setDireccion(direccion);
                datosHemodinamica.setTelefono(telefono);
                datosHemodinamica.setnExpediente(expediente);
                Date fechaConsulta = DateUtil.StringToDate(fconsulta, "dd/MM/yyyy");
                datosHemodinamica.setFecha(fechaConsulta);
                Date fechaEnf = DateUtil.StringToDate(fie, "dd/MM/yyyy");
                datosHemodinamica.setFie(fechaEnf);
                datosHemodinamica.setPeso(peso);
                Double size = Double.parseDouble(talla);
                datosHemodinamica.setTalla(size);
                datosHemodinamica.setNumeroPagina(numPagina);
                datosHemodinamica.setNumParametros(numParametro);
                datosHemodinamica.setNumeroEvento(numEvento);
                char positivo = (chkpositivo.equals("1") ? '1' : '0');
                datosHemodinamica.setPositivo(positivo);
                double valorASC = getAreaSupCorp(peso, size);
                datosHemodinamica.setAsc(Double.valueOf(round(valorASC,2)));
                double valorImc = getIMC(peso, size);
                datosHemodinamica.setImc(Double.valueOf(round(valorImc,2)));
                int diasEnf = (int) diasEntreDosFechas(fechaConsulta,fechaEnf);
                datosHemodinamica.setDiasenf(diasEnf);
                String fn = DateUtil.DateToString(participante.getFechaNac(),"dd/MM/yyyy");
                datosHemodinamica.setEdad(calculateAge(fn, fconsulta));
                datosHemodinamica.setPasive('0');
                datosHemodinamica.setEstado('1');
                datosHemodinamica.setRecordDate(new Date());
                datosHemodinamica.setDeviceid("NicaUmich05");
                datosHemodinamica.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            }
                this.hemodinamicaService.saveOrUpdate(datosHemodinamica);
        } else {
            return JsonUtil.createJsonResponse("Participante no encontrado.");
        }
        return JsonUtil.createJsonResponse(datosHemodinamica);
    }



    @RequestMapping(value = "addDetails/{idDatoHemo}", method = RequestMethod.GET)
    public String adddetails(@PathVariable(value = "idDatoHemo") String idDatoHemo, ModelMap model) {
            List<MessageResource> nivelConciencia = messageResourceService.getCatalogo("NIVELCONCIENCIA");
            model.addAttribute("nivelConciencia", nivelConciencia);
            List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
            model.addAttribute("pulsoCalidad", pulsoCalidad);
            List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
            model.addAttribute("extremidades", extremidades);
            List<MessageResource> clasificacion = messageResourceService.getCatalogo("CLASIFICACIONDENGUE");
            model.addAttribute("clasificacion", clasificacion);
            List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
            model.addAttribute("llenadoCapilar", llenadoCapilar);
            model.addAttribute("idDatoHemo", idDatoHemo);
            List<MessageResource> diuresis = messageResourceService.getCatalogo("DIURESIS");
            model.addAttribute("diuresis", diuresis);
            model.addAttribute("editando", false);
            HashSet<String> cat_cargos = new HashSet<String>();
            cat_cargos.add("CAT_CARGO_2");
            cat_cargos.add("CAT_CARGO_3");
            cat_cargos.add("CAT_CARGO_4");
            List<Personal> personaValida = this.hemodinamicaService.getPersonal(cat_cargos);
            model.addAttribute("personaValida", personaValida);
            DatosHemodinamica datos = hemodinamicaService.getHemoDinamicaById(idDatoHemo);
            model.addAttribute("datos", datos);
            model.addAttribute("tituloForm","Crear");
            List<HemoDetalle> contParams = hemodinamicaService.NumeroHemoDet(idDatoHemo);
            model.addAttribute("numParameter", (datos.getNumParametros() == null) ? "0" : datos.getNumParametros());
            model.addAttribute("contParams", contParams.size());
            model.addAttribute("obj", new HemoDetalle());
        return "/hemodinamica/detalleForm";
    }


    @RequestMapping(value = "editDetail/{idHemoDetalle}", method = RequestMethod.GET)
    public String editDetail(@PathVariable(value = "idHemoDetalle") String idHemoDetalle, ModelMap model){
        HemoDetalle obj = hemodinamicaService.getByHemoDetalleId(idHemoDetalle);
        List<MessageResource> nivelConciencia = messageResourceService.getCatalogo("NIVELCONCIENCIA");
        model.addAttribute("nivelConciencia", nivelConciencia);
        List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
        model.addAttribute("pulsoCalidad", pulsoCalidad);
        List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
        model.addAttribute("extremidades", extremidades);
        List<MessageResource> clasificacion = messageResourceService.getCatalogo("CLASIFICACIONDENGUE");
        model.addAttribute("clasificacion", clasificacion);
        List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
        model.addAttribute("llenadoCapilar", llenadoCapilar);
        model.addAttribute("datos", hemodinamicaService.getHemoDinamicaById(obj.getDatoshemodinamica().getIdDatoHemo()));
        List<MessageResource> diuresis = messageResourceService.getCatalogo("DIURESIS");
        model.addAttribute("diuresis", diuresis);
        model.addAttribute("editando", true);
        HashSet<String> cat_cargos = new HashSet<String>();
        cat_cargos.add("CAT_CARGO_2");
        cat_cargos.add("CAT_CARGO_3");
        cat_cargos.add("CAT_CARGO_4");
        List<Personal> personaValida = this.hemodinamicaService.getPersonal(cat_cargos);
        model.addAttribute("personaValida", personaValida);
        model.addAttribute("tituloForm","Editar");
        model.addAttribute("obj",obj);
        return "/hemodinamica/detalleForm";
    }


    @RequestMapping(value = "saveDetail", method = RequestMethod.POST)
    public ResponseEntity<String> saveDetail(
            @RequestParam(value = "idHemoDetalle", required = false) String idHemoDetalle
            , @RequestParam(value = "signo", required = true) String signo
            , @RequestParam(value = "fecha", required = true) String fecha
            , @RequestParam(value = "hora", required = true) String hora
            , @RequestParam(value = "nivelConciencia", required = true) String nivelConciencia
            , @RequestParam(value = "ps", required = true) String ps
            , @RequestParam(value = "pd", required = true) String pd
            , @RequestParam(value = "fc", required = true) String fc
            , @RequestParam(value = "fr", required = true) String fr
            , @RequestParam(value = "tc") String tc
            , @RequestParam(value = "sa") String sa
            , @RequestParam(value = "extremidades", required = true) String extremidades
            , @RequestParam(value = "llenadoCapilar", required = true) String llenadoCapilar
            , @RequestParam(value = "pulsoCalidad", required = true) String pulsoCalidad
            , @RequestParam(value = "diuresis", required = true) String diuresis
            , @RequestParam(value = "densidadUrinaria", required = true) String densidadUrinaria
            , @RequestParam(value = "personaValida", required = true) int personaValida
            , @RequestParam(value = "idDatoHemo", required = true) String idDatoHemo
            , @RequestParam(value = "dx") String dx
            , @RequestParam(value = "cantidadOrina", required = false) String cantidadOrina
            , @RequestParam(value = "cargas_iv", required = false) String cargas_iv
            , @RequestParam(value = "sro", required = false) String sro
    ) {
        try {
            HemoDetalle obj = new HemoDetalle();
            DatosHemodinamica datos = hemodinamicaService.getHemoDinamicaById(idDatoHemo);
            if (datos != null) {
                if (idHemoDetalle.equals("")) {
                    if (!hemodinamicaService.SiExisteHemo(idDatoHemo, DateUtil.StringToDate(fecha, "dd/MM/yyyy"), hora)) {
                        obj.setSigno(signo);
                        obj.setDx(dx);
                        obj.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                        obj.setHora(hora);
                        obj.setNivelConciencia(nivelConciencia);
                        obj.setPs(ps);
                        Integer psistolica = Integer.parseInt(ps);
                        Integer pdiastolica = Integer.parseInt(pd);
                        Integer diferencia = PAM(psistolica, pdiastolica);
                        obj.setPp("" + diferencia);
                        obj.setPd(pd);
                        double aProm = ((pdiastolica * 2) + psistolica) / 3;
                        obj.setPam("" + Math.round(aProm));
                        obj.setFc(fc);
                        obj.setFr(fr);
                        obj.setTc(tc);
                        obj.setSa(sa);
                        obj.setExtremidades(extremidades);
                        obj.setLlenadoCapilar(llenadoCapilar);
                        obj.setPulsoCalidad(pulsoCalidad);
                        obj.setDiuresis(diuresis);
                        obj.setDensidadUrinaria(densidadUrinaria);
                        if (!cantidadOrina.equals("")) {
                            BigDecimal cantiOrina = BigDecimal.valueOf(Double.valueOf(cantidadOrina));
                            obj.setCantidadOrina(cantiOrina);
                        }
                        if (!cargas_iv.equals("")) {
                            BigDecimal carga_intravenosa = BigDecimal.valueOf(Double.valueOf(cargas_iv));
                            obj.setCargasIV(carga_intravenosa);
                        }
                        if (!sro.equals("")) {
                            BigDecimal suero_oral = BigDecimal.valueOf(Double.valueOf(sro));
                            obj.setSro(suero_oral);
                        }
                        obj.setPersonaValida(personaValida);
                        obj.setDatoshemodinamica(datos);
                        obj.setEstado('1');
                        obj.setPasive('0');
                        obj.setRecordDate(new Date());
                        obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        obj.setDeviceid("NicaUmich05");
                        this.hemodinamicaService.saveDetailHemo(obj);
                    } else {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("msj", "El Detalle ya existe.");
                        return JsonUtil.createJsonResponse(map);
                    }
                } else {
                    obj.setIdHemoDetalle(idHemoDetalle);
                    obj.setSigno(signo);
                    obj.setDx(dx);
                    obj.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                    obj.setHora(hora);
                    obj.setNivelConciencia(nivelConciencia);
                    obj.setPs(ps);
                    Integer psistolica = Integer.parseInt(ps);
                    Integer pdiastolica = Integer.parseInt(pd);
                    Integer diferencia = PAM(psistolica, pdiastolica);
                    obj.setPp("" + diferencia);
                    obj.setPd(pd);
                    double aProm = ((pdiastolica * 2) + psistolica) / 3;
                    obj.setPam("" + Math.round(aProm));
                    obj.setFc(fc);
                    obj.setFr(fr);
                    obj.setTc(tc);
                    obj.setSa(sa);
                    obj.setExtremidades(extremidades);
                    obj.setLlenadoCapilar(llenadoCapilar);
                    obj.setPulsoCalidad(pulsoCalidad);
                    obj.setDiuresis(diuresis);
                    obj.setDensidadUrinaria(densidadUrinaria);
                    if (!cantidadOrina.equals("")) {
                        BigDecimal cantiOrina = BigDecimal.valueOf(Double.valueOf(cantidadOrina));
                        obj.setCantidadOrina(cantiOrina);
                    }
                    if (!cargas_iv.equals("")) {
                        BigDecimal carga_intravenosa = BigDecimal.valueOf(Double.valueOf(cargas_iv));
                        obj.setCargasIV(carga_intravenosa);
                    }
                    if (!sro.equals("")) {
                        BigDecimal suero_oral = BigDecimal.valueOf(Double.valueOf(sro));
                        obj.setSro(suero_oral);
                    }
                    obj.setPersonaValida(personaValida);
                    obj.setDatoshemodinamica(datos);
                    obj.setEstado('1');
                    obj.setPasive('0');
                    obj.setRecordDate(new Date());
                    obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    obj.setDeviceid("NicaUmich05");
                this.hemodinamicaService.saveDetailHemo(obj);
                }
            }else {
                return JsonUtil.createJsonResponse("No se encontró el registro!");
            }
            return JsonUtil.createJsonResponse(obj);
        } catch (Exception e) {
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<String, String>();
            map.put("msj", e.toString());
            return JsonUtil.createJsonResponse(map);
        }
    }


    @RequestMapping(value = "/listDetailsHemo/{idDatoHemo}", method = RequestMethod.GET)
    public ModelAndView listDetailsHemo(@PathVariable(value = "idDatoHemo") String idDatoHemo) throws ParseException {
        ModelAndView mv = new ModelAndView();
        try {
            List<HemoDetalleDto> dtos = hemodinamicaService.getListHemoDetalleDto(idDatoHemo);
            mv.addObject("dtos", dtos);
            DatosHemodinamica obj = hemodinamicaService.getHemoDinamicaById(idDatoHemo);
            mv.addObject("obj",obj);
            List<MessageResource> nivel = messageResourceService.getCatalogo("NIVELCONCIENCIA");
            mv.addObject("nivel", nivel);
            List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
            mv.addObject("extremidades", extremidades);
            List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
            mv.addObject("pulsoCalidad", pulsoCalidad);
            List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
            mv.addObject("llenadoCapilar", llenadoCapilar);
            mv.addObject("codigo", obj.getParticipante().getCodigo());
            mv.addObject("nombre", obj.getParticipante().getNombreCompleto());
            mv.addObject("edad", obj.getParticipante().getEdad());
            mv.addObject("fechanac", obj.getParticipante().getFechaNac());
            mv.addObject("expediente", obj.getnExpediente());
            mv.addObject("idDatoHemo", obj.getIdDatoHemo());
            mv.setViewName("/hemodinamica/detalleList");
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }

    List<MessageResource> messagediuresis = new ArrayList<MessageResource>();
    @RequestMapping(value = "/ViewResutl", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String BuscarResultado(@RequestParam(value = "idHemoDetalle") String idHemoDetalle) throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        HemoDetalle obj = hemodinamicaService.getByHemoDetalleId(idHemoDetalle);
        map.put("ps", obj.getPs());
        map.put("pd", obj.getPd());
        map.put("pp", obj.getPp());
        map.put("pam", obj.getPam());
        map.put("fcardi", obj.getFc());
        map.put("fr", obj.getFr());
        map.put("tc", obj.getTc());
        map.put("sa", obj.getSa());
        messagediuresis = messageResourceService.getMensajeByCatalogAndCatKeys(obj.getDiuresis(), "DIURESIS");
        map.put("diuresis", getDescripcionCatalogo(obj.getDiuresis(), "DIURESIS"));
        Personal personal = this.hemodinamicaService.getPersonalById(obj.getPersonaValida());
        map.put("personaValida", personal.getIdPersona().toString() + " - " + personal.getNombre());
        map.put("densidadU", (obj.getDensidadUrinaria() != null ? obj.getDensidadUrinaria() : "-"));
        map.put("cantidadOrina", (obj.getCantidadOrina() == null ? "" : obj.getCantidadOrina().toString()));
        map.put("cargasIV", (obj.getCargasIV() == null ? "" : obj.getCargasIV().toString()));
        map.put("sro", (obj.getSro() == null ? "" : obj.getSro().toString()));
        String jsonResponse;
        jsonResponse = new Gson().toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }

    private String getDescripcionCatalogo(String codigo, String catroot) {
        for (MessageResource rnv : messagediuresis) {
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }
    public static Integer PAM(Integer ps, Integer pd) {
        Integer diferencia = (ps > pd)?(ps - pd):(pd - ps);
        return diferencia;
    }
    private static String calculateAge(String fechaNac, String fconsulta){
        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechanacimiento = LocalDate.parse(fechaNac, fmt);
        LocalDate fechaconsulta = LocalDate.parse(fconsulta, fmt);
        Period period = Period.between(fechanacimiento, fechaconsulta);
        return String.format("%s años, %s meses, y %s dias", period.getYears(), period.getMonths(), period.getDays());
    }
    public static String obtenerEdad(Date fechan, Date fconsulta) {
        GregorianCalendar today = new GregorianCalendar();
        today.setTime(fconsulta);
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        fechaNacimiento.setTime(fechan);

        int age = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int month = (age)*12 + today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);

        if(today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)){
            month = month - 1;
        }

        if(month == 0) {
            Long tDias = (today.getTimeInMillis() - fechaNacimiento.getTimeInMillis())  / (1000 * 60 * 60 * 24);
            return new StringBuffer().append(tDias).append(" dias").toString();
        }
        else if(age == 0) {
            age = today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            if(age == 0) {
                age = today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                return new StringBuffer().append(age).append(" dias").toString();
            }else {
                int diaFechaActual = today.get(Calendar.DAY_OF_MONTH);
                int diaFechaNac = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                if (diaFechaActual < diaFechaNac) {
                    age = age - 1;
                    return new StringBuffer().append(age).append(" meses").toString();
                } else {
                    return new StringBuffer().append(age).append(" meses").toString();
                }

            }
        } else if (month > 0 && month < 12) {
            return new StringBuffer().append(month).append(" meses").toString();

        }else {
            if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
            return new StringBuffer().append(age).append(" años").toString();
        }
    }
    public static double getAreaSupCorp(String peso, double talla) {
        double p = Double.parseDouble(peso);
        return Math.sqrt((p * talla) / 3600);
    }
    public static double getIMC(String peso, double altura) {
        double alturaMetro = (altura) / 100;
        double p = Double.parseDouble(peso);
        double cuadrado = (alturaMetro * alturaMetro);
        double imc = p / cuadrado;
        return imc;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static long diasEntreDosFechas(Date fechaDesde, Date fechaHasta) {
        long startTime = fechaDesde.getTime();
        long endTime = fechaHasta.getTime();
        long diasDesde = (long) Math.floor(startTime / (1000 * 60 * 60 * 24));
        long diasHasta = (long) Math.floor(endTime / (1000 * 60 * 60 * 24));
        long dias = diasHasta - diasDesde;
        return dias+1;
    }
}
