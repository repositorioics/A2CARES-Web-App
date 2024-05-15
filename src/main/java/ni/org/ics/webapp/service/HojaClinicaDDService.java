package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.audit.AuditTrail;
import ni.org.ics.webapp.domain.audit.CorrectionsTrail;
import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.clinical.HojaClinicaDobleDigitacion;
import ni.org.ics.webapp.dto.DiferenciasHojasDigitadasDto;
import ni.org.ics.webapp.dto.HojaClinicaDobleDDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 1/12/2021.
 */
@Transactional
@Service("hojaClinicaDDService")
public class HojaClinicaDDService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "auditTrailService")
    private AuditTrailService auditTrailService;

    @Resource(name = "correctionsTrailService")
    private CorrectionsTrailService correctionsTrailService;

    public void saveOrUpdate(HojaClinicaDobleDigitacion hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hojaClinica);
    }
    public void saveOrUpdate1(HojaClinica hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hojaClinica);
    }
    public void saveOrUpdate2(HojaClinicaDobleDigitacion hojaClinica){
        try {
            Session session = sessionFactory.getCurrentSession();
           // session.delete(hojaClinica);
            session.saveOrUpdate(hojaClinica);
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }

    }

    @SuppressWarnings("unchecked")
    public String getObtenerDatosHojaClinica(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call getObtenerDatosHojaClinica(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
         //   query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            return query.list().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    public List<HojaClinicaDobleDigitacion> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from HojaClinicaDobleDigitacion ");
        return query.list();
    }

    public List<HojaClinicaDobleDDto> get(String codigoPart, Date fechaInicioCons, Date fechaFinCons){
        Session session = sessionFactory.getCurrentSession();
        String strQuery = "select h.codigoParticipante.codigo as codigo, concat(h.codigoParticipante.nombre1, ' ', h.codigoParticipante.nombre2, ' ', h.codigoParticipante.apellido1, ' ', h.codigoParticipante.apellido2) as nombreCompleto," +
                "h.numHojaConsulta as numHojaConsulta, h.recordUser as usuarioRegistro, "+
                " DATE_FORMAT(h.fechaConsulta, '%d/%m/%Y') as fechaConsulta, " +
                "(select p.spanish from MessageResource p where p.catKey = h.lugarAtencion and p.catRoot = 'CAT_LUGAR_CONS_HC') as lugarAtencion, " +
                "(select p.spanish from MessageResource p where p.catKey = h.consulta and p.catRoot = 'CAT_TIPO_CONSULTA') as tipoConsulta, " +
                "(select concat(cast(p.codigo as string), ' - ', p.nombre) from Personal p where p.codigo = h.medico) as medico, " +
                "(select concat(cast(p.codigo as string), ' - ', p.nombre) from Personal p where p.codigo = h.enfermeria)  as enfermeria  " +
                "from HojaClinicaDobleDigitacion h where 1=1";
        if (codigoPart != null) strQuery += " and h.codigoParticipante.codigo = :codigoPart";
        if (fechaInicioCons != null && fechaFinCons != null) strQuery += " and h.fechaConsulta between :fechaInicioCons and :fechaFinCons";
        Query query = session.createQuery(strQuery);
        if (codigoPart != null) query.setParameter("codigoPart", codigoPart);
        if (fechaInicioCons != null && fechaFinCons != null) {
            query.setParameter("fechaInicioCons", fechaInicioCons);
            query.setParameter("fechaFinCons", fechaFinCons);
        }
        query.setResultTransformer(Transformers.aliasToBean(HojaClinicaDobleDDto.class));

        return query.list();
    }


    @SuppressWarnings("unchecked")
    public List<String> getcodigoSupervisor(String codigo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call fn_extaer_codigo_medico_supervisor(:codigo)");
            query.setParameter("codigo", codigo);
           // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.list();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getNumHojaDigitadaH1(Integer codigo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call fn_extaer_si_existe_hoja_en_H1(:codigo)");
            query.setParameter("codigo", codigo);
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.list();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getNumHojaDigitadaH2(Integer codigo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call fn_extaer_si_existe_hoja_en_H2(:codigo)");
            query.setParameter("codigo", codigo);
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.list();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
}
