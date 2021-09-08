package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.visitas.VisitaTerrenoParticipante;
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
@Service("visitaTerrenoService")
@Transactional
public class VisitaTerrenoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Crear o actualizar un VisitaTerrenoParticipante
     * @param visitaTerrenoParticipante
     */
    public void saveOrUpdateVisitaTerrenoParticipante(VisitaTerrenoParticipante visitaTerrenoParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaTerrenoParticipante);
    }

    @SuppressWarnings("unchecked")
    public List<VisitaTerrenoParticipante> getVisitasTerrenoParticipante(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaTerrenoParticipante ");
        return query.list();
    }

}
