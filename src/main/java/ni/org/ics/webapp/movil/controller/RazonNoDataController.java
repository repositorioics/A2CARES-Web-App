package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.RazonNoData;
import ni.org.ics.webapp.service.RazonNoDataService;
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
public class RazonNoDataController {

    private static final Logger logger = LoggerFactory.getLogger(RazonNoDataController.class);

    @Resource(name = "razonNoDataService")
    private RazonNoDataService razonNoDataService;

    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param noDataArray Objeto serializado de RazonNoData
     * @return String con el resultado
     */
    @RequestMapping(value = "razonesDatosPendientes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveParticipantes(@RequestBody RazonNoData[] noDataArray){
        logger.debug("Insertando/Actualizando razones datos pendientes");
        if (noDataArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<RazonNoData> razonNoDataList = Arrays.asList(noDataArray);
            for (RazonNoData razonNoData : razonNoDataList){
                razonNoDataService.saveOrUpdate(razonNoData);
            }
        }
        return "Datos recibidos!";
    }
}
