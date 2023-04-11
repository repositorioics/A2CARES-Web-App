package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.survey.EncuestaSatisfaccionUsuario;
import ni.org.ics.webapp.service.EncuestaSatisfaccionUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Ing. Santiago Carballo on 11/04/2023.
 */

@Controller
@RequestMapping("/movil/*")
public class EncuestaSatisfaccionUsuarioController {
    @Resource(name = "encuestaSatisfaccionUsuarioService")
    private EncuestaSatisfaccionUsuarioService encuestaSatisfaccionUsuarioService;

    private static final Logger logger = LoggerFactory.getLogger(EncuestaSatisfaccionUsuarioController.class);

    @RequestMapping(value = "encuestaSatisfaccionUsuario", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestaSatisfaccionUsuario(@RequestBody EncuestaSatisfaccionUsuario[] objetos){
        logger.debug("Insertando/Actualizando las encuestas de satisfaccion de usuario");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaSatisfaccionUsuario> encuestaSatisfaccionUsuarioList = Arrays.asList(objetos);
            for(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario : encuestaSatisfaccionUsuarioList) {
                encuestaSatisfaccionUsuario.setFechaRegistro(new Date());
                encuestaSatisfaccionUsuarioService.saveOrUpdateEncuestaSatisfaccionUsuario(encuestaSatisfaccionUsuario);
            }
        }
        return "Datos recibidos!";
    }
}
