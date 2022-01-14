package ni.org.ics.webapp.web.controller;

import com.google.gson.Gson;
import ni.org.ics.webapp.domain.audit.AuditTrail;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.PersonalService;
import ni.org.ics.webapp.service.UsuarioService;
import ni.org.ics.webapp.users.model.UserSistema;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 10/12/2021.
 */
@Controller
@RequestMapping("/admin/personal/*")
public class AdminPersonalController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCatalogsController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;

    @Resource(name="personalService")
    private PersonalService personalService;

    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerEntidades(Model model) throws ParseException {
        logger.debug("Mostrando registros en JSP");
        List<Personal> entidades = personalService.getAll();
        model.addAttribute("entidades", entidades);
        return "admin/personal/list";
    }

    /**
     * Custom handler for displaying a entidad.
     *
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/add/")
    public ModelAndView showEntity() {
        ModelAndView mav;
        List<MessageResource> cargos = messageResourceService.getCatalogo("CAT_CARGO");
        Personal entidad = new Personal();
        mav = new ModelAndView("admin/personal/enterForm");
        mav.addObject("entidad",entidad);
        mav.addObject("catCargo",cargos);
        return mav;
    }

    @RequestMapping("/edit/{codigo}/")
    public ModelAndView showEntity(@PathVariable("codigo") String codigo) {
        ModelAndView mav;
        Personal entidad = personalService.getPersonalByCodigo(Integer.valueOf(codigo));
        List<MessageResource> cargos = messageResourceService.getCatalogo("CAT_CARGO");
        if(entidad==null){
            mav = new ModelAndView("403");
        }
        else{
            mav = new ModelAndView("admin/personal/enterForm");
            mav.addObject("entidad",entidad);
            mav.addObject("catCargo",cargos);
        }
        return mav;
    }

    /**
     * Custom handler for saving.
     *
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping( value="/save/", method=RequestMethod.POST)
    public ResponseEntity<String> processEntity(@RequestParam( value="codigo", required=false ) String codigo
            , @RequestParam( value="nombre", required=true) String nombre
            , @RequestParam( value="cargo", required=true) String cargo
    )
    {
        try{
            WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            Personal entidad = new Personal();
            if (codigo != null && !codigo.isEmpty()) {
                entidad = personalService.getPersonalByCodigo(Integer.valueOf(codigo));
                if (entidad == null) {
                    Gson gson = new Gson();
                    String json = gson.toJson("No se encontro entidad para editar");
                    return JsonUtil.createJsonResponse(json);
                }
            } else {
                //para nuevos registros es necesario poner un valor por defecto en el codigo de personal, aunque se setee con el trigger tg_set_codigo_personal
                entidad.setIdPersona(0);
            }
            entidad.setNombre(nombre);
            MessageResource cargoVal = messageResourceService.getMensaje(cargo);
            entidad.setCargo(cargoVal);
            entidad.setRecordUser(usuarioActual.getUsername());
            entidad.setRecordDate(new Date());
            entidad.setRecordIp(wad.getRemoteAddress());
            this.personalService.saveOrUpdate(entidad);
            return JsonUtil.createJsonResponse(entidad);
        }
        catch (DataIntegrityViolationException e){
            String message = e.getMostSpecificCause().getMessage();
            Gson gson = new Gson();
            String json = gson.toJson(message);
            return JsonUtil.createJsonResponse(json);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return JsonUtil.createJsonResponse(json);
        }

    }

    /**
     * Custom handler for disabling.
     *
     * @param codigo the ID to disable
     * @param redirectAttributes
     * @return a String
     */
    @RequestMapping("/disable/{codigo}/")
    public String disableEntity(@PathVariable("codigo") String codigo,
                                RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        Personal entidad = this.personalService.getPersonalByCodigo(Integer.valueOf(codigo));
        if(entidad!=null){
            entidad.setPasive('1');
            this.personalService.saveOrUpdate(entidad);
            redirectAttributes.addFlashAttribute("disabledEntity", true);
            redirectAttributes.addFlashAttribute("entityName", entidad.getNombre());
            redirecTo = "redirect:/admin/personal/";
        }
        else{
            redirecTo = "403";
        }
        return redirecTo;
    }


    /**
     * Custom handler for enabling.
     *
     * @param codigo the ID to enable
     * @param redirectAttributes
     * @return a String
     */
    @RequestMapping("/enable/{codigo}/")
    public String enableEntity(@PathVariable("codigo") String codigo,
                               RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        Personal entidad = this.personalService.getPersonalByCodigo(Integer.valueOf(codigo));
        if(entidad!=null){
            entidad.setPasive('0');
            this.personalService.saveOrUpdate(entidad);
            redirectAttributes.addFlashAttribute("enabledEntity", true);
            redirectAttributes.addFlashAttribute("entityName", entidad.getNombre());
            redirecTo = "redirect:/admin/personal/";
        }
        else{
            redirecTo = "403";
        }
        return redirecTo;
    }
}
