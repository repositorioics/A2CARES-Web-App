package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.RazonNoData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by miguel on 28/12/2021.
 */
@Transactional
@Service("razonNoDataService")
public class RazonNoDataService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(RazonNoData obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }
}
