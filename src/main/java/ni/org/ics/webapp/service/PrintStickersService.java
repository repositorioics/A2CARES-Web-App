package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.ControlSecCodigos;
import ni.org.ics.webapp.dto.CasasExistMuestreoDto;
import ni.org.ics.webapp.dto.RecepcionEnfermoDto;
import ni.org.ics.webapp.dto.ControlSecCodigosDto;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Everts Morales Reyes.
 * V1.0
 */
@Service("printstickersservice")
@Transactional
public class PrintStickersService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
	@SuppressWarnings("unchecked")
	public List<ControlSecCodigosDto> getControlSecCodigos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ControlSecCodigos");
      //  query.setResultTransformer(Transformers.aliasToBean(ControlSecCodigosDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<CasasExistMuestreoDto> getExtraeCasaMuestreo(String codigo_casa){
        try{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("call  fn_Get_Casa_Participantes_Muestreo(:codigo_casa)")
        .addScalar("indice", Hibernate.INTEGER)
        .addScalar("codigo_casa", Hibernate.STRING)
        .addScalar("codigo_participante", Hibernate.STRING);
        query.setParameter("codigo_casa", codigo_casa);
        query.setResultTransformer(Transformers.aliasToBean(CasasExistMuestreoDto.class));
        return query.list();
    }catch (Exception e){
        System.err.println(e.toString());
        throw e;
    }
    }

    @SuppressWarnings("unchecked")
    public List<String> getExtraeCasaMuestreo1(String codigo_casa){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("call  fn_Get_Casa_Participantes_Muestreo1(:codigo_casa)")
                .addScalar("codigo_participante", Hibernate.STRING);
        query.setParameter("codigo_casa", codigo_casa);
     //   query.setResultTransformer(Transformers.aliasToBean(CasasExistMuestreoDto.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public  List<ControlSecCodigosDto>  setIncCodPart(){
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call  fn_Set_Inc_Codigo_Participantes()");

        //query.setResultTransformer(Transformers.aliasToBean(CasasExistMuestreoDto.class));
        return query.list();
    }catch (Exception e){
            System.err.println(e.toString());
        throw e;
    }
    }

    @SuppressWarnings("unchecked")
    public  List<ControlSecCodigosDto> setIncCodCasa() {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call  fn_Set_Inc_Codigo_Casa()");
            return query.list();

    }catch (Exception e){
            System.err.println(e.toString());
        throw e;
    }

    }



}
