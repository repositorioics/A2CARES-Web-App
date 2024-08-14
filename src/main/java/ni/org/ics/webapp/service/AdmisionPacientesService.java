package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.AdmisionPacientes;
import ni.org.ics.webapp.dto.AdmisionPacientesDto;
import ni.org.ics.webapp.dto.FiltroMxEnfermoDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Everts Morales Reyes
 */

@Service("admisionPacientesService")
@Transactional
public class AdmisionPacientesService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<AdmisionPacientes> getAmisionPacientes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from AdmisionPacientes where pasive = '0' ");
        return query.list();
    }

    public void saveOrUpdateAdmisionPacientes(AdmisionPacientes admisionPacientes) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(admisionPacientes);
    }

    @SuppressWarnings("unchecked")
    public List<AdmisionPacientesDto> getAdmisionPacientesListar(FiltroMxEnfermoDto filtro)throws Exception{
        Session session = sessionFactory.getCurrentSession();
     /*   Query query = session.createQuery("select r.idRecepcion as idRecepcion, DATE_FORMAT(r.fechaRecepcion, '%d/%m/%Y') as fechaRecepcion, " +
                "r.codigo as participante, r.volumen as volumen, r.observacion as observacion, DATE_FORMAT(r.fis, '%d/%m/%Y') as fis, DATE_FORMAT(r.fif, '%d/%m/%Y') as fif, " +
                "r.categoria as categoria, (select m.spanish from MessageResource m where m.catRoot = 'CAT_TIPO_CONSULTA' and m.catKey = r.consulta) as consulta, " +
                "(select m.spanish from MessageResource m where m.catRoot = 'CAT_FASE_MX' and m.catKey = r.tipoMuestra) as tipoMuestra, r.enviado as enviado, r.codigoBarra as codigoBarra " +
                "from ControlAsistencia r inner join r.participante p where r.pasive = '0' " +

                (filtro.getFechaInicio()!=null  && filtro.getFechaFin() != null ? " and r.fechaRecepcion between :fechaInicio and :fechaFin ":" ")+
                "order by p.codigo asc");*/
        Query query = session.createSQLQuery("call sp_obtener_admision_pacientes(:fechaInicio, :fechaFin)");

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            query.setParameter("fechaInicio", filtro.getFechaInicio());
            query.setParameter("fechaFin", filtro.getFechaFin());
        }
        query.setResultTransformer(Transformers.aliasToBean(AdmisionPacientesDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public String getidadmisionpacientes()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_max_id_admision_pacientes()");
            // query.setResultTransformer(Transformers.aliasToBean(String.class));
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public String getValidaNumeroHoja(Integer num_hoja, Date anio)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_valida_num_hoja(:num_hoja, :anio)");
            query.setParameter("num_hoja", num_hoja);
            query.setParameter("anio", anio);
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public String getverificaPacienteIngresado(String usuario)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("SELECT fn_verificar_paciente_ingresado(:usuario)");
            query.setParameter("usuario", usuario);
            return query.uniqueResult().toString();
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

}
