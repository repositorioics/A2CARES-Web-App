package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.service.ParticipanteProcesosService;
import ni.org.ics.webapp.service.ParticipanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name="participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "participantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> getParticipantes() {
        try {
            logger.info("Descargando toda la informacion de Participante");
            List<Participante> respuestaList = participanteService.getParticipantes();
            if (respuestaList == null) {
                logger.debug("Nulo");
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param participantesArray Objeto serializado de Participante
     * @return String con el resultado
     */
    @RequestMapping(value = "participantes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveParticipantes(@RequestBody Participante[] participantesArray){
        logger.debug("Insertando/Actualizando formularios Participante");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Participante> participantes = Arrays.asList(participantesArray);
            for (Participante participante : participantes){
                participanteService.saveOrUpdateParticipante(participante);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna participantes activos. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    /*@RequestMapping(value = "participantesactivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> descargarParticipantesActivos() {
        logger.info("Descargando toda la informacion de participantes activos");
        List<Participante> participantes = participanteService.getParticipantesActivos();
        if (participantes == null){
            logger.debug("Nulo");
        }
        return participantes;
    }*/

    /**
     * Retorna codigo casa. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "checkcasa/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Integer checkCasa(@PathVariable Integer codigo) {
        logger.info("Descargando codigo de casa del participante");
        Integer codCasa = participanteService.checkCasa(codigo);
        if (codCasa == null){
            logger.debug("Nulo");
        }
        return codCasa;
    }

    /**
     * Retorna participante. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "participante/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> descargarParticipante(@PathVariable Integer codigo) {
        logger.info("Descargando toda la informacion del participante "+codigo);
        List<Participante> participantes = new ArrayList<Participante>();
        participantes.add(participanteService.getParticipanteByCodigo(codigo));
        return participantes;
    }

    /**
     * Retorna participantes. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    @RequestMapping(value = "participantesprocesos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteProcesos> descargarParticipantes() {
        try {
            logger.info("Descargando toda la informacion de participantes");
            List<ParticipanteProcesos> participantes = participanteProcesosService.getParticipantesProcesos();
            if (participantes == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return participantes;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud POST con un parámetro JSON
     * @param participantesArray Objeto serializado de Participante
     * @return String con el resultado
     */
    @RequestMapping(value = "participantesprocesos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesProcesos(@RequestBody ParticipanteProcesos[] participantesArray){
        logger.debug("Insertando/Actualizando datos procesos de participantes");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteProcesos> participantes = Arrays.asList(participantesArray);
            for (ParticipanteProcesos participante : participantes){
                participanteProcesosService.saveOrUpdateParticipanteProc(participante);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna participante_procesos. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "participanteprocesos/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteProcesos> descargarParticipanteProcesos(@PathVariable Integer codigo) {
        logger.info("Descargando toda la informacion del participante "+codigo);
        List<ParticipanteProcesos> participantes = new ArrayList<ParticipanteProcesos>();
        participantes.add(participanteProcesosService.getParticipante(codigo));

        return participantes;
    }


}
