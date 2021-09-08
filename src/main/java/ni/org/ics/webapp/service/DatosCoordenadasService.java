package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.DatosCoordenadas;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 4/6/2021.
 */
@Service("datosCoordenadasService")
@Transactional
public class DatosCoordenadasService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    public List<DatosCoordenadas> getDatosCoordenadas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosCoordenadas tc where tc.pasive = '0'");
        return query.list();
    }

    public void saveOrUpdateDatosCoordenadas(DatosCoordenadas telefono){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(telefono);
    }
}
