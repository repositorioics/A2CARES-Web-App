package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.dto.RecepcionEnfermoDto;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 29/1/2022.
 */
@Transactional
@Service("recepcionEnfermoService")
public class RecepcionEnfermoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<RecepcionEnfermo> getRecepcionsEnfermos() throws Exception
    {
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionEnfermo where pasive = '0' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/" + String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }

    public void saveOrUpdateRecepcionEnfermo(RecepcionEnfermo Recepcion){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Recepcion);
    }

    @SuppressWarnings("unchecked")
    public List<RecepcionEnfermoDto> getSerologiaEnfermosNoEnviadasDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend ='0';
        Query query = session.createQuery("select r.idRecepcion as idRecepcion, DATE_FORMAT(r.fechaRecepcion, '%d/%m/%Y') as fechaRecepcion, " +
                "p.codigo as participante, r.volumen as volumen, r.observacion as observacion, DATE_FORMAT(r.fis, '%d/%m/%Y') as fis, DATE_FORMAT(r.fif, '%d/%m/%Y') as fif, " +
                "r.categoria as categoria, r.consulta as consulta, r.tipoMuestra as tipoMuestra " +
                "  from RecepcionEnfermo r inner join r.participante p where r.pasive = '0' and r.enviado = '0' " +
                "order by p.codigo asc");
        //query.setParameter("noSend", noSend);
        query.setResultTransformer(Transformers.aliasToBean(RecepcionEnfermoDto.class));
        return query.list();
    }

    public List<RecepcionEnfermo> getSerologiaEnfermosNoEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionEnfermo s where s.fechaRecepcion between :fechaInicio and :fechaFin and s.pasive='0' and s.enviado='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }


    public boolean existeSerologia(Date fecha, String codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from RecepcionEnfermo s where s.fechaRecepcion =:fecha and s.participante.codigo =:codigo and s.tipoTubo = 'R'");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    public RecepcionEnfermo getRecepcionEnfermoById(String idRecepcion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionEnfermo where idRecepcion = :idRecepcion");
        query.setParameter("idRecepcion", idRecepcion);
        return (RecepcionEnfermo) query.uniqueResult();
    }

    public List<MuestraEnfermoEnvio> getMuestraEnfermoEnvioByDatesAndNumber(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MuestraEnfermoEnvio se where se.fecha between :fechaInicio and :fechaFin and se.numeroEnvio =:nEnvios");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

    public List<MuestraEnfermoDetalleEnvio> getAllMuestrasDetalleByDatesAndNumber(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MuestraEnfermoDetalleEnvio se where se.envio.fecha between :fechaInicio and :fechaFin and se.envio.numeroEnvio =:nEnvios  order by se.muestra.participante.codigo asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }
    public void saveOrUpdateMuestraEnfermoEnvio(MuestraEnfermoEnvio Recepcion){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Recepcion);
    }

    public void saveMuestraEnfermoDetalleEnvio(List<MuestraEnfermoDetalleEnvio> detalleEnvioList){
        Session session = sessionFactory.openSession();
        try {
            if (detalleEnvioList.size() > 0) {
                session.beginTransaction();
                for (MuestraEnfermoDetalleEnvio muestraEnfermoDetalleEnvio : detalleEnvioList) {
                    session.save(muestraEnfermoDetalleEnvio);
                    RecepcionEnfermo recepcionEnfermo = muestraEnfermoDetalleEnvio.getMuestra();
                    recepcionEnfermo.setEnviado("1");
                    session.save(recepcionEnfermo);
                }
                session.getTransaction().commit();
            }
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public void saveMuestraEnfermoDetalleEnvio(MuestraEnfermoEnvio envio, List<RecepcionEnfermo> listaNoEnviadas){
        Session session = sessionFactory.openSession();
        try {
            if (listaNoEnviadas.size() > 0) {
                session.beginTransaction();
                session.save(envio);// aqui guardo los datos del envio
                if (envio.getIdEnvio()!=null) {
                    for (RecepcionEnfermo recepcionEnfermo : listaNoEnviadas) {
                        //se registra el detalle del envio
                        MuestraEnfermoDetalleEnvio detalleEnvio = new MuestraEnfermoDetalleEnvio();
                        detalleEnvio.setMuestra(recepcionEnfermo);
                        detalleEnvio.setEnvio(envio);
                        session.save(detalleEnvio);
                        //se marca como enviada
                        recepcionEnfermo.setEnviado("1");
                        session.update(recepcionEnfermo);
                    }
                }
                session.getTransaction().commit();
            }
        }catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
    }
}
