package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.medico.InformeFindeDiaMedicos;
import ni.org.ics.webapp.dto.InformeFinDiaMedicosDto;
import ni.org.ics.webapp.dto.FiltroMxEnfermoDto;
import ni.org.ics.webapp.service.InformeFinDiaMedicosService;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Everts Morales Reyes
 */

@Controller
@RequestMapping("/movil/*")
public class InformeFinDiaMedicosController {

    private static final Logger logger = LoggerFactory.getLogger(InformeFinDiaMedicosController.class);

    @Resource(name = "informeFinDiaMedicosService")
    private InformeFinDiaMedicosService informeFinDiaMedicosService;


    @RequestMapping(value = "informeFinDiaMedicosMx", method = RequestMethod.GET)
    public String controlIngresosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de Informe Fin de Dia Medicos JSP");

        return "mxEnfermos/informeFinDiaMedicosMx";
    }


    @RequestMapping(value = "getInformeFinDiaMed", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<InformeFinDiaMedicosDto> getInformeFinDiaM(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<InformeFinDiaMedicosDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de Informe de fin de dia Medicos");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();

            filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            recepcion = informeFinDiaMedicosService.getInformeFinDiaMedicosListar (filtro);
            return recepcion;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

   /* @RequestMapping(value = "informemedicos1", method = RequestMethod.GET)
    public String controlIngresosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de Admision de Pacientes JSP");

        return "admin/personal/admisionPacientes";
    }*/

    @RequestMapping(value = "informemedicos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveInformeFinDiaMedicos(@RequestBody InformeFindeDiaMedicos[] objetos){
        Date fechaactual = java.sql.Date.valueOf(LocalDate.now());

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        logger.debug("Insertando/Actualizando Informe Fin de Dia Médicos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{


            List<InformeFindeDiaMedicos> informeList = Arrays.asList(objetos);
            for(InformeFindeDiaMedicos informeFindeDiaMedicos : informeList) {
                try {
                    String numIdAsist = informeFinDiaMedicosService.getidinformediaMedicos();
                    String existeInformeDia = informeFinDiaMedicosService.getverificaInformeIngresado(informeFindeDiaMedicos.getRecordUser());

                    if (existeInformeDia.equals("0"))
                    {
                        informeFindeDiaMedicos.setId(Integer.parseInt(numIdAsist) + 1);
                        informeFinDiaMedicosService.saveOrUpdateInformeFinDiaMedicos(informeFindeDiaMedicos);

                   }else{
                       // return "Informe de Fin de Día del médico: "+ informeFindeDiaMedicos.getRecordUser() + " ya Existe. Error al Guardar.";
                        return "Informe de Fin de Día del médico:  ya Existe. Error al Guardar.";
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        }
        return "Datos recibidos!";
    }
}
