package ni.org.ics.webapp.web.controller.entomologia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;

/**
 * Created by miguel on 15/8/2022.
 */
@Controller
@RequestMapping("/ento/*")
public class CuestionarioHogarWebController {
    private static final Logger logger = LoggerFactory.getLogger(CuestionarioHogarWebController.class);

    @RequestMapping(value = "informacion", method = RequestMethod.GET)
    public String informacion(Model model) throws ParseException {
        logger.debug("Solicitar informacion de cuestionarios entomologia para generar excel");

        return "entomologia/informacion";
    }
}
