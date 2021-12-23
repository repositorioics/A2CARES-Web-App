package ni.org.ics.webapp.service.reportes;

import com.google.common.base.Predicate;

import ni.org.ics.webapp.domain.core.CartaConsentimiento;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.survey.EncuestaParticipante;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.service.*;
import ni.org.ics.webapp.users.model.UserSistema;
import ni.org.ics.webapp.web.utils.DateUtil;
import ni.org.ics.webapp.web.utils.FilterLists;
import ni.org.ics.webapp.web.utils.pdf.DatosGeneralesParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */

@Service("reportesPdfService")
@Transactional
public class ReportesPdfService {
    @Resource(name = "participanteService")
    ParticipanteService participanteService;
    @Resource(name = "participanteProcesosService")
    ParticipanteProcesosService participanteProcesosService;
    @Resource(name = "messageResourceService")
    MessageResourceService messageResourceService;
    @Resource(name = "cartaConsentimientoService")
    CartaConsentimientoService cartaConsentimientoService;

    @Resource(name = "tamizajeService")
    TamizajeService tamizajeService;
    @Resource(name = "usuarioService")
    UsuarioService usuarioService;
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    @Resource(name = "encuestaParticipanteService")
    private EncuestaParticipanteService encuestaParticipanteService;

    private final static String SI="SI";
    private final static String NO="NO";
    private final static String NINGUNO="NINGUNO";
    private final static String NINGUNA="NINGUNA";
    private final static String DESCONOCIDO="DESCONOCIDO";
    private final static String CSSF = "SOCRATES FLORES VIVAS";
    private List<MessageResource> catalogos = new ArrayList<MessageResource>();
    private List<UserSistema> usuarios = new ArrayList<UserSistema>();

    /***
     * Obtiene todas las cartas de consentimiento que coinciden con los parámetros de búsqueda especificados
     * Se ordena por fecha de firma (los mas antiguos primero) y por estudio (iniciando por familia)
     * Cuándo se filtran todos los estudios, se obtiene sólo la carta mas antigua
     * @param estudio codigo del estudio a filtrar, 0 si se desea filtrar por todos los estudios
     * @param codigoParticipante participante que se desea filtrar, null si no hay participante en específico
     * @param fechaInicio rango inicial de fecha de firma de carta de consentimiento, null si no hay rango
     * @param fechaFin rango final de fecha de firma de carta de consentimiento, null si no hay rango
     * @return List<CartaConsentimiento>
     */
    public List<CartaConsentimiento> getCartasConsentimientoByFiltro(Integer estudio, Integer codigoParticipante, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select cc from CartaConsentimiento cc inner join cc.tamizaje t inner join cc.participante p " +
                "where 1=1 ";//(cc.reconsentimiento is null or cc.reconsentimiento = '0')

        //sub consulta para obtener la carta mas antigua
        String sqlSubQuery = "select min(cc2.fechaFirma) from CartaConsentimiento cc2 inner join cc2.tamizaje t2 inner join cc2.participante p2 " +
                "where p2.codigo = p.codigo "; //(cc2.reconsentimiento is null or cc2.reconsentimiento = '0') and

        if (estudio>0) {
            sqlQuery += " and t.estudio.codigo = :estudio";
            sqlSubQuery += " and t2.estudio.codigo = t.estudio.codigo";
        }
        if(codigoParticipante != null) {
            sqlQuery += " and p.codigo = :codigoParticipante";
        }
        if (fechaInicio!=null && fechaFin!=null) {
            sqlQuery += " and cc.fechaFirma between :fechaInicio and :fechaFin";
            sqlSubQuery += " and cc2.fechaFirma between :fechaInicio and :fechaFin";
        }
        sqlQuery += " and cc.fechaFirma = ("+sqlSubQuery+") order by cc.fechaFirma asc, t.estudio.codigo asc, p.codigo";

        Query query = session.createQuery(sqlQuery);

        if(codigoParticipante != null)
            query.setParameter("codigoParticipante", codigoParticipante);
        if (estudio>0)
            query.setParameter("estudio", estudio);
        if (fechaInicio!=null && fechaFin!=null) {
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
        }

        return query.list();
    }

    /**
     * Obtiene la descripción en lenguaje español de un mensaje
     * @param messageKey identificador del mansaje a evaluar
     * @return String si se encuentra el mensaje, vacío en caso contrario
     */
    private String getMensajeSpanishByMessageKey(final String messageKey){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getMessageKey().equals(messageKey);
            }
        };
        Collection<MessageResource> resultado = FilterLists.filter(catalogos, predicate);
        return resultado.size()>0?resultado.iterator().next().getSpanish():"";
    }

    /**
     * Obtiene una mensaje tipo catálogo por medio del catalago principal y una llave de la lista
     * @param catKey key a evaluar
     * @param catalogo catálogo
     * @return MessageResource si es encontrado, null en caso contrario
     */
    private MessageResource getMensajeByCatalogAndCatKey(final String catKey, final String catalogo){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getCatRoot()!=null && messageResource.getCatKey()!=null && messageResource.getCatRoot().equals(catalogo) && messageResource.getCatKey().equals(catKey);
            }
        };
        Collection<MessageResource> resultado = FilterLists.filter(catalogos, predicate);
        return resultado.size()>0?resultado.iterator().next():null;
    }

    /***
     * Obtiene una Colección de mensajes tipo catálogo por medio del catalago principal y varias llaves de la lista
     * @param catKeys key de los elementos dentro del catálogo
     * @param catalogo catálogo
     * @return Collection<MessageResource>
     */
    private Collection<MessageResource> getMensajesByCatalogAndCatKey(final String catKeys, final String catalogo){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getCatRoot()!=null && messageResource.getCatKey()!=null && messageResource.getCatRoot().equals(catalogo) && catKeys.contains(messageResource.getCatKey());
            }
        };
        return FilterLists.filter(catalogos, predicate);
    }

    /***
     * Obtiene el nombre completo de un usuario del sistema mediante su nombre de usuario
     * @param username nombre de usuario a evaluar
     * @return String nombre completo
     */
    private String getUserCompleteNameByUsername(final String username){
        Predicate<UserSistema> predicate = new Predicate<UserSistema>() {
            @Override
            public boolean apply(UserSistema messageResource) {
                return messageResource.getUsername().equals(username);
            }
        };
        Collection<UserSistema> resultado = FilterLists.filter(usuarios, predicate);
        return resultado.size()>0?resultado.iterator().next().getCompleteName():"";
    }

}
