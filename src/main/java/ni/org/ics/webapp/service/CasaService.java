package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.Casa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 *
 * V1.0
 */
@Service("casaService")
@Transactional
public class CasaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    
	@SuppressWarnings("unchecked")
	public List<Casa> getCasas()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Casa ");
        return query.list();
    }

    public Casa getCasaByCodigo(Integer codigo)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Casa where codigo = :codigo");
        query.setParameter("codigo",codigo);
        return (Casa)query.uniqueResult();
    }

    public void saveOrUpdateCasa(Casa casa)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(casa);
    }

    /**
     * Regresa los datos de una casa
     *
     *
     * @return una <code>Casa</code>
     */

    @SuppressWarnings("unchecked")
    public List<Casa> getCasaParticipante (Integer codCasa) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Casa cas where cas.codigo = "+ codCasa);
        return  query.list();
    }

    /**
     * Verifica los datos de la casa
     *
     * @return true or false
     */

    public Boolean checkCasa(Integer codCasa) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Casa cas where cas.codigo = "+ codCasa);
        Casa casa = (Casa) query.uniqueResult();
        if(casa!=null){
            return true;
        }
        else{
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    public List<Object> getMensajesSms() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select mensaje_code, mensaje_show from mensajes_sms");
        // Retrieve all
        return  query.list();
    }

    public String getMensajeSms(Integer idMensaje){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select mensaje_text from mensajes_sms where mensaje_code=:idMensaje");
        query.setParameter("idMensaje", idMensaje);
        // Retrieve all
        return  (String) query.uniqueResult();
    }

    public String getApiKeySms(){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select api_key from sms_api_key");
        // Retrieve all
        return  (String) query.uniqueResult();
    }

    public String getProjectIdSms(){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select project_id from sms_project");
        // Retrieve all
        return  (String) query.uniqueResult();
    }

    public String getPhone1Sms(){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select phone1 from sms_phones");
        // Retrieve all
        return  (String) query.uniqueResult();
    }

    public String getPhone2Sms(){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("Select phone2 from sms_phones");
        // Retrieve all
        return  (String) query.uniqueResult();
    }
}
