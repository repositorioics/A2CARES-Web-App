package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.survey.EncuestaParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Transactional
@Service("encuestaParticipanteService")
public class EncuestaParticipanteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaParticipante> getEncuestasParticipante()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante where pasive = '0' ");
        return  query.list();
    }

    public void saveOrUpdate(EncuestaParticipante encuestaParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaParticipante);
    }
 }
