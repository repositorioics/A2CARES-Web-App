package ni.org.ics.webapp.movil.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.webapp.domain.Serologia.Bhc;
import ni.org.ics.webapp.domain.Serologia.Serologia;
import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.core.ControlAsistencia;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.personal.JustificacionesICS;
import ni.org.ics.webapp.dto.*;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.ControlAsistenciaService;

import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


/**
 * Created by Everts Morales Reyes
 */

@Controller
@RequestMapping("/movil/*")
public class ControlAsistenciaController {

    private static final Logger logger = LoggerFactory.getLogger(ControlAsistenciaController.class);

    @Resource(name = "controlAsistenciaService")
    private ControlAsistenciaService controlAsistenciaService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

   @RequestMapping(value = "getcontrolAsistencia", method = RequestMethod.GET)
    public String controlIngresosMx(Model model) throws ParseException {
        logger.debug("Mostrando lista de control de asistencia JSP");

        return "admin/personal/controlAsistencia1";
    }
    @RequestMapping(value = "getcontrolAsistenciaICS", method = RequestMethod.GET)
    public String getcontrolAsistenciaICS(Model model) throws ParseException {
        logger.debug("Mostrando lista de control de asistencia JSP");

        return "admin/personal/controlAsistencia1ICS";
    }
    @RequestMapping(value = "getreporteHorasICS", method = RequestMethod.GET)
    public String getreporteHorasICS(Model model) throws ParseException {
        logger.debug("Mostrando lista de Horas de asistencia JSP");

        List<SitiosAsistenciaICSDto> sitios = null;
        try {
            sitios = messageResourceService.getObtenerSitiosICS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sitios", sitios);
        List<SitiosAsistenciaICSDto> empleados = null;
        try {
            empleados = messageResourceService.getObtenerEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("empleados", empleados);
        return "admin/personal/reporteHorasICS";
    }
    @RequestMapping(value = "getJustICS", method = RequestMethod.GET)
    public String getJustICS(Model model) throws ParseException {
        logger.debug("Mostrando lista de Justificaciones JSP");

        return "admin/personal/justificacionesICS";
    }
    @RequestMapping(value = "getJustAddICS", method = RequestMethod.GET)
    public String getJustAddICS(Model model) throws ParseException {
        logger.debug("Mostrando Add de Justificaciones JSP");

        List<ControlAsistenciaICSDto> cat = null;
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_JUSTIFICACION");
        List<MessageResource> empleados = messageResourceService.getCatalogoEmpleados("CAT_EMPLEADOS");
        model.addAttribute("sitios", sitios);
        model.addAttribute("empleados", empleados);
        return "admin/personal/justificacionesAgregarICS";
    }
    @RequestMapping(value = "getAsistenciaPersonalICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ControlAsistenciaICSDto> getAsistenciaPersonalICS1(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<ControlAsistenciaICSDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de control de asistencia de personal ICS");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            if (desde.equals("")){
                desde = hasta;
                filtro.setFechaInicio(DateUtil.StringToDate("30/12/2020" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate("30/12/2020"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
            }

            if(!desde.equals("") && desde != null) {
                filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                UnicodeEscaper escaper = UnicodeEscaper.above(127);
                recepcion = this.controlAsistenciaService.getControlAsistenciaPersonalICS(filtro);
                return recepcion;
            }else{
                filtro.setFechaInicio(DateUtil.StringToDate("30/12/2020" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate("30/12/2020"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                recepcion = this.controlAsistenciaService.getControlAsistenciaPersonalICS(filtro);
                return recepcion;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getNombreEmpleadosICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<NombreEmpleadosICSDto> getNombreEmpleadosICS(
            @RequestParam(value="id", required=false ) String id)
            throws ParseException {

        List<NombreEmpleadosICSDto> nombreEmp = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar nombres de personal ICS");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
                logger.debug("ID = "+ id);
                String[] idtemp = id.split("-");
                if(idtemp != null) {
                    nombreEmp = this.controlAsistenciaService.getNombreEmpleadosICS(idtemp[1], idtemp[0]);
                    for (NombreEmpleadosICSDto rph : nombreEmp) {
                        rph.setSitio("<span class='badge bg-light text-dark'>" + rph.getSitio() + "</span>");
                        rph.setDepto("<span class='badge bg-light text-dark'>" + rph.getDepto() + "</span>");
                        rph.setNombreEmpleado("<span class='badge bg-light text-dark'>" + rph.getNombreEmpleado() + "</span>");
                    }
                    return nombreEmp;
                }else{
                    return null;
                }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "getSitiosICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SitiosICSDto> getSitiosICS()
            throws ParseException {

        List<SitiosICSDto> sitios = null;
        LocalDate localDate = LocalDate.now();
        try {
            logger.debug("buscar sitios de personal ICS");
                sitios = this.controlAsistenciaService.getSitiosICS();
                return sitios;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "getDepartamentosICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<DepartamentosICSDto> getDepartamentosICS(@RequestParam(value="id", required=false ) String id)
            throws ParseException {
        List<DepartamentosICSDto> depto = null;
        LocalDate localDate = LocalDate.now();
        Model model = null;
        try {
            logger.debug("buscar sitios de personal ICS");
            if(id != null) {
                depto = this.controlAsistenciaService.getDepartamentosICS(id);
               // model.addAttribute("depto", depto);
                return depto;
            }else{
                id = "9999";
                depto = this.controlAsistenciaService.getDepartamentosICS(id);
                return depto;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getHorasTrabajadasICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ReporteHorasICSDto> getHorasTrabajadasICS(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="id", required=false ) String id
            ,@RequestParam(value="idDepto", required=false ) String idDepto
            ,@RequestParam(value="idSitio", required=false ) String idSitio)
            throws ParseException {

        List<ReporteHorasICSDto> horasEmpleados = null;
        List<NombreEmpleadosICSDto> nombreEmp = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de Horas de asistencia de personal ICS");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            if (desde.equals("")){
                desde = hasta;
                id = "9999";
                filtro.setFechaInicio(DateUtil.StringToDate("30/12/2020" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate("30/12/2020"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, "9999", "9999");
                logger.debug("desde = "+ filtro.getFechaInicio());
                logger.debug("hasta = "+ filtro.getFechaFin());
                logger.debug("ID = "+ id);
            }

            if(!desde.equals("")) {
                filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                UnicodeEscaper escaper = UnicodeEscaper.above(127);
                logger.debug("desde = "+ desde);
                logger.debug("hasta = "+ hasta);
                logger.debug("ID = "+ id);
                String[] idtemp = id.split("-");
                if (idtemp != null && !idtemp[0].isEmpty() && idtemp.length > 1) {
                    horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, idtemp[1], idtemp[0]);
                    idSitio = idtemp[0];
                    DecimalFormat df = new DecimalFormat("#.00");
                    // System.out.println(df.format(number));
                    Long total;
                    total = Long.valueOf(0);
                    if(horasEmpleados != null) {

                        int con = 1;
                        for (ReporteHorasICSDto rph : horasEmpleados) {
                            nombreEmp = this.controlAsistenciaService.getNombreEmpleadosICS(rph.getId(),idSitio);
                            String[] rst = rph.getHoras().split("-");
                            double h1 = 0;
                            double h2 = 0;
                            int m1 = 0;
                            int m2 = 0;
                            if (rst[0].toString().length() > 5) {
                                if (rst.length > 1) {
                                    Arrays.stream(rst).sorted();
                                    String aux;
                                    for(int i=1; i<=rst.length; i++) {
                                        for(int j=0; j<rst.length-i; j++) {
                                            if( rst[j].compareTo( rst[j+1] ) > 0 ) {
                                                aux   = rst[j];
                                                rst[j]  = rst[j+1];
                                                rst[j+1]= aux;
                                            }
                                        }
                                    }
                                    h1 = Double.parseDouble(rst[0].substring(1, 3));
                                    String[] tokens1 = rst[0].split(":");
                                    int secondsToMs1 = Integer.parseInt(tokens1[2].trim()) * 1000;
                                    int minutesToMs1 = Integer.parseInt(tokens1[1].trim()) * 60000;
                                    int hoursToMs1 = Integer.parseInt(tokens1[0].trim()) * 3600000;
                                    int total1 = secondsToMs1 + minutesToMs1 + hoursToMs1;

                                    String[] tokens12 = rst[rst.length-1].split(":");
                                    int secondsToMs12 = Integer.parseInt(tokens12[2].trim()) * 1000;
                                    int minutesToMs12 = Integer.parseInt(tokens12[1].trim()) * 60000;
                                    int hoursToMs12 = Integer.parseInt(tokens12[0].trim()) * 3600000;
                                    int total12 = secondsToMs12 + minutesToMs12 + hoursToMs12;

                                    Long t = new Long(total12) - new Long(total1);
                                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(t),
                                            TimeUnit.MILLISECONDS.toMinutes(t) % TimeUnit.HOURS.toMinutes(1),
                                            TimeUnit.MILLISECONDS.toSeconds(t) % TimeUnit.MINUTES.toSeconds(1));

                                    h2 = Double.parseDouble(rst[1].substring(1, 3));
                                    if (Integer.parseInt(hms.substring(0, 2)) < 8) {
                                        rph.setResta(String.valueOf((hms)));
                                        rph.setResta("<span class='badge bg-danger'>" + rph.getResta() + "</span>");
                                        rph.setHoras(rst[1] + " - "+ rst[0]);
                                    } else {

                                        rph.setResta(String.valueOf((hms)));
                                    }
                                    total = total + t;
                                } else {
                                    rph.setResta(String.valueOf(h1));
                                    rph.setResta("<span class='badge bg-danger'>" + 0 + "</span>");
                                }
                            }
                            logger.debug("h1 = " + h1);
                            logger.debug("h2 = " + h2);
                            // bhclst.setImpreso("<span class='badge badge-danger'>"+bhclst.getImpreso()+"</span>");
                            //  return JsonUtil.createJsonResponse(Bhclist);

                            con++;

                        }
                        if (horasEmpleados.size() > 0) {
                            String hmst = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(total),
                                    TimeUnit.MILLISECONDS.toMinutes(total) % TimeUnit.HOURS.toMinutes(1),
                                    TimeUnit.MILLISECONDS.toSeconds(total) % TimeUnit.MINUTES.toSeconds(1));
                            ReporteHorasICSDto rph1 = new ReporteHorasICSDto();
                            if (Integer.parseInt(hmst.substring(0, 2)) < 40) {
                                rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                rph1.setResta(String.valueOf((hmst)));
                                rph1.setResta("<span class='badge bg-danger'>" + rph1.getResta() + "</span>");
                            } else {

                                rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                rph1.setResta(String.valueOf((hmst)));
                                rph1.setResta("<span class='badge bg-dark'>" + rph1.getResta() + "</span>");
                                // rph1.setVeces("<span class='badge bg-dark'>" + rph1.getVeces() + "</span>");
                            }
                            for (NombreEmpleadosICSDto rphe : nombreEmp) {
                                // rphe.setSitio("<span class='badge bg-light text-dark'>" + rphe.getSitio() + "</span>");
                                // rphe.setDepto("<span class='badge bg-light text-dark'>" + rphe.getDepto() + "</span>");
                                // rphe.setNombreEmpleado("<span class='badge bg-light text-dark'>" + rphe.getNombreEmpleado() + "</span>");
                                rph1.setHoras(("<span class='badge bg-dark'>" + rphe.getNombreEmpleado() + "</span>"));
                                // rph1.setVeces(("<span class='badge bg-dark'>" + rphe.getSitio() + "</span>"));
                                rph1.setSemana(("<span class='badge bg-dark'>" + rphe.getSitio()  + " - " + rphe.getDepto()+ "</span>"));
                            }

                            //rph1.setResta(String.valueOf(hmst));

                            horasEmpleados.add(rph1);
                            Collections.sort(horasEmpleados, (a, b) -> a.getResta().compareToIgnoreCase(b.getResta()));
                        }

                        return horasEmpleados;

                    }else{
                        id = "9999";
                        filtro.setFechaInicio(DateUtil.StringToDate("06/08/2024" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                        filtro.setFechaFin(DateUtil.StringToDate("06/08/2024"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                        horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, "9999", "9999");
                        return horasEmpleados;
                    }
                }else
                    if (!idDepto.isEmpty() && idDepto != null && idDepto != "") {
                        String[] rstdep = idDepto.split("-");
                        try {
                            horasEmpleados = this.controlAsistenciaService.getReporteHorasSitiosDeptoICS(filtro, rstdep[1], rstdep[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        DecimalFormat df = new DecimalFormat("#.00");
                        // System.out.println(df.format(number));
                        Long total;
                        total = Long.valueOf(0);
                        if(horasEmpleados != null) {

                            int con = 1;
                            for (ReporteHorasICSDto rph : horasEmpleados) {
                                try {
                                    nombreEmp = this.controlAsistenciaService.getNombreEmpleadosICS(rph.getId(),idSitio);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String[] rst = rph.getHoras().split("-");
                                double h1 = 0;
                                double h2 = 0;
                                int m1 = 0;
                                int m2 = 0;
                                if (rst[0].toString().length() > 5) {
                                    if (rst.length > 1) {
                                        Arrays.stream(rst).sorted();
                                        String aux;
                                        for(int i=1; i<=rst.length; i++) {
                                            for(int j=0; j<rst.length-i; j++) {
                                                if( rst[j].compareTo( rst[j+1] ) > 0 ) {
                                                    aux   = rst[j];
                                                    rst[j]  = rst[j+1];
                                                    rst[j+1]= aux;
                                                }
                                            }
                                        }
                                        h1 = Double.parseDouble(rst[0].substring(1, 3));
                                        String[] tokens1 = rst[0].split(":");
                                        int secondsToMs1 = Integer.parseInt(tokens1[2].trim()) * 1000;
                                        int minutesToMs1 = Integer.parseInt(tokens1[1].trim()) * 60000;
                                        int hoursToMs1 = Integer.parseInt(tokens1[0].trim()) * 3600000;
                                        int total1 = secondsToMs1 + minutesToMs1 + hoursToMs1;

                                        String[] tokens12 = rst[rst.length-1].split(":");
                                        int secondsToMs12 = Integer.parseInt(tokens12[2].trim()) * 1000;
                                        int minutesToMs12 = Integer.parseInt(tokens12[1].trim()) * 60000;
                                        int hoursToMs12 = Integer.parseInt(tokens12[0].trim()) * 3600000;
                                        int total12 = secondsToMs12 + minutesToMs12 + hoursToMs12;

                                        Long t = new Long(total12) - new Long(total1);
                                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(t),
                                                TimeUnit.MILLISECONDS.toMinutes(t) % TimeUnit.HOURS.toMinutes(1),
                                                TimeUnit.MILLISECONDS.toSeconds(t) % TimeUnit.MINUTES.toSeconds(1));

                                        h2 = Double.parseDouble(rst[1].substring(1, 3));
                                        if (Integer.parseInt(hms.substring(0, 2)) < 8) {
                                            rph.setResta(String.valueOf((hms)));
                                            rph.setResta("<span class='badge bg-danger'>" + rph.getResta() + "</span>");
                                            rph.setHoras(rst[1] + " - "+ rst[0]);
                                        } else {

                                            rph.setResta(String.valueOf((hms)));
                                        }
                                        total = total + t;
                                    } else {
                                        rph.setResta(String.valueOf(h1));
                                        rph.setResta("<span class='badge bg-danger'>" + 0 + "</span>");
                                    }
                                }
                                logger.debug("h1 = " + h1);
                                logger.debug("h2 = " + h2);
                                // bhclst.setImpreso("<span class='badge badge-danger'>"+bhclst.getImpreso()+"</span>");
                                //  return JsonUtil.createJsonResponse(Bhclist);

                                con++;

                            }
                            if (horasEmpleados.size() > 0) {
                                String hmst = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(total),
                                        TimeUnit.MILLISECONDS.toMinutes(total) % TimeUnit.HOURS.toMinutes(1),
                                        TimeUnit.MILLISECONDS.toSeconds(total) % TimeUnit.MINUTES.toSeconds(1));
                                ReporteHorasICSDto rph1 = new ReporteHorasICSDto();
                                if (Integer.parseInt(hmst.substring(0, 2)) < 40) {
                                    rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                    rph1.setResta(String.valueOf((hmst)));
                                    rph1.setResta("<span class='badge bg-danger'>" + rph1.getResta() + "</span>");
                                } else {

                                    rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                    rph1.setResta(String.valueOf((hmst)));
                                    rph1.setResta("<span class='badge bg-dark'>" + rph1.getResta() + "</span>");
                                    // rph1.setVeces("<span class='badge bg-dark'>" + rph1.getVeces() + "</span>");
                                }
                          /*  for (NombreEmpleadosICSDto rphe : nombreEmp) {
                                // rphe.setSitio("<span class='badge bg-light text-dark'>" + rphe.getSitio() + "</span>");
                                // rphe.setDepto("<span class='badge bg-light text-dark'>" + rphe.getDepto() + "</span>");
                                // rphe.setNombreEmpleado("<span class='badge bg-light text-dark'>" + rphe.getNombreEmpleado() + "</span>");
                                rph1.setHoras(("<span class='badge bg-dark'>" + rphe.getNombreEmpleado() + "</span>"));
                                // rph1.setVeces(("<span class='badge bg-dark'>" + rphe.getSitio() + "</span>"));
                                rph1.setSemana(("<span class='badge bg-dark'>" + rphe.getSitio()  + " - " + rphe.getDepto()+ "</span>"));
                            }*/

                                //rph1.setResta(String.valueOf(hmst));

                                horasEmpleados.add(rph1);
                                Collections.sort(horasEmpleados, (a, b) -> a.getResta().compareToIgnoreCase(b.getResta()));
                            }

                            return horasEmpleados;

                        }else{
                            id = "9999";
                            filtro.setFechaInicio(DateUtil.StringToDate("06/08/2024" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                            filtro.setFechaFin(DateUtil.StringToDate("06/08/2024"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                            try {
                                horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, "9999", "9999");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return horasEmpleados;
                        }
                }else
                if(!idSitio.equals("") && idSitio != null ){
                    horasEmpleados = this.controlAsistenciaService.getReporteHorasSitiosICS(filtro,idSitio);
                    DecimalFormat df = new DecimalFormat("#.00");
                    // System.out.println(df.format(number));
                    Long total;
                    total = Long.valueOf(0);
                    if(horasEmpleados != null) {

                        int con = 1;
                        for (ReporteHorasICSDto rph : horasEmpleados) {
                            nombreEmp = this.controlAsistenciaService.getNombreEmpleadosICS(rph.getId(),idSitio);
                            String[] rst = rph.getHoras().split("-");
                            double h1 = 0;
                            double h2 = 0;
                            int m1 = 0;
                            int m2 = 0;
                            if (rst[0].toString().length() > 5) {
                                if (rst.length > 1) {
                                    Arrays.stream(rst).sorted();
                                    String aux;
                                    for(int i=1; i<=rst.length; i++) {
                                        for(int j=0; j<rst.length-i; j++) {
                                            if( rst[j].compareTo( rst[j+1] ) > 0 ) {
                                                aux   = rst[j];
                                                rst[j]  = rst[j+1];
                                                rst[j+1]= aux;
                                            }
                                        }
                                    }
                                    h1 = Double.parseDouble(rst[0].substring(1, 3));
                                    String[] tokens1 = rst[0].split(":");
                                    int secondsToMs1 = Integer.parseInt(tokens1[2].trim()) * 1000;
                                    int minutesToMs1 = Integer.parseInt(tokens1[1].trim()) * 60000;
                                    int hoursToMs1 = Integer.parseInt(tokens1[0].trim()) * 3600000;
                                    int total1 = secondsToMs1 + minutesToMs1 + hoursToMs1;

                                    String[] tokens12 = rst[rst.length-1].split(":");
                                    int secondsToMs12 = Integer.parseInt(tokens12[2].trim()) * 1000;
                                    int minutesToMs12 = Integer.parseInt(tokens12[1].trim()) * 60000;
                                    int hoursToMs12 = Integer.parseInt(tokens12[0].trim()) * 3600000;
                                    int total12 = secondsToMs12 + minutesToMs12 + hoursToMs12;

                                    Long t = new Long(total12) - new Long(total1);
                                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(t),
                                            TimeUnit.MILLISECONDS.toMinutes(t) % TimeUnit.HOURS.toMinutes(1),
                                            TimeUnit.MILLISECONDS.toSeconds(t) % TimeUnit.MINUTES.toSeconds(1));

                                    h2 = Double.parseDouble(rst[1].substring(1, 3));
                                    if (Integer.parseInt(hms.substring(0, 2)) < 8) {
                                        rph.setResta(String.valueOf((hms)));
                                        rph.setResta("<span class='badge bg-danger'>" + rph.getResta() + "</span>");
                                        rph.setHoras(rst[1] + " - "+ rst[0]);
                                    } else {

                                        rph.setResta(String.valueOf((hms)));
                                    }
                                    total = total + t;
                                } else {
                                    rph.setResta(String.valueOf(h1));
                                    rph.setResta("<span class='badge bg-danger'>" + 0 + "</span>");
                                }
                            }
                            logger.debug("h1 = " + h1);
                            logger.debug("h2 = " + h2);
                            // bhclst.setImpreso("<span class='badge badge-danger'>"+bhclst.getImpreso()+"</span>");
                            //  return JsonUtil.createJsonResponse(Bhclist);

                            con++;

                        }
                        if (horasEmpleados.size() > 0) {
                            String hmst = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(total),
                                    TimeUnit.MILLISECONDS.toMinutes(total) % TimeUnit.HOURS.toMinutes(1),
                                    TimeUnit.MILLISECONDS.toSeconds(total) % TimeUnit.MINUTES.toSeconds(1));
                            ReporteHorasICSDto rph1 = new ReporteHorasICSDto();
                            if (Integer.parseInt(hmst.substring(0, 2)) < 40) {
                                rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                rph1.setResta(String.valueOf((hmst)));
                                rph1.setResta("<span class='badge bg-danger'>" + rph1.getResta() + "</span>");
                            } else {

                                rph1.setVeces("<span class='badge bg-dark'>" +"Total Horas: "+ "</span>");
                                rph1.setResta(String.valueOf((hmst)));
                                rph1.setResta("<span class='badge bg-dark'>" + rph1.getResta() + "</span>");
                                // rph1.setVeces("<span class='badge bg-dark'>" + rph1.getVeces() + "</span>");
                            }
                          /*  for (NombreEmpleadosICSDto rphe : nombreEmp) {
                                // rphe.setSitio("<span class='badge bg-light text-dark'>" + rphe.getSitio() + "</span>");
                                // rphe.setDepto("<span class='badge bg-light text-dark'>" + rphe.getDepto() + "</span>");
                                // rphe.setNombreEmpleado("<span class='badge bg-light text-dark'>" + rphe.getNombreEmpleado() + "</span>");
                                rph1.setHoras(("<span class='badge bg-dark'>" + rphe.getNombreEmpleado() + "</span>"));
                                // rph1.setVeces(("<span class='badge bg-dark'>" + rphe.getSitio() + "</span>"));
                                rph1.setSemana(("<span class='badge bg-dark'>" + rphe.getSitio()  + " - " + rphe.getDepto()+ "</span>"));
                            }*/

                            //rph1.setResta(String.valueOf(hmst));

                            horasEmpleados.add(rph1);
                            Collections.sort(horasEmpleados, (a, b) -> a.getResta().compareToIgnoreCase(b.getResta()));
                        }

                        return horasEmpleados;

                    }else{
                        id = "9999";
                        filtro.setFechaInicio(DateUtil.StringToDate("06/08/2024" + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                        filtro.setFechaFin(DateUtil.StringToDate("06/08/2024"+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                        try {
                            horasEmpleados = this.controlAsistenciaService.getReporteHorasICS(filtro, "9999", "9999");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return horasEmpleados;
                    }
                }

                return horasEmpleados;
            }else{
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    class AgeComparator implements Comparator<ReporteHorasICSDto> {
        @Override
        public int compare(ReporteHorasICSDto a, ReporteHorasICSDto b) {
            return Double.parseDouble(a.getResta()) > Double.parseDouble(b.getResta()) ? 1 : Double.parseDouble(a.getResta()) == Double.parseDouble(b.getResta()) ? 0 : -1;
        }
    }


    @RequestMapping(value = "getJustificaionesICS", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<JustificacionesICSDto> getJustificaionesICS(
            @RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta)
            throws ParseException {

        List<JustificacionesICSDto> recepcion = null;
        LocalDate localDate = LocalDate.now();
        try {

            logger.debug("buscar datos de control de asistencia de personal ICS");
            FiltroMxEnfermoDto filtro = new FiltroMxEnfermoDto();
            if (desde.equals("")){
                desde = hasta;
            }

            if(!desde.equals("")) {
                filtro.setFechaInicio(DateUtil.StringToDate(desde + " 00:00:00", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                filtro.setFechaFin(DateUtil.StringToDate(hasta+" 23:59:59", Constants.STRING_FORMAT_DD_MM_YYYY_HH24));
                UnicodeEscaper escaper = UnicodeEscaper.above(127);
                recepcion = this.controlAsistenciaService.getJustificacionesICS(filtro);
                return recepcion;
            }else{
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "getGuardarJustificacionesICS", method = RequestMethod.POST)
    public ResponseEntity<String> GuardarJustificacionesICS (@RequestParam(value = "userId", required=false, defaultValue="") String userId
            , @RequestParam( value = "tipoJust", required = false, defaultValue=""  ) String tipoJust
            , @RequestParam( value = "fechaInicioCons",          required = false, defaultValue=""  ) String fechaInicioCons
            , @RequestParam( value = "fechaFinCons",        required = false, defaultValue=""  ) String fechaFinCons
    ) throws Exception {
        try{

            JustificacionesICS just = new JustificacionesICS();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            String ComputerIp = InetAddress.getLocalHost().getHostAddress();
            just.setDeviceid(nameComputer);
            just.setRecordIp(ComputerIp);
            just.setEstado('1');
            just.setPasive('0');
            just.setRecordDate(new Date());
            just.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
            String fechaF = (fechaFinCons.substring(6,10)+"-"+fechaFinCons.substring(0,2)+"-"+fechaFinCons.substring(3,5)+"-"+ fechaFinCons.substring(11,fechaFinCons.length()));
            String fechaI = (fechaInicioCons.substring(6,10)+"-"+fechaInicioCons.substring(0,2)+"-"+fechaInicioCons.substring(3,5)+"-"+ fechaInicioCons.substring(11,fechaInicioCons.length()));

            just.setIdUser(userId);
            just.setIdTipoJustificacion(tipoJust);
            just.setFechaInicio(fechaI);
            just.setFechaFin(fechaF);
                    controlAsistenciaService.saveOrUpdateJustificaciones(just);
                    return JsonUtil.createJsonResponse(just);

        }
        catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private JustificacionesICS ParseJsonRequestToJustificaciones(HttpServletRequest request) throws Exception{
        JustificacionesICS justificacionesICS = new JustificacionesICS();
        String json = "";

        String idusr = null;
        String identficadorEquipo = null;
        String estado = null;
        String pasivo = null;
        DateTime fechaReg = null;
        String usuarioReg = null;
        String descJust = null;
        String fechaInicio = null;
        String fechaFin = null;
        String idTipoJust = null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
        json = br.readLine();
        //Recuperando Json enviado desde el cliente
        JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
        String just = jObjectRespuestas.get("justificacionInput").toString();



        JsonObject jsonpObject = new Gson().fromJson(just, JsonObject.class);

        if (jsonpObject.get("userId")!=null && !jsonpObject.get("userId").getAsString().isEmpty()) idusr = jsonpObject.get("userId").getAsString();
        if (jsonpObject.get("tipoJust")!=null && !jsonpObject.get("tipoJust").getAsString().isEmpty()) idTipoJust = jsonpObject.get("tipoJust").getAsString();
        if (jsonpObject.get("tipoJust")!=null && !jsonpObject.get("tipoJust").getAsString().isEmpty()) descJust = jsonpObject.get("tipoJust").getAsString();

        if (jsonpObject.get("fechaInicioCons")!=null && !jsonpObject.get("fechaInicioCons").getAsString().isEmpty()) fechaInicio = jsonpObject.get("fechaInicioCons").getAsString();
        if (jsonpObject.get("fecha_fin")!=null && !jsonpObject.get("fecha_fin").getAsString().isEmpty()) fechaFin = jsonpObject.get("fecha_fin").getAsString();

        justificacionesICS.setIdUser(idusr);
        justificacionesICS.setDescripcionJust(idusr);
        justificacionesICS.setIdTipoJustificacion(idusr);
        justificacionesICS.setFechaInicio(fechaInicio);
        justificacionesICS.setFechaInicio(fechaFin);


        return justificacionesICS;
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
