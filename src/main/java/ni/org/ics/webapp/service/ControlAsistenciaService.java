package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.ControlAsistencia;
import ni.org.ics.webapp.domain.personal.JustificacionesICS;
import ni.org.ics.webapp.dto.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Everts Morales Reyes
 */

@Service("controlAsistenciaService")
@Transactional
public class ControlAsistenciaService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<ControlAsistencia> getControlAsistencia(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ControlAsistencia where pasive = '0' ");
        return query.list();
    }

    public void saveOrUpdateControlAsistencia(ControlAsistencia controlAsistencia) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(controlAsistencia);
    }
    public void saveOrUpdateJustificaciones(JustificacionesICS justificacionesICS) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(justificacionesICS);
    }

    @SuppressWarnings("unchecked")
    public List<ControlAsistenciaDto> getControlAsistenciaPersonal(FiltroMxEnfermoDto filtro)throws Exception{
        Session session = sessionFactory.getCurrentSession();
     /*   Query query = session.createQuery("select r.idRecepcion as idRecepcion, DATE_FORMAT(r.fechaRecepcion, '%d/%m/%Y') as fechaRecepcion, " +
                "r.codigo as participante, r.volumen as volumen, r.observacion as observacion, DATE_FORMAT(r.fis, '%d/%m/%Y') as fis, DATE_FORMAT(r.fif, '%d/%m/%Y') as fif, " +
                "r.categoria as categoria, (select m.spanish from MessageResource m where m.catRoot = 'CAT_TIPO_CONSULTA' and m.catKey = r.consulta) as consulta, " +
                "(select m.spanish from MessageResource m where m.catRoot = 'CAT_FASE_MX' and m.catKey = r.tipoMuestra) as tipoMuestra, r.enviado as enviado, r.codigoBarra as codigoBarra " +
                "from ControlAsistencia r inner join r.participante p where r.pasive = '0' " +

                (filtro.getFechaInicio()!=null  && filtro.getFechaFin() != null ? " and r.fechaRecepcion between :fechaInicio and :fechaFin ":" ")+
                "order by p.codigo asc");*/
        Query query = session.createSQLQuery("call sp_obtener_control_asistencia(:fechaInicio, :fechaFin)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
        }
        query.setResultTransformer(Transformers.aliasToBean(ControlAsistenciaDto.class));
        return query.list();
    }
    public List<ControlAsistenciaICSDto> getControlAsistenciaPersonalICS(FiltroMxEnfermoDto filtro)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_obtener_control_asistencia_ics(:fechaInicio, :fechaFin)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
        }
        query.setResultTransformer(Transformers.aliasToBean(ControlAsistenciaICSDto.class));
        return query.list();
    }
    public List<JustificacionesICSDto> getJustificacionesICS(FiltroMxEnfermoDto filtro)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_obtener_justificaciones_ics(:fechaInicio, :fechaFin)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
        }
        query.setResultTransformer(Transformers.aliasToBean(JustificacionesICSDto.class));
        return query.list();
    }
    public List<NombreEmpleadosICSDto> getNombreEmpleadosICS(String id,String idSitio)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_obtener_nombres_empleados_ics_sitios(:id,:idSitio)");
            query.setParameter("id", id);
            query.setParameter("idSitio", idSitio);
        query.setResultTransformer(Transformers.aliasToBean(NombreEmpleadosICSDto.class));
        return query.list();
    }
    public List<SitiosICSDto> getSitiosICS()throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_obtener_sitios_ics()");
        query.setResultTransformer(Transformers.aliasToBean(SitiosICSDto.class));
        return query.list();
    }
    public List<DepartamentosICSDto> getDepartamentosICS(String id)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_obtener_departamentos_ics(:id)");
        query.setParameter("id", id);
        query.setResultTransformer(Transformers.aliasToBean(DepartamentosICSDto.class));
        return query.list();
    }

    public List<ReporteHorasICSDto> getReporteHorasICS(FiltroMxEnfermoDto filtro,String id,String idSitio)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_imprimir_reporte_diario_horas_ics(:fechaInicio, :fechaFin,:id,:idSitio)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
            if (id.isEmpty()){
                id = "9999";
            }
            if (idSitio.isEmpty()){
                idSitio = "9999";
            }
            query.setParameter("id", id);
            query.setParameter("idSitio", idSitio);
        }
        query.setResultTransformer(Transformers.aliasToBean(ReporteHorasICSDto.class));
        return query.list();
    }
    public List<ReporteHorasICSDto> getReporteHorasSitiosICS(FiltroMxEnfermoDto filtro,String idSitio)throws Exception{
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("call sp_imprimir_reporte_diario_horas_ics_sitios(:fechaInicio, :fechaFin,:idSitio)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
            query.setParameter("idSitio", idSitio);
        }
        query.setResultTransformer(Transformers.aliasToBean(ReporteHorasICSDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public String getcodigoAsistencia()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_max_codigo_asistencia()");
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public String getverificaUsuarioHrEntrada(String usuario)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_verificar_hora_entrada_asistencia(:usuario)");
            query.setParameter("usuario", usuario);
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public String getverificaUsuarioHrSalida(String usuario)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_verificar_hora_salida_asistencia(:usuario)");
            query.setParameter("usuario", usuario);
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
}
