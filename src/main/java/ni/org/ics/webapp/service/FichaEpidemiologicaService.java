package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.clinical.HojaClinica;
import ni.org.ics.webapp.dto.HojaClinicaDto;
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
 * Created by miguel on 1/12/2021.
 */
@Transactional
@Service("fichaEpidemiologicaService")
public class FichaEpidemiologicaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

  /*  public void saveOrUpdate(FichaEpidemiologica FichaEpidemiologica){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(FichaEpidemiologica);
    }

    public List<FichaEpidemiologica> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FichaEpidemiologica ");
        return query.list();
    }*/
/*
    public List<FichaEpidemiologicaDto> get(String codigoPart, String nro_Expediente, Date fechaInicioCons, Date fechaFinCons){
        Session session = sessionFactory.getCurrentSession();
        String strQuery = "select h.codigoParticipante.codigo as codigo, concat(h.codigoParticipante.nombre1, ' ', h.codigoParticipante.nombre2, ' ', h.codigoParticipante.apellido1, ' ', h.codigoParticipante.apellido2) as nombreCompleto," +
                " DATE_FORMAT(h.fechaConsulta, '%d/%m/%Y') as fechaConsulta, " +
                "(select p.spanish from MessageResource p where p.catKey = h.lugarAtencion and p.catRoot = 'CAT_LUGAR_CONS_HC') as lugarAtencion, " +
                "(select p.spanish from MessageResource p where p.catKey = h.consulta and p.catRoot = 'CAT_TIPO_CONSULTA') as tipoConsulta, " +
                "(select concat(cast(p.codigo as string), ' - ', p.nombre) from Personal p where p.codigo = h.medico) as medico, " +
                "(h.fis)  as Fis,  " +
                "(h.ftm)  as Ftm,  " +
                "(h.fif)  as Fif  " +
                "from FichaEpidemiologica h where 1=1";
        if (codigoPart != null) strQuery += " and h.codigoParticipante.codigo = :codigoPart";
        if (nro_Expediente != null) strQuery += " and h.nro_Expediente = :nro_Expediente";
        if (fechaInicioCons != null && fechaFinCons != null) strQuery += " and h.fechaConsulta between :fechaInicioCons and :fechaFinCons";
        Query query = session.createQuery(strQuery);
        if (codigoPart != null) query.setParameter("codigoPart", codigoPart);
        if (fechaInicioCons != null && fechaFinCons != null) {
            query.setParameter("fechaInicioCons", fechaInicioCons);
            query.setParameter("fechaFinCons", fechaFinCons);
        }
        query.setResultTransformer(Transformers.aliasToBean(FichaEpidemiologicaDto.class));

        return query.list();
    }*/
}
