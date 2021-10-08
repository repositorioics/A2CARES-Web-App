package ni.org.ics.webapp.web.controller;

import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.ParticipanteService;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by miguel on 27/8/2021.
 */
@Controller
@RequestMapping("/hoja-clinica/*")
public class HojaClinicaController {

    private static final Logger logger = LoggerFactory.getLogger(HojaClinicaController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException {
        logger.debug("Mostrando Usuarios en JSP");
        //List<UserSistema> usuarios = usuarioService.getUsers();
        //model.addAttribute("usuarios", usuarios);
        return "fingering/clinicalSheet";
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participante para positivo UO1");
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            if (procesos != null && procesos.getRetirado().equals(1))
                return JsonUtil.createJsonResponse("Participante retirado");
            else {
                map.put("codigo", participante.getCodigo().toString());
                map.put("nombre", participante.getNombreCompleto());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));
                map.put("edad", participante.getEdad());
                map.put("sexo", participante.getSexo());
            }
        }
        else
            return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        return JsonUtil.createJsonResponse(map);
    }

}
