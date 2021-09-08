package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.supervisor.RecepcionMuestra;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 2/9/2021.
 */
@Transactional
@Service("recepcionMuestraService")
public class RecepcionMuestraService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<RecepcionMuestra> getRecepcionesMuestras()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionMuestra where pasive = '0' ");
        return  query.list();
    }

    public void saveOrUpdate(RecepcionMuestra recepcionMuestra){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recepcionMuestra);
    }

}
