package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.personal.Personal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 9/12/2021.
 */
@Service("personalService")
@Transactional
public class PersonalService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Personal> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal ");
        return query.list();
    }

    public List<Personal> getAllActives(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal where pasive = '0'");
        return query.list();
    }

    public Personal saveOrUpdate(Personal obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
        return obj;
    }

    //obtiene personal por idPersona
    public Personal getPersonalByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal where idPersona = :codigo");
        query.setParameter("codigo", codigo);
        return (Personal) query.uniqueResult();
    }

    public Personal getPersonalBySeq(Integer seq){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal where codigo = :seq");
        query.setParameter("seq", seq);
        return (Personal) query.uniqueResult();
    }

    public List<Personal> getByCargo(String cargo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.cargo.messageKey = :cargo");
        query.setParameter("cargo", cargo);
        return query.list();
    }

}
