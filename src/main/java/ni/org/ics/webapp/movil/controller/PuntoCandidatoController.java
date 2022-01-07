package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.puntos.PuntoCandidato;
import ni.org.ics.webapp.service.PuntoCandidatoService;
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
 * Created by miguel on 29/12/2021.
 */
@Controller
@RequestMapping("/movil/*")
public class PuntoCandidatoController {

    private static final Logger logger = LoggerFactory.getLogger(PuntoCandidatoController.class);

    @Resource(name = "puntoCandidatoService")
    private PuntoCandidatoService puntoCandidatoService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "puntosCandidatos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PuntoCandidato> getPuntoCandidatos() {
        logger.info("Descargando toda la información de puntos candidatos");
        List<PuntoCandidato> respuestaList = puntoCandidatoService.getPuntosCandidatos();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param puntoCandidatos Objeto serializado de PuntoCandidato
     * @return String con el resultado
     */
    @RequestMapping(value = "puntosCandidatos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String savePuntoCandidatos(@RequestBody PuntoCandidato[] puntoCandidatos){
        logger.debug("Insertando/Actualizando puntos candidatos");
        if (puntoCandidatos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<PuntoCandidato> puntoCandidatoList = Arrays.asList(puntoCandidatos);
            for (PuntoCandidato puntoCandidato : puntoCandidatoList){
                puntoCandidatoService.saveOrUpdatePunto(puntoCandidato);
            }
        }
        return "Datos recibidos!";
    }
}

