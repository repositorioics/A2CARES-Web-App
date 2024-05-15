package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.domain.clinical.HojaClinicaDobleDigitacion;
import ni.org.ics.webapp.dto.BhcDto;
import ni.org.ics.webapp.dto.DiferenciasHojasDigitadasDto;
import ni.org.ics.webapp.dto.HojaClinicaDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
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
    public void saveOrUpdate2(HojaClinicaDobleDigitacion hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hojaClinica);
    }

    public void updatehc(HojaClinica hojaClinica){
        Session session = sessionFactory.getCurrentSession();
        session.update(hojaClinica);
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
                "h.numHojaConsulta as numHojaConsulta, h.recordUser as usuarioRegistro, "+
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
      public String getGuardarEdicionBHC(String codigo_participante, int bhc_id) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select sp_modificar_ProcesadaCSFV(:codigo_participante,:bhc_id)");
            query.setParameter("codigo_participante", codigo_participante);
            query.setParameter("bhc_id", bhc_id);
            // query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            //  return (DiferenciasHojasDigitadasDto) query.uniqueResult();
            System.err.println("codigo_participante" + codigo_participante);
            System.err.println("bhc_id" + bhc_id);
            return query.toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    public List<BhcDto> getObtenerBHCEnviadas(Date fechaInicio, String numEnvio,String participante, int bhcId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("call sp_extraer_bhc_enviadas(:fechaInicio,:numEnvio,:participante,:bhcId)");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("numEnvio", numEnvio);
        query.setParameter("participante", participante);
        query.setParameter("bhcId", bhcId);
        query.setResultTransformer(Transformers.aliasToBean(BhcDto.class));
        return query.list();
    }
    public List<BhcDto> getObtenerBHCEnviadas2(Date fechaInicio, String numEnvio,String participante, int bhcId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("call sp_extraer_bhc_enviadas2(:fechaInicio,:numEnvio,:participante,:bhcId)");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("numEnvio", numEnvio);
        query.setParameter("participante", participante);
        query.setParameter("bhcId", bhcId);
        query.setResultTransformer(Transformers.aliasToBean(BhcDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public String getNumHojaDigitadaH1(Integer codigo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call fn_extaer_si_existe_hoja_en_H1(:codigo)");
            query.setParameter("codigo", codigo);
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.list().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public String getNumHojaDigitadaH2(Integer codigo)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call fn_extaer_si_existe_hoja_en_H2(:codigo)");
            query.setParameter("codigo", codigo);
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.list().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public Date getfn_guardar_usuario_fecha_Modifica_HC1(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select fn_guardar_usuario_fecha_Modifica_HC1(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            //   query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
          //  return Integer.parseInt(query.list().toString().substring(1,query.list().toString().length()-1));
            System.err.println("RECUPERANDO: " + query.list().toString());
            return (Date) query.uniqueResult();

        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public Date getfn_guardar_usuario_fecha_Modifica_HCDD1(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select fn_guardar_usuario_fecha_Modifica_HCDD1(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            //   query.setResultTransformer(Transformers.aliasToBean(DiferenciasHojasDigitadasDto.class));
            //  return Integer.parseInt(query.list().toString().substring(1,query.list().toString().length()-1));
            System.err.println("RECUPERANDO: " + query.list().toString());
            return (Date) query.uniqueResult();

        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public String getfn_guardar_usuario_fecha_Modifica_HC2(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select fn_guardar_usuario_fecha_Modifica_HC2(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            return (String) query.uniqueResult();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public String getfn_guardar_usuario_fecha_Modifica_HCDD2(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select fn_guardar_usuario_fecha_Modifica_HCDD2(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            return (String) query.uniqueResult();
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

    @SuppressWarnings("unchecked")
    public HojaClinica getObtenerRegHojaClinicaCorrecH1(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call getObtenerRegHojaClinicaCorrecH1(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            query.setResultTransformer(Transformers.aliasToBean(HojaClinica.class));
            return (HojaClinica) query.uniqueResult();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public HojaClinicaDobleDigitacion getObtenerRegHojaClinicaCorrecH2(String codigo_participante1, Integer numero_hoja) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call getObtenerRegHojaClinicaCorrecH2(:codigo_participante1,:numero_hoja)");
            query.setParameter("codigo_participante1", codigo_participante1);
            query.setParameter("numero_hoja", numero_hoja);
            query.setResultTransformer(Transformers.aliasToBean(HojaClinicaDobleDigitacion.class));
            return (HojaClinicaDobleDigitacion) query.uniqueResult();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }


}
