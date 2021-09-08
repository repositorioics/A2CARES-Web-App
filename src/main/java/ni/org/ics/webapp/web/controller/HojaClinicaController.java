package ni.org.ics.webapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;

/**
 * Created by miguel on 27/8/2021.
 */
@Controller
@RequestMapping("/hoja-clinica/*")
public class HojaClinicaController {

    private static final Logger logger = LoggerFactory.getLogger(HojaClinicaController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException {
        logger.debug("Mostrando Usuarios en JSP");
        //List<UserSistema> usuarios = usuarioService.getUsers();
        //model.addAttribute("usuarios", usuarios);
        return "fingering/clinicalSheet";
    }
}
