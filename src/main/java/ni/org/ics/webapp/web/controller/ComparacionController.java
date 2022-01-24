package ni.org.ics.webapp.web.controller;

import ni.org.ics.webapp.dto.DiferenciaParteCartaDto;
import ni.org.ics.webapp.dto.ComparacionCartasDto;
import ni.org.ics.webapp.dto.ComparacionMuestrasMA;
import ni.org.ics.webapp.dto.ComparacionRelFamCartasDto;
import ni.org.ics.webapp.service.ComparacionCartasService;
import ni.org.ics.webapp.service.ComparacionMuestrasService;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Created by miguel on 7/1/2022.
 */
@Controller
@RequestMapping("/comparacion/*")
public class ComparacionController {

    private static final Logger logger = LoggerFactory.getLogger(ComparacionController.class);

    private static final String marcarDiferencia = "<span class='badge badge-danger'>%s</span>";

    @Resource(name = "comparacionCartasService")
    private ComparacionCartasService comparacionCartasService;

    @Resource(name = "comparacionMuestrasService")
    private ComparacionMuestrasService comparacionMuestrasService;

    @RequestMapping(value = "cartas", method = RequestMethod.GET)
    public String letters(Model model) throws ParseException {
        logger.debug("Mostrando diferencias de cartas consentimiento en JSP");

        return "comparison/letters";
    }

    @RequestMapping(value = "getCartasPartes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCartasPartes() throws ParseException {
        try {
            logger.debug("buscar diferencias en partes de cartas");
            List<DiferenciaParteCartaDto> diferenciaParteCartaDtos = comparacionCartasService.getDiferenciasPartesCartas();
            for(DiferenciaParteCartaDto diferencia : diferenciaParteCartaDtos) {
                if (!diferencia.getAceptaParteACc().equalsIgnoreCase(diferencia.getAceptaParteASc())) {
                    diferencia.setAceptaParteACc("<span class='badge badge-danger'>"+diferencia.getAceptaParteACc()+"</span>");
                    diferencia.setAceptaParteASc("<span class='badge badge-danger'>"+diferencia.getAceptaParteASc()+"</span>");
                }
                if (!diferencia.getAceptaParteBCc().equalsIgnoreCase(diferencia.getAceptaParteBSc())) {
                    diferencia.setAceptaParteBCc("<span class='badge badge-danger'>"+diferencia.getAceptaParteBCc()+"</span>");
                    diferencia.setAceptaParteBSc("<span class='badge badge-danger'>"+diferencia.getAceptaParteBSc()+"</span>");
                }
                if (!diferencia.getAceptaParteCCc().equalsIgnoreCase(diferencia.getAceptaParteCSc())) {
                    diferencia.setAceptaParteCCc("<span class='badge badge-danger'>"+diferencia.getAceptaParteCCc()+"</span>");
                    diferencia.setAceptaParteCSc("<span class='badge badge-danger'>"+diferencia.getAceptaParteCSc()+"</span>");
                }
                if (!diferencia.getAceptaContactoFuturoCc().equalsIgnoreCase(diferencia.getAceptaContactoFuturoSc())) {
                    diferencia.setAceptaContactoFuturoCc("<span class='badge badge-danger'>"+diferencia.getAceptaContactoFuturoCc()+"</span>");
                    diferencia.setAceptaContactoFuturoSc("<span class='badge badge-danger'>"+diferencia.getAceptaContactoFuturoSc()+"</span>");
                }
                if (!diferencia.getAsentimientoVerbalCc().equalsIgnoreCase(diferencia.getAsentimientoVerbalSc())) {
                    diferencia.setAsentimientoVerbalCc("<span class='badge badge-danger'>"+diferencia.getAsentimientoVerbalCc()+"</span>");
                    diferencia.setAsentimientoVerbalSc("<span class='badge badge-danger'>"+diferencia.getAsentimientoVerbalSc()+"</span>");
                }
                diferencia.setEdadActualMeses(diferencia.getEdadActualMeses()/12);
                diferencia.setEdadMeses(diferencia.getEdadMeses()/12);
            }

            return JsonUtil.createJsonResponse(diferenciaParteCartaDtos);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCartasSinDigitar", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCartasSinDigitar() throws ParseException {
        try {
            logger.debug("buscar diferencias de cartas sin digitar");
            List<ComparacionCartasDto> cartas = comparacionCartasService.getConsentimientosSinCarta();
            return JsonUtil.createJsonResponse(cartas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCartasRelFam", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getDiferenciasRelFam() throws ParseException {
        try {
            logger.debug("buscar diferencias de cartas sin digitar");
            List<ComparacionRelFamCartasDto> cartas = comparacionCartasService.getDiferenciasRelFam();
            for(ComparacionRelFamCartasDto diferencia : cartas){
                //comparar primer nombre
                if (diferencia.getNombre1TutorC() == null) {
                    diferencia.setNombre1TutorC(String.format(marcarDiferencia, " "));
                    diferencia.setNombre1TutorS(String.format(marcarDiferencia, diferencia.getNombre1TutorS()));
                } else if (diferencia.getNombre1TutorS() == null) {
                    diferencia.setNombre1TutorS(String.format(marcarDiferencia, " "));
                    diferencia.setNombre1TutorC(String.format(marcarDiferencia, diferencia.getNombre1TutorC()));
                } else if (!diferencia.getNombre1TutorC().equalsIgnoreCase(diferencia.getNombre1TutorS())) {
                    diferencia.setNombre1TutorC(String.format(marcarDiferencia, diferencia.getNombre1TutorC()));
                    diferencia.setNombre1TutorS(String.format(marcarDiferencia, diferencia.getNombre1TutorS()));
                }
                //comparar segundo nombre
                if (diferencia.getNombre2TutorC() == null && diferencia.getNombre2TutorS() != null) {
                    diferencia.setNombre2TutorC(String.format(marcarDiferencia, " "));
                    diferencia.setNombre2TutorS(String.format(marcarDiferencia, diferencia.getNombre2TutorS()));
                } else if (diferencia.getNombre2TutorS() == null && diferencia.getNombre2TutorC() != null) {
                    diferencia.setNombre2TutorS(String.format(marcarDiferencia, " "));
                    diferencia.setNombre2TutorC(String.format(marcarDiferencia, diferencia.getNombre2TutorC()));
                } else if (diferencia.getNombre2TutorC() != null && (!diferencia.getNombre2TutorC().equalsIgnoreCase(diferencia.getNombre2TutorS()))) {
                    diferencia.setNombre2TutorC(String.format(marcarDiferencia, diferencia.getNombre2TutorC()));
                    diferencia.setNombre2TutorS(String.format(marcarDiferencia, diferencia.getNombre2TutorS()));
                }
                //comparar primer apellido
                if (diferencia.getApellido1TutorC() == null) {
                    diferencia.setApellido1TutorC(String.format(marcarDiferencia, " "));
                    diferencia.setApellido1TutorS(String.format(marcarDiferencia, diferencia.getApellido1TutorS()));
                } else if (diferencia.getApellido1TutorS() == null) {
                    diferencia.setApellido1TutorS(String.format(marcarDiferencia, " "));
                    diferencia.setApellido1TutorC(String.format(marcarDiferencia, diferencia.getApellido1TutorC()));
                } else if (!diferencia.getApellido1TutorC().equalsIgnoreCase(diferencia.getApellido1TutorS())) {
                    diferencia.setApellido1TutorC(String.format(marcarDiferencia, diferencia.getApellido1TutorC()));
                    diferencia.setApellido1TutorS(String.format(marcarDiferencia, diferencia.getApellido1TutorS()));
                }
                //comparar segundo apellido
                if (diferencia.getApellido2TutorC() == null && diferencia.getApellido2TutorS() != null) {
                    diferencia.setApellido2TutorC(String.format(marcarDiferencia, " "));
                    diferencia.setApellido2TutorS(String.format(marcarDiferencia, diferencia.getApellido2TutorS()));
                } else if (diferencia.getApellido2TutorS() == null && diferencia.getApellido2TutorC() != null) {
                    diferencia.setApellido2TutorS(String.format(marcarDiferencia, " "));
                    diferencia.setApellido2TutorC(String.format(marcarDiferencia, diferencia.getApellido2TutorC()));
                } else if (diferencia.getApellido2TutorC() != null && (!diferencia.getApellido2TutorC().equalsIgnoreCase(diferencia.getApellido2TutorS()))) {
                    diferencia.setApellido2TutorC(String.format(marcarDiferencia, diferencia.getApellido2TutorC()));
                    diferencia.setApellido2TutorS(String.format(marcarDiferencia, diferencia.getApellido2TutorS()));
                }
                //poner nomres completos
                diferencia.setQuienFirmaC(diferencia.getNombre1TutorC()+ " "+diferencia.getNombre2TutorC()+ " "+diferencia.getApellido1TutorC()+" "+diferencia.getApellido2TutorC());
                diferencia.setQuienFirmaS(diferencia.getNombre1TutorS()+ " "+diferencia.getNombre2TutorS()+ " "+diferencia.getApellido1TutorS()+" "+diferencia.getApellido2TutorS());
                //comparar relacion familiar
                if (diferencia.getRelacionFamiliarC() == null) {
                    diferencia.setRelacionFamiliarC(String.format(marcarDiferencia, " "));
                    diferencia.setRelacionFamiliarS(String.format(marcarDiferencia, diferencia.getRelacionFamiliarS()));
                } else if (diferencia.getRelacionFamiliarS() == null) {
                    diferencia.setRelacionFamiliarS(String.format(marcarDiferencia, " "));
                    diferencia.setRelacionFamiliarC(String.format(marcarDiferencia, diferencia.getRelacionFamiliarC()));
                } else if(!diferencia.getRelacionFamiliarC().equalsIgnoreCase(diferencia.getRelacionFamiliarS())){
                    diferencia.setRelacionFamiliarC(String.format(marcarDiferencia, diferencia.getRelacionFamiliarC()));
                    diferencia.setRelacionFamiliarS(String.format(marcarDiferencia, diferencia.getRelacionFamiliarS()));
                }
            }
            return JsonUtil.createJsonResponse(cartas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
/*
    ccalidad.comprojo1=Tubos Rojos del supervisor que no tienen las estaciones
    ccalidad.comprojo2=Tubos Rojos del supervisor que no tiene el laboratorio
    ccalidad.comprojo3=Tubos Rojos de las estaciones que no tiene el supervisor
    ccalidad.comprojo4=Tubos Rojos de las estaciones que no tiene el laboratorio
    ccalidad.comprojo5=Tubos Rojos de laboratorio que no tiene el supervisor
    ccalidad.comprojo6=Tubos Rojos de laboratorio que no tienen las estaciones
    */
    @RequestMapping(value = "muestras-ma", method = RequestMethod.GET)
    public String samples(Model model) throws ParseException {
        logger.debug("Mostrando diferencias en muestras muestreo anual en JSP");

        return "comparison/samples";
    }

    @RequestMapping(value = "getCompSeroSupNoEstacionesHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroSupEstacionesHoy() throws ParseException {
        try {
            logger.debug("buscar diferencias de tubos Rojos del supervisor que no tienen las estaciones");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroSupNoEstacionesHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCompSeroSupNoLaboHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroSupLaboHoy() throws ParseException {
        try {
            logger.debug("Diferencias de tubos Rojos del supervisor que no tiene el laboratorio");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroSupNoLaboHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCompSeroEstacionesNoSupHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroEstacionesNoSupHoy() throws ParseException {
        try {
            logger.debug("Diferencias de tubos Rojos de las estaciones que no tiene el supervisor");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroEstacionesNoSupHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCompSeroEstacionesNoLabHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroEstacionesNoLabHoy() throws ParseException {
        try {
            logger.debug("Diferencias de tubos Rojos de las estaciones que no tiene el laboratorio");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroEstacionesNoLabHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCompSeroLabNoSupHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroLabNoSupHoy() throws ParseException {
        try {
            logger.debug("Diferencias de laboratorio que no tiene el supervisor");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroLabNoSupHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCompSeroLabNoEstHoy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getCompSeroLabNoEstHoy() throws ParseException {
        try {
            logger.debug("Diferencias de tubos Rojos de laboratorio que no tienen las estaciones");
            List<ComparacionMuestrasMA> muestras = comparacionMuestrasService.getCompSeroLabNoEstHoy();
            return JsonUtil.createJsonResponse(muestras);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
