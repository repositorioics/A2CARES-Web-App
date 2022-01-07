package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.ObsequioGeneral;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 04/01/2022.
 * V1.0
 */
@Service("obsequioService")
@Transactional
public class ObsequioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<ObsequioGeneral> getObsequios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ObsequioGeneral where pasive = '0' ");
        return query.list();
    }

    public void saveorUpdateObsequio(ObsequioGeneral obsequioGeneral){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obsequioGeneral);
    }
}
