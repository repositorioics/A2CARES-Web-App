package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.DatosCoordenadas;
import ni.org.ics.webapp.service.DatosCoordenadasService;
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
public class DatosCoordenadasController {

    private static final Logger logger = LoggerFactory.getLogger(DatosCoordenadasController.class);

    @Resource(name = "datosCoordenadasService")
    private DatosCoordenadasService datosCoordenadasService;

    @RequestMapping(value = "datoscoordenadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosCoordenadas> getDatosCoordenadas(){
        logger.info("Descargando toda la informacion de los datos de DatosCoordenadas");
        List<DatosCoordenadas> coordenadas = datosCoordenadasService.getDatosCoordenadas();
        if (coordenadas == null){
            logger.debug("Nulo");
        }
        return  coordenadas;
    }

    @RequestMapping(value = "datoscoordenadas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveDatosCoordenadas(@RequestBody DatosCoordenadas[] objetos){
        logger.debug("Insertando/Actualizando DatosCoordenadas");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<DatosCoordenadas> coordenadas = Arrays.asList(objetos);
            for(DatosCoordenadas coordenada : coordenadas) {
                datosCoordenadasService.saveOrUpdateDatosCoordenadas(coordenada);
            }
        }
        return "Datos recibidos!";
    }

}
