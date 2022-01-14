package ni.org.ics.webapp.service.Retiro;

import ni.org.ics.webapp.domain.Retiros.Retiros;
import ni.org.ics.webapp.domain.catalogs.Razones_Retiro;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.ParticipanteSeroDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS on 29/10/2020.
 */
@Service("RetiroService")
@Transactional
public class RetiroService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Personal> getPersonalRecibeRetiro()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p order by p.nombre asc ");
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public ParticipanteSeroDto getDatosParticipanteByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as idparticipante, concat(p.nombre1,' ',p.nombre2,' ',p.apellido1,' ',p.apellido2) as nombreCompleto, p.casa.codigo as codigo_casa, p.fechaNac as fechaNacimiento, pp.retirado as estado " +
                " from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteSeroDto.class));
        return (ParticipanteSeroDto)query.uniqueResult();
    }



    /* Este Servicio Devuelve un Participante por parametro, falta validar que el participante este activo */
    @SuppressWarnings("unchecked")
    public Participante getParticipante(String parametro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Participante p where p.codigo =:parametro");
            query.setParameter("parametro", parametro);
            return (Participante) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

      public Personal getSupervisorById(Integer idpersona)throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.idPersona= :idpersona ");
        query.setParameter("idpersona",idpersona);
        Personal obj = (Personal) query.uniqueResult();
        return obj;
    }

    //Obtener Supervisor y Directora.
    @SuppressWarnings("unchecked")
    public List<Personal> getSupervisor()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal pc where pc.pasive='0'");
        return query.list();
    }

    //OBTENER SUPERVISOR Y PERSONAL DE MUESTREO
    @SuppressWarnings("unchecked")
    public List<Personal> getSupervisorAndDigitador()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal pc where pc.pasive='0' ");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Razones_Retiro> getRazonesRetiros(Integer grupo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Razones_Retiro r where r.grupoid= :grupo");
        query.setParameter("grupo", grupo);
        return (List<Razones_Retiro>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Retiros> getListadoRetiroByID(String idParticipante) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.participante.codigo =:idParticipante order by r.fecha_retiro asc");
            query.setParameter("idParticipante", idParticipante);
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public Retiros getRetiroByID(Integer idretiro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.idretiro =:idretiro order by r.fecha_retiro desc ");
            query.setParameter("idretiro", idretiro);
            Retiros objRetiro = (Retiros) query.uniqueResult();
            return objRetiro;
        }catch (Exception e){
            throw e;
        }
    }

    public List<Razones_Retiro> getlistaDeRazonRetiro()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query  =  session.createQuery("from Razones_Retiro rr order by rr.motivo asc");
            return (List<Razones_Retiro>) query.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List<Razones_Retiro> getlistaDeRazonRetiroPorIdGrupo(Integer idgrupo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query  =  session.createQuery("from Razones_Retiro rr where rr.grupoid=:idgrupo order by rr.motivo asc");
            query.setParameter("idgrupo",idgrupo);
            return (List<Razones_Retiro>) query.list();
        }catch (Exception e){
            throw e;
        }
    }

    public Razones_Retiro getRazonRetiro(String razon)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query  =  session.createQuery("from Razones_Retiro rr where rr.motivo= :razon");
            query.setParameter("razon", razon);
            return (Razones_Retiro) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public void SaveRetiros(Retiros obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }
    //endregion


}
