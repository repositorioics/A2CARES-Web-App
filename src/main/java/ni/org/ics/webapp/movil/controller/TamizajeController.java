package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.Tamizaje;
import ni.org.ics.webapp.service.TamizajeService;
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
public class TamizajeController {

    private static final Logger logger = LoggerFactory.getLogger(TamizajeController.class);

    @Resource(name = "tamizajeService")
    private TamizajeService tamizajeService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "tamizajes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Tamizaje> getTamizajes() {
        logger.info("Descargando toda la informacion de formularios Tamizaje" );
        List<Tamizaje> respuestaList = tamizajeService.getTamizajes();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }


    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Tamizaje
     * @return String con el resultado
     */
    @RequestMapping(value = "tamizajes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveTamizajes(@RequestBody Tamizaje[] envio) {
        logger.debug("Insertando/Actualizando formularios Tamizaje");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Tamizaje> tamizajes = Arrays.asList(envio);
            for (Tamizaje tam : tamizajes){
                if (tam.getAsentimientoVerbal()!=null && tam.getAsentimientoVerbal().equalsIgnoreCase("null"))
                    tam.setAsentimientoVerbal(null);
                if (tam.getAceptaTamizajePersona()!=null && tam.getAceptaTamizajePersona().equalsIgnoreCase("null"))
                    tam.setAceptaTamizajePersona("0");
            	tamizajeService.saveOrUpdateTamizaje(tam);
            }
        }
        return "Datos recibidos!";
    }


}
