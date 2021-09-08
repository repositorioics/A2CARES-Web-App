package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.Tamizaje;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 * V1.0
 */
@Service("tamizajeService")
@Transactional
public class TamizajeService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<Tamizaje> getTamizajes()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Tamizaje ");
        return query.list();
    }

    public Tamizaje getTamizajeByCodigo(String codigo)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Tamizaje where codigo = :codigo");
        return (Tamizaje)query.uniqueResult();
    }

    public void saveOrUpdateTamizaje(Tamizaje tamizaje)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tamizaje);
    }
/*
    public List<EnfermedadCronica> getEnfermedadesCronicas()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EnfermedadCronica ");
        return query.list();
    }

    public void saveOrUpdateEnfermedadCronica(EnfermedadCronica enfermedadCronica)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(enfermedadCronica);
    }

    public List<EnfermedadCronica> getEnfermedadesCronicasByTamizaje(String tamizaje)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EnfermedadCronica where tamizaje.codigo = :tamizaje ");
        query.setParameter("tamizaje", tamizaje);
        return query.list();
    }*/
}
