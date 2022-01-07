package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.Serologia.Serologia;
import ni.org.ics.webapp.service.Serologia.SerologiaService;
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
 * Created by miguel on 28/12/2021.
 */
@Controller
@RequestMapping("/movil/*")
public class SerologiaLabController {

    private static final Logger logger = LoggerFactory.getLogger(SerologiaLabController.class);

    @Resource(name = "SerologiaService")
    private SerologiaService serologiaService;

    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param recepcionMuestras Objeto serializado de RecepcionMuestra
     * @return String con el resultado
     */
    @RequestMapping(value = "serologiasLab", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveSerologiaLab(@RequestBody Serologia[] recepcionMuestras) throws Exception{
        logger.debug("Insertando/Actualizando recepciones de serologias laboratorio");
        if (recepcionMuestras == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Serologia> recepcionMuestraList = Arrays.asList(recepcionMuestras);
            for (Serologia recepcionMuestra : recepcionMuestraList){
                serologiaService.saveSerologia(recepcionMuestra);
            }
        }
        return "Datos recibidos!";
    }

}
