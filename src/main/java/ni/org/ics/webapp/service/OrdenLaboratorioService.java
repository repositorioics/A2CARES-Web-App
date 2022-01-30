package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.medico.OrdenLaboratorio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 27/1/2022.
 */
@Service("ordenLaboratorioService")
@Transactional
public class OrdenLaboratorioService {


    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<OrdenLaboratorio> getOrdenesLaboratorio(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrdenLaboratorio where pasive = '0' ");
        return query.list();
    }

    public void saveorUpdateOrdenLaboratorio(OrdenLaboratorio ordenLaboratorio){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(ordenLaboratorio);
    }
}
