package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.CambioDomicilio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ing. Santiago Carballo on 26/04/2023.
 */

@Service("cambioDomicilioService")
@Transactional
public class CambioDomicilioService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdateCambioDomicilio(CambioDomicilio cambioDomicilio) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cambioDomicilio);
    }

    @SuppressWarnings("unchecked")
    public List<CambioDomicilio> getCambioDomicilioByCodigo(String codigo)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CambioDomicilio where codigoParticipante = :codigo order by fechaRegistro desc ");
        query.setParameter("codigo",codigo);
        return  query.list();
    }
}
