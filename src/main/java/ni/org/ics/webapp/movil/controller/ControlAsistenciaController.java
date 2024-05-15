package ni.org.ics.webapp.movil.controller;

import ni.org.ics.webapp.domain.core.ControlAsistencia;
import ni.org.ics.webapp.dto.ControlAsistenciaDto;
import ni.org.ics.webapp.dto.FiltroMxEnfermoDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.ControlAsistenciaService;

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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 * Created by Everts Morales Reyes
 */

@Controller
@RequestMapping("/movil/*")
public class ControlAsistenciaController {

    private static final Logger logger = LoggerFactory.getLogger(ControlAsistenciaController.class);

    @Resource(name = "controlAsistenciaService")
    private ControlAsistenciaService controlAsistenciaService;


   @RequestMapping(value = "getcontrolAsistencia", method = RequestMethod.GET)
    public String controlIngresosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de control de asistencia JSP");

        return "admin/personal/controlAsistencia1";
    }


    @RequestMapping(value = "getAsistenciaPersonal", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ControlAsistenciaDto> getControlAsistenciaA2cares(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<ControlAsistenciaDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de control de asistencia de personal");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            if (desde.equals("")){
                desde = hasta;
            }
            filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            UnicodeEscaper escaper = UnicodeEscaper.above(127);

            recepcion = this.controlAsistenciaService.getControlAsistenciaPersonal(filtro);
            return recepcion;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "controlasistencia", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveControlAsistencia(@RequestBody ControlAsistencia[] objetos){
        java.util.Date fechaactual = java.sql.Date.valueOf(LocalDate.now());

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        logger.debug("Insertando/Actualizando Control de Asistencia de Personal");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{


            List<ControlAsistencia> asistencialList = Arrays.asList(objetos);
            for(ControlAsistencia controlAsistencia : asistencialList) {
                try {
                    String numIdAsist = controlAsistenciaService.getcodigoAsistencia();

                    controlAsistencia.setId(Integer.parseInt(numIdAsist)+1);


                String verificaEntrada = controlAsistenciaService.getverificaUsuarioHrEntrada(controlAsistencia.getRecordUser().toString());
                String verificaSalida = controlAsistenciaService.getverificaUsuarioHrSalida(controlAsistencia.getRecordUser().toString());
                    logger.debug("verifica entrada"+ verificaEntrada);
                    logger.debug("verifica salida"+ verificaSalida);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String fecasist = ("2023" +"-"+ controlAsistencia.getFechaasistencia().getMonth() +"-"+controlAsistencia.getFechaasistencia().getDay());
                    java.util.Date fechaConvertida = null;
                    java.util.Date fechaConvertidaact = null;
             /*       fechaConvertida = (Date) dateFormat.parse(controlAsistencia.getFechaasistencia().toString());
                    fechaConvertidaact = (Date) dateFormat.parse(fechaactual.toString());
                    System.out.println(fechaConvertida);*/

                 //   if ( controlAsistencia.getFechaasistencia().equals(fechaConvertidaact)) {
                    if ( verificaEntrada.equalsIgnoreCase("0")) {
                        if (controlAsistencia.getHoraentrada() == null) {
                            controlAsistencia.setHoraentrada(controlAsistencia.getHorasalida());
                            controlAsistencia.setHorasalida("");
                        }

                         controlAsistenciaService.saveOrUpdateControlAsistencia(controlAsistencia);
                    }else {
                        if ( verificaSalida.equalsIgnoreCase("0") && controlAsistencia.getHorasalida() != null) {

                            controlAsistenciaService.saveOrUpdateControlAsistencia(controlAsistencia);
                        }else {
                            if (!verificaEntrada.equalsIgnoreCase("0") && controlAsistencia.getHorasalida() == null) {
                                return "Usuario: " + controlAsistencia.getRecordUser().toUpperCase() + ", tiene Hora de entrada hoy: " + fechaactual + "  Error al Guardar.";
                            }
                            if (!verificaSalida.equalsIgnoreCase("0")) {
                                return "Usuario: " + controlAsistencia.getRecordUser().toUpperCase() + ", tiene Hora de Salida hoy: " + fechaactual + "   Error al Guardar.";
                            }
                        }
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
