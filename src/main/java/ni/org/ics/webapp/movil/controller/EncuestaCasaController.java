package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.survey.EncuestaCasa;
import ni.org.ics.webapp.service.EncuestaCasaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class EncuestaCasaController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaCasaController.class);

    @Resource(name = "encuestaCasaService")
    private EncuestaCasaService encuestaCasaService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasCasa", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaCasa> getEncuestasCasa() {
        logger.info("Descargando toda la informacion de formularios encuesta casa");
        List<EncuestaCasa> respuestaList = encuestaCasaService.getEncuestasCasa();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param encuestas Objeto serializado de EncuestaCasa
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasCasa", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestasCasa(@RequestBody EncuestaCasa[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta Casa");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaCasa> encuestasList = Arrays.asList(encuestas);
            for (EncuestaCasa encuesta : encuestasList){
                encuestaCasaService.saveOrUpdate(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
