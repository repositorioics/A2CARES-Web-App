package ni.org.ics.webapp.service.hemodinamica;

import ni.org.ics.webapp.domain.catalogs.RangosFrecuenciasCardiacas;
import ni.org.ics.webapp.domain.catalogs.RangosPresion;
import ni.org.ics.webapp.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.webapp.domain.hemodinamica.HemoDetalle;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.HemoDetalleDto;
import ni.org.ics.webapp.service.UsuarioService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Lizandro Serrano on 15/08/2024.
 */
@Service
@Transactional
public class HemodinamicaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "usuarioService")
    private UsuarioService usuarioService;

    @SuppressWarnings("unchecked")
    public void saveOrUpdate(DatosHemodinamica obj) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }

    @SuppressWarnings("unchecked")
    public void saveDetailHemo(HemoDetalle details) throws Exception {
       Session session = sessionFactory.getCurrentSession();
       session.saveOrUpdate(details);
    }

    @SuppressWarnings("unchecked")
    public List<DatosHemodinamica> getListadoHemoByID(String idParticipante) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosHemodinamica d where d.participante.codigo =:idParticipante and d.pasive='0' order by fecha desc");
        query.setParameter("idParticipante", idParticipante);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public DatosHemodinamica getHemoDinamicaById(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosHemodinamica d where d.idDatoHemo=:codigo and d.pasive='0'");
        query.setParameter("codigo", codigo);
        return (DatosHemodinamica) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<HemoDetalle> NumeroHemoDet(String idDatoHemo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle hd where hd.datoshemodinamica.idDatoHemo =:idDatoHemo order by hd.fecha asc, hd.hora asc ");
        query.setParameter("idDatoHemo", idDatoHemo);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public RangosPresion ObtenerRangosPresion(String sexo, Integer edad, String medida){
        Session session = sessionFactory.getCurrentSession();
        String consulta = "FROM RangosPresion WHERE :edad BETWEEN edadmin AND edadmax AND sexo =:sexo AND umedida =:medida";
        Query query = session.createQuery(consulta);
        query.setParameter("edad", edad);
        query.setParameter("sexo", sexo);
        query.setParameter("medida", medida);
        RangosPresion obj = (RangosPresion) query.uniqueResult();
        return obj;
    }


    @SuppressWarnings("unchecked")
    public RangosFrecuenciasCardiacas ObtenerFCardiaca(String medida, Integer edad){
        Session session = sessionFactory.getCurrentSession();
        String consulta ="from RangosFrecuenciasCardiacas where :edad between edadmin and edadmax and umedida =:medida";
        Query query = session.createQuery(consulta);
        query.setParameter("medida", medida);
        query.setParameter("edad", edad);
        RangosFrecuenciasCardiacas obj = (RangosFrecuenciasCardiacas) query.uniqueResult();
        return obj;
    }

    @SuppressWarnings("unchecked")
    public List<Personal> getPersonal(HashSet<String> ids){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.cargo.messageKey in (:ids) and p.pasive ='0' order by p.nombre desc ");
        query.setParameterList("ids",ids);
        return query.list();
    }

    public boolean SiExisteHemo(String idDatoHemo, Date fecha, String horas) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from HemoDetalle h where " + "h.datoshemodinamica.idDatoHemo =:idDatoHemo and  h.fecha =:fecha and h.hora =:horas");
        query.setParameter("idDatoHemo", idDatoHemo);
        query.setParameter("fecha", fecha);
        query.setParameter("horas", horas);
        return query.list().size() > 0;
    }
    public List<HemoDetalle> getListHemoDetalle(String idDatoHemo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle det where " + "det.datoshemodinamica.idDatoHemo =:idDatoHemo order by  det.fecha,det.hora asc ");
        query.setParameter("idDatoHemo",idDatoHemo);
        return  query.list();
    }
    public List<HemoDetalleDto> getListHemoDetalleDto(String idDatoHemo){
        Session session = sessionFactory.getCurrentSession();
        String SQL = "SELECT idHemoDetalle AS idHemoDetalle,IDENTIFICADOR_EQUIPO AS identificadorEquipo,ESTADO AS estado,PASIVO AS pasivo,FECHA_REGISTRO AS fechaRegistro,USUARIO_REGISTRO AS usuarioRegistro,signo AS signo,dx AS dx,CONCAT(DATE_FORMAT(fecha,'%d/%m/%Y'), ' ' ,hora) AS fecha_hora,hora AS hora,nivelConciencia AS nivelConciencia,ps AS ps,pd AS pd,pp AS pp,pam AS pam,fc AS fc,fr AS fr,tc AS tc,sa AS sa,extremidades AS extremidades,llenadoCapilar AS llenadoCapilar,pulsoCalidad AS pulsoCalidad,diuresis AS diuresis,densidadUrinaria AS densidadUrinaria,personaValida AS personaValida,idDatoHemo AS idDatoHemo,cantidad_orina AS cantidadOrina,cargas_iv AS cargasIV,suero_oral AS sro FROM hemodetalle h WHERE h.idDatoHemo =:idDatoHemo AND h.PASIVO='0' ORDER BY fecha_hora asc;";
        Query query = session.createSQLQuery(SQL);
        query.setParameter("idDatoHemo",idDatoHemo);
        query.setResultTransformer(Transformers.aliasToBean(HemoDetalleDto.class));
        return  query.list();
    }
    @SuppressWarnings("unchecked")
    public HemoDetalle getByHemoDetalleId(String idHemoDetalle){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle h where " + "h.idHemoDetalle =:idHemoDetalle");
        query.setParameter("idHemoDetalle", idHemoDetalle);
        HemoDetalle objDet =(HemoDetalle) query.uniqueResult();
        return objDet;
    }
    @SuppressWarnings("unchecked")
    public Personal getPersonalById(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.idPersona =:codigo");
        query.setParameter("codigo",codigo);
        return (Personal) query.uniqueResult();
    }
}
