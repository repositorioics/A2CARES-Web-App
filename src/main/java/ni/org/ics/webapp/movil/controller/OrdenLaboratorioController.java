package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.medico.OrdenLaboratorio;
import ni.org.ics.webapp.service.OrdenLaboratorioService;
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
 * Created by miguel on 27/1/2022.
 */
@Controller
@RequestMapping("/movil/*")
public class OrdenLaboratorioController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenLaboratorioController.class);

    @Resource(name = "ordenLaboratorioService")
    private OrdenLaboratorioService ordenLaboratorioService;

    @RequestMapping(value = "ordeneslab", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<OrdenLaboratorio> getOrdenesLaboratorio(){
        logger.info("Descargando toda la informacion de los datos de las OrdenLaboratorio");
        List<OrdenLaboratorio> ordenesLaboratorio = ordenLaboratorioService.getOrdenesLaboratorio();
        if (ordenesLaboratorio == null){
            logger.debug("Nulo");
        }
        return  ordenesLaboratorio;
    }

    @RequestMapping(value = "ordeneslab", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveOrdenLaboratorio(@RequestBody OrdenLaboratorio[] objetos){
        logger.debug("Insertando/Actualizando OrdenLaboratorio");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<OrdenLaboratorio> ordenes = Arrays.asList(objetos);
            for(OrdenLaboratorio orden : ordenes) {
                ordenLaboratorioService.saveorUpdateOrdenLaboratorio(orden);
            }
        }
        return "Datos recibidos!";
    }
}
