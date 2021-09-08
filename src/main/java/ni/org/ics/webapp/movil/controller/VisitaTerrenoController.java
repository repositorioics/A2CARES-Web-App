package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.visitas.VisitaTerrenoParticipante;
import ni.org.ics.webapp.service.VisitaTerrenoService;
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
public class VisitaTerrenoController {

    private static final Logger logger = LoggerFactory.getLogger(VisitaTerrenoController.class);

    @Resource(name = "visitaTerrenoService")
    private VisitaTerrenoService visitaTerrenoService;

    @RequestMapping(value = "visitasterrenoparti", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaTerrenoParticipante> getVisitasTerrenoParticipante(){
        logger.info("Descargando toda la informacion de los datos de las VisitaTerrenoParticipante");
        List<VisitaTerrenoParticipante> visitas = visitaTerrenoService.getVisitasTerrenoParticipante();
        if (visitas == null){
            logger.debug("Nulo");
        }
        return  visitas;
    }

    @RequestMapping(value = "visitasterrenoparti", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveVisitaTerrenoParticipante(@RequestBody VisitaTerrenoParticipante[] objetos){
        logger.debug("Insertando/Actualizando VisitaTerrenoParticipante");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaTerrenoParticipante> visitas = Arrays.asList(objetos);
            for(VisitaTerrenoParticipante visita : visitas) {
                visitaTerrenoService.saveOrUpdateVisitaTerrenoParticipante(visita);
            }
        }
        return "Datos recibidos!";
    }

}
