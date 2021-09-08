package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.Estudio;
import ni.org.ics.webapp.service.EstudioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class EstudioController {

    private static final Logger logger = LoggerFactory.getLogger(EstudioController.class);

    @Resource(name = "estudioService")
    private EstudioService estudioService;

    @RequestMapping(value = "estudios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Estudio> getEstudios(){
        logger.info("Descargando toda la informacion de los datos de los Estudios");
        List<Estudio> estudios = estudioService.getEstudios();
        if (estudios == null){
            logger.debug("Nulo");
        }
        return  estudios;
    }

}
