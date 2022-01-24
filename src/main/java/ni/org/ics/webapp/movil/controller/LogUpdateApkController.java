package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.audit.LogUpdateApk;
import ni.org.ics.webapp.service.AuditTrailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 19/1/2022.
 */
@Controller
@RequestMapping("/movil/*")
public class LogUpdateApkController {
    private static final Logger logger = LoggerFactory.getLogger(LogUpdateApkController.class);

    @Resource(name = "auditTrailService")
    private AuditTrailService auditTrailService;
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param encuestas Objeto serializado de LogUpdateApk
     * @return String con el resultado
     */
    @RequestMapping(value = "logUpdateApk", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestasCasa(@RequestBody LogUpdateApk[] encuestas){
        logger.debug("Insertando log de actualizacion de apk");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<LogUpdateApk> logUpdateApks = Arrays.asList(encuestas);
            for (LogUpdateApk logUpdateApk : logUpdateApks){
                logUpdateApk.setFechaRecibido(new Date());
                auditTrailService.saveLogUpdateApk(logUpdateApk);
            }
        }
        return "Datos recibidos!";
    }
}
