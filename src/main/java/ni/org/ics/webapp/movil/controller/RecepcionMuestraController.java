package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.supervisor.RecepcionMuestra;
import ni.org.ics.webapp.service.RecepcionMuestraService;
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
 * Created by miguel on 2/9/2021.
 */
@Controller
@RequestMapping("/movil/*")
public class RecepcionMuestraController {

    private static final Logger logger = LoggerFactory.getLogger(RecepcionMuestraController.class);

    @Resource(name = "recepcionMuestraService")
    private RecepcionMuestraService recepcionMuestraService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "recepcionMuestras", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionMuestra> getRecepcionMuestras() {
        logger.info("Descargando toda la información de recepciones de muestras");
        List<RecepcionMuestra> respuestaList = recepcionMuestraService.getRecepcionesMuestras();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param recepcionMuestras Objeto serializado de RecepcionMuestra
     * @return String con el resultado
     */
    @RequestMapping(value = "recepcionMuestras", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveRecepcionMuestras(@RequestBody RecepcionMuestra[] recepcionMuestras){
        logger.debug("Insertando/Actualizando recepciones de muestras");
        if (recepcionMuestras == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<RecepcionMuestra> recepcionMuestraList = Arrays.asList(recepcionMuestras);
            for (RecepcionMuestra recepcionMuestra : recepcionMuestraList){
                recepcionMuestraService.saveOrUpdate(recepcionMuestra);
            }
        }
        return "Datos recibidos!";
    }

}
