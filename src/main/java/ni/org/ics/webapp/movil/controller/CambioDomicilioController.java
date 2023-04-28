package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.CambioDomicilio;
import ni.org.ics.webapp.service.CambioDomicilioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ing. Santiago Carballo on 26/04/2023.
 */

@Controller
@RequestMapping("/movil/*")
public class CambioDomicilioController {
    @Resource(name = "cambioDomicilioService")
    private CambioDomicilioService cambioDomicilioService;

    private static final Logger logger = LoggerFactory.getLogger(CambioDomicilioController.class);

    @RequestMapping(value = "cambiodomicilio", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveCambioDomicilio(@RequestBody CambioDomicilio[] objetos) {
        logger.debug("Insertando/Actualizando los cambios de domicilios");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        } else {
            List<CambioDomicilio> cambioDomicilioList =  Arrays.asList(objetos);

            for(CambioDomicilio cambioDomicilio : cambioDomicilioList) {
                String movimiento = "";
                List<CambioDomicilio> list = cambioDomicilioService.getCambioDomicilioByCodigo(cambioDomicilio.getCodigoParticipante());
                if (list.size() > 0) {
                    String valor = list.get(0).getCodigoMovimiento();
                    String[] result = valor.split("_");
                    movimiento = nextString(result[1].trim());
                    for (CambioDomicilio cambioDom : list) {
                        cambioDom.setActual(false);
                        cambioDomicilioService.saveOrUpdateCambioDomicilio(cambioDom);
                    }
                } else {
                    movimiento = nextString("");
                }
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                String mov = String.valueOf(year) + "_"+movimiento;

                cambioDomicilio.setId(null);
                cambioDomicilio.setFechaRegistro(new Date());
                cambioDomicilio.setCodigoMovimiento(mov);
                cambioDomicilio.setActual(true);
                cambioDomicilioService.saveOrUpdateCambioDomicilio(cambioDomicilio);
            }
        }

        return "Datos recibidos!";
    }

    public static String nextString(String str) {
        String firstValue = "A";
        if (isNullOrEmpty(str))
            return firstValue;  // return 'A' if str is empty or null

        int charValue = str.charAt(0);
        String next = String.valueOf((char) (charValue + 1));
        return next;
    }

    public static boolean isNullOrEmpty(String string) {
        return string==null || string.trim().equals("");
    }
}
