package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.Muestra;
import ni.org.ics.webapp.service.MuestraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 7/14/2021.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class MuestraController {

    private static final Logger logger = LoggerFactory.getLogger(MuestraController.class);

    @Resource(name = "muestraService")
    private MuestraService muestraService;


    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestras", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestras() throws Exception {
        logger.info("Descargando toda la informacion de formularios muestras");
        List<Muestra> respuestaList = muestraService.getMuestras();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }
    
    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestrastx", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestrasTX() throws Exception {
        logger.info("Descargando toda la informacion de formularios muestras enfermos");
        List<Muestra> respuestaList = muestraService.getMuestrasTx();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param muestras Objeto serializado de Muestras
     * @return String con el resultado
     */
    @RequestMapping(value = "muestras", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveMuestras(@RequestBody Muestra[] muestras){
        logger.debug("Insertando/Actualizando muestras");
        if (muestras == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Muestra> muestraList = Arrays.asList(muestras);
            for (Muestra muestra : muestraList){
                muestraService.saveOrUpdate(muestra);
            }
        }
        return "Datos recibidos!";
    }
}
