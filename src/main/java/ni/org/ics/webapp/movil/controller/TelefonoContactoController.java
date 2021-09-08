package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.TelefonoContacto;
import ni.org.ics.webapp.service.TelefonoContactoService;
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
public class TelefonoContactoController {

    private static final Logger logger = LoggerFactory.getLogger(TelefonoContactoController.class);

    @Resource(name = "telefonoContactoService")
    private TelefonoContactoService telefonoContactoService;

    @RequestMapping(value = "telefonos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TelefonoContacto> getTelefonosContacto(){
        logger.info("Descargando toda la informacion de los datos de las TelefonoContacto");
        List<TelefonoContacto> telefonos = telefonoContactoService.getTelefonosContacto();
        if (telefonos == null){
            logger.debug("Nulo");
        }
        return  telefonos;
    }

    @RequestMapping(value = "telefonos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveTelefonoContacto(@RequestBody TelefonoContacto[] objetos){
        logger.debug("Insertando/Actualizando TelefonoContacto");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<TelefonoContacto> telefonos = Arrays.asList(objetos);
            for(TelefonoContacto telefono : telefonos) {
            	telefonoContactoService.saveOrUpdateTelefono(telefono);
            }
        }
        return "Datos recibidos!";
    }

}
