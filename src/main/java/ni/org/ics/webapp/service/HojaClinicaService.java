package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.clinical.HojaClinicaDobleDigitacion;
import ni.org.ics.webapp.dto.DiferenciasHojasDigitadasDto;
import ni.org.ics.webapp.dto.HojaClinicaDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
 * Created by miguel on 1/12/2021.
 */
@Transactional
@Service("hojaClinicaService")
public class HojaClinicaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(HojaClinica hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hojaClinica);
    }
    public void deleteHc(HojaClinica hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SET FOREIGN_KEY_CHECKS=0");
        session.delete(hojaClinica);
    }

    public HojaClinica getHC1(Integer numero_hoja) {
        // Retrieve session from Hibernate
        try {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HojaClinica where " +
                "numHojaConsulta =:numero_hoja");
        query.setParameter("numero_hoja",numero_hoja);

        query.setResultTransformer(Transformers.aliasToBean(HojaClinica.class));
        return (HojaClinica) query.uniqueResult();
    }catch (Exception e){
        System.err.println(e.toString());
        throw e;
    }
    }

    public List<HojaClinica> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from HojaClinica ");
        return query.list();
    }

    public List<HojaClinicaDto> get(String codigoPart, Date fechaInicioCons, Date fechaFinCons){
        Session session = sessionFactory.getCurrentSession();
        String strQuery = "select h.codigoParticipante.codigo as codigo, concat(h.codigoParticipante.nombre1, ' ', h.codigoParticipante.nombre2, ' ', h.codigoParticipante.apellido1, ' ', h.codigoParticipante.apellido2) as nombreCompleto," +
                " DATE_FORMAT(h.fechaConsulta, '%d/%m/%Y') as fechaConsulta, " +
                "(select p.spanish from MessageResource p where p.catKey = h.lugarAtencion and p.catRoot = 'CAT_LUGAR_CONS_HC') as lugarAtencion, " +
                "(select p.spanish from MessageResource p where p.catKey = h.consulta and p.catRoot = 'CAT_TIPO_CONSULTA') as tipoConsulta, " +
                "(select concat(cast(p.codigo as string), ' - ', p.nombre) from Personal p where p.codigo = h.medico) as medico, " +
                "(select concat(cast(p.codigo as string), ' - ', p.nombre) from Personal p where p.codigo = h.enfermeria)  as enfermeria  " +
                "from HojaClinica h where 1=1";
        if (codigoPart != null) strQuery += " and h.codigoParticipante.codigo = :codigoPart";
        if (fechaInicioCons != null && fechaFinCons != null) strQuery += " and h.fechaConsulta between :fechaInicioCons and :fechaFinCons";
        Query query = session.createQuery(strQuery);
        if (codigoPart != null) query.setParameter("codigoPart", codigoPart);
        if (fechaInicioCons != null && fechaFinCons != null) {
            query.setParameter("fechaInicioCons", fechaInicioCons);
            query.setParameter("fechaFinCons", fechaFinCons);
        }
        query.setResultTransformer(Transformers.aliasToBean(HojaClinicaDto.class));

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
    public List<DiferenciasHojasDigitadasDto> getObtenerDiferenciasHC(Date fecha_digitacion1, Date fecha_digitacion2) throws Exception{
        try{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("call extrae_hojas_doble_digitacion_pfecha(:fecha_digitacion1,:fecha_digitacion2)");
        query.setParameter("fecha_digitacion1", fecha_digitacion1);
        query.setParameter("fecha_digitacion2", fecha_digitacion2);
        query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
        return query.list();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public DiferenciasHojasDigitadasDto getObtenerRegHojaClinica(String codigo_participante1, String numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call getObtenerRegHojaClinica(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            return (DiferenciasHojasDigitadasDto) query.uniqueResult();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public Integer getObtenerDatosHojaClinica(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select getFnObtenerDatosHojaClinica(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            //   query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            return Integer.parseInt(query.list().toString().substring(1,query.list().toString().length()-1));
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public DiferenciasHojasDigitadasDto getObtenerRegHojaClinica2(String codigo_participante1, String numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call getObtenerRegHojaClinica2(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            return (DiferenciasHojasDigitadasDto) query.uniqueResult();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }


}
