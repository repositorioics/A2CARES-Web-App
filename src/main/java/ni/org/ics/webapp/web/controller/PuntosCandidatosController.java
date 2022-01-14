package ni.org.ics.webapp.web.controller;

import ni.org.ics.webapp.domain.puntos.PuntoCandidato;
import ni.org.ics.webapp.dto.ComparacionMuestrasMA;
import ni.org.ics.webapp.service.PuntoCandidatoService;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel on 12/1/2022.
 */
@Controller
@RequestMapping("/puntos/*")
public class PuntosCandidatosController {

    private static final Logger logger = LoggerFactory.getLogger(PuntosCandidatosController.class);

    @Resource(name="puntoCandidatoService")
    private PuntoCandidatoService puntoCandidatoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) throws ParseException {
        logger.debug("Mostrando puntos gps candidatos en JSP");
        return "points/list";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getPuntosCandidatos() throws ParseException {
        try {
            logger.debug("Obtener todos los puntos gps candidatos");
            List<PuntoCandidato> puntos = this.puntoCandidatoService.getPuntosCandidatos2();
            return JsonUtil.createJsonResponse(puntos);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonUtil.createJsonResponse(new ArrayList<PuntoCandidato>());
        }
    }
}
