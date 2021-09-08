package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.survey.EncuestaParticipante;
import ni.org.ics.webapp.service.EncuestaParticipanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class EncuestaParticipanteController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaParticipanteController.class);

    @Resource(name = "encuestaParticipanteService")
    private EncuestaParticipanteService encuestaParticipanteService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasParticipante", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipante> getEncuestasParticipante() {
        logger.info("Descargando toda la informacion de formularios encuesta Participante");
        List<EncuestaParticipante> respuestaList = encuestaParticipanteService.getEncuestasParticipante();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param encuestas Objeto serializado de EncuestaParticipante
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasParticipante", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestasParticipante(@RequestBody EncuestaParticipante[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta Participante");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaParticipante> encuestasList = Arrays.asList(encuestas);
            for (EncuestaParticipante encuesta : encuestasList){
                encuestaParticipanteService.saveOrUpdate(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
