package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.dto.FiltroMxEnfermoDto;
import ni.org.ics.webapp.dto.RecepcionEnfermoDto;
import ni.org.ics.webapp.web.utils.Constants;
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
        Query query = session.createQuery("select r.idRecepcion as idRecepcion, DATE_FORMAT(r.fechaRecepcion, '%d/%m/%Y') as fechaRecepcion, " +
                "r.codigo as participante, r.volumen as volumen, r.observacion as observacion, DATE_FORMAT(r.fis, '%d/%m/%Y') as fis, DATE_FORMAT(r.fif, '%d/%m/%Y') as fif, " +
                "r.categoria as categoria, (select m.spanish from MessageResource m where m.catRoot = 'CAT_TIPO_CONSULTA' and m.catKey = r.consulta) as consulta, " +
                "(select m.spanish from MessageResource m where m.catRoot = 'CAT_FASE_MX' and m.catKey = r.tipoMuestra) as tipoMuestra " +
                "  from RecepcionEnfermo r inner join r.participante p where r.pasive = '0' and r.enviado = '0' " +
                "order by p.codigo asc");
        query.setResultTransformer(Transformers.aliasToBean(RecepcionEnfermoDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<RecepcionEnfermoDto> getSerologiaEnfermosDto(FiltroMxEnfermoDto filtro)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r.idRecepcion as idRecepcion, DATE_FORMAT(r.fechaRecepcion, '%d/%m/%Y') as fechaRecepcion, " +
                "r.codigo as participante, r.volumen as volumen, r.observacion as observacion, DATE_FORMAT(r.fis, '%d/%m/%Y') as fis, DATE_FORMAT(r.fif, '%d/%m/%Y') as fif, " +
                "r.categoria as categoria, (select m.spanish from MessageResource m where m.catRoot = 'CAT_TIPO_CONSULTA' and m.catKey = r.consulta) as consulta, " +
                "(select m.spanish from MessageResource m where m.catRoot = 'CAT_FASE_MX' and m.catKey = r.tipoMuestra) as tipoMuestra, r.enviado as enviado, r.codigoBarra as codigoBarra " +
                "from RecepcionEnfermo r inner join r.participante p where r.pasive = '0' " +
                (filtro.getParticipante()!=null && !filtro.getParticipante().isEmpty()? " and p.codigo = :participante ":" ")+
                (filtro.getCodigoMx()!=null  && !filtro.getCodigoMx().isEmpty()? " and r.codigo like :codigoMx ":" ")+
                (filtro.getFechaInicio()!=null  && filtro.getFechaFin() != null ? " and r.fechaRecepcion between :fechaInicio and :fechaFin ":" ")+
                "order by p.codigo asc");
        if (filtro.getParticipante() != null && !filtro.getParticipante().isEmpty()) {
            query.setParameter("participante", filtro.getParticipante());
        }
        if (filtro.getCodigoMx() != null && !filtro.getCodigoMx().isEmpty()) {
            query.setParameter("codigoMx", "%"+filtro.getCodigoMx()+"%");
        }
        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
        }
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

    public String saveMuestraEnfermoDetalleEnvio(MuestraEnfermoEnvio envio, List<RecepcionEnfermo> listaNoEnviadas){
        Session session = sessionFactory.openSession();
        String codigosImprimir = null;
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
                        //FIF|FTOMA|1ESPACIO|*CODIGO_LAB|*QRBARCODE|*CANTIDADCOPIAS
                        //6/12/202117/12/2021 *A2.0001.R21.A.1*1*1
                        if (codigosImprimir == null) {
                            codigosImprimir = String.format(Constants.CODIGO_BARRA_FORMAT_PRINT, recepcionEnfermo.getCodigoBarra().replace("A2", "*A2"));
                        } else {
                            codigosImprimir = codigosImprimir+","+String.format(Constants.CODIGO_BARRA_FORMAT_PRINT, recepcionEnfermo.getCodigoBarra().replace("A2", "*A2"));
                        }
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
        return codigosImprimir;
    }
}
