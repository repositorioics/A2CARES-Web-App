package ni.org.ics.webapp.api;

import com.google.common.base.Predicate;
import com.google.gson.Gson;
import ni.org.ics.webapp.domain.audit.AuditTrail;
import ni.org.ics.webapp.dto.BHCParticipanteDto;
import ni.org.ics.webapp.language.DatabaseDrivenMessageSource;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.AuditTrailService;
import ni.org.ics.webapp.service.MessageResourceService;
import ni.org.ics.webapp.service.ParticipanteService;
import ni.org.ics.webapp.service.UsuarioService;
import ni.org.ics.webapp.users.model.UserSistema;
import ni.org.ics.webapp.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

/**
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/a2caresDatosGenerales/*")
public class DatosGeneralesController {
	private static final Logger logger = LoggerFactory.getLogger(DatosGeneralesController.class);

	@Resource(name = "participanteService")
	private ParticipanteService participanteService;


	/**
	 * Acepta una solicitud GET para JSON
	 * @return JSON
	 */
	@RequestMapping(value = "Bhc/participantes/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BHCParticipanteDto getParticipantes(String codigo) {
		try {
			logger.info("Descargando toda la informacion de participantes para imprimir BHC en ESTUDIOS");
			BHCParticipanteDto respuestaList = participanteService.getBHCParticipantes(codigo);
			if (respuestaList == null) {
				logger.debug("Nulo");
			}
			return respuestaList;
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

}
